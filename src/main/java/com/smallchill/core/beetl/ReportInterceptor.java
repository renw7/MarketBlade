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
package com.smallchill.core.beetl;

import com.smallchill.core.toolbox.kit.DateKit;
import org.beetl.sql.core.InterceptorContext;
import org.beetl.sql.ext.DebugInterceptor;

import java.util.Collection;

/**
 * 重写beetlsql输出的sql语句格式
 */
public class ReportInterceptor extends DebugInterceptor {

	@Override
	public void before(InterceptorContext ctx) {
		String sqlId = ctx.getSqlId();
		if (this.isDebugEanble(sqlId)) {
			ctx.put("debug.time", System.currentTimeMillis());
		}

		StringBuilder sb = new StringBuilder();
		sb.append("\nBlade SqlReport --------------------- " + DateKit.getTime() + " -------------------------------\n")
				.append("索引: " + ctx.getSqlId().replaceAll("\\s+", " ")).append("\n")
				.append("语句: " + ctx.getSql().replaceAll("\\s+", " ")).append("\n")
				.append("参数: " + formatParas(ctx.getParas()))
				.append("\n");

        RuntimeException ex = new RuntimeException();
        StackTraceElement[] traces = ex.getStackTrace();
        int index = lookBusinessCodeInTrace(traces);
        StackTraceElement bussinessCode = traces[index];
        String className = bussinessCode.getClassName();
        String mehodName = bussinessCode.getMethodName();
        int line = bussinessCode.getLineNumber();
        sb.append("位置: " + className + "." + mehodName + "(" + bussinessCode.getFileName() + ":" + line + ")").append("\n");


		ctx.put("debug.sb", sb);
	}

	@Override
	public void after(InterceptorContext ctx) {
		long time = System.currentTimeMillis();
		long start = (Long) ctx.get("debug.time");
		
		StringBuilder sb = (StringBuilder) ctx.get("debug.sb");
		sb.append("时间: " + (time - start) + "ms").append("\n");

		if (ctx.isUpdate()) {
			sb.append("更新: [");
			if (ctx.getResult().getClass().isArray()) {
				int[] ret = (int[]) ctx.getResult();
                for(int i=0;i<ret.length;i++){
                    if(i>0) sb.append(",");
                    sb.append(ret[i]);
                }
			} else {
				sb.append(ctx.getResult());
			}
			sb.append("]");
		} else {
            if(ctx.getResult() instanceof Collection){
                sb.append("返回: ").append(((Collection)ctx.getResult()).size()).append("");
            }else{
                sb.append("返回: ").append(ctx.getResult()).append("");
            }
		}
		sb.append("\n").append("-----------------------------------------------------------------------------------------");
		println(sb.toString());
	}
	
	@Override
	public void exception(InterceptorContext ctx, Exception ex) {
		StringBuilder sb =(StringBuilder) ctx.get("debug.sb");
		sb.append("错误: ").append(ex != null ? ex.getMessage() : "");
		sb.append("\n").append("-----------------------------------------------------------------------------------------");
		println(sb.toString());
	}

}
