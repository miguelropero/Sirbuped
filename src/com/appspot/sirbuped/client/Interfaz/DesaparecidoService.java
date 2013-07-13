package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("desaparecido")
public interface DesaparecidoService extends RemoteService
{
	void ingresar(Desaparecido desaparecido);
	ArrayList<Desaparecido> consultar(boolean todos);
	Desaparecido consultar(String documento);
}
