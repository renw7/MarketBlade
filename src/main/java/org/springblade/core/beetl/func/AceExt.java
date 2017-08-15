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
package org.springblade.core.beetl.func;

import java.util.HashMap;
import java.util.Map;

import org.springblade.core.constant.ConstCache;
import org.springblade.core.constant.ConstCacheKey;
import org.springblade.core.shiro.ShiroKit;
import org.springblade.core.toolbox.cache.CacheKit;
import org.springblade.core.toolbox.cache.ILoader;


/**
 * ace工具类
 */
public class AceExt {

	public String theme() {
		if (null == ShiroKit.getUser()) {
			return "ace-dark.css";
		}
		Map<String, String> theme = CacheKit.get(ConstCache.SYS_CACHE, ConstCacheKey.ACE_THEME + ShiroKit.getUser().getId(), new ILoader() {
			public Object load() {
				Map<String, String> map = new HashMap<String, String>();
				map.put("ace", "ace-dark.css");
				return map;
			}
		});
		return theme.get("ace");
	}

}
