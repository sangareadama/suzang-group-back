package com.appatam.Suzang_Group_Back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appatam.Suzang_Group_Back.domain.PasswordRessetToken;

public interface PasswordResetTokenDao extends JpaRepository<PasswordRessetToken, String> {

}
