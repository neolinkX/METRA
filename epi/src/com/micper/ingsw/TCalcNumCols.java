package com.micper.ingsw;

/**
 * Cálculo de número de columnas a emplear en despliegue de botones.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de calcular el número de columnas necesarias para
 * desplegar X número de botones dado un máximo número de renglones
 * proporcionado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TCreaBotonRowCol.html">TCreaBotonRowCol</a></small>
 *      </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCalcNumCols.png">
 */

public class TCalcNumCols {
	/** Almacena el resultado de número de columnas a generar */
	private int iNumCols;
	/**
	 * Almacena el resultado del número de columnas vacías para despliegue de
	 * botones en una tabla
	 */
	private int iNumColsEmpty;

	/**
	 * Este constructor se encarga de realizar el cálculo de columnas a
	 * desplegar para un número de renglones proporcionados y un número de
	 * botones a desplegar, almacenando el resultado de columnas máximas y
	 * columnas vacías.
	 * 
	 * @param iNumBotones
	 *            Número de botones a desplegar en una tabla
	 * @param iMaxRengs
	 *            Número máximo de renglones en los que se desplegarán los
	 *            botones
	 *            <p>
	 * @see <a href="TCalcNumReng.html">TCalcNumReng</a>
	 */
	public TCalcNumCols(int iNumBotones, int iMaxRengs) {
		int iTemp;
		iNumCols = 1;
		iTemp = iNumBotones;
		if (iTemp <= iMaxRengs) {
			iNumCols = 1;
		} else {
			while (iTemp > iMaxRengs) {
				if (iTemp % iMaxRengs != 0) {
					iNumCols++;
					iTemp = iTemp - iMaxRengs;
				} else {
					iNumCols = (iTemp / iMaxRengs);
					break;
				}
			}
			iNumColsEmpty = (iMaxRengs * iNumCols) - iNumBotones;
		}
	}

	/**
	 * Este método se encarga de devolver el valor del número de columnas a
	 * emplear, dicho valor se calcula en el constructor.
	 * 
	 * @return Número de columnas a emplear en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumReng.html">TCalcNumReng</a>
	 */
	public int getNumCols() {
		return iNumCols;
	}

	/**
	 * Este método se encarga de devolver el valor del número de columnas
	 * vacías, dicho valor se calcula en el constructor.
	 * 
	 * @return Número de columnas vacías que se emplearán en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumReng.html">TCalcNumReng</a>
	 */
	public int getNumColsEmpty() {
		return iNumColsEmpty;
	}
}