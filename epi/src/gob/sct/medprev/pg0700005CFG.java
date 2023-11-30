package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
//import java.util.*;
import java.lang.*;
import com.micper.ingsw.*;

/**
 * Clase de configuraci�n para la p�gina que genera el panel de actualizaci�n
 * general.
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de inicializar los par�metros necesarios para generar
 * el panel de actualizaci�n general.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700005.jsp');">Click
 *      para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="pg0700005CFG.png">
 */

public class pg0700005CFG {
	/**
	 * Constructor principal de la clase.
	 */
	public pg0700005CFG() {
	}

	/**
	 * M�todo encargado de inicializar los valores de configuraci�n del JSP.
	 * 
	 * @param vEntorno
	 *            Objeto con los valores de configuraci�n del entorno del JSP.
	 * @param UpdPanel
	 *            Objeto utilizado para configurar el panel de actualizaci�n.
	 */
	public void runConfig(TEntorno vEntorno, TUpdPanel UpdPanel) {
		// Se asignan valores a los diferentes elementos del entorno general
		vEntorno.setTituloVentana("Administraci�n y Seguridad para JSP - Acceso");
		vEntorno.setArchAyuda("0700001");
		vEntorno.setNumModulo(07);
		vEntorno.setArchFuncs("FValida");
		vEntorno.setArchTooltips("07_Tooltips");
		vEntorno.clearListaImgs();
		vEntorno.setMetodoForm("POST");
		vEntorno.setActionForm("pg0700005.jsp");
		vEntorno.setOnLoad("fOnLoad();");
		vEntorno.setUrlLogo("Acerca");
		UpdPanel.setVisible(true);
		UpdPanel.setButtons("Hidden"); // Coloca modo de actualizaci�n el panel
		UpdPanel.setActionN("JavaScript:fSubmite(document.forms[0].Nuevo, 'Nuevo')");
		UpdPanel.setActionG("JavaScript:fSubmite(document.forms[0].Guardar, document.forms[0].hdSaveAction.value)");
		UpdPanel.setActionM("JavaScript:fSubmite(document.forms[0].Actualizar, 'Modificar')");
		UpdPanel.setActionC("JavaScript:fSubmite(document.forms[0].Cancelar, 'Cancelar')");
		UpdPanel.setActionI("JavaScript:fSubmite(document.forms[0].Imprimir, 'Imprimir')");
		UpdPanel.setActionR("JavaScript:fSubmite(document.forms[0].Reporte, 'Generar Reporte')");
		UpdPanel.setActionE("JavaScript:if (document.forms[0].Borrar) fSubmite(document.forms[0].Borrar, document.forms[0].hdDeleteAction.value); else fSubmite(document.forms[0].BorrarB, document.forms[0].hdDeleteAction.value);");
	}

	/**
	 * M�todo que se ejecuta desde el JSP en la secci�n &lt;HEAD&gt;.
	 * 
	 * @param vEntorno
	 *            Objeto que contiene los valores de configuraci�n del JSP.
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