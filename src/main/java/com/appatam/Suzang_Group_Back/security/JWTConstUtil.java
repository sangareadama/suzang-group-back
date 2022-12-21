package com.appatam.Suzang_Group_Back.security;
  
public class JWTConstUtil {

	public static final String SECRET="secretJWT";
	
	public static final String AUTH_HEADER="Authorization";
	
	public static final int EXPIRE_TIME_ACCESS_TOKEN=24*60*60*1000;
	
	public static final int EXPIRE_TIME_REFRESH_TOKEN=30*60*1000;
	
	public static final String PREFIX_BEARER= "Bearer ";
	
  
}
