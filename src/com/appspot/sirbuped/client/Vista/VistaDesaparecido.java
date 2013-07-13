package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import java.util.Date;
import com.appspot.sirbuped.client.RichTextToolbar;
import com.appspot.sirbuped.client.DTO.DatoDesaparicion;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Morfologia;
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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
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
import com.google.gwt.user.client.ui.Widget;
/*import com.appspot.sirbuped.client.RichTextToolbar;*/

public class VistaDesaparecido extends Composite 
{
	public ArrayList<String> listaSenales = new ArrayList<String>();
	public ArrayList<RadioButton> morfologiaRadio = new ArrayList<RadioButton>();
	public ArrayList<CheckBox> morfologiaCheckBox = new ArrayList<CheckBox>();
	public Boolean error = false;
	public FormPanel uploadForm = new FormPanel();
	Desaparecido desaparecido = null;
	
	public VistaDesaparecido()
	{
		HTMLPanel subContent 			= new HTMLPanel("");
		subContent.setStyleName("desaparecido");
		subContent.getElement().setId("desaparecido");
		
		listaSenales.add("Amputaciones");
		//listaSenales.add("Acn\u00E9");
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
		listaSenales.add("Enfermedad Fisica o Mental");
		listaSenales.add("Otros");
		
		final CaptionPanel divPersonales= this.datosPersonales();
		final HTMLPanel divMorfologia 	= this.datosMorfologicos();
		final HTMLPanel divSenales		= this.senalesParticulares();
		final HTMLPanel divPrendas 		= this.prendasDeVestir(); 
		final HTMLPanel divDesaparicion = this.datosDesaparicion();
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
		final Label descripcion			= new Label("Para registrarse en Sofia Plus debe ingresar informacion basica de identificacion. Tenga en cuenta que los campos marcados con * son obligatorios para continuar el registro.");
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
				error = false;
				divError.clear();
				Label errores = new Label();
				ArrayList<String> personales = new ArrayList<String>();
				
				IsWidget esWidget = null;
				com.google.gwt.user.client.Element elemento = null;
				
				personales.add("nombre1");
				personales.add("apellido1");
				personales.add("numeroDocumento");
				personales.add("edad");
				personales.add("tipoDocumento");
				personales.add("genero");
				personales.add("diaNacimiento");
				personales.add("mesNacimiento");
				personales.add("anioNacimiento");
				personales.add("fotografia");
				
				for(byte i=0; i < personales.size(); i++)
				{
					elemento = DOM.getElementById(personales.get(i));
					esWidget = getWidget(elemento);
					
					if(i < 4)
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
					else if(i < 9)
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
						if(fotografia.getFilename().isEmpty())
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
						else
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
		
		/* Evento clic boton se�ales particulares */
		btnSenales.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
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
				paso4.setStyleName("paso_pasado");	//paso5.setStyleName("paso_pasado");
				paso5.setStyleName("paso_presente");//paso6.setStyleName("paso_presente");
			}
		});
		btnPrendasVol.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				encabezado.getElementById("punta_morfologia").setClassName("punta_flecha_pasado_ultima");//encabezado.getElementById("punta_senales").setClassName("punta_flecha_pasado_ultima");
				encabezado.getElementById("senales").setClassName("flecha_presente");				//encabezado.getElementById("medicos").setClassName("flecha_presente");
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_presente");	//encabezado.getElementById("punta_medicos").setClassName("punta_flecha_presente");
				encabezado.getElementById("prendas").setClassName("flecha_futuro");
				encabezado.getElementById("punta_prendas").setClassName("punta_flecha_futuro");
				divPrendas.setVisible(false);
				btnPrendas.setVisible(false);
				btnPrendasVol.setVisible(false);
				divSenales.setVisible(true);	//divMedicos.setVisible(true);
				btnSenales.setVisible(true);	//btnMedicos.setVisible(true);
				btnSenalesVol.setVisible(true);	//btnMedicosVol.setVisible(true);
				titulo.setHTML("<h2>Se\u0148ales Particulares</h2>");	//titulo.setHTML("<h2>Antecedentes M\u00E9dicos</h2>");
				paso3.setStyleName("paso_presente");//paso4.setStyleName("paso_presente");
				paso4.setStyleName("paso_futuro");	//paso5.setStyleName("paso_futuro");
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
				
				/* Validando la fecha */
				elemento 	= DOM.getElementById("diaDesaparicion");
				esWidget 	= getWidget(elemento);
				ListBox diaDesaparicion = (ListBox) esWidget;
				
				elemento 	= DOM.getElementById("mesDesaparicion");
				esWidget 	= getWidget(elemento);
				ListBox mesDesaparicion = (ListBox) esWidget;
				
				elemento 	= DOM.getElementById("anioDesaparicion");
				esWidget 	= getWidget(elemento);
				ListBox anioDesaparicion = (ListBox) esWidget;
				
				if((diaDesaparicion.getSelectedIndex() > 0) && ((mesDesaparicion.getSelectedIndex() == 0) || (anioDesaparicion.getSelectedIndex() == 0)) ||
				   (diaDesaparicion.getSelectedIndex() == 0) && ((mesDesaparicion.getSelectedIndex() > 0) || (anioDesaparicion.getSelectedIndex() > 0)) ||
				   (diaDesaparicion.getSelectedIndex() == 0) && ((mesDesaparicion.getSelectedIndex() == 0) && (anioDesaparicion.getSelectedIndex() == 0)))
				{
						diaDesaparicion.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						mesDesaparicion.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						anioDesaparicion.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						
						errores = new Label("La fecha de desaparicion no es Valida, revise los datos e intente nuevamente");
						error = true;
						divError.add(errores);
				}
				
				elemento = DOM.getElementById("descripcionHecho");
				esWidget = getWidget(elemento);
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
				
				elemento = DOM.getElementById("diaNacimiento");
				esWidget = getWidget(elemento);
				ListBox diaNacimiento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("mesNacimiento");
				esWidget = getWidget(elemento);
				ListBox mesNacimiento = (ListBox) esWidget;
				
				elemento = DOM.getElementById("anioNacimiento");
				esWidget = getWidget(elemento);
				ListBox anioNacimiento = (ListBox) esWidget;
				
				Date fechaNacimiento = null;
				if(diaNacimiento.getSelectedIndex() != 0)
				{
					fechaNacimiento = new Date();
					fechaNacimiento.setYear(Integer.parseInt(anioNacimiento.getValue(anioNacimiento.getSelectedIndex()))-1900); 
					fechaNacimiento.setMonth((mesNacimiento.getSelectedIndex()-1));
					fechaNacimiento.setDate(diaNacimiento.getSelectedIndex());
				}
				
				elemento = DOM.getElementById("edad");
				esWidget = getWidget(elemento);
				TextBox edad = (TextBox) esWidget;
				
				/*elemento = DOM.getElementById("direccion");
				esWidget = getWidget(elemento);
				TextBox direccion = (TextBox) esWidget;*/
				
				elemento = DOM.getElementById("genero");
				esWidget = getWidget(elemento);
				ListBox listGenero = (ListBox) esWidget;
				Boolean genero;
				
				if(listGenero.getValue(listGenero.getSelectedIndex()).equals("Masculino"))
					genero = true;
				else 
					genero = false;
				
				/*elemento = DOM.getElementById("grupoSanguineo");
				esWidget = getWidget(elemento);
				ListBox gSanguineo = (ListBox) esWidget;*/
				
				elemento = DOM.getElementById("estatura");
				esWidget = getWidget(elemento);
				TextBox estatura = (TextBox) esWidget;
				
				elemento = DOM.getElementById("peso");
				esWidget = getWidget(elemento);
				TextBox peso = (TextBox) esWidget;
				
				desaparecido = new Desaparecido();
				
				desaparecido.setNombre1(nombre1.getValue());
				desaparecido.setNombre2(nombre2.getValue());
				desaparecido.setApellido1(apellido1.getValue());
				desaparecido.setApellido2(apellido2.getValue());
				desaparecido.setTipoDocumento(tipoDocumento.getValue(tipoDocumento.getSelectedIndex()));
				desaparecido.setNumeroDocumento(numeroDocumento.getValue());
				desaparecido.setFechaNacimiento(fechaNacimiento);
				desaparecido.setEdad(Byte.parseByte(edad.getValue()));
				//desaparecido.setDireccion(direccion.getValue());
				desaparecido.setGenero(genero);
				//desaparecido.setGrupoSanguineo(gSanguineo.getValue(gSanguineo.getSelectedIndex()));
				desaparecido.setEstatura(estatura.getValue());
				desaparecido.setPeso(peso.getValue());
				
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
				
				
				/* Para Capturar las Se�ales Particulares seleccionadas */ 
				
				for(byte i=0; i<listaSenales.size(); i++)
				{
					elemento = DOM.getElementById("checkBox"+listaSenales.get(i));
					esWidget = getWidget(elemento);
					
					if(esWidget != null)
					{
						CheckBox seleccionado = (CheckBox) esWidget;
						if(seleccionado.getValue())
						{
							elemento = DOM.getElementById("textAreaUbicacion"+listaSenales.get(i));
							esWidget = getWidget(elemento);
							TextArea ubicacion = (TextArea) esWidget;
							
							elemento = DOM.getElementById("textAreaCaracteristica"+listaSenales.get(i));
							esWidget = getWidget(elemento);
							TextArea caracteristica = (TextArea) esWidget;
							
							SenalParticular senal = new SenalParticular(listaSenales.get(i).toString(), ubicacion.getValue(), caracteristica.getValue());
							desaparecido.getSenalParticular().add(senal);
							//Window.alert(listaSenales.get(i) + " " + ubicacion.getValue() + " " + caracteristica.getValue());
						}
					}
				}
				
				
				// CAPTURANDO LOS DATOS RELATIVOS A LA DESAPARICION 
				elemento = DOM.getElementById("corregimiento");
				esWidget = getWidget(elemento);
				TextBox corregimiento = (TextBox) esWidget;
				
				elemento = DOM.getElementById("inspeccion");
				esWidget = getWidget(elemento);
				TextBox inspeccion = (TextBox) esWidget;
				
				Date fechaDesaparicion = null;
				if(diaDesaparicion.getSelectedIndex() != 0)
				{
					fechaDesaparicion = new Date();
					fechaDesaparicion.setYear(Integer.parseInt(anioDesaparicion.getValue(anioDesaparicion.getSelectedIndex()))-1900); 
					fechaDesaparicion.setMonth((mesDesaparicion.getSelectedIndex()-1));
					fechaDesaparicion.setDate(diaDesaparicion.getSelectedIndex());
				}
				
				DatoDesaparicion dato = new DatoDesaparicion(fechaDesaparicion, corregimiento.getValue(), inspeccion.getValue(), descripcionHecho.getText());
				desaparecido.setDatoDesaparicion(dato);
				
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				
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
		            	Window.alert("mal" + caught.toString());
		            	caught.printStackTrace();
		            }
		        });
				
				uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() 
				{
					@Override
					public void onSubmitComplete(SubmitCompleteEvent event) 
					{
						desaparecido.setKeyFoto("/sirbuped/uploadImagen?blob-key=" + event.getResults().trim());
					
						DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
						desaparecidoService.ingresar(desaparecido, new AsyncCallback<Void>() 
						{
						    public void onSuccess(Void ignore) 
						    {
						    	History.newItem("Registro-Correcto");
						    	RootPanel.get("content").add(new VistaConsultarDesaparecido(desaparecido));
						    }
						    public void onFailure(Throwable error) 
							{
								Window.alert(error.toString());
							}
				        });
						
						
					}
				});
			}
		});
		
		btnDesaparicionVol.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				encabezado.getElementById("punta_senales").setClassName("punta_flecha_pasado_ultima"); //encabezado.getElementById("punta_medicos").setClassName("punta_flecha_pasado_ultima");
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
				paso4.setStyleName("paso_presente");//paso5.setStyleName("paso_presente");
				paso5.setStyleName("paso_futuro");	//paso6.setStyleName("paso_futuro");
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
		Label lblEdad					= new Label("Edad: *");
		//Label lblGsanguineo			= new Label("Grupo Sangu\u00EDneo:");
		//Label lblDireccion			= new Label("Direcci\u00F3n:");
		Label lblFotografia					= new Label("Fotografia:");
		
		TextBox textNombre1 			= new TextBox();
		TextBox textNombre2 			= new TextBox();
		TextBox textApellido1 			= new TextBox();
		TextBox textApellido2 			= new TextBox();
		ListBox selectTipoDocument		= new ListBox();
		final TextBox textDocumento 	= new TextBox();
		ListBox selectPais 				= new ListBox();
		ListBox selectDepartamento 		= new ListBox();
		ListBox selectCiudad 			= new ListBox();
		ListBox selectGenero 			= new ListBox();
		final TextBox textEdad 			= new TextBox();
		ListBox dateDia 				= this.diaAnio(true);
		ListBox selectMes 				= this.listaMeses();
		ListBox selectAnio 				= this.diaAnio(false);
	    //ListBox selectGsanguineo 		= new ListBox();
		//TextBox textDireccion			= new TextBox();
		FileUpload fotografia 			= new FileUpload();
		fotografia.setName("foto");
		
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
		dateDia.getElement().setId("diaNacimiento");
		selectMes.getElement().setId("mesNacimiento");
		selectAnio.getElement().setId("anioNacimiento");
		fotografia.getElement().setId("fotografia");
		//selectGsanguineo.getElement().setId("grupoSanguineo");
		//textDireccion.getElement().setId("direccion");
		
		selectTipoDocument.addItem("Seleccione...");
	  	selectTipoDocument.addItem("C\u00E9dula de Ciudadania");
		selectTipoDocument.addItem("C\u00E9dula Extrangera");
		selectTipoDocument.addItem("Tarjeta de Identidad");
		selectTipoDocument.addItem("Pasaporte");
		
		selectPais.addItem("Pa\u00EDs...");
	    selectPais.setStyleName("pais");
	    selectDepartamento.addItem("Dpto...");
		selectDepartamento.setStyleName("departamento");
		selectCiudad.addItem("Ciudad...");
	    selectCiudad.setStyleName("ciudad");
	    
	    selectGenero.addItem("Seleccione...");
		selectGenero.addItem("Masculino");
		selectGenero.addItem("Femenino");
		
		/*selectGsanguineo.addItem("Seleccione...", "No Registra" );
		selectGsanguineo.addItem("RH O+");
		selectGsanguineo.addItem("RH O-");
		selectGsanguineo.addItem("RH A+");
		selectGsanguineo.addItem("RH A-");
		selectGsanguineo.addItem("RH B+");
		selectGsanguineo.addItem("RH B-");
		selectGsanguineo.addItem("RH AB+");
		selectGsanguineo.addItem("RH AB-");*/
		
		textNombre1.setMaxLength(20);
		textNombre2.setMaxLength(20);
		textApellido1.setMaxLength(20);
		textApellido2.setMaxLength(20);
		textDocumento.setMaxLength(10);
		//textDireccion.setMaxLength(50);
		textEdad.setMaxLength(2);
		
		
		textEdad.getElement().setAttribute("placeHolder", "al momento de la desaparici\u00F3n");
		dateDia.getElement().setAttribute("placeHolder", "D\u00EDa entre 1 y 31");
		selectMes.setStyleName("select_mes");
		
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
		divDatosPersonales1.add(lblGenero);
		divDatosPersonales1.add(selectGenero);
		divDatosPersonales2.add(lblLugarNacimiento);
		divDatosPersonales2.add(selectPais);
		divDatosPersonales2.add(selectDepartamento);
		divDatosPersonales2.add(selectCiudad);
		divDatosPersonales2.add(lblFechaNacimiento);
		divDatosPersonales2.add(dateDia);
		divDatosPersonales2.add(selectMes);
		divDatosPersonales2.add(selectAnio);
		divDatosPersonales2.add(lblEdad);
		divDatosPersonales2.add(textEdad);
		//divDatosPersonales2.add(lblDireccion);
		//divDatosPersonales2.add(textDireccion);
		//divDatosPersonales2.add(lblGsanguineo);
		//divDatosPersonales2.add(selectGsanguineo);
		divDatosPersonales2.add(lblFotografia);
		divDatosPersonales2.add(fotografia);
				
		divDatosPersonalesG.add(divDatosPersonales1);
		divDatosPersonalesG.add(divDatosPersonales2);
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
		        	ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico, y corresponde al numero de identidad de la" +
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
		        	ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico, comprendido entre los n\u00FAmeros" +
		        				 " 1 y 99", "error");
		        	textEdad.setText("");
		        }
		    }
		});
		
		return fieldsetPersonales;
	}
	
	
	public HTMLPanel datosMorfologicos()
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
		orejas.add("No Recuerda");
		
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
		particularidadCabello.add("No Recuerda");
		
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
		//particularidadOjos.add("Usa Gafas");
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
				        	ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico, estatura" +
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
				        	ventanaModal("Error", "El valor Ingresado debe ser num\u00E9rico y representa el peso aproximado en kilogramos" +
				        				 " persona desaparecido en kilogramos", "error");
				        	textPeso.setText("");
				        }
				    }
				});
				
				div.add(peso);
				div.add(estatura);
			}
			
			for(byte j = 0; j < tipo.size(); j++)
			{
				ArrayList<String> caracteristica = tipo.get(j);
				CaptionPanel fieldsetInterno = new CaptionPanel(caracteristica.get(1));
				HTMLPanel subDiv = new HTMLPanel("");
				
				for(byte k = 2; k < caracteristica.size(); k++)
				{
					if(caracteristica.get(0) == "radio")
					{
						RadioButton radio = new RadioButton(caracteristica.get(1) + " " + cadena.get(i), caracteristica.get(k));
						radio.getElement().setAttribute("identificador", String.valueOf(identificador++));
						radio.getElement().setAttribute("tipo", cadena.get(i));
						radio.getElement().setAttribute("caracterictica", caracteristica.get(1));
						subDiv.add(radio);
						this.morfologiaRadio.add(radio);
					}
					else
					{
						CheckBox check = new CheckBox(caracteristica.get(k));
						check.getElement().setAttribute("identificador", String.valueOf(identificador++));
						check.getElement().setAttribute("tipo", cadena.get(i));
						check.getElement().setAttribute("caracterictica", caracteristica.get(1));
						subDiv.add(check);
						this.morfologiaCheckBox.add(check);
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
        
  		
  		/* For para generar cada una de las posibles se�anes particulares
  		 * del desaparecido en base a un array previamente definido 
  		 */
  		for(int i=0; i<listaSenales.size(); i++)
  		{
  			/* Creando el div General de las se�ales */
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
  		}
  		
  		fieldsetSenales.add(divSenales);
        content.add(fieldsetSenales);
		return content;
	}
	
	
	public HTMLPanel prendasDeVestir()
	{
		HTMLPanel divPrendasVestir 				= new HTMLPanel("");
        divPrendasVestir.setStyleName("prendas");
 		
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
 		
 		/*ArrayList<String> interiores 			= new ArrayList<String>();
 		interiores.add("Prendas Interiores");
 		interiores.add("Brassier");
 		interiores.add("Interiores");
 		interiores.add("Tanga");
 		interiores.add("Boxer");
 		interiores.add("Medias");*/
 		
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
 		//tipoPrendas.add(interiores);
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
 		 		divPrenda.setStyleName("opcion-prendas");
 		 		
 		        HTMLPanel divCheckPrenda		= new HTMLPanel(" ");
 		        HTMLPanel divLblPrenda			= new HTMLPanel(" ");
 		        HTMLPanel divMaterialPrenda		= new HTMLPanel(" ");
 		        HTMLPanel divColorPrenda		= new HTMLPanel(" ");
 		        //HTMLPanel divTallaPrenda		= new HTMLPanel(" ");
 		        HTMLPanel divMarcaPrenda		= new HTMLPanel(" ");
 		        HTMLPanel divObservacionesPrenda= new HTMLPanel(" ");
 		 			
 		        final CheckBox checkBoxPrenda	= new CheckBox(x.get(j));
 		        Label lblPrenda					= new Label(x.get(j));
 		        TextBox textMaterialPrenda 		= new TextBox();
 		        TextBox textColorPrenda 		= new TextBox();
 		        //TextBox textTallaPrenda 		= new TextBox();
 		        TextBox textMarcaPrenda 		= new TextBox();
 		        TextArea textObservacionesPrenda= new TextArea();
 		        
 		        divCheckPrenda.add(checkBoxPrenda);
 		        divLblPrenda.add(lblPrenda);
 		        divMaterialPrenda.add(textMaterialPrenda);
 		        divColorPrenda.add(textColorPrenda);
 		        //divTallaPrenda.add(textTallaPrenda);
 		        divMarcaPrenda.add(textMarcaPrenda);
 		        divObservacionesPrenda.add(textObservacionesPrenda);
 		        
 		        divPrenda.add(divCheckPrenda);
 		        divPrenda.add(divLblPrenda);
 		        divPrenda.add(divMaterialPrenda);
 		        divPrenda.add(divColorPrenda);
 		        //divPrenda.add(divTallaPrenda);
 		        divPrenda.add(divMarcaPrenda);
 		        divPrenda.add(divObservacionesPrenda);
 		        
 		        divFieldset.add(divPrenda);
 			}
 			fieldset.add(divFieldset);
 			divPrendasVestir.add(fieldset);
 		}
 		
 		/*
        if(RootPanel.get().getOffsetWidth() < 500)
        {
		}
		*/
        return divPrendasVestir;
	}
	
	
	public HTMLPanel datosDesaparicion()
	{
		HTMLPanel content					= new HTMLPanel("");
		CaptionPanel fieldsetDesaparicion	= new CaptionPanel("Datos de Desaparici\u00F3n");
		HTMLPanel divDesaparicion 			= new HTMLPanel("");
		   
        Label lblFechaDes					= new Label("Fecha de Desaparici\u00F3n: *");
        //Label lblPaisDes					= new Label("Pais de Desaparicion");
        Label lblDepartamentoDes			= new Label("Departamento de desaparici\u00F3n: *");
        Label lblMunicipioDes				= new Label("Ciudad o Municipio de Desaparici\u00F3n: *");
        Label lblCorregimiento				= new Label("Corregimiento o Vereda de Desaparici\u00F3n: ");
        Label lblInspeccion					= new Label("Inspecci\u00F3n de Polic\u00EDa: ");
        Label lblDescrip					= new Label("Descripci\u00F3n del Hecho: ");
        
        ListBox diaDesaparicion				= this.diaAnio(true);
        ListBox  mesDesaparicion			= this.listaMeses();
        ListBox anioDesaparicion			= this.diaAnio(false);
        //ListBox paisDes 					= new ListBox();
        ListBox departamentoDesaparicion	= new ListBox();
        ListBox ciudadDesaparicion			= new ListBox();
        TextBox textCorregimiento			= new TextBox();
		final TextBox textInspeccion				= new TextBox();
		final RichTextArea textDescripcion 		= new RichTextArea();
		RichTextToolbar descripcion			= new RichTextToolbar(textDescripcion);
		
		fieldsetDesaparicion.setStyleName("desaparicion");
		//paisDes.addItem("Seleccione...");
        //paisDes.setStyleName("pais");
        departamentoDesaparicion.addItem("Seleccione...");
        departamentoDesaparicion.setStyleName("departamento");
        diaDesaparicion.setStyleName("date_dia");
        diaDesaparicion.getElement().setAttribute("placeHolder", "entre 1 y 31");
        ciudadDesaparicion.addItem("Seleccione...");
        ciudadDesaparicion.setStyleName("ciudad");
        textDescripcion.getElement().setAttribute("maxlength", "500");
        descripcion.getElement().setAttribute("maxlength", "500");
		
        diaDesaparicion.getElement().setId("diaDesaparicion");
        mesDesaparicion.getElement().setId("mesDesaparicion");
        anioDesaparicion.getElement().setId("anioDesaparicion");
        departamentoDesaparicion.getElement().setId("dptoDesaparicion");
        ciudadDesaparicion.getElement().setId("ciudadDesaparicion");
        textCorregimiento.getElement().setId("corregimiento");
        textInspeccion.getElement().setId("inspeccion");
        textDescripcion.getElement().setId("descripcionHecho");
        
		divDesaparicion.add(lblFechaDes);
		divDesaparicion.add(diaDesaparicion);
		divDesaparicion.add(mesDesaparicion);
		divDesaparicion.add(anioDesaparicion);
		//divDesaparicion.add(lblPaisDes);
		//divDesaparicion.add(paisDes);
		divDesaparicion.add(lblDepartamentoDes);
		divDesaparicion.add(departamentoDesaparicion);
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
		
		textDescripcion.addKeyUpHandler(new KeyUpHandler()
		{
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (textDescripcion.getText().length() > 490) 
				{
					textDescripcion.setText(textDescripcion.getText().substring(0, 499));
					textInspeccion.setFocus(true);
				}
			}
		});
		
		return content;
	}
	
	
	public HTMLPanel encabezadoPrendas()
	{
		/* Encabezado de la Tabla Prendas de vestir */
        HTMLPanel divEncabezado				= new HTMLPanel("");
        divEncabezado.setStyleName("encabezado-prendas");
        
        Label lblTrue						= new Label("Si/No");
        Label lblTipoPrenda					= new Label("Tipo");
        Label lblMaterial					= new Label("Material");
   		Label lblColor						= new Label("Color");
   		//Label lblTalla						= new Label("Talla");
   		Label lblMarca						= new Label("Marca");
 		Label lblObservaciones				= new Label("Observaciones"); 
 		
 		divEncabezado.add(lblTrue);
 		divEncabezado.add(lblTipoPrenda);
 		divEncabezado.add(lblMaterial);
 		divEncabezado.add(lblColor);
 		//divEncabezado.add(lblTalla);
 		divEncabezado.add(lblMarca);
 		divEncabezado.add(lblObservaciones);
 		
 		return divEncabezado;
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
	
	
	public ListBox listaMeses()
	{
		ListBox selectMes = new ListBox();
		
		selectMes.setStyleName("select_mes");
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
			select.setStyleName("select_anio");
			select.addItem("A\u0148o...");
			for(int i=2013; i > 1909; i--)
			{
				select.addItem(String.valueOf(i));
			}
		}
		return select;
	}
	
	
	public DialogBox ventanaModal(String titulo, String contenido, String tipo) 
	{
		final DialogBox dialogBox  = new DialogBox();
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Cerrar");
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
}