package com.appspot.sirbuped.client.DTO;

import java.util.Date;

import javax.jdo.annotations.Persistent;

public class Usuario 
{
	@Persistent
	private String nombre_1;
	
	@Persistent
	private String nombre_2;
	
	@Persistent
	private String apellido_1;
	
	@Persistent
	private String apellido_2;
	
	@Persistent
	private String documento;
	
	@Persistent
	private Date fechaNacimiento;
	
	@Persistent
	private String genero;
	
	@Persistent
	private String telefono;
	
	@Persistent
	private String email;
	
	public Usuario()
	{
		
	}
	
	public Usuario(String nombre_1, String nombre_2, String apellido_1, String apellido_2, String documento, Date fechaNacimiento, String genero) 
	{
		this.nombre_1 = nombre_1;
		this.nombre_2 = nombre_2;
		this.apellido_1 = apellido_1;
		this.apellido_2 = apellido_2;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
	}

	public String getNombre_1() 
	{
		return nombre_1;
	}

	public void setNombre_1(String nombre_1) 
	{
		this.nombre_1 = nombre_1;
	}

	public String getNombre_2() 
	{
		return nombre_2;
	}

	public void setNombre_2(String nombre_2) 
	{
		this.nombre_2 = nombre_2;
	}

	public String getApellido_1() 
	{
		return apellido_1;
	}

	public void setApellido_1(String apellido_1) 
	{
		this.apellido_1 = apellido_1;
	}

	public String getApellido_2() 
	{
		return apellido_2;
	}

	public void setApellido_2(String apellido_2) 
	{
		this.apellido_2 = apellido_2;
	}

	public String getDocumento() 
	{
		return documento;
	}

	public void setDocumento(String documento) 
	{
		this.documento = documento;
	}

	public Date getFecha_nacimiento() 
	{
		return fechaNacimiento;
	}

	public void setFecha_nacimiento(Date fechaNacimiento) 
	{
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() 
	{
		return genero;
	}

	public void setGenero(String genero) 
	{
		this.genero = genero;
	}

	public String getTelefono() 
	{
		return telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
}
