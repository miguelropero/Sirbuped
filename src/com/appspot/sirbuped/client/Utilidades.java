package com.appspot.sirbuped.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Utilidades 
{
	
	public Utilidades()
	{}
	
	/*
	 * Agrega el encabezado de acuerdo al contenido mostrado
	 * @param nombre de la acción solicitada por el usuario
	 */
	public HTMLPanel actualizarEncabezadoContenido(String accion)
	{
		if(accion.equals("iniciar-sesion"))
		{
			accion = "Iniciar sesi\u00F3n";
		}
		
		else if(accion.equals("boletin"))
		{
			accion = "bolet\u00EDn";
		}
		
		else if(accion.equals("estadisticas"))
		{
			accion = "estad\u00EDsticas";
		}
		
		else if(accion.equals("contactenos"))
		{
			accion = "cont\u00E1ctenos";
		}
		if(accion.equals("detalle-de-desaparicion"))
		{
			accion = "Detalle de desaparici\u00F3n";
		}
		
		if(accion.contains("-"))
		{
			accion = accion.replace("-", " ");
		}
		
		HTMLPanel encabezadoContenido = new HTMLPanel(" ");
		
		HTML titulo = new HTML("<h2 class=\"titulo_head_content\">"+ accion +"</h2>", true);
		titulo.setStyleName("box_titulo");
		
		encabezadoContenido.add(titulo);
		encabezadoContenido.setStyleName("encabezado_contenido");
		
		return encabezadoContenido;
	}
}
