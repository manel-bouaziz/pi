package tn.esprit.PIBD.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Remote;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Reclamation;

@Remote
public interface ReclamationServiceRemote {
	public int ajouterReclamation(Reclamation reclamation);

	public void supprimerReclamationById(int id);

	public void supprimerReclamation(Reclamation reclamation);

	public TypedQuery<Reclamation> ReccuperationDesRemarques();

	public Reclamation getReclamationById(int id);

	public void ModifierReclamation(String newDescription, int id_Reclamation);

	public void UpdateReclamation(Reclamation reclamation);

	public Long getnbrReclamationJPQL();

	public String getInfoReclamationByID(int reclamationId);

	public void DeleteAllReclamationJPQL();

	public List<String> getAllReclamationDescriptionJPQL();

	public void AffectationReclamationclient(int reclamationId, int clientid);

	public List<Reclamation> getReclamationByClientId(int clientId);

	public List<Reclamation> getAllReclamationsJPQL();

	public void affectationReclamationAuclient(int reclamationId, int clientid, int portfolioid);

	public List<Reclamation> getReclamationByClientIdETEtat(int clientId, String etat);

	public String getDECRIPReclamationByID(int reclamationId);

	public String StopWords(int idrem) throws FileNotFoundException, EJBTransactionRolledbackException;

}