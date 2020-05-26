package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
	private static final long serialVersionUID = -558553967080513790L;
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="Transaction_ID") 
	int id;
	@Column(name="Transaction_Date") 
	LocalDateTime date;
	@Column(name="Code_S") 
	String code_S;
	@Column(name="Type") 
	String type;
	@Column(name="Quantity_S") 
	int quantity_S;
	@Column(name="CoursAchat_Ou_PrixVente") 
	float coursAchat_ou_prixVente;
	@Column(name="CTB") 
	Double CTB;
	@Column(name="TVA") 
	float TVA;
	@Column(name="Cost_Transaction_Net") 
	float cost_Transaction_Net;

	@ManyToOne
	Portfolio portfolio;
	
	public Transaction() {}
	public LocalDateTime getDate() {return date;} 
	public void setDate(LocalDateTime date) {this.date = date;}
	public String getCode_S() {return code_S;} 
	public void setCode_S(String code_S) {this.code_S = code_S;}
	public int getQuantity_S() {return quantity_S;} 
	public void setQuantity_S(int quantity_S) {this.quantity_S = quantity_S;}
	

	public float getCoursAchat_ou_prixVente() {
		return coursAchat_ou_prixVente;
	}
	public void setCoursAchat_ou_prixVente(float coursAchat_ou_prixVente) {
		this.coursAchat_ou_prixVente = coursAchat_ou_prixVente;
	}
	public double getCTB() {
		return CTB;
	}
	public void setCTB(double cTB) {
		CTB = cTB;
	}
	public float getTVA() {
		return TVA;
	}
	public void setTVA(float tVA) {
		TVA = tVA;
	}
	public float getCost_Transaction_Net() {
		return cost_Transaction_Net;
	}
	public void setCost_Transaction_Net(float cost_Transaction_Net) {
		this.cost_Transaction_Net = cost_Transaction_Net;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Portfolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
