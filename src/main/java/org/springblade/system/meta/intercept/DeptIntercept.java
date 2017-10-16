package org.springblade.system.meta.intercept;

import org.springblade.core.aop.AopContext;
import org.springblade.core.constant.ConstShiro;
import org.springblade.core.meta.PageIntercept;
import org.springblade.core.shiro.ShiroKit;
import org.springblade.core.toolbox.support.Convert;

/**
 * DeptIntercept
 * @author zhuangqian
 */
public class DeptIntercept extends PageIntercept {

	@Override
    public void queryBefore(AopContext ac) {
		if (ShiroKit.lacksRole(ConstShiro.ADMINISTRATOR)) {
			String depts = ShiroKit.getUser().getDeptId() + "," + ShiroKit.getUser().getSubDepts();
			String condition = "and id in (#{join(ids)})";
			ac.setCondition(condition);
			ac.getParam().put("ids", Convert.toIntArray(depts));
		}
	}

}
