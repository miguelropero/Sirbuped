package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Otro implements Serializable 
{	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;
	
	@Persistent
	private String nombre1;
	
	@Persistent
	private String nombre2;
	
	@Persistent
	private String apellido1;
	
	@Persistent
	private String apellido2;
	
	@Persistent
	private String tipoDocumento;
	
    @Persistent
	private String numeroDocumento;
    
    @Persistent
	private ArrayList<Hijo> hijos = new ArrayList<Hijo>();

	public Otro(String nombre1, String nombre2, String apellido1,
			String apellido2, String tipoDocumento, String numeroDocumento) 
	{
		super();
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public ArrayList<Hijo> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Hijo> hijos) {
		this.hijos = hijos;
	}
    
    
}
