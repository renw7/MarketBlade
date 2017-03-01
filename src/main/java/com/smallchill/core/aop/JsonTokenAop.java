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
package com.smallchill.core.aop;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.smallchill.core.toolbox.kit.HashKit;
import com.smallchill.core.toolbox.kit.HttpKit;
import com.smallchill.core.toolbox.kit.StrKit;
import com.smallchill.core.toolbox.support.Convert;

/**
 * API校验
 */
@Aspect
@Component
public class JsonTokenAop {

	@Pointcut(value = "@annotation(com.smallchill.core.annotation.JsonToken)")
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