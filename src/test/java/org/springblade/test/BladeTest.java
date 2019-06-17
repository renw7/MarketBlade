package org.springblade.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringRunner;
import org.springblade.modules.desk.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Blade单元测试
 *
 * @author Chill
 */
@RunWith(BladeSpringRunner.class)
@BladeBootTest(appName = "blade-runner", profile = "test")
public class BladeTest {

	@Autowired
	private INoticeService noticeService;

	@Test
	public void contextLoads() {
		int count = noticeService.count();
		System.out.println("notice数量：[" + count + "] 个");
	}

}
