/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springblade.develop.support;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 代码生成器配置类
 *
 * @author Chill
 */
@Data
@Slf4j
public class BladeGenerator {

	private String packageName = "org.springblade.test";
	private String packageDir = "/blade-ops/blade-codegen/src/main/java";
	private String[] tablePrefix = {"blade_"};
	private String[] includeTables = {"blade_test"};
	private String[] excludeTables = {};
	private Boolean hasSuperEntity = Boolean.TRUE;
	private String[] superEntityColumns = {"id", "create_time", "create_user", "update_time", "update_user", "status", "is_deleted"};
	private Boolean isSwagger2 = Boolean.TRUE;

	public void run() {
		// 获取配置文件
		Properties props = getProperties();
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String outputDir = getOutputDir();
		String author = props.getProperty("author");
		gc.setOutputDir(outputDir);
		gc.setAuthor(author);
		gc.setFileOverride(true);
		gc.setOpen(false);
		/**
		 * 开启 activeRecord 模式
		 */
		gc.setActiveRecord(false);
		/**
		 * XML 二级缓存
		 */
		gc.setEnableCache(false);
		/**
		 * XML ResultMap
		 */
		gc.setBaseResultMap(true);
		/**
		 * XML columList
		 */
		gc.setBaseColumnList(true);
		/**
		 * 自定义文件命名，注意 %s 会自动填充表实体属性！
		 */
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		gc.setSwagger2(isSwagger2);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		String driverName = props.getProperty("spring.datasource.driver-class-name");
		if (StringUtil.containsAny(driverName, DbType.MYSQL.getDb())) {
			dsc.setDbType(DbType.MYSQL);
			dsc.setTypeConvert(new MySqlTypeConvert());
		} else {
			dsc.setDbType(DbType.POSTGRE_SQL);
			dsc.setTypeConvert(new PostgreSqlTypeConvert());
		}
		dsc.setUrl(props.getProperty("spring.datasource.url"));

		dsc.setDriverName(driverName);
		dsc.setUsername(props.getProperty("spring.datasource.username"));
		dsc.setPassword(props.getProperty("spring.datasource.password"));
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名
		// strategy.setDbColumnUnderline(true);//全局下划线命名

		/**
		 * 表名生成策略
		 */
		strategy.setNaming(NamingStrategy.underline_to_camel);
		/**
		 * 字段名生成策略
		 */
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		/**
		 * 此处可以修改为您的表前缀
		 */
		strategy.setTablePrefix(tablePrefix);

		if (includeTables.length > 0) {
			/**
			 * 需要生成的表
			 */
			strategy.setInclude(includeTables);
		}

		if (excludeTables.length > 0) {
			/**
			 * 排除生成的表
			 */
			strategy.setExclude(excludeTables);
		}


		if (hasSuperEntity) {
			strategy.setSuperEntityClass("org.springblade.core.mp.base.BaseEntity");
			strategy.setSuperEntityColumns(superEntityColumns);
			strategy.setSuperServiceClass("org.springblade.core.mp.base.BaseService");
			strategy.setSuperServiceImplClass("org.springblade.core.mp.base.BaseServiceImpl");
		} else {
			strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
			strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
		}

		// 自定义 controller 父类
		strategy.setSuperControllerClass("org.springblade.core.boot.ctrl.BladeController");

		strategy.setEntityBuilderModel(false);
		strategy.setEntityLombokModel(true);
		strategy.setControllerMappingHyphenStyle(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		// 控制台扫描
		pc.setModuleName(null);
		pc.setParent(packageName);
		pc.setController("controller");
		pc.setEntity("entity");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/entityVO.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "vo" + "/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/entityDTO.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "dto" + "/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
			}
		});
		focList.add(new FileOutConfig("/templates/wrapper.java.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return getOutputDir() + "/" + packageName.replace(".", "/") + "/" + "wrapper" + "/" + tableInfo.getEntityName() + "Wrapper" + StringPool.DOT_JAVA;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		mpg.execute();
	}

	/**
	 * 获取配置文件
	 *
	 * @return 配置Props
	 */
	private Properties getProperties() {
		// 读取配置文件
		Resource resource = new ClassPathResource("generator.properties");
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 生成到项目中
	 *
	 * @return outputDir
	 */
	public String getOutputDir() {
		return System.getProperty("user.dir") + packageDir;
	}

	/**
	 * 页面生成的文件名
	 */
	private String getGeneratorViewPath(String viewOutputDir, TableInfo tableInfo, String suffixPath) {
		String name = StringUtils.firstToLowerCase(tableInfo.getEntityName());
		String path = viewOutputDir + "/" + name + "/" + name + suffixPath;
		File viewDir = new File(path).getParentFile();
		if (!viewDir.exists()) {
			viewDir.mkdirs();
		}
		return path;
	}

}
