package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Morfologia implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@Persistent
	private String id;
	
	@Persistent
	private String nombre;
	
	@Persistent
	private String tipo;
	
	@Persistent
	private String caracteristica;
	
	public Morfologia() { }
	
	public Morfologia(String id, String nombre, String tipo, String caracteristica) 
	{
		this.id = id;
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
		return (this.getId() + " \n " + this.getNombre() + " \n " + this.getTipo() + " \n " + this.getCaracteristica());
	}
}
