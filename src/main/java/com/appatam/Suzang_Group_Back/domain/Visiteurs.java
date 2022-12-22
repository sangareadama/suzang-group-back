package com.appatam.Suzang_Group_Back.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Visiteurs {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ipAddress;
	private String pays;
	private String ville;
	private Double lat;
	private Double lon;
	private String hostIpAddress;
	private String hostName;
	private String pageVisite;
	private Date date;
	
	
	public Visiteurs() {
		super();
		this.date=new Date();
		// TODO Auto-generated constructor stub
	}
	
	public Visiteurs(String ipAddress, String pays, String ville, Double lat, Double lon, String hostIpAddress,
			String hostName, String pageVisite) {
		super();
		this.ipAddress = ipAddress;
		this.pays = pays;
		this.ville = ville;
		this.lat = lat;
		this.lon = lon;
		this.hostIpAddress = hostIpAddress;
		this.hostName = hostName;
		this.pageVisite = pageVisite;
		this.date=new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public String getHostIpAddress() {
		return hostIpAddress;
	}
	public void setHostIpAddress(String hostIpAddress) {
		this.hostIpAddress = hostIpAddress;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPageVisite() {
		return pageVisite;
	}
	public void setPageVisite(String pageVisite) {
		this.pageVisite = pageVisite;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
	
	
}
