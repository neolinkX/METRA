/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.cntmgr.placas;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import org.apache.commons.fileupload.FileItem;
import com.micper.ingsw.TParametro;

//import weblogic.apache.org.apache.velocity.texen.util.FileUtil;

public class AdministradorContenidosServletServicios extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TParametro VParametros = new TParametro("7");

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		FileUploadWrapper wrapper = new FileUploadWrapper(request);

		PrintWriter out = response.getWriter();
		
		String iCveUsuario =request.getParameter("archivo");
		String iCveExpediente =request.getParameter("iCveExpediente");
		String iNumExamen =request.getParameter("iNumExamen");
		String iCveServicio =request.getParameter("iCveServicio");
		String iCveRama =request.getParameter("iCveRama");
		
		//File archivo = File.createTempFile("myfile", ".tmp");
		File archivo = File.createTempFile("myfile", ".tmp", new File(VParametros.getPropEspecifica("RutaNASServicios")));
		// byte[] archivoBytes = null;
		int resultado = 0;
		//System.out.println("Guardando en la NAS MyFile.tmp");
		int TamMaxImgServicios = Integer.parseInt(VParametros
				.getPropEspecifica("TamanioMaxImgServicios"));

		try {
			try {
				FileItem fileItem = wrapper.getFileItem("file");
				try {
					fileItem.write(archivo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int claveDocumento = 0;
		//claveDocumento=Integer.parseInt(request.getParameter("archivo"));
		DBObject db = new DBObject();
		
		AdministradorContenidosServicios content = new AdministradorContenidosServicios();

		// Esta linea esta temporalmente desabilitada
		// int resultado = content.subirArchivo(claveDocumento,
		// obterneByteArchivos(archivo));
		// /////hasta la proxima semana 04 02 2014

		// Nueva implementacion guarda content, nas y valida tamaño
		if (archivo.length() >= TamMaxImgServicios) {
			// /La Imagen es mas grande de lo permitido
			resultado = 2;
		} else {
			if (archivo.length() > 10) {
				ArrayList<?> info = db
						.executeQuery("SELECT * FROM CONTDOCMEDPREV WHERE IDULTIMODOC = 'ULTIMA_PLACA' ");
				Iterator<?> it = info.iterator();
				while (it.hasNext()) {
					String[] values = (String[]) it.next();
					try {
						claveDocumento = Integer.parseInt(values[1]) + 1;
						db.executeInsert("UPDATE CONTDOCMEDPREV SET VALOR="
								+ claveDocumento
								+ " WHERE IDULTIMODOC = 'ULTIMA_PLACA' ");
					} catch (Exception e) {
						claveDocumento = 1;
						db.executeInsert("UPDATE CONTDOCMEDPREV SET VALOR="
								+ claveDocumento
								+ " WHERE IDULTIMODOC = 'ULTIMA_PLACA' ");
					}

				}
				//resultado = content.subirArchivoNAS(claveDocumento,obterneByteArchivos(archivo));//Comentado el 31 de Mayo 2016 AG SA
				resultado = content.subirArchivoNASV2(claveDocumento,obterneByteArchivos(archivo));//Agregado el 18 Noviembre 20176 AG SA
				// resultado = 0;				
			}else{
				resultado = 1;
			}
		}

		// int resultado = content.subirArchivo(claveDocumento,archivoBytes);
		// System.out.println("resultados="+resultado);
		if (resultado == 0) {// si se subio el archivo correctamente
			// guardar folio en tabla del sistema
			guardaReferenciaArchivo(claveDocumento, wrapper, iCveExpediente, 
					 iNumExamen, iCveServicio, iCveRama, iCveUsuario );
			out.print("<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"%>"
					+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
					+ "				<jsp:include page=\"/sys/inc/valida.inc.jsp\" flush=\"true\" />"
					+ "<html>"
					+ "<head>"
					+ "<script language=\"JavaScript\" type=\"text/JavaScript\">"
					+ "	function fSetLlave() {"
					+ "		if (window.opener.fSetValue) {"
					+ "			window.opener.fSetValue("
					+ claveDocumento
					+ ");"
					+ "		} else {"
					+ "			alert('No tiene la funcion setvalue');"
					+ "		}"
					+ "	}"
					+ "</script>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
					+ "<title>Exito en el guardado</title>"
					+ "<style type=\"text/css\">"
					+ "<!--"
					+ ".style1 {"
					+ "	font-size: 18px;"
					+ "	color: #FFFFFF;"
					+ "}"
					+ "-->"
					+ "</style>"
					+ "</head>"
					+ "<body onLoad=\"fSetLlave();\">"
					+ "<form method=\"get\" action=\"AdministradorContenidosServlet\" enctype=\"multipart/form-data\">"
					+ "<br>"
					+ "<div class=\"style1\" style=\"background:#0099FF\" onClick=\"fSetLlave();\">Guardado del archivo exitoso."
					+ "</div>"
					+ "  <input type=\"hidden\" name=\"iCveDocumento\" id =\"iCveDocumento\" value=\""
					+ claveDocumento
					+ "\">"
					+ "</form>"
					+ "</body>"
					+ "</html>");
		} else {
			if (resultado == 1) {// ///////// ERROR AL GUARDAR ARCHIVO
									// ////////////
				out.print("<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"%>"
						+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
						+ "				<jsp:include page=\"/sys/inc/valida.inc.jsp\" flush=\"true\" />"
						+ "<html>"
						+ "<head>"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
						+ "<title>Error!!</title>"
						+ "<style type=\"text/css\">"
						+ "<!--"
						+ ".style1 {"
						+ "	font-size: 18px;"
						+ "	color: #FFFFFF;"
						+ "}"
						+ "-->"
						+ "</style>"
						+ "</head>"
						+ "<body>"
						+ "<form method=\"get\" action=\"AdministradorContenidosServlet\" enctype=\"multipart/form-data\">"
						+ "<br>"
						+ "<div class=\"style1\" style=\"background:#FF0000\">Error en el guardado de archivos.</div>"
						+ "<a href=\"./SubirArchivo.jsp\">Intentar de nuevo</a>"
						+ "</form>" + "</body>" + "</html>");
			}
			if (resultado == 2) {// ///////// MAYOR A 4 MEGABYTES (MB)
									// ////////////
				out.print("<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"%>"
						+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
						+ "				<jsp:include page=\"/sys/inc/valida.inc.jsp\" flush=\"true\" />"
						+ "<html>"
						+ "<head>"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
						+ "<title>Error!!</title>"
						+ "<style type=\"text/css\">"
						+ "<!--"
						+ ".style1 {"
						+ "	font-size: 18px;"
						+ "	color: #FFFFFF;"
						+ "}"
						+ "-->"
						+ "</style>"
						+ "</head>"
						+ "<body>"
						+ "<form method=\"get\" action=\"AdministradorContenidosServlet\" enctype=\"multipart/form-data\">"
						+ "<br>"
						+ "<div class=\"style1\" style=\"background:#FF0000\">La imagen que est&aacute; intentando guardar es mayor a 4 megabytes (MB).</div>"
						+ "<a href=\"./SubirArchivo.jsp\">Reduce el tama&ntilde;o de la imagen e intentar subirla nuevamente</a>"
						+ "</form>" + "</body>" + "</html>");
			}
		}
	}

	public byte[] obterneByteArchivos(File fichero) throws IOException {
		FileInputStream fos = new FileInputStream(fichero);
		byte[] zipped = new byte[(int) fichero.length()];
		fos.read(zipped);
		fos.close();
		return zipped;
	}

	public void guardaReferenciaArchivo(int idArchivo, FileUploadWrapper wrapper, String iCveExpediente, 
			 String iNumExamen,  String iCveServicio,  String iCveRama, String iCveUsuario) {
		DBObject db = new DBObject();
		String SQL = "INSERT INTO EXPSERVARCHCM(" + "ICVEEXPEDIENTE, "
				+ "INUMEXAMEN, " + "ICVESERVICIO, " + "ICVERAMA, "
				+ "LINTICVEDOCUMEN, " + "TSGENERADO, " + "ICVEUSUARIO, "
				+ "CTIPODOCUMENTO" + ",LVALIDO) " + "VALUES (" + ""
				+ iCveExpediente + ", " + ""
				+ iNumExamen + ", " + ""
				+ iCveServicio + ", " + ""
				+ iCveRama + ", " + idArchivo + ", "
				+ "CURRENT TIMESTAMP, " + ""
				+ iCveUsuario + ", " + "'" + "jpeg"
				+ "'" + ",1)";
		System.out.println(SQL);
		db.executeInsert(SQL);
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
		// System.out.println("Borrando el Servlet");
	}

	// Clean up resources
	public void destroy() {
		System.out.println("Destruyendo el Servlet");
	}
}
