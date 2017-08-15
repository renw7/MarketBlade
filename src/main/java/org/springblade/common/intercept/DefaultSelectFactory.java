package org.springblade.common.intercept;

import org.springblade.core.intercept.SelectInterceptor;
import org.springblade.core.meta.IQuery;

public class DefaultSelectFactory extends SelectInterceptor {
	
	public IQuery deptIntercept() {
		return new SelectDeptIntercept();
	}
	
	public IQuery roleIntercept() {
		return new SelectRoleIntercept();
	}
	
}
