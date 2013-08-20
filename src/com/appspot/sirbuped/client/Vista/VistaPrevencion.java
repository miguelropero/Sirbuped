package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaPrevencion extends Composite
{
	
	public VistaPrevencion()
	{
		HTMLPanel subContent = new HTMLPanel("Esta es la pagina de Prevecion");
		subContent.setStyleName("subContent");
		
		initWidget(subContent);
	}
}
