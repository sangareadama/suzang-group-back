package com.appatam.Suzang_Group_Back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appatam.Suzang_Group_Back.domain.Utilisateur;

public interface UtilisateurDao  extends JpaRepository<Utilisateur, Long>{

	Utilisateur findByUsername(String username); 
} 
