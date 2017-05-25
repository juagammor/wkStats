package com.juanra.wk.stats.model;

import java.util.Scanner;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Clase que gestiona el mapeo o transformacion de una linea tr de la pagina web
 * leida a un VO java capaz de ser tratado para su grabación
 * @author juanra
 *
 */
public class EstadisticaFactory {

	/**
	 * 
	 * @param row Element leido por Jsoup que debemos modelar
	 * @param datosPartido Objeto Estadistica con los datos comunes de un partido
	 * @return un objeto estadistica con todos los datos completos
	 */
	public static Estadistica parse(Element row, Estadistica datosPartido) {
		Elements tds = row.select("td");
		//Si tiene menos de 22 posiciones es una linea con totales, cabeceras, otros datos o 
		//nombre de equipo con tanteo
		if (tds.size() < 22) {
			Estadistica equipo = new Estadistica();
			equipo.setNombre(tds.get(0).text());
			return equipo;
		} else if (tds.size() >= 22) {
			//Con mas de 22 debemos encapsular en un try-catch
			//para ignorar las cabeceras que detallan los datos de las
			//estadisticas
			try {
				
				Estadistica est = new Estadistica();
				
				//Datos comunes de partido
				est.setJornada(datosPartido.getJornada());
				est.setFecha(datosPartido.getFecha());
				est.setTemporada(datosPartido.getTemporada());
				est.setRival(datosPartido.getRival());

				//Transformacion dato a dato desde el tr hasta dato concreto del objeto 
				//Estadistica
				est.setDorsal(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_DORSAL)));
				est.setNombre(tratarTDText(tds.get(EstadisticasConstants.INDEX_NOMBRE)));
				Element elementoClave = tds.get(EstadisticasConstants.INDEX_NOMBRE);
				est.setClaveJugador(elementoClave.select("a").get(0).attr("href").replace("/jugador.php?id=", ""));
				est.setMinutos(tratarTDMinutos(tds.get(EstadisticasConstants.INDEX_MINUTOS)));
				est.setPuntos(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_PUNTOS)));
				
				EstadisticaCompuesta t2 = tratarTDCompuesta(tds.get(EstadisticasConstants.INDEX_T2), "/");
				est.setT2Conv(t2.getA());
				est.setT2Total(t2.getB());
				est.setT2Porc(0.0);
				
				EstadisticaCompuesta t3 = tratarTDCompuesta(tds.get(EstadisticasConstants.INDEX_T3), "/");
				est.setT3Conv(t3.getA());
				est.setT3Total(t3.getB());
				est.setT3Porc(0.0);
				
				EstadisticaCompuesta t1 = tratarTDCompuesta(tds.get(EstadisticasConstants.INDEX_T1), "/");
				est.setT1Conv(t1.getA());
				est.setT1Total(t1.getB());
				est.setT1Porc(0.0);
				
				est.setRebotesTotales(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_REBOTES)));
				EstadisticaCompuesta rebotesDO = tratarTDCompuesta(tds.get(EstadisticasConstants.INDEX_REBOTES_DO), "\\+");
				est.setRebotesDef(rebotesDO.getA());
				est.setRebotesOf(rebotesDO.getB());
				
				est.setAsistencias(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_ASISTENCIAS)));
				est.setRecuperaciones(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_RECUPERACIONES)));
				est.setContraataques(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_CONTRAATAQUE)));
				
				est.setTaponesFavor(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_TAPON_FAVOR)));
				est.setTaponesContra(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_TAPON_CONTRA)));
				
				est.setMates(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_MATES)));
				
				est.setFaltas(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_FALTAS_COMETIDAS)));
				est.setFaltasRecibidas(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_FALTAS_RECIBIDAS)));
				
				//Si la linea tiene 23 columnas es que existe la estadistica +/- y tenemos que ponerla
				if(tds.size() == 23) {
					est.setMasmenos(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_MAS_MENOS)));
					est.setValoracion(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_VALORACION)));
				} else {
					//En otro caso, no tenemos que especificarla y el dato de la columna 22 es la valoracion
					est.setValoracion(tratarTDNumeric(tds.get(EstadisticasConstants.INDEX_MAS_MENOS)));
				}

				return est;
			} catch (Exception ex) {
				//ex.printStackTrace();
			}
		} else {
			//Ni longitud 22 o 23 
		}
		

		return null;
	}

	/**
	 * Mapeo del dato de Tiempo de Juego a un objeto TiempoJuego
	 * @param element
	 * @return
	 */
	private static TiempoJuego tratarTDMinutos(Element element) {
		if (element.text() == null 
				|| element.text().trim().equalsIgnoreCase("")) {
			return new TiempoJuego(0, 0);
		} else {
			try {
				Scanner sc = new Scanner(element.text().trim());
				sc.useDelimiter(":");
				TiempoJuego tj = new TiempoJuego(sc.nextInt(), sc.nextInt());
				sc.close();
				return tj;
			}	catch (Exception ex) {
				return new TiempoJuego(0,0);
			}
		}
	}
	
	/**
	 * Mapeo de un dato compuesto a EstadisticaCompuesta definiendo el delimitador
	 * @param element
	 * @param delimiter
	 * @return
	 */
	private static EstadisticaCompuesta tratarTDCompuesta(Element element, String delimiter) {
		if (element.text() == null || element.text().trim().equalsIgnoreCase("") ) {
			return new EstadisticaCompuesta(0, 0);
		} else {
			try {
				Scanner sc = new Scanner(element.text().trim());
				sc.useDelimiter(delimiter);
				EstadisticaCompuesta ec = new EstadisticaCompuesta(sc.nextInt(), sc.nextInt());
				sc.close();
				return ec;
			} catch (Exception ex) {
				return new EstadisticaCompuesta(0, 0);
			}
		}
	}

	/**
	 * Mapeo de un dato a entero
	 * @param element
	 * @return
	 */
	private static int tratarTDNumeric(Element element) {
		try {
			return element.text() == null || element.text().trim().equalsIgnoreCase("") 
					? 0
					: new Integer(element.text().trim());
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * Mapeo de un dato a String
	 * @param element
	 * @return
	 */
	private static String tratarTDText(Element element) {
		try {
			return element.text() == null || element.text().trim().equalsIgnoreCase("") 
					? ""  
				    : element.text().trim();
		} catch (Exception ex) {
			return "";
		}
	}
	
}
