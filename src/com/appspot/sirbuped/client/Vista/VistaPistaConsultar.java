package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Pista;
import com.appspot.sirbuped.client.Interfaz.PistaService;
import com.appspot.sirbuped.client.Interfaz.PistaServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class VistaPistaConsultar extends Composite
{
	public VistaPistaConsultar()
	{
		
		final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		RootPanel.get("content").add(cargando);
		
		final TabPanel tabPanel = new TabPanel();	
	
		final HTMLPanel subContent = new HTMLPanel(" ");
		subContent.setStyleName("div-buzon");
		subContent.setVisible(false);
		
		final HTMLPanel divRecibidas = new HTMLPanel(" ");
		final HTMLPanel divEnviadas = new HTMLPanel(" ");
		 
		
		/******Pistas Recibidas*******/
		PistaServiceAsync pistaService = GWT.create(PistaService.class);
		pistaService.getPistasRecibidas(new AsyncCallback<ArrayList<Desaparecido>>()
		{
			public void onSuccess(ArrayList<Desaparecido> desaparecidos) 
			{
				for(Desaparecido desaparecido : desaparecidos)
				{
					
					final HTMLPanel infoTitulo = new HTMLPanel("Persona Desaparecida: " + desaparecido.getNombre1() + " " + desaparecido.getApellido1());
					infoTitulo.setStyleName("personaDesaparecida");
					
					divRecibidas.add(infoTitulo);
					
					if(!desaparecido.getPistas().isEmpty())
					{
						for(Pista pista : desaparecido.getPistas())
						{
							HTMLPanel mensaje = new HTMLPanel("");	
							mensaje.setStyleName("mensaje");
							
							final HTMLPanel remitente = new HTMLPanel("");
							remitente.setStyleName("nombre");
							
							HTMLPanel usuario = new HTMLPanel(pista.getRemitente().getNombres() + " " + pista.getRemitente().getApellidos());
							remitente.add(usuario);
							
							DateTimeFormat format1 = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
							String fSuceso = format1.format(pista.getFechaSuceso());
							
							HTMLPanel fechaS = new HTMLPanel(fSuceso);
							fechaS.setStyleName("asunto");
							
							DateTimeFormat format = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
							String date = format.format(pista.getFechaRegistro());
							
							HTMLPanel fecha = new HTMLPanel(date);
							fecha.setStyleName("fecha");
							
							final HTMLPanel contenido = new HTMLPanel(pista.getMensaje());
							contenido.setStyleName("cont-mensaje");
							contenido.setVisible(false);
							
							final HTML zonaClick = new HTML("<img src='image/mas.jpg' />");
							zonaClick.setStyleName("verMas");
							
							mensaje.add(remitente);
							mensaje.add(fechaS);
							mensaje.add(fecha);
							mensaje.add(zonaClick);
							mensaje.add(contenido);
							
							divRecibidas.add(mensaje);
							
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
					}
					else
					{
						Label noPistas = new Label("La persona no tiene pistas registradas");
						divRecibidas.add(noPistas);
					}
					 
					cargando.getElement().setAttribute("style", "display:none");
					subContent.setVisible(true);
				}
			}
			public void onFailure(Throwable error) 
			{
				cargando.getElement().setAttribute("style", "display:none");
				new Utilidades().ventanaModal("Error", "No fue posible consultar las pistas recibidas. Intente nuevamente", "error");
			}
		});
		
		/***Pistas Enviadas**/
		pistaService.getPistasEnviadas( new AsyncCallback<ArrayList<Pista>>() 
		{
			public void onSuccess(ArrayList<Pista> pistas) 
			{
				if(pistas.size() > 0)
				{
					for(Pista pista :  pistas)
					{
						HTMLPanel mensaje = new HTMLPanel("");	
						mensaje.setStyleName("mensaje tab2");
						
						HTMLPanel nombre=new HTMLPanel(pista.getDesaparecido().getNombre1() + " " +pista.getDesaparecido().getApellido1());
						nombre.setStyleName("nombre");
						
						DateTimeFormat format1 = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
						String fSuceso= format1.format(pista.getFechaSuceso());
						
						HTMLPanel asunto=new HTMLPanel(fSuceso);
						asunto.setStyleName("asunto");
						
						DateTimeFormat format = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
						String date = format.format(pista.getFechaRegistro());
						
						HTMLPanel fecha = new HTMLPanel(date);
						fecha.setStyleName("fecha");
						
						final HTMLPanel contenido=new HTMLPanel(pista.getMensaje());
						contenido.setStyleName("cont-mensaje");
						contenido.setVisible(false);
						
						final HTML zonaClick = new HTML("<img src='image/mas.jpg' />");
						zonaClick.setStyleName("verMas");
						
						mensaje.add(nombre);
						mensaje.add(asunto);
						mensaje.add(fecha);
						mensaje.add(zonaClick);
						mensaje.add(contenido);
						
						divEnviadas.add(mensaje);
						
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
				}
				else
				{
					HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron resultados que coincidan con su b\u00FAsqueda.</p>");
					sinResultados.setStyleName("sinResultados");
					RootPanel.get("verDesaparecido").add(sinResultados);
				}
				
				cargando.getElement().setAttribute("style", "display:none");
				subContent.setVisible(true);
			}
			public void onFailure(Throwable error) 
			{
				cargando.getElement().setAttribute("style", "display:none");
				new Utilidades().ventanaModal("Error", "No fue posible consultar las pistas enviadas. Intente nuevamente", "error");
			}
		});
		 			 
		tabPanel.setAnimationEnabled(true);
		tabPanel.add(divRecibidas, "Pistas Recibidas", true);
		tabPanel.add(divEnviadas, "Pistas Enviadas", true);
		tabPanel.selectTab(0);
		 
		subContent.add(tabPanel);
		 
		initWidget(subContent);
	}	
}
