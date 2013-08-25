package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Morfologia implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String consecutivo;
	
	@Persistent
	private String tipo;
	
	@Persistent
	private String nombre;
	
	@Persistent
	private String caracteristica;
	
	public Morfologia() 
	{ }
	
	public Morfologia(String consecutivo, String nombre, String tipo, String caracteristica) 
	{
		this.consecutivo = consecutivo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.caracteristica = caracteristica;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}
	
	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getConsecutivo() 
	{
		return consecutivo;
	}
	
	public void setConsecutivo(String consecutivo) 
	{
		this.consecutivo = consecutivo;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getTipo() 
	{
		return tipo;
	}
	
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	
	public String getCaracteristica() 
	{
		return caracteristica;
	}
	
	public void setCaracteristica(String caracteristica) 
	{
		this.caracteristica = caracteristica;
	}

	@Override
	public String toString() 
	{
		return "Morfologia [id=" + id + ", consecutivo=" + consecutivo + ", tipo=" + tipo + ", nombre=" 
				+ nombre + ", caracteristica=" + caracteristica + "]";
	}
	
	
}
