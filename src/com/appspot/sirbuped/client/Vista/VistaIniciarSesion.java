package com.appspot.sirbuped.client.Vista;


//import sirbuped.client.dto.LoginInfo;
//import sirbuped.client.facade.LoginService;
//import sirbuped.client.facade.LoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class VistaIniciarSesion extends Composite {
	
	public VistaIniciarSesion() 
	{
		final HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("content_content");
		
		final HTMLPanel divSesion = new HTMLPanel("");
		divSesion.setStyleName("div_sesion");
		
		final HTML divTitulo = new HTML("<h2>Inicie sesi\u00F3n con su cuenta<h2>");
		
		final HTMLPanel divGoogle = new HTMLPanel("");
		final Button btnIniciarGoogle = new Button("Iniciar sesi\u00F3n con Google");
		btnIniciarGoogle.setStyleName("boton_google");
		divGoogle.add(btnIniciarGoogle);
		
		final HTMLPanel divSeparator = new HTMLPanel("<span>---------------------</span> \u00F3 " +
										"<span>--------------------</span>");
		divSeparator.setStyleName("div_separator");
		final TextBox textUsuario = new TextBox();
		textUsuario.getElement().setAttribute("placeHolder", "Nombre de usuario");
		textUsuario.setFocus(true);
		final TextBox textPassword = new TextBox();
		textPassword.getElement().setAttribute("placeHolder", "Contrase\u00F1a");
		final HTMLPanel divBoton = new HTMLPanel("");
		divBoton.setStyleName("div_boton");
		final CheckBox checkBoxRecordarme = new CheckBox("Recordarme");
		final Button btnIniciar = new Button("Iniciar sesi\u00F3n");
		divBoton.add(checkBoxRecordarme);
		divBoton.add(btnIniciar);
		
		final HTMLPanel divFooter = new HTMLPanel("");
		divFooter.setStyleName("div_footer");
		final Button btnRecContrasena = new Button("Olvid\u00F3 su contrase\u00F1a?");
		divFooter.add(btnRecContrasena);
		
		divSesion.add(divTitulo);
		divSesion.add(divGoogle);
		divSesion.add(divSeparator);
		divSesion.add(textUsuario);
		divSesion.add(textPassword);
		divSesion.add(divBoton);
		divSesion.add(divFooter);
		
		//divBgSesion.add(divSesion);
		
		subContent.add(divSesion);
		
		/*btnIniciarGoogle.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{	
				//Check login status using login service.
			    LoginServiceAsync loginService = GWT.create(LoginService.class);
			    String x = GWT.getHostPageBaseURL()+"#" + History.getToken();
			    Window.alert(x);
			    loginService.login(x, new AsyncCallback<LoginInfo>() 
			    		{
			    			public void onFailure(Throwable error) 
			    			{
			    				TextBox x = new TextBox();
			    				x.setText(error.toString());
			    				RootPanel.get("footer").add(x);
			    			}
			    			public void onSuccess(LoginInfo result) 
			    			{
			    				LoginInfo loginInfo = result;
			    				/*VerticalPanel loginPanel = new VerticalPanel();
			    				Label loginLabel = new Label("Please sign in to your Google Account to access the StockWatcher application.");
			    				Anchor signInLink = new Anchor("Sign In");
			    				Anchor signOutLink = new Anchor("Sign Out");*/
			    				/*			    				
			    				if(loginInfo.isLoggedIn()) 
			    				{
			    					// Set up sign out hyperlink.
			    				    //signOutLink.setHref(loginInfo.getLogoutUrl());
			    				    Window.Location.replace(loginInfo.getLogoutUrl());
			    				    // Assemble Main panel.
			    				    //loginPanel.add(signOutLink);
			    				    //loginPanel.add(signOutLink);
			    				    //RootPanel.get("content").clear();
			    				    //RootPanel.get("footer").add(loginPanel);
			    				} else {
			    					// Assemble login panel.
			    					//RootPanel.get("content").clear();
			    					Window.Location.replace(loginInfo.getLoginUrl());
			    					//signInLink.setHref(loginInfo.getLoginUrl());
			    				    //loginPanel.add(loginLabel);
			    				    //loginPanel.add(signInLink);
			    				    //RootPanel.get("footer").add(loginPanel);
			    				}
			    			}
			    });
			}
		});*/
		
		initWidget(subContent);
	}
}
