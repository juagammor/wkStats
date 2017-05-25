package com.juanra.wk.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

public class App2 {

	public static void main(String[] args) {
/*		// JDOM
		try {

			File f = new File("/home/juanra/Descargas/ACB.html");
			File f2 = new File("/home/juanra/Descargas/ACB_OUT.xml");

			Tidy tidy = new Tidy();
			tidy.setXHTML(true);
			tidy.setHideComments(true);
			tidy.setMakeClean(true);
			tidy.setXmlOut(true);
			

			tidy.parse(new FileInputStream(f), new FileOutputStream(f2));

			SAXBuilder builder = new SAXBuilder();
			builder.setIgnoringBoundaryWhitespace(true);

			org.jdom2.Document d = builder.build(f2);

			System.out.println(d);

			Element e = d.getRootElement();
			
			System.out.println(e.getValue());
			List<Element> hijos = e.getChildren();
			for (Iterator<Element> iterator = hijos.iterator(); iterator.hasNext();) {
				Element object = (Element) iterator.next();
				System.out.println("-");
				System.out.println(object);
				
			}
			
			
			Element html = e.getChild("html");
			
			hijos = html.getChildren();
			for (Iterator<Element> iterator = hijos.iterator(); iterator.hasNext();) {
				Element object = (Element) iterator.next();
				System.out.println(object);
				
			}
			
			Element body = html.getChild("body");
			Element tableCuerpo = body.getChild("table");
			
			List<Element> listadoTablas = tableCuerpo.getChildren("table");
			System.out.println("ListadoTablas: " + listadoTablas.size());
			
			for (Iterator iterator = listadoTablas.iterator(); iterator
					.hasNext();) {
				Element element = (Element) iterator.next();
				Attribute attb = element.getAttribute("class");
				System.out.println(attb.getName() + " - " + attb.getValue());
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}*/
	}

}
