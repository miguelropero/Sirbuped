package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("desaparecido")
public interface DesaparecidoService extends RemoteService
{
	public String registrar(Desaparecido desaparecido);
	public ArrayList<Desaparecido> getDesaparecidos(byte cantidad);
	public Desaparecido getDesaparecido(String documento);
	public ArrayList<Desaparecido> consultarDesaparecido(Desaparecido desaparecido);
	public ArrayList<String> mapaDesaparecidos();
	public ArrayList<Desaparecido> getDesaparecidosDpto(String departamento, int cantidad);
	public void actualizarDesaparecido(Desaparecido actualizado);
	public Desaparecido validarDesaparecidoUsuario(String keyDesaparecido);
	
	/* estadisticas */
	public String consultarGenero();
	public ArrayList<String> consultarDesaparecidoEdad();
	public ArrayList<String> consultarDesaparecidoAnio();
}
