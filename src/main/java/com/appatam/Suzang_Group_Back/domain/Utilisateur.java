package com.appatam.Suzang_Group_Back.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private	long id;
    private String nom;
    private String prenom;
    private String username;
    private String password; 
    @ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "UtilisateurRoles",
//			joinColumns = @JoinColumn(
//					name="UtilisateurId",referencedColumnName = "id"
//					),
//			inverseJoinColumns = @JoinColumn(
//					name="RoleId",referencedColumnName = "id"
//					)
//			)
 	private Collection<Role>  roles ;
    
    @ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImageModel> utilisateurImage;   
    
    
	public Set<ImageModel> getUtilisateurImage() { 
		return utilisateurImage;
	}
	public void setUtilisateurImage(Set<ImageModel> utilisateurImage) {
		this.utilisateurImage = utilisateurImage;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles; 
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public Utilisateur(String nom, String prenom, String username, String password, Collection<Role> roles) {
		super();
		this.nom = nom;  
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

    
	

}
