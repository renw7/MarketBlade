/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
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
package com.smallchill.system.meta.intercept;

import com.smallchill.core.aop.Invocation;
import com.smallchill.core.constant.ConstShiro;
import com.smallchill.core.intercept.BladeValidator;
import com.smallchill.core.plugins.dao.Db;
import com.smallchill.core.shiro.ShiroKit;
import com.smallchill.core.toolbox.Paras;
import com.smallchill.core.toolbox.kit.CollectionKit;
import com.smallchill.core.toolbox.kit.StrKit;

public class RoleValidator extends BladeValidator {

	@Override
	protected void doValidate(Invocation inv) {
		validateRole("ids", "超级管理员不能去掉角色管理的权限!");
	}

	protected void validateRole(String field, String errorMessage) {
		String ids = request.getParameter(field);
		if (StrKit.isBlank(ids)) {
			addError("请选择权限!");
		} else if(ShiroKit.hasRole(ConstShiro.ADMINISTRATOR)){
			String[] id = ids.split(",");
			String roleAuthory = Db.queryStr("select id from tfw_menu where code = #{code}", Paras.create().set("code", "role_authority"));
			if(!CollectionKit.contains(id, roleAuthory)){
				//超管不包含权限配置则报错
				addError(errorMessage);
			}
		}
	}

}
