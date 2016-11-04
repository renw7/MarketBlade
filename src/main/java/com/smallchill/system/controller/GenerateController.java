package com.smallchill.system.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.db.TableDesc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smallchill.core.base.controller.CurdController;
import com.smallchill.core.beetl.BeetlMaker;
import com.smallchill.core.constant.Cst;
import com.smallchill.core.interfaces.IMeta;
import com.smallchill.core.plugins.dao.Blade;
import com.smallchill.core.toolbox.Paras;
import com.smallchill.core.toolbox.ajax.AjaxResult;
import com.smallchill.core.toolbox.kit.ClassKit;
import com.smallchill.core.toolbox.kit.DateKit;
import com.smallchill.core.toolbox.kit.LogKit;
import com.smallchill.core.toolbox.kit.StrKit;
import com.smallchill.system.meta.factory.GenerateFactory;
import com.smallchill.system.model.Generate;

@Controller
@RequestMapping("/generate")
public class GenerateController extends CurdController<Generate> {

	@Override
	protected Class<? extends IMeta> metaFactoryClass() {
		return GenerateFactory.class;
	}
	
	@ResponseBody
	@RequestMapping("/pojo/{table}")
	public String createPojo(@PathVariable String table) {
		try {
			Blade.dao().genPojoCodeToConsole(table);
			return "[ " + table + " ] pojo生成成功,请查看控制台";
		} catch (Exception e) {
			return "[ " + table + " ] pojo生成失败:" + e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping("/pojo/{slave}/{table}")
	public String createPojoSlave(@PathVariable String slave, @PathVariable String table) {
		try {
			Blade.dao(slave).genPojoCodeToConsole(table);
			return "[ " + table + " ] pojo生成成功,请查看控制台";
		} catch (Exception e) {
			return "[ " + table + " ] pojo生成失败:" + e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping("/sql/{table:.+}")
	public String createBuiltInSql(@PathVariable String table) {
		try {
			LogKit.println("\n\n-------------------------------- gen by beetlsql {} --------------------------------\n", DateKit.getTime());
			LogKit.println("-----↓------- curd -------↓-----\n");
			Blade.dao().genBuiltInSqlToConsole(ClassKit.newInstance(table).getClass());
			LogKit.println("\n-----↓-- updateNotNull --↓-----\n");
			LogKit.println(Blade.dao().getDbStyle().genUpdateTemplate(ClassKit.newInstance(table).getClass()).getTemplate());
			LogKit.println("\n-----↓------- field -------↓-----\n");
			Blade.dao().genSQLTemplateToConsole(ClassKit.newInstance(table).getClass().getAnnotation(Table.class).name());
			return "[ " + table + " ] sql生成成功,请查看控制台";
		} catch (Exception e) {
			return "[ " + table + " ] sql生成失败:" + e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping("/sql/{slave}/{table:.+}")
	public String createBuiltInSqlSlave(@PathVariable String slave, @PathVariable String table) {
		try {
			LogKit.println("\n\n-------------------------------- gen by beetlsql {} --------------------------------\n", DateKit.getTime());
			LogKit.println("-----↓------- curd --------↓-----\n");
			Blade.dao(slave).genBuiltInSqlToConsole(ClassKit.newInstance(table).getClass());
			LogKit.println("\n-----↓-- updateNotNull --↓-----\n");
			LogKit.println(Blade.dao(slave).getDbStyle().genUpdateTemplate(ClassKit.newInstance(table).getClass()).getTemplate());
			LogKit.println("\n-----↓------ field -------↓-----\n");
			Blade.dao(slave).genSQLTemplateToConsole(ClassKit.newInstance(table).getClass().getAnnotation(Table.class).name());
			return "[ " + table + " ] sql生成成功,请查看控制台";
		} catch (Exception e) {
			return "[ " + table + " ] sql生成失败:" + e.getMessage();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/code")
	public AjaxResult gencode(){
		String ids = getParameter("ids");
		List<Generate> list = Blade.create(Generate.class).findBy("id in (#{join(ids)})", Paras.create().set("ids", ids.split(",")));

		for (Generate gen : list) {
			
			String realPath = gen.getRealpath() + File.separator + "src" + File.separator + "main";
			String packageName = gen.getPackagename();
			String modelName = gen.getModelname();
			String upperModelName = StrKit.firstCharToUpperCase(modelName);
			String lowerModelName = StrKit.firstCharToLowerCase(modelName);
			
			String tableName = gen.getTablename();
			String pkName = gen.getPkname();
			String path = File.separator + realPath + File.separator + "java" + File.separator + packageName.replace(StrKit.DOT, File.separator);
			String resourcesPath = File.separator + realPath + File.separator + "resources";
			String webappPath = File.separator + realPath + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "view";
			
			//java
			String controllerPath = path + File.separator + "controller" + File.separator + upperModelName + "Controller.java";
			String modelPath = path + File.separator + "model" + File.separator + upperModelName + ".java";
			String servicePath = path + File.separator + "service" + File.separator + upperModelName + "Service.java";
			String serviceimplPath = path + File.separator + "service" + File.separator + "impl" + File.separator + upperModelName + "ServiceImpl.java";
			
			//resources
			String sqlPath = resourcesPath + File.separator + "beetlsql" + File.separator + lowerModelName + ".md";
			
			//webapp
			String indexPath = webappPath + File.separator + "gen" + File.separator + lowerModelName + File.separator + lowerModelName + ".html";
			String addPath = webappPath + File.separator + "gen" + File.separator + lowerModelName + File.separator + lowerModelName + "_add.html";
			String editPath = webappPath + File.separator + "gen" + File.separator + lowerModelName + File.separator + lowerModelName + "_edit.html";
			String viewPath = webappPath + File.separator + "gen" + File.separator + lowerModelName + File.separator + lowerModelName + "_view.html";
			
			Map<String, String> pathMap = new HashMap<>();
			pathMap.put("controllerPath", controllerPath);
			pathMap.put("modelPath", modelPath);
			pathMap.put("servicePath", servicePath);
			pathMap.put("serviceimplPath", serviceimplPath);
			pathMap.put("sqlPath", sqlPath);
			pathMap.put("indexPath", indexPath);
			pathMap.put("addPath", addPath);
			pathMap.put("editPath", editPath);
			pathMap.put("viewPath", viewPath);
			
			for (Map.Entry<String, String> entry : pathMap.entrySet()) {  
				File file = new File(entry.getValue());
				if (file.exists()) {
					continue;
				} else {
					file.getParentFile().mkdirs();
				}
			}
			
			//java
			String baseTemplatePath = File.separator + Cst.me().getRealPath() + File.separator + "WEB-INF" + File.separator + "view" + File.separator + "common" + File.separator + "_template" + File.separator;
			String controllerTemplatePath = baseTemplatePath + "_controller" + File.separator + "_controller.bld";
			String modelTemplatePath = baseTemplatePath + "_model" + File.separator +  "_model.bld";
			String serviceTemplatePath = baseTemplatePath + "_service" + File.separator + "_service.bld";
			String serviceimplTemplatePath = baseTemplatePath + "_service" + File.separator + "_impl" + File.separator + "_serviceimpl.bld";
			
			//resources
			String sqlTemplatePath = baseTemplatePath + "_sql" + File.separator + "_sql.bld";
			
			//webapp
			String indexTemplatePath = baseTemplatePath + "_view" + File.separator + "_index.bld";
			String addTemplatePath = baseTemplatePath + "_view" + File.separator + "_add.bld";
			String editTemplatePath = baseTemplatePath + "_view" + File.separator + "_edit.bld";
			String viewTemplatePath = baseTemplatePath + "_view" + File.separator + "_view.bld";
			
			Paras ps = Paras.create();
			ps.set("realPath", realPath);
			ps.set("packageName", packageName);
			ps.set("modelName", upperModelName);
			ps.set("lowerModelName", lowerModelName);
			ps.set("tableName", tableName);
			ps.set("pkName", pkName);
			
			//java
			BeetlMaker.makeHtml(controllerTemplatePath, ps, controllerPath);
			BeetlMaker.makeHtml(modelTemplatePath, ps, modelPath);
			BeetlMaker.makeHtml(serviceTemplatePath, ps, servicePath);
			BeetlMaker.makeHtml(serviceimplTemplatePath, ps, serviceimplPath);
			
			//resources
			BeetlMaker.makeHtml(sqlTemplatePath, ps, sqlPath);
			
			//webapp
			final TableDesc tableDesc = Blade.dao().getMetaDataManager().getTable(tableName);
			Set<String> cols = tableDesc.getIdNames();
			ps.set("cols", cols);
			
			BeetlMaker.makeHtml(indexTemplatePath, ps, indexPath);
			BeetlMaker.makeHtml(addTemplatePath, ps, addPath);
			BeetlMaker.makeHtml(editTemplatePath, ps, editPath);
			BeetlMaker.makeHtml(viewTemplatePath, ps, viewPath);
			
		}
		
		return success("生成成功,已经存在的文件将会覆盖!");
	}
}
