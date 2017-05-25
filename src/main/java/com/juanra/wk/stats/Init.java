package com.juanra.wk.stats;

import com.juanra.wk.stats.model.Config;
import com.juanra.wk.stats.model.EstadisticasConstants;
import com.juanra.wk.stats.service.EstadisticaService;

public class Init {

	private String codigoEquipo;
	private String codigoCompeticion;
	private int codigoEdicion;
	private int jornada1;
	private int jornada2;

	private boolean cleaning;

	public Init() {
		// Por defecto elimino
		try {
			this.cleaning = new Boolean(Config.getInstance().getBundle()
					.getString("cleaning"));
		} catch (Exception ex) {
			this.cleaning = true;
		}
	}

	/* Getters & Setters */

	public String getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(String codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public String getCodigoCompeticion() {
		return codigoCompeticion;
	}

	public void setCodigoCompeticion(String codigoCompeticion) {
		this.codigoCompeticion = codigoCompeticion;
	}

	public int getCodigoEdicion() {
		return codigoEdicion;
	}

	public void setCodigoEdicion(int codigoEdicion) {
		this.codigoEdicion = codigoEdicion;
	}

	public int getJornada1() {
		return jornada1;
	}

	public void setJornada1(int jornada1) {
		this.jornada1 = jornada1;
	}

	public int getJornada2() {
		return jornada2;
	}

	public void setJornada2(int jornada2) {
		this.jornada2 = jornada2;
	}

	public boolean isCleaning() {
		return cleaning;
	}

	public void setCleaning(boolean cleaning) {
		this.cleaning = cleaning;
	}

	/*
	 * metodos
	 */

	/**
	 * 
	 */
	public void iniciaProceso() {

		// Eliminamos fichero y/o limpiamos BBDD
		if (cleaning) {
			new EstadisticaService().cleanup();
		}

		if (codigoEdicion > 0) {
			// Para una temporada 
			//Creamos el objeto con toda la informacion para su procesado
			EstadisticasTemporada et = new EstadisticasTemporada(codigoEquipo,
					jornada1, jornada2, codigoEdicion, codigoCompeticion);
			//Proceso
			et.procesarTemporada();
		} else {
			//Para todas las temporadas
			for (int i = 0; i < EstadisticasConstants.CODIGOS_EDICION.length; i++) {
				//Creamos el objeto con toda la informacion para su procesado
				EstadisticasTemporada et = new EstadisticasTemporada(
						codigoEquipo, jornada1, jornada2,
						EstadisticasConstants.CODIGOS_EDICION[i],
						codigoCompeticion);
				//Proceso
				et.procesarTemporada();
			}

		}
	}

	/**
	 * Iniciar el proceso definiendo los parametros
	 * @param args
	 */
	public static void main(String[] args) {
		//Construccion del objeto
		Init init = new Init();
		//Codigo de equipo que sera necesaria para montar las URL
		init.setCodigoEquipo("SEV");
		//Codigo de Competicion (liga ACB)
		init.setCodigoCompeticion("LACB");
		//Codigo de edicion. -1 para todos, entre 34 y 60 para una edicion 
		//concreta
		init.setCodigoEdicion(-1);
		// init.setCodigoEdicion(60);
		
		//Jornada inicial hasta jornada final.Ambas a cero para todas jornadas
		init.setJornada1(0);
		init.setJornada2(0);
		
		//Esta parametrizado por lo que no seria necesario
		//init.setCleaning(true);

		//Iniciar proceso
		init.iniciaProceso();
	}

}
