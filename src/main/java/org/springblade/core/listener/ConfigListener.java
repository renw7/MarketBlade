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
package org.springblade.core.listener;

import org.springblade.core.constant.Const;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.kit.ObjectKit;
import org.springblade.core.toolbox.kit.PropKit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置监听
 * @author zhuangqian
 */
public class ConfigListener implements ServletContextListener {

	private static Map<String, String> conf = new HashMap<>();

	public static Map<String, String> getConf() {
		return ObjectKit.clone(conf);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		conf.clear();
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		ServletContext sc = evt.getServletContext();

		conf.put("realPath", sc.getRealPath("/"));
		conf.put("contextPath", sc.getContextPath());

		Properties prop = PropKit.use(Const.PROPERTY_FILE).getProperties();
		for (Object name : prop.keySet()) {
			conf.put(name.toString(), Func.toStr(prop.get(name)));
		}
	}

}
