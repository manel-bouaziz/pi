package tn.esprit.PIBD.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Company implements Serializable
{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Company_ID") 
	int id;
	
	@Column(name="Name") 
	String name;
	@Column(name="Logo") 
	String logo;
	@Column(name="Description") 
	String description;
	public Company() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
