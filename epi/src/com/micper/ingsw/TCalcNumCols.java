package com.micper.ingsw;

/**
 * C�lculo de n�mero de columnas a emplear en despliegue de botones.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de calcular el n�mero de columnas necesarias para
 * desplegar X n�mero de botones dado un m�ximo n�mero de renglones
 * proporcionado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
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
	/** Almacena el resultado de n�mero de columnas a generar */
	private int iNumCols;
	/**
	 * Almacena el resultado del n�mero de columnas vac�as para despliegue de
	 * botones en una tabla
	 */
	private int iNumColsEmpty;

	/**
	 * Este constructor se encarga de realizar el c�lculo de columnas a
	 * desplegar para un n�mero de renglones proporcionados y un n�mero de
	 * botones a desplegar, almacenando el resultado de columnas m�ximas y
	 * columnas vac�as.
	 * 
	 * @param iNumBotones
	 *            N�mero de botones a desplegar en una tabla
	 * @param iMaxRengs
	 *            N�mero m�ximo de renglones en los que se desplegar�n los
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
	 * Este m�todo se encarga de devolver el valor del n�mero de columnas a
	 * emplear, dicho valor se calcula en el constructor.
	 * 
	 * @return N�mero de columnas a emplear en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumReng.html">TCalcNumReng</a>
	 */
	public int getNumCols() {
		return iNumCols;
	}

	/**
	 * Este m�todo se encarga de devolver el valor del n�mero de columnas
	 * vac�as, dicho valor se calcula en el constructor.
	 * 
	 * @return N�mero de columnas vac�as que se emplear�n en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumReng.html">TCalcNumReng</a>
	 */
	public int getNumColsEmpty() {
		return iNumColsEmpty;
	}
}