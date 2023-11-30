package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.jxls.transformer.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.msc.*;
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
public class servXLSpg070307020 extends HttpServlet {

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
		TDTOXTemperRefr dToxTemperRefr = new TDTOXTemperRefr();
		TDTOXTurnoRef dToxTurnoRef = new TDTOXTurnoRef();

		if (request.getParameter("iCveArea") != null
				&& !request.getParameter("iCveArea").equals("")
				&& request.getParameter("iAnio") != null
				&& !request.getParameter("iAnio").equals("")
				&& request.getParameter("iMeses") != null
				&& !request.getParameter("iMeses").equals("")
				&& request.getParameter("iCveEquipo") != null
				&& !request.getParameter("iCveEquipo").equals("")) {

			int iAnio = Integer.parseInt(request.getParameter("iAnio"));
			int iMeses = Integer.parseInt(request.getParameter("iMeses"));
			int iCveArea = Integer.parseInt(request.getParameter("iCveArea"));
			int iCveEquipo = Integer.parseInt(request
					.getParameter("iCveEquipo"));
			List listTurnos = new ArrayList();

			try {
				listTurnos = dToxTemperRefr.findTurnos(iCveArea);
			} catch (DAOException ex) {
				ex.printStackTrace();
			}

			/*
			 * Si se oprime el link Reporte ****************************
			 */
			if (request.getParameter("hdReporte") != null) {
				/*
				 * REFRIGERADORES Y CONGELADORES *****************************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {
					try {
						String excelFile = "Temperaturas";

						response.setContentType("application/vnd.ms-excel");
						// Lo sig. hace que el archivo se abra fuera de la JSP
						// response.setHeader("Content-Disposition",
						// "attachment;filename=workbook.xls");
						// Lo sig. hace que el archivo se abra dentro de la JSP
						response.setHeader("Content-Disposition",
								"inline;filename=" + excelFile + "-out.xls");

						// LEER LA PLANTILLA DE EXCEL
						// ##########################

						// URL url = new
						// URL("http://localhost:8080/FileUploadJSP/" +
						// excelFile + ".xls");
						// System.out.println("URL = " +
						// vParametros.getPropEspecifica("ExcelRutaOrig") +
						// excelFile + ".xls");
						URL url = new URL(
								vParametros.getPropEspecifica("ExcelRutaOrig")
										+ excelFile + ".xls");
						URLConnection conn = url.openConnection();
						InputStream isPlantillaExcel = conn.getInputStream();

						/*
						 * ServletContext ctx = getServletContext(); URL url =
						 * ctx.getResource("/WEB-INF/plantillas/" + excelFile +
						 * ".xls"); URLConnection conn = url.openConnection();
						 * InputStream isPlantillaExcel = conn.getInputStream();
						 */

						/*
						 * InputStream isPlantillaExcel =
						 * ctx.getResourceAsStream("/" + excelFile + ".xls");
						 */
						// #####################################

						JXLSBean jxlsbean = dToxTemperRefr.generaReporte(iAnio,
								iMeses, iCveArea, iCveEquipo, listTurnos);
						Map beans = new HashMap();
						beans.put("jxlsbean", jxlsbean);
						XLSTransformer transformer = new XLSTransformer();
						HSSFWorkbook workbook = transformer.transformXLS(
								isPlantillaExcel, beans);

						HSSFSheet sheet0 = workbook.getSheetAt(0);

						// Estilo para Marca, Modelo y Serie
						HSSFFont font = workbook.createFont();
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						HSSFCellStyle style = workbook.createCellStyle();
						style.setFont(font);
						style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

						// MES, AÑO, AREA
						HSSFRow row9 = sheet0.getRow(8);
						// MARCA
						HSSFRow row24 = sheet0.getRow(23);
						HSSFRow row25 = sheet0.getRow(24);
						// MODELO
						HSSFRow row26 = sheet0.getRow(25);
						HSSFRow row27 = sheet0.getRow(26);
						// SERIE
						HSSFRow row28 = sheet0.getRow(27);
						HSSFRow row29 = sheet0.getRow(28);

						HSSFCell cellMes = row9.getCell((short) 10);
						HSSFCell cellAnio = row9.getCell((short) 16);
						HSSFCell cellArea = row9.getCell((short) 24);

						HSSFCell cellEtiquetaMarca = row24.getCell((short) 22);
						HSSFCell cellMarca = row25.getCell((short) 22);

						HSSFCell cellEtiquetaModelo = row26.getCell((short) 22);
						HSSFCell cellModelo = row27.getCell((short) 22);

						HSSFCell cellEtiquetaSerie = row28.getCell((short) 22);
						HSSFCell cellSerie = row29.getCell((short) 22);

						if (cellMes == null) {
							cellMes = row9.createCell((short) 10);
						}

						if (cellAnio == null) {
							cellAnio = row9.createCell((short) 16);
						}

						if (cellArea == null) {
							cellArea = row9.createCell((short) 24);
						}

						if (cellEtiquetaMarca == null) {
							cellEtiquetaMarca = row24.createCell((short) 22);
						}

						if (cellMarca == null) {
							cellMarca = row25.createCell((short) 22);
						}

						if (cellEtiquetaModelo == null) {
							cellEtiquetaModelo = row26.createCell((short) 22);
						}

						if (cellModelo == null) {
							cellModelo = row27.createCell((short) 22);
						}

						if (cellEtiquetaSerie == null) {
							cellEtiquetaSerie = row28.createCell((short) 22);
						}

						if (cellSerie == null) {
							cellSerie = row29.createCell((short) 22);
						}

						cellEtiquetaMarca.setCellStyle(style);
						cellEtiquetaModelo.setCellStyle(style);
						cellEtiquetaSerie.setCellStyle(style);

						// Despues de dToxTemperRefr.generaReporte()
						// puedo obtener datos de este bean
						pg070307020Bean bean2 = dToxTemperRefr.getBean2();

						TDEQMMarca dEQMMarca = new TDEQMMarca();
						Vector vcMarca = dEQMMarca.FindByAll(
								" where iCveMarca = " + bean2.getIcvemarca(),
								"");

						if (vcMarca.size() > 0) {
							for (int i = 0; i < vcMarca.size(); i++) {
								TVEQMMarca vEQMMarca = (TVEQMMarca) vcMarca
										.get(i);
								bean2.setCdscmarca(vEQMMarca.getCDscMarca());
							}
						} else {
							bean2.setCdscmarca("");
						}

						cellMes.setCellValue(String.valueOf(bean2.getImes()));
						cellAnio.setCellValue(String.valueOf(bean2.getIanio()));
						cellArea.setCellValue(bean2.getCdscArea());
						cellEtiquetaMarca.setCellValue("Marca:");
						cellMarca.setCellValue(bean2.getCdscmarca());
						cellEtiquetaModelo.setCellValue("Modelo:");
						cellModelo.setCellValue(bean2.getCmodelo());
						cellEtiquetaSerie.setCellValue("No. de Serie:");
						cellSerie.setCellValue(bean2.getCnumserie());

						int read = 0;
						byte[] bytes = new byte[2048];

						OutputStream os = response.getOutputStream();

						InputStream is = new ByteArrayInputStream(
								workbook.getBytes());

						while ((read = is.read(bytes)) != -1) {
							workbook.write(os);
						}

						os.flush();
						os.close();
					} catch (DAOException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					/*
					 * TEMPERATURA AMBIENTE ********************
					 */
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("ReporteTemperaturaAmbiente") == 0) {
					try {
						String excelFile = "TemperaturaAmbiente";

						response.setContentType("application/vnd.ms-excel");
						// Lo sig. hace que el archivo se abra fuera de la JSP
						// response.setHeader("Content-Disposition",
						// "attachment;filename=workbook.xls");
						// Lo sig. hace que el archivo se abra dentro de la JSP
						response.setHeader("Content-Disposition",
								"inline;filename=" + excelFile + "-out.xls");

						// LEER LA PLANTILLA DE EXCEL
						// ##########################

						// URL url = new
						// URL("http://localhost:8080/FileUploadJSP/" +
						// excelFile + ".xls");
						// System.out.println("URL = " +
						// vParametros.getPropEspecifica("ExcelRutaOrig") +
						// excelFile + ".xls");
						URL url = new URL(
								vParametros.getPropEspecifica("ExcelRutaOrig")
										+ excelFile + ".xls");
						URLConnection conn = url.openConnection();
						InputStream isPlantillaExcel = conn.getInputStream();

						/*
						 * ServletContext ctx = getServletContext(); URL url =
						 * ctx.getResource("/WEB-INF/plantillas/" + excelFile +
						 * ".xls"); URLConnection conn = url.openConnection();
						 * InputStream isPlantillaExcel = conn.getInputStream();
						 */

						/*
						 * InputStream isPlantillaExcel =
						 * ctx.getResourceAsStream("/" + excelFile + ".xls");
						 */
						// #####################################

						JXLSBean jxlsbean = dToxTurnoRef
								.generaReporteTempAmbiente(iAnio, iMeses,
										iCveArea, listTurnos);
						Map beans = new HashMap();
						beans.put("jxlsbean", jxlsbean);
						XLSTransformer transformer = new XLSTransformer();
						HSSFWorkbook workbook = transformer.transformXLS(
								isPlantillaExcel, beans);

						HSSFSheet sheet0 = workbook.getSheetAt(0);

						// MES, AÑO, AREA
						HSSFRow row9 = sheet0.getRow(8);

						HSSFCell cellMes = row9.getCell((short) 10);
						HSSFCell cellAnio = row9.getCell((short) 16);
						HSSFCell cellArea = row9.getCell((short) 24);

						if (cellMes == null) {
							cellMes = row9.createCell((short) 10);
						}

						if (cellAnio == null) {
							cellAnio = row9.createCell((short) 16);
						}

						if (cellArea == null) {
							cellArea = row9.createCell((short) 24);
						}

						// Despues de dToxTemperRefr.generaReporte()
						// puedo obtener datos de este bean
						pg070307020Bean bean2 = dToxTurnoRef.getBean2();

						cellMes.setCellValue(String.valueOf(bean2.getImes()));
						cellAnio.setCellValue(String.valueOf(bean2.getIanio()));
						cellArea.setCellValue(bean2.getCdscArea());

						int read = 0;
						byte[] bytes = new byte[2048];

						OutputStream os = response.getOutputStream();

						InputStream is = new ByteArrayInputStream(
								workbook.getBytes());

						while ((read = is.read(bytes)) != -1) {
							workbook.write(os);
						}

						os.flush();
						os.close();
					} catch (DAOException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					/*
					 * HUMEDAD *******
					 */
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("ReporteHumedad") == 0) {
					try {
						String excelFile = "Humedad";

						response.setContentType("application/vnd.ms-excel");
						// Lo sig. hace que el archivo se abra fuera de la JSP
						// response.setHeader("Content-Disposition",
						// "attachment;filename=workbook.xls");
						// Lo sig. hace que el archivo se abra dentro de la JSP
						response.setHeader("Content-Disposition",
								"inline;filename=" + excelFile + "-out.xls");

						// LEER LA PLANTILLA DE EXCEL
						// ##########################

						// URL url = new
						// URL("http://localhost:8080/FileUploadJSP/" +
						// excelFile + ".xls");
						// System.out.println("URL = " +
						// vParametros.getPropEspecifica("ExcelRutaOrig") +
						// excelFile + ".xls");
						URL url = new URL(
								vParametros.getPropEspecifica("ExcelRutaOrig")
										+ excelFile + ".xls");
						URLConnection conn = url.openConnection();
						InputStream isPlantillaExcel = conn.getInputStream();

						/*
						 * ServletContext ctx = getServletContext(); URL url =
						 * ctx.getResource("/WEB-INF/plantillas/" + excelFile +
						 * ".xls"); URLConnection conn = url.openConnection();
						 * InputStream isPlantillaExcel = conn.getInputStream();
						 */

						/*
						 * InputStream isPlantillaExcel =
						 * ctx.getResourceAsStream("/" + excelFile + ".xls");
						 */
						// #####################################

						JXLSBean jxlsbean = dToxTurnoRef.generaReporteHumedad(
								iAnio, iMeses, iCveArea, listTurnos);
						Map beans = new HashMap();
						beans.put("jxlsbean", jxlsbean);
						XLSTransformer transformer = new XLSTransformer();
						HSSFWorkbook workbook = transformer.transformXLS(
								isPlantillaExcel, beans);

						HSSFSheet sheet0 = workbook.getSheetAt(0);

						// MES, AÑO, AREA
						HSSFRow row9 = sheet0.getRow(8);

						HSSFCell cellMes = row9.getCell((short) 10);
						HSSFCell cellAnio = row9.getCell((short) 16);
						HSSFCell cellArea = row9.getCell((short) 24);

						if (cellMes == null) {
							cellMes = row9.createCell((short) 10);
						}

						if (cellAnio == null) {
							cellAnio = row9.createCell((short) 16);
						}

						if (cellArea == null) {
							cellArea = row9.createCell((short) 24);
						}

						// Despues de dToxTemperRefr.generaReporte()
						// puedo obtener datos de este bean
						pg070307020Bean bean2 = dToxTurnoRef.getBean2();

						cellMes.setCellValue(String.valueOf(bean2.getImes()));
						cellAnio.setCellValue(String.valueOf(bean2.getIanio()));
						cellArea.setCellValue(bean2.getCdscArea());

						int read = 0;
						byte[] bytes = new byte[2048];

						OutputStream os = response.getOutputStream();

						InputStream is = new ByteArrayInputStream(
								workbook.getBytes());

						while ((read = is.read(bytes)) != -1) {
							workbook.write(os);
						}

						os.flush();
						os.close();
					} catch (DAOException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
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
