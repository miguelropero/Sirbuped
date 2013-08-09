package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.Utilidades;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hyperlink;

public class VistaMapaDesaparecidos extends Composite 
{
	private byte indice;
	public VistaMapaDesaparecidos()
	{	
		indice = 0;
		final HTMLPanel divMapaDesaparecidos = new HTMLPanel("");
		divMapaDesaparecidos.setStyleName("div-mapa");
		
		final HTMLPanel cargando = new HTMLPanel("");
		cargando.setStyleName("cargando");
		divMapaDesaparecidos.add(cargando);
		
		final HTMLPanel infoMapa = new HTMLPanel("");
		infoMapa.setStyleName("info-mapa");
		
		HTMLPanel infoMapaTitulo = new HTMLPanel("<h2>Informaci\u00F3n</h2>");
		infoMapaTitulo.setStyleName("titulo");
		
		HTMLPanel infoMapa1 = new HTMLPanel("Tenga en cuenta la siguiente informaci\u00F3n que le ayudara en la navegaci\u00F3n a traves del mapa");
		
		HTMLPanel infoMapa2 = new HTMLPanel("");
		HTMLPanel infoMapaImage2 = new HTMLPanel("");
		infoMapa2.add(infoMapaImage2);
		HTMLPanel infoMapaContent2 = new HTMLPanel("Departamentos en los cuales existen casos reportados de personas desaparecidas");
		infoMapa2.add(infoMapaContent2);
		
		HTMLPanel infoMapa3 = new HTMLPanel("");
		HTMLPanel infoMapaImage3 = new HTMLPanel("");
		infoMapa3.add(infoMapaImage3);
		HTMLPanel infoMapaContent3 = new HTMLPanel("Al hacer clic sobre el icono anterior podra detallar la informacion del departamento");
		infoMapa3.add(infoMapaContent3);
		
		HTMLPanel infoMapa4 = new HTMLPanel("");
		HTMLPanel infoMapaImage4 = new HTMLPanel("");
		infoMapa4.add(infoMapaImage4);
		HTMLPanel infoMapaContent4 = new HTMLPanel("Cantidad de personas desaparecidas registradas en el departamento");
		infoMapa4.add(infoMapaContent4);
		
		HTMLPanel infoMapa5 = new HTMLPanel("");
		HTMLPanel infoMapaImage5 = new HTMLPanel("");
		infoMapa5.add(infoMapaImage5);
		HTMLPanel infoMapaContent5 = new HTMLPanel("Tambien puede ver el listado de desaparecidos para el departamento seleccionado");
		infoMapa5.add(infoMapaContent5);
		
		infoMapaTitulo.setStyleName("titulo");
		
		infoMapa.add(infoMapaTitulo);
		infoMapa.add(infoMapa1);
		infoMapa.add(infoMapa2);
		infoMapa.add(infoMapa3);
		infoMapa.add(infoMapa4);
		infoMapa.add(infoMapa5);
		
		Maps.loadMapsApi("", "2", false, new Runnable()
		{
			public void run() 
			{
				final MapWidget map = new MapWidget();
				map.setStyleName("mapa-desaparecidos");
				  
				final LatLng coordenadas = LatLng.newInstance(3.8424444261106507, -73.35546875);
				  
				//mostramos el mapa centrado con las coordenas (-18.0376, -70.2506)
				map.setCenter(coordenadas);
				//establecemos en nivel de zoom
				map.setZoomLevel(5);
				  
				//Zoom
				map.setDoubleClickZoom(false);
				map.setScrollWheelZoomEnabled(false);
				
				DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
				desaparecidoService.mapaDesaparecidos(new AsyncCallback<ArrayList<String>>() 
				{
					public void onSuccess(final ArrayList<String> results) 
					{
						loadPoints(map, results, 0);
						
						Button botonMas = new Button("mas");

						botonMas.addClickHandler(new ClickHandler() 
						{
							public void onClick(ClickEvent event) 
							{
								loadPoints(map, results, ++indice);
							}
						});
					    	
						divMapaDesaparecidos.add(infoMapa);
						divMapaDesaparecidos.add(map);
						divMapaDesaparecidos.add(botonMas);
						cargando.getElement().setAttribute("style", "display:none");
					}
					
					public void onFailure(Throwable error) 
					{
					}
				});
			}
		});
		
		initWidget(divMapaDesaparecidos);
	}
	
	public void loadPoints(final MapWidget map, ArrayList<String> dpto, int indice)
	{
		byte ini = 0;
		byte end = 0;
		if(indice == 0)
		{
			end = 12;
		}
		else if(indice == 1)
		{
			ini = 12;
			end = 23;
		}
		else
		{
			ini = 23;
			end = 32;
		}
		
		for(byte i = ini; i < end; i++)
		{
			final String [] punto = dpto.get(i).split("-");
					
			Geocoder geocoder=new Geocoder();
			geocoder.getLatLng(punto[0] + ", Colombia", new LatLngCallback()
			{
				public void onSuccess(LatLng point)
				{
					Icon icon = Icon.newInstance("../image/maps/"+ punto[1] +".png");
					MarkerOptions ops = MarkerOptions.newInstance(icon);
					  
					final Marker marker=new Marker(point, ops);
					map.addOverlay(marker);
					
					marker.addMarkerClickHandler(new MarkerClickHandler()
					{
						@Override
						public void onClick(MarkerClickEvent event) 
						{
							if(!punto[1].equals("0"))
							{
								DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
								desaparecidoService.getDesaparecidosDpto(punto[0], new AsyncCallback<ArrayList<Desaparecido>>() 
								{
									public void onSuccess(final ArrayList<Desaparecido> results) 
									{
										//Widget en InfoWindow Normal
										HTMLPanel content = new HTMLPanel("<h4>"+ punto[0] +" - Ultimas Desapariciones</h4>");
										content.setStyleName("utimosDesaparecidosMapa");
										HTMLPanel desaparecidos = new VistaVerDesaparecidos().mostarTodos(results);
										desaparecidos.setStyleName("verDesaparecido enMapa");
										Hyperlink verTodos = new Hyperlink("Ver todos", false, "#");
										
										content.add(desaparecidos);
										content.add(verTodos);
										
										InfoWindowContent info = new InfoWindowContent(content);
										info.setMaxWidth(map.getSize().getWidth()/3);
										map.getInfoWindow().open(marker.getLatLng(),info);
									}
									
									public void onFailure(Throwable error) 
									{
										new Utilidades().ventanaModal("Error", "En este momento no fue posible consultar la bade de datos. Intente nuevmente.", "error");
									}
								});
							}
							else
							{
								HTMLPanel sinResultados = new HTMLPanel("<h3>\u00A1Oppps!</h3><br><p>Hasta la fecha, el departamento de " + punto[0] + " no tiene personas registradas como desaparecidas.</p>");
					    		sinResultados.setStyleName("sinResultados");
					    		InfoWindowContent info=new InfoWindowContent(sinResultados);
					    		info.setMaxWidth(map.getSize().getWidth()/2);
					    		map.getInfoWindow().open(marker.getLatLng(),info);
							}
						}
					});
				}
				
				@Override
				public void onFailure() 
				{
					//new Utilidades().ventanaModal("Error", "No se puede acceder a las coordenadas solicitadas. intente nuevamente.", "error");
				}
  			});
  		}
	}
}
