package com.appspot.sirbuped.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class UploadBlobImagen extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	//Override the doPost method to store the Blob's meta-data
	@SuppressWarnings("deprecation")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		//Start Blobstore
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("foto");
		
		//Redirect recursively to this servlet (calls doGet)
		res.sendRedirect("/sirbuped/uploadBlobImagen?id=" + blobKey.getKeyString());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		//Send the meta-data id back to the client in the HttpServletResponse response
		String id = req.getParameter("id");
		resp.setHeader("Content-Type", "text/html");
		resp.getWriter().println(id);
	}
}
