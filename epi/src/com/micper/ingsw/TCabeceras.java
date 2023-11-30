package com.micper.ingsw;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

/**
 * Definici�n de encabezado gen�rico.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de generar el encabezado gen�rico en las p�ginas JSP
 * que se generen en cualquier m�dulo.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte cualquier JSP para ver su uso');"
 *      >Ingenier�a de Software (*.jsp)</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCabeceras.png">
 */

public class TCabeceras {
	/**
	 * Dispositivo donde se escribe todo el HTML generado como encabezado de los
	 * JSP
	 */
	JspWriter out;

	/**
	 * Constructor por omisi�n, inicializa el valor de la variable out
	 */
	public TCabeceras(PageContext pc) throws IOException {
		out = pc.getOut();
	}

	/**
	 * Este m�todo se encarga de incluir las funciones de JavaScript para
	 * validaci�n, as� como de crear las funciones gen�ricas e inclusi�n de
	 * Tooltips.
	 * 
	 * @param vEntorno
	 *            Contiene los valores del Entorno para correr los JSP
	 * @param vParametros
	 *            Contiene los valores de los par�metros de configuraci�n
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Inclusi�n de librer�as de JavaScript</a>
	 */
	public void TCabeceras(TEntorno vEntorno, TParametro vParametros)
			throws IOException {
		out.print(new TFuncionJS(vEntorno.getNumModulo(), vEntorno
				.getArchFuncs(), vParametros).getFuncionJS() + "\n");
		out.print(new TTooltips(vEntorno, vParametros).getResultado());
	}

	/**
	 * Este m�todo se encarga de incluir las funciones de JavaScript para
	 * validaci�n, as� como de crear las funciones gen�ricas e inclusi�n de
	 * Tooltips, adicionalmente incluir� las funciones de validaci�n de
	 * cat�logos ubicadas en un JS diferente.
	 * 
	 * @param vEntorno
	 *            Contiene los valores del Entorno para correr los JSP
	 * @param vParametros
	 *            Contiene los valores de los par�metros de configuraci�n
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Inclusi�n de librer�as de JavaScript</a>
	 */
	public void TCabecerasCat(TEntorno vEntorno, TParametro vParametros)
			throws IOException {
		this.TCabeceras(vEntorno, vParametros);
		out.print(new TFuncionJS(vEntorno.getNumModulo(), vEntorno
				.getArchFCatalogo(), vParametros).getFuncionJS() + "\n");
	}
}