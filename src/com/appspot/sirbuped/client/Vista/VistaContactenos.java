package com.appspot.sirbuped.client.Vista;

import com.appspot.sirbuped.client.RichTextToolbar;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Mensaje;
import com.appspot.sirbuped.client.Interfaz.MensajeService;
import com.appspot.sirbuped.client.Interfaz.MensajeServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaContactenos extends Composite
{
	public VistaContactenos()
	{
		final HTMLPanel subContent = new HTMLPanel(" ");
		subContent.setStyleName("contacto");
		
		CaptionPanel fieldsetContacto = new CaptionPanel("Cont\u00E1ctenos");
        HTMLPanel divContacto = new HTMLPanel(" ");
		
		Label lblUsuario 	= new Label("Nombre: *");
		Label lblEmail 		= new Label("E-mail: *");
		Label lblAsunto		= new Label("Asunto: *");
        Label lblMsj		= new Label("Mensaje: *");

		final TextBox textUsuario = new TextBox();
		final TextBox textEmail 	= new TextBox();
		final TextBox textAsunto 	= new TextBox();
		final RichTextArea areaMensaje = new RichTextArea();
		RichTextToolbar toolbar = new RichTextToolbar(areaMensaje);
		
		HTMLPanel divBotones= new HTMLPanel(" ");
		
		Button btnEnviar	= new Button(" Enviar ");
		Button btnLimpiar 	= new Button("Limpiar");
		
		divBotones.add(btnEnviar);
		divBotones.add(btnLimpiar);
		
		divContacto.add(lblUsuario);		
		divContacto.add(textUsuario);
		divContacto.add(lblEmail);
		divContacto.add(textEmail);
		divContacto.add(lblAsunto);
		divContacto.add(textAsunto);
		divContacto.add(lblMsj);
		divContacto.add(toolbar);
		divContacto.add(areaMensaje);
		
		final HTMLPanel divError = new HTMLPanel("");
		divError.setStyleName("div_error");
		divError.setVisible(false);
		
		fieldsetContacto.add(divContacto);
		
		subContent.add(fieldsetContacto);
		subContent.add(divError);
		subContent.add(divBotones);
		
		btnEnviar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				/*
				 * Validando los campos abligatorios del formulario
				 */
				divError.clear();
				boolean hayErrores = false;
				
				if(textUsuario.getValue().isEmpty())
				{
					textUsuario.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores = true;
					textUsuario.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textUsuario.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				
				if(!textEmail.getValue().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
				{
					textEmail.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					Label lblError = new Label("El correo electr\u00F3nico no tiene una sintaxis valida");
					divError.add(lblError);
					hayErrores = true;
					textEmail.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
						{
							textEmail.getElement().setAttribute("style", "");
							divError.setVisible(false);
						}
					});
				}
				
				if(textAsunto.getValue().isEmpty())
				{
					textAsunto.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores = true;
					textAsunto.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textAsunto.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				
				if(areaMensaje.getText().isEmpty())
				{
					areaMensaje.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores = true;
					areaMensaje.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							areaMensaje.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				
				if(hayErrores)
				{
					Label lblError = new Label("El formulario contiene campos vacios que son obligatorios");
					divError.add(lblError);
					divError.setVisible(true);
					return;
				}
				
				
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				subContent.setVisible(false);
				
				Mensaje mensaje = new Mensaje(textUsuario.getValue(), textEmail.getValue(), textAsunto.getValue(), areaMensaje.getText(), null, true);
				
				MensajeServiceAsync mensajeService = GWT.create(MensajeService.class);
				mensajeService.addMensaje(mensaje, new AsyncCallback<Void>() 
				{
				    public void onSuccess(Void ignore) 
				    {
				    	cargando.getElement().setAttribute("style", "display:none");
				    	subContent.setVisible(true);
						new Utilidades().ventanaModal("Correcto", "El mensaje se ha enviado Correctamente ", "Exito");										 
				    }
				    public void onFailure(Throwable error) 
					{
						new Utilidades().ventanaModal("Error", error.toString() , "error");
						subContent.setVisible(true);
					}
		        });							
			}
		});
		
		btnLimpiar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				textEmail.setText("");
				textUsuario.setText("");
				textAsunto.setText("");
				areaMensaje.setText("");
			}
		});
		
		initWidget(subContent);
	}
}
