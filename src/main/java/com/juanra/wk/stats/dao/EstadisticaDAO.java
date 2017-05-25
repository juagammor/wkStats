package com.juanra.wk.stats.dao;

import com.juanra.wk.stats.model.Estadistica;

/**
 * Interfaz donde se definen los metodos para grabacion y limpieza / reseteo
 * 
 * @author juanra
 *
 */
public interface EstadisticaDAO {
	
	/**
	 * Grabacion de la estadistica en el medio definido 
	 * por el parametro 'mode' en el fichero de 
	 * configuracion 
	 * @param est Estadistica a grabar
	 * @return Exito o fracaso en la operacion
	 */
	public boolean grabar(Estadistica est);
	
	/**
	 * Limpieza o reseteo del medio para el posterior 
	 * grabado
	 */
	public void cleanup();

}
