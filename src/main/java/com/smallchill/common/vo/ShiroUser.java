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
package com.smallchill.common.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.smallchill.common.tool.SysCache;
import com.smallchill.core.constant.ConstCache;
import com.smallchill.core.constant.ConstCacheKey;
import com.smallchill.core.plugins.dao.Db;
import com.smallchill.core.toolbox.Func;
import com.smallchill.core.toolbox.CMap;
import com.smallchill.core.toolbox.cache.CacheKit;
import com.smallchill.core.toolbox.cache.ILoader;
import com.smallchill.core.toolbox.kit.CollectionKit;
import com.smallchill.core.toolbox.kit.StrKit;
import com.smallchill.core.toolbox.support.Convert;


public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 6847303349754497231L;
	
	private Object id;// 主键
	private Object deptId;// 部门id
	private String deptName;// 部门名称
	private String loginName;// 账号
	private String name;// 姓名
	private List<Integer> roleList;// 角色集
	private String roles;// 角色集
	private Object superDepts;// 上级部门集合
	private Object subDepts;// 子部门集合
	private Object subRoles;// 子角色集合
	private Object subUsers;// 子账号集合

	@SuppressWarnings("rawtypes")
	public ShiroUser(Object id, Object deptId, String loginName, String name, List<Integer> roleList) {
		this.id = id;
		this.deptId = deptId;
		this.deptName = SysCache.getDeptName(deptId);
		this.loginName = loginName;
		this.name = name;
		this.roleList = roleList;
		this.roles = CollectionKit.join(roleList.toArray(), ",");
		
		// 递归查找上级部门id集合
		String superDeptSql;
		String superDepts = null;
		if (Func.isOracle()) {
			superDeptSql = "select wm_concat(ID) subDepts from (select ID,PID,SIMPLENAME from blade_dept start with ID in (#{join(deptIds)}) connect by prior PID=ID order by ID) a where a.ID not in (#{join(deptIds)})";
			superDepts = Db.queryStr(superDeptSql, CMap.init().set("deptIds", Convert.toIntArray(deptId.toString())));
		} else if (Func.isMySql()){
			String[] arr = deptId.toString().split(",");
			StringBuilder sb = new StringBuilder();
			for (String deptid : arr) {
				superDeptSql = "select queryParent(#{deptid},'blade_dept') as superdepts";
				String str = Db.queryStr(superDeptSql, CMap.init().set("deptid", deptid));
				sb.append(str).append(",");
			}
			superDepts = StrKit.removeSuffix(sb.toString(), ",");
		} else if (Func.isPostgresql()){
			superDeptSql = "select id from (with RECURSIVE cte as (select a.id,a.simplename,a.pid from blade_dept as a where id in (#{join(deptIds)}) union all  select k.id,k.simplename,k.pid  from blade_dept as k inner join cte as c on c.pid = k.id )select id,pid,simplename from cte) a where id not in (#{join(deptIds)})";
			List<Map> list = Db.selectList(superDeptSql, CMap.init().set("deptIds", Convert.toIntArray(deptId.toString())));
			StringBuilder sb = new StringBuilder();
			for (Map m : list) {
				sb.append(m.get("id")).append(",");
			}
			superDepts = StrKit.removeSuffix(sb.toString(), ",");
		}
		this.superDepts = superDepts;
		
		// 递归查找子部门id集合
		String subDeptSql;
		String subDepts = null;
		if (Func.isOracle()) {
			subDeptSql = "select wm_concat(ID) subDepts from (select ID,PID,SIMPLENAME from blade_dept start with ID in (#{join(deptIds)}) connect by prior ID=PID order by ID) a where a.ID not in (#{join(deptIds)})";
			subDepts = Db.queryStr(subDeptSql, CMap.init().set("deptIds", Convert.toIntArray(deptId.toString())));
		} else if (Func.isMySql()){
			String[] arr = deptId.toString().split(",");
			StringBuilder sb = new StringBuilder();
			for (String deptid : arr) {
				subDeptSql = "select queryChildren(#{deptid},'blade_dept') as subdepts";
				String str = Db.queryStr(subDeptSql, CMap.init().set("deptid", deptid));
				sb.append(str).append(",");
			}
			subDepts = StrKit.removeSuffix(sb.toString(), ",");
		} else if (Func.isPostgresql()){
			subDeptSql = "select id from (with RECURSIVE cte as (select a.id,a.simplename,a.pid from blade_dept as a where id in (#{join(deptIds)}) union all  select k.id,k.simplename,k.pid  from blade_dept as k inner join cte as c on c.id = k.pid )select id,pid,simplename from cte) a where id not in (#{join(deptIds)})";
			List<Map> list = Db.selectList(subDeptSql, CMap.init().set("deptIds", Convert.toIntArray(deptId.toString())));
			StringBuilder sb = new StringBuilder();
			for (Map m : list) {
				sb.append(m.get("id")).append(",");
			}
			superDepts = StrKit.removeSuffix(sb.toString(), ",");
		}
		this.subDepts = subDepts;
		
		// 递归查找子角色id集合
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
			superDepts = StrKit.removeSuffix(sb.toString(), ",");
		}
		this.subRoles = subRoles;
		
		// 查找子角色对应账号id集合
		List<Map<String, Object>> listUser = CacheKit.get(ConstCache.SYS_CACHE, ConstCacheKey.USER_ALL_LIST, new ILoader() {
			@Override
			public Object load() {
				return Db.selectList("SELECT * FROM blade_user where status = 1 and name is not null");
			}
		});
		
		String[] subrolestr = Func.toStr(this.subRoles).split(",");
		StringBuilder sbUser = new StringBuilder();
		for (Map<String, Object> map : listUser) {
			for (String str : subrolestr) {
				if (Func.toStr(map.get("ROLEID")).indexOf(str) >= 0 && (("," + sbUser.toString() + ",").indexOf("," + Func.toStr(map.get("ID")) + ",") == -1)) {
					Func.builder(sbUser, Func.toStr(map.get("ID")) + ",");
				}
			}
		}
		
		this.subUsers = StrKit.removeSuffix(sbUser.toString(), ","); 
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