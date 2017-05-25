package com.juanra.wk.stats.model;

import org.bson.Document;

import com.mongodb.BasicDBObject;

/**
 * Clase que contiene una linea estadistica de un partido concreto de un jugador
 * @author juanra
 *
 */
public class Estadistica {
	
	private String temporada;
	private String fecha;
	private int jornada;
	private String rival;
	
	private int dorsal;
	private String nombre;
	private String claveJugador;
	private TiempoJuego minutos;
	private int puntos;
	private int t2Conv;
	private int t2Total;
	private double t2Porc;
	private int t3Conv;
	private int t3Total;
	private double t3Porc;
	private int t1Conv;
	private int t1Total;
	private double t1Porc;
	private int rebotesTotales;
	private int rebotesDef;
	private int rebotesOf;
	private int asistencias;
	private int recuperaciones;
	private int contraataques;
	private int taponesFavor;
	private int taponesContra;
	private int mates;
	private int faltas;
	private int faltasRecibidas;
	private int masmenos;
	private int valoracion;
	
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public int getJornada() {
		return jornada;
	}
	public void setJornada(int jornada) {
		this.jornada = jornada;
	}
	public String getRival() {
		return rival;
	}
	public void setRival(String rival) {
		this.rival = rival;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TiempoJuego getMinutos() {
		return minutos;
	}
	public void setMinutos(TiempoJuego minutos) {
		this.minutos = minutos;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getT2Conv() {
		return t2Conv;
	}
	public void setT2Conv(int t2Conv) {
		this.t2Conv = t2Conv;
	}
	public int getT2Total() {
		return t2Total;
	}
	public void setT2Total(int t2Total) {
		this.t2Total = t2Total;
	}
	public double getT2Porc() {
		return t2Porc;
	}
	public void setT2Porc(double t2Porc) {
		this.t2Porc = t2Porc;
	}
	public int getT3Conv() {
		return t3Conv;
	}
	public void setT3Conv(int t3Conv) {
		this.t3Conv = t3Conv;
	}
	public int getT3Total() {
		return t3Total;
	}
	public void setT3Total(int t3Total) {
		this.t3Total = t3Total;
	}
	public double getT3Porc() {
		return t3Porc;
	}
	public void setT3Porc(double t3Porc) {
		this.t3Porc = t3Porc;
	}
	public int getT1Conv() {
		return t1Conv;
	}
	public void setT1Conv(int t1Conv) {
		this.t1Conv = t1Conv;
	}
	public int getT1Total() {
		return t1Total;
	}
	public void setT1Total(int t1Total) {
		this.t1Total = t1Total;
	}
	public double getT1Porc() {
		return t1Porc;
	}
	public void setT1Porc(double t1Porc) {
		this.t1Porc = t1Porc;
	}
	public int getRebotesTotales() {
		return rebotesTotales;
	}
	public void setRebotesTotales(int rebotesTotales) {
		this.rebotesTotales = rebotesTotales;
	}
	public int getRebotesDef() {
		return rebotesDef;
	}
	public void setRebotesDef(int rebotesDef) {
		this.rebotesDef = rebotesDef;
	}
	public int getRebotesOf() {
		return rebotesOf;
	}
	public void setRebotesOf(int rebotesOf) {
		this.rebotesOf = rebotesOf;
	}
	public int getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}
	public int getRecuperaciones() {
		return recuperaciones;
	}
	public void setRecuperaciones(int recuperaciones) {
		this.recuperaciones = recuperaciones;
	}
	public int getContraataques() {
		return contraataques;
	}
	public void setContraataques(int contraataques) {
		this.contraataques = contraataques;
	}
	public int getTaponesFavor() {
		return taponesFavor;
	}
	public void setTaponesFavor(int taponesFavor) {
		this.taponesFavor = taponesFavor;
	}
	public int getTaponesContra() {
		return taponesContra;
	}
	public void setTaponesContra(int taponesContra) {
		this.taponesContra = taponesContra;
	}
	public int getMates() {
		return mates;
	}
	public void setMates(int mates) {
		this.mates = mates;
	}
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	public int getFaltasRecibidas() {
		return faltasRecibidas;
	}
	public void setFaltasRecibidas(int faltasRecibidas) {
		this.faltasRecibidas = faltasRecibidas;
	}
	public int getMasmenos() {
		return masmenos;
	}
	public void setMasmenos(int masmenos) {
		this.masmenos = masmenos;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getClaveJugador() {
		return claveJugador;
	}
	public void setClaveJugador(String claveJugador) {
		this.claveJugador = claveJugador;
	}
	
	public Document getDocument() {
		Document d = new Document();

		d.put("temporada", this.temporada);
		d.put("fecha", this.fecha);
		d.put("jornada", this.jornada);
		d.put("rival", this.rival);
		
		d.put("dorsal", this.dorsal);
		d.put("nombre", this.nombre);
		d.put("claveJugador", this.claveJugador);
		d.put("minutos", this.minutos.toString());
		d.put("puntos", this.puntos);
		d.put("t2Conv", this.t2Conv);
		d.put("t2Total", this.t2Total);
		d.put("t2Porc", this.t2Porc);
		d.put("t3Conv", this.t3Conv);
		d.put("t3Total", this.t3Total);
		d.put("t3Porc", this.t3Porc);
		d.put("t1Conv", this.t1Conv);
		d.put("t1Total", this.t1Total);
		d.put("t1Porc", this.t1Porc);
		d.put("rebotesTotales", this.rebotesTotales);
		d.put("rebotesDef", this.rebotesDef);
		d.put("rebotesOf", this.rebotesOf);
		d.put("asistencias", this.asistencias);
		d.put("recuperaciones", this.recuperaciones);
		d.put("contraataques", this.contraataques);
		d.put("taponesFavor", this.taponesFavor);
		d.put("taponesContra", this.taponesContra);
		d.put("mates", this.mates);
		d.put("faltas", this.faltas);
		d.put("faltasRecibidas", this.faltasRecibidas);
		d.put("masmenos", this.masmenos);
		d.put("valoracion", this.valoracion);
		
		return d;
	}
	
	public BasicDBObject getBasicDBObject() {
		BasicDBObject d = new BasicDBObject();

		d.put("temporada", this.temporada);
		d.put("fecha", this.fecha);
		d.put("jornada", this.jornada);
		d.put("rival", this.rival);
		
		d.put("dorsal", this.dorsal);
		d.put("nombre", this.nombre);
		d.put("claveJugador", this.claveJugador);
		d.put("minutos", this.minutos.toString());
		d.put("puntos", this.puntos);
		d.put("t2Conv", this.t2Conv);
		d.put("t2Total", this.t2Total);
		d.put("t2Porc", this.t2Porc);
		d.put("t3Conv", this.t3Conv);
		d.put("t3Total", this.t3Total);
		d.put("t3Porc", this.t3Porc);
		d.put("t1Conv", this.t1Conv);
		d.put("t1Total", this.t1Total);
		d.put("t1Porc", this.t1Porc);
		d.put("rebotesTotales", this.rebotesTotales);
		d.put("rebotesDef", this.rebotesDef);
		d.put("rebotesOf", this.rebotesOf);
		d.put("asistencias", this.asistencias);
		d.put("recuperaciones", this.recuperaciones);
		d.put("contraataques", this.contraataques);
		d.put("taponesFavor", this.taponesFavor);
		d.put("taponesContra", this.taponesContra);
		d.put("mates", this.mates);
		d.put("faltas", this.faltas);
		d.put("faltasRecibidas", this.faltasRecibidas);
		d.put("masmenos", this.masmenos);
		d.put("valoracion", this.valoracion);
		
		return d;
	}

	
		
}
