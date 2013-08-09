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
		dptoColombia.add("Atlántico");
		dptoColombia.add("Bolivar");
		dptoColombia.add("Boyaca");
		dptoColombia.add("Caldas");
		dptoColombia.add("Caqueta");
		dptoColombia.add("Casanare");
		dptoColombia.add("Cauca");
		dptoColombia.add("Cesar");
		dptoColombia.add("Chocó");
		dptoColombia.add("Cordoba");
		dptoColombia.add("Cundinamarca");
		dptoColombia.add("Guainía");
		dptoColombia.add("Guajira");
		dptoColombia.add("Guaviare");
		dptoColombia.add("Huila");
		dptoColombia.add("Magdalena");
		dptoColombia.add("Meta");
		dptoColombia.add("Nariño");
		dptoColombia.add("Norte de Santander");
		dptoColombia.add("Putumayo");
		dptoColombia.add("Quindío");
		dptoColombia.add("Risaralda");
		dptoColombia.add("San Andres y Providencia");
		dptoColombia.add("Santander");
		dptoColombia.add("Sucre");
		dptoColombia.add("Tolima");
		dptoColombia.add("Valle del Cauca");
		dptoColombia.add("Vaupés");
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
		ciudadesAmazonas.add("Mirití-Paraná");
		ciudadesAmazonas.add("Puerto Alegría");
		ciudadesAmazonas.add("Puerto Arica");
		ciudadesAmazonas.add("Puerto Nariño");
		ciudadesAmazonas.add("Puerto Santander");
		ciudadesAmazonas.add("Tarapacá");
		
		ArrayList<String> ciudadesAntioquia = new ArrayList<String>();
		ciudadesAntioquia.add("Medellín");
		ciudadesAntioquia.add("Abejorral");
		ciudadesAntioquia.add("Abriaqui");
		ciudadesAntioquia.add("Alejandría");
		ciudadesAntioquia.add("Amagá");
		ciudadesAntioquia.add("Amalfi");
		ciudadesAntioquia.add("Andes");
		ciudadesAntioquia.add("Angelópolis");
		ciudadesAntioquia.add("Angostura");
		ciudadesAntioquia.add("Anorí");
		ciudadesAntioquia.add("Antioquia");
		ciudadesAntioquia.add("Anzá");
		ciudadesAntioquia.add("Apartadó");
		ciudadesAntioquia.add("Arboletes");
		ciudadesAntioquia.add("Argelia");
		ciudadesAntioquia.add("Armenia");
		ciudadesAntioquia.add("Barbosa");
		ciudadesAntioquia.add("Belmira");
		ciudadesAntioquia.add("Bello");
		ciudadesAntioquia.add("Betania");
		ciudadesAntioquia.add("Betulia");
		ciudadesAntioquia.add("Bolívar");
		ciudadesAntioquia.add("Briseño");
		ciudadesAntioquia.add("Buriticá");
		ciudadesAntioquia.add("Cáceres");
		ciudadesAntioquia.add("Caicedo");
		ciudadesAntioquia.add("Caldas");
		ciudadesAntioquia.add("Campamento");
		ciudadesAntioquia.add("Cañasgordas");
		ciudadesAntioquia.add("Caracolí");
		ciudadesAntioquia.add("Caramanta");
		ciudadesAntioquia.add("Carepa");
		ciudadesAntioquia.add("Carmen de Viboral");
		ciudadesAntioquia.add("Carolina");
		ciudadesAntioquia.add("Caucasia");
		ciudadesAntioquia.add("Chigorodó");
		ciudadesAntioquia.add("Cisneros");
		ciudadesAntioquia.add("Cocorná");
		ciudadesAntioquia.add("Concepción");
		ciudadesAntioquia.add("Concordia");
		ciudadesAntioquia.add("Copacabana");
		ciudadesAntioquia.add("Dabeiba");
		ciudadesAntioquia.add("Don Matías");
		ciudadesAntioquia.add("Ebéjico");
		ciudadesAntioquia.add("El Bagre");
		ciudadesAntioquia.add("Entrerríos");
		ciudadesAntioquia.add("Envigado");
		ciudadesAntioquia.add("Fredonia");
		ciudadesAntioquia.add("Frontino");
		ciudadesAntioquia.add("Giraldo");
		ciudadesAntioquia.add("Girardota");
		ciudadesAntioquia.add("Gómez Plata");
		ciudadesAntioquia.add("Granada");
		ciudadesAntioquia.add("Guadalupe");
		ciudadesAntioquia.add("Guarne");
		ciudadesAntioquia.add("Guatapé");
		ciudadesAntioquia.add("Heliconia");
		ciudadesAntioquia.add("Hispania");
		ciudadesAntioquia.add("Itagüí");
		ciudadesAntioquia.add("Ituango");
		ciudadesAntioquia.add("Jardín");
		ciudadesAntioquia.add("Jericó");
		ciudadesAntioquia.add("La Ceja");
		ciudadesAntioquia.add("La Estrella");
		ciudadesAntioquia.add("La Pintada");
		ciudadesAntioquia.add("La Unión");
		ciudadesAntioquia.add("Liborina");
		ciudadesAntioquia.add("Maceo");
		ciudadesAntioquia.add("Marinilla");
		ciudadesAntioquia.add("Montebello");
		ciudadesAntioquia.add("Murindó");
		ciudadesAntioquia.add("Mutatá");
		ciudadesAntioquia.add("Nariño");
		ciudadesAntioquia.add("Necoclí");
		ciudadesAntioquia.add("Nechí");
		ciudadesAntioquia.add("Olaya");
		ciudadesAntioquia.add("Peñol");
		ciudadesAntioquia.add("Peque");
		ciudadesAntioquia.add("Pueblorrico");
		ciudadesAntioquia.add("Puerto Berrío");
		ciudadesAntioquia.add("Puerto Nare");
		ciudadesAntioquia.add("Puerto Triunfo");
		ciudadesAntioquia.add("Remedios");
		ciudadesAntioquia.add("Retiro");
		ciudadesAntioquia.add("Rionegro");
		ciudadesAntioquia.add("Sabanalarga");
		ciudadesAntioquia.add("Sabaneta");
		ciudadesAntioquia.add("Salgar");
		ciudadesAntioquia.add("San Andrés");
		ciudadesAntioquia.add("San Carlos");
		ciudadesAntioquia.add("San francisco");
		ciudadesAntioquia.add("San Jerónimo");
		ciudadesAntioquia.add("San José de Montaña");
		ciudadesAntioquia.add("San Juan de Urabá");
		ciudadesAntioquia.add("San Luis");
		ciudadesAntioquia.add("San Pedro");
		ciudadesAntioquia.add("San Pedro de Urabá");
		ciudadesAntioquia.add("San Rafael");
		ciudadesAntioquia.add("San Roque");
		ciudadesAntioquia.add("San Vicente");
		ciudadesAntioquia.add("Santa Bárbara");
		ciudadesAntioquia.add("Santa Rosa de Osos");
		ciudadesAntioquia.add("Santo Domingo");
		ciudadesAntioquia.add("Santuario");
		ciudadesAntioquia.add("Segovia");
		ciudadesAntioquia.add("Sonsón");
		ciudadesAntioquia.add("Sopetrán");
		ciudadesAntioquia.add("Támesis");
		ciudadesAntioquia.add("Tarazá");
		ciudadesAntioquia.add("Tarso");
		ciudadesAntioquia.add("Titiribí");
		ciudadesAntioquia.add("Toledo");
		ciudadesAntioquia.add("Turbo");
		ciudadesAntioquia.add("Uramita");
		ciudadesAntioquia.add("Urrao");
		ciudadesAntioquia.add("Valdivia");
		ciudadesAntioquia.add("Valparaíso");
		ciudadesAntioquia.add("Vegachí");
		ciudadesAntioquia.add("Venecia");
		ciudadesAntioquia.add("Vigía del Fuerte");
		ciudadesAntioquia.add("Yalí");
		ciudadesAntioquia.add("Yarumal");
		ciudadesAntioquia.add("Yolombó");
		ciudadesAntioquia.add("Yondó (Casabe)");
		ciudadesAntioquia.add("Zaragoza");
		
		ArrayList<String> ciudadesArauca = new ArrayList<String>();
		ciudadesArauca.add("Arauca");
		ciudadesArauca.add("Arauquita");
		ciudadesArauca.add("Cravo Norte");
		ciudadesArauca.add("Fortul");
		ciudadesArauca.add("Puerto Rondón");
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
		ciudadesAtlantico.add("Manatí");
		ciudadesAtlantico.add("Palmar de Varela");
		ciudadesAtlantico.add("Piojó");
		ciudadesAtlantico.add("Polonuevo");
		ciudadesAtlantico.add("Ponedera");
		ciudadesAtlantico.add("Puerto Colombia");
		ciudadesAtlantico.add("Repelón");
		ciudadesAtlantico.add("Sabanagrande");
		ciudadesAtlantico.add("Sabanalarga");
		ciudadesAtlantico.add("Santa Lucía");
		ciudadesAtlantico.add("Santo Tomás");
		ciudadesAtlantico.add("Soledad");
		ciudadesAtlantico.add("Suán");
		ciudadesAtlantico.add("Tubará");
		ciudadesAtlantico.add("Usiacurí");
		
		ArrayList<String> ciudadesBolivar = new ArrayList<String>();
		ciudadesBolivar.add("Cartagena");
		ciudadesBolivar.add("Arenal");
		ciudadesBolivar.add("Arjona");
		ciudadesBolivar.add("Arroyohondo");
		ciudadesBolivar.add("Barranco de Loba");
		ciudadesBolivar.add("Brazuelo de Papayal");
		ciudadesBolivar.add("Calamar");
		ciudadesBolivar.add("Cantagallo");
		ciudadesBolivar.add("El Carmen de Bolívar");
		ciudadesBolivar.add("Cicuco");
		ciudadesBolivar.add("Clemencia");
		ciudadesBolivar.add("Córdoba");
		ciudadesBolivar.add("El Guamo");
		ciudadesBolivar.add("El Peñón");
		ciudadesBolivar.add("Hatillo de Loba");
		ciudadesBolivar.add("Magangué");
		ciudadesBolivar.add("Mahates");
		ciudadesBolivar.add("Margarita");
		ciudadesBolivar.add("María La Baja");
		ciudadesBolivar.add("Montecristo");
		ciudadesBolivar.add("Morales");
		ciudadesBolivar.add("Norosí");
		ciudadesBolivar.add("Pinillos");
		ciudadesBolivar.add("Regidor");
		ciudadesBolivar.add("Río Viejo");
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
		ciudadesBolivar.add("Simití");
		ciudadesBolivar.add("Soplaviento");
		ciudadesBolivar.add("Talaigua Nuevo");
		ciudadesBolivar.add("Tiquisio");
		ciudadesBolivar.add("Turbaco");
		ciudadesBolivar.add("Turbaná");
		ciudadesBolivar.add("Villanueva");
		ciudadesBolivar.add("Zambrano");
		
		ArrayList<String> ciudadesBoyaca = new ArrayList<String>();
		ciudadesBoyaca.add("Tunja");
		ciudadesBoyaca.add("Almeida");
		ciudadesBoyaca.add("Aquitania");
		ciudadesBoyaca.add("Arcabuco");
		ciudadesBoyaca.add("Belén");
		ciudadesBoyaca.add("Berbeo");
		ciudadesBoyaca.add("Beteitiva");
		ciudadesBoyaca.add("Boavita");
		ciudadesBoyaca.add("Boyacá");
		ciudadesBoyaca.add("Briseño");
		ciudadesBoyaca.add("Buenavista");
		ciudadesBoyaca.add("Busbanzá");
		ciudadesBoyaca.add("Caldas");
		ciudadesBoyaca.add("Campohermoso");
		ciudadesBoyaca.add("Cerinza");
		ciudadesBoyaca.add("Chinavita");
		ciudadesBoyaca.add("Chiquinquirá");
		ciudadesBoyaca.add("Chiscas");
		ciudadesBoyaca.add("Chita");
		ciudadesBoyaca.add("Chitaranque");
		ciudadesBoyaca.add("Chivatá");
		ciudadesBoyaca.add("Ciénaga");
		ciudadesBoyaca.add("Cómbita");
		ciudadesBoyaca.add("Coper");
		ciudadesBoyaca.add("Corrales");
		ciudadesBoyaca.add("Covarachia");
		ciudadesBoyaca.add("Cubar");
		ciudadesBoyaca.add("Cucaita");
		ciudadesBoyaca.add("Cuitiva");
		ciudadesBoyaca.add("Chíquiza");
		ciudadesBoyaca.add("Chivor");
		ciudadesBoyaca.add("Duitama");
		ciudadesBoyaca.add("El Cocuy");
		ciudadesBoyaca.add("El Espino");
		ciudadesBoyaca.add("Firavitoba");
		ciudadesBoyaca.add("Floresta");
		ciudadesBoyaca.add("Gachantivá");
		ciudadesBoyaca.add("Gámeza");
		ciudadesBoyaca.add("Garagoa");
		ciudadesBoyaca.add("Guacamayas");
		ciudadesBoyaca.add("Guateque");
		ciudadesBoyaca.add("Guayatá");
		ciudadesBoyaca.add("Guicán");
		ciudadesBoyaca.add("Iza");
		ciudadesBoyaca.add("Jenesano");
		ciudadesBoyaca.add("Jericó");
		ciudadesBoyaca.add("Labranzagrande");
		ciudadesBoyaca.add("La Capilla");
		ciudadesBoyaca.add("La Victoria");
		ciudadesBoyaca.add("La Ubita");
		ciudadesBoyaca.add("Villa de Leyva");
		ciudadesBoyaca.add("Macanal");
		ciudadesBoyaca.add("Maripí");
		ciudadesBoyaca.add("Miraflores");
		ciudadesBoyaca.add("Mongua");
		ciudadesBoyaca.add("Monguí");
		ciudadesBoyaca.add("Moniquirá");
		ciudadesBoyaca.add("Motavita");
		ciudadesBoyaca.add("Muzo");
		ciudadesBoyaca.add("Nobsa");
		ciudadesBoyaca.add("Nuevo Colón");
		ciudadesBoyaca.add("Oicatá");
		ciudadesBoyaca.add("Otanche");
		ciudadesBoyaca.add("Pachavita");
		ciudadesBoyaca.add("Páez");
		ciudadesBoyaca.add("Paipa");
		ciudadesBoyaca.add("Pajarito");
		ciudadesBoyaca.add("Panqueba");
		ciudadesBoyaca.add("Pauna");
		ciudadesBoyaca.add("Paya");
		ciudadesBoyaca.add("Paz de Río");
		ciudadesBoyaca.add("Pesca");
		ciudadesBoyaca.add("Pisva");
		ciudadesBoyaca.add("Puerto Boyacá");
		ciudadesBoyaca.add("Quípama");
		ciudadesBoyaca.add("Ramiquirí");
		ciudadesBoyaca.add("Ráquira");
		ciudadesBoyaca.add("Rondón");
		ciudadesBoyaca.add("Saboyá");
		ciudadesBoyaca.add("Sáchica");
		ciudadesBoyaca.add("Samacá");
		ciudadesBoyaca.add("San Eduardo");
		ciudadesBoyaca.add("San José de Pare");
		ciudadesBoyaca.add("San Luis de Gaceno");
		ciudadesBoyaca.add("San Mateo");
		ciudadesBoyaca.add("San Miguel de Sema");
		ciudadesBoyaca.add("San Pablo de Borbur");
		ciudadesBoyaca.add("Santana");
		ciudadesBoyaca.add("Santa María");
		ciudadesBoyaca.add("Santa Rosa de Viterbo");
		ciudadesBoyaca.add("Santa Sofía");
		ciudadesBoyaca.add("Sativanorte");
		ciudadesBoyaca.add("Sativasur");
		ciudadesBoyaca.add("Siachoque");
		ciudadesBoyaca.add("Soatá");
		ciudadesBoyaca.add("Socotá");
		ciudadesBoyaca.add("Socha");
		ciudadesBoyaca.add("Sogamoso");
		ciudadesBoyaca.add("Somondoco");
		ciudadesBoyaca.add("Sora");
		ciudadesBoyaca.add("Sotaquirá");
		ciudadesBoyaca.add("Soracá");
		ciudadesBoyaca.add("Susacón");
		ciudadesBoyaca.add("Sutamarchán");
		ciudadesBoyaca.add("Sutatenza");
		ciudadesBoyaca.add("Tasco");
		ciudadesBoyaca.add("Tenza");
		ciudadesBoyaca.add("Tibaná");
		ciudadesBoyaca.add("Tibasosa");
		ciudadesBoyaca.add("Tinjacá");
		ciudadesBoyaca.add("Tipacoque");
		ciudadesBoyaca.add("Toca");
		ciudadesBoyaca.add("Toguí");
		ciudadesBoyaca.add("Tópaga");
		ciudadesBoyaca.add("Tota");
		ciudadesBoyaca.add("Tunungua");
		ciudadesBoyaca.add("Turmequé");
		ciudadesBoyaca.add("Tuta");
		ciudadesBoyaca.add("Tutazá");
		ciudadesBoyaca.add("Úmbita");
		ciudadesBoyaca.add("Ventaquemada");
		ciudadesBoyaca.add("Viracachá");
		ciudadesBoyaca.add("Zetaquirá");
		
		ArrayList<String> ciudadesCaldas = new ArrayList<String>();
		ciudadesCaldas.add("Manizales");
		ciudadesCaldas.add("Aguadas");
		ciudadesCaldas.add("Anserma");
		ciudadesCaldas.add("Aranzazu");
		ciudadesCaldas.add("Belalcázar");
		ciudadesCaldas.add("Chinchina");
		ciudadesCaldas.add("Filadelfia");
		ciudadesCaldas.add("La Dorada");
		ciudadesCaldas.add("La Merced");
		ciudadesCaldas.add("Manzanares");
		ciudadesCaldas.add("Marmato");
		ciudadesCaldas.add("Marquetalia");
		ciudadesCaldas.add("Marulanda");
		ciudadesCaldas.add("Neira");
		ciudadesCaldas.add("Pácora");
		ciudadesCaldas.add("Palestina");
		ciudadesCaldas.add("Pensilvania");
		ciudadesCaldas.add("Riosucio");
		ciudadesCaldas.add("Risaralda");
		ciudadesCaldas.add("Salamina");
		ciudadesCaldas.add("Samaná");
		ciudadesCaldas.add("San José");
		ciudadesCaldas.add("Supía");
		ciudadesCaldas.add("Victoria");
		ciudadesCaldas.add("Villamaría");
		ciudadesCaldas.add("Viterbo");
		
		ArrayList<String> ciudadesCaqueta = new ArrayList<String>();
		ciudadesCaqueta.add("Florencia");
		ciudadesCaqueta.add("Albania");
		ciudadesCaqueta.add("Belén de los Andaquíes");
		ciudadesCaqueta.add("Cartagena del Chairá");
		ciudadesCaqueta.add("Curillo");
		ciudadesCaqueta.add("El Doncello");
		ciudadesCaqueta.add("El Paujil");
		ciudadesCaqueta.add("La Montañita");
		ciudadesCaqueta.add("Milán");
		ciudadesCaqueta.add("Morelia");
		ciudadesCaqueta.add("Puerto Rico");
		ciudadesCaqueta.add("San José del Fragua");
		ciudadesCaqueta.add("San Vicente del Caguán");
		ciudadesCaqueta.add("Solano");
		ciudadesCaqueta.add("Solita");
		ciudadesCaqueta.add("Valparaíso");
		
		ArrayList<String> ciudadesCasanare = new ArrayList<String>();
		ciudadesCasanare.add("Yopal");
		ciudadesCasanare.add("Aguazul");
		ciudadesCasanare.add("Chameza");
		ciudadesCasanare.add("Hato Corozal");
		ciudadesCasanare.add("La Salina");
		ciudadesCasanare.add("Maní");
		ciudadesCasanare.add("Monterrey");
		ciudadesCasanare.add("Nunchía");
		ciudadesCasanare.add("Orocué");
		ciudadesCasanare.add("Paz de Ariporo");
		ciudadesCasanare.add("Pore");
		ciudadesCasanare.add("Recetor");
		ciudadesCasanare.add("Sabalarga");
		ciudadesCasanare.add("Sácama");
		ciudadesCasanare.add("San Luis de Palenque");
		ciudadesCasanare.add("Támara");
		ciudadesCasanare.add("Florencia");
		ciudadesCasanare.add("Trinidad");
		ciudadesCasanare.add("Villanueva");
		
		ArrayList<String> ciudadesCauca = new ArrayList<String>();
		ciudadesCauca.add("Popayán");
		ciudadesCauca.add("Almaguer");
		ciudadesCauca.add("Argelia");
		ciudadesCauca.add("Balboa");
		ciudadesCauca.add("Bolívar");
		ciudadesCauca.add("Buenos Aires");
		ciudadesCauca.add("Cajibío");
		ciudadesCauca.add("Caldono");
		ciudadesCauca.add("Caloto");
		ciudadesCauca.add("Corinto");
		ciudadesCauca.add("El Tambo");
		ciudadesCauca.add("Florencia");
		ciudadesCauca.add("Guachené");
		ciudadesCauca.add("Guapi");
		ciudadesCauca.add("Inzá");
		ciudadesCauca.add("Jambaló");
		ciudadesCauca.add("La Sierra");
		ciudadesCauca.add("La Vega");
		ciudadesCauca.add("López (Micay)");
		ciudadesCauca.add("Mercaderes");
		ciudadesCauca.add("Miranda");
		ciudadesCauca.add("Morales");
		ciudadesCauca.add("Padilla");
		ciudadesCauca.add("Páez (Belalcazar)");
		ciudadesCauca.add("Patía (El Bordo)");
		ciudadesCauca.add("Piamonte");
		ciudadesCauca.add("Piendamó");
		ciudadesCauca.add("Puerto Tejada");
		ciudadesCauca.add("Puracé (Coconuco)");
		ciudadesCauca.add("Rosas");
		ciudadesCauca.add("San Sebastián");
		ciudadesCauca.add("Santander de Quilichao");
		ciudadesCauca.add("Santa Rosa");
		ciudadesCauca.add("Silvia");
		ciudadesCauca.add("Sotará (Paispamba)");
		ciudadesCauca.add("Suárez");
		ciudadesCauca.add("Sucre");
		ciudadesCauca.add("Timbío");
		ciudadesCauca.add("Timbiquí");
		ciudadesCauca.add("Toribío");
		ciudadesCauca.add("Totoró");
		ciudadesCauca.add("Villa Rica");

		
		ArrayList<String> ciudadesCesar = new ArrayList<String>();
		ciudadesCesar.add("Valledupar");
		ciudadesCesar.add("Aguachica");
		ciudadesCesar.add("Agustín Codazzi");
		ciudadesCesar.add("Astrea");
		ciudadesCesar.add("Becerril");
		ciudadesCesar.add("Bosconia");
		ciudadesCesar.add("Chimichagua");
		ciudadesCesar.add("Chiriguaná");
		ciudadesCesar.add("Curumaní");
		ciudadesCesar.add("El Copey");
		ciudadesCesar.add("El Paso");
		ciudadesCesar.add("Gamarra");
		ciudadesCesar.add("González");
		ciudadesCesar.add("La Gloria");
		ciudadesCesar.add("La Jagua de Ibirico");
		ciudadesCesar.add("Manaure Balcón Cesar");
		ciudadesCesar.add("Pailitas");
		ciudadesCesar.add("Pelaya");
		ciudadesCesar.add("Pueblo Bello");
		ciudadesCesar.add("Río de Oro");
		ciudadesCesar.add("La Paz (Robles)");
		ciudadesCesar.add("San Alberto");
		ciudadesCesar.add("San Diego");
		ciudadesCesar.add("San Martín");
		ciudadesCesar.add("Tamalameque");

		ArrayList<String> ciudadesChoco = new ArrayList<String>();
		ciudadesChoco.add("Quibdó");
		ciudadesChoco.add("Acandí");
		ciudadesChoco.add("Alto Baudó (Pie de Pato)");
		ciudadesChoco.add("Atrato (Yuto)");
		ciudadesChoco.add("Bagadó");
		ciudadesChoco.add("Bahía Solano (Mútis)");
		ciudadesChoco.add("Bajo Baudó (Pizarro)");
		ciudadesChoco.add("Bojayá (Bellavista)");
		ciudadesChoco.add("Cantón de San Pablo");
		ciudadesChoco.add("Cértegui");	
		ciudadesChoco.add("Condoto");
		ciudadesChoco.add("El Carmen");
		ciudadesChoco.add("El Litoral de San Juan");
		ciudadesChoco.add("Itsmina");
		ciudadesChoco.add("Juradó");
		ciudadesChoco.add("Lloró");
		ciudadesChoco.add("Medio Atrato");
		ciudadesChoco.add("Medio Baudó");
		ciudadesChoco.add("Medio San Juan");
		ciudadesChoco.add("Nóvita");
		ciudadesChoco.add("Nuquí");
		ciudadesChoco.add("Riosucio");
		ciudadesChoco.add("Rio Iró");
		ciudadesChoco.add("San José del Palmar");
		ciudadesChoco.add("Sipí");
		ciudadesChoco.add("Tadó");
		ciudadesChoco.add("Unguía");
		ciudadesChoco.add("Unión Panamericana");
		
		ArrayList<String> ciudadesCordoba = new ArrayList<String>();
		ciudadesCordoba.add("Montería");
		ciudadesCordoba.add("Ayapel");
		ciudadesCordoba.add("Buenavista");
		ciudadesCordoba.add("Canalete");
		ciudadesCordoba.add("Cereté");
		ciudadesCordoba.add("Chimá");
		ciudadesCordoba.add("Chinú");
		ciudadesCordoba.add("Ciénaga de Oro");
		ciudadesCordoba.add("Cotorra");
		ciudadesCordoba.add("La Apartada (Frontera)");
		ciudadesCordoba.add("Lorica");
		ciudadesCordoba.add("Los Córdobas");
		ciudadesCordoba.add("Momil");
		ciudadesCordoba.add("Montelíbano");
		ciudadesCordoba.add("Monitos");
		ciudadesCordoba.add("Planeta Rica");
		ciudadesCordoba.add("Pueblo Nuevo");
		ciudadesCordoba.add("Puerto Escondido");
		ciudadesCordoba.add("Puerto Libertador");
		ciudadesCordoba.add("Purísima");
		ciudadesCordoba.add("Sahagún");
		ciudadesCordoba.add("San Andrés Sotavento");
		ciudadesCordoba.add("San Antero");
		ciudadesCordoba.add("San Bernardo del Viento");
		ciudadesCordoba.add("San Carlos");
		ciudadesCordoba.add("San José de Uré");
		ciudadesCordoba.add("San Pelayo");
		ciudadesCordoba.add("Santa Crus de Lorica");
		ciudadesCordoba.add("Tierralta");
		ciudadesCordoba.add("Tuchin");
		ciudadesCordoba.add("Valencia");
		
		ArrayList<String> ciudadesCundinamarca = new ArrayList<String>();
		ciudadesCundinamarca.add("Bogota");
		ciudadesCundinamarca.add("Agua de Dios");
		ciudadesCundinamarca.add("Albán");
		ciudadesCundinamarca.add("Anapoima");
		ciudadesCundinamarca.add("Anolaima");
		ciudadesCundinamarca.add("Arbeláez");
		ciudadesCundinamarca.add("Beltrán");
		ciudadesCundinamarca.add("Bituima");
		ciudadesCundinamarca.add("Bojacá");
		ciudadesCundinamarca.add("Cabrera");
		ciudadesCundinamarca.add("Cachipay");
		ciudadesCundinamarca.add("Cajicá");
		ciudadesCundinamarca.add("Caparrapí");
		ciudadesCundinamarca.add("Cáqueza");
		ciudadesCundinamarca.add("Carmen de Carupa");
		ciudadesCundinamarca.add("Chaguaní");
		ciudadesCundinamarca.add("Chía");
		ciudadesCundinamarca.add("Chipaque");
		ciudadesCundinamarca.add("Choachí");
		ciudadesCundinamarca.add("Chocontá");
		ciudadesCundinamarca.add("Cogua");
		ciudadesCundinamarca.add("Cota");
		ciudadesCundinamarca.add("Cucunubá");
		ciudadesCundinamarca.add("El Colegio");
		ciudadesCundinamarca.add("El Peñón");
		ciudadesCundinamarca.add("El Rosal");
		ciudadesCundinamarca.add("Facatativá");
		ciudadesCundinamarca.add("Fómeque");
		ciudadesCundinamarca.add("Fosca ");
		ciudadesCundinamarca.add("Funza");
		ciudadesCundinamarca.add("Fúquene");
		ciudadesCundinamarca.add("Fusagasugá");
		ciudadesCundinamarca.add("Gachalá");
		ciudadesCundinamarca.add("Gachancipá");
		ciudadesCundinamarca.add("Gachetá");
		ciudadesCundinamarca.add("Gama");
		ciudadesCundinamarca.add("Girardot");
		ciudadesCundinamarca.add("Granada");
		ciudadesCundinamarca.add("Guachetá");
		ciudadesCundinamarca.add("Guaduas");
		ciudadesCundinamarca.add("Guasca");
		ciudadesCundinamarca.add("Guataquí");
		ciudadesCundinamarca.add("Guatavita");
		ciudadesCundinamarca.add("Guayabal de Síquima");
		ciudadesCundinamarca.add("Guayabetal");
		ciudadesCundinamarca.add("Gutiérrez");
		ciudadesCundinamarca.add("Jerusalén");
		ciudadesCundinamarca.add("Junín");
		ciudadesCundinamarca.add("La Calera");
		ciudadesCundinamarca.add("La Mesa");
		ciudadesCundinamarca.add("La Palma");
		ciudadesCundinamarca.add("La Peña");
		ciudadesCundinamarca.add("La Vega");
		ciudadesCundinamarca.add("Lenguazaque");
		ciudadesCundinamarca.add("Machetá");
		ciudadesCundinamarca.add("Madrid");
		ciudadesCundinamarca.add("Manta");
		ciudadesCundinamarca.add("Medina");
		ciudadesCundinamarca.add("Mosquera");
		ciudadesCundinamarca.add("Nariño");
		ciudadesCundinamarca.add("Nemocón");
		ciudadesCundinamarca.add("Nilo");
		ciudadesCundinamarca.add("Nimaima");
		ciudadesCundinamarca.add("Nocaima");
		ciudadesCundinamarca.add("Venecia (Ospina Pérez)");
		ciudadesCundinamarca.add("Pacho");
		ciudadesCundinamarca.add("Paime");
		ciudadesCundinamarca.add("Pandi");
		ciudadesCundinamarca.add("Paratebueno");
		ciudadesCundinamarca.add("Pasca");
		ciudadesCundinamarca.add("Puerto Salgar");
		ciudadesCundinamarca.add("Pulí");
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
		ciudadesCundinamarca.add("Sesquilé");
		ciudadesCundinamarca.add("Sibate");
		ciudadesCundinamarca.add("Silvania");
		ciudadesCundinamarca.add("Simijaca");
		ciudadesCundinamarca.add("Soacha");
		ciudadesCundinamarca.add("Sopó");
		ciudadesCundinamarca.add("Subachoque");
		ciudadesCundinamarca.add("Suesca");
		ciudadesCundinamarca.add("Supatá");
		ciudadesCundinamarca.add("Susa");
		ciudadesCundinamarca.add("Sutatausa");
		ciudadesCundinamarca.add("Tabio");
		ciudadesCundinamarca.add("Tausa");
		ciudadesCundinamarca.add("Tena");
		ciudadesCundinamarca.add("Tenjo");
		ciudadesCundinamarca.add("Tibacuy");
		ciudadesCundinamarca.add("Tibiritá");
		ciudadesCundinamarca.add("Tocaima");
		ciudadesCundinamarca.add("Tocancipá");
		ciudadesCundinamarca.add("Topaipí");
		ciudadesCundinamarca.add("Ubalá");
		ciudadesCundinamarca.add("Ubaque");
		ciudadesCundinamarca.add("Ubaté");
		ciudadesCundinamarca.add("Une");
		ciudadesCundinamarca.add("Útica");
		ciudadesCundinamarca.add("Vergara");
		ciudadesCundinamarca.add("Vianí");
		ciudadesCundinamarca.add("Villagómez");
		ciudadesCundinamarca.add("Villapinzón");
		ciudadesCundinamarca.add("Villeta");
		ciudadesCundinamarca.add("Viotá");
		ciudadesCundinamarca.add("Yacopí");
		ciudadesCundinamarca.add("Zipacón");
		ciudadesCundinamarca.add("Zipaquirá");		
		
		ArrayList<String> ciudadesGuainia = new ArrayList<String>();
		ciudadesGuainia.add("Inírida");
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
		ciudadesGuajira.add("Distracción");
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
		ciudadesGuaviare.add("San José del Guaviare");
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
		ciudadesHuila.add("Elías");
		ciudadesHuila.add("Garzón");
		ciudadesHuila.add("Gigante");
		ciudadesHuila.add("Guadalupe");
		ciudadesHuila.add("Hobo");
		ciudadesHuila.add("Iquira");
		ciudadesHuila.add("Isnos");
		ciudadesHuila.add("La Argentina");
		ciudadesHuila.add("La Plata");
		ciudadesHuila.add("Nátaga");
		ciudadesHuila.add("Oporapa");
		ciudadesHuila.add("Paicol");
		ciudadesHuila.add("Palermo");
		ciudadesHuila.add("Palestina");
		ciudadesHuila.add("Pital");
		ciudadesHuila.add("Pitalito");
		ciudadesHuila.add("Rivera");
		ciudadesHuila.add("Saladoblanco");
		ciudadesHuila.add("San Agustín");
		ciudadesHuila.add("Santa María");
		ciudadesHuila.add("Suazá");
		ciudadesHuila.add("Tarqui");
		ciudadesHuila.add("Tesalia");
		ciudadesHuila.add("Tello");
		ciudadesHuila.add("Teruel");
		ciudadesHuila.add("Timaná");
		ciudadesHuila.add("Villavieja");
		ciudadesHuila.add("Yaguará");
		
		ArrayList<String> ciudadesMagdalena = new ArrayList<String>();
		ciudadesMagdalena.add("Santa Marta");
		ciudadesMagdalena.add("Algarrobo");
		ciudadesMagdalena.add("Aracataca");
		ciudadesMagdalena.add("Ariguaní (El Difícil)");
		ciudadesMagdalena.add("Cerro San Antonio");
		ciudadesMagdalena.add("Chivolo");
		ciudadesMagdalena.add("Ciénaga");
		ciudadesMagdalena.add("Concordia");
		ciudadesMagdalena.add("El Banco");
		ciudadesMagdalena.add("El Piñón");
		ciudadesMagdalena.add("El Retén");
		ciudadesMagdalena.add("Fundación");
		ciudadesMagdalena.add("Guamal");
		ciudadesMagdalena.add("Nueva Granada");
		ciudadesMagdalena.add("Pedraza");
		ciudadesMagdalena.add("Pijiño del Carmen");
		ciudadesMagdalena.add("Pivijay");
		ciudadesMagdalena.add("Plato");
		ciudadesMagdalena.add("Publoviejo");
		ciudadesMagdalena.add("Remolino");
		ciudadesMagdalena.add("Sabanas de San Angel");
		ciudadesMagdalena.add("Salamina");
		ciudadesMagdalena.add("San Sebastián de Buenavista");
		ciudadesMagdalena.add("San Zenón");
		ciudadesMagdalena.add("Santa Ana");
		ciudadesMagdalena.add("Santa Bárbara de Pinto");
		ciudadesMagdalena.add("Sitionuevo");
		ciudadesMagdalena.add("Tenerife");
		ciudadesMagdalena.add("Zapayán");
		ciudadesMagdalena.add("Zona Bananera");
		
		ArrayList<String> ciudadesMeta = new ArrayList<String>();
		ciudadesMeta.add("Villavicencio");
		ciudadesMeta.add("Acacias");
		ciudadesMeta.add("Barranca de Upía");
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
		ciudadesMeta.add("Mapiripán");
		ciudadesMeta.add("Mesetas");
		ciudadesMeta.add("La Macarena");
		ciudadesMeta.add("La Uribe");
		ciudadesMeta.add("Lejanías");
		ciudadesMeta.add("Puerto Concordia");
		ciudadesMeta.add("Puerto Gaitán");
		ciudadesMeta.add("Puerto López");
		ciudadesMeta.add("Puerto Lleras");
		ciudadesMeta.add("Puerto Rico");
		ciudadesMeta.add("Restrepo");
		ciudadesMeta.add("San Carlos de Guaroa");
		ciudadesMeta.add("San Juan de Arama");
		ciudadesMeta.add("San Juanito");
		ciudadesMeta.add("San Martín");
		ciudadesMeta.add("Vistahermosa");
		
		ArrayList<String> ciudadesNarino = new ArrayList<String>();
		ciudadesNarino.add("Pasto");
		ciudadesNarino.add("Albán (San José)");
		ciudadesNarino.add("Aldana");
		ciudadesNarino.add("Ancuyá");
		ciudadesNarino.add("Arboleda (Berruecos)");
		ciudadesNarino.add("Barbacoas");
		ciudadesNarino.add("Belén");
		ciudadesNarino.add("Buesaco");
		ciudadesNarino.add("Colón (Génova)");
		ciudadesNarino.add("Consacá");
		ciudadesNarino.add("Contadero");
		ciudadesNarino.add("Córdoba");
		ciudadesNarino.add("Cuaspud (Carlosama)");
		ciudadesNarino.add("Cumbal");
		ciudadesNarino.add("Cumbitará");
		ciudadesNarino.add("Chachagüi");
		ciudadesNarino.add("El Charco");
		ciudadesNarino.add("El Rosario");
		ciudadesNarino.add("El Tablón");
		ciudadesNarino.add("El Tambo");
		ciudadesNarino.add("Funes");
		ciudadesNarino.add("Guachucal");
		ciudadesNarino.add("Guaitarilla");
		ciudadesNarino.add("Gualmatán");
		ciudadesNarino.add("Iles");
		ciudadesNarino.add("Imúes");
		ciudadesNarino.add("Ipiales");
		ciudadesNarino.add("La Cruz");
		ciudadesNarino.add("La Florida");
		ciudadesNarino.add("La Llanada");
		ciudadesNarino.add("La Tola");
		ciudadesNarino.add("La Unión");
		ciudadesNarino.add("Leiva");
		ciudadesNarino.add("Linares");
		ciudadesNarino.add("Los Andes (Sotomayor)");
		ciudadesNarino.add("Magüí (Payán)");
		ciudadesNarino.add("Mallama (Piedrancha)");
		ciudadesNarino.add("Mosquera");
		ciudadesNarino.add("Olaya");
		ciudadesNarino.add("Ospina");
		ciudadesNarino.add("Francisco Pizarro");
		ciudadesNarino.add("Policarpa");
		ciudadesNarino.add("Potosí");
		ciudadesNarino.add("Providencia");
		ciudadesNarino.add("Puerres");
		ciudadesNarino.add("Pupiales");
		ciudadesNarino.add("Ricaurte");
		ciudadesNarino.add("Roberto Payán (San José)");
		ciudadesNarino.add("Samaniego");
		ciudadesNarino.add("Sandoná");
		ciudadesNarino.add("San Bernardo");
		ciudadesNarino.add("San Lorenzo");
		ciudadesNarino.add("San Pablo");
		ciudadesNarino.add("San Pedro de Cartago");
		ciudadesNarino.add("Santa Bárbara (Iscuandé)");
		ciudadesNarino.add("Santa Cruz (Guachávez)");
		ciudadesNarino.add("Sapuyés");
		ciudadesNarino.add("Taminango");
		ciudadesNarino.add("Tangua");
		ciudadesNarino.add("Tumaco");
		ciudadesNarino.add("Túquerres");
		ciudadesNarino.add("Yacuanquer");
		
		ArrayList<String> ciudadesNorteSantander  = new ArrayList<String>();
		ciudadesNorteSantander.add("Cúcuta");
		ciudadesNorteSantander.add("Abrego");
		ciudadesNorteSantander.add("Arboledas");
		ciudadesNorteSantander.add("Bochalema");
		ciudadesNorteSantander.add("Bucarasica");
		ciudadesNorteSantander.add("Cácota");
		ciudadesNorteSantander.add("Cáchira");
		ciudadesNorteSantander.add("Chinácota");
		ciudadesNorteSantander.add("Chitagá");
		ciudadesNorteSantander.add("Convención");
		ciudadesNorteSantander.add("Cucutilla");
		ciudadesNorteSantander.add("Durania");
		ciudadesNorteSantander.add("El Carmen");
		ciudadesNorteSantander.add("El Tarra");
		ciudadesNorteSantander.add("El Zulia");
		ciudadesNorteSantander.add("Gramalote");
		ciudadesNorteSantander.add("Hacarí");
		ciudadesNorteSantander.add("Herrán");
		ciudadesNorteSantander.add("Labateca");
		ciudadesNorteSantander.add("La Esperanza");
		ciudadesNorteSantander.add("La Playa");
		ciudadesNorteSantander.add("Los Patios");
		ciudadesNorteSantander.add("Lourdes");
		ciudadesNorteSantander.add("Mutiscua");
		ciudadesNorteSantander.add("Ocaña");
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
		ciudadesNorteSantander.add("Tibú");
		ciudadesNorteSantander.add("Toledo");
		ciudadesNorteSantander.add("Villacaro");
		ciudadesNorteSantander.add("Villa del Rosario");
		
		ArrayList<String> ciudadesPutumayo = new ArrayList<String>();
		ciudadesPutumayo.add("Mocoa");
		ciudadesPutumayo.add("Colón");
		ciudadesPutumayo.add("Orito");
		ciudadesPutumayo.add("Puerto Asís");
		ciudadesPutumayo.add("Puerto Caicedo");
		ciudadesPutumayo.add("Puerto Guzmán");
		ciudadesPutumayo.add("Puerto Leguízamo");
		ciudadesPutumayo.add("Sibundoy");
		ciudadesPutumayo.add("San Francisco");
		ciudadesPutumayo.add("San Miguel");
		ciudadesPutumayo.add("Santiago");
		ciudadesPutumayo.add("Villa Gamuez (La Hormiga)");
		ciudadesPutumayo.add("Villa Garzón");
		
		ArrayList<String> ciudadesQuindio = new ArrayList<String>();
		ciudadesQuindio.add("Armenia");
		ciudadesQuindio.add("Buenavista");
		ciudadesQuindio.add("Calarcá");
		ciudadesQuindio.add("Circasia");
		ciudadesQuindio.add("Córdoba");
		ciudadesQuindio.add("Filandia");
		ciudadesQuindio.add("Génova");
		ciudadesQuindio.add("La Tebaida");
		ciudadesQuindio.add("Montenegro");
		ciudadesQuindio.add("Pijao");
		ciudadesQuindio.add("Quimbaya");
		ciudadesQuindio.add("Salento");
		
		ArrayList<String> ciudadesRisaralda = new ArrayList<String>();
		ciudadesRisaralda.add("Pereira");
		ciudadesRisaralda.add("Apía");
		ciudadesRisaralda.add("Balboa");
		ciudadesRisaralda.add("Belén de Umbría");
		ciudadesRisaralda.add("Dos Quebradas");
		ciudadesRisaralda.add("Guática");
		ciudadesRisaralda.add("La Celia");
		ciudadesRisaralda.add("La Virginia");
		ciudadesRisaralda.add("Marsella");
		ciudadesRisaralda.add("Mistrató");
		ciudadesRisaralda.add("Pueblo Rico");
		ciudadesRisaralda.add("Quinchia");
		ciudadesRisaralda.add("Santa Rosa de Cabal");
		ciudadesRisaralda.add("Santuario");
		
		ArrayList<String> ciudadesSanAndres = new ArrayList<String>();
		ciudadesSanAndres.add("San Andrés");
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
		ciudadesSantander.add("Bolívar");
		ciudadesSantander.add("Cabrera");
		ciudadesSantander.add("California");
		ciudadesSantander.add("Capitanejo");
		ciudadesSantander.add("Carcasí");
		ciudadesSantander.add("Cepitá");
		ciudadesSantander.add("Cerrito");
		ciudadesSantander.add("Charalá");
		ciudadesSantander.add("Charta");
		ciudadesSantander.add("Chima");
		ciudadesSantander.add("Chipatá");
		ciudadesSantander.add("Cimitarra");
		ciudadesSantander.add("Concepción");
		ciudadesSantander.add("Confines");
		ciudadesSantander.add("Contratación");
		ciudadesSantander.add("Coromoro");
		ciudadesSantander.add("Curití");
		ciudadesSantander.add("El Carmen");
		ciudadesSantander.add("El Guacamayo");
		ciudadesSantander.add("El Peñón");
		ciudadesSantander.add("El Playón");
		ciudadesSantander.add("Encino");
		ciudadesSantander.add("Enciso");
		ciudadesSantander.add("Florián");
		ciudadesSantander.add("Floridablanca");
		ciudadesSantander.add("Galán");
		ciudadesSantander.add("Gámbita");
		ciudadesSantander.add("Girón");
		ciudadesSantander.add("Guaca");
		ciudadesSantander.add("Guadalupe");
		ciudadesSantander.add("Guapotá");
		ciudadesSantander.add("Guavata");
		ciudadesSantander.add("Guepsa");
		ciudadesSantander.add("Hato");
		ciudadesSantander.add("Jesús María");
		ciudadesSantander.add("Jordán");
		ciudadesSantander.add("La Belleza");
		ciudadesSantander.add("Landázuri");
		ciudadesSantander.add("La Paz");
		ciudadesSantander.add("Lebrija");
		ciudadesSantander.add("Los Santos");
		ciudadesSantander.add("Macaravita");
		ciudadesSantander.add("Málaga");
		ciudadesSantander.add("Matanza");
		ciudadesSantander.add("Mogotes");
		ciudadesSantander.add("Molagavita");
		ciudadesSantander.add("Ocamonte");
		ciudadesSantander.add("Oiba");
		ciudadesSantander.add("Onzága");
		ciudadesSantander.add("Palmar");
		ciudadesSantander.add("Palmas del Socorro");
		ciudadesSantander.add("Páramo");
		ciudadesSantander.add("Pie de Cuesta");
		ciudadesSantander.add("Pinchote");
		ciudadesSantander.add("Puente Nacional");
		ciudadesSantander.add("Puerto Parra");
		ciudadesSantander.add("Puerto Wilches");
		ciudadesSantander.add("Rionegro");
		ciudadesSantander.add("Sabana de Torres");
		ciudadesSantander.add("San Andrés");
		ciudadesSantander.add("San Benito");
		ciudadesSantander.add("San Gil");
		ciudadesSantander.add("San Joaquín");
		ciudadesSantander.add("San José de Miranda");
		ciudadesSantander.add("San Miguel");
		ciudadesSantander.add("San Vicente de Chucurí");
		ciudadesSantander.add("Santa Bárbara");
		ciudadesSantander.add("Santa Helena del Opón");
		ciudadesSantander.add("Simacota");
		ciudadesSantander.add("Socorro");
		ciudadesSantander.add("Suaita");
		ciudadesSantander.add("Sucre");
		ciudadesSantander.add("Suratá");
		ciudadesSantander.add("Tona");
		ciudadesSantander.add("Valle de San José");
		ciudadesSantander.add("Vélez");
		ciudadesSantander.add("Vetas");
		ciudadesSantander.add("Villanueva");
		ciudadesSantander.add("Zapatoca");
		
		ArrayList<String> ciudadesSucre = new ArrayList<String>();
		ciudadesSucre.add("Sincelejo");
		ciudadesSucre.add("Buenavista");
		ciudadesSucre.add("Caimito");
		ciudadesSucre.add("Coloso (Ricaurte)");
		ciudadesSucre.add("Corozal");
		ciudadesSucre.add("Chalán");
		ciudadesSucre.add("El Roble");
		ciudadesSucre.add("Galeras (Nueva Granada)");
		ciudadesSucre.add("Guarandá");
		ciudadesSucre.add("La Unión");
		ciudadesSucre.add("Los Palmitos");
		ciudadesSucre.add("Majagual");
		ciudadesSucre.add("Morroa");
		ciudadesSucre.add("Ovejas");
		ciudadesSucre.add("Palmito");
		ciudadesSucre.add("Sampués");
		ciudadesSucre.add("San Benito Abad");
		ciudadesSucre.add("San Juan de Betulia");
		ciudadesSucre.add("San Marcos");
		ciudadesSucre.add("San Onofre");
		ciudadesSucre.add("San Pedro");
		ciudadesSucre.add("Santiago de Tolú");
		ciudadesSucre.add("Sincé");
		ciudadesSucre.add("Sucre");
		ciudadesSucre.add("Tolú");
		ciudadesSucre.add("Toluviejo");
		
		ArrayList<String> ciudadesTolima = new ArrayList<String>();
		ciudadesTolima.add("Ibagué");
		ciudadesTolima.add("Alpujarra");
		ciudadesTolima.add("Alvarado");
		ciudadesTolima.add("Ambalema");
		ciudadesTolima.add("Anzóategui");
		ciudadesTolima.add("Armero (Guayabal)");
		ciudadesTolima.add("Ataco");
		ciudadesTolima.add("Cajamarca");
		ciudadesTolima.add("Carmen de Apicalá");
		ciudadesTolima.add("Casabianca");
		ciudadesTolima.add("Chaparral");
		ciudadesTolima.add("Coello");
		ciudadesTolima.add("Coyaima");
		ciudadesTolima.add("Cunday");
		ciudadesTolima.add("Dolores");
		ciudadesTolima.add("Espinal");
		ciudadesTolima.add("Falán");
		ciudadesTolima.add("Flandes");
		ciudadesTolima.add("Fresno");
		ciudadesTolima.add("Guamo");
		ciudadesTolima.add("Herveo");
		ciudadesTolima.add("Honda");
		ciudadesTolima.add("Icononzo");
		ciudadesTolima.add("Lérida");
		ciudadesTolima.add("Líbano");
		ciudadesTolima.add("Mariquita");
		ciudadesTolima.add("Melgar");
		ciudadesTolima.add("Murillo");
		ciudadesTolima.add("Natagaima");
		ciudadesTolima.add("Ortega");
		ciudadesTolima.add("Palocabildo");
		ciudadesTolima.add("Piedras");
		ciudadesTolima.add("Planadas");
		ciudadesTolima.add("Prado");
		ciudadesTolima.add("Purificación");
		ciudadesTolima.add("Rioblanco");
		ciudadesTolima.add("Roncesvalles");
		ciudadesTolima.add("Rovira");
		ciudadesTolima.add("Saldaña");
		ciudadesTolima.add("San Antonio");
		ciudadesTolima.add("San Luis");
		ciudadesTolima.add("Santa Isabel");
		ciudadesTolima.add("Suárez");
		ciudadesTolima.add("Valle de San Juan");
		ciudadesTolima.add("Venadillo");
		ciudadesTolima.add("Villahermosa");
		ciudadesTolima.add("Villarrica");
		
		ArrayList<String> ciudadesValle = new ArrayList<String>();
		ciudadesValle.add("Cali");
		ciudadesValle.add("Alcalá");
		ciudadesValle.add("Andalucía");
		ciudadesValle.add("Ansermanuevo");
		ciudadesValle.add("Argelia");
		ciudadesValle.add("Bolívar");
		ciudadesValle.add("Buenaventura");
		ciudadesValle.add("Buga");
		ciudadesValle.add("Bugalagrande");
		ciudadesValle.add("Caicedonia");
		ciudadesValle.add("Calima (Darién)");
		ciudadesValle.add("Candelaria");
		ciudadesValle.add("Cartago");
		ciudadesValle.add("Dagua");
		ciudadesValle.add("El Águila");
		ciudadesValle.add("El Cairo");
		ciudadesValle.add("El Cerrito");
		ciudadesValle.add("El Dovio");
		ciudadesValle.add("Florida");
		ciudadesValle.add("Ginebra");
		ciudadesValle.add("Guacarí");
		ciudadesValle.add("Jamundí");
		ciudadesValle.add("La Cumbre");
		ciudadesValle.add("La Unión");
		ciudadesValle.add("La Victoria");
		ciudadesValle.add("Obando");
		ciudadesValle.add("Palmira");
		ciudadesValle.add("Pradera");
		ciudadesValle.add("Restrepo");
		ciudadesValle.add("Riofrío");
		ciudadesValle.add("Roldanillo");
		ciudadesValle.add("San Pedro");
		ciudadesValle.add("Sevilla");
		ciudadesValle.add("Toro");
		ciudadesValle.add("Trujillo");
		ciudadesValle.add("Tuluá");
		ciudadesValle.add("Ulloa");
		ciudadesValle.add("Versalles");
		ciudadesValle.add("Vijes");
		ciudadesValle.add("Yotoco");
		ciudadesValle.add("Yumbo");
		ciudadesValle.add("Zarzal");
		
		ArrayList<String> ciudadesVaupes = new ArrayList<String>();
		ciudadesVaupes.add("Mitú");
		ciudadesVaupes.add("Carurú");
		ciudadesVaupes.add("Pacoa");
		ciudadesVaupes.add("Papunaua");
		ciudadesVaupes.add("Taraira");
		ciudadesVaupes.add("Yavaraté");
		
		ArrayList<String> ciudadesVichada = new ArrayList<String>();
		ciudadesVichada.add("Puerto Carreño");
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
