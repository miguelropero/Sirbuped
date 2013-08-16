package com.appspot.sirbuped.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.sirbuped.client.DTO.Mensaje;
import com.appspot.sirbuped.client.Interfaz.MensajeService;

public class MensajeServiceImpl extends HttpServlet implements MensajeService
{

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Mensaje.class.getName());
	
	public void addMensaje(Mensaje mensaje)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		mensaje.setFechaRegistro(new Date());
	    
	    try
	    {
	    	pm.makePersistent(mensaje);
	    }		
	    
	    finally 
	    {
	    	pm.close();
	    }
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public ArrayList<Mensaje> consultarMensajes()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Mensaje.class);
		query.setOrdering("nombre");
		
	    List<Mensaje> mensajes = null;
	    ArrayList<Mensaje> results = null;
	    
		try 
		{
			mensajes = (List<Mensaje>) query.execute();
			log.warning(mensajes.toString());
			results = new ArrayList<Mensaje>();
			Mensaje msj = new Mensaje();
			
			for(Mensaje x : mensajes)
			{
				msj = (pm.detachCopy(x));
				results.add(msj);
			}
        }
		catch(Exception e)
		{
			log.warning(e.toString());
		}
		finally 
        {
            pm.close();
            query.closeAll();
        }
		
		return (results);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{ 
		Properties props = new Properties(); 
		Session session = Session.getDefaultInstance(props, null); 
		
		try 
		{
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			log.warning("AAAAAAAAAAAAAAAAAAAAAAAAAA" + message.toString());
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
	
}
