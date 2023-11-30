package com.micper.ingsw;

import java.util.*;
import javax.servlet.http.*;

import com.micper.sql.*;

/**
 * Definicion de objetos HTML con caracteristicas generales.
 * <p>
 * Ingenieria de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear el c�digo de HTML para desplegar textos y
 * campos de tal modo que se vean uniformes. El despliegue se hace en el
 * contexto de una tabla, empleando generaci�n de celdas de acuerdo a lo que se
 * requiera. <br>
 * Una vez creado un objeto se puede realizar el llamado m�ltiples veces para
 * desplegar con el mismo objeto todas las instancias necesarias.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnologia Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href="TShowMenu.html">TShowMenu</a></small>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg0700002.jsp\n-pg0700009.jsp\n-pg070201*.jsp\n-pg070301*.jsp');"
 *      >Click para m�s informaci�n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TEtiCampo.png">
 */

public class TEtiCampo {
	/**
	 * Almacena un conjunto de cadenas y posteriormente las regresa a la clase
	 * que llam� a esta clase
	 */
	StringBuffer sbRes = new StringBuffer();

	/**
	 * Constructor por omision uso: new TEtiCampo()
	 */
	public TEtiCampo() {
	}

	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de etiqueta con el estilo y textos proporcionados en
	 * parametros
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desea desplegar en la celda.
	 */
	private void Etiqueta(String cEstiloE, String cEtiqueta) {
		sbRes.append("<td class=\"" + cEstiloE + "\">" + cEtiqueta + "</td>\n");
	}

	private void Etiqueta2(String cEstiloE, String cEtiqueta) {
		sbRes.append("<td colspan=\"2\" class=\"" + cEstiloE + "\">"
				+ cEtiqueta + "</td>\n");
	}
	
	private void Etiqueta3(String cEstiloE, String cEtiqueta) {
		sbRes.append("" + cEtiqueta + "\n");
	}

	private void EtiquetaOculto(String cEstiloE, String cEtiqueta, String cName) {
		sbRes.append("<td class=\"" + cEstiloE + "\"><div id=\""+cName+"\" style=\"display:none;\">" + cEtiqueta + "</div></td>\n");
	}
	
	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de etiqueta con el estilo y textos proporcionados en
	 * parametros
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desea desplegar en la celda.
	 */
	private void EtiquetaCS(String cEstiloE, String cEtiqueta, int iSpan) {
		sbRes.append("<td class=\"" + cEstiloE + "\" colspan=\"" + iSpan
				+ "\">" + cEtiqueta + "</td>\n");
	}
	
	private void EtiquetaCSOculto(String cEstiloE, String cEtiqueta, int iSpan, String cName) {
		sbRes.append("<td class=\"" + cEstiloE + "\" colspan=\"" + iSpan
				+ "\"><div id=\""+cName+"\" style=\"display:none;\">" + cEtiqueta + "</div></td>\n");
	}

	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de campo con el estilo proporcionado, adem�s genera el
	 * campo con las caracteristicas proporcionadas.
	 * 
	 * @param cEstiloC
	 *            Estilo para la celda del campo.
	 * @param cTipo
	 *            Tipo de campo a generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se asignara al ancho del campo.
	 * @param iMaxLen
	 *            Numero m�ximo de caracteres que se permitir� de captura en un
	 *            campo.
	 * @param cName
	 *            Nombre del objeto que se generar� para su uso en JavaScript.
	 * @param cValue
	 *            Valor inicial del campo que se genera.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando el campo cambie
	 *            de valor.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del campo cuando se
	 *            ingrese al campo.
	 * @param lActivo
	 *            Indica si se debe permitir la captura o no del campo (atributo
	 *            disabled de HTML).
	 * @param lRetornoCarro
	 *            Inidca si se debe agregar un tag BR entre el campo y el ancla.
	 */
	private void Campo(String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lRetornoCarro) {
		sbRes.append("<td class=\"" + cEstiloC + "\">\n");
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (!cOnChange.equals("")) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (!cOnBlur.equals("")) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		}
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">");
		if (cName.substring(0, 2).compareTo("dt") == 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new java.util.Date());
			if (lRetornoCarro)
				sbRes.append("<br>");
			sbRes.append("<a class=\"" + "EAncla" + "\" name=\"" + "calendario"
					+ "\" href=\"" + "JavaScript:fLoadCal("
					+ calendar.get(calendar.DAY_OF_MONTH) + ","
					+ (calendar.get(calendar.MONTH) + 1) + ","
					+ calendar.get(calendar.YEAR) + "," + "document.forms[0]."
					+ cName + ");" + "\">");
			sbRes.append("(dd/mm/aaaa)");
			sbRes.append("</a>");
		}
		sbRes.append("</td>\n");
	}
	
	private void Campo2(String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lRetornoCarro) {
		//sbRes.append("<td class=\"" + cEstiloC + "\">\n");
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (!cOnChange.equals("")) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (!cOnBlur.equals("")) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		}
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">");
		if (cName.substring(0, 2).compareTo("dt") == 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new java.util.Date());
			if (lRetornoCarro)
				sbRes.append("<br>");
			sbRes.append("<a class=\"" + "EAncla" + "\" name=\"" + "calendario"
					+ "\" href=\"" + "JavaScript:fLoadCal("
					+ calendar.get(calendar.DAY_OF_MONTH) + ","
					+ (calendar.get(calendar.MONTH) + 1) + ","
					+ calendar.get(calendar.YEAR) + "," + "document.forms[0]."
					+ cName + ");" + "\">");
			sbRes.append("(dd/mm/aaaa)");
			sbRes.append("</a>");
		}
		//sbRes.append("</td>\n");
	}

	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de campo con el estilo proporcionado, adem�s genera el
	 * campo con las caracteristicas proporcionadas.
	 * 
	 * @param cEstiloC
	 *            Estilo para la celda del campo.
	 * @param cTipo
	 *            Tipo de campo a generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se asignara al ancho del campo.
	 * @param iMaxLen
	 *            Numero m�ximo de caracteres que se permitir� de captura en un
	 *            campo.
	 * @param cName
	 *            Nombre del objeto que se generar� para su uso en JavaScript.
	 * @param cValue
	 *            Valor inicial del campo que se genera.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando el campo cambie
	 *            de valor.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del campo cuando se
	 *            ingrese al campo.
	 * @param lActivo
	 *            Indica si se debe permitir la captura o no del campo (atributo
	 *            disabled de HTML).
	 */
	private void CampoCS(String cEstiloC, String cTipo, int iSize, int iMaxLen,
			int iSpan, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\" colspan=\"" + iSpan
				+ "\">\n");
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (!cOnChange.equals("")) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (!cOnBlur.equals("")) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		}
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">");
		if (cName.substring(0, 2).compareTo("dt") == 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new java.util.Date());
			sbRes.append("<a class=\"" + "EAncla" + "\" name=\"" + "calendario"
					+ "\" href=\"" + "JavaScript:fLoadCal("
					+ calendar.get(calendar.DAY_OF_MONTH) + ","
					+ (calendar.get(calendar.MONTH) + 1) + ","
					+ calendar.get(calendar.YEAR) + "," + "document.forms[0]."
					+ cName + ");" + "\">");
			sbRes.append("(dd/mm/aaaa)");
			sbRes.append("</a>");
		}
	}
	
	
	
	private void CampoCSOculto(String cEstiloC, String cTipo, int iSize, int iMaxLen,
			int iSpan, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\" colspan=\"" + iSpan
				+ "\">\n <div id=\""+cName+"2\" style=\"display:none;\">");
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (!cOnChange.equals("")) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (!cOnBlur.equals("")) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		}
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">");
		if (cName.substring(0, 2).compareTo("dt") == 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new java.util.Date());
			sbRes.append("<a class=\"" + "EAncla" + "\" name=\"" + "calendario"
					+ "\" href=\"" + "JavaScript:fLoadCal("
					+ calendar.get(calendar.DAY_OF_MONTH) + ","
					+ (calendar.get(calendar.MONTH) + 1) + ","
					+ calendar.get(calendar.YEAR) + "," + "document.forms[0]."
					+ cName + ");" + "\">");
			sbRes.append("(dd/mm/aaaa)");
			sbRes.append("</a>");			
		}
		sbRes.append("</div><td>");
	}
	
	
	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de campo con el estilo proporcionado, adem�s genera el
	 * campo con las caracteristicas proporcionadas.
	 * 
	 * @param cEstiloC
	 *            Estilo para la celda del campo.
	 * @param cTipo
	 *            Tipo de campo a generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se asignara al ancho del campo.
	 * @param iMaxLen
	 *            Numero m�ximo de caracteres que se permitir� de captura en un
	 *            campo.
	 * @param cName
	 *            Nombre del objeto que se generar� para su uso en JavaScript.
	 * @param cValue
	 *            Valor inicial del campo que se genera.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando el campo cambie
	 *            de valor.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del campo cuando se
	 *            ingrese al campo.
	 * @param lActivo
	 *            Indica si se debe permitir la captura o no del campo (atributo
	 *            disabled de HTML).
	 */
	private void CampoCSNom024(String cEstiloC, String cTipo, int iSize, int iMaxLen,
			int iSpan, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\" colspan=\"" + iSpan
				+ "\">\n");
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (!cOnChange.equals("")) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (!cOnBlur.equals("")) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		}
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">");
		if (cName.substring(0, 2).compareTo("dt") == 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new java.util.Date());
			sbRes.append("<a class=\"" + "EAncla" + "\" name=\"" + "calendario"
					+ "\" href=\"" + "JavaScript:fLoadCal("
					+ calendar.get(calendar.DAY_OF_MONTH) + ","
					+ (calendar.get(calendar.MONTH) + 1) + ","
					+ calendar.get(calendar.YEAR) + "," + "document.forms[0]."
					+ cName + ");" + "\">");
			//sbRes.append("(dd/mm/aaaa)");
			sbRes.append("[aaaammdd]");
			sbRes.append("</a>");
		}
	}	
	
	
	
	

	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de campo con el estilo proporcionado, adem�s genera el
	 * campo con las caracteristicas proporcionadas.
	 * 
	 * @param cTipo
	 *            Tipo de campo a generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se asignara al ancho del campo.
	 * @param iMaxLen
	 *            Numero m�ximo de caracteres que se permitir� de captura en un
	 *            campo.
	 * @param cName
	 *            Nombre del objeto que se generar� para su uso en JavaScript.
	 * @param cValue
	 *            Valor inicial del campo que se genera.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando el campo cambie
	 *            de valor.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del campo cuando se
	 *            ingrese al campo.
	 * @param lActivo
	 *            Indica si se debe permitir la captura o no del campo (atributo
	 *            disabled de HTML).
	 * @return Objeto con las cadenas que se sumaron para el despliegue del
	 *         campo.
	 */
	public StringBuffer CampoSinCelda(String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo) {
		sbRes = new StringBuffer();
		sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
				+ "\" maxlength=\"");
		sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		if (!cOnChange.equals("")) {
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		}
		if (!cOnBlur.equals("")) {
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		}
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">");
		if (cName.substring(0, 2).compareTo("dt") == 0) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new java.util.Date());
			sbRes.append("<a class=\"" + "EAncla" + "\" name=\"" + "calendario"
					+ "\" href=\"" + "JavaScript:fLoadCal("
					+ calendar.get(calendar.DAY_OF_MONTH) + ","
					+ (calendar.get(calendar.MONTH) + 1) + ","
					+ calendar.get(calendar.YEAR) + "," + "document.forms[0]."
					+ cName + ");" + "\">");
			sbRes.append("(dd/mm/aaaa)");
			sbRes.append("</a>");
		}
		return sbRes;
	}

	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de campo con el estilo proporcionado, adem�s de generar
	 * el objeto textarea de HTML con las caracteristicas proporcionadas en los
	 * parametros.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param iCol
	 *            Numero de columnas que tendra el textarea como propiedad.
	 * @param iReng
	 *            Numero de renglones que tendra el textarea como propiedad.
	 * @param cName
	 *            Nombre del objeto HTML para su uso en JavaScript.
	 * @param cValue
	 *            Valor inicial del textarea para su despliegue.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando el objeto cambie
	 *            de valor.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            objeto.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar o no, el texto cuando se ingrese
	 *            al objeto.
	 * @param lActivo
	 *            Indica si se habilitar� el objeto para captura o no, (opci�n
	 *            disabled de HTML).
	 */
	private void AreaTexto(String cEstiloC, int iCol, int iReng, String cName,
			String cValue, int iTooltip, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\">\n");
		sbRes.append("<textarea cols=\"" + iCol + "\" rows=\"" + iReng + "\" ");
		sbRes.append(" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">\n");
		sbRes.append(cValue);
		sbRes.append("</textarea></td>\n");
	}

	// clone request
	private void AreaTexto(String cEstiloC, int iCol, int iReng, String cName,
			String cValue, int iTooltip, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo, HttpServletRequest request) {

		if (request.getParameter(cName) != null) {
			cValue = request.getParameter(cName);

		}
		AreaTexto(cEstiloC, iCol, iReng, cName, cValue, iTooltip, cOnChange,
				cOnBlur, lSelectonFocus, lActivo);
	}

	/**
	 * metodo privado para uso posterior por los metodos p�blicos, se encarga de
	 * generar una celda de campo con el estilo proporcionado, adem�s de generar
	 * el objeto textarea de HTML con las caracteristicas proporcionadas en los
	 * parametros.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param iCol
	 *            Numero de columnas que tendra el textarea como propiedad.
	 * @param iReng
	 *            Numero de renglones que tendra el textarea como propiedad.
	 * @param iSpan
	 *            Numero de espacios para darle formato dentro de una tabla.
	 * @param cName
	 *            Nombre del objeto HTML para su uso en JavaScript.
	 * @param cValue
	 *            Valor inicial del textarea para su despliegue.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando el objeto cambie
	 *            de valor.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            objeto.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar o no, el texto cuando se ingrese
	 *            al objeto.
	 * @param lActivo
	 *            Indica si se habilitar� el objeto para captura o no, (opci�n
	 *            disabled de HTML).
	 */
	private void AreaTextoCS(String cEstiloC, int iCol, int iReng, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\" colspan=\"" + iSpan
				+ "\">\n");
		sbRes.append("<textarea cols=\"" + iCol + "\" rows=\"" + iReng + "\" ");
		sbRes.append(" name=\"" + cName + "\"\n");
		sbRes.append("value=\"" + cValue + "\"\n");
		if (lActivo == false) {
			sbRes.append(" disabled");
		}
		if (lSelectonFocus == true) {
			sbRes.append(" onfocus=\"this.select();\"\n");
		}
		sbRes.append(" onChange=\"" + cOnChange + "\"\n");
		sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
		sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
		sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
		sbRes.append(iTooltip);
		sbRes.append(");\">\n");
		sbRes.append(cValue);
		sbRes.append("</textarea></td>\n");
	}

	// clone request
	private void AreaTextoCS(String cEstiloC, int iCol, int iReng, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			HttpServletRequest request) {

		if (request.getParameter(cName) != null) {
			cValue = request.getParameter(cName);

		}
		AreaTextoCS(cEstiloC, iCol, iReng, iSpan, cName, cValue, iTooltip,
				cOnChange, cOnBlur, lSelectonFocus, lActivo);

	}

	/**
	 * Este metodo se encarga de crear una celda con el estilo y texto
	 * proporcionados, regresando la cadena resultante. <br>
	 * A diferencia de Etiqueta, en este metodo se crea el StringBuffer y se
	 * regresa a la instancia que lo mand� a llamar.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda del texto.
	 * @param cTexto
	 *            Texto que se desplegara en la celda.
	 * @return Texto generado por el metodo Etiqueta.
	 */
	public StringBuffer Texto(String cEstiloE, String cTexto) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cTexto);
		return sbRes;
	}
	
	public StringBuffer Texto3(String cEstiloE, String cTexto) {
		sbRes = new StringBuffer("");
		this.Etiqueta3(cEstiloE, cTexto);
		return sbRes;
	}
	

	/**
	 * Este metodo se encarga de crear una celda con el estilo y texto
	 * proporcionados, regresando la cadena resultante. <br>
	 * A diferencia de Etiqueta, en este metodo se crea el StringBuffer y se
	 * regresa a la instancia que lo mand� a llamar.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda del texto.
	 * @param cTexto
	 *            Texto que se desplegara en la celda.
	 * @return Texto generado por el metodo Etiqueta.
	 */
	public StringBuffer Texto2(String cEstiloE, String cTexto) {
		sbRes = new StringBuffer("");
		this.Etiqueta2(cEstiloE, cTexto);
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear una celda con el estilo y texto
	 * proporcionados, regresando la cadena resultante. <br>
	 * A diferencia de Etiqueta, en este metodo se crea el StringBuffer y se
	 * regresa a la instancia que lo mand� a llamar.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda del texto.
	 * @param cTexto
	 *            Texto que se desplegara en la celda.
	 * @return Texto generado por el metodo Etiqueta.
	 */
	public StringBuffer TextoCS(String cEstiloE, String cTexto, int iSpan) {
		sbRes = new StringBuffer("");
		this.EtiquetaCS(cEstiloE, cTexto, iSpan);
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear las celdas de un campo con su etiqueta y
	 * estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara en la celda de etiqueta.
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cTipo
	 *            Tipo de campo que se desea generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se desea del campo.
	 * @param iMaxLen
	 *            M�ximo Numero de caracteres que se permitir� capturar en este
	 *            campo.
	 * @param cName
	 *            Nombre del objeto HTML, mismo que se emplear� en JavaScript y
	 *            proceso de formularios
	 * @param cValue
	 *            Valor inicial que se le dara al campo.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se desea ejecutar cuando cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se desea ejecutar al abandonar el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se desea seleccionar el texto o no, esto en el
	 *            momento de ingresar al campo.
	 * @param lActivo
	 *            Indica si se desea mostrar habilitado o no el campo.
	 * @param lCampo
	 *            Indica si se debe mostrar como un campo o como un texto el
	 *            valor proporcionado.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiCampo(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.Campo(cEstiloC, cTipo, iSize, iMaxLen, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo,
					false);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}
	public StringBuffer EtiCampo2(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo) {
		sbRes = new StringBuffer("");
		//this.Etiqueta3(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.Campo2(cEstiloC, cTipo, iSize, iMaxLen, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo,
					false);
		} else {
			//this.Etiqueta3(cEstiloC, cValue);
		}
		return sbRes;
	}
	// clone request
	public StringBuffer EtiCampo(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}

		return EtiCampo(cEstiloE, cEtiqueta, cEstiloC, cTipo, iSize, iMaxLen,
				cName, cValue, iTooltip, cOnChange, cOnBlur, lSelectonFocus,
				lActivo, lCampo);
	}

	
	// clone request
	public StringBuffer EtiCampo2(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}

		return EtiCampo2(cEstiloE, cEtiqueta, cEstiloC, cTipo, iSize, iMaxLen,
				cName, cValue, iTooltip, cOnChange, cOnBlur, lSelectonFocus,
				lActivo, lCampo);
	}
	
	
	
	
	/**
	 * Este metodo se encarga de crear las celdas de un campo con su etiqueta y
	 * estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara en la celda de etiqueta.
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cTipo
	 *            Tipo de campo que se desea generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se desea del campo.
	 * @param iMaxLen
	 *            M�ximo Numero de caracteres que se permitir� capturar en este
	 *            campo.
	 * @param cName
	 *            Nombre del objeto HTML, mismo que se emplear� en JavaScript y
	 *            proceso de formularios
	 * @param cValue
	 *            Valor inicial que se le dara al campo.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se desea ejecutar cuando cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se desea ejecutar al abandonar el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se desea seleccionar el texto o no, esto en el
	 *            momento de ingresar al campo.
	 * @param lActivo
	 *            Indica si se desea mostrar habilitado o no el campo.
	 * @param lCampo
	 *            Indica si se debe mostrar como un campo o como un texto el
	 *            valor proporcionado.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiCampoCS(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {

			this.CampoCS(cEstiloC, cTipo, iSize, iMaxLen, iSpan, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.EtiquetaCS(cEstiloC, cValue, iSpan);
		}
		return sbRes;
	}
	
	
	public StringBuffer EtiCampoCSOculto(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo) {
		sbRes = new StringBuffer("");
		this.EtiquetaOculto(cEstiloE, cEtiqueta,cName);
		if (lCampo == true) {

			this.CampoCSOculto(cEstiloC, cTipo, iSize, iMaxLen, iSpan, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.EtiquetaCSOculto(cEstiloC, cValue, iSpan, cName);
		}
		return sbRes;
	}

	// clone request
	public StringBuffer EtiCampoCS(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}
		return EtiCampoCS(cEstiloE, cEtiqueta, cEstiloC, cTipo, iSize, iMaxLen,
				iSpan, cName, cValue, iTooltip, cOnChange, cOnBlur,
				lSelectonFocus, lActivo, lCampo);
	}
	
	public StringBuffer EtiCampoCSOculto(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}
		return EtiCampoCSOculto(cEstiloE, cEtiqueta, cEstiloC, cTipo, iSize, iMaxLen,
				iSpan, cName, cValue, iTooltip, cOnChange, cOnBlur,
				lSelectonFocus, lActivo, lCampo);
	}
	
	
	
	
	
	/**
	 * Este metodo se encarga de crear las celdas de un campo con su etiqueta y
	 * estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara en la celda de etiqueta.
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cTipo
	 *            Tipo de campo que se desea generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se desea del campo.
	 * @param iMaxLen
	 *            M�ximo Numero de caracteres que se permitir� capturar en este
	 *            campo.
	 * @param cName
	 *            Nombre del objeto HTML, mismo que se emplear� en JavaScript y
	 *            proceso de formularios
	 * @param cValue
	 *            Valor inicial que se le dara al campo.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se desea ejecutar cuando cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se desea ejecutar al abandonar el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se desea seleccionar el texto o no, esto en el
	 *            momento de ingresar al campo.
	 * @param lActivo
	 *            Indica si se desea mostrar habilitado o no el campo.
	 * @param lCampo
	 *            Indica si se debe mostrar como un campo o como un texto el
	 *            valor proporcionado.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiCampoCSNom024(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {

			this.CampoCSNom024(cEstiloC, cTipo, iSize, iMaxLen, iSpan, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.EtiquetaCS(cEstiloC, cValue, iSpan);
		}
		return sbRes;
	}

	// clone request
	public StringBuffer EtiCampoCSNom024(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cTipo, int iSize, int iMaxLen, int iSpan,
			String cName, String cValue, int iTooltip, String cOnChange,
			String cOnBlur, boolean lSelectonFocus, boolean lActivo,
			boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}
		return EtiCampoCS(cEstiloE, cEtiqueta, cEstiloC, cTipo, iSize, iMaxLen,
				iSpan, cName, cValue, iTooltip, cOnChange, cOnBlur,
				lSelectonFocus, lActivo, lCampo);
	}
	

	/**
	 * Este metodo se encarga de crear un campo con estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cTipo
	 *            Tipo de campo que se desea generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se desea del campo.
	 * @param iMaxLen
	 *            M�ximo Numero de caracteres que se permitir� capturar en este
	 *            campo.
	 * @param cName
	 *            Nombre del objeto HTML, mismo que se emplear� en JavaScript y
	 *            proceso de formularios
	 * @param cValue
	 *            Valor inicial que se le dara al campo.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se desea ejecutar cuando cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se desea ejecutar al abandonar el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se desea seleccionar el texto o no, esto en el
	 *            momento de ingresar al campo.
	 * @param lActivo
	 *            Indica si se desea mostrar habilitado o no el campo.
	 * @param lCampo
	 *            Indica si se debe mostrar como un campo o como un texto el
	 *            valor proporcionado.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer CeldaCampo(String cEstiloC, String cTipo, int iSize,
			int iMaxLen, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo) {
		sbRes = new StringBuffer("");
		if (lCampo == true) {
			this.Campo(cEstiloC, cTipo, iSize, iMaxLen, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo,
					false);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un campo con estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cTipo
	 *            Tipo de campo que se desea generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se desea del campo.
	 * @param iMaxLen
	 *            M�ximo Numero de caracteres que se permitir� capturar en este
	 *            campo.
	 * @param cName
	 *            Nombre del objeto HTML, mismo que se emplear� en JavaScript y
	 *            proceso de formularios
	 * @param cValue
	 *            Valor inicial que se le dara al campo.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se desea ejecutar cuando cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se desea ejecutar al abandonar el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se desea seleccionar el texto o no, esto en el
	 *            momento de ingresar al campo.
	 * @param lActivo
	 *            Indica si se desea mostrar habilitado o no el campo.
	 * @param lCampo
	 *            Indica si se debe mostrar como un campo o como un texto el
	 *            valor proporcionado.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer CeldaCampoBR(String cEstiloC, String cTipo, int iSize,
			int iMaxLen, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo) {
		sbRes = new StringBuffer("");
		if (lCampo == true) {
			this.Campo(cEstiloC, cTipo, iSize, iMaxLen, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo, true);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}

	// clone request
	public StringBuffer CeldaCampo(String cEstiloC, String cTipo, int iSize,
			int iMaxLen, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}
		return CeldaCampo(cEstiloC, cTipo, iSize, iMaxLen, cName, cValue,
				iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo, lCampo);
	}

	/**
	 * Este metodo se encarga de crear un campo con estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cTipo
	 *            Tipo de campo que se desea generar: "text" o "password".
	 * @param iSize
	 *            Tama�o que se desea del campo.
	 * @param iMaxLen
	 *            M�ximo Numero de caracteres que se permitir� capturar en este
	 *            campo.
	 * @param cName
	 *            Nombre del objeto HTML, mismo que se emplear� en JavaScript y
	 *            proceso de formularios
	 * @param cValue
	 *            Valor inicial que se le dara al campo.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se desea ejecutar cuando cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se desea ejecutar al abandonar el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se desea seleccionar el texto o no, esto en el
	 *            momento de ingresar al campo.
	 * @param lActivo
	 *            Indica si se desea mostrar habilitado o no el campo.
	 * @param lCampo
	 *            Indica si se debe mostrar como un campo o como un texto el
	 *            valor proporcionado.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer CeldaCampoCS(String cEstiloC, String cTipo, int iSize,
			int iMaxLen, int iSpan, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo) {
		sbRes = new StringBuffer("");
		if (lCampo == true) {
			this.CampoCS(cEstiloC, cTipo, iSize, iMaxLen, iSpan, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.EtiquetaCS(cEstiloC, cValue, iSpan);
		}
		return sbRes;
	}

	// clone request
	public StringBuffer CeldaCampoCS(String cEstiloC, String cTipo, int iSize,
			int iMaxLen, int iSpan, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo, HttpServletRequest request) {
		if (lCampo == true) {
			if (request.getParameter(cName) != null) {
				cValue = request.getParameter(cName);
			}
		}
		return CeldaCampoCS(cEstiloC, cTipo, iSize, iMaxLen, iSpan, cName,
				cValue, iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo,
				lCampo);
	}

	/**
	 * Este metodo se encarga de crear las celdas de un toggle-box con su
	 * etiqueta y estilos proporcionados. <br>
	 * Adem�s se determina si se quiere mostrar un campo o un texto, realizando
	 * el llamado a los diferentes metodos privados de esta clase, seg�n se vaya
	 * requiriendo.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara en la celda de etiqueta.
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cName
	 *            Nombre que se asignara al objeto. El que se desea usar para
	 *            JavaScript y proceso de formularios.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el toggle-box.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param cValTrue
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor seleccionado.
	 * @param cValFalse
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor no seleccionado.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiToggle(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cName, String cSelected, String cOnClick,
			int iTooltip, boolean lCampo, String cValTrue, String cValFalse,
			boolean lActivo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		this.ObjToggle(cEstiloC, cName, cSelected, cOnClick, iTooltip, lCampo,
				cValTrue, cValFalse, lActivo);
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un toggle-box con un estilo
	 * proporcionado.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cName
	 *            Nombre que se asignara al objeto. El que se desea usar para
	 *            JavaScript y proceso de formularios.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el toggle-box.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param cValTrue
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor seleccionado.
	 * @param cValFalse
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor no seleccionado.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 */
	public void ObjToggle(String cEstiloC, String cName, String cSelected,
			String cOnClick, int iTooltip, boolean lCampo, String cValTrue,
			String cValFalse, boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\">\n");
		if (lCampo == true) {
			sbRes.append("<input type=\"checkbox\" name=\"" + cName
					+ "\" value=\"1\"");
			if (cSelected.compareToIgnoreCase("1") == 0) {
				sbRes.append(" checked");
			}
			if (lActivo == false) {
				sbRes.append(" disabled");
			}
			sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
			sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
			sbRes.append(iTooltip);
			sbRes.append(");\"\n");
			sbRes.append(" onClick=\"" + cOnClick + "\">");
		} else if (cSelected.compareToIgnoreCase("0") == 0) {
			sbRes.append(cValFalse);
		} else {
			sbRes.append(cValTrue);
		}
		sbRes.append("</td>\n");
	}

	/**
	 * Este metodo se encarga de crear un toggle-box con un estilo
	 * proporcionado.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param iSpan
	 *            Numero de espacios para darle formato dentro de una tabla.
	 * @param cName
	 *            Nombre que se asignara al objeto. El que se desea usar para
	 *            JavaScript y proceso de formularios.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el toggle-box.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param cValTrue
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor seleccionado.
	 * @param cValFalse
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor no seleccionado.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 */
	public void ObjToggleCS(String cEstiloC, int iSpan, String cName,
			String cSelected, String cOnClick, int iTooltip, boolean lCampo,
			String cValTrue, String cValFalse, boolean lActivo) {
		sbRes.append("<td class=\"" + cEstiloC + "\" colspan=\"" + iSpan
				+ "\">\n");
		if (lCampo == true) {
			sbRes.append("<input type=\"checkbox\" name=\"" + cName
					+ "\" value=\"1\"");
			if (cSelected.compareToIgnoreCase("1") == 0) {
				sbRes.append(" checked");
			}
			if (lActivo == false) {
				sbRes.append(" disabled");
			}
			sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
			sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
			sbRes.append(iTooltip);
			sbRes.append(");\"\n");
			sbRes.append(" onClick=\"" + cOnClick + "\">");
		} else if (cSelected.compareToIgnoreCase("0") == 0) {
			this.Etiqueta(cEstiloC, cValFalse);
		} else {
			this.Etiqueta(cEstiloC, cValTrue);
		}
		sbRes.append("</td>\n");
	}

	/**
	 * Este metodo se encarga de crear un toggle-box con un estilo
	 * proporcionado.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cName
	 *            Nombre que se asignara al objeto. El que se desea usar para
	 *            JavaScript y proceso de formularios.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el toggle-box.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param cValTrue
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor seleccionado.
	 * @param cValFalse
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor no seleccionado.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 * @return Valor que se debe desplegar en el JSP, correspondiente a una
	 *         celda.
	 */
	public StringBuffer ToggleMov(String cEstiloC, String cName,
			String cSelected, String cOnClick, int iTooltip, boolean lCampo,
			String cValTrue, String cValFalse, boolean lActivo) {
		sbRes = new StringBuffer("");
		this.ObjToggle(cEstiloC, cName, cSelected, cOnClick, iTooltip, lCampo,
				cValTrue, cValFalse, lActivo);
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un toggle-box con un estilo
	 * proporcionado.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param iSpan
	 *            Numero de espacios para darle formato dentro de una tabla.
	 * @param cName
	 *            Nombre que se asignara al objeto. El que se desea usar para
	 *            JavaScript y proceso de formularios.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el toggle-box.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param cValTrue
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor seleccionado.
	 * @param cValFalse
	 *            Indica el valor que se desplegara en caso de no ser campo con
	 *            un valor no seleccionado.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 * @return Valor que se debe desplegar en el JSP, correspondiente a una
	 *         celda.
	 */
	public StringBuffer ToggleMovCS(String cEstiloC, int iSpan, String cName,
			String cSelected, String cOnClick, int iTooltip, boolean lCampo,
			String cValTrue, String cValFalse, boolean lActivo) {
		sbRes = new StringBuffer("");
		this.ObjToggleCS(cEstiloC, iSpan, cName, cSelected, cOnClick, iTooltip,
				lCampo, cValTrue, cValFalse, lActivo);
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear las celdas de un toggle-box con el estilo
	 * proporcionado. <br>
	 * En este caso sole se crear� la etiqueta y el valor, no se mostrar� un
	 * objeto toggle, realizando el llamado a los diferentes metodos privados de
	 * esta clase, seg�n se vaya requiriendo.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo.
	 * @param cSelected
	 *            Indica el valor de falso (0) o verdadero (1) que presentar� el
	 *            campo.
	 * @param cValTrue
	 *            Cadena que se desplegara en caso de ser un valor 1
	 *            (verdadero).
	 * @param cValFalse
	 *            Cadena que se desplegara en caso de ser un valor 0 (falso).
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer Toggle(String cEstiloC, String cSelected,
			String cValTrue, String cValFalse) {
		sbRes = new StringBuffer("");
		if (cSelected.compareToIgnoreCase("0") == 0) {
			this.Etiqueta(cEstiloC, cValFalse);
		} else {
			this.Etiqueta(cEstiloC, cValTrue);
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un ancla o liga de HTML con un texto en
	 * particular. <br>
	 * En este metodo no se generan tags TD, de tal modo que se puede emplear en
	 * cualquir lugar. Solo se est� obligado a que el ancla sea creada sobre una
	 * cadena de texto.
	 * 
	 * @param cEstilo
	 *            Estilo que se le asignara al tag <A>.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara para que el usuario (texto entre
	 *            <A> y </A>.
	 * @param cRef
	 *            URL que se le asignara al valor HREF del tag <A>.
	 * @param cStatus
	 *            Texto a desplegar en la barra de estado cuando se pase el
	 *            mouse sobre la liga.
	 * @param cName
	 *            Nombre del objeto <A> que se asignara en HTML, para poder
	 *            referenciar desde JavaScript.
	 * @return Cadena que se debe desplegar en el JSP.
	 */
	public StringBuffer clsAnclaTexto(String cEstilo, String cEtiqueta,
			String cRef, String cStatus, String cName) {
		sbRes = new StringBuffer("");
		sbRes.append("<a class=\"" + cEstilo + "\" name=\"" + cName
				+ "\" href=\"" + cRef + "\"\n");
		sbRes.append("  onMouseOut=\"self.status='';return true;\"\n");
		sbRes.append("  onMouseOver=\"self.status='" + cStatus
				+ "';return true;\">");
		sbRes.append(cEtiqueta);
		sbRes.append("</a>");
		return sbRes;
	}

	public StringBuffer clsAnclaTexto(String cEstilo, String cEtiqueta,
			String cRef, String cName) {
		sbRes = new StringBuffer("");
		sbRes.append("<a class=\"" + cEstilo + "\" name=\"" + cName
				+ "\" href=\"" + cRef + "\">");
		sbRes.append(cEtiqueta);
		sbRes.append("</a>");
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un �rea de texto con su respectiva
	 * etiqueta. <br>
	 * El �rea de texto ser� generada en base a los parametros que se
	 * proporcionen en el llamado.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta a desplegar.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara en la celda de etiqueta.
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del area de texto a generar.
	 * @param iCol
	 *            Numero de columnas que emplear� el �rea de texto.
	 * @param iReng
	 *            Numero de renglones que emplear� el �rea de texto.
	 * @param cName
	 *            Nombre que se asignara al �rea de texto, para referenciarlo en
	 *            JavaScript.
	 * @param cValue
	 *            Valor inicial del �rea de texto.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando se cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del �rea al momento
	 *            de ingresar al campo.
	 * @param lActivo
	 *            Indica si el �rea de texto estar� habilitada para captura o
	 *            no.
	 * @param lCampo
	 *            Indica si se debe mostrar un objeto textarea o solo un texto
	 *            en la celda.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiAreaTexto(String cEstiloE, String cEtiqueta,
			String cEstiloC, int iCol, int iReng, String cName, String cValue,
			int iTooltip, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo, boolean lCampo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.AreaTexto(cEstiloC, iCol, iReng, cName, cValue, iTooltip,
					cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}

	// clone request

	public StringBuffer EtiAreaTexto(String cEstiloE, String cEtiqueta,
			String cEstiloC, int iCol, int iReng, String cName, String cValue,
			int iTooltip, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo, boolean lCampo,
			HttpServletRequest request) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.AreaTexto(cEstiloC, iCol, iReng, cName, cValue, iTooltip,
					cOnChange, cOnBlur, lSelectonFocus, lActivo, request);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un �rea de texto con su respectiva
	 * etiqueta, y que tenga un COLSPAN <br>
	 * El �rea de texto ser� generada en base a los parametros que se
	 * proporcionen en el llamado.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta a desplegar.
	 * @param cEtiqueta
	 *            Etiqueta que se desplegara en la celda de etiqueta.
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del area de texto a generar.
	 * @param iCol
	 *            Numero de columnas que emplear� el �rea de texto.
	 * @param iReng
	 *            Numero de renglones que emplear� el �rea de texto.
	 * @param iSpan
	 *            Numero de espacios para darle formato dentro de una tabla.
	 * @param cName
	 *            Nombre que se asignara al �rea de texto, para referenciarlo en
	 *            JavaScript.
	 * @param cValue
	 *            Valor inicial del �rea de texto.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando se cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del �rea al momento
	 *            de ingresar al campo.
	 * @param lActivo
	 *            Indica si el �rea de texto estar� habilitada para captura o
	 *            no.
	 * @param lCampo
	 *            Indica si se debe mostrar un objeto textarea o solo un texto
	 *            en la celda.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiAreaTextoCS(String cEstiloE, String cEtiqueta,
			String cEstiloC, int iCol, int iReng, int iSpan, String cName,
			String cValue, int iTooltip, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo, boolean lCampo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.AreaTextoCS(cEstiloC, iCol, iReng, iSpan, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.EtiquetaCS(cEstiloC, cValue, iSpan);
		}
		return sbRes;
	}

	// clone request
	public StringBuffer EtiAreaTextoCS(String cEstiloE, String cEtiqueta,
			String cEstiloC, int iCol, int iReng, int iSpan, String cName,
			String cValue, int iTooltip, String cOnChange, String cOnBlur,
			boolean lSelectonFocus, boolean lActivo, boolean lCampo,
			HttpServletRequest request) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		if (lCampo == true) {
			this.AreaTextoCS(cEstiloC, iCol, iReng, iSpan, cName, cValue,
					iTooltip, cOnChange, cOnBlur, lSelectonFocus, lActivo,
					request);
		} else {
			this.EtiquetaCS(cEstiloC, cValue, iSpan);
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un �rea de texto con su respectiva
	 * etiqueta. <br>
	 * El �rea de texto ser� generada en base a los parametros que se
	 * proporcionen en el llamado.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del area de texto a generar.
	 * @param iCol
	 *            Numero de columnas que emplear� el �rea de texto.
	 * @param iReng
	 *            Numero de renglones que emplear� el �rea de texto.
	 * @param cName
	 *            Nombre que se asignara al �rea de texto, para referenciarlo en
	 *            JavaScript.
	 * @param cValue
	 *            Valor inicial del �rea de texto.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param cOnChange
	 *            Funcion de JavaScript que se ejecutara cuando se cambie el
	 *            valor del campo.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando se abandone el
	 *            campo.
	 * @param lSelectonFocus
	 *            Indica si se debe seleccionar el contenido del �rea al momento
	 *            de ingresar al campo.
	 * @param lActivo
	 *            Indica si el �rea de texto estar� habilitada para captura o
	 *            no.
	 * @param lCampo
	 *            Indica si se debe mostrar un objeto textarea o solo un texto
	 *            en la celda.
	 * @return Cadena que se debe desplegar en el JSP, correspondiente a dos
	 *         celdas.
	 */
	public StringBuffer EtiAreaTextoSINEtiqueta(String cEstiloC, int iCol,
			int iReng, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo) {
		sbRes = new StringBuffer("");
		if (lCampo == true) {
			this.AreaTexto(cEstiloC, iCol, iReng, cName, cValue, iTooltip,
					cOnChange, cOnBlur, lSelectonFocus, lActivo);
		} else {
			this.Etiqueta(cEstiloC, cValue);
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select de una l�nea con los datos proporcionados.
	 * 
	 * @param cEstiloC
	 *            Nombre del estilo que se dara a la celda.
	 * @param cNombre
	 *            Nombre que se dara al objeto de HTML.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param Vector
	 *            que contiene los datos de cada registro a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cCampoDsc
	 *            Nombre del campo descripcion para extraer el valor a desplegar
	 *            en la secci�n option del select.
	 * @param HttpServletRequest
	 * @return Valor por omision del Select.
	 */
	public StringBuffer SelectOneRow(String cEstiloC, String cNombre,
			String cOnChange, Vector vDatos, String cCampoCve,
			String cCampoDsc, HttpServletRequest request, String cOmision) {
		StringBuffer sbTemp = new StringBuffer();
		sbTemp.append("<td class=\"" + cEstiloC + "\">");
		sbTemp.append(this.SelectOneRowSinTD(cNombre, cOnChange, vDatos,
				cCampoCve, cCampoDsc, request, cOmision));
		sbTemp.append("</td>");
		return sbTemp;
	}

	// clone simple vector

	public StringBuffer SelectOneRow(String cEstiloC, String cNombre,
			String cOnChange, Vector vDatos, HttpServletRequest request,
			String cOmision) {
		StringBuffer sbTemp = new StringBuffer();
		sbTemp.append("<td class=\"" + cEstiloC + "\">");
		sbTemp.append(this.SelectOneRowSinTD(cNombre, cOnChange, vDatos,
				request, cOmision));
		sbTemp.append("</td>");
		return sbTemp;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select de una l�nea con los datos proporcionados.
	 * 
	 * @param cNombre
	 *            Nombre que se dara al objeto de HTML.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param Vector
	 *            que contiene los datos de cada registro a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cCampoDsc
	 *            Nombre del campo descripcion para extraer el valor a desplegar
	 *            en la secci�n option del select.
	 * @param HttpServletRequest
	 * @return Valor por omision del Select.
	 */

	public StringBuffer SelectOneRowSinTD(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			boolean lTomaReq, HttpServletRequest request, String cOmision,
			boolean lConSelect) {
		try {
			boolean lFirst = false;
			BeanScroller beanSc = new BeanScroller(vDatos);
			String cValActual = "" + request.getParameter(cNombre);
			if (cValActual.equalsIgnoreCase("null") || !lTomaReq) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			sbRes.append("<SELECT NAME=\"" + cNombre
					+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			if (beanSc != null) {
				if (lConSelect) {
					sbRes.append("<option value=\"-1\">Seleccione...</option>");
				}
				for (int i = 0; i < beanSc.rowSize(); i++) {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "");
					if (cValActual.compareTo("") == 0 && lFirst == false) {
						CampoClave = " SELECTED ";
						lFirst = true;
					} else {
						if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
							CampoClave = " SELECTED ";
						} else {
							CampoClave = "";
						}
					}

					sbRes.append("<option " + CampoClave + " value=\""
							+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
							+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
				}
				sbRes.append("</SELECT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}
	

	public StringBuffer SelectOneRowSinTDOtro(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			boolean lTomaReq, HttpServletRequest request, String cOmision,
			boolean lConSelect) {
		try {
			boolean lFirst = false;
			BeanScroller beanSc = new BeanScroller(vDatos);
			String cValActual = "" + request.getParameter(cNombre);
			if (cValActual.equalsIgnoreCase("null") || !lTomaReq) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			sbRes.append("<SELECT NAME=\"" + cNombre
					+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			if (beanSc != null) {
				if (lConSelect) {
					sbRes.append("<option value=\"-1\">Seleccione...</option>");
					sbRes.append("<option value=\"-2\">Otro...</option>");
				}
				for (int i = 0; i < beanSc.rowSize(); i++) {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "");
					if (cValActual.compareTo("") == 0 && lFirst == false) {
						CampoClave = " SELECTED ";
						lFirst = true;
					} else {
						if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
							CampoClave = " SELECTED ";
						} else {
							CampoClave = "";
						}
					}

					sbRes.append("<option " + CampoClave + " value=\""
							+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
							+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
				}
				sbRes.append("</SELECT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}	

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select Multiple de una l�nea con los datos proporcionados.
	 * 
	 * @param cNombre
	 *            Nombre que se dara al objeto de HTML.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param Vector
	 *            que contiene los datos de cada registro a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cCampoDsc
	 *            Nombre del campo descripcion para extraer el valor a desplegar
	 *            en la secci�n option del select.
	 * @param HttpServletRequest
	 * @return Valor por omision del Select.
	 */

	public StringBuffer SelectOneRowSinTD3(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			boolean lTomaReq, HttpServletRequest request, String cOmision,
			boolean lConSelect) {
		try {
			boolean lFirst = false;
			BeanScroller beanSc = new BeanScroller(vDatos);
			String cValActual = "" + request.getParameter(cNombre);
			if (cValActual.equalsIgnoreCase("null") || !lTomaReq) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			sbRes.append("<SELECT NAME=\"" + cNombre
					+ "\" multiple=\"multiple\" SIZE=\"4\" onChange=\""
					+ cOnChange + "\">");
			if (beanSc != null) {
				if (lConSelect) {
					sbRes.append("<option value=\"-1\">Seleccione...</option>");
				}
				for (int i = 0; i < beanSc.rowSize(); i++) {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "");
					if (cValActual.compareTo("") == 0 && lFirst == false) {
						CampoClave = " SELECTED ";
						lFirst = true;
					} else {
						if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
							CampoClave = " SELECTED ";
						} else {
							CampoClave = "";
						}
					}

					sbRes.append("<option " + CampoClave + " value=\""
							+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
							+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
				}
				sbRes.append("</SELECT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select agregando un Id de una l�nea con los datos proporcionados.
	 * 
	 * @param cNombre
	 *            Nombre que se dara al objeto de HTML.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param Vector
	 *            que contiene los datos de cada registro a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cCampoDsc
	 *            Nombre del campo descripcion para extraer el valor a desplegar
	 *            en la secci�n option del select.
	 * @param HttpServletRequest
	 * @return Valor por omision del Select.
	 */

	public StringBuffer SelectOneRowSinTD2(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			boolean lTomaReq, HttpServletRequest request, String cOmision,
			boolean lConSelect) {
		try {
			boolean lFirst = false;
			BeanScroller beanSc = new BeanScroller(vDatos);
			String cValActual = "" + request.getParameter(cNombre);
			if (cValActual.equalsIgnoreCase("null") || !lTomaReq) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			sbRes.append("<SELECT NAME=\"" + cNombre + "\" Id=\"Id" + cNombre
					+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			if (beanSc != null) {
				if (lConSelect) {
					sbRes.append("<option value=\"-1\">Seleccione...</option>");
				}
				for (int i = 0; i < beanSc.rowSize(); i++) {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "");
					if (cValActual.compareTo("") == 0 && lFirst == false) {
						CampoClave = " SELECTED ";
						lFirst = true;
					} else {
						if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
							CampoClave = " SELECTED ";
						} else {
							CampoClave = "";
						}
					}

					sbRes.append("<option " + CampoClave + " value=\""
							+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
							+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
				}
				sbRes.append("</SELECT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}

	public StringBuffer SelectOneRowSinTD(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, boolean lConSelect) {
		return this.SelectOneRowSinTD(cNombre, cOnChange, vDatos, cCampoCve,
				cCampoDsc, true, request, cOmision, lConSelect);
	}

	public StringBuffer SelectOneRowSinTDOtro(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, boolean lConSelect) {
		return this.SelectOneRowSinTDOtro(cNombre, cOnChange, vDatos, cCampoCve,
				cCampoDsc, true, request, cOmision, lConSelect);
	}
	
	public StringBuffer SelectOneRowSinTD2(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, boolean lConSelect) {
		return this.SelectOneRowSinTD2(cNombre, cOnChange, vDatos, cCampoCve,
				cCampoDsc, true, request, cOmision, lConSelect);
	}

	public StringBuffer SelectOneRowSinTD3(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, boolean lConSelect) {
		return this.SelectOneRowSinTD3(cNombre, cOnChange, vDatos, cCampoCve,
				cCampoDsc, true, request, cOmision, lConSelect);
	}

	public StringBuffer SelectOneRowSinTD(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision) {
		try {
			boolean lFirst = false;
			BeanScroller beanSc = new BeanScroller(vDatos);
			String cValActual = "" + request.getParameter(cNombre);
			if (cValActual.compareTo("null") == 0) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			sbRes.append("<SELECT NAME=\"" + cNombre
					+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			if (beanSc != null) {
				for (int i = 0; i < beanSc.rowSize(); i++) {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "");
					if (cValActual.compareTo("") == 0 && lFirst == false) {
						CampoClave = " SELECTED ";
						lFirst = true;
					} else {
						if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
							CampoClave = " SELECTED ";
						} else {
							CampoClave = "";
						}
					}

					sbRes.append("<option " + CampoClave + " value=\""
							+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
							+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
				}
				sbRes.append("</SELECT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}

	public StringBuffer SelectOneRowSinTD(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, String lActivo) {
		try {
			boolean lFirst = false;
			BeanScroller beanSc = new BeanScroller(vDatos);
			String cValActual = "" + request.getParameter(cNombre);
			if (cValActual.compareTo("null") == 0) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			if (lActivo == "1") {
				sbRes.append("<SELECT NAME=\"" + cNombre
						+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			} else {
				sbRes.append("<SELECT NAME=\"" + cNombre
						+ "\" SIZE=\"1\" onChange=\"" + cOnChange
						+ "\" disabled >");

			}
			if (beanSc != null) {
				for (int i = 0; i < beanSc.rowSize(); i++) {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "");
					if (cValActual.compareTo("") == 0 && lFirst == false) {
						CampoClave = " SELECTED ";
						lFirst = true;
					} else {
						if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
							CampoClave = " SELECTED ";
						} else {
							CampoClave = "";
						}
					}

					sbRes.append("<option " + CampoClave + " value=\""
							+ beanSc.getFieldValue(cCampoCve, "" + i) + "\">"
							+ beanSc.getFieldValue(cCampoDsc, "") + "</option>");
				}
				sbRes.append("</SELECT>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}

	// clone simple vector

	public StringBuffer SelectOneRowSinTD(String cNombre, String cOnChange,
			Vector vDatos, HttpServletRequest request, String cOmision) {
		try {
			boolean lFirst = false;
			String cValActual = "" + request.getParameter(cNombre);
			String cSelected = "", cCve = "", cDsc = "";
			StringTokenizer stDatos;
			if (cValActual.compareTo("null") == 0) {
				cValActual = cOmision;
			}
			sbRes = new StringBuffer("");
			sbRes.append("<SELECT NAME=\"" + cNombre
					+ "\" SIZE=\"1\" onChange=\"" + cOnChange + "\">");
			for (int i = 0; i < vDatos.size(); i++) {
				cCve = "" + vDatos.get(i);
				stDatos = new StringTokenizer(cCve, "|");
				cCve = stDatos.nextToken();
				cDsc = stDatos.nextToken();

				cSelected = "";

				if (cValActual.compareTo("") == 0 && lFirst == false) {
					cSelected = " SELECTED ";
					lFirst = true;
				} else {
					if (cCve.compareTo(cValActual) == 0) {
						cSelected = " SELECTED ";
					}
				}
				sbRes.append("<option " + cSelected + " value=\"" + cCve
						+ "\">" + cDsc + "</option>");
			}
			sbRes.append("</SELECT>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbRes;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select de una l�nea con los datos proporcionados.
	 * 
	 * @param cEstiloC
	 *            Nombre del estilo que se dara a la celda.
	 * @param cNombre
	 *            Nombre que se dara al objeto de HTML.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param beanSc
	 *            BeanScroller que contiene los datos de cada registro a
	 *            desplegar.
	 * @param cNomTabla
	 *            Nombre de la tabla para extraer el valor a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cValActual
	 *            Valor que se desea tener por omision seleccionado en el objeto
	 *            select.
	 * @param vCampoDsc
	 *            Vector con los nombres de campos descripcion para extraer el
	 *            valor a desplegar en la secci�n option del select.
	 * @param cSepPrin
	 *            Cadena de separador entre el campo uno y el resto.
	 * @param cSepSec
	 *            Cadena de separador entre cada campo desde el segundo en
	 *            adelante.
	 * @return Cadena que se desplegara en el JSP, correspondiente al HTML del
	 *         select.
	 */
	public StringBuffer SelectOneRowMultiDsc(String cEstiloC, String cNombre,
			String cOnChange, Vector vDatos, String cCampoCve,
			String cValActual, Vector vCampoDsc, String cSepPrin, String cSepSec) {
		sbRes = new StringBuffer("");
		sbRes.append("<td class=\"" + cEstiloC + "\">");
		sbRes.append(this.SelectOneRowMultiDscSinTD(cNombre, cOnChange, vDatos,
				cCampoCve, cValActual, vCampoDsc, cSepPrin, cSepSec));
		sbRes.append("</td>");
		return sbRes;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select de una l�nea con los datos proporcionados.
	 * 
	 * @param cNombre
	 *            Nombre que se dara al objeto de HTML.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param beanSc
	 *            BeanScroller que contiene los datos de cada registro a
	 *            desplegar.
	 * @param cNomTabla
	 *            Nombre de la tabla para extraer el valor a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cValActual
	 *            Valor que se desea tener por omision seleccionado en el objeto
	 *            select.
	 * @param vCampoDsc
	 *            Vector con los nombres de campos descripcion para extraer el
	 *            valor a desplegar en la secci�n option del select.
	 * @param cSepPrin
	 *            Cadena de separador entre el campo uno y el resto.
	 * @param cSepSec
	 *            Cadena de separador entre cada campo desde el segundo en
	 *            adelante.
	 * @return Cadena que se desplegara en el JSP, correspondiente al HTML del
	 *         select.
	 */
	public StringBuffer SelectOneRowMultiDscSinTD(String cNombre,
			String cOnChange, Vector vDatos, String cCampoCve,
			String cValActual, Vector vCampoDsc, String cSepPrin, String cSepSec) {
		BeanScroller beanSc = new BeanScroller(vDatos);
		String CampoDsc = "";
		String cSeparaPrin = cSepPrin;
		String cSeparaSec = cSepSec;
		if (cSeparaPrin.compareToIgnoreCase("") == 0) {
			cSeparaPrin = " - ";
		}
		if (cSeparaSec.compareToIgnoreCase("") == 0) {
			cSeparaSec = " ";
		}
		if (cSeparaPrin.indexOf(",") >= 0) {
			cSeparaPrin = " - ";
		}
		if (cSeparaSec.indexOf(",") >= 0) {
			cSeparaSec = " ";
		}
		sbRes = new StringBuffer("");
		sbRes.append("<SELECT NAME=\"" + cNombre + "\" SIZE=\"1\" onChange=\""
				+ cOnChange + "\">");
		if (beanSc != null) {
			for (int i = 0; i < beanSc.rowSize(); i++) {
				try {
					beanSc.setRowIdx(i + 1);
					String CampoClave = ""
							+ beanSc.getFieldValue(cCampoCve, "0");
					for (int j = 0; j < vCampoDsc.size(); j++) {
						if (j == 0) {
							CampoDsc = ""
									+ beanSc.getFieldValue(
											"" + vCampoDsc.elementAt(j),
											"&nbsp;");
						}
						if (j >= 1) {
							if (j == 1) {
								CampoDsc = CampoDsc
										+ cSeparaPrin
										+ beanSc.getFieldValue(
												"" + vCampoDsc.elementAt(j),
												"&nbsp;");
							} else {
								CampoDsc = CampoDsc
										+ cSeparaSec
										+ beanSc.getFieldValue(
												"" + vCampoDsc.elementAt(j),
												"&nbsp;");
							}
						}
					}
					if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
						sbRes.append("<option SELECTED value=\"" + CampoClave
								+ "\">" + CampoDsc + "</option>");
					} else {
						sbRes.append("<option value=\"" + CampoClave + "\">"
								+ CampoDsc + "</option>");
					}
				} catch (Exception ex) {
				}
			}
		}
		sbRes.append("</SELECT>");
		return sbRes;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar una
	 * cadena de texto y un campo oculto para controlar Select en modo
	 * actualizaci�n.
	 * 
	 * @param cEstiloC
	 *            Nombre del estilo que se dara a la celda del despliegue.
	 * @param cNombre
	 *            Nombre del campo oculto a generar.
	 * @param beanSc
	 *            BeanScroller que contiene los datos de cada registro a
	 *            desplegar.
	 * @param cNomTabla
	 *            Nombre de la tabla para extraer el valor a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del campo oculto.
	 * @param cCampoDsc
	 *            Nombre del campo descripcion para extraer el valor a desplegar
	 *            en la secci�n value del campo oculto.
	 * @param cValActual
	 *            Valor que se desea tener por omision seleccionado en el objeto
	 *            select.
	 * @return Cadena que se desplegara en el JSP, correspondiente al HTML del
	 *         campo oculto.
	 */
	public StringBuffer TextoCampoOculto(String cEstiloC, String cNombre,
			BeanScroller beanSc, String cNomTabla, String cCampoCve,
			String cCampoDsc, String cValActual) {
		String CampoClave = "";
		String CampoDsc = "";
		sbRes = new StringBuffer("");
		sbRes.append("<td class=\"" + cEstiloC + "\">");
		if (beanSc != null) {
			for (int i = 0; i < beanSc.rowSize(); i++) {
				try {
					beanSc.setRowIdx(i + 1);
					CampoClave = beanSc.getFieldValue(
							cNomTabla + "." + cCampoCve,
							new Integer(i).toString()).toString();
					CampoDsc = beanSc.getFieldValue(
							cNomTabla + "." + cCampoDsc, "&nbsp;").toString();
					if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
						break;
					}
				} catch (Exception ex) {
				}
			}
			sbRes.append(CampoDsc + "&nbsp;");
			sbRes.append("<input type=\"hidden\" name=\"" + cNombre
					+ "\" value=\"" + CampoClave + "\">");
		}
		sbRes.append("</td>");
		return sbRes;
	}

	/**
	 * Este metodo se encarga de crear un objeto de tipo Radio con un estilo
	 * proporcionado.
	 * 
	 * @param cEstiloC
	 *            Estilo que se asignara a la celda del campo, cuando solo a
	 *            parece como texto.
	 * @param cName
	 *            Nombre que se asignara al objeto. El que se desea usar para
	 *            JavaScript y proceso de formularios.
	 * @param cValor
	 *            Indica el valor del radio.
	 * @param cTexto
	 *            Indica el texto que se vera asociado al radio.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el radio.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando el usuario salga
	 *            del objeto.
	 * @param cOnFocus
	 *            Funcion de JavaScript que se ejecutara cuando el foco llegue
	 *            al objeto.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 */
	public void ObjRadio(String cEstiloC, String cName, String cValor,
			String cTexto, String cSelected, String cOnClick, String cOnBlur,
			String cOnFocus, int iTooltip, boolean lCampo, boolean lActivo) {
		if (lCampo == true) {
			sbRes.append("<input type=\"radio\" name=\"" + cName
					+ "\" value=\"" + cValor + "\"");
			if (cSelected.compareToIgnoreCase(cValor) == 0) {
				sbRes.append(" checked");
			}
			if (lActivo == false) {
				sbRes.append(" disabled");
			}
			sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
			sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
			sbRes.append(iTooltip);
			sbRes.append(");\"\n");
			sbRes.append(" onClick=\"" + cOnClick + "\" onBlur=\"" + cOnBlur
					+ "\" onFocus=\"" + cOnFocus + "\" > " + cTexto);
		} else if (cSelected.compareToIgnoreCase(cValor) == 0) {
			this.Etiqueta(cEstiloC, cTexto);
		}
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Objeto de tipo Radio con Etiqueta.
	 * 
	 * @param cEstiloE
	 *            Nombre del estilo que se dara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Tecto que tendra la etiqueta.
	 * @param cEstiloC
	 *            Nombre del estilo para la celda donde va el objeto radio.
	 * @param cName
	 *            Nombre que se dara al objeto de HTML.
	 * @param cValor
	 *            Valor que se asignara al objeto radio.
	 * @param cTexto
	 *            Indica el texto que se vera asociado al radio.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el radio.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando el usuario salga
	 *            del objeto.
	 * @param cOnFocus
	 *            Funcion de JavaScript que se ejecutara cuando el foco llegue
	 *            al objeto.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 * @return Cadena que se desplegara en el JSP, correspondiente al HTML del
	 *         select.
	 */
	public StringBuffer ObjRadioCE(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cName, String cValor, String cTexto,
			String cSelected, String cOnClick, String cOnBlur, String cOnFocus,
			int iTooltip, boolean lCampo, boolean lActivo) {
		sbRes = new StringBuffer("");
		this.Etiqueta(cEstiloE, cEtiqueta);
		sbRes.append("<td class=\"" + cEstiloC + "\">\n");
		this.ObjRadio(cEstiloC, cName, cValor, cTexto, cSelected, cOnClick,
				cOnBlur, cOnFocus, iTooltip, lCampo, lActivo);
		sbRes.append("</td>\n");
		return sbRes;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Objeto de tipo Radio sin Etiqueta.
	 * 
	 * @param cEstiloC
	 *            Nombre del estilo para la celda donde va el objeto radio.
	 * @param cName
	 *            Nombre que se dara al objeto de HTML.
	 * @param cValor
	 *            Valor que se asignara al objeto radio.
	 * @param cTexto
	 *            Indica el texto que se vera asociado al radio.
	 * @param cSelected
	 *            Indica si se debe mostrar seleccionado o no el radio.
	 * @param cOnClick
	 *            Funcion de JavaScript que se ejecutara cuando el usuario haga
	 *            click con el mouse.
	 * @param cOnBlur
	 *            Funcion de JavaScript que se ejecutara cuando el usuario salga
	 *            del objeto.
	 * @param cOnFocus
	 *            Funcion de JavaScript que se ejecutara cuando el foco llegue
	 *            al objeto.
	 * @param iTooltip
	 *            Numero de tooltip que le corresponde del archivo.
	 * @param lCampo
	 *            Indica si debe mostrarse como un campo o como una etiqueta.
	 * @param lActivo
	 *            Indica si el objeto debe desplegarse como activo para que el
	 *            usuario cambie su valor o no.
	 * @return Cadena que se desplegara en el JSP, correspondiente al HTML del
	 *         select.
	 */
	public StringBuffer ObjRadioSE(String cEstiloC, String cName,
			String cValor, String cTexto, String cSelected, String cOnClick,
			String cOnBlur, String cOnFocus, int iTooltip, boolean lCampo,
			boolean lActivo) {
		sbRes = new StringBuffer("");
		this.ObjRadio(cEstiloC, cName, cValor, cTexto, cSelected, cOnClick,
				cOnBlur, cOnFocus, iTooltip, lCampo, lActivo);
		return sbRes;
	}

	public StringBuffer SelectOneRowSinTD(String cNombre, String cOnChange,
			Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, boolean lConSelect,
			boolean lActivo) {
		StringBuffer sbTmp = new StringBuffer("");
		sbTmp.append("<select name=\"" + cNombre + "\" size=\"1\" onChange=\""
				+ cOnChange + "\"" + (lActivo ? ">" : " disabled>"));
		if (lConSelect) {
			sbTmp.append("<option value=\"-1\">Seleccione...</option>");
		}
		try {
			boolean lFirst = false;
			BeanScroller bsTmp = new BeanScroller(vDatos);
			String cValue = request.getParameter(cNombre);
			cValue = cValue != null ? cValue : cOmision;
			for (int i = 0; i < bsTmp.rowSize(); i++) {
				bsTmp.setRowIdx(i + 1);
				String cSelected = cValue.equalsIgnoreCase(bsTmp.getFieldValue(
						cCampoCve, "").toString()) ? " selected " : "";
				sbTmp.append("<option " + cSelected + " value=\""
						+ bsTmp.getFieldValue(cCampoCve, "-1") + "\">"
						+ bsTmp.getFieldValue(cCampoDsc, "") + "</option>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sbTmp.append("</select>");
		return sbTmp;
	}

	/**
	 * Este metodo se encarga de generar el HTML necesario para desplegar un
	 * Select de una l�nea con los datos proporcionados.
	 * 
	 * @param cEstiloE
	 *            Estilo que se asignara a la celda de la etiqueta.
	 * @param cEtiqueta
	 *            Etiqueta que se desea desplegar en la celda de la etiqueta.
	 * @param cEstiloC
	 *            Nombre del estilo que se dara a la celda del select.
	 * @param cNombre
	 *            Nombre que se dara al objeto tipo select de HTML.
	 * @param cValue
	 *            Valor del campo que se genera en caso de no estar activo.
	 * @param cOnChange
	 *            JavaScript a ejecutar en el change del select.
	 * @param vDatos
	 *            Vector que contiene los datos de cada registro a desplegar.
	 * @param cCampoCve
	 *            Nombre del campo clave para extraer el valor a desplegar en la
	 *            secci�n value del select.
	 * @param cCampoDsc
	 *            Nombre del campo descripcion para extraer el valor a desplegar
	 *            en la secci�n option del select.
	 * @param HttpServletRequest
	 *            request de donde se extrae el indice actualmente seleccionado.
	 * @param cOmision
	 *            Valor por omision del Select en caso de no encontrarlo en el
	 *            request.
	 * @param lActivo
	 *            Indica si se debe permitir la captura (="1") o no (!="1") del
	 *            campo (atributo disabled de HTML).
	 * @param lCampo
	 *            Indica si se debe mostrar como un Select o como un texto el
	 *            valor proporcionado.
	 * @return un StringBuffer con el formato adecuado dependiendo de los datos
	 *         proporcionados.
	 */
	public StringBuffer EtiSelectOneRowCS(String cEstiloE, String cEtiqueta,
			String cEstiloC, String cNombre, String cValue, String cOnChange,
			int iSpan, Vector vDatos, String cCampoCve, String cCampoDsc,
			HttpServletRequest request, String cOmision, boolean lConSelect,
			boolean lActivo, boolean lCampo) {
		StringBuffer sbTmp = new StringBuffer("");
		sbTmp.append("<td class=\"" + cEstiloE + "\">" + cEtiqueta + "</td>\n");
		sbTmp.append("<td class=\"" + cEstiloC);
		if (iSpan > 1) {
			sbTmp.append("\" colspan=\"" + iSpan);
		}
		sbTmp.append("\">");
		if (lCampo) {
			sbTmp.append(SelectOneRowSinTD(cNombre, cOnChange, vDatos,
					cCampoCve, cCampoDsc, request, cOmision, lConSelect,
					lActivo));
		} else {
			sbTmp.append(cValue);
		}
		sbTmp.append("</td>\n");
		return sbTmp;
	}
}
