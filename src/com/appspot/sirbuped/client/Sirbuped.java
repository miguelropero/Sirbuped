package com.appspot.sirbuped.client;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.ListaBoletin;
import com.appspot.sirbuped.client.DTO.LoginInfo;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.appspot.sirbuped.client.Interfaz.ListaBoletinService;
import com.appspot.sirbuped.client.Interfaz.ListaBoletinServiceAsync;
import com.appspot.sirbuped.client.Interfaz.LugarService;
import com.appspot.sirbuped.client.Interfaz.LugarServiceAsync;
import com.appspot.sirbuped.client.Interfaz.UsuarioService;
import com.appspot.sirbuped.client.Interfaz.UsuarioServiceAsync;
import com.appspot.sirbuped.client.Vista.Error404;
import com.appspot.sirbuped.client.Vista.VistaComoActuar;
import com.appspot.sirbuped.client.Vista.VistaContactenos;
import com.appspot.sirbuped.client.Vista.VistaDesaparecido;
import com.appspot.sirbuped.client.Vista.VistaDesaparecidoConsultar;
import com.appspot.sirbuped.client.Vista.VistaDesaparecidoDepartamento;
import com.appspot.sirbuped.client.Vista.VistaDesaparecidoDetallar;
import com.appspot.sirbuped.client.Vista.VistaDesaparecidoMapa;
import com.appspot.sirbuped.client.Vista.VistaDesaparecidoUsuario;
import com.appspot.sirbuped.client.Vista.VistaEstadistica;
import com.appspot.sirbuped.client.Vista.VistaHome;
import com.appspot.sirbuped.client.Vista.VistaIniciarSesion;
import com.appspot.sirbuped.client.Vista.VistaMensaje;
import com.appspot.sirbuped.client.Vista.VistaNosotros;
import com.appspot.sirbuped.client.Vista.VistaPista;
import com.appspot.sirbuped.client.Vista.VistaPistaConsultar;
import com.appspot.sirbuped.client.Vista.VistaPrevencion;
import com.appspot.sirbuped.client.Vista.VistaRequisitos;
import com.appspot.sirbuped.client.Vista.VistaTerminosCondiciones;
import com.appspot.sirbuped.client.Vista.VistaUsuario;
import com.appspot.sirbuped.client.Vista.VistaUsuarioCuenta;
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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sirbuped implements EntryPoint, ValueChangeHandler<String> 
{	
	
	private boolean haySesion;
	private String usuario;
	Utilidades utilidades = new Utilidades();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		this.analytics();
		this.haySesion = false;
		
		this.crearFormularioBoletin();
		
		// Add history listener
	    History.addValueChangeHandler(this);
	    
	    if (History.getToken().length() == 0) 
	    {
	    	utilidades.crearBotonesDeSesion(false);
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
	    			utilidades.crearBotonesDeSesion(true);
	    			usuario = result;
	    			haySesion = true;
	    		}
	    		else
	    		{
	    			utilidades.crearBotonesDeSesion(false);
	    			usuario = "";
	    			haySesion = false;
	    		}
	    		mostrarVista(token);
			}
	    	public void onFailure(Throwable error) 
	    	{
	    		Window.alert(error.toString());
	    		utilidades.crearBotonesDeSesion(false);
	    		mostrarVista("Home");
	    	}
	   });
	}
	
	public void mostrarVista(String token)
	{
		String subToken = String.valueOf(token.charAt(0));
		RootPanel.get("content").clear();
		RootPanel.get("content").add(utilidades.actualizarEncabezadoContenido(token));
		
		if(token.equals("home") || token.isEmpty() || token.equals(" "))
		{
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new VistaHome());
			this.cargarSlider();
		}
		else if(token.equals("personas-desaparecidas"))
	    {
			Desaparecido nulo = null;
			RootPanel.get("content").add(new VistaDesaparecidoDetallar(nulo));
	    }
		else if(token.equals("consultar-desaparecido"))
	    {
			RootPanel.get("content").add(new VistaDesaparecidoConsultar());
	    }
		else if(token.equals("registrarse"))
	    {
			RootPanel.get("content").add(new VistaUsuario(null));
	    }
		else if(token.equals("estadisticas"))
	    {
			RootPanel.get("content").add(new VistaEstadistica());
	    }
		else if(token.equals("mapa-de-desaparecidos"))
	    {
			RootPanel.get("content").add(new VistaDesaparecidoMapa());
	    }
		else if(token.equals("nosotros"))
	    {
			RootPanel.get("content").add(new VistaNosotros());
	    }
		else if(token.equals("prevencion"))
		{
			RootPanel.get("content").add(new VistaPrevencion());
		}
		else if(token.equals("como-actuar"))
		{
			RootPanel.get("content").add(new VistaComoActuar());
		}
		else if(token.equals("requisitos"))
		{
			RootPanel.get("content").add(new VistaRequisitos());
		}
		else if(token.equals("terminos-y-condiciones"))
		{
			RootPanel.get("content").add(new VistaTerminosCondiciones());
		}
		else if(token.equals("contactenos"))
	    {
			RootPanel.get("content").add(new VistaContactenos());
	    }
		else if(token.equals("iniciar-sesion"))
	    {
			if(haySesion)
				History.newItem("mi-cuenta");
			else
				RootPanel.get("content").add(new VistaIniciarSesion(utilidades.deleteSesion("token")));
	    }
		else if(token.equals("registrar-desaparecido"))
	    {
			if(haySesion)
			{
				RootPanel.get("content").add(new VistaDesaparecido(null));
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
		    }
	    }
		else if(token.equals("cerrar-sesion"))
	    {
			utilidades.cerrarSesion();
	    }
		else if(subToken.equals("-"))
	    {
			RootPanel.get("content").clear();
			subToken = token.replace("-", "");
			RootPanel.get("content").add(utilidades.actualizarEncabezadoContenido("detalle-de-desaparicion"));
			RootPanel.get("content").add(new HTMLPanel("<div id='verDesaparecido' class='verDesaparecido'></div>"));
			RootPanel.get("content").add(new VistaDesaparecidoDetallar(subToken));
	    }
		else if(subToken.equals("_"))
	    {
			RootPanel.get("content").clear();
			subToken = token.replace("_", "");
			String departamento = subToken.split("-") [2];
		
			RootPanel.get("content").add(utilidades.actualizarEncabezadoContenido(subToken));
			RootPanel.get("content").add(new HTMLPanel("<div id='verDesaparecido' class='verDesaparecido'></div>"));
			new VistaDesaparecidoDepartamento(departamento);
	    }
		else if(subToken.equals("*"))
	    {
			if(haySesion)
			{
				RootPanel.get("content").clear();
				subToken = token.replace("*", "");
				RootPanel.get("content").add(utilidades.actualizarEncabezadoContenido("registrar-pista"));
				RootPanel.get("content").add(new VistaPista(subToken));
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
			}
	    }
		else if(token.equals("mi-cuenta"))
		{
			if(haySesion)
			{
				HTML bienvenida = new HTML("<div><span>Usuario: </span>" + usuario + "</div>");
				bienvenida.setStyleName("bienvenida");
				RootPanel.get("content").add(bienvenida);
				RootPanel.get("content").add(new VistaUsuarioCuenta());
		    }
		    else
		    {
		    	utilidades.crearSesion("token", token);
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
		    				utilidades.crearSesion("logout", loginInfo.getLogoutUrl());
		    				
			    			if(utilidades.getSesion("token") == null)
			    			{
			    				History.newItem("mi-cuenta");
			    			}
			    			else
			    			{
			    				History.newItem(utilidades.deleteSesion("token"));
			    			}
		    			}
		    			else
		    			{
		    				utilidades.ventanaModal("Error", "usted se ecuentra logueado con la cuenta de google "+ loginInfo.getEmailAddress() +",  sin embargo, su correo electronico no se encuentra registrado en el sistema. Debe registrarse para iniciar sesi\u00F3n", "error" );
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
		else if(token.equals("editar-usuario"))
		{
			if(haySesion)
			{
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				
				UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
				
			    usuarioService.getUsuario(new AsyncCallback<Usuario>() 
			    {
			    	@Override
					public void onSuccess(Usuario usuario) 
			    	{
			    		cargando.getElement().setAttribute("style", "display:none");
			    		RootPanel.get("content").add(new VistaUsuario(usuario));
					}
			    	public void onFailure(Throwable error) 
			    	{
			    		cargando.getElement().setAttribute("style", "display:none");
			    		Window.alert("Error, usuario no existe");
			    	}
			   });
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
			}
		}
		else if(token.equals("personas-registradas"))
		{
			if(haySesion)
			{
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				
				UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
				
			    usuarioService.getDesaparecidosUsuario(new AsyncCallback<ArrayList<Desaparecido>>() 
			    {
			    	@Override
					public void onSuccess(ArrayList<Desaparecido> desaparecidos) 
			    	{
			    		if(desaparecidos.size() > 0)
			    		{
			    			RootPanel.get("content").add(new VistaDesaparecidoUsuario(desaparecidos));
			    		}
			    		else
			    		{
			    			HTMLPanel sinResultados = new HTMLPanel("<br><h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron registros asociados a su cuenta.</p><br><br><br><br><br>");
				    		sinResultados.setStyleName("sinResultados");
				    		
				    		HTMLPanel subContent = new HTMLPanel("");
				    		subContent.setStyleName("verDesaparecido");				    		
				    		subContent.add(sinResultados);
				    		
				    		RootPanel.get("content").add(subContent);
			    		}
			    		cargando.getElement().setAttribute("style", "display:none");
					}
			    	public void onFailure(Throwable error) 
			    	{
			    		cargando.getElement().setAttribute("style", "display:none");
			    		utilidades.ventanaModal("Error", "En esto moneto no podemos consultar sus registros. Por favor intente nuevamente.", "error");
			    	}
			   });
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
			}
		}
		else if(token.equals("editar-desaparecido"))
		{
			if(haySesion)
			{
				if(utilidades.getSesion("keyDesaparecido") != null)
				{
					final HTMLPanel cargando = new HTMLPanel("");
					cargando.setStyleName("cargando");
					RootPanel.get("content").add(cargando);
						
					DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
				    desaparecidoService.validarDesaparecidoUsuario(utilidades.getSesion("keyDesaparecido"), new AsyncCallback<Desaparecido>() 
				    {
				    	@Override
						public void onSuccess(Desaparecido desaparecido) 
				    	{
				    		if(desaparecido != null)
				    		{
				    			cargando.getElement().setAttribute("style", "display:none");
				    			RootPanel.get("content").add(new VistaDesaparecido(desaparecido));
				    		}
				    		else
				    		{
				    			utilidades.ventanaModal("Error", "Usted no tiene permisos para editar los datos de esta persona.", "error");
				    		}
						}
				    	public void onFailure(Throwable error) 
				    	{
				    		utilidades.ventanaModal("Error", "No se puede comprobar si el registro de la persona desaparecida pertenece a su cuenta", "error");
				    	}
				    });
				}
				else
				{
					utilidades.ventanaModal("Informaci\u00F3n", "Para actualizar los datos de la persona desaparecida dirijase a Mi Cuenta > Personas Registradas y seleccione la persona a editar", "Exito");
					History.newItem("mi-cuenta");
				}
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
			}
		}
		else if(token.equals("mensajes-de-usuario"))
		{
			if(haySesion)
			{
				RootPanel.get("content").add(new VistaMensaje());
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
			}
		}
		
		else if(token.equals("consultar-pistas"))
		{
			if(haySesion)
			{
				RootPanel.get("content").add(new VistaPistaConsultar());
			}
			else
			{
				utilidades.crearSesion("token", token);
		    	History.newItem("iniciar-sesion");
			}
		}
		else
		{
			RootPanel.get("content").add(new Error404());
			RootPanel.get("google").getElement().setAttribute("style", "display:block");
		}
	}
	
	public void crearFormularioBoletin()
	{
		final TextBox textEmail 	= new TextBox();
		final Button btnRegistrar 	= new Button("<label>Registrar</label><label>Ok</label>");
		final Button btnLugar 	= new Button("Generar");
		final Button btnDelete 	= new Button("Eliminar");
		
		textEmail.getElement().setAttribute("placeHolder", "suemail@proveedor.com");
		
		RootPanel.get("suscribirse").add(textEmail);
		RootPanel.get("suscribirse").add(btnRegistrar);
		//RootPanel.get("suscribirse").add(btnLugar);
		//RootPanel.get("suscribirse").add(btnDelete);
		
		btnRegistrar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				//Aquí va el código que se ejecuta una vez que se
				//hace clic en el botón.
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
		
		btnLugar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				LugarServiceAsync listaBoletin = GWT.create(LugarService.class);
				listaBoletin.generarLugar(new AsyncCallback<Void>() 
				{
				     public void onFailure(Throwable error) 
				     {
				    	Window.alert(error.toString()); 
				     }
				     public void onSuccess(Void ignore) 
				     {
				    	 Window.alert("Correcto"); 
					 }
				});
			}
		});
		btnDelete.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				LugarServiceAsync listaBoletin = GWT.create(LugarService.class);
				listaBoletin.deleteCiudad(new AsyncCallback<Void>() 
				{
				     public void onFailure(Throwable error) 
				     {
				    	Window.alert(error.toString()); 
				     }
				     public void onSuccess(Void ignore) 
				     {
				    	 Window.alert("Correcto"); 
					 }
				});
			}
		});
	}
	
	public native void cargarSlider() 
	/*-{
		
		$wnd.$('.bxslider').bxSlider({
			auto: true,
  			pause: 7000,
  			mode: 'fade'
		});
	}-*/;

	public native void analytics() 
	/*-{
		
		(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function()
  		{
  			(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  			m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  		})
  					
  		(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		ga('create', 'UA-42869891-1', 'sirbuped.appspot.com');
		ga('send', 'pageview');
	}-*/;
}
