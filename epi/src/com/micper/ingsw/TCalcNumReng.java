package com.micper.ingsw;

/**
 * Cálculo de número de renglones a emplear en despliegue de botones.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de calcular el número de renglones necesarios para
 * desplegar X número de botones dado un máximo número de columnas
 * proporcionado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TCreaBotonRowCol.html">TCreaBotonRowCol</a></small> 
 *      </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCalcNumReng.png">
 */

public class TCalcNumReng {
	/** Almacena el resultado de número de renglones a generar */
	private int iNumReng;
	/**
	 * Almacena el resultado del número de renglones vacíos para despliegue de
	 * botones en una tabla
	 */
	private int iNumRengEmpty;

	/**
	 * Este constructor se encarga de realizar el cálculo de renglones a
	 * desplegar para un número de columnas proporcionadas y un número de
	 * botones a desplegar, almacenando el resultado de renglones máximos y
	 * renglones vacíos.
	 * 
	 * @param iNumBotones
	 *            Número de botones a desplegar en una tabla
	 * @param iMaxCols
	 *            Número máximo de columnas en las que se desplegarán los
	 *            botones
	 *            <p>
	 * @see <a href="TCalcNumCols.html">TCalcNumCols</a>
	 */
	public TCalcNumReng(int iNumBotones, int iMaxCols) {
		int iTemp;
		iNumReng = 1;
		iTemp = iNumBotones;
		if (iTemp <= iMaxCols) {
			iNumReng = 1;
		} else {
			while (iTemp > iMaxCols) {
				if (iTemp % iMaxCols != 0) {
					iNumReng++;
					iTemp = iTemp - iMaxCols;
				} else {
					iNumReng = (iTemp / iMaxCols);
					break;
				}
			}
			iNumRengEmpty = (iMaxCols * iNumReng) - iNumBotones;
		}
	}

	/**
	 * Este método se encarga de devolver el valor del número de renglones a
	 * emplear, dicho valor se calcula en el constructor.
	 * 
	 * @return Número de renglones a emplear en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumCols.html">TCalcNumCols</a>
	 */
	public int getNumReng() {
		return iNumReng;
	}

	/**
	 * Este método se encarga de devolver el valor del número de renglones
	 * vacíos, dicho valor se calcula en el constructor.
	 * 
	 * @return Número de renglones vacíos que se emplearán en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumCols.html">TCalcNumCols</a>
	 */
	public int getNumRengEmpty() {
		return iNumRengEmpty;
	}
}
