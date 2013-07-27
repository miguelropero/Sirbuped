package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@SuppressWarnings("serial")
@PersistenceCapable
public class Hijo implements Serializable
{
	@Persistent
	private String nombre;
	
	@Persistent
	private String tipo;
	
	@Persistent
	private String caracteristica;

	public Hijo(String nombre, String tipo, String caracteristica) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.caracteristica = caracteristica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	

}
