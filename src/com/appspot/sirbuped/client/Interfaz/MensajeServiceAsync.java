package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Mensaje;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MensajeServiceAsync 
{	
	void addMensaje(Mensaje mensaje, AsyncCallback<Void> callback);
	void consultarMensajes(AsyncCallback<ArrayList<Mensaje>> callback);
}
