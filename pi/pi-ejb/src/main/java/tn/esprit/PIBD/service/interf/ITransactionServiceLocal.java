package tn.esprit.PIBD.service.interf;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.PIBD.entity.Portfolio;
import tn.esprit.PIBD.entity.Transaction;



@Local
public interface ITransactionServiceLocal {
	public int addTransaction(Transaction transaction,Portfolio p);
	public void removeTransaction(int id);
	public void updateTransaction(Transaction transactionNewValues,Portfolio p); 
	public Transaction findTransactionById(int id);
	public List<Transaction> findAllTransactions();
	//public List<Transaction> getListTransactionsByPortfolio(Portfolio portfolio);
	public int addPortfolio(Portfolio p);
	//public void TransactionintoPortfolio(int TransactionId,int PortfolioId);
	public Portfolio findById(int id);
	public float CommissionTTC(int transactionid);
	public float CommissionHT(int transactionid);

}
