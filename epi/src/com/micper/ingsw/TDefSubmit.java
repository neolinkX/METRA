package com.micper.ingsw;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

/**
 * Definición de funciones JavaScript y campos para manejo de Submit y Menú.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de definir los campos ocultos y funciones para el
 * manejo del submit de los botones tipo imagen. Además define los campos para
 * el manejo del menú y submenú.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-(*.jsp)');">Click para
 *      ver lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TDefSubmit.png">
 */

public class TDefSubmit {

	/**
	 * Este constructor genera el HTML de campos ocultos y función de submit, la
	 * salida es directa en el JSP.
	 * 
	 * @param pc
	 *            Contexto del JSP para poder obtener el dispositivo de salida
	 *            para escribir.
	 */
	public TDefSubmit(PageContext pc) throws IOException {
		JspWriter out = pc.getOut();
		// Se define el campo oculto para asignar valor de accion antes de
		// submitir
		out.print("<input type=\"hidden\" name=\"hdBoton\" value=\"\">\n");
		out.print("<input type=\"hidden\" name=\"hdIni\" value=\"\">\n");
		out.print("<input type=\"hidden\" name=\"hdMenu\" value=\"\">\n");
		out.print("<input type=\"hidden\" name=\"hdSubMenu\" value=\"\">\n");
		out.print("              <SCRIPT LANGUAGE=\"JavaScript\">\n");
		// Define la funcion de submitir, se llama a fValidaTodo si esta
		// definida
		out.print("                function fSubmitForm(theButton) {\n");
		out.print("                  document.forms[0].hdBoton.value = theButton;\n");
		out.print("                  document.forms[0].target=\"_self\";\n");
		out.print("                  if (window.fValidaTodo)\n");
		out.print("                    lSubmitir = fValidaTodo();\n");
		out.print("                  else\n");
		out.print("                    lSubmitir = true;\n");
		out.print("                  if (lSubmitir){\n");
		out.print("                 ");
		out.print("                    document.forms[0].submit();\n");
		out.print("                  }else\n");
		out.print("                    document.forms[0].hdBoton.value = \"\";\n");
		out.print("                }\n");
		out.print("              </SCRIPT>");
	}
}