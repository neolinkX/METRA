package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.msc.*;

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
public class servXLSpg0703060110 extends HttpServlet {

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
		TFechas tFechas = new TFechas();

		try {
			// #####################################
			// ## AQUI METER EL CODIGO PARA EXCEL ##
			// #####################################
			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("cLote") != null
					&& request.getParameter("iCveEquipo") != null
					&& request.getParameter("dtFechaI") != null
					&& request.getParameter("dtFechaF") != null) {

				TDTOXCtrolCalibra dTOXCtrolCalibra = new TDTOXCtrolCalibra();

				String cFiltro = " where CC.lCual = 1 "
						+ // => Sólo Análisis Presuntivo
						" and CC.dtPreparacion between '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaI"))
						+ "'" + "                      and '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaF"))
						+ "'" + " and E.iCveEquipo = "
						+ request.getParameter("iCveEquipo")
						+ " and CC.cLote = '" + request.getParameter("cLote")
						+ "' Order by 12,14  DESC";
				// " and CC.iCveEmpleoCalib = 4 " +
				// " and CC.iCveCtrolCalibra = 5 ";

				String excelFile = "pg0703060110";

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
				// URL url = new URL("http://127.0.0.1:7001/medprev/" +
				// excelFile + ".xls");
				// System.out.println("URL = " +
				// vParametros.getPropEspecifica("ExcelRutaOrig") + excelFile +
				// ".xls");
				URL url = new URL(
						vParametros.getPropEspecifica("ExcelRutaOrig")
								+ excelFile + ".xls");
				URLConnection conn = url.openConnection();
				InputStream isPlantillaExcel = conn.getInputStream();

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

				JXLSBean jxlsbean = dTOXCtrolCalibra.generaReporteXLS(cFiltro);

				Map beans = new HashMap();
				beans.put("jxlsbean", jxlsbean);
				XLSTransformer transformer = new XLSTransformer();
				transformer.markAsFixedSizeCollection("jxlsbean");
				HSSFWorkbook workbook = transformer.transformXLS(
						isPlantillaExcel, beans);

				HSSFSheet sheet0 = workbook.getSheetAt(0);

				HSSFRow row8 = sheet0.getRow(7);
				HSSFRow row9 = sheet0.getRow(8);

				HSSFCell cellCodigoControl = row8.getCell((short) 2);
				HSSFCell cellConcentracion = row8.getCell((short) 4);
				HSSFCell cellConcentExper = row8.getCell((short) 6);
				HSSFCell cellRespuesta = row8.getCell((short) 8);
				HSSFCell cellEquipo = row9.getCell((short) 2);
				HSSFCell cellModelo = row9.getCell((short) 4);
				HSSFCell cellSerie = row9.getCell((short) 6);
				HSSFCell cellFechaAutorizacion = row9.getCell((short) 8);

				if (cellCodigoControl == null) {
					cellCodigoControl = row8.createCell((short) 2);
				}

				if (cellConcentracion == null) {
					cellConcentracion = row8.createCell((short) 4);
				}

				if (cellConcentExper == null) {
					cellConcentExper = row8.createCell((short) 6);
				}

				if (cellRespuesta == null) {
					cellRespuesta = row8.createCell((short) 8);
				}

				if (cellEquipo == null) {
					cellEquipo = row9.createCell((short) 2);
				}

				if (cellModelo == null) {
					cellModelo = row9.createCell((short) 4);
				}

				if (cellSerie == null) {
					cellSerie = row9.createCell((short) 6);
				}

				if (cellFechaAutorizacion == null) {
					cellFechaAutorizacion = row9.createCell((short) 8);
				}

				if (dTOXCtrolCalibra.getBean2().getClote() != null) {
					// cellCodigoControl.setCellValue(dTOXCtrolCalibra.getBean2().getClote());
					cellCodigoControl.setCellValue(request
							.getParameter("cLote"));
				} else {
					cellCodigoControl.setCellValue("");
				}

				if (dTOXCtrolCalibra.getBean2().getDconcentracion() != null) {
					cellConcentracion.setCellValue(dTOXCtrolCalibra.getBean2()
							.getDconcentracion().doubleValue());
				} else {
					cellConcentracion.setCellValue("");
				}

				if (dTOXCtrolCalibra.getBean2().getDconcentexper() != null) {
					cellConcentExper.setCellValue(dTOXCtrolCalibra.getBean2()
							.getDconcentexper().doubleValue());
				} else {
					cellConcentExper.setCellValue("");
				}

				if (dTOXCtrolCalibra.getBean2().getCdscequipo() != null) {
					cellEquipo.setCellValue(dTOXCtrolCalibra.getBean2()
							.getCdscequipo());
				} else {
					cellEquipo.setCellValue("");
				}

				if (dTOXCtrolCalibra.getBean2().getCmodelo() != null) {
					cellModelo.setCellValue(dTOXCtrolCalibra.getBean2()
							.getCmodelo());
				} else {
					cellModelo.setCellValue("");
				}

				if (dTOXCtrolCalibra.getBean2().getCnumserie() != null) {
					cellSerie.setCellValue(dTOXCtrolCalibra.getBean2()
							.getCnumserie());
				} else {
					cellSerie.setCellValue("");
				}

				if (dTOXCtrolCalibra.getBean2().getDtautoriza() != null) {
					cellFechaAutorizacion.setCellValue(dTOXCtrolCalibra
							.getBean2().getDtautoriza());
				} else {
					cellFechaAutorizacion.setCellValue("");
				}

				if (request.getParameter("cRespuesta") != null) {
					if (request.getParameter("cRespuesta").equals("")) {
						cellRespuesta.setCellValue("");
					} else {
						cellRespuesta.setCellValue(request
								.getParameter("cRespuesta"));
					}
				}

				int read = 0;
				byte[] bytes = new byte[2048];

				OutputStream os = response.getOutputStream();

				InputStream is = new ByteArrayInputStream(workbook.getBytes());

				while ((read = is.read(bytes)) != -1) {
					workbook.write(os);
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
