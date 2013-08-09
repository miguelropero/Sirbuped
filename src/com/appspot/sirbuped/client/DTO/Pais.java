package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pais implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String nombre;
	
	@Persistent(mappedBy = "pais")
	private ArrayList<Departamento> departamentos;
	
	public Pais()
	{
		departamentos = new ArrayList<Departamento>();
	}

	public Pais(String nombre) 
	{
		this.nombre = nombre;
		departamentos = new ArrayList<Departamento>();
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

	public ArrayList<Departamento> getDepartamentos() 
	{
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamento> departamentos) 
	{
		this.departamentos = departamentos;
	}

	@Override
	public String toString() 
	{
		return "Pais [id=" + id + ", nombre=" + nombre + "]";
	}
}
