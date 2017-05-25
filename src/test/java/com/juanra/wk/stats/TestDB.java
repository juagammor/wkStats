package com.juanra.wk.stats;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TestDB {

	
	public static void main(String[] args) throws Exception {
		
		//jugadoresSinMinutos();
		
		//recordStat("taponesFavor");
		
		estadisticasConMasDe("puntos", "35");

	}
	
	private static void estadisticasConMasDe(String datoEstadistico, String cantidad) {
		MongoClient cliente = new MongoClient();

		DB db = cliente.getDB("wkstats");
		DBCollection collection = db.getCollection("estadisticas");
		
		//Query
		DBObject query = new BasicDBObject();
		DBObject mayorIgualQue = new BasicDBObject("$gte", new Integer(cantidad).intValue()); 
		query.put(datoEstadistico, mayorIgualQue);
		
		DBObject orderBy = new BasicDBObject();
		orderBy.put(datoEstadistico, -1);
		DBCursor dc = collection.find(query).sort(orderBy);
		
		HashMap<String, Integer> vecesSinJugar = new HashMap<String, Integer>();
		
		while (dc.hasNext()) {
			
			DBObject obj = dc.next();
			System.out.println(obj.get("temporada") + " - " + obj.get("jornada") + " - " + obj.get("rival") + " - " + obj.get("nombre") + " - " + obj.get(datoEstadistico));
			
			vecesSinJugar.put((String) obj.get("nombre"), 
					vecesSinJugar.get((String) obj.get("nombre")) == null 
					? 1 
				    : ((Integer) vecesSinJugar.get((String) obj.get("nombre"))).intValue() + 1);
			
		}
		
		
	}

	private static void jugadoresSinMinutos() {
		MongoClient cliente = new MongoClient();

		DB db = cliente.getDB("wkstats");
		DBCollection collection = db.getCollection("estadisticas");
		
		
		/* Jugadores sin minutos en partido */
		DBObject query = new BasicDBObject();
		query.put("minutos", "0:00");
		DBObject orderBy = new BasicDBObject();
		orderBy.put("nombre", 1);
		DBCursor dc = collection.find(query).sort(orderBy);
		
		HashMap<String, Integer> vecesSinJugar = new HashMap<String, Integer>();
		
		while (dc.hasNext()) {
			
			DBObject obj = dc.next();
			System.out.println(obj.get("temporada") + " - " + obj.get("jornada") + " - " + obj.get("rival") + " - " + obj.get("nombre"));
			
			vecesSinJugar.put((String) obj.get("nombre"), 
					vecesSinJugar.get((String) obj.get("nombre")) == null 
					? 1 
				    : ((Integer) vecesSinJugar.get((String) obj.get("nombre"))).intValue() + 1);
			
		}
				
		Set<String> keys = vecesSinJugar.keySet();
		
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key + " - " + vecesSinJugar.get(key));
		}
		
	}
	
	private static void recordStat(String field) {
		MongoClient cliente = new MongoClient();

		DB db = cliente.getDB("wkstats");
		DBCollection collection = db.getCollection("estadisticas");
		
		
		/* Jugadores sin minutos en partido */
		DBObject query = new BasicDBObject();
		DBObject orderBy = new BasicDBObject();
		orderBy.put(field, -1);
		DBCursor dc = collection.find().limit(15).sort(orderBy);
		
		HashMap<String, Integer> vecesSinJugar = new HashMap<String, Integer>();
		
		while (dc.hasNext()) {
			
			DBObject obj = dc.next();
			System.out.println(obj.get("temporada") + " - " + obj.get("jornada") + " - " + obj.get("rival") + " - " + obj.get("nombre") + " - " + obj.get(field));
			
			vecesSinJugar.put((String) obj.get("nombre"), 
					vecesSinJugar.get((String) obj.get("nombre")) == null 
					? 1 
				    : ((Integer) vecesSinJugar.get((String) obj.get("nombre"))).intValue() + 1);
			
		}
				
/*		Set<String> keys = vecesSinJugar.keySet();
		
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key + " - " + vecesSinJugar.get(key));
		}*/
		
	}
}
