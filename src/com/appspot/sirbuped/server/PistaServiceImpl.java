package com.appspot.sirbuped.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Pista;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.PistaService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PistaServiceImpl extends RemoteServiceServlet implements PistaService, Serializable
{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Desaparecido.class.getName());
	
	
	public void registrar(Pista pista, String id)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try 
		{
			HttpServletRequest request = this.getThreadLocalRequest();
    		HttpSession session = request.getSession();
			
    		String keyUsuario = session.getAttribute("keyUsuario").toString();
    		pista.setFechaRegistro(new Date());
			pista.setKeyRemitente(keyUsuario);
			
			Desaparecido desaparecido = pm.getObjectById(Desaparecido.class, id);
			desaparecido.getPistas().add(pista);
		}
		
		finally 
        {
            pm.close();
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Pista> getPistasEnviadas()
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		
		Query query= pm.newQuery(Pista.class);
		query.setFilter("this.keyRemitente == keyUsuario");
		query.declareParameters("String keyUsuario");
		
		List<Pista> result=new ArrayList<Pista>();
		ArrayList<Pista> pistas= new ArrayList<Pista>();
		
		try
		{
		   result = (List<Pista>) query.execute(session.getAttribute("keyUsuario").toString());
		   Pista pista = new Pista();
		   
		   for(Pista p : result)
		   {
			   pista = (pm.detachCopy(p));
			 
			   Desaparecido desDetach=pm.detachCopy(p.getDesaparecido());
			   pista.setDesaparecido(desDetach);
			   
			   pistas.add(pista);
		   }
		}
		
		finally
		{
			pm.close();
			query.closeAll();
		}
	
		return pistas;
	}
	
	
	public ArrayList<Desaparecido> getPistasRecibidas()
	{
		PersistenceManager pm= PMF.get().getPersistenceManager();
		HttpServletRequest request = this.getThreadLocalRequest();
		HttpSession session = request.getSession();
		
		ArrayList<Desaparecido> desaparecidosDetached = new ArrayList<Desaparecido>();
		
		try
		{
		   String keyUsuario = session.getAttribute("keyUsuario").toString();
		   Usuario usuario = pm.getObjectById(Usuario.class, keyUsuario);
		 
		   ArrayList<Desaparecido> desaparecidos = usuario.getDesaparecidos();
		   
		   for(Desaparecido desaparecido : desaparecidos)
		   {
			   Desaparecido DesaparecidoDetached = pm.detachCopy(desaparecido);
				
			   ArrayList<Pista> pistasDetached = new ArrayList<Pista>();
				
			   for(Pista pista : desaparecido.getPistas())
			   {
				   Usuario remitente = pm.getObjectById(Usuario.class, pista.getKeyRemitente());
				   
				   pistasDetached.add(pm.detachCopy(pista));
				   pistasDetached.get(pistasDetached.size()-1).setRemitente(pm.detachCopy(remitente));
				   
				   log.warning(pistasDetached.toString());
			   }
			   
			   DesaparecidoDetached.setCiudadNacimiento(null);
			   DesaparecidoDetached.setMorfologia(null);
			   DesaparecidoDetached.setSenalParticular(null);
			   DesaparecidoDetached.setPistas(pistasDetached);
				
			   desaparecidosDetached.add(DesaparecidoDetached);
		   }		
		}
		
		finally
		{
			pm.close();
			
		}
	
		return desaparecidosDetached;
	}
}
