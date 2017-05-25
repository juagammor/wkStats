package com.juanra.wk.stats;

import java.util.Iterator;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		MongoClient client = new MongoClient("localhost", 27017);
		MongoIterable<String> db = client.listDatabaseNames();

		for (Iterator<String> iterator = db.iterator(); iterator.hasNext();) {
			String db2 = iterator.next();
			System.out.println(db2);
		}

		System.out.println("Obtener Database blog...");
		MongoDatabase mdb = client.getDatabase("blog");

		MongoIterable<String> collSts = mdb.listCollectionNames();
		for (Iterator iterator = collSts.iterator(); iterator.hasNext();) {
			String st = (String) iterator.next();
			System.out.println(st);

		}

		/*
		 * MongoCollection collectionPosts = mdb.getCollection("posts");
		 * 
		 * FindIterable posts = collectionPosts.find(); for (Iterator iterator =
		 * posts.iterator(); iterator.hasNext();) { Document type = (Document)
		 * iterator.next(); System.out.println(type); }
		 */

		System.out.println("aaaa");

		client.close();

		

	}
}
