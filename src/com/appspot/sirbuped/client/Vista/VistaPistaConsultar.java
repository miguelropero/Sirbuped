package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Pista;
import com.appspot.sirbuped.client.Interfaz.PistaService;
import com.appspot.sirbuped.client.Interfaz.PistaServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TabPanel;

public class VistaPistaConsultar extends Composite
{
	public VistaPistaConsultar()
	{
	
		TabPanel tabPanel = new TabPanel();	
	
		final HTMLPanel subContent = new HTMLPanel(" ");
		subContent.setStyleName("div-buzon");
		final HTMLPanel divRecibidas=new HTMLPanel(" ");
		final HTMLPanel divEnviadas=new HTMLPanel(" ");
		 
		 /***Pistas Enviadas**/
		 PistaServiceAsync pistaService = GWT.create(PistaService.class);
		 pistaService.getPistasEnviadas( new AsyncCallback<ArrayList<Pista>>() 
		 {
			 public void onSuccess(ArrayList<Pista> pistas) 
			 {
				 for(byte i=0; i < pistas.size(); i++)
				 {
					 final Pista p= pistas.get(i);
					 HTMLPanel mensaje = new HTMLPanel("");	
					 mensaje.setStyleName("mensaje");
					 
					 HTMLPanel nombre=new HTMLPanel(p.getDesaparecido().getNombre1());
					 nombre.setStyleName("nombre");
					 
					 DateTimeFormat format1 = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
					 String fSuceso= format1.format(p.getFechaSuceso());
					 
					 HTMLPanel asunto=new HTMLPanel(fSuceso);
					 asunto.setStyleName("asunto");
					 
					 DateTimeFormat format = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
					 String date = format.format(p.getFechaRegistro());
					 
					 HTMLPanel fecha=new HTMLPanel(date);
					 fecha.setStyleName("fecha");
					 final HTMLPanel contenido=new HTMLPanel(p.getMensaje());
					 contenido.setStyleName("cont-mensaje");
					 contenido.setVisible(false);
					 
					 HTML zonaClick = new HTML();
					 zonaClick.setStyleName("zonaClick");
					 
					 mensaje.add(zonaClick);
					 mensaje.add(nombre);
					 mensaje.add(asunto);
					 mensaje.add(fecha);
					 mensaje.add(contenido);
					 
					 divEnviadas.add(mensaje);
					 
					 zonaClick.addClickHandler(new ClickHandler() 
					 {
						 public void onClick(final ClickEvent event) 
						 {
							 if(contenido.isVisible())
								 contenido.setVisible(false);
							 
							 else
								 contenido.setVisible(true);
						 }
					 });
				 }
			 }
			 public void onFailure(Throwable error) 
			 {
				 Window.alert(error.toString());
			 }
		 });
		 
		 
		 /******Pistas Recibidas*******/
		 PistaServiceAsync pistaService2 = GWT.create(PistaService.class);
		 pistaService2.getPistasRecibidas( new AsyncCallback<ArrayList<Desaparecido>>()
		 {
			 public void onSuccess(ArrayList<Desaparecido> desaparecidos) 
			 {
				 for(byte i=0; i< desaparecidos.size(); i++ )
				 {
					 
					 Desaparecido desaparecido=desaparecidos.get(i);
					 
					 final HTMLPanel infoTitulo = new HTMLPanel(desaparecido.getNombre1()+" "+desaparecido.getApellido1());
					 infoTitulo.setStyleName("usuario");
					 
					 divRecibidas.add(infoTitulo);
					 
					 for(byte j=0; j < desaparecido.getPistas().size(); j++)
					 {
						 final Pista p= desaparecido.getPistas().get(j);
						 HTMLPanel mensaje = new HTMLPanel("");	
						 mensaje.setStyleName("mensaje");
						 
						 final HTMLPanel remitente=new HTMLPanel("");
						 remitente.setStyleName("nombre");
						 
						 PistaServiceAsync pistaService3 = GWT.create(PistaService.class);
						 pistaService3.getusuarioPista(p.getKeyUsuario(), new AsyncCallback<String>()
						 {
							 public void onSuccess(String nombre)
							 {
								 HTMLPanel usuario=new HTMLPanel(nombre);
								 remitente.add(usuario);
							 }
							 public void onFailure(Throwable error) 
							 {
								 Window.alert(error.toString());
							 }
						 });
						 
						 DateTimeFormat format1 = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
						 String fSuceso= format1.format(p.getFechaSuceso());
						 
						 HTMLPanel fechaS=new HTMLPanel(fSuceso);
						 fechaS.setStyleName("asunto");
						 
						 DateTimeFormat format = DateTimeFormat.getFormat("dd 'de' MMMM 'de' yyyy");
						 String date = format.format(p.getFechaRegistro());
						 
						 HTMLPanel fecha=new HTMLPanel(date);
						 fecha.setStyleName("fecha");
						 final HTMLPanel contenido=new HTMLPanel(p.getMensaje());
						 contenido.setStyleName("cont-mensaje");
						 contenido.setVisible(false);
						 
						 HTML zonaClick = new HTML();
						 zonaClick.setStyleName("zonaClick");
						 
						 mensaje.add(zonaClick);
						 mensaje.add(remitente);
						 mensaje.add(fechaS);
						 mensaje.add(fecha);
						 mensaje.add(contenido);
						 
						 divRecibidas.add(mensaje);
						 
						 zonaClick.addClickHandler(new ClickHandler() 
						 {
							 public void onClick(final ClickEvent event) 
							 {
								 if(contenido.isVisible())
									 contenido.setVisible(false);
								 
								 else
									 contenido.setVisible(true);
							 }
						 });
						 
					 }
				 }
				 
			 }
			 public void onFailure(Throwable error) 
			 {
				 Window.alert(error.toString());
			 }
		 });
		 	
		 tabPanel.add( divRecibidas, "Pistas Recibidas");
		 tabPanel.add(divEnviadas, "Pistas Enviadas");
		 tabPanel.selectTab(0);
		 subContent.add(tabPanel);
		 
		 initWidget(subContent);
	}	
}
