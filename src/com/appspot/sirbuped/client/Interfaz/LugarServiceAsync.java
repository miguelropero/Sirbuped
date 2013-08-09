package com.appspot.sirbuped.client.Interfaz;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Ciudad;
import com.appspot.sirbuped.client.DTO.Departamento;
import com.appspot.sirbuped.client.DTO.Pais;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LugarServiceAsync 
{
	void getPaises(AsyncCallback<ArrayList<Pais>> asyncCallback);
	void getDepartamentos(String pais, AsyncCallback<ArrayList<Departamento>> asyncCallback);
	void getCiudades(String pais, String dpto, AsyncCallback<ArrayList<Ciudad>> asyncCallback);
	void generarLugar(AsyncCallback<Void> callback);
	void deleteCiudad(AsyncCallback<Void> asyncCallback);
}
