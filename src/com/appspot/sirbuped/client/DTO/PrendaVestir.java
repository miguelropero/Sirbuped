package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class PrendaVestir implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String nombre;
	
	@Persistent
	private String caracteristica;
	
	@Persistent
	private String observacion;
	
	public PrendaVestir()
	{}
	
	public PrendaVestir(String nombre, String caracteristica, String observacion) 
	{
		this.nombre = nombre;
		this.caracteristica = caracteristica;
		this.observacion = observacion;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getCaracteristica() 
	{
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) 
	{
		this.caracteristica = caracteristica;
	}

	public String getObservacion() 
	{
		return observacion;
	}

	public void setObservacion(String observacion) 
	{
		this.observacion = observacion;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}

	@Override
	public String toString() 
	{
		return "PrendaVestir [id=" + id + ", nombre=" + nombre + ", caracteristica=" + caracteristica 
				+ ", observacion=" + observacion + "]";
	}
}
