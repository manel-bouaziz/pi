package tn.esprit.PIBD.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.PIBD.entity.Client;
import tn.esprit.PIBD.entity.Ordre;
import tn.esprit.PIBD.entity.Portfolio;
import tn.esprit.PIBD.entity.Transaction;
import tn.esprit.PIBD.service.interf.ITransactionServiceLocal;
import tn.esprit.PIBD.service.interf.ITransactionServiceRemote;
@Stateless
public class TransactionServiceImpl implements ITransactionServiceLocal, ITransactionServiceRemote
{
	@PersistenceContext(unitName = "pi-ejb") 
	EntityManager em;
	@Override
	public int addTransaction(Transaction transaction,Portfolio p) { 
		System.out.println("In addTransaction : "); 
		LocalDateTime now = LocalDateTime.now();
		transaction.setDate(now);
		transaction.setPortfolio(p);
		em.persist(transaction); 
		System.out.println("Out of addTransaction" + transaction.getId()); 
		return transaction.getId(); 
		}

	@Override 
	public void removeTransaction(int id) {
		 Transaction t = em.find(Transaction.class, id);

		  em.remove(t);
	
		// em.createQuery("DELETE FROM Transaction c WHERE c.id = :10 ").executeUpdate();
	
		}
	@Override 
	public void updateTransaction(Transaction transactionNewValues,Portfolio p) { 
		System.out.println("In updateTransaction : "); 
		Transaction transaction = em.find(Transaction.class, transactionNewValues.getId());
		LocalDateTime now = LocalDateTime.now();
		transaction.setDate(now);
		transaction.setPortfolio(p);
		transaction.setCode_S(transactionNewValues.getCode_S()); 
		transaction.setQuantity_S(transactionNewValues.getQuantity_S());
		transaction.setType(transactionNewValues.getType());
		transaction.setCoursAchat_ou_prixVente(transactionNewValues.getCoursAchat_ou_prixVente());
		transaction.setCTB(transactionNewValues.getCTB());
		transaction.setTVA(transactionNewValues.getTVA());
		transaction.setCost_Transaction_Net(transactionNewValues.getCost_Transaction_Net());
		System.out.println("Out of updateTransaction : ");
		}
	@Override
	public Transaction findTransactionById(int id) {
		System.out.println("In findTransactionById : "); 
		Transaction transaction = em.find(Transaction.class, id); 
		System.out.println("Out of findTransactionById : "); 
		return transaction; 
		}
	
	@Override
	public List<Transaction> findAllTransactions() { 
	
    javax.persistence.Query q = em.createQuery("SELECT distinct t  FROM Transaction t join t.portfolio p", Transaction.class);
	@SuppressWarnings("unchecked")
	List<Transaction> transactions = (List<Transaction>)q.getResultList();
	transactions.forEach(System.out::println);
		return transactions; 
		} 
	
	@Override
	public int addPortfolio(Portfolio p) { 
	
		em.persist(p); 
	
		return p.getId(); 
		}
	//pour verifier suppression en cascade
	@Override 
	public void remove(int id) {
		em.remove(em.find(Portfolio.class, id));
		
		}
	@Override
	public Portfolio findById(int id) {
		Portfolio portfolio = em.find(Portfolio.class, id); 

		return portfolio; 
		}
	/*
	@Override
	public void TransactionintoPortfolio(int TransactionId,int PortfolioId) {
		Transaction transaction = em.find(Transaction.class, TransactionId); 
		Portfolio portfolio = em.find(Portfolio.class, PortfolioId);

		transaction.setPortfolio(portfolio);
	}
*/
	@Override
    public float CommissionTTC(int transactionid) {
    Transaction transaction = em.find(Transaction.class, transactionid); 
    float com_TTC= 0;
    
	if (transaction.getType().equalsIgnoreCase("purchase")) {
		// ne pas oublier cost_transaction_Achatnet = coursAchat*+quantite+CTB_TTC+Commission_TTC
		//calcul CTB 
	    float cost_CTB = (float) (transaction.getCoursAchat_ou_prixVente()*transaction.getQuantity_S()*(transaction.getCTB()/100));
	    float TVA_CTB = cost_CTB*(transaction.getTVA()/100);
	    //calcul com_ttc
		com_TTC= transaction.getCost_Transaction_Net() - (transaction.getCoursAchat_ou_prixVente()*transaction.getQuantity_S())-cost_CTB-TVA_CTB;
		return com_TTC;
	}
	else if(transaction.getType().equalsIgnoreCase("sale")) {
		// ne pas oublier cost_transaction_Ventenet = prixVente*quantite+Commission_HT
		//Lorsque vous revendrez vos actions, il n'y aura pas de taxe CTB, cette dernière n'étant due que lors de l'achat.
		float com_HT= transaction.getCost_Transaction_Net() - (transaction.getCoursAchat_ou_prixVente()*transaction.getQuantity_S());  
		float TVA_com = com_HT*(transaction.getTVA()/100);
		com_TTC = com_HT + TVA_com;
		return com_TTC;
	}
	return com_TTC;
    	
            }
	
	@Override
	public float CommissionHT(int transactionid) {
		  Transaction transaction = em.find(Transaction.class, transactionid); 
		float com_HT= CommissionTTC(transactionid)-CommissionTTC(transactionid)*(transaction.getTVA()/100);
		return com_HT;
	}
    
    
    
}
