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
import javax.persistence.Table;

import tn.esprit.*;

@Table(name = "Client")
@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Client_ID")
	int id;
	@Column(name = "Name")
	String name;
	@Column(name = "Login")
	String login;
	@Column(name = "Password")
	String password;
	@Column(name = "Proxy")
	String proxy;
	@Column(name = "Account_number")
	int account_number;
	@Column(name = "Account_type")
	String account_type;
	@Column(name = "Interet_Annuel")
	float interet;
	@Column(name = "Investissement")
	float invest;

	
	public Client(int id, String name, String login, String password, String proxy, int account_number,
			String account_type, float interet, float invest) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.proxy = proxy;
		this.account_number = account_number;
		this.account_type = account_type;
		this.interet = interet;
		this.invest = invest;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private Set<Ordre> ordres;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private Set<Reclamation> reclamation;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private Set<Portfolio> portfolio;

	public Client() {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public Set<Reclamation> getReclamation() {
		return reclamation;
	}

	public void setReclamation(Set<Reclamation> reclamation) {
		this.reclamation = reclamation;
	}

	public Set<Portfolio> getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Set<Portfolio> portfolio) {
		this.portfolio = portfolio;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

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

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + ", proxy="
				+ proxy + ", account_number=" + account_number + ", account_type=" + account_type + ", interet="
				+ interet + ", invest=" + invest + "]";
	}

}
