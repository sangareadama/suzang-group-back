package com.appatam.Suzang_Group_Back.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appatam.Suzang_Group_Back.domain.PageAccueil;
import com.appatam.Suzang_Group_Back.domain.PagePresentation;

public interface PagePresentationDao extends JpaRepository<PagePresentation, Long> {
	
	@Query("SELECT t FROM PagePresentation t ORDER BY t.id ASC")
	List<PagePresentation> findAll();

}
