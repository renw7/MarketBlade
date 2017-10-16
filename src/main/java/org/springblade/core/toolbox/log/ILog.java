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
package org.springblade.core.toolbox.log;

import org.springblade.core.toolbox.CMap;

/**
 *  日志记录接口
 *  @author zhuangqian
 */
public interface ILog {
	
	/**
	 * 定义日志拦截的方法
     * @return
	 */
	String[] logPatten();
	
	/**
	 * 定义日志拦截的方法名
     * @return
     */
	CMap logMaps();
	
	/**
	 * 是否需要记录日志
     * @return
     */
	boolean isDoLog();

    /**
     * 日志记录
     * @param logName
     * @param msg
     * @param succeed
     * @return
     */
	boolean doLog(String logName, String msg, boolean succeed);
}
