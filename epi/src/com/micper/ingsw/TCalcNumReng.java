package com.micper.ingsw;

/**
 * C�lculo de n�mero de renglones a emplear en despliegue de botones.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de calcular el n�mero de renglones necesarios para
 * desplegar X n�mero de botones dado un m�ximo n�mero de columnas
 * proporcionado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
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
	/** Almacena el resultado de n�mero de renglones a generar */
	private int iNumReng;
	/**
	 * Almacena el resultado del n�mero de renglones vac�os para despliegue de
	 * botones en una tabla
	 */
	private int iNumRengEmpty;

	/**
	 * Este constructor se encarga de realizar el c�lculo de renglones a
	 * desplegar para un n�mero de columnas proporcionadas y un n�mero de
	 * botones a desplegar, almacenando el resultado de renglones m�ximos y
	 * renglones vac�os.
	 * 
	 * @param iNumBotones
	 *            N�mero de botones a desplegar en una tabla
	 * @param iMaxCols
	 *            N�mero m�ximo de columnas en las que se desplegar�n los
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
	 * Este m�todo se encarga de devolver el valor del n�mero de renglones a
	 * emplear, dicho valor se calcula en el constructor.
	 * 
	 * @return N�mero de renglones a emplear en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumCols.html">TCalcNumCols</a>
	 */
	public int getNumReng() {
		return iNumReng;
	}

	/**
	 * Este m�todo se encarga de devolver el valor del n�mero de renglones
	 * vac�os, dicho valor se calcula en el constructor.
	 * 
	 * @return N�mero de renglones vac�os que se emplear�n en el despliegue
	 *         <p>
	 * @see <a href="TCalcNumCols.html">TCalcNumCols</a>
	 */
	public int getNumRengEmpty() {
		return iNumRengEmpty;
	}
}
