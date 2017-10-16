package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

import java.util.Date;

@Table(name = "blade_operation_log")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 日志表
 * @author zhuangqian
 */
public class OperationLog extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 类名
     */
	private String classname;
    /**
     * 日志名
     */
	private String logname;
    /**
     * 消息
     */
	private String message;
    /**
     * 方法
     */
	private String method;
    /**
     * 是否成功
     */
	private String succeed;
    /**
     * 用户id
     */
	private String userid;
    /**
     * 创建时间
     */
	private Date createtime;

	@AutoID
	@SeqID(name = "SEQ_OLOG")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
