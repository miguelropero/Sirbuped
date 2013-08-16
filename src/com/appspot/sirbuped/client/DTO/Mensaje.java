package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Mensaje implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Persistent
	private String nombre;
	
	@Persistent
	private String email;
	
	@Persistent
	private String asunto;
	
	@Persistent
	private String mensaje;
	
	@Persistent
	private Date fechaRegistro;
	
	public Mensaje()
	{}
	
	public Mensaje(String nombre, String email, String asunto, String mensaje, Date fechaRegistro) 
	{
		this.nombre = nombre;
		this.email = email;
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.fechaRegistro=fechaRegistro;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getAsunto() 
	{
		return asunto;
	}

	public void setAsunto(String asunto) 
	{
		this.asunto = asunto;
	}

	public String getMensaje() 
	{
		return mensaje;
	}

	public void setMensaje(String mensaje) 
	{
		this.mensaje = mensaje;
	}

	public Date getFechaRegistro() 
	{
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) 
	{
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public String toString() 
	{
		return "Mensaje [nombre=" + nombre + ", email=" + email + ", asunto=" + asunto 
				+ ", mensaje=" + mensaje + ", fechaRegistro=" + fechaRegistro + "]";
	}


	
	
	
	
	

}
