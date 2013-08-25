package com.appspot.sirbuped.client.Vista;

import java.util.Date;
import com.appspot.sirbuped.client.RichTextToolbar;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Pista;
import com.appspot.sirbuped.client.Interfaz.PistaService;
import com.appspot.sirbuped.client.Interfaz.PistaServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;

public class VistaPista extends Composite
{
	
	private HTMLPanel content;
	public Pista pista=null;
	
	public VistaPista()
	{}

	@SuppressWarnings("deprecation")
	public VistaPista(final String id)
	{
		content = new HTMLPanel("");
		content.setStyleName("contacto");
		
		CaptionPanel fieldsetContacto = new CaptionPanel("Pista");
        HTMLPanel divContacto 		= new HTMLPanel(" ");
		
        Label lblfecha      		= new Label("Fecha del Suceso:");
        Label lblMsj				= new Label("Pista:");
       
		final ListBox selectDia		= new Utilidades().diaAnio(true);
		final ListBox selectMes		= new Utilidades().listaMeses();
		final ListBox selectAnio	= new Utilidades().diaAnio(false);
		final RichTextArea mensaje	= new RichTextArea();
		RichTextToolbar toolbar 	= new RichTextToolbar(mensaje);
		
		HTMLPanel divBotones		= new HTMLPanel(" ");
		
		Button btnEnviar			= new Button(" Enviar ");
		Button btnCancelar 			= new Button("Cancelar");
		Button btnLimpiar 			= new Button("Limpiar");
		
		divBotones.add(btnEnviar);
		divBotones.add(btnLimpiar);
		divBotones.add(btnCancelar);
		
		divContacto.add(lblfecha);
		divContacto.add(selectDia);
		divContacto.add(selectMes);
		divContacto.add(selectAnio);
		divContacto.add(lblMsj);
		divContacto.add(toolbar);
		divContacto.add(mensaje);
	
		fieldsetContacto.add(divContacto);
	
		final HTMLPanel divError = new HTMLPanel("");
		divError.setStyleName("div_error");
		divError.setVisible(false);
		
		content.add(fieldsetContacto);
		content.add(divError);
		content.add(divBotones);
		
		btnEnviar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				divError.clear();
				boolean hayErrores=false;
				
				if(mensaje.getText().isEmpty())
				{
					mensaje.getElement().setAttribute("style", "border: 1px solid rgb(255, 157, 157)");
					hayErrores=true;
					mensaje.addBlurHandler(new BlurHandler()
					{
						@Override
						public void onBlur(BlurEvent event)
					    {
							mensaje.getElement().setAttribute("style", "");
					        divError.setVisible(false);
					    }
					});
				}
				
				if(hayErrores)
				{
					Label lblError = new Label("El formulario contiene campos vacios que son obligatorios");
					divError.add(lblError);
					divError.setVisible(true);
					return;
				}
				
				pista = new Pista();
				pista.setMensaje(mensaje.getText());
				
				if(selectAnio.getSelectedIndex() > 0 && selectMes.getSelectedIndex() > 0 && selectDia.getSelectedIndex() > 0)
				{
					Date fechaSuceso = new Date();
					fechaSuceso.setYear(Integer.parseInt(selectAnio.getValue(selectAnio.getSelectedIndex()))-1900); 
					fechaSuceso.setMonth((selectMes.getSelectedIndex()-1));
					fechaSuceso.setDate(selectDia.getSelectedIndex());
					
					pista.setFechaSuceso(fechaSuceso);
				}
				
				
				final HTMLPanel cargando = new HTMLPanel("");
				cargando.setStyleName("cargando");
				RootPanel.get("content").add(cargando);
				content.setVisible(false);
				
				PistaServiceAsync pistaService = GWT.create(PistaService.class);
				pistaService.registrar(pista, id, new AsyncCallback<Void>() 
				{
				    public void onSuccess(Void ignore) 
				    {
				    	cargando.getElement().setAttribute("style", "display:none");
						new Utilidades().ventanaModal("Envio Exitoso", "La informaci\u00F3n se ha enviado correctamente. Gracias pos su colaboraci\u00F3.", "Exito");
						String token = History.getToken().replace("*", "-"); 
				    	History.newItem(token);
				    }
				    public void onFailure(Throwable error) 
					{
						new Utilidades().ventanaModal("Error", error.toString() , "error");
						content.setVisible(true);
					}
		        });
				
				
			}
		});
		
		initWidget(content);
	}
}
