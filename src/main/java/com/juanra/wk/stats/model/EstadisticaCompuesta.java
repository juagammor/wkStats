package com.juanra.wk.stats.model;

/**
 * Clase que define una estadistica compuesta de dos datos enteros 
 * como puede ser rebotes ofensivos y defensivos (d+o) o tiros 
 * convertidos e intentados (i/t).
 * 
 * @author juanra
 *
 */
public class EstadisticaCompuesta {

	private int A;
	private int B;
	
	public EstadisticaCompuesta(int A, int B) {
		this.A = A;
		this.B = B;
	}
	
	public int getA() {
		return A;
	}
	public void setA(int a) {
		A = a;
	}
	public int getB() {
		return B;
	}
	public void setB(int b) {
		B = b;
	}
	
}
