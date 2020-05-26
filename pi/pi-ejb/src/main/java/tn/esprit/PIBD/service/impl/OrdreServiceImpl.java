package tn.esprit.PIBD.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Ordre;
import tn.esprit.PIBD.service.interf.IOrdreServiceLocal;
import tn.esprit.PIBD.service.interf.IOrdreServiceRemote;



@Stateless
public class OrdreServiceImpl implements IOrdreServiceLocal, IOrdreServiceRemote
{
	@PersistenceContext(unitName = "pi-ejb") 
	EntityManager em;
	
	@Override
	public int addOrdre(Ordre ordre) { 
		System.out.println("In addOrdre : "); 
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//Date date = new Date();
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		LocalDateTime now = LocalDateTime.now();
		ordre.setDate(now);
		//System.out.println(dtf.format(now));
		em.persist(ordre); 
		System.out.println("Out of addOrdre" + ordre.getId()); 
		return ordre.getId(); 
		}
	
	@Override 
	public void removeOrdre(int id) {
		System.out.println("In removeOrdre : ");
		em.remove(em.find(Ordre.class, id));
		System.out.println("Out of removeOrdre : ");
		}
	
	@Override 
	public void updateOrdre(Ordre ordreNewValues) { 
		System.out.println("In updateOrdre : "); 
		Ordre ordre = em.find(Ordre.class, ordreNewValues.getId());
		//Date date = new Date();
		LocalDateTime now = LocalDateTime.now();
		ordre.setDate(now);
		ordre.setChoice_company(ordreNewValues.getChoice_company()); 
		ordre.setQuantity_to_purchase(ordreNewValues.getQuantity_to_purchase());
		ordre.setQuantity_to_sale(ordreNewValues.getQuantity_to_sale());
		ordre.setLimit_amount(ordreNewValues.getLimit_amount()); 
		ordre.setRisk(ordreNewValues.getRisk()); 

		System.out.println("Out of updateOrdre : ");
		}
	
	
	@Override
	public Ordre findOrdreById(int id) {
		System.out.println("In findOrdreById : "); 
		Ordre ordre = em.find(Ordre.class, id); 
		System.out.println("Out of findOrdreById : "); 
		return ordre; 
		}
	
	@Override
	public List<Ordre> findAllOrdres() { 
	
		javax.persistence.Query q = em.createQuery("SELECT  p FROM Ordre p join  p.client q", Ordre.class);
	@SuppressWarnings("unchecked")
	List<Ordre> ordres = (List<Ordre>)q.getResultList();
     ordres.forEach(System.out::println);
     

		return ordres; 
		} 
	
	@Override
	public List<Ordre> getListOrdresByClient(Client client) {
		TypedQuery<Ordre>q = em.createQuery("SELECT distinct  p FROM Ordre p  join  p.client q where q.id=:cid", Ordre.class);
		//javax.persistence.Query query = em.createQuery("select p From Ordre p", Ordre.class);
		q.setParameter("cid",2);
		@SuppressWarnings("unchecked")
		List<Ordre> listOrdresClient = (List<Ordre>)q.getResultList();
		/*
		Query q  = em.createQuery("select o From Ordre o inner join fetch o.Client q where q.id = :cl", Ordre.class);
		
		q.setParameter("cl",client.getId());
		@SuppressWarnings("unchecked")
		List<Ordre> listOrdresClient = q.getResultList();*/
		return listOrdresClient ;
	}
	@Override
	public int addclient(Client c) { 
	
		em.persist(c); 
	
		return c.getId(); 
		}
	@Override
	public void remove(int id) {
		System.out.println("In removeOrdre : ");
		em.remove(em.find(Client.class, id));
		System.out.println("Out of removeOrdre : ");
		}
	@Override
	public void OrdreintoClient(int OrdreId,int ClientId) {
		Ordre ordre = em.find(Ordre.class, OrdreId); 
		Client client = em.find(Client.class, ClientId);
		//client.getOrdres().add(ordre);
		ordre.setClient(client);
	}
	

}
