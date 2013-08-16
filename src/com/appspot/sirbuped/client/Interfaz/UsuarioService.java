package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.LoginInfo;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usuario")
public interface UsuarioService extends RemoteService 
{
	public void addUsuario(Usuario nuevo);
	public Usuario iniciarSesion(Usuario usuario);
	public void cerrarSesion();
	public String getSesion(String key);
	public LoginInfo loginGoogle(String requestUri);
	public Usuario getUsuario();
	public void editarUsuario(Usuario editado);
	public ArrayList<Desaparecido> getDesaparecidosUsuario();
	public boolean validarEmail(String email);
}
