package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Ciudad;
import com.appspot.sirbuped.client.DTO.Departamento;
import com.appspot.sirbuped.client.DTO.Pais;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("lugar")
public interface LugarService extends RemoteService
{
	//void registrar(Desaparecido desaparecido);
	ArrayList<Pais> getPaises();
	ArrayList<Departamento> getDepartamentos(String pais);
	ArrayList<Ciudad> getCiudades(String pais, String dpto);
	void generarLugar();
	void deleteCiudad();
}
