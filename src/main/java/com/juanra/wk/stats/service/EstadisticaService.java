package com.juanra.wk.stats.service;

import com.juanra.wk.stats.config.Config;
import com.juanra.wk.stats.dao.EstadisticaDAOJSONImpl;
import com.juanra.wk.stats.dao.EstadisticaDAOMongoDBImpl;
import com.juanra.wk.stats.model.Estadistica;
import com.juanra.wk.stats.model.EstadisticasConstants;

/**
 * Clase que actua a modo de fachada para realizar las grabaciones
 * segun el mode definido en el fichero de configuracion
 * @author juanra
 *
 */
public class EstadisticaService {

	private String modo;

	/**
	 * Constructor en el que obtenemos el modo
	 */
	public EstadisticaService() {
		this.modo = Config.getInstance().getBundle().getString("mode");
	}

	/**
	 * Grabacion en la que, segun el modo, se llamara al DAOImpl 
	 * correspondiente
	 * @param est
	 * @return
	 */
	public boolean grabar(Estadistica est) {

		if (modo.equalsIgnoreCase(EstadisticasConstants.MODO_JSON)) {
			return new EstadisticaDAOJSONImpl().grabar(est);
		}

		if (modo.equalsIgnoreCase(EstadisticasConstants.MODO_MONGO)) {
			return new EstadisticaDAOMongoDBImpl().grabar(est);
		}

		if (modo.equalsIgnoreCase(EstadisticasConstants.MODO_SCRIPT_SQL)) {
			// No desarrollado
			return false;
		}

		return false;
	}

	/**
	 * Proceso de reseteo, limpieza segun el modo definido
	 */
	public void cleanup() {
		if (modo.equalsIgnoreCase(EstadisticasConstants.MODO_JSON)) {
			new EstadisticaDAOJSONImpl().cleanup();
		}

		if (modo.equalsIgnoreCase(EstadisticasConstants.MODO_MONGO)) {
			new EstadisticaDAOMongoDBImpl().cleanup();
		}

		if (modo.equalsIgnoreCase(EstadisticasConstants.MODO_SCRIPT_SQL)) {
			// No desarrollado
			
		}

		
	}

}
