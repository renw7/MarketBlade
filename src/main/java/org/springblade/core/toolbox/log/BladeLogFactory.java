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

import org.springblade.common.vo.ShiroUser;
import org.springblade.core.constant.Const;
import org.springblade.core.constant.ConstCache;
import org.springblade.core.constant.ConstCacheKey;
import org.springblade.core.plugins.dao.Blade;
import org.springblade.core.plugins.dao.Db;
import org.springblade.core.shiro.ShiroKit;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.cache.CacheKit;
import org.springblade.core.toolbox.cache.ILoader;
import org.springblade.system.model.OperationLog;

import java.util.Date;
import java.util.Map;

/**
 * 系统默认日志记录
 * @author zhuangqian
 */
public class BladeLogFactory implements ILog {

	@Override
    public String[] logPatten() {
		String[] patten = { "login", "logout", "grant", "save", "update", "remove", "del", "delete", "restore", "change" };
		return patten;
	}

	@Override
    public CMap logMaps() {
		CMap cmap = CMap.init()
				.set("login", "登录")
				.set("logout", "登出")
				.set("grant", "授权")
				.set("save", "新增")
				.set("update", "修改")
				.set("remove", "删除")
				.set("del", "删除")
				.set("delete", "删除")
				.set("restore", "还原")
				.set("restore", "变更");
		return cmap;
	}

	@Override
    public boolean isDoLog() {
		@SuppressWarnings("rawtypes")
		Map map = CacheKit.get(ConstCache.SYS_CACHE, ConstCacheKey.PARAMETER_LOG, new ILoader() {
			@Override
			public Object load() {
				return Db.selectOne("select para from blade_parameter where code = #{code}", CMap.init().set("code", Const.PARA_LOG_CODE));
			}
		}); 
		if(map.get("para").equals("1")){
			return true;
		}
		return false;
	}
	
	@Override
    public boolean doLog(String logName, String msg, boolean succeed) {
		ShiroUser user = ShiroKit.getUser();
		if (null == user) {
			return true;
		}
		try {
			OperationLog log = new OperationLog();
			log.setMethod(msg);
			log.setCreatetime(new Date());
			log.setSucceed((succeed) ? "1" : "0");
			log.setUserid(Func.toStr(user.getId()));
			log.setLogname(logName);
			boolean temp = Blade.create(OperationLog.class).save(log);
			return temp;
		} catch (Exception ex) {
			return false;
		}
	}

}
