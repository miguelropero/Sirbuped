package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Morfologia;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VistaConsultarDesaparecido extends Composite 
{
	public VistaConsultarDesaparecido()
	{
		HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("desaparecido");
		
		final ArrayList<RadioButton> morfologiaRadio = new ArrayList<RadioButton>();
		final ArrayList<CheckBox> morfologiaCheckBox = new ArrayList<CheckBox>();
		
		/* Datos Personales */
		HTMLPanel divPersonales = new HTMLPanel("");
		divPersonales.setStyleName("extend-consulta");
		final Button btnPersonales = new Button("-");
		Label lblPersonales = new Label("Datos Personales");
		divPersonales.add(btnPersonales);
		divPersonales.add(lblPersonales);
		
		final HTMLPanel personales = new HTMLPanel("");
		personales.add(this.datosPersonales());
		personales.setVisible(true);
		
		/* Morfologia */
		
		HTMLPanel divMorfologia = new HTMLPanel("");
		divMorfologia.setStyleName("extend-consulta");
		final Button btnMorfologia = new Button("+");
		Label lblMorfologia = new Label("Datos Morfologicos");
		divMorfologia.add(btnMorfologia);
		divMorfologia.add(lblMorfologia);
		
		final HTMLPanel morfologia = new VistaDesaparecido().datosMorfologicos(morfologiaRadio, morfologiaCheckBox);
		morfologia.setVisible(false);
		
		/* Señales Particulares */
		HTMLPanel divSenales = new HTMLPanel("");
		divSenales.setStyleName("extend-consulta");
		final Button btnSenales = new Button("+");
		Label lblSenales = new Label("Senales Particulares");
		divSenales.add(btnSenales);
		divSenales.add(lblSenales);
		
		final HTMLPanel senales = new HTMLPanel("<h1>Senales Particulares</h1>");
		senales.setVisible(false);
		
		/* Prendas de vestir */
		HTMLPanel divPrendas = new HTMLPanel("");
		divPrendas.setStyleName("extend-consulta");
		final Button btnPrendas = new Button("+");
		Label lblPrendas = new Label("Prendas de Vestir");
		divPrendas.add(btnPrendas);
		divPrendas.add(lblPrendas);
		
		final HTMLPanel prendas = new HTMLPanel("<h1>Senales Particulares</h1>");
		prendas.addStyleName("animacion");
		prendas.setVisible(false);
		
		
		btnPersonales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(personales.isVisible())
				{
					btnPersonales.setText("+");
					personales.setVisible(false);
				}
				else
				{
					btnPersonales.setText("-");
					personales.setVisible(true);
					morfologia.setVisible(false);
					senales.setVisible(false);
				}
			}
		});
		
		btnMorfologia.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(morfologia.isVisible())
				{
					btnMorfologia.setText("+");
					morfologia.setVisible(false);
				}
				else
				{
					btnMorfologia.setText("-");
					morfologia.setVisible(true);
					senales.setVisible(false);
				}
			}
		});
		
		btnSenales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(senales.isVisible())
				{
					btnSenales.setText("+");
					senales.setVisible(false);
				}
				else
				{
					btnSenales.setText("-");
					senales.setVisible(true);
					morfologia.setVisible(false);
				}
			}
		});
		
		subContent.add(divPersonales);
		subContent.add(personales);
		subContent.add(divMorfologia);
		subContent.add(morfologia);
		subContent.add(divSenales);
		subContent.add(senales);
		
		Button btnConsultar	= new Button("Consultar");
		subContent.add(btnConsultar);
		
		/* Panel de Resultados de busqueda */
		final HTMLPanel divResultados = new HTMLPanel("");
		divResultados.setStyleName("verDesaparecido");
		divResultados.getElement().setId("verDesaparecido");
		subContent.add(divResultados);
		
		btnConsultar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				Desaparecido desaparecido = new Desaparecido();
				
				if(!morfologiaRadio.isEmpty())
				{
					for(int i=0;i<morfologiaRadio.size();i++)
					{
						if(morfologiaRadio.get(i).getValue()==true)
						{
							Morfologia morfologia = new Morfologia();
							morfologia.setId(morfologiaRadio.get(i).getElement().getAttribute("identificador"));
							morfologia.setNombre(morfologiaRadio.get(i).getText());
							morfologia.setTipo(morfologiaRadio.get(i).getElement().getAttribute("tipo"));
							morfologia.setCaracteristica(morfologiaRadio.get(i).getElement().getAttribute("caracterictica"));
							desaparecido.getMorfologia().add(morfologia);
						}
					}
				}
				
				if(!morfologiaCheckBox.isEmpty())
				{
					for(int i=0;i<morfologiaCheckBox.size();i++)
					{
						if(morfologiaCheckBox.get(i).getValue())
						{
							Morfologia morfologia = new Morfologia();
							morfologia.setId(morfologiaCheckBox.get(i).getElement().getAttribute("identificador"));
							morfologia.setNombre(morfologiaCheckBox.get(i).getText());
							morfologia.setTipo(morfologiaCheckBox.get(i).getElement().getAttribute("tipo"));
							morfologia.setCaracteristica(morfologiaCheckBox.get(i).getElement().getAttribute("caracterictica"));
							desaparecido.getMorfologia().add(morfologia);
						}
					}
				}
				
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				elemento = DOM.getElementById("nombre1");
				esWidget = getWidget(elemento);
				TextBox nombre1 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("nombre2");
				esWidget = getWidget(elemento);
				TextBox nombre2 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("apellido1");
				esWidget = getWidget(elemento);
				TextBox apellido1 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("apellido2");
				esWidget = getWidget(elemento);
				TextBox apellido2 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("tipoDocumento");
				esWidget = getWidget(elemento);
				ListBox tipoDocumento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("numeroDocumento");
				esWidget = getWidget(elemento);
				TextBox numeroDocumento = (TextBox) esWidget;
				
				elemento = DOM.getElementById("edad");
				esWidget = getWidget(elemento);
				TextBox edad = (TextBox) esWidget;
				
				String tipo = "";
				if(tipoDocumento.getSelectedIndex() != 0)
					tipo = tipoDocumento.getItemText(tipoDocumento.getSelectedIndex());
				
				desaparecido.setNombre1(nombre1.getValue());
				desaparecido.setNombre2(nombre2.getValue());
				desaparecido.setApellido1(apellido1.getValue());
				desaparecido.setApellido2(apellido2.getValue());
				desaparecido.setTipoDocumento(tipo);
				desaparecido.setNumeroDocumento(numeroDocumento.getValue());
				
				if(edad.getValue().isEmpty())
					desaparecido.setEdad((byte)0);
				else
					desaparecido.setEdad(Byte.parseByte(edad.getValue()));
				
				btnPersonales.setText("+");
				personales.setVisible(false);
				btnMorfologia.setText("+");
				morfologia.setVisible(false);
				btnSenales.setText("+");
				senales.setVisible(false);
				
				RootPanel.get("verDesaparecido").clear();
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("verDesaparecido").add(cargando);
				
				DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
				desaparecidoService.consultarDesaparecido(desaparecido, new AsyncCallback<ArrayList<Desaparecido>>() 
				{
				    public void onSuccess(ArrayList<Desaparecido> results) 
				    {
				    	
				    	if(results.size() > 0)
				    	{
					    	RootPanel.get("verDesaparecido").add(new VistaVerDesaparecidos().mostarTodos(results));
					    	cargando.getElement().setAttribute("style", "display:none");
				    	}
				    	else
				    	{
				    		HTMLPanel sinResultados = new HTMLPanel("<h3>¡Oppps!</h3><br><p>Los sentimos. No se encontraron resultados que coincidan con su busqueda.</p>");
				    		sinResultados.setStyleName("sinResultados");
				    		RootPanel.get("verDesaparecido").add(sinResultados);
				    		cargando.getElement().setAttribute("style", "display:none");
				    	}
				    }
				    public void onFailure(Throwable error) 
					{
						Window.alert(error.toString());
					}
		        });
				
			}
		});
		
		initWidget(subContent);
	}
	
	
	
	public CaptionPanel datosPersonales()
	{		
		CaptionPanel fieldsetPersonales = new CaptionPanel();

		HTMLPanel divDatosPersonalesG 	= new HTMLPanel(" ");
		HTMLPanel divDatosPersonales1 	= new HTMLPanel(" ");
		HTMLPanel divDatosPersonales2 	= new HTMLPanel(" ");
		
		Label lblNombre1				= new Label("Primer Nombre: *");
		Label lblNombre2 				= new Label("Segundo Nombre:");
		Label lblApellido1 				= new Label("Primer Apellido: *");
		Label lblApellido2 				= new Label("Segundo Apellido:");
		Label lblDocumento 				= new Label("Tipo de Documento: *");
		Label lblNdocumento 			= new Label("N\u00FAmero de Documento: *");
		Label lblGenero 				= new Label("G\u00E9nero: *");
		Label lblEdad					= new Label("Edad: *");
		
		TextBox textNombre1 			= new TextBox();
		TextBox textNombre2 			= new TextBox();
		TextBox textApellido1 			= new TextBox();
		TextBox textApellido2 			= new TextBox();
		ListBox selectTipoDocument		= new ListBox();
		final TextBox textDocumento 	= new TextBox();
		ListBox selectGenero 			= new ListBox();
		final TextBox textEdad 			= new TextBox();
		
		textNombre1.getElement().setId("nombre1");
		textNombre2.getElement().setId("nombre2");
		textApellido1.getElement().setId("apellido1");
		textApellido2.getElement().setId("apellido2");
		selectTipoDocument.getElement().setId("tipoDocumento");
		textDocumento.getElement().setId("numeroDocumento");
		selectGenero.getElement().setId("genero");
		textEdad.getElement().setId("edad");
		
		selectTipoDocument.addItem("Seleccione...");
	  	selectTipoDocument.addItem("C\u00E9dula de Ciudadania");
		selectTipoDocument.addItem("C\u00E9dula Extrangera");
		selectTipoDocument.addItem("Tarjeta de Identidad");
		selectTipoDocument.addItem("Pasaporte");
	    
	    selectGenero.addItem("Seleccione...");
		selectGenero.addItem("Masculino");
		selectGenero.addItem("Femenino");
		
		textNombre1.setMaxLength(20);
		textNombre2.setMaxLength(20);
		textApellido1.setMaxLength(20);
		textApellido2.setMaxLength(20);
		textDocumento.setMaxLength(10);
		textEdad.setMaxLength(2);
		textEdad.getElement().setAttribute("placeHolder", "al momento de la desaparici\u00F3n");
		
		divDatosPersonales1.add(lblNombre1);
		divDatosPersonales1.add(textNombre1);
		divDatosPersonales1.add(lblNombre2);
		divDatosPersonales1.add(textNombre2);
		divDatosPersonales1.add(lblApellido1);
		divDatosPersonales1.add(textApellido1);
		divDatosPersonales1.add(lblApellido2);
		divDatosPersonales1.add(textApellido2);
		divDatosPersonales2.add(lblDocumento);
		divDatosPersonales2.add(selectTipoDocument);
		divDatosPersonales2.add(lblNdocumento);
		divDatosPersonales2.add(textDocumento);
		divDatosPersonales2.add(lblGenero);
		divDatosPersonales2.add(selectGenero);
		divDatosPersonales2.add(lblEdad);
		divDatosPersonales2.add(textEdad);
				
		divDatosPersonalesG.add(divDatosPersonales1);
		divDatosPersonalesG.add(divDatosPersonales2);
		divDatosPersonalesG.setStyleName("divPersonales");
		
		fieldsetPersonales.add(divDatosPersonalesG);
		
		textDocumento.addBlurHandler(new BlurHandler()
	    {
	        @Override
	        public void onBlur(BlurEvent event)
	        {
		        try
		        {
		        	if(!textDocumento.getValue().isEmpty())
		        		Integer.parseInt(textDocumento.getValue());
		        }
		        catch(NumberFormatException ex)
		        {
		        	new Utilidades().ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico, y corresponde al numero de identidad de la" +
		        				 " persona desaparecida", "error");
		        	textDocumento.setText("");
		        }
		    }
		});
		
		textEdad.addBlurHandler(new BlurHandler()
	    {
	        @Override
	        public void onBlur(BlurEvent event)
	        {
		        try
		        {
		        	if(!textEdad.getValue().isEmpty())
		        		Integer.parseInt(textEdad.getValue());
		        }
		        catch(NumberFormatException ex)
		        {
		        	new Utilidades().ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico, comprendido entre los n\u00FAmeros" +
		        				 " 1 y 99", "error");
		        	textEdad.setText("");
		        }
		    }
		});
		
		return fieldsetPersonales;
	}
	
	/* Metodo que permite validar si el Objeto Element que llega como Parametro 
	 * es un Widget.
	 */
	public static IsWidget getWidget(com.google.gwt.user.client.Element element) 
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
