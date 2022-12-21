package com.appatam.Suzang_Group_Back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appatam.Suzang_Group_Back.domain.ContenuDePage;
import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.Utilisateur;

public interface PageAccueilDao extends JpaRepository<PageAccueil, Long>{
	
	@Query("SELECT t FROM PageAccueil t ORDER BY t.ordre ASC")
	List<PageAccueil> findAll();
	

}
