package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class VistaDesaparecidoDepartamento extends Composite
{
	public VistaDesaparecidoDepartamento(String departamento)
	{
		final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		RootPanel.get("verDesaparecido").add(cargando);
		
		DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
		desaparecidoService.getDesaparecidosDpto(departamento, 0, new AsyncCallback<ArrayList<Desaparecido>>() 
		{
			
			public void onSuccess(final ArrayList<Desaparecido> results) 
			{
				cargando.setVisible(false);
				RootPanel.get("verDesaparecido").add(new VistaDesaparecidoDetallar().mostarTodos(results));
			}
			
			public void onFailure(Throwable error) 
			{
				new Utilidades().ventanaModal("Error", "En este momento no fue posible consultar la base de datos. Intente nuevamente.", "error");
			}
		});
	}
}
