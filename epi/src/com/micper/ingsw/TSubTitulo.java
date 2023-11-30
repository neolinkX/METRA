package com.micper.ingsw;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Clase que se encarga de desplegar una barra de subtítulo con sus botones de
 * acción.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código HTML necesario para crear una barra
 * de subtítulo, en la cual se incluye un nombre de módulo, un nombre de página
 * y los botones de acción deseados.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg07XXXXX.jsp de acuerdo al diagrama de clases');"
 *      >Click para mas información</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TSubTitulo.png">
 */

public class TSubTitulo {

	/**
	 * Constructor por omisión. Uso: new TSubTitulo();
	 */
	public TSubTitulo() {
	}

	/**
	 * Este constructor se encarga de generar el HTML necesario para mostrar una
	 * barra de subtítulo dentro del JSP. <br>
	 * El subtítulo se compone del nombre del módulo, un nombre de página y una
	 * barra de botones de acción a la derecha (catálogo, listado, menú, etc.).
	 * 
	 * @param pc
	 *            Contexto en el que se ejecuta el JSP.
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del JSP.
	 * @param cBotones
	 *            Vector que contiene los botones a desplegar en la barra de
	 *            subtítulo.
	 * @param iNumRowsSec
	 *            Número de renglones en los que se desea desplegar los botones.
	 * @param cUrls
	 *            Vector que contiene el URL de cada botón a desplegar en la
	 *            barra.
	 * @param cEstatus
	 *            Vector que contiene el texto a desplegar en la barra de estado
	 *            para cada uno de los botones.
	 * @param vParametros
	 *            Objeto que contiene los parámetros de configuración del
	 *            módulo.
	 * @throws IOException
	 *             Necesario indicar esta excepción cuando se trabaja con salida
	 *             directa (out).
	 */
	public TSubTitulo(PageContext pc, TEntorno vEntorno, Vector cBotones,
			int iNumRowsSec, Vector cUrls, Vector cEstatus,
			TParametro vParametros) throws IOException {
		JspWriter out = pc.getOut();
		int iNumModulo = vEntorno.getNumModulo();
		Vector lActivosSec = new Vector();
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();

		for (int i = 1; i <= cBotones.toArray().length; i++)
			lActivosSec.addElement("true");
		// se define la tabla que contiene todo el subtítulo
		out.print("<table height=\"26\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"ETablaST\" background=\""
				+ cRutaImg + "fondoSTitulo" + cTipoImg + "\">\n");
		out.print("  <tr>\n");
		// Segundo renglon, primer celda, nombre del módulo mas título de página
		out.print("    <td colspan=\"2\" class=\"ETituloTPag\" align=\"left\">\n");
		out.print("&nbsp;" + vParametros.getPropEspecifica("NomModulo"));
		out.print(" - " + vEntorno.getTituloPagina());
		out.print("</td>\n");
		// Segundo renglon, segunda celda, despliegue de tabla con botones de
		// accion secundarios
		out.print("    <td class=\"ETituloTPag\" align=\"right\" valign=\"bottom\">\n");
		out.print("      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n");
		// tabla de despliegue de botones de menu secundario
		out.print(new TCreaBotonRowCol("DerIzq", vEntorno, 3, cBotones,
				iNumRowsSec, cUrls, cEstatus, lActivosSec, vParametros)
				.getResultado());
		out.print("      </table>\n");
		out.print("</td><td>&nbsp;</td>\n");
		out.print("  </tr>\n");
		out.print("</table>\n");
	}
}