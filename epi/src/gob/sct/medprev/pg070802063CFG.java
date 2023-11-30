package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070802063CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vAlmxArt = new Vector();
	public Vector vArticulos = new Vector();
	public TVALMArticulo tvArticulo = new TVALMArticulo();
	public TVALMArtxAlm tvArtxAlm = new TVALMArtxAlm();
	public TVALMTpoArticulo tvTpoArticulo = new TVALMTpoArticulo();
	public TVALMFamilia tvFamilia = new TVALMFamilia();
	public TVALMAlmacen tvAlmacen = new TVALMAlmacen();
	String cvAnio = "";
	int ivAlmacen = 0;
	int ivTipoArticulo = 0;
	int ivFamilia = 0;
	String articulo = "";

	public pg070802063CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070802060.jsp|";
	}

	public void mainBlock() {
		TDALMArtxAlm dLoteCuantita = new TDALMArtxAlm();

		if (request.getParameter("iCveArticulo") != null) {
			articulo = request.getParameter("iCveArticulo").toString();
		}
		if (request.getParameter("SLSAlmacen") != null) {
			ivAlmacen = new Integer(request.getParameter("SLSAlmacen"))
					.intValue();
		}
		if (request.getParameter("SLSTipoArticulo") != null) {
			ivTipoArticulo = new Integer(
					request.getParameter("SLSTipoArticulo")).intValue();
		}
		if (request.getParameter("SLSFamilia") != null) {
			ivFamilia = new Integer(request.getParameter("SLSFamilia")
					.toString()).intValue();
		}

	}

	public void fillVector() {
		String cCondicion2 = "";
		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
		TDALMFamilia DALMFamilia = new TDALMFamilia();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMMovimiento DALMMovimiento = new TDALMMovimiento();
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMUbicacion DALMUbicacion = new TDALMUbicacion();
		TDALMSeccion DALMSeccion = new TDALMSeccion();

		// Vector de los Almacenes.
		try {
			vAlmacenes = DALMAlmacen
					.FindByCustomWhere(" where lActivo = 1 and iCveAlmacen="
							+ ivAlmacen);
			if (vAlmacenes.size() > 0)
				tvAlmacen = (TVALMAlmacen) vAlmacenes.get(0);
			else
				tvAlmacen.setCDscAlmacen("&nbsp;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Tipos de Artículos.
		try {
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" where iCveTpoArticulo="
							+ ivTipoArticulo);
			if (vTpoArticulos.size() > 0)
				tvTpoArticulo = (TVALMTpoArticulo) vTpoArticulos.get(0);
			else
				tvTpoArticulo.setCDscBreve("&nbsp;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de Familia
		try {
			vFamilias = DALMFamilia.FindByCustomWhere(" where iCveTpoArticulo="
					+ ivTipoArticulo + " and iCveFamilia=" + ivFamilia);
			if (vFamilias.size() > 0)
				tvFamilia = (TVALMFamilia) vFamilias.get(0);
			else
				tvFamilia.setcDscBreve("&nbsp;");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector Articulo a Mostrar
		if (!articulo.equals("0")) {
			try {
				Vector x = DALMArticulo.FindByAllMov(" where iCveArticulo="
						+ articulo);
				if (x.size() > 0) {
					tvArticulo = (TVALMArticulo) x.get(0);
				} else {
					tvArticulo.setcCveArticulo("&nbsp;");
					tvArticulo.setiCveArticulo(0);
				}
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		} else {
			tvArticulo.setiCveArticulo(0);
			tvArticulo.setcCveArticulo("");
		}

		// B e a n S c r o l l e r
		try {
			String q = " join ALMArea "
					+ " on ALMArea.iCveAlmacen = ALMSeccion.iCveAlmacen "
					+ " and ALMArea.iCveArea   = ALMSeccion.iCveArea "
					+ " and ALMArea.iCveTpoArticulo = " + ivTipoArticulo
					+ " and ALMArea.lActivo = 1 " + " JOIN ALMUbicacion "
					+ " on ALMUbicacion.iCveAlmacen = ALMSeccion.iCveAlmacen "
					+ " and ALMUbicacion.iCveArea = ALMSeccion.iCveArea "
					+ " and ALMUbicacion.iCveSeccion = ALMSeccion.iCveSeccion "
					+ " where ALMSeccion.iCveAlmacen = " + ivAlmacen
					+ " and ALMUbicacion.iCveArticulo = " + articulo
					+ " and ALMSeccion.lActivo = 1 ";
			if (cCondicion != null && !cCondicion.equals(""))
				q += " and " + cCondicion + " ";
			q += cOrden;
			vDespliega = DALMSeccion.FindByCustomWhere(q);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			cImprimir = "Imprimir";
			if (vDespliega.size() > iNumReg)
				NavStatus = "FirstRecord";
			else
				NavStatus = "Disabled";
			/*
			 * if (NavStatus.compareToIgnoreCase("Disabled") == 0) { NavStatus =
			 * "FirstRecord"; }
			 */
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}