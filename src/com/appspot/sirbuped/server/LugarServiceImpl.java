package com.appspot.sirbuped.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.appspot.sirbuped.client.DTO.Ciudad;
import com.appspot.sirbuped.client.DTO.Departamento;
import com.appspot.sirbuped.client.DTO.Desaparecido;
import com.appspot.sirbuped.client.DTO.Pais;
import com.appspot.sirbuped.client.Interfaz.LugarService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LugarServiceImpl extends RemoteServiceServlet implements LugarService
{
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Desaparecido.class.getName());
	
	@SuppressWarnings("unchecked")
	public ArrayList<Pais> getPaises()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Pais.class);
		
		List<Pais> paises = new ArrayList<Pais>();
		List<Pais> detachedPaises = new ArrayList<Pais>();
		
		try 
		{
			paises = (List<Pais>) query.execute();
			
			for(Pais pais:paises)
			{
				detachedPaises.add(pm.detachCopy(pais));
				detachedPaises.get(detachedPaises.size()-1).setDepartamentos(null);
			}
		}
		finally
		{
			pm.close();
		}
		
		return ((ArrayList<Pais>)detachedPaises);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Departamento> getDepartamentos(String pais)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Pais.class);
		query.setFilter("nombre == nombrePais");
	    query.declareParameters("String nombrePais");
		
		List<Pais> paises = new ArrayList<Pais>();
		
		List<Departamento> detachedDepartamento = new ArrayList<Departamento>();
		
		try 
		{
			paises = (List<Pais>) query.execute(pais);
			
			for(Departamento departamento : paises.get(0).getDepartamentos())
			{
				detachedDepartamento.add(pm.detachCopy(departamento));
				detachedDepartamento.get(detachedDepartamento.size()-1).setCiudades(null);
			}
		}
		finally
		{
			pm.close();
		}
		
		return ((ArrayList<Departamento>)detachedDepartamento);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Ciudad> getCiudades(String pais, String dpto)
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Pais.class);
		query.setFilter("nombre == nombrePais");
	    query.declareParameters("String nombrePais");
		
		List<Pais> paises = new ArrayList<Pais>();
		List<Pais> detachedPaises = new ArrayList<Pais>();
		
		List<Ciudad> detachedCiudad = new ArrayList<Ciudad>();
		
		try 
		{
			paises = (List<Pais>) query.execute(pais);
			
			for(Departamento departamento : paises.get(0).getDepartamentos())
			{
				if(departamento.getNombre().equals(dpto))
				{
					for(Ciudad ciudad : departamento.getCiudades())
					{
						detachedCiudad.add(pm.detachCopy(ciudad));
					}
				}
			}
			log.warning(detachedPaises.toString());
		}
		finally
		{
			pm.close();
		}
		return ((ArrayList<Ciudad>)detachedCiudad);
	}
	
	@SuppressWarnings("unchecked")
	public void generarLugar()
	{
		
		ArrayList<String> paises = new ArrayList<String>();
		paises.add("Colombia");
		//paises.add("Venezuela");
		
		ArrayList<ArrayList<String>> departamentos = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> dptoColombia = new ArrayList<String>();
		dptoColombia.add("Amazonas");
		dptoColombia.add("Antioquia");
		dptoColombia.add("Arauca");
		dptoColombia.add("Atl�ntico");
		dptoColombia.add("Bolivar");
		dptoColombia.add("Boyaca");
		dptoColombia.add("Caldas");
		dptoColombia.add("Caqueta");
		dptoColombia.add("Casanare");
		dptoColombia.add("Cauca");
		dptoColombia.add("Cesar");
		dptoColombia.add("Choc�");
		dptoColombia.add("Cordoba");
		dptoColombia.add("Cundinamarca");
		dptoColombia.add("Guain�a");
		dptoColombia.add("Guajira");
		dptoColombia.add("Guaviare");
		dptoColombia.add("Huila");
		dptoColombia.add("Magdalena");
		dptoColombia.add("Meta");
		dptoColombia.add("Nari�o");
		dptoColombia.add("Norte de Santander");
		dptoColombia.add("Putumayo");
		dptoColombia.add("Quind�o");
		dptoColombia.add("Risaralda");
		dptoColombia.add("San Andres y Providencia");
		dptoColombia.add("Santander");
		dptoColombia.add("Sucre");
		dptoColombia.add("Tolima");
		dptoColombia.add("Valle del Cauca");
		dptoColombia.add("Vaup�s");
		dptoColombia.add("Vichada");
		
		/*ArrayList<String> dptoVenezuela = new ArrayList<String>();
		dptoVenezuela.add("Zulia");
		dptoVenezuela.add("Tachira");
		dptoVenezuela.add("Merida");
		dptoVenezuela.add("Barinas");
		dptoVenezuela.add("Trujillo");
		dptoVenezuela.add("Lara");
		dptoVenezuela.add("Falc\u00F3n");
		dptoVenezuela.add("Portuguesa");
		dptoVenezuela.add("Apure");
		dptoVenezuela.add("Cojedes");
		dptoVenezuela.add("Yaracuy");
		dptoVenezuela.add("Carabobo");*/
		
		departamentos.add(dptoColombia);
		//departamentos.add(dptoVenezuela);
		
		
		ArrayList<ArrayList<String>> ciudades = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> ciudadesAmazonas = new ArrayList<String>();
		ciudadesAmazonas.add("El Encanto");
		ciudadesAmazonas.add("La Chorrera");
		ciudadesAmazonas.add("La Pedrera");
		ciudadesAmazonas.add("La Victoria");
		ciudadesAmazonas.add("Leticia");
		ciudadesAmazonas.add("Mirit�-Paran�");
		ciudadesAmazonas.add("Puerto Alegr�a");
		ciudadesAmazonas.add("Puerto Arica");
		ciudadesAmazonas.add("Puerto Nari�o");
		ciudadesAmazonas.add("Puerto Santander");
		ciudadesAmazonas.add("Tarapac�");
		
		ArrayList<String> ciudadesAntioquia = new ArrayList<String>();
		ciudadesAntioquia.add("Medell�n");
		ciudadesAntioquia.add("Abejorral");
		ciudadesAntioquia.add("Abriaqui");
		ciudadesAntioquia.add("Alejandr�a");
		ciudadesAntioquia.add("Amag�");
		ciudadesAntioquia.add("Amalfi");
		ciudadesAntioquia.add("Andes");
		ciudadesAntioquia.add("Angel�polis");
		ciudadesAntioquia.add("Angostura");
		ciudadesAntioquia.add("Anor�");
		ciudadesAntioquia.add("Antioquia");
		ciudadesAntioquia.add("Anz�");
		ciudadesAntioquia.add("Apartad�");
		ciudadesAntioquia.add("Arboletes");
		ciudadesAntioquia.add("Argelia");
		ciudadesAntioquia.add("Armenia");
		ciudadesAntioquia.add("Barbosa");
		ciudadesAntioquia.add("Belmira");
		ciudadesAntioquia.add("Bello");
		ciudadesAntioquia.add("Betania");
		ciudadesAntioquia.add("Betulia");
		ciudadesAntioquia.add("Bol�var");
		ciudadesAntioquia.add("Brise�o");
		ciudadesAntioquia.add("Buritic�");
		ciudadesAntioquia.add("C�ceres");
		ciudadesAntioquia.add("Caicedo");
		ciudadesAntioquia.add("Caldas");
		ciudadesAntioquia.add("Campamento");
		ciudadesAntioquia.add("Ca�asgordas");
		ciudadesAntioquia.add("Caracol�");
		ciudadesAntioquia.add("Caramanta");
		ciudadesAntioquia.add("Carepa");
		ciudadesAntioquia.add("Carmen de Viboral");
		ciudadesAntioquia.add("Carolina");
		ciudadesAntioquia.add("Caucasia");
		ciudadesAntioquia.add("Chigorod�");
		ciudadesAntioquia.add("Cisneros");
		ciudadesAntioquia.add("Cocorn�");
		ciudadesAntioquia.add("Concepci�n");
		ciudadesAntioquia.add("Concordia");
		ciudadesAntioquia.add("Copacabana");
		ciudadesAntioquia.add("Dabeiba");
		ciudadesAntioquia.add("Don Mat�as");
		ciudadesAntioquia.add("Eb�jico");
		ciudadesAntioquia.add("El Bagre");
		ciudadesAntioquia.add("Entrerr�os");
		ciudadesAntioquia.add("Envigado");
		ciudadesAntioquia.add("Fredonia");
		ciudadesAntioquia.add("Frontino");
		ciudadesAntioquia.add("Giraldo");
		ciudadesAntioquia.add("Girardota");
		ciudadesAntioquia.add("G�mez Plata");
		ciudadesAntioquia.add("Granada");
		ciudadesAntioquia.add("Guadalupe");
		ciudadesAntioquia.add("Guarne");
		ciudadesAntioquia.add("Guatap�");
		ciudadesAntioquia.add("Heliconia");
		ciudadesAntioquia.add("Hispania");
		ciudadesAntioquia.add("Itag��");
		ciudadesAntioquia.add("Ituango");
		ciudadesAntioquia.add("Jard�n");
		ciudadesAntioquia.add("Jeric�");
		ciudadesAntioquia.add("La Ceja");
		ciudadesAntioquia.add("La Estrella");
		ciudadesAntioquia.add("La Pintada");
		ciudadesAntioquia.add("La Uni�n");
		ciudadesAntioquia.add("Liborina");
		ciudadesAntioquia.add("Maceo");
		ciudadesAntioquia.add("Marinilla");
		ciudadesAntioquia.add("Montebello");
		ciudadesAntioquia.add("Murind�");
		ciudadesAntioquia.add("Mutat�");
		ciudadesAntioquia.add("Nari�o");
		ciudadesAntioquia.add("Necocl�");
		ciudadesAntioquia.add("Nech�");
		ciudadesAntioquia.add("Olaya");
		ciudadesAntioquia.add("Pe�ol");
		ciudadesAntioquia.add("Peque");
		ciudadesAntioquia.add("Pueblorrico");
		ciudadesAntioquia.add("Puerto Berr�o");
		ciudadesAntioquia.add("Puerto Nare");
		ciudadesAntioquia.add("Puerto Triunfo");
		ciudadesAntioquia.add("Remedios");
		ciudadesAntioquia.add("Retiro");
		ciudadesAntioquia.add("Rionegro");
		ciudadesAntioquia.add("Sabanalarga");
		ciudadesAntioquia.add("Sabaneta");
		ciudadesAntioquia.add("Salgar");
		ciudadesAntioquia.add("San Andr�s");
		ciudadesAntioquia.add("San Carlos");
		ciudadesAntioquia.add("San francisco");
		ciudadesAntioquia.add("San Jer�nimo");
		ciudadesAntioquia.add("San Jos� de Monta�a");
		ciudadesAntioquia.add("San Juan de Urab�");
		ciudadesAntioquia.add("San Luis");
		ciudadesAntioquia.add("San Pedro");
		ciudadesAntioquia.add("San Pedro de Urab�");
		ciudadesAntioquia.add("San Rafael");
		ciudadesAntioquia.add("San Roque");
		ciudadesAntioquia.add("San Vicente");
		ciudadesAntioquia.add("Santa B�rbara");
		ciudadesAntioquia.add("Santa Rosa de Osos");
		ciudadesAntioquia.add("Santo Domingo");
		ciudadesAntioquia.add("Santuario");
		ciudadesAntioquia.add("Segovia");
		ciudadesAntioquia.add("Sons�n");
		ciudadesAntioquia.add("Sopetr�n");
		ciudadesAntioquia.add("T�mesis");
		ciudadesAntioquia.add("Taraz�");
		ciudadesAntioquia.add("Tarso");
		ciudadesAntioquia.add("Titirib�");
		ciudadesAntioquia.add("Toledo");
		ciudadesAntioquia.add("Turbo");
		ciudadesAntioquia.add("Uramita");
		ciudadesAntioquia.add("Urrao");
		ciudadesAntioquia.add("Valdivia");
		ciudadesAntioquia.add("Valpara�so");
		ciudadesAntioquia.add("Vegach�");
		ciudadesAntioquia.add("Venecia");
		ciudadesAntioquia.add("Vig�a del Fuerte");
		ciudadesAntioquia.add("Yal�");
		ciudadesAntioquia.add("Yarumal");
		ciudadesAntioquia.add("Yolomb�");
		ciudadesAntioquia.add("Yond� (Casabe)");
		ciudadesAntioquia.add("Zaragoza");
		
		ArrayList<String> ciudadesArauca = new ArrayList<String>();
		ciudadesArauca.add("Arauca");
		ciudadesArauca.add("Arauquita");
		ciudadesArauca.add("Cravo Norte");
		ciudadesArauca.add("Fortul");
		ciudadesArauca.add("Puerto Rond�n");
		ciudadesArauca.add("Saravena");
		ciudadesArauca.add("Tame");
		
		ArrayList<String> ciudadesAtlantico = new ArrayList<String>();
		ciudadesAtlantico.add("Barranquilla");
		ciudadesAtlantico.add("Baranoa");
		ciudadesAtlantico.add("Campo de la Cruz");
		ciudadesAtlantico.add("Candelaria");
		ciudadesAtlantico.add("Galapa");
		ciudadesAtlantico.add("Juan de Acosta");
		ciudadesAtlantico.add("Luruaco");
		ciudadesAtlantico.add("Malambo");
		ciudadesAtlantico.add("Manat�");
		ciudadesAtlantico.add("Palmar de Varela");
		ciudadesAtlantico.add("Pioj�");
		ciudadesAtlantico.add("Polonuevo");
		ciudadesAtlantico.add("Ponedera");
		ciudadesAtlantico.add("Puerto Colombia");
		ciudadesAtlantico.add("Repel�n");
		ciudadesAtlantico.add("Sabanagrande");
		ciudadesAtlantico.add("Sabanalarga");
		ciudadesAtlantico.add("Santa Luc�a");
		ciudadesAtlantico.add("Santo Tom�s");
		ciudadesAtlantico.add("Soledad");
		ciudadesAtlantico.add("Su�n");
		ciudadesAtlantico.add("Tubar�");
		ciudadesAtlantico.add("Usiacur�");
		
		ArrayList<String> ciudadesBolivar = new ArrayList<String>();
		ciudadesBolivar.add("Cartagena");
		ciudadesBolivar.add("Arenal");
		ciudadesBolivar.add("Arjona");
		ciudadesBolivar.add("Arroyohondo");
		ciudadesBolivar.add("Barranco de Loba");
		ciudadesBolivar.add("Brazuelo de Papayal");
		ciudadesBolivar.add("Calamar");
		ciudadesBolivar.add("Cantagallo");
		ciudadesBolivar.add("El Carmen de Bol�var");
		ciudadesBolivar.add("Cicuco");
		ciudadesBolivar.add("Clemencia");
		ciudadesBolivar.add("C�rdoba");
		ciudadesBolivar.add("El Guamo");
		ciudadesBolivar.add("El Pe��n");
		ciudadesBolivar.add("Hatillo de Loba");
		ciudadesBolivar.add("Magangu�");
		ciudadesBolivar.add("Mahates");
		ciudadesBolivar.add("Margarita");
		ciudadesBolivar.add("Mar�a La Baja");
		ciudadesBolivar.add("Montecristo");
		ciudadesBolivar.add("Morales");
		ciudadesBolivar.add("Noros�");
		ciudadesBolivar.add("Pinillos");
		ciudadesBolivar.add("Regidor");
		ciudadesBolivar.add("R�o Viejo");
		ciudadesBolivar.add("San Cristobal");
		ciudadesBolivar.add("San Estalisnao");
		ciudadesBolivar.add("San Fernando");
		ciudadesBolivar.add("San Jacinto");
		ciudadesBolivar.add("San Jacinto del Cauca");
		ciudadesBolivar.add("San Juan Nepomuceno");
		ciudadesBolivar.add("San Martin de Loba");
		ciudadesBolivar.add("San Pablo");
		ciudadesBolivar.add("Santa Catalina");
		ciudadesBolivar.add("Santa Cruz de Mompox");
		ciudadesBolivar.add("Santa Rosa");
		ciudadesBolivar.add("Santa Rosa del Sur");
		ciudadesBolivar.add("Simit�");
		ciudadesBolivar.add("Soplaviento");
		ciudadesBolivar.add("Talaigua Nuevo");
		ciudadesBolivar.add("Tiquisio");
		ciudadesBolivar.add("Turbaco");
		ciudadesBolivar.add("Turban�");
		ciudadesBolivar.add("Villanueva");
		ciudadesBolivar.add("Zambrano");
		
		ArrayList<String> ciudadesBoyaca = new ArrayList<String>();
		ciudadesBoyaca.add("Tunja");
		ciudadesBoyaca.add("Almeida");
		ciudadesBoyaca.add("Aquitania");
		ciudadesBoyaca.add("Arcabuco");
		ciudadesBoyaca.add("Bel�n");
		ciudadesBoyaca.add("Berbeo");
		ciudadesBoyaca.add("Beteitiva");
		ciudadesBoyaca.add("Boavita");
		ciudadesBoyaca.add("Boyac�");
		ciudadesBoyaca.add("Brise�o");
		ciudadesBoyaca.add("Buenavista");
		ciudadesBoyaca.add("Busbanz�");
		ciudadesBoyaca.add("Caldas");
		ciudadesBoyaca.add("Campohermoso");
		ciudadesBoyaca.add("Cerinza");
		ciudadesBoyaca.add("Chinavita");
		ciudadesBoyaca.add("Chiquinquir�");
		ciudadesBoyaca.add("Chiscas");
		ciudadesBoyaca.add("Chita");
		ciudadesBoyaca.add("Chitaranque");
		ciudadesBoyaca.add("Chivat�");
		ciudadesBoyaca.add("Ci�naga");
		ciudadesBoyaca.add("C�mbita");
		ciudadesBoyaca.add("Coper");
		ciudadesBoyaca.add("Corrales");
		ciudadesBoyaca.add("Covarachia");
		ciudadesBoyaca.add("Cubar");
		ciudadesBoyaca.add("Cucaita");
		ciudadesBoyaca.add("Cuitiva");
		ciudadesBoyaca.add("Ch�quiza");
		ciudadesBoyaca.add("Chivor");
		ciudadesBoyaca.add("Duitama");
		ciudadesBoyaca.add("El Cocuy");
		ciudadesBoyaca.add("El Espino");
		ciudadesBoyaca.add("Firavitoba");
		ciudadesBoyaca.add("Floresta");
		ciudadesBoyaca.add("Gachantiv�");
		ciudadesBoyaca.add("G�meza");
		ciudadesBoyaca.add("Garagoa");
		ciudadesBoyaca.add("Guacamayas");
		ciudadesBoyaca.add("Guateque");
		ciudadesBoyaca.add("Guayat�");
		ciudadesBoyaca.add("Guic�n");
		ciudadesBoyaca.add("Iza");
		ciudadesBoyaca.add("Jenesano");
		ciudadesBoyaca.add("Jeric�");
		ciudadesBoyaca.add("Labranzagrande");
		ciudadesBoyaca.add("La Capilla");
		ciudadesBoyaca.add("La Victoria");
		ciudadesBoyaca.add("La Ubita");
		ciudadesBoyaca.add("Villa de Leyva");
		ciudadesBoyaca.add("Macanal");
		ciudadesBoyaca.add("Marip�");
		ciudadesBoyaca.add("Miraflores");
		ciudadesBoyaca.add("Mongua");
		ciudadesBoyaca.add("Mongu�");
		ciudadesBoyaca.add("Moniquir�");
		ciudadesBoyaca.add("Motavita");
		ciudadesBoyaca.add("Muzo");
		ciudadesBoyaca.add("Nobsa");
		ciudadesBoyaca.add("Nuevo Col�n");
		ciudadesBoyaca.add("Oicat�");
		ciudadesBoyaca.add("Otanche");
		ciudadesBoyaca.add("Pachavita");
		ciudadesBoyaca.add("P�ez");
		ciudadesBoyaca.add("Paipa");
		ciudadesBoyaca.add("Pajarito");
		ciudadesBoyaca.add("Panqueba");
		ciudadesBoyaca.add("Pauna");
		ciudadesBoyaca.add("Paya");
		ciudadesBoyaca.add("Paz de R�o");
		ciudadesBoyaca.add("Pesca");
		ciudadesBoyaca.add("Pisva");
		ciudadesBoyaca.add("Puerto Boyac�");
		ciudadesBoyaca.add("Qu�pama");
		ciudadesBoyaca.add("Ramiquir�");
		ciudadesBoyaca.add("R�quira");
		ciudadesBoyaca.add("Rond�n");
		ciudadesBoyaca.add("Saboy�");
		ciudadesBoyaca.add("S�chica");
		ciudadesBoyaca.add("Samac�");
		ciudadesBoyaca.add("San Eduardo");
		ciudadesBoyaca.add("San Jos� de Pare");
		ciudadesBoyaca.add("San Luis de Gaceno");
		ciudadesBoyaca.add("San Mateo");
		ciudadesBoyaca.add("San Miguel de Sema");
		ciudadesBoyaca.add("San Pablo de Borbur");
		ciudadesBoyaca.add("Santana");
		ciudadesBoyaca.add("Santa Mar�a");
		ciudadesBoyaca.add("Santa Rosa de Viterbo");
		ciudadesBoyaca.add("Santa Sof�a");
		ciudadesBoyaca.add("Sativanorte");
		ciudadesBoyaca.add("Sativasur");
		ciudadesBoyaca.add("Siachoque");
		ciudadesBoyaca.add("Soat�");
		ciudadesBoyaca.add("Socot�");
		ciudadesBoyaca.add("Socha");
		ciudadesBoyaca.add("Sogamoso");
		ciudadesBoyaca.add("Somondoco");
		ciudadesBoyaca.add("Sora");
		ciudadesBoyaca.add("Sotaquir�");
		ciudadesBoyaca.add("Sorac�");
		ciudadesBoyaca.add("Susac�n");
		ciudadesBoyaca.add("Sutamarch�n");
		ciudadesBoyaca.add("Sutatenza");
		ciudadesBoyaca.add("Tasco");
		ciudadesBoyaca.add("Tenza");
		ciudadesBoyaca.add("Tiban�");
		ciudadesBoyaca.add("Tibasosa");
		ciudadesBoyaca.add("Tinjac�");
		ciudadesBoyaca.add("Tipacoque");
		ciudadesBoyaca.add("Toca");
		ciudadesBoyaca.add("Togu�");
		ciudadesBoyaca.add("T�paga");
		ciudadesBoyaca.add("Tota");
		ciudadesBoyaca.add("Tunungua");
		ciudadesBoyaca.add("Turmequ�");
		ciudadesBoyaca.add("Tuta");
		ciudadesBoyaca.add("Tutaz�");
		ciudadesBoyaca.add("�mbita");
		ciudadesBoyaca.add("Ventaquemada");
		ciudadesBoyaca.add("Viracach�");
		ciudadesBoyaca.add("Zetaquir�");
		
		ArrayList<String> ciudadesCaldas = new ArrayList<String>();
		ciudadesCaldas.add("Manizales");
		ciudadesCaldas.add("Aguadas");
		ciudadesCaldas.add("Anserma");
		ciudadesCaldas.add("Aranzazu");
		ciudadesCaldas.add("Belalc�zar");
		ciudadesCaldas.add("Chinchina");
		ciudadesCaldas.add("Filadelfia");
		ciudadesCaldas.add("La Dorada");
		ciudadesCaldas.add("La Merced");
		ciudadesCaldas.add("Manzanares");
		ciudadesCaldas.add("Marmato");
		ciudadesCaldas.add("Marquetalia");
		ciudadesCaldas.add("Marulanda");
		ciudadesCaldas.add("Neira");
		ciudadesCaldas.add("P�cora");
		ciudadesCaldas.add("Palestina");
		ciudadesCaldas.add("Pensilvania");
		ciudadesCaldas.add("Riosucio");
		ciudadesCaldas.add("Risaralda");
		ciudadesCaldas.add("Salamina");
		ciudadesCaldas.add("Saman�");
		ciudadesCaldas.add("San Jos�");
		ciudadesCaldas.add("Sup�a");
		ciudadesCaldas.add("Victoria");
		ciudadesCaldas.add("Villamar�a");
		ciudadesCaldas.add("Viterbo");
		
		ArrayList<String> ciudadesCaqueta = new ArrayList<String>();
		ciudadesCaqueta.add("Florencia");
		ciudadesCaqueta.add("Albania");
		ciudadesCaqueta.add("Bel�n de los Andaqu�es");
		ciudadesCaqueta.add("Cartagena del Chair�");
		ciudadesCaqueta.add("Curillo");
		ciudadesCaqueta.add("El Doncello");
		ciudadesCaqueta.add("El Paujil");
		ciudadesCaqueta.add("La Monta�ita");
		ciudadesCaqueta.add("Mil�n");
		ciudadesCaqueta.add("Morelia");
		ciudadesCaqueta.add("Puerto Rico");
		ciudadesCaqueta.add("San Jos� del Fragua");
		ciudadesCaqueta.add("San Vicente del Cagu�n");
		ciudadesCaqueta.add("Solano");
		ciudadesCaqueta.add("Solita");
		ciudadesCaqueta.add("Valpara�so");
		
		ArrayList<String> ciudadesCasanare = new ArrayList<String>();
		ciudadesCasanare.add("Yopal");
		ciudadesCasanare.add("Aguazul");
		ciudadesCasanare.add("Chameza");
		ciudadesCasanare.add("Hato Corozal");
		ciudadesCasanare.add("La Salina");
		ciudadesCasanare.add("Man�");
		ciudadesCasanare.add("Monterrey");
		ciudadesCasanare.add("Nunch�a");
		ciudadesCasanare.add("Orocu�");
		ciudadesCasanare.add("Paz de Ariporo");
		ciudadesCasanare.add("Pore");
		ciudadesCasanare.add("Recetor");
		ciudadesCasanare.add("Sabalarga");
		ciudadesCasanare.add("S�cama");
		ciudadesCasanare.add("San Luis de Palenque");
		ciudadesCasanare.add("T�mara");
		ciudadesCasanare.add("Florencia");
		ciudadesCasanare.add("Trinidad");
		ciudadesCasanare.add("Villanueva");
		
		ArrayList<String> ciudadesCauca = new ArrayList<String>();
		ciudadesCauca.add("Popay�n");
		ciudadesCauca.add("Almaguer");
		ciudadesCauca.add("Argelia");
		ciudadesCauca.add("Balboa");
		ciudadesCauca.add("Bol�var");
		ciudadesCauca.add("Buenos Aires");
		ciudadesCauca.add("Cajib�o");
		ciudadesCauca.add("Caldono");
		ciudadesCauca.add("Caloto");
		ciudadesCauca.add("Corinto");
		ciudadesCauca.add("El Tambo");
		ciudadesCauca.add("Florencia");
		ciudadesCauca.add("Guachen�");
		ciudadesCauca.add("Guapi");
		ciudadesCauca.add("Inz�");
		ciudadesCauca.add("Jambal�");
		ciudadesCauca.add("La Sierra");
		ciudadesCauca.add("La Vega");
		ciudadesCauca.add("L�pez (Micay)");
		ciudadesCauca.add("Mercaderes");
		ciudadesCauca.add("Miranda");
		ciudadesCauca.add("Morales");
		ciudadesCauca.add("Padilla");
		ciudadesCauca.add("P�ez (Belalcazar)");
		ciudadesCauca.add("Pat�a (El Bordo)");
		ciudadesCauca.add("Piamonte");
		ciudadesCauca.add("Piendam�");
		ciudadesCauca.add("Puerto Tejada");
		ciudadesCauca.add("Purac� (Coconuco)");
		ciudadesCauca.add("Rosas");
		ciudadesCauca.add("San Sebasti�n");
		ciudadesCauca.add("Santander de Quilichao");
		ciudadesCauca.add("Santa Rosa");
		ciudadesCauca.add("Silvia");
		ciudadesCauca.add("Sotar� (Paispamba)");
		ciudadesCauca.add("Su�rez");
		ciudadesCauca.add("Sucre");
		ciudadesCauca.add("Timb�o");
		ciudadesCauca.add("Timbiqu�");
		ciudadesCauca.add("Torib�o");
		ciudadesCauca.add("Totor�");
		ciudadesCauca.add("Villa Rica");

		
		ArrayList<String> ciudadesCesar = new ArrayList<String>();
		ciudadesCesar.add("Valledupar");
		ciudadesCesar.add("Aguachica");
		ciudadesCesar.add("Agust�n Codazzi");
		ciudadesCesar.add("Astrea");
		ciudadesCesar.add("Becerril");
		ciudadesCesar.add("Bosconia");
		ciudadesCesar.add("Chimichagua");
		ciudadesCesar.add("Chiriguan�");
		ciudadesCesar.add("Curuman�");
		ciudadesCesar.add("El Copey");
		ciudadesCesar.add("El Paso");
		ciudadesCesar.add("Gamarra");
		ciudadesCesar.add("Gonz�lez");
		ciudadesCesar.add("La Gloria");
		ciudadesCesar.add("La Jagua de Ibirico");
		ciudadesCesar.add("Manaure Balc�n Cesar");
		ciudadesCesar.add("Pailitas");
		ciudadesCesar.add("Pelaya");
		ciudadesCesar.add("Pueblo Bello");
		ciudadesCesar.add("R�o de Oro");
		ciudadesCesar.add("La Paz (Robles)");
		ciudadesCesar.add("San Alberto");
		ciudadesCesar.add("San Diego");
		ciudadesCesar.add("San Mart�n");
		ciudadesCesar.add("Tamalameque");

		ArrayList<String> ciudadesChoco = new ArrayList<String>();
		ciudadesChoco.add("Quibd�");
		ciudadesChoco.add("Acand�");
		ciudadesChoco.add("Alto Baud� (Pie de Pato)");
		ciudadesChoco.add("Atrato (Yuto)");
		ciudadesChoco.add("Bagad�");
		ciudadesChoco.add("Bah�a Solano (M�tis)");
		ciudadesChoco.add("Bajo Baud� (Pizarro)");
		ciudadesChoco.add("Bojay� (Bellavista)");
		ciudadesChoco.add("Cant�n de San Pablo");
		ciudadesChoco.add("C�rtegui");	
		ciudadesChoco.add("Condoto");
		ciudadesChoco.add("El Carmen");
		ciudadesChoco.add("El Litoral de San Juan");
		ciudadesChoco.add("Itsmina");
		ciudadesChoco.add("Jurad�");
		ciudadesChoco.add("Llor�");
		ciudadesChoco.add("Medio Atrato");
		ciudadesChoco.add("Medio Baud�");
		ciudadesChoco.add("Medio San Juan");
		ciudadesChoco.add("N�vita");
		ciudadesChoco.add("Nuqu�");
		ciudadesChoco.add("Riosucio");
		ciudadesChoco.add("Rio Ir�");
		ciudadesChoco.add("San Jos� del Palmar");
		ciudadesChoco.add("Sip�");
		ciudadesChoco.add("Tad�");
		ciudadesChoco.add("Ungu�a");
		ciudadesChoco.add("Uni�n Panamericana");
		
		ArrayList<String> ciudadesCordoba = new ArrayList<String>();
		ciudadesCordoba.add("Monter�a");
		ciudadesCordoba.add("Ayapel");
		ciudadesCordoba.add("Buenavista");
		ciudadesCordoba.add("Canalete");
		ciudadesCordoba.add("Ceret�");
		ciudadesCordoba.add("Chim�");
		ciudadesCordoba.add("Chin�");
		ciudadesCordoba.add("Ci�naga de Oro");
		ciudadesCordoba.add("Cotorra");
		ciudadesCordoba.add("La Apartada (Frontera)");
		ciudadesCordoba.add("Lorica");
		ciudadesCordoba.add("Los C�rdobas");
		ciudadesCordoba.add("Momil");
		ciudadesCordoba.add("Montel�bano");
		ciudadesCordoba.add("Monitos");
		ciudadesCordoba.add("Planeta Rica");
		ciudadesCordoba.add("Pueblo Nuevo");
		ciudadesCordoba.add("Puerto Escondido");
		ciudadesCordoba.add("Puerto Libertador");
		ciudadesCordoba.add("Pur�sima");
		ciudadesCordoba.add("Sahag�n");
		ciudadesCordoba.add("San Andr�s Sotavento");
		ciudadesCordoba.add("San Antero");
		ciudadesCordoba.add("San Bernardo del Viento");
		ciudadesCordoba.add("San Carlos");
		ciudadesCordoba.add("San Jos� de Ur�");
		ciudadesCordoba.add("San Pelayo");
		ciudadesCordoba.add("Santa Crus de Lorica");
		ciudadesCordoba.add("Tierralta");
		ciudadesCordoba.add("Tuchin");
		ciudadesCordoba.add("Valencia");
		
		ArrayList<String> ciudadesCundinamarca = new ArrayList<String>();
		ciudadesCundinamarca.add("Bogota");
		ciudadesCundinamarca.add("Agua de Dios");
		ciudadesCundinamarca.add("Alb�n");
		ciudadesCundinamarca.add("Anapoima");
		ciudadesCundinamarca.add("Anolaima");
		ciudadesCundinamarca.add("Arbel�ez");
		ciudadesCundinamarca.add("Beltr�n");
		ciudadesCundinamarca.add("Bituima");
		ciudadesCundinamarca.add("Bojac�");
		ciudadesCundinamarca.add("Cabrera");
		ciudadesCundinamarca.add("Cachipay");
		ciudadesCundinamarca.add("Cajic�");
		ciudadesCundinamarca.add("Caparrap�");
		ciudadesCundinamarca.add("C�queza");
		ciudadesCundinamarca.add("Carmen de Carupa");
		ciudadesCundinamarca.add("Chaguan�");
		ciudadesCundinamarca.add("Ch�a");
		ciudadesCundinamarca.add("Chipaque");
		ciudadesCundinamarca.add("Choach�");
		ciudadesCundinamarca.add("Chocont�");
		ciudadesCundinamarca.add("Cogua");
		ciudadesCundinamarca.add("Cota");
		ciudadesCundinamarca.add("Cucunub�");
		ciudadesCundinamarca.add("El Colegio");
		ciudadesCundinamarca.add("El Pe��n");
		ciudadesCundinamarca.add("El Rosal");
		ciudadesCundinamarca.add("Facatativ�");
		ciudadesCundinamarca.add("F�meque");
		ciudadesCundinamarca.add("Fosca�");
		ciudadesCundinamarca.add("Funza");
		ciudadesCundinamarca.add("F�quene");
		ciudadesCundinamarca.add("Fusagasug�");
		ciudadesCundinamarca.add("Gachal�");
		ciudadesCundinamarca.add("Gachancip�");
		ciudadesCundinamarca.add("Gachet�");
		ciudadesCundinamarca.add("Gama");
		ciudadesCundinamarca.add("Girardot");
		ciudadesCundinamarca.add("Granada");
		ciudadesCundinamarca.add("Guachet�");
		ciudadesCundinamarca.add("Guaduas");
		ciudadesCundinamarca.add("Guasca");
		ciudadesCundinamarca.add("Guataqu�");
		ciudadesCundinamarca.add("Guatavita");
		ciudadesCundinamarca.add("Guayabal de S�quima");
		ciudadesCundinamarca.add("Guayabetal");
		ciudadesCundinamarca.add("Guti�rrez");
		ciudadesCundinamarca.add("Jerusal�n");
		ciudadesCundinamarca.add("Jun�n");
		ciudadesCundinamarca.add("La Calera");
		ciudadesCundinamarca.add("La Mesa");
		ciudadesCundinamarca.add("La Palma");
		ciudadesCundinamarca.add("La Pe�a");
		ciudadesCundinamarca.add("La Vega");
		ciudadesCundinamarca.add("Lenguazaque");
		ciudadesCundinamarca.add("Machet�");
		ciudadesCundinamarca.add("Madrid");
		ciudadesCundinamarca.add("Manta");
		ciudadesCundinamarca.add("Medina");
		ciudadesCundinamarca.add("Mosquera");
		ciudadesCundinamarca.add("Nari�o");
		ciudadesCundinamarca.add("Nemoc�n");
		ciudadesCundinamarca.add("Nilo");
		ciudadesCundinamarca.add("Nimaima");
		ciudadesCundinamarca.add("Nocaima");
		ciudadesCundinamarca.add("Venecia (Ospina P�rez)");
		ciudadesCundinamarca.add("Pacho");
		ciudadesCundinamarca.add("Paime");
		ciudadesCundinamarca.add("Pandi");
		ciudadesCundinamarca.add("Paratebueno");
		ciudadesCundinamarca.add("Pasca");
		ciudadesCundinamarca.add("Puerto Salgar");
		ciudadesCundinamarca.add("Pul�");
		ciudadesCundinamarca.add("Quebradanegra");
		ciudadesCundinamarca.add("Quetame");
		ciudadesCundinamarca.add("Quipile");
		ciudadesCundinamarca.add("Rafael");
		ciudadesCundinamarca.add("Ricaurte");
		ciudadesCundinamarca.add("San Antonio de Tequendama");
		ciudadesCundinamarca.add("San Bernardo");
		ciudadesCundinamarca.add("San Cayetano");
		ciudadesCundinamarca.add("San Francisco");
		ciudadesCundinamarca.add("San Juan de Rioseco");
		ciudadesCundinamarca.add("Sasaima");
		ciudadesCundinamarca.add("Sesquil�");
		ciudadesCundinamarca.add("Sibate");
		ciudadesCundinamarca.add("Silvania");
		ciudadesCundinamarca.add("Simijaca");
		ciudadesCundinamarca.add("Soacha");
		ciudadesCundinamarca.add("Sop�");
		ciudadesCundinamarca.add("Subachoque");
		ciudadesCundinamarca.add("Suesca");
		ciudadesCundinamarca.add("Supat�");
		ciudadesCundinamarca.add("Susa");
		ciudadesCundinamarca.add("Sutatausa");
		ciudadesCundinamarca.add("Tabio");
		ciudadesCundinamarca.add("Tausa");
		ciudadesCundinamarca.add("Tena");
		ciudadesCundinamarca.add("Tenjo");
		ciudadesCundinamarca.add("Tibacuy");
		ciudadesCundinamarca.add("Tibirit�");
		ciudadesCundinamarca.add("Tocaima");
		ciudadesCundinamarca.add("Tocancip�");
		ciudadesCundinamarca.add("Topaip�");
		ciudadesCundinamarca.add("Ubal�");
		ciudadesCundinamarca.add("Ubaque");
		ciudadesCundinamarca.add("Ubat�");
		ciudadesCundinamarca.add("Une");
		ciudadesCundinamarca.add("�tica");
		ciudadesCundinamarca.add("Vergara");
		ciudadesCundinamarca.add("Vian�");
		ciudadesCundinamarca.add("Villag�mez");
		ciudadesCundinamarca.add("Villapinz�n");
		ciudadesCundinamarca.add("Villeta");
		ciudadesCundinamarca.add("Viot�");
		ciudadesCundinamarca.add("Yacop�");
		ciudadesCundinamarca.add("Zipac�n");
		ciudadesCundinamarca.add("Zipaquir�");		
		
		ArrayList<String> ciudadesGuainia = new ArrayList<String>();
		ciudadesGuainia.add("In�rida");
		ciudadesGuainia.add("Barrancominas");
		ciudadesGuainia.add("Cacahual");
		ciudadesGuainia.add("La Guadalupe");
		ciudadesGuainia.add("Mapiripana");
		ciudadesGuainia.add("Morichal Nuevo");
		ciudadesGuainia.add("Pana Pana");
		ciudadesGuainia.add("Puerto Colombia");
		ciudadesGuainia.add("San Felipe");
		
		ArrayList<String> ciudadesGuajira = new ArrayList<String>();
		ciudadesGuajira.add("Riohacha");
		ciudadesGuajira.add("Albania");
		ciudadesGuajira.add("Barrancas");
		ciudadesGuajira.add("Dibulla");
		ciudadesGuajira.add("Distracci�n");
		ciudadesGuajira.add("El Molino");
		ciudadesGuajira.add("Fonseca");
		ciudadesGuajira.add("Hatonuevo");
		ciudadesGuajira.add("La Jagua del Pilar");
		ciudadesGuajira.add("Maicao");
		ciudadesGuajira.add("Manaure");
		ciudadesGuajira.add("San Juan del Cesar");
		ciudadesGuajira.add("Uribia");
		ciudadesGuajira.add("Urumita");
		ciudadesGuajira.add("Villanueva");
		
		ArrayList<String> ciudadesGuaviare = new ArrayList<String>();
		ciudadesGuaviare.add("San Jos� del Guaviare");
		ciudadesGuaviare.add("Calamar");
		ciudadesGuaviare.add("El Retorno");
		ciudadesGuaviare.add("Miraflores");		
		
		ArrayList<String> ciudadesHuila = new ArrayList<String>();
		ciudadesHuila.add("Neiva");
		ciudadesHuila.add("Acevedo");
		ciudadesHuila.add("Agrado");
		ciudadesHuila.add("Aipe");
		ciudadesHuila.add("Algeciras");
		ciudadesHuila.add("Altamira");
		ciudadesHuila.add("Baraya");
		ciudadesHuila.add("Campoalegre");
		ciudadesHuila.add("Colombia");
		ciudadesHuila.add("El�as");
		ciudadesHuila.add("Garz�n");
		ciudadesHuila.add("Gigante");
		ciudadesHuila.add("Guadalupe");
		ciudadesHuila.add("Hobo");
		ciudadesHuila.add("Iquira");
		ciudadesHuila.add("Isnos");
		ciudadesHuila.add("La Argentina");
		ciudadesHuila.add("La Plata");
		ciudadesHuila.add("N�taga");
		ciudadesHuila.add("Oporapa");
		ciudadesHuila.add("Paicol");
		ciudadesHuila.add("Palermo");
		ciudadesHuila.add("Palestina");
		ciudadesHuila.add("Pital");
		ciudadesHuila.add("Pitalito");
		ciudadesHuila.add("Rivera");
		ciudadesHuila.add("Saladoblanco");
		ciudadesHuila.add("San Agust�n");
		ciudadesHuila.add("Santa Mar�a");
		ciudadesHuila.add("Suaz�");
		ciudadesHuila.add("Tarqui");
		ciudadesHuila.add("Tesalia");
		ciudadesHuila.add("Tello");
		ciudadesHuila.add("Teruel");
		ciudadesHuila.add("Timan�");
		ciudadesHuila.add("Villavieja");
		ciudadesHuila.add("Yaguar�");
		
		ArrayList<String> ciudadesMagdalena = new ArrayList<String>();
		ciudadesMagdalena.add("Santa Marta");
		ciudadesMagdalena.add("Algarrobo");
		ciudadesMagdalena.add("Aracataca");
		ciudadesMagdalena.add("Ariguan� (El Dif�cil)");
		ciudadesMagdalena.add("Cerro San Antonio");
		ciudadesMagdalena.add("Chivolo");
		ciudadesMagdalena.add("Ci�naga");
		ciudadesMagdalena.add("Concordia");
		ciudadesMagdalena.add("El Banco");
		ciudadesMagdalena.add("El Pi��n");
		ciudadesMagdalena.add("El Ret�n");
		ciudadesMagdalena.add("Fundaci�n");
		ciudadesMagdalena.add("Guamal");
		ciudadesMagdalena.add("Nueva Granada");
		ciudadesMagdalena.add("Pedraza");
		ciudadesMagdalena.add("Piji�o del Carmen");
		ciudadesMagdalena.add("Pivijay");
		ciudadesMagdalena.add("Plato");
		ciudadesMagdalena.add("Publoviejo");
		ciudadesMagdalena.add("Remolino");
		ciudadesMagdalena.add("Sabanas de San Angel");
		ciudadesMagdalena.add("Salamina");
		ciudadesMagdalena.add("San Sebasti�n de Buenavista");
		ciudadesMagdalena.add("San Zen�n");
		ciudadesMagdalena.add("Santa Ana");
		ciudadesMagdalena.add("Santa B�rbara de Pinto");
		ciudadesMagdalena.add("Sitionuevo");
		ciudadesMagdalena.add("Tenerife");
		ciudadesMagdalena.add("Zapay�n");
		ciudadesMagdalena.add("Zona Bananera");
		
		ArrayList<String> ciudadesMeta = new ArrayList<String>();
		ciudadesMeta.add("Villavicencio");
		ciudadesMeta.add("Acacias");
		ciudadesMeta.add("Barranca de Up�a");
		ciudadesMeta.add("Cabuyaro");
		ciudadesMeta.add("Castilla la Nueva");
		ciudadesMeta.add("Cubarral");
		ciudadesMeta.add("Cumaral");
		ciudadesMeta.add("El Calvario");
		ciudadesMeta.add("El Castillo");
		ciudadesMeta.add("El Dorado");
		ciudadesMeta.add("Fuente de Oro");
		ciudadesMeta.add("Granada");
		ciudadesMeta.add("Guamal");
		ciudadesMeta.add("Mapirip�n");
		ciudadesMeta.add("Mesetas");
		ciudadesMeta.add("La Macarena");
		ciudadesMeta.add("La Uribe");
		ciudadesMeta.add("Lejan�as");
		ciudadesMeta.add("Puerto Concordia");
		ciudadesMeta.add("Puerto Gait�n");
		ciudadesMeta.add("Puerto L�pez");
		ciudadesMeta.add("Puerto Lleras");
		ciudadesMeta.add("Puerto Rico");
		ciudadesMeta.add("Restrepo");
		ciudadesMeta.add("San Carlos de Guaroa");
		ciudadesMeta.add("San Juan de Arama");
		ciudadesMeta.add("San Juanito");
		ciudadesMeta.add("San Mart�n");
		ciudadesMeta.add("Vistahermosa");
		
		ArrayList<String> ciudadesNarino = new ArrayList<String>();
		ciudadesNarino.add("Pasto");
		ciudadesNarino.add("Alb�n (San Jos�)");
		ciudadesNarino.add("Aldana");
		ciudadesNarino.add("Ancuy�");
		ciudadesNarino.add("Arboleda (Berruecos)");
		ciudadesNarino.add("Barbacoas");
		ciudadesNarino.add("Bel�n");
		ciudadesNarino.add("Buesaco");
		ciudadesNarino.add("Col�n (G�nova)");
		ciudadesNarino.add("Consac�");
		ciudadesNarino.add("Contadero");
		ciudadesNarino.add("C�rdoba");
		ciudadesNarino.add("Cuaspud (Carlosama)");
		ciudadesNarino.add("Cumbal");
		ciudadesNarino.add("Cumbitar�");
		ciudadesNarino.add("Chachag�i");
		ciudadesNarino.add("El Charco");
		ciudadesNarino.add("El Rosario");
		ciudadesNarino.add("El Tabl�n");
		ciudadesNarino.add("El Tambo");
		ciudadesNarino.add("Funes");
		ciudadesNarino.add("Guachucal");
		ciudadesNarino.add("Guaitarilla");
		ciudadesNarino.add("Gualmat�n");
		ciudadesNarino.add("Iles");
		ciudadesNarino.add("Im�es");
		ciudadesNarino.add("Ipiales");
		ciudadesNarino.add("La Cruz");
		ciudadesNarino.add("La Florida");
		ciudadesNarino.add("La Llanada");
		ciudadesNarino.add("La Tola");
		ciudadesNarino.add("La Uni�n");
		ciudadesNarino.add("Leiva");
		ciudadesNarino.add("Linares");
		ciudadesNarino.add("Los Andes (Sotomayor)");
		ciudadesNarino.add("Mag�� (Pay�n)");
		ciudadesNarino.add("Mallama (Piedrancha)");
		ciudadesNarino.add("Mosquera");
		ciudadesNarino.add("Olaya");
		ciudadesNarino.add("Ospina");
		ciudadesNarino.add("Francisco Pizarro");
		ciudadesNarino.add("Policarpa");
		ciudadesNarino.add("Potos�");
		ciudadesNarino.add("Providencia");
		ciudadesNarino.add("Puerres");
		ciudadesNarino.add("Pupiales");
		ciudadesNarino.add("Ricaurte");
		ciudadesNarino.add("Roberto Pay�n (San Jos�)");
		ciudadesNarino.add("Samaniego");
		ciudadesNarino.add("Sandon�");
		ciudadesNarino.add("San Bernardo");
		ciudadesNarino.add("San Lorenzo");
		ciudadesNarino.add("San Pablo");
		ciudadesNarino.add("San Pedro de Cartago");
		ciudadesNarino.add("Santa B�rbara (Iscuand�)");
		ciudadesNarino.add("Santa Cruz (Guach�vez)");
		ciudadesNarino.add("Sapuy�s");
		ciudadesNarino.add("Taminango");
		ciudadesNarino.add("Tangua");
		ciudadesNarino.add("Tumaco");
		ciudadesNarino.add("T�querres");
		ciudadesNarino.add("Yacuanquer");
		
		ArrayList<String> ciudadesNorteSantander  = new ArrayList<String>();
		ciudadesNorteSantander.add("C�cuta");
		ciudadesNorteSantander.add("Abrego");
		ciudadesNorteSantander.add("Arboledas");
		ciudadesNorteSantander.add("Bochalema");
		ciudadesNorteSantander.add("Bucarasica");
		ciudadesNorteSantander.add("C�cota");
		ciudadesNorteSantander.add("C�chira");
		ciudadesNorteSantander.add("Chin�cota");
		ciudadesNorteSantander.add("Chitag�");
		ciudadesNorteSantander.add("Convenci�n");
		ciudadesNorteSantander.add("Cucutilla");
		ciudadesNorteSantander.add("Durania");
		ciudadesNorteSantander.add("El Carmen");
		ciudadesNorteSantander.add("El Tarra");
		ciudadesNorteSantander.add("El Zulia");
		ciudadesNorteSantander.add("Gramalote");
		ciudadesNorteSantander.add("Hacar�");
		ciudadesNorteSantander.add("Herr�n");
		ciudadesNorteSantander.add("Labateca");
		ciudadesNorteSantander.add("La Esperanza");
		ciudadesNorteSantander.add("La Playa");
		ciudadesNorteSantander.add("Los Patios");
		ciudadesNorteSantander.add("Lourdes");
		ciudadesNorteSantander.add("Mutiscua");
		ciudadesNorteSantander.add("Oca�a");
		ciudadesNorteSantander.add("Pamplona");
		ciudadesNorteSantander.add("Pamplonita");
		ciudadesNorteSantander.add("Puerto Santander");
		ciudadesNorteSantander.add("Ragonvalia");
		ciudadesNorteSantander.add("Salazar");
		ciudadesNorteSantander.add("San Calixto");
		ciudadesNorteSantander.add("San Cayetano");
		ciudadesNorteSantander.add("Santiago");
		ciudadesNorteSantander.add("Sardinata");
		ciudadesNorteSantander.add("Silos");
		ciudadesNorteSantander.add("Teorama");
		ciudadesNorteSantander.add("Tib�");
		ciudadesNorteSantander.add("Toledo");
		ciudadesNorteSantander.add("Villacaro");
		ciudadesNorteSantander.add("Villa del Rosario");
		
		ArrayList<String> ciudadesPutumayo = new ArrayList<String>();
		ciudadesPutumayo.add("Mocoa");
		ciudadesPutumayo.add("Col�n");
		ciudadesPutumayo.add("Orito");
		ciudadesPutumayo.add("Puerto As�s");
		ciudadesPutumayo.add("Puerto Caicedo");
		ciudadesPutumayo.add("Puerto Guzm�n");
		ciudadesPutumayo.add("Puerto Legu�zamo");
		ciudadesPutumayo.add("Sibundoy");
		ciudadesPutumayo.add("San Francisco");
		ciudadesPutumayo.add("San Miguel");
		ciudadesPutumayo.add("Santiago");
		ciudadesPutumayo.add("Villa Gamuez (La Hormiga)");
		ciudadesPutumayo.add("Villa Garz�n");
		
		ArrayList<String> ciudadesQuindio = new ArrayList<String>();
		ciudadesQuindio.add("Armenia");
		ciudadesQuindio.add("Buenavista");
		ciudadesQuindio.add("Calarc�");
		ciudadesQuindio.add("Circasia");
		ciudadesQuindio.add("C�rdoba");
		ciudadesQuindio.add("Filandia");
		ciudadesQuindio.add("G�nova");
		ciudadesQuindio.add("La Tebaida");
		ciudadesQuindio.add("Montenegro");
		ciudadesQuindio.add("Pijao");
		ciudadesQuindio.add("Quimbaya");
		ciudadesQuindio.add("Salento");
		
		ArrayList<String> ciudadesRisaralda = new ArrayList<String>();
		ciudadesRisaralda.add("Pereira");
		ciudadesRisaralda.add("Ap�a");
		ciudadesRisaralda.add("Balboa");
		ciudadesRisaralda.add("Bel�n de Umbr�a");
		ciudadesRisaralda.add("Dos Quebradas");
		ciudadesRisaralda.add("Gu�tica");
		ciudadesRisaralda.add("La Celia");
		ciudadesRisaralda.add("La Virginia");
		ciudadesRisaralda.add("Marsella");
		ciudadesRisaralda.add("Mistrat�");
		ciudadesRisaralda.add("Pueblo Rico");
		ciudadesRisaralda.add("Quinchia");
		ciudadesRisaralda.add("Santa Rosa de Cabal");
		ciudadesRisaralda.add("Santuario");
		
		ArrayList<String> ciudadesSanAndres = new ArrayList<String>();
		ciudadesSanAndres.add("San Andr�s");
		ciudadesSanAndres.add("Providencia");
		
		ArrayList<String> ciudadesSantander = new ArrayList<String>();
		ciudadesSantander.add("Bucaramanga");
		ciudadesSantander.add("Aguada");
		ciudadesSantander.add("Albania");
		ciudadesSantander.add("Aratoca");
		ciudadesSantander.add("Barbosa");
		ciudadesSantander.add("Barichara");
		ciudadesSantander.add("Barrancabermeja");
		ciudadesSantander.add("Betulia");
		ciudadesSantander.add("Bol�var");
		ciudadesSantander.add("Cabrera");
		ciudadesSantander.add("California");
		ciudadesSantander.add("Capitanejo");
		ciudadesSantander.add("Carcas�");
		ciudadesSantander.add("Cepit�");
		ciudadesSantander.add("Cerrito");
		ciudadesSantander.add("Charal�");
		ciudadesSantander.add("Charta");
		ciudadesSantander.add("Chima");
		ciudadesSantander.add("Chipat�");
		ciudadesSantander.add("Cimitarra");
		ciudadesSantander.add("Concepci�n");
		ciudadesSantander.add("Confines");
		ciudadesSantander.add("Contrataci�n");
		ciudadesSantander.add("Coromoro");
		ciudadesSantander.add("Curit�");
		ciudadesSantander.add("El Carmen");
		ciudadesSantander.add("El Guacamayo");
		ciudadesSantander.add("El Pe��n");
		ciudadesSantander.add("El Play�n");
		ciudadesSantander.add("Encino");
		ciudadesSantander.add("Enciso");
		ciudadesSantander.add("Flori�n");
		ciudadesSantander.add("Floridablanca");
		ciudadesSantander.add("Gal�n");
		ciudadesSantander.add("G�mbita");
		ciudadesSantander.add("Gir�n");
		ciudadesSantander.add("Guaca");
		ciudadesSantander.add("Guadalupe");
		ciudadesSantander.add("Guapot�");
		ciudadesSantander.add("Guavata");
		ciudadesSantander.add("Guepsa");
		ciudadesSantander.add("Hato");
		ciudadesSantander.add("Jes�s Mar�a");
		ciudadesSantander.add("Jord�n");
		ciudadesSantander.add("La Belleza");
		ciudadesSantander.add("Land�zuri");
		ciudadesSantander.add("La Paz");
		ciudadesSantander.add("Lebrija");
		ciudadesSantander.add("Los Santos");
		ciudadesSantander.add("Macaravita");
		ciudadesSantander.add("M�laga");
		ciudadesSantander.add("Matanza");
		ciudadesSantander.add("Mogotes");
		ciudadesSantander.add("Molagavita");
		ciudadesSantander.add("Ocamonte");
		ciudadesSantander.add("Oiba");
		ciudadesSantander.add("Onz�ga");
		ciudadesSantander.add("Palmar");
		ciudadesSantander.add("Palmas del Socorro");
		ciudadesSantander.add("P�ramo");
		ciudadesSantander.add("Pie de Cuesta");
		ciudadesSantander.add("Pinchote");
		ciudadesSantander.add("Puente Nacional");
		ciudadesSantander.add("Puerto Parra");
		ciudadesSantander.add("Puerto Wilches");
		ciudadesSantander.add("Rionegro");
		ciudadesSantander.add("Sabana de Torres");
		ciudadesSantander.add("San Andr�s");
		ciudadesSantander.add("San Benito");
		ciudadesSantander.add("San Gil");
		ciudadesSantander.add("San Joaqu�n");
		ciudadesSantander.add("San Jos� de Miranda");
		ciudadesSantander.add("San Miguel");
		ciudadesSantander.add("San Vicente de Chucur�");
		ciudadesSantander.add("Santa B�rbara");
		ciudadesSantander.add("Santa Helena del Op�n");
		ciudadesSantander.add("Simacota");
		ciudadesSantander.add("Socorro");
		ciudadesSantander.add("Suaita");
		ciudadesSantander.add("Sucre");
		ciudadesSantander.add("Surat�");
		ciudadesSantander.add("Tona");
		ciudadesSantander.add("Valle de San Jos�");
		ciudadesSantander.add("V�lez");
		ciudadesSantander.add("Vetas");
		ciudadesSantander.add("Villanueva");
		ciudadesSantander.add("Zapatoca");
		
		ArrayList<String> ciudadesSucre = new ArrayList<String>();
		ciudadesSucre.add("Sincelejo");
		ciudadesSucre.add("Buenavista");
		ciudadesSucre.add("Caimito");
		ciudadesSucre.add("Coloso (Ricaurte)");
		ciudadesSucre.add("Corozal");
		ciudadesSucre.add("Chal�n");
		ciudadesSucre.add("El Roble");
		ciudadesSucre.add("Galeras (Nueva Granada)");
		ciudadesSucre.add("Guarand�");
		ciudadesSucre.add("La Uni�n");
		ciudadesSucre.add("Los Palmitos");
		ciudadesSucre.add("Majagual");
		ciudadesSucre.add("Morroa");
		ciudadesSucre.add("Ovejas");
		ciudadesSucre.add("Palmito");
		ciudadesSucre.add("Sampu�s");
		ciudadesSucre.add("San Benito Abad");
		ciudadesSucre.add("San Juan de Betulia");
		ciudadesSucre.add("San Marcos");
		ciudadesSucre.add("San Onofre");
		ciudadesSucre.add("San Pedro");
		ciudadesSucre.add("Santiago de Tol�");
		ciudadesSucre.add("Sinc�");
		ciudadesSucre.add("Sucre");
		ciudadesSucre.add("Tol�");
		ciudadesSucre.add("Toluviejo");
		
		ArrayList<String> ciudadesTolima = new ArrayList<String>();
		ciudadesTolima.add("Ibagu�");
		ciudadesTolima.add("Alpujarra");
		ciudadesTolima.add("Alvarado");
		ciudadesTolima.add("Ambalema");
		ciudadesTolima.add("Anz�ategui");
		ciudadesTolima.add("Armero (Guayabal)");
		ciudadesTolima.add("Ataco");
		ciudadesTolima.add("Cajamarca");
		ciudadesTolima.add("Carmen de Apical�");
		ciudadesTolima.add("Casabianca");
		ciudadesTolima.add("Chaparral");
		ciudadesTolima.add("Coello");
		ciudadesTolima.add("Coyaima");
		ciudadesTolima.add("Cunday");
		ciudadesTolima.add("Dolores");
		ciudadesTolima.add("Espinal");
		ciudadesTolima.add("Fal�n");
		ciudadesTolima.add("Flandes");
		ciudadesTolima.add("Fresno");
		ciudadesTolima.add("Guamo");
		ciudadesTolima.add("Herveo");
		ciudadesTolima.add("Honda");
		ciudadesTolima.add("Icononzo");
		ciudadesTolima.add("L�rida");
		ciudadesTolima.add("L�bano");
		ciudadesTolima.add("Mariquita");
		ciudadesTolima.add("Melgar");
		ciudadesTolima.add("Murillo");
		ciudadesTolima.add("Natagaima");
		ciudadesTolima.add("Ortega");
		ciudadesTolima.add("Palocabildo");
		ciudadesTolima.add("Piedras");
		ciudadesTolima.add("Planadas");
		ciudadesTolima.add("Prado");
		ciudadesTolima.add("Purificaci�n");
		ciudadesTolima.add("Rioblanco");
		ciudadesTolima.add("Roncesvalles");
		ciudadesTolima.add("Rovira");
		ciudadesTolima.add("Salda�a");
		ciudadesTolima.add("San Antonio");
		ciudadesTolima.add("San Luis");
		ciudadesTolima.add("Santa Isabel");
		ciudadesTolima.add("Su�rez");
		ciudadesTolima.add("Valle de San Juan");
		ciudadesTolima.add("Venadillo");
		ciudadesTolima.add("Villahermosa");
		ciudadesTolima.add("Villarrica");
		
		ArrayList<String> ciudadesValle = new ArrayList<String>();
		ciudadesValle.add("Cali");
		ciudadesValle.add("Alcal�");
		ciudadesValle.add("Andaluc�a");
		ciudadesValle.add("Ansermanuevo");
		ciudadesValle.add("Argelia");
		ciudadesValle.add("Bol�var");
		ciudadesValle.add("Buenaventura");
		ciudadesValle.add("Buga");
		ciudadesValle.add("Bugalagrande");
		ciudadesValle.add("Caicedonia");
		ciudadesValle.add("Calima (Dari�n)");
		ciudadesValle.add("Candelaria");
		ciudadesValle.add("Cartago");
		ciudadesValle.add("Dagua");
		ciudadesValle.add("El �guila");
		ciudadesValle.add("El Cairo");
		ciudadesValle.add("El Cerrito");
		ciudadesValle.add("El Dovio");
		ciudadesValle.add("Florida");
		ciudadesValle.add("Ginebra");
		ciudadesValle.add("Guacar�");
		ciudadesValle.add("Jamund�");
		ciudadesValle.add("La Cumbre");
		ciudadesValle.add("La Uni�n");
		ciudadesValle.add("La Victoria");
		ciudadesValle.add("Obando");
		ciudadesValle.add("Palmira");
		ciudadesValle.add("Pradera");
		ciudadesValle.add("Restrepo");
		ciudadesValle.add("Riofr�o");
		ciudadesValle.add("Roldanillo");
		ciudadesValle.add("San Pedro");
		ciudadesValle.add("Sevilla");
		ciudadesValle.add("Toro");
		ciudadesValle.add("Trujillo");
		ciudadesValle.add("Tulu�");
		ciudadesValle.add("Ulloa");
		ciudadesValle.add("Versalles");
		ciudadesValle.add("Vijes");
		ciudadesValle.add("Yotoco");
		ciudadesValle.add("Yumbo");
		ciudadesValle.add("Zarzal");
		
		ArrayList<String> ciudadesVaupes = new ArrayList<String>();
		ciudadesVaupes.add("Mit�");
		ciudadesVaupes.add("Carur�");
		ciudadesVaupes.add("Pacoa");
		ciudadesVaupes.add("Papunaua");
		ciudadesVaupes.add("Taraira");
		ciudadesVaupes.add("Yavarat�");
		
		ArrayList<String> ciudadesVichada = new ArrayList<String>();
		ciudadesVichada.add("Puerto Carre�o");
		ciudadesVichada.add("La Primavera");
		ciudadesVichada.add("Santa Rosalia");
		ciudadesVichada.add("Cumaribo");
		
		ciudades.add(ciudadesAmazonas);
		ciudades.add(ciudadesAntioquia);
		ciudades.add(ciudadesArauca);
		ciudades.add(ciudadesAtlantico);
		ciudades.add(ciudadesBolivar);
		ciudades.add(ciudadesBoyaca);
		ciudades.add(ciudadesCaldas);
		ciudades.add(ciudadesCaqueta);
		ciudades.add(ciudadesCasanare);
		ciudades.add(ciudadesCauca);
		ciudades.add(ciudadesCesar);
		ciudades.add(ciudadesChoco);
		ciudades.add(ciudadesCordoba);
		ciudades.add(ciudadesCundinamarca);
		ciudades.add(ciudadesGuainia);
		ciudades.add(ciudadesGuajira);
		ciudades.add(ciudadesGuaviare);
		ciudades.add(ciudadesHuila);
		ciudades.add(ciudadesMagdalena);
		ciudades.add(ciudadesMeta);
		ciudades.add(ciudadesNarino);
		ciudades.add(ciudadesNorteSantander);
		ciudades.add(ciudadesPutumayo);
		ciudades.add(ciudadesQuindio);
		ciudades.add(ciudadesRisaralda);
		ciudades.add(ciudadesSanAndres);
		ciudades.add(ciudadesSantander);
		ciudades.add(ciudadesSucre);
		ciudades.add(ciudadesTolima);
		ciudades.add(ciudadesValle);
		ciudades.add(ciudadesVaupes);
		ciudades.add(ciudadesVichada);
		
		/*for(byte i = 0; i < paises.size(); i++)
		{
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Pais pais = new Pais(paises.get(i));
			
			try
			{
				//for(byte j = 0; j < departamentos.get(i).size(); j++)
				for(byte j = 0; j < 2; j++)
				{
					Departamento dptoNuevo = new Departamento(departamentos.get(i).get(j));
					pais.getDepartamentos().add(dptoNuevo);
					
					for(int k = 0; k < ciudades.get(j).size(); k++)
					{
						Ciudad ciudadNueva = new Ciudad(ciudades.get(j).get(k));
						dptoNuevo.getCiudades().add(ciudadNueva);
					}
				}
				log.warning(pais.toString());
				pm.makePersistent(pais);
			}
			finally
			{
				pm.close();
			}
		}*/
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Pais.class);
		
		try
		{
			List<Pais> lista = (List<Pais>) query.execute();
			
			Pais col = lista.get(0);
			
			for(byte j = 1; j < 7; j++)
			{
				Departamento dptoNuevo = new Departamento(departamentos.get(0).get(j));
				col.getDepartamentos().add(dptoNuevo);
				
				for(int k = 0; k < ciudades.get(j).size(); k++)
				{
					Ciudad ciudadNueva = new Ciudad(ciudades.get(j).get(k));
					dptoNuevo.getCiudades().add(ciudadNueva);
				}
			}
		}
		finally
		{
			query.closeAll();
			pm.close();
		}
	}
	
	public void deleteCiudad()
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Ciudad.class);
	    query.deletePersistentAll();
	}
}
