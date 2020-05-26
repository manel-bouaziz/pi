package tn.esprit.PIBD.service.interf;

import javax.ejb.Remote;
import javax.mail.PasswordAuthentication;

@Remote
public interface IEmailServiceEJB {
	//public PasswordAuthentication getPasswordAuthentication();
	public void sendAccountActivationLinkToBuyer(String destinationEmail,
	        String name,String PATH_TO_FILE);
	
	public void recevoirMail(String repertoire,boolean archive);
	 public void downloadEmailAttachments(String host, String port,String userName, String password);
}
