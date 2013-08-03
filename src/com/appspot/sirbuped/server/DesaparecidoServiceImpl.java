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
	
	public void registrar(Desaparecido desaparecido)
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
			//comentario
		}
		finally 
        {
            pm.close();
        }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Desaparecido> getDesaparecidos(boolean todos)
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
			
			log.warning(malos.get(0).getDatoDesaparicion().getDesaparecido().toString());
			
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
		/*catch(Exception e)
		{
			log.warning(e.toString());
		}*/
		finally 
        {
            pm.close();
            query.closeAll();
        }
		
		return (results);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Desaparecido getDesaparecido(String documento)
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
	
	@SuppressWarnings("unchecked")
	public ArrayList<Desaparecido> consultarDesaparecido(Desaparecido desaparecido)
	{
		
		ArrayList<Desaparecido> resultFinal = new ArrayList<Desaparecido>();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Desaparecido> results = null;
		
		String operador = "";
		
		String nombre1="";
		if(!desaparecido.getNombre1().isEmpty())
		{
			nombre1 = "nombre1 == " + "\'" + desaparecido.getNombre1() + "\' ";
			operador = "&&";
		}
		
		String nombre2 = "";
		if(!desaparecido.getNombre2().isEmpty())
		{
			nombre2 = operador + " nombre2 == " + "\'" + desaparecido.getNombre2() + "\' ";
			operador = "&&";
		}
		
		String apellido1 = "";	
		if(!desaparecido.getApellido1().isEmpty())
		{
			apellido1 = operador + " apellido1 == " + "\'" + desaparecido.getApellido1() + "\' ";
			operador = "&&";
		}
			
		String apellido2 = "";	
		if(!desaparecido.getApellido2().isEmpty())
		{
			apellido2 = operador + " apellido2 == " + "\'" + desaparecido.getApellido2() + "\' ";
			operador = "&&";
		}
		
		String tipoDocumento = "";	
		if(!desaparecido.getTipoDocumento().isEmpty())
		{
			tipoDocumento = operador + " tipoDocumento == " + "\'" + desaparecido.getTipoDocumento() + "\' ";
			operador = "&&";
		}
		
		String numeroDocumento = "";
		if(!desaparecido.getNumeroDocumento().isEmpty())
		{
			numeroDocumento = operador + " numeroDocumento == " + "\'" + desaparecido.getNumeroDocumento() + "\'";
			operador = "&&";
		}
		
		/*String genero = "";
		if(desaparecido.getGenero() != null)
			genero = "genero =" + desaparecido.getGenero();*/
		
		String edad = "";
		if(desaparecido.getEdad() != 0)
			edad = operador + " edad == " + desaparecido.getEdad();
		
		Query query = pm.newQuery(Desaparecido.class, nombre1 + nombre2 + apellido1 + apellido2 + tipoDocumento + numeroDocumento + edad);
		
		log.warning(query.toString());
		
		try
		{
			results = (List<Desaparecido>) query.execute();
			
			List<Desaparecido> filterMorfologia = new ArrayList<Desaparecido>();
			
			if(desaparecido.getMorfologia().size() > 0)
			{
				for(Desaparecido filter: results)
				{
					byte indicador = 0;
					for(byte i = 0; i < desaparecido.getMorfologia().size(); i++)
					{
						for(byte j = 0; j < filter.getMorfologia().size(); j++)
						{
							if(desaparecido.getMorfologia().get(i).getId().equals(filter.getMorfologia().get(j).getId()))
							{
								indicador++;
								break;
							}
						}
					}
					if(indicador > 0)
					{
						filterMorfologia.add(filter);
					}
				}
			}
			else
			{
				filterMorfologia = results;
			}
			
			List<Desaparecido> filterSenales = new ArrayList<Desaparecido>();
			
			if(desaparecido.getSenalParticular().size() > 0)
			{
				for(Desaparecido filter: filterMorfologia)
				{
					byte indicador = 0;
					for(byte i = 0; i < desaparecido.getSenalParticular().size(); i++)
					{
						for(byte j = 0; j < filter.getSenalParticular().size(); j++)
						{
							if(desaparecido.getSenalParticular().get(i).getNombre().equals(filter.getSenalParticular().get(j).getNombre()))
							{
								indicador++;
								break;
							}
						}
					}
					if(indicador > 0)
					{
						filterSenales.add(filter);
					}
				}
			}
			else
			{
				filterSenales = filterMorfologia;
			}
			
			//resultFinal = (ArrayList<Desaparecido>) filterSenales;
			
			Desaparecido serializado;
			for(Desaparecido x : filterSenales)
			{
				serializado = (pm.detachCopy(x));
				serializado.setMorfologia(null);
				serializado.setSenalParticular(null);
				serializado.setDatoDesaparicion(null);
				
				resultFinal.add(serializado);
			}
			
			log.warning(desaparecido.toString());
			log.warning(results.toString());
			log.warning(filterMorfologia.toString());
			log.warning(filterSenales.toString());
			log.warning(resultFinal.toString());
			
		}
		finally
		{
			pm.close();
			query.closeAll();
		}
		
		return resultFinal;
	}
	
}
