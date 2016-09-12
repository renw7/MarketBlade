/**
 * Copyright (c) 2011-2015, James Zhan 詹波 (jfinal@126.com).
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
package com.smallchill.core.base.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.smallchill.core.constant.ConstCache;
import com.smallchill.core.constant.ConstCurd;
import com.smallchill.core.toolbox.Paras;
import com.smallchill.core.toolbox.support.BeanInjector;
import com.smallchill.core.toolbox.support.WafRequestWrapper;

/**
 * @author James Zhan, Chill Zhuang
 */
public class JController implements ConstCurd, ConstCache {

	@Resource
	private HttpServletRequest request;
	
	protected HttpServletRequest getRequest() {
		return new WafRequestWrapper(this.request);
	}
	
	/** ============================     mapping    =================================================  */
	
	/**
	 * 表单值映射为javabean
	 * 
	 * @param beanClass
	 *            javabean.class
	 * @return T
	 */
	public <T> T mapping(Class<T> beanClass) {
		return paraInject(beanClass);
	}

	/**
	 * 表单值映射为javabean
	 * 
	 * @param paraPerfix
	 *            name前缀
	 * @param beanClass
	 *            javabean.class
	 * @return T
	 */
	public <T> T mapping(String paraPerfix, Class<T> beanClass) {
		return paraInject(beanClass, paraPerfix);
	}

	/**
	 * 表单值映射为Maps
	 * 
	 * @return Maps
	 */
	public Paras getParas() {
		return paraMapsInject();
	}

	/**
	 * 表单值映射为Maps
	 * 
	 * @param paraPerfix  name前缀
	 * @return Maps
	 */
	public Paras getParas(String paraPerfix) {
		return paraMapsInject(paraPerfix);
	}
	
	private <T> T paraInject(Class<T> beanClass) {
		return (T) BeanInjector.inject(beanClass, getRequest());
	}

	private <T> T paraInject(Class<T> beanClass, String paraPerfix) {
		return (T) BeanInjector.inject(beanClass, paraPerfix, getRequest());
	}
	
	private Paras paraMapsInject() {
		return BeanInjector.injectMaps(getRequest());
	}

	private Paras paraMapsInject(String paraPerfix) {
		return BeanInjector.injectMaps(paraPerfix, getRequest());
	}
	
}
