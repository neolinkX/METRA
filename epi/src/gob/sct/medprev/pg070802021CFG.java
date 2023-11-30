package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070802021CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
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
	int ivArticulo = 0;

	public pg070802021CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070802020.jsp|";
	}

	public void mainBlock() {

		if (request.getParameter("SLSAlmacen") != null)
			ivAlmacen = new Integer(request.getParameter("SLSAlmacen"))
					.intValue();
		else if (request.getParameter("hdAlmacen") != null)
			ivAlmacen = new Integer(request.getParameter("hdAlmacen"))
					.intValue();

		if (request.getParameter("SLSTipoArticulo") != null)
			ivTipoArticulo = new Integer(
					request.getParameter("SLSTipoArticulo")).intValue();
		else if (request.getParameter("hdTipoArticulo") != null)
			ivTipoArticulo = new Integer(request.getParameter("hdTipoArticulo"))
					.intValue();

		if (request.getParameter("SLSFamilia") != null)
			ivFamilia = new Integer(request.getParameter("SLSFamilia")
					.toString()).intValue();
		else if (request.getParameter("hdFamilia") != null)
			ivFamilia = new Integer(request.getParameter("hdFamilia")
					.toString()).intValue();

		if (request.getParameter("hdIni") != null)
			ivArticulo = new Integer(request.getParameter("hdIni").toString())
					.intValue();

	}

	public void Guardar() {
		TDALMSeccion DALMSeccion = new TDALMSeccion();
		TDALMUbicacion DALMUbicacion = new TDALMUbicacion();
		Vector vGuardar = new Vector();
		Vector vUbica = new Vector();

		// Vector de la Ubicación de los Artículos en el Almacen.
		try {
			vUbica = DALMUbicacion
					.FindByCustomWhere(" where ALMUbicacion.iCveAlmacen    = "
							+ ivAlmacen
							+ " order by ALMUbicacion.iCveArticulo ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vGuardar = DALMSeccion.FindByCustomWhere(" join ALMArea "
					+ " on ALMArea.iCveAlmacen = ALMSeccion.iCveAlmacen "
					+ " and ALMArea.iCveArea   = ALMSeccion.iCveArea "
					+ " and ALMArea.iCveTpoArticulo = " + ivTipoArticulo
					+ " and ALMArea.lActivo = 1 "
					+ " where ALMSeccion.iCveAlmacen = " + ivAlmacen
					+ " and   ALMSeccion.lActivo = 1 ");
		} catch (Exception e) {
			e.printStackTrace();
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vGuardar.isEmpty()) {
			for (int i = 0; i < vGuardar.size(); i++) {
				TVALMSeccion VALMSeccion = new TVALMSeccion();
				VALMSeccion = (TVALMSeccion) vGuardar.get(i);

				if (request.getParameter("TBXSel-" + VALMSeccion.getICveArea()
						+ "-" + VALMSeccion.getICveSeccion()) != null) {

					TVALMUbicacion VALMUbicacion = new TVALMUbicacion();
					VALMUbicacion.setiCveAlmacen(ivAlmacen);
					VALMUbicacion.setiCveArea(VALMSeccion.getICveArea());
					VALMUbicacion.setiCveSeccion(VALMSeccion.getICveSeccion());
					VALMUbicacion.setiCveArticulo(ivArticulo);
					boolean lExiste = false;

					if (!vUbica.isEmpty()) {
						for (int j = 0; j < vUbica.size(); j++) {
							TVALMUbicacion VALMUbicacionExiste = new TVALMUbicacion();
							VALMUbicacionExiste = (TVALMUbicacion) vUbica
									.get(j);

							if (VALMUbicacionExiste.getiCveAlmacen() == VALMUbicacion
									.getiCveAlmacen()
									&& VALMUbicacionExiste.getiCveArea() == VALMUbicacion
											.getiCveArea()
									&& VALMUbicacionExiste.getiCveSeccion() == VALMUbicacion
											.getiCveSeccion()
									&& VALMUbicacionExiste.getiCveArticulo() == VALMUbicacion
											.getiCveArticulo())
								lExiste = true;

						}
					}

					if (!lExiste)
						try {
							DALMUbicacion.insert(null, VALMUbicacion);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
				} else {

					if (request.getParameter("TBXHid-"
							+ VALMSeccion.getICveArea() + "-"
							+ VALMSeccion.getICveSeccion()) != null) {
						TVALMUbicacion VALMUbicacion = new TVALMUbicacion();
						VALMUbicacion.setiCveAlmacen(ivAlmacen);
						VALMUbicacion.setiCveArea(VALMSeccion.getICveArea());
						VALMUbicacion.setiCveSeccion(VALMSeccion
								.getICveSeccion());
						VALMUbicacion.setiCveArticulo(ivArticulo);

						try {
							DALMUbicacion.delete(null, VALMUbicacion);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void fillVector() {

		String cCondicion2 = "";

		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
		TDALMFamilia DALMFamilia = new TDALMFamilia();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMUbicacion DALMUbicacion = new TDALMUbicacion();
		TDALMSeccion DALMSeccion = new TDALMSeccion();

		// Vector de los Almacenes.
		try {
			vAlmacenes = DALMAlmacen.FindByCustomWhere(" join ALMArea "
					+ " on ALMArea.iCveAlmacen = ALMAlmacen.iCveAlmacen "
					+ " and ALMArea.iCveTpoArticulo = " + ivTipoArticulo
					+ " where ALMAlmacen.lActivo = 1 ");
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
							+ " on ALMArea.iCveAlmacen            = "
							+ ivAlmacen
							+ " and ALMArea.iCveTpoArticulo         = ALMTpoArticulo.iCveTpoArticulo "
							+ " and ALMArea.lActivo                = 1 "
							+ " where ALMFamilia.iCveTpoArticulo   = "
							+ ivTipoArticulo
							+ " and   ALMFamilia.lActivo           = 1 "
							+ " ORDER BY ALMFamilia.iCveTpoArticulo, ALMFamilia.cDscBreve ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Artículos por Almacén.
		try {
			vAlmxArt = DALMArtxAlm
					.FindByCustomWhere(" join ALMArticulo                                          "
							+ " on ALMArticulo.iCveArticulo     = ALMArtxAlm.iCveArticulo "
							+ " where ALMArtxAlm.iCveAlmacen    = "
							+ ivAlmacen
							+ " and ALMArticulo.iCveTpoArticulo = "
							+ ivTipoArticulo
							+ " and ALMArticulo.iCveFamilia     = "
							+ ivFamilia
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

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by iCveArticulo ";

		try {
			vDespliega = DALMSeccion.FindByCustomWhere(" join ALMArea "
					+ " on ALMArea.iCveAlmacen = ALMSeccion.iCveAlmacen "
					+ " and ALMArea.iCveArea   = ALMSeccion.iCveArea "
					+ " where ALMSeccion.iCveAlmacen = " + ivAlmacen
					+ " and ALMSeccion.lActivo = 1 "
					+ " and ALMArea.iCveTpoArticulo = " + ivTipoArticulo
					+ " and ALMArea.lActivo = 1 ");
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0
					|| cAccion.compareToIgnoreCase("Actual") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

	}
}
