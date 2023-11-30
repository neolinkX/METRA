package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.ingsw.*;

/**
 * Clase utilizada para configurar el jsp que maneja el panel de navegaci�n
 * general. y el comportamiento est�ndar.
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700006.jsp');">Click
 *      para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="pg0700006CFG.png">
 */

public class pg0700006CFG {
	/**
	 * Constructor principal de la clase.
	 */
	public pg0700006CFG() {
	}

	/**
	 * Metodo encargado de inicializar los valores de configuracion del JSP.
	 * 
	 * @param vEntorno
	 *            Objeto con los valores de configuracion del entorno del JSP.
	 * @param navPanel
	 *            Objeto con los valores de configuracion del Panel de
	 *            navegaci�n.
	 */
	public void runConfig(TEntorno vEntorno, TNavPanel navPanel) {
		// Se asignan valores a los diferentes elementos del entorno general
		vEntorno.setTituloVentana("Administraci�n y Seguridad para JSP - Acceso");
		vEntorno.setArchAyuda("0700001");
		vEntorno.setNumModulo(07);
		vEntorno.setArchFuncs("FValida");
		vEntorno.setArchTooltips("07_Tooltips");
		vEntorno.clearListaImgs();
		vEntorno.setMetodoForm("POST");
		vEntorno.setActionForm("pg0700006.jsp");
		vEntorno.setOnLoad("fOnLoad();");
		vEntorno.setUrlLogo("Acerca");
		navPanel.setVisible(true);
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

	/*
	 * public void controlNavPanel(){ // Aqui est� su control del panel de
	 * navegaci�n NavPanel.setPrimero(true); // Estatus de bot�n Primero
	 * NavPanel.setAnterior(true); // Estatus de bot�n Anterior
	 * NavPanel.setSiguiente(true); // Estatus de bot�n Siguiente
	 * NavPanel.setUltimo(true); // Estatus de bot�n Ultimo
	 * NavPanel.setButtons(false,false,true,true); // Coloca todos los estatus
	 * // NavPanel.setInactivo(); // Inactiva todo el panel
	 * NavPanel.setVisible(true); // Indica si debe mostrar o no el panel de
	 * navegaci�n NavPanel.setPrefijo("Cliente"); // Nombre de prefijo de
	 * navegaci�n }
	 */
}