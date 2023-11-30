package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

public class pg071008010CFG extends CFGListBase2 {
	public pg071008010CFG() {
		vParametros = new TParametro("07");
		UpdStatus = "SaveOnly";
		cPaginas = "pg071003051.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDGRLMdoTrans dMEDServicio = new TDGRLMdoTrans();
		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0)
				cWhere += " where lActivo = 1 and " + cCondicion;
			else
				cWhere += " where lActivo = 1 ";

			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			vDespliega = dMEDServicio.findByAll(cWhere, cOrderBy);
			iNumReg = vDespliega.size();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVGRLProcesoMDOT vGRLServUM = new TVGRLProcesoMDOT();
			TDGRLProcesoMDOT dGRLServUM = new TDGRLProcesoMDOT();
			Vector vcGRLServUM = new Vector();

			for (int i = 1; i < Integer.parseInt(request
					.getParameter("hdTotalReg")); i++) {
				if (request.getParameter("chk" + i) != null) {
					vcGRLServUM = dGRLServUM.FindByUMMDPR(
							" where  a. ICVEMDOTRANS = b.ICVEMDOTRANS AND a.iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and a.iCveModulo = "
									+ request.getParameter("iCveUniMed")
									+ " and a.ICVEPROCESO = "
									+ request.getParameter("ICVEPROCESO")
									+ " and a.ICVEMDOTRANS = "
									+ Integer.parseInt(request
											.getParameter("chk" + i)), "");
					if (vcGRLServUM.size() == 0) {
						// Inserta el Registro
						vGRLServUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLServUM.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLServUM.setICveProceso(Integer.parseInt(request
								.getParameter("ICVEPROCESO")));
						vGRLServUM.setICveMdoTrans(Integer.parseInt(request
								.getParameter("chk" + i)));
						cClave = (String) dGRLServUM.delete(null, vGRLServUM);
						// System.out.println("BORRADO "+cClave);
						cClave = (String) dGRLServUM.insert(null, vGRLServUM);
						// System.out.println("INSERTADO "+cClave);
					}
				} else {
					vcGRLServUM = dGRLServUM.FindByUMMDPR(
							" where  a. ICVEMDOTRANS = b.ICVEMDOTRANS AND a.iCveUniMed = "
									+ request.getParameter("iCveUniMed")
									+ " and a.iCveModulo = "
									+ request.getParameter("iCveModulo")
									+ " and a.ICVEPROCESO = "
									+ request.getParameter("ICVEPROCESO")
									+ " and a.ICVEMDOTRANS = "
									+ request.getParameter("hdChk" + i), "");
					if (vcGRLServUM.size() > 0) {
						// Elimina el registro
						vGRLServUM.setICveUniMed(Integer.parseInt(request
								.getParameter("iCveUniMed")));
						vGRLServUM.setICveModulo(Integer.parseInt(request
								.getParameter("iCveModulo")));
						vGRLServUM.setICveProceso(Integer.parseInt(request
								.getParameter("ICVEPROCESO")));
						vGRLServUM.setICveMdoTrans(Integer.parseInt(request
								.getParameter("hdChk" + i)));
						cClave = (String) dGRLServUM.delete(null, vGRLServUM);
						// System.out.println("BORRADO2 "+cClave);
					}
				}
			}

		} catch (Exception ex) {
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

}