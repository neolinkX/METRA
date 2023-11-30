package com.micper.ingsw;

import com.micper.util.*;

/**
 * Esta clase se encarga de cargar en el entorno de JAVA los mensajes de error.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de obtener los mensajes de error gen�ricos desde el
 * archivo errores.properties. Estos mensajes se cargan una sola vez y se dejan
 * en memoria para que puedan ser utilizados m�ltiples veces.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TError.html">TError</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TListaErr.png">
 */

public class TListaErr {
	/** Variable que contendr� todos los atributos del archivo de propiedades. */
	static private TFileProperties vErrores = null;
	/** Variable que contiene el nombre del archivo de propiedades a cargar. */
	private static final String APP_CONFIG_FILE = "errores";

	/**
	 * Constructor que se encarga de cargar los valores del archivo
	 * errores.properties en un objeto est�tico.
	 */
	public TListaErr() {
		try {
			vErrores = new TFileProperties(APP_CONFIG_FILE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * M�todo que se encarga de hacer la recarga del archivo errores.properties.
	 * 
	 * @param forceReload
	 *            Indica con un true si se desea obligar la recarga de errores o
	 *            false para solo cargar si no se ha hecho una vez.
	 */
	public void reload(boolean forceReload) {
		vErrores.reload(forceReload);
	}

	/**
	 * M�todo que se encarga de buscar el valor asociado a un nombre de
	 * propiedad (en este caso, n�mero de error).
	 * 
	 * @param cProp
	 *            Nombre del atributo a recuperar, en este caso n�mero de error
	 *            del que se desea obtener el mensaje.
	 * @return Mensaje asociado al n�mero de error proporcionado como par�metro.
	 */
	public String getProp(String cProp) {
		String cValor = vErrores.getProperty(cProp);
		if (cValor == null)
			cValor = "";
		return cValor;
	}
}