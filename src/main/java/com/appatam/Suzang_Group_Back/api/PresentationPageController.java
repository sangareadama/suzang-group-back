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

import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;
import com.appatam.Suzang_Group_Back.service.IPresentationPageService;
import com.appatam.Suzang_Group_Back.service.IUtilisateurService;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/api")
@RequiredArgsConstructor
public class PresentationPageController { 
	
	AccueilPageController ac = new AccueilPageController();
	
	@Autowired
	private IPresentationPageService iPresentationService;
	 
	@GetMapping("/presentation")   
	public ResponseEntity<List<PresentationPage>>listePresentation(){
		
		       
		return ResponseEntity.ok().body(iPresentationService.ListePresentation());
		
	}
	
	@PostMapping("/presentation/save")
	public ResponseEntity<PresentationPage> enregistrerPresentation(@RequestBody PresentationPage ps) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/presentation/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iPresentationService.enregistrerPresentation(ps));

	}
	  
	
	@PostMapping("/presentation/update")
	public ResponseEntity<PresentationPage> modifierPresentation(@RequestBody PresentationPage ps) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/presentation/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iPresentationService.modifierPresentation(ps));
	   
	}
	
}
