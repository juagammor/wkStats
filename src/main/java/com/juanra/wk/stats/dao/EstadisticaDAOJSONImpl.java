package com.juanra.wk.stats.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ResourceBundle;

import com.juanra.wk.stats.config.Config;
import com.juanra.wk.stats.model.Estadistica;

/**
 * Clase que se encarga de la grabacion y limpieza del fichero de documentos JSON
 * @author juanra
 *
 */
public class EstadisticaDAOJSONImpl implements EstadisticaDAO {

	private ResourceBundle bundle = Config.getInstance().getBundle();
	

	/**
	 * Metodo encargado de grabar la Estadistica como documento JSON en el fichero  
	 * @param est La estadistica que deseamos grabar
	 */
	@Override
	public boolean grabar(Estadistica est) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(bundle.getString("file.json")), true));

			bw.write(est.getBasicDBObject().toString());
			bw.newLine();
			bw.close();
		} catch (Exception ex) {

		} finally {

		}

		return true;
	}

	/**
	 * Metodo encargado de borrar el fichero para reiniciar la grabacion
	 */
	@Override
	public void cleanup() {
		new File(bundle.getString("file.json")).delete();
	}

}
