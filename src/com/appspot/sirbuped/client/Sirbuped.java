package com.appspot.sirbuped.client;

import com.appspot.sirbuped.client.DTO.ListaBoletin;
import com.appspot.sirbuped.client.DTO.LoginInfo;
import com.appspot.sirbuped.client.Interfaz.ListaBoletinService;
import com.appspot.sirbuped.client.Interfaz.ListaBoletinServiceAsync;
import com.appspot.sirbuped.client.Interfaz.UsuarioService;
import com.appspot.sirbuped.client.Interfaz.UsuarioServiceAsync;
import com.appspot.sirbuped.client.Vista.Mapa;
import com.appspot.sirbuped.client.Vista.VistaConsultarDesaparecido;
import com.appspot.sirbuped.client.Vista.VistaContactenos;
import com.appspot.sirbuped.client.Vista.VistaCuentaUsuario;
import com.appspot.sirbuped.client.Vista.VistaDesaparecido;
import com.appspot.sirbuped.client.Vista.VistaHome;
import com.appspot.sirbuped.client.Vista.VistaIniciarSesion;
import com.appspot.sirbuped.client.Vista.VistaMapaDesaparecidos;
import com.appspot.sirbuped.client.Vista.VistaNosotros;
import com.appspot.sirbuped.client.Vista.VistaUsuario;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sirbuped implements EntryPoint, ValueChangeHandler<String> 
{	
	
	private boolean haySesion;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		this.haySesion = false;
		
		this.crearFormularioBoletin();
		
		// Add history listener
	    History.addValueChangeHandler(this);
	    
	    if (History.getToken().length() == 0) 
	    {
	    	new Utilidades().crearBotonesDeSesion(false);
	    	validarSesion("home");
			this.cargarSlider();
	    }
	    else
	    {
	    	validarSesion(History.getToken());
	    }
	}
	
	/**
	 * Metodo que detecta el cambio en la url del navegador para determinar que pagina esta solicitando 
	 * el usuario. Esto lo hace a traves de un token que se agrega a la url anteponiendo el caracter #
	 * @param el token que se agrega al final de la url producto del evento realizado por el usuario
	 */
	
	public void onValueChange(ValueChangeEvent<String> event) 
	{	
		validarSesion(event.getValue());
	}
	
	public void validarSesion(final String token)
	{
		UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
		
	    usuarioService.getSesion("usuario", new AsyncCallback<String>() 
	    {
	    	@Override
			public void onSuccess(String result) 
	    	{
	    		if(result != null)
	    		{
	    			new Utilidades().crearBotonesDeSesion(true);
	    			haySesion = true;
	    		}
	    		else
	    		{
	    			new Utilidades().crearBotonesDeSesion(false);
	    			haySesion = false;
	    		}
	    		mostrarVista(token);
			}
	    	public void onFailure(Throwable error) 
	    	{
	    		Window.alert(error.toString());
	    		new Utilidades().crearBotonesDeSesion(false);
	    		mostrarVista("Home");
	    	}
	   });
	}
	
	public void mostrarVista(String token)
	{
		//Window.alert("Mi token " + new Utilidades().getSesion("token"));
		String subToken = String.valueOf(token.charAt(0));
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new Utilidades().actualizarEncabezadoContenido(token));
		
		if(token.equals("home") || token.isEmpty() || token.equals(" "))
		{
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new VistaHome());
			this.cargarSlider();
		}
		else if(token.equals("consultar"))
	    {
			RootPanel.get("content").add(new VistaConsultarDesaparecido());
	    }
		else if(token.equals("registrarse"))
	    {
			RootPanel.get("content").add(new VistaUsuario());
	    }
		else if(token.equals("mapa-de-desaparecidos"))
	    {
			RootPanel.get("content").add(new VistaMapaDesaparecidos());
	    }
		else if(token.equals("mapa"))
	    {
			new Mapa();
	    }
		else if(token.equals("nosotros"))
	    {
			RootPanel.get("content").add(new VistaNosotros());
	    }
		else if(token.equals("iniciar-sesion"))
	    {
			if(haySesion)
				History.newItem("mi-cuenta");
			else
				RootPanel.get("content").add(new VistaIniciarSesion(new Utilidades().deleteSesion("token")));
	    }
		else if(token.equals("registrar-desaparecido"))
	    {
			if(haySesion)
			{
				RootPanel.get("content").add(new VistaDesaparecido());
			}
			else
			{
				new Utilidades().crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
		    }
	    }
		else if(token.equals("contactenos"))
	    {
			if(haySesion)
			{
			    RootPanel.get("content").add(new VistaContactenos());
		    }
		    else
		    {
		    	new Utilidades().crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
		    }
	    }
		else if(token.equals("cerrar-sesion"))
	    {
			new Utilidades().cerrarSesion(new Utilidades().deleteSesion("token"));
	    }
		else if(subToken.equals("-"))
	    {
			RootPanel.get("content").clear();
			subToken = token.replace("-", "");
			RootPanel.get("content").add(new Utilidades().actualizarEncabezadoContenido("detalle-de-desaparicion"));
			RootPanel.get("content").add(new HTMLPanel("<div id='verDesaparecido' class='verDesaparecido'></div>"));
			RootPanel.get("content").add(new VistaConsultarDesaparecido(subToken));
	    }
		else if(token.equals("mi-cuenta"))
		{
			if(haySesion)
			{
				RootPanel.get("content").add(new VistaCuentaUsuario());
		    }
		    else
		    {
		    	new Utilidades().crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
		    }
		}
		else if(token.equals("login-google"))
		{
		    UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
		    String url = GWT.getHostPageBaseURL()+"#iniciar-sesion";
		    
		    usuarioService.loginGoogle(url, new AsyncCallback<LoginInfo>() 
		    {
		    	@Override
				public void onSuccess(LoginInfo loginInfo) 
		    	{
		    		if(loginInfo.isLoggedIn()) 
		    		{
		    			if(loginInfo.getRegistrado())
		    			{
		    				new Utilidades().crearSesion("logout", loginInfo.getLogoutUrl());
		    				
			    			if(new Utilidades().getSesion("token") == null)
			    			{
			    				History.newItem("mi-cuenta");
			    			}
			    			else
			    			{
			    				History.newItem(new Utilidades().deleteSesion("token"));
			    			}
		    			}
		    			else
		    			{
		    				new Utilidades().ventanaModal("Error", "usted se ecuentra logueado con la cuenta de google "+ loginInfo.getEmailAddress() +",  sin embargo, su correo electronico no se encuentra registrado en el sistema. Debe registrarse para iniciar sesi\u00F3n", "error" );
		    				History.newItem("registrarse");
		    			}
		    		} 
		    		else 
		    		{
		    			History.newItem("iniciar-sesion");
		    		}
		    	}
		    	public void onFailure(Throwable error) 
		    	{
		    		Window.alert(error.toString());
		    	}
		   });
		}
		else
		{
			Window.alert("La pagina solcitada no se encuentra");
		}
	}
	
	public void crearFormularioBoletin()
	{
		final TextBox textEmail 	= new TextBox();
		final Button btnRegistrar 	= new Button("<label>Registrar</label><label>Ok</label>");
		
		textEmail.getElement().setAttribute("placeHolder", "suemail@proveedor.com");
		
		RootPanel.get("suscribirse").add(textEmail);
		RootPanel.get("suscribirse").add(btnRegistrar);
		
		btnRegistrar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				//Aqu� va el c�digo que se ejecuta una vez que se
				//hace clic en el bot�n.
				ListaBoletinServiceAsync listaBoletin = GWT.create(ListaBoletinService.class);
				listaBoletin.addEmail(new ListaBoletin(textEmail.getText()), new AsyncCallback<Void>() 
				{
				     public void onFailure(Throwable error) 
				     {
				    	Window.alert(error.toString()); 
				     }
				     public void onSuccess(Void ignore) 
				     {
				    	 Window.alert("Su Email se ha registrado correctamente en nuestro Boletin"); 
					 }
				});
			}
		});
	}
	
	public native void cargarSlider() /*-{
		$wnd.$('.bxslider').bxSlider({
			auto: true,
  			pause: 7000,
  			mode: 'fade'
		});
	}-*/;
}
