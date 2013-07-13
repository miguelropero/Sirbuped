package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DesaparecidoServiceAsync 
{
	void ingresar(Desaparecido desaparecido, AsyncCallback<Void> callback);
	void consultar(boolean todos, AsyncCallback<ArrayList<Desaparecido>> asyncCallback);
	void consultar(String documento, AsyncCallback<Desaparecido> callback);
}
