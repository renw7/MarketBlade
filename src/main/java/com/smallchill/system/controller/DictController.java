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
package com.smallchill.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smallchill.common.base.BaseController;
import com.smallchill.core.plugins.dao.Blade;
import com.smallchill.core.toolbox.Maps;
import com.smallchill.core.toolbox.ajax.AjaxResult;
import com.smallchill.core.toolbox.kit.CacheKit;
import com.smallchill.core.toolbox.kit.JsonKit;
import com.smallchill.core.toolbox.kit.StrKit;
import com.smallchill.system.model.Dict;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController{
	private static String LIST_SOURCE = "Dict.list";
	private static String BASE_PATH = "/system/dict/";
	private static String CODE = "dict";
	private static String PERFIX = "tfw_dict";
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView(BASE_PATH + "dict.html");
		view.addObject("code", CODE);
		return view;
	}
	
	
	@ResponseBody
	@RequestMapping(KEY_LIST)
	public Object list() {
		Object gird = paginate(LIST_SOURCE);
		return gird;
	}
	
	@RequestMapping(KEY_ADD)
	public ModelAndView add() {
		ModelAndView view = new ModelAndView(BASE_PATH + "dict_add.html");
		view.addObject("code", CODE);
		return view;
	}
	
	@RequestMapping(KEY_ADD + "/{id}")
	public ModelAndView add(@PathVariable String id) {
		ModelAndView view = new ModelAndView(BASE_PATH + "dict_add.html");
		if (StrKit.notBlank(id)) {
			Dict dict = Blade.create(Dict.class).findById(id);
			view.addObject("dictcode", dict.getCode());
			view.addObject("pId", id);
			view.addObject("num", findLastNum(dict.getCode()));
		}
		view.addObject("code", CODE);
		return view;
	}
	
	@RequestMapping(KEY_EDIT + "/{id}")
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView view = new ModelAndView(BASE_PATH + "dict_edit.html");
		Dict dict = Blade.create(Dict.class).findById(id);
		view.addObject("model", JsonKit.toJson(dict));
		view.addObject("code", CODE);
		return view;
	}

	@RequestMapping(KEY_VIEW + "/{id}")
	public ModelAndView view(@PathVariable String id) {
		ModelAndView view = new ModelAndView(BASE_PATH + "dict_view.html");
		Blade blade = Blade.create(Dict.class);
		Dict dict = blade.findById(id);
		Dict parent = blade.findById(dict.getPid());
		String pName = (null == parent) ? "" : parent.getName();
		Maps maps = Maps.parse(dict);
		maps.set("pName", pName);
		view.addObject("model", JsonKit.toJson(maps));
		view.addObject("code", CODE);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(KEY_SAVE)
	public AjaxResult save() {
		Dict dict = mapping(PERFIX, Dict.class);
		boolean temp = Blade.create(Dict.class).save(dict);
		if (temp) {
			CacheKit.removeAll(DICT_CACHE);
			return success(SAVE_SUCCESS_MSG);
		} else {
			return error(SAVE_FAIL_MSG);
		}
	}

	@ResponseBody
	@RequestMapping(KEY_UPDATE)
	public AjaxResult update() {
		Dict dict = mapping(PERFIX, Dict.class);
		dict.setVersion(getParaToInt("VERSION", 0) + 1);
		boolean temp =  Blade.create(Dict.class).update(dict);
		if (temp) {
			CacheKit.removeAll(DICT_CACHE);
			return success(UPDATE_SUCCESS_MSG);
		} else {
			return error(UPDATE_FAIL_MSG);
		}
	}

	@ResponseBody
	@RequestMapping(KEY_REMOVE)
	public AjaxResult remove(@RequestParam String ids) {
		int cnt = Blade.create(Dict.class).deleteByIds(ids);
		if (cnt > 0) {
			CacheKit.removeAll(DICT_CACHE);
			return success(DEL_SUCCESS_MSG);
		} else {
			return error(DEL_FAIL_MSG);
		}
	}

	
	private int findLastNum(String code){
		try{
			Blade blade = Blade.create(Dict.class);
			Dict dict = blade.findFirstBy("code = #{code} order by num desc", Maps.create().set("code", code));
			return dict.getNum() + 1;
		}
		catch(Exception ex){
			return 1;
		}
	}
	
	
}
