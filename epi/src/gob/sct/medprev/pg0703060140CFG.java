package gob.sct.medprev;

import java.sql.*;
import java.util.*;

import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * 
 * <p>
 * Title: Registro y Comportamiento del Reactivo y Soluciones para An�lisis
 * Confirmatorio
 * </p>
 * <p>
 * Description: Obtiene el Reporte
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 */
public class pg0703060140CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg0703060140CFG() {
		vParametros = new TParametro("07");
	}

	/**
	 * Metodo fillVector
	 */
	public void fillVector() {
		TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
		TFechas tFechas = new TFechas();

		try {
			// ## AQUI METER EL CODIGO PARA EXCEL ##
			if (request.getParameter("hdReporte") != null) {
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {

					if (request.getParameter("iCveLaboratorio") != null
							&& request.getParameter("dtFechaI") != null
							&& request.getParameter("dtFechaF") != null) {

						/**
						 * Abrir el archivo generado de Excel
						 */
						if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
							TExcel Rep = new TExcel("07");

							// Primero bajo la plantilla del servidor al disco
							// local
							// Este Metodo por default ya incluye la ruta y la
							// extensi�n (.xls)
							this.activeX.append(Rep.creaActiveX("pg0703060140",
									"pg0703060140", false, false, false));

							String cFiltro = " where R.iCveTpoReact = T.iCveTpoReact "
									+ " and R.iCveMarcaSust = M.iCveMarcaSust "
									+ " and R.lCuantCual = 1 "
									+ " and R.iCveLaboratorio = "
									+ request.getParameter("iCveLaboratorio")
									+ " and R.dtPreparacion between '"
									+ tFechas.getDateSQL(request.getParameter(
											"dtFechaI").toString())
									+ "'"
									+ "                     and '"
									+ tFechas.getDateSQL(request
											.getParameter("dtFechaF")) + "'";
							String cOrden = " order by R.iAnio, R.iCveLaboratorio, R.iCveTpoReact, R.dtPreparacion ";

							/*
							 * Si la casilla de verificaci�n viene null => no
							 * seleccionada 3 => SOLUCIONES 4 => DERIVATIZANTE
							 * (REACTIVO) 8 => REACTIVO CROMATOGRAFIA (REACTIVO)
							 */
							if (request.getParameter("checkSolucion") != null
									&& request.getParameter("checkReactivo") == null) {
								cFiltro = cFiltro + " and R.iCveTpoReact = 3 ";
							} else if (request.getParameter("checkSolucion") == null
									&& request.getParameter("checkReactivo") != null) {
								cFiltro = cFiltro
										+ " and R.iCveTpoReact IN (4,8) ";
							} else if (request.getParameter("checkSolucion") != null
									&& request.getParameter("checkReactivo") != null) {
								cFiltro = cFiltro
										+ " and R.iCveTpoReact IN (3,4,8) ";
							}

							ResultSet rs = dTOXReactivo.getResultSet(cFiltro,
									cOrden);

							List list = new ArrayList();

							while (rs.next()) {
								TVTOXReactivo vTOXReactivo = new TVTOXReactivo();
								vTOXReactivo.setDtPreparacion(rs
										.getDate("dtPreparacion"));
								vTOXReactivo
										.setICodigo(rs.getString("iCodigo"));
								vTOXReactivo.setDVolumen(rs
										.getFloat("dVolumen"));
								vTOXReactivo.setIViales(rs.getInt("iViales"));
								vTOXReactivo.setDtCaducidad(rs
										.getDate("dtCaducidad"));
								vTOXReactivo.setCNomCompletoPrepara(rs
										.getString("cNomCompletoPrepara"));
								vTOXReactivo.setCNomCompletoAutoriza(rs
										.getString("cNomCompletoAutoriza"));
								vTOXReactivo.setDtAgotado(rs
										.getDate("dtAgotado"));
								vTOXReactivo.setCObservacion(rs
										.getString("cObservacion"));
								vTOXReactivo.setCDscBreve(rs
										.getString("cDscBreve"));

								list.add(vTOXReactivo);
							}

							Map beans = new HashMap();
							beans.put("list", list);
							XLSTransformer transformer = new XLSTransformer();
							transformer.transformXLS(
									vParametros
											.getPropEspecifica("ExcelRutaDest")
											+ "pg0703060140.xls",
									beans,
									vParametros
											.getPropEspecifica("ExcelRutaDest")
											+ "pg0703060140-out.xls");

							/*
							 * RowSetDynaClass rsdc = new RowSetDynaClass(rs,
							 * true); Map beans = new HashMap();
							 * beans.put("toxreactivo", rsdc.getRows());
							 * XLSTransformer transformer = new
							 * XLSTransformer();
							 * transformer.transformXLS(vParametros
							 * .getPropEspecifica("ExcelRutaDest") +
							 * "pg0703060140.xls", beans,
							 * vParametros.getPropEspecifica("ExcelRutaDest") +
							 * "pg0703060140-out.xls");
							 */

							// Ahora abro el archivo de Excel generado a traves
							// de la plantilla
							// Este Metodo por default ya incluye la ruta y la
							// extensi�n (.xls)
							this.activeX.append(Rep.creaActiveX(
									"pg0703060140-out", false, false, true));

						}
					}
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}
}
