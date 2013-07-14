package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import java.util.Date;

public class VistaRegistrarUsuario extends Composite 
{
	public VistaRegistrarUsuario()
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
		
		HTMLPanel divPersonales 	= new HTMLPanel(" ");
		
		Label lblNombre1			= new Label("1er Nombre: *");
		Label lblNombre2			= new Label("2do Nombre:");
		Label lblApellido1			= new Label("1er Apellido: *");
		Label lblApellido2			= new Label("2do Apellido:");
		Label lblDocumento			= new Label("Documento:");
		Label lblGenero				= new Label("Genero: *");
		Label lblFechaNacimiento	= new Label("Fecha Nacimiento*");
		
		final TextBox textNombre1 	= new TextBox();
		final TextBox textNombre2 	= new TextBox();
		final TextBox textApellido1	= new TextBox();
		final TextBox textApellido2	= new TextBox();
		final TextBox textDocumento	= new TextBox();
		final ListBox selectGenero	= new ListBox();
			selectGenero.addItem("Seleccione...");
			selectGenero.addItem("Masculino");
			selectGenero.addItem("Femenino");
		final ListBox selectDia		= new ListBox();
			selectDia.addItem("Dia...");
		final ListBox selectMes		= new ListBox();
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
		final ListBox selectAnio	= new ListBox();
			selectAnio.addItem("Anio...");
			for(int i=1910; i<2014; i++)
			{
				selectAnio.addItem(String.valueOf(i));
			}
		
		divPersonales.add(lblNombre1);
		divPersonales.add(textNombre1);
		divPersonales.add(lblNombre2);
		divPersonales.add(textNombre2);
		divPersonales.add(lblApellido1);
		divPersonales.add(textApellido1);
		divPersonales.add(lblApellido2);
		divPersonales.add(textApellido2);
		divPersonales.add(lblDocumento);
		divPersonales.add(textDocumento);
		divPersonales.add(lblGenero);
		divPersonales.add(selectGenero);
		divPersonales.add(lblFechaNacimiento);
		divPersonales.add(selectDia);
		divPersonales.add(selectMes);
		divPersonales.add(selectAnio);
		
		fieldsetPersonales.add(divPersonales);
		
		/*
		 * DATOS DE CONTACTO DE USUARIO
		 * Creación del Fieldset contenedor
		 * Creación de los Widgets para la captura de datos
		 */
		
		CaptionPanel fieldsetContacto = new CaptionPanel("Datos de Contacto");
		fieldsetContacto.setStyleName("fieldset_contacto");
		
		HTMLPanel divContacto = new HTMLPanel(" ");
		
		Label lblEmail					= new Label("Correo Electr\u00F3nico: *");
		Label lblTelefono1 				= new Label("Tel\u00E9fono 1:");
		Label lblTelefono2				= new Label("Tel\u00E9fono 2:");
		Label lblTelefonoMovil			= new Label("Tel\u00E9fono Celular:");
		Label lblResidencia			= new Label("Lugar de Residencia:");
		//Label lblDireccion				= new Label("Direcci\u00F3n:");
		
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
		textDireccion.getElement().setAttribute("placeHolder", "Direcci\u00F3n");
		
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
		
		final Button btnRegistrar = new Button("Registrar");
		Button btnCancelar = new Button("Cancelar");
		Button btnLimpiar = new Button("Limpiar");
		btnRegistrar.setEnabled(false);
		
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
				if(textNombre1.getValue().isEmpty())
				{
					Label lblError = new Label("El formulario tiene campos vac\u00EDos que son obligatorios");
					divError.add(lblError);
					hayErrores=true;
				}
				if(!textEmail.getValue().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
				{
					Label lblError = new Label("El correo electr\u00F3nico no tiene una sintaxis valida");
					divError.add(lblError);
					hayErrores=true;
				}
				if(!textPassword1.getValue().equals(textPassword2.getValue()))
				{
					Label lblError = new Label("Las contrase\u00F1as no coinciden");
					divError.add(lblError);
					hayErrores=true;
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
				
				/*
				 * Realización asincrona para guardar los datos
				 */
				/*final HTML mensaje = new HTML("");
				subContent.clear();
				
				final UsuarioServiceAsync usuario = GWT.create(UsuarioService.class);
				usuario.addUsuario(new Usuario(textNombre1.getText(), textNombre2.getText(), textApellido1.getText(), textApellido2.getText(), textDocumento.getText(), fechaNacimiento, selectGenero.getValue(selectGenero.getSelectedIndex())), new AsyncCallback<Void>() 
				{
				      public void onFailure(Throwable error) 
				      {
				    	  mensaje.setText(error.toString());
				    	  divPersonales.add(mensaje);
				      }
				      public void onSuccess(Void ignore) 
				      {
				    	  mensaje.setText("Su registro se ha realizado exitosamente. Para activar su cuenta revise su direcci\u00F3n " +
				    			  "de correo electr\u00F3nico y siga las instrucciones.");
				    	  divPersonales.add(mensaje);
				    	  History.newItem("ok");
				      }
				});*/
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
				textNombre1.setText("");
				textNombre2.setText("");
				textApellido1.setText("");
				textApellido2.setText("");
				textDocumento.setText("");
				selectGenero.setItemSelected(0, true);
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
				if(!History.getToken().equals("registrar"))
				{
					History.newItem("home");
				}
			}
		});
		
		
		/*
		 * Agregando el evento clic al checkbox de los Terminos y Condiciones
		 * Si el checbox esta seleccionado habilita el boton enviar sino lo inhabilita
		 */
		checkBoxCondiciones.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				if(checkBoxCondiciones.getValue())
					btnRegistrar.setEnabled(true);
				else
					btnRegistrar.setEnabled(false);
			}
		});
		
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
