package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Morfologia;
import com.appspot.sirbuped.client.DTO.PrendaVestir;
import com.appspot.sirbuped.client.DTO.SenalParticular;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
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

public class VistaDesaparecidoConsultar extends Composite 
{
	public VistaDesaparecidoConsultar()
	{
		
		final Utilidades utilidades = new Utilidades(); 
		
		HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("desaparecido");
		
		final ArrayList<RadioButton> morfologiaRadio = new ArrayList<RadioButton>();
		final ArrayList<CheckBox> morfologiaCheckBox = new ArrayList<CheckBox>();
		final ArrayList<CheckBox> senalesCheckBox = new ArrayList<CheckBox>();
		final ArrayList<CheckBox> prendasCheckBox = new ArrayList<CheckBox>();
		
		/* Datos Personales */
		HTMLPanel divPersonales = new HTMLPanel("");
		divPersonales.setStyleName("extend-consulta");
		final Button btnPersonales = new Button("<span>-</span><span>Datos Personales</span>");
		Label lblPersonales = new Label("Datos Personales");
		divPersonales.add(btnPersonales);
		divPersonales.add(lblPersonales);
		
		final HTMLPanel personales = new HTMLPanel("");
		personales.add(this.datosPersonales());
		personales.setVisible(true);
		
		/* Morfologia */
		
		HTMLPanel divMorfologia = new HTMLPanel("");
		divMorfologia.setStyleName("extend-consulta");
		final Button btnMorfologia = new Button("<span>+</span><span>Datos Morfol\u00F3gicos</span>");
		Label lblMorfologia = new Label("Datos Morfol\u00F3gicos");
		divMorfologia.add(btnMorfologia);
		divMorfologia.add(lblMorfologia);
		
		final HTMLPanel morfologia = new VistaDesaparecido().datosMorfologicos(morfologiaRadio, morfologiaCheckBox);
		morfologia.setVisible(false);
		
		/* Señales Particulares */
		HTMLPanel divSenales = new HTMLPanel("");
		divSenales.setStyleName("extend-consulta");
		final Button btnSenales = new Button("<span>+</span><span>Se\u0148ales Particulares</span>");
		Label lblSenales = new Label("Se\u0148ales Particulares");
		divSenales.add(btnSenales);
		divSenales.add(lblSenales);
		
		final HTMLPanel senales = new HTMLPanel("");
		senales.add(this.senalesParticulares(senalesCheckBox));
		senales.setVisible(false);
		
		/* Prendas de vestir */
		HTMLPanel divPrendas = new HTMLPanel("");
		divPrendas.setStyleName("extend-consulta");
		final Button btnPrendas = new Button("+");
		Label lblPrendas = new Label("Prendas de Vestir");
		divPrendas.add(btnPrendas);
		divPrendas.add(lblPrendas);
		
		final HTMLPanel prendas = new HTMLPanel("");
		prendas.add(this.prendasDeVestir(prendasCheckBox));
		prendas.setVisible(false);
		
		
		btnPersonales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(personales.isVisible())
				{
					btnPersonales.setHTML("<span>+</span><span>Datos Personales</span>");
					personales.setVisible(false);
				}
				else
				{
					btnPersonales.setHTML("<span>-</span><span>Datos Personales</span>");
					personales.setVisible(true);
					morfologia.setVisible(false);
					senales.setVisible(false);
					prendas.setVisible(false);
				}
			}
		});
		
		btnMorfologia.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(morfologia.isVisible())
				{
					btnMorfologia.setHTML("<span>+</span><span>Datos Morfol\u00F3gicos</span>");
					morfologia.setVisible(false);
				}
				else
				{
					btnMorfologia.setHTML("<span>-</span><span>Datos Morfol\u00F3gicos</span>");
					morfologia.setVisible(true);
					personales.setVisible(false);
					senales.setVisible(false);
					prendas.setVisible(false);
				}
			}
		});
		
		btnSenales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(senales.isVisible())
				{
					btnSenales.setHTML("<span>+</span><span>Se\u0148ales Particulares</span>");
					senales.setVisible(false);
				}
				else
				{
					btnSenales.setHTML("<span>-</span><span>Se\u0148ales Particulares</span>");
					senales.setVisible(true);
					personales.setVisible(false);
					morfologia.setVisible(false);
					prendas.setVisible(false);
				}
			}
		});
		
		btnPrendas.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(prendas.isVisible())
				{
					btnPrendas.setHTML("<span>+</span><span>Prendas de Vestir</span>");
					prendas.setVisible(false);
				}
				else
				{
					btnPrendas.setHTML("<span>-</span><span>Prendas de Vestir</span>");
					prendas.setVisible(true);
					personales.setVisible(false);
					morfologia.setVisible(false);
					senales.setVisible(false);
				}
			}
		});
		
		subContent.add(divPersonales);
		subContent.add(personales);
		subContent.add(divMorfologia);
		subContent.add(morfologia);
		subContent.add(divSenales);
		subContent.add(senales);
		subContent.add(divPrendas);
		subContent.add(prendas);
		
		/* Botones */
		Button btnConsultar	= new Button("Consultar");
		subContent.add(btnConsultar);
		
		Button btnLimpiar	= new Button("Limpiar");
		subContent.add(btnLimpiar);
		
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
							morfologia.setConsecutivo(morfologiaRadio.get(i).getElement().getAttribute("identificador"));
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
							morfologia.setConsecutivo(morfologiaCheckBox.get(i).getElement().getAttribute("identificador"));
							morfologia.setNombre(morfologiaCheckBox.get(i).getText());
							morfologia.setTipo(morfologiaCheckBox.get(i).getElement().getAttribute("tipo"));
							morfologia.setCaracteristica(morfologiaCheckBox.get(i).getElement().getAttribute("caracterictica"));
							desaparecido.getMorfologia().add(morfologia);
						}
					}
				}
				
				if(!senalesCheckBox.isEmpty())
				{
					for(int i=0;i<senalesCheckBox.size();i++)
					{
						if(senalesCheckBox.get(i).getValue())
						{
							SenalParticular senal = new SenalParticular();
							senal.setNombre(senalesCheckBox.get(i).getText());
							desaparecido.getSenalParticular().add(senal);
						}
					}
				}
				
				if(!prendasCheckBox.isEmpty())
				{
					for(int i=0; i < prendasCheckBox.size();i++)
					{
						if(prendasCheckBox.get(i).getValue())
						{
							PrendaVestir prenda = new PrendaVestir();
							prenda.setNombre(prendasCheckBox.get(i).getText());
							desaparecido.getPrendaVestir().add(prenda);
						}
					}
				}
				
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				elemento = DOM.getElementById("nombre1");
				esWidget = utilidades.getWidget(elemento);
				TextBox nombre1 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("nombre2");
				esWidget = utilidades.getWidget(elemento);
				TextBox nombre2 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("apellido1");
				esWidget = utilidades.getWidget(elemento);
				TextBox apellido1 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("apellido2");
				esWidget = utilidades.getWidget(elemento);
				TextBox apellido2 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("tipoDocumento");
				esWidget = utilidades.getWidget(elemento);
				ListBox tipoDocumento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("numeroDocumento");
				esWidget = utilidades.getWidget(elemento);
				TextBox numeroDocumento = (TextBox) esWidget;
				
				elemento = DOM.getElementById("edad");
				esWidget = utilidades.getWidget(elemento);
				TextBox edad = (TextBox) esWidget;
				
				String tipo = "";
				if(tipoDocumento.getSelectedIndex() != 0)
					tipo = tipoDocumento.getItemText(tipoDocumento.getSelectedIndex());
				
				elemento = DOM.getElementById("genero");
				esWidget = utilidades.getWidget(elemento);
				ListBox listGenero = (ListBox) esWidget;
				String genero = "";
				
				if(listGenero.getSelectedIndex() > 0)
					genero = listGenero.getValue(listGenero.getSelectedIndex());
				
				desaparecido.setNombre1(nombre1.getValue());
				desaparecido.setNombre2(nombre2.getValue());
				desaparecido.setApellido1(apellido1.getValue());
				desaparecido.setApellido2(apellido2.getValue());
				desaparecido.setTipoDocumento(tipo);
				desaparecido.setNumeroDocumento(numeroDocumento.getValue());
				desaparecido.setGenero(genero);
				
				if(edad.getValue().isEmpty())
					desaparecido.setEdad(0);
				else
					desaparecido.setEdad(Byte.parseByte(edad.getValue()));
				
				btnPersonales.setHTML("<span>+</span><span>Datos Personales</span>");
				personales.setVisible(false);
				btnMorfologia.setHTML("<span>+</span><span>Datos Morfol\u00F3gicos</span>");
				morfologia.setVisible(false);
				btnSenales.setHTML("<span>+</span><span>Se\u0148ales Particulares</span>");
				senales.setVisible(false);
				btnPrendas.setHTML("<span>+</span><span>Prendas de Vestir</span>");
				prendas.setVisible(false);
				
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
					    	RootPanel.get("verDesaparecido").add(new VistaDesaparecidoDetallar().mostarTodos(results));
					    	cargando.getElement().setAttribute("style", "display:none");
				    	}
				    	else
				    	{
				    		HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Lo sentimos. No se encontraron resultados que coincidan con su b\u00FAsqueda.</p>");
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
		
		btnLimpiar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				elemento = DOM.getElementById("nombre1");
				esWidget = utilidades.getWidget(elemento);
				TextBox nombre1 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("nombre2");
				esWidget = utilidades.getWidget(elemento);
				TextBox nombre2 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("apellido1");
				esWidget = utilidades.getWidget(elemento);
				TextBox apellido1 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("apellido2");
				esWidget = utilidades.getWidget(elemento);
				TextBox apellido2 = (TextBox) esWidget;
				
				elemento = DOM.getElementById("tipoDocumento");
				esWidget = utilidades.getWidget(elemento);
				ListBox tipoDocumento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("numeroDocumento");
				esWidget = utilidades.getWidget(elemento);
				TextBox numeroDocumento = (TextBox) esWidget;
				
				elemento = DOM.getElementById("edad");
				esWidget = utilidades.getWidget(elemento);
				TextBox edad = (TextBox) esWidget;
				
				elemento = DOM.getElementById("genero");
				esWidget = utilidades.getWidget(elemento);
				ListBox listGenero = (ListBox) esWidget;
				
				nombre1.setValue("");
				nombre2.setValue("");
				apellido1.setValue("");
				apellido2.setValue("");
				tipoDocumento.setSelectedIndex(0);
				numeroDocumento.setValue("");
				edad.setValue("");
				listGenero.setSelectedIndex(0);
				
				if(!morfologiaRadio.isEmpty())
				{
					for(int i = 0; i < morfologiaRadio.size(); i++)
					{
						if(morfologiaRadio.get(i).getValue()==true)
							morfologiaRadio.get(i).setValue(false);
					}
				}
				
				if(!morfologiaCheckBox.isEmpty())
				{
					for(int i=0;i<morfologiaCheckBox.size();i++)
					{
						if(morfologiaCheckBox.get(i).getValue())
							morfologiaCheckBox.get(i).setValue(false);
					}
				}
				
				if(!senalesCheckBox.isEmpty())
				{
					for(int i=0;i<senalesCheckBox.size();i++)
					{
						if(senalesCheckBox.get(i).getValue())
							senalesCheckBox.get(i).setValue(false);
					}
				}
				
				if(!prendasCheckBox.isEmpty())
				{
					for(int i=0; i < prendasCheckBox.size();i++)
					{
						if(prendasCheckBox.get(i).getValue())
							prendasCheckBox.get(i).setValue(false);
					}
				}
				
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
	
	public CaptionPanel senalesParticulares(ArrayList<CheckBox> senalesCheckBox)
	{
		ArrayList<String> listaSenales = new ArrayList<String>();
		
		listaSenales.add("Amputaciones");
		listaSenales.add("Cicatrices");
		listaSenales.add("Deformidad");
		listaSenales.add("Discapacidad");
		listaSenales.add("Fracturas");
		listaSenales.add("Lunares");
		listaSenales.add("Manchas");
		listaSenales.add("Pecas");
		listaSenales.add("Piercing");
		listaSenales.add("Tatuajes");
		listaSenales.add("Berrugas");
		listaSenales.add("Embarazo");
		listaSenales.add("Enfermedad F\u00EDsica");
		listaSenales.add("Enfermedad Mental");
		listaSenales.add("Otros");
		
		CaptionPanel fieldsetSenales = new CaptionPanel();
		fieldsetSenales.setStyleName("buscarSenales");
		HTMLPanel divFielset = new HTMLPanel("");
		
		for(int i=0; i<listaSenales.size(); i++)
  		{
  	        /* Creando los Componentes */
  	        CheckBox check = new CheckBox(listaSenales.get(i));
  	        
  	        /* Asignando id a los componenetes */
  	        check.getElement().setId("checkBox" + listaSenales.get(i));
  	        divFielset.add(check);
  	        senalesCheckBox.add(check);
  		}
		
		fieldsetSenales.add(divFielset);
		return fieldsetSenales;
	}
	
	public CaptionPanel prendasDeVestir(ArrayList<CheckBox> prendasCheckBox)
	{
		ArrayList<String> listaPrendas = new ArrayList<String>();
		
		listaPrendas.add("Camisa");
		listaPrendas.add("Camiseta");
		listaPrendas.add("Buso");
		listaPrendas.add("Camibuso");
		listaPrendas.add("Pantal\u00F3n");
		listaPrendas.add("Falda");
		listaPrendas.add("Pantaloneta");
		listaPrendas.add("Short");
		listaPrendas.add("Sudadera");
		listaPrendas.add("Bermuda");
		listaPrendas.add("Chaqueta");
		listaPrendas.add("Saco");
		listaPrendas.add("Ruana");
		listaPrendas.add("Poncho");
		listaPrendas.add("Tenis");
		listaPrendas.add("Botas");
		listaPrendas.add("Zapatos");
		listaPrendas.add("Sandalias");
		
		CaptionPanel fieldsetPrendas = new CaptionPanel();
		fieldsetPrendas.setStyleName("buscarSenales");
		HTMLPanel divFielset = new HTMLPanel("");
		
		for(int i=0; i<listaPrendas.size(); i++)
  		{
  	        /* Creando los Componentes */
  	        CheckBox check = new CheckBox(listaPrendas.get(i));
  	        
  	        /* Asignando id a los componenetes */
  	        check.getElement().setId("checkBox" + listaPrendas.get(i));
  	        divFielset.add(check);
  	        prendasCheckBox.add(check);
  		}
		
		fieldsetPrendas.add(divFielset);
		return fieldsetPrendas;
	}
}
