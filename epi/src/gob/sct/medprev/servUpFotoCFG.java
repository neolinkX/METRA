package gob.sct.medprev;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//import com.oreilly.servlet.multipart.*;
import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;
import gob.sct.medprev.dao.*;

public class servUpFotoCFG extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest r = (HttpServletRequest) request;
		MultipartParser mp;
		Part p = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			mp = new MultipartParser(r, Integer.MAX_VALUE);
			while ((p = mp.readNextPart()) != null) {
				if (p.isFile()) {
					FilePart fp = (FilePart) p;
					fp.writeTo(baos);
					baos.close();
				} else {
					throw new InternalError(
							"PART DE TIPO DESCONOCIDO. INCAPAZ PARSEAR REQUEST.");
				}
			}
			byte[] archivo = baos.toByteArray();
			try {
				boolean lAsigna = false;
				String cUp = "" + request.getParameter("hdUp");
				if (cUp.equals("Foto")) {
					new TDPERFoto()
							.saveFile(archivo, Integer.parseInt(
									request.getParameter("hdiCvePersonal"), 10));
					lAsigna = true;
				}
				if (cUp.equals("Firma")) {
					new TDPERFirma()
							.saveFile(archivo, Integer.parseInt(
									request.getParameter("hdiCvePersonal"), 10));
					lAsigna = true;
				}
				if (cUp.equals("Huella")) {
					new TDPERHuella()
							.saveFile(
									archivo,
									Integer.parseInt(request
											.getParameter("hdiCvePersonal"), 10),
									Integer.parseInt(request
											.getParameter("hdiCveHuella"), 10));
					lAsigna = true;
				}

				if (!lAsigna) {
					throw new Exception("");
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				response.getWriter()
						.println("<script language=\"JavaScript\">");
				response.getWriter()
						.println(
								"alert('La imagen no ha sido guardada, el tama�o m�ximo es 256kb!');window.close();");
				response.getWriter().println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println("<script language=\"JavaScript\">");
		response.getWriter().println("window.opener.fClose();window.close();");
		response.getWriter().println("</script>");
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
