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
package org.springblade.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springblade.core.toolbox.kit.HashKit;
import org.springblade.core.toolbox.kit.HttpKit;
import org.springblade.core.toolbox.kit.StrKit;
import org.springblade.core.toolbox.support.Convert;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * API校验
 * @author zhuangqian
 */
@Aspect
@Component
public class JsonTokenAop {

	@Pointcut(value = "@annotation(org.springblade.core.annotation.JsonToken)")
	private void cutBefore() {

	}

	@Around("cutBefore()")
	public Object doBefore(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = HttpKit.getRequest();
		Map<String, Object> headMap = new HashMap<String, Object>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            headMap.put(key, value);
        }
        String nonce = Convert.toStr(headMap.get("nonce"), "");
        String timestamp = Convert.toStr(headMap.get("timestamp"), "");
        String signature = Convert.toStr(headMap.get("signature"), "");
        String _signature = HashKit.sha1(nonce + timestamp);
        if (!StrKit.equals(_signature, signature)) {
        	throw new RuntimeException("您提交的签名有误,无法访问该资源!");
        } else {
        	return point.proceed();
        }
	}
}