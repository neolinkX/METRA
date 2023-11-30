package com.micper.ingsw;

/**
 * Generaci�n de script para vincular un archivo .JS al JSP.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo de JavaScript necesario para
 * vincular el archivo especificado al JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
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
	 * que llam� a esta clase
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor por omisi�n uso: new TFuncionJS()
	 */
	public TFuncionJS() {
	}

	/**
	 * Este constructor se encarga de generar el texto necesario para que un JSP
	 * vincule un archivo .JS con funciones de JavaScript.
	 * 
	 * @param NumModulo
	 *            N�mero del m�dulo del cual se tomar�n los par�metros de
	 *            configuraci�n.
	 * @param cArchFuncs
	 *            Nombre del archivo de funciones que se desea vincular.
	 * @param vParametros
	 *            Nombre del par�metro de configuraci�n que indica la ruta donde
	 *            se encuentran los archivos de funci�n.
	 */
	public TFuncionJS(int NumModulo, String cArchFuncs, TParametro vParametros) {
		sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\" SRC=\"");
		sbResultado.append(vParametros.getPropEspecifica("RutaFuncs"));
		sbResultado.append(vParametros.getPropEspecifica(cArchFuncs));
		sbResultado.append("\"></SCRIPT>");
	}

	/**
	 * Este m�todo se encarga de devolver el valor del buffer generado en el
	 * constructor.
	 * 
	 * @return Valor del buffer previamente generado.
	 */
	public StringBuffer getFuncionJS() {
		return sbResultado;
	}
}