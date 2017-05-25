package com.juanra.wk.stats.model;

/**
 * Clase tiempo de Juego
 * @author juanra
 *
 */
public class TiempoJuego {

	private int minutos;
	private int segundos;
	
	public TiempoJuego(int minutos, int segundos) {
		this.minutos = minutos;
		this.segundos = segundos;
	}
	
	public int getMinutos() {
		return minutos;
	}
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	public int getSegundos() {
		return segundos;
	}
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	@Override
	public String toString() {
		return minutos + ":" + String.format("%02d", segundos);
	}
	
	/*public static void main(String[] args) {
		TiempoJuego tj = new TiempoJuego(24, 2);
		System.out.println(tj);
	}*/
	
}
