/**
 * Copyright (c) 2015-2017, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smallchill.core.shiro.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import redis.clients.util.SafeEncoder;

import com.smallchill.core.plugins.dao.Redis;
import com.smallchill.core.toolbox.redis.IJedis;
import com.smallchill.core.toolbox.redis.serializer.JdkSerializer;

/**
 * 缓存接口实现类
 */
public class RedisCache<K, V> implements Cache<K, V>, Serializable {

	private static final long serialVersionUID = 4521785299624111291L;

	private static Logger LOGGER = LogManager.getLogger(RedisCache.class);

	private String shiroName;
	
	private String keyPrefix;

	private String name = "redis_cache";

	
	public RedisCache(String shiroName, String keyPrefix, final String name) {
		this.shiroName = shiroName;
		this.keyPrefix = keyPrefix;
		this.name = name;
	}

	private final byte[] getKeyToBytes(K key) {
		if (key instanceof byte[]) {
			return (byte[]) key;
		}
		final byte[] preBytes = getPrefixToBytes();
		byte[] keyBytes = JdkSerializer.me.serialize(key);
		return JdkSerializer.me.mergeBytes(preBytes, keyBytes);
	}

	private final byte[] getPrefixToBytes() {
		return SafeEncoder.encode(getName().concat("_").concat(getKeyPrefix()));
	}

	private final byte[] getKeyPattern() {
		return SafeEncoder.encode(getName().concat("_").concat(getKeyPrefix()) + "*");
	}

	@Override
	public V get(K key) throws CacheException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("从缓存名 [" + getName() + "] 缓存主键 [" + key + "] 中获取缓存");
		}
		IJedis jedis = initJedis();
		try {
			if (key == null) {
				return null;
			} else {
				V value = jedis.get(getKeyToBytes(key));
				if (value == null) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("缓存主键: [" + key + "] 对应的值为空");
					}
					return null;
				} else {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("缓存主键: [" + key + "] 对应的值已存在,使用该缓存");
					}
					return value;
				}
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("向缓存名 [" + getName() + "] 缓存主键 [" + key + "] 内添加缓存");
		}
		IJedis jedis = initJedis();
		try {
			V previous = get(key);
			jedis.set(getKeyToBytes(key), value);
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("从缓存名 [" + getName() + "] 缓存主键 [" + key + "] 中删除缓存");
		}
		IJedis jedis = initJedis();
		try {
			V previous = get(key);
			long statusCode = jedis.del(getKeyToBytes(key));
			if (statusCode > 0) {
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("缓存主键 [{}] 删除成功", key);
				}
			}
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public void clear() throws CacheException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("清空所有缓存名 [" + getName() + "] 下的缓存");
		}
		IJedis jedis = initJedis();
		try {
			String statusCode = jedis.flushDB();
			if ("OK".equalsIgnoreCase(statusCode)) {
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("刷新 DB_{} 成功.", jedis.getDB());
				}
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public int size() {
		IJedis jedis = initJedis();
		try {
			long size = jedis.dbSize();
			return Long.valueOf(size).intValue();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<K> keys() {
		IJedis jedis = initJedis();
		try {
			Set<byte[]> keySet = jedis.keys(getKeyPattern());
			if (!CollectionUtils.isEmpty(keySet)) {
				Set<K> keys = new LinkedHashSet<K>();
				for (byte[] key : keySet) {
					keys.add((K) key);
				}
				return keys;
			} else {
				return Collections.emptySet();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public Collection<V> values() {
		try {
			Set<K> keys = keys();
			if (!CollectionUtils.isEmpty(keys)) {
				List<V> values = new ArrayList<V>(keys.size());
				for (K key : keys) {
					V value = get(key);
					if (value != null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}
	
	public IJedis initJedis() {
		return Redis.init(getShiroName());
	}

	@Override
	public String toString() {
		return "RedisCache [" + getName() + "]";
	}

	public String getShiroName() {
		return shiroName;
	}

	public void setShiroName(String shiroName) {
		this.shiroName = shiroName;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
} 
