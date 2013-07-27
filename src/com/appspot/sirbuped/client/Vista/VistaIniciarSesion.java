package com.appspot.sirbuped.client.Vista;

import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.LoginInfo;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.UsuarioService;
import com.appspot.sirbuped.client.Interfaz.UsuarioServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class VistaIniciarSesion extends Composite 
{	
	public VistaIniciarSesion(final String token) 
	{
		final HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("content_content");
		
		final HTMLPanel divSesion = new HTMLPanel("");
		divSesion.setStyleName("div_sesion");
		
		final HTML divTitulo = new HTML("<h2>Inicie sesi\u00F3n con su cuenta<h2>");
		
		HTMLPanel divGoogle = new HTMLPanel("");
		Button btnIniciarGoogle = new Button("Iniciar sesi\u00F3n con Google");
		btnIniciarGoogle.setStyleName("boton_google");
		divGoogle.add(btnIniciarGoogle);
		
		HTMLPanel divSeparator = new HTMLPanel("<span>---------------------</span> \u00F3 " +
										"<span>--------------------</span>");
		divSeparator.setStyleName("div_separator");
		final TextBox textUsuario = new TextBox();
		textUsuario.getElement().setAttribute("placeHolder", "Correo electr\u00F3nico");
		textUsuario.setFocus(true);
		final PasswordTextBox textPassword = new PasswordTextBox();
		textPassword.getElement().setAttribute("placeHolder", "Contrase\u00F1a");
		HTMLPanel divBoton = new HTMLPanel("");
		divBoton.setStyleName("div_boton");
		CheckBox checkBoxRecordarme = new CheckBox("Recordarme");
		Button btnIniciar = new Button("Iniciar sesi\u00F3n");
		divBoton.add(checkBoxRecordarme);
		divBoton.add(btnIniciar);
		
		final HTMLPanel divError = new HTMLPanel("");
		divError.setVisible(false);
		
		HTMLPanel divFooter = new HTMLPanel("");
		divFooter.setStyleName("div_footer");
		Button btnRecContrasena = new Button("Olvid\u00F3 su contrase\u00F1a?");
		divFooter.add(btnRecContrasena);
		
		divSesion.add(divTitulo);
		divSesion.add(divGoogle);
		divSesion.add(divSeparator);
		divSesion.add(textUsuario);
		divSesion.add(textPassword);
		divSesion.add(divBoton);
		divSesion.add(divError);
		divSesion.add(divFooter);
		
		//divBgSesion.add(divSesion);
		
		subContent.add(divSesion);
		
		textUsuario.addBlurHandler(new BlurHandler()
		{
			@Override
			public void onBlur(BlurEvent event)
		    {
				if(!textUsuario.getValue().isEmpty() && !textUsuario.getValue().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
				{
					new Utilidades().ventanaModal("Error", "El correo electr\u00F3nico ingresado no tiene una sintaxis valida	", "error");
				}
			}
		});
		
		
		btnIniciarGoogle.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{	
			    UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
			    String url = GWT.getHostPageBaseURL()+"#login-google";
			    
			    usuarioService.loginGoogle(url, new AsyncCallback<LoginInfo>() 
			    {
			    	@Override
					public void onSuccess(LoginInfo loginInfo) 
			    	{
			    		if(loginInfo.isLoggedIn()) 
			    		{
			    			//Window.alert("Logueado");
			    			if(loginInfo.getRegistrado())
			    			{
			    				new Utilidades().crearSesion("logout", loginInfo.getLogoutUrl());
			    				
				    		    if(token != null)
				    			{
				    				new Utilidades().deleteSesion("token");
				    				History.newItem(token);
				    			}
				    			else
				    			{
				    				History.newItem("mi-cuenta");
				    			}
			    			}
			    			else
			    			{
			    				new Utilidades().ventanaModal("Error", "usted se ecuentra logueado con la cuenta de google "+ loginInfo.getEmailAddress() +",  sin embargo, su correo electronico no se encuentra registrado en el sistema. Debe registrarse para iniciar sesi\u00F3n", "error" );
			    			}
			    		} 
			    		else 
			    		{
			    			//Window.alert("No Logueado");
			    			if(token != null)
			    			{
			    				new Utilidades().crearSesion("token", token);
			    			}
			    			else
			    			{
			    				new Utilidades().crearSesion("token", "mi-cuenta");
			    			}
			    			
			    			Window.Location.replace(loginInfo.getLoginUrl());
			    		}
			    	}
			    	public void onFailure(Throwable error) 
			    	{
			    		Window.alert(error.toString());
			    	}
			   });
			}
		});
		
		class MyHandler implements ClickHandler, KeyUpHandler 
		{
			public void onClick(ClickEvent event) 
			{
				iniciarSesion();
			}

			public void onKeyUp(KeyUpEvent event) 
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) 
				{
					iniciarSesion();
				}
			}	
			    
			private void iniciarSesion() 
			{
				
				if(textUsuario.getValue().isEmpty() || textPassword.getValue().isEmpty())
				{
					Label error = new Label("No ha ingresado los datos de su cuenta");
	    			error.getElement().setAttribute("style", "color: #B40404; padding: 0 0 2em 0");
	    			divError.add(error);
	    			divError.setVisible(true);
					return;
				}
				
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				divSesion.setVisible(false);
				subContent.add(cargando);
				
				UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
		    
			    Usuario usuario = new Usuario();
			    usuario.setEmail(textUsuario.getValue());
			    usuario.setPassword(textPassword.getValue());
			    usuarioService.iniciarSesion(usuario, new AsyncCallback<Usuario>() 
			    {
			    	public void onSuccess(Usuario result) 
			    	{
			    		if(result != null)
			    		{
			    			new Utilidades().crearBotonesDeSesion(true);
			    			
			    			if(token != null)
			    			{
			    				new Utilidades().deleteSesion("token");
			    				History.newItem(token);
			    			}
			    			else
			    			{
			    				History.newItem("mi-cuenta");
			    			}
			    		}
			    		else
			    		{
			    			divSesion.setVisible(true);
							cargando.setVisible(false);
			    			Label error = new Label("Datos no validos o cuenta inactiva");
			    			error.getElement().setAttribute("style", "color: #B40404; padding: 0 0 2em 0");
			    			divError.add(error);
			    			divError.setVisible(true);
			    		}
			    	}
			    	public void onFailure(Throwable error) 
			    	{
			    		divSesion.setVisible(true);
						cargando.setVisible(false);
			    		Window.alert(error.toString());
			    	}
			   });
			
			}
			
		}
		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		btnIniciar.addClickHandler(handler);
		textPassword.addKeyUpHandler(handler);
		
		initWidget(subContent);
	}
}
