package com.smallchill.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.smallchill.core.shiro.ShiroKit;
import com.smallchill.test.base.BaseTest;

public class JunitTest extends BaseTest{

	@After
	public void test1() {
		System.out.println("结束");
	}

	@Test
	public void test2() {
		//host=192.168.243.128, port=6379, timeout=2000, database=7, password=null, clientName=shiro-cache
		
		JedisPoolConfig c = new JedisPoolConfig();
		c.setMaxIdle(200);
		c.setMaxTotal(1000);
		c.setMaxWaitMillis(10000);
		c.setTestOnBorrow(true);
		c.setTestOnReturn(true);
		
		JedisPool p = new JedisPool(c, "192.168.243.128", 6379, 2000, null, 7, "shiro-cache");
		
		Jedis resource = p.getResource();
		System.out.println(resource.keys("*"));
		resource.close();
		p.close();
		
	
		
		
		System.out.println(ShiroKit.md5("admin", "admin"));
	}

	@Before
	public void test3() {
		System.out.println("开始");
	}

}
