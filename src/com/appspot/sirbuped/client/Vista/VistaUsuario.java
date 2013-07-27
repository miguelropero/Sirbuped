package com.appspot.sirbuped.client.Vista;

import java.util.Date;
import com.appspot.sirbuped.client.JCrypt;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Usuario;
import com.appspot.sirbuped.client.Interfaz.UsuarioService;
import com.appspot.sirbuped.client.Interfaz.UsuarioServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaUsuario extends Composite 
{
	public VistaUsuario()
	{
		final HTMLPanel subContent	= new HTMLPanel(" ");
		subContent.setStyleName("usario_personales");
	
		/*
		 * DATOS PERSONALES
		 * Creaci�n del Fieldset contenedor de los datos
		 * Creaci�n de los Widgets de ingreso de datos
		 */
		CaptionPanel fieldsetPersonales = new CaptionPanel("Datos Personales");
		fieldsetPersonales.setStyleName("fieldset_personales");
		
		final HTMLPanel divPersonales 	= new HTMLPanel(" ");
		
		Label lblNombres			= new Label("Nombres: *");
		Label lblApellido1			= new Label("1er Apellido: *");
		Label lblApellido2			= new Label("2do Apellido: *");
		Label lblTipoDocumento		= new Label("Tipo de Documento: *");
		Label lblDocumento			= new Label("Documento: *");
		Label lblFechaNacimiento	= new Label("Fecha Nacimiento*");
		
		final TextBox textNombres 	= new TextBox();
		final TextBox textApellido1	= new TextBox();
		final TextBox textApellido2	= new TextBox();
		final TextBox textDocumento	= new TextBox();
		final ListBox selectTipoDoc	= new ListBox();
			selectTipoDoc.addItem("Seleccione...");
			selectTipoDoc.addItem("C\u00E9dula de Ciudadania");
			selectTipoDoc.addItem("C\u00E9dula Extrangera");
			selectTipoDoc.addItem("Tarjeta de Identidad");
			selectTipoDoc.addItem("Pasaporte");
		final ListBox selectDia		= new Utilidades().diaAnio(true);
		final ListBox selectMes		= new Utilidades().listaMeses();
		final ListBox selectAnio	= new Utilidades().diaAnio(false);
			
		divPersonales.add(lblNombres);
		divPersonales.add(textNombres);
		divPersonales.add(lblApellido1);
		divPersonales.add(textApellido1);
		divPersonales.add(lblApellido2);
		divPersonales.add(textApellido2);
		divPersonales.add(lblTipoDocumento);
		divPersonales.add(selectTipoDoc);
		divPersonales.add(lblDocumento);
		divPersonales.add(textDocumento);
		divPersonales.add(lblFechaNacimiento);
		divPersonales.add(selectDia);
		divPersonales.add(selectMes);
		divPersonales.add(selectAnio);
		
		fieldsetPersonales.add(divPersonales);
		
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
		
		/*
		 * DATOS DE CONTACTO DE USUARIO
		 * Creaci�n del Fieldset contenedor
		 * Creaci�n de los Widgets para la captura de datos
		 */
		
		CaptionPanel fieldsetContacto = new CaptionPanel("Datos de Contacto");
		fieldsetContacto.setStyleName("fieldset_contacto");
		
		HTMLPanel divContacto = new HTMLPanel(" ");
		
		Label lblEmail					= new Label("Correo Electr\u00F3nico: *");
		Label lblTelefono1 				= new Label("Tel\u00E9fono 1:");
		Label lblTelefono2				= new Label("Tel\u00E9fono 2:");
		Label lblTelefonoMovil			= new Label("Tel\u00E9fono Celular:");
		Label lblResidencia			= new Label("Lugar de Residencia:");
		Label lblDireccion				= new Label("Direcci\u00F3n:");
		
		final TextBox textEmail			= new TextBox();
		final TextBox textTelefono1		= new TextBox();
		final TextBox textTelefono2		= new TextBox();
		final TextBox textTelefonoMovil = new TextBox();
		final ListBox selectPais		= new ListBox();
			selectPais.addItem("Pais...");
		final ListBox selectDepartamento= new ListBox();
			selectDepartamento.addItem("Departamento...");
		final ListBox selectCiudad		= new ListBox();
			selectCiudad.addItem("Ciudad...");
		final TextBox textDireccion		= new TextBox();
		//textDireccion.getElement().setAttribute("placeHolder", "Direcci\u00F3n");
		
		selectCiudad.addChangeHandler(new ChangeHandler()
		{
			  public void onChange(ChangeEvent event)
			  {
				  Window.alert("Something got selected " + selectCiudad.getSelectedIndex());
			  }
		});
		
		divContacto.add(lblEmail);
		divContacto.add(textEmail);
		divContacto.add(lblTelefono1);
		divContacto.add(textTelefono1);
		divContacto.add(lblTelefono2);
		divContacto.add(textTelefono2);
		divContacto.add(lblTelefonoMovil);
		divContacto.add(textTelefonoMovil);
		divContacto.add(lblResidencia);
		divContacto.add(selectPais);
		divContacto.add(selectDepartamento);
		divContacto.add(selectCiudad);
		divContacto.add(lblDireccion);
		divContacto.add(textDireccion);
		
		fieldsetContacto.add(divContacto);
		
		
		/*
		 * PASSWORD
		 * Creaci�n del Fieldset para el Password de la Cuenta
		 * Creaci�n de los Widgets para el ingreso del password
		 */
		
		CaptionPanel fieldsetPassword	= new CaptionPanel("Contrase\u00F1a");
		fieldsetPassword.setStyleName("fieldset_password");
		
		HTMLPanel divPassword 			= new HTMLPanel(" ");
		
		Label lblPassword1 				= new Label("Contrase\u00F1a: *");
		Label lblPassword2 				= new Label("Confirme Contrase\u00F1a: *");
		
		final PasswordTextBox textPassword1 = new PasswordTextBox();
		final PasswordTextBox textPassword2 = new PasswordTextBox();
		
		textPassword1.getElement().setAttribute("placeHolder", "m\u00EDnimo 5 caracteres");
		
		divPassword.add(lblPassword1);		
		divPassword.add(textPassword1);
		divPassword.add(lblPassword2);
		divPassword.add(textPassword2);
		
		fieldsetPassword.add(divPassword);
		
		
		/*
		 * OPCIONES Y TERMINOS Y CONDICIONES
		 * Creaci�n del Fieldset para las opciones
		 * Creaci�n de los Widgets para captura de opciones
		 */
		
		CaptionPanel fieldsetOpciones = new CaptionPanel("Opciones - T\u00E9rminos y Condiciones");
		fieldsetOpciones.setStyleName("fieldset_opciones");
		
		HTMLPanel divOpciones = new HTMLPanel(" ");
		
		final CheckBox checkBoxBoletin = new CheckBox("Desea suscribirse a nuestro bolet\u00EDn mensual de desapariciones");
		final CheckBox checkBoxCondiciones = new CheckBox("Acepta los t\u00E9rminos y condiciones de Sirbuped *");
		final Button btnVerCondiciones = new Button("ver terminos y condiciones");
		divOpciones.add(checkBoxBoletin);
		divOpciones.add(checkBoxCondiciones);
		divOpciones.add(btnVerCondiciones);
		
		if(checkBoxCondiciones.getValue())
		{
			
		}
		
		fieldsetOpciones.add(divOpciones);
		
		/*
		 * Creaci�n de los botones para registrar el usuario, limpiar el formulario y
		 * cancelar la acci�n actual y volver al home
		 */
	
		HTMLPanel divBotones = new HTMLPanel(" ");
		divBotones.setStyleName("div_botones");
		
		final Button btnRegistrar = new Button("Registrar");
		Button btnCancelar = new Button("Cancelar");
		Button btnLimpiar = new Button("Limpiar");
		
		divBotones.add(btnRegistrar);
		divBotones.add(btnLimpiar);
		divBotones.add(btnCancelar);
		
		/*
		 * PANEL DE NOTIFICACI�N DE ERRORES
		 * Inicialmente no es visible
		 */
		
		final HTMLPanel divError = new HTMLPanel("");
		divError.setStyleName("div_error");
		divError.setVisible(false);
		
		/*
		 * Agregando los componentes generados al panel principal
		 */
		
		subContent.add(fieldsetPersonales);
		subContent.add(fieldsetContacto);
		subContent.add(fieldsetPassword);
		subContent.add(fieldsetOpciones);
		subContent.add(divError);
		subContent.add(divBotones);
		
		/*
		 * Agregando el evento clic al boton registrar
		 */
		
		btnRegistrar.addClickHandler(new ClickHandler() 
		{
			@SuppressWarnings("deprecation")
			public void onClick(ClickEvent event) 
			{
				/*
				 * Validando los campos abligatorios del formulario
				 */
				divError.clear();
				boolean hayErrores=false;
				if(textNombres.getValue().isEmpty())
				{
					textNombres.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores=true;
					textNombres.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textNombres.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				if(textApellido1.getValue().isEmpty())
				{
					textApellido1.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores=true;
					textApellido1.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textApellido1.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				
				if(selectTipoDoc.getSelectedIndex() == 0)
				{
					selectTipoDoc.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores=true;
					selectTipoDoc.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							selectTipoDoc.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
					
				}
				if(textDocumento.getValue().isEmpty())
				{
					textDocumento.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores=true;
					textDocumento.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textDocumento.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				if(hayErrores)
				{
					Label lblError = new Label("El formulario contiene campos vacios que son obligatorios");
					divError.add(lblError);
				}
				if((selectDia.getSelectedIndex() == 0) || (selectMes.getSelectedIndex() == 0) || (selectAnio.getSelectedIndex() == 0))
				{
					Label lblError = new Label("La fecha de nacimiento no es valida");
					divError.add(lblError);
					hayErrores=true;
					if(selectDia.getSelectedIndex() == 0)
					{
						selectDia.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						selectDia.addBlurHandler(new BlurHandler()
						{
							@Override
							public void onBlur(BlurEvent event)
							{
								if(selectDia.getSelectedIndex() > 0)
								{
									selectDia.getElement().setAttribute("style", "");
									divError.setVisible(false);
								}
							}
						});
					}
					if(selectMes.getSelectedIndex() == 0)
					{
						selectMes.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						selectMes.addBlurHandler(new BlurHandler()
						{
							@Override
							public void onBlur(BlurEvent event)
							{
								if(selectMes.getSelectedIndex() > 0)
								{
									selectMes.getElement().setAttribute("style", "");
									divError.setVisible(false);
								}
							}
						});
					}
					if(selectAnio.getSelectedIndex() == 0)
					{
						selectAnio.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
						selectAnio.addBlurHandler(new BlurHandler()
						{
							@Override
							public void onBlur(BlurEvent event)
							{
								if(selectAnio.getSelectedIndex() > 0)
								{
									selectAnio.getElement().setAttribute("style", "");
									divError.setVisible(false);
								}
							}
						});
					}
				}
				if(!textEmail.getValue().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
				{
					textEmail.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					Label lblError = new Label("El correo electr\u00F3nico no tiene una sintaxis valida");
					divError.add(lblError);
					hayErrores=true;
					textEmail.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textEmail.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				if((!textPassword1.getValue().equals(textPassword2.getValue())) || (textPassword1.getValue().isEmpty()))
				{
					textPassword1.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					textPassword2.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					Label lblError = new Label("Las contrase\u00F1as no coinciden");
					divError.add(lblError);
					hayErrores=true;
					textPassword1.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textPassword1.getElement().setAttribute("style", "");
							textPassword2.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				if(!checkBoxCondiciones.getValue())
				{
					checkBoxCondiciones.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					Label lblError = new Label("Debe aceptar los Terminos y Condiciones para hacer uso del servicio");
					divError.add(lblError);
					hayErrores=true;
					checkBoxCondiciones.addClickHandler(new ClickHandler()
					{
						@Override
						public void onClick(ClickEvent event)
					    {
							if(checkBoxCondiciones.getValue())
							{
								checkBoxCondiciones.getElement().setAttribute("style", "");
								divError.setVisible(false);
							}
					    }
					});
				}
				if(hayErrores)
				{
					divError.setVisible(true);
					return;
				}
				
				Date fechaNacimiento = new Date();
				fechaNacimiento.setYear(Integer.parseInt(selectAnio.getValue(selectAnio.getSelectedIndex()))-1900); 
				fechaNacimiento.setMonth((selectMes.getSelectedIndex()-1));
				fechaNacimiento.setDate(selectDia.getSelectedIndex());
				
				/* Encrptando la clave del usuario */
				
				String password = JCrypt.crypt(new Utilidades().salto(), textPassword1.getValue());
				
				/*
				 * Realizaci�n asincrona para guardar los datos
				 */
				final HTML mensaje = new HTML("");
				subContent.clear();
				
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				
				UsuarioServiceAsync usuario = GWT.create(UsuarioService.class);
				Usuario nuevo = new Usuario(textNombres.getText(), textApellido1.getText(), textApellido2.getText(), selectTipoDoc.getValue(selectTipoDoc.getSelectedIndex()), textDocumento.getText(), fechaNacimiento, textEmail.getText(), textTelefono1.getText(), textTelefono2.getText(), textTelefonoMovil.getText(), textDireccion.getText(), password);
				usuario.addUsuario(nuevo, new AsyncCallback<Void>() 
				{
				      public void onSuccess(Void ignore) 
				      {
				    	  cargando.getElement().setAttribute("style", "display:none");
				    	  new Utilidades().ventanaModal("Registro Exitoso", "El usuario se ha registrado correctamente. Verfique su correo electronico para activar su cuenta", "Exito");
				    	  History.newItem("iniciar-sesion");
				      }
				      public void onFailure(Throwable error) 
				      {
				    	  new Utilidades().ventanaModal("Error", error.toString(), "error");
				    	  mensaje.setText(error.toString());
				      }
				});
			}
		});
		
		
		/*
		 * Agregando el evento clic al boton limpiar
		 */
		btnLimpiar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				//Limpiando los datos personales
				textNombres.setText("");
				textApellido1.setText("");
				textApellido2.setText("");
				textDocumento.setText("");
				selectTipoDoc.setItemSelected(0, true);
				selectDia.setItemSelected(0, true);
				selectMes.setItemSelected(0, true);
				selectAnio.setItemSelected(0, true);
				
				//Limpiando los datos de contacto
				textEmail.setText("");
				textTelefono1.setText("");
				textTelefono2.setText("");
				textTelefonoMovil.setText("");
				selectDepartamento.setItemSelected(0, true);
				selectCiudad.setItemSelected(0, true);
				textDireccion.setText("");
				
				//Limpiando las contrase�as
				textPassword1.setText("");
				textPassword2.setText("");
				
				//Opciones
				checkBoxBoletin.setValue(false);
				checkBoxCondiciones.setValue(false);
			}
		});
		
		/*
		 * Agregando el evento clic al boton Cancelar
		 */
		btnCancelar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(!History.getToken().equals("registrar"))
				{
					History.newItem("home");
				}
			}
		});
		
		
		/*
		 * Agregando el evento clic al checkbox de los Terminos y Condiciones
		 * Si el checbox esta seleccionado habilita el boton enviar sino lo inhabilita
		 
		checkBoxCondiciones.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(checkBoxCondiciones.getValue())
					btnRegistrar.setEnabled(true);
				else
					btnRegistrar.setEnabled(false);
			}
		});*/
		
		btnVerCondiciones.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				//Window.alert("hola");
				final DialogBox dialogBox = new DialogBox();
				dialogBox.getElement().setId("miguel");
				//dialogBox.setWidth("900px");
				//dialogBox.setHeight("600px");
				
				//dialogBox.setText("Remote Procedure Call");
				dialogBox.setAnimationEnabled(true);
				final Button closeButton = new Button("Cerrar");
				// We can set the id of a widget by accessing its Element
				closeButton.getElement().setId("closeButton");
				
				final HTMLPanel divDialogbox = new HTMLPanel("");
				final HTMLPanel fade = new HTMLPanel("");
				final HTMLPanel overlay = new HTMLPanel("");
				fade.setStyleName("fade");
				overlay.setStyleName("divModal");
				
				final HTMLPanel titulo = new HTMLPanel("<h1>Terminos y Condiciones del Servicio</h1>");
				titulo.setStyleName("titulo");
				
				final HTMLPanel parrafo1 = new HTMLPanel("Normally, both your asses would be dead as fucking fried chicken, but you happen to pull this " +
						"shit while I'm in a transitional period so I don't wanna kill you, I wanna help you. But I can't give you " +
						"this case, it don't belong to me. Besides, I've already been through too much shit this morning over this " +
						"case to hand it over to your dumb ass. ");
				parrafo1.setStyleName("parrafo");
				
				final HTMLPanel parrafo2 = new HTMLPanel("Normally, both your asses would be dead as fucking fried chicken, but you happen to pull this " +
						"shit while I'm in a transitional period so I don't wanna kill you, I wanna help you. But I can't give you " +
						"this case, it don't belong to me. Besides, I've already been through too much shit this morning over this " +
						"case to hand it over to your dumb ass. ");
				parrafo2.setStyleName("parrafo");
				
				final HTMLPanel parrafo3 = new HTMLPanel("Normally, both your asses would be dead as fucking fried chicken, but you happen to pull this " +
						"shit while I'm in a transitional period so I don't wanna kill you, I wanna help you. But I can't give you " +
						"this case, it don't belong to me. Besides, I've already been through too much shit this morning over this " +
						"case to hand it over to your dumb ass. ");
				parrafo3.setStyleName("parrafo");
				
				final HTMLPanel parrafo4 = new HTMLPanel("Normally, both your asses would be dead as fucking fried chicken, but you happen to pull this " +
						"shit while I'm in a transitional period so I don't wanna kill you, I wanna help you. But I can't give you " +
						"this case, it don't belong to me. Besides, I've already been through too much shit this morning over this " +
						"case to hand it over to your dumb ass. ");
				parrafo4.setStyleName("parrafo");
				
				overlay.add(titulo);
				overlay.add(parrafo1);
				overlay.add(parrafo2);
				overlay.add(parrafo3);
				overlay.add(parrafo4);
				overlay.add(closeButton);
				divDialogbox.add(fade);
				divDialogbox.add(overlay);
				
				dialogBox.setWidget(divDialogbox);
				dialogBox.center();

				closeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
					}
				});
			}
		});
		
		initWidget(subContent);
	}
}
