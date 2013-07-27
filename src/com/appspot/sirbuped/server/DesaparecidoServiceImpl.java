package com.appspot.sirbuped.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.appspot.sirbuped.client.DTO.DatoDesaparicion;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Morfologia;
import com.appspot.sirbuped.client.DTO.SenalParticular;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DesaparecidoServiceImpl extends RemoteServiceServlet implements DesaparecidoService, Serializable
{
	private static final long serialVersionUID = 1L;	
	private static final Logger log = Logger.getLogger(Desaparecido.class.getName());
	
	public void ingresar(Desaparecido desaparecido)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try 
		{
			HttpServletRequest request = this.getThreadLocalRequest();
    		HttpSession session = request.getSession();
			
			String keyUsuario = session.getAttribute("keyUsuario").toString();
			
			Usuario u = pm.getObjectById(Usuario.class, keyUsuario);
			desaparecido.setFechaRegistro(new Date());
			u.getDesaparecidos().add(desaparecido);
		}
		finally 
        {
            pm.close();
        }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Desaparecido> consultar(boolean todos)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Desaparecido.class);
		query.setOrdering("fechaRegistro desc");
		
		if(!todos)
			query.setRange(0, 5);
	    
	    List<Desaparecido> malos = null;
	    ArrayList<Desaparecido> results = null;
	    
		try 
		{
			malos = (List<Desaparecido>) query.execute();
			results = new ArrayList<Desaparecido>();
			Desaparecido des = new Desaparecido();
			for(Desaparecido x : malos)
			{
				des = (pm.detachCopy(x));
				ArrayList<Morfologia> morfologia = x.getMorfologia();
				ArrayList<Morfologia> morfologiaDetached = new ArrayList<Morfologia>();
				
				for(Morfologia y : morfologia)
					morfologiaDetached.add(pm.detachCopy(y));
				
				ArrayList<SenalParticular> senal = x.getSenalParticular();
				ArrayList<SenalParticular> SenalDetached = new ArrayList<SenalParticular>();
				
				for(SenalParticular y : senal)
					SenalDetached.add(pm.detachCopy(y));
				
				DatoDesaparicion dato = x.getDatoDesaparicion();
				DatoDesaparicion datoDetached = new DatoDesaparicion();
				
				datoDetached = pm.detachCopy(dato);
				
				Collections.sort(morfologiaDetached, new Comparator() {  
					  
				    public int compare(Object o1, Object o2) {  
				        Morfologia e1 = (Morfologia) o1;  
				        Morfologia e2 = (Morfologia) o2;  
				        
				        if (Integer.parseInt(e1.getId()) > Integer.parseInt(e2.getId())) {  
				            return 1;  
				        } else if (Integer.parseInt(e1.getId()) < Integer.parseInt(e2.getId())) {  
				            return -1;  
				        } else {  
				            return 0;  
				        } 
				        
				    }  
				}); 
				
				des.setMorfologia(morfologiaDetached);
				des.setSenalParticular(SenalDetached);
				des.setDatoDesaparicion(datoDetached);
				
				results.add(des);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Desaparecido consultar(String documento)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Desaparecido.class);
		query.setFilter("numeroDocumento == documento");
	    query.declareParameters("String documento");
	    
	    List<Desaparecido> desaparecidos = null;
	    ArrayList<Desaparecido> desaparecidosDetached = null;
	    
		try 
		{
			desaparecidos = (List<Desaparecido>) query.execute(documento);
			desaparecidosDetached = new ArrayList<Desaparecido>();
			Desaparecido des = new Desaparecido();
			for(Desaparecido x : desaparecidos)
			{
				des = (pm.detachCopy(x));
				
				ArrayList<Morfologia> morfologia = x.getMorfologia();
				ArrayList<Morfologia> morfologiaDetached = new ArrayList<Morfologia>();
				
				for(Morfologia y : morfologia)
					morfologiaDetached.add(pm.detachCopy(y));
				
				ArrayList<SenalParticular> senal = x.getSenalParticular();
				ArrayList<SenalParticular> SenalDetached = new ArrayList<SenalParticular>();
				
				for(SenalParticular y : senal)
					SenalDetached.add(pm.detachCopy(y));
				
				DatoDesaparicion dato = x.getDatoDesaparicion();
				DatoDesaparicion datoDetached = new DatoDesaparicion();
				
				datoDetached = pm.detachCopy(dato);
				
				Collections.sort(morfologiaDetached, new Comparator() 
				{  
				    public int compare(Object o1, Object o2) 
				    {  
				        Morfologia e1 = (Morfologia) o1;  
				        Morfologia e2 = (Morfologia) o2;  
				        
				        if (Integer.parseInt(e1.getId()) > Integer.parseInt(e2.getId())) {  
				            return 1;  
				        } else if (Integer.parseInt(e1.getId()) < Integer.parseInt(e2.getId())) {  
				            return -1;  
				        } else {  
				            return 0;  
				        } 
				        
				    }  
				}); 
				
				des.setMorfologia(morfologiaDetached);
				des.setSenalParticular(SenalDetached);
				des.setDatoDesaparicion(datoDetached);
				
				desaparecidosDetached.add(des);
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
		return (desaparecidosDetached.get(0));
	}
}
