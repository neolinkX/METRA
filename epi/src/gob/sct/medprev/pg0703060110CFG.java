package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

/**
 * 
 * <p>
 * Title: Registro y Comportamiento de Controles para An�lisis Presuntivo
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
public class pg0703060110CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg0703060110CFG() {
		vParametros = new TParametro("07");
	}

	/**
	 * Metodo fillVector
	 */
	public void fillVector() {
		TDTOXEquipo dTOXEquipo = new TDTOXEquipo();

		try {
			String cWhere = "";
			String cOrderBy = "";

			// Solo traigo los equipos que pertenecen a An�lisis Presuntivo
			// (Inmunoensayo)
			cCondicion = " TOXEquipo.lCuantCual = 0 ";

			if (cCondicion.compareTo("") != 0) {
				cWhere += " where " + cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			} else {
				cOrderBy = "order by TOXEquipo.iCveEquipo";
			}

			vDespliega = dTOXEquipo.FindByAll(cWhere, cOrderBy);

			// El codigo de aqui se traslado al servlet:
			// servXLSpg0703060110.java
		} catch (Exception ex) {
			ex.printStackTrace();
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
