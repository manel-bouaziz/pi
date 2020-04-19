package tn.esprit.PIBD.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Portfolio;
import tn.esprit.PIBD.entity.Reclamation;
import tn.esprit.PIBD.interfaces.ReclamationServiceRemote;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class ReclamationService implements ReclamationServiceRemote {
	@PersistenceContext(unitName = "pi-ejb")
	private EntityManager em;

	public ReclamationService() {
	}

	public int ajouterReclamation(Reclamation reclamation) {
		em.persist(reclamation);
		return reclamation.getId_reclamation();
	}

	public void supprimerReclamationById(int id) {
		Reclamation reclamation = em.find(Reclamation.class, id);
		supprimerReclamation(reclamation);
	}

	public void supprimerReclamation(Reclamation reclamation) {
		em.remove(reclamation);

	}

	public TypedQuery<Reclamation> ReccuperationDesRemarques() {
		TypedQuery<Reclamation> q1 = em.createQuery("select e from Reclamation e", Reclamation.class);
		return q1;
	}

	// Read
	public Reclamation getReclamationById(int id) {
		return em.find(Reclamation.class, id);
	}

	// Update
	public void ModifierReclamation(String newDescription, int id_Reclamation) {
		Reclamation reclamation = em.find(Reclamation.class, id_Reclamation);
		reclamation.setDescription(newDescription);

	}

	public void UpdateReclamation(Reclamation reclamation) {
		Reclamation emp = em.find(Reclamation.class, reclamation.getId_reclamation());
		emp.setDescription(reclamation.getDescription());

	}

	public Long getnbrReclamationJPQL() {
		TypedQuery<Long> query = em.createQuery("select count(c) from Reclamation c", Long.class);
		return query.getSingleResult();
	}

	public String getInfoReclamationByID(int reclamationId) {
		Reclamation reclamation = em.find(Reclamation.class, reclamationId);
		String lareclamation = "la reclamation dont l'id est" + reclamation.getId_reclamation() + "  " + "est" + "  "
				+ reclamation.getDescription();
		return lareclamation;
	}

	public void DeleteAllReclamationJPQL() {
		int modified = em.createQuery("delete from Reclamation").executeUpdate();
		if (modified > 1) {
			System.out.println("successfully deleted");
		} else {
			System.out.println("failed to delete");
		}
	}

	public List<String> getAllReclamationDescriptionJPQL() {
		List<Reclamation> reclamations = em.createQuery("select e from Reclamation e", Reclamation.class)
				.getResultList();
		List<String> ReclamationDescription = new ArrayList<>();
		for (Reclamation reclamation : reclamations) {
			ReclamationDescription.add(reclamation.getDescription());
		}

		return ReclamationDescription;
	}

	public void AffectationReclamationclient(int reclamationId, int clientid) {

		Client ClientManagedEntity = em.find(Client.class, clientid);
		Reclamation ReclamationManagedEntity = em.find(Reclamation.class, reclamationId);

		ReclamationManagedEntity.setClient(ClientManagedEntity);
		System.out.println("Affecté !!");
	}

	public List<Reclamation> getReclamationByClientId(int clientId) {

		Query q = em.createQuery("select e from Reclamation e  WHERE e.client.id = :clientId");
		q.setParameter("clientId", clientId);
		List<Reclamation> list = (List<Reclamation>) q.getResultList();
		return list;
	}

	public List<Reclamation> getAllReclamationsJPQL() {
		Query q = em.createQuery("select e from Reclamation e ");
		List<Reclamation> list = (List<Reclamation>) q.getResultList();
		return list;
	}

	@Override
	public void affectationReclamationAuclient(int reclamationId, int clientid, int portfolioid) {
		// TODO Auto-generated method stub

	}

	public List<Reclamation> getReclamationByClientIdETEtat(int clientId, String etat) {

		Query q = em.createQuery("select e from Reclamation e  WHERE e.client.id = :clientId and e.etat= :etat");
		q.setParameter("clientId", clientId);
		q.setParameter("etat", etat);

		List<Reclamation> list = (List<Reclamation>) q.getResultList();
		return list;
	}

	public String getDECRIPReclamationByID(int reclamationId) {
		Reclamation reclamation = em.find(Reclamation.class, reclamationId);

		return reclamation.getDescription();
	}

	public String StopWords(int idrem) throws FileNotFoundException, EJBTransactionRolledbackException {

		String nomdufichier = "C:\\test.txt";
		File file = new File(nomdufichier);
		String l = getDECRIPReclamationByID(idrem);
		String txt = "Reclamation claire";
		ArrayList<String> WordArrayList = new ArrayList<String>();
		Scanner in = new Scanner(file);
		while (in.hasNext()) {
			String line = in.nextLine();
			WordArrayList.add(line);
		}

		for (int i = 0; i < WordArrayList.size(); i++) {
			// System.out.println(WordArrayList.get(i));
			if (WordArrayList.get(i) != l) {
				txt = " Suppression imediate de votre reclamaiton";

			}

			supprimerReclamationById(idrem);
			break;
		}
		return txt;

	}
}
