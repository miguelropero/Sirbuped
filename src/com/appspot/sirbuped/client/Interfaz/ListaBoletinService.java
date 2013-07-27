package com.appspot.sirbuped.client.Interfaz;

import com.appspot.sirbuped.client.DTO.ListaBoletin;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("agregar-email")
public interface ListaBoletinService  extends RemoteService  
{
	public void addEmail(ListaBoletin nuevo);
}
