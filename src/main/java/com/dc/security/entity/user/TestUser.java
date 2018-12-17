package com.dc.security.entity.user;

public class TestUser {

	private int userId;
	private String userName;
	private String password;
	private String isEnable;
	private String status;
	
	public int getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TestUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", isEnable="
				+ isEnable + ", status=" + status + "]";
	}

	
	
}
