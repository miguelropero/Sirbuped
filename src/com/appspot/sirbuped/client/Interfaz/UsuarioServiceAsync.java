package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.LoginInfo;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UsuarioServiceAsync 
{
	public void addUsuario(Usuario nuevo, AsyncCallback<Void> async);
	public void iniciarSesion(Usuario usuario, AsyncCallback<Usuario> async);
	public void cerrarSesion(AsyncCallback<Void> async);
	public void getSesion(String key, AsyncCallback<String> async);
	public void loginGoogle(String requestUri, AsyncCallback<LoginInfo> async);
	public void getUsuario(AsyncCallback<Usuario> async);
	public void editarUsuario(Usuario editado, AsyncCallback<Void> async);
	public void getDesaparecidosUsuario(AsyncCallback<ArrayList<Desaparecido>> async);
	public void validarEmail(String email, AsyncCallback<Boolean> async);
}
