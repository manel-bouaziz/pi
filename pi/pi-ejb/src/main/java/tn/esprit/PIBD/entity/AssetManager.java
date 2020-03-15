package tn.esprit.PIBD.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AssetManager implements Serializable
{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="AM_ID") 
	int id;
	@Column(name="Name") 
	String name;
	@Column(name="Login") 
	String login;
	@Column(name="Password") 
	String password;
	public AssetManager() {}
	public String getName() {return name;} 
	public void setName(String name) {this.name = name;}
	public String getLogin() {return login;} 
	public void setLogin(String login) {this.login = login;}
	public String getPassword() {return password;} 
	public void setPassword(String password) {this.password = password;}

}
