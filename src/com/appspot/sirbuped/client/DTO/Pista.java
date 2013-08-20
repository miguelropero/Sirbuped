package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pista implements Serializable
{

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String mensaje;
	
	@Persistent
	private Desaparecido desaparecido = new Desaparecido();
	
	@Persistent
	private String keyUsuario;
	
	@Persistent
	private Date fechaRegistro;
	
	@Persistent
	private Date fechaSuceso;
	
	public Pista()
	{
		
	}

	public Pista(String id, String mensaje, Desaparecido desaparecido, String keyUsuario, Date fechaRegistro, Date fechaSuceso) 
	{
		this.mensaje = mensaje;
		this.desaparecido = desaparecido;
		this.keyUsuario = keyUsuario;
		this.fechaRegistro = fechaRegistro;
		this.fechaSuceso= fechaSuceso;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getMensaje() 
	{
		return mensaje;
	}

	public void setMensaje(String mensaje) 
	{
		this.mensaje = mensaje;
	}

	public Desaparecido getDesaparecido() 
	{
		return desaparecido;
	}

	public void setDesaparecido(Desaparecido desaparecido) 
	{
		this.desaparecido = desaparecido;
	}

	public String getKeyUsuario() 
	{
		return keyUsuario;
	}

	public void setKeyUsuario(String keyUsuario) 
	{
		this.keyUsuario = keyUsuario;
	}

	public Date getFechaRegistro() 
	{
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) 
	{
		this.fechaRegistro = fechaRegistro;
	}
	
	public Date getFechaSuceso() 
	{
		return fechaSuceso;
	}

	public void setFechaSuceso(Date fechaSuceso) 
	{
		this.fechaSuceso = fechaSuceso;
	}

	@Override
	public String toString() 
	{
		return "Pista [id=" + id + ", mensaje=" + mensaje + ", keyUsuario=" + keyUsuario 
				+ ", fechaRegistro=" + fechaRegistro + ", fechaSuceso=" + fechaSuceso + "]";
	}
}
