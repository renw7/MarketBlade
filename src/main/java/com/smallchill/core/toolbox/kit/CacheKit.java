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

package com.smallchill.core.toolbox.kit;

import java.util.List;

import com.smallchill.core.constant.Cst;
import com.smallchill.core.interfaces.ICache;
import com.smallchill.core.interfaces.ILoader;

/**
 * 缓存工具类
 */
public class CacheKit {
	
	private static ICache defaultGridFactory = Cst.me().getDefaultCacheFactory();

	public static void put(String cacheName, Object key, Object value) {
		defaultGridFactory.put(cacheName, key, value);
	}
	
	public static <T> T get(String cacheName, Object key) {
		return defaultGridFactory.get(cacheName, key);
	}
	
	@SuppressWarnings("rawtypes")
	public static List getKeys(String cacheName) {
		return defaultGridFactory.getKeys(cacheName);
	}
	
	public static void remove(String cacheName, Object key) {
		defaultGridFactory.remove(cacheName, key);
	}
	
	public static void removeAll(String cacheName) {
		defaultGridFactory.removeAll(cacheName);
	}
	
	public static <T> T get(String cacheName, Object key, ILoader iLoader) {
		return defaultGridFactory.get(cacheName, key, iLoader);
	}
	
	public static <T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass) {
		return defaultGridFactory.get(cacheName, key, iLoaderClass);
	}
	
}


