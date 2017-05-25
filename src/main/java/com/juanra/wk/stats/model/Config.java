package com.juanra.wk.stats.model;

import java.util.ResourceBundle;

/**
 * Clase singleton que gestiona el acceso a la
 * configuracion de la aplicacion
 * @author juanra
 *
 */
public class Config {

	private static final String RUTA_BUNDLE = "com/juanra/wk/stats/model/config";

	private static Config instance;
	private ResourceBundle bundle;

	private Config() {
		this.bundle = ResourceBundle.getBundle(RUTA_BUNDLE);
	}

	public synchronized static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public ResourceBundle getBundle() {
		return this.bundle;
	}

}
