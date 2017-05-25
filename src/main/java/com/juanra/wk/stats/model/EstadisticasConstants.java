package com.juanra.wk.stats.model;

import java.util.LinkedHashMap;

/**
 * Clase con todas las constantes de la aplicacion
 * @author juanra
 *
 */
public class EstadisticasConstants {

	public static final int INDEX_DORSAL = 0;
	public static final int INDEX_NOMBRE = 1;
	public static final int INDEX_MINUTOS = 2;
	public static final int INDEX_PUNTOS = 3;
	public static final int INDEX_T2 = 4;
	public static final int INDEX_T2_P = 5;
	public static final int INDEX_T3 = 6;
	public static final int INDEX_T3_P = 7;
	public static final int INDEX_T1 = 8;
	public static final int INDEX_T1_P = 9;
	public static final int INDEX_REBOTES = 10;
	public static final int INDEX_REBOTES_DO = 11;
	public static final int INDEX_ASISTENCIAS = 12;
	public static final int INDEX_RECUPERACIONES = 13;
	public static final int INDEX_PERDIDAS = 14;
	public static final int INDEX_CONTRAATAQUE = 15;
	public static final int INDEX_TAPON_FAVOR = 16;
	public static final int INDEX_TAPON_CONTRA = 17;
	public static final int INDEX_MATES = 18;
	public static final int INDEX_FALTAS_COMETIDAS = 19;
	public static final int INDEX_FALTAS_RECIBIDAS = 20;
	public static final int INDEX_MAS_MENOS = 21;
	public static final int INDEX_VALORACION = 22;

	//MODOS DE GRABACION
	public static final String MODO_JSON = "JSON";
	public static final String MODO_MONGO = "MONGO";
	public static final String MODO_SCRIPT_SQL = "SQL";
	
	//
	public static final String[] CDB_SEVILLA_NAMES = new String[] {"CAJA SAN FERNANDO", "CAJASOL", "CAJASOL BANCA CÍVICA", "CAJASOL BANCA CIVICA", "BANCA CÍVICA", "BANCA CIVICA", "BALONCESTO SEVILLA"};
	
	public static final String prefijoURL = "http://www.acb.com/fichas/";
	public static final String prefijoURLTemp ="http://www.acb.com/partclub.php?cod_equipo=";
	
	public static final int[] CODIGOS_EDICION = new int[] {
		34,
		35,
        36,
        37,
        38,
        39,
        40,
        41,
        42,
        43,
        44,
        45,
        46,
        47,
        48,
        49,
        50,
        51,
        52,
        53,
        54,
        55,
        56,
        57,
        58,
        59,
        60
	};
	
	public static LinkedHashMap<String, String> temporadas;
	static {
		temporadas = new LinkedHashMap<String, String>();
		temporadas.put("34", "1989-1990");
		temporadas.put("35", "1990-1991");
		temporadas.put("36", "1991-1992");
	    temporadas.put("37", "1992-1993");
	    temporadas.put("38", "1993-1994");
	    temporadas.put("39", "1994-1995");
	    temporadas.put("40", "1995-1996");
	    temporadas.put("41", "1996-1997");
	    temporadas.put("42", "1997-1998");
	    temporadas.put("43", "1998-1999");
	    temporadas.put("44", "1999-2000");
	    temporadas.put("45", "2000-2001");
	    temporadas.put("46", "2001-2002");
	    temporadas.put("47", "2002-2003");
	    temporadas.put("48", "2003-2004");
	    temporadas.put("49", "2004-2005");
	    temporadas.put("50", "2005-2006");
	    temporadas.put("51", "2006-2007");
	    temporadas.put("52", "2007-2008");
	    temporadas.put("53", "2008-2009");
	    temporadas.put("54", "2009-2010");
	    temporadas.put("55", "2010-2011");
	    temporadas.put("56", "2011-2012");
	    temporadas.put("57", "2012-2013");
	    temporadas.put("58", "2013-2014");
	    temporadas.put("59", "2014-2015");
	    temporadas.put("60", "2015-2016");
	    temporadas.put("61", "2016-2017");
	}
	
	public static boolean isCaja(String equipo) {
		for (int i = 0; i < CDB_SEVILLA_NAMES.length; i++) {
			if (equipo.contains(CDB_SEVILLA_NAMES[i])) {
				return true;
			}
		}
		return false;
	}
	
	
}
