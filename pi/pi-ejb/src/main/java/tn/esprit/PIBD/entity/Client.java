package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity 
public class Client implements Serializable
{
	private static final long serialVersionUID = 1L; 
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Client_ID") 
	int id;
	@Column(name="Name") 
	String name;
	@Column(name="Login") 
	String login;
	@Column(name="Password") 
	String password;
	@Column(name="Account_number") 
	int account_number;
	@Column(name="Account_type") 
	String account_type;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="client",fetch=FetchType.EAGER)
	private List<Ordre> ordres;
	
	public Client() {}
	public String getName() {return name;} 
	public void setName(String name) {this.name = name;}
	public String getLogin() {return login;} 
	public void setLogin(String login) {this.login = login;}
	public String getPassword() {return password;} 
	public void setPassword(String password) {this.password = password;}
	public int getAccount_number() {return account_number;} 
	public void setAccount_number(int account_number) {this.account_number = account_number;}
	public String getAccount_type() {return account_type;} 
	public void setAccount_type(String account_type) {this.account_type = account_type;}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Ordre> getOrdres() {
		return ordres;
	}
	public void setOrdres(List<Ordre> ordres) {
		this.ordres = ordres;
	}
	

}
