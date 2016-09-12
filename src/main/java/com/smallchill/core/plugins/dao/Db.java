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
package com.smallchill.core.plugins.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.SQLReady;

import com.smallchill.core.aop.AopContext;
import com.smallchill.core.interfaces.IQuery;
import com.smallchill.core.toolbox.Paras;
import com.smallchill.core.toolbox.support.BladePage;

@SuppressWarnings("rawtypes")
public class Db {

	private static volatile DbManager dbManager = null;

	public static DbManager init(String name) {
		return DbManager.init(name);
	}

	private Db() {}
	
	private static DbManager getDbManager() {
		if (null == dbManager) {
			synchronized (Db.class) {
				dbManager = DbManager.init();
			}
		}
		return dbManager;
	}
	
	
	/************   ↓↓↓   ********     通用     *********   ↓↓↓   ****************/
	
	/**
	 * 直接执行sql语句，sql语句已经是准备好的，采用preparedstatment执行
	 * @param clazz
	 * @param p
	 * @return 返回查询结果
	 */
	public static <T> List<T> execute(SQLReady p, Class<T> clazz){
		return getDbManager().execute(p, clazz);
	}
	
	/** 直接执行sql语句，sql语句已经是准备好的，采用preparedstatment执行
	 * @param p
	 * @return 返回更新条数
	 */
	public static int executeUpdate(SQLReady p){
		return getDbManager().executeUpdate(p);
	}
	
	/**
	 * 根据sql新增数据
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static int insert(String sqlTemplate, Object modelOrMap){
		return getDbManager().insert(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 根据sql修改数据
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static int update(String sqlTemplate, Object modelOrMap){
		return getDbManager().update(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 根据sql删除数据
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static int delete(String sqlTemplate, Object modelOrMap){
		return getDbManager().delete(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 获取一条数据
	 * @param sqlTemplate	sql语句
	 * @return
	 */
	public static Map selectOne(String sqlTemplate){
		return getDbManager().selectOne(sqlTemplate);
	}
	
	/**
	 * 获取一条数据
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static Map selectOne(String sqlTemplate, Object modelOrMap){
		return getDbManager().selectOne(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 获取一条数据
	 * @param sqlTemplate	sql语句
	 * @return
	 */
	public static List<Map> selectList(String sqlTemplate){	
		return getDbManager().selectList(sqlTemplate);
	}
	
	/**
	 * 获取多条数据
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static List<Map> selectList(String sqlTemplate, Object modelOrMap){	
		return getDbManager().selectList(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 根据表名、主键获取一条数据
	 * @param tableName	表名
	 * @param pkValue	主键值
	 * @return
	 */
	public static Map findById(String tableName, String pkValue) {
		return getDbManager().findById(tableName, pkValue);
	}
	
	/**
	 * 根据表名、主键名、主键值获取一条数据
	 * @param tableName	表名
	 * @param pk		主键名
	 * @param pkValue	主键值
	 * @return
	 */
	public static Map findById(String tableName, String pk, String pkValue) {
		return getDbManager().findById(tableName, pk, pkValue);
	}
	
	/**
	 * 获取Integer
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static Integer queryInt(String sqlTemplate, Object modelOrMap){
		return getDbManager().queryInt(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 获取Integer集合
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static List<Integer> queryListInt(String sqlTemplate, Object modelOrMap){
		return getDbManager().queryListInt(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 获取字符串
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static String queryStr(String sqlTemplate, Object modelOrMap){
		return getDbManager().queryStr(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 获取字符串集合
	 * @param sqlTemplate	sql语句
	 * @param modelOrMap	实体类或map
	 * @return
	 */
	public static List<String> queryListStr(String sqlTemplate, Object modelOrMap){
		return getDbManager().queryListStr(sqlTemplate, modelOrMap);
	}
	
	/** 查询aop返回单条数据
	 * @param sqlTemplate
	 * @param modelOrMap
	 * @param ac
	 * @return
	 */
	public static Map selectOne(String sqlTemplate, Map<String, Object> param, AopContext ac) {
		return getDbManager().selectOne(sqlTemplate, param, ac);
	}
	
	/**查询aop返回多条数据
	 * @param sqlTemplate
	 * @param modelOrMap
	 * @param ac
	 * @return
	 */
	public static List<Map> selectList(String sqlTemplate, Map<String, Object> param, AopContext ac) {
		return getDbManager().selectList(sqlTemplate, param, ac);
	}
	
	/** 查询aop返回单条数据
	 * @param sqlTemplate
	 * @param modelOrMap
	 * @param ac
	 * @param intercept
	 * @return
	 */
	public static Map selectOne(String sqlTemplate, Map<String, Object> param, AopContext ac, IQuery intercept) {
		return getDbManager().selectOne(sqlTemplate, param, ac, intercept);
	}
	
	/**查询aop返回多条数据
	 * @param sqlTemplate
	 * @param modelOrMap
	 * @param ac
	 * @param intercept
	 * @return
	 */
	public static List<Map> selectList(String sqlTemplate, Map<String, Object> param, AopContext ac, IQuery intercept) {
		return getDbManager().selectList(sqlTemplate, param, ac, intercept);
	}
	
	/************   ↑↑↑   ********     通用     *********   ↑↑↑   ****************/
	
	/**
	 * 新增一条数据
	 * @param tableName	表名
	 * @param pk		主键名
	 * @param ps		参数
	 * @return
	 */
	public static int save(String tableName, String pk, Paras ps) {
		return getDbManager().save(tableName, pk, ps);
	}
	
	/**
	 * 修改一条数据
	 * @param tableName	表名
	 * @param pk		主键名
	 * @param ps		参数
	 * @return
	 */
	public static int update(String tableName, String pk, Paras ps) {
		return getDbManager().update(tableName, pk, ps);
	}
	
	/**
	 * 根据表名、字段名、值删除数据
	 * @param table	表名
	 * @param col	字段名
	 * @param ids	字段值集合(1,2,3)
	 * @return
	 */
	public static int deleteByIds(String table, String col, String ids) {
		return getDbManager().deleteByIds(table, col, ids);
	}
	
	
	/**
	 * 获取list
	 * @param model 实体类
	 * @param start 页号
	 * @param size	数量
	 * @return
	 */
	public static <T> List<T> getList(Object model, int start, int size) {
		return getDbManager().getList(model, start, size);
	}
	

	/**
	 * 获取list
	 * @param sqlTemplate sql语句
	 * @param clazz	返回类型
	 * @param paras	参数
	 * @param start	页号
	 * @param size	数量
	 * @return
	 */
	public static <T> List<T> getList(String sqlTemplate, Class<?> clazz, Object paras, int start, int size) {
		return getDbManager().getList(sqlTemplate, clazz, paras, start, size);
	}
	
	/**
	 * 分页
	 * @param sqlTemplate sql语句
	 * @param paras	参数
	 * @param start	页号
	 * @param size	数量
	 * @return
	 */
	public static <T> BladePage<T> paginate(String sqlTemplate, Object paras, int start, int size){
		return getDbManager().paginate(sqlTemplate, paras, start, size);
	}
	
	/**
	 * 分页
	 * @param sqlTemplate sql语句
	 * @param clazz	返回类型
	 * @param paras	参数
	 * @param start	页号
	 * @param size	数量
	 * @return
	 */
	public static <T> BladePage<T> paginate(String sqlTemplate, Class<?> clazz, Object paras, int start, int size){
		return getDbManager().paginate(sqlTemplate, clazz, paras, start, size);
	}
	
	/**
	 * 是否存在
	 * 
	 * @param sqlTemplate
	 * @param paras
	 * @return
	 */
	public static boolean isExist(String sqlTemplate, Object modelOrMap) {
		return getDbManager().isExist(sqlTemplate, modelOrMap);
	}
	
	/**
	 * 根据sqlId获取beetlsql的sql语句
	 * @param sqlId
	 * @return
	 */
	public static String getSql(String sqlId){
		return getDbManager().getSql(sqlId);
	}
}
