package tn.esprit.PIBD.interfaces;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIBD.entity.Reclamation;

@Remote
public interface ReclamationServiceRemote {
	public List<Reclamation> getReclamationByClientIdETEtat(int clientId, String etat);

	public int ajouterReclamation(Reclamation reclamation);

	public void supprimerReclamationById(int id);

	public void supprimerReclamation(Reclamation reclamation);

	public TypedQuery<Reclamation> reccuperertouslesremarques();

	public Reclamation getReclamationById(int id);

	public void modifierReclamationClient(String newDescription, int id_Reclamation);

	public void updateReclamation(Reclamation reclamation);

	public Long getnbrReclamationJPQL();

	public String getInfoReclamationByID(int reclamationId);

	public void affectationReclamationAuclient(int reclamationId, int clientid, int portfolioid);

	public List<Reclamation> getReclamationByClientId(int clientId);

	public void deleteAllReclamationJPQL();

	public List<String> getAllReclamationDescriptionJPQL();

	public List<Reclamation> getAllReclamationsJPQL();

	public String detectiondInsulte(int idrem) throws FileNotFoundException;

	public String getDECRIPReclamationByID(int reclamationId);
}