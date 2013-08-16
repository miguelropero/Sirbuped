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
	private String apellidos;
	
	@Persistent
	private String tipoDocumento;
	
	@Persistent
	private String numeroDocumento;
	
	@Persistent
	private Date fechaNacimiento;
	
	@Persistent
	private String email;
	
	@Persistent
	private String telefono;
	
	@Persistent
	private String telefonoCel;
	
	@Persistent
	private String direccion;
	
	@Persistent
	private String password;
	
	@Persistent
	private boolean estado;
	
	@Persistent
	private String keyCiudadResidencia;
	
	@NotPersistent
	private Ciudad ciudadResidencia;
	
	@Persistent
	private ArrayList<Desaparecido> desaparecidos = new ArrayList<Desaparecido>();
	
	public Usuario()
	{
		desaparecidos = new ArrayList<Desaparecido>();
	}

	public Usuario(String nombres, String apellidos, String tipoDocumento, String numeroDocumento, Date fechaNacimiento, 
				   String email, String telefono, String telefonoCel, String direccion, String password) 
	{
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.telefono = telefono;
		this.telefonoCel = telefonoCel;
		this.direccion = direccion;
		this.password = password;
		this.estado = false;
		desaparecidos = new ArrayList<Desaparecido>();
	}
	
	public String getId() 
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

	public String getApellidos() 
	{
		return apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
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

	public String getTelefono() 
	{
		return telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
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
	
	public String getKeyCiudadResidencia() 
	{
		return keyCiudadResidencia;
	}

	public void setKeyCiudadResidencia(String keyCiudadResidencia) 
	{
		this.keyCiudadResidencia = keyCiudadResidencia;
	}

	public Ciudad getCiudadResidencia() 
	{
		return ciudadResidencia;
	}

	public void setCiudadResidencia(Ciudad ciudadResidencia) 
	{
		this.ciudadResidencia = ciudadResidencia;
	}

	public ArrayList<Desaparecido> getDesaparecidos() 
	{
		return desaparecidos;
	}

	public void setDesaparecidos(ArrayList<Desaparecido> desaparecidos) 
	{
		this.desaparecidos = desaparecidos;
	}

	@Override
	public String toString() 
	{
		return "Usuario [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos 
				+ ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento 
				+ ", fechaNacimiento=" + fechaNacimiento + ", email=" + email + ", telefono="
				+ telefono + ", telefonoCel=" + telefonoCel + ", direccion=" + direccion 
				+ ", password=" + password + ", estado=" + estado + ", desaparecidos=" + desaparecidos + "]";
	}
}
