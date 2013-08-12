package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaRequisitos extends Composite
{
	
	public VistaRequisitos()
	{
		HTMLPanel subContent = new HTMLPanel("Esta es la pagina de requisitos");
		subContent.setStyleName("subContent");
		
		initWidget(subContent);
	}
}
