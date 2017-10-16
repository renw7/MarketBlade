package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

import java.util.Date;

@Table(name = "blade_attach")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * Attach
 * @author zhuangqian
 */
public class Attach extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 编码
     */
	private String code;
    /**
     * 创建人
     */
	private Integer creater;
    /**
     * 附件名
     */
    private String name;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 附件地址
     */
	private String url;
    /**
     * 上传时间
     */
	private Date createtime;

	@AutoID
	@SeqID(name = "SEQ_ATTACH")
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

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
