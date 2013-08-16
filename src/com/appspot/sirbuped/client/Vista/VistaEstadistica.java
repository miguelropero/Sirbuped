package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.appspot.sirbuped.client.Interfaz.DesaparecidoService;
import com.appspot.sirbuped.client.Interfaz.DesaparecidoServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.ColumnChart;
import com.googlecode.gwt.charts.client.corechart.ColumnChartOptions;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;



public class  VistaEstadistica extends DockLayoutPanel 
{
	
	private PieChart chart;
	private ColumnChart chart1;
	private ColumnChart chart2;
	
	public VistaEstadistica()
	{
		super(Unit.PX);
		final HTMLPanel divEstadistica = new HTMLPanel("");
		divEstadistica.setStyleName("div-estadistica");
		divEstadistica.getElement().setId("estadistica");
		RootPanel.get("content").add(divEstadistica);
		initialize();
	}

	private void initialize()
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() 
		{

			@Override
			public void run()
			{
				
				
				// Create and attach the chart
				chart = new PieChart();
				add(chart);
				draw1();
				
				chart1= new ColumnChart();
				add(chart1);
				draw2();
				
				chart2= new ColumnChart();
				add(chart2);
				draw3();
				
			}
		});
	}

	
   /******Grafica que muestra estadisticas sobre personas desaparecidas segun su genero*****/ 
   private void draw1() 
   {
	   
	   
	    final HTMLPanel infoEstadistica1= new HTMLPanel("");
	    infoEstadistica1.setStyleName("info-estadistica");
	    final HTMLPanel infoTitulo = new HTMLPanel("<h2>Desaparecidos por Genero</h2>");
	    infoTitulo.setStyleName("title");
	    final HTMLPanel grafica= new HTMLPanel("");
	    grafica.setStyleName("grafica");
	   
	    
		
		// Prepare the data
		final DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Task");
		dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
		dataTable.addRows(8);
	
		
		 DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
		 desaparecidoService.consultarGenero( new AsyncCallback<String>()
		 
				{
		    public void onSuccess(String size) 
		    {
		    	String[] desaparecidos=size.split("-");
		    	dataTable.setValue(0, 0, "Femenino");
			    dataTable.setValue(0, 1, Integer.parseInt(desaparecidos[0]));
			    dataTable.setValue(1, 0, "Masculino");
				dataTable.setValue(1, 1, Integer.parseInt(desaparecidos[1]));
				

				// Set options
				PieChartOptions options = PieChartOptions.create();
				options.setBackgroundColor("#fff");

				// options.setColors(colors);
				//options.setWidth(450);
			    //options.setHeight(300);
				options.setFontName("Tahoma");
				options.setIs3D(true);
				options.setPieResidueSliceColor("#000000");
				options.setPieResidueSliceLabel("Others");
				options.setSliceVisibilityThreshold(0.1);
				options.setTitle("");
			

				// Draw the chart
				chart.draw(dataTable, options);
				grafica.add(chart);
				infoEstadistica1.add(infoTitulo);
				infoEstadistica1.add(grafica);
				RootPanel.get("estadistica").add(infoEstadistica1);
			    	
		    }
		    	
		    
		    public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			
			}
        });
		
	 
		
	}
   
   
   
	private void draw2() 
	{
		
		 final HTMLPanel infoEstadistica2= new HTMLPanel("");
		 infoEstadistica2.setStyleName("info-estadistica");
		 final HTMLPanel infoTitulo2= new HTMLPanel("<h2>Desaparecidos en los \u00DAltimos A\u0148os</h2>");
	     infoTitulo2.setStyleName("title");
	     final HTMLPanel grafica2= new HTMLPanel("");
    	 grafica2.setStyleName("grafica");
		
		DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
		desaparecidoService.consultarDesaparecidoAnio( new AsyncCallback<ArrayList<String>>()
		 {
		    public void onSuccess(ArrayList<String> size) 
		    {
		
		    	
		    	String[] years = new String[4];
		    	int[][] values=new int[1][4];
		    	
		    	
		    	for(byte i=0; i<size.size();i++)
		    	{
		    		String[] cadena=size.get(i).split("-");
		    		years[i]=cadena[0];
		    		values[0][i]=Integer.parseInt(cadena[1]);
		    	}   	
		    	
		String[] countries = new String[] { ""};
		
		// Prepare the data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Year");
		for (int i = 0; i < countries.length; i++) {
			dataTable.addColumn(ColumnType.NUMBER, countries[i]);
		}
		dataTable.addRows(years.length);
		for (int i = 0; i < years.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(years[i]));
		}
		for (int col = 0; col < values.length; col++) {
			for (int row = 0; row < values[col].length; row++) {
				dataTable.setValue(row, col + 1, values[col][row]);
			}
		}

		// Set options
		ColumnChartOptions options = ColumnChartOptions.create();
		options.setFontName("Tahoma");
		options.setTitle("");
		options.setHAxis(HAxis.create("A\u0148o"));
		options.setVAxis(VAxis.create("Personas"));
		options.setColors("#09ACA7");

		// Draw the chart
		chart1.draw(dataTable, options);  
		grafica2.add(chart1);
		infoEstadistica2.add(infoTitulo2);
		infoEstadistica2.add(grafica2);
		RootPanel.get("estadistica").add(infoEstadistica2);
		    }
		
		 public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
				
			}
     });
	}
	
	
	
	/**** Grafica que muestra estadisticas sobre personas desaparecidas por edad ******/
	private void draw3()
	{
		
		final HTMLPanel infoEstadistica3= new HTMLPanel("");
	    infoEstadistica3.setStyleName("info-estadistica");
	    final HTMLPanel infoTitulo3= new HTMLPanel("<h2>Personas Desaparecidas Por Edades </h2>");
	    infoTitulo3.setStyleName("title");
	    final HTMLPanel grafica3= new HTMLPanel("");
   	    grafica3.setStyleName("grafica");
   	    
		DesaparecidoServiceAsync desaparecidoService = GWT.create(DesaparecidoService.class);
		desaparecidoService.consultarDesaparecidoEdad( new AsyncCallback<ArrayList<String>>()
		 
				{
		    public void onSuccess(ArrayList<String> size) 
		    {
		    	
		    	String[] edades = new String[] {"0-10 a\u0148os","10-20","20-30", "30-40","40-50","Mayor a 50"};
		    	String[] year = new String[3];
		    	int[][] values=new int[6][3];
		    	
		    	
		    	for(byte i=0; i<size.size();i++)
		    	{
		    		String[] cadena=size.get(i).split("-");
		    		year[i]=cadena[0];
		    		values[0][i]=Integer.parseInt(cadena[1]);
		    		values[1][i]=Integer.parseInt(cadena[2]);
		    		values[2][i]=Integer.parseInt(cadena[3]);
		    		values[3][i]=Integer.parseInt(cadena[4]);
		    		values[4][i]=Integer.parseInt(cadena[5]);
		    		values[5][i]=Integer.parseInt(cadena[6]);
		    		
		    	}
		    	
		    
				// Prepare the data
				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "A\u0148o");
				
				for (int i = 0; i < edades.length; i++) {
					dataTable.addColumn(ColumnType.NUMBER, edades[i]);
				}
				dataTable.addRows(year.length);
				for (int i = 0; i < year.length; i++) {
					dataTable.setValue(i, 0, String.valueOf(year[i]));
				}
				for (int col = 0; col < values.length; col++) {
					for (int row = 0; row < values[col].length; row++) {
						dataTable.setValue(row, col + 1, values[col][row]);
					}
				}

				// Set options
				ColumnChartOptions options = ColumnChartOptions.create();
				options.setFontName("Tahoma");
				options.setTitle("");
				options.setHAxis(HAxis.create("A\u0148o"));
				options.setVAxis(VAxis.create("Desaparecidos"));
				
				

				// Draw the chart
				chart2.draw(dataTable, options);
				grafica3.add(chart2);
				infoEstadistica3.add(infoTitulo3);
				infoEstadistica3.add(grafica3);
				RootPanel.get("estadistica").add(infoEstadistica3);
		    	
		    	
		    }
		    	
		    
		    public void onFailure(Throwable error) 
			{
				Window.alert(error.toString());
			}
        });
			
	}
	
	
	
}

