package com.appatam.Suzang_Group_Back.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
public class PageBloc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private	long id;
    private String designation;
    @ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
 	private Collection<Role>  roles ;
    
    
    
	public PageBloc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBloc(String designation, Collection<Role> roles) {
		super();
		this.designation = designation;
		this.roles = roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

    
}
