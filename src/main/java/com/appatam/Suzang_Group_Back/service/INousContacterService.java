package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.ContactPage;
import com.appatam.Suzang_Group_Back.domain.NousContacter;

public interface INousContacterService {
	
	public NousContacter enregistrerNousContacter(NousContacter nousContacter);
	 
	public NousContacter rechercherNousContacterParEmail(String email);
	
	public List<NousContacter>ListeNousContacter(); 
	
    public NousContacter modifier(NousContacter nousContacter);
    
    public void NousContacterVus(NousContacter nousContacter);
	
	public void supprimerNousContacter(NousContacter nousContacter);


}
