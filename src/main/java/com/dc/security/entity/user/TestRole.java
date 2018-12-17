package com.dc.security.entity.user;

public class TestRole {

	
	private String systemFrom;
	private String roleId;
	private String roleDesc;
	private String enable;
	
	
	public TestRole() {
		super();
	}
	public TestRole(String systemFrom, String roleId, String roleDesc, String enable) {
		super();
		this.systemFrom = systemFrom;
		this.roleId = roleId;
		this.roleDesc = roleDesc;
		this.enable = enable;
	}
	public String getSystemFrom() {
		return systemFrom;
	}
	public void setSystemFrom(String systemFrom) {
		this.systemFrom = systemFrom;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "TestRole [systemFrom=" + systemFrom + ", roleId=" + roleId + ", roleDesc=" + roleDesc + ", enable="
				+ enable + "]";
	}
	
}