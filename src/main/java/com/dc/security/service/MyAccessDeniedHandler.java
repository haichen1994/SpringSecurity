package com.dc.security.service;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.dc.security.base.dataformat.ResultJson;
 
@Component
//自定义403响应内容
public class MyAccessDeniedHandler implements AccessDeniedHandler {
 
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
		PrintWriter out =  null;
		response.setContentType("application/json;charset=utf-8");
		try {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				out = response.getWriter();
				String message = e.getMessage();
				ResultJson result = new ResultJson();
				result.buildSysFailure(message);
				out.write(result.toString());
				out.flush();
			} catch (Exception e1) {
				throw e1;
			}finally{
				if(out != null){
					out.close();
				}
			}
	}
  }