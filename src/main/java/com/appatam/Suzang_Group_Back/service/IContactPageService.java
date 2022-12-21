package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.AtoutPage;
import com.appatam.Suzang_Group_Back.domain.ContactPage;

public interface IContactPageService {
	
	public ContactPage enregistrerContactPage(ContactPage contactPage);
	 
	public ContactPage rechercherContactPage(Long id);
	
	public List<ContactPage>ListeContactPage(); 
	
    public ContactPage modifierContactPage(ContactPage contactPage);
    
	
	public void supprimerContactPage(ContactPage contactPage);

}
