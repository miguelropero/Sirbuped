package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
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
	HTMLPanel ultimasDesapariciones = new HTMLPanel("<div class='separator' id='home' >" + "<div class='ver_todos'><a href='#personas-desaparecidas'>Ver todas</a></div>" + "</div><h2>\u00DAltimas Desapariciones</h2>");	
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
												"<h4>Registre un Desaparecido</h4>" +
	    									"</div>" +
	    									"<div class='content-enlace'>" +
	    										"<figure>" +
	    											"<div><img alt='Registrar Desaparecido' src='image/registrar_desaparecido.png' ></div>" +
	    											"<figcaption>" +
	    												"Regitre una persona desaparecida y permita que cientos de personas contribuyan a su busqueda." +
	    											"</figcaption>" +
	    										"</figure>" +
	    									"</div>" +
	    									"<div class='box-boton-enlace'>" +
	    										"<a href='#registrar-desaparecido'>Registrar</a>" +
	    									"</div>");
		
		HTML enlaceConsultar		= new HTML("<div class='box-header-enlace'>" +
												"<h4>Realice una B\u00FAsqueda</h4>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<div><img alt='Buscar una Persona Desaparecida' src='image/buscar_desaparecido.png' ></div>" +
													"<figcaption>" +
														"Realice la busqueda de una persona desaparecida, no pierda la esperanza de encontrarla." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#consultar-desaparecido'>Consultar</a>" +
											"</div>");
		
		HTML enlaceColaborar		= new HTML("<div class='box-header-enlace'>" +
												"<h4>Colabore con Nosotros</h4>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<div><img alt='Colabore con nosotros' src='image/colabore_con_nosotros.png' ></div>" +
													"<figcaption>" +
														"Genere e imprima un cartel, suscribase a nuestro boletin mensual de desaparecidos, registre una pista." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#colaborar'>Colaborar</a>" +
											"</div>");
		
		HTML contactenos		= new HTML("<div class='box-header-enlace'>" +
											"<h4>Cont\u00E1ctenos</h4>" +
										"</div>" +
										"<div class='content-enlace'>" +
											"<figure>" +
												"<div><img alt='Colabore con nosotros' src='image/suscribase.png' ></div>" +
												"<figcaption>" +
													"Tiene un comentario, pregunta, duda o sugerencia, no dude en ponerse en cont\u00E1cto con nosotros." +
												"</figcaption>" +
											"</figure>" +
										"</div>" +
										"<div class='box-boton-enlace'>" +
											"<a href='#contactenos'>Contactar</a>" +
										"</div>");
		
		
		
		HTMLPanel contentDeInteres	= new HTMLPanel("<div class='separator'></div>" +
				   					   				"<h2>Informaci\u00F3n de Inter\u00E9s</h2>");
		
		HTML prevencion 			= new HTML("<a href='#prevencion'>" +
													"<h3>Prevenci\u00F3n</h3>" +
													"<p>" +
														"Pasos para evitar que sus hijos o personas con algun tipo de discapacidad sean victimas del flagelo" +
														"de la desaparici\u00F3n. <span>Mas informaci\u00F3n.</span>" +
													"</p>" +
												"</a>");
		
		HTML comoActuar 			= new HTML("<a href='#como-actuar'>" +
													"<h3>\u00BFC\u00F3mo Actuar?</h3>" +
													"<p>" +
														"Si usted se encuentra ante un caso de desaparici\u00F3n, debe tener en cuenta ciertos pasos que " +
														"le ayudaran en su b\u00FAsqueda. <span>Conozcalos aqu\u00ED.</span>" +
													"</p>" +
												"</a>");
		
		HTML requisitos 			= new HTML("<a href='#requisitos'>" +
													"<h3>Requisitos</h3>" +
													"<p>" +
														"Para realizar el registro de una persona desaparecida en el sistema, Sirbuped establece una serie de " + 
														"requisitos para su publicaci\u00F3n . <span>Ver requisitos.</span>" +
													"</p>" +
												"</a>");
		
		HTML avisoLegal 			= new HTML("<a href='#terminos-y-condiciones'>" +
													"<h3>Aviso Legal</h3>" +
													"<p>" +
														"Tenga en cuenta que la informaci\u00F3n registrada sera comprobada para estblecer su veracidad." + 
														"Conozca los t\u00E9rminos y condiciones. <span>Mas informaci\u00F3n.</span>" +
													"</p>" +
												"</a>");
		
		
    	ultimasDesapariciones.setStyleName("verDesaparecido");
    	
    	final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		ultimasDesapariciones.add(cargando);
		
		DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
		desaparecidoService.getDesaparecidos((byte)6, new AsyncCallback<ArrayList<Desaparecido>>()
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
			    		image.setUrl(desaparecido.getKeyFoto());
			    		
			    		HTML figcaption = new HTML(desaparecido.getNombre1() + " " +desaparecido.getNombre2() + " " + 
			    								   desaparecido.getApellido1() + " " + desaparecido.getApellido2());
			    		
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
								History.newItem("-" + desaparecido.getId());
			    			}
			    		});
			    	}
		    	}
		    	cargando.getElement().setAttribute("style", "display:none");
		    }
		    public void onFailure(Throwable error) 
			{
		    	cargando.getElement().setAttribute("style", "display:none");
		    	HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron resultados que coincidan con su busqueda.</p>");
	    		sinResultados.setStyleName("sinResultados");
		    	ultimasDesapariciones.add(sinResultados);
			}
        });
		
		contentDeInteres.add(prevencion);
		contentDeInteres.add(comoActuar);
		contentDeInteres.add(requisitos);
		contentDeInteres.add(avisoLegal);
		deInteres.add(contentDeInteres);
		
		banner.add(contentBanner);
		enlaces.add(enlaceRegistrar);
		enlaces.add(enlaceConsultar);
		enlaces.add(enlaceColaborar);
		enlaces.add(contactenos);
		
		subContent.add(banner);
		subContent.add(enlaces);
		subContent.add(ultimasDesapariciones);
		subContent.add(deInteres);
		
		initWidget(subContent);
	}
}
