package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Pista;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("pista")
public interface PistaService extends RemoteService
{
	public void registrar(Pista pista, String id);
	public ArrayList<Pista>  getPistasEnviadas();
	public ArrayList<Desaparecido>getPistasRecibidas();
}
