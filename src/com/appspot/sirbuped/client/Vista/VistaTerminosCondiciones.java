package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaTerminosCondiciones extends Composite
{
	
	public VistaTerminosCondiciones()
	{
		HTMLPanel subContent = new HTMLPanel("Esta es la pagina de Terminos y condiciones");
		subContent.setStyleName("subContent");
		
		initWidget(subContent);
	}

}
