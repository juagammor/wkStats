package com.juanra.wk.stats.dao;

import java.util.ResourceBundle;

import com.juanra.wk.stats.config.Config;
import com.juanra.wk.stats.model.Estadistica;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

/**
 * Clase que se encarga de la grabacion y limpieza del fichero de documentos JSON
 * @author juanra
 *
 */
public class EstadisticaDAOMongoDBImpl implements EstadisticaDAO {

	/**
	 * Metodo encargado de grabar la Estadistica como documento JSON en el fichero  
	 * @param est La estadistica que deseamos grabar
	 */
	@Override
	public boolean grabar(Estadistica est) {
		MongoClient cliente = null;
		ResourceBundle bundle = Config.getInstance().getBundle();

		try {

			cliente = new MongoClient(bundle.getString("bbdd.url"),
					new Integer((String) bundle.getObject("bbdd.port")));

			DB baseDeDatos = cliente.getDB(bundle.getString("bbdd.database"));
			DBCollection collection = baseDeDatos.getCollection(bundle
					.getString("bbdd.collection"));

			// collection.drop();

			DBObject documento = est.getBasicDBObject();

			WriteResult wr = collection.insert(WriteConcern.SAFE, documento);
			System.out.println(wr.toString());
		} catch (Exception ex) {
			return false;
		} finally {
			cliente.close();
		}
		return true;
	}

	/**
	 * Metodo encargado de borrar el fichero para reiniciar la grabacion
	 */
	@Override
	public void cleanup() {
		MongoClient cliente = null;
		ResourceBundle bundle = Config.getInstance().getBundle();

		try {
			cliente = new MongoClient(bundle.getString("bbdd.url"),
					new Integer((String) bundle.getObject("bbdd.port")));
			DB baseDeDatos = cliente.getDB(bundle.getString("bbdd.database"));
			baseDeDatos.dropDatabase();
		} catch (Exception ex) {

		}
	}

}
