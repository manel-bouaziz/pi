package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import tn.esprit.*;
@Entity 
public class Client implements Serializable
{
	private static final long serialVersionUID = 1L; 
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password
				+ ", account_number=" + account_number + ", account_type=" + account_type + ", interet=" + interet
				+ ", invest=" + invest + ", ordres=" + ordres + ", reclamation=" + reclamation + ", portfolio="
				+ portfolio + "]";
	}
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Client_ID") 
	int id;
	@Column(name="Name") 
	String name;
	public Set<Reclamation> getReclamation() {
		return reclamation;
	}
	public void setReclamation(Set<Reclamation> reclamation) {
		this.reclamation = reclamation;
	}
	@Column(name="Login") 
	String login;
	//ceci est un commentaire
	@Column(name="Password") 
	String password;
	@Column(name="Account_number") 
	int account_number;
	@Column(name="Account_type") 
	String account_type;
	@Column(name="Interet_Annuel")
	float interet;
	@Column(name="Investissement")
	float invest;
	public float getInteret() {
		return interet;
	}
	public void setInteret(float interet) {
		this.interet = interet;
	}
	public float getInvest() {
		return invest;
	}
	public void setInvest(float invest) {
		this.invest = invest;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
	private Set<Ordre> ordres;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
	private Set<Reclamation> reclamation;
	/*public Set<Reclamation> getReclamation() {
		return reclamation;
	}
	public void setReclamation(Set<Reclamation> reclamation) {
		this.reclamation = reclamation;
	}*/

	@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
	private Set<Portfolio> portfolio;

	public Client() {}
	public int getId() {return id;} 
	public void setId(int id) {this.id = id;}
	public String getName() {return name;} 
	public void setName(String name) {this.name = name;}
	public String getLogin() {return login;} 
	public void setLogin(String login) {this.login = login;}
	public String getPassword() {return password;} 

	public Set<Portfolio> getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Set<Portfolio> portfolio) {
		this.portfolio = portfolio;
	}
	
	public void setPassword(String password) {this.password = password;}
	public int getAccount_number() {return account_number;} 
	public void setAccount_number(int account_number) {this.account_number = account_number;}
	public String getAccount_type() {return account_type;} 
	public void setAccount_type(String account_type) {this.account_type = account_type;}
	//sans le portefeuille
	public String toString1() {
		return "Client [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password
				+ ", account_number=" + account_number + ", account_type=" + account_type + ", interet=" + interet
				+ ", invest=" + invest + "]";
	}
	

}




