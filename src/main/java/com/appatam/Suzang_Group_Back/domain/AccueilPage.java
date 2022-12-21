package com.appatam.Suzang_Group_Back.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccueilPage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre11;
	@Column(length = 2000)
	private String contenu11;
	private int decalageHaut1;
	private int decalageGauche1;
	private String titre22;
	@Column(length = 2000)
	private String contenu21;
	@Column(length = 2000)
	private String contenu22;
	private int decalageHaut2;
	private int decalageGauche2;
	private String titre33;
	@Column(length = 2000)  
	private String contenu31; 
	@Column(length = 2000)
	private String contenu32;
	private int decalageHaut3;
	private int decalageGauche3;

	
	
	
	public AccueilPage(String titre11, String contenu11, int decalageHaut1, int decalageGauche1, String titre22,
			String contenu21, String contenu22, int decalageHaut2, int decalageGauche2, String titre33,
			String contenu31, String contenu32, int decalageHaut3, int decalageGauche3) {
		super();
		this.titre11 = titre11;
		this.contenu11 = contenu11;
		this.decalageHaut1 = decalageHaut1;
		this.decalageGauche1 = decalageGauche1;
		this.titre22 = titre22;
		this.contenu21 = contenu21;
		this.contenu22 = contenu22;
		this.decalageHaut2 = decalageHaut2;
		this.decalageGauche2 = decalageGauche2;
		this.titre33 = titre33;
		this.contenu31 = contenu31;
		this.contenu32 = contenu32;
		this.decalageHaut3 = decalageHaut3;
		this.decalageGauche3 = decalageGauche3;
	}

	public AccueilPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre11() {
		return titre11;
	}
	public void setTitre11(String titre11) {
		this.titre11 = titre11;
	}
	public String getContenu11() {
		return contenu11;
	}
	public void setContenu11(String contenu11) {
		this.contenu11 = contenu11;
	}
	public String getTitre22() {
		return titre22;
	}
	public void setTitre22(String titre22) {
		this.titre22 = titre22;
	}
	public String getContenu21() {
		return contenu21;
	}
	public void setContenu21(String contenu21) {
		this.contenu21 = contenu21;
	}
	public String getContenu22() {
		return contenu22;
	}
	public void setContenu22(String contenu22) {
		this.contenu22 = contenu22;
	}
	public String getTitre33() {
		return titre33;
	}
	public void setTitre33(String titre33) {
		this.titre33 = titre33;
	}
	public String getContenu31() {
		return contenu31;
	}
	public void setContenu31(String contenu31) {
		this.contenu31 = contenu31;
	}
	public String getContenu32() {
		return contenu32;
	}
	public void setContenu32(String contenu32) {
		this.contenu32 = contenu32;
	}

	public int getDecalageHaut1() {
		return decalageHaut1;
	}

	public void setDecalageHaut1(int decalageHaut1) {
		this.decalageHaut1 = decalageHaut1;
	}

	public int getDecalageGauche1() {
		return decalageGauche1;
	}

	public void setDecalageGauche1(int decalageGauche1) {
		this.decalageGauche1 = decalageGauche1;
	}

	public int getDecalageHaut2() {
		return decalageHaut2;
	}

	public void setDecalageHaut2(int decalageHaut2) {
		this.decalageHaut2 = decalageHaut2;
	}

	public int getDecalageGauche2() {
		return decalageGauche2;
	}

	public void setDecalageGauche2(int decalageGauche2) {
		this.decalageGauche2 = decalageGauche2;
	}

	public int getDecalageHaut3() {
		return decalageHaut3;
	}

	public void setDecalageHaut3(int decalageHaut3) {
		this.decalageHaut3 = decalageHaut3;
	}

	public int getDecalageGauche3() {
		return decalageGauche3;
	}

	public void setDecalageGauche3(int decalageGauche3) {
		this.decalageGauche3 = decalageGauche3;
	}
	
}
