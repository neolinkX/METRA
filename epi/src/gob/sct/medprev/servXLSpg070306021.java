package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.jxls.transformer.*;
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
public class servXLSpg070306021 extends HttpServlet {

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
		TDTOXCtrolCalibra dCtrolCalibra = new TDTOXCtrolCalibra();

		// Agregado por Rafael Alcocer Caldera 25/Ago/2006
		// #####################################
		// ## AQUI METER EL CODIGO PARA EXCEL ##
		// #####################################
		try {
			if (request.getParameter("hdReporte") != null) {
				/**
				 * ANALISIS CONFIRMATORIO **********************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Analisis Confirmatorio") == 0) {

					int iCveLaboratorio = 0;
					String cLote = "";
					int iCveReactivo = 0;
					int iLCuantCual = 1;

					if (request.getParameter("iCveLaboratorio") != null) {
						iCveLaboratorio = Integer.parseInt(request
								.getParameter("iCveLaboratorio"));
						// System.out.println("### iCveLaboratorio: " +
						// iCveLaboratorio);
					}

					if (request.getParameter("hdCLote") != null) {
						cLote = request.getParameter("hdCLote");
						// System.out.println("### cLote: " + cLote);
					}

					if (request.getParameter("hdICveReactivo") != null) {
						iCveReactivo = Integer.parseInt(request
								.getParameter("hdICveReactivo"));
						// System.out.println("### iCveReactivo: " +
						// iCveReactivo);
					}

					String excelFile = "pg070306021AC";

					response.setContentType("application/vnd.ms-excel");
					// Lo sig. hace que el archivo se abra fuera de la JSP
					// response.setHeader("Content-Disposition",
					// "attachment;filename=workbook.xls");
					// Lo sig. hace que el archivo se abra dentro de la JSP
					response.setHeader("Content-Disposition",
							"inline;filename=" + excelFile + "-out.xls");

					// LEER LA PLANTILLA DE EXCEL
					// ##########################

					// URL url = new URL("http://localhost:8080/FileUploadJSP/"
					// + excelFile + ".xls");
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

					// System.out.println("### SUBSTRING cLote: " +
					// cLote.substring(0, 6) + "\'");

					// lCuantCual = 1 => Traer s�lo lo relacionado a
					// An�lisis Confirmatorio
					String cWhere = " where C.iCveLaboratorio = "
							+ iCveLaboratorio + " and C.iCveReactivo = "
							+ iCveReactivo + " and C.lCuantCual = "
							+ iLCuantCual + " and substr(C.cLote,1,6) = '"
							+ cLote.substring(0, 6) + "'";

					String cOrden2 = " order by C.cLote ";

					dCtrolCalibra.FindByAll(cWhere, cOrden2);

					List jxlsBeanList = dCtrolCalibra.getJXLSBeanList();
					Map beans = new HashMap();
					beans.put("jxlsBeanList", jxlsBeanList);

					XLSTransformer transformer = new XLSTransformer();
					HSSFWorkbook workbook = transformer.transformXLS(
							isPlantillaExcel, beans);

					HSSFSheet sheet = workbook.getSheetAt(0);

					// Renglon 10
					HSSFRow row10 = sheet.getRow(9);
					HSSFCell cellE10 = row10.getCell((short) 4);
					HSSFCell cellF10 = row10.getCell((short) 5);
					// Renglon 11
					HSSFRow row11 = sheet.getRow(10);
					HSSFCell cellE11 = row11.getCell((short) 4);
					HSSFCell cellF11 = row11.getCell((short) 5);
					// Renglon 12
					HSSFRow row12 = sheet.getRow(11);
					HSSFCell cellE12 = row12.getCell((short) 4);
					HSSFCell cellF12 = row12.getCell((short) 5);
					// Renglon 13
					HSSFRow row13 = sheet.getRow(12);
					HSSFCell cellE13 = row13.getCell((short) 4);
					HSSFCell cellF13 = row13.getCell((short) 5);

					// Renglon 26
					HSSFRow row26 = sheet.getRow(25);
					HSSFCell cellD26 = row26.getCell((short) 3);
					// Renglon 27
					HSSFRow row27 = sheet.getRow(26);
					HSSFCell cellD27 = row27.getCell((short) 3);
					// Renglon 28
					HSSFRow row28 = sheet.getRow(27);
					HSSFCell cellD28 = row28.getCell((short) 3);
					// Renglon 29
					HSSFRow row29 = sheet.getRow(28);
					HSSFCell cellD29 = row29.getCell((short) 3);

					// Renglon 42
					HSSFRow row42 = sheet.getRow(41);
					HSSFCell cellD42 = row42.getCell((short) 3);
					// Renglon 43
					HSSFRow row43 = sheet.getRow(42);
					HSSFCell cellD43 = row43.getCell((short) 3);
					// Renglon 44
					HSSFRow row44 = sheet.getRow(43);
					HSSFCell cellD44 = row44.getCell((short) 3);
					// Renglon 45
					HSSFRow row45 = sheet.getRow(44);
					HSSFCell cellD45 = row45.getCell((short) 3);

					// CORTE
					// Clave del control
					cellE10.setCellValue(cellD26.getStringCellValue());
					// Volumen
					cellE11.setCellValue(cellD27.getStringCellValue());
					// Concentracion Teorica
					cellE12.setCellValue(cellD28.getStringCellValue());
					// Concentracion Experimental
					cellE13.setCellValue(cellD29.getStringCellValue());

					// POSITIVO
					// Clave del control
					cellF10.setCellValue(cellD42.getStringCellValue());
					// Volumen
					cellF11.setCellValue(cellD43.getStringCellValue());
					// Concentracion Teorica
					cellF12.setCellValue(cellD44.getStringCellValue());
					// Concentracion Experimental
					cellF13.setCellValue(cellD45.getStringCellValue());

					for (int i = 23; i < sheet.getLastRowNum(); i++) {
						sheet.removeRow(sheet.getRow(i));
					}

					// Unir celdas (merge) para las Observaciones
					sheet.addMergedRegion(new Region(14, (short) 2, 19,
							(short) 5));

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
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/**
		 * ANALISIS PRESUNTIVO *******************
		 */
		try {
			if (request.getParameter("hdReporte").compareToIgnoreCase(
					"Analisis Presuntivo") == 0) {

				String excelFile = "pg070306021AP";

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

				// toma la platilla local
				// InputStream isPlantillaExcel = new BufferedInputStream(new
				// FileInputStream("C:/sct/medprev/pg070306021AP.xls"));

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

				TVTOXCtrolCalibra vTOXCtrolCalibra = (TVTOXCtrolCalibra) getInputs(
						request, response);

				// lCual = 1 => Traer s�lo lo relacionado a An�lisis
				// Confirmatorio
				String cWhere = " where C.iCveLaboratorio = "
						+ vTOXCtrolCalibra.getICveLaboratorio()
						+ " and C.iCveCtrolCalibra = "
						+ vTOXCtrolCalibra.getICveCtrolCalibra();

				String cOrden3 = "";

				dCtrolCalibra.FindByAll(cWhere, cOrden3);

				List jxlsBeanList = dCtrolCalibra.getJXLSBeanList();
				Map beans = new HashMap();
				beans.put("jxlsBeanList", jxlsBeanList);

				XLSTransformer transformer = new XLSTransformer();
				HSSFWorkbook workbook = transformer.transformXLS(
						isPlantillaExcel, beans);

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

	/**
	 * Metodo getInputs
	 */
	public Object getInputs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVTOXCtrolCalibra vCtrolCalibra = new TVTOXCtrolCalibra();

		try {
			cCampo = "" + request.getParameter("iCveLaboratorio"); // iCveLaboratorio
																	// , aqui
																	// tenia
																	// iCveLaboratorio
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveLaboratorio(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave3"); // hdCampoClave3
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vCtrolCalibra.setICveCtrolCalibra(iCampo);

			cCampo = "" + request.getParameter("iCveReactivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveReactivo(iCampo);

			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setIAnio(iCampo);

			cCampo = "" + request.getParameter("cLote");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCLote(cCampo);

			cCampo = "" + request.getParameter("cDscCtrolCalibra");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCDscCtrolCalibra(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("iCveSustancia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveSustancia(iCampo);

			cCampo = "" + request.getParameter("dVolumen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDVolumen(fCampo);

			cCampo = "" + request.getParameter("dConcentracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDConcentracion(fCampo);

			cCampo = "" + request.getParameter("iCveEmpleoCalib");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveEmpleoCalib(iCampo);
			if (request.getParameter("iCuantCual") != null) {
				cCampo = "" + request.getParameter("iCuantCual");
			} else {
				cCampo = "null";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);

			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLCuantCual(iCampo);

			if (request.getParameter("iCual") != null) {
				cCampo = "" + request.getParameter("iCual");
			} else {
				cCampo = "null";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);

			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLCual(iCampo);

			cCampo = "" + request.getParameter("iViales");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setIViales(iCampo);

			cCampo = "" + request.getParameter("dtPreparacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtPreparacion(dtCampo);

			cCampo = "" + request.getParameter("dtCaducidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtCaducidad(dtCampo);

			cCampo = "" + request.getParameter("dtAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtAutoriza(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveUsuAutoriza(iCampo);

			cCampo = "" + request.getParameter("lAgotado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLAgotado(iCampo);

			cCampo = "" + request.getParameter("dtAgotado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtAgotado(dtCampo);

			cCampo = "" + request.getParameter("lBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLBaja(iCampo);

			cCampo = "" + request.getParameter("dtBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtBaja(dtCampo);

			cCampo = "" + request.getParameter("iAnioBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setIAnioBaja(iCampo);

			cCampo = "" + request.getParameter("iCveBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveBaja(iCampo);

			cCampo = "" + request.getParameter("iCveMarcaSust");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveMarcaSust(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveUsuPrepara");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveUsuPrepara(iCampo);

			/*
			 * cCampo = "" + request.getParameter("lCual"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vCtrolCalibra.setLCual(iCampo);
			 */

			cCampo = "" + request.getParameter("dConcentracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDConcentracion(fCampo);

			cCampo = "" + request.getParameter("iCveEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("dtValoracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtBaja(dtCampo);

			cCampo = "" + request.getParameter("dVolUtilizado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDVolUtilizado(fCampo);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vCtrolCalibra;
	}

}
