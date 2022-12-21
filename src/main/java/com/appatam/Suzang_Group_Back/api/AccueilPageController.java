package com.appatam.Suzang_Group_Back.api;

import java.net.URI;
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

import com.appatam.Suzang_Group_Back.Commun.ClientReferences;
import com.appatam.Suzang_Group_Back.domain.AccueilPage;
import com.appatam.Suzang_Group_Back.domain.AtoutPage;
import com.appatam.Suzang_Group_Back.domain.Visiteurs;
import com.appatam.Suzang_Group_Back.service.IAccueilPageService;
import com.appatam.Suzang_Group_Back.service.IVisiteursService;

import lombok.RequiredArgsConstructor;



import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

import static java.util.Objects.nonNull;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccueilPageController {
	
	@Autowired
	IAccueilPageService iAccueilPageService;
	
	@Autowired
	 private DatabaseReader databaseReader;
	
	@Autowired
	 private IVisiteursService VisiteursService;
	
	
	@GetMapping("/accueilPage")  
	public ResponseEntity<List<AccueilPage>>listeAccueilPage(HttpServletRequest rq) throws IOException, GeoIp2Exception{
		
		ClientReferences cr = new ClientReferences(databaseReader);

		 Visiteurs vs= cr.getViseteursLocation(rq);    
		 
		 VisiteursService.enregistrerVisiteurs(vs);
		 

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
