package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.DTO.Mensaje;
import com.appspot.sirbuped.client.Interfaz.MensajeService;
import com.appspot.sirbuped.client.Interfaz.MensajeServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class VistaMensaje extends Composite
{
	
	public VistaMensaje()
	{
		final HTMLPanel subContent = new HTMLPanel(" ");
		subContent.setStyleName("subContent div-buzon");
		subContent.getElement().setId("mensajes");
		
		final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		RootPanel.get("content").add(cargando);
		
		MensajeServiceAsync mensajeService = GWT.create(MensajeService.class);
		mensajeService.consultarMensajes( new AsyncCallback<ArrayList<Mensaje>>() 
		{
			public void onSuccess(ArrayList<Mensaje> msj) 
			{
				cargando.getElement().setAttribute("style", "display:none");
				
				
				for(byte i = 0; i < msj.size(); i++)
				{
					final Mensaje m = msj.get(i);
					HTMLPanel mensaje = new HTMLPanel("");	
					mensaje.setStyleName("mensaje");
					
					HTMLPanel nombre=new HTMLPanel(m.getNombre());
					nombre.setStyleName("nombre");
					HTMLPanel asunto=new HTMLPanel(m.getAsunto());
					asunto.setStyleName("asunto");
					
					DateTimeFormat format = DateTimeFormat.getFormat("dd 'de' MMMM HH:mm");
					String date = format.format(m.getFechaRegistro());
					
					HTMLPanel fecha=new HTMLPanel(date);
					fecha.setStyleName("fecha");
					
					final HTMLPanel contenido = new HTMLPanel(m.getMensaje());
					contenido.setStyleName("cont-mensaje");
					contenido.setVisible(false);
					
					HTML zonaClick = new HTML("<img src='image/mas.jpg' />");
					zonaClick.setStyleName("verMas");
					
					
					mensaje.add(nombre);
					mensaje.add(asunto);
					mensaje.add(fecha);
					mensaje.add(zonaClick);
					mensaje.add(contenido);
					
					subContent.add(mensaje);
					
					zonaClick.addClickHandler(new ClickHandler() 
					{
						public void onClick(final ClickEvent event) 
						{
							if(contenido.isVisible())
								contenido.setVisible(false);
							
							else
								contenido.setVisible(true);
			        	}
					});
				}
			}
			
			public void onFailure(Throwable error) 
			{
				HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron mensajes asociados a su cuenta.</p>");
	    		sinResultados.setStyleName("sinResultados");
	    		RootPanel.get("mensajes").add(sinResultados);
	    		cargando.getElement().setAttribute("style", "display:none");
			}
		});
		
		initWidget(subContent);
	}
}
