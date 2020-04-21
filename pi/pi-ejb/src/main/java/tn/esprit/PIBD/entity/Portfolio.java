package tn.esprit.PIBD.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.text.DateFormat;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "Portfolio")
@Entity
public class Portfolio implements Serializable {
	public Portfolio(int id, String type, String code_SB, int quantity_SB, float cMB, float last_price,
			float current_price, float montant, float solde, LocalDateTime date_achat, float rendement, float dividende,
			float volatilité, Client client) {
		super();
		this.id = id;
		this.type = type;
		this.code_SB = code_SB;
		this.quantity_SB = quantity_SB;
		CMB = cMB;
		this.last_price = last_price;
		this.current_price = current_price;
		this.montant = montant;
		this.solde = solde;
		this.date_achat = date_achat;
		this.rendement = rendement;
		this.dividende = dividende;
		this.volatilité = volatilité;
		this.client = client;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Option_Id")
	int id;
	@Column(name = "Type")
	String type;
	@Column(name = "CMP")
	String code_SB;
	@Column(name = "Quantity_SB")
	int quantity_SB;
	@Column(name = "CMB")
	float CMB;
	@Column(name = "Last_price")
	float last_price;
	@Column(name = "Current_price")
	float current_price;
	@Column(name = "Montant")
	float montant;
	@Column(name = "Value")
	float solde;
	@Column(name = "Date_achat")
	LocalDateTime date_achat;
	@Column(name = "Rendement")
	float rendement;
	@Column(name = "Dividende")
	float dividende;
	@Column(name = "Volatilité")
	float volatilité;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio")
	private Set<Transaction> transactions;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Client_Id")
	Client client;

	public float getVolatilité() {
		return volatilité;
	}

	public void setVolatilité(float volatilité) {
		this.volatilité = volatilité;
	}

	public float getDividende() {
		return dividende;
	}

	public void setDividende(float dividende) {
		this.dividende = dividende;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public LocalDateTime getDate_achat() {
		return date_achat;
	}

	public void setDate_achat(LocalDateTime localDateTime) {
		this.date_achat = localDateTime;
	}

	public Portfolio() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode_SB() {
		return code_SB;
	}

	public void setCode_SB(String code_SB) {
		this.code_SB = code_SB;
	}

	public int getQuantity_SB() {
		return quantity_SB;
	}

	public void setQuantity_SB(int quantity_SB) {
		this.quantity_SB = quantity_SB;
	}

	public float getRendement() {
		return rendement;
	}

	public void setRendement(float rendement) {
		this.rendement = rendement;
	}

	public float getCMB() {
		return CMB;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setCMB(float CMB) {
		this.CMB = CMB;
	}

	public float getLast_price() {
		return last_price;
	}

	public void setLast_price(float last_price) {
		this.last_price = last_price;
	}

	public float getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(float current_price) {
		this.current_price = current_price;
	}

	public float getSolde() {
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	@Override
	public String toString() {
		return "Portfolio [id=" + id + ", type=" + type + ", code_SB=" + code_SB + ", quantity_SB=" + quantity_SB
				+ ", CMB=" + CMB + ", last_price=" + last_price + ", current_price=" + current_price + ", montant="
				+ montant + ", solde=" + solde + ", date_achat=" + date_achat + ", rendement=" + rendement
				+ ", dividende=" + dividende + ", volatilité=" + volatilité + "]";
	}

}
