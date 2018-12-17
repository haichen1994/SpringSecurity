package com.dc.security.entity.user;

public class TestResource {
	
	private String resourceId;
	private String resourceName;
	private String url;
	private String moduleName;
	
	
	public TestResource() {
		super();
	}
	public TestResource(String resourceId, String resourceName, String url, String moduleName) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.url = url;
		this.moduleName = moduleName;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Override
	public String toString() {
		return "TestResource [resourceId=" + resourceId + ", resourceName=" + resourceName + ", url=" + url
				+ ", moduleName=" + moduleName + "]";
	}
}
