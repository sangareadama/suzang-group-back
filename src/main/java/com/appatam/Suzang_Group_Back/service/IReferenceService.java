package com.appatam.Suzang_Group_Back.service;

import java.util.List;

import com.appatam.Suzang_Group_Back.domain.PresentationPage;
import com.appatam.Suzang_Group_Back.domain.Reference;

public interface IReferenceService {
	

	public Reference enregistrerReference(Reference reference);
	
	public Reference rechercherReference(Long id);
	
	public List<Reference>ListeReference();
	
    public Reference modifierReference(Reference reference);
	
	
	public void supprimerReference(Reference reference);
	

}
