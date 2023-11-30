package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase para desplegar un panel de actualización.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el HTML necesario para crear un panel de los
 * botones de actualización dentro de una tabla.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700005.jsp');">Click
 *      para mas información</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TShowUpdPanel.png">
 */

public class TShowUpdPanel {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llamó a esta clase.
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor encargado de generar la tabla con los botones del panel de
	 * actualización de acuerdo al objeto UpdPanel recibido.
	 * 
	 * @param UpdPanel
	 *            Objeto que contiene los valores de los botones del panel de
	 *            actualización.
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del JSP.
	 * @param iNumRows
	 *            Número de renglones en los que se desea desplegar el panel.
	 * @param vParametros
	 *            Objeto que contiene los valores de configuración del módulo.
	 */
	public TShowUpdPanel(TUpdPanel UpdPanel, TEntorno vEntorno, int iNumRows,
			TParametro vParametros) {
		Vector vBotones = new Vector();
		Vector vUrls = new Vector();
		Vector vEstatus = new Vector();
		Vector lActivos = new Vector();
		if (UpdPanel.lVisible == true) {
			sbResultado
					.append("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\" ALIGN=\"CENTER\">");
			vBotones.addElement("bNuevo");
			vUrls.addElement(UpdPanel.cActionN);
			vEstatus.addElement(UpdPanel.cEstatusN);
			lActivos.addElement(new Boolean(UpdPanel.lNuevo).toString());
			vBotones.addElement("bGuardar");
			vUrls.addElement(UpdPanel.cActionG);
			vEstatus.addElement(UpdPanel.cEstatusG);
			lActivos.addElement(new Boolean(UpdPanel.lGuardar).toString());
			vBotones.addElement("bActualizar");
			vUrls.addElement(UpdPanel.cActionM);
			vEstatus.addElement(UpdPanel.cEstatusM);
			lActivos.addElement(new Boolean(UpdPanel.lModificar).toString());
			vBotones.addElement("bCancelar");
			vUrls.addElement(UpdPanel.cActionC);
			vEstatus.addElement(UpdPanel.cEstatusC);
			lActivos.addElement(new Boolean(UpdPanel.lCancelar).toString());
			if (UpdPanel.lInactivar == true)
				vBotones.addElement("bBorrar");
			else
				vBotones.addElement("bBorrarB");
			vUrls.addElement(UpdPanel.cActionE);
			vEstatus.addElement(UpdPanel.cEstatusE);
			lActivos.addElement(new Boolean(UpdPanel.lEliminar).toString());
			vBotones.addElement("bImprimir");
			vUrls.addElement(UpdPanel.cActionI);
			vEstatus.addElement(UpdPanel.cEstatusI);
			lActivos.addElement(new Boolean(UpdPanel.lImprimir).toString());
			vBotones.addElement("bReporte");
			vUrls.addElement(UpdPanel.cActionR);
			vEstatus.addElement(UpdPanel.cEstatusR);
			lActivos.addElement(new Boolean(UpdPanel.lReporte).toString());
			sbResultado.append(new TCreaBotonRowCol("IzqDer", vEntorno, 6,
					vBotones, iNumRows, vUrls, vEstatus, lActivos, vParametros)
					.getResultado());
			sbResultado.append("</TABLE>");
		}
	}

	/**
	 * Este método devuelve el texto acumulado en el buffer por el constructor.
	 * 
	 * @return Buffer de datos previamente generado por el constructor.
	 */
	public StringBuffer getResultado() {
		return sbResultado;
	}
}