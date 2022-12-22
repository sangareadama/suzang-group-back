package com.appatam.Suzang_Group_Back.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appatam.Suzang_Group_Back.security.JWTConstUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


public class CustomJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	  
	public CustomJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	
		User user = (User) authentication.getPrincipal();
		
		Algorithm algorithm = Algorithm.HMAC256(JWTConstUtil.SECRET);
		
		String access_token=JWT.create()
				
				.withSubject(user.getUsername())
				
				.withExpiresAt(new Date(System.currentTimeMillis()+JWTConstUtil.EXPIRE_TIME_ACCESS_TOKEN))
				.withIssuer(request.getRequestURI().toString())
				.withClaim("roles", user.getAuthorities().stream().map( ga ->ga.getAuthority()).collect(Collectors.toList()))
				.sign(algorithm);
		
		String refresh_token=JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+JWTConstUtil.EXPIRE_TIME_REFRESH_TOKEN))
				.withIssuer(request.getRequestURI().toString())
				.sign(algorithm);
	response.setHeader(JWTConstUtil.AUTH_HEADER, access_token);
//		response.setHeader("refresh_token", refresh_token);
		
		Map<String,String> tokens = new HashMap<>(); 
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		
	}
	
}
