package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaCuentaUsuario extends Composite 
{
	public VistaCuentaUsuario()
	{
		HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("enlaces-rapidos");
		
		HTML enlaceRegistrar	= new HTML("<div class='box-header-enlace'>" +
												"<h4>Actilizar Datos</h4>" +
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
												"<a href='#editar-usuario'>Actualizar Datos</a>" +
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
						"<a href='#consultar'>Consultar</a>" +
					"</div>");
		
		HTML enlaceColaborar	= new HTML("<div class='box-header-enlace'>" +
										   		"<h4>Mensajes</h4>" +
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
		
		HTML enlaceSuscribase	= new HTML("<div class='box-header-enlace'>" +
												"<h4>Pistas Recibidas</h4>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<div><img alt='Colabore con nosotros' src='image/suscribase.png' ></div>" +
													"<figcaption>" +
														"Suscriba se a nuestro boletin mensual de desaparecidos y comparta la informacion con sus contactos." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#colaborar'>Colaborar</a>" +
											"</div>");
		
		
		subContent.add(enlaceRegistrar);
		subContent.add(enlaceConsultar);
		subContent.add(enlaceColaborar);
		subContent.add(enlaceSuscribase);
		
		initWidget(subContent);
	}
}
