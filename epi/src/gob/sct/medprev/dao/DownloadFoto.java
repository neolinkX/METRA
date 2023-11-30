/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.micper.seguridad.vo.*;
import com.micper.ingsw.TParametro;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.micper.util.ZipDecompressor;
import gob.sct.medprev.cntmgr.*;
import java.io.*;

/**
 * 
 * @author AG SA
 */

public class DownloadFoto extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String[] keys = { "userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen" };
		String[] operators = { "", "", "", "", "", "=" };

		CM_GetContent cmImport = new CM_GetContent();
		String Docto = "";
		int iCarga = 0;
		byte[] btArchivo = null;
		byte[] btConten = null;
		TDLICFFH dLicFFH = new TDLICFFH();

		StringBuffer cSQL = new StringBuffer();
		cSQL.append(" select iNoDocto as iNoDocto ")
				.append(" from LICFFH ")
				.append(" where tscaptura = ")
				.append("                   (select max(L.tscaptura) ")
				.append("                   FROM LICFFH AS L, EXPEXAMCAT AS C ")
				.append("                   WHERE L.iCvePersonal = ")
				.append(request.getParameter("ICVEPERSONAL"))
				.append("                   AND L.ICVETIPOFFH = 1 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL ")
				.append("                   AND C.INUMEXAMEN = ")
				.append(request.getParameter("INUMEXAMEN"))
				.append("                   AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS )");

		try {
			Docto = dLicFFH.findByCustom(cSQL.toString());
			if (!Docto.equals("null")) {
				System.out.println(Docto);
				String[] values = { "db2cmuca", "ucacmv82", "eLicDoc", "1",
						"true", Docto };
				int iOpc = 1;
				btArchivo = cmImport.connect(keys, values, operators);

			} else {
				iCarga = 1;
			}
		} catch (Exception e) {
			iCarga = 2;
			System.out.print("LIDownload.processRequest");
			e.printStackTrace();
		} finally {
			if (iCarga == 0) {
				response.setContentType("application/octet-stream");
				OutputStream browser = response.getOutputStream();
				browser.write(btArchivo);
				browser.flush();
				browser.close();
			} else if (iCarga == 3) {
				response.getWriter()
						.println(
								"<html><body><form name=\"frm1\"></form><script language=\"JavaScript\">");
				response.getWriter().println(
						"alert(\"¡No fue posible consultar el documento!\");");
				response.getWriter().println("window.parent.close();");
				response.getWriter().println("</script></body></html>");

			} else {
				response.getWriter()
						.println(
								"<html><body><form name=\"frm1\"></form><script language=\"JavaScript\">");
				response.getWriter()
						.println(
								"alert(\"¡Este examen no cuenta con foto capturada!\");");
				response.getWriter().println("window.parent.close();");
				response.getWriter().println("</script></body></html>");
			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
