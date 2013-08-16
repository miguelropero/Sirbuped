package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DesaparecidoServiceAsync 
{
	void registrar(Desaparecido desaparecido, AsyncCallback<Void> callback);
	void getDesaparecidos(byte cantidad, AsyncCallback<ArrayList<Desaparecido>> asyncCallback);
	void getDesaparecido(String documento, AsyncCallback<Desaparecido> callback);
	void consultarDesaparecido(Desaparecido desaparecido, AsyncCallback<ArrayList<Desaparecido>> callback);
	void mapaDesaparecidos(AsyncCallback<ArrayList<String>> callback);
	void getDesaparecidosDpto(String departamento, AsyncCallback<ArrayList<Desaparecido>> asyncCallback);
	void actualizarDesaparecido(Desaparecido desaparecido, AsyncCallback<Void> callback);
	void validarDesaparecidoUsuario(String keyDesaparecido, AsyncCallback<Desaparecido> callback);
	
	/* estadisticas */
	void consultarGenero(AsyncCallback<String> asyncCallback);
	void consultarDesaparecidoEdad(AsyncCallback<ArrayList<String>> asyncCallback);
	void consultarDesaparecidoAnio(AsyncCallback<ArrayList<String>> asyncCallback);
}
