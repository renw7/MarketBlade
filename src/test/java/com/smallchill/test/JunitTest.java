package com.smallchill.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.smallchill.core.shiro.ShiroKit;
import com.smallchill.core.toolbox.kit.AESKit;
import com.smallchill.test.base.BaseTest;

public class JunitTest extends BaseTest{

	@After
	public void test1() {
		System.out.println("结束");
	}

	@Test
	public void test2() {
		System.out.println(AESKit.encrypt("com.mysql.jdbc.Driver"));
		System.out.println(AESKit.encrypt("jdbc:mysql://localhost:3306/blade?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true"));
		System.out.println(AESKit.encrypt("root"));
		System.out.println(AESKit.encrypt("root"));
		System.out.println(ShiroKit.md5("admin", "admin"));
	}

	@Before
	public void test3() {
		System.out.println("开始");
	}

}
