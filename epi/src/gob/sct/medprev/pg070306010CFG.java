package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;

public class pg070306010CFG extends CFGListBase2 {

	public pg070306010CFG() {
		this.vParametros = new TParametro("07");
		cPaginas = "pg070306011.jsp";
	}

	public void fillVector() {
		try {
			TDTOXReactivo dToxReactivo = new TDTOXReactivo();
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null) {
				if (cCondicion.compareTo("") != 0)
					cCondicion = " AND " + cCondicion + " AND iAnio = "
							+ request.getParameter("iAnio")
							+ " AND iCveLaboratorio = "
							+ request.getParameter("iCveUniMed");
				else
					cCondicion = " AND iAnio = "
							+ request.getParameter("iAnio")
							+ " AND iCveLaboratorio = "
							+ request.getParameter("iCveUniMed");
				vDespliega = dToxReactivo.findByWhere(cCondicion, cOrden);
			}
		} catch (Exception e) {
			vDespliega = new Vector();
		}
		if (vDespliega.isEmpty()) {
			vErrores.acumulaError("", 15, "");
		}
	}

}
