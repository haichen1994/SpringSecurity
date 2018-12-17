package com.dc.security.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dc.security.base.dataformat.ResultJson;
@Component
public class AuthenticationFailureHandler
		implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		ResultJson result = new ResultJson();
		try {
			if (e instanceof UsernameNotFoundException) {
				result.buildBusinessFailure("用户不存在");
			}
			if (e instanceof BadCredentialsException) {
				result.buildBusinessFailure("用户名或密码输入错误，登录失败!");
			}
			out.write(result.toString());
			out.flush();
		} catch (Exception e1) {
			throw e;
		} finally {
			if (out != null)
				out.close();
		}

	}

}
