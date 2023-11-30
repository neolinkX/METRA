package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * 
 * <p>
 * Title: Registro y Comportamiento de Controles para Análisis Confirmatorio
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
public class pg0703060100CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg0703060100CFG() {
		vParametros = new TParametro("07");
	}

	/**
	 * Método fillVector
	 */
	public void fillVector() {
		TDTOXEquipo dTOXEquipo = new TDTOXEquipo();

		try {
			String cWhere = "";
			String cOrderBy = "";

			// Solo traigo los equipos que pertenecen a Análisis Confirmatorio
			// (Cromatógrafos)
			cCondicion = " TOXEquipo.lCuantCual = 1 ";

			if (cCondicion.compareTo("") != 0) {
				cWhere += " where " + cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else {
				cOrderBy = "order by TOXEquipo.iCveEquipo";
			}

			vDespliega = dTOXEquipo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}

		// El codigo de aqui se traslado al servlet: servXLSpg0703060100.java
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}
}
