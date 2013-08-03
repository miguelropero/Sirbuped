package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Error404 extends Composite 
{
	public Error404()
	{
		HTMLPanel subContent = new HTMLPanel("<h1>404</h1>");
		initWidget(subContent);
	}
}
