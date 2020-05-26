package tn.esprit.PIBD.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.jboss.logging.Logger;

import com.sun.mail.pop3.POP3Store;

import java.io.InputStream;
import java.io.InputStreamReader;

import tn.esprit.PIBD.service.interf.IEmailServiceEJB;
@Resource(name = "mail/myMailSession")
@Stateless(name = "ejbs/EmailServiceEJB")
public class EmailServiceEJB extends Authenticator implements IEmailServiceEJB {
	/*@Override
	public PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication("ejbtestmail@gmail.com",
	            "ejbtestmail123");
	}*/
	@Override
	public void sendAccountActivationLinkToBuyer(String destinationEmail,
	        String name,String PATH_TO_FILE) {
	    // OUR EMAIL SETTINGS
	    String host = "smtp.gmail.com";// Gmail
	    int port = 465;
	    String serviceUsername = "ejbtestmail@gmail.com";
	    String servicePassword = "ejbtestmail123";// Our Gmail password

	    Properties props = new Properties();
	    props.put("mail.smtp.user", serviceUsername);
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.debug", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.socketFactory.port", port);
	   props.put("mail.smtp.socketFactory.class",
	            "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");

	    // Destination of the email
	    String to = destinationEmail;
	    String from = "ejbtestmail@gmail.com";

	    // Creating a javax.Session with the our properties
	  
	    Session session = Session.getInstance(props,new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ejbtestmail@gmail.com",
        	            "ejbtestmail123");
            }
        });

	    try {
	        Message message = new MimeMessage(session);
	        // From: is our service
	        message.setFrom(new InternetAddress(from));
	        // To: destination given
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(to));
	        message.setSubject("Comfirm your account");
	        // Instead of simple text, a .html template should be added here!
	        message.setText("Welcome....... ");
	        
	       /*
	       // File file = new File(PATH_TO_FILE);
	        FileDataSource datasource1 = new FileDataSource(PATH_TO_FILE);
	        DataHandler handler1 = new DataHandler(datasource1);
	        MimeBodyPart pdf = new MimeBodyPart();
	       
	            pdf.setDataHandler(handler1);
	            pdf.setFileName(datasource1.getName());
	     
	        MimeMultipart mimeMultipart = new MimeMultipart();
	   
	      
	            mimeMultipart.addBodyPart(pdf);*/
	        
	        Multipart multipart = new MimeMultipart();
	       

	        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	        attachmentBodyPart.attachFile(new File("D:\\"+"/"+PATH_TO_FILE), "application/pdf", null);
	        multipart.addBodyPart(attachmentBodyPart);
	        MimeBodyPart attachmentBodyPart2 = new MimeBodyPart();
	        attachmentBodyPart2.setText("maaanouuulaaaaa");
	        multipart.addBodyPart(attachmentBodyPart2);
	   
	            message.setContent(multipart);
	   

	        Transport transport = session.getTransport("smtps");
	        transport.connect(host, port, serviceUsername, servicePassword);
	        Transport.send(message, message.getAllRecipients());
	        System.out.println("Done");
	        transport.close();

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
	 private boolean textIsHtml = false;

	    /**
	     * Return the primary text content of the message.
	     */
	    private String getText(Part p) throws MessagingException, IOException {
	        if (p.isMimeType("text/*")) {
	            String s = (String)p.getContent();
	            textIsHtml = p.isMimeType("text/html");
	            return s;
	        }

	        if (p.isMimeType("multipart/alternative")) {
	            // prefer html text over plain text
	            Multipart mp = (Multipart)p.getContent();
	            String text = null;
	            for (int i = 0; i < mp.getCount(); i++) {
	                Part bp = mp.getBodyPart(i);
	                if (bp.isMimeType("text")) {
	                    if (text == null)
	                        text = getText(bp);
	                    continue;
	                } else if (bp.isMimeType("text/html")) {
	                    String s = getText(bp);
	                    if (s != null)
	                        return s;
	                } else {
	                    return getText(bp);
	                }
	            }
	            return text;
	        } else if (p.isMimeType("multipart/*")) {
	            Multipart mp = (Multipart)p.getContent();
	            for (int i = 0; i < mp.getCount(); i++) {
	                String s = getText(mp.getBodyPart(i));
	                if (s != null)
	                    return s;
	            }
	        }

	        return null;
	    }
	    
	    
	@Override
	public void recevoirMail(String repertoire,boolean archive){
   		try{
                    Properties prop = System.getProperties();
                    Auth aut=new Auth();
                    prop.put("mail.pop3.host","pop.gmail.com");
                    prop.put("mail.pop3.auth","true");
 
                    prop.put("mail.pop3.user", "ejbtestmail@gmail.com");
                    prop.put("mail.pop3.port", 995);
                    prop.put("mail.pop3.starttls.enable","true");
                    prop.put("mail.pop3.socketFactory.port", 995);
                    prop.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    prop.put("mail.pop3.socketFactory.fallback", "false");
                    prop.setProperty("mail.imap.partialfetch", "false");
 
 
                    Session session = Session.getInstance(prop,aut);
 
                    // Récupère la "messagerie" et se connecte
                    Store store = session.getStore("pop3");
 
                    session.setDebug(true);
                    store.connect("pop.gmail.com", "ejbtestmail@gmail.com","ejbtestmail123");
                    //store.connect();
 
                    // Récupère le fichier "Boite de réception" et l'ouvre
                    Folder folder = store.getFolder("INBOX");
                    System.out.println("lecture des messages");
                    folder.open(Folder.READ_ONLY);
 
                    // Check les messages du dossier
                    Message message[] = folder.getMessages();
                    System.out.println("taille de messages :");
                    System.out.println(message.length);
                    
                 // search for all "unseen" messages
                    Flags seen = new Flags(Flags.Flag.SEEN);
                    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
                    Message messages[] = folder.search(unseenFlagTerm);


                    if (messages.length == 0) System.out.println("No messages found.");
                    
                    List<File> attachments = new ArrayList<File>();
                    for (Message unMessage : message) {
                         
                         System.out.println("---------------------------------");  
                         
                         System.out.println("Subject: " + unMessage.getSubject());  
                         System.out.println("From: " + unMessage.getFrom()[0]);  
                        System.out.println("Text: " + getText(unMessage).toString());
                       
                     // lecture de la pièce jointe
                        Multipart mp = (Multipart) unMessage.getContent();
         
                        for (int a = 0; a <  mp.getCount(); a++) {
         
                            BodyPart bodyPart = mp.getBodyPart(a);
                            String disposition = bodyPart.getDisposition();
         
                            if ((disposition != null) && ((disposition.equalsIgnoreCase(Part.ATTACHMENT) || (disposition.equals(Part.INLINE))))) {
                                File file = new File(repertoire + bodyPart.getFileName());
                                // on enregistre le fichier dans le repertoire désiré
                                FileWriter ecriture=new FileWriter(file);
                                InputStreamReader lecture=new InputStreamReader(bodyPart.getInputStream());
                                int flux;
                                while ((flux=lecture.read())!=-1) {
                                    ecriture.write(flux);
                                }
                                lecture.close();
                                ecriture.close();
         /*
                                // s'il s'agit d'une archive, on l'extrait dans le repertoire source
                                if (archive) {
                                	file.unzip(file, repertoire);
                                }*/
                            }
                         
                    }
               
                    
		}
   		}
		catch (Exception e) {
                  e.printStackTrace();
         }
 
   }
	
 
 private class Auth extends Authenticator{
   	protected  PasswordAuthentication getPasswordAuthentication(){
   		return new PasswordAuthentication("ejbtestmail@gmail.com","ejbtestmail123");
   	}
   }
 
 //*********************************************************************************************************//
 
 /** log4j Logger */
	private static Logger log = Logger.getLogger(EmailServiceEJB.class);
	
	private static String saveDirectory = "C:\\Users/ASUS/Desktop/repertoire"; // directory to save the downloaded documents

	/**
	 * Sets the directory where attached files will be stored.
	 * @param dir absolute path of the directory
	 */
	public void setSaveDirectory(String dir) {
		EmailServiceEJB.saveDirectory = dir;
	}

	/**
	 * Downloads new messages and saves attachments to disk if any.
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 */
	@Override
	public  void downloadEmailAttachments(String host, String port, String userName, String password) {
		Properties properties = new Properties();

		// server setting
		properties.put("mail.pop3.host", host);
		properties.put("mail.pop3.port", port);

		// SSL setting
		properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.pop3.socketFactory.fallback", "false");
		properties.setProperty("mail.pop3.socketFactory.port", String.valueOf(port));

		Session session = Session.getDefaultInstance(properties);

		try {
			// connects to the message store
			Store store = session.getStore("pop3");
			store.connect(userName, password);

			// opens the inbox folder
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			// fetches new messages from server
			Message[] arrayMessages = folderInbox.getMessages();

			for (int i = 0; i < arrayMessages.length; i++) {
				Message message = arrayMessages[i];
				Address[] fromAddress = message.getFrom();
				String from = fromAddress[0].toString();
				String subject = message.getSubject();
				String sentDate = message.getSentDate().toString();

				String contentType = message.getContentType();
				String messageContent = "";

				// store attachment file name, separated by comma
				String attachFiles = "";

				if (contentType.contains("multipart")) {
					// content may contain attachments
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(saveDirectory + File.separator + fileName);
						} else {
							// this part may be the message content
							messageContent = part.getContent().toString();
						}
					}

					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					Object content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}

			//print out details of each message
				 System.out.println("Message #" + (i + 1) + ":");
	                System.out.println("\t From: " + from);
	                System.out.println("\t Subject: " + subject);
	                System.out.println("\t Sent Date: " + sentDate);
	                System.out.println("\t Message: " + messageContent);
	                System.out.println("\t Attachments: " + attachFiles);
			}

			// disconnect
			folderInbox.close(false);
			store.close();
		} catch (NoSuchProviderException ex) {
			System.out.println("No provider for pop3.");
			ex.printStackTrace();
			log.error(ex);
		} catch (MessagingException ex) {
			System.out.println("Could not connect to the message store");
			ex.printStackTrace();
			log.error(ex);
		} catch (IOException ex) {
			ex.printStackTrace();
			log.error(ex);
		}
	}
 
 
 
 
}
