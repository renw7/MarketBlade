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
package org.springblade.system.controller;

import org.springblade.common.base.BaseController;
import org.springblade.core.annotation.Before;
import org.springblade.core.annotation.Json;
import org.springblade.core.constant.ConstCurd;
import org.springblade.core.toolbox.CMap;
import org.springblade.core.toolbox.cache.CacheKit;
import org.springblade.core.toolbox.kit.JsonKit;
import org.springblade.system.meta.intercept.DictValidator;
import org.springblade.system.model.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springblade.core.plugins.dao.Blade;
import org.springblade.core.toolbox.ajax.AjaxResult;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
	private static String LIST_SOURCE = "dict.list";
	private static String BASE_PATH = "/system/dict/";
	private static String CODE = "dict";
	private static String PREFIX = "blade_dict";
	
	@RequestMapping("/")
	public String index(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "dict.html";
	}
	
	
	@Json
	@RequestMapping(ConstCurd.KEY_LIST)
	public Object list() {
		Object gird = paginate(LIST_SOURCE);
		return gird;
	}
	
	@RequestMapping(ConstCurd.KEY_ADD)
	public String add(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "dict_add.html";
	}
	
	@RequestMapping(ConstCurd.KEY_ADD + "/{id}")
	public String add(@PathVariable Integer id, ModelMap mm) {
		if (null != id) {
			Dict dict = Blade.create(Dict.class).findById(id);
			mm.put("dictcode", dict.getCode());
			mm.put("pId", id);
			mm.put("num", findLastNum(dict.getCode()));
		}
		mm.put("code", CODE);
		return BASE_PATH + "dict_add.html";
	}
	
	@RequestMapping(ConstCurd.KEY_EDIT + "/{id}")
	public String edit(@PathVariable Integer id, ModelMap mm) {
		Dict dict = Blade.create(Dict.class).findById(id);
		mm.put("model", JsonKit.toJson(dict));
		mm.put("code", CODE);
		return BASE_PATH + "dict_edit.html";
	}

	@RequestMapping(ConstCurd.KEY_VIEW + "/{id}")
	public String view(@PathVariable Integer id, ModelMap mm) {
		Blade blade = Blade.create(Dict.class);
		Dict dict = blade.findById(id);
		Dict parent = blade.findById(dict.getPid());
		String pname = (null == parent) ? "" : parent.getName();
		CMap cmap = CMap.parse(dict);
		cmap.set("pname", pname);
		mm.put("model", JsonKit.toJson(cmap));
		mm.put("code", CODE);
		return BASE_PATH + "dict_view.html";
	}
	
	@Json
	@Before(DictValidator.class)
	@RequestMapping(ConstCurd.KEY_SAVE)
	public AjaxResult save() {
		Dict dict = mapping(PREFIX, Dict.class);
		boolean temp = Blade.create(Dict.class).save(dict);
		if (temp) {
			CacheKit.removeAll(SYS_CACHE);
			return success(ConstCurd.SAVE_SUCCESS_MSG);
		} else {
			return error(ConstCurd.SAVE_FAIL_MSG);
		}
	}

	@Json
	@Before(DictValidator.class)
	@RequestMapping(ConstCurd.KEY_UPDATE)
	public AjaxResult update() {
		Dict dict = mapping(PREFIX, Dict.class);
		boolean temp =  Blade.create(Dict.class).update(dict);
		if (temp) {
			CacheKit.removeAll(SYS_CACHE);
			return success(ConstCurd.UPDATE_SUCCESS_MSG);
		} else {
			return error(ConstCurd.UPDATE_FAIL_MSG);
		}
	}

	@Json
	@RequestMapping(ConstCurd.KEY_REMOVE)
	public AjaxResult remove() {
		int cnt = Blade.create(Dict.class).deleteByIds(getParameter("ids"));
		if (cnt > 0) {
			CacheKit.removeAll(SYS_CACHE);
			return success(ConstCurd.DEL_SUCCESS_MSG);
		} else {
			return error(ConstCurd.DEL_FAIL_MSG);
		}
	}

	
	private int findLastNum(String code){
		try{
			Blade blade = Blade.create(Dict.class);
			Dict dict = blade.findFirstBy("code = #{code} order by num desc", CMap.init().set("code", code));
			return dict.getNum() + 1;
		}
		catch(Exception ex){
			return 1;
		}
	}
	
	
}
