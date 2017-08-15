package org.springblade.common.task;

import org.springblade.core.toolbox.kit.DateKit;

public class GlobalTask implements Runnable {

	@Override
	public void run() {
		
		System.out.println("任务调度执行:" + DateKit.getTime());
	}

}
