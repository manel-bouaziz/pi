package tn.esprit.PIBD.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Portfolio;
import tn.esprit.PIBD.entity.Reclamation;
import tn.esprit.PIBD.interfaces.ClientServiceRemote;

@Stateless
@LocalBean
public class ClientService implements ClientServiceRemote {

	@PersistenceContext(unitName = "pi-ejb")
	private EntityManager em;

	public ClientService() {
	}

	// Create
	public int ajouterClient(Client client) {
		em.persist(client);
		return client.getId();
	}

	// Delete
	public void supprimerClientById(int id) {
		Client client = em.find(Client.class, id);
		supprimerClient(client);
	}

	public void supprimerClient(Client client) {
		em.remove(client);
	}

	@Override
	public void updateClient(Client client) {
		Client emp = em.find(Client.class, client.getId());
		emp.setAccount_number(client.getAccount_number());
		emp.setAccount_type(client.getAccount_type());
		emp.setLogin(client.getLogin());
		emp.setName(client.getName());
		emp.setPassword(client.getPassword());

	}

	@Override
	public Long getNombreClientJPQL() {
		TypedQuery<Long> query = em.createQuery("select count(c) from Client c", Long.class);
		return query.getSingleResult();
	} // tekhdem

	// Update passwordclient
	public void modifierPasswordLesParametre(String newpassword, int clientId) {

		Client client = em.find(Client.class, clientId);
		client.setPassword(newpassword);

	}// tekhdem

	// UpdateLetype de conpte
	public void modifierAcountTypeClient(String newAcountType, int clientId) {
		Client client = em.find(Client.class, clientId);
		client.setAccount_type(newAcountType);
//tekhdem
	}

	// UpdateLetype de Login
	public void modifierlogin(String newlogin, int clientId) {
		Client client = em.find(Client.class, clientId);
		client.setLogin(newlogin);

	}

	@Override
	public String getinfoClientById(int clientId) {
		Client client = em.find(Client.class, clientId);
		String leclient = "le client dont l'id est" + client.getId() + "  " + "est" + "  " + client.getName() + "  "
				+ "dont le numero de compte est" + "   " + client.getAccount_number() + "   " + "de type " + " "
				+ client.getAccount_type();
		return leclient;
	}

	@Override
	public Client getClientById(int clientId) {
		return em.find(Client.class, clientId);
	}

	public void deleteAllClientJPQL() {
		int modified = em.createQuery("delete from Client").executeUpdate();
		if (modified > 1) {
			System.out.println("successfully deleted");
		} else {
			System.out.println("failed to delete");
		}
	}

	public String getClientPrenomById(int clientId) {
		Client client = em.find(Client.class, clientId);
		return client.getName();
	}

	public List<String> getAllClientNamesJPQL() {
		List<Client> clients = em.createQuery("select e from Client e", Client.class).getResultList();
		List<String> clientNames = new ArrayList<>();
		for (Client client : clients) {
			clientNames.add(client.getName());
		}

		return clientNames;
	}

	public float AfficherleGainInteretduclient(int client_Id) {
		Client client = em.find(Client.class, client_Id);
		float interet = (1 + (client.getInteret() / 100)) * client.getInvest();
		System.out.println("le montant annuelle de votre investissement est devenu" + "  " + interet);
		return interet;
	}

	public Client getClientByLoginAndPassword(String login, String password) {
		TypedQuery<Client> query = em
				.createQuery("select e from Client e WHERE e.login=:login and e.password=:password ", Client.class);
		query.setParameter("Login", login);
		query.setParameter("password", password);
		Client client = null;
		try {
			client = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		return client;
	}

	public void mettreAjourEmailByClientIdJPQL(String login, int Client_id) {
		Query query = em.createQuery("update Client e set e.login=:login where e.id=:Client_id");
		query.setParameter("login", login);
		query.setParameter("Client_id", Client_id);
		int modified = query.executeUpdate();
		if (modified == 1) {
			System.out.println("successfully updated");
		} else {
			System.out.println("failed to update");
		}
	}
}
