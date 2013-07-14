package com.appspot.sirbuped.client;

import com.appspot.sirbuped.client.Vista.VistaConsultarDesaparecido;
import com.appspot.sirbuped.client.Vista.VistaContactenos;
import com.appspot.sirbuped.client.Vista.VistaDesaparecido;
import com.appspot.sirbuped.client.Vista.VistaHome;
import com.appspot.sirbuped.client.Vista.VistaIniciarSesion;
import com.appspot.sirbuped.client.Vista.VistaMapaDesaparecidos;
import com.appspot.sirbuped.client.Vista.VistaNosotros;
import com.appspot.sirbuped.client.Vista.VistaRegistrarUsuario;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sirbuped implements EntryPoint, ValueChangeHandler<String> 
{
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		this.crearBotonesAcceso();
		this.crearFormularioBoletin();

		// Add history listener
	    History.addValueChangeHandler(this);
	    
	    if (History.getToken().length() == 0) 
	    {
	    	RootPanel.get("content").add(new VistaHome());
			this.cargarSlider();
	    }
	    else
	    {
	    	String subToken = String.valueOf(History.getToken().charAt(0));
	    	if(subToken.equals("1") || subToken.equals("2") || subToken.equals("3") || subToken.equals("4") || subToken.equals("5"))
	    		procesarSolicitud("-"+History.getToken());
	    	else
	    		procesarSolicitud(History.getToken());
	    }
	}
	
	public void crearBotonesAcceso()
	{
		Hyperlink registrarse 	= 
				new Hyperlink("<img src='image/registrarse2.png' /><span>Registrarse<span/>", true, "registrarse");
		Hyperlink iniciarSesion = 
				new Hyperlink("<img src='image/login2.png' /><span>Iniciar Sesion<span/>", true, "iniciar-sesion");
		
		RootPanel rootPanel = RootPanel.get("botonesAcceso");
		rootPanel.add(registrarse);
		RootPanel.get("botonesAcceso").add(iniciarSesion);
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
				//Aquí va el código que se ejecuta una vez que se
				//hace clic en el botón.
			}
		});
	}
	
	public void onValueChange(ValueChangeEvent<String> event) 
	{	
		procesarSolicitud(event.getValue());
	}
	
	public void procesarSolicitud(String token)
	{			
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
		else if(token.equals("iniciar-sesion"))
	    {
			RootPanel.get("content").add(new VistaIniciarSesion());
	    }
		else if(token.equals("contactenos"))
	    {
			RootPanel.get("content").add(new VistaContactenos());
	    }
		else if(token.equals("nosotros"))
	    {
			RootPanel.get("content").add(new VistaNosotros());
	    }
		else if(token.equals("registrarse"))
	    {
			RootPanel.get("content").add(new VistaRegistrarUsuario());
	    }
		else if(token.equals("registrar-desaparecido"))
	    {
			RootPanel.get("content").add(new VistaDesaparecido());
	    }
		else if(token.equals("mapa-de-desaparecidos"))
	    {
			RootPanel.get("content").add(new VistaMapaDesaparecidos());
	    }
		else
	    {
			RootPanel.get("content").clear();
			String subToken = String.valueOf(token.charAt(0));
			if(subToken.equals("-"))
			{
				subToken = token.replace("-", "");
				RootPanel.get("content").add(new Utilidades().actualizarEncabezadoContenido("detalle-de-desaparicion"));
				RootPanel.get("content").add(new HTMLPanel("<div id='verDesaparecido' class='verDesaparecido'></div>"));
				RootPanel.get("content").add(new VistaConsultarDesaparecido(subToken));
			}
			else
			{
				RootPanel.get("content").add(new Utilidades().actualizarEncabezadoContenido("detalle-de-desaparicion"));
				RootPanel.get("content").add(new HTMLPanel("<div id='verDesaparecido' class='verDesaparecido'></div>"));
				RootPanel.get("content").add(new VistaConsultarDesaparecido(token));
			}
	    }
	}
	
	public native void cargarSlider() /*-{
		$wnd.$('.bxslider').bxSlider({
			auto: true,
  			pause: 7000,
  			mode: 'fade'
		});
	}-*/;
	
}
