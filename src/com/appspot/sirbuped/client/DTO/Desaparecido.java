package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Desaparecido implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
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
	private Date fechaNacimiento;
	
	@Persistent
	private byte edad;
	
	@Persistent
	private boolean genero;
	
	@Persistent
	private String estatura;
	
	@Persistent
	private String peso;
	
	@Persistent
	private String keyFoto;
	
	@Persistent
	private Date fechaRegistro;
	
	@Persistent
	private ArrayList<Morfologia> morfologia = new ArrayList<Morfologia>();
	
	@Persistent
	private ArrayList<SenalParticular> senalParticular = new ArrayList<SenalParticular>();
	
	/*@Persistent
	private ArrayList<PrendaVestir> prendaVestir = new ArrayList<PrendaVestir>();*/
	
	@Persistent
	private DatoDesaparicion datoDesaparicion;
	
	public Desaparecido() 
	{
		
	}
	
	public Desaparecido(String id, String nombre1, String nombre2, String apellido1, String apellido2, String tipoDocumento,
						String numeroDocumento, Date fechaNacimiento, byte edad, boolean genero, String estatura, String peso, 
						String keyFoto, Date fechaRegistro, ArrayList<Morfologia> morfologia, ArrayList<SenalParticular> senalParticular,
						DatoDesaparicion datoDesaparicion) 
	{
		this.id = id;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.genero = genero;
		this.estatura = estatura;
		this.peso = peso;
		this.keyFoto = keyFoto;
		this.fechaRegistro = fechaRegistro;
		this.morfologia = morfologia;
		this.senalParticular = senalParticular;
		this.datoDesaparicion = datoDesaparicion;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public static long getSerialversionuid() 
	{
		return serialVersionUID;
	}

	public String getNombre1() 
	{
		return nombre1;
	}
	
	public void setNombre1(String nombre1) 
	{
		this.nombre1 = nombre1;
	}

	public String getNombre2() 
	{
		return nombre2;
	}
	
	public void setNombre2(String nombre2) 
	{
		this.nombre2 = nombre2;
	}

	public String getApellido1() 
	{
		return apellido1;
	}

	public void setApellido1(String apellido1) 
	{
		this.apellido1 = apellido1;
	}

	public String getApellido2() 
	{
		return apellido2;
	}

	public void setApellido2(String apellido2) 
	{
		this.apellido2 = apellido2;
	}

	public String getTipoDocumento() 
	{
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) 
	{
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() 
	{
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) 
	{
		this.numeroDocumento = numeroDocumento;
	}

	public Date getFechaNacimiento() 
	{
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) 
	{
		this.fechaNacimiento = fechaNacimiento;
	}

	public byte getEdad() 
	{
		return edad;
	}

	public void setEdad(byte edad) 
	{
		this.edad = edad;
	}

	public boolean getGenero() 
	{
		return genero;
	}

	public void setGenero(boolean genero) 
	{
		this.genero = genero;
	}

	public String getEstatura() 
	{
		return estatura;
	}

	public void setEstatura(String estatura) 
	{
		this.estatura = estatura;
	}

	public String getPeso() 
	{
		return peso;
	}

	public void setPeso(String peso) 
	{
		this.peso = peso;
	}
	
	public String getKeyFoto() 
	{
		return keyFoto;
	}

	public void setKeyFoto(String keyFoto) 
	{
		this.keyFoto = keyFoto;
	}
	
	public Date getFechaRegistro() 
	{
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) 
	{
		this.fechaRegistro = fechaRegistro;
	}

	public ArrayList<Morfologia> getMorfologia() 
	{
		return morfologia;
	}

	public void setMorfologia(ArrayList<Morfologia> morfologia) 
	{
		this.morfologia = morfologia;
	}
	
	public ArrayList<SenalParticular> getSenalParticular() 
	{
		return senalParticular;
	}

	public void setSenalParticular(ArrayList<SenalParticular> senalParticular) 
	{
		this.senalParticular = senalParticular;
	}
	/*
	public ArrayList<PrendaVestir> getPrendaVestir() 
	{
		return prendaVestir;
	}

	public void setPrendaVestir(ArrayList<PrendaVestir> prendaVestir) 
	{
		this.prendaVestir = prendaVestir;
	}
	*/
	public DatoDesaparicion getDatoDesaparicion() 
	{
		return datoDesaparicion;
	}

	public void setDatoDesaparicion(DatoDesaparicion datoDesaparicion) 
	{
		this.datoDesaparicion = datoDesaparicion;
	}
	
	@Override
	public String toString()
	{
		return (this.nombre1 + " \n " + this.nombre2 + " \n " + this.apellido1 + " \n " + this.apellido2 + " \n " +
				this.tipoDocumento + " \n " + this.numeroDocumento + " \n " + this.fechaNacimiento + " \n " + this.edad + " \n " +
				this.genero + " \n " + this.estatura + " \n " + this.peso + " \n " + this.getKeyFoto() + " \n " 
				+ this.getFechaRegistro() + " \n " + this.getMorfologia() + " \n " + this.getSenalParticular() + " \n " 
				+ this.getDatoDesaparicion());
	}
}
