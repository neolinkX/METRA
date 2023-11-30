package gob.sct.medprev;

import java.sql.*;
import java.util.*;

import org.apache.commons.beanutils.*;
import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;

/**
 * 
 * <p>
 * Title: Registro y Comportamiento de Estandares y Deuteriados para An�lisis
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
public class pg0703060120CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg0703060120CFG() {
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
							&& request.getParameter("dtFechaF") != null
							&& request.getParameter("SLSSustancia") != null) {

						/**
						 * Abrir el archivo generado de Excel
						 */
						if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
							TExcel Rep = new TExcel("07");

							// Primero bajo la plantilla del servidor al disco
							// local
							// Este Metodo por default ya incluye la ruta y la
							// extensi�n (.xls)
							this.activeX.append(Rep.creaActiveX("pg0703060120",
									"pg0703060120", false, false, false));

							// and R.cDscBreve like '%" +
							// request.getParameter("SLSSustancia").trim().substring(0,
							// 4) + "%'"
							// lo hice para obtener COCA o COCAINA, ANFE o
							// ANFETAMINA, CANN o CANNABIS, etc.
							String cFiltro = " where R.iCveTpoReact = T.iCveTpoReact "
									+ " and R.iCveMarcaSust = M.iCveMarcaSust "
									+ " and R.iCveLaboratorio = "
									+ request.getParameter("iCveLaboratorio")
									+ " and R.dtPreparacion between '"
									+ tFechas.getDateSQL(request.getParameter(
											"dtFechaI").toString())
									+ "'"
									+ "                     and '"
									+ tFechas.getDateSQL(request
											.getParameter("dtFechaF"))
									+ "'"
									+
									// " and R.cDscBreve like '%" +
									// request.getParameter("SLSSustancia").trim().substring(0,
									// 4) + "%'"; //Se modifico esta parte ya
									// que la descripcion no existina en este
									// campo si no en CDSCBREVE
									" and R.cDscReactivo like '%"
									+ request.getParameter("SLSSustancia")
											.trim().substring(0, 4) + "%'";
							String cOrden = " order by R.iAnio, R.iCveLaboratorio, R.dtPreparacion, R.iCodigo";

							/*
							 * Si la casilla de verificacion viene null => no
							 * seleccionada
							 */
							if (request.getParameter("checkEstandar") != null
									&& request.getParameter("checkDeuteriado") == null) {
								cFiltro = cFiltro + " and R.iCveTpoReact = 1 ";
							} else if (request.getParameter("checkEstandar") == null
									&& request.getParameter("checkDeuteriado") != null) {
								cFiltro = cFiltro + " and R.iCveTpoReact = 2 ";
							} else if (request.getParameter("checkEstandar") != null
									&& request.getParameter("checkDeuteriado") != null) {
								cFiltro = cFiltro
										+ " and R.iCveTpoReact IN (1,2) ";
							}

							ResultSet rs = dTOXReactivo.getResultSet(cFiltro,
									cOrden);

							RowSetDynaClass rsdc = new RowSetDynaClass(rs, true);
							Map beans = new HashMap();
							beans.put("toxreactivo", rsdc.getRows());
							XLSTransformer transformer = new XLSTransformer();
							// transformer.transformXLS(vParametros.getPropEspecifica("ExcelRutaDest")
							// + "pg0703060120.xls", beans,
							// vParametros.getPropEspecifica("ExcelRutaDest") +
							// "pg0703060120-out.xls");
							transformer.transformXLS(
									"C:/sct/medprev/pg0703060120.xls", beans,
									"C:/sct/medprev/pg0703060120-out.xls");
							// System.out.println("Parte del activeX");
							// System.out.println(activeX);
							// System.out.println("Parte del beans");
							// System.out.println(beans);
							// System.out.println("Parte de la ruta");
							// System.out.println(vParametros.getPropEspecifica("ExcelRutaDest"));

							// Ahora abro el archivo de Excel generado a traves
							// de la plantilla
							// Este Metodo por default ya incluye la ruta y la
							// extensi�n (.xls)
							this.activeX.append(Rep.creaActiveX(
									"pg0703060120-out", false, false, true));

							rs.close();
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
