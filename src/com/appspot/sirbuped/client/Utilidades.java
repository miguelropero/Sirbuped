package com.appspot.sirbuped.client;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Ciudad;
import com.appspot.sirbuped.client.DTO.Departamento;
import com.appspot.sirbuped.client.DTO.Pais;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.LugarService;
import com.appspot.sirbuped.client.Interfaz.LugarServiceAsync;
import com.appspot.sirbuped.client.Interfaz.UsuarioService;
import com.appspot.sirbuped.client.Interfaz.UsuarioServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Utilidades 
{
	public Usuario usuario;
	public Utilidades()
	{
		usuario = new Usuario();
	}
	
	
	public void crearBotonesDeSesion(boolean sesion)
	{
		RootPanel.get("botonesAcceso").clear();
		
		Hyperlink btn1;
		Hyperlink btn2;
		if(sesion)
		{
			btn1 = new Hyperlink("<img src='image/sesion/cuenta.png' align='absmiddle' /><span>Mi Cuenta<span/>", true, "mi-cuenta");
			btn2 = new Hyperlink("<img src='image/sesion/cerrar.png' align='absmiddle' /><span>Cerrar Sesi\u00F3n<span/>", true, "cerrar-sesion");
		}
		else 
		{
			btn1 = new Hyperlink("<img src='image/sesion/registrarse.png' align='absmiddle' /><span>Registrarse<span/>", true, "registrarse");
			btn2 = new Hyperlink("<img src='image/sesion/login.png' align='absmiddle' /><span>Iniciar Sesion<span/>", true, "iniciar-sesion");
		}
		 
		RootPanel.get("botonesAcceso").add(btn1);;
		RootPanel.get("botonesAcceso").add(btn2);
	}
	
	
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
	
	/* Metodos de Sesion */
	
	public native String deleteSesion(String key) 
	/*-{
		var value = sessionStorage.getItem(key);
		sessionStorage.removeItem(key);
		return value;
	}-*/;
	
	public void cerrarSesion(String key)
	{
		UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
	    
	    usuarioService.cerrarSesion(new AsyncCallback<Void>() 
	    {
	    	@Override
			public void onSuccess(Void result) 
	    	{
	    		crearBotonesDeSesion(false);
	    		History.newItem("iniciar-sesion");
			}
	    	public void onFailure(Throwable error) 
	    	{
	    		Window.alert(error.toString());
	    	}
	   });
	    
	    if(this.getSesion("logout") != null)
	    	Window.Location.replace(this.getSesion("logout"));
	}
	
	public native String getSesion(String key) 
	/*-{
		return sessionStorage.getItem(key);
	}-*/;
	
	public native void crearSesion(String key, String value) 
	/*-{
		sessionStorage.setItem(key, value);
	}-*/;
	
	
	/* Generar Select de los Paises */
	public void getPaises(final ListBox selectPaises)
	{
		LugarServiceAsync lugarService = GWT.create(LugarService.class);
		lugarService.getPaises(new AsyncCallback<ArrayList<Pais>>() 
		{
			@Override
			public void onSuccess(ArrayList<Pais> paises) 
			{
				for(Pais pais : paises)
					selectPaises.addItem(pais.getNombre());				
			}
			public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			}
        });
	}
	
	/* Generar Select de los Departamentos */
	public void getDepartamentos(String pais, final ListBox selectDepartamentos)
	{
		LugarServiceAsync lugarService = GWT.create(LugarService.class);
		lugarService.getDepartamentos(pais, new AsyncCallback<ArrayList<Departamento>>() 
		{
			@Override
			public void onSuccess(ArrayList<Departamento> departamentos) 
			{
				selectDepartamentos.clear();
				selectDepartamentos.addItem("Departamento...");
				
				for(Departamento departamento : departamentos)
					selectDepartamentos.addItem(departamento.getNombre());
			}
			public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			}
        });
	}
	
	/* Generar Select de las Ciudadades */
	public void getCiudades(String pais, String departamento, final ListBox selectCiudades)
	{
		LugarServiceAsync lugarService = GWT.create(LugarService.class);
		lugarService.getCiudades(pais, departamento, new AsyncCallback<ArrayList<Ciudad>>() 
		{
			@Override
			public void onSuccess(ArrayList<Ciudad> ciudades) 
			{
				selectCiudades.clear();
				selectCiudades.addItem("Ciudad...");
				
				for(Ciudad ciudad : ciudades)
					selectCiudades.addItem(ciudad.getNombre(), ciudad.getId());
			}
			public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			}
        });
	}
	
	public void selectedIndex(final ListBox selectPaises, final ListBox selectDepartamentos, final ListBox selectCiudades, final String pais, final String departamento, final String ciudad )
	{
		
		LugarServiceAsync lugarService = GWT.create(LugarService.class);
		lugarService.getPaises(new AsyncCallback<ArrayList<Pais>>() 
		{
			@Override
			public void onSuccess(ArrayList<Pais> paises) 
			{
				for(Pais pais : paises)
					selectPaises.addItem(pais.getNombre());	
				
				if(!pais.isEmpty())
				{
					for(int i = 0; i < selectPaises.getItemCount(); i++)
					{
						if(selectPaises.getValue(i).equals(pais))
						{
							selectPaises.setSelectedIndex(i);
							break;
						}	
					}
				}
				
				LugarServiceAsync lugarService = GWT.create(LugarService.class);
				lugarService.getDepartamentos(pais, new AsyncCallback<ArrayList<Departamento>>() 
				{
					@Override
					public void onSuccess(ArrayList<Departamento> departamentos) 
					{
						selectDepartamentos.clear();
						selectDepartamentos.addItem("Departamento...");
						
						for(Departamento departamento : departamentos)
							selectDepartamentos.addItem(departamento.getNombre());
						
						if(!departamento.isEmpty())
						{
							for(int i = 0; i < selectDepartamentos.getItemCount(); i++)
							{
								if(selectDepartamentos.getValue(i).equals(departamento))
								{
									selectDepartamentos.setSelectedIndex(i);
									break;
								}	
							}
						}
						
						LugarServiceAsync lugarService = GWT.create(LugarService.class);
						lugarService.getCiudades(pais, departamento, new AsyncCallback<ArrayList<Ciudad>>() 
						{
							@Override
							public void onSuccess(ArrayList<Ciudad> ciudades) 
							{
								selectCiudades.clear();
								selectCiudades.addItem("Ciudad...");
								
								for(Ciudad ciudad : ciudades)
									selectCiudades.addItem(ciudad.getNombre(), ciudad.getId());
								
								if(!ciudad.isEmpty())
								{
									for(int i = 0; i < selectCiudades.getItemCount(); i++)
									{
										if(selectCiudades.getItemText(i).equals(ciudad))
										{
											selectCiudades.setSelectedIndex(i);
											break;
										}	
									}
								}
							}
							public void onFailure(Throwable error) 
							{
								Window.alert(error.toString());
							}
				        });
					}
					public void onFailure(Throwable error) 
					{
						Window.alert(error.toString());
					}
		        });
			}
			public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			}
        });
	}
	
	/* Metodo que permite validar si el Objeto Element que llega como Parametro 
	 * es un Widget.
	 */
	public IsWidget getWidget(com.google.gwt.user.client.Element element) 
	{
	    EventListener listener = DOM
	            .getEventListener((com.google.gwt.user.client.Element) element);
	    // No listener attached to the element, so no widget exist for this
	    // element
	    if (listener == null) {
	        return null;
	    }
	    if (listener instanceof Widget) {
	        // GWT uses the widget as event listener
	        return (Widget) listener;
	    }
	    return null;
	}
	
}
