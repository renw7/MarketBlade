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
package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;

import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

@Table(name = "blade_role_ext")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 权限代理表
 * @author zhuangqian
 */
public class RoleExt extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 额外附加的权限
     */
	private String rolein;
    /**
     * 额外剔除的权限
     */
	private String roleout;
    /**
     * 用户id
     */
	private Integer userid;

	@AutoID
	@SeqID(name = "SEQ_ROLE_EXT")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolein() {
		return rolein;
	}

	public void setRolein(String rolein) {
		this.rolein = rolein;
	}

	public String getRoleout() {
		return roleout;
	}

	public void setRoleout(String roleout) {
		this.roleout = roleout;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
