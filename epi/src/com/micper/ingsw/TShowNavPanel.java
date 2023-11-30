package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase para desplegar los elementos de un panel de navegación.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código HTML necesario para desplegar un
 * panel de navegación. <br>
 * En este caso se generará desde el tag &ltTABLE&gt, por lo tanto se puede
 * hacer el llamado en cualquier parte de un JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700006.jsp');">Click
 *      para mas información</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TShowNavPanel.png">
 */

public class TShowNavPanel {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llamó a esta clase.
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor que se encarga de generar el HTML para desplegar los botones
	 * del panel de navegación.
	 * 
	 * @param NavPanel
	 *            Objeto que contiene las opciones del panel con su
	 *            configuración.
	 * @param vEntorno
	 *            Objeto que contiene los parámetros de configuración del JSP.
	 * @param iNumRows
	 *            Número de renglones en los que se desea desplegar el panel.
	 * @param vParametros
	 *            Objeto que contiene los parámetros de configuración del
	 *            módulo.
	 */
	public TShowNavPanel(TNavPanel NavPanel, TEntorno vEntorno, int iNumRows,
			TParametro vParametros) {
		Vector vBotones = new Vector();
		Vector vUrls = new Vector();
		Vector vEstatus = new Vector();
		Vector lActivos = new Vector();
		if (NavPanel.lVisible == true) {
			sbResultado
					.append("<TABLE BORDER=\"0\" CELLSPACING=\"0\" CELLPADDING=\"0\" ALIGN=\"CENTER\">");
			vBotones.addElement("bPrimero");
			vUrls.addElement("JavaScript:fSubmite(document.forms[0].Primero, 'Primero')");
			vEstatus.addElement(NavPanel.cEstatusP);
			lActivos.addElement(new Boolean(NavPanel.lPrimero).toString());
			vBotones.addElement("bAnterior");
			vUrls.addElement("JavaScript:fSubmite(document.forms[0].Anterior, 'Anterior')");
			vEstatus.addElement(NavPanel.cEstatusA);
			lActivos.addElement(new Boolean(NavPanel.lAnterior).toString());
			vBotones.addElement("bSiguiente");
			vUrls.addElement("JavaScript:fSubmite(document.forms[0].Siguiente, 'Siguiente')");
			vEstatus.addElement(NavPanel.cEstatusS);
			lActivos.addElement(new Boolean(NavPanel.lSiguiente).toString());
			vBotones.addElement("bUltimo");
			vUrls.addElement("JavaScript:fSubmite(document.forms[0].Ultimo, 'Ultimo')");
			vEstatus.addElement(NavPanel.cEstatusU);
			lActivos.addElement(new Boolean(NavPanel.lUltimo).toString());
			sbResultado.append(new TCreaBotonRowCol("IzqDer", vEntorno, 6,
					vBotones, iNumRows, vUrls, vEstatus, lActivos, vParametros)
					.getResultado());
			sbResultado.append("</TABLE>");
		}
	}

	/**
	 * Método encargado de devolver el HTML generado por el constructor.
	 * 
	 * @return Buffer de datos que debe mostrarse en el JSP.
	 */
	public StringBuffer getResultado() {
		return sbResultado;
	}
}