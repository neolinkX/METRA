package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * 
 * <p>
 * Title: Lee Plantillas JXLS de EXCEL
 * </p>
 * <p>
 * Description: Lee la plantilla la procesa y envia el resultado
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 */
public class servXLSpg070309010 extends HttpServlet {

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
		TParametro vParametros = new TParametro("07");
		TDEXPServicio dExpServicio = new TDEXPServicio();
		TDPERDatos dPerDatos = new TDPERDatos();
		TFechas tFechas = new TFechas();

		try {
			// #####################################
			// ## AQUI METER EL CODIGO PARA EXCEL ##
			// #####################################
			if (request.getParameter("numExpediente") != null
					&& !(request.getParameter("numExpediente")
							.compareToIgnoreCase("") == 0)
					&& request.getParameter("numExamen") != null
					&& !(request.getParameter("numExamen").compareToIgnoreCase(
							"") == 0)
					&& request.getParameter("iCveServicio") != null
					&& !(request.getParameter("iCveServicio")
							.startsWith("Seleccione"))
					&& request.getParameter("dtFecha") != null
					&& !(request.getParameter("dtFecha")
							.compareToIgnoreCase("") == 0)) {

				String excelFile = "pg070309010";

				response.setContentType("application/vnd.ms-excel");
				// Lo sig. hace que el archivo se abra fuera de la JSP
				// response.setHeader("Content-Disposition",
				// "attachment;filename=workbook.xls");
				// Lo sig. hace que el archivo se abra dentro de la JSP
				response.setHeader("Content-Disposition", "inline;filename="
						+ excelFile + "-out.xls");

				// LEER LA PLANTILLA DE EXCEL
				// ##########################

				// URL url = new URL("http://localhost:8080/FileUploadJSP/" +
				// excelFile + ".xls");
				// System.out.println("URL = " +
				// vParametros.getPropEspecifica("ExcelRutaOrig") + excelFile +
				// ".xls");
				URL url = new URL(
						vParametros.getPropEspecifica("ExcelRutaOrig")
								+ excelFile + ".xls");
				URLConnection conn = url.openConnection();
				InputStream isPlantillaExcel = conn.getInputStream();
				BufferedInputStream in = new BufferedInputStream(
						isPlantillaExcel);

				/*
				 * ServletContext ctx = getServletContext(); URL url =
				 * ctx.getResource("/WEB-INF/plantillas/" + excelFile + ".xls");
				 * URLConnection conn = url.openConnection(); InputStream
				 * isPlantillaExcel = conn.getInputStream();
				 */

				/*
				 * InputStream isPlantillaExcel = ctx.getResourceAsStream("/" +
				 * excelFile + ".xls");
				 */
				// #####################################

				// Vector vcExpServicio =
				// dExpServicio.getNotaMed(request.getParameter("numExpediente"),
				// request.getParameter("numExamen"),
				// request.getParameter("iCveServicio"));
				HashMap map = new HashMap();
				map.put("iCveExpediente", request.getParameter("numExpediente"));
				map.put("iNumExamen", request.getParameter("numExamen"));
				map.put("iCveServicio", request.getParameter("iCveServicio"));
				Vector vcExpServicio = dExpServicio.findByPK(map);

				TVEXPServicio vExpServicio = new TVEXPServicio();

				StringBuffer bufferNotaMedicaAnterior = new StringBuffer();
				StringBuffer bufferNotaMedicaActual = new StringBuffer();

				for (int i = 0; i < vcExpServicio.size(); i++) {
					vExpServicio = (TVEXPServicio) vcExpServicio.get(i);
					bufferNotaMedicaAnterior.append(vExpServicio
							.getCNotaMedica());
					bufferNotaMedicaAnterior.append("\n");
				}

				int iInserted = 0;
				int iUpdated = 0;

				if (request.getParameter("notaActual") != null) {
					if (!(request.getParameter("notaActual")
							.compareToIgnoreCase("") == 0)) {
						bufferNotaMedicaActual.append(request
								.getParameter("dtFecha"));
						bufferNotaMedicaActual.append(": ");
						bufferNotaMedicaActual.append("\n");
						bufferNotaMedicaActual.append(request
								.getParameter("notaActual"));

						vExpServicio.setICveExpediente(Integer.parseInt(request
								.getParameter("numExpediente")));
						vExpServicio.setINumExamen(Integer.parseInt(request
								.getParameter("numExamen")));
						vExpServicio.setICveServicio(Integer.parseInt(request
								.getParameter("iCveServicio")));

						// Si el Vector > 0 => Ya hay datos ACTUALIZAR
						if (vcExpServicio.size() > 0) {
							vExpServicio.setDtFin(tFechas.TodaySQL());
							vExpServicio
									.setCNotaMedica(bufferNotaMedicaAnterior
											.toString()
											+ " "
											+ "\n"
											+ bufferNotaMedicaActual.toString());
							dExpServicio.update(null, vExpServicio);
							iUpdated = dExpServicio.getIUpdated();
							// Si el Vector == 0 => No hay Datos INSERTAR
						} else {
							vExpServicio.setDtInicio(tFechas.TodaySQL());
							vExpServicio.setDtFin(tFechas.TodaySQL());
							vExpServicio.setLConcluido(0);
							vExpServicio.setCNotaMedica(bufferNotaMedicaActual
									.toString());

							dExpServicio.insert(null, vExpServicio);

							iInserted = dExpServicio.getIInserted();
						}
					}
				}

				// Obtengo el Nombre, Sexo, etc.
				TVPERDatos vPerDatos = dPerDatos.findUserbyExp(vExpServicio
						.getICveExpediente());

				if (vPerDatos != null) {
					POIFSFileSystem fs = new POIFSFileSystem(in);
					HSSFWorkbook wb = new HSSFWorkbook(fs);
					HSSFSheet sheet0 = wb.getSheetAt(0);

					HSSFRow row2 = sheet0.getRow(1);
					HSSFRow row3 = sheet0.getRow(2);
					HSSFRow row4 = sheet0.getRow(3);

					HSSFCell cellServicio = row2.getCell((short) 1);
					HSSFCell cellNumExpediente = row2.getCell((short) 3);
					HSSFCell cellNombre = row3.getCell((short) 1);
					HSSFCell cellEdad = row4.getCell((short) 1);
					HSSFCell cellGenero = row4.getCell((short) 3);

					if (cellServicio == null) {
						cellServicio = row2.createCell((short) 1);
					}

					if (cellNumExpediente == null) {
						cellNumExpediente = row2.createCell((short) 3);
					}

					if (cellNombre == null) {
						cellNombre = row3.createCell((short) 1);
					}

					if (cellEdad == null) {
						cellEdad = row4.createCell((short) 1);
					}

					if (cellGenero == null) {
						cellGenero = row4.createCell((short) 3);
					}

					cellServicio.setCellValue(vExpServicio.getCDscServicio());
					cellNumExpediente.setCellValue(vExpServicio
							.getICveExpediente());
					cellNombre.setCellValue(vPerDatos.getCNombreCompleto());
					cellEdad.setCellValue(vPerDatos.getDtNacimiento());
					cellGenero.setCellValue(vPerDatos.getCGenero());

					String[] notaArray = vExpServicio.getCNotaMedica().split(
							"\n");
					HSSFRow[] rowArray = new HSSFRow[notaArray.length];
					HSSFCell[] cellArray = new HSSFCell[notaArray.length];

					for (int j = 0; j < notaArray.length; j++) {
						int row = j + 5;

						rowArray[j] = sheet0.getRow(row);

						if (rowArray[j] == null) {
							rowArray[j] = sheet0.createRow((short) row);
						}

						cellArray[j] = rowArray[j].getCell((short) 1);

						if (cellArray[j] == null) {
							cellArray[j] = rowArray[j].createCell((short) 1);
						}

						cellArray[j].setCellValue(notaArray[j]);
					}

					int read = 0;
					byte[] bytes = new byte[2048];

					OutputStream os = response.getOutputStream();

					InputStream is = new ByteArrayInputStream(wb.getBytes());

					while ((read = is.read(bytes)) != -1) {
						wb.write(os);
					}

					os.flush();
					os.close();
				} else {
					PrintWriter out = response.getWriter();
					out.println("<script language=\"JavaScript\">");
					out.println("alert('El archivo no pudo ser generado!');");
					out.println("window.close();");
					out.println("</script>");
				}
			}
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
