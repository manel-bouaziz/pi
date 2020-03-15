package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Ordre")
public class Ordre implements Serializable
{
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Order_ID") 
	int id;

	@Column(name="Order_Date") 
	Date date;
	
	@Column(name="Choice_company") 
	String choice_company;
	
	@Column(name="Quantity_to_purchase") 
	int quantity_to_purchase;
	
	@Column(name="Quantity_to_sale") 
	int quantity_to_sale;
	
	@Column(name="Gap") 
	float gap;
	
	@Column(name="Limit_Amount") 
	float limit_amount;
	
	@Column(name="Risk") 
	int risk;
	
	@Column(name="Comission") 
	int comission;
	
	@ManyToOne
	Client client;
	
	public Ordre() {}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getQuantity_to_purchase() {
		return quantity_to_purchase;
	}
	public void setQuantity_to_purchase(int quantity_to_purchase) {
		this.quantity_to_purchase = quantity_to_purchase;
	}
	public int getQuantity_to_sale() {
		return quantity_to_sale;
	}
	public void setQuantity_to_sale(int quantity_to_sale) {
		this.quantity_to_sale = quantity_to_sale;
	}
	public float getGap() {
		return gap;
	}
	public void setGap(float gap) {
		this.gap = gap;
	}
	public float getLimit_amount() {
		return limit_amount;
	}
	public void setLimit_amount(float limit_amount) {
		this.limit_amount = limit_amount;
	}
	public int getRisk() {
		return risk;
	}
	public void setRisk(int risk) {
		this.risk = risk;
	}
	public int getComission() {
		return comission;
	}
	public void setComission(int comission) {
		this.comission = comission;
	}
	public String getChoice_company() {
		return choice_company;
	}
	public void setChoice_company(String choice_company) {
		this.choice_company = choice_company;
	}
	

}
