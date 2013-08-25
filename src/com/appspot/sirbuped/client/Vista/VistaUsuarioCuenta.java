package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaUsuarioCuenta extends Composite 
{
	public VistaUsuarioCuenta()
	{
		HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("enlaces-rapidos cuenta");
		
		HTML actualizarDatos	= new HTML("<div class='box-header-enlace'>" +
												"<h4>Actilizar Datos</h4>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<div><img alt='Registrar Desaparecido' src='image/actualizar_usuario.png' ></div>" +
													"<figcaption>" +
														"Actualice la informaci\u00F3n de su cuenta de usuario, sus datos personales y sus datos de cont\u00E1cto." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#editar-usuario'>Actualizar Datos</a>" +
											"</div>");
		
		HTML misRegistros		= new HTML("<div class='box-header-enlace'>" +
												"<h4>Personas Registradas</h4>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<div><img alt='Buscar una Persona Desaparecida' src='image/buscar_desaparecido.png' ></div>" +
													"<figcaption>" +
														"Consulte y actualice las personas que tiene registradas como desaparecidas en Sirbuped." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#personas-registradas'>Ver registros</a>" +
											"</div>");
		
		
		HTML pistas				= new HTML("<div class='box-header-enlace'>" +
												"<h4>Consultar Pistas</h4>" +
											"</div>" +
											"<div class='content-enlace'>" +
												"<figure>" +
													"<div><img alt='Consultar Pistas' src='image/pistas.png' ></div>" +
													"<figcaption>" +
														"Consulte las pistas enviadas por nuestros usuarios registrados y las pistas enviadas desde su cuenta." +
													"</figcaption>" +
												"</figure>" +
											"</div>" +
											"<div class='box-boton-enlace'>" +
												"<a href='#consultar-pistas'>Consultar</a>" +
											"</div>");
		
		
		HTML cambiarPassword	= new HTML("<div class='box-header-enlace'>" +
		   										"<h4>Cambiar Contrase\u00F1a</h4>" +
										   	"</div>" +
										   	"<div class='content-enlace'>" +
										   		"<figure>" +
										   			"<div><img alt='Cambiar Password' src='image/cambiar_password.png' ></div>" +
										   			"<figcaption>" +
										   				"Recuerde cambiar periodicamente su contrase\u00F1a para evitar inconvenientes de seguridad." +
										   				"</figcaption>" +
										   		"</figure>" +
										   	"</div>" +
										   	"<div class='box-boton-enlace'>" +
										   		"<a href='#cambiar-password'>Cambiar Contrase\u00F1a</a>" +
											"</div>");
		
		HTML mensajes			= new HTML("<div class='box-header-enlace'>" +
												"<h4>Mensajes Recibidos</h4>" +
										   	"</div>" +
										   	"<div class='content-enlace'>" +
										   		"<figure>" +
										   			"<div><img alt='Cambiar Password' src='image/suscribase.png' ></div>" +
										   			"<figcaption>" +
										   				"Comentarios, dudas y sugrenencias enviadas por los usuarios acerca de nuestros servicios." +
										   				"</figcaption>" +
										   		"</figure>" +
										   	"</div>" +
										   	"<div class='box-boton-enlace'>" +
										   		"<a href='#mensajes-de-usuario'>Ver Mensajes</a>" +
											"</div>");
		
		subContent.add(actualizarDatos);
		subContent.add(misRegistros);
		subContent.add(pistas);
		subContent.add(cambiarPassword);
		subContent.add(mensajes);
		
		initWidget(subContent);
	}
}
