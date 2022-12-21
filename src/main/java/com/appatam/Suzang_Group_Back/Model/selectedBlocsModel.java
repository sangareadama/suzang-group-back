package com.appatam.Suzang_Group_Back.Model;

import java.util.Collection;

import javax.persistence.Column;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;

public class selectedBlocsModel {
	
	
	private Collection<ContenuDePage> contenus; 
	
	

	public selectedBlocsModel(Collection<ContenuDePage> contenus) {
		super();
		this.contenus = contenus;
	}

	public selectedBlocsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection<ContenuDePage> getContenus() {
		return contenus;
	}

	public void setContenus(Collection<ContenuDePage> contenus) {
		this.contenus = contenus;
	}
	
	

}
