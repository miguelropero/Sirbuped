package com.appspot.sirbuped.server;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

        try 
        {
        	String htmlBody = this.bodyMensaje(new DesaparecidoServiceImpl().getDesaparecidos((byte)4));
        	
        	Multipart mp = new MimeMultipart();
        	
        	MimeBodyPart htmlPart = new MimeBodyPart();
	        htmlPart.setContent(htmlBody, "text/html");
	        mp.addBodyPart(htmlPart);
        	
        	Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sirbuped.ufps@gmail.com", "Sirbuped"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, ""));
            msg.setSubject("Boletin Mensual de Desaparecidos");
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
	
	public String bodyMensaje(ArrayList<Desaparecido> desaparecidos)
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
								"<p style='margin: 0'>Estimado Colaborador! <br /><br />Le enviamos un nuevo bolet\u00EDn con las ultimas personas desaparecidas registradas en nuestra plataforma. Su ayuda es muy valiosa para nosotros, le invitamos a compartir este mensaje con sus contactos y conocidos.</p>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td>"+
								"<div>";
		
		String listado = "";
		
		for(byte i = 0; i < desaparecidos.size(); i++)
		{
			listado += "<div style='border: 1px solid #CCC; color: #FFF; box-shadow: 0 0 2px #CCC;	display: inline-block; margin: 10px 1%; text-align: center; vertical-align: top; width: 22%'>" +
							"<a href='http://sirbuped.appspot.com/#-"+desaparecidos.get(i).getNumeroDocumento()+"' style='cursor: pointer; display: block; text-decoration: none; color: #FFF'>"+
								"<div style='overflow: hidden; width: 100%; margin-bottom: -3px;'>"+
									"<img src='http://sirbuped.appspot.com/"+desaparecidos.get(i).getKeyFoto()+"' style='padding: 3px; width: 95%;'>"+
								"</div>"+
								"<div style='background: #00bebc; font-size: 0.9em; height: 40px; padding: 0.3em 0; width: 100%;'>"+
									desaparecidos.get(i).getNombre1() + " " + desaparecidos.get(i).getApellido1() + " " + desaparecidos.get(i).getApellido2() +
								"</div>"+
							"</a>"+
						"</div>";
		}
									
		String footer = "</div>"+
						"</td>"+
						"</tr>"+
						"<tr>"+
							"<td style='text-align: center; padding: 35px 0; padding-top:15px'>"+
								"<br />"+
								"<a href='http://sirbuped.appspot.com/#personas-desaparecidas' style='color: #FFF; padding: 10px 25px; background: #00bebc; border-radius: 3px; text-decoration: none'> Ver todos </a>"+
								"<br />"+
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
