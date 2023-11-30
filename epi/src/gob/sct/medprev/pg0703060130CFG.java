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
 * Title: Registro y Comportamiento de Estandares y Controles para An�lisis
 * Presuntivo
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
public class pg0703060130CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg0703060130CFG() {
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
							this.activeX.append(Rep.creaActiveX("pg0703060130",
									"pg0703060130", false, false, false));

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
											.getParameter("dtFechaF")) + "'";
							String cOrden = " order by R.iAnio, R.iCveLaboratorio, R.dtPreparacion, R.iCodigo";

							/*
							 * Si la casilla de verificaci�n viene null => no
							 * seleccionada
							 */
							if (request.getParameter("checkEstandar") != null
									&& request.getParameter("checkControl") == null) {
								cFiltro = cFiltro + " and R.iCveTpoReact = 9 ";
							} else if (request.getParameter("checkEstandar") == null
									&& request.getParameter("checkControl") != null) {
								cFiltro = cFiltro + " and R.iCveTpoReact = 6 ";
							} else if (request.getParameter("checkEstandar") != null
									&& request.getParameter("checkControl") != null) {
								cFiltro = cFiltro
										+ " and R.iCveTpoReact IN (6,9) ";
							}

							ResultSet rs = dTOXReactivo.getResultSet(cFiltro,
									cOrden);

							RowSetDynaClass rsdc = new RowSetDynaClass(rs, true);
							Map beans = new HashMap();
							beans.put("toxreactivo", rsdc.getRows());
							XLSTransformer transformer = new XLSTransformer();
							transformer.transformXLS(
									vParametros
											.getPropEspecifica("ExcelRutaDest")
											+ "pg0703060130.xls",
									beans,
									vParametros
											.getPropEspecifica("ExcelRutaDest")
											+ "pg0703060130-out.xls");

							// Ahora abro el archivo de Excel generado a traves
							// de la plantilla
							// Este Metodo por default ya incluye la ruta y la
							// extensi�n (.xls)
							this.activeX.append(Rep.creaActiveX(
									"pg0703060130-out", false, false, true));
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
