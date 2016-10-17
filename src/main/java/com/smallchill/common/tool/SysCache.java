package com.smallchill.common.tool;

import com.smallchill.core.constant.ConstCache;
import com.smallchill.core.interfaces.ILoader;
import com.smallchill.core.plugins.dao.Blade;
import com.smallchill.core.toolbox.Func;
import com.smallchill.core.toolbox.Paras;
import com.smallchill.core.toolbox.kit.CacheKit;
import com.smallchill.core.toolbox.kit.StrKit;
import com.smallchill.system.model.Dept;
import com.smallchill.system.model.Dict;
import com.smallchill.system.model.Parameter;
import com.smallchill.system.model.Role;
import com.smallchill.system.model.User;

public class SysCache {
	/**
	 * 获取字典表对应中文
	 * @param code 字典编号
	 * @param num  字典序号
	 * @return
	 */
	public static String getDictName(final Object code, final Object num) {
		Dict dict = CacheKit.get(ConstCache.DICT_CACHE, "getDictName_" + code + "_" + num, new ILoader() {
			@Override
			public Object load() {
				return Blade.create(Dict.class).findFirstBy("code=#{code} and num=#{num}", Paras.create().set("code", code).set("num", num));
			}
		});
		if(null == dict){
			return "";
		}
		return dict.getName();
	}

	/**
	 * 获取对应角色名
	 * @param roleId 角色id
	 * @return
	 */
	public static String getRoleName(final Object roleIds) {
		if(Func.isEmpty(roleIds)){
			return "";
		}
		final String [] roleIdArr = roleIds.toString().split(",");
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < roleIdArr.length; i++){
			final String roleId = roleIdArr[i];
			Role role = CacheKit.get(ConstCache.ROLE_CACHE, "getRoleName" + "_" + roleId, new ILoader() {
				@Override
				public Object load() {
					return Blade.create(Role.class).findById(roleId);
				}
			});
			sb.append(role.getName()).append(",");
		}
		return StrKit.removeSuffix(sb.toString(), ",");
	}

	/**
	 * 获取对应角色别名
	 * @param roleId 角色id
	 * @return
	 */
	public static String getRoleAlias(final Object roleIds) {
		if(Func.isEmpty(roleIds)){
			return "";
		}
		final String [] roleIdArr = roleIds.toString().split(",");
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < roleIdArr.length; i++){
			final String roleId = roleIdArr[i];
			Role role = CacheKit.get(ConstCache.ROLE_CACHE, "getRoleAlias" + "_" + roleId, new ILoader() {
				@Override
				public Object load() {
					return Blade.create(Role.class).findById(roleId);
				}
			});
			sb.append(role.getTips()).append(",");
		}
		return StrKit.removeSuffix(sb.toString(), ",");
	}

	/**
	 * 获取对应用户名
	 * @param userId 用户id
	 * @return
	 */
	public static String getUserName(final Object userId) {
		User user = CacheKit.get(ConstCache.USER_CACHE, "getUserName" + "_" + userId, new ILoader() {
			@Override
			public Object load() {
				return Blade.create(User.class).findById(userId);
			}
		});
		if(null == user){
			return "";
		}
		return user.getName();
	}

	/**
	 * 获取对应部门名
	 * @param deptIds 部门id集合
	 * @return
	 */
	public static String getDeptName(final Object deptIds) {
		if(Func.isEmpty(deptIds)){
			return "";
		}
		final String [] deptIdArr = deptIds.toString().split(",");
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < deptIdArr.length; i++){
			final String deptId = deptIdArr[i];
			Dept dept = CacheKit.get(ConstCache.DEPT_CACHE, "getDeptName" + "_" + deptId, new ILoader() {
				@Override
				public Object load() {
					return Blade.create(Dept.class).findById(deptId);
				}
			});
			sb.append(dept.getSimplename()).append(",");
		}
		return StrKit.removeSuffix(sb.toString(), ",");
	}
	
	/**   
	 * 获取参数表参数值
	 * @param code 参数编号
	 * @return String
	*/
	public static String getParamByCode(String code){
		Parameter param = Blade.create(Parameter.class).findFirstBy("code = #{code} and status = 1", Paras.create().set("code", code));
		return param.getPara();
	}
}
