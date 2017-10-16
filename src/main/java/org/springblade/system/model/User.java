package org.springblade.system.model;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;
import org.springblade.core.annotation.BindID;
import org.springblade.core.base.model.BaseModel;

import java.util.Date;

@Table(name = "blade_user")
@BindID(name = "id")
@SuppressWarnings("serial")
/**
 * 用户表
 * @author zhuangqian
 */
public class User extends BaseModel {
    /**
     * 主键
     */
	private Integer id;
    /**
     * 账号
     */
	private String account;
    /**
     * 部门id
     */
	private Integer deptid;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 姓名
     */
	private String name;
    /**
     * 密码
     */
	private String password;
    /**
     * 密码盐
     */
	private String salt;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 角色id
     */
	private String roleid;
    /**
     * 性别
     */
	private Integer sex;
    /**
     * 状态
     */
	private Integer status;
    /**
     * 版本号
     */
	private Integer version;
    /**
     * 生日
     */
	private Date birthday;
    /**
     * 创建时间
     */
	private Date createtime;
    

	@AutoID //mysql自增
	@SeqID(name = "SEQ_USER") //oracle sequence自增
    /**
     * 两者只需要写一个,根据数据库不同来选择
     */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
