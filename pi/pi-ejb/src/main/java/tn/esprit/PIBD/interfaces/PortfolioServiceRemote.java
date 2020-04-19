package tn.esprit.PIBD.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.Remote;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Portfolio;

@Remote
public interface PortfolioServiceRemote {

	public int ajouterPortfolio(Portfolio portfolio);

	// Delete
	public void supprimerPortfolioById(int id);

	public void supprimerPortfolio(Portfolio portfolio);

	// Read
	public Portfolio getPortfolioById(int id);

	// Modification de la quantités d'actions
	public void modifierQuantity_SB(String NewQuantity_SB, int portfolio_ID);

	public String updatePortfolio(Portfolio portfolio);

	public Long getnbrPortfolioJPQL();

	// modifier le portefeuille
	public void ModifierPortfolio(String newType, String newCode_SB, int newQuantity_SB, float newCMB,
			float newLast_price, float newCurrent_price, int portfolioId, float newsolde, float newmontant,
			float newRendement);

	// supprimer tous les portefeuilles
	public void deleteAllportfolioIdJPQL();

	// c'est comme toString
	public String getinfoPortfolioById(int portfolioId);

	public void AffectationClientPortefeuille(int portfolioid, int clientid);

	public List<Portfolio> getPortfolioByClientId(int clientId);

	// je dois la revoir
	public List<String> getCodeOptionByClientId(int clientId);
	/// PARITES FINANCE

	public void EvolutionOption(int portfolioId);

	public void getMaxValue();

	public void getMinValue();

	public void ValueInBetween();

	public void OrdreSelonBestValue();

	public Portfolio getMeilleurOption(int clientId);

	public Portfolio getPireOption(int clientId);

	public float CaulculRendementsurligne(Portfolio p);

	public float CalculRendementPortefeuilleParclient(int clientId);

	public void ModificationSoldeCalculdeRendementDActionEtMontant(int portfolioId);

	public Double volatilitéduportefeuilleclient(int clientid);

	public String PortfeuilleOptimale(int clientid, int clientId, float taux, float taux1);

	public void EntrepriseDeHausse() throws FileNotFoundException;

	public void EntrepriseDeBaisses() throws FileNotFoundException;
}