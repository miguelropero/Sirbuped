package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import java.util.Date;
import com.appspot.sirbuped.client.RichTextToolbar;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.DatoDesaparicion;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Morfologia;
import com.appspot.sirbuped.client.DTO.PrendaVestir;
import com.appspot.sirbuped.client.DTO.SenalParticular;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.appspot.sirbuped.client.Interfaz.UploadImage;
import com.appspot.sirbuped.client.Interfaz.UploadImageAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

public class VistaDesaparecido extends Composite 
{
	public ArrayList<String> listaSenales = new ArrayList<String>();
	public ArrayList<String> listaPrendas = new ArrayList<String>();
	public Boolean error = false;
	public FormPanel uploadForm = new FormPanel();
	public Desaparecido desaparecido = null;
	
	private Utilidades utilidades = new Utilidades();
	
	public VistaDesaparecido()
	{}
	
	public VistaDesaparecido(final Desaparecido editar)
	{
		this.desaparecido = editar; 
		
		HTMLPanel subContent 			= new HTMLPanel("");
		subContent.setStyleName("desaparecido");
		subContent.getElement().setId("desaparecido");
		
		final ArrayList<RadioButton> morfologiaRadio = new ArrayList<RadioButton>();
		final ArrayList<CheckBox> morfologiaCheckBox = new ArrayList<CheckBox>();
		
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
		
		final CaptionPanel divPersonales= this.datosPersonales();
		final HTMLPanel divMorfologia 	= this.datosMorfologicos(morfologiaRadio, morfologiaCheckBox);
		final HTMLPanel divSenales		= this.senalesParticulares();
		final HTMLPanel divPrendas 		= this.prendasDeVestir(); 
		final HTMLPanel divDesaparicion = this.datoDesaparicion();
		final HTMLPanel divError		= new HTMLPanel("");
		divError.setStyleName("div_error");
		
		final Button btnPersonales 		= new Button("Continuar");
		final Button btnMorfologia		= new Button("Continuar");
		final Button btnMorfologiaVol	= new Button("Regresar");
		final Button btnSenales		 	= new Button("Continuar");
		final Button btnSenalesVol		= new Button("Regresar");
		final Button btnPrendas			= new Button("Continuar");
		final Button btnPrendasVol		= new Button("Regresar");
		final Button btnDesaparicion	= new Button("Registrar");
		final Button btnDesaparicionVol	= new Button("Regresar");
		
		btnMorfologia.setVisible(false);
		btnMorfologiaVol.setVisible(false);
		btnSenales.setVisible(false);
		btnSenalesVol.setVisible(false);
		btnPrendas.setVisible(false);
		btnPrendasVol.setVisible(false);
		btnDesaparicion.setVisible(false);
		btnDesaparicionVol.setVisible(false);
		
		final HTMLPanel encabezado 		= this.crearEncabezado();
		final HTMLPanel navegacion 		= new HTMLPanel("");
		final HTML titulo				= new HTML("<h2>Datos Personales</h2>");
		final Label descripcion				= new Label("Representan los principales filtros de b\u00FAuqeda para que los usuarios puedan ayudar a identificar y suminnistrar informaci\u00F3n de una persona desaparecida. Los campos marcados con (*) son obligatorios.");
		HTMLPanel DivPasos				= new HTMLPanel("");
		final HTML paso1				= new HTML("Paso 1");
		final HTML paso2				= new HTML("Paso 2");
		final HTML paso3				= new HTML("Paso 3");
		final HTML paso4				= new HTML("Paso 4");
		final HTML paso5				= new HTML("Paso 5");
		
		paso1.setStyleName("paso_presente");
		paso2.setStyleName("paso_futuro");
		paso3.setStyleName("paso_futuro");
		paso4.setStyleName("paso_futuro");
		paso5.setStyleName("paso_futuro");
		
		DivPasos.add(paso1);
		DivPasos.add(paso2);
		DivPasos.add(paso3);
		DivPasos.add(paso4);
		DivPasos.add(paso5);
		navegacion.add(DivPasos);
		navegacion.add(titulo);
		navegacion.add(descripcion);
		DivPasos.setStyleName("pasos");
		navegacion.setStyleName("navegacion");
		
		subContent.add(encabezado);
		subContent.add(navegacion);
		subContent.add(divPersonales);
		subContent.add(divMorfologia);
		subContent.add(divSenales);
		subContent.add(divPrendas);
		subContent.add(divDesaparicion);
		
		subContent.add(divPersonales);
		subContent.add(divMorfologia);
		subContent.add(divSenales);
		subContent.add(divPrendas);
		subContent.add(divDesaparicion);
		subContent.add(divError);
		
		subContent.add(btnPersonales);
		subContent.add(btnMorfologia);
		subContent.add(btnMorfologiaVol);
		subContent.add(btnSenales);
		subContent.add(btnSenalesVol);
		subContent.add(btnPrendas);
		subContent.add(btnPrendasVol);
		subContent.add(btnDesaparicion);
		subContent.add(btnDesaparicionVol);
		
		divMorfologia.setVisible(false);
		divSenales.setVisible(false);
		divPrendas.setVisible(false);
		divDesaparicion.setVisible(false);
		divError.setVisible(false);
		
		/* Evento clic boton datos personales */
		btnPersonales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				
				descripcion.setText("Permiten dar una descripci\u00F3n mas detallada de las caracter\u00EDsticas fisicas de la persona. Su registro no es obligatorio pero resulta importante para que los usuarios puedan contribuir a su b\u00FAsqueda.");
				
				error = false;
				divError.clear();
				Label errores = new Label();
				ArrayList<String> personales = new ArrayList<String>();
				
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				personales.add("nombre1");
				personales.add("apellido1");
				personales.add("numeroDocumento");
				personales.add("tipoDocumento");
				personales.add("genero");
				personales.add("paisNacimiento");
				personales.add("departamentoNacimiento");
				personales.add("ciudadNacimiento");
				personales.add("diaNacimiento");
				personales.add("mesNacimiento");
				personales.add("anioNacimiento");
				personales.add("fotografia");
				
				for(byte i=0; i < personales.size(); i++)
				{
					elemento = DOM.getElementById(personales.get(i));
					esWidget = utilidades.getWidget(elemento);
					
					if(i < 3)
					{
						final TextBox campoTexto = (TextBox) esWidget;
						if(campoTexto.getValue().isEmpty())
						{
							errores = new Label("El lformulario contiene campos vacios. Por favor verifique e intente nuevamente");
							campoTexto.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
							campoTexto.addBlurHandler(new BlurHandler()
							{
								@Override
						        public void onBlur(BlurEvent event)
						        {
						        	campoTexto.getElement().setAttribute("style", "");
						        	divError.setVisible(false);
						        }
						    });
							error = true;
						}
					}
					else if(i < 11)
					{
						final ListBox select = (ListBox) esWidget;
						
						if(select.getSelectedIndex() == 0)
						{
							select.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
							errores = new Label("El formulario contiene campos vacios. Por favor verifique e intente nuevamente");
							select.addBlurHandler(new BlurHandler()
						      {
						        @Override
						        public void onBlur(BlurEvent event)
						        {
						        	select.getElement().setAttribute("style", "");
						        	divError.setVisible(false);
						        }
						    });
							
							error = true;
						}
					}
					else
					{
						final FileUpload fotografia = (FileUpload) esWidget;
							
						if(fotografia.getFilename().isEmpty() && desaparecido == null)
						{
							fotografia.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
							errores = new Label("El formulario contiene campos vacios. Por favor verifique e intente nuevamente");
							fotografia.addChangeHandler(new ChangeHandler()
							{
							    public void onChange(ChangeEvent event) 
							    {
									fotografia.getElement().setAttribute("style", "");
									divError.setVisible(false);
						        }
						    });
							error = true;
						}
						else if(!fotografia.getFilename().isEmpty())
						{
							String extension = fotografia.getFilename().substring(fotografia.getFilename().length()-3, fotografia.getFilename().length());
							if(!(extension.equals("gif") || extension.equals("jpg") || extension.equals("png")))
							{
								fotografia.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
								errores = new Label("La extension de la fotografia no es valida. Debe ser un archivo jpg, png o gif");
								error = true;
							}
						}
					}
				}
				
				if(error)
				{
					divError.add(errores);
					divError.setVisible(true);
					return;
				}
				
				encabezado.getElementById("personales").setClassName("flecha_pasado");
				encabezado.getElementById("punta_personales").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("morfologia").setClassName("flecha_presente");
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_presente");
				divPersonales.setVisible(false);
				btnPersonales.setVisible(false);
				divMorfologia.setVisible(true);
				btnMorfologia.setVisible(true);
				btnMorfologiaVol.setVisible(true);
				paso1.setStyleName("paso_pasado");
				paso2.setStyleName("paso_presente");
				titulo.setHTML("<h2>Datos Morfol\u00F3gicos</h2>");
				divError.clear();
				divError.setVisible(false);
			}
		});
		/* Evento clic boton datos morfologicos */
		btnMorfologia.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				descripcion.setText("Permiten definir caracter\u00EDsticas unicas y particulares de la persona desaparecida. Su registro no es obligatorio pero resulta importante para que los usuarios puedan contribuir a su identificaci\u00F3n y b\u00FAsqueda.");
				encabezado.getElementById("punta_personales").setClassName("punta_flecha_pasado");
				encabezado.getElementById("morfologia").setClassName("flecha_pasado");
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("senales").setClassName("flecha_presente");
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_presente");
				divMorfologia.setVisible(false);
				btnMorfologia.setVisible(false);
				btnMorfologiaVol.setVisible(false);
				divSenales.setVisible(true);
				btnSenales.setVisible(true);
				btnSenalesVol.setVisible(true);
				titulo.setHTML("<h2>Se\u0148ales Particulares</h2>");
				paso2.setStyleName("paso_pasado");
				paso3.setStyleName("paso_presente");
			}
		});
		btnMorfologiaVol.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				descripcion.setText("Representan los principales filtros de b\u00FAuqeda para que los usuarios puedan ayudar a identificar y suminnistrar informaci\u00F3n de una persona desaparecida. Los campos marcados con (*) son obligatorios.");
				encabezado.getElementById("personales").setClassName("flecha_presente");
				encabezado.getElementById("punta_personales").setClassName("punta_flecha_presente");
				encabezado.getElementById("morfologia").setClassName("flecha_futuro");
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_futuro");
				divMorfologia.setVisible(false);
				btnMorfologia.setVisible(false);
				btnMorfologiaVol.setVisible(false);
				divPersonales.setVisible(true);
				btnPersonales.setVisible(true);
				titulo.setHTML("<h2>Datos Personales</h2>");
				paso1.setStyleName("paso_presente");
				paso2.setStyleName("paso_futuro");
			}
		});
		
		/* Evento clic boton señales particulares */
		btnSenales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				divError.clear();
				error = false;
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				for(byte i=0; i<listaSenales.size(); i++)
				{
					elemento = DOM.getElementById("checkBox"+listaSenales.get(i));
					esWidget = utilidades.getWidget(elemento);
					
					if(esWidget != null)
					{
						CheckBox seleccionado = (CheckBox) esWidget;
						if(seleccionado.getValue())
						{
							elemento = DOM.getElementById("textAreaUbicacion"+listaSenales.get(i));
							esWidget = utilidades.getWidget(elemento);
							final TextArea ubicacion = (TextArea) esWidget;
							
							elemento = DOM.getElementById("textAreaCaracteristica"+listaSenales.get(i));
							esWidget = utilidades.getWidget(elemento);
							final TextArea caracteristica = (TextArea) esWidget;
							
							if(caracteristica.getValue().isEmpty() || ubicacion.getValue().isEmpty())
							{
								error = true;
								ubicacion.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
								caracteristica.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
								
								ubicacion.addBlurHandler(new BlurHandler()
								{
									@Override
							        public void onBlur(BlurEvent event)
							        {
										ubicacion.getElement().setAttribute("style", "");
							        	divError.setVisible(false);
							        }
							    });
								
								caracteristica.addBlurHandler(new BlurHandler()
								{
									@Override
							        public void onBlur(BlurEvent event)
							        {
										caracteristica.getElement().setAttribute("style", "");
							        	divError.setVisible(false);
							        }
							    });
							}
						}
					}
				}
				if(error)
				{
					Label errores = new Label("Si selecciona una se\u0148al debe completar los campos asociados de Ubicaci\u00F3n y Caracter\u00EDstica.");
					divError.add(errores);
					divError.setVisible(true);
					return;
				}
				
				divError.setVisible(false);
				
				descripcion.setText("Informaci\u00F3n relacionada con los atuendos que la persona utilizaba al momento de la desaparici\u00F3n. Su registro no es obligatorio pero resulta importante para que los usuarios puedan contribuir a su identificaci\u00F3n y b\u00FAsqueda.");
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_pasado");
				encabezado.getElementById("senales").setClassName("flecha_pasado");
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("prendas").setClassName("flecha_presente");				//encabezado.getElementById("medicos").setClassName("flecha_presente");
				encabezado.getElementById("punta_prendas").setClassName("punta_flecha_presente");	//encabezado.getElementById("punta_medicos").setClassName("punta_flecha_presente");
				divSenales.setVisible(false);
				btnSenales.setVisible(false);
				btnSenalesVol.setVisible(false);
				divPrendas.setVisible(true); 	//divMedicos.setVisible(true);
				btnPrendas.setVisible(true); 	//btnMedicos.setVisible(true);
				btnPrendasVol.setVisible(true); //btnMedicosVol.setVisible(true);
				titulo.setHTML("<h2>Prendas de Vestir</h2>");	//titulo.setHTML("<h2>Antecedentes M\u00E9dicos</h2>");
				paso3.setStyleName("paso_pasado");
				paso4.setStyleName("paso_presente");
			}
		});
		btnSenalesVol.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				descripcion.setText("Permiten dar una descripci\u00F3n mas detallada de las caracter\u00EDsticas fisicas de la persona. Su registro no es obligatorio pero resulta importante para que los usuarios puedan contribuir a su b\u00F3squeda.");
				encabezado.getElementById("punta_personales").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("morfologia").setClassName("flecha_presente");
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_presente");
				encabezado.getElementById("senales").setClassName("flecha_futuro");
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_futuro");
				divSenales.setVisible(false);
				btnSenales.setVisible(false);
				btnSenalesVol.setVisible(false);
				divMorfologia.setVisible(true);
				btnMorfologia.setVisible(true);
				btnMorfologiaVol.setVisible(true);
				titulo.setHTML("<h2>Datos Morfol\u00F3gicos</h2>");
				paso2.setStyleName("paso_presente");
				paso3.setStyleName("paso_futuro");
			}
		});
		
		/* Evento clic boton Prendas de Vestir */
		btnPrendas.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				divError.clear();
				error = false;
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				for(byte i=0; i<listaPrendas.size(); i++)
				{
					elemento = DOM.getElementById("checkBox"+listaPrendas.get(i));
					esWidget = utilidades.getWidget(elemento);
					
					if(esWidget != null)
					{
						CheckBox seleccionado = (CheckBox) esWidget;
						if(seleccionado.getValue())
						{
							elemento = DOM.getElementById("textAreaCaracteristica"+listaPrendas.get(i));
							esWidget = utilidades.getWidget(elemento);
							final TextArea caracteristica = (TextArea) esWidget;
							
							elemento = DOM.getElementById("textAreaObservacion"+listaPrendas.get(i));
							esWidget = utilidades.getWidget(elemento);
							final TextArea observacion = (TextArea) esWidget;
							
							if(caracteristica.getValue().isEmpty() || observacion.getValue().isEmpty())
							{
								error = true;
								caracteristica.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
								observacion.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
								
								caracteristica.addBlurHandler(new BlurHandler()
								{
									@Override
							        public void onBlur(BlurEvent event)
							        {
										caracteristica.getElement().setAttribute("style", "");
							        	divError.setVisible(false);
							        }
							    });
								
								observacion.addBlurHandler(new BlurHandler()
								{
									@Override
							        public void onBlur(BlurEvent event)
							        {
										observacion.getElement().setAttribute("style", "");
							        	divError.setVisible(false);
							        }
							    });
							}
						}
					}
				}
				if(error)
				{
					Label errores = new Label("Si selecciona una prenda, debe completar los campos asociados de Caracter\u00EDstica Observaci\u00F3n.");
					divError.add(errores);
					divError.setVisible(true);
					return;
				}
				
				divError.setVisible(false);
				
				descripcion.setText("Datos asociados del momento de desaparici\u00F3n de la persona, debe ser claro y puntual para describir los hechos. Los campos marcados con (*) son obligatorios.");
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_pasado");	//encabezado.getElementById("punta_medicos").setClassName("punta_flecha_pasado");
				encabezado.getElementById("prendas").setClassName("flecha_pasado");
				encabezado.getElementById("punta_prendas").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("desaparicion").setClassName("flecha_presente");
				encabezado.getElementById("punta_desaparicion").setClassName("punta_flecha_presente");
				divPrendas.setVisible(false);
				btnPrendas.setVisible(false);
				btnPrendasVol.setVisible(false);
				divDesaparicion.setVisible(true);
				btnDesaparicion.setVisible(true);
				btnDesaparicionVol.setVisible(true);
				titulo.setHTML("<h2>Datos de la  Desaprici\u00F3n</h2>");
				paso4.setStyleName("paso_pasado");
				paso5.setStyleName("paso_presente");
			}
		});
		btnPrendasVol.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				descripcion.setText("Permiten definir caracter\u00EDsticas unicas y particulares de la persona desaparecida. Su registro no es obligatorio pero resulta importante para que los usuarios puedan contribuir a su identificaci\u00F3n y b\u00FAsqueda.");
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("senales").setClassName("flecha_presente");	
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_presente");
				encabezado.getElementById("prendas").setClassName("flecha_futuro");
				encabezado.getElementById("punta_prendas").setClassName("punta_flecha_futuro");
				divPrendas.setVisible(false);
				btnPrendas.setVisible(false);
				btnPrendasVol.setVisible(false);
				divSenales.setVisible(true);
				btnSenales.setVisible(true);
				btnSenalesVol.setVisible(true);
				titulo.setHTML("<h2>Se\u0148ales Particulares</h2>");
				paso3.setStyleName("paso_presente");
				paso4.setStyleName("paso_futuro");
			}
		});
		
		btnDesaparicion.addClickHandler(new ClickHandler() 
		{
			@SuppressWarnings("deprecation")
			public void onClick(ClickEvent event) 
			{
				
				/* Validaciones */
				divError.clear();
				error = false;
				Label errores = new Label();
				
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				ArrayList<String> dato = new ArrayList<String>();
				
				dato.add("diaDesaparicion");
				dato.add("mesDesaparicion");
				dato.add("anioDesaparicion");
				dato.add("departamentoDesaparicion");
				dato.add("ciudadDesaparicion");
				
				for(byte i=0; i < dato.size(); i++)
				{
					elemento = DOM.getElementById(dato.get(i));
					esWidget = utilidades.getWidget(elemento);
					final ListBox select = (ListBox) esWidget;
						
					if(select.getSelectedIndex() == 0)
					{
						select.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						errores = new Label("El formulario contiene campos vacios. Por favor verifique e intente nuevamente");
						select.addBlurHandler(new BlurHandler()
					    {
							@Override
					        public void onBlur(BlurEvent event)
					        {
					        	select.getElement().setAttribute("style", "");
					        	divError.setVisible(false);
					        }
					    });
					error = true;
					}
				}
				
				
				elemento = DOM.getElementById("descripcionHecho");
				esWidget = utilidades.getWidget(elemento);
				RichTextArea descripcionHecho = (RichTextArea) esWidget;
				
				if(descripcionHecho.getText().length() > 500)
				{
					errores = new Label("La descripcion del hecho permite un maximo de 500 caracteres");
					error = true;
					divError.add(errores);
				}
				
				if(error)
				{
					divError.setVisible(true);
					return;
				}
				
				divError.setVisible(false);
				
				// CAPTURANDO LOS DATOS PERSONALES
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
				
				elemento = DOM.getElementById("ciudadNacimiento");
				esWidget = utilidades.getWidget(elemento);
				ListBox ciudadNacimiento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("diaNacimiento");
				esWidget = utilidades.getWidget(elemento);
				ListBox diaNacimiento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("mesNacimiento");
				esWidget = utilidades.getWidget(elemento);
				ListBox mesNacimiento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("anioNacimiento");
				esWidget = utilidades.getWidget(elemento);
				ListBox anioNacimiento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("fotografia");
				esWidget = utilidades.getWidget(elemento);
				FileUpload fotografia = (FileUpload) esWidget;
				
				Date fechaNacimiento = null;
				if(diaNacimiento.getSelectedIndex() != 0)
				{
					fechaNacimiento = new Date();
					fechaNacimiento.setYear(Integer.parseInt(anioNacimiento.getValue(anioNacimiento.getSelectedIndex()))-1900); 
					fechaNacimiento.setMonth((mesNacimiento.getSelectedIndex()-1));
					fechaNacimiento.setDate(diaNacimiento.getSelectedIndex());
				}
				
				elemento = DOM.getElementById("genero");
				esWidget = utilidades.getWidget(elemento);
				ListBox listGenero = (ListBox) esWidget;
				
				elemento = DOM.getElementById("estatura");
				esWidget = utilidades.getWidget(elemento);
				TextBox estatura = (TextBox) esWidget;
				
				elemento = DOM.getElementById("peso");
				esWidget = utilidades.getWidget(elemento);
				TextBox peso = (TextBox) esWidget;
				
				if(desaparecido == null)
					desaparecido = new Desaparecido();
				
				desaparecido.setNombre1(nombre1.getValue());
				desaparecido.setNombre2(nombre2.getValue());
				desaparecido.setApellido1(apellido1.getValue());
				desaparecido.setApellido2(apellido2.getValue());
				desaparecido.setTipoDocumento(tipoDocumento.getValue(tipoDocumento.getSelectedIndex()));
				desaparecido.setNumeroDocumento(numeroDocumento.getValue());
				desaparecido.setKeyCiudadNacimiento(ciudadNacimiento.getValue(ciudadNacimiento.getSelectedIndex()));
				desaparecido.setFechaNacimiento(fechaNacimiento);
				desaparecido.setGenero(listGenero.getValue(listGenero.getSelectedIndex()));
				desaparecido.setEstatura(estatura.getValue());
				desaparecido.setPeso(peso.getValue());
				
				desaparecido.getMorfologia().clear();
				desaparecido.getSenalParticular().clear();
				desaparecido.getPrendaVestir().clear();
				
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
				
				/* Para Capturar las Señales Particulares seleccionadas */ 
				
				for(byte i=0; i<listaSenales.size(); i++)
				{
					elemento = DOM.getElementById("checkBox"+listaSenales.get(i));
					esWidget = utilidades.getWidget(elemento);
					
					if(esWidget != null)
					{
						CheckBox seleccionado = (CheckBox) esWidget;
						if(seleccionado.getValue())
						{
							elemento = DOM.getElementById("textAreaUbicacion"+listaSenales.get(i));
							esWidget = utilidades.getWidget(elemento);
							TextArea ubicacion = (TextArea) esWidget;
							
							elemento = DOM.getElementById("textAreaCaracteristica"+listaSenales.get(i));
							esWidget = utilidades.getWidget(elemento);
							TextArea caracteristica = (TextArea) esWidget;
							
							SenalParticular senal = new SenalParticular(listaSenales.get(i).toString(), ubicacion.getValue(), caracteristica.getValue());
							desaparecido.getSenalParticular().add(senal);
						}
					}
				}
				
				/* Para Capturar las Prendas de Vestir seleccionadas */ 
				
				for(byte i=0; i<listaPrendas.size(); i++)
				{
					elemento = DOM.getElementById("checkBox"+listaPrendas.get(i));
					esWidget = utilidades.getWidget(elemento);
					
					if(esWidget != null)
					{
						CheckBox seleccionado = (CheckBox) esWidget;
						if(seleccionado.getValue())
						{
							elemento = DOM.getElementById("textAreaCaracteristica"+listaPrendas.get(i));
							esWidget = utilidades.getWidget(elemento);
							TextArea caracteristicas = (TextArea) esWidget;
							
							elemento = DOM.getElementById("textAreaObservacion"+listaPrendas.get(i));
							esWidget = utilidades.getWidget(elemento);
							TextArea observacion = (TextArea) esWidget;
							
							PrendaVestir prenda = new PrendaVestir(listaPrendas.get(i).toString(), caracteristicas.getValue(), observacion.getValue());
							desaparecido.getPrendaVestir().add(prenda);
						}
					}
				}
				
				// CAPTURANDO LOS DATOS RELATIVOS A LA DESAPARICION 
				elemento = DOM.getElementById("corregimiento");
				esWidget = utilidades.getWidget(elemento);
				TextBox corregimiento = (TextBox) esWidget;
				
				elemento = DOM.getElementById("inspeccion");
				esWidget = utilidades.getWidget(elemento);
				TextBox inspeccion = (TextBox) esWidget;
				
				elemento 	= DOM.getElementById("diaDesaparicion");
				esWidget 	= utilidades.getWidget(elemento);
				ListBox diaDesaparicion = (ListBox) esWidget;
				
				elemento 	= DOM.getElementById("mesDesaparicion");
				esWidget 	= utilidades.getWidget(elemento);
				ListBox mesDesaparicion = (ListBox) esWidget;
				
				elemento 	= DOM.getElementById("anioDesaparicion");
				esWidget 	= utilidades.getWidget(elemento);
				ListBox anioDesaparicion = (ListBox) esWidget;
				
				/* Generando la fecha de desaparicion */
				Date fechaDesaparicion = null;
				fechaDesaparicion = new Date();
				fechaDesaparicion.setYear(Integer.parseInt(anioDesaparicion.getValue(anioDesaparicion.getSelectedIndex()))-1900); 
				fechaDesaparicion.setMonth((mesDesaparicion.getSelectedIndex()-1));
				fechaDesaparicion.setDate(diaDesaparicion.getSelectedIndex());
				
				/* Generando el Lugar de Desaparicion */
				elemento 	= DOM.getElementById("ciudadDesaparicion");
				esWidget 	= utilidades.getWidget(elemento);
				ListBox ciudadDesaparicion = (ListBox) esWidget;
				
				DatoDesaparicion datoDesaparicion = new DatoDesaparicion(fechaDesaparicion, corregimiento.getValue(), inspeccion.getValue(), descripcionHecho.getText());
				datoDesaparicion.setKeyCiudadDesaparicion(ciudadDesaparicion.getValue(ciudadNacimiento.getSelectedIndex()));
				desaparecido.setDatoDesaparicion(datoDesaparicion);
				
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				
				if(!fotografia.getFilename().isEmpty())
				{
					UploadImageAsync uploadImagen = GWT.create(UploadImage.class);
					uploadImagen.getBlobStoreUploadUrl(new AsyncCallback<String>() 
					{
						public void onSuccess(String result) 
						{
			                uploadForm.setAction(result.toString());
			                uploadForm.submit();
			                //uploadForm.reset();
			            }
			            public void onFailure(Throwable caught) 
			            {
			            	new Utilidades().ventanaModal("Error", caught.toString() , "error");
			            }
			        });
				}
				
				if(editar == null)
				{
					uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() 
					{
						@Override
						public void onSubmitComplete(SubmitCompleteEvent event) 
						{
							desaparecido.setKeyFoto("/sirbuped/uploadImagen?blob-key=" + event.getResults().trim());
							
							DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
							desaparecidoService.registrar(desaparecido, new AsyncCallback<String>() 
							{
							    public void onSuccess(String keyDesaparecido) 
							    {
							    	History.newItem("-" + keyDesaparecido);
							    }
							    public void onFailure(Throwable error) 
								{
									new Utilidades().ventanaModal("Error", error.toString() , "error");
								}
					        });
						}
					});
				}
				else
				{
					if(!fotografia.getFilename().isEmpty())
					{
						uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() 
						{
							@Override
							public void onSubmitComplete(SubmitCompleteEvent event) 
							{
								desaparecido.setKeyFoto("/sirbuped/uploadImagen?blob-key=" + event.getResults().trim());
							
								DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
								desaparecidoService.actualizarDesaparecido(desaparecido, new AsyncCallback<Void>() 
								{
								    public void onSuccess(Void ignore) 
								    {
								    	History.newItem("-" + desaparecido.getNumeroDocumento());
								    }
								    public void onFailure(Throwable error) 
									{
										new Utilidades().ventanaModal("Error", error.toString() , "error");
									}
						        });
							}
						});
					}
					else
					{
						DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
						desaparecidoService.actualizarDesaparecido(desaparecido, new AsyncCallback<Void>() 
						{
						    public void onSuccess(Void ignore) 
						    {
						    	History.newItem("-" + desaparecido.getNumeroDocumento());
						    }
						    public void onFailure(Throwable error) 
							{
								new Utilidades().ventanaModal("Error", error.toString() , "error");
							}
				        });
					}
				}
			}
		});
		
		btnDesaparicionVol.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				descripcion.setText("Informaci\u00F3n relacionada con los atuendos que la persona utilizaba al momento de la desaparici\u00F3n. Su registro no es obligatorio pero resulta importante para que los usuarios puedan contribuir a su identificaci\u00F3n y b\u00FAsqueda.");
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("prendas").setClassName("flecha_presente");
				encabezado.getElementById("punta_prendas").setClassName("punta_flecha_presente");
				encabezado.getElementById("desaparicion").setClassName("flecha_futuro");
				encabezado.getElementById("punta_desaparicion").setClassName("punta_flecha_futuro");
				divDesaparicion.setVisible(false);
				btnDesaparicion.setVisible(false);
				btnDesaparicionVol.setVisible(false);
				divPrendas.setVisible(true);
				btnPrendas.setVisible(true);
				btnPrendasVol.setVisible(true);
				titulo.setHTML("<h2>Prendas de Vestir</h2>");
				paso4.setStyleName("paso_presente");
				paso5.setStyleName("paso_futuro");
			}
		});
		
		initWidget(subContent);
	}
	
	
	public HTMLPanel crearEncabezado()
	{
		HTMLPanel encabezado 				= new HTMLPanel("");
		encabezado.setStyleName("encabezado");
		
		Label lblPersonales					= new Label("Datos Personales");
		HTMLPanel puntaFlechaPersonales 	= new HTMLPanel("");
		Label lblMorfologia					= new Label("Morfolog\u00EDa");
		HTMLPanel puntaFlechaMorfologia 	= new HTMLPanel("");
		Label lblSenales					= new Label("Se\u0148ales Particulares");
		HTMLPanel puntaFlechaSenales 		= new HTMLPanel("");
		Label lblPrendas					= new Label("Prendas de Vestir");
		HTMLPanel puntapuntaFlechaPrendas 	= new HTMLPanel("");
		Label lblDesaparicion				= new Label("Desaparici\u00F3n");
		HTMLPanel puntaFlechaDesaparicion 	= new HTMLPanel("");
		
		lblPersonales.setStyleName("flecha_presente");
		lblPersonales.getElement().setAttribute("id", "personales");
		lblMorfologia.setStyleName("flecha_futuro");
		lblMorfologia.getElement().setAttribute("id", "morfologia");
		lblSenales.setStyleName("flecha_futuro");
		lblSenales.getElement().setAttribute("id", "senales");
		lblPrendas.setStyleName("flecha_futuro");
		lblPrendas.getElement().setAttribute("id", "prendas");
		lblDesaparicion.setStyleName("flecha_futuro");
		lblDesaparicion.getElement().setAttribute("id", "desaparicion");
		
		puntaFlechaPersonales.setStyleName("punta_flecha_presente");
		puntaFlechaPersonales.getElement().setAttribute("id", "punta_personales");
		puntaFlechaMorfologia.setStyleName("punta_flecha_futuro");
		puntaFlechaMorfologia.getElement().setAttribute("id", "punta_morfologia");
		puntaFlechaSenales.setStyleName("punta_flecha_futuro");
		puntaFlechaSenales.getElement().setAttribute("id", "punta_senales");
		puntapuntaFlechaPrendas.setStyleName("punta_flecha_futuro");
		puntapuntaFlechaPrendas.getElement().setAttribute("id", "punta_prendas");
		puntaFlechaDesaparicion.setStyleName("punta_flecha_futuro");
		puntaFlechaDesaparicion.getElement().setAttribute("id", "punta_desaparicion");
		
		encabezado.add(lblPersonales);
		encabezado.add(puntaFlechaPersonales);
		encabezado.add(lblMorfologia);
		encabezado.add(puntaFlechaMorfologia);
		encabezado.add(lblSenales);
		encabezado.add(puntaFlechaSenales);
		encabezado.add(lblPrendas);
		encabezado.add(puntapuntaFlechaPrendas);
		encabezado.add(lblDesaparicion);
		encabezado.add(puntaFlechaDesaparicion);
		
		return encabezado;
	}
	
	
	@SuppressWarnings("deprecation")
	public CaptionPanel datosPersonales()
	{		
		CaptionPanel fieldsetPersonales = new CaptionPanel("Datos Personales");
		
		HTMLPanel divDatosPersonalesG 	= new HTMLPanel(" ");
		HTMLPanel divDatosPersonales1 	= new HTMLPanel(" ");
		HTMLPanel divDatosPersonales2 	= new HTMLPanel(" ");
		
		Label lblNombre1				= new Label("Primer Nombre: *");
		Label lblNombre2 				= new Label("Segundo Nombre:");
		Label lblApellido1 				= new Label("Primer Apellido: *");
		Label lblApellido2 				= new Label("Segundo Apellido:");
		Label lblDocumento 				= new Label("Tipo de Documento: *");
		Label lblNdocumento 			= new Label("N\u00FAmero de Documento: *");
		Label lblLugarNacimiento 		= new Label("Lugar de Nacimiento: *");
		Label lblGenero 				= new Label("G\u00E9nero: *");
		Label lblFechaNacimiento 		= new Label("Fecha Nacimiento:");
		Label lblFotografia				= new Label("Fotografia:");
		
		TextBox textNombre1 			= new TextBox();
		TextBox textNombre2 			= new TextBox();
		TextBox textApellido1 			= new TextBox();
		TextBox textApellido2 			= new TextBox();
		ListBox selectTipoDocument		= new ListBox();
		final TextBox textDocumento 	= new TextBox();
		final ListBox selectPais 		= new ListBox();
		final ListBox selectDepartamento= new ListBox();
		final ListBox selectCiudad 		= new ListBox();
		ListBox selectGenero 			= new ListBox();
		final TextBox textEdad 			= new TextBox();
		ListBox selectDia 				= new Utilidades().diaAnio(true);
		ListBox selectMes 				= new Utilidades().listaMeses();
		ListBox selectAnio 				= new Utilidades().diaAnio(false);
		FileUpload fotografia 			= new FileUpload();
		fotografia.setName("foto");
		
		selectPais.addItem("Pa\u00EDs...");
		selectDepartamento.addItem("Departamento...");
	    selectCiudad.addItem("Ciudad...");
		
		new Utilidades().getPaises(selectPais);
		
		selectPais.addChangeHandler(new ChangeHandler()
		{
			public void onChange(ChangeEvent event)
			{
				if(selectPais.getSelectedIndex() > 0)
				{
					String pais = selectPais.getValue(selectPais.getSelectedIndex());
					new Utilidades().getDepartamentos(pais, selectDepartamento);
				}
				else
				{
					selectDepartamento.clear();
					selectDepartamento.addItem("Departamento...");
					selectCiudad.clear();
					selectCiudad.addItem("Ciudad...");
				}
			}
		});
		
		selectDepartamento.addChangeHandler(new ChangeHandler()
		{
			public void onChange(ChangeEvent event)
			{
				if(selectDepartamento.getSelectedIndex() > 0)
				{
					String pais = selectPais.getValue(selectPais.getSelectedIndex());
					String departamento = selectDepartamento.getValue(selectDepartamento.getSelectedIndex());
					new Utilidades().getCiudades(pais, departamento, selectCiudad);
				}
				else
				{
					selectCiudad.clear();
					selectCiudad.addItem("Ciudad...");
				}
			}
		});
		
		textNombre1.getElement().setId("nombre1");
		textNombre2.getElement().setId("nombre2");
		textApellido1.getElement().setId("apellido1");
		textApellido2.getElement().setId("apellido2");
		selectTipoDocument.getElement().setId("tipoDocumento");
		textDocumento.getElement().setId("numeroDocumento");
		selectPais.getElement().setId("paisNacimiento");
		selectDepartamento.getElement().setId("departamentoNacimiento");
		selectCiudad.getElement().setId("ciudadNacimiento");
		selectGenero.getElement().setId("genero");
		textEdad.getElement().setId("edad");
		selectDia.getElement().setId("diaNacimiento");
		selectMes.getElement().setId("mesNacimiento");
		selectAnio.getElement().setId("anioNacimiento");
		fotografia.getElement().setId("fotografia");
		
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
		
		/* Si es para editar desaparecido que cargue los datos del desaparecido en el formulario */
		if(desaparecido != null)
		{
			textNombre1.setValue(desaparecido.getNombre1());
			textNombre2.setValue(desaparecido.getNombre2());
			textApellido1.setValue(desaparecido.getApellido1());
			textApellido2.setValue(desaparecido.getApellido2());
			
			for(int i = 0; i < selectTipoDocument.getItemCount(); i++)
			{
				if(selectTipoDocument.getValue(i).equals(desaparecido.getTipoDocumento()))
					selectTipoDocument.setSelectedIndex(i);
			}
			
			textDocumento.setValue(desaparecido.getNumeroDocumento());
			
			if(desaparecido.getGenero().equals("Masculino"))
				selectGenero.setSelectedIndex(1);
			else
				selectGenero.setSelectedIndex(2);
			
			/* Cargando los datos a los select de Pais, dpto y ciudad con los respectivos valores del desaparecido*/
			new Utilidades().selectedIndex(selectPais, selectDepartamento, selectCiudad, desaparecido.getCiudadNacimiento().getDepartamento().getPais().getNombre(), desaparecido.getCiudadNacimiento().getDepartamento().getNombre(), desaparecido.getCiudadNacimiento().getNombre());
			
			/* Seleccionando los datos correspondientes a la fecha de nacimiento del desaparecido */
			if(desaparecido.getFechaNacimiento() != null)
			{
				for(int i = 1; i < selectAnio.getItemCount(); i++)
				{
					if((desaparecido.getFechaNacimiento().getYear()+1900) == Integer.parseInt((selectAnio.getValue(i))))
					{
						selectAnio.setSelectedIndex(i);
						break;
					}
				}
				selectMes.setSelectedIndex(desaparecido.getFechaNacimiento().getMonth()+1);
				selectDia.setSelectedIndex(desaparecido.getFechaNacimiento().getDate());
			}
		}
		
		divDatosPersonales1.add(lblNombre1);
		divDatosPersonales1.add(textNombre1);
		divDatosPersonales1.add(lblNombre2);
		divDatosPersonales1.add(textNombre2);
		divDatosPersonales1.add(lblApellido1);
		divDatosPersonales1.add(textApellido1);
		divDatosPersonales1.add(lblApellido2);
		divDatosPersonales1.add(textApellido2);
		divDatosPersonales1.add(lblDocumento);
		divDatosPersonales1.add(selectTipoDocument);
		divDatosPersonales1.add(lblNdocumento);
		divDatosPersonales1.add(textDocumento);
		divDatosPersonales2.add(lblGenero);
		divDatosPersonales2.add(selectGenero);
		divDatosPersonales2.add(lblLugarNacimiento);
		divDatosPersonales2.add(selectPais);
		divDatosPersonales2.add(selectDepartamento);
		divDatosPersonales2.add(selectCiudad);
		divDatosPersonales2.add(lblFechaNacimiento);
		divDatosPersonales2.add(selectDia);
		divDatosPersonales2.add(selectMes);
		divDatosPersonales2.add(selectAnio);
				
		divDatosPersonalesG.add(divDatosPersonales1);
		divDatosPersonalesG.add(divDatosPersonales2);
		divDatosPersonalesG.add(lblFotografia);
		divDatosPersonalesG.add(fotografia);
		divDatosPersonalesG.setStyleName("divPersonales");
		
		// The upload form, when submitted, will trigger an HTTP call to the
	    // servlet.  The following parameters must be set
	    uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
	    uploadForm.setMethod(FormPanel.METHOD_POST);
		
		uploadForm.add(divDatosPersonalesG);
		
		fieldsetPersonales.add(uploadForm);
		
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
		
		return fieldsetPersonales;
	}
	
	
	public HTMLPanel datosMorfologicos(ArrayList<RadioButton> morfologiaRadio, ArrayList<CheckBox> morfologiaCheckBox)
	{
		HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("morfologia");
		
		ArrayList<ArrayList<ArrayList<String>>> morfologia = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> general = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> contextura = new ArrayList<String>();
		contextura.add("radio");
		contextura.add("Contextura");
		contextura.add("Obesa");
		contextura.add("Robusta");
		contextura.add("Mediana");
		contextura.add("Delgada");
		contextura.add("No Recuerda");
		
		ArrayList<String> labios = new ArrayList<String>();
		labios.add("radio");
		labios.add("Labios");
		labios.add("Gruesos");
		labios.add("Medianos");
		labios.add("Delgados");
		labios.add("No Recuerda");
		
		ArrayList<String> boca = new ArrayList<String>();
		boca.add("radio");
		boca.add("Boca");
		boca.add("Grande");
		boca.add("Mediana");
		boca.add("Peque\u0148a");
		boca.add("No Recuerda");
		
		ArrayList<String> nariz = new ArrayList<String>();
		nariz.add("checkBox");
		nariz.add("Nariz");
		nariz.add("Desviada");
		nariz.add("Achatada");
		nariz.add("Operada");
		nariz.add("Alomada");
		nariz.add("Recta");
		
		ArrayList<String> orejas = new ArrayList<String>();
		orejas.add("checkBox");
		orejas.add("Orejas");
		orejas.add("Peludas");
		orejas.add("Perforadas");
		orejas.add("L\u00F3bulo Adherido");
		orejas.add("L\u00F3bulo Separado");
		
		general.add(contextura);
		general.add(labios);
		general.add(boca);
		general.add(nariz);
		general.add(orejas);
		
		ArrayList<ArrayList<String>> cara = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> contorno = new ArrayList<String>();
		contorno.add("radio");
		contorno.add("Contorno");
		contorno.add("Redonda");
		contorno.add("Ovalada");
		contorno.add("Cuadrada");
		contorno.add("Rectangular");
		contorno.add("No Recuerda");
		
		ArrayList<String> color = new ArrayList<String>();
		color.add("radio");
		color.add("Color");
		color.add("Albino");
		color.add("Blanco");
		color.add("Trigue\u0148o");
		color.add("Negro");
		color.add("Moreno");
		
		ArrayList<String> particularidad = new ArrayList<String>();
		particularidad.add("checkBox");
		particularidad.add("Particularidad");
		particularidad.add("Acn\u00E9");
		particularidad.add("Cicatriz Acn\u00E9");
		particularidad.add("Manchada");
		particularidad.add("Pecosa");
		particularidad.add("Lunares");
		
		cara.add(contorno);
		cara.add(color);
		cara.add(particularidad);
		
		ArrayList<ArrayList<String>> cabello = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> longitud = new ArrayList<String>();
		longitud.add("radio");
		longitud.add("Longitud");
		longitud.add("Rapado");
		longitud.add("Corto");
		longitud.add("Mediano");
		longitud.add("Largo");
		longitud.add("No Recuerda");
		
		ArrayList<String> forma = new ArrayList<String>();
		forma.add("radio");
		forma.add("Forma");
		forma.add("Liso");
		forma.add("Ondulado");
		forma.add("Lanoso");
		forma.add("Crespo");
		forma.add("No Recuerda");
		
		ArrayList<String> calvicie = new ArrayList<String>();
		calvicie.add("radio");
		calvicie.add("Calvicie");
		calvicie.add("No Presenta");
		calvicie.add("Total");
		calvicie.add("Coronal");
		calvicie.add("Fronto Coronal");
		calvicie.add("Frontal");
		calvicie.add("Bilateral");
		calvicie.add("No Recuerda");
		
		ArrayList<String> colorCabello = new ArrayList<String>();
		colorCabello.add("radio");
		colorCabello.add("Color");
		colorCabello.add("Albino");
		colorCabello.add("Cano");
		colorCabello.add("Entrecano");
		colorCabello.add("Rubio");
		colorCabello.add("Casta\u0148o");
		colorCabello.add("Negro");
		colorCabello.add("Tinturado");
		
		ArrayList<String> particularidadCabello = new ArrayList<String>();
		particularidadCabello.add("checkBox");
		particularidadCabello.add("Particularidad");
		particularidadCabello.add("Biso\u0148\u00E9");
		particularidadCabello.add("Transplante");
		particularidadCabello.add("Peluca");
		particularidadCabello.add("Sint\u00E9tico");
		particularidadCabello.add("Extensiones");
		particularidadCabello.add("Rasta");
		
		cabello.add(longitud);
		cabello.add(forma);
		cabello.add(calvicie);
		cabello.add(colorCabello);
		cabello.add(particularidadCabello);
		
		ArrayList<ArrayList<String>> ojos = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> colorOjos = new ArrayList<String>();
		colorOjos.add("radio");
		colorOjos.add("Color");
		colorOjos.add("Negros");
		colorOjos.add("Miel");
		colorOjos.add("Marrones");
		colorOjos.add("Grises");
		colorOjos.add("Azules");
		colorOjos.add("Verdes");
		colorOjos.add("No Recuerda");
		
		ArrayList<String> tamanioOjos = new ArrayList<String>();
		tamanioOjos.add("radio");
		tamanioOjos.add("Tama\u0148o");
		tamanioOjos.add("Grandes");
		tamanioOjos.add("Medianos");
		tamanioOjos.add("Peque\u0148os");
		tamanioOjos.add("No Recuerda");
		
		ArrayList<String> particularidadOjos = new ArrayList<String>();
		particularidadOjos.add("checkBox");
		particularidadOjos.add("Particularidad");
		particularidadOjos.add("Ojo Artificial");
		particularidadOjos.add("Falta Ojo Izquierdo");
		particularidadOjos.add("Falta Ojo Derecho");
		particularidadOjos.add("Diferente Color");
		particularidadOjos.add("P\u00E1rpado Ca\u00EDdo");
		particularidadOjos.add("Vizco");
		particularidadOjos.add("Ciego");
		
		ojos.add(colorOjos);
		ojos.add(tamanioOjos);
		ojos.add(particularidadOjos);
		
		ArrayList<ArrayList<String>> barba = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> capilariad = new ArrayList<String>();
		capilariad.add("radio");
		capilariad.add("Capilaridad");
		capilariad.add("Poblada");
		capilariad.add("Despoblada");
		
		ArrayList<String> estilo = new ArrayList<String>();
		estilo.add("radio");
		estilo.add("Estilo");
		estilo.add("Chivera");
		estilo.add("Candado");
		estilo.add("Patillas");
		
		ArrayList<String> longitudBarba = new ArrayList<String>();
		longitudBarba.add("radio");
		longitudBarba.add("Longitud");
		longitudBarba.add("Larga");
		longitudBarba.add("Corta");
		longitudBarba.add("Mediana");
		longitudBarba.add("Rasurada");
		
		ArrayList<String> particularidadBarba = new ArrayList<String>();
		particularidadBarba.add("radio");
		particularidadBarba.add("Particularidad");
		particularidadBarba.add("Cana");
		particularidadBarba.add("Entrecana");
		particularidadBarba.add("Rojiza");
		particularidadBarba.add("Alvina");
		
		barba.add(capilariad);
		barba.add(estilo);
		barba.add(longitudBarba);
		barba.add(particularidadBarba);
		
		ArrayList<ArrayList<String>> bigote = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> capilariadBigote = new ArrayList<String>();
		capilariadBigote.add("radio");
		capilariadBigote.add("Capilaridad");
		capilariadBigote.add("Poblado");
		capilariadBigote.add("Despoblado");
		
		ArrayList<String> longitudBigote = new ArrayList<String>();
		longitudBigote.add("radio");
		longitudBigote.add("Longitud");
		longitudBigote.add("Largo");
		longitudBigote.add("Corto");
		longitudBigote.add("Mediano");
		longitudBigote.add("Rasurado");
		
		ArrayList<String> particularidadBigote = new ArrayList<String>();
		particularidadBigote.add("radio");
		particularidadBigote.add("Particularidad");
		particularidadBigote.add("Cano");
		particularidadBigote.add("Entrecano");
		particularidadBigote.add("Rojizo");
		particularidadBigote.add("Albino");
		
		bigote.add(capilariadBigote);
		bigote.add(longitudBigote);
		bigote.add(particularidadBigote);
		
		
		morfologia.add(general);
		morfologia.add(cara);
		morfologia.add(cabello);
		morfologia.add(ojos);
		morfologia.add(barba);
		morfologia.add(bigote);
		
		ArrayList<String> cadena = new ArrayList<String>();
		cadena.add("General");
		cadena.add("Cara");
		cadena.add("Cabello");
		cadena.add("Ojos");
		cadena.add("Barba");
		cadena.add("Bigote");
		
		Byte identificador = 0;
		
		for(byte i = 0; i < cadena.size(); i++)
		{
			ArrayList<ArrayList<String>> tipo = morfologia.get(i);
			CaptionPanel fieldsetGeneral = new CaptionPanel(cadena.get(i));
			
			HTMLPanel div = new HTMLPanel("");
			
			if(cadena.get(i).equals("General"))
			{
				HTMLPanel peso 				= new HTMLPanel("");
				HTMLPanel estatura 			= new HTMLPanel("");
				
				Label lblEstatura			= new Label("Estatura / CMs:");
				Label lblPeso				= new Label("Peso / KG:");
				
				final TextBox textEstatura	= new TextBox();
				final TextBox textPeso		= new TextBox();
				
				textPeso.getElement().setId("peso");
				textPeso.setMaxLength(3);
				
				textEstatura.getElement().setId("estatura");
				textEstatura.setMaxLength(3);
				
				peso.add(lblPeso);
				peso.add(textPeso);
				estatura.add(lblEstatura);
				estatura.add(textEstatura);
				
				textEstatura.addBlurHandler(new BlurHandler()
			    {
			        @Override
			        public void onBlur(BlurEvent event)
			        {
				        try
				        {
				        	if(!textEstatura.getValue().isEmpty())
				        		Integer.parseInt(textEstatura.getValue());
				        }
				        catch(NumberFormatException ex)
				        {
				        	new Utilidades().ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico, estatura" +
				        				 " ", "error");
				        	textEstatura.setText("");
				        }
				    }
				});
				
				textPeso.addBlurHandler(new BlurHandler()
			    {
			        @Override
			        public void onBlur(BlurEvent event)
			        {
				        try
				        {
				        	if(!textPeso.getValue().isEmpty())
				        		Integer.parseInt(textPeso.getValue());
				        }
				        catch(NumberFormatException ex)
				        {
				        	new Utilidades().ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico y representa el peso aproximado en kilogramos" +
				        				 " persona desaparecido en kilogramos", "error");
				        	textPeso.setText("");
				        }
				    }
				});
				
				div.add(peso);
				div.add(estatura);
				
				if(desaparecido != null)
				{
					textEstatura.setValue(desaparecido.getEstatura());
					textPeso.setValue(desaparecido.getPeso());
				}
			}
			
			for(byte j = 0; j < tipo.size(); j++)
			{
				ArrayList<String> caracteristica = tipo.get(j);
				CaptionPanel fieldsetInterno = new CaptionPanel(caracteristica.get(1));
				HTMLPanel subDiv = new HTMLPanel("");
				
				ArrayList<Morfologia> morfo = new ArrayList<Morfologia>();
				if(desaparecido != null)
					morfo = desaparecido.getMorfologia();
				
				for(byte k = 2; k < caracteristica.size(); k++)
				{
					if(caracteristica.get(0) == "radio")
					{
						RadioButton radio = new RadioButton(caracteristica.get(1) + " " + cadena.get(i), caracteristica.get(k));
						radio.getElement().setAttribute("identificador", String.valueOf(identificador++));
						radio.getElement().setAttribute("tipo", cadena.get(i));
						radio.getElement().setAttribute("caracterictica", caracteristica.get(1));
						subDiv.add(radio);
						morfologiaRadio.add(radio);
						
						if(morfologia != null)
						{
							for(byte m = 0; m < morfo.size(); m++)
							{
								Morfologia nueva = morfo.get(m);
								if(nueva.getConsecutivo().equals(radio.getElement().getAttribute("identificador")))
								{
									morfo.remove(m);
									radio.setValue(true);
									break;
								}
								
								if(Byte.parseByte(nueva.getConsecutivo()) > identificador)
									break;
							}
						}
					}
					else
					{
						CheckBox check = new CheckBox(caracteristica.get(k));
						check.getElement().setAttribute("identificador", String.valueOf(identificador++));
						check.getElement().setAttribute("tipo", cadena.get(i));
						check.getElement().setAttribute("caracterictica", caracteristica.get(1));
						subDiv.add(check);
						morfologiaCheckBox.add(check);
						
						for(byte m = 0; m < morfo.size(); m++)
						{
							Morfologia nueva = morfo.get(m);
							if(nueva.getConsecutivo().equals(check.getElement().getAttribute("identificador")))
							{
								morfo.remove(m);
								check.setValue(true);
								break;
							}
							if(Byte.parseByte(nueva.getConsecutivo()) > identificador)
								break;
						}
					}
				}

				fieldsetInterno.add(subDiv);
				div.add(fieldsetInterno);
			}
			
			fieldsetGeneral.add(div);
			subContent.add(fieldsetGeneral);
		}
		
		return subContent;
	}
	
	
	public HTMLPanel senalesParticulares()
	{
		
		HTMLPanel content = new HTMLPanel("");
		
		CaptionPanel fieldsetSenales 			= new CaptionPanel("Se\u0148ales Particulares");
		fieldsetSenales.setStyleName("senales");
		
		HTMLPanel divSenales	 				= new HTMLPanel("");
		
		/* Encabezado de la Tabla Senales Particulares */
        HTMLPanel divEncabezado 				= new HTMLPanel(" ");
        divEncabezado.setStyleName("encabezado-senales");
        
        Label lblCheck							= new Label("Si / No");
  		Label lblTipo							= new Label("Tipo");
  		Label lblUbicacion						= new Label("Ubicaci\u00F3n");
  		Label lblCaract							= new Label("Caracter\u00EDsticas");
  		
  		divEncabezado.add(lblCheck);
  		divEncabezado.add(lblTipo);
  		divEncabezado.add(lblUbicacion);
  		divEncabezado.add(lblCaract);
  		
  		divSenales.add(divEncabezado);        
  		
  		/* For para generar cada una de las posibles señanes particulares
  		 * del desaparecido en base a un array previamente definido 
  		 */
  		for(int i=0; i<listaSenales.size(); i++)
  		{
  			/* Creando el div General de las señales */
  			HTMLPanel divOpcionSenales 			= new HTMLPanel(" ");
  			divOpcionSenales.setStyleName("opcion-senales");
  			
  			/* Creando los div de los Componentes */
  			HTMLPanel divCheck 					= new HTMLPanel(" ");
  	        HTMLPanel divTipo 					= new HTMLPanel(" ");
  	        HTMLPanel divUbicacion 				= new HTMLPanel(" ");
  	        HTMLPanel divCaracteristica 		= new HTMLPanel(" ");
  	        
  	        /* Creando los Componentes */
  	        CheckBox check						= new CheckBox(listaSenales.get(i));
  	        Label lblTipoSenal					= new Label(listaSenales.get(i));
  	        TextArea textAreaUbicacion 			= new TextArea();
  	        TextArea textAreaCaracteristica		= new TextArea();
  	        
  	        if(i == 0)
  	        	textAreaCaracteristica.getElement().setAttribute("placeHolder", "forma, tama\u00F1o, color");
  	        
  	        /* Asignando id a los componenetes */
  	        check.getElement().setId("checkBox" + listaSenales.get(i));
  	        textAreaUbicacion.getElement().setId("textAreaUbicacion" + listaSenales.get(i));
  	        textAreaCaracteristica.getElement().setId("textAreaCaracteristica" + listaSenales.get(i));
  	        
  	        /* Asignando los componentes a su respectivo div */
  	        divCheck.add(check);
  	        divTipo.add(lblTipoSenal);
  	        divUbicacion.add(textAreaUbicacion);
  	        divCaracteristica.add(textAreaCaracteristica);
  	        
  	        /* Asignando los div de los componentes al div de opcion */   
  	        divOpcionSenales.add(divCheck);
  	        divOpcionSenales.add(divTipo);
  	        divOpcionSenales.add(divUbicacion);
  	        divOpcionSenales.add(divCaracteristica);
  	        
  	        /* Agregando el div opcion al contenedor de opciones */
  	        divSenales.add(divOpcionSenales);
  	        
  	        /* Si el desaparecidno no es null, se cargan en el formulario las senales particulares de la persona*/
  	        if(desaparecido != null)
  	        {
  	        	for(SenalParticular senal : desaparecido.getSenalParticular())
				{
					if(senal.getNombre().equals(listaSenales.get(i)))
					{
						check.setValue(true);
						textAreaUbicacion.setValue(senal.getUbicacion());
						textAreaCaracteristica.setValue(senal.getCaracteristica());
						break;
					}
				}
  	        }
  		}
  		
  		fieldsetSenales.add(divSenales);
        content.add(fieldsetSenales);
		return content;
	}
	
	public HTMLPanel prendasDeVestir()
	{
		HTMLPanel divPrendasVestir 				= new HTMLPanel("");
        divPrendasVestir.setStyleName("senales");
 		
 		ArrayList<ArrayList<String>> tipoPrendas= new ArrayList<ArrayList<String>>();
 		
 		ArrayList<String> superiores 			= new ArrayList<String>();
 		superiores.add("Prendas Superiores");
 		superiores.add("Camisa");
 		superiores.add("Camiseta");
 		superiores.add("Buso");
 		superiores.add("Camibuso");
 		
 		ArrayList<String> inferiores 			= new ArrayList<String>();
 		inferiores.add("Prendas Inferiores");
 		inferiores.add("Pantal\u00F3n");
 		inferiores.add("Falda");
 		inferiores.add("Pantaloneta");
 		inferiores.add("Short");
 		inferiores.add("Sudadera");
 		inferiores.add("Bermuda");
 		
 		ArrayList<String> exteriores 			= new ArrayList<String>();
 		exteriores.add("Prendas Exteriores");
 		exteriores.add("Chaqueta");
 		exteriores.add("Saco");
 		exteriores.add("Ruana");
 		exteriores.add("Poncho");
 		
 		ArrayList<String> calzado 				= new ArrayList<String>();
 		calzado.add("Calzado");
 		calzado.add("Tenis");
 		calzado.add("Botas");
 		calzado.add("Zapatos");
 		calzado.add("Sandalias");
 		
 		tipoPrendas.add(superiores);
 		tipoPrendas.add(inferiores);
 		tipoPrendas.add(exteriores);
 		tipoPrendas.add(calzado);
 		
 		for(byte i=0; i < tipoPrendas.size(); i++)
 		{
 			ArrayList<String> x 				= tipoPrendas.get(i);
 			
 			CaptionPanel fieldset			 	= new CaptionPanel(x.get(0));
 			HTMLPanel divFieldset				= new HTMLPanel("");
 			
 			divFieldset.add(this.encabezadoPrendas());
 			
 			for(byte j=1; j < x.size(); j++)
 			{
 		        HTMLPanel divPrenda				= new HTMLPanel("");
 		 		divPrenda.setStyleName("opcion-senales");
 		 		
 		        HTMLPanel divCheckPrenda		= new HTMLPanel(" ");
 		        HTMLPanel divLblPrenda			= new HTMLPanel(" ");
 		        HTMLPanel divCaracteristicas	= new HTMLPanel(" ");
 		        HTMLPanel divObservacionesPrenda= new HTMLPanel(" ");
 		 			
 		        final CheckBox checkBoxPrenda	= new CheckBox(x.get(j));
 		        Label lblPrenda					= new Label(x.get(j));
 		        TextArea textCaracteristicas	= new TextArea();
 		        TextArea textObservacionesPrenda= new TextArea();
 		        
 		        if(i == 1)
 		        {
	 		        textCaracteristicas.getElement().setAttribute("placeHolder", "material, color, talla, marca");
	 		        textObservacionesPrenda.getElement().setAttribute("placeHolder", "estampados, modificaciones, estado");
 		        }
 		        
 		        /* Llenando el array de prendas que se utiliza para capturar los datos*/
 		        listaPrendas.add(x.get(j));
 		        checkBoxPrenda.getElement().setId("checkBox" + x.get(j));
 		        textCaracteristicas.getElement().setId("textAreaCaracteristica" + x.get(j));
 		        textObservacionesPrenda.getElement().setId("textAreaObservacion" + x.get(j));
 		        
 		        divCheckPrenda.add(checkBoxPrenda);
 		        divLblPrenda.add(lblPrenda);
 		        divCaracteristicas.add(textCaracteristicas);
 		        divObservacionesPrenda.add(textObservacionesPrenda);
 		        
 		        divPrenda.add(divCheckPrenda);
 		        divPrenda.add(divLblPrenda);
 		        divPrenda.add(divCaracteristicas);
 		        divPrenda.add(divObservacionesPrenda);
 		        
 		        divFieldset.add(divPrenda);
 		        
 		        /* Si el desaparecidno no es null, se cargan en el formulario las senales particulares de la persona*/
 		        if(desaparecido != null)
 			    {
 		 			for(PrendaVestir prenda : desaparecido.getPrendaVestir())
 					{
 						if(prenda.getNombre().equals(x.get(j)))
 						{
 							checkBoxPrenda.setValue(true);
 							textCaracteristicas.setValue(prenda.getCaracteristica());
 							textObservacionesPrenda.setValue(prenda.getObservacion());
 							break;
 						}
 					}
 			    }
 			}
 			
 			fieldset.add(divFieldset);
 			divPrendasVestir.add(fieldset);
 		}
 		
        return divPrendasVestir;
	}
	
	
	@SuppressWarnings("deprecation")
	public HTMLPanel datoDesaparicion()
	{
		HTMLPanel content					= new HTMLPanel("");
		CaptionPanel fieldsetDesaparicion	= new CaptionPanel("Datos de Desaparici\u00F3n");
		HTMLPanel divDesaparicion 			= new HTMLPanel("");
		   
        Label lblFechaDes					= new Label("Fecha de Desaparici\u00F3n: *");
        Label lblDepartamentoDes			= new Label("Departamento de desaparici\u00F3n: *");
        Label lblMunicipioDes				= new Label("Ciudad o Municipio de Desaparici\u00F3n: *");
        Label lblCorregimiento				= new Label("Corregimiento o Vereda de Desaparici\u00F3n: ");
        Label lblInspeccion					= new Label("Inspecci\u00F3n de Polic\u00EDa: ");
        Label lblDescrip					= new Label("Descripci\u00F3n del Hecho: ");
        
        ListBox diaDesaparicion				= new Utilidades().diaAnio(true);
        ListBox mesDesaparicion				= new Utilidades().listaMeses();
        ListBox anioDesaparicion			= new Utilidades().diaAnio(false);
        ListBox paisDesaparicion 			= new ListBox();
        final ListBox dptoDesaparicion		= new ListBox();
        final ListBox ciudadDesaparicion	= new ListBox();
        TextBox textCorregimiento			= new TextBox();
		final TextBox textInspeccion		= new TextBox();
		final RichTextArea textDescripcion 	= new RichTextArea();
		RichTextToolbar descripcion			= new RichTextToolbar(textDescripcion);
		
		fieldsetDesaparicion.setStyleName("desaparicion");
        dptoDesaparicion.addItem("Seleccione...");
        dptoDesaparicion.setStyleName("departamento");
        diaDesaparicion.getElement().setAttribute("placeHolder", "entre 1 y 31");
        ciudadDesaparicion.addItem("Seleccione...");
        ciudadDesaparicion.setStyleName("ciudad");
        textDescripcion.getElement().setAttribute("maxlength", "500");
        descripcion.getElement().setAttribute("maxlength", "500");
		
        diaDesaparicion.getElement().setId("diaDesaparicion");
        mesDesaparicion.getElement().setId("mesDesaparicion");
        anioDesaparicion.getElement().setId("anioDesaparicion");
        dptoDesaparicion.getElement().setId("departamentoDesaparicion");
        ciudadDesaparicion.getElement().setId("ciudadDesaparicion");
        textCorregimiento.getElement().setId("corregimiento");
        textInspeccion.getElement().setId("inspeccion");
        textDescripcion.getElement().setId("descripcionHecho");
        
		divDesaparicion.add(lblFechaDes);
		divDesaparicion.add(diaDesaparicion);
		divDesaparicion.add(mesDesaparicion);
		divDesaparicion.add(anioDesaparicion);
		divDesaparicion.add(lblDepartamentoDes);
		divDesaparicion.add(dptoDesaparicion);
		divDesaparicion.add(lblMunicipioDes);
		divDesaparicion.add(ciudadDesaparicion);
		divDesaparicion.add(lblCorregimiento);
		divDesaparicion.add(textCorregimiento);
		divDesaparicion.add(lblInspeccion);
		divDesaparicion.add(textInspeccion);
		divDesaparicion.add(lblDescrip);
		divDesaparicion.add(descripcion);
		divDesaparicion.add(textDescripcion);
		
		fieldsetDesaparicion.add(divDesaparicion);
		content.add(fieldsetDesaparicion);
		
		new Utilidades().getDepartamentos("Colombia", dptoDesaparicion);
		
		dptoDesaparicion.addChangeHandler(new ChangeHandler()
		{
			public void onChange(ChangeEvent event)
			{
				if(dptoDesaparicion.getSelectedIndex() > 0)
				{
					String departamento = dptoDesaparicion.getValue(dptoDesaparicion.getSelectedIndex());
					new Utilidades().getCiudades("Colombia", departamento, ciudadDesaparicion);
				}
				else
				{
					ciudadDesaparicion.clear();
					ciudadDesaparicion.addItem("Ciudad...");
				}
			}
		});
		
		textDescripcion.addKeyUpHandler(new KeyUpHandler()
		{
			@Override
			public void onKeyUp(KeyUpEvent event) 
			{
				if (textDescripcion.getText().length() > 490) 
				{
					textDescripcion.setText(textDescripcion.getText().substring(0, 499));
					textInspeccion.setFocus(true);
				}
			}
		});
		
		if(desaparecido != null)
		{
			/* Seleccionando los datos correspondientes a la fecha de desaparicion del desaparecido */
			for(int i = 1; i < anioDesaparicion.getItemCount(); i++)
			{
				if((desaparecido.getDatoDesaparicion().getFechaDesaparicion().getYear()+1900) == Integer.parseInt((anioDesaparicion.getValue(i))))
				{
					anioDesaparicion.setSelectedIndex(i);
					break;
				}
			}
			mesDesaparicion.setSelectedIndex(desaparecido.getDatoDesaparicion().getFechaDesaparicion().getMonth()+1);
			diaDesaparicion.setSelectedIndex(desaparecido.getDatoDesaparicion().getFechaDesaparicion().getDate());
			
			/* Cargando los datos a los select de Pais, dpto y ciudad con los respectivos valores del desaparecido*/
			new Utilidades().selectedIndex(paisDesaparicion, dptoDesaparicion, ciudadDesaparicion, desaparecido.getDatoDesaparicion().getCiudadDesaparicion().getDepartamento().getPais().getNombre(), desaparecido.getDatoDesaparicion().getCiudadDesaparicion().getDepartamento().getNombre(), desaparecido.getDatoDesaparicion().getCiudadDesaparicion().getNombre());
			
			textCorregimiento.setValue(desaparecido.getDatoDesaparicion().getCorregimiento());
			textInspeccion.setValue(desaparecido.getDatoDesaparicion().getInspeccionPolicia());
			textDescripcion.setText(desaparecido.getDatoDesaparicion().getDescripcion());
		}
		
		return content;
	}
	
	/* Encabezado de la Tabla Prendas de vestir */
	public HTMLPanel encabezadoPrendas()
	{
        HTMLPanel divEncabezado				= new HTMLPanel("");
        divEncabezado.setStyleName("encabezado-senales");
        
        Label lblTrue						= new Label("Si/No");
        Label lblTipoPrenda					= new Label("Tipo");
        Label lblCaracteristicas			= new Label("Caracteristicas");
 		Label lblObservaciones				= new Label("Observaciones"); 
 		
 		divEncabezado.add(lblTrue);
 		divEncabezado.add(lblTipoPrenda);
 		divEncabezado.add(lblCaracteristicas);
 		divEncabezado.add(lblObservaciones);
 		
 		return divEncabezado;
	}
}
