package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.Role;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;

public interface IUtilisateurService {
	
	public Utilisateur enregistrerUtilisateur(Utilisateur utilisateur);
	
	public Role  enregistrerRole(Role role);    
	
	public void  ajouterRoleAUtilisateur(String email, String libelle);
	
	public Utilisateur rechercherUtilisateur(String username);
	
	public List<Utilisateur>ListeUtilisateur();
	
	public List<Role> ListeRole();
	
	public Utilisateur modifierUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur rechercherUtilisateurParId(Long id);
	
	public void supprimerUtilisateur(Utilisateur utilisateur);
	
	
	

 
}
