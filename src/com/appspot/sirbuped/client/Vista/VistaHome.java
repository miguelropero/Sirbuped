package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;

public class VistaHome extends Composite 
{
	HTMLPanel subContent 			= new HTMLPanel("");
	HTMLPanel banner 				= new HTMLPanel("");
	HTMLPanel enlaces 				= new HTMLPanel("");
	HTMLPanel ultimasDesapariciones = new HTMLPanel("<div class='separator' id='home' >" + "<div class='ver_todos'><a href='#consultar'>Ver todas</a></div>" + "</div><h2>\u00DAltimas Desapariciones</h2>");	
	HTMLPanel deInteres				= new HTMLPanel("");
	
	public VistaHome()
	{
		banner.setStyleName("banner");
		enlaces.setStyleName("enlaces-rapidos");
		deInteres.setStyleName("sugerencias");
		
		HTML contentBanner 			= new HTML("<ul class='bxslider'>" +
												"<li>" +
													"<a href='#nosotros'>" +
														"<img width='100%' src='image/banner/banner_1.png' />" +
													"</a>" +
												"</li>" +
												"<li>" +
													"<a href='#'>" +
									  					"<img width='100%' src='image/banner/banner_3.png' />" +
									  				"</a>" +
									  			"</li>" +
									  		"</ul>");
		
		HTML enlaceRegistrar		= new HTML("<div class='box-header-enlace'>" +
												"<h3>Registre un Desaparecido</h3>" +
	    									"</div>" +
	    									"<div class='content-enlace'>" +
	    										"<figure>" +
	    											"<img alt='Registrar Desaparecido' src='image/registrar_desaparecido.gif' align='left' >" +
	    											"<figcaption>" +
	    												"Sirbuped permite el registro de personas desaparecidas capturando los datos relevenates de la persona " +
	    												"los cuales son utilizados para filtar los resultados de b&uacute;squeda en nuestras Base de Datos." +
	    											"</figcaption>" +
	    										"</figure>" +
	    									"</div>" +
	    									"<div class='box-boton-enlace'>" +
	    										"<a href='#registrar-desaparecido'>Registrar</a>" +
	    									"</div>");
		
		HTML enlaceConsultar		= new HTML("<div class='box-header-enlace'>" +
												"<h3>Realice una B\u00FAsqueda</h3>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<img alt='Buscar una Persona Desaparecida' src='image/buscar_desaparecido.gif' align='left' >" +
													"<figcaption>" +
														"Sirbuped le ofrece un completo sistema de b&uacute;squeda de personas desaparecidas, permitiendo filtrar los " +
														"resultados por los nombres, apellidos, caracteristicas fisicas o morfologicas, prendas de vestir etc." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#consultar'>Consultar</a>" +
											"</div>");
		
		HTML enlaceColaborar		= new HTML("<div class='box-header-enlace'>" +
												"<h3>Colabore con Nosotros</h3>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<img alt='Colabore con nosotros' src='image/colabore_con_nosotros.gif' align='left' >" +
													"<figcaption>" +
														"Si desea colaborar con nosotros, Sirbuped le ofrece tres opciones, registrando pistas de una persona " +
														"desaparecida, recibiendo nuestro boletines mensuales o generando e imprimiendo carteles." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#colaborar'>Colaborar</a>" +
											"</div>");
		
		
		
		HTMLPanel contentDeInteres	= new HTMLPanel("<div class='separator'></div>" +
				   					   				"<h2>Informaci\u00F3n de Inter\u00E9s</h2>");
		
		HTML comoActuar 			= new HTML("<a href='#'>" +
													"<h3>\u00BFC\u00F3mo Actuar?</h3>" +
													"<p>" +
														"Si usted se encuentra ante un caso de desaparici\u00F3n, debe tener en cuenta ciertos pasos que " +
														"le ayudaran en su b\u00FAsqueda. <span>Conozcalos aqu\u00ED.</span>" +
													"</p>" +
												"</a>");
		
		HTML requisitos 			= new HTML("<a href='#'>" +
													"<h3>Requisitos</h3>" +
													"<p>" +
														"Para realizar el registro de una persona desaparecida en el sistema, Sirbuped establece una serie de " + 
														"requisitos para su publicaci\u00F3n . <span>Ver requisitos.</span>" +
													"</p>" +
												"</a>");
		
		HTML avisoLegal 			= new HTML("<a href='#'>" +
													"<h3>Aviso Legal</h3>" +
													"<p>" +
														"Importante gu\u00EDa que le indica como actuar en caso de desaparici\u00F3n de un familiar o ser quierido. " + 
														"Importante gu\u00EDa que le indica como actuar. <span>Leer mas.</span>" +
													"</p>" +
												"</a>");
		
		HTML contactenos 			= new HTML("<a href='#contactenos'>" +
													"<h3>Cont\u00E1ctenos</h3>" +
													"<p>" +
														"Si tiene alguna comentario, pregunta o sugerencia acerca de nuestros servicios, no dude en ponerse " + 
														"en contacto con nosotros. <span>Enviar mensaje.</span>" +
													"</p>" +
												"</a>");
		
		
		
    	ultimasDesapariciones.setStyleName("verDesaparecido");
    	
    	final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		ultimasDesapariciones.add(cargando);
    	
		DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
		desaparecidoService.consultar(false, new AsyncCallback<ArrayList<Desaparecido>>()
		{
		    public void onSuccess(ArrayList<Desaparecido> desaparecidos) 
		    {
		    	if(desaparecidos != null)
		    	{
		    	
			    	for(byte i=0; i < desaparecidos.size(); i++)
			    	{
			    		final Desaparecido desaparecido = desaparecidos.get(i);
			    		HTMLPanel figure = new HTMLPanel("");
			    		HTMLPanel divImagen = new HTMLPanel("");
			    		figure.setStyleName("figure");
			    		
			    		Image image = new Image();
			    		image.setUrl(desaparecidos.get(i).getKeyFoto()); 
			    		
			    		HTML figcaption = new HTML(desaparecidos.get(i).getNombre1() + " " +desaparecidos.get(i).getNombre2() + " " + 
			    								   desaparecidos.get(i).getApellido1() + " " + desaparecidos.get(i).getApellido2());
			    		
			    		figcaption.setStyleName("figcaption");
			    		
			    		HTML zonaClick = new HTML();
			    		zonaClick.setStyleName("zonaClick");
			    		
			    		divImagen.add(image);
			    		figure.add(zonaClick);
			    		figure.add(divImagen);
			    		figure.add(figcaption);
			    		ultimasDesapariciones.add(figure);
			    		
			    		zonaClick.addClickHandler(new ClickHandler() 
			    		{
			    			public void onClick(final ClickEvent event) 
			    			{
			    				subContent.getElement().setAttribute("style", "display:none");
								History.newItem(desaparecido.getNumeroDocumento());
								//RootPanel.get("content").add(new VistaConsultarDesaparecido(desaparecido));
			    			}
			    		});
			    	}
		    	}
		    	cargando.getElement().setAttribute("style", "display:none");
		    }
		    public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			}
        });
		
		contentDeInteres.add(comoActuar);
		contentDeInteres.add(requisitos);
		contentDeInteres.add(avisoLegal);
		contentDeInteres.add(contactenos);
		deInteres.add(contentDeInteres);
		
		banner.add(contentBanner);
		enlaces.add(enlaceRegistrar);
		enlaces.add(enlaceConsultar);
		enlaces.add(enlaceColaborar);
		
		subContent.add(banner);
		subContent.add(enlaces);
		subContent.add(ultimasDesapariciones);
		subContent.add(deInteres);
		
		initWidget(subContent);
	}
}
