package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.DatoDesaparicion;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Morfologia;
import com.appspot.sirbuped.client.DTO.PrendaVestir;
import com.appspot.sirbuped.client.DTO.SenalParticular;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class VistaVerDesaparecidos extends Composite 
{
	private HTMLPanel content;
	
	public VistaVerDesaparecidos()
	{}
	
	public VistaVerDesaparecidos(Desaparecido desaparecido)
	{
		this.retornarDesaparecido(desaparecido, "");
		initWidget(content);
	}
	public VistaVerDesaparecidos(String documento)
	{
		this.retornarDesaparecido(null, documento);
		initWidget(content);
	}
	
	public HTMLPanel retornarDesaparecido(Desaparecido desaparecido, String documento)
	{	
		content = new HTMLPanel("");
		content.setStyleName("verDesaparecido");
		
		final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		RootPanel.get("content").add(cargando);
		
		if(!documento.isEmpty())
		{
			DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
			desaparecidoService.getDesaparecido(documento, new AsyncCallback<Desaparecido>()
			{
			    public void onSuccess(final Desaparecido desaparecido) 
			    {
    				RootPanel.get("verDesaparecido").add(devolverDatosPersonales(desaparecido));
    				RootPanel.get("verDesaparecido").add(devolverMorfologia(desaparecido));
    				RootPanel.get("verDesaparecido").add(devolverSenales(desaparecido.getSenalParticular()));
    				RootPanel.get("verDesaparecido").add(devolverPrendas(desaparecido.getPrendaVestir()));
    				RootPanel.get("verDesaparecido").add(devolverDatoDesaparicion(desaparecido.getDatoDesaparicion()));
			    	cargando.getElement().setAttribute("style", "display:none");
			    	
					final HTMLPanel divBotones = new HTMLPanel("");
					content.add(divBotones);
					
					DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
				    desaparecidoService.validarDesaparecidoUsuario(desaparecido.getId(), new AsyncCallback<Desaparecido>() 
				    {
				    	@Override
						public void onSuccess(Desaparecido valido) 
				    	{
				    		if(desaparecido != null)
				    		{
				    			Button editarUsuario = new Button("Actualizar Datos");
				    			divBotones.add(editarUsuario);
				    			editarUsuario.addClickHandler(new ClickHandler() 
				    			{
				    				public void onClick(ClickEvent event) 
				    				{
				    					new Utilidades().crearSesion("keyDesaparecido", desaparecido.getId());
				    					History.newItem("editar-desaparecido");
				    				}
				    			});
				    		}
						}
				    	public void onFailure(Throwable error) 
				    	{
				    	}
				    });
			    }
			    public void onFailure(Throwable error) 
				{
			    	cargando.getElement().setAttribute("style", "display:none");
			    	HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron resultados que coincidan con su busqueda.</p>");
		    		sinResultados.setStyleName("sinResultados");
			    	RootPanel.get("verDesaparecido").add(sinResultados);
				}
	        });
		}
		else if(desaparecido == null)
		{
			DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
			desaparecidoService.getDesaparecidos((byte)0, new AsyncCallback<ArrayList<Desaparecido>>()
			{
			    public void onSuccess(ArrayList<Desaparecido> desaparecidos) 
			    {
			    	content.add(mostarTodos(desaparecidos));
			    	cargando.getElement().setAttribute("style", "display:none");
			    }
			    public void onFailure(Throwable error) 
				{
			    	cargando.getElement().setAttribute("style", "display:none");
			    	HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron resultados que coincidan con su busqueda.</p>");
		    		sinResultados.setStyleName("sinResultados");
		    		content.add(sinResultados);
				}
	        });
		}
		else
		{			
			content.add(this.devolverDatosPersonales(desaparecido));
			content.add(this.devolverMorfologia(desaparecido));
			content.add(this.devolverSenales(desaparecido.getSenalParticular()));
			content.add(this.devolverPrendas(desaparecido.getPrendaVestir()));
			content.add(this.devolverDatoDesaparicion(desaparecido.getDatoDesaparicion()));
			
			cargando.getElement().setAttribute("style", "display:none");
		}
		return content;
	}
	
	@SuppressWarnings("deprecation")
	public CaptionPanel devolverDatosPersonales(Desaparecido desaparecido)
	{
		CaptionPanel fieldsetPersonales = new CaptionPanel("Datos Personales");
		HTMLPanel divPersonales = new HTMLPanel("");
		
		HTMLPanel divImagen = new HTMLPanel("");
		divImagen.setStyleName("imagen");
		
		Image image = new Image();
		image.setWidth("210px");
		
		image.setUrl(desaparecido.getKeyFoto());
		
    	divImagen.add(image);
    	
		HTMLPanel datos = new HTMLPanel("");
		datos.setStyleName("datos");
		
		HTML nombre = new HTML("<h2>" + desaparecido.getNombre1() + " " + desaparecido.getNombre2() + " " + 
								desaparecido.getApellido1() + " " + desaparecido.getApellido2() + "</h2>");
		
		Label sexo = new Label("Sexo:");
		sexo.addStyleName("negrita");
		Label valueSexo = null;
		
		if(desaparecido.getGenero())
			valueSexo = new Label("Masculino");
		else
			valueSexo = new Label("Femenino");
		
		Label lugarNacimiento = new Label("Lugar de Nacimiento:");
		lugarNacimiento.addStyleName("negrita");
		Label valueLugar = new Label(desaparecido.getCiudadNacimiento().getNombre() + ",  \u0020" + desaparecido.getCiudadNacimiento().getDepartamento().getNombre() + ", \u0020" + desaparecido.getCiudadNacimiento().getDepartamento().getPais().getNombre());
		
		Label fechaNacimiento = new Label("Fecha de Nacimiento:");
		fechaNacimiento.addStyleName("negrita");
		Label valueFecha = null;
		
		String mes = "";
		
		switch (desaparecido.getFechaNacimiento().getMonth()) 
		{
			case(0):
			{
				mes = "Enero";
				break;
			}
			case(1):
			{
				mes = "Febrero";
				break;
			}
			case(2):
			{
				mes = "Marzo";
				break;
			}
			case(3):
			{
				mes = "Abril";
				break;
			}
			case(4):
			{
				mes = "Mayo";
				break;
			}
			case(5):
			{
				mes = "Junio";
				break;
			}
			case(6):
			{
				mes = "Julio";
				break;
			}
			case(7):
			{
				mes = "Agosto";
				break;
			}
			case(8):
			{
				mes = "Septiembre";
				break;
			}
			case(9):
			{
				mes = "Octubre";
				break;
			}
			case(10):
			{
				mes = "Noviembre";
				break;
			}
			case(11):
			{
				mes = "Diciembre";
				break;
			}
			default:
			{
				break;
			}
		}
		
		if(desaparecido.getFechaNacimiento() != null)
			valueFecha = new Label(desaparecido.getFechaNacimiento().getDate() + " de " + mes + " de " + (desaparecido.getFechaNacimiento().getYear()+1900));
		else
			valueFecha = new Label("No registrada");
		
		Label edad = new Label("Edad al momento de la desaparici\u00F3n:");
		edad.addStyleName("negrita");
		Label valueEdad = new Label(desaparecido.getEdad() + " A\u0148os");
		
		Label edadActual = new Label("Edad Actual:");
		edadActual.addStyleName("negrita");
		Label valueEdadActual = new Label(desaparecido.getEdad() + " A\u0148os");
		
		datos.add(nombre);
		datos.add(sexo);
		datos.add(valueSexo);
		datos.add(lugarNacimiento);
		datos.add(valueLugar);
		datos.add(fechaNacimiento);
		datos.add(valueFecha);
		datos.add(edad);
		datos.add(valueEdad);
		datos.add(edadActual);
		datos.add(valueEdadActual);
		
		divPersonales.add(divImagen);
		divPersonales.add(datos);
		fieldsetPersonales.add(divPersonales);
		
		return fieldsetPersonales;
	}
	
	public CaptionPanel devolverMorfologia(Desaparecido desaparecido)
	{
		CaptionPanel fieldsetMorfologia = new CaptionPanel("Morfolog\u00EDa");
		fieldsetMorfologia.setStyleName("senales");
		
		HTMLPanel divMorfologia = new HTMLPanel("");
		ArrayList<Morfologia> listMorfologia = desaparecido.getMorfologia();
		
		boolean pesoEstatura = false;
		
		ArrayList<String> tipos = new ArrayList<String>();
		tipos.add("General");
		tipos.add("Cara");
		tipos.add("Cabello");
		tipos.add("Ojos");
		tipos.add("Barba");
		tipos.add("Bigote");
		
		for(byte i=0; i < tipos.size(); i++)
		{
			HTMLPanel panel = new HTMLPanel("<h3>" + tipos.get(i) + "</h3>");
			int con = 0;
			
			if(listMorfologia != null && listMorfologia.size() > 0)
			{
				for(byte x=0;x < listMorfologia.size(); x++)
				{
					Morfologia otro = listMorfologia.get(x);
					
					if(tipos.get(i).equals(otro.getTipo()))
					{
						if(tipos.get(i).equals("General") && !pesoEstatura)
						{
							if(!desaparecido.getPeso().isEmpty())
							{
								HTML etiqueta = new HTML("<span class='vineta'></span><b> Peso: </b><span>" + desaparecido.getPeso() + " Kgs</span>");
								panel.add(etiqueta);
							}
							if(!desaparecido.getEstatura().isEmpty())
							{
								HTML etiqueta = new HTML("<span class='vineta'></span><b> Estatura: </b><span>" + desaparecido.getEstatura() + " Cms</span>");
								panel.add(etiqueta);
							}
							
							pesoEstatura = true;
						}
						HTML etiqueta = new HTML("<span class='vineta'></span><b>" + otro.getCaracteristica() + ": </b><span>" + otro.getNombre() + "</span>");
						panel.add(etiqueta);
						con++;
					}
				}
				if(con == 0)
				{
					Label sinInformacion = new Label("Sin Informaci\u00F3n");
					panel.add(sinInformacion);
				}
				for(byte j=0;j < con; j++)
				{
					listMorfologia.remove(0);
				}
				con=0;
			}
			else
			{
				Label sinInformacion = new Label("Sin Informaci\u00F3n");
				panel.add(sinInformacion);
			}
			
			divMorfologia.add(panel);
		}
		
		fieldsetMorfologia.add(divMorfologia);
		
		return fieldsetMorfologia;
	}

	public CaptionPanel devolverSenales(ArrayList<SenalParticular> listado)
	{
		CaptionPanel fieldsetSenales = new CaptionPanel("Se\u0148ales Particulares");
		HTMLPanel divSenales = new HTMLPanel("");
		
		if(listado != null && listado.size() > 0)
		{
			/* Encabezado de la Tabla Señales Particulares */
	        HTMLPanel divEncabezado 				= new HTMLPanel(" ");
	        divEncabezado.setStyleName("encabezado-senales");
	        
	  		Label lblTipo							= new Label("Tipo");
	  		Label lblUbicacion						= new Label("Ubicaci\u00F3n");
	  		Label lblCaract							= new Label("Caracter\u00EDsticas");
	  		
	  		divEncabezado.add(lblTipo);
	  		divEncabezado.add(lblUbicacion);
	  		divEncabezado.add(lblCaract);
	  		
	  		divSenales.add(divEncabezado);
	  		
	  		for(byte i = 0; i < listado.size(); i++)
	  		{
	  			HTMLPanel divOpcion 				= new HTMLPanel(" ");
	  		    divOpcion.setStyleName("opcion-senales");
	  		    
	  		    SenalParticular nueva = listado.get(i);
	  		    
	  	  		Label nombreSenal					= new Label(nueva.getNombre());
	  	  		Label ubicacion						= new Label(nueva.getUbicacion());
	  	  		Label caracteristica				= new Label(nueva.getCaracteristica());
	  	  		
	  	  		HTMLPanel o1 = new HTMLPanel("");
	  	  		HTMLPanel o2 = new HTMLPanel("");
	  	  		HTMLPanel o3 = new HTMLPanel("");
	  	  		
	  	  		o1.add(nombreSenal);
	  	  		o2.add(ubicacion);
	  	  		o3.add(caracteristica);
	  	  		
	  	  		divOpcion.add(o1);
	  	  		divOpcion.add(o2);
	  	  		divOpcion.add(o3);
	  			
	  	  		divSenales.add(divOpcion);
	  		}
		}
		else
		{
			Label sinRegistros = new Label("No tiene informaci\u00F3n registrada");
			divSenales.add(sinRegistros);
		}

  		
  		fieldsetSenales.add(divSenales);
  		
  		return fieldsetSenales;
	}
	
	public CaptionPanel devolverPrendas(ArrayList<PrendaVestir> prendas)
	{
		CaptionPanel fieldsetPrendas = new CaptionPanel("Prendas de Vestir");
		HTMLPanel divPrendas = new HTMLPanel("");
		
		if(prendas != null && prendas.size() > 0)
		{
			/* Encabezado de la Tabla Prendas de vestir */
	        HTMLPanel divEncabezado 				= new HTMLPanel(" ");
	        divEncabezado.setStyleName("encabezado-senales");
	        
	  		Label lblTipo							= new Label("Prenda");
	  		Label lblCaracteristica					= new Label("Caracter\u00EDsticas");
	  		Label lblObservacion					= new Label("Observaci\u00F3n");
	  		
	  		divEncabezado.add(lblTipo);
	  		divEncabezado.add(lblCaracteristica);
	  		divEncabezado.add(lblObservacion);
	  		
	  		divPrendas.add(divEncabezado);
	  		
	  		for(byte i = 0; i < prendas.size(); i++)
	  		{
	  			HTMLPanel divOpcion 				= new HTMLPanel(" ");
	  		    divOpcion.setStyleName("opcion-senales");
	  		    
	  		    PrendaVestir nueva = prendas.get(i);
	  		    
	  	  		Label nombreSenal					= new Label(nueva.getNombre());
	  	  		Label caracteristica				= new Label(nueva.getCaracteristica());
	  	  		Label observacion					= new Label(nueva.getObservacion());
	  	  		
	  	  		HTMLPanel o1 = new HTMLPanel("");
	  	  		HTMLPanel o2 = new HTMLPanel("");
	  	  		HTMLPanel o3 = new HTMLPanel("");
	  	  		
	  	  		o1.add(nombreSenal);
	  	  		o2.add(caracteristica);
	  	  		o3.add(observacion);
	  	  		
	  	  		divOpcion.add(o1);
	  	  		divOpcion.add(o2);
	  	  		divOpcion.add(o3);
	  			
	  	  		divPrendas.add(divOpcion);
	  		}
		}
		else
		{
			Label sinRegistros = new Label("No tiene informaci\u00F3n registrada");
			divPrendas.add(sinRegistros);
		}

  		
  		fieldsetPrendas.add(divPrendas);
  		
  		return fieldsetPrendas;
	}
	
	public CaptionPanel devolverDatoDesaparicion(DatoDesaparicion dato)
	{
		CaptionPanel fieldsetDesaparicion = new CaptionPanel("Datos de Desaparici\u00F3n");
		HTMLPanel divDesaparicion = new HTMLPanel("");
		divDesaparicion.setStyleName("mostrar-desaparicion");
		
		Label fechaDesaparicion		= new Label("Fecha de la Desaparicion");
	  	Label lugarDesaparicion		= new Label("Lugar de la Desaparici\u00F3n");
	  	Label inspeccion			= new Label("Inspecci\u00F3n Donde Radic\u00F3 la Denuncia");
	  	Label descripcionHecho		= new Label("Descripci\u00F3n del Hecho");
	  	
	  	Label fechaDesaparicionVal	= new Label(dato.getFechaDesaparicion().toString());
	  	Label lugarDesaparicionVal	= new Label(dato.getCiudadDesaparicion().getNombre() + ", \u0020" + dato.getCiudadDesaparicion().getDepartamento().getNombre() + ", \u0020" + dato.getCiudadDesaparicion().getDepartamento().getPais().getNombre());
	  	Label inspeccionVal			= new Label(dato.getInspeccionPolicia());
	  	Label descripcionHechoVal	= new Label(dato.getDescripcion());
		
	  	fechaDesaparicion.addStyleName("negrita");
	  	lugarDesaparicion.addStyleName("negrita");
	  	inspeccion.addStyleName("negrita");
	  	descripcionHecho.addStyleName("negrita");
	  	
	  	divDesaparicion.add(fechaDesaparicion);
	  	divDesaparicion.add(fechaDesaparicionVal);
	  	divDesaparicion.add(lugarDesaparicion);
	  	divDesaparicion.add(lugarDesaparicionVal);
	  	divDesaparicion.add(inspeccion);
	  	divDesaparicion.add(inspeccionVal);
	  	divDesaparicion.add(descripcionHecho);
	  	divDesaparicion.add(descripcionHechoVal);
	  	
	  	fieldsetDesaparicion.add(divDesaparicion);
	  	
	  	return fieldsetDesaparicion; 
	}
	
	public HTMLPanel mostarTodos(final ArrayList<Desaparecido> desaparecidos)
	{	
		HTMLPanel listaDesaparecidos = new HTMLPanel("");
		for(byte i=0; i < desaparecidos.size(); i++)
    	{
			final Desaparecido desaparecido = desaparecidos.get(i);
			
    		HTMLPanel figure = new HTMLPanel("");
    		HTMLPanel divImagen = new HTMLPanel("");
    		figure.setStyleName("figure");
    		
    		Image image = new Image();
    		image.setUrl(desaparecidos.get(i).getKeyFoto());
    		
    		HTML figcaption = new HTML(desaparecido.getNombre1() + " " + desaparecido.getNombre2() + " " + desaparecido.getApellido1() + " " +
    							  				 desaparecido.getApellido2());
    		figcaption.setStyleName("figcaption");
    		
    		HTML zonaClick = new HTML();
    		zonaClick.setStyleName("zonaClick");
    		
    		divImagen.add(image);
    		figure.add(zonaClick);
    		figure.add(divImagen);
    		figure.add(figcaption);
    		listaDesaparecidos.add(figure);
    		
    		zonaClick.addClickHandler(new ClickHandler() 
    		{
    			public void onClick(final ClickEvent event) 
    			{
    				History.newItem("-" + desaparecido.getId());
    				//RootPanel.get("verDesaparecido").add(devolverDatosPersonales(desaparecido));
    				//RootPanel.get("verDesaparecido").add(devolverMorfologia(desaparecido));
    				//RootPanel.get("verDesaparecido").add(devolverSenales(desaparecido.getSenalParticular()));
    				//RootPanel.get("verDesaparecido").add(devolverDatoDesaparicion(desaparecido.getDatoDesaparicion()));
    			}
    		});
    	}
		return listaDesaparecidos;
	}
}