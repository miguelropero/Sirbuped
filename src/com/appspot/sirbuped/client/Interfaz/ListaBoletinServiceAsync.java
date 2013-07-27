package com.appspot.sirbuped.client.Interfaz;

import com.appspot.sirbuped.client.DTO.ListaBoletin;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ListaBoletinServiceAsync 
{
	public void addEmail(ListaBoletin nuevo, AsyncCallback<Void> async);
}
