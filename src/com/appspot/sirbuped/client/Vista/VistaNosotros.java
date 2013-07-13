package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaNosotros extends Composite 
{
	
	public VistaNosotros()
	{
		HTMLPanel subContent = new HTMLPanel("<br><br><h1>Esta es la seccion de Nosotros</h1>");
		subContent.setStyleName("verDesaparecido");
		
		initWidget(subContent);
	}
}
