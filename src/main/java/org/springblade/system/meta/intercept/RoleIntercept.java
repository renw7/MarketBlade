package org.springblade.system.meta.intercept;

import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstShiro;
import org.springblade.core.meta.PageIntercept;
import org.springblade.core.shiro.ShiroKit;
import org.springblade.core.toolbox.support.Convert;

public class RoleIntercept extends PageIntercept {

	public void queryBefore(AopContext ac) {
		if (ShiroKit.lacksRole(ConstShiro.ADMINISTRATOR)) {
			String roles = ShiroKit.getUser().getRoles() + "," + ShiroKit.getUser().getSubRoles();
			String condition = "and id in (#{join(ids)})";
			ac.setCondition(condition);
			ac.getParam().put("ids", Convert.toIntArray(roles));
		}
	}

}
