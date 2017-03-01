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
 * before拦截
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