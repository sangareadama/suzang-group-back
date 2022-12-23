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

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.ContenuPageTemp;
import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.domain.Offre;
import com.appatam.Suzang_Group_Back.service.IOffreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OffreController {
	
	@Autowired
	IOffreService iOffreService;
	
	@GetMapping("/offre")  
	public ResponseEntity<List<Offre>>listeOffre(){
		
		return ResponseEntity.ok().body(iOffreService.ListeOffre());	
	}
	
	@PostMapping("/offre/save")  
	public ResponseEntity<Offre> EnregistrerOffre(@RequestBody Offre of) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/offre/save").toUriString());
		
		return ResponseEntity.created(uri).body(iOffreService.enregistrerOffre(of));
	   
	 }
	
	@PostMapping("/offre/update")
	public Offre updateOffre(@RequestBody Offre offre) {
		
		return iOffreService.modifierOffre(offre);
	 
	  }

	  
	@PostMapping("/offre/supprimer")
	public ResponseEntity<Offre> supprimerOffre(@RequestBody Offre offre) {
		
		iOffreService.supprimerOffre(offre);
		
		return null;
	 
	  }

}
