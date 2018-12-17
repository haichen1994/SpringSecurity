package com.dc.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.security.base.dataformat.ResultJson;
import com.dc.security.entity.user.TestUser;
import com.dc.security.mapper.UserDaoMapper;

@Controller
public class LoginController {
	
	@Autowired
	private UserDaoMapper userMapper;

	@RequestMapping("/login")
	public Object Login(){
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/getUser")
	public Object getUser(){
		ResultJson result = new ResultJson();
		List<TestUser> userList = userMapper.getUserAll();
		if(userList == null || userList.size() == 0){
			result.buildBusinessFailure("查询无记录！");
		}else{
			result.buildSucess(userList);
		}
		return result;
	}
}
