package gob.sct.medprev.cntmgr;

import gob.sct.medprev.cntmgr.placas.AdministradorContenidos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BajarHuellaBMPconINoDocto
 */
public class BajarHuellaBMPconINoDocto extends HttpServlet {

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try{
		String LINTICVEDOCUMEN = request.getParameter("iNoDocto");
		String LINTTIPOPERSONA = request.getParameter("iTipoPersona");
		String LINTEXPEDIENTE = request.getParameter("iCveExpediente");
		String LINTIDEDO = request.getParameter("iDedo");

		AdministradorContenidos content = new AdministradorContenidos();
		byte[] bytesArchivo = null;

		if (LINTTIPOPERSONA.equals("1")) {// Medico
			bytesArchivo = content.bajarArchivoHuellaBMP(LINTICVEDOCUMEN);
			sendResponseBMP(bytesArchivo, response);
		} else {// Paciente
				// Se busca la huella del paciente en la nas con su expediente
				// se le tomarón HOY los biometricos
			if (LINTIDEDO.equals("2")) {
				bytesArchivo = content
						.buscarHuellaNASPacientesConExpediente(LINTEXPEDIENTE);
			}
			if (bytesArchivo == null) {
				/*
				 * En caso de que no se los hayan tomado HOY se baja
				 * directamente del CONTENT se busca en la NAS por mmedio de la
				 * clave de documento antes de intentar bajarla si es que ya
				 * habia ingresado al sistema antes y su huella esta en la NAS
				 */
				bytesArchivo = content
						.bajarArchivoHuellaPacienteBMP(LINTICVEDOCUMEN);
			}
			sendResponseBMP(bytesArchivo, response);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
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
		//System.out.println("Borrando el Servlet");
	}

	// Clean up resources
	public void destroy() {
		//System.out.println("Destruyendo el Servlet");
	}

}
