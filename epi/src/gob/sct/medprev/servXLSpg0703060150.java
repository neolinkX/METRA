package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import org.apache.commons.beanutils.*;

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
public class servXLSpg0703060150 extends HttpServlet {

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
						+
						// " and R.lCual = 1 " +
						" and R.iCveLaboratorio = "
						+ request.getParameter("iCveLaboratorio")
						+ " and R.dtPreparacion between '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaI")
								.toString()) + "'"
						+ "                     and '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaF"))
						+ "'";

				String cOrden = " order by R.iAnio, R.iCveLaboratorio, R.dtPreparacion, R.iCodigo";

				/*
				 * Si la casilla de verificación viene null => no seleccionada 3
				 * => SOLUCION 7, 11, 12, 13 => REACTIVO
				 */
				if (request.getParameter("checkSolucion").equals("1")
						&& request.getParameter("checkReactivo").equals("0")) {
					cFiltro = cFiltro + " and R.iCveTpoReact = 3 ";
				} else if (request.getParameter("checkSolucion").equals("0")
						&& request.getParameter("checkReactivo").equals("1")) {
					cFiltro = cFiltro + " and R.iCveTpoReact IN (7,11,12,13) ";
				} else if (request.getParameter("checkSolucion").equals("1")
						&& request.getParameter("checkReactivo").equals("1")) {
					cFiltro = cFiltro
							+ " and R.iCveTpoReact IN (3,7,11,12,13) ";
				}

				// System.out.println("Filtro"+cFiltro);

				String excelFile = "pg0703060150";

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

				StringBuffer ruta = new StringBuffer(
						"C:/sct/MedprevInt/archivos/plantillas/pg0703060150.xls");

				URL url = new URL(
						vParametros.getPropEspecifica("ExcelRutaOrig")
								+ excelFile + ".xls");
				// URL url = new
				// URL("file:///C:/sct/MedprevInt/archivos/plantillas/pg0703060150.xls");
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

				// System.out.println("Ir a la consultar");
				ResultSet rs = dTOXReactivo.getResultSet(cFiltro, cOrden);

				// System.out.println("Salir de consulta");
				/*
				 * List list = new ArrayList();
				 * 
				 * while (rs.next()) { TVTOXReactivo vTOXReactivo = new
				 * TVTOXReactivo();
				 * vTOXReactivo.setDtPreparacion(rs.getDate("dtPreparacion"));
				 * vTOXReactivo.setICodigo(rs.getString("iCodigo"));
				 * vTOXReactivo.setDVolumen(rs.getFloat("dVolumen"));
				 * vTOXReactivo.setIViales(rs.getInt("iViales"));
				 * vTOXReactivo.setDtCaducidad(rs.getDate("dtCaducidad"));
				 * vTOXReactivo
				 * .setCNomCompletoPrepara(rs.getString("cNomCompletoPrepara"));
				 * vTOXReactivo
				 * .setCNomCompletoAutoriza(rs.getString("cNomCompletoAutoriza"
				 * )); vTOXReactivo.setDtAgotado(rs.getDate("dtAgotado"));
				 * vTOXReactivo.setCObservacion(rs.getString("cobservacion"));
				 * vTOXReactivo.setCDscBreve(rs.getString("cDscBreve"));
				 * 
				 * list.add(vTOXReactivo); }
				 */
				RowSetDynaClass rsdc = new RowSetDynaClass(rs, true);
				Map beans = new HashMap();
				// beans.put("list", list);
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
