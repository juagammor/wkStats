package com.juanra.wk.stats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.juanra.wk.stats.model.Estadistica;
import com.juanra.wk.stats.model.EstadisticaFactory;
import com.juanra.wk.stats.model.EstadisticasConstants;
import com.juanra.wk.stats.service.EstadisticaService;

public class EstadisticasPartido {

	/**
	 * 
	 * @param uri
	 * @param temporada
	 */
	public static void procesarPartido(String uri, String temporada) {
		try {
			
			String url = uri;
			//Obtenemos todo el documento
			Document d = Jsoup.connect(url).get();

			//Obtenemos los td que contienen los nombres y marcados de los equipos participantes
			Elements classesEquipos = d.getElementsByAttributeValue("class", "estverdel");
			
			//De los dos elementos que obtenemos obtenemos el primero
			String equipo1 = getEquipo(classesEquipos.get(0).text());
			//De los dos elementos que obtenemos obtenemos el segundo
			String equipo2 = getEquipo(classesEquipos.get(1).text());
			//Definimos el rival, para posteriormente ignorar las estadisticas
			//que no nos interesen
			String rival = !EstadisticasConstants.isCaja(equipo1) 
					? equipo1 
				    : !EstadisticasConstants.isCaja(equipo2)
				    ? equipo2
				    : "WTF!";
			
			//Obtenemos las tablas de la pagina que contienen las estadisticas
			Elements classesEst = d.getElementsByAttributeValue("class", "estadisticas");
			if (classesEst.size() == 0) {
				//Si no hay es que estamos en un tipo de estadistica de nuevo formato (con mas menos)
				classesEst = d.getElementsByAttributeValue("class", "estadisticasnew");
			}
			//Si sigue sin haber salimos.
			if (classesEst.size() != 2) {
				return;
			} else {
				//Datos generales del partido (Se encuentran en la primera parte de estadisticas
				Element tablaDatosPartidos = classesEst.get(0);
				Elements tds = tablaDatosPartidos.select("td");
				Element datosPartidos = tds.get(0);
				Scanner sc = new Scanner(datosPartidos.text().replaceAll("&nbsp;", " ")); 
				sc.useDelimiter("\\|");
				
				String jornada = sc.next();
				String fechaPartido = sc.next();
				sc.close();
				
				//Una vez obtenidos los datos comunes, creamos un objeto de 
				//Estadistica para recopilar dichos datos
				Estadistica datosPartido = new Estadistica();
				String jornadaSt = jornada.replaceAll("J", "").substring(2).trim();
				datosPartido.setJornada(new Integer(jornadaSt));
				datosPartido.setFecha(fechaPartido.trim());
				datosPartido.setRival(rival);
				datosPartido.setTemporada(temporada);
				
				
				// Me interesa solo la segunda tabla para estadisticas y obtenemos las 'rows'
				// de la tabla
				Element tablaEstadisticas = classesEst.get(1);
				Elements trs = tablaEstadisticas.select("tr");

				List<Object> listadoEstadisticas = new ArrayList<Object>();
				//Recorremos todas las filas y las tratamos para convertirlas en 
				//un Objeto Estadistica. Si fuera null, queda descartada
				for (Element tr : trs) {
					Estadistica est = tratarTR(tr, datosPartido);
					if (est != null) {
						listadoEstadisticas.add(tratarTR(tr, datosPartido));
					}
				}

				
				//Recorremos las estadisticas para grabarlas omitiendo las que no sean del cdb sevilla
				boolean ignore = true;
				for (Iterator<Object> iterator = listadoEstadisticas.iterator(); iterator.hasNext();) {
					Estadistica stat = (Estadistica) iterator.next();
					//Comprobamos si corresponde a una Estadistica con el nombre del equipo exclusivamente
					if (stat.getNombre().startsWith(equipo1) || stat.getNombre().startsWith(equipo2)) {
						//Si es el equipo rival, ignoramos
						if (stat.getNombre().startsWith(rival)) {
							ignore = true;
						} else {
							ignore = false;
						}
					} else {
						//Si es una Estadistica normal y no ignoramos, grabamos donde este configurado
						if (!ignore) {
							//GRABAR
							//Nos aseguramos que el objeto tenga clave de jugador para grabar
							if (stat.getClaveJugador() != null) {
								//System.out.println(stat.getBasicDBObject());
								new EstadisticaService().grabar(stat);
							}
						}
					}
					
				}
			}

		} catch (Exception ex) {
			//ex.printStackTrace();
		}
	}

	/**
	 * Método que obtiene el nombre del equipo eliminando el tanteo.
	 * Cuando el constructor de entero provoque excepción no incluimos
	 * ese token en el resultado siempre y cuando sea el ultimo token
	 * (P. ej. XACOBEO 99 OURENSE daba problemas)
	 * @param text
	 * @return
	 */
	private static String getEquipo(String text) {
		Scanner sc1 = new Scanner(text);

		String equipo = "";
		while (sc1.hasNext()) {
			String next = sc1.next().trim();
			try{
				new Integer(next);
				if (sc1.hasNext()) {
					equipo += next + " " ;
				}
			} catch (Exception ex) {
				equipo += next + " ";
			}
		}
		sc1.close();
		return equipo;
	}

	/**
	 * 
	 * @param tr
	 * @param datosPartido
	 * @return
	 */
	private static Estadistica tratarTR(Element tr, Estadistica datosPartido) {
		return EstadisticaFactory.parse(tr, datosPartido);
		
		
	}

}
