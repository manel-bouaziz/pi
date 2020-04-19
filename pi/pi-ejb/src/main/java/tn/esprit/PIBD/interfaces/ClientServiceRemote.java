package tn.esprit.PIBD.interfaces;

import tn.esprit.PIBD.entity.Portfolio;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Reclamation;

@Remote
public interface ClientServiceRemote {

	public int ajouterClient(Client client);

	public void supprimerClientById(int id);

	public void supprimerClient(Client client);

	public void updateClient(Client client);

	public Long getNombreClientJPQL();

	public void modifierPasswordLesParametre(String newpassword, int clientId);

	public String getinfoClientById(int clientId);

	public void modifierAcountTypeClient(String newAcountType, int clientId);

	public void modifierlogin(String newlogin, int clientId);

	public Client getClientById(int clientId);

	public void deleteAllClientJPQL();

	public String getClientPrenomById(int clientId);

	public List<String> getAllClientNamesJPQL();

	public float AfficherleGainInteretduclient(int client_Id);

	public Client getClientByLoginAndPassword(String login, String password);

	public void mettreAjourEmailByClientIdJPQL(String login, int Client_id);
}