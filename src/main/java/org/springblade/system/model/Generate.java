package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;

import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

@Table(name = "blade_generate")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 代码生成表
 * @author zhuangqian
 */
public class Generate extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 实体类名
     */
	private String modelname;
    /**
     * 模块名称
     */
	private String name;
    /**
     * 物理地址
     */
	private String realpath;
    /**
     * package包名
     */
	private String packagename;
    /**
     * 主键名
     */
	private String pkname;
    /**
     * 表名
     */
	private String tablename;
    /**
     * 备注
     */
	private String tips;

	@AutoID
	@SeqID(name = "SEQ_GENERATE")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealpath() {
		return realpath;
	}

	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getPkname() {
		return pkname;
	}

	public void setPkname(String pkname) {
		this.pkname = pkname;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

}
