package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.ingsw.*;

/**
 * Clase utilizada para controlar el panel que no tiene botones.
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700007.jsp');">Click
 *      para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="pg0700007CFG.png">
 */

public class pg0700007CFG {
	/**
	 * Constructor general de la clase.
	 */
	public pg0700007CFG() {
	}

	/**
	 * Método encargado de inicializar los valores de configuración del JSP.
	 * 
	 * @param vEntorno
	 *            Objeto con los valores de configuración del entorno del JSP.
	 * @param vBotones
	 *            Objeto que controlará los botones en caso de existir.
	 * @param vUrls
	 *            Objeto que controlará los Url's en caso de existir botones.
	 * @param vEstatus
	 *            Objeto que controlará los mensajes en la barra de estado en
	 *            caso de existir botones.
	 * @param lActivos
	 *            Objeto que controlará si están o no activos los botones.
	 */
	public void runConfig(TEntorno vEntorno, Vector vBotones, Vector vUrls,
			Vector vEstatus, Vector lActivos) {
		// Se asignan valores a los diferentes elementos del entorno general
		vEntorno.setTituloVentana("Administración y Seguridad para JSP - Acceso");
		vEntorno.setArchAyuda("0700001");
		vEntorno.setNumModulo(07);
		vEntorno.setArchFuncs("FValida");
		vEntorno.setArchTooltips("07_Tooltips");
		vEntorno.clearListaImgs();
		vEntorno.setMetodoForm("POST");
		vEntorno.setActionForm("pg0700007.jsp");
		vEntorno.setOnLoad("fOnLoad();");
		vEntorno.setUrlLogo("Acerca");
		// Control de botones a desplegar 1 bloque de 4 líneas por cada botón
		// vBotones.addElement("bImprimir");
		// vUrls.addElement("JavaScript:fSubmite(document.forms[0].Imprimir, 'Imprimir')");
		// vEstatus.addElement("Imprimir");
		// lActivos.addElement("true");
	}

	/**
	 * Método que se ejecuta desde el JSP en la sección &lt;HEAD&gt;.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuración del JSP.
	 * @param vErrores
	 *            Objeto que permite la acumulación de errores para su
	 *            despliegue.
	 * @param pc
	 *            Contexto del JSP.
	 * @param httpReq
	 *            Petición de HTTP en la que se encuentra el JSP.
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