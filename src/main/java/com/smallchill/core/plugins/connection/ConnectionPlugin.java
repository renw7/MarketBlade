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

import com.smallchill.core.config.BladeConfig;
import com.smallchill.core.interfaces.IPlugin;
import com.smallchill.core.toolbox.kit.LogKit;
import com.smallchill.core.toolbox.redis.IJedis;
import com.smallchill.core.toolbox.redis.IKeyNamingPolicy;
import com.smallchill.core.toolbox.redis.RedisCluster;
import com.smallchill.core.toolbox.redis.RedisSingle;
import com.smallchill.core.toolbox.redis.serializer.JdkSerializer;

import org.beetl.sql.core.IDAutoGen;
import org.beetl.sql.core.SQLManager;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionPlugin implements IPlugin{

	private static Map<String, SQLManager> sqlManagerPool = new ConcurrentHashMap<String, SQLManager>();
	private static Map<String, IJedis> redisCachePool = new ConcurrentHashMap<String, IJedis>();
	
	public String MASTER = "master";
	
	public Map<String, SQLManager> getSqlManagerPool(){
		return sqlManagerPool;
	}
	
	public Map<String, IJedis> getRedisCachePool(){
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
			
			//注入redisSingle
			for(String key : BladeConfig.getJedisPool().keySet()){
				JedisPool jedisPool = BladeConfig.getJedisPool().get(key);
				//创建redis单机操作类
				RedisSingle rs = new RedisSingle(key, jedisPool, JdkSerializer.me, IKeyNamingPolicy.defaultKeyNamingPolicy);
				redisCachePool.put(key, rs);
			}
			//注入redisCluster
			for(String key : BladeConfig.getJedisCluster().keySet()){
				JedisCluster jedisCluster = BladeConfig.getJedisCluster().get(key);
				//创建redis集群操作类
				RedisCluster rc = new RedisCluster(key, jedisCluster, JdkSerializer.me, IKeyNamingPolicy.defaultKeyNamingPolicy);
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
		for (IJedis jedis : redisCachePool.values()) {
			jedis.close();
		}
		redisCachePool.clear();
		sqlManagerPool.clear();
		LogKit.println("ConnectionPlugin关闭成功");
	}

}
