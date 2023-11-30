package com.micper.ingsw;

/**
 * Generación de script para vincular un archivo .JS al JSP.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código de JavaScript necesario para
 * vincular el archivo especificado al JSP.
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
 *      <img src="TFuncionJS.png">
 */

public class TFuncionJS {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llamó a esta clase
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor por omisión uso: new TFuncionJS()
	 */
	public TFuncionJS() {
	}

	/**
	 * Este constructor se encarga de generar el texto necesario para que un JSP
	 * vincule un archivo .JS con funciones de JavaScript.
	 * 
	 * @param NumModulo
	 *            Número del módulo del cual se tomarán los parámetros de
	 *            configuración.
	 * @param cArchFuncs
	 *            Nombre del archivo de funciones que se desea vincular.
	 * @param vParametros
	 *            Nombre del parámetro de configuración que indica la ruta donde
	 *            se encuentran los archivos de función.
	 */
	public TFuncionJS(int NumModulo, String cArchFuncs, TParametro vParametros) {
		sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\" SRC=\"");
		sbResultado.append(vParametros.getPropEspecifica("RutaFuncs"));
		sbResultado.append(vParametros.getPropEspecifica(cArchFuncs));
		sbResultado.append("\"></SCRIPT>");
	}

	/**
	 * Este método se encarga de devolver el valor del buffer generado en el
	 * constructor.
	 * 
	 * @return Valor del buffer previamente generado.
	 */
	public StringBuffer getFuncionJS() {
		return sbResultado;
	}
}