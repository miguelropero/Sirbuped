package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaDesaparecidoUsuario extends Composite 
{
	public VistaDesaparecidoUsuario(ArrayList<Desaparecido> desaparecidos)
	{
		HTMLPanel subContent = new VistaDesaparecidoDetallar().mostarTodos(desaparecidos);
		subContent.setStyleName("verDesaparecido");
		initWidget(subContent);
	}
}
