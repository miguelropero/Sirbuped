package com.appspot.sirbuped.server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import com.appspot.sirbuped.client.DTO.Ciudad;
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
			Usuario usuario = pm.getObjectById(Usuario.class, keyUsuario);
			
			desaparecido.setFechaRegistro(new Date());
			desaparecido.setEdad(this.calcularEdad(desaparecido.getFechaNacimiento()));
			
			usuario.getDesaparecidos().add(desaparecido);
		}
		finally 
        {
            pm.close();
        }
	}
	
	public void actualizarDesaparecido(Desaparecido actualizado)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try
		{
			Desaparecido original = pm.getObjectById(Desaparecido.class, actualizado.getId());
			
			original.setNombre1(actualizado.getNombre1());
			original.setNombre2(actualizado.getNombre2());
			original.setApellido1(actualizado.getApellido1());
			original.setApellido2(actualizado.getApellido2());
			original.setTipoDocumento(actualizado.getTipoDocumento());
			original.setGenero(actualizado.getGenero());
			original.setKeyCiudadNacimiento(actualizado.getKeyCiudadNacimiento());
			original.setFechaNacimiento(actualizado.getFechaNacimiento());
			original.setKeyFoto(actualizado.getKeyFoto());
			original.setEdad(this.calcularEdad(actualizado.getFechaNacimiento()));
			original.setPeso(actualizado.getPeso());
			original.setEstatura(actualizado.getEstatura());
			pm.deletePersistentAll(original.getMorfologia());
			original.getMorfologia().clear();
			original.setMorfologia(actualizado.getMorfologia());
			pm.deletePersistentAll(original.getSenalParticular());
			original.getSenalParticular().clear();
			original.setSenalParticular(actualizado.getSenalParticular());
			pm.deletePersistentAll(original.getPrendaVestir());
			original.getPrendaVestir().clear();
			original.setPrendaVestir(actualizado.getPrendaVestir());
			original.getDatoDesaparicion().setFechaDesaparicion(actualizado.getDatoDesaparicion().getFechaDesaparicion());
			original.getDatoDesaparicion().setKeyCiudadDesaparicion(actualizado.getDatoDesaparicion().getKeyCiudadDesaparicion());
			original.getDatoDesaparicion().setCorregimiento(actualizado.getDatoDesaparicion().getCorregimiento());
			original.getDatoDesaparicion().setInspeccionPolicia(actualizado.getDatoDesaparicion().getInspeccionPolicia());
			original.getDatoDesaparicion().setDescripcion(actualizado.getDatoDesaparicion().getDescripcion());
		}
		
		finally
		{
			pm.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Desaparecido> getDesaparecidos(byte cantidad)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Desaparecido.class);
		query.setOrdering("fechaRegistro desc");
		
		if(cantidad > 0)
			query.setRange(0, cantidad);
	    
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
				
				Collections.sort(morfologiaDetached, new Comparator() 
				{
				    public int compare(Object o1, Object o2) 
				    {				    
				        Morfologia e1 = (Morfologia) o1;  
				        Morfologia e2 = (Morfologia) o2;  
				        
				        if (Integer.parseInt(e1.getConsecutivo()) > Integer.parseInt(e2.getConsecutivo())) 
				            return 1;  
				        
				        else if (Integer.parseInt(e1.getConsecutivo()) < Integer.parseInt(e2.getConsecutivo()))  				      
				            return -1;
				        
				        else 				         
				            return 0;  				        				       
				    }  
				}); 
				
				des.setMorfologia(morfologiaDetached);
				des.setSenalParticular(SenalDetached);
				des.setDatoDesaparicion(datoDetached);
				
				results.add(des);
			}
        }
		finally 
        {
            pm.close();
            query.closeAll();
        }
		
		return results;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Desaparecido getDesaparecido(String keyDesaparecido)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    
	    Desaparecido desaparecido = null;
	    Desaparecido desaparecidoDetached = new Desaparecido();
	    
		try 
		{
			desaparecido = pm.getObjectById(Desaparecido.class, keyDesaparecido);
			desaparecidoDetached = pm.detachCopy(desaparecido);
				
			/* Agregando los datos de la Ciudad de Nacimiento */
			Ciudad ciudadNacimiento = pm.getObjectById(Ciudad.class, desaparecido.getKeyCiudadNacimiento());
			Ciudad ciudadDesaparicion = pm.getObjectById(Ciudad.class, desaparecido.getDatoDesaparicion().getKeyCiudadDesaparicion());
				
			ArrayList<Morfologia> morfologia = desaparecido.getMorfologia();
			ArrayList<Morfologia> morfologiaDetached = new ArrayList<Morfologia>();
				
			if(morfologia.size() > 0)
			{
				for(Morfologia y : morfologia)
					morfologiaDetached.add(pm.detachCopy(y));
			}
			
			ArrayList<SenalParticular> senal = desaparecido.getSenalParticular();
			ArrayList<SenalParticular> SenalDetached = new ArrayList<SenalParticular>();
				
			if(senal.size() > 0)
			{
				for(SenalParticular y : senal)
					SenalDetached.add(pm.detachCopy(y));
			}
				
			DatoDesaparicion dato = desaparecido.getDatoDesaparicion();
			DatoDesaparicion datoDetached = new DatoDesaparicion();
				
			datoDetached = pm.detachCopy(dato);
				
			Collections.sort(morfologiaDetached, new Comparator() 
			{  
				public int compare(Object o1, Object o2) 
				{  
					Morfologia e1 = (Morfologia) o1;  
					Morfologia e2 = (Morfologia) o2;  
				        
					if (Integer.parseInt(e1.getConsecutivo()) > Integer.parseInt(e2.getConsecutivo())) 
						return 1;  
				    
					else if (Integer.parseInt(e1.getConsecutivo()) < Integer.parseInt(e2.getConsecutivo()))  
						return -1;  
				    
					else
						return 0;  				        
				}  
			}); 
				
			desaparecidoDetached.setMorfologia(morfologiaDetached);
			desaparecidoDetached.setSenalParticular(SenalDetached);
			desaparecidoDetached.setDatoDesaparicion(datoDetached);
				
			desaparecidoDetached.setCiudadNacimiento(pm.detachCopy(ciudadNacimiento));
			desaparecidoDetached.getCiudadNacimiento().setDepartamento(pm.detachCopy(ciudadNacimiento.getDepartamento()));
			desaparecidoDetached.getCiudadNacimiento().getDepartamento().setPais(pm.detachCopy(ciudadNacimiento.getDepartamento().getPais()));
				
			desaparecidoDetached.getDatoDesaparicion().setCiudadDesaparicion(pm.detachCopy(ciudadDesaparicion));
			desaparecidoDetached.getDatoDesaparicion().getCiudadDesaparicion().setDepartamento(pm.detachCopy(ciudadDesaparicion.getDepartamento()));
			desaparecidoDetached.getDatoDesaparicion().getCiudadDesaparicion().getDepartamento().setPais(pm.detachCopy(ciudadDesaparicion.getDepartamento().getPais()));
        }
		/*catch(Exception e)
		{
			log.warning(e.toString());
		}*/
		finally 
        {
            pm.close();
        }
		
		return desaparecidoDetached;
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
			numeroDocumento = operador + " numeroDocumento == " + "\'" + desaparecido.getNumeroDocumento() + "\' ";
			operador = "&&";
		}
		
		/*String genero = "";
		if(desaparecido.getGenero() != null)
		{
			genero = operador + " genero == " + "\'" + desaparecido.getGenero() + "\' ";
			operador = "&&";
		}*/
		
		String edad = "";
		if(desaparecido.getEdad() != 0)
			edad = operador + " edad == " + desaparecido.getEdad();
		
		Query query = pm.newQuery(Desaparecido.class, nombre1 + nombre2 + apellido1 + apellido2 + tipoDocumento + numeroDocumento + edad);
		//query.setOrdering("fechaRegistro desc");
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
							if(desaparecido.getMorfologia().get(i).getConsecutivo().equals(filter.getMorfologia().get(j).getConsecutivo()))
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
					if(indicador == desaparecido.getSenalParticular().size())
					{
						filterSenales.add(filter);
					}
				}
			}
			else
			{
				filterSenales = filterMorfologia;
			}
			
			Desaparecido serializado;
			
			for(Desaparecido x : filterSenales)
			{
				serializado = (pm.detachCopy(x));
				serializado.setMorfologia(null);
				serializado.setSenalParticular(null);
				serializado.setDatoDesaparicion(null);
				
				resultFinal.add(serializado);
			}
		}
		finally
		{
			pm.close();
			query.closeAll();
		}
		
		return resultFinal;
	}
	
	public ArrayList<String> mapaDesaparecidos()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<Desaparecido> desaparecidos = this.getDesaparecidos((byte)0);
		
		final ArrayList<String> dpto = new ArrayList<String>();
		dpto.add("Amazonas");
		dpto.add("Antioquia");
		dpto.add("Arauca");
		dpto.add("Atlántico");
		dpto.add("Bolivar");
		dpto.add("Boyaca");
		dpto.add("Caldas");
		dpto.add("Caqueta");
		dpto.add("Casanare");
		dpto.add("Cauca");
		dpto.add("Cesar");
		dpto.add("Choc\u00F3");
		dpto.add("Cordoba");
		dpto.add("Cundinamarca");
		dpto.add("Guainía");
		dpto.add("Guaviare");
		dpto.add("Guajira");
		dpto.add("Huila");
		dpto.add("Magdalena");
		dpto.add("Meta");
		dpto.add("Nariño");
		dpto.add("Norte de Santander");
		dpto.add("Putumayo");
		dpto.add("Quindío");
		dpto.add("Risaralda");
		dpto.add("San Andres y Providencia");
		dpto.add("Santander");
		dpto.add("Sucre");
		dpto.add("Tolima");
		dpto.add("Valle del Cauca");
		dpto.add("Vaupés");
		dpto.add("Vichada");
		
		int [] cantidades = new int [32];
		for(byte i = 0; i < cantidades.length; i++)
		{
			cantidades [i] = 0;
		}
		
		for(Desaparecido desaparecido: desaparecidos)
		{
			Ciudad ciudadNacimiento = pm.getObjectById(Ciudad.class, desaparecido.getDatoDesaparicion().getKeyCiudadDesaparicion());
			desaparecido.setCiudadNacimiento(pm.detachCopy(ciudadNacimiento));
			
			cantidades [dpto.indexOf(ciudadNacimiento.getDepartamento().getNombre())] =  cantidades [dpto.indexOf(ciudadNacimiento.getDepartamento().getNombre())] +1;
		}
		
		for(byte i = 0; i < dpto.size(); i++)
		{
			dpto.set(i, dpto.get(i) + "-" +cantidades[i]); 
		}
		
		return dpto;
	}
	
	public ArrayList<Desaparecido> getDesaparecidosDpto(String departamento)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ArrayList<Desaparecido> results = new ArrayList<Desaparecido>();
		
		try
		{
			ArrayList<Desaparecido> desaparecidos = this.getDesaparecidos((byte)0);
			
			for(Desaparecido desaparecido: desaparecidos)
			{
				Ciudad ciudadDesaparicion = pm.getObjectById(Ciudad.class, desaparecido.getDatoDesaparicion().getKeyCiudadDesaparicion());
				
				if(ciudadDesaparicion.getDepartamento().getNombre().equals(departamento))
				{
					results.add(desaparecido);
				}
				
				if(results.size() == 3)
					break;
			}
		}
		finally
		{
			pm.close();
		}
		
		return results;
	}
	
	public Desaparecido validarDesaparecidoUsuario(String keyDesaparecido)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try
		{
			HttpServletRequest request = this.getThreadLocalRequest();
			HttpSession session = request.getSession();
			
			String keyUsuario = session.getAttribute("keyUsuario").toString();
			
			if(keyUsuario != null)
			{
				Usuario usuario = pm.getObjectById(Usuario.class, keyUsuario);
				for(Desaparecido nuevo : usuario.getDesaparecidos())
				{
					if(nuevo.getId().equals(keyDesaparecido))
						return this.getDesaparecido(keyDesaparecido);
				}
			}
		}
		
		finally
		{
			pm.close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public boolean validarDocumento(String documento)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Desaparecido.class, "numeroDocumento == \'" + documento + "\'");
		try
		{
			List<Desaparecido> desaparecidos = (List<Desaparecido>) query.execute();
			
			if(desaparecidos.size() > 0)
				return false;
		}
		finally
		{
			pm.close();
		}
		
		return true;
	}
	
	private int calcularEdad(Date fechaNacimiento)
	{
		Date fechaActual = new Date();
		
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    String hoy = formato.format(fechaActual);
	    
	    String fNacimiento = formato.format(fechaNacimiento);
	    
	    String[] dat1 = fNacimiento.split("/");
	    String[] dat2 = hoy.split("/");
	    
	    int anos = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
	    int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
	    if (mes < 0) 
	    {
	      anos = anos - 1;
	    } 
	    else if (mes == 0) 
	    {
	      int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
	      if (dia > 0) 
	      {
	        anos = anos - 1;
	      }
	    }
	    
	    return anos;
	}
	
	
	
	/******************************************************************************
	 *************** METODOS PARA LA GENERACIÓN DE LAS ESTADISTICAS ***************
	/******************************************************************************/
	
	@SuppressWarnings({ "unchecked"})
	public String consultarGenero()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Desaparecido.class);
		query.setFilter("this.genero == genero");
		query.declareParameters("boolean genero");
	    
	    List<Desaparecido> mujeres= null;
	    List<Desaparecido> hombres= null;
	    String size="";
	    
	    try 
		{
			mujeres = (List<Desaparecido>) query.execute(false);
			size=mujeres.size()+"-";
			
			hombres = (List<Desaparecido>) query.execute(true);
			size+=hombres.size();
		}
		finally 
        {
            pm.close();
            query.closeAll();
        }
	    
	    return size;
	}
	
	private String consultaDD( List<DatoDesaparicion> result, int fecha)
	{
	
		 int a=0,b=0,c=0,d=0,f=0,g=0;
		 String cadena="";
    	 
    	 for(DatoDesaparicion dd: result)
    	 {
    		
    		
    		 if(dd.getDesaparecido().getEdad()<=10)
    			 a+=1;
    		 else if(dd.getDesaparecido().getEdad()>10 && dd.getDesaparecido().getEdad()<=20)
    			 b+=1;
    		 else if(dd.getDesaparecido().getEdad()>20 && dd.getDesaparecido().getEdad()<=30)
    			 c+=1;
    		 else if(dd.getDesaparecido().getEdad()>30 && dd.getDesaparecido().getEdad()<=40)
    			 d+=1;
    		 else if(dd.getDesaparecido().getEdad()>40 && dd.getDesaparecido().getEdad()<=50)
    			 f+=1;
    		 else if(dd.getDesaparecido().getEdad()>50 && dd.getDesaparecido().getEdad()<=90)
    			 g+=1;
    	 }
    	cadena=(fecha+1900)+"-"+a+"-"+b+"-"+c+"-"+d+"-"+f+"-"+g+"";
    	
    	return cadena;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<String> consultarDesaparecidoEdad()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Date fecha=new Date();
		fecha.setMonth(11);
		fecha.setDate(31);
		
		Date fecha2=new Date((fecha.getYear()-1),11,31);
		Date fecha3=new Date((fecha2.getYear()-1),11,31);
		Date fecha4=new Date((fecha3.getYear()-1),11,31);
		
		Query query= pm.newQuery(DatoDesaparicion.class);
		query.setFilter("this.fechaDesaparicion>fecha2 && this.fechaDesaparicion<=fecha");
		query.declareParameters("Date fecha2, Date fecha ");
		query.declareImports("import java.util.Date");
		
	    List<DatoDesaparicion> anio1= new ArrayList<DatoDesaparicion>();
	    List<DatoDesaparicion> anio2= new ArrayList<DatoDesaparicion>();
	    List<DatoDesaparicion> anio3= new ArrayList<DatoDesaparicion>();
	   
	    ArrayList<String> size= new ArrayList<String>();
	    
	    try 
		{
	    	 anio1= (List<DatoDesaparicion>) query.execute(fecha2,fecha);
	    	 anio2= (List<DatoDesaparicion>) query.execute(fecha3,fecha2);
	    	 anio3= (List<DatoDesaparicion>) query.execute(fecha4,fecha3); 
	    		
	    	 size.add(this.consultaDD(anio3, fecha3.getYear()));
	    	 size.add(this.consultaDD(anio2, fecha2.getYear()));
	    	 size.add(this.consultaDD(anio1, fecha.getYear()));
		}
		finally 
        {
			query.closeAll();
            pm.close();
        }
	    
	    return size;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ArrayList<String> consultarDesaparecidoAnio()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Date fecha=new Date();
		fecha.setMonth(11);
		fecha.setDate(31);
		
		Date fecha2=new Date((fecha.getYear()-1),11,31);
		Date fecha3=new Date((fecha2.getYear()-1),11,31);
		Date fecha4=new Date((fecha3.getYear()-1),11,31);
		Date fecha5=new Date((fecha4.getYear()-1),11,31);
		
		Query query= pm.newQuery(DatoDesaparicion.class);
		query.setFilter("this.fechaDesaparicion>fecha2 && this.fechaDesaparicion<=fecha");
		query.declareParameters("Date fecha2, Date fecha ");
		query.declareImports("import java.util.Date");
		
	    List<DatoDesaparicion> anio1= new ArrayList<DatoDesaparicion>();
	    List<DatoDesaparicion> anio2= new ArrayList<DatoDesaparicion>();
	    List<DatoDesaparicion> anio3= new ArrayList<DatoDesaparicion>();
	    List<DatoDesaparicion> anio4= new ArrayList<DatoDesaparicion>();
	   
	    ArrayList<String> size= new ArrayList<String>();
	    
	    try
	    {
		    anio1= (List<DatoDesaparicion>) query.execute(fecha2,fecha);
		    anio2= (List<DatoDesaparicion>) query.execute(fecha3,fecha2);
		    anio3= (List<DatoDesaparicion>) query.execute(fecha4,fecha3); 
		    anio4= (List<DatoDesaparicion>) query.execute(fecha5,fecha4);	
		    	
		    size.add((fecha4.getYear()+1900)+"-"+anio4.size());
	        size.add((fecha3.getYear()+1900)+"-"+anio3.size());
	        size.add((fecha2.getYear()+1900)+"-"+anio2.size());
	        size.add((fecha.getYear()+1900)+"-"+anio1.size());
	    }
		finally 
        {
            pm.close();
            query.closeAll();
        }
	    
	    return size;    
	}
	
}
