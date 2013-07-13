package com.appspot.sirbuped.client.Interfaz;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UploadImageAsync 
{
	void getBlobStoreUploadUrl(AsyncCallback<String> callback);
}
