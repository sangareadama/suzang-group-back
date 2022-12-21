package com.appatam.Suzang_Group_Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appatam.Suzang_Group_Back.domain.Role;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;
import com.appatam.Suzang_Group_Back.service.IUtilisateurService;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/api")
@RequiredArgsConstructor
public class UtilisateurController {
	
	@Autowired
	private IUtilisateurService iUtilisateurService;
	

	// @RequestMapping(value="/user",method=RequestMethod.GET)
	@GetMapping("/user")
	 public List<Utilisateur>
	  ListeEleve() {
		 
		 System.out.println("okkkkk");
	  
	  return iUtilisateurService.ListeUtilisateur();
	 }
	
	@GetMapping("/user/role")
	 public List<Role>
	  ListeRole() {
	  
	  return iUtilisateurService.ListeRole();
	 }
}
