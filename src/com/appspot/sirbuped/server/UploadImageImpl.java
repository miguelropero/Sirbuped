package com.appspot.sirbuped.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appspot.sirbuped.client.Interfaz.UploadImage;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UploadImageImpl extends RemoteServiceServlet implements UploadImage
{
	private static final long serialVersionUID = 1L;
	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	//Generate a Blobstore Upload URL from the GAE BlobstoreService
	
	public String getBlobStoreUploadUrl() 
	{
		//Map the UploadURL to the uploadservice which will be called by
		//submitting the FormPanel
		return blobstoreService.createUploadUrl("/sirbuped/uploadBlobImagen");
	}
	
	//Override doGet to serve blobs.  This will be called automatically by the Image Widget
	//in the client
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        blobstoreService.serve(blobKey, resp);
	}
}
