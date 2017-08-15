package org.springblade.common.intercept;

import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstShiro;
import org.springblade.core.intercept.QueryInterceptor;
import org.springblade.core.shiro.ShiroKit;

public class SelectRoleIntercept extends QueryInterceptor {

	public void queryBefore(AopContext ac) {
		if (ShiroKit.lacksRole(ConstShiro.ADMINISTRATOR)) {
			String roles = ShiroKit.getUser().getRoles() + "," + ShiroKit.getUser().getSubRoles();
			String condition = "where id in (#{join(ids)})";
			ac.setCondition(condition);
			ac.getParam().put("ids", roles.split(","));
		}
	}

}
