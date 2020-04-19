package tn.esprit.PIBD.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Portfolio;
import tn.esprit.PIBD.interfaces.PortfolioServiceRemote;

@Stateless
@LocalBean
public class PortfolioService implements PortfolioServiceRemote {

	@PersistenceContext(unitName = "pi-ejb")
	public EntityManager em;

	public PortfolioService() {
	}

	// Create
	public int ajouterPortfolio(Portfolio portfolio) {

		em.persist(portfolio);
		return portfolio.getId();

	}

	// Delete
	public void supprimerPortfolioById(int id) {
		Portfolio portfolio = em.find(Portfolio.class, id);
		supprimerPortfolio(portfolio);
	}

	public void supprimerPortfolio(Portfolio portfolio) {
		em.remove(portfolio);
	}

	// Read
	public Portfolio getPortfolioById(int id) {
		return em.find(Portfolio.class, id);
	}

	// Modification de la quantités d'actions
	public void modifierQuantity_SB(String NewQuantity_SB, int portfolio_ID) {
		Portfolio portfolio = em.find(Portfolio.class, portfolio_ID);
		portfolio.setType(NewQuantity_SB);
	}

	public String updatePortfolio(Portfolio portfolio) {
		Portfolio emp = em.find(Portfolio.class, portfolio.getId());
		emp.setCMB(portfolio.getCMB());
		emp.setCode_SB(portfolio.getCode_SB());
		emp.setCurrent_price(portfolio.getCurrent_price());
		emp.setLast_price(portfolio.getLast_price());
		emp.setQuantity_SB(portfolio.getQuantity_SB());
		emp.setType(portfolio.getType());
		emp.setSolde(portfolio.getSolde());
		emp.setMontant(portfolio.getMontant());
		emp.setDate_achat(portfolio.getDate_achat());
		emp.setMontant(portfolio.getMontant());
		emp.setSolde(portfolio.getSolde());
		emp.setRendement(portfolio.getRendement());
		return "update";
	}

	public Long getnbrPortfolioJPQL() {
		TypedQuery<Long> query = em.createQuery("select count(p) from Portfolio p", Long.class);
		return query.getSingleResult();

	}

	// modifier le portefeuille
	public void ModifierPortfolio(String newType, String newCode_SB, int newQuantity_SB, float newCMB,
			float newLast_price, float newCurrent_price, int portfolioId, float newsolde, float newmontant,
			float newRendement) {
		Portfolio portfolio = em.find(Portfolio.class, portfolioId);
		portfolio.setCode_SB(newCode_SB);
		portfolio.setQuantity_SB(newQuantity_SB);
		portfolio.setCMB(newCMB);
		portfolio.setLast_price(newLast_price);
		portfolio.setCurrent_price(newCurrent_price);
		portfolio.setSolde(newsolde);
		portfolio.setMontant(newmontant);
		portfolio.setRendement(newRendement);
	}

	@Override
	// supprimer tous les portefeuilles
	public void deleteAllportfolioIdJPQL() {
		// TODO Auto-generated method stub
		int modified = em.createQuery("delete from Portfolio").executeUpdate();
		if (modified > 1) {
			System.out.println("successfully deleted");
		} else {
			System.out.println("failed to delete");
		}
	}

	// c'est comme toString
	public String getinfoPortfolioById(int portfolioId) {
		Portfolio portfolio = em.find(Portfolio.class, portfolioId);
		String potefeuille = "le portfolioId dont l'id est" + portfolio.getId() + "  " + "est" + "  " + "  "
				+ "dont le numero de compte est" + "   " + portfolio.getCMB() + "   " + portfolio.getCode_SB() + "   "
				+ portfolio.getCurrent_price() + "   " + portfolio.getLast_price() + "   " + portfolio.getQuantity_SB()
				+ "   " + portfolio.getType();
		return potefeuille;
	}

	public void AffectationClientPortefeuille(int portfolioid, int clientid) {

		Client ClientManagedEntity = em.find(Client.class, clientid);
		Portfolio PortfolioManagedEntity = em.find(Portfolio.class, portfolioid);
		PortfolioManagedEntity.setClient(ClientManagedEntity);
		System.out.println("Affecté !!");
	}

	public List<Portfolio> getPortfolioByClientId(int clientId) {
		Query q = em.createQuery("select e from Portfolio e  WHERE e.client.id = :clientId  ORDER BY e.solde DESC");
		q.setParameter("clientId", clientId);
		List<Portfolio> list = (List<Portfolio>) q.getResultList();
		return list;

	}

	// je dois la revoir
	public List<String> getCodeOptionByClientId(int clientId) {
		Client clientManagedEntity = em.find(Client.class, clientId);
		List<String> code = new ArrayList<>();
		for (Portfolio dep : clientManagedEntity.getPortfolio()) {
			code.add(dep.getCode_SB());

		}

		return code;
	}
	/// PARITES FINANCE et métiers.

	public void EvolutionOption(int portfolioId) {
		Portfolio portfolio = em.find(Portfolio.class, portfolioId);

		if (portfolio.getSolde() > 0) {
			System.out.println("Bonne Affaires !! ");
		} else {
			System.out.println("En baisse ");
		}

	}
	// pvalue est plus bas

	public void getMaxValue() {

		Query query1 = em.createQuery("Select MAX(e.solde) from Portfolio e");
		float result = (float) query1.getSingleResult();
		System.out.println("Max solde  :" + result);
		// Portfolio portfolio = em.find(Portfolio.class, result);

	}
	// pvalue est plus bas

	public void getMinValue() {
		Query query1 = em.createQuery("Select MIN(e.solde) from Portfolio e");
		float result = (float) query1.getSingleResult();
		System.out.println("Max solde  :" + result);

	}

	public void ValueInBetween() {
		Query query = em.createQuery("Select e " + "from Portfolio e " + "where e.solde " + "Between 190 and 200");

		List<Portfolio> list = (List<Portfolio>) query.getResultList();

		for (Portfolio e : list) {
			System.out.print("Portfolio ID :" + e.getId());
			System.out.println("\t Portfolio Solde :" + e.getSolde());
		}
	}

	public void OrdreSelonBestValue() {
		Query query = em.createQuery("Select e " + "from Portfolio e " + "ORDER BY e.solde ASC");
		List<Portfolio> list = (List<Portfolio>) query.getResultList();

		for (Portfolio e : list) {
			System.out.print("Portfolio ID :" + e.getId());
			System.out.println("\t Portfolio Solde :" + e.getCode_SB());
		}
	}
	// l'option dans le pvalue est plus haut

	public Portfolio getMeilleurOption(int clientId) {
		List<Portfolio> liste = getPortfolioByClientId(clientId);
		return getPortfolioByClientId(clientId).get(0);
	}

//l'option dans le pvalue est plus bas
	public Portfolio getPireOption(int clientId) {
		List<Portfolio> liste = getPortfolioByClientId(clientId);
		return liste.get(liste.size() - 1);
		// methode simple
		/*
		 * client clientManagedEntity = em.find(Client.class, clientId); float A = 0;
		 * int id = 1; for (Portfolio dep : clientManagedEntity.getPortfolio()) {
		 * System.out.println("hello"); if (A > dep.getSolde()) A = dep.getSolde(); if
		 * (A == dep.getSolde()) { id = dep.getId(); Portfolio B =
		 * em.find(Portfolio.class, id);
		 * System.out.println("le protfolio qui apartient au client d'id " + " +" +
		 * clientId + "   est celui dont le foloio id est " + id + "qui esr" + B); }
		 * System.out.println("fin de la rechercher"); }
		 */

	}

	public float CaulculRendementsurligne(Portfolio p) {
		float rendementligne = p.getRendement() * p.getQuantity_SB();
		return rendementligne;

	}

// le rendement du portefeuille se fait le rendement de chaque actions * sa quantité sur la quantité globale 
	public float CalculRendementPortefeuilleParclient(int clientId) {

		List<Portfolio> liste = getPortfolioByClientId(clientId);
		float somme = 0;
		int q = 0;
		for (Portfolio p : liste) {
			somme += CaulculRendementsurligne(p);
			q += p.getQuantity_SB();

		}

		return somme / q;

	}

// le solde est le pvalue que le client a soit gagner soit perdu en achetant cette actions.
	public void ModificationSoldeCalculdeRendementDActionEtMontant(int portfolioId) {
		Portfolio portfolio = em.find(Portfolio.class, portfolioId);
		float A = (portfolio.getCurrent_price() - portfolio.getCMB()) * (portfolio.getQuantity_SB());
		portfolio.setSolde(A);
		float B = (portfolio.getCMB()) * (portfolio.getQuantity_SB());
		portfolio.setMontant(B);
		float C = (portfolio.getCurrent_price() - portfolio.getLast_price() + portfolio.getDividende())
				/ (portfolio.getLast_price());
		portfolio.setRendement(C);

		if (portfolio.getRendement() != 0)
			portfolio.setRendement(C);

		if (portfolio.getRendement() == C)
			System.out.println("Modification dU Rendement ");

	}

	public Double volatilitéduportefeuilleclient(int clientid) {

		Query q = em.createQuery("select AVG(volatilité) from Portfolio   WHERE client.id = :clientid");
		q.setParameter("clientid", clientid);
		return (Double) q.getSingleResult();
	}

// on distingue le protefeuille optimal entre deux clients par la comparaison de leur ratio de sharpe avec taux sans rique donnée rendement deja calculer et  volatilité 
	@Override
	public String PortfeuilleOptimale(int clientid, int clientId, float taux, float taux1) {
		// TODO Auto-generated method stub
		float a = (float) ((CalculRendementPortefeuilleParclient(clientid) - taux)
				/ volatilitéduportefeuilleclient(clientid));
		float b = (float) ((CalculRendementPortefeuilleParclient(clientId) - taux1)
				/ volatilitéduportefeuilleclient(clientId));
		if (a < b) {
			return "Selon le calcul du ration de sharpe le portefeuille optimale est celui du client dont l'id est"
					+ "  " + clientId;
		}
		return "Selon le calcul du ration de sharpe le client client dont l'id est" + "  " + clientid;
	} // A l'aide d'un fichier excel le client peut consulter dans le portefeuille les
		// 5 Entreprises qui occupent le 1er classement sur l'echelle national

	public void EntrepriseDeHausse() throws FileNotFoundException {
		String Path = "C:\\Hausse.csv";
		File file = new File(Path);

		ArrayList<String> HausseArrayList = new ArrayList<String>();
		ArrayList<String> List = new ArrayList<String>();
		String name = "";
		Scanner in = new Scanner(file);
		System.out.println(LocalDateTime.now());
		while (in.hasNext()) {
			String line = in.nextLine();
			HausseArrayList.add(line);
			for (int i = 0; i < HausseArrayList.size(); i++) {
				name = HausseArrayList.get(i);

			}

			String mots[] = name.split(";");
			System.out.println(mots[0] + " " + "est une entreprise qui a marquer une hausse avec" + " " + mots[1]
					+ "est une variance de " + " " + mots[2]);

		}

	}

	// A l'aide d'un fichier excel le client peut consulter dans le portefeuille les
	// 5 Entreprises qui occupent le dernierer classement sur l'echelle national
	public void EntrepriseDeBaisses() throws FileNotFoundException {
		String Path = "C:\\Baisse.csv";
		File file = new File(Path);

		ArrayList<String> HausseArrayList = new ArrayList<String>();
		ArrayList<String> List = new ArrayList<String>();
		String name = "";
		Scanner in = new Scanner(file);
		System.out.println(LocalDateTime.now());
		while (in.hasNext()) {
			String line = in.nextLine();
			HausseArrayList.add(line);
			for (int i = 0; i < HausseArrayList.size(); i++) {
				name = HausseArrayList.get(i);

			}

			String mots[] = name.split(";");
			System.out.println(mots[0] + " " + "est une entreprise qui a marquer une Baisse avec" + " " + mots[1]
					+ "est une variance de " + " " + mots[2]);

		}

	}

}
