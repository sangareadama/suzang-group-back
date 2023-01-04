package com.appatam.Suzang_Group_Back.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.Model.ClientDatailModel;
import com.appatam.Suzang_Group_Back.domain.AccueilPage;
import com.appatam.Suzang_Group_Back.domain.Visiteurs;
import com.appatam.Suzang_Group_Back.service.IAccueilPageService;
import com.appatam.Suzang_Group_Back.service.IVisiteursService;
//import com.maxmind.geoip2.DatabaseReader;
//import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccueilPageController {
	
	@Autowired
	IAccueilPageService iAccueilPageService;
	
//	@Autowired
//	 private DatabaseReader databaseReader;
	
	@Autowired
	 private IVisiteursService VisiteursService;
	
	
	public static String getUrlContents(String theUrl)  
	  {  
	    StringBuilder content = new StringBuilder();  
	  // Use try and catch to avoid the exceptions  
	    try  
	    {  
	      URL url = new URL(theUrl); // creating a url object  
	      URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
	    
	      // wrapping the urlconnection in a bufferedreader  
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
	      String line;  
	      // reading from the urlconnection using the bufferedreader  
	      while ((line = bufferedReader.readLine()) != null)  
	      {  
	        content.append(line + "\n");  
	      }  
	      bufferedReader.close();  
	    }  
	    catch(Exception e)  
	    {  
	      e.printStackTrace();  
	    }  
	    return content.toString();  
	  }  
	
	  
	
	@GetMapping("/accueilPage")    
	public ResponseEntity<List<AccueilPage>>listeAccueilPage(HttpServletRequest rq) throws IOException{
		
		 //site de recuperation des details du client
		 String clientDetail  = getUrlContents("https://ipapi.co/json/"); 
		 
		 //formatage en une classe java
		 ClientDatailModel JsonDetail = new ObjectMapper().readValue(clientDetail, ClientDatailModel.class);
		 
		 Visiteurs vs = new Visiteurs(JsonDetail.getIp(),JsonDetail.getCity() , JsonDetail.getCountry_name(), JsonDetail.getLatitude(), JsonDetail.getLongitude(), JsonDetail.getOrg());
		 
		 
		return ResponseEntity.ok().body(iAccueilPageService.ListeAccueilPage());
		  
	}
	 
	@PostMapping("/accueilPage/save")
	public ResponseEntity<AccueilPage> enregistrerAccueilPage(@RequestBody AccueilPage ap) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accueilPage/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iAccueilPageService.enregistrerAccueilPage(ap));
	   
	  }
	  
	@PostMapping("/accueilPage/update")
	public ResponseEntity<AccueilPage> modifierAccueilPage(@RequestBody AccueilPage ap) {
		
	  URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/accueilPage/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iAccueilPageService.modifierAccueilPage(ap));
	   
	  }
}
