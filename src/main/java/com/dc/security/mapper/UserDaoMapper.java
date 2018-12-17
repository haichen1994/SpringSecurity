package com.dc.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dc.security.entity.user.TestResource;
import com.dc.security.entity.user.TestUser;

@Mapper
public interface UserDaoMapper {

	public List<TestUser> getUserByName(@Param(value = "userName") String userName);

	public List<TestUser> getUserAll();

	public List<TestResource> getUserResource(@Param(value = "userName")  String userName);
	
	
	
}
