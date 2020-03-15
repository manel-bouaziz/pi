package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity 
public class Transaction implements Serializable
{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Transaction_ID") 
	int id;
	@Column(name="Transaction_Date") 
	Date date;
	@Column(name="Code_SB") 
	String code_SB;
	@Column(name="Quantity_SB") 
	int quantity_SB;
	@Column(name="Average_price") 
	float average_price;
	
	@ManyToOne
	Portfolio portfolio;
	
	public Transaction() {}
	public Date getDate() {return date;} 
	public void setDate(Date date) {this.date = date;}
	public String getCode_SB() {return code_SB;} 
	public void setCode_SB(String code_SB) {this.code_SB = code_SB;}
	public int getQuantity_SB() {return quantity_SB;} 
	public void setQuantity_SB(int quantity_SB) {this.quantity_SB = quantity_SB;}
	public float getAverage_price() {return average_price;} 
	public void setAverage_price(float average_price) {this.average_price = average_price;}
	

}
