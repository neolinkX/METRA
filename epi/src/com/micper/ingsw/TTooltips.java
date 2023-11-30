package com.micper.ingsw;

/**
 * Clase para vincular archivos de tooltips y funciones para su manejo.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código de JavaScript necesario para
 * vincular un archivo de tooltips al JSP. Además genera las funciones de
 * JavaScript que se emplean en los campos para poder desplegar el tooltip en la
 * barra de estado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TCabeceras.html">TCabeceras</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TTooltips.png">
 */

public class TTooltips {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llamó a esta clase.
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor que se encarga de generar el HTML para vincular el archivo y
	 * crear funciones de JavaScript.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del JSP.
	 * @param vParametros
	 *            Objeto que contiene los valores de configuración del módulo.
	 */
	public TTooltips(TEntorno vEntorno, TParametro vParametros) {
		sbResultado.append("<SCRIPT language=\"JavaScript\" src=\"");
		sbResultado.append(vParametros.getPropEspecifica("RutaFuncs"));
		sbResultado.append("t" + vEntorno.getArchTooltips() + ".js\">");
		sbResultado.append("</SCRIPT>\n");
	}

	/**
	 * Método encargado de devolver el valor del buffer que se llenó en el
	 * constructor.
	 * 
	 * @return Valor del buffer previamente generado en el constructor.
	 */
	public StringBuffer getResultado() {
		return sbResultado;
	}
}