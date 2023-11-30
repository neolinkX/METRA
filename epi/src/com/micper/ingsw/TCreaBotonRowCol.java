package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Despliegue de botones en una tabla en 'n' renglones.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código de HTML necesario para desplegar un
 * vector de botones con sus respectivas características en una tabla, estos
 * botones serán desplegados en el número de renglones que le indiquemos. <br>
 * Se generarán los tag de renglon y columna automáticamente, pero deberá
 * llamarse dentro de un tag de tabla.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TShowPanel.html">TShowPanel</a></small>
 * @see <small><a href="TShowUpdPanel.html">TShowUpdPanel</a></small>
 * @see <small><a href="TShowNavPanel.html">TShowNavPanel</a></small>
 * @see <small><a href="TTitulo.html">TTitulo</a></small>
 * @see <small><a href="TSubTitulo.html">TSubTitulo</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCreaBotonRowCol.png">
 */
public class TCreaBotonRowCol {
	/** Buffer que contendrá el almacenamiento de HTML para el despliegue. */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Este constructor se encarga de generar el HTML para el despliegue de
	 * botones, acumulando el resultado en un buffer para después regresarlo a
	 * quien lo solicite a través del método get.
	 * 
	 * @param cAlinea
	 *            Alineación que deseamos para el vector de botones. Las
	 *            alineaciones válidas son: ('IzqDer' o 'DerIzq')
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del módulo y
	 *            el JSP
	 * @param iTipoBtn
	 *            Tipo de botones que se empleará en el despliegue (mas
	 *            información en com.micper.ingws.TCreaBoton)
	 * @param cBotones
	 *            Vector que contiene los nombres de los botones a desplegar
	 * @param iNumRows
	 *            Número de renglones en los que se desea desplegar el vector de
	 *            botones
	 * @param cUrls
	 *            Vector de los URL correspondientes de cada botón contenido en
	 *            cBotones
	 * @param cEstatus
	 *            Vector de los mensajes a desplegar en la barra de estado
	 * @param lActivos
	 *            Vector que indica que botones se desplegarán en modo activo y
	 *            cuales en modo inactivo
	 * @param vParametros
	 *            Objeto que contiene los parámetros de configuración del módulo
	 *            <p>
	 * @see <a href="TCreaBoton.html">TCreaBoton</a>
	 * @see <a href="TCalcNumCols.html">TCalcNumCols</a>
	 */
	public TCreaBotonRowCol(String cAlinea, TEntorno vEntorno, int iTipoBtn,
			Vector cBotones, int iNumRows, Vector cUrls, Vector cEstatus,
			Vector lActivos, TParametro vParametros) {
		int iMaxCols = cBotones.toArray().length;
		TCalcNumCols vCalcNumCols;
		int iNumEmptyCols;
		int iBtnActual = 0;
		int iNumCols;
		if (iNumRows < 1)
			iNumRows = 1;
		if (iNumRows > iMaxCols)
			iNumRows = iMaxCols;
		vCalcNumCols = new TCalcNumCols(iMaxCols, iNumRows);
		iNumCols = vCalcNumCols.getNumCols();
		iNumEmptyCols = vCalcNumCols.getNumColsEmpty();
		for (int i = 1; i <= iNumRows; i++) {
			sbResultado.append("        <tr>\n");
			for (int j = 1; j <= iNumCols; j++) {
				if ((i == iNumRows) && (iNumEmptyCols > 0)
						&& (cAlinea == "DerIzq"))
					for (int k = 1; k <= iNumEmptyCols; k++, j++)
						sbResultado.append("          <td></td>\n");
				sbResultado.append("          <td>\n");
				if (iTipoBtn != 4) {
					if (lActivos.elementAt(iBtnActual).toString() == "true")
						sbResultado.append(new TCreaBoton().clsCreaBoton(
								vEntorno, iTipoBtn,
								cBotones.elementAt(iBtnActual).toString()
										.substring(1),
								cBotones.elementAt(iBtnActual).toString(),
								vEntorno.getTipoImg(),
								cEstatus.elementAt(iBtnActual).toString(),
								cUrls.elementAt(iBtnActual).toString(),
								vParametros));
					else
						sbResultado.append(new TCreaBoton().clsCreaBoton(
								vEntorno, cBotones.elementAt(iBtnActual)
										.toString().substring(1), cBotones
										.elementAt(iBtnActual).toString(),
								vEntorno.getTipoImg(), vParametros));
				} else
					sbResultado.append(new TCreaBoton().clsCreaBoton(vEntorno,
							cBotones.elementAt(iBtnActual).toString()
									.substring(1),
							cBotones.elementAt(iBtnActual).toString(),
							vEntorno.getTipoImg(), vParametros));
				iBtnActual++;
				sbResultado.append("</td>\n");
				if ((i == iNumRows) && (iNumEmptyCols > 0)
						&& (cAlinea == "IzqDer") && (iBtnActual >= iMaxCols))
					for (int k = 1; k <= iNumEmptyCols; k++, j++)
						sbResultado.append("          <td></td>\n");
			}
			sbResultado.append("        </tr>\n");
		}
	}

	/**
	 * Este método se encarga de devolver el HTML generado en el constructor.
	 * 
	 * @return Buffer con texto HTML generado en el constructor
	 */
	public StringBuffer getResultado() {
		return sbResultado;
	}
}