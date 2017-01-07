package com.smallchill.core.toolbox.cache;

import java.util.ArrayList;
import java.util.List;

import com.smallchill.core.interfaces.ICache;
import com.smallchill.core.interfaces.ILoader;
import com.smallchill.core.plugins.dao.Redis;
import com.smallchill.core.toolbox.redis.IJedis;

/**
 * Redis缓存工厂
 */
public class RedisCacheFactory implements ICache {
	
	/**
	 * BladeConfig中注入的redis名称
	 */
	private String redisName;
	
	public String getRedisName() {
		return redisName;
	}

	public void setRedisName(String redisName) {
		this.redisName = redisName;
	}
	
	public RedisCacheFactory(String redisName) {
		super();
		this.redisName = redisName;
	}

	public IJedis getJedis() {
		return Redis.init(getRedisName());
	}
	
	public void put(String cacheName, Object key, Object value) {
		getJedis().hset(cacheName, key, value);
	}

	public <T> T get(String cacheName, Object key) {
		return getJedis().hget(cacheName, key);
	}

	@SuppressWarnings("rawtypes")
	public List getKeys(String cacheName) {
		return new ArrayList<>(getJedis().hkeys(cacheName));
	}

	public void remove(String cacheName, Object key) {
		getJedis().hdel(cacheName, key);
	}

	public void removeAll(String cacheName) {
		getJedis().del(cacheName);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key, ILoader iLoader) {
		Object data = get(cacheName, key);
		if (data == null) {
			data = iLoader.load();
			put(cacheName, key, data);
		}
		return (T) data;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass) {
		Object data = get(cacheName, key);
		if (data == null) {
			try {
				ILoader dataLoader = iLoaderClass.newInstance();
				data = dataLoader.load();
				put(cacheName, key, data);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return (T) data;
	}

}
