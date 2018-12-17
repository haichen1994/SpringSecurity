package com.dc.security.utils;

import java.util.ArrayList;
import java.util.List;

import com.dc.security.entity.user.TestUser;

public class Utils {

	public static TestUser getUser(String userName){
		
		TestUser user = new TestUser();
		user.setIsEnable("1");
		user.setUserName("admin");
		user.setPassword("123456");
		user.setStatus("1");
		user.setUserId(23);
		if("admin".equals(userName)){
			return user;
		}
		return null;
	}
	
	public static List<String> getRole(){
		List<String> list =new ArrayList<String>();
		for(int i=0;i<10;i++){
			list.add("role_"+i);
		}
		return list;
	}
}
