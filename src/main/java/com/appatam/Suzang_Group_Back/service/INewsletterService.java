package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.Newsletter;

public interface INewsletterService {
	
	public Newsletter enregistrerNewsletter(Newsletter newsletter);
	 
	public Newsletter rechercherNewsletter(Long id);
	
	public List<Newsletter>ListeNewsletter(); 
	
    public Newsletter rechercherNewsletterParEmail(String  email);
	
	public Newsletter dessouscrirNewsletterParEmail(Newsletter newsletter);
	
	public void SupprimerNewsletterParId(Long id);  


}
