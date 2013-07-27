package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class Mapa extends Composite 
{

	public Mapa()
	{
	    
	    Maps.loadMapsApi("", "2", false, new Runnable() 
	    {
	    	public void run() 
	    	{
	    		final MapWidget  map=new MapWidget(); 
	    		map.setWidth("1000px");
	    		map.setHeight("1000px");
	    		final LatLng coordenadas = LatLng.newInstance(3.8424444261106507, -73.35546875);
	    		//mostramos el mapa centrado con las coordenas (-18.0376, -70.2506)
	    		map.setCenter(coordenadas);
	    		//establecemos en nivel de zoom
	    		map.setZoomLevel(6); 
	    		//Controles
	    		map.addControl(new LargeMapControl());
	    		map.addControl(new MapTypeControl());
	    		//Zoom
	    		map.setDoubleClickZoom(true);
	    		map.setScrollWheelZoomEnabled(true);
	    		
	    		//Marcadores y Ventanas
				 
	    		//Marker marker= new Marker(LatLng.newInstance(3.8424444261106507, -73.35546875));
	    		//map.addOverlay(marker);
				 
	    		final ArrayList<String> dpto = new ArrayList<String>();
	    		dpto.add("Amazonas");
	    		dpto.add("Antioquia");
	    		dpto.add("Arauca");
	    		dpto.add("Atlántico");
	    		dpto.add("Bolivar");
	    		dpto.add("Boyaca");
	    		dpto.add("Caldas");
	    		dpto.add("Caqueta");
	    		dpto.add("Casanare");
	    		dpto.add("Cauca");
	    		dpto.add("Cesar");
	    		dpto.add("Choc\u00F3");
	    		dpto.add("Cordoba");
	    		dpto.add("Cundinamarca");
	    		dpto.add("Guainía");
	    		dpto.add("Guaviare");
	    		dpto.add("Guajira");
	    		dpto.add("Huila");
	    		dpto.add("Magdalena");
	    		dpto.add("Meta");
	    		dpto.add("Nariño");
	    		dpto.add("Norte de Santander");
	    		dpto.add("Putumayo");
	    		dpto.add("Quindío");
	    		dpto.add("Risaralda");
	    		dpto.add("San Andres y Providencia");
	    		dpto.add("Santander");
	    		dpto.add("Sucre");
	    		dpto.add("Tolima");
	    		dpto.add("Valle del Cauca");
	    		dpto.add("Vaupés");
	    		dpto.add("Vichada");
	    		
	    		for(byte i=0; i < dpto.size(); i++)
	    		{
	    			final String departamento = dpto.get(i);	    			
	    			//info.open(marker, new InfoWindowContent("<b>Desaparecidoooooo 1</b>"));
	    			Geocoder geocoder=new Geocoder();
	    			geocoder.getLatLng(dpto.get(i) + ", Colombia", new LatLngCallback()
	    			{
			    		public void onSuccess(LatLng point)
			    		{
			    			final Marker marker=new Marker(point);
			    			map.addOverlay(marker);
			    			
			    			marker.addMarkerClickHandler(new MarkerClickHandler()
			    			{
			    				@Override
			    				public void onClick(MarkerClickEvent event) 
			    				{
			    					//creamos la Ventana de Informacion
			    					InfoWindowContent info = new InfoWindowContent(departamento);    
			    					map.getInfoWindow().open(marker.getLatLng(),info);
			    					
			    				}
			    			}); 
						}

						@Override
						public void onFailure() 
						{

						}
		
						 
	    			});
	    		}
	    	RootPanel.get().add(map);
	    }});
		
	}
}
