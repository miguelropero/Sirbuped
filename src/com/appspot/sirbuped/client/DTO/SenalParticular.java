package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class SenalParticular implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String nombre;
	
	@Persistent
	private String ubicacion;
	
	@Persistent
	private String caracteristica;
	
	public SenalParticular()
	{}

	public SenalParticular(String nombre, String ubicacion, String caracteristica) 
	{
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.caracteristica = caracteristica;
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

	public String getUbicacion() 
	{
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) 
	{
		this.ubicacion = ubicacion;
	}

	public String getCaracteristica() 
	{
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) 
	{
		this.caracteristica = caracteristica;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}

	@Override
	public String toString() 
	{
		return "SenalParticular [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", caracteristica="
				+ caracteristica + "]";
	}
	
}
