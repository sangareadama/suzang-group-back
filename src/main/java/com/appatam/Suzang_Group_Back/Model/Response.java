package com.appatam.Suzang_Group_Back.Model;

public class Response {
	
	private int code ;
	private Object response ;
	
	  
	
	public Response(int code, Object response) {
		super();
		this.code = code;
		this.response = response; 
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code; 
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	
	

}
