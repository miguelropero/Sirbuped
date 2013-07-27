package com.appspot.sirbuped.client.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L; 
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String nombres;
	
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
	private String email;
	
	@Persistent
	private String telefono1;
	
	@Persistent
	private String telefono2;
	
	@Persistent
	private String telefonoCel;
	
	@Persistent
	private String direccion;
	
	@Persistent
	private String password;
	
	@Persistent
	private boolean estado;
	
	@NotPersistent
	private String keySesion;
	
	@Persistent
	ArrayList<Desaparecido> desaparecidos = new ArrayList<Desaparecido>();
	
	public Usuario()
	{
		desaparecidos = new ArrayList<Desaparecido>();
	}

	public Usuario(String nombres, String apellido1, String apellido2, String tipoDocumento, String numeroDocumento, 
				   Date fechaNacimiento, String email, String telefono1, String telefono2, String telefonoCel, String direccion, 
				   String password) 
	{
		this.nombres = nombres;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.telefonoCel = telefonoCel;
		this.direccion = direccion;
		this.password = password;
		this.estado = false;
		desaparecidos = new ArrayList<Desaparecido>();
	}
	
	public String getKey() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getNombres() 
	{
		return nombres;
	}

	public void setNombres(String nombres) 
	{
		this.nombres = nombres;
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

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getTelefono1() 
	{
		return telefono1;
	}

	public void setTelefono1(String telefono1) 
	{
		this.telefono1 = telefono1;
	}

	public String getTelefono2() 
	{
		return telefono2;
	}

	public void setTelefono2(String telefono2) 
	{
		this.telefono2 = telefono2;
	}

	public String getTelefonoCel() 
	{
		return telefonoCel;
	}

	public void setTelefonoCel(String telefonoCel) 
	{
		this.telefonoCel = telefonoCel;
	}

	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public boolean getEstado() 
	{
		return estado;
	}

	public void setEstado(boolean estado) 
	{
		this.estado = estado;
	}
	
	public String getKeySesion() 
	{
		return keySesion;
	}

	public void setKeySesion(String keySesion) 
	{
		this.keySesion = keySesion;
	}

	
	
	public ArrayList<Desaparecido> getDesaparecidos() {
		return desaparecidos;
	}

	public void setDesaparecidos(ArrayList<Desaparecido> desaparecidos) {
		this.desaparecidos = desaparecidos;
	}

	@Override
	public String toString() {
		return "Usuario [nombres=" + nombres + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", tipoDocumento="
				+ tipoDocumento + ", numeroDocumento=" + numeroDocumento
				+ ", fechaNacimiento=" + fechaNacimiento + ", email=" + email
				+ ", telefono1=" + telefono1 + ", telefono2=" + telefono2
				+ ", telefonoCel=" + telefonoCel + ", direccion=" + direccion
				+ ", password=" + password + ", estado=" + estado
				+ ", keySesion=" + keySesion + "]";
	}

	
	
}
