package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Mensaje;
import com.appspot.sirbuped.client.Interfaz.MensajeService;
import com.appspot.sirbuped.client.Interfaz.MensajeServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;

public class VistaMensaje extends Composite
{
	public VistaMensaje()
	{
		
		final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		RootPanel.get("content").add(cargando);
		
		final HTMLPanel subContent = new HTMLPanel(" ");
		subContent.setStyleName("subContent div-buzon");
		subContent.getElement().setId("mensajes");
		subContent.setVisible(false);
		
		final HTMLPanel divContenido = new HTMLPanel("");
		final HTMLPanel divContenido2 = new HTMLPanel("");
		
		HTMLPanel divBotones = new HTMLPanel(" ");
		final Button btnEliminar = new Button("Eliminar");
		
		
		TabPanel tabPanel = new TabPanel();	
		divBotones.add(btnEliminar);
		 
		final ArrayList<CheckBox> idMensajes = new ArrayList<CheckBox>();
		
		MensajeServiceAsync mensajeService = GWT.create(MensajeService.class);
		mensajeService.consultarMensajes( new AsyncCallback<ArrayList<Mensaje>>() 
		{
			public void onSuccess(ArrayList<Mensaje> msj) 
			{
				for(byte i = 0; i < msj.size(); i++)
				{
					final Mensaje mensaje = msj.get(i);
					HTMLPanel divMensaje = new HTMLPanel("");	
					divMensaje.setStyleName("mensaje");
					
					CheckBox check = new CheckBox();
					check.getElement().setAttribute("id", String.valueOf(mensaje.getId()));
					idMensajes.add(check);
					
					HTMLPanel nombre = new HTMLPanel(mensaje.getNombre());
					nombre.setStyleName("nombre");
					
					HTMLPanel asunto = new HTMLPanel(mensaje.getAsunto());
					asunto.setStyleName("asunto");
					
					DateTimeFormat format = DateTimeFormat.getFormat("dd 'de' MMMM HH:mm");
					String date = format.format(mensaje.getFechaRegistro());
					
					HTMLPanel fecha = new HTMLPanel(date);
					fecha.setStyleName("fecha");
					
					final HTMLPanel contenido = new HTMLPanel("Remitente: " + mensaje.getEmail() + "<br /><br />" + mensaje.getMensaje());
					contenido.setStyleName("cont-mensaje");
					contenido.setVisible(false);
					
					final TextArea areaMensaje = new TextArea();
					final Button btnResponder = new Button("Responder");
					areaMensaje.getElement().setAttribute("placeHolder", "Responder mensaje...");
					
					final HTML zonaClick = new HTML("<img src='image/mas.jpg' />");
					zonaClick.setStyleName("verMas");
					
					if(mensaje.getEstado())
	        		{
						contenido.add(areaMensaje);
						contenido.add(btnResponder);
						divMensaje.add(check);
						divMensaje.add(nombre);
						divMensaje.add(asunto);
						divMensaje.add(fecha);
						divMensaje.add(zonaClick);
						divMensaje.add(contenido);
						
		    			
						
		    	      divContenido.add(divMensaje);

		    	      btnResponder.addClickHandler(new ClickHandler() 
		    	      {
		    	    	  public void onClick(ClickEvent event) 
		    	    	  {
		    	    		  final HTMLPanel cargando = new HTMLPanel("");
		    	    		  cargando.setStyleName("cargando");
		    	    		  RootPanel.get("content").add(cargando);
		    	    		  subContent.setVisible(false);
		    					
		    	    		  MensajeServiceAsync mensajeService = GWT.create(MensajeService.class);
		    	    		  mensajeService.responderMensaje(mensaje.getEmail(), areaMensaje.getText(), new AsyncCallback<Void>()
		    	    		  {
		    	    			  public void onSuccess(Void ignore) 
		    	    			  {
		    	    				  cargando.getElement().setAttribute("style", "display:none");
		    	    				  subContent.setVisible(true);
		    	    				  areaMensaje.setText("");
		    	    				  
		    	    				  new Utilidades().ventanaModal("Envio correcto", "El Mensaje se ha Enviado Correctamente ", "Exito");
		    	    			  }
		    									
		    	    			  public void onFailure(Throwable error) 
		    	    			  {
		    	    				  cargando.getElement().setAttribute("style", "display:none");
		    	    				  subContent.setVisible(true);
		    	    				  new Utilidades().ventanaModal("Error", error.toString() , "error");
		    	    			  }
		    	    		  });
		    	    	  }
		    	      });
	        		}
	        		
	        		else
	        		{
	        			divMensaje.setStyleName("mensaje tab2");
	        			
	        			divMensaje.add(nombre);
	        			divMensaje.add(asunto);
	        			divMensaje.add(fecha);
	        			divMensaje.add(zonaClick);
	        			divMensaje.add(contenido);
	        			
		    			divContenido2.add(divMensaje);
	        		}
					
					zonaClick.addClickHandler(new ClickHandler() 
					{
						public void onClick(final ClickEvent event) 
						{
							if(contenido.isVisible())
							{
								contenido.setVisible(false);
								zonaClick.setHTML("<img src='image/mas.jpg' />");
							}
							else
							{
								contenido.setVisible(true);
								zonaClick.setHTML("<img src='image/menos.jpg' />");
							}
			        	}
					});
				}
				
				cargando.getElement().setAttribute("style", "display:none");
				subContent.setVisible(true);
			}
			
			public void onFailure(Throwable error) 
			{
				HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron mensajes asociados a su cuenta.</p>");
	    		sinResultados.setStyleName("sinResultados");
	    		RootPanel.get("mensajes").add(sinResultados);
	    		
	    		cargando.getElement().setAttribute("style", "display:none");
				subContent.setVisible(true);
			}
		});
		
		tabPanel.setAnimationEnabled(true);
		tabPanel.add(divContenido, "Recibidos", true);
		tabPanel.add(divContenido2,"Papelera", true);
		tabPanel.selectTab(0);
		 
		subContent.add(tabPanel);
		
		subContent.add(divBotones);
		 
		btnEliminar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				subContent.setVisible(false);
						
				if(!idMensajes.isEmpty())
				{
					for(int i=0;i<idMensajes.size();i++)
					{
						if(idMensajes.get(i).getValue())
						{
							MensajeServiceAsync mensajeService = GWT.create(MensajeService.class);
							mensajeService.actualizarEstado(idMensajes.get(i).getElement().getAttribute("id"), new AsyncCallback<Void>() 
							{
								public void onSuccess(Void ignore) 
								{
									cargando.getElement().setAttribute("style", "display:none");
									subContent.setVisible(true);
									
									new Utilidades().ventanaModal("Mensaje Exitoso", "El Mensaje se ha Eliminado ", "Exito");
								}
								
								public void onFailure(Throwable error) 
								{
									cargando.getElement().setAttribute("style", "display:none");
									subContent.setVisible(true);
									new Utilidades().ventanaModal("Error", error.toString() , "error");
								}
							});
						}
					}
				}
			}
		});
		
		
		initWidget(subContent);
	}
}
