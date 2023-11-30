package com.micper.ingsw;

/**
 * Clase encargada de controlar las características de un panel de
 * actualización.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TShowUpdPanel.html">TShowUpdPanel</a></small>
 * @see <small><a
 *      href="./../ingsample/pg0700005CFG.html">com.micper.ingsample.pg0700005CFG
 *      </a></small>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700005.jsp');">Click
 *      para mas información</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TUpdPanel.png">
 */

public class TUpdPanel {
	/** Conserva si se debe mostrar o no el panel de navegación. */
	boolean lVisible;
	/** Indica si se habilita o no el botón nuveo. */
	boolean lNuevo;
	/** Indica si se habilita o no el botón guardar. */
	boolean lGuardar;
	/** Indica si se habilita o no el botón modificar. */
	boolean lModificar;
	/** Indica si se habilita o no el botón cancelar. */
	boolean lCancelar;
	/** Indica si se habilita o no el botón eliminar. */
	boolean lEliminar;
	/** Indica si se habilita o no el botón inactivar. */
	boolean lInactivar;
	/** Indica si se habilita o no el botón imprimir. */
	boolean lImprimir;
	/** Indica si se habilita o no el botón reporte. */
	boolean lReporte;
	/** Almacena el texto a desplegar en la barra de estado para el botón nuevo. */
	String cEstatusN;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el botón
	 * guardar.
	 */
	String cEstatusG;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el botón
	 * modificar.
	 */
	String cEstatusM;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el botón
	 * cancelar.
	 */
	String cEstatusC;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el botón
	 * eliminar.
	 */
	String cEstatusE;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el botón
	 * imprimir.
	 */
	String cEstatusI;
	/**
	 * Almacena el texto a desplegar en la barra de estado para el botón
	 * reporte.
	 */
	String cEstatusR;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * nuevo.
	 */
	String cActionN;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * guardar.
	 */
	String cActionG;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * modificar.
	 */
	String cActionM;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * cancelar.
	 */
	String cActionC;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * eliminar.
	 */
	String cActionE;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * imprimir.
	 */
	String cActionI;
	/**
	 * Almacena la acción que se asignará al campo hdBoton para el botón de
	 * Reporte.
	 */
	String cActionR;

	/**
	 * Constructor por omisión. Uso: new TUpdPanel(); <br>
	 * Este constructor se encarga de inicializar los valores de las variables,
	 * mismos que podrán modificarse posteriormente por medio de los métodos
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
	 * Método que se encarga de regresar el valor del atributo lVisible.
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
	 * Este método se encarga de modificar los valores de habilitado o no de los
	 * botones de acuerdo a la acción que se haya elegido. Tomando un
	 * comportamiento estándar.
	 * 
	 * @param cEstado
	 *            Estado que se desea asignar a los botones, puede ser uno de
	 *            los siguientes: <li>UpdateBegin = Nuevo, modificar y eliminar
	 *            inactivos, el resto activos. <li>UpdateComplete = Nuevo,
	 *            modificar y eliminar activos, el resto inactivos. <li>AddOnly
	 *            = Solo el botón nuevo activo, el resto inactivos. <li>
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
	 * Método que se encarga de inhabilitar todos los botones del panel.
	 */
	public void setInactivo() {
		lNuevo = lGuardar = lModificar = lCancelar = lEliminar = lImprimir = false;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lNuevo.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lNuevo.
	 */
	public void setNuevo(boolean lValor) {
		lNuevo = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lGuardar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lGuardar.
	 */
	public void setGuardar(boolean lValor) {
		lGuardar = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lModificar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lModificar.
	 */
	public void setModificar(boolean lValor) {
		lModificar = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lCancelar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lCancelar.
	 */
	public void setCancelar(boolean lValor) {
		lCancelar = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lEliminar.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lEliminar.
	 */
	public void setEliminar(boolean lValor) {
		lEliminar = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lImprimir.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lImprimir.
	 */
	public void setImprimir(boolean lValor) {
		lImprimir = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lReporte.
	 * 
	 * @param lValor
	 *            Valor que se desea asignar al atributo lReporte.
	 */
	public void setReporte(boolean lValor) {
		lReporte = lValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo lInactivar,
	 * cambiando también el action y estatus para inactivar.
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
	 * Método que se encarga de asignar el valor al atributo cActionN.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionN.
	 */
	public void setActionN(String cValor) {
		cActionN = cValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo cActionG.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionG.
	 */
	public void setActionG(String cValor) {
		cActionG = cValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo cActionM.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionM.
	 */
	public void setActionM(String cValor) {
		cActionM = cValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo cActionC.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionC.
	 */
	public void setActionC(String cValor) {
		cActionC = cValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo cActionE.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionE.
	 */
	public void setActionE(String cValor) {
		cActionE = cValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo cActionI.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionI.
	 */
	public void setActionI(String cValor) {
		cActionI = cValor;
	}

	/**
	 * Método que se encarga de asignar el valor al atributo cActionR.
	 * 
	 * @param cValor
	 *            Valor que se desea asignar al atributo cActionR.
	 */
	public void setActionR(String cValor) {
		cActionR = cValor;
	}

}