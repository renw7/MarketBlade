package com.smallchill.modules.platform.model;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;

import com.smallchill.core.annotation.BindID;
import com.smallchill.core.base.model.BaseModel;

@Table(name = "blade_notice")
@BindID(name = "id")
// @DbName(name = "other") //多数据源配置注解
@SuppressWarnings("serial")
public class Notice extends BaseModel {
	// 序列
	private Integer id;
	// 创建人
	private Integer creater;
	// 图片
	private Integer pic;
	// 类型
	private Integer type;
	// 版本号
	private Integer version;
	// 内容
	private String content;
	// 标题
	private String title;
	// 创建时间
	private Date createtime;
	// 发布时间
	private Date publishtime;

	@AutoID //mysql、postgresql自增使用
	@SeqID(name = "SEQ_NOTICE") //oracle sequence序列使用
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Integer getPic() {
		return pic;
	}

	public void setPic(Integer pic) {
		this.pic = pic;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

}
