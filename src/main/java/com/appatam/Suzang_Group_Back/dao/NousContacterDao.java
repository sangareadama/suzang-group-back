package com.appatam.Suzang_Group_Back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appatam.Suzang_Group_Back.domain.NousContacter;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;

public interface NousContacterDao extends JpaRepository<NousContacter, Long> {
	
	NousContacter findByEmail(String email); 

}
