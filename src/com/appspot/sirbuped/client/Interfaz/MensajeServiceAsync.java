package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Mensaje;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MensajeServiceAsync 
{	
	public void addMensaje(Mensaje mensaje, AsyncCallback<Void> callback);
	public void consultarMensajes(AsyncCallback<ArrayList<Mensaje>> callback);
	public void actualizarEstado(String id, AsyncCallback<Void> callback);
	public void responderMensaje(String email, String mensaje, AsyncCallback<Void> callback);
}
