package com.appatam.Suzang_Group_Back.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.appatam.Suzang_Group_Back.security.JWTConstUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class CustomJWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 
		if (request.getServletPath().equals("/generateToken")) {
			
			//passer au filte suivante
			 filterChain.doFilter(request, response);  
		}
		
		else {
			  
			String authorizationToken = request.getHeader(JWTConstUtil.AUTH_HEADER);
			
			  
			
			if(authorizationToken!=null && authorizationToken.startsWith(JWTConstUtil.PREFIX_BEARER)) {
				
				
				try {
					String jwt = authorizationToken.substring(JWTConstUtil.PREFIX_BEARER.length());
					
					Algorithm algo = Algorithm.HMAC256(JWTConstUtil.SECRET);
					
					JWTVerifier jwtVerifie = JWT.require(algo).build();
					
					DecodedJWT decodedJWT = jwtVerifie.verify(jwt);
					
					String username = decodedJWT.getSubject();
					
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					
					Collection<GrantedAuthority> authorities = new ArrayList<>();
					
					for (String role : roles) { 
						
						authorities.add(new SimpleGrantedAuthority(role));
					}
					 UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(username,null,authorities);
					 
					 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					 
					 //passer au filte suivante
					 filterChain.doFilter(request, response);
					 
					 
					 
					 
				} catch (Exception e) { 
					// TODO: handle exception
					
					response.setHeader("Error message", e.getMessage() );
					
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				} 
				
				
			}else {
				//passer au filte suivante
				 filterChain.doFilter(request, response);
			}
		}
		
	}

}
