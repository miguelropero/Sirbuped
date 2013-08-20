package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Mensaje implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
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
	
	@Persistent
	private boolean estado;
	
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

	public boolean isEstado() 
	{
		return estado;
	}

	public void setEstado(boolean estado) 
	{
		this.estado = estado;
	}

	@Override
	public String toString() 
	{
		return "Mensaje [id=" + id + ", nombre=" + nombre + ", email=" + email + ", asunto=" + asunto 
				+ ", mensaje=" + mensaje + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado + "]";
	}
	
}
