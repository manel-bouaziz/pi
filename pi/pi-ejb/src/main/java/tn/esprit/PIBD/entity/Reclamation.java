package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tn.esprit.*;

@Table(name = "Reclamation")
@Entity
public class Reclamation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Reclamation_ID")
	int id_reclamation;
	@Column(name = "Date_de_Reclamation")
	LocalDateTime date_achat;

	@Column(name = "Description")
	String description;
	@Column(name = "EtatDeReclamation")
	String etat = "en cours";
	@ManyToOne
	Client client;

	public Reclamation(int id_reclamation, LocalDateTime date_achat, String description, String etat, Client client) {
		super();
		this.id_reclamation = id_reclamation;
		this.date_achat = date_achat;
		this.description = description;
		this.etat = etat;
		this.client = client;
	}

	public Reclamation() {

	}

	public Reclamation(String string) {
		this.description = string;
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

	@Override
	public String toString() {
		return "Reclamation [id_reclamation=" + id_reclamation + ", date_achat=" + date_achat + ", description="
				+ description + ", etat=" + etat + ", client=" + client + "]";
	}

	public LocalDateTime getDate_achat() {
		return date_achat;
	}

	public void setDate_achat(LocalDateTime date_achat) {
		this.date_achat = date_achat;
	}

	public void setId_reclamation(int id_reclamation) {
		this.id_reclamation = id_reclamation;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
