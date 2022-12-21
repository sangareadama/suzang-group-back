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
import com.appatam.Suzang_Group_Back.domain.ClientPage;
import com.appatam.Suzang_Group_Back.service.IAtoutPageSevice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AtoutPageController {
	 
	@Autowired
	IAtoutPageSevice iAtoutPageSevice;
	
	@GetMapping("/atoutPage")  
	public ResponseEntity<List<AtoutPage>>listeAtoutPage(){
		
		return ResponseEntity.ok().body(iAtoutPageSevice.ListeAtoutPage());
		
	}
	
	@PostMapping("/atoutPage/save")
	public ResponseEntity<AtoutPage> enregistrerAtoutPage(@RequestBody AtoutPage ap) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/atoutPage/save").toUriString());
		
	  return ResponseEntity.created(uri).body(iAtoutPageSevice.enregistrerAtoutPage(ap));
	   
	  }
	  
	@PostMapping("/atoutPage/update")
	public ResponseEntity<AtoutPage> modifierAtoutPage(@RequestBody AtoutPage ap) {
		
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/atoutPage/update").toUriString());
		
	  return ResponseEntity.created(uri).body(iAtoutPageSevice.modifierAtoutPage(ap));
	   
	  }

}
