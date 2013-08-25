package com.appspot.sirbuped.server;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
	
	public void actualizarEstado(String id)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try
		{
            Mensaje original = pm.getObjectById(Mensaje.class, id);
			original.setEstado(false);
		}
		
		finally
		{
			pm.close();
		}
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
	
	public void responderMensaje(String email, String mensaje)
	{
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try 
        {
        	String htmlBody = this.bodyMensaje(mensaje);
        	
        	Multipart mp = new MimeMultipart();
        	
        	MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(htmlBody, "text/html");
	        mp.addBodyPart(htmlPart);
        	
        	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sirbuped.ufps@gmail.com", "Sirbuped"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, ""));
            msg.setSubject("Respuesta de SIRBUPED a su Mensaje");
            msg.setContent(mp);
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
	
	public String bodyMensaje(String mensaje)
	{
		String head = "<body style='background: #F5F5F5; font-family: Helvetica; padding: 15px 0'>"+
		"<table style='width:600px; background: #FFF; border: 10px solid #FFF; margin: 0 auto'>"+
			"<tr>"+
				"<td>"+
					"<table style='width: 100%; margin: 0 auto; background: #FFF; padding: 15px 0; border-bottom: 8px solid #00BEBC'>"+
						"<tr>"+
							"<td Style='width: 250px;'><img src='http://sirbuped.appspot.com/image/logo.jpg' /></td>"+
							"<td align='left'><span style='font-size:18px; color:#606060'>Sistema de registro y b\u00FAsqueda de personas desaparecidas</span></td>"+
						"</tr>"+
					"</table>"+
				"</td>"+
			"</tr>"+
			"<tr>"+
				"<td>"+
					"<table style='width:100%;margin: 0 auto'>"+
						"<tr>"+
							"<td>"+
								"<p>"+
									"<img width='100%' src='http://sirbuped.appspot.com/image/banner/banner-mensaje.jpg' />"+
								"</p>"+
							"</td>"+
						"</tr>"+
					"</table>"+
			"</tr>"+
			"<tr>"+
				"<td>"+
					"<table style='color: #606060; font-size: 15px'>"+
						"<tr>"+
							"<td>"+
								"<p style='margin: 0'>Estimado Colaborador! <br /><br />"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td>"+
								"<div>";
		
		String listado = "<p>" + mensaje + "</p>" + "<br />";
		
								
		String footer = "</div>"+
						"</td>"+
						"</tr>"+
						"<tr>"+
							"<td style='padding: 5px;'>"+
								"Recuerde visitar nuestro portal y conocer mas acerca de nuestra labor. Le invitamos a formar parte activa de nuestro proyecto. Gracias por su atenci\u00F3n."+ 
							"</td>"+
						"</tr>"+
					"</table>"+
				"</td>"+
			"</tr>"+
		
			"<tr>"+
				"<td>"+
					"<table style='width: 100%; margin: 0 auto; background: #FFF; padding: 15px 0; border-top: 2px solid #E2E2E2; border-bottom: 2px solid #E2E2E2'>"+
						"<tr>"+
							"<td style='text-align: center'>"+
								"<a href='#'>"+
									"<img style='margin-right: 15px' src='http://sirbuped.appspot.com/image/social/facebook2.png'>"+
								"</a>"+
								"<a>"+
									"<img style='margin-right: 15px' src='http://sirbuped.appspot.com/image/social/twitter2.png'>"+
								"</a>"+
								"<a>"+
									"<img src='http://sirbuped.appspot.com/image/social/youtube2.png'>"+
								"</a>"+
							"</td>"+
						"</tr>"+
					"</table>"+
				"</td>"+
			"</tr>"+

			"<tr>"+
				"<td>"+
					"<table style='width: 100%; margin: 0 auto; background: #FFF; font-size: 12px'>"+
						"<tr>"+
							"<td style='text-align: left; color: #666; padding: 2px 10px'>"+
								"<span>Copyright 2013 <a style='text-decoration:none;color: #00bebc' href='http://Sirbuped.appspot.com'>Sirbuped.appspot.com</a>, Todos los derecho reservados.</span><br />"+
								"<span style='font-size: 11px'>Sirbuped - Avenida Gran Colombia No. 12E-96 Barrio Colsag</span>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td style='text-align: left; color: #666; padding: 0 10px 10px 10px; background: #F1F1F1'>"+
								"<span>Cucuta, Colombia</span><br />"+
								"<span>Universidad Francisco de Paula Santander</span><br />"+
								"<span>Programa de Ing. de Sistemas</span><br />"+
								"<span>2013</span>"+
							"</td>"+
						"</tr>"+
					"</table>"+
				"</td>"+
			"</tr>"+
		"</table>"+
		"</body>";
		
		return (head + listado + footer);
	}
}
