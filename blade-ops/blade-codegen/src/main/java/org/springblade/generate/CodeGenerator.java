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
package org.springblade.generate;


import org.springblade.support.BladeGenerator;

/**
 * 代码生成器
 *
 * @author Chill
 */
public class CodeGenerator {

	public static String PACKAGE_NAME = "org.springblade.system";
	public static String PACKAGE_DIR = "/blade-ops/blade-codegen/src/main/java";
	public static String[] TABLE_PREFIX = {"blade_"};
	public static String[] INCLUDE_TABLES = {"blade_param"};
	public static String[] EXCLUDE_TABLES = {};
	public static Boolean HAS_SUPER_ENTITY = Boolean.TRUE;
	public static String[] SUPER_ENTITY_COLUNMS = {"id", "create_time", "create_user", "update_time", "update_user", "status", "is_deleted"};

	/**
	 * RUN THIS
	 */
	public static void main(String[] args) {
		BladeGenerator generator = new BladeGenerator();
		generator.setPackageName(PACKAGE_NAME);
		generator.setPackageDir(PACKAGE_DIR);
		generator.setTablePrefix(TABLE_PREFIX);
		generator.setIncludeTables(INCLUDE_TABLES);
		generator.setExcludeTables(EXCLUDE_TABLES);
		generator.setHasSuperEntity(HAS_SUPER_ENTITY);
		generator.setSuperEntityColumns(SUPER_ENTITY_COLUNMS);
		generator.run();
	}

}
