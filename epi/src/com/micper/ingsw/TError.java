package com.micper.ingsw;

import java.util.*;
import java.lang.*;

/**
 * Clase para acumulaci?n y despliegue de errores.
 * <p>
 * Ingenier?a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de acumular en el entorno del JSP, las im?genes que se
 * desplegar?n posteriormente en una funci?n de precarga de im?genes.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog?a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-(*.jsp)\n-pg07XXXXXCFG.java\n-TValidaAcceso.java');"
 *      >Click para mas informaci?n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TError.png">
 */

public class TError {
	/**
	 * Vector para acumular los textos que van antes del n?mero de mensaje
	 * est?ndar.
	 */
	Vector cVErrAntes;
	/** Vector para acumular los n?meros de mensaje est?ndar. */
	Vector iVNumError;
	/**
	 * Vector para acumular los textos que van despu?s del n?mero de mensaje
	 * est?nda.r
	 */
	Vector cVErrDespues;

	/**
	 * Constructor por omisi?n en donde se inicializan los vectores que se
	 * emplear?n para acumular errores.
	 */
	public TError() {
		cVErrAntes = new Vector();
		iVNumError = new Vector();
		cVErrDespues = new Vector();
	}

	/**
	 * Este m?todo se encarga de acumular los valores enviados por el usuario en
	 * los vectores destinados para acumulaci?n.
	 * 
	 * @param sVAntes
	 *            Cadena que se acumular? en el vector de textos previos.
	 * @param iVNumero
	 *            N?mero de mensaje est?ndar que se acumular? en el vector de
	 *            n?meros de mensaje.
	 * @param sVDespues
	 *            Cadena que se acumular? en el vector de textos posteriores.
	 */
	public void acumulaError(String sVAntes, int iVNumero, String sVDespues) {
		Integer iVNum = new Integer(iVNumero);
		try {
			cVErrAntes.addElement(sVAntes);
			iVNumError.addElement(iVNum);
			cVErrDespues.addElement(sVDespues);
		} catch (Exception exc) {
			// Cualquier excepci?n la despliega con ShowException
			this.errorConsola(exc, "clsError.acumulaError");
		}
	}

	/**
	 * Se encarga de crear la cadena que ser? desplegada por JavaScript en un
	 * alert. Acumula la cantidad de errores y luego cada uno de los errores con
	 * su respectivo texto anterior y posterior, despu?s de cada error acumular?
	 * un caracter de nueva l?nea. <br>
	 * Finalmente se crear? el Script de Java para el despliegue del mensaje
	 * creado.
	 * 
	 * @return Cadena que se desplegar? en un alert de JavaScript.
	 */
	public StringBuffer muestraError() {
		Enumeration eErrorAntes = cVErrAntes.elements();
		Enumeration eErrores = iVNumError.elements();
		Enumeration eErrorDesp = cVErrDespues.elements();
		StringBuffer sbErrores = new StringBuffer();
		StringBuffer sbResultado = new StringBuffer();
		TListaErr vListaErr = new TListaErr();
		try {
			if (cVErrAntes.size() > 0) {
				// Si existen errores acumulados los genera como una cadena
				sbErrores.append(iVNumError.size());
				sbErrores.append(" mensaje(s) generado(s) en la página:");
				while (eErrores.hasMoreElements()) {
					sbErrores.append("\\n- ");
					sbErrores.append((String) eErrorAntes.nextElement() + " ");
					String datError = vListaErr.getProp(eErrores.nextElement()
							.toString());
					if (datError.compareTo("0") != 0)
						sbErrores.append(datError);
					sbErrores.append((String) " " + eErrorDesp.nextElement());
				}
			}
		} catch (Exception exc) {
			// Cualquier excepci?n la despliega con ShowException
			this.errorConsola(exc, "clsError.muestraError");
		}
		if (sbErrores.length() > 0) {
			// Se genera el c?digo HTML que genera el script de despliegue de
			// mensajes
			sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\">\n");
			sbResultado.append("  window.alert(\"" + sbErrores.toString()
					+ "\");\n");
			sbResultado.append("</SCRIPT>\n");
		}
		return sbResultado;
	}



	/**
	 * Se encarga de crear la cadena que ser? desplegada por JavaScript en un
	 * alert. Acumula la cantidad de errores y luego cada uno de los errores con
	 * su respectivo texto anterior y posterior, despu?s de cada error acumular?
	 * un caracter de nueva l?nea. <br>
	 * Finalmente se crear? el Script de Java para el despliegue del mensaje
	 * creado.
	 * 
	 * @return Cadena que se desplegar? en un alert de JavaScript.
	 */
	public StringBuffer muestraError2() {
		Enumeration eErrorAntes = cVErrAntes.elements();
		Enumeration eErrores = iVNumError.elements();
		Enumeration eErrorDesp = cVErrDespues.elements();
		StringBuffer sbErrores = new StringBuffer();
		StringBuffer sbErroresInicio = new StringBuffer();
		StringBuffer sbResultado = new StringBuffer();
		TListaErr vListaErr = new TListaErr();
		String error = "";
		String error2 = "";
		int count= 0;
		try {
			if (cVErrAntes.size() > 0) {
				// Si existen errores acumulados los genera como una cadena
				//sbErroresInicio.append(iVNumError.size());
				sbErroresInicio.append("Mensaje(s) generado(s) en la página:");
				while (eErrores.hasMoreElements()) {
					error= ""+(String) eErrorAntes.nextElement() + " "+eErrores.nextElement()
					.toString()+" "+eErrorDesp.nextElement();
					
					if(!error.equals(error2)){
						sbErrores.append("\\n- ");
						sbErrores.append((String) eErrorAntes.nextElement() + " ");
						String datError = vListaErr.getProp(eErrores.nextElement()
								.toString());
						if (datError.compareTo("0") != 0)
							sbErrores.append(datError);
						sbErrores.append((String) " " + eErrorDesp.nextElement());
						count++;
					}
					
					error2= ""+(String) eErrorAntes.nextElement() + " "+eErrores.nextElement()
					.toString()+" "+eErrorDesp.nextElement();
				}
			}
		} catch (Exception exc) {
			// Cualquier excepci?n la despliega con ShowException
			this.errorConsola(exc, "clsError.muestraError");
		}
		if (sbErrores.length() > 0) {
			// Se genera el c?digo HTML que genera el script de despliegue de
			// mensajes
			sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\">\n");
			sbResultado.append("  window.alert(\"" + sbErroresInicio.toString() +" " +sbErrores.toString()
					+ "\");\n");
			sbResultado.append("</SCRIPT>\n");
		}
		return sbResultado;
	}

	
	
	/**
	 * Este m?todo nos indica si existe un error acumulado o no.
	 * 
	 * @return true si se ha acumulado un error, false en caso contrario.
	 */
	public boolean hasErrors() {
		if (cVErrAntes.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Despliega un mensaje de error por una excepci?n en la consola, seguido de
	 * su descripci?n.
	 * 
	 * @param exc
	 *            Objeto Excepci?n que se gener? y atrap? en un bloque
	 *            try{}catch{}
	 * @param cClase
	 *            Cadena que identifica la clase que dispar? la impresi?n en la
	 *            consola para efectos de seguimiento.
	 */
	public void errorConsola(Exception exc, String cClase) {
		// System.out.println("Error ocurrido en la clase: " + cClase);
		System.out.print(exc.getMessage());
		System.out.print(" - ");
		// System.out.println(exc.toString());
	}

	/**
	 * Mensaje que se desea desplegar en la consola. Permite desplegar valores
	 * para efectos de seguimiento.
	 * 
	 * @param cMensaje
	 *            Mensaje que se desea desplegar en la consola.
	 */
	public void mensajeConsola(String cMensaje) {
		// System.out.println("Mensaje: " + cMensaje);
	}
}