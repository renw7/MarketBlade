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
package org.springblade.system.meta.factory;

import org.springblade.core.meta.MetaIntercept;
import org.springblade.core.meta.MetaManager;
import org.springblade.system.meta.intercept.LogIntercept;
import org.springblade.system.model.OperationLog;

import java.util.HashMap;
import java.util.Map;

/**
 * OLogFactory
 * @author zhuangqian
 */
public class OLogFactory extends MetaManager {

	@Override
    public Class<? extends MetaIntercept> intercept() {
		return LogIntercept.class;
	}
	
	@Override
    public String controllerKey() {
		return "olog";
	}

	@Override
    public String paraPrefix() {
		return getTableName(OperationLog.class);
	}

	@Override
    public Map<String, String> renderMap() {
		Map<String, String> renderMap = new HashMap<>();
		renderMap.put(KEY_INDEX, "/system/log/olog.html");
		renderMap.put(KEY_ADD, "/system/log/olog_add.html");
		renderMap.put(KEY_EDIT, "/system/log/olog_edit.html");
		renderMap.put(KEY_VIEW, "/system/log/olog_view.html");
		return renderMap;
	}

	@Override
    public Map<String, String> sourceMap() {
		Map<String, String> sourceMap = new HashMap<>();
		sourceMap.put(KEY_INDEX, "olog.sourceList");
		return sourceMap;
	}

}
