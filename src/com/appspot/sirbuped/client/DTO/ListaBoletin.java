package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ListaBoletin implements Serializable
{
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	private String email;
	
	@Persistent
	private Date fechaRegistro;
  
	public ListaBoletin(String email) 
	{
		this.email = email;
		this.fechaRegistro = new Date();	
	}

	public ListaBoletin()
	{}
	
	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Date getFechaRegistro() 
	{
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) 
	{
		this.fechaRegistro = fechaRegistro;
	}
}
