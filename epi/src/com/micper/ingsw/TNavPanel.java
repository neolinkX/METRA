package com.micper.ingsw;

/**
 * Clase encargada de controlar las caracter�sticas de un panel de navegaci�n.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TShowNavPanel.html">TShowNavPanel</a></small>
 * @see <small><a
 *      href="./../ingsample/pg0700006CFG.html">com.micper.ingsample.pg0700006CFG
 *      </a></small>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700006.jsp');">Click
 *      para mas informaci�n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TNavPanel.png">
 */

public class TNavPanel {
	/** Conserva si se debe mostrar o no el panel de navegaci�n. */
	boolean lVisible;
	/** Indica si se habilita o no el bot�n primero. */
	boolean lPrimero;
	/** Indica si se habilita o no el bot�n anterior. */
	boolean lAnterior;
	/** Indica si se habilita o no el bot�n siguiente. */
	boolean lSiguiente;
	/** Indica si se habilita o no el bot�n ultimo. */
	boolean lUltimo;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * primero.
	 */
	String cEstatusP;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * anterior.
	 */
	String cEstatusA;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * siguiente.
	 */
	String cEstatusS;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n ultimo.
	 */
	String cEstatusU;

	/**
	 * Constructor por omisi�n. Forma de uso: new TNavPanel();. <br>
	 * En este constructor se inicializan los valores de los atributos al valor
	 * por omisi�n.
	 */
	public TNavPanel() {
		lVisible = lPrimero = lAnterior = lSiguiente = lUltimo = true;
		cEstatusP = "Primer registro";
		cEstatusA = "Registro anterior";
		cEstatusS = "Siguiente registro";
		cEstatusU = "Ultimo registro";
	}

	/**
	 * Este m�todo se encarga de colocar al atributo lVisible el valor del
	 * par�metro.
	 * 
	 * @param vVisible
	 *            Valor que se desea asignar
	 */
	public void setVisible(boolean vVisible) {
		lVisible = vVisible;
	}

	/**
	 * Este m�todo se encarga de regresar el valor del atributo lVisible.
	 * 
	 * @return Valor del atributo previamente asignado.
	 */
	public boolean getVisible() {
		return lVisible;
	}

	/**
	 * Este m�todo se encarga de generar los mensajes para la barra de estatus
	 * cuando se cambia el prefijo.
	 * 
	 * @param cPrefijo
	 *            Valor de prefijo que se desea asignar.
	 */
	public void setPrefijo(String cPrefijo) {
		if (cPrefijo != "") {
			cEstatusP = "Primer " + cPrefijo.toLowerCase();
			cEstatusA = cPrefijo.toUpperCase().substring(0, 1)
					+ cPrefijo.toLowerCase().substring(1) + " anterior";
			cEstatusS = "Siguiente " + cPrefijo.toLowerCase();
			cEstatusU = "Ultimo " + cPrefijo.toLowerCase();
		}
	}

	/**
	 * Este m�todo se encarga de guardar los valores de habilitado o no para
	 * cada bot�n.
	 * 
	 * @param lPri
	 *            Valor del bot�n primero
	 * @param lAnt
	 *            Valor del bot�n anterior
	 * @param lSig
	 *            Valor del bot�n siguiente
	 * @param lUlt
	 *            Valor del bot�n �ltimo
	 */
	public void setButtons(boolean lPri, boolean lAnt, boolean lSig,
			boolean lUlt) {
		lPrimero = lPri;
		lAnterior = lAnt;
		lSiguiente = lSig;
		lUltimo = lUlt;
	}

	/**
	 * Este m�todo se encarga de asignar el valor de inactivos a todos los
	 * botones.
	 */
	public void setInactivo() {
		lPrimero = lAnterior = lSiguiente = lUltimo = false;
	}

	/**
	 * Este m�todo se encarga de asignar el valor proporcionado al atributo
	 * lPrimero.
	 * 
	 * @param lValor
	 *            Valor que se asignar� al atributo.
	 */
	public void setPrimero(boolean lValor) {
		lPrimero = lValor;
	}

	/**
	 * Este m�todo se encarga de asignar el valor proporcionado al atributo
	 * lAnterior.
	 * 
	 * @param lValor
	 *            Valor que se asignar� al atributo.
	 */
	public void setAnterior(boolean lValor) {
		lAnterior = lValor;
	}

	/**
	 * Este m�todo se encarga de asignar el valor proporcionado al atributo
	 * lSiguiente.
	 * 
	 * @param lValor
	 *            Valor que se asignar� al atributo.
	 */
	public void setSiguiente(boolean lValor) {
		lSiguiente = lValor;
	}

	/**
	 * Este m�todo se encarga de asignar el valor proporcionado al atributo
	 * lUltimo.
	 * 
	 * @param lValor
	 *            Valor que se asignar� al atributo.
	 */
	public void setUltimo(boolean lValor) {
		lUltimo = lValor;
	}
}