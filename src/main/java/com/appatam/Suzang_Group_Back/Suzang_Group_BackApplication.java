package com.appatam.Suzang_Group_Back;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.appatam.Suzang_Group_Back.domain.Role;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;
import com.appatam.Suzang_Group_Back.service.EmailSenderService;
import com.appatam.Suzang_Group_Back.service.IAccueilPageService;
import com.appatam.Suzang_Group_Back.service.IAtoutPageSevice;
import com.appatam.Suzang_Group_Back.service.IClientPageService;
import com.appatam.Suzang_Group_Back.service.IContactPageService;
import com.appatam.Suzang_Group_Back.service.IPresentationPageService;
import com.appatam.Suzang_Group_Back.service.IServicePageService;
import com.appatam.Suzang_Group_Back.service.IUtilisateurService;

@SpringBootApplication
@EnableScheduling
public class Suzang_Group_BackApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		return application.sources(Suzang_Group_BackApplication.class); 
	}
	
	@Autowired
	private EmailSenderService senderService;
	
	public static void main(String[] args) {
			 
		SpringApplication.run(Suzang_Group_BackApplication.class, args);
		
		 
		
	} 
	   
	 /*@EventListener(ApplicationReadyEvent.class)
	  public void SendMail() {
		  
		 senderService.sendeEmail("sangadam7407@gmail.com", "Voici le subject" , "le contenue est le suivant votre mail est bien zoooo");
	  }*/
	
	@Bean 
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200","https://suzang.appatam.com")); 
		//corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
		//		"Accept", "Authorization", "Origin, Accept", "X-Requested-With",   
		//		"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
	
	
	
	
	@Bean 
	PasswordEncoder passwordEncoder(){ 
		
		return new BCryptPasswordEncoder();  
		
	};
	 
	
	  @Bean 
	  public CommandLineRunner run(IUtilisateurService iUtilisateurService,IPresentationPageService IpService
			  ,IClientPageService IcService,IAtoutPageSevice iaService,IServicePageService IsService,
			  IAccueilPageService iaccueilService,IContactPageService icontactsevice) {
		  return args -> { 
			  
			  if(iUtilisateurService.ListeUtilisateur().isEmpty()) {
				  
				  iUtilisateurService.enregistrerRole(new Role("ROLE_ADMIN"));
					iUtilisateurService.enregistrerRole(new Role("ROLE_MANAGER"));

					iUtilisateurService.enregistrerUtilisateur(
							new Utilisateur("sangare", "adama", "sangare", "123", new ArrayList<>()));
					iUtilisateurService
							.enregistrerUtilisateur(new Utilisateur("sang", "Ablo", "adama", "123", new ArrayList<>()));

					iUtilisateurService.ajouterRoleAUtilisateur("sangare", "ROLE_ADMIN");
					iUtilisateurService.ajouterRoleAUtilisateur("sangare", "ROLE_MANAGER");
					iUtilisateurService.ajouterRoleAUtilisateur("adama", "ROLE_MANAGER");
				  
			  }
			  
				
				
				//initialisation des pages 
				PagesInitialisation pi = new PagesInitialisation();
				
				 pi.initializePage( IpService, IcService,iaService,IsService,iaccueilService,icontactsevice);
				
	  };
	    
	  
	  }
	  
	  
	 
	 
	 
	
	 
	/*
	 * @Bean public ServletWebServerFactory servletContainer() { // Enable SSL
	 * Trafic TomcatServletWebServerFactory tomcat = new
	 * TomcatServletWebServerFactory() { protected void postProcessContext(Context
	 * context) { SecurityConstraint securityConstraint = new SecurityConstraint();
	 * securityConstraint.setUserConstraint("CONFIDENTIAL"); SecurityCollection
	 * collection = new SecurityCollection(); collection.addPattern("/*");
	 * securityConstraint.addCollection(collection);
	 * 
	 * } };
	 * 
	 * // Add HTTP to HTTPS redirect
	 * tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
	 * 
	 * return tomcat; }
	 * 
	 * 
	 * We need to redirect from HTTP to HTTPS. Without SSL, this application used
	 * port 8082. With SSL it will use port 8443. So, any request for 8082 needs to
	 * be redirected to HTTPS on 8443.
	 * 
	 * private Connector httpToHttpsRedirectConnector() { Connector connector = new
	 * Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
	 * connector.setScheme("http"); connector.setPort(8081);
	 * connector.setSecure(false); connector.setRedirectPort(8443); return
	 * connector; }
	 */

}
