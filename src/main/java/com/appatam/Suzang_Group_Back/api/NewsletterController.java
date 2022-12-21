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

import com.appatam.Suzang_Group_Back.Model.DessouscrirNewsModel;
import com.appatam.Suzang_Group_Back.domain.ContactPage;
import com.appatam.Suzang_Group_Back.domain.Newsletter;
import com.appatam.Suzang_Group_Back.service.EmailSenderService;
import com.appatam.Suzang_Group_Back.service.INewsletterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NewsletterController {
	
	@Autowired
	INewsletterService inewsletterService;
	
	@Autowired
	private EmailSenderService senderService;
	
	@GetMapping("/newsletter")  
	public ResponseEntity<List<Newsletter>>listeNewsletter(){
		
		return ResponseEntity.ok().body(inewsletterService.ListeNewsletter());
		
	}
	
	@PostMapping("/newsletter/save")
	public ResponseEntity<Newsletter> enregistrerNewsletter(@RequestBody Newsletter nl) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/newsletter/save").toUriString());
		
	  return ResponseEntity.created(uri).body(inewsletterService.enregistrerNewsletter(nl));
	   
	  }
	
	@PostMapping("/newsletter/supprimer")
	public ResponseEntity<Newsletter> supprimerNewsletter(@RequestBody Newsletter nl) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/newsletter/invalider").toUriString());
		
		inewsletterService.SupprimerNewsletterParId(nl.getId());
		
		return null;
		
		
	   
	  }

	
	@PostMapping("/newsletter/dessouscrir")
	public ResponseEntity<Newsletter> invaliderNewsletter(@RequestBody DessouscrirNewsModel ds) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/newsletter/invalider").toUriString());
		
		Newsletter ns = inewsletterService.rechercherNewsletterParEmail(ds.getUsername());
		
	  return ResponseEntity.created(uri).body(inewsletterService.dessouscrirNewsletterParEmail(ns));
	   
	  }
	
	/*@PostMapping("/newsletter/sendDessouscrirMail")
	public ResponseEntity<Newsletter> sendDessouscrirMail(@RequestBody DessouscrirNewsModel ds) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/newsletter/invalider").toUriString());
		
		Newsletter ns = inewsletterService.rechercherNewsletterParEmail(ds.getUsername());
		
	  return ResponseEntity.created(uri).body(inewsletterService.dessouscrirNewsletterParEmail(ns));
	   
	  }
	*/


}
