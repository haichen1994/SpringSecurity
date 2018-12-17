package com.dc.security.base.dataformat;

import com.dc.security.constans.CommonConstant;

public class ResultJson {
	private String status;
	private String message;
	private Object result;
	
	public ResultJson(){
		
	}
	
	public ResultJson buildSucess(Object obj){
		this.setStatus(CommonConstant.STATUS_SUCESS);
		this.setMessage(CommonConstant.STATUS_SUCESS_MSG);
		this.setResult(obj);
		return this;
	}
	
	public ResultJson buildBusinessFailure(Object obj){
		this.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
		this.setMessage(CommonConstant.STATUS_BUSINESS_EXCEPTION_MSG);
		this.setResult(obj);
		return this;
	}
	
	public ResultJson buildSysFailure(Object obj){
		this.setStatus(CommonConstant.STATUS_BUSINESS_EXCEPTION);
		this.setMessage(CommonConstant.STATUS_BUSINESS_EXCEPTION_MSG);
		this.setResult(obj);
		return this;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "status:'" + status + "', message:'" + message + "', result:'" + result + "'";
	}
	
	
}
