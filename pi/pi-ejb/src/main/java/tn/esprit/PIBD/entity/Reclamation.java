package tn.esprit.PIBD.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reclamation implements Serializable
{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Reclamation_ID") 
	int id_reclamation;

	@Column(name="Description") 
	String description;
	@ManyToOne
	Client client;
	public Reclamation() {
		
	}
	public Reclamation(String string) {
		this.description=string;
	}
	public int getId_reclamation() {
		return id_reclamation;
	}
	public void setId(int id) {
		this.id_reclamation = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setClient(Client client) {
		// TODO Auto-generated method stub
		this.client = client;

	}
	

}
