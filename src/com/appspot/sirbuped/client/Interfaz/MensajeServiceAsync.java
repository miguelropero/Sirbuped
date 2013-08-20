package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Mensaje;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MensajeServiceAsync 
{	
	public void addMensaje(Mensaje mensaje, AsyncCallback<Void> callback);
	public void consultarMensajes(AsyncCallback<ArrayList<Mensaje>> callback);
}
