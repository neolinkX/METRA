package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070802060CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vAlmxArt = new Vector();
	public Vector vUbicacion = new Vector();
	// public Vector vAnalisis = new Vector();
	String cvAnio = "";
	int ivAlmacen = 0;
	int ivTipoArticulo = 0;
	int ivFamilia = 0;

	public pg070802060CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		TDALMArtxAlm dLoteCuantita = new TDALMArtxAlm();

		if (request.getParameter("SLSAlmacen") != null)
			ivAlmacen = new Integer(request.getParameter("SLSAlmacen"))
					.intValue();

		if (request.getParameter("SLSTipoArticulo") != null)
			ivTipoArticulo = new Integer(
					request.getParameter("SLSTipoArticulo")).intValue();

		if (request.getParameter("SLSFamilia") != null)
			ivFamilia = new Integer(request.getParameter("SLSFamilia")
					.toString()).intValue();
	}

	public void fillVector() {

		cPaginas = "pg070802020.jsp|";
		String cCondicion2 = "";

		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
		TDALMFamilia DALMFamilia = new TDALMFamilia();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMUbicacion DALMUbicacion = new TDALMUbicacion();

		// Vector de los Almacenes.
		try {
			vAlmacenes = DALMAlmacen.FindByCustomWhere(" where lActivo = 1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Tipos de Artículos.
		try {
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" join ALMArea "
							+ " on ALMArea.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo "
							+ " and ALMArea.iCveAlmacen    = " + ivAlmacen
							+ " where ALMTpoArticulo.lActivo = 1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Tipos de Artículos.
		try {
			vFamilias = DALMFamilia
					.FindByCustomWhere(" join ALMTpoArticulo "
							+ " on  ALMTpoArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo "
							+ " and ALMTpoArticulo.lActivo         = 1 "
							+ " join ALMArea "
							+ " on ALMArea.iCveTpoArticulo         = ALMTpoArticulo.iCveTpoArticulo "
							+ " and ALMArea.iCveAlmacen            = "
							+ ivAlmacen
							+ " and ALMArea.lActivo                = 1 "
							+ " where ALMFamilia.iCveTpoArticulo   = "
							+ ivTipoArticulo
							+ " and   ALMFamilia.lActivo           = 1 "
							+ " ORDER BY  ALMFamilia.cDscBreve ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Artículos por Almacén.
		try {
			vAlmxArt = DALMArtxAlm
					.FindByCustomWhere(" join ALMArticulo                                          "
							+ " on ALMArticulo.iCveArticulo     = ALMArtxAlm.iCveArticulo "
							+ " and ALMArticulo.iCveTpoArticulo = "
							+ ivTipoArticulo
							+ " and ALMArticulo.iCveFamilia     = "
							+ ivFamilia
							+ " where ALMArtxAlm.iCveAlmacen    = "
							+ ivAlmacen
							+ "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de la Ubicación de los Artículos en el Almacen.
		try {
			vUbicacion = DALMUbicacion
					.FindByCustomWhere(" where ALMUbicacion.iCveAlmacen    = "
							+ ivAlmacen
							+ " order by ALMUbicacion.iCveArticulo ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!cCondicion.equals(""))
			cCondicion = " where "
					+ cCondicion
					+ " and ALMArticulo.iCveTpoArticulo = "
					+ ivTipoArticulo
					+ "   and ALMArticulo.iCveFamilia     = "
					+ ivFamilia
					+ "   and ALMArtxAlm.iCveAlmacen      = "
					+ ivAlmacen
					+ "   and ALMArticulo.iCveArticulo    = ALMArtxAlm.iCveArticulo ";
		else
			cCondicion = " where ALMArticulo.iCveTpoArticulo = "
					+ ivTipoArticulo
					+ "   and ALMArticulo.iCveFamilia     = "
					+ ivFamilia
					+ "   and ALMArtxAlm.iCveAlmacen      = "
					+ ivAlmacen
					+ "   and ALMArticulo.iCveArticulo    = ALMArtxAlm.iCveArticulo ";

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by iCveArticulo ";

		try {
			vDespliega = DALMArtxAlm.FindByArtAlm(cCondicion);

		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}
		if (!vDespliega.isEmpty()) {
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

	}
}
