package com.juanra.wk.stats;

import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.juanra.wk.stats.model.EstadisticasConstants;

public class EstadisticasTemporada {
	
	private String codigoEquipo;
	private int jornada1;
	private int jornada2;
	private int codigoEdicion;
	private String codigoCompeticion;
	
	/**
	 * Constructor, donde definimos todos los parametros
	 * @param codigoEquipo
	 * @param jornada1
	 * @param jornada2
	 * @param codigoEdicion
	 * @param codigoCompeticion
	 */
	public EstadisticasTemporada(String codigoEquipo,
			int jornada1,
			int jornada2,
			int codigoEdicion,
			String codigoCompeticion) {
		this.codigoEquipo = codigoEquipo;
		this.jornada1 = jornada1;
		this.jornada2 = jornada2;
		this.codigoEdicion = codigoEdicion;
		this.codigoCompeticion = codigoCompeticion;
	}
	
	/**
	 * Metodo de procesamiento de una temporada acorde a los paramteros definidos
	 */
	public void procesarTemporada() {
		try {
			//Entrada
			//Montamos la url correspondiente a esa temporada para obtener el 
			//listado de partidos reflejados en la pagina de la ACB
			String urlTemporada = montarURL();
			//Obtenemos el texto de la temporada. P. ej. '2015-2016'
			String temporada = EstadisticasConstants.temporadas.get(new Integer(this.codigoEdicion).toString());
		    
			//Obtener los codigos de partido td con class="negro2"
			Document temporadaDoc = Jsoup.connect(urlTemporada).get();
			Elements enlaces = temporadaDoc.getElementsByAttributeValue("class", "negro2");
			//Para cada enlace obtenido tratamos dicho enlace en busca del parametro codigo partido  
			for (Element element : enlaces) {
				//Buscamos el atributo href de las etiquetas a
				String urlpartidoAntiguo = element.select("a").get(0).attr("href");
				//Con la clase scanner recorremos los parametros separados por '&'
				Scanner sc = new Scanner(urlpartidoAntiguo);
				sc.useDelimiter("&");
				int numPartido = 0;
				//Recorremos dicho scanner en busca de 'partido'
				while(sc.hasNext()) {
					String param = sc.next();
					if (param.startsWith("partido")) {
						//Cuando lo encontramos lo guardamos y salimos del bucle
						numPartido = new Integer(param.replaceAll("partido=", "").trim());
						break;
					}
				}
				sc.close();
				//montamos la url del partido y llamamos al procedimiento de grabar esos datos
				//El formato de la URL es http://www.acb.com/fichas/ + LACB (codigo Competicion)
				// + (codigo edicion, 2 digitos) + (codigo partido, 3 digitos)
				String phpPartido = EstadisticasConstants.prefijoURL + codigoCompeticion + codigoEdicion + String.format("%03d", numPartido) + ".php";
				//Procesamientop del partido
				EstadisticasPartido.procesarPartido(phpPartido, temporada);
				
			}
		//}
		
		} catch (Exception ex) {
			
		}
	}

	/**
	 * 
	 * @return
	 */
	private String montarURL() {
		return EstadisticasConstants.prefijoURLTemp 
				+ this.codigoEquipo
				+ "&jornada1=" + this.jornada1
				+ "&cod_edicion1=" + this.codigoEdicion
				+ "&jornada2=" + this.jornada2
				+ "&cod_competicion=" + this.codigoCompeticion;
	}

}
