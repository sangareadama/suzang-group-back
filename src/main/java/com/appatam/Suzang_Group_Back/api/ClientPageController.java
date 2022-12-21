package com.appatam.Suzang_Group_Back.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.domain.ClientPage;
import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.service.IClientPageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientPageController {
	
	@Autowired
	IClientPageService iClientPageService;
	
	@GetMapping("/clientPage")  
	public ResponseEntity<List<ClientPage>>listeClientPage(){
		
		return ResponseEntity.ok().body(iClientPageService.ListeClientPage());
		
	}
	
	@PostMapping("/clientPage/save")
	public ResponseEntity<ClientPage> enregistrerClientPage(@RequestBody ClientPage cp) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientPage/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iClientPageService.enregistrerClientPage(cp));
	   
	  }
	  
	@PostMapping("/clientPage/update")
	public ResponseEntity<ClientPage> modifierClientPage(@RequestBody ClientPage cp) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/clientPage/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iClientPageService.modifierClientPage(cp));
	   
	  }
	
	

}
