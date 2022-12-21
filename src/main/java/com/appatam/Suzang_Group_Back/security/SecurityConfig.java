package com.appatam.Suzang_Group_Back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appatam.Suzang_Group_Back.filter.CustomJWTAuthenticationFilter;
import com.appatam.Suzang_Group_Back.filter.CustomJWTAuthorizationFilter;
import com.appatam.Suzang_Group_Back.service.IUtilisateurService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IUtilisateurService iUtilisateurService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//		auth.userDetailsService(new UserDetailsService() {
//			
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				// TODO Auto-generated method stub
//				Utilisateur ut = iUtilisateurService.rechercherUtilisateur(username);
//				
//				Collection<GrantedAuthority> authorities = new ArrayList<>();
//				
//				ut.getRoles().forEach(role->{
//					authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
//				});
//				
//				return new User(ut.getUsername(), ut.getPassword(), authorities);
//			}
//		});
	}

	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {  
		// TODO Auto-generated method stub
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login/**","/api/**","/home/**").permitAll();  
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/role/**").hasAuthority("ROLE_ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/role/**").hasAuthority("ROLE_MANAGER");
		http.authorizeRequests().anyRequest().authenticated(); 
		http.addFilter(new CustomJWTAuthenticationFilter(authenticationManagerBean()));
		//les filtres dans l'ordre qui doivent intercepter la requette
		http.addFilterBefore(new CustomJWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	   
	/*@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(false);
		
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		
		 
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return urlBasedCorsConfigurationSource;
	}*/
	 
	@Bean
	@Override 
	public AuthenticationManager authenticationManagerBean() throws Exception{
		 
		return super.authenticationManagerBean();
	}
	
	
	

}
