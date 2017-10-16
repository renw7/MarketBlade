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
package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;

import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

@Table(name = "blade_menu")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 菜单表
 * @author zhuangqian
 */
public class Menu extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 菜单编号
     */
	private String code;
    /**
     * 菜单别名
     */
	private String alias;
    /**
     * 图标
     */
	private String icon;
    /**
     * 是否打开tab
     */
	private String isopen;
    /**
     * 是否模板
     */
	private String istemplate;
    /**
     * 菜单层级
     */
	private Integer levels;
    /**
     * 菜单名称
     */
	private String name;
    /**
     * 排序号
     */
	private Integer num;
    /**
     * 地址
     */
	private String path;
    /**
     * 父编号
     */
	private String pcode;
    /**
     * 数据源
     */
	private String source;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 备注
     */
	private String tips;
    /**
     * 接口地址
     */
	private String url;
    /**
     * 版本号
     */
	private Integer version;

	@AutoID
	@SeqID(name = "SEQ_MENU")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getIstemplate() {
		return istemplate;
	}

	public void setIstemplate(String istemplate) {
		this.istemplate = istemplate;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
