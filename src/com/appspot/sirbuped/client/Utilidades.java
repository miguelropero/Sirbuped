package com.appspot.sirbuped.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;

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
	
	public DialogBox ventanaModal(String titulo, String contenido, String tipo) 
	{
		final DialogBox dialogBox  = new DialogBox();
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Aceptar");
		closeButton.getElement().setId("closeButton");
		
		final HTMLPanel divDialogbox = new HTMLPanel("");
		final HTMLPanel fade = new HTMLPanel("");
		final HTMLPanel overlay = new HTMLPanel("");
		fade.setStyleName("fade");
		
		final HTMLPanel divTitulo = new HTMLPanel(titulo);
		divTitulo.setStyleName("titulo");
		
		final HTMLPanel divContenido = new HTMLPanel(contenido);
		divContenido.setStyleName("contenido");
		
		overlay.add(divTitulo);
		overlay.add(divContenido);
		overlay.add(closeButton);
		divDialogbox.add(fade);
		divDialogbox.add(overlay);
		
		dialogBox.setWidget(divDialogbox);
		dialogBox.center();
		
		if(tipo.equals("error"))
		{	
			overlay.setStyleName("divModalError");
		}
		else if(tipo.equals("Exito"))
		{	
			overlay.setStyleName("divModalExito");
		}
		
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.removeFromParent();
			}
		});
		
		closeButton.addKeyUpHandler(new KeyUpHandler()
		{
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
				{
					dialogBox.removeFromParent();
				}
			}
		});
		
		closeButton.setFocus(true);
		
		return dialogBox;
	}
	
	public ListBox diaAnio(boolean dia)
	{
		ListBox select = new ListBox();
		if(dia)
		{
			select.addItem("Dia...");
			for(int i=1; i < 32; i++)
			{
				select.addItem(String.valueOf(i));
			}
		}
		else
		{
			select.addItem("A\u0148o...");
			for(int i=2013; i > 1909; i--)
			{
				select.addItem(String.valueOf(i));
			}
		}
		return select;
	}
	
	public ListBox listaMeses()
	{
		ListBox selectMes = new ListBox();
		selectMes.addItem("Mes...");
		selectMes.addItem("Enero");
		selectMes.addItem("Febrero");
		selectMes.addItem("Marzo");
		selectMes.addItem("Abril");
		selectMes.addItem("Mayo");
		selectMes.addItem("Junio");
		selectMes.addItem("Julio");
		selectMes.addItem("Agosto");
		selectMes.addItem("Septiembre");
		selectMes.addItem("Octubre");
		selectMes.addItem("Noviembre");
		selectMes.addItem("Diciembre");
		
		return selectMes;
	}
	public String salto()
    {
            int dato1=(int)(Math.random()*30+60);
            int dato2=(int)(Math.random()*30+60);
            return ((char)dato1+(char)dato2+"");
            
    }
}
