package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Departamento implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String nombre;
	
	@Persistent(mappedBy = "departamento")
	private ArrayList<Ciudad> ciudades;
	
	@Persistent
	private Pais pais;
	
	public Departamento() 
	{
		ciudades = new ArrayList<Ciudad>();
	}
	
	public Departamento(String nombre) 
	{
		this.nombre = nombre;
		ciudades = new ArrayList<Ciudad>();
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

	public ArrayList<Ciudad> getCiudades() 
	{
		return ciudades;
	}

	public void setCiudades(ArrayList<Ciudad> ciudades) 
	{
		this.ciudades = ciudades;
	}
	
	public Pais getPais() 
	{
		return pais;
	}

	public void setPais(Pais pais) 
	{
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", pais="
				+ pais + "]";
	}
	
}
