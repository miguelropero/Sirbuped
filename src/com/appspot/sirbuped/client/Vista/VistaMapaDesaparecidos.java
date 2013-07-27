package com.appspot.sirbuped.client.Vista;

import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VistaMapaDesaparecidos extends Composite 
{
	
	public VistaMapaDesaparecidos()
	{	
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
				  
				  //Controles
				  map.addControl(new LargeMapControl());
				  map.addControl(new MapTypeControl());
				  
				  //Zoom
				  map.setDoubleClickZoom(true);
				  map.setScrollWheelZoomEnabled(true);	
				  
				  //Marcadores y Ventanas
				  final Marker mrk=new Marker(coordenadas);
				  map.addOverlay(mrk);  
				  
				  mrk.addMarkerClickHandler(new MarkerClickHandler()
				  {
					  @Override
					  public void onClick(MarkerClickEvent event)
					  {
						  //Widget en InfoWindow Normal
						  Image img=new Image();
				 
						  img.setUrl("http://lh6.ggpht.com/_oxEB1W000Zc/S5sCOsecjdI/AAAAAAAAAGg/_CDb-vUE7gs/s800/13%20DE%20SET.%20%20PLAZA%20ATMAT.JPG");
						  img.setWidth("235px");				 
						  img.setHeight("267px");
				 
						  //Widget en InfoWindow Maximizado
						  
						  HTML video= new HTML("<object width='180' height='160'><param name='movie' value='http://www.youtube.com/v/8qp_VSmV17I&hl=es_ES&fs=1&rel=0'></param><param name='allowFullScreen' value='true'></param><param name='allowscriptaccess' value='always'></param><embed src='http://www.youtube.com/v/8qp_VSmV17I&hl=es_ES&fs=1&rel=0' type='application/x-shockwave-flash' allowscriptaccess='always' allowfullscreen='false' width='180' height='160'></embed></object>");
						  HTML album= new HTML("<embed type='application/x-shockwave-flash' src='http://picasaweb.google.com/s/c/bin/slideshow.swf' width='180' height='160' flashvars='host=picasaweb.google.com&hl=es&feat=flashalbum&RGB=0x000000&feed=http%3A%2F%2Fpicasaweb.google.com%2Fdata%2Ffeed%2Fapi%2Fuser%2FVictorCabreraZolla%2Falbumid%2F5447950544182068785%3Falt%3Drss%26kind%3Dphoto%26hl%3Des' pluginspage='http://www.macromedia.com/go/getflashplayer'></embed>");    
				 
						  HorizontalPanel hp=new HorizontalPanel();
						  hp.setSpacing(20);
				 
						  VerticalPanel vp=new VerticalPanel();
						  vp.add(new HTML("video"));
						  vp.add(video);
						  vp.add(new HTML("diapositiva"));
						  vp.add(album);
						  
						  hp.add(new HTML("<p style=\"text-align: justify;\">Este parque se construyo como un esfuerzo mancomunado del Sr. Alcalde  Jorge Jumanor  con los vecinos, haciendo realidad un sueño muchas veces  postergado por las anteriores autoridadede de Turno</p><p style=\"text-align: justify;\">La Obra se empezo  a contruir a inicios del año 2009 culminandose satisfactoriamente a  mediados del  2009 en el aniversario del Distrito Gregorio  Albarracion  Lanchipa</p>"));
						  hp.add(vp);
				 
						  //creamos la Ventana de Informacion
						  InfoWindowContent info=new InfoWindowContent(img);    						  
						  info.setMaxTitle("Parque Perez Gamboa");
						  info.setMaxContent(hp);
				 
						  map.getInfoWindow().open(mrk.getLatLng(),info);
				   }});
				  divMapaDesaparecidos.add(infoMapa);
				  divMapaDesaparecidos.add(map);
				  cargando.getElement().setAttribute("style", "display:none");
				  //RootPanel.get("content").add(divMapaDesaparecidos);
			  }
		});
		
		initWidget(divMapaDesaparecidos);
	}

}
