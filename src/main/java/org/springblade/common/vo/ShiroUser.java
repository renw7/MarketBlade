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
package org.springblade.common.vo;

import org.springblade.common.tool.SysCache;
import org.springblade.core.plugins.dao.Db;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.Func;
import org.springblade.core.toolbox.kit.CollectionKit;
import org.springblade.core.toolbox.kit.StrKit;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhuangqian
 */
public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 6847303349754497231L;

    /**
     *  主键
     */
	private Object id;
    /**
     * 部门id
     */
	private Object deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 账号
     */
    private String loginName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 角色集
     */
    private List<Integer> roleList;
    /**
     * 角色集
     */
    private String roles;
    /**
     * 上级部门集合
     */
    private Object superDepts = "0";
    /**
     * 子部门集合
     */
    private Object subDepts = "0";
    /**
     * 子角色集合
     */
    private Object subRoles = "0";
    /**
     * 子账号集合
     */
    private Object subUsers = "0";


	@SuppressWarnings("rawtypes")
	public ShiroUser(Object id, Object deptId, String loginName, String name, List<Integer> roleList) {
		this.id = id;
		this.deptId = deptId;
		this.deptName = SysCache.getDeptName(deptId);
		this.loginName = loginName;
		this.name = name;
		this.roleList = roleList;
		this.roles = CollectionKit.join(roleList.toArray(), ",");

        /**
         * 递归查找子角色id集合
         */
		String roleSql;
		String subRoles = null;
		if (Func.isOracle()) {
			roleSql = "select wm_concat(ID) subRoles from (select ID,PID,NAME from blade_role start with ID in (#{join(roleIds)}) connect by prior ID=PID order by ID) a where a.ID not in (#{join(roleIds)})";
			subRoles = Db.queryStr(roleSql, CMap.init().set("roleIds", roleList));
		} else if (Func.isMySql()){
			StringBuilder sb = new StringBuilder();
			for (Integer roleid : roleList) {
				roleSql = "SELECT queryChildren(#{deptid},'blade_role') as subroles";
				String str = Db.queryStr(roleSql, CMap.init().set("deptid", roleid));
				sb.append(str).append(",");
			}
			subRoles = StrKit.removeSuffix(sb.toString(), ",");
		} else if (Func.isPostgresql()){
			roleSql = "select id from (with RECURSIVE cte as (select a.id,a.name,a.pid from blade_role as a where id in (#{join(roleIds)}) union all  select k.id,k.name,k.pid  from blade_role as k inner join cte as c on c.id = k.pid )select id,pid,name from cte) a where id not in (#{join(roleIds)})";
			List<Map> list = Db.selectList(roleSql, CMap.init().set("roleIds", roleList));
			StringBuilder sb = new StringBuilder();
			for (Map m : list) {
				sb.append(m.get("id")).append(",");
			}
            subRoles = StrKit.removeSuffix(sb.toString(), ",");
		}
		this.subRoles = subRoles;

	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getDeptId() {
		return deptId;
	}

	public void setDeptId(Object deptId) {
		this.deptId = deptId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Integer> roleList) {
		this.roleList = roleList;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Object getSuperDepts() {
		return superDepts;
	}

	public void setSuperDepts(Object superDepts) {
		this.superDepts = superDepts;
	}

	public Object getSubDepts() {
		return subDepts;
	}

	public void setSubDepts(Object subDepts) {
		this.subDepts = subDepts;
	}

	public Object getSubRoles() {
		return subRoles;
	}

	public void setSubRoles(Object subRoles) {
		this.subRoles = subRoles;
	}

	public Object getSubUsers() {
		return subUsers;
	}

	public void setSubUsers(Object subUsers) {
		this.subUsers = subUsers;
	}
}