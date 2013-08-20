package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Pista;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PistaServiceAsync {

	
	void registrar(Pista pista, String id, AsyncCallback<Void> callback);
	void  getPistasEnviadas(AsyncCallback<ArrayList<Pista>> callback);
	void  getPistasRecibidas(AsyncCallback<ArrayList<Desaparecido>> callback);
	void getusuarioPista(String id, AsyncCallback<String> callback);
}
