package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase que obtiene la extensión de un tipo de imágen solicitado.
 * <p>Ingeniería de Software generada en JAVA (J2EE).
 * <p>Esta clase se encarga de regresar la extensión para un tipo de imágen dado.
 * @version 1.0
 * <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 * <p>
 * </dd>
 * <p></p><DT><strong>Diagrama de Clase:</strong>
 * <p><img src="TTipoImagen.png">
 */

public class TTipoImagen {

	/**
	 * Constructor por omisión. Uso: new TTipoImagen();
	 */
	public TTipoImagen() {
	}

	/**
	 * Este método se encarga de regresar la cadena correspondiente a la
	 * extensión del tipo de imágen solicitado.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del JSP.
	 * @param iTipoImg
	 *            Tipo de imagen del cual se desea obtener la extensión.
	 * @return Extensión correspondiente al tipo de imágen solicitado.
	 */
	public String clsTipoImagen(TEntorno vEntorno, int iTipoImg) {
		return vEntorno.getTiposImg().elementAt(iTipoImg).toString();
	}
}