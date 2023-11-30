package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase que obtiene la extensi�n de un tipo de im�gen solicitado.
 * <p>Ingenier�a de Software generada en JAVA (J2EE).
 * <p>Esta clase se encarga de regresar la extensi�n para un tipo de im�gen dado.
 * @version 1.0
 * <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 * <p>
 * </dd>
 * <p></p><DT><strong>Diagrama de Clase:</strong>
 * <p><img src="TTipoImagen.png">
 */

public class TTipoImagen {

	/**
	 * Constructor por omisi�n. Uso: new TTipoImagen();
	 */
	public TTipoImagen() {
	}

	/**
	 * Este m�todo se encarga de regresar la cadena correspondiente a la
	 * extensi�n del tipo de im�gen solicitado.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuraci�n del JSP.
	 * @param iTipoImg
	 *            Tipo de imagen del cual se desea obtener la extensi�n.
	 * @return Extensi�n correspondiente al tipo de im�gen solicitado.
	 */
	public String clsTipoImagen(TEntorno vEntorno, int iTipoImg) {
		return vEntorno.getTiposImg().elementAt(iTipoImg).toString();
	}
}