package com.dc.security.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.dc.security.entity.user.TestResource;
import com.dc.security.entity.user.TestUser;
import com.dc.security.mapper.UserDaoMapper;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	
	@Autowired
	private UserDaoMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userName)  {
		List<TestUser> userList =  userMapper.getUserByName(userName);
		if(userList == null){
			throw new UsernameNotFoundException("用户【"+userName+"]不存在");
		}else{
			TestUser userItem = userList.get(0);
			List<TestResource> resourceList = userMapper.getUserResource(userItem.getUserName());
			UserDetails user = new UserDetailsImpl(userItem,resourceList);
			return user;
		}
	}

}
