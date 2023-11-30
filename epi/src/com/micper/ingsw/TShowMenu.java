package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase para generaci�n de men� en HTML.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo HTML necesario para desplegar un
 * men�. <br>
 * Las opciones se generar�n dentro de una tabla, por lo tanto es necesario
 * ubicar el llamado dentro del JSP entre los tags &ltTABLE&gt y &lt/TABLE&gt
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="./../ingsample/pg0700003MAIN.html">pg0700003MAIN</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TShowMenu.png">
 */

public class TShowMenu {

	/**
	 * Constructor por omisi�n. Uso: new TShowMenu();
	 */
	public TShowMenu() {
	}

	/**
	 * Este m�todo se encarga de crear cada una de las opciones de primer nivel
	 * del men�. La creaci�n de opciones se hace en una tabla, generando un
	 * nuevo rengl�n.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuraci�n del JSP.
	 * @param cOpc
	 *            Opci�n que se desplegar� (o nombre de im�gen que debe
	 *            existir).
	 * @param cDesc
	 *            Descripci�n que se mostrar� al usuario (solo se pasa para
	 *            otros tipos de men�).
	 * @param cRef
	 *            Referencia que tendr� la opci�n en el bot�n. Si esta es #, se
	 *            desplegar� una imagen de flecha hacia abajo.
	 * @param cVar
	 *            Nombre de la variable que se asignar� al bot�n, para poder
	 *            controlar el cambio de imagen.
	 * @param cImg
	 *            Nombre de la imagen que se desea desplegar en la opci�n de
	 *            men�.
	 * @param cBoton2
	 *            Indica si se ha solicitado o no un submen�, de tal modo que
	 *            puedan controlarse el n�mero de columnas a expandir en la
	 *            celda.
	 * @param cInforDef
	 *            Nombre de la im�gen informativa (la que aparece a la derecha
	 *            del men�).
	 * @param vParametros
	 *            Objeto que contiene los par�metros de configuraci�n de la
	 *            aplicaci�n.
	 * @return Cadena que se debe desplegar en el JSP.
	 */
	public StringBuffer createMenu(TEntorno vEntorno, String cOpc,
			String cDesc, String cRef, String cVar, String cImg,
			String cBoton2, String cInforDef, TParametro vParametros) {
		StringBuffer sbRes = new StringBuffer();
		String cEstatus = "Muestra la p�gina solicitada ";
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		int iCols = 3;
		if (cBoton2 == null)
			cBoton2 = "";
		if (cBoton2.compareTo("") == 0)
			iCols--;
		if (cRef.toUpperCase().compareTo("#") == 0)
			cEstatus = "Muestra el submen� solicitado";
		else
			cRef = "JavaScript: fCargarPag('" + cRef + "');";
		sbRes.append("<tr><td><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
		sbRes.append("<tr Align=\"left\" vAlign=\"middle\"><td vAlign=\"middle\" class=\"menu\" colspan=\""
				+ iCols + "\">\n");
		sbRes.append(new TCreaBoton().clsCreaBotonMenu(vEntorno, 1, cVar, cImg,
				0, cEstatus, cOpc, cInforDef + "01", cRef, vParametros));
		sbRes.append("</td>");
		if (cRef.toUpperCase().compareTo("#") == 0)
			sbRes.append("<td><img name=\"-\" border=\"0\" src=\"" + cRutaImg
					+ "bFlecha01.gif\"></td>");
		else
			sbRes.append("<td><img name=\"-\" border=\"0\" src=\"" + cRutaImg
					+ "bFlecha03.gif\"></td>");
		sbRes.append("</tr></table></td></tr>");
		return sbRes;
	}

	/**
	 * Este m�todo se encarga de crear cada una de las opciones de segundo nivel
	 * del men�. La creaci�n de opciones se hace en una tabla, generando un
	 * nuevo rengl�n.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuraci�n del JSP.
	 * @param cOpc
	 *            Opci�n que se desplegar� (o nombre de im�gen que debe
	 *            existir).
	 * @param cSubOpc
	 *            Valor de la SubOpci�n de men� que se eligi�, para conservar el
	 *            valor en campo oculto.
	 * @param cDesc
	 *            Descripci�n que se mostrar� al usuario (solo se pasa para
	 *            otros tipos de men�).
	 * @param cRef
	 *            Referencia que tendr� la opci�n en el bot�n. Si esta es #, se
	 *            desplegar� una imagen de flecha hacia abajo.
	 * @param cVar
	 *            Nombre de la variable que se asignar� al bot�n, para poder
	 *            controlar el cambio de imagen.
	 * @param cBoton2
	 *            Indica si se ha solicitado o no un submen�, de tal modo que
	 *            puedan controlarse el n�mero de columnas a expandir en la
	 *            celda.
	 * @param cImg
	 *            Nombre de la imagen que se desea desplegar en la opci�n de
	 *            men�.
	 * @param cInforDef
	 *            Nombre de la im�gen informativa (la que aparece a la derecha
	 *            del men�).
	 * @param vParametros
	 *            Objeto que contiene los par�metros de configuraci�n de la
	 *            aplicaci�n.
	 * @return Cadena que se debe desplegar en el JSP.
	 */
	public StringBuffer createSubMenu(TEntorno vEntorno, String cOpc,
			String cSubOpc, String cDesc, String cRef, String cVar,
			String cBoton2, String cImg, String cInforDef,
			TParametro vParametros) {
		StringBuffer sbRes = new StringBuffer();
		String cRefDest = cRef;
		String cEstatus = "Muestra la p�gina solicitada";
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg()
				.elementAt(vEntorno.getTipoImg()).toString();
		int iCols = 2;
		if (cBoton2 == null)
			cBoton2 = "";
		if (cBoton2.compareTo("") == 0)
			iCols--;
		// sbRes.append("  <tr vAlign=\"middle\" Align=\"left\" onMouseOver=\"if(top.fCambiaImagen)top.fCambiaImagen(document, 'bInforma','','"
		// + cRutaImg + cImg + "03" + cTipoImg +
		// "',1);\" onMouseOut=\"if(top.fCambiaImagen)top.fCambiaImagen(document, 'bInforma','','"
		// + cRutaImg + cInforDef + "01" + cTipoImg +
		// "',1);\"><td width=\"10px\"></td><td style=\"padding-left: 15px\" colspan=\""
		// + iCols + "\">\n");
		sbRes.append("<tr\"><td style=\"padding-left: 15px\" colspan=\""
				+ iCols + "\">\n");
		if (cRef.toUpperCase().compareTo("#") == 0) {
			cRef = "JavaScript: document.forms[0].hdMenu.value='" + cOpc
					+ "';document.forms[0].hdSubMenu.value='" + cSubOpc
					+ "';document.forms[0].submit();";
			cEstatus = "Muestra el submen� solicitado";
		} else
			cRef = "JavaScript: fCargarPag('" + cRef + "','" + cDesc + "');";
		sbRes.append(new TEtiCampo().clsAnclaTexto("include", cDesc, cRef,
				cEstatus, cVar));
		sbRes.append("</td>");
		sbRes.append("<td>");
		if (cRefDest.toUpperCase().compareTo("#") == 0)
			sbRes.append("<img name=\"-\" border=\"0\" src=\"" + cRutaImg
					+ "bFlecha02.gif\">");
		sbRes.append("</td>");
		sbRes.append("</tr>");
		return sbRes;
	}

	/**
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuraci�n del JSP.
	 * @param cDesc
	 *            Descripci�n que se mostrar� al usuario (solo se pasa para
	 *            otros tipos de men�).
	 * @param cRef
	 *            Referencia que tendr� la opci�n en el bot�n. Si esta es #, se
	 *            desplegar� una imagen de flecha hacia abajo.
	 * @param cVar
	 *            Nombre de la variable que se asignar� al bot�n, para poder
	 *            controlar el cambio de imagen.
	 * @param cImg
	 *            Nombre de la imagen que se desea desplegar en la opci�n de
	 *            men�.
	 * @param cInforDef
	 *            Nombre de la im�gen informativa (la que aparece a la derecha
	 *            del men�).
	 * @param vParametros
	 *            Objeto que contiene los par�metros de configuraci�n de la
	 *            aplicaci�n.
	 * @return Cadena que se debe desplegar en el JSP.
	 */
	public StringBuffer createSubSubMenu(TEntorno vEntorno, String cDesc,
			String cRef, String cVar, String cImg, String cInforDef,
			TParametro vParametros) {
		StringBuffer sbRes = new StringBuffer();
		String cRutaImg = vParametros.getPropEspecifica("RutaImg");
		String cTipoImg = vEntorno.getTiposImg()
				.elementAt(vEntorno.getTipoImg()).toString();
		// sbRes.append("  <tr vAlign=\"left\" onMouseOver=\"if(top.fCambiaImagen)top.fCambiaImagen(document,'bInforma','','"
		// + cRutaImg + cImg + "03" + cTipoImg +
		// "',1);\" onMouseOut=\"if(top.fCambiaImagen)top.fCambiaImagen(document,'bInforma','','"
		// + cRutaImg + cInforDef + "01" + cTipoImg +
		// "',1);\"><td width=\"10px\"></td><td width=\"10px\"></td><td style=\"padding-left: 20px\">\n");
		sbRes.append("<tr\"><td width=\"10px\"></td><td style=\"padding-left: 20px\">\n");
		cRef = "JavaScript: fCargarPag('" + cRef + "','" + cDesc + "');";
		sbRes.append(new TEtiCampo().clsAnclaTexto("include", cDesc, cRef,
				"Muestra la p�gina solicitada ", cVar));
		sbRes.append("</td>");
		sbRes.append("</tr>");
		return sbRes;
	}
}