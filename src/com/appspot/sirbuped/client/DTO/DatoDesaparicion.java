package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class DatoDesaparicion implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Persistent
	private Date fechaDesaparicion;
	
	@Persistent
	private String corregimiento;
	
	@Persistent
	private String inspeccionPolicia;
	
	@Persistent
	private String descripcion;
	
	public DatoDesaparicion()
	{}
	
	public DatoDesaparicion(Date fechaDesaparicion, String corregimiento, String inspeccionPolicia, String descripcion) 
	{
		this.fechaDesaparicion = fechaDesaparicion;
		this.corregimiento = corregimiento;
		this.inspeccionPolicia = inspeccionPolicia;
		this.descripcion = descripcion;
	}

	public Date getFechaDesaparicion() 
	{
		return fechaDesaparicion;
	}

	public void setFechaDesaparicion(Date fechaDesaparicion) 
	{
		this.fechaDesaparicion = fechaDesaparicion;
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
	
	@Override
	public String toString()
	{
		return (this.getFechaDesaparicion() + " \n " + this.getCorregimiento() + " \n " + this.getInspeccionPolicia() + " \n " +
				this.getDescripcion());
	}
}
