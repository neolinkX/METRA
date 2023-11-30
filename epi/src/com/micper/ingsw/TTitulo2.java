package com.micper.ingsw;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Clase que genera los elementos de una pï¿½gina de tï¿½tulo.
 * <p>
 * Ingenierï¿½a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el HTML necesario para desplegar un tï¿½tulo
 * en un JSP. <br>
 * Un tï¿½tulo consta de una imï¿½gen con un URL acerca de, un texto, y un
 * conjunto de botones principales.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnologï¿½a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700001.jsp');">Click
 *      para mas informaciï¿½n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TTitulo.png">
 */

public class TTitulo2 {
	/**
	 * Constructor por omisiï¿½n. Uso: new TTitulo();
	 */
	public TTitulo2() {
	}

	/**
	 * Constructor que se encarga de generar el HTML del tï¿½tulo, para lo cual
	 * se genera una imï¿½gen con URL del acerca de del sistema, un texto que
	 * corresponde al tï¿½tulo del sistema y un grupo de botones de acciï¿½n
	 * principales.
	 * 
	 * @param pc
	 *            Contexto en el cuï¿½l se ejecuta el JSP.
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuracion del JSP.
	 * @param cBotonesPrin
	 *            Vector que contiene los botones principales a desplegar.
	 * @param iNumRowsPrin
	 *            Nï¿½mero de renglones en los que se desea desplegar el panel
	 *            de botones.
	 * @param cUrlsPrin
	 *            Vector que contiene el URL de cada botï¿½n a desplegar en el
	 *            panel.
	 * @param cEstatusPrin
	 *            Vector con el texto a desplegar en la barra de estado para
	 *            cada botï¿½n.
	 * @param lVisible
	 *            Indica si se debe mostrar o no el panel de botones.
	 * @param vParametros
	 *            Objeto que contiene los parï¿½metros de configuracion del
	 *            mï¿½dulo.
	 * @throws IOException
	 *             Se debe indicar esta excepciï¿½n cuando se trabaja con la
	 *             salida directa (out).
	 */
	public TTitulo2(PageContext pc, TEntorno vEntorno, Vector cBotonesPrin,
			int iNumRowsPrin, Vector cUrlsPrin, Vector cEstatusPrin,
			boolean lVisible, TParametro vParametros) throws IOException {
		JspWriter out = pc.getOut();
		int iNumModulo = vEntorno.getNumModulo();
		String cUrlLogo = vEntorno.getUrlLogo();
		String cStatusLogo = vEntorno.getBarraEdoLogo();
		String cTitulo = vEntorno.getTituloPagina();
		Vector lActivosPrin = new Vector();
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();

		if (cBotonesPrin.isEmpty()) {
			// cBotonesPrin.addElement("bMenu");
			// cBotonesPrin.addElement("bAyuda");
			cBotonesPrin.addElement("bSalir");
		}
		if (cEstatusPrin.isEmpty()) {
			// cEstatusPrin.addElement("Muestra el MenÃº del Sistema");
			// cEstatusPrin.addElement("Muestra la Ayuda de la Pantalla");
			cEstatusPrin.addElement("Termina con la sesión del sistema");
		}
		if (cUrlsPrin.isEmpty()) {
			// cUrlsPrin.addElement("JavaScript:fMenu();");
			// cUrlsPrin.addElement("JavaScript:fAyuda();");
			cUrlsPrin.addElement(vParametros.getPropEspecifica("Salir"));
		}
		for (int i = 1; i <= cBotonesPrin.toArray().length; i++)
			lActivosPrin.addElement("true");

		// se define la tabla que contiene todo el encabezado
		if (lVisible == true) {
			out.print("<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"ETablaST\" background=\""
					+ cRutaImg + "relleno.jpg" + "\">\n");
		} else {
			out.print("<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" class=\"ETablaST\" background=\""
					+ cRutaImg + "fondoTab" + cTipoImg + "\">\n");
		}
		out.print("  <tr>\n");
		// Primera celda, logotipo de empresa con llamado al acerca de
		out.print("    <td class=\"ELogoTSist\" valign=\"middle\" width=\"13%\">\n");
		// Prueba de cambio de logo
		// out.print(new TCreaBoton().clsCreaBoton(vEntorno, 3, "Acerca",
		// "Logo", 0, "Acerca de...","JavaScript:fAcerca();", vParametros));
		if (lVisible == true) {
			out.print(new TCreaBoton().clsCreaBoton(vEntorno, 7,
					"Acceso al sistema", "pleca01", 1, "Acceso al sistema...",
					"sinAccion", vParametros));
		}
		// ----------------------------------------------------------------------------------
		out.print("</td>\n");
		// Segunda celda, tï¿½tulo general del sistema
		out.print("    <td valign=\"middle\" class=\"ETituloTSist\">"
				+ vParametros.getPropEspecifica("TituloGral") + "</td>\n");
		// Tercer celda, despliegue de tabla con botones de accion principales
		out.print("    <td class=\"ETituloTSistBtn\" valign=\"middle\">\n");
		if (lVisible == true) {
			out.print("      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\">\n");
			// tabla de despliegue de botones de menu principal
			out.print(new TCreaBotonRowCol("DerIzq", vEntorno, 3, cBotonesPrin,
					iNumRowsPrin, cUrlsPrin, cEstatusPrin, lActivosPrin,
					vParametros).getResultado());
			out.print("      </table>\n");
		} else
			out.print("&nbsp;");
		out.print("</td>\n");
		out.print("  </tr>\n");
		out.print("</table>\n");
	}
}