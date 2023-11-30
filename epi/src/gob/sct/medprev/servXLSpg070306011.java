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
public class servXLSpg070306011 extends HttpServlet {

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
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		Vector vDespliega = new Vector();

		String lab = request.getParameter("hdCampoClave1"); // iCveLaboratorio
		String react = request.getParameter("hdCampoClave2"); // iCveReactivo
		String where = "";

		if ((lab == null || lab.equals(""))
				&& (react == null || react.equals(""))) {
			where += "";
		}

		if ((lab != null && !lab.equals(""))
				&& (react == null || react.equals(""))) {
			where += " AND iCveLaboratorio = " + lab;
		}

		where += " AND iAnio = " + request.getParameter("iAnio")
				+ " AND iCveLaboratorio = "
				+ request.getParameter("iCveUniMed");

		if (request.getParameter("iAnioSelect") != null
				&& request.getParameter("iCveUniMed") != null) {

			where += " AND iAnio = " + request.getParameter("iAnioSelect")
					+ " AND iCveLaboratorio = "
					+ request.getParameter("iCveUniMed");
		}

		try {
			vDespliega = dTOXReactivo.findByWhere(where, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Modificado Rafael Alcocer Caldera 22/Sep/2006
		// #####################################
		// ## AQUI METER EL CODIGO PARA EXCEL ##
		// #####################################
		try {
			if (request.getParameter("hdReporte") != null) {
				/**
				 * INMUNOENSAYO **********************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Inmunoensayo") == 0) {
					where += " and R.iCveReactivo = "
							+ Integer.parseInt(request
									.getParameter("iCveReactivo"));

					String excelFile = "pg070306011AP";

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
					// System.out.println(vParametros.getPropEspecifica("ExcelRutaOrig")
					// + excelFile + ".xls");
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

					vDespliega = dTOXReactivo.findByWhere(where, "");
					JXLSBean jxlsBean = dTOXReactivo.getJXLSBean();

					Map beans = new HashMap();
					beans.put("jxlsBean", jxlsBean);
					XLSTransformer transformer = new XLSTransformer();
					HSSFWorkbook workbook = transformer.transformXLS(
							isPlantillaExcel, beans);

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

		try {
			// Modificado Rafael Alcocer Caldera 22/Sep/2006
			// *********************************************
			if (request.getParameter("hdReporte") != null) {
				/**
				 * CROMATOGRAFIA **********************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Cromatografia") == 0) {
					where += " and R.iCveReactivo = "
							+ Integer.parseInt(request
									.getParameter("iCveReactivo"));

					String excelFile = "pg070306011AC";

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

					vDespliega = dTOXReactivo.findByWhere(where, "");
					JXLSBean jxlsBean = dTOXReactivo.getJXLSBean();

					Map beans = new HashMap();
					beans.put("jxlsBean", jxlsBean);
					XLSTransformer transformer = new XLSTransformer();
					HSSFWorkbook workbook = transformer.transformXLS(
							isPlantillaExcel, beans);

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
		// System.out.println(request);
		// System.out.println(response);
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
