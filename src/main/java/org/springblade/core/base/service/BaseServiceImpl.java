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
package org.springblade.core.base.service;

import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstCurd;
import org.springblade.core.constant.Cst;
import org.springblade.core.meta.ICurd;
import org.springblade.core.plugins.dao.Blade;
import org.springblade.core.toolbox.grid.BladePage;
import org.springblade.core.toolbox.kit.ClassKit;

import java.util.List;

/**
 * 业务基类
 * @author zhuangqian
 * @param <M>
 */
@SuppressWarnings({ "unchecked" })
public class BaseServiceImpl<M> implements IService<M>, ConstCurd {
	
	private Class<M> modelClass;
	private Blade dao;

	private void setModelClass(Class<M> modelClass) {
		this.modelClass = modelClass;
	}

	public BaseServiceImpl() {
		this.setModelClass(ClassKit.getSuperClassGenricFirstType(this.getClass()));
	}

	private Blade getSqlMananger() {
		if(null == dao){
			synchronized (BaseServiceImpl.class) {
				if(null == dao){
					dao = Blade.create(modelClass);
				}
			}
		}
		return dao;
	}

	@Override
	public M findById(Object id) {
		Blade dao = getSqlMananger();
		return dao.findById(id);
	}

    @Override
	public List<M> find(String sqlTemplate, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.find(sqlTemplate, modelOrMap);
	}

    @Override
	public List<M> findTop(int topNum, M model) {
		Blade dao = getSqlMananger();
		return dao.findTop(topNum, model);
	}

    @Override
	public List<M> findTop(int topNum, String sqlTemplate) {
		Blade dao = getSqlMananger();
		return dao.findTop(topNum, sqlTemplate);
	}

    @Override
	public List<M> findTop(int topNum, String sqlTemplate, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.findTop(topNum, sqlTemplate, modelOrMap);
	}

    @Override
	public List<M> findAll() {
		Blade dao = getSqlMananger();
		return dao.findAll();
	}

    @Override
	public List<M> findBy(String where, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.findBy(where, modelOrMap);
	}

    @Override
	public List<M> findBy(String columns, String where, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.findBy(columns, where, modelOrMap);
	}

    @Override
	public List<M> findByTemplate(M model) {
		Blade dao = getSqlMananger();
		return dao.findByTemplate(model);
	}

    @Override
	public M findFirst(String sqlTemplate, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.findFirst(sqlTemplate, modelOrMap);
	}

    @Override
	public M findFirstBy(String where, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.findFirstBy(where, modelOrMap);
	}

    @Override
	public boolean save(M model) {
		Blade dao = getSqlMananger();
		return dao.save(model);
	}

    @Override
	public int saveRtId(M model) {
		Blade dao = getSqlMananger();
		return dao.saveRtId(model);
	}

    @Override
	public String saveRtStrId(M model) {
		Blade dao = getSqlMananger();
		return dao.saveRtStrId(model);
	}

    @Override
	public boolean saveAndSetKey(M model){
		Blade dao = getSqlMananger();
		return dao.saveAndSetKey(model);
	}

    @Override
	public void saveBatch(List<?> list) {
		Blade dao = getSqlMananger();
		dao.saveBatch(list);
	}

    @Override
	public boolean update(M model) {
		Blade dao = getSqlMananger();
		return dao.update(model);
	}

    @Override
	public boolean updateEveryCol(M model) {
		Blade dao = getSqlMananger();
		return dao.updateEveryCol(model);
	}

    @Override
	public int updateAllRecords(M model) {
		Blade dao = getSqlMananger();
		return dao.updateAllRecords(model);
	}

    @Override
	public boolean updateBy(String set, String where, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.updateBy(set, where, modelOrMap);
	}

    @Override
	public int[] updateBathById(List<M> list) {
		Blade dao = getSqlMananger();
		return dao.updateBathById(list);
	}

    @Override
	public int delete(Object id) {
		Blade dao = getSqlMananger();
		return dao.delete(id);
	}

    @Override
	public int deleteByIds(String ids) {
		Blade dao = getSqlMananger();
		return dao.deleteByIds(ids);
	}

    @Override
	public int deleteByCols(String col, String ids) {
		Blade dao = getSqlMananger();
		return dao.deleteByCols(col, ids);
	}

    @Override
	public int deleteTableByCols(String table, String col, String ids) {
		Blade dao = getSqlMananger();
		return dao.deleteTableByCols(table, col, ids);
	}

    @Override
	public int deleteByStrIds(String ids) {
		Blade dao = getSqlMananger();
		return dao.deleteByStrIds(ids);
	}

    @Override
	public int deleteByStrCols(String col, String ids) {
		Blade dao = getSqlMananger();
		return dao.deleteByStrCols(col, ids);
	}

    @Override
	public int deleteTableByStrCols(String table, String col, String ids) {
		Blade dao = getSqlMananger();
		return dao.deleteTableByStrCols(table, col, ids);
	}

    @Override
	public int deleteBy(String sqlTemplate) {
		Blade dao = getSqlMananger();
		return dao.deleteBy(sqlTemplate);
	}

    @Override
	public int deleteBy(String where, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.deleteBy(where, modelOrMap);
	}

    @Override
	public long total() {
		Blade dao = getSqlMananger();
		return dao.total();
	}

    @Override
	public long count(M model) {
		Blade dao = getSqlMananger();
		return dao.count(model);
	}

    @Override
	public int count(String where, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.count(where, modelOrMap);
	}

    @Override
	public int countBy(String sqlTemplate, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.countBy(sqlTemplate, modelOrMap);
	}

    @Override
	public List<M> getList(int start, int size) {
		Blade dao = getSqlMananger();
		return dao.getList(start, size);
	}

    @Override
	public List<M> getList(M model, int start, int size) {
		Blade dao = getSqlMananger();
		return dao.getList(model, start, size);
	}

    @Override
	public List<M> getList(String sqlTemplate, Object modelOrMap, int start, int size) {
		Blade dao = getSqlMananger();
		return dao.getList(sqlTemplate, modelOrMap, start, size);
	}

    @Override
	public BladePage<M> paginate(String sqlTemplate, Object paras, int start, int size) {
		Blade dao = getSqlMananger();
		return dao.paginate(sqlTemplate, paras, start, size);
	}

    @Override
	public boolean isExist(String sqlTemplate, Object modelOrMap) {
		Blade dao = getSqlMananger();
		return dao.isExist(sqlTemplate, modelOrMap);
	}

    @Override
	public Object getIdValue(Object model) {
		Blade dao = getSqlMananger();
		return dao.getIdValue(model);
	}

    @Override
	public boolean save(M model, AopContext ac) {
		return save(model, ac, Cst.me().getDefaultCURDFactory());
	}

    @Override
	public boolean update(M model, AopContext ac) {
		return update(model, ac, Cst.me().getDefaultCURDFactory());
	}

    @Override
	public boolean removeByIds(String ids, AopContext ac) {
		return removeByIds(ids, ac, Cst.me().getDefaultCURDFactory());
	}

    @Override
	public boolean delByIds(String ids, AopContext ac) {
		return delByIds(ids, ac, Cst.me().getDefaultCURDFactory());
	}

    @Override
	public boolean restoreByIds(String ids, AopContext ac) {
		return restoreByIds(ids, ac, Cst.me().getDefaultCURDFactory());
	}

    @Override
	public boolean save(M model, AopContext ac, ICurd intercept) {
		if (null != intercept) {
			ac.setObject(model);
			intercept.saveBefore(ac);
		}
		String rtid = this.saveRtStrId(model);
		boolean tempAfter = true;
		if (null != intercept && rtid.length() > 0) {
			ac.setId(rtid);
			tempAfter = intercept.saveAfter(ac);
		}
		return (rtid.length() > 0 && tempAfter);
	}

    @Override
	public boolean update(M model, AopContext ac, ICurd intercept) {
		if (null != intercept) {
			ac.setObject(model);
			intercept.updateBefore(ac);
		}
		boolean temp = this.update(model);
		boolean tempAfter = true;
		if (null != intercept && temp) {
			tempAfter = intercept.updateAfter(ac);
		}
		return (temp && tempAfter);
	}

    @Override
	public boolean removeByIds(String ids, AopContext ac, ICurd intercept) {
		if (null != intercept) {
			ac.setId(ids);
			intercept.removeBefore(ac);
		}
		int n = this.deleteByIds(ids);
		boolean tempAfter = true;
		if (null != intercept && n > 0) {
			tempAfter = intercept.removeAfter(ac);
		}
		return (n > 0 && tempAfter);
	}

    @Override
	public boolean delByIds(String ids, AopContext ac, ICurd intercept) {
		if (null != intercept) {
			ac.setId(ids);
			intercept.delBefore(ac);
		}
		boolean temp = this.updateBy(ac.getSql(), ac.getWhere(), ac.getParam());
		boolean tempAfter = true;
		if (null != intercept && temp) {
			tempAfter = intercept.delAfter(ac);
		}
		return (temp && tempAfter);
	}

    @Override
	public boolean restoreByIds(String ids, AopContext ac, ICurd intercept) {
		if (null != intercept) {
			ac.setId(ids);
			intercept.restoreBefore(ac);
		}
		boolean temp = this.updateBy(ac.getSql(), ac.getWhere(), ac.getParam());
		boolean tempAfter = true;
		if (null != intercept && temp) {
			tempAfter = intercept.restoreAfter(ac);
		}
		return (temp && tempAfter);
	}
	
}
