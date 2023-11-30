/*
 * DownloaderServlet.java
 *
 * Created on 24 de noviembre de 2003, 03:53 PM
 */

package gob.sct.medprev;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import gob.sct.medprev.dao.*;

public class servDownFotoCFG extends HttpServlet {

	public servDownFotoCFG() {
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * Destroys the servlet.
	 */
	public void destroy() {
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest r = (HttpServletRequest) request;
		byte[] archivo = null;
		try {

			boolean lAsigna = false;
			String cDown = "" + request.getParameter("hdDown");

			if (cDown.equals("Foto")) {
				archivo = new TDPERFoto().getFile(Integer.parseInt(
						request.getParameter("hdiCvePersonal"), 10), Integer
						.parseInt(request.getParameter("hdiCveSec"), 10));
			}

			if (cDown.equals("Firma")) {
				archivo = new TDPERFirma().getFile(Integer.parseInt(
						request.getParameter("hdiCvePersonal"), 10));
			}

			if (cDown.equals("Huella")) {
				archivo = new TDPERHuella().getFile(Integer.parseInt(
						request.getParameter("hdiCvePersonal"), 10), Integer
						.parseInt(request.getParameter("hdiCveSec"), 10));
			}

			if (archivo != null) {
				response.setContentType("application/octet-stream");
				OutputStream browser = response.getOutputStream();
				browser.write(archivo);
				browser.flush();
				browser.close();
			} else {
				response.getWriter()
						.println("<script language=\"JavaScript\">");
				response.getWriter()
						.println(
								"alert('La imagen no puede ser mostrada!');window.close();");
				response.getWriter().println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
