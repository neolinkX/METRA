package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.ingsw.*;

/**
 * Clase encargada de controlar los botones y la configuracion del panel
 * especial.
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700008.jsp');">Click
 *      para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="pg0700008CFG.png">
 */

public class pg0700008CFG {
	/**
	 * Constructor general de la clase.
	 */
	public pg0700008CFG() {
	}

	/**
	 * Metodo encargado de inicializar los valores de configuracion del JSP.
	 * 
	 * @param vEntorno
	 *            Objeto con los valores de configuracion del entorno del JSP.
	 * @param vBotones
	 *            Objeto que controla los botones que se van a presentar.
	 * @param vUrls
	 *            Objeto que controla los Url's de los botones.
	 * @param vEstatus
	 *            Objeto que controla los mensajes en la barra de estado de cada
	 *            uno de los botones.
	 * @param lActivos
	 *            Objeto que controlar� si est�n o no activos los botones.
	 */
	public void runConfig(TEntorno vEntorno, Vector vBotones, Vector vUrls,
			Vector vEstatus, Vector lActivos) {
		// Se asignan valores a los diferentes elementos del entorno general
		vEntorno.setTituloVentana("Administraci�n y Seguridad para JSP - Acceso");
		vEntorno.setArchAyuda("0700008");
		vEntorno.setNumModulo(07);
		vEntorno.setArchFuncs("FValida");
		vEntorno.setArchTooltips("07_Tooltips");
		vEntorno.clearListaImgs();
		vEntorno.setMetodoForm("POST");
		vEntorno.setActionForm("pg0700008.jsp");
		vEntorno.setOnLoad("fOnLoad();");
		vEntorno.setUrlLogo("Acerca");
		// Control de botones a desplegar
		vBotones.addElement("bImprimir");
		vUrls.addElement("JavaScript:fSubmite(document.forms[0].Imprimir, 'Imprimir')");
		vEstatus.addElement("Imprimir pantalla");
		lActivos.addElement("true");

		vBotones.addElement("bInstructor");
		vUrls.addElement("JavaScript:fSubmite(document.forms[0].Instructor, 'Instructor')");
		vEstatus.addElement("Mostrar instructores");
		lActivos.addElement("true");

		vBotones.addElement("bRegistrar");
		vUrls.addElement("JavaScript:fSubmite(document.forms[0].Registrar, 'Registrar')");
		vEstatus.addElement("Registrar informaci�n");
		lActivos.addElement("true");

	}

	/**
	 * Metodo que se ejecuta desde el JSP en la secci�n &lt;HEAD&gt;.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuracion del JSP.
	 * @param vErrores
	 *            Objeto que permite la acumulaci�n de errores para su
	 *            despliegue.
	 * @param pc
	 *            Contexto del JSP.
	 * @param httpReq
	 *            Petici�n de HTTP en la que se encuentra el JSP.
	 * @param httpResp
	 *            Respuesta de HTTP en la que se encuentra el JSP.
	 * @throws IOException
	 * @throws ServletException
	 */
	public void outputHeader(TEntorno vEntorno, TError vErrores,
			PageContext pc, HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws IOException, ServletException {
		JspWriter out = pc.getOut();
		ServletRequest request = pc.getRequest();
		if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
		}
		if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
		}
	}
}