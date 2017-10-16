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
package org.springblade.core.meta;

import org.springblade.core.aop.AopContext;
import org.springblade.core.toolbox.ajax.AjaxResult;

/**
 * 增删改查aop
 * @author zhuangqian
 */
public interface ICurd {
    /**
     * 主表新增前操作
     * @param ac 拦截器
     */
	void saveBefore(AopContext ac);

    /**
     * 主表新增后操作(事务内)
     * @param ac 拦截器
     * @return
     */
	boolean saveAfter(AopContext ac);

	/**
	 * 新增全部完毕后操作(事务外)
	 * @param ac 拦截器
     * @return
	 */
	AjaxResult saveSucceed(AopContext ac);

	/**
	 * 主表修改前操作
	 * @param ac 拦截器
	 */
	void updateBefore(AopContext ac);

	/**
	 * 主表修改后操作(事务内)
	 * @param ac 拦截器
     * @return
	 */
	boolean updateAfter(AopContext ac);

	/**
	 * 修改全部完毕后操作(事务外)
	 * @param ac 拦截器
     * @return
	 */
	AjaxResult updateSucceed(AopContext ac);

	/**
	 * 物理删除前操作
	 * @param ac
	 */
	void removeBefore(AopContext ac);

	/**
	 * 物理删除后操作(事务内)
	 * @param ac
     * @return
	 */
	boolean removeAfter(AopContext ac);

	/**
	 * 物理删除全部完毕后操作(事务外)
	 * @param ac
     * @return AjaxResult
	 */
	AjaxResult removeSucceed(AopContext ac);
	
	/**
	 * 逻辑删除前操作
	 * @param ac
	 */
	void delBefore(AopContext ac);

	/**
	 * 逻辑删除后操作(事务内)
	 * @param ac
     * @return
	 */
	boolean delAfter(AopContext ac);
	
	/**
	 * 逻辑删除后操作(事务外)
	 * @param ac
     * @return AjaxResult
	 */
	AjaxResult delSucceed(AopContext ac);
	
	/**
	 * 主表还原前操作
	 * @param ac
	 */
	void restoreBefore(AopContext ac);

	/**
	 * 主表还原后操作(事务内)
	 * @param ac
     * @return
	 */
	boolean restoreAfter(AopContext ac);
	
	/**
	 * 还原全部完毕后操作(事务外)
	 * @param ac
     * @return
	 */
	AjaxResult restoreSucceed(AopContext ac);
}
