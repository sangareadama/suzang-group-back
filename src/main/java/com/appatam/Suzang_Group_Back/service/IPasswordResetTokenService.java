package com.appatam.Suzang_Group_Back.service;

import com.appatam.Suzang_Group_Back.domain.PasswordRessetToken;


public interface IPasswordResetTokenService {
	
	 public PasswordRessetToken save(PasswordRessetToken passwordResetToken) ;
	 public PasswordRessetToken findByToken(String token);
	 public PasswordRessetToken modifier(PasswordRessetToken passwordResetToken);

}
