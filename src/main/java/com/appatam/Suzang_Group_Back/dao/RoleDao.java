package com.appatam.Suzang_Group_Back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appatam.Suzang_Group_Back.domain.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
	
	Role findByLibelle(String libelle);
}
