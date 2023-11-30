package com.micper.util;

import java.sql.*;
import java.util.*;
import com.micper.ingsw.*;
import java.text.*;

/**
 * Clase para manejo de fechas: generación, today, y cadenas de despliegue.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="./../ingsample/pg9902041CFG.html">com.micper.ingsample.pg9902041CFG
 *      </a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TFechas.png">
 */

public class TNumeros {

	/** Constructor por omisión de la clase. */
	public TNumeros() {
	}

	/**
	 * Método encargado de regresar un número formateado como cadena para
	 * mostrar x Número de dígitos.
	 * 
	 * @param iNumero
	 *            Número que se desea obtener con formato.
	 * @param iNumDigitos
	 *            Número de dígitos que se desea obtener en el resultado.
	 * @return Cadena de texto correspondiente al número con formato
	 */
	public String getNumeroSinSeparador(Integer iNumero, int iNumDigitos) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumIntegerDigits(iNumDigitos);
		nf.setMaximumIntegerDigits(iNumDigitos);
		nf.setGroupingUsed(false);
		return nf.format(iNumero);
	}
}