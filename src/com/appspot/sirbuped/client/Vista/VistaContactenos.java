package com.appspot.sirbuped.client.Vista;

import com.appspot.sirbuped.client.RichTextToolbar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;

public class VistaContactenos extends Composite
{
	public VistaContactenos()
	{
		HTMLPanel subContent = new HTMLPanel(" ");
		subContent.setStyleName("contacto");
		
		CaptionPanel fieldsetContacto = new CaptionPanel("Cont\u00E1ctenos");
        HTMLPanel divContacto = new HTMLPanel(" ");
		
		Label lblUsuario 	= new Label("Nombre:");
		Label lblEmail 		= new Label("E-mail:");
		Label lblAsunto		= new Label("Asunto:");
        Label lblMsj		= new Label("Mensaje:");

		TextBox textUsuario = new TextBox();
		TextBox textEmail 	= new TextBox();
		TextBox textAsunto 	= new TextBox();
		final RichTextArea areaMensaje = new RichTextArea();
		RichTextToolbar toolbar = new RichTextToolbar(areaMensaje);
		
		HTMLPanel divBotones= new HTMLPanel(" ");
		
		Button btnEnviar	= new Button(" Enviar ");
		Button btnCancelar 	= new Button("Cancelar");
		Button btnLimpiar 	= new Button("Limpiar");
		btnEnviar.setEnabled(false);
		
		divBotones.add(btnEnviar);
		divBotones.add(btnLimpiar);
		divBotones.add(btnCancelar);
		
		divContacto.add(lblUsuario);		
		divContacto.add(textUsuario);
		divContacto.add(lblEmail);
		divContacto.add(textEmail);
		divContacto.add(lblAsunto);
		divContacto.add(textAsunto);
		divContacto.add(lblMsj);
		divContacto.add(toolbar);
		divContacto.add(areaMensaje);
		
		fieldsetContacto.add(divContacto);
		
		subContent.add(fieldsetContacto);
		subContent.add(divBotones);
		
		btnEnviar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				Window.alert(areaMensaje.getHTML());
			}
		});
		
		initWidget(subContent);
	}
}
