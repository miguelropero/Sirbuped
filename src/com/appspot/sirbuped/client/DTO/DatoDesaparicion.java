package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class DatoDesaparicion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private Date fechaDesaparicion;
	
	@Persistent
	private String keyCiudadDesaparicion;
	
	@Persistent
	private Ciudad ciudadDesaparicion;
	
	@Persistent
	private String corregimiento;
	
	@Persistent
	private String inspeccionPolicia;
	
	@Persistent
	private String descripcion;
	
	@Persistent(mappedBy = "datoDesaparicion")
    private Desaparecido desaparecido;
	
	public DatoDesaparicion()
	{}
	
	public DatoDesaparicion(Date fechaDesaparicion, String corregimiento, String inspeccionPolicia, String descripcion) 
	{
		this.fechaDesaparicion = fechaDesaparicion;
		this.corregimiento = corregimiento;
		this.inspeccionPolicia = inspeccionPolicia;
		this.descripcion = descripcion;
	}
	
	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public Date getFechaDesaparicion() 
	{
		return fechaDesaparicion;
	}

	public void setFechaDesaparicion(Date fechaDesaparicion) 
	{
		this.fechaDesaparicion = fechaDesaparicion;
	}

	public String getKeyCiudadDesaparicion() 
	{
		return keyCiudadDesaparicion;
	}

	public void setKeyCiudadDesaparicion(String keyCiudadDesaparicion) 
	{
		this.keyCiudadDesaparicion = keyCiudadDesaparicion;
	}

	public Ciudad getCiudadDesaparicion() 
	{
		return ciudadDesaparicion;
	}

	public void setCiudadDesaparicion(Ciudad ciudadDesaparicion) 
	{
		this.ciudadDesaparicion = ciudadDesaparicion;
	}

	public String getCorregimiento() 
	{
		return corregimiento;
	}

	public void setCorregimiento(String corregimiento) 
	{
		this.corregimiento = corregimiento;
	}

	public String getInspeccionPolicia() 
	{
		return inspeccionPolicia;
	}

	public void setInspeccionPolicia(String inpeccionPolicia) 
	{
		this.inspeccionPolicia = inpeccionPolicia;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	public Desaparecido getDesaparecido() 
	{
		return desaparecido;
	}

	public void setDesaparecido(Desaparecido desaparecido) 
	{
		this.desaparecido = desaparecido;
	}

	@Override
	public String toString() {
		return "DatoDesaparicion [id=" + id + ", fechaDesaparicion="
				+ fechaDesaparicion + ", keyCiudadDesaparicion="
				+ keyCiudadDesaparicion + ", ciudadDesaparicion="
				+ ciudadDesaparicion + ", corregimiento=" + corregimiento
				+ ", inspeccionPolicia=" + inspeccionPolicia + ", descripcion="
				+ descripcion + "]";
	}
	
	
}
