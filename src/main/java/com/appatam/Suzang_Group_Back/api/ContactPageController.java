package com.appatam.Suzang_Group_Back.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appatam.Suzang_Group_Back.domain.ContactPage;
import com.appatam.Suzang_Group_Back.domain.NousContacter;
import com.appatam.Suzang_Group_Back.service.EmailSenderService;
import com.appatam.Suzang_Group_Back.service.IContactPageService;
import com.appatam.Suzang_Group_Back.service.INousContacterService;

@RestController
@RequestMapping("/api")
public class ContactPageController {
	
	@Autowired
	IContactPageService iContactPageService;
	
	@Autowired
	private EmailSenderService senderService;
	
	@Autowired
	 INousContacterService iNousContacterService;
	
	private int NosContactsMessageNbre;
	
	@GetMapping("/contactPage")  
	public ResponseEntity<List<ContactPage>>listeContactPage(){
		
		return ResponseEntity.ok().body(iContactPageService.ListeContactPage());
		
	}
	
	@GetMapping("/nombreMessage")    
	public int nombreMessage(){
		
		return NosContactsMessageNbre;
		
	}
	@PostMapping("/messageOuvert")  
	public int MessageOuvert(@RequestBody NousContacter cp){
		
		
		if(!cp.isVus()) {
			
			iNousContacterService.NousContacterVus(cp);
			
			if(NosContactsMessageNbre>0) {
				NosContactsMessageNbre-=1; 
			}	
		}
		
		return NosContactsMessageNbre;
		
	}
	  
	@PostMapping("/contactPage/save")
	public ResponseEntity<ContactPage> enregistrerContactPage(@RequestBody ContactPage cp) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contactPage/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iContactPageService.enregistrerContactPage(cp));
	   
	  }
	  
	@PostMapping("/contactPage/update")
	public ResponseEntity<ContactPage> modifierContactPage(@RequestBody ContactPage cp) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contactPage/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iContactPageService.modifierContactPage(cp));
	   
	  }
	@GetMapping("/contactPage/nousContacter")    
	public ResponseEntity<List<NousContacter>>listeNousContacter(){
		
		return ResponseEntity.ok().body(iNousContacterService.ListeNousContacter());
		  
	}
	
	@PostMapping("/contactPage/sendEmail") 
	public ResponseEntity<SimpleMailMessage> sendEmail(@RequestBody NousContacter em) { 
		
				NosContactsMessageNbre+=1; 
		
		  String subject ="Suzang_Group : "+em.getObjet();
		  
		  iNousContacterService.enregistrerNousContacter(em);
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/contactPage/sendEmail").toUriString());
		
	  return ResponseEntity.created(uri).body(senderService.ContactUs(em.getEmail(),subject  , em.getCommentaire()));
	    
	  }


}
