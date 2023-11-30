package com.micper.ingsw;

/**
 * Definici�n de funciones JavaScript generales.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo de JavaScript necesario para no
 * permitir que el usuario emplee el bot�n derecho del rat�n en el navegador. <br>
 * El JavaScript generado es una funci�n que puede llamarse desde cualquier
 * lugar del JSP generado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TCabeceras.html">TCabeceras</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TCreaFuncion.png">
 */

public class TCreaFuncion {
	/** Buffer que contendr� el almacenamiento de HTML para el despliegue. */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Este constructor genera las funciones de JavaScript que se emplear�n en
	 * los JSP.
	 * 
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Manejo de Eventos del mouse</a>
	 */
	public TCreaFuncion() {
		sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\">\n");
		/* Funcion para no permitir el bot�n derecho en el navegador */
		sbResultado
				.append("  var cMsg = \"Lo sentimos, operaci�n no permitida\";\n");
		sbResultado.append("  function fNoRightClick(e){\n");
		sbResultado.append("    if (document.all){\n");
		sbResultado.append("      if (Event.button == 2){\n");
		sbResultado.append("        alert(cMsg);\n");
		sbResultado.append("        return false;\n");
		sbResultado.append("      }\n");
		sbResultado.append("    }\n");
		sbResultado.append("    if (document.layers){\n");
		sbResultado.append("      if (Event.which == 3){\n");
		sbResultado.append("        alert(cMsg);\n");
		sbResultado.append("        return false;\n");
		sbResultado.append("      }\n");
		sbResultado.append("    }\n");
		sbResultado.append("  }\n\n");
		sbResultado.append("  if (document.layers){\n");
		sbResultado.append("    document.captureEvents(Event.MOUSEDOWN);\n");
		sbResultado.append("  }\n\n");
		sbResultado.append("  document.onmousedown = fNoRightClick();\n\n");
		sbResultado.append("</SCRIPT>");
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