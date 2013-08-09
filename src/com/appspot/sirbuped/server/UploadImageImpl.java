package com.appspot.sirbuped.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.appspot.sirbuped.client.Interfaz.UploadImage;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UploadImageImpl extends RemoteServiceServlet implements UploadImage
{
	private static final long serialVersionUID = 1L;
	//private static final Logger log = Logger.getLogger(Desaparecido.class.getName());
	
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
		
		ImagesService imagesService = ImagesServiceFactory.getImagesService();

		Image oldImage = ImagesServiceFactory.makeImageFromBlob(blobKey);
		Transform resize = ImagesServiceFactory.makeResize(500, 198);
		
		Image newImage = imagesService.applyTransform(resize, oldImage);
		
		float porcentajeX = 0F;
		float porcentajeY = 0F;
		
		float dimension = 0F;
		float diferencia = 0F;
		
		if(newImage.getWidth() > 150)
		{
			dimension = newImage.getWidth();
			diferencia = dimension - 150;
			porcentajeX = (diferencia/dimension)/2;
		}
		else
		{
			Transform resize3 = ImagesServiceFactory.makeResize(150, 500);
			newImage = imagesService.applyTransform(resize3, oldImage);
			
			dimension = newImage.getHeight();
			diferencia = dimension - 198;
			
			if(diferencia > 0)
				porcentajeY = diferencia/dimension;
		}
		
		Transform resize2 = ImagesServiceFactory.makeCrop(porcentajeX, 0, (1 - porcentajeX), (1 - porcentajeY));
		
		Image newImage2 = imagesService.applyTransform(resize2, newImage);
		
		//byte [] newImagenData = newImage.getImageData();
        //blobstoreService.serve(blobKey, resp);
		resp.setContentType(newImage2.getFormat().toString());
        resp.getOutputStream().write(newImage2.getImageData());
		//resp.getWriter().println(newImage);
	}
}
