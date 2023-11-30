package com.micper.seguridad.dao;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.logging.*;

public class CFGBase {
	protected String UpdStatus = "hidden";
	protected String NavStatus = "hidden";
	protected String OtroStatus = "hidden";
	protected String CanWrite = "no";
	protected String SaveAction = "no";
	protected String DeleteAction = "no";
	protected String cOrden = "";
	protected String cCondicion = "";
	protected String hdCondicion = "";
	protected String cActual = null;
	protected String cClave = null;
	protected boolean Captura = false;
	protected boolean Nuevo = false;
	protected boolean AccesoValido = false;
	protected TParametro vParametros;
	protected int iNumReg;
	protected String cAccionOriginal = "";
	protected String cAccion = null;
	protected HttpServletRequest httpReq = null;
	protected JspWriter out = null;
	protected ServletRequest request = null;
	protected Vector vDespliega = new Vector();
	protected TError vErrores;
	protected String cDscPaginas = "";
	protected String cPaginas = "";
	protected boolean lVerAcceso = true;
	protected HashMap hmPUsuario;

	public void CFGBase() {
	}

	public void setAccesos(PageContext pc) {
		if (pc.getSession().getAttribute("PermisosUsuario") != null) {
			hmPUsuario = (HashMap) pc.getSession().getAttribute(
					"PermisosUsuario");
		}
	}

	public void verOtrosAccesos() {
		String cPaginaAux = cPaginas;
		StringTokenizer stPaginas = new StringTokenizer(cPaginaAux, "|");
		try {
			cPaginas = cDscPaginas = "";
			StringTokenizer stDscPag;
			while (stPaginas.hasMoreTokens()) {
				cPaginaAux = "" + stPaginas.nextElement();
				if (hmPUsuario.containsKey(cPaginaAux)) {
					cPaginas = cPaginas + cPaginaAux + "|";
					stDscPag = new StringTokenizer(""
							+ hmPUsuario.get(cPaginaAux), "|");
					if (stDscPag.countTokens() == 2) {
						cPaginaAux = "" + stDscPag.nextElement();
					} else {
						cPaginaAux = "";
					}
					cDscPaginas = cDscPaginas + cPaginaAux + "|";
				}
			}
		} catch (Exception e) {
			error("verOtrosAccesos:" + cPaginas + "->" + cDscPaginas, e);
		}
	}

	public void verAcceso(String cPagina) {
		String cWrite = "";
		System.out.println("uno");
		if (hmPUsuario != null) {
			System.out.println("dos");
			if (hmPUsuario.containsKey(cPagina)) {
				System.out.println("tres");
				AccesoValido = true;
				StringTokenizer stWrite = new StringTokenizer(
						(String) hmPUsuario.get(cPagina), "|");
				if (stWrite.countTokens() == 2) {
					System.out.println("cuarto");
					stWrite.nextElement();
					System.out.println("cinco");
					cWrite = (String) stWrite.nextElement();
					if (cWrite.compareTo("1") == 0) {
						System.out.println("seis");
						CanWrite = "yes";
					}
				}
			}
		}
	}
	
	public void verAcceso2() {
		AccesoValido = true;
		lVerAcceso = true;
		this.setVerAcceso(lVerAcceso);
		//Captura = true;
		//Nuevo = true;
	}

	public String getDscPaginas() {
		return cDscPaginas;
	}

	public String getPaginas() {
		return cPaginas;
	}

	/**
	 * M�todo encargado de enviar el estado que va a tener el panel de
	 * actualizaci�n.
	 * 
	 * @return Estado del panel de actualizaci�n.
	 */
	public String getUpdStatus() {
		return UpdStatus;
	}

	/**
	 * M�todo encargado de enviar el estado que va a tener el panel de
	 * navegaci�n.
	 * 
	 * @return Estado del panel de navegaci�n.
	 */
	public String getNavStatus() {
		return NavStatus;
	}

	/**
	 * M�todo encargado de enviar el estado que va a tener el panel especial.
	 * 
	 * @return Estado del panel especial.
	 */
	public String getOtroStatus() {
		return OtroStatus;
	}

	/**
	 * M�todo encargado de enviar si el usuario tiene o no permiso de escritura.
	 * 
	 * @return Valor del atributo, verdadero si tiene permiso, falso en caso
	 *         contrario.
	 */
	public String getCanWrite() {
		return CanWrite;
	}

	/**
	 * M�todo encargado de enviar el valor almacenado en la propiedad de la
	 * acci�n del bot�n guardar en el panel de actualizaci�n.
	 * 
	 * @return Valor del atributo.
	 */
	public String getSaveAction() {
		return SaveAction;
	}

	/**
	 * M�todo encargado de enviar el valor almacenado en la propiedad de la
	 * acci�n del bot�n borrar en el panel de actualizaci�n.
	 * 
	 * @return Valor del atributo.
	 */
	public String getDeleteAction() {
		return DeleteAction;
	}

	/**
	 * M�todo encargado de enviar el valor de la propiedad de captura, la cual
	 * indica si se presenta en el JSP una forma o los datos.
	 * 
	 * @return Valor asignado al atributo.
	 */
	public boolean getCaptura() {
		return Captura;
	}

	/**
	 * M�todo encargado de enviar el valor que indica si se va a presentar una
	 * forma vac�a.
	 * 
	 * @return Valor asignado al atributo.
	 */
	public boolean getNuevo() {
		return Nuevo;
	}

	/**
	 * M�todo encargado de enviar la condici�n utilizada para filtrar los datos.
	 * 
	 * @return Valor de la condici�n del filtro.
	 */
	public String gethdCondicion() {
		return hdCondicion;
	}

	/**
	 * M�todo encargado de enviar el valor que indica si puede o no ejecutar el
	 * cat�logo.
	 * 
	 * @return Valor de la propiedad.
	 */
	public boolean getLPagina(String cPagina) {
		boolean lAcceso = true;
		if (lVerAcceso) {
			lAcceso = false;
			if (hmPUsuario != null) {
				lAcceso = hmPUsuario.containsKey(cPagina);
			}
		}
		System.out.println("##############################################");
		System.out.println("getLPagina = "+lAcceso);
		return lAcceso;
	}

	/**
	 * M�todo encargado de enviar el valor que indica si el usuario accedi� al
	 * programa de manera correcta.
	 * 
	 * @return Valor de la propiedad.
	 */
	public boolean getAccesoValido() {
		return AccesoValido;
	}

	public String getAccionOriginal() {
		return cAccionOriginal;
	}

	public void setVerAcceso(boolean lVerAcceso) {
		this.lVerAcceso = lVerAcceso;
	}

	/**
	 * M�todo encargado de enviar mensajes de advertencia que el programador
	 * decida enviar, dentro del metodo catch.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	protected void warn(String Aviso, Exception ex) {
		TLogger.log(this, TLogger.WARN, Aviso, ex);
	}

	/**
	 * M�todo encargado de enviar mensajes informativos que el programador
	 * decida enviar. Se pueden poner en cualquier parte del c�digo.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 */
	public void info(String Aviso) {
		TLogger.log(this, TLogger.INFO, Aviso, null);
	}

	/**
	 * M�todo encargado de enviar mensajes de debugueo que el programador decida
	 * enviar. Se pueden poner en cualquier parte del c�digo y tendr�n la opcion
	 * de poderse apagar mediante una bandera.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 */
	public void debug(String Aviso) {
		TLogger.log(this, TLogger.DEBUG, Aviso, null);
	}

	/**
	 * M�todo encargado de enviar mensajes de error que el programador decida
	 * enviar, dentro del metodo catch.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	public void error(String Aviso, Exception ex) {
		TLogger.log(this, TLogger.ERROR, Aviso, ex);
	}

	/**
	 * M�todo encargado de enviar mensajes de errores fatales que el programador
	 * decida enviar, dentro del metodo catch.
	 * 
	 * @param obj
	 *            se refiere al modulo en donde ocurre el error.
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	public void fatal(String Aviso, Exception ex) {
		TLogger.log(this, TLogger.FATAL, Aviso, ex);
	}

	public void setCPaginas(String cPaginas) {
		this.cPaginas = cPaginas;
	}
}
