package com.appspot.sirbuped.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import com.appspot.sirbuped.client.JCrypt;
import com.appspot.sirbuped.client.DTO.Ciudad;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.LoginInfo;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.UsuarioService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UsuarioServiceImpl extends RemoteServiceServlet implements UsuarioService
{	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Desaparecido.class.getName());
	
	public void addUsuario(Usuario nuevo)
	{    
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
	    try 
	    {
	    	pm.makePersistent(nuevo);
	    	
	    	Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);

	        try 
	        {
	        	String htmlBody = this.body(nuevo);

		        Multipart mp = new MimeMultipart();

		        MimeBodyPart htmlPart = new MimeBodyPart();
		        htmlPart.setContent(htmlBody, "text/html");
		        mp.addBodyPart(htmlPart);
	        	
	        	Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("sirbuped.ufps@gmail.com", "Sirbuped"));
	            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(nuevo.getEmail(), ""));
	            msg.setSubject("Avtivar cuenta de usuario");
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
				e.printStackTrace();
			}
	    } 
	    finally 
	    {
	    	pm.close();
	    }
	}
	
	public void editarUsuario(Usuario editado)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
	
		try
		{
			Usuario original  = pm.getObjectById(Usuario.class, editado.getId());
			original.setNombres(editado.getNombres());
			original.setApellidos(editado.getApellidos());
			original.setTipoDocumento(editado.getTipoDocumento());
			original.setNumeroDocumento(editado.getNumeroDocumento());
			original.setFechaNacimiento(editado.getFechaNacimiento());
			original.setTelefono(editado.getTelefono());
			original.setTelefonoCel(editado.getTelefonoCel());
			original.setKeyCiudadResidencia(editado.getKeyCiudadResidencia());
			original.setDireccion(editado.getDireccion());
			
			Cache cache;
			try 
			{
				CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
				cache = cacheFactory.createCache(Collections.emptyMap());
	            
				// Put the value into the cache.
		        cache.put(original.getId(), original);
		          
		        HttpServletRequest request = this.getThreadLocalRequest();
					
		        ((HttpServletRequest)request).getSession().setAttribute("usuario", original.getNombres() + " " + original.getApellidos());
			} 
			catch (CacheException e) 
			{
				log.warning(e.toString());
			}
		}
		finally
		{
			pm.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Usuario iniciarSesion(Usuario usuario)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Usuario.class);
		query.setFilter("email == mail");
	    query.declareParameters("String mail");
	    
	    List<Usuario> usuarios = new ArrayList<Usuario>();
	    
	    try 
		{
			usuarios = (List<Usuario>) query.execute(usuario.getEmail());
			
			if(usuarios.size() > 0 && usuarios.get(0).getEstado())
			{
				
				Usuario usuarioValido = new Usuario();
				usuarioValido = pm.detachCopy(usuarios.get(0));
				
				String salto = usuarioValido.getPassword().substring(0, 2);
				String passwordCrypt = JCrypt.crypt(salto, usuario.getPassword());
				
				if(passwordCrypt.equals(usuarioValido.getPassword()))
				{
					Cache cache;
					try 
					{
						CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
						cache = cacheFactory.createCache(Collections.emptyMap());
			            
						// Put the value into the cache.
				        cache.put(usuarioValido.getId(), usuarioValido);
				        log.warning(usuarioValido.toString());
				        HttpServletRequest request = this.getThreadLocalRequest();
							
				        ((HttpServletRequest)request).getSession().setAttribute("usuario", usuarioValido.getNombres() + " " + usuarioValido.getApellidos());
				        ((HttpServletRequest)request).getSession().setAttribute("keyUsuario", usuarioValido.getId());
					} 
					catch (CacheException e) 
					{
						log.warning(e.toString());
						return null;
					}
					return usuarioValido;
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
        }
	    catch(Exception e)
	    {
	    	log.warning(e.toString());
	    	return null;
	    }
		finally 
        {
            pm.close();
            query.closeAll();
        }
	}
	
	public void cerrarSesion()
	{
		Cache cache;
		try 
		{
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
            
            HttpServletRequest request = this.getThreadLocalRequest();
    		HttpSession session = request.getSession();
    		
            cache.remove(session.getAttribute("keyUsuario"));
            session.invalidate();
        } 
		catch (CacheException e) 
		{
			log.warning(e.toString());
        }
	}
	
	public String getSesion(String atributo)
	{
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		Object rta = session.getAttribute(atributo);
		
		if(rta!=null) 
		{
			return rta.toString();
		}
		else
		{
			return null;
		}
	}
	
	/** Metodo que permite obtener el usuario almacenado en Cache 
	 * 
	 **/
	public Usuario getUsuario()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Cache cache;
		Usuario usuario = new Usuario();
		Ciudad ciudad = new Ciudad();
		
		try 
		{
			
			String  key = this.getSesion("keyUsuario");
			
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
            log.warning(ciudad.toString());
            usuario = (Usuario) cache.get(key);
            
            //en caso de que la cache falle
            if(usuario == null)
            {
            	Usuario user = pm.getObjectById(Usuario.class, key); 
            	usuario = pm.detachCopy(user);
            	log.warning("2" + usuario.toString());
            }
            
            if(!usuario.getKeyCiudadResidencia().isEmpty())
            {
            	ciudad = pm.getObjectById(Ciudad.class, usuario.getKeyCiudadResidencia());
            	log.warning(ciudad.toString());
            }
        } 
		catch (CacheException e) 
		{
			log.warning("Usuario no existe usuario");
			log.warning(e.toString());
			usuario = null;
        }
		usuario.setCiudadResidencia(ciudad);
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public LoginInfo loginGoogle(String requestUri) 
	{
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    LoginInfo loginInfo = new LoginInfo();
	    loginInfo.setLoggedIn(false);
	    
	    List<Usuario> usuarios = new ArrayList<Usuario>();
	    
	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    Query query = pm.newQuery(Usuario.class);
		query.setFilter("email == mail");
	    query.declareParameters("String mail");
	    
	    if (user != null)
	    {
	    	loginInfo.setLoggedIn(true);
		    try 
			{
		    	usuarios = (List<Usuario>) query.execute(user.getEmail());
		    	
		    	if(usuarios.size() > 0 && usuarios.get(0).getEstado())
		    	{
					loginInfo.setEmailAddress(user.getEmail());
					//loginInfo.setNickname(user.getNickname());
					loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
					loginInfo.setRegistrado(true);
				    	
					Usuario usuarioValido = new Usuario();
					usuarioValido = pm.detachCopy(usuarios.get(0));
					
					Cache cache;
					CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
					cache = cacheFactory.createCache(Collections.emptyMap());
				        
					// Put the value into the cache.
					cache.put(usuarioValido.getId(), usuarioValido);
					
					HttpServletRequest request = this.getThreadLocalRequest();
					
					((HttpServletRequest)request).getSession().setAttribute("usuario", usuarioValido.getNombres() + " " + usuarioValido.getApellidos());
					((HttpServletRequest)request).getSession().setAttribute("keyUsuario", usuarioValido.getId());
				}
		    	else
		    	{
		    		loginInfo.setEmailAddress(user.getEmail());
		    		loginInfo.setRegistrado(false);
		    		
		    	}
			}
			catch (CacheException e) 
			{
				log.warning(e.toString());
				loginInfo.setLoggedIn(false);
		    	loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
			}
		}
	    else 
	    {
	    	loginInfo.setLoggedIn(false);
	    	log.warning(userService.createLoginURL(requestUri));
	    	loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
	    }
	    return loginInfo;
	}
	
	public String body(Usuario usuario)
	{
		String body = "<body style='background: #F5F5F5; font-family: Helvetica; padding: 15px 0'>"+
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
								"<p style='margin: 0'>Hola "+ usuario.getNombres() + " " + usuario.getApellidos() +", <br /><br />Usted se ha registrado correctamente en Sirbuped. Gracias por formar parte de esta comunidad que brinda una luz de esperanza a todos aquellos que sufren el flagelo de la desaparici\u00F3n de un familiar o ser querido.</p>"+

								"<p>Para completar su registro, habilitar su cuenta y hacer uso de los diferentes servicios que ofrece nuestra plataforma debe hacer clic en el siguiente enlace.</p>"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td style='text-align: center; padding: 35px 0; padding-top:15px'>"+
								"<br />"+
								"<a href='http://sirbuped.appspot.com/activar-cuenta?key="+usuario.getId()+"' style='color: #FFF; padding: 10px 20px; background: #00bebc; border-radius: 3px; text-decoration: none'>Activar cuenta</a>"+
								"<br />"+
							"</td>"+
						"</tr>"+
						"<tr>"+
							"<td style='padding: 5px;'>"+
								"Tenga en cuenta los t\u00E9rminos y condiciones que Sirbuped establece para hacer uso de cada uno de los servicios ofrecidos. Recuerde que cualquier informaci\u00F3n fraudulenta conlleva al cierre de su cuenta."+ 
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
		
		return body;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String key = req.getParameter("key");
		resp.setHeader("Content-Type", "text/html");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try 
		{
			Usuario usuario = pm.getObjectById(Usuario.class, key);
			if(usuario != null && !usuario.getEstado())
			{
				usuario.setEstado(true);
				resp.getWriter().println("" +
						"<body style='background: #FFF'>" +
							"<div style='margin: 120px auto; padding: 48px; border: 1px solid #C8C8C8; box-shadow: 0 3px 20px rgba(0, 0, 0, 0.2); width: 40%; text-align: center'>"+
								"<h1 style=\"margin-bottom: 20px; padding: 10px; background: #00BEBC; color:#FFF; font: normal 2em/1.4em \'PT Sans\', Verdana, Geneva, sans-serif; border: 1px solid #009C9A\" >Su cuenta ha sido Activada!</h1>"+
								"<hr style='margin-bottom: 30px; height: 1px; border: none; background-image: linear-gradient(180deg, rgba(200, 200, 200, 0) 0%, #c8c8c8 50%, rgba(200, 200, 200, 0));'>"+
								"<p style=\"color: #3c3c3c; margin: 0; font: normal 1em/1.6em \'PT Sans\', Verdana, Geneva, sans-serif;\">Gracias por activar su cuenta. Ahora puede <a href='/#iniciar-sesion' style='text-decoration: none; color: #00BEBC'>iniciar sesi\u00F3n</a>.</p>"+
							"</div>"+
						"</body>");
			}
			else
			{
				resp.getWriter().println("Error, el enlace no es valido");
			}
		}
		finally 
        {
            pm.close();
        }
	}
	
	public ArrayList<Desaparecido> getDesaparecidosUsuario()
	{
		Usuario usuario = this.getUsuario();
		
		log.warning(usuario.toString());
		
		ArrayList<Desaparecido> desaparecidosDetached = usuario.getDesaparecidos();
		
		if(desaparecidosDetached.isEmpty())
		{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			
			try
			{
				usuario = pm.getObjectById(Usuario.class, usuario.getId());			
				
				ArrayList<Desaparecido> desaparecidos = usuario.getDesaparecidos();
				for(Desaparecido desaparecido : desaparecidos)
				{
					Desaparecido DesaparecidoDetached = pm.detachCopy(desaparecido);
					DesaparecidoDetached.setCiudadNacimiento(null);
					DesaparecidoDetached.setMorfologia(null);
					DesaparecidoDetached.setSenalParticular(null);
					DesaparecidoDetached.setDatoDesaparicion(null);
					
					desaparecidosDetached.add(DesaparecidoDetached);
				}
			}
			finally
			{
				pm.close();
			}
		}
		
		return desaparecidosDetached;
	}
}
