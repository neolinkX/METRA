package com.micper.ingsw;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

/**
 * Definición de encabezado genérico.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de generar el encabezado genérico en las páginas JSP
 * que se generen en cualquier módulo.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte cualquier JSP para ver su uso');"
 *      >Ingeniería de Software (*.jsp)</a></small> </dd>
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
	 * Constructor por omisión, inicializa el valor de la variable out
	 */
	public TCabeceras(PageContext pc) throws IOException {
		out = pc.getOut();
	}

	/**
	 * Este método se encarga de incluir las funciones de JavaScript para
	 * validación, así como de crear las funciones genéricas e inclusión de
	 * Tooltips.
	 * 
	 * @param vEntorno
	 *            Contiene los valores del Entorno para correr los JSP
	 * @param vParametros
	 *            Contiene los valores de los parámetros de configuración
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Inclusión de librerías de JavaScript</a>
	 */
	public void TCabeceras(TEntorno vEntorno, TParametro vParametros)
			throws IOException {
		out.print(new TFuncionJS(vEntorno.getNumModulo(), vEntorno
				.getArchFuncs(), vParametros).getFuncionJS() + "\n");
		out.print(new TTooltips(vEntorno, vParametros).getResultado());
	}

	/**
	 * Este método se encarga de incluir las funciones de JavaScript para
	 * validación, así como de crear las funciones genéricas e inclusión de
	 * Tooltips, adicionalmente incluirá las funciones de validación de
	 * catálogos ubicadas en un JS diferente.
	 * 
	 * @param vEntorno
	 *            Contiene los valores del Entorno para correr los JSP
	 * @param vParametros
	 *            Contiene los valores de los parámetros de configuración
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Inclusión de librerías de JavaScript</a>
	 */
	public void TCabecerasCat(TEntorno vEntorno, TParametro vParametros)
			throws IOException {
		this.TCabeceras(vEntorno, vParametros);
		out.print(new TFuncionJS(vEntorno.getNumModulo(), vEntorno
				.getArchFCatalogo(), vParametros).getFuncionJS() + "\n");
	}
}