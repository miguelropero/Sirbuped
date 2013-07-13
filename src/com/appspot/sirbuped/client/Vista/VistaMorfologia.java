package com.appspot.sirbuped.client.Vista;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RadioButton;

public class VistaMorfologia extends Composite 
{
	public VistaMorfologia(Boolean editar)
	{
		final HTMLPanel subContent = new HTMLPanel("");
		subContent.setStyleName("morfologia");
		final ArrayList<RadioButton> listadoRadio = new ArrayList<RadioButton>();
		final ArrayList<CheckBox> listadoCheckBox = new ArrayList<CheckBox>();
		Byte identificador = 0;
		
		/* OPCIONES GENERALES DE LA MORFOLOGIA DEL DESAPARECIDO */
		CaptionPanel fieldsetGeneral = new CaptionPanel("General");
		HTMLPanel divGeneral = new HTMLPanel("");
		
		/* Opciones de la Contextura del desaparecido */
		CaptionPanel fieldsetContextura = new CaptionPanel("Contextura");
		HTMLPanel divContextura = new HTMLPanel("");
		
		RadioButton contexturaObesa = new RadioButton("contextura", "Obesa");
		contexturaObesa.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaObesa);
		RadioButton contexturaRobusta = new RadioButton("contextura", "Robusta");
		contexturaRobusta.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaRobusta);
		RadioButton contexturaMediana = new RadioButton("contextura", "Mediana");
		contexturaMediana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaMediana);
		RadioButton contexturaDelgada = new RadioButton("contextura", "Delgada");
		contexturaDelgada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaDelgada);
		RadioButton contexturaNoRecuerda = new RadioButton("contextura", "No Recuerda");
		contexturaNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaNoRecuerda);
		
		divContextura.add(contexturaObesa);
		divContextura.add(contexturaRobusta);
		divContextura.add(contexturaMediana);
		divContextura.add(contexturaDelgada);
		divContextura.add(contexturaNoRecuerda);
		
		fieldsetContextura.add(divContextura);
		divGeneral.add(fieldsetContextura);
		
		/* Opciones de los Labios del desaparecido */
		CaptionPanel fieldsetLabios = new CaptionPanel("Labios");
		HTMLPanel divLabios = new HTMLPanel("");
		
		RadioButton labiosGruesos = new RadioButton("labios", "Gruesos");
		labiosGruesos.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(labiosGruesos);
		RadioButton labiosMedianos = new RadioButton("labios", "Medianos");
		labiosMedianos.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(labiosMedianos);
		RadioButton labiosDelgados = new RadioButton("labios", "Delgados");
		labiosDelgados.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(labiosDelgados);
		RadioButton labiosNoRecuerda = new RadioButton("labios", "No Recuerda");
		labiosNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(labiosNoRecuerda);
		
		divLabios.add(labiosGruesos);
		divLabios.add(labiosMedianos);
		divLabios.add(labiosDelgados);
		divLabios.add(labiosNoRecuerda);
		
		fieldsetLabios.add(divLabios);
		divGeneral.add(fieldsetLabios);
		
		/* Opciones de la Boca del desaparecido */
		CaptionPanel fieldsetBoca = new CaptionPanel("Boca");
		HTMLPanel divBoca = new HTMLPanel("");
		
		RadioButton bocaGrande = new RadioButton("boca", "Grande");
		bocaGrande.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bocaGrande);
		RadioButton bocaMediana = new RadioButton("boca", "Mediana");
		bocaMediana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bocaMediana);
		RadioButton bocaPequena = new RadioButton("boca", "Pequena");
		bocaPequena.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bocaPequena);
		RadioButton bocaNoRecuerda = new RadioButton("boca", "No Recuerda");
		bocaNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bocaNoRecuerda);
		
		divBoca.add(bocaGrande);
		divBoca.add(bocaMediana);
		divBoca.add(bocaPequena);
		divBoca.add(bocaNoRecuerda);
		
		fieldsetBoca.add(divBoca);
		divGeneral.add(fieldsetBoca);
		
		fieldsetGeneral.add(divGeneral);
		
		/* Opciones de la Nariz del desaparecido */
		CaptionPanel fieldsetNariz = new CaptionPanel("Nariz");
		HTMLPanel divNariz = new HTMLPanel("");
		
		CheckBox narizDesviada = new CheckBox("Desviada");
		narizDesviada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(narizDesviada);
		CheckBox narizAchatada = new CheckBox("Achatada");
		narizAchatada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(narizAchatada);
		CheckBox narizOperada = new CheckBox("Operada");
		narizOperada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(narizOperada);
		CheckBox narizAlomada = new CheckBox("Alomada");
		narizAlomada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(narizAlomada);
		CheckBox narizRecta = new CheckBox("Recta");
		narizRecta.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(narizRecta);
		//CheckBox narizNoRecuerda = new CheckBox("No Recuerda");
		//narizNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoCheckBox.add(narizNoRecuerda);
		
		divNariz.add(narizDesviada);
		divNariz.add(narizAchatada);
		divNariz.add(narizOperada);
		divNariz.add(narizAlomada);
		divNariz.add(narizRecta);
		//divNariz.add(narizNoRecuerda);
		
		fieldsetNariz.add(divNariz);
		divGeneral.add(fieldsetNariz);
		
		/* Opciones de las Orejas del desaparecido */
		CaptionPanel fieldsetOrejas = new CaptionPanel("Orejas");
		HTMLPanel divOrejas = new HTMLPanel("");
		
		CheckBox orejasPeludas = new CheckBox("Peludas");
		orejasPeludas.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(orejasPeludas);
		CheckBox orejasPerforadas = new CheckBox("Perforadas");
		orejasPerforadas.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(orejasPerforadas);
		//CheckBox orejasProtesisAuditiva = new CheckBox("Protesis Auditiva");
		//orejasProtesisAuditiva.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoCheckBox.add(orejasProtesisAuditiva);
		CheckBox orejasLobuloAdherido = new CheckBox("Lobulos Adheridos");
		orejasLobuloAdherido.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(orejasLobuloAdherido);
		CheckBox orejasLobuloSeparado = new CheckBox("Lobulo Separado");
		orejasLobuloSeparado.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(orejasLobuloSeparado);
		CheckBox lobuloNoRecuerda = new CheckBox("No Recuerda");
		lobuloNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(lobuloNoRecuerda);
		
		divOrejas.add(orejasPeludas);
		divOrejas.add(orejasPerforadas);
		//divOrejas.add(orejasProtesisAuditiva);
		divOrejas.add(orejasLobuloAdherido);
		divOrejas.add(orejasLobuloSeparado);
		divOrejas.add(lobuloNoRecuerda);
		
		fieldsetOrejas.add(divOrejas);
		divGeneral.add(fieldsetOrejas);
		
		
		/* OPCIONES PARA LAS CARACTERISTICAS DE LA CARA DEL DESAPARECIDO */
		CaptionPanel fieldsetCara = new CaptionPanel("Cara");
		HTMLPanel divCara = new HTMLPanel("");
		
		/* Contorno de la Cara */
		CaptionPanel fieldsetContornoCara = new CaptionPanel("Contorno");
		HTMLPanel divContornoCara = new HTMLPanel("");
		
		RadioButton contexturaCaraRedonda = new RadioButton("contexturacara", "Redonda");
		contexturaCaraRedonda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaCaraRedonda);
		RadioButton contexturaCaraOvalada = new RadioButton("contexturacara", "Ovalada");
		contexturaCaraOvalada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaCaraOvalada);
		RadioButton contexturaCaraCuadrada = new RadioButton("contexturacara", "Cuadrada");
		contexturaCaraCuadrada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaCaraCuadrada);
		RadioButton contexturaCaraRectangular = new RadioButton("contexturacara", "Rectangular");
		contexturaCaraRectangular.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaCaraRectangular);
		RadioButton contexturaCaraNoRecuerda = new RadioButton("contexturacara", "No Recuerda");
		contexturaCaraNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(contexturaCaraNoRecuerda);
		
		divContornoCara.add(contexturaCaraRedonda);
		divContornoCara.add(contexturaCaraOvalada);
		divContornoCara.add(contexturaCaraCuadrada);
		divContornoCara.add(contexturaCaraRectangular);
		divContornoCara.add(contexturaCaraNoRecuerda);
		
		fieldsetContornoCara.add(divContornoCara);
		divCara.add(fieldsetContornoCara);
		
		/* Color de Piel de la Cara */
		CaptionPanel fieldsetColorCara = new CaptionPanel("Color de Piel");
		HTMLPanel divColorCara = new HTMLPanel("");
		
		RadioButton colorCaraAlbino = new RadioButton("colorcara", "Albino");
		colorCaraAlbino.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCaraAlbino);
		RadioButton colorCaraBlanco = new RadioButton("colorcara", "Blanco");
		colorCaraBlanco.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCaraBlanco);
		RadioButton colorCaraTrigueno = new RadioButton("colorcara", "Trigueno");
		colorCaraTrigueno.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCaraTrigueno);
		RadioButton colorCaraNegro = new RadioButton("colorcara", "Negro");
		colorCaraNegro.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCaraNegro);
		RadioButton colorCaraMoreno = new RadioButton("colorcara", "Morena");
		colorCaraMoreno.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCaraMoreno);
		//RadioButton colorCaraAmarillo = new RadioButton("colorcara", "Amarillo");
		//colorCaraAmarillo.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoRadio.add(colorCaraAmarillo);
		//RadioButton colorCaraNoRecuerda = new RadioButton("colorcara", "No Recuerda");
		//colorCaraNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoRadio.add(colorCaraNoRecuerda);
		
		divColorCara.add(colorCaraAlbino);
		divColorCara.add(colorCaraBlanco);
		divColorCara.add(colorCaraTrigueno);
		divColorCara.add(colorCaraNegro);
		divColorCara.add(colorCaraMoreno);
		//divColorCara.add(colorCaraAmarillo);
		//divColorCara.add(colorCaraNoRecuerda);
		
		fieldsetColorCara.add(divColorCara);
		divCara.add(fieldsetColorCara);
		
		/* Particularidades de la Cara del Desaparecido */
		CaptionPanel fieldsetParticularidadCara = new CaptionPanel("Particularidad");
		HTMLPanel divParticularidadCara = new HTMLPanel("");
		
		CheckBox particularidadCaraAcne = new CheckBox("Acne");
		particularidadCaraAcne.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCaraAcne);
		CheckBox particularidadCicCaraAcne = new CheckBox("Cicatriz Acne");
		particularidadCicCaraAcne.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCicCaraAcne);
		CheckBox particularidadCaraManchada = new CheckBox("Manchada");
		particularidadCaraManchada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCaraManchada);
		CheckBox particularidadCaraPecosa = new CheckBox("Pecosa");
		particularidadCaraPecosa.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCaraPecosa);
		CheckBox particularidadCaraLunares = new CheckBox("Lunares");
		particularidadCaraLunares.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCaraLunares);
		//CheckBox particularidadCaraNoRecuerda = new CheckBox("No Recuerda");
		//particularidadCaraNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoCheckBox.add(particularidadCaraNoRecuerda);
		
		divParticularidadCara.add(particularidadCaraAcne);
		divParticularidadCara.add(particularidadCicCaraAcne);
		divParticularidadCara.add(particularidadCaraManchada);
		divParticularidadCara.add(particularidadCaraPecosa);
		divParticularidadCara.add(particularidadCaraLunares);
		//divParticularidadCara.add(particularidadCaraNoRecuerda);
		
		fieldsetParticularidadCara.add(divParticularidadCara);
		divCara.add(fieldsetParticularidadCara);
		
		fieldsetCara.add(divCara);
		
		
		/* OPCIONES QUE DESCRIBEN EL CABELLO DEL DESAPARECIDO */
		CaptionPanel fieldsetCabello = new CaptionPanel("Cabello");
		HTMLPanel divCabello = new HTMLPanel("");
		
		/* Opciones de la Longitud del cabello del desaparecido */
		CaptionPanel fieldsetLongitudCabello = new CaptionPanel("Logitud Cabello");
		HTMLPanel divLongitudCabello = new HTMLPanel("");
		
		RadioButton longitudCabelloRapado = new RadioButton("longitudcabello", "Rapado");
		longitudCabelloRapado.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(longitudCabelloRapado);
		RadioButton longitudCabelloCorto = new RadioButton("longitudcabello", "Corto");
		longitudCabelloCorto.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(longitudCabelloCorto);
		RadioButton longitudCabelloMediano = new RadioButton("longitudcabello", "Mediano");
		longitudCabelloMediano.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(longitudCabelloMediano);
		RadioButton longitudCabelloLargo = new RadioButton("longitudcabello", "Largo");
		longitudCabelloLargo.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(longitudCabelloLargo);
		RadioButton longitudCabelloNoRecuerda = new RadioButton("longitudcabello", "No Recuerda");
		longitudCabelloNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(longitudCabelloNoRecuerda);
		
		divLongitudCabello.add(longitudCabelloRapado);
		divLongitudCabello.add(longitudCabelloCorto);
		divLongitudCabello.add(longitudCabelloMediano);
		divLongitudCabello.add(longitudCabelloLargo);
		divLongitudCabello.add(longitudCabelloNoRecuerda);
		
		fieldsetLongitudCabello.add(divLongitudCabello);
		divCabello.add(fieldsetLongitudCabello);
		
		/* Opciones de la Forma del cabello del desaparecido */
		CaptionPanel fieldsetFormaCabello = new CaptionPanel("Forma Cabello");
		HTMLPanel divFormaCabello = new HTMLPanel("");
		
		RadioButton formaCabelloLiso = new RadioButton("formacabello", "Liso");
		formaCabelloLiso.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(formaCabelloLiso);
		RadioButton formaCabelloOndulado = new RadioButton("formacabello", "Ondulado");
		formaCabelloOndulado.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(formaCabelloOndulado);
		RadioButton formaCabelloLanoso = new RadioButton("formacabello", "Lanoso");
		formaCabelloLanoso.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(formaCabelloLanoso);
		RadioButton formaCabelloCrespo = new RadioButton("formacabello", "Crespo");
		formaCabelloCrespo.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(formaCabelloCrespo);
		RadioButton formaCabelloNoRecuerda = new RadioButton("formacabello", "No Recuerda");
		formaCabelloNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(formaCabelloNoRecuerda);
		
		divFormaCabello.add(formaCabelloLiso);
		divFormaCabello.add(formaCabelloOndulado);
		divFormaCabello.add(formaCabelloLanoso);
		divFormaCabello.add(formaCabelloCrespo);
		divFormaCabello.add(formaCabelloNoRecuerda);
		
		fieldsetFormaCabello.add(divFormaCabello);
		divCabello.add(fieldsetFormaCabello);
		
		/* Opciones de la Calvicie del desaparecido */
		CaptionPanel fieldsetCalvicie = new CaptionPanel("Calvicie");
		HTMLPanel divCalvicie = new HTMLPanel("");
		
		RadioButton calvicieNoPresenta = new RadioButton("calvicie", "No Presenta");
		calvicieNoPresenta.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieNoPresenta);
		RadioButton calvicieTotal = new RadioButton("calvicie", "Total");
		calvicieTotal.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieTotal);
		RadioButton calvicieCoronal = new RadioButton("calvicie", "Coronal");
		calvicieCoronal.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieCoronal);
		RadioButton calvicieFrontoCoronal = new RadioButton("calvicie", "Fronto Coronal");
		calvicieFrontoCoronal.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieFrontoCoronal);
		RadioButton calvicieFrontal = new RadioButton("calvicie", "Frontal");
		calvicieFrontal.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieFrontal);
		RadioButton calvicieBilateral = new RadioButton("calvicie", "Bilateral");
		calvicieBilateral.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieBilateral);
		RadioButton calvicieNoRecuerda = new RadioButton("calvicie", "No Recuerda");
		calvicieNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(calvicieNoRecuerda);
		
		divCalvicie.add(calvicieNoPresenta);
		divCalvicie.add(calvicieTotal);
		divCalvicie.add(calvicieCoronal);
		divCalvicie.add(calvicieFrontoCoronal);
		divCalvicie.add(calvicieFrontal);
		divCalvicie.add(calvicieBilateral);
		divCalvicie.add(calvicieNoRecuerda);
		
		fieldsetCalvicie.add(divCalvicie);
		divCabello.add(fieldsetCalvicie);
		
		/* Opciones del Color del cabello desaparecido */
		CaptionPanel fieldsetColorCabello = new CaptionPanel("Color Cabello");
		HTMLPanel divColorCabello = new HTMLPanel("");
		
		RadioButton colorCabelloAlbino = new RadioButton("colorcabello", "Albino");
		colorCabelloAlbino.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloAlbino);
		RadioButton colorCabelloCano = new RadioButton("colorcabello", "Cano");
		colorCabelloCano.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloCano);
		RadioButton colorCabelloEntreCano = new RadioButton("colorcabello", "Entrecano");
		colorCabelloEntreCano.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloEntreCano);
		RadioButton colorCabelloRubio = new RadioButton("colorcabello", "Rubio");
		colorCabelloRubio.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloRubio);
		RadioButton colorCabelloCastano = new RadioButton("colorcabello", "Castano");
		colorCabelloCastano.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloCastano);
		//RadioButton colorCabelloCastanoOscuro = new RadioButton("colorcabello", "Castano Oscuro");
		//colorCabelloCastanoOscuro.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoRadio.add(colorCabelloCastanoOscuro);
		//RadioButton colorCabelloRojizo = new RadioButton("colorcabello", "Rojizo");
		//colorCabelloRojizo.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoRadio.add(colorCabelloRojizo);
		RadioButton colorCabelloNegro = new RadioButton("colorcabello", "Negro");
		colorCabelloNegro.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloNegro);
		RadioButton colorCabelloTinturado = new RadioButton("colorcabello", "Tinturado");
		colorCabelloTinturado.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloTinturado);
		RadioButton colorCabelloNoRecuerda = new RadioButton("colorcabello", "No Recuerda");
		colorCabelloNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(colorCabelloNoRecuerda);
		
		divColorCabello.add(colorCabelloAlbino);
		divColorCabello.add(colorCabelloCano);
		divColorCabello.add(colorCabelloEntreCano);
		divColorCabello.add(colorCabelloRubio);
		divColorCabello.add(colorCabelloCastano);
		//divColorCabello.add(colorCabelloCastanoOscuro);
		//divColorCabello.add(colorCabelloRojizo);
		divColorCabello.add(colorCabelloNegro);
		divColorCabello.add(colorCabelloTinturado);
		//divColorCabello.add(colorCabelloNoRecuerda);
		
		fieldsetColorCabello.add(divColorCabello);
		divCabello.add(fieldsetColorCabello);
		
		/* Particularidad del Cabello del Desaparecido */
		CaptionPanel fieldsetParticularidadCabello = new CaptionPanel("Particularidad");
		HTMLPanel divParticularidadCabello = new HTMLPanel("");
		
		CheckBox particularidadCabelloBisone = new CheckBox("Bisone");
		particularidadCabelloBisone.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloBisone);
		CheckBox particularidadCabelloTransplante = new CheckBox("Transplante");
		particularidadCabelloTransplante.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloTransplante);
		CheckBox particularidadCabelloPeluca = new CheckBox("Peluca");
		particularidadCabelloPeluca.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloPeluca);
		CheckBox particularidadCabelloSistetico = new CheckBox("Sintetico");
		particularidadCabelloSistetico.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloSistetico);
		CheckBox particularidadCabelloExtensiones = new CheckBox("Extensiones");
		particularidadCabelloExtensiones.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloExtensiones);
		CheckBox particularidadCabelloRasta = new CheckBox("Rasta");
		particularidadCabelloRasta.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloRasta);
		CheckBox particularidadCabelloNoRecuerda = new CheckBox("No Recuerda");
		particularidadCabelloNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadCabelloNoRecuerda);
		
		divParticularidadCabello.add(particularidadCabelloBisone);
		divParticularidadCabello.add(particularidadCabelloTransplante);
		divParticularidadCabello.add(particularidadCabelloPeluca);
		divParticularidadCabello.add(particularidadCabelloSistetico);
		divParticularidadCabello.add(particularidadCabelloExtensiones);
		divParticularidadCabello.add(particularidadCabelloRasta);
		divParticularidadCabello.add(particularidadCabelloNoRecuerda);
		
		fieldsetParticularidadCabello.add(divParticularidadCabello);
		divCabello.add(fieldsetParticularidadCabello);
		
		fieldsetCabello.add(divCabello);
		
		
		/* OPCIONES PARA LAS CARACTERISTICAS DE LOS OJOS DEL DESAPARECIDO */
		CaptionPanel fieldsetOjos = new CaptionPanel("Ojos");
		HTMLPanel divOjos = new HTMLPanel("");
		
		/* Color de los Ojos */
		CaptionPanel fieldsetColorOjos = new CaptionPanel("Color");
		HTMLPanel divColorOjos = new HTMLPanel("");
		
		RadioButton ojosColorNegro = new RadioButton("colorojos", "Negros");
		ojosColorNegro.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorNegro);
		RadioButton ojosColorMiel = new RadioButton("colorojos", "Miel");
		ojosColorMiel.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorMiel);
		RadioButton ojosColorMarron = new RadioButton("colorojos", "Marrones");
		ojosColorMarron.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorMarron);
		RadioButton ojosColorGris = new RadioButton("colorojos", "Grises");
		ojosColorGris.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorGris);
		RadioButton ojosColorAzul = new RadioButton("colorojos", "Azules");
		ojosColorAzul.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorAzul);
		RadioButton ojosColorVerde = new RadioButton("colorojos", "Verdes");
		ojosColorVerde.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorVerde);
		RadioButton ojosColorNoRecuerda = new RadioButton("colorojos", "No Recuerda");
		ojosColorNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosColorNoRecuerda);
		
		divColorOjos.add(ojosColorNegro);
		divColorOjos.add(ojosColorMiel);
		divColorOjos.add(ojosColorMarron);
		divColorOjos.add(ojosColorGris);
		divColorOjos.add(ojosColorAzul);
		divColorOjos.add(ojosColorVerde);
		divColorOjos.add(ojosColorNoRecuerda);
		
		fieldsetColorOjos.add(divColorOjos);
		divOjos.add(fieldsetColorOjos);
		
		/* Tamaño de los ojos del desaparecido */
		CaptionPanel fieldsetTamanoOjos = new CaptionPanel("Tamano de Ojos");
		HTMLPanel divTamanoOjos = new HTMLPanel("");
		
		RadioButton ojosTamanoGrande = new RadioButton("tamanoojos", "Grandes");
		ojosTamanoGrande.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosTamanoGrande);
		RadioButton ojosTamanoMediano = new RadioButton("tamanoojos", "Medianos");
		ojosTamanoMediano.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosTamanoMediano);
		RadioButton ojosTamanoPequeno = new RadioButton("tamanoojos", "Pequenos");
		ojosTamanoPequeno.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosTamanoPequeno);
		RadioButton ojosTamanoNoRecuerda = new RadioButton("tamanoojos", "No Recuerda");
		ojosTamanoNoRecuerda.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(ojosTamanoNoRecuerda);
		
		divTamanoOjos.add(ojosTamanoGrande);
		divTamanoOjos.add(ojosTamanoMediano);
		divTamanoOjos.add(ojosTamanoPequeno);
		divTamanoOjos.add(ojosTamanoNoRecuerda);
		
		fieldsetTamanoOjos.add(divTamanoOjos);
		divOjos.add(fieldsetTamanoOjos);
		
		/* Particularidades de los Ojos del Desaparecido */
		CaptionPanel fieldsetParticularidadOjos = new CaptionPanel("Particularidad");
		HTMLPanel divParticularidadOjos = new HTMLPanel("");
		
		CheckBox particularidadOjoArtificial = new CheckBox("Ojo Artificial");
		particularidadOjoArtificial.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoArtificial);
		CheckBox particularidadOjoFaltaIzq = new CheckBox("Falta Ojo Izquierdo");
		particularidadOjoFaltaIzq.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoFaltaIzq);
		CheckBox particularidadOjoFaltaDer = new CheckBox("Falta Ojo Derecho");
		particularidadOjoFaltaDer.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoFaltaDer);
		CheckBox particularidadOjoDiferenteColor = new CheckBox("Diferente Color");
		particularidadOjoDiferenteColor.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoDiferenteColor);
		//CheckBox particularidadOjoLenteContacto = new CheckBox("Lente de Contacto");
		//particularidadOjoLenteContacto.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoCheckBox.add(particularidadOjoLenteContacto);
		CheckBox particularidadOjoParpadoCaido = new CheckBox("Parpado Caido");
		particularidadOjoParpadoCaido.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoParpadoCaido);
		CheckBox particularidadOjoUsaGafas = new CheckBox("Usa Gafas");
		particularidadOjoUsaGafas.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoUsaGafas);
		//CheckBox particularidadOjoCataratas = new CheckBox("Cataratas");
		//particularidadOjoCataratas.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoCheckBox.add(particularidadOjoCataratas);
		CheckBox particularidadOjoBizco = new CheckBox("Bizco");
		particularidadOjoBizco.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoBizco);
		CheckBox particularidadOjoCiego = new CheckBox("Ciego");
		particularidadOjoCiego.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoCheckBox.add(particularidadOjoCiego);
		//CheckBox particularidadOjoOjeras = new CheckBox("Ojeras");
		//particularidadOjoOjeras.getElement().setAttribute("identificador", String.valueOf(identificador++));
		//listadoCheckBox.add(particularidadOjoOjeras);
		
		divParticularidadOjos.add(particularidadOjoArtificial);
		divParticularidadOjos.add(particularidadOjoFaltaIzq);
		divParticularidadOjos.add(particularidadOjoFaltaDer);
		divParticularidadOjos.add(particularidadOjoDiferenteColor);
		//divParticularidadOjos.add(particularidadOjoLenteContacto);
		divParticularidadOjos.add(particularidadOjoParpadoCaido);
		divParticularidadOjos.add(particularidadOjoUsaGafas);
		//divParticularidadOjos.add(particularidadOjoCataratas);
		divParticularidadOjos.add(particularidadOjoBizco);
		divParticularidadOjos.add(particularidadOjoCiego);
		//divParticularidadOjos.add(particularidadOjoOjeras);
		
		fieldsetParticularidadOjos.add(divParticularidadOjos);
		divOjos.add(fieldsetParticularidadOjos);
		
		fieldsetOjos.add(divOjos);
		
		
		/* OPCIONES PARA LAS CARACTERISTICAS DE LA BARBA DEL DESAPARECIDO */
		CaptionPanel fieldsetBarba = new CaptionPanel("Barba");
		HTMLPanel divBarba = new HTMLPanel("");
		
		/* Capilaridad de la Barba */
		CaptionPanel fieldsetCapilaridadBarba = new CaptionPanel("Capilaridad");
		HTMLPanel divCapilaridadBarba = new HTMLPanel("");
		
		RadioButton barbaCapilaridadPoblada = new RadioButton("capilaridadbarba", "Poblada");
		barbaCapilaridadPoblada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaCapilaridadPoblada);
		RadioButton barbaCapilaridadDespoblada = new RadioButton("capilaridadbarba", "Despoblada");
		barbaCapilaridadDespoblada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaCapilaridadDespoblada);
		
		divCapilaridadBarba.add(barbaCapilaridadPoblada);
		divCapilaridadBarba.add(barbaCapilaridadDespoblada);
		
		fieldsetCapilaridadBarba.add(divCapilaridadBarba);
		divBarba.add(fieldsetCapilaridadBarba);
		
		/* Estilo de la Barba */
		CaptionPanel fieldsetEstiloBarba = new CaptionPanel("Estilo");
		HTMLPanel divEstiloBarba = new HTMLPanel("");
		
		RadioButton barbaEstiloChivera = new RadioButton("estilobarba", "Chivera");
		barbaEstiloChivera.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaEstiloChivera);
		RadioButton barbaEstiloCandado = new RadioButton("estilobarba", "Candado");
		barbaEstiloCandado.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaEstiloCandado);
		RadioButton barbaEstiloPatillas = new RadioButton("estilobarba", "Patillas");
		barbaEstiloPatillas.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaEstiloPatillas);
		
		divEstiloBarba.add(barbaEstiloChivera);
		divEstiloBarba.add(barbaEstiloCandado);
		divEstiloBarba.add(barbaEstiloPatillas);
		
		fieldsetEstiloBarba.add(divEstiloBarba);
		divBarba.add(fieldsetEstiloBarba);
		
		/* Longitud de la Barba */
		CaptionPanel fieldsetLongitudBarba = new CaptionPanel("Longitud");
		HTMLPanel divLongitudBarba = new HTMLPanel("");
		
		RadioButton barbaLongitudLarga = new RadioButton("longitudbarba", "Larga");
		barbaLongitudLarga.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaLongitudLarga);
		RadioButton barbaLongitudCorta = new RadioButton("longitudbarba", "Corta");
		barbaLongitudCorta.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaLongitudCorta);
		RadioButton barbaLongitudMediana = new RadioButton("longitudbarba", "Mediana");
		barbaLongitudMediana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaLongitudMediana);
		RadioButton barbaLongitudRasurada = new RadioButton("longitudbarba", "Rasurada");
		barbaLongitudRasurada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaLongitudRasurada);
		
		divLongitudBarba.add(barbaLongitudLarga);
		divLongitudBarba.add(barbaLongitudCorta);
		divLongitudBarba.add(barbaLongitudMediana);
		divLongitudBarba.add(barbaLongitudRasurada);
		
		fieldsetLongitudBarba.add(divLongitudBarba);
		divBarba.add(fieldsetLongitudBarba);
		
		/* Particularidad de la Barba */
		CaptionPanel fieldsetParticularidadBarba = new CaptionPanel("Particularidad");
		HTMLPanel divParticularidadBarba = new HTMLPanel("");
		
		RadioButton barbaParticularidadCana = new RadioButton("particularidadbarba", "Cana");
		barbaParticularidadCana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaParticularidadCana);
		RadioButton barbaParticularidadEntreCana = new RadioButton("particularidadbarba", "Entrecana");
		barbaParticularidadEntreCana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaParticularidadEntreCana);
		RadioButton barbaParticularidadRojiza = new RadioButton("particularidadbarba", "Rojiza");
		barbaParticularidadRojiza.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaParticularidadRojiza);
		RadioButton barbaParticularidadAlbina = new RadioButton("particularidadbarba", "Albina");
		barbaParticularidadAlbina.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(barbaParticularidadAlbina);
		
		divParticularidadBarba.add(barbaParticularidadCana);
		divParticularidadBarba.add(barbaParticularidadEntreCana);
		divParticularidadBarba.add(barbaParticularidadRojiza);
		divParticularidadBarba.add(barbaParticularidadAlbina);
		
		fieldsetParticularidadBarba.add(divParticularidadBarba);
		divBarba.add(fieldsetParticularidadBarba);
		
		fieldsetBarba.add(divBarba);
		
		
		/* OPCIONES PARA LAS CARACTERISTICAS DEL BIGOTE DEL DESAPARECIDO */
		CaptionPanel fieldsetBigote = new CaptionPanel("Bigote");
		HTMLPanel divBigote = new HTMLPanel("");
		
		/* Capilaridad de la Bigote */
		CaptionPanel fieldsetCapilaridadBigote = new CaptionPanel("Capilaridad");
		HTMLPanel divCapilaridadBigote = new HTMLPanel("");
		
		RadioButton bigoteCapilaridadPoblada = new RadioButton("capilaridadbigote", "Poblada");
		bigoteCapilaridadPoblada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteCapilaridadPoblada);
		RadioButton bigoteCapilaridadDespoblada = new RadioButton("capilaridadbigote", "Despoblada");
		bigoteCapilaridadDespoblada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteCapilaridadDespoblada);
		
		divCapilaridadBigote.add(bigoteCapilaridadPoblada);
		divCapilaridadBigote.add(bigoteCapilaridadDespoblada);
		
		fieldsetCapilaridadBigote.add(divCapilaridadBigote);
		divBigote.add(fieldsetCapilaridadBigote);
		
		/* Longitud del Bigote */
		CaptionPanel fieldsetLongitudBigote = new CaptionPanel("Longitud");
		HTMLPanel divLongitudBigote = new HTMLPanel("");
		
		RadioButton bigoteLongitudLarga = new RadioButton("longitudbigote", "Largo");
		bigoteLongitudLarga.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteLongitudLarga);
		RadioButton bigoteLongitudCorta = new RadioButton("longitudbigote", "Corto");
		bigoteLongitudCorta.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteLongitudCorta);
		RadioButton bigoteLongitudMediana = new RadioButton("longitudbigote", "Mediano");
		bigoteLongitudMediana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteLongitudMediana);
		RadioButton bigoteLongitudRasurada = new RadioButton("longitudbigote", "Rasurado");
		bigoteLongitudRasurada.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteLongitudRasurada);
		
		divLongitudBigote.add(bigoteLongitudLarga);
		divLongitudBigote.add(bigoteLongitudCorta);
		divLongitudBigote.add(bigoteLongitudMediana);
		divLongitudBigote.add(bigoteLongitudRasurada);
		
		fieldsetLongitudBigote.add(divLongitudBigote);
		divBigote.add(fieldsetLongitudBigote);
		
		/* Particularidad de la Bigote */
		CaptionPanel fieldsetParticularidadBigote = new CaptionPanel("Particularidad");
		HTMLPanel divParticularidadBigote = new HTMLPanel("");
		
		RadioButton bigoteParticularidadCana = new RadioButton("particularidadbigote", "Cana");
		bigoteParticularidadCana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteParticularidadCana);
		RadioButton bigoteParticularidadEntreCana = new RadioButton("particularidadbigote", "Entrecana");
		bigoteParticularidadEntreCana.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteParticularidadEntreCana);
		RadioButton bigoteParticularidadRojiza = new RadioButton("particularidadbigote", "Rojiza");
		bigoteParticularidadRojiza.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteParticularidadRojiza);
		RadioButton bigoteParticularidadAlbina = new RadioButton("particularidadbigote", "Albina");
		bigoteParticularidadAlbina.getElement().setAttribute("identificador", String.valueOf(identificador++));
		listadoRadio.add(bigoteParticularidadAlbina);
		
		divParticularidadBigote.add(bigoteParticularidadCana);
		divParticularidadBigote.add(bigoteParticularidadEntreCana);
		divParticularidadBigote.add(bigoteParticularidadRojiza);
		divParticularidadBigote.add(bigoteParticularidadAlbina);
		
		fieldsetParticularidadBigote.add(divParticularidadBigote);
		divBigote.add(fieldsetParticularidadBigote);
		
		fieldsetBigote.add(divBigote);
		
		
		/* ADICIÓN DE LOS DIFERENTES FIELDSET AL CONTENEDOR GENERAL */
		subContent.add(fieldsetGeneral);
		subContent.add(fieldsetCara);
		subContent.add(fieldsetCabello);
		subContent.add(fieldsetOjos);
		subContent.add(fieldsetBarba);
		subContent.add(fieldsetBigote);
		
		final Button btnRegistrar = new Button("Registrar");
		subContent.add(btnRegistrar);
		btnRegistrar.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event) 
			{
				for(int i=0;i<listadoRadio.size();i++)
				{
					if(listadoRadio.get(i).getValue()==true)
					{
						Window.alert(listadoRadio.get(i).getText());
						Window.alert(listadoRadio.get(i).getElement().getAttribute("identificador"));
					}
				}
			}
				
		});
		
		initWidget(subContent);
	}
}
