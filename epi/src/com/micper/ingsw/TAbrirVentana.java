package com.micper.ingsw;

//import java.lang.*;
import java.util.*;

/**
 * Función JavaScript para abrir nueva ventana.
 * <p>
 * Ingeniería de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el código de JavaScript necesario para abrir
 * una ventana en base a las características que se vayan seleccionando. <br>
 * El JavaScript generado es una función que puede llamarse desde cualquier
 * lugar del JSP generado.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TTiulo.html">TTitulo</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TAbrirVentana.png">
 */

public class TAbrirVentana {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llamó a esta clase.
	 */
	StringBuffer sbResultado = new StringBuffer();

	/**
	 * Constructor por omisión uso: new TAbrirVentana()
	 */
	public TAbrirVentana() {
	}

	/**
	 * Este método se encarga de obtener un valor yes/no de acuerdo al
	 * solicitado true/false, para poder emplearse de acuerdo a como lo requiere
	 * JavaScript en la función window.open.
	 * 
	 * @param cCad
	 *            Valor true o false que se desea convertir a yes/no para su uso
	 *            en window.open de JavaScript
	 *            <p>
	 * @return Valor yes/no, dependiendo de lo que se haya enviado en cCad
	 *         <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Función open del objeto window</a>
	 */
	public String convYesNo(String cCad) {
		if (cCad.toLowerCase().compareTo("true") == 0)
			return "yes";
		if (cCad.toLowerCase().compareTo("false") == 0)
			return "no";
		return "no";
	}

	/**
	 * Este método se encarga de generar en JavaScript una función que abre una
	 * ventana del navegador con el nombre y características especificados.
	 * 
	 * @param cNomFunc
	 *            Nombre de la función de JavaScript a generar
	 * @param cNomPagina
	 *            Nombre de la página a cargar en la nueva ventana
	 * @param cNomWindow
	 *            Nombre del objeto window que se genera, para uso en JavaScript
	 * @param lDependiente
	 *            Indica si se desea que la ventana se cierre al cerrar la
	 *            ventana padre
	 * @param lHotKeys
	 *            Indica si se desea habilitar comandos rápidos en la ventana
	 *            que se abre
	 * @param lLocationBar
	 *            Indica si se desea mostrar o no el URL en la barra de
	 *            direcciones del navegador
	 * @param lMenuBar
	 *            Indica si se desea mostrar una barra de menú o no
	 * @param lPersonalBar
	 *            Indica si se desea mostrar una barra de accesos personalizados
	 *            o no
	 * @param lResizable
	 *            Indica si se desea que la nueva ventana pueda ser cambiada de
	 *            tamaño por el usuario o no
	 * @param lScrollBars
	 *            Indica si se desean barras de desplazamiento o no en la nueva
	 *            ventana
	 * @param lStatusBar
	 *            Indica si se desea mostrar una barra de estado o no en la
	 *            nueva ventana
	 * @param lTitleBar
	 *            Indica si se desea mostrar o no una barra de título en la
	 *            nueva ventana
	 * @param lToolBar
	 *            Indica si se desea mostrar o no una barra de herramientas en
	 *            la nueva ventana
	 * @param lMaximized
	 *            Indica si se desea que la nueva ventana aparezca maximizada o
	 *            restaurada
	 * @param iWidth
	 *            Indica el ancho que se le dará a la nueva ventana
	 * @param iHeight
	 *            Indica el alto que se le dará a la nueva ventana
	 * @param iPosX
	 *            Indica la posición en X de la nueva ventana
	 * @param iPosY
	 *            Indica la posición en Y de la nueva ventana
	 *            <p>
	 * @return HTML con el código de JavaScript de la función generada
	 *         <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Función open del objeto window</a>
	 */
	public StringBuffer abrirVentana(String cNomFunc, String cNomPagina,
			String cNomWindow, boolean lDependiente, boolean lHotKeys,
			boolean lLocationBar, boolean lMenuBar, boolean lPersonalBar,
			boolean lResizable, boolean lScrollBars, boolean lStatusBar,
			boolean lTitleBar, boolean lToolBar, boolean lMaximized,
			int iWidth, int iHeight, int iPosX, int iPosY) {
		sbResultado = new StringBuffer();
		StringBuffer sbOtrasCar = new StringBuffer();
		Integer iAncho = new Integer(iWidth);
		Integer iAlto = new Integer(iHeight);
		Integer iXPos = new Integer(iPosX);
		Integer iYPos = new Integer(iPosY);
		sbOtrasCar.append("dependent="
				+ this.convYesNo(new Boolean(lDependiente).toString()));
		sbOtrasCar.append(",hotKeys="
				+ this.convYesNo(new Boolean(lHotKeys).toString()));
		sbOtrasCar.append(",location="
				+ this.convYesNo(new Boolean(lLocationBar).toString()));
		sbOtrasCar.append(",menubar="
				+ this.convYesNo(new Boolean(lMenuBar).toString()));
		sbOtrasCar.append(",personalbar="
				+ this.convYesNo(new Boolean(lPersonalBar).toString()));
		sbOtrasCar.append(",resizable="
				+ this.convYesNo(new Boolean(lResizable).toString()));
		sbOtrasCar.append(",scrollbars="
				+ this.convYesNo(new Boolean(lScrollBars).toString()));
		sbOtrasCar.append(",status="
				+ this.convYesNo(new Boolean(lStatusBar).toString()));
		sbOtrasCar.append(",titlebar="
				+ this.convYesNo(new Boolean(lTitleBar).toString()));
		sbOtrasCar.append(",toolbar="
				+ this.convYesNo(new Boolean(lToolBar).toString()));
		if (lMaximized == false) {
			sbOtrasCar.append(",width=" + iAncho.toString());
			sbOtrasCar.append(",height=" + iAlto.toString());
			sbOtrasCar.append(",screenX=" + iXPos.toString());
			sbOtrasCar.append(",screenY=" + iYPos.toString());
		}
		sbResultado.append("<SCRIPT LANGUAGE=\"JavaScript\">\n");
		sbResultado.append("  function " + cNomFunc + "() {\n");
		sbResultado.append("    wExp = open(" + cNomPagina + ",\"" + cNomWindow
				+ "\",\"" + sbOtrasCar + "\");\n");
		if (lMaximized == true) {
			sbResultado
					.append("    wExp.moveTo(window.screenX, window.screenY);\n");
			sbResultado.append("    wExp.resizeTo(750, 550);\n");
		}
		sbResultado.append("    wExp.focus();\n");
		sbResultado.append("  }\n");
		sbResultado.append("</SCRIPT>");
		return sbResultado;
	}

	/**
	 * Este método se encarga de generar de crear una ventana de nombre fAyuda,
	 * esta es empleada por el logotipo para llamar la ventana de ayuda que
	 * indiquen las diferentes páginas del módulo.
	 * 
	 * @return HTML con el código de JavaScript de la función fAyuda()
	 *         <p>
	 * @see <a
	 *      href="JavaScript:alert('Consulte un manual de JavaScript');">JavaScript
	 *      : Función open del objeto window</a>
	 */
	public StringBuffer ventanaAyuda() {
		return this.abrirVentana("fAyuda", "document.forms[0].hdAyuda.value",
				"Ayuda", true, false, false, false, false, true, true, true,
				true, false, false, 700, 450, 0, 0);
	}
}