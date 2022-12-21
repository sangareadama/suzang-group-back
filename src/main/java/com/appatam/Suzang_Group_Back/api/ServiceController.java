package com.appatam.Suzang_Group_Back.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.domain.AtoutPage;
import com.appatam.Suzang_Group_Back.domain.ServicePage;
import com.appatam.Suzang_Group_Back.domain.ServicesItem;
import com.appatam.Suzang_Group_Back.service.IServiceItemService;
import com.appatam.Suzang_Group_Back.service.IServicePageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor 
public class ServiceController {
	
	@Autowired
	IServiceItemService iServiceItemService;
	
	@Autowired
	IServicePageService iServicePageService;
	

	@GetMapping("/serviceItem")  
	public ResponseEntity<List<ServicesItem>>listeServicesItem(){
		
		return ResponseEntity.ok().body(iServiceItemService.ListeServicesItem());
		
	}
	
	@PostMapping("/serviceItem/save")
	public ResponseEntity<ServicesItem> enregistrerServicesItem(@RequestBody ServicesItem si) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/serviceItem/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iServiceItemService.enregistrerServicesItem(si));
	   
	  }
	  
	@PostMapping("/serviceItem/update")
	public ResponseEntity<ServicesItem> modifierServicesItem(@RequestBody ServicesItem si) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/serviceItem/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iServiceItemService.modifierServicesItem(si));
	   
	  }
	
	 
	//pages contente
	
	@GetMapping("/servicePage")  
	public ResponseEntity<List<ServicePage>>listeservicePage(){
		
		return ResponseEntity.ok().body(iServicePageService.ListeServicePage());
		
	}
	
	@PostMapping("/servicePage/save")
	public ResponseEntity<ServicePage> enregistrerservicePage(@RequestBody ServicePage sp) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicePage/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iServicePageService.enregistrerServicePage(sp));
	   
	  }
	  
	@PostMapping("/servicePage/update")
	public ResponseEntity<ServicePage> modifierServicePage(@RequestBody ServicePage sp) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/servicePage/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iServicePageService.modifierServicePage(sp));
	   
	  } 

}
