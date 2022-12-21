package com.appatam.Suzang_Group_Back.api.pageController;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.ContenuPageTemp;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.service.IContenuDePageService;
import com.appatam.Suzang_Group_Back.service.IPageAccueilService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ContenuDePageController {
	
	@Autowired
	IPageAccueilService iPageAccueilService;
	
	@Autowired 
	IContenuDePageService iContenuDePageService;
	
	// Contenus des pages
	
		@GetMapping("/pageAccueil/contenus")  
		public ResponseEntity<List<ContenuDePage>>listeContenuDePage(){
			
			return ResponseEntity.ok().body(iContenuDePageService.ListeContenuDePage());	
		}
		
		@GetMapping("/pageAccueil/contenusTemp")     
		public ResponseEntity<List<ContenuPageTemp>>listeContenusTemp(){
			
			return ResponseEntity.ok().body(iContenuDePageService.ListeContenuPageTemp());
		}
		
		@PostMapping("/pageAccueil/contenus/save")
		public ResponseEntity<ContenuPageTemp> EnregistrerContenuDePage(@RequestBody ContenuPageTemp cdp) {
			
			URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pageAccueil/contenus/save").toUriString());
			
			return ResponseEntity.created(uri).body(iContenuDePageService.enregistrerContenuPageTemp(cdp));
		   
		 }
		  
		@PostMapping("/pageAccueil/contenusReel/save/{id}")
		public ResponseEntity<PageAccueil> EnregistrerContenuDePageReel(@PathVariable("id") Long id,@RequestBody ContenuDePage cdp) {
			
			PageAccueil pa = iPageAccueilService.rechercherPageAccueil(id);
			
			ContenuDePage ctpReel= iContenuDePageService.enregistrerContenuDePage(cdp);
			
			iPageAccueilService.ajouterContenuDePageAuBloc(pa.getId(), ctpReel.getId());
			
			URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contenusReel/save").toUriString());
			
			return ResponseEntity.created(uri).body(pa);
		   
		 }
		
		
		@PostMapping("/pageAccueil/contenus/update")
		public ResponseEntity<PageAccueil> updateContenuDePage(@RequestBody ContenuDePage cdp) {
			
			URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contenus/update").toUriString());
			
			return ResponseEntity.created(uri).body(iContenuDePageService.modifierContenuDePage(cdp));
		   
		 }
		
		@PostMapping("/pageAccueil/contenusTemp/supprimer")
		public void SupprimerContenusTemp(@RequestBody ContenuPageTemp cdp) {
				
			iContenuDePageService.supprimerContenuPageTemp(cdp);
		   
		 }
		
		/*@PostMapping("/pageAccueil/contenus/supprimer")
		public PageAccueil SupprimerContenuDePage(@RequestBody ContenuDePage cdp) {
			
			return iContenuDePageService.supprimerContenuDePage(cdp);
		   
		 }*/

}
