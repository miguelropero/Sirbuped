package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DesaparecidoServiceAsync 
{
	public void registrar(Desaparecido desaparecido, AsyncCallback<String> callback);
	public void getDesaparecidos(byte cantidad, AsyncCallback<ArrayList<Desaparecido>> asyncCallback);
	public void getDesaparecido(String documento, AsyncCallback<Desaparecido> callback);
	public void consultarDesaparecido(Desaparecido desaparecido, AsyncCallback<ArrayList<Desaparecido>> callback);
	public void mapaDesaparecidos(AsyncCallback<ArrayList<String>> callback);
	public void getDesaparecidosDpto(String departamento, int cantidad, AsyncCallback<ArrayList<Desaparecido>> asyncCallback);
	public void actualizarDesaparecido(Desaparecido desaparecido, AsyncCallback<Void> callback);
	public void validarDesaparecidoUsuario(String keyDesaparecido, AsyncCallback<Desaparecido> callback);
	
	/* estadisticas */
	public void consultarGenero(AsyncCallback<String> asyncCallback);
	public void consultarDesaparecidoEdad(AsyncCallback<ArrayList<String>> asyncCallback);
	public void consultarDesaparecidoAnio(AsyncCallback<ArrayList<String>> asyncCallback);
}
