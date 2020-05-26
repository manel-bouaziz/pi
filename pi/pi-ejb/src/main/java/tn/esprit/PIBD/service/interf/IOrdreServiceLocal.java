package tn.esprit.PIBD.service.interf;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Ordre;

@Local
public interface IOrdreServiceLocal {
	public int addOrdre(Ordre ordre);
	public void removeOrdre(int id);
	public void updateOrdre(Ordre ordre); 
	public Ordre findOrdreById(int id);
	public List<Ordre> findAllOrdres();
	public List<Ordre> getListOrdresByClient(Client client);
	public int addclient(Client c);
	public void OrdreintoClient(int OrdreId,int ClientId);

}
