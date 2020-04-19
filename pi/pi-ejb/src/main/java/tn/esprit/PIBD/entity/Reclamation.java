package tn.esprit.PIBD.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import tn.esprit.*;
@Entity
public class Reclamation implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Reclamation_ID") 
	int id_reclamation;


	@Override
	public String toString() {
		return "Reclamation [description=" + description + ", etat=" + etat + "]";
	}

	@Column(name="Description") 
	String description;
	@Column(name="EtatDeReclamation") 
	String etat="en cours";
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
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}


}
