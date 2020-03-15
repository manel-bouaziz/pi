package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity 
public class Portfolio implements Serializable
{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Portfolio_ID") 
	int id; 
	@Column(name="Type") 
	String type;
	@Column(name="Code_SB") 
	String code_SB;
	@Column(name="Quantity_SB") 
	int quantity_SB;
	@Column(name="CMB") 
	float CMB;
	@Column(name="Last_price") 
	float last_price;
	@Column(name="Current_price") 
	float current_price;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="portfolio")
	private Set<Transaction> transactions;
	
	public Portfolio() {}
	public String getType() {return type;} 
	public void setType(String type) {this.type = type;}
	public String getCode_SB() {return code_SB;} 
	public void setCode_SB(String code_SB) {this.code_SB = code_SB;}
	public int getQuantity_SB() {return quantity_SB;} 
	public void setQuantity_SB(int quantity_SB) {this.quantity_SB = quantity_SB;}
	public float getCMB() {return CMB;}
	public void setCMB(float CMB) {this.CMB = CMB;}
	public float getLast_price() {return last_price;}
	public void setLast_price(float last_price) {this.last_price = last_price;}
	public float getCurrent_price() {return current_price;}
	public void setCurrent_price(float current_price) {this.current_price = current_price;}
	
	

}
