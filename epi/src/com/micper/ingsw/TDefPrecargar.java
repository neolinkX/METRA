package com.micper.ingsw;

import java.util.*;

/**
 * Definici�n de funci�n de precarga en base a los elementos acumulados.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo de JavaScript necesario para
 * realizar el llamado a la funci�n de precarga de imagenes por cada una de
 * las imagenes que se encuentren en el vector de imagenes acumuladas, adem�s
 * de efectuar el llamado de la funci�n una sola vez por JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-(*.jsp)');">Click para
 *      ver lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TDefPrecargar.png">
 */

public class TDefPrecargar {
	/** Buffer que contendr� el almacenamiento de HTML para el despliegue. */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Este constructor genera las funciones de JavaScript que se emplear�n en
	 * los JSP.
	 * 
	 * @param ListaImgs
	 *            Vector que contiene las imagenes que se fueron acumulando en
	 *            el JSP
	 */
	public TDefPrecargar(Vector ListaImgs) {
		if (!ListaImgs.isEmpty()) {
			Enumeration eListaImgs = ListaImgs.elements();
			sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\">\n");
			/*
			 * Funcion que define la precarga de im�genes y sus llamados
			 * respectivos
			 */
			sbResultado.append("  function fCargaImagenes(){\n");
			if (ListaImgs.size() > 0) {
				sbResultado.append("    if (top.fPreCargarImagen){\n");
				while (eListaImgs.hasMoreElements()) {
					sbResultado.append("    top.fPreCargarImagen(document,\"");
					sbResultado.append((String) eListaImgs.nextElement());
					sbResultado.append("\");\n");
				}
				sbResultado.append("    }\n");
			}
			sbResultado.append("  }\n\n");
			sbResultado.append("  fCargaImagenes();\n\n");
			sbResultado.append("</SCRIPT>");
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
