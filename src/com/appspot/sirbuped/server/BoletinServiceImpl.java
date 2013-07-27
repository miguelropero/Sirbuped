package com.appspot.sirbuped.server;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.ListaBoletin;
import com.appspot.sirbuped.client.Interfaz.ListaBoletinService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class BoletinServiceImpl extends RemoteServiceServlet implements ListaBoletinService
{
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Desaparecido.class.getName());

	public BoletinServiceImpl()
	{	
		this.consultarEmail();	
	}

	public void enviarEmail( String email)
	{
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "SIRBUPED  te envia el Boletin de Ultimos desaparecidos";

        try 
        {
        	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sirbuped.ufps@gmail.com", "Sistema de Registro y Búsqueda de Personas Desaparecidas"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, ""));
            msg.setSubject("Boletin Mensual de Desaparecidos");
            msg.setText(msgBody);
            Transport.send(msg);
        }
        catch (AddressException e) 
        {
        	log.warning(e.toString());
        }
        catch (MessagingException e) 
        {
        	log.warning(e.toString());
        } 
        catch (UnsupportedEncodingException e) 
        {
        	log.warning(e.toString());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void  consultarEmail()
	{
		  
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		
         Query q = pm.newQuery(ListaBoletin.class);
         
         List<ListaBoletin> results = (List<ListaBoletin>) q.execute();
         
         for(ListaBoletin l:results)
         {
        	 this.enviarEmail(l.getEmail());          
         }
	}
	
	public void addEmail(ListaBoletin nuevo)
	{
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    try 
	    {
	    	pm.makePersistent(nuevo);
	    } 
	    finally 
	    {
	    	pm.close();
	    }
	}
}
