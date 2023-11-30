package gob.sct.medprev;

import gob.sct.medprev.dwr.MedPredDwr;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;

public class pg0700002CFG {
	TParametro vParametros;
	TVUsuario vUsuario;

	public pg0700002CFG() {
	}

	public void runConfig(TEntorno vEntorno, Vector vBtnSec, Vector vUrlSec,
			Vector vEstSec, TParametro vParams) {
		vParametros = vParams;
		vEntorno.setTituloVentana("Sistema Integral de Protección y Medicina Preventiva en el Transporte");
		vEntorno.setTituloPagina("Acceso al Sistema");
		vEntorno.setArchAyuda("0700002");
		vEntorno.setArchFuncs("FValida");
		vEntorno.setArchTooltips("07_Tooltips");
		vEntorno.clearListaImgs();
		vEntorno.setMetodoForm("POST");
		vEntorno.setActionForm("pg0700002.jsp");
		vEntorno.setOnLoad("document.forms[0].FILUsuario.focus();");
		vEntorno.setUrlLogo("Acerca");
	}

	@SuppressWarnings("unused")
	public void outputHeader2(TEntorno vEntorno, TError vErrores,
			PageContext pc, HttpServletRequest httpReq,
			HttpServletResponse httpResp, String usuario, String password) throws IOException, ServletException {
		TDPermisos dUsuario = new TDPermisos();
		System.out.println("outputHeader2");
		MedPredDwr mp = new MedPredDwr();
		ServletRequest request = pc.getRequest();
		String cUsuario = usuario;
		String cContras = password;
		//String cClues = request.getParameter("FILClues");
System.out.println("nuevo ="+httpReq.getMethod());
		if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
			httpReq.getSession(true).removeAttribute("UsrID");
			httpReq.getSession(true).removeAttribute("MenuUsuario");
			httpReq.getSession(true).removeAttribute("PermisosUsuario");
			httpReq.getSession(true).removeAttribute("SelPer");
		}
		if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
				//if (cUsuario != null && cContras != null && cClues != null) {
				if (cUsuario != null && cContras != null) {
					System.out.println("Claves validas");
					vUsuario = dUsuario.accesoUsuario(cUsuario, cContras,vEntorno.getNumModuloStr());
					/*int clueValida = mp.validaCLUES(cUsuario, cClues);
					if (clueValida == 0 || clueValida == -1) {
						vUsuario = null;
						System.out.println("Clues invalida");
					}*/
				}
			
		}
	}
	
	public void outputHeader(TEntorno vEntorno, TError vErrores,
			PageContext pc, HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws IOException, ServletException {
		TDPermisos dUsuario = new TDPermisos();
		MedPredDwr mp = new MedPredDwr();
		ServletRequest request = pc.getRequest();
		String cUsuario = "";
		String cContras = "";
		//String cClues = request.getParameter("FILClues");

		if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
			httpReq.getSession(true).removeAttribute("UsrID");
			httpReq.getSession(true).removeAttribute("MenuUsuario");
			httpReq.getSession(true).removeAttribute("PermisosUsuario");
			httpReq.getSession(true).removeAttribute("SelPer");
		}
		if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
				//if (cUsuario != null && cContras != null && cClues != null) {
				if (cUsuario != null && cContras != null) {
					System.out.println("Claves validas");
					vUsuario = dUsuario.accesoUsuario(cUsuario, cContras,
							vEntorno.getNumModuloStr());
					/*int clueValida = mp.validaCLUES(cUsuario, cClues);
					if (clueValida == 0 || clueValida == -1) {
						vUsuario = null;
						System.out.println("Clues invalida");
					}*/
				}
			
		}
	}

	public TVUsuario getVUsuario() {
		return vUsuario;
	}

	public boolean usupwdigual(TEntorno vEntorno, TError vErrores,
			PageContext pc2, HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws IOException, ServletException {
		TDPermisos dPermisos = new TDPermisos();
		boolean lSuccess = false;
		ServletRequest request = pc2.getRequest();
		String cUsuario = request.getParameter("FILUsuario");
		try {
			if (dPermisos.accesoUsuario2("" + cUsuario, "" + cUsuario, "07")) {
				lSuccess = true;
			} else {
				lSuccess = false;
			}
		} catch (Exception e) {
			lSuccess = false;
		}
		return lSuccess;
	}

}