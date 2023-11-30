/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.cntmgr;

import gob.sct.medprev.cntmgr.placas.AdministradorContenidos;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.micper.ingsw.TParametro;

/**
 * 
 * @author admin
 */
public class BajarArchivoCMServlet extends HttpServlet {

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String LINTICVEDOCUMEN = request.getParameter("LINTICVEDOCUMEN");
		String idTipoDocumento = request.getParameter("idTipoDocumento");
		String icveexpediente = request.getParameter("icveexpediente");
		String IDEDO = request.getParameter("IDEDO");
		 //System.out.println("LINTICVEDOCUMEN "+LINTICVEDOCUMEN);
		 //System.out.println("icveexpediente "+icveexpediente);
		 //System.out.println("IDEDO "+IDEDO);
		TParametro VParametros = new TParametro("7");
		String dataSourceName = VParametros
				.getPropEspecifica("ConDBModulo");
		/*OutputStream bosR = new FileOutputStream(
				VParametros.getPropEspecifica("RutaNAS2") + "r-"
						+ icveexpediente + ".bmp");*/
		// OutputStream bos = new FileOutputStream("C://nas/" + "r-" +
		// icveexpediente + ".bmp");


		AdministradorContenidos content = new AdministradorContenidos();
		byte[] bytesArchivo = null;
		if (icveexpediente != null) {
			if (idTipoDocumento.equals("0")) {
				bytesArchivo = content.bajarArchivoFoto(LINTICVEDOCUMEN);
				sendResponseNormal(bytesArchivo, response);
			}
			if (idTipoDocumento.equals("1")) {
				bytesArchivo = content.bajarArchivoHuella(LINTICVEDOCUMEN);
				sendResponseNormal(bytesArchivo, response);
			}
			if (idTipoDocumento.equals("2")) {
				bytesArchivo = content.bajarArchivoFirma(LINTICVEDOCUMEN);
				//bosR.write(bytesArchivo);
				sendResponseNormal(bytesArchivo, response);
			}
			if (idTipoDocumento.equals("3")) {
				if (IDEDO.equals("2")) {
					bytesArchivo = content
							.buscarHuellaNASPacientesConExpediente(icveexpediente);
					if (bytesArchivo == null)
						bytesArchivo = content
								.bajarArchivoHuellaPacienteBMP(LINTICVEDOCUMEN);
					sendResponseBMP(bytesArchivo, response);
				} else {
					bytesArchivo = content
							.bajarArchivoHuellaPacienteBMP(LINTICVEDOCUMEN);
					sendResponseBMP(bytesArchivo, response);
				}
			}
		} else {
			if (idTipoDocumento.equals("0")) {
				bytesArchivo = content.bajarArchivoFoto(LINTICVEDOCUMEN);
				sendResponseNormal(bytesArchivo, response);
			}
			if (idTipoDocumento.equals("1")) {
				bytesArchivo = content.bajarArchivoHuella(LINTICVEDOCUMEN);
				sendResponseNormal(bytesArchivo, response);
			}
			if (idTipoDocumento.equals("2")) {
				bytesArchivo = content.bajarArchivoFirma(LINTICVEDOCUMEN);
				sendResponseNormal(bytesArchivo, response);
			}
			if (idTipoDocumento.equals("3")) {
				bytesArchivo = content.bajarArchivoHuellaBMP(LINTICVEDOCUMEN);
				sendResponseBMP(bytesArchivo, response);
			}
		}
		//bosR.close();
		//bosR = null;
	}

	public void sendResponseNormal(byte[] bytesArchivo,
			HttpServletResponse response) {
		try {
			// ByteArrayInputStream IS = new ByteArrayInputStream(bytesArchivo);
			response.reset();
			// String disHeader = "Attachment;Filename=\"Imagen.jpg\"";
			// response.setHeader("Content-Disposition", disHeader);
			// response.setContentType("application/octet-stream");
			// response.setContentLength(bytesArchivo.length);
			response.setContentType("image/jpeg");
			response.setHeader("Cache-control", "public");
			// OutputStream fos = response.getOutputStream();
			ServletOutputStream fos = response.getOutputStream();
			fos.write(bytesArchivo, 0, bytesArchivo.length);
			// IS.close();
			fos.close();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.writeTo(fos);
			baos = null;
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	public void sendResponseBMP(byte[] bytesArchivo,
			HttpServletResponse response) {
		try {
			response.setContentType("application/octet-stream");
			OutputStream browser = response.getOutputStream();
			browser.write(bytesArchivo);
			browser.flush();
			browser.close();
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	public static byte[] obterneByteArchivos(File fichero) throws IOException {
		FileInputStream fos = new FileInputStream(fichero);
		byte[] zipped = new byte[(int) fichero.length()];
		fos.read(zipped);
		fos.close();
		return zipped;
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Process the HTTP Put request
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// Process the HTTP Delete request
	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Borrando el Servlet");
	}

	// Clean up resources
	public void destroy() {
		//System.out.println("Destruyendo el Servlet");
	}
}
