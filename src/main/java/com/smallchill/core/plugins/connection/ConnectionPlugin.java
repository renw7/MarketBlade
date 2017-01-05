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
package com.smallchill.core.plugins.connection;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.SQLManager;

import redis.clients.jedis.JedisPool;

import com.smallchill.core.config.BladeConfig;
import com.smallchill.core.interfaces.IPlugin;
import com.smallchill.core.toolbox.redis.RedisCache;
import com.smallchill.core.toolbox.redis.IKeyNamingPolicy;
import com.smallchill.core.toolbox.redis.serializer.RedisSerializer;

public class ConnectionPlugin implements IPlugin{

	private static Map<String, SQLManager> sqlManagerPool = new ConcurrentHashMap<String, SQLManager>();
	private static Map<String, RedisCache> redisCachePool = new ConcurrentHashMap<String, RedisCache>();
	
	public String MASTER = "master";
	
	public Map<String, SQLManager> getSqlManagerPool(){
		return sqlManagerPool;
	}
	
	public Map<String, RedisCache> getRedisCachePool(){
		return redisCachePool;
	}
	
	private ConnectionPlugin() { }
	
	private static ConnectionPlugin me = new ConnectionPlugin();
	
	public static ConnectionPlugin init(){
		return me;
	}
	
	public void start() {
		try {
			//注入sqlmanager
			for(String key : BladeConfig.getSqlManagerPool().keySet()){
				SQLManager sm = BladeConfig.getSqlManagerPool().get(key);
				//增加自定义@AssignID注解的值, 使用方式: @Assign("uuid")
				sm.addIdAutonGen("uuid", new IDAutoGen<String>() {
					public String nextID(String arg0) {
						return UUID.randomUUID().toString();
					}
				});
				sqlManagerPool.put(key, sm);
			}
			if(!sqlManagerPool.containsKey(MASTER)){
				throw new RuntimeException("BladeConfig必须注入key值为master的sqlManager!");
			}
			
			//注入redis
			for(String key : BladeConfig.getJedisPool().keySet()){
				JedisPool jedisPool = BladeConfig.getJedisPool().get(key);
				//创建redis通用cache操作类
				RedisCache rc = new RedisCache(key, jedisPool, RedisSerializer.me, IKeyNamingPolicy.defaultKeyNamingPolicy);
				redisCachePool.put(key, rc);
			}
			if(!redisCachePool.containsKey(MASTER) && redisCachePool.size() > 0){
				throw new RuntimeException("BladeConfig必须注入key值为master的jedisPool!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		sqlManagerPool.clear();
		redisCachePool.clear();
	}

}
