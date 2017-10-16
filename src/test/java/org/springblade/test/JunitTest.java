package org.springblade.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.springblade.core.shiro.ShiroKit;
import org.springblade.core.toolbox.kit.AesKit;
import org.springblade.test.base.BaseTest;

public class JunitTest extends BaseTest{

	@After
	public void test1() {
		System.out.println("结束");
	}

	@Test
	public void test2() {
		System.out.println(AesKit.encrypt("com.mysql.jdbc.Driver"));
		System.out.println(AesKit.encrypt("jdbc:mysql://localhost:3306/blade?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true"));
		System.out.println(AesKit.encrypt("root"));
		System.out.println(AesKit.encrypt("root"));
		System.out.println(ShiroKit.md5("admin", "admin"));
	}

	@Before
	public void test3() {
		System.out.println("开始");
	}

}
