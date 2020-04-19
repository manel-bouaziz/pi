package tn.esprit.PIBD.interfaces;
import java.io.FileNotFoundException;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Portfolio;
@Remote
public interface PortfolioServiceRemote {
	
	public int ajouterPortfolio(Portfolio portfolio);
	public void supprimerPortfolioById(int id);
	public void supprimerPortfolio(Portfolio portfolio);
	public Portfolio getPortfolioById(int id);
	public void modifierQuantity_SB( String NewQuantity_SB, int portfolio_ID);
	public void modifierType( String NewType, int portfolio_ID);
	public void deleteAllportfolioIdJPQL();
	public Long getnbrPortfolioJPQL();
	public String getinfoPortfolioById(int portfolioId);
	public void getplusbasOptionPrix();
	public void getplusHautOptionPrix();
	public void ModificationSoldeCalculdeRendementDActionEtMontant(int portfolioId);
	
	public void evolutiondeloption(int portfolioId);
	public void affectation(int portfolioid , int clientid);
	public List<String> getCodeOptionByClientId(int clientId);
	public void PORTFOLIODANSSOLDENTRE();
	public void LordredesmeilleuresAchatn();
	public  List<Portfolio> getPortfolioByClientId(int clientId);
	public float CalculRendementPortefeuilleParclient(int clientId);
	public Portfolio getMeilleurOption(int clientId);
	public Portfolio getPireOption(int clientId);
	public List<Portfolio> getAllPortfolioByClient(Client client);
	public float CaulculRendementsurligne(Portfolio p);
	 public String portfeuilleOptimale(int clientid,int clientId, float taux,float taux1);
	 public Double volatilitéduportefeuilleclient(int clientid);
public void entreprisedeHausse() throws FileNotFoundException;}