package com.micper.ingsw;

/**
 * Clase encargada de controlar las caracter�sticas de un panel de
 * actualizaci�n.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TShowUpdPanel.html">TShowUpdPanel</a></small>
 * @see <small><a
 *      href="./../ingsample/pg0700005CFG.html">com.micper.ingsample.pg0700005CFG
 *      </a></small>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700005.jsp');">Click
 *      para mas informaci�n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TUpdPanel.png">
 */

public class TUpdPanel {
	/** Conserva si se debe mostrar o no el panel de navegaci�n. */
	boolean lVisible;
	/** Indica si se habilita o no el bot�n nuveo. */
	boolean lNuevo;
	/** Indica si se habilita o no el bot�n guardar. */
	boolean lGuardar;
	/** Indica si se habilita o no el bot�n modificar. */
	boolean lModificar;
	/** Indica si se habilita o no el bot�n cancelar. */
	boolean lCancelar;
	/** Indica si se habilita o no el bot�n eliminar. */
	boolean lEliminar;
	/** Indica si se habilita o no el bot�n inactivar. */
	boolean lInactivar;
	/** Indica si se habilita o no el bot�n imprimir. */
	boolean lImprimir;
	/** Indica si se habilita o no el bot�n reporte. */
	boolean lReporte;
	/** Almacena el texto a desplegar en la barra de estado para el bot�n nuevo. */
	String cEstatusN;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * guardar.
	 */
	String cEstatusG;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * modificar.
	 */
	String cEstatusM;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * cancelar.
	 */
	String cEstatusC;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * eliminar.
	 */
	String cEstatusE;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * imprimir.
	 */
	String cEstatusI;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el bot�n
	 * reporte.
	 */
	String cEstatusR;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * nuevo.
	 */
	String cActionN;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * guardar.
	 */
	String cActionG;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * modificar.
	 */
	String cActionM;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * cancelar.
	 */
	String cActionC;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * eliminar.
	 */
	String cActionE;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * imprimir.
	 */
	String cActionI;
	/**
	 * Almacena la acci�n que se asignar� al campo hdBoton para el bot�n de
	 * Reporte.
	 */
	String cActionR;

	/**
	 * Constructor por omisi�n. Uso: new TUpdPanel(); <br>
	 * Este constructor se encarga de inicializar los valores de las variables,
	 * mismos que podr�n modificarse posteriormente por medio de los m�todos
	 * set.
	 */
	public TUpdPanel() {
		lInactivar = false;
		lVisible = lNuevo = lGuardar = lModificar = lCancelar = lEliminar = lImprimir = lReporte = true;
		cEstatusN = "Nuevo registro";
		cEstatusG = "Guardar cambios";
		cEstatusM = "Modificar registro";
		cEstatusC = "Cancelar cambios";
		cEstatusE = "Eliminar registro";
		cEstatusI = "Imprimir registro";
		cEstatusR = "Generar Reporte";
		cActionN = "Nuevo";
		cActionG = "Guardar";
		cActionM = "Actualizar";
		cActionC = "Cancelar";
		cActionE = "Borrar";
		cActionI = "Imprimir";
		cActionR = "Reporte";
	}

	/**
	 * Coloca el valor proporcionado al atributo lVisible.
	 * 
	 * @param vVisible
	 *            Valor que se desea asignar al atributo lVisible.
	 */
	public void setVisible(boolean vVisible) {
		lVisible = vVisible;
	}

	/**
	 * M�todo que se encarga de regresar el valor del atributo lVisible.
	 * 
	 * @return Valor del atributo lVisible.
	 */
	public boolean getVisible() {
		return lVisible;
	}

	/**
	 * Cambia el valor del estatus para cada uno de los botones tomando en
	 * cuenta el prefijo.
	 * 
	 * @param cPrefijo
	 *            Valor que se desea emplear en el estatus de los botones.
	 */
	public void setPrefijo(String cPrefijo) {
		if (cPrefijo != "") {
			cEstatusN = "Nuevo registro de " + cPrefijo.toLowerCase();
			cEstatusG = "Guardar cambios al registro de "
					+ cPrefijo.toLowerCase();
			cEstatusM = "Modificar registro de " + cPrefijo.toLowerCase();
			cEstatusC = "Cancelar cambios al registro de "
					+ cPrefijo.toLowerCase();
			cEstatusI = "Imprimir";
			cEstatusR = "Generar Reporte";
			if (lInactivar == false)
				cEstatusE = "Eliminar registro de " + cPrefijo.toLowerCase();
			else
				cEstatusE = "Inactivar registro de " + cPrefijo.toLowerCase();
		}
	}

	/**
	 * Este m�todo se encarga de modificar los valores de habilitado o no de los
	 * botones de acuerdo a la acci�n que se haya elegido. Tomando un
	 * comportamiento est�ndar.
	 * 
	 * @param cEstado
	 *            Estado que se desea asignar a los botones, puede ser uno de
	 *            los siguientes: <li>UpdateBegin = Nuevo, modificar y eliminar
	 *            inactivos, el resto activos. <li>UpdateComplete = Nuevo,
	 *            modificar y eliminar activos, el resto inactivos. <li>AddOnly
	 *            = Solo el bot�n nuevo activo, el resto inactivos. <li>
	 *            AllEnabled = Todos los botones activos.
	 */
	public void setButtons(String cEstado) {
		if (cEstado == "UpdateBegin") {
			lNuevo = lModificar = lEliminar = false;
			lGuardar = lCancelar = true;
		}
		if (cEstado == "UpdateComplete") {
			lNuevo = lModificar = lEliminar = true;
			lGuardar = lCancelar = false;
		}
		if (cEstado == "AddOnly") {
			lNuevo = true;
			lGuardar = lModificar = lCancelar = lEliminar = false;
		}
		if (cEstado == "SaveOnly") {
			lGuardar = true;
			lNuevo = lModificar = lCancelar = lEliminar = false;
		}
		if (cEstado == "UpdateOnly") {
			lModificar = true;
			lNuevo = lGuardar = lCancelar = lEliminar = false;
		}
		if (cEstado == "SaveCancelOnly") {
			lGuardar = lCancelar = true;
			lNuevo = lModificar = lEliminar = false;
		}
		if (cEstado == "DeleteOnly") {
			lEliminar = lImprimir = true;
			lNuevo = lModificar = lGuardar = lCancelar = false;
		}
		if (cEstado == "AllEnabled") {
			lNuevo = lGuardar = lModificar = lCancelar = lEliminar = lImprimir = lReporte = true;
		}
	}

	/**
	 * M�todo que se encarga de inhabilitar todos los botones del panel.
	 */
	public void setInactivo() {
		lNuevo = lGuardar = lModificar = lCancelar = lEliminar = lImprimir = false;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lNuevo.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lNuevo.
	 */
	public void setNuevo(boolean lValor) {
		lNuevo = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lGuardar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lGuardar.
	 */
	public void setGuardar(boolean lValor) {
		lGuardar = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lModificar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lModificar.
	 */
	public void setModificar(boolean lValor) {
		lModificar = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lCancelar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lCancelar.
	 */
	public void setCancelar(boolean lValor) {
		lCancelar = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lEliminar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lEliminar.
	 */
	public void setEliminar(boolean lValor) {
		lEliminar = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lImprimir.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lImprimir.
	 */
	public void setImprimir(boolean lValor) {
		lImprimir = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lReporte.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lReporte.
	 */
	public void setReporte(boolean lValor) {
		lReporte = lValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo lInactivar,
	 * cambiando tambi�n el action y estatus para inactivar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lInactivar.
	 */
	public void setInactivar(boolean lValor) {
		lInactivar = lValor;
		if (lInactivar == true) {
			cActionE = "Inactivar";
			cEstatusE = "Inactivar registro";
		}
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionN.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionN.
	 */
	public void setActionN(String cValor) {
		cActionN = cValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionG.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionG.
	 */
	public void setActionG(String cValor) {
		cActionG = cValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionM.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionM.
	 */
	public void setActionM(String cValor) {
		cActionM = cValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionC.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionC.
	 */
	public void setActionC(String cValor) {
		cActionC = cValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionE.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionE.
	 */
	public void setActionE(String cValor) {
		cActionE = cValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionI.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionI.
	 */
	public void setActionI(String cValor) {
		cActionI = cValor;
	}

	/**
	 * M�todo que se encarga de asignar el valor al atributo cActionR.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionR.
	 */
	public void setActionR(String cValor) {
		cActionR = cValor;
	}

}