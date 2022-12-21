package com.appatam.Suzang_Group_Back.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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

import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.domain.StatNewsletter;
import com.appatam.Suzang_Group_Back.service.IStatNewsletterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StatNewsletterController {
	
	@Autowired
	IStatNewsletterService iStatNewsletterService;
	
	@GetMapping("/statNewsletter")  
	public ResponseEntity<List<StatNewsletter>>listeStatNewsletter(){
		
		return ResponseEntity.ok().body(iStatNewsletterService.ListeStatNewsletter());
		
	}  
	
	@GetMapping("/statNewsletterParJour/{jour}")
	public ResponseEntity<StatNewsletter> StatNewsletterParJour(@PathVariable String jour) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/newsletter/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iStatNewsletterService.rechercherStatNewsletterParJour(jour));
	   
	  } 
	
	@GetMapping("/statNewsletterParMois/{mois}")
	public ResponseEntity<List<StatNewsletter>> StatNewsletterParMois(@PathVariable String mois) {
		
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;"+mois.toLowerCase());
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/statNewsletterParMois").toUriString());
		
	  return ResponseEntity.created(uri).body(iStatNewsletterService.findByDateMois(mois.toLowerCase()));
	   
	  }
	
	@GetMapping("/statNewsletterParInterval/{deb}/{fin}")
	public ResponseEntity<List<StatNewsletter>> statNewsletterParInterval(@PathVariable Date deb,@PathVariable Date fin) {
		
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;"+deb +" et "+fin);
		
		List<StatNewsletter> stat = iStatNewsletterService.ListeStatNewsletter();
		
		List<StatNewsletter> statParInterval = new ArrayList<StatNewsletter>();
		
		 for (StatNewsletter statNewsletter : stat) {
			 
			 if(statNewsletter.getDate().after(deb) && statNewsletter.getDate().before(fin)) {
				 
				 statParInterval.add(statNewsletter);
				 
				 System.out.println(";;;;;;;;========= "+statNewsletter.getDate());
				 
				 
			 }
			 
		}
		
		
		/* Date currentDate = new Date();
		  
	        System.out.println("Date 2: " + currentDate);
	  
	        System.out.println("Is Date 2 before Date 1: "
	                         + currentDate.before(deb));*/
	    
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/statNewsletterParMois").toUriString());
		
	   return ResponseEntity.created(uri).body(statParInterval);
		   
	  }
	
	@GetMapping("/statNewsletterParAn/{annee}")
	public ResponseEntity<List<StatNewsletter>> StatNewsletterParAn(@PathVariable String annee) {
		
		System.out.println(";;;;;;;;;;;;;;;;;;;;;;;"+annee);
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/statNewsletterParAn").toUriString());
		
		/*List<StatNewsletter> stat=	iStatNewsletterService.findByAnnee(annee);
		
		for (StatNewsletter statNewsletter : stat) {
			System.out.println("okk;;;;;;;;;;;;;;;;;;;;;;;"+statNewsletter.getQuantite());
		}*/
		return ResponseEntity.created(uri).body(iStatNewsletterService.findByAnnee(annee));
	   
	  }

}
