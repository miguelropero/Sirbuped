package com.appspot.sirbuped.client.Interfaz;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("uploadImagen")
public interface UploadImage extends RemoteService
{
	String getBlobStoreUploadUrl();
}
