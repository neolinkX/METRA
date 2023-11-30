package gob.sct.medprev;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import com.micper.ingsw.*;

/**
 * 
 * <p>
 * Title: Cargar Archivo
 * </p>
 * <p>
 * Description: Carga un archivo de Excel
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ing. Rafael Alcocer Caldera
 * @version 1.0
 */
public class servCholestechUpXLS extends HttpServlet {

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Crea un factory para items de archivos disk-based
		// FileItemFactory factory = new DiskFileItemFactory();

		// Crea un factory para items de archivos disk-based
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Fija los constraints del factory
		// factory.setSizeThreshold(1000000);
		// factory.setRepository(new File("C:\\Temp"));

		// Crea un nuevo manejador para subir archivos
		ServletFileUpload upload = new ServletFileUpload(factory);

		PrintWriter out = response.getWriter();
		TParametro vParametros = new TParametro("07");

		try {
			List items = upload.parseRequest(request);
			Iterator it = items.iterator();

			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();

				// Checa si el item actual es un form field o un archivo subido
				if (item.isFormField()) {

					// Obtiene el nombre del campo
					String fieldName = item.getFieldName();

					// Si es nombre, podemos fijarlo en el request para
					// agradecer al usuario.
					if (fieldName.equals("nombre")) {
						request.setAttribute("msg",
								"Gracias: " + item.getString());
					}
				} else {
					// El item debe ser un archivo subido salvado en el disco.
					// Hay que notar que parece haber un bug en item.getName()
					// ya que regresa el path completo en la maquina del cliente
					// para el nombre del archivo subido, en vez de solo el
					// nombre.
					// Para resolver esto, se usa fullFile.getName().
					File fullFile = new File(item.getName());
					// File savedFile = new
					// File(getServletContext().getRealPath("/"),
					// fullFile.getName());
					// File savedFile = new
					// File(vParametros.getPropEspecifica("ExcelRutaDest"),
					// fullFile.getName());
					File savedFile = new File(
							vParametros.getPropEspecifica("ExcelRutaDest"),
							"CholestechXLS.xls");

					item.write(savedFile);

					try {
						FileReader fileReader = new FileReader(savedFile);
						BufferedReader reader = new BufferedReader(fileReader);
						String line = null;

						out.print("<table border='4' bordercolor='#0000ff'>");

						String cGLU = "";
						String cTC = "";
						String cTRG = "";
						String cHDL = "";
						String cLDL = "";
						String cTCHDL = "";

						int i = 0;

						while ((line = reader.readLine()) != null) {
							if (line.trim().startsWith("TC=")) {
								String[] split = line.split("=");
								String valor = split[1].trim();
								String[] split2 = valor.split("mg");

								cTC = split2[0];
							} else if (line.trim().startsWith("HDL=")) {
								String[] split = line.split("=");
								String valor = split[1].trim();
								String[] split2 = valor.split("mg");

								cHDL = split2[0];
							} else if (line.trim().startsWith("TRG=")) {
								String[] split = line.split("=");
								String valor = split[1].trim();
								String[] split2 = valor.split("mg");

								cTRG = split2[0];
							} else if (line.trim().startsWith("LDL=")) {
								String[] split = line.split("=");
								String valor = split[1].trim();
								String[] split2 = valor.split("mg");

								cLDL = split2[0];
							} else if (line.trim().startsWith("non-HDL=")) {
								// String[] split = line.split("=");
								// String valor = split[1].trim();
								// String[] split2 = valor.split("mg");

								// No se asigna a nada
							} else if (line.trim().startsWith("TC/HDL=")) {
								String[] split = line.split("=");
								String valor = split[1].trim();
								String[] split2 = valor.split("mg");

								cTCHDL = split2[0];
							} else if (line.trim().startsWith("GLU=")) {
								String[] split = line.split("=");
								String valor = split[1].trim();
								String[] split2 = valor.split("mg");

								cGLU = split2[0];
							}

							out.print("<tr>");
							out.print("<td>");
							out.print("<input type='text' name='txtSource"
									+ ++i + "' value='" + line
									+ "' readonly='true' />");
							out.print("</td>");
							out.print("</tr>");

						}

						// Lo sig. innerText solo funciona en IE para Netscape
						// no
						// por lo tanto se utilizó forms[0]
						/*
						 * out.print("<input type='button' value='Enviar Datos' "
						 * +
						 * "onclick='window.opener.document.all.dValorI_41.innerText="
						 * + cGLU + ";" +
						 * "window.opener.document.all.dValorI_45.innerHTML=" +
						 * cTC + ";" +
						 * "window.opener.document.all.dValorI_46.innerText=" +
						 * cTRG + ";" +
						 * "window.opener.document.all.dValorI_48.innerText=" +
						 * cHDL + ";" +
						 * "window.opener.document.all.dValorI_49.innerText=" +
						 * cLDL + ";" +
						 * "window.opener.document.all.dValorI_413.innerText=" +
						 * cTCHDL + "' />");
						 */
						out.print("<input type='button' value='Enviar Datos' "
								+ "onclick='window.opener.document.forms[0].dValorI_41.value="
								+ cGLU
								+ ";"
								+ "window.opener.document.forms[0].dValorI_45.value="
								+ cTC
								+ ";"
								+ "window.opener.document.forms[0].dValorI_46.value="
								+ cTRG
								+ ";"
								+ "window.opener.document.forms[0].dValorI_48.value="
								+ cHDL
								+ ";"
								+ "window.opener.document.forms[0].dValorI_49.value="
								+ cLDL
								+ ";"
								+ "window.opener.document.forms[0].dValorI_413.value="
								+ cTCHDL
								+ ";"
								+ "window.opener.document.forms[0].dValorI_41.readOnly="
								+ true
								+ ";"
								+ "window.opener.document.forms[0].dValorI_45.readOnly="
								+ true
								+ ";"
								+ "window.opener.document.forms[0].dValorI_46.readOnly="
								+ true
								+ ";"
								+ "window.opener.document.forms[0].dValorI_48.readOnly="
								+ true
								+ ";"
								+ "window.opener.document.forms[0].dValorI_49.readOnly="
								+ true
								+ ";"
								+ "window.opener.document.forms[0].dValorI_413.readOnly="
								+ true + ";' />");
						out.print("</table>");

						reader.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (FileUploadException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
