package com.appatam.Suzang_Group_Back.Model;

public class UtilisateurModel {
	
	 private String username;
	 private String password;
	 
	 
	public UtilisateurModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public UtilisateurModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 

}
