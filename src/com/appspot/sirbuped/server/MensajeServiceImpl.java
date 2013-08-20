package com.appspot.sirbuped.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.appspot.sirbuped.client.DTO.Mensaje;
import com.appspot.sirbuped.client.Interfaz.MensajeService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MensajeServiceImpl extends RemoteServiceServlet implements MensajeService, Serializable
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
		query.setOrdering("fechaRegistro desc");
		
	    List<Mensaje> mensajes = null;
	    ArrayList<Mensaje> mensajesDetached = new ArrayList<Mensaje>();;
	    
		try 
		{
			mensajes = (List<Mensaje>) query.execute();
			
			log.warning(mensajes.toString());
			
			for(Mensaje x : mensajes)
			{
				Mensaje nuevo = pm.detachCopy(x);
				mensajesDetached.add(nuevo);
			}
        }
		
		finally 
        {
			query.closeAll();
            pm.close();
        }
		
		return mensajesDetached;
	}
	
	/*public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
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
	}*/
	
	
}
