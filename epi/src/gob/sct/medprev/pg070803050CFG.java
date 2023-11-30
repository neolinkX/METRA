package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import java.util.*;

/**
 * * Clase de configuracion para Control de Vehiculos - Solicitudes de Vehiculo
 * por Usuario
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070704010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070704010CFG.png'>
 */
public class pg070803050CFG extends CFGListBase2 {
	private StringBuffer sbReporte = new StringBuffer();
	private int iCantidad = 0;

	public pg070803050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMSolicSumin tdSolicSumin = new TDALMSolicSumin();
		try {
			if (request.getParameter("hdBoton") != null
					&& (request.getParameter("hdBoton").equals("Primero") || request
							.getParameter("hdBoton").equals("Generar Reporte"))
					&& request.getParameter("iAnio") != null
					&& Integer.parseInt(request.getParameter("iAnio")) > 0) {
				HashMap hm = new HashMap();
				String cOrderBy = "";

				hm.put("iAnio", request.getParameter("iAnio"));

				if (request.getParameter("iCveModulo") != null
						&& Integer.parseInt(request.getParameter("iCveModulo")) > 0) {
					hm.put("iCveModulo", request.getParameter("iCveModulo"));
				}
				if (request.getParameter("iCveArea") != null
						&& Integer.parseInt(request.getParameter("iCveArea")) > 0) {
					hm.put("iCveArea", request.getParameter("iCveArea"));
				}
				if (request.getParameter("iCveUniMed") != null
						&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0) {
					hm.put("iCveUniMed", request.getParameter("iCveUniMed"));
				}
				if (request.getParameter("iCvePeriodo") != null
						&& Integer
								.parseInt(request.getParameter("iCvePeriodo")) > 0) {
					hm.put("iCvePeriodo", request.getParameter("iCvePeriodo"));
				}
				if (request.getParameter("iCveTpoArticulo") != null
						&& Integer.parseInt(request
								.getParameter("iCveTpoArticulo")) > 0) {
					hm.put("iCveTpoArticulo",
							request.getParameter("iCveTpoArticulo"));
				}
				if (request.getParameter("iCveFamilia") != null
						&& Integer
								.parseInt(request.getParameter("iCveFamilia")) > 0) {
					hm.put("iCveFamilia", request.getParameter("iCveFamilia"));
				}
				if (request.getParameter("iCveArticulo") != null
						&& Integer.parseInt(request
								.getParameter("iCveArticulo")) > 0) {
					hm.put("iCveArticulo", request.getParameter("iCveArticulo"));
				}

				if (cOrden.trim().length() > 0)
					cOrderBy = cOrden
							+ ", a.iCveTpoArticulo, a.iCveFamilia, a.iCveArticulo";
				else
					cOrderBy = " ORDER BY a.iCveTpoArticulo, a.iCveFamilia, a.iCveArticulo";

				vDespliega = tdSolicSumin.buscaSolicitud(hm, cOrderBy);
				if (vDespliega != null && vDespliega.size() > 0) {
					this.iCantidad = vDespliega.size();
					if (cAccion.equals("Generar Reporte")) {
						setExcel(vDespliega);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setExcel(Vector vDatos) {
		TExcel tRep = new TExcel("07");
		TFechas tFecha = new TFechas("07");
		int iRow = 11;
		try {
			for (int i = 0; i < vDatos.size(); i++) {
				TVALMSolicSumin tvSolicSumin = (TVALMSolicSumin) vDatos.get(i);
				String cNombre = "";

				if (tvSolicSumin.getCNombre() != null) {
					cNombre = tvSolicSumin.getCNombre();
					if (tvSolicSumin.getCApPaterno() != null) {
						cNombre += " " + tvSolicSumin.getCApPaterno();
						if (tvSolicSumin.getCApMaterno() != null) {
							cNombre += " " + tvSolicSumin.getCApMaterno();
						}
					}
				}

				tRep.comDespliega("A", iRow,
						"" + tvSolicSumin.getICveSolicSum());
				tRep.comDespliega(
						"B",
						iRow,
						(tvSolicSumin.getDtSolicitud() != null ? tFecha
								.getFechaDDMMYYYY(
										tvSolicSumin.getDtSolicitud(), "") : ""));
				tRep.comDespliega(
						"C",
						iRow,
						(tvSolicSumin.getCDscUniMed() != null ? tvSolicSumin
								.getCDscUniMed() : ""));
				tRep.comDespliega(
						"D",
						iRow,
						(tvSolicSumin.getCDscModulo() != null ? tvSolicSumin
								.getCDscModulo() : ""));
				tRep.comDespliega(
						"E",
						iRow,
						(tvSolicSumin.getCDscArea() != null ? tvSolicSumin
								.getCDscArea() : ""));
				tRep.comDespliega("F", iRow, cNombre);
				tRep.comDespliega(
						"G",
						iRow,
						(tvSolicSumin.getCDscTpoArticulo() != null ? tvSolicSumin
								.getCDscTpoArticulo() : ""));
				tRep.comDespliega(
						"H",
						iRow,
						(tvSolicSumin.getCDscFamilia() != null ? tvSolicSumin
								.getCDscFamilia() : ""));
				tRep.comDespliega(
						"I",
						iRow,
						(tvSolicSumin.getCDscArticulo() != null ? tvSolicSumin
								.getCDscArticulo() : ""));
				tRep.comDespliega(
						"J",
						iRow,
						(tvSolicSumin.getCDscUnidad() != null ? tvSolicSumin
								.getCDscUnidad() : ""));
				tRep.comDespliega("K", iRow,
						"" + tvSolicSumin.getDUnidSolicita());
				tRep.comDespliega("L", iRow, "" + tvSolicSumin.getDUnidAutor());
				tRep.comDespliega(
						"M",
						iRow,
						(tvSolicSumin.getDtSuministro() != null ? tFecha
								.getFechaDDMMYYYY(
										tvSolicSumin.getDtSuministro(), "")
								: ""));
				tRep.comDespliega(
						"N",
						iRow,
						(tvSolicSumin.getCDscMeta() != null ? tvSolicSumin
								.getCDscMeta() : ""));
				iRow++;
			}
			sbReporte.append(tRep.creaActiveX("pg070803050", "ConsSol_"
					+ tFecha.getFechaDDMMYYYY(tFecha.TodaySQL(), "-"), false,
					false, true));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Vector Articulo(String ivTipoArticulo, String ivFamilia) {
		Vector vArticulos = new Vector();
		try {
			// vArticulos = new
			// TDALMArticulo().FindByCustomWhere(" WHERE ALMArticulo.iCveArticulo = "
			// + ivArticulo);

			vArticulos = new TDALMArticulo()
					.FindByCustomWhere(" where ALMArticulo.iCveTpoArticulo = "
							+ ivTipoArticulo
							+ "   and ALMArticulo.iCveFamilia     = "
							+ ivFamilia + " ORDER BY ALMArticulo.cDscBreve ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vArticulos;
	}

	public StringBuffer getSbReporte() {
		return sbReporte;
	}

	public int getICantidad() {
		return iCantidad;
	}
}