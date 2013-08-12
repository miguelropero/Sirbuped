package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaNosotros extends Composite 
{
	
	public VistaNosotros()
	{
		HTMLPanel subContent = new HTMLPanel("Esta es la seccion de Nosotros");
		subContent.setStyleName("subContent");
		
		initWidget(subContent);
	}
}
