package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Mensaje;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mensaje")
public interface MensajeService extends RemoteService
{
	public void addMensaje(Mensaje mensaje);
	public ArrayList<Mensaje> consultarMensajes();
	public void actualizarEstado(String id);
	public void responderMensaje(String email, String mensaje);
}
