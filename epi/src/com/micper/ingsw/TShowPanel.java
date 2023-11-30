package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase para desplegar un panel de botones.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el HTML necesario para crear un panel de
 * botones dentro de una tabla.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg0700007.jsp\n-pg0700008.jsp');"
 *      >Click para mas información</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TShowPanel.png">
 */

public class TShowPanel {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llamó a esta clase.
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor encargado de generar la tabla con los botones del panel
	 * configurado en los vectores.
	 * 
	 * @param lVisible
	 *            Indica si el panel debe mostrarse o no en su estado inicial.
	 * @param vBotones
	 *            Vector que contiene los valores de los botones a desplegar.
	 * @param vUrls
	 *            Vector que contiene los URL de cada uno de los botones a
	 *            desplegar.
	 * @param vEstatus
	 *            Vector que contiene los estatus de cada uno de los botones a
	 *            desplegar.
	 * @param lActivos
	 *            Vector que indica el estado de activo o inactivo de cada uno
	 *            de los botones a desplegar.
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del JSP.
	 * @param iNumRows
	 *            Número de renglones en los que se desea desplegar el panel.
	 * @param vParametros
	 *            Objeto que contiene los valores de configuración del módulo.
	 */
	public TShowPanel(boolean lVisible, Vector vBotones, Vector vUrls,
			Vector vEstatus, Vector lActivos, TEntorno vEntorno, int iNumRows,
			TParametro vParametros) {
		if (lVisible == true) {
			sbResultado
					.append("<TABLE BORDER=\"0\" CELLSPACING=\"1\" CELLPADDING=\"1\" ALIGN=\"CENTER\">");
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