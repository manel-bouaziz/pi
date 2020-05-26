package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
//import java.util.Date;

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
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Order_ID") 
	int id;

	@Column(name="Order_Date") 
	LocalDateTime date;
	
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
	@ManyToOne
	Client client;
	
	public Ordre() {}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
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
	
	public String getChoice_company() {
		return choice_company;
	}
	public void setChoice_company(String choice_company) {
		this.choice_company = choice_company;
	}

	public int getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
