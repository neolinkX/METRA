/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.medprev.cntmgr.placas;

import gob.sct.medprev.cntmgr.placas.DBObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.oreilly.servlet.multipart.*;
import com.lowagie.text.pdf.*;
import com.micper.ingsw.TParametro;

public class MostrarArchivoServlet extends HttpServlet {

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TParametro VParametros = new TParametro("7");
		String LINTICVEDOCUMEN = request.getParameter("LINTICVEDOCUMEN");

		AdministradorContenidos content = new AdministradorContenidos();
		// byte[] bytesArchivo = content.bajarArchivo(LINTICVEDOCUMEN);

		// ////////////////////Modificando linea 2014-02-04////////////////
		// /byte[] bytesArchivo = content.bajarArchivo(LINTICVEDOCUMEN);
		// ////////////////////Modificando linea 2016-06-01 AG SA ////////////////
		byte[] bytesArchivo = null;
		try{
			bytesArchivo = content.bajarArchivoNAS(LINTICVEDOCUMEN);
		}catch(IOException e){
			//e.printStackTrace();
		}
		
		if(bytesArchivo == null || bytesArchivo.length==0){
			bytesArchivo = content.obterneByteError();
			response.setContentType("text/html;Charset=UTF-8");
	        PrintWriter salida = response.getWriter();
	        salida.println("<html><head><title>Error en el documento</title></head>");
	        salida.println("<body>");
	        salida.println("<center>");
	        salida.println("<IMG SRC="+VParametros.getPropEspecifica("RutaImgServer")+"NoDisponible.jpg>");
	        salida.println("</center>");
	        salida.println("</body></html>");
	        salida.close();
		}else{
			// ///////////////////////////////////////////////////////////////
			ByteArrayInputStream IS = new ByteArrayInputStream(bytesArchivo);
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
			IS.close();
			fos.close();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.writeTo(fos);
			baos = null;
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
