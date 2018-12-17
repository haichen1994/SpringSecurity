package com.dc.security.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dc.security.base.dataformat.ResultJson;

@Component
public class AuthenticationSucessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setContentType("application/json;charset=utf-8");
		String requestUrl = request.getRequestURL().toString();
		PrintWriter out = response.getWriter();
		try {
			ResultJson result = new ResultJson();
			result.buildSucess("访问" + requestUrl + "认证成功！");
			out.write(result.toString());
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			if (out != null)
				out.close();
		}

	}

}
