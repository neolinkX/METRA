package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;
import java.util.*;

/**
 * * Clase de configuración para CFG de la pagina pg070104061
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104061CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104061CFG.png'>
 */
public class pg070104061CFG extends CFGListBase2 {
	public Vector vResultado = new Vector();
	int lVariosMed = 0;

	public pg070104061CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "";
		NavStatus = "Disabled";
	}

	/**
	 * Método FillVector
	 */
	;

	public void fillVector() {
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		StringBuffer cFiltro = new StringBuffer();
		TFechas TFecha = new TFechas();
		int iCveServicio, iCveRama = 0, iSituacion;
		String cCampo;
		try {

			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveServicio").compareTo("null") != 0
					&& request.getParameter("iCveServicio").compareTo("") != 0) {
				iCveServicio = Integer.parseInt(
						request.getParameter("iCveServicio").toString(), 10);
				cFiltro.append(" where EA.iCveUniMed = ")
						.append(request.getParameter("iCveUniMed"))
						.append("   and EA.iCveModulo = ")
						.append(request.getParameter("iCveModulo"))
						.append("   and EA.iCveProceso = ")
						.append(this.vParametros
								.getPropEspecifica("EPIProceso"))
						.append("   and EA.dtSolicitado = '")
						.append(TFecha.getDateSQL(request.getParameter(
								"dtFechaI").toString())).append("'");
				// -- EA.iCveExpediente
				// PD.cApPaterno, PD.cApMaterno
				if (request.getParameter("iCveRama") != null) {
					this.lVariosMed = 1;
					cCampo = " and ER.lConcluido = ";
					iCveRama = Integer.parseInt(request
							.getParameter("iCveRama").toString(), 10);
				} else {
					this.lVariosMed = 0;
					cCampo = " and ES.lConcluido = ";
				}
				iSituacion = Integer.parseInt(request.getParameter("RSSitua")
						.toString(), 10);
				switch (iSituacion) {
				case 1:
					cFiltro.append(cCampo).append(" 0 ");
					break;
				case 2:
					cFiltro.append(cCampo).append(" 1 ");
					break;
				case 3:
					break;
				}
				if (this.cCondicion.compareToIgnoreCase("") != 0)
					cFiltro.append(" and ").append(this.cCondicion);
				if (this.cOrden.compareToIgnoreCase("") != 0)
					cFiltro.append(this.cOrden);
				else
					cFiltro.append(" order by EA.icveUniMed, EA.iCveModulo, EA.iCveProceso, EA.iFolioES ");

				vResultado = dEXPServicio.FindAtenXServ(cFiltro.toString(),
						this.lVariosMed, iCveServicio, iCveRama);
				iNumReg = vDespliega.size();
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public int getLVariosMed() {
		return lVariosMed;
	}
}