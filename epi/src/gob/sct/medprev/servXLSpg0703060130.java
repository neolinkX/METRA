package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.beanutils.*;
import org.apache.poi.hssf.usermodel.*;
import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;

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
public class servXLSpg0703060130 extends HttpServlet {

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
		TFechas tFechas = new TFechas();

		try {
			// #####################################
			// ## AQUI METER EL CODIGO PARA EXCEL ##
			// #####################################
			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("dtFechaI") != null
					&& request.getParameter("dtFechaF") != null) {

				String cFiltro = " where R.iCveTpoReact = T.iCveTpoReact "
						+ " and R.iCveMarcaSust = M.iCveMarcaSust "
						+ " and R.iCveLaboratorio = "
						+ request.getParameter("iCveLaboratorio")
						+ " and R.dtPreparacion between '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaI")
								.toString()) + "'"
						+ "                     and '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaF"))
						+ "'";
				String cOrden = " order by R.cDscReactivo, R.iAnio, R.iCveLaboratorio, R.dtPreparacion, R.iCodigo";

				/*
				 * Si la casilla de verificación viene null => no seleccionada
				 */
				if (request.getParameter("checkEstandar").equals("1")
						&& request.getParameter("checkControl").equals("0")) {
					// cFiltro = cFiltro +
					// " and R.iCveTpoReact = 9 and R.cDscBreve ='BIOCHIPS'";
					cFiltro = cFiltro + " and R.cDscBreve ='BIOCHIPS'";
				} else if (request.getParameter("checkEstandar").equals("0")
						&& request.getParameter("checkControl").equals("1")) {
					// cFiltro = cFiltro + " and R.iCveTpoReact = 6 ";
					// cFiltro = cFiltro +
					// " and iCveTpoReact IN (6,9) and cDscBreve IN('CALIBRADOR','CONTROL')";
					cFiltro = cFiltro
							+ " and cDscBreve IN('CALIBRADOR','CONTROL')";
				} else if (request.getParameter("checkEstandar").equals("1")
						&& request.getParameter("checkControl").equals("1")) {
					// cFiltro = cFiltro + " and R.iCveTpoReact IN (6,9) ";
					// cFiltro = cFiltro +
					// " and iCveTpoReact IN (6,9) and cDscBreve IN('CALIBRADOR','CONTROL','BIOCHIPS')";
					cFiltro = cFiltro
							+ " and cDscBreve IN('CALIBRADOR','CONTROL','BIOCHIPS')";
				}

				// //
				// System.out.println("checkEstandar:  "+request.getParameter("checkEstandar"));
				// //
				// System.out.println("checkControl:   "+request.getParameter("checkControl"));

				String excelFile = "pg0703060130";

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
				// System.out.println("Obteniendo el excel");
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

				ResultSet rs = dTOXReactivo.getResultSet(cFiltro, cOrden);

				RowSetDynaClass rsdc = new RowSetDynaClass(rs, true);
				Map beans = new HashMap();
				beans.put("toxreactivo", rsdc.getRows());// mete una lista al
															// MAP con los
															// resultados de la
															// busqueda
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
}
