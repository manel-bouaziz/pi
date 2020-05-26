package tn.esprit.PIBD.service.interf;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.PIBD.entity.Portfolio;
import tn.esprit.PIBD.entity.Transaction;
@Remote
public interface ITransactionServiceRemote {
	public int addTransaction(Transaction transaction,Portfolio p);
	public void removeTransaction(int id);
	public void updateTransaction(Transaction transactionNewValues,Portfolio p); 
	public Transaction findTransactionById(int id);
	public List<Transaction> findAllTransactions();
	//public List<Transaction> getListTransactionsByPortfolio(Portfolio portfolio);
	public int addPortfolio(Portfolio p);
	//public void TransactionintoPortfolio(int TransactionId,int PortfolioId);
	public void remove(int id);
	public Portfolio findById(int id);
	public float CommissionTTC(int transactionid);
	public float CommissionHT(int transactionid);


}
