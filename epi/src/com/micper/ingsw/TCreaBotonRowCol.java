package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Despliegue de botones en una tabla en 'n' renglones.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo de HTML necesario para desplegar un
 * vector de botones con sus respectivas caracter�sticas en una tabla, estos
 * botones ser�n desplegados en el n�mero de renglones que le indiquemos. <br>
 * Se generar�n los tag de renglon y columna autom�ticamente, pero deber�
 * llamarse dentro de un tag de tabla.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
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
	/** Buffer que contendr� el almacenamiento de HTML para el despliegue. */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Este constructor se encarga de generar el HTML para el despliegue de
	 * botones, acumulando el resultado en un buffer para despu�s regresarlo a
	 * quien lo solicite a trav�s del m�todo get.
	 * 
	 * @param cAlinea
	 *            Alineaci�n que deseamos para el vector de botones. Las
	 *            alineaciones v�lidas son: ('IzqDer' o 'DerIzq')
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuraci�n del m�dulo y
	 *            el JSP
	 * @param iTipoBtn
	 *            Tipo de botones que se emplear� en el despliegue (mas
	 *            informaci�n en com.micper.ingws.TCreaBoton)
	 * @param cBotones
	 *            Vector que contiene los nombres de los botones a desplegar
	 * @param iNumRows
	 *            N�mero de renglones en los que se desea desplegar el vector de
	 *            botones
	 * @param cUrls
	 *            Vector de los URL correspondientes de cada bot�n contenido en
	 *            cBotones
	 * @param cEstatus
	 *            Vector de los mensajes a desplegar en la barra de estado
	 * @param lActivos
	 *            Vector que indica que botones se desplegar�n en modo activo y
	 *            cuales en modo inactivo
	 * @param vParametros
	 *            Objeto que contiene los par�metros de configuraci�n del m�dulo
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
	 * Este m�todo se encarga de devolver el HTML generado en el constructor.
	 * 
	 * @return Buffer con texto HTML generado en el constructor
	 */
	public StringBuffer getResultado() {
		return sbResultado;
	}
}