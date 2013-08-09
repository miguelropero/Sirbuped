package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("desaparecido")
public interface DesaparecidoService extends RemoteService
{
	void registrar(Desaparecido desaparecido);
	ArrayList<Desaparecido> getDesaparecidos(byte cantidad);
	Desaparecido getDesaparecido(String documento);
	ArrayList<Desaparecido> consultarDesaparecido(Desaparecido desaparecido);
	ArrayList<String> mapaDesaparecidos();
	ArrayList<Desaparecido> getDesaparecidosDpto(String departamento);
}
