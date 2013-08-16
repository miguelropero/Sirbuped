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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaUsuario extends Composite 
{
	
	@SuppressWarnings("deprecation")
	public VistaUsuario(final Usuario usuario)
	{
		final HTMLPanel subContent	= new HTMLPanel(" ");
		subContent.setStyleName("usario_personales");
	
		/*
		 * DATOS PERSONALES
		 * Creación del Fieldset contenedor de los datos
		 * Creación de los Widgets de ingreso de datos
		 */
		CaptionPanel fieldsetPersonales = new CaptionPanel("Datos Personales");
		fieldsetPersonales.setStyleName("fieldset_personales");
		
		final HTMLPanel divPersonales 	= new HTMLPanel(" ");
		
		Label lblNombres			= new Label("Nombres: *");
		Label lblApellidos			= new Label("Apellidos: *");
		Label lblTipoDocumento		= new Label("Tipo de Documento: *");
		Label lblDocumento			= new Label("Documento: *");
		Label lblFechaNacimiento	= new Label("Fecha Nacimiento*");
		
		final TextBox textNombres 	= new TextBox();
		final TextBox textApellidos	= new TextBox();
		final TextBox textDocumento	= new TextBox();
		final ListBox selectTipoDoc	= new ListBox();
			selectTipoDoc.addItem("Seleccione...");
			selectTipoDoc.addItem("C\u00E9dula de Ciudadania");
			selectTipoDoc.addItem("C\u00E9dula Extrangera");
			selectTipoDoc.addItem("Pasaporte");
		final ListBox selectDia		= new Utilidades().diaAnio(true);
		final ListBox selectMes		= new Utilidades().listaMeses();
		final ListBox selectAnio	= new Utilidades().diaAnio(false);
			
		divPersonales.add(lblNombres);
		divPersonales.add(textNombres);
		divPersonales.add(lblApellidos);
		divPersonales.add(textApellidos);
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
		 * Creación del Fieldset contenedor
		 * Creación de los Widgets para la captura de datos
		 */
		
		CaptionPanel fieldsetContacto = new CaptionPanel("Datos de Contacto");
		fieldsetContacto.setStyleName("fieldset_contacto");
		
		HTMLPanel divContacto = new HTMLPanel(" ");
		
		Label lblEmail					= new Label("Correo Electr\u00F3nico: *");
		Label lblTelefono 				= new Label("Tel\u00E9fono:");
		Label lblCelular				= new Label("Tel\u00E9fono Celular:");
		Label lblResidencia				= new Label("Lugar de Residencia:");
		Label lblDireccion				= new Label("Direcci\u00F3n:");
		
		final TextBox textEmail			= new TextBox();
		final TextBox textTelefono		= new TextBox();	
		final TextBox textCelular 		= new TextBox();
		final ListBox selectPais		= new ListBox();
			selectPais.addItem("Pais...");
		final ListBox selectDepartamento= new ListBox();
			selectDepartamento.addItem("Departamento...");
		final ListBox selectCiudad		= new ListBox();
			selectCiudad.addItem("Ciudad...");
		final TextBox textDireccion		= new TextBox();
		
		/* Cargando los datos a los select de Pais, dpto y ciudad */
		if(usuario != null && (!usuario.getKeyCiudadResidencia().isEmpty()))
			new Utilidades().selectedIndex(selectPais, selectDepartamento, selectCiudad, usuario.getCiudadResidencia().getDepartamento().getPais().getNombre(), usuario.getCiudadResidencia().getDepartamento().getNombre(), usuario.getCiudadResidencia().getNombre());
		else
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
					selectCiudad.clear();
					selectDepartamento.addItem("Departamento...");
					selectCiudad.addItem("Ciudad...");
				}
			}
		});
		
		textEmail.addBlurHandler(new BlurHandler()
		{
			@Override
			public void onBlur(BlurEvent event)
		    {
				if(!textEmail.getValue().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
				{
					textEmail.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					new Utilidades().ventanaModal("Error", "La sintaxis del correo electronico no es valida", "error");
				}
				else
				{
					UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
					usuarioService.validarEmail(textEmail.getValue(), new AsyncCallback<Boolean>() 
					{
						public void onSuccess(Boolean esValido) 
						{
							if(!esValido)
								new Utilidades().ventanaModal("Error", "El correo electronico ya se encuentra registrado", "error");
							else
								textEmail.getElement().setAttribute("style", "");
						}
						public void onFailure(Throwable error) 
						{
							
						}
					});
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
		
		
		divContacto.add(lblEmail);
		divContacto.add(textEmail);
		divContacto.add(lblTelefono);
		divContacto.add(textTelefono);
		divContacto.add(lblCelular);
		divContacto.add(textCelular);
		divContacto.add(lblResidencia);
		divContacto.add(selectPais);
		divContacto.add(selectDepartamento);
		divContacto.add(selectCiudad);
		divContacto.add(lblDireccion);
		divContacto.add(textDireccion);
		
		fieldsetContacto.add(divContacto);
		
		
		/*
		 * PASSWORD
		 * Creación del Fieldset para el Password de la Cuenta
		 * Creación de los Widgets para el ingreso del password
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
		 * Creación del Fieldset para las opciones
		 * Creación de los Widgets para captura de opciones
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
		
		fieldsetOpciones.add(divOpciones);
		
		/*
		 * Creación de los botones para registrar el usuario, limpiar el formulario y
		 * cancelar la acción actual y volver al home
		 */
	
		HTMLPanel divBotones = new HTMLPanel(" ");
		divBotones.setStyleName("div_botones");
		
		Button btnRegistrar;
		
		if(usuario == null)
			btnRegistrar = new Button("Registrar");
		else
			btnRegistrar = new Button("Actualizar");
		
		
		Button btnCancelar = new Button("Cancelar");
		Button btnLimpiar = new Button("Limpiar");
		
		divBotones.add(btnRegistrar);
		divBotones.add(btnLimpiar);
		divBotones.add(btnCancelar);
		
		/*
		 * PANEL DE NOTIFICACIÓN DE ERRORES
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
		
		
		/* Cargando en el formulario los datos del usuario en caso de que se hay cargado la 
		 * vista para editar mas no para ingresar  
		 */
		if(usuario != null)
		{
			textNombres.setText(usuario.getNombres());
			textApellidos.setText(usuario.getApellidos());
			for(int i = 0; i < selectTipoDoc.getItemCount(); i++)
			{
				if(usuario.getTipoDocumento().equals(selectTipoDoc.getValue(i)))
					selectTipoDoc.setSelectedIndex(i);
			}
			textDocumento.setText(usuario.getNumeroDocumento());
			
			if(usuario.getFechaNacimiento() != null)
			{
				for(int i=1; i<104; i++)
				{
					if((usuario.getFechaNacimiento().getYear()+1900) == Integer.parseInt((selectAnio.getValue(i))))
					{
						selectAnio.setSelectedIndex(i);
						break;
					}
				}
				selectMes.setSelectedIndex(usuario.getFechaNacimiento().getMonth()+1);
				selectDia.setSelectedIndex(usuario.getFechaNacimiento().getDate());
			}
			
			textEmail.setValue(usuario.getEmail());
			textTelefono.setValue(usuario.getTelefono());
			textCelular.setValue(usuario.getTelefonoCel());
			textDireccion.setValue(usuario.getDireccion());
			
			fieldsetPassword.setVisible(false);
			fieldsetOpciones.setVisible(false);
		}
		
		
		btnRegistrar.addClickHandler(new ClickHandler() 
		{
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
				
				if(textApellidos.getValue().isEmpty())
				{
					textApellidos.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores=true;
					textApellidos.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							textApellidos.getElement().setAttribute("style", "");
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
				
				if(hayErrores)
				{
					divError.setVisible(true);
					return;
				}
					
				Date fechaNacimiento = new Date();
				fechaNacimiento.setYear(Integer.parseInt(selectAnio.getValue(selectAnio.getSelectedIndex()))-1900); 
				fechaNacimiento.setMonth((selectMes.getSelectedIndex()-1));
				fechaNacimiento.setDate(selectDia.getSelectedIndex());
					
				if(usuario == null)
				{
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
					
					/* Encriptando la clave del usuario */
						
					String password = JCrypt.crypt(new Utilidades().salto(), textPassword1.getValue());
					
					subContent.setVisible(false);
					final HTMLPanel cargando = new HTMLPanel("");
					cargando.setStyleName("cargando");
					RootPanel.get("content").add(cargando);
					
					/*
					 * Realización asincrona para guardar los datos
					 */
					
					//UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
					final Usuario usuario = new Usuario(textNombres.getText(), textApellidos.getText(), selectTipoDoc.getValue(selectTipoDoc.getSelectedIndex()), textDocumento.getText(), fechaNacimiento, textEmail.getText(), textTelefono.getText(), textCelular.getText(), textDireccion.getText(), password);
					
					// Si seleccionó una ciudada, agreguela al desaparecido 
					if(selectCiudad.getSelectedIndex() > 0)
						usuario.setKeyCiudadResidencia(selectCiudad.getValue(selectCiudad.getSelectedIndex()));
					
					final UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
					usuarioService.validarEmail(textEmail.getValue(), new AsyncCallback<Boolean>() 
					{
						public void onSuccess(Boolean esValido) 
						{
							if(!esValido)
							{
								cargando.getElement().setAttribute("style", "display:none");
								textEmail.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
								Label lblError = new Label("El correo electr\u00F3nico ya se encuentra registrado");
								divError.add(lblError);
								divError.setVisible(true);
								subContent.setVisible(true);
								return;
							}
							else
							{
								usuarioService.addUsuario(usuario, new AsyncCallback<Void>() 
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
										cargando.getElement().setAttribute("style", "display:none");
										subContent.setVisible(true);
									}
								});
							}
						}
						public void onFailure(Throwable error) 
						{
							
						}
					});
				}
				
				else
				{
					subContent.setVisible(false);
					final HTMLPanel cargando = new HTMLPanel("");
					cargando.setStyleName("cargando");
					RootPanel.get("content").add(cargando);						
						
					usuario.setNombres(textNombres.getValue());
					usuario.setApellidos(textApellidos.getValue());
					usuario.setTipoDocumento(selectTipoDoc.getValue(selectTipoDoc.getSelectedIndex()));
					usuario.setNumeroDocumento(textDocumento.getValue());
					usuario.setFechaNacimiento(fechaNacimiento);
					usuario.setEmail(textEmail.getValue());
					usuario.setTelefono(textTelefono.getValue());
					usuario.setTelefonoCel(textCelular.getValue());
					usuario.setDireccion(textDireccion.getValue());
					
					//Si seleccionó una ciudada, agreguela al desaparecido
					if(selectCiudad.getSelectedIndex() > 0)
						usuario.setKeyCiudadResidencia(selectCiudad.getValue(selectCiudad.getSelectedIndex()));
					else
						usuario.setKeyCiudadResidencia("");
					
					UsuarioServiceAsync usuarioService = GWT.create(UsuarioService.class);
					usuarioService.editarUsuario(usuario, new AsyncCallback<Void>() 
					{
						public void onSuccess(Void ignore) 
						{
							cargando.getElement().setAttribute("style", "display:none");
							new Utilidades().ventanaModal("Actualizaci\u00F3n exitosa", "Sus datos se han actualizado correctamente", "Exito");
							History.newItem("mi-cuenta");
						}
						public void onFailure(Throwable error) 
						{
							subContent.setVisible(true);
							new Utilidades().ventanaModal("Error", error.toString(), "error");
						}
					});
				}
			}
		});
			
		btnVerCondiciones.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				final DialogBox dialogBox = new DialogBox();
				
				dialogBox.setAnimationEnabled(true);
				final Button closeButton = new Button("Cerrar");
				closeButton.getElement().setId("closeButton");
				
				final HTMLPanel divDialogbox = new HTMLPanel("");
				final HTMLPanel fade = new HTMLPanel("");
				final HTMLPanel overlay = new HTMLPanel("");
				fade.setStyleName("fade");
				overlay.setStyleName("divModal");
				
				final HTMLPanel titulo = new HTMLPanel("<h1>Terminos y Condiciones del Servicio</h1>");
				titulo.setStyleName("titulo");
				
				overlay.add(titulo);
				overlay.add(new VistaTerminosCondiciones());
				overlay.add(closeButton);
				divDialogbox.add(fade);
				divDialogbox.add(overlay);
				
				dialogBox.setWidget(divDialogbox);
				dialogBox.center();
				
				closeButton.addClickHandler(new ClickHandler() 
				{
					public void onClick(ClickEvent event) 
					{
						dialogBox.hide();
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
				textApellidos.setText("");
				textDocumento.setText("");
				selectTipoDoc.setItemSelected(0, true);
				selectDia.setItemSelected(0, true);
				selectMes.setItemSelected(0, true);
				selectAnio.setItemSelected(0, true);
				
				//Limpiando los datos de contacto
				textEmail.setText("");
				textTelefono.setText("");
				textCelular.setText("");
				selectDepartamento.setItemSelected(0, true);
				selectCiudad.setItemSelected(0, true);
				textDireccion.setText("");
				
				//Limpiando las contraseñas
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
				if(History.getToken().equals("editar-usuario"))
				{
					History.newItem("mi-cuenta");
				}
				else
				{
					History.newItem("mi-cuenta");
				}
			}
		});
		
		initWidget(subContent);
	}	 
}
