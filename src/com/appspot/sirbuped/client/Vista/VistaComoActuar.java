package com.appspot.sirbuped.client.Vista;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;

public class VistaComoActuar extends Composite
{
	public VistaComoActuar()
	{
		HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("subContent comoActuar");
		
		HTML bienvenida = new HTML("<div>Que hacer ante una desaparici\u00F3n</div>");
		bienvenida.setStyleName("bienvenida");
		
		HTMLPanel paso1 = new HTMLPanel("<h3>Paso 1</h3><p>Reflexionar y valorar rapidamente las costumbres y habitos de la persona desaparecida.</p>");
		
		HTMLPanel paso2 = new HTMLPanel("<h3>Paso 2</h3><p>Hablar con las personas con las que se haya relacionado el dia de la desaparici\u00F3n. hacerse una primera hip\u00F3tesis de lo que puede haber sucedido.</p>");
		
		HTMLPanel paso3 = new HTMLPanel("<h3>Paso 3</h3><p>Si no hay ninguna explicaci\u00F3n clara, acudir a una entidad publica que tenga competencias para formalizar la denuncia e informar de todos los datos necesarios. Recuerde que un dato que para usted no tenga ningun valor, puede ser clave. N	o oculte nada a los investigadres.</p>");
		
		HTMLPanel paso4 = new HTMLPanel("<h3>Paso 4</h3><p>Entrevistese con el responsable principal y preguntar que mas se puede hacer para ayudar a su localizaci\u00F3n. El le orientara sus dudas y le indicara cual es el procedimiento a seguir en el caso.</p>");
		
		HTMLPanel paso5 = new HTMLPanel("<h3>Paso 5</h3><p>Hacer publica la denuncia ante los medio de comunicaci\u00F3n: prensa, radio, televisi\u00F3n y redes de internet. Recuerde que es necesario la correspondiente denuncia para poder difundir la desaparici\u00F3n en estos medios.</p>");
		
		HTMLPanel paso6 = new HTMLPanel("<h3>Paso 6</h3><p>Editar y repsatir carteles con la fotografia y datos caracteristicos indicando como contacto el numero de la policia. Se pueden repartir en lugares de concurrencia de publico, autobuses, comisarias, juzgados, hopitales y otros lugares muy frecuentados. Recuerde que los carteles pueden ser generados a traves de nuestra plataforma.</p>");
		
		HTMLPanel paso7 = new HTMLPanel("<h3>Paso 7</h3><p>Ponerse en contacto con otros organismoa e intituciones que puedan ayudar: Policia municipal, bomberos, cruz roja, defensa civil o servicios sociales.</p>");
		
		HTMLPanel paso8 = new HTMLPanel("<h3>Paso 8</h3><p>Alojar los datos personales y la fotografia en las paginas de internet de las asociaciones y hacer la difusi\u00F3n por toda la red. No pague por tener un desaparecido en un pafina web, la solidaridad es lo primero y el gasto de esta acci\u00F3n es nulo.</p>");
		
		HTMLPanel paso9 = new HTMLPanel("<h3>Paso 9</h3><p>Contactar a todas las personas con la que mantenia una relaci\u00F3n importante para encontrar algun indicio.</p>");
		
		HTMLPanel paso10 = new HTMLPanel("<h3>Paso 10</h3><p>Digundir a todas las entidades y organismos publicos y privados que quieran colaborar en la busqueda. Seguridad social, consulados y embajadas, empresas de transporte, hoteles...</p>");
		
		HTMLPanel paso11 = new HTMLPanel("<h3>Paso 11</h3><p>Realizar todas las gestiones y acciones que la familia se vea capaz de ir realizando para mantener la busqueda y conseguir la localizaci\u00F3n de la persona desaparecida.</p>");
		
		HTMLPanel paso12 = new HTMLPanel("<h3>Paso 12</h3><p>Personarse ante el juzgado correspondiente donde se han abierto las diligencias previas con el fin de realizar el seguimmiento judiciall o pedir las gestiones que sean necesarias referentes a la ausencia de la persona buscada.</p>");
		
		HTMLPanel paso13 = new HTMLPanel("<h3>Paso 13</h3><p>Es necesario retirar la denuncia hecha en la policia cuando aparezca la persona desaparecida.</p>");
		
		HTMLPanel paso14 = new HTMLPanel("<h3>Paso 14</h3><p>Retira las fotografias y carteles de los lugares donde se hayan puesto, asi como dar de baja el caso en las paginas web donde se encuentre alojado.</p>");
		
		paso1.setStyleName("pasos");
		paso2.setStyleName("pasos");
		paso3.setStyleName("pasos");
		paso4.setStyleName("pasos");
		paso5.setStyleName("pasos");
		paso6.setStyleName("pasos");
		paso7.setStyleName("pasos");
		paso8.setStyleName("pasos");
		paso9.setStyleName("pasos");
		paso10.setStyleName("pasos");
		paso11.setStyleName("pasos");
		paso12.setStyleName("pasos");
		paso13.setStyleName("pasos");
		paso14.setStyleName("pasos");
		
		subContent.add(bienvenida);
		subContent.add(paso1);
		subContent.add(paso2);
		subContent.add(paso3);
		subContent.add(paso4);
		subContent.add(paso5);
		subContent.add(paso6);
		subContent.add(paso7);
		subContent.add(paso8);
		subContent.add(paso9);
		subContent.add(paso10);
		subContent.add(paso11);
		subContent.add(paso12);
		subContent.add(paso13);
		subContent.add(paso14);
		
		initWidget(subContent);
	}
}
