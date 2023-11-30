package gob.sct.medprev;

import java.util.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070802064CFG extends CFGListBase2 {
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
	String cvAnio = "";
	int ivAlmacen = 0;
	int ivTipoArticulo = 0;
	int ivFamilia = 0;
	String oldFamilia = "";
	String articulo = "";
	String hdMov = "";

	public pg070802064CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070802060.jsp|";
	}

	public void mainBlock() {
		TDALMArtxAlm dLoteCuantita = new TDALMArtxAlm();

		if (request.getParameter("iCveArticulo") != null) {
			articulo = request.getParameter("iCveArticulo").toString();
		}
		if (request.getParameter("oldFamilia") != null) {
			oldFamilia = request.getParameter("oldFamilia").toString();
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
		if (request.getParameter("hdMov") != null) {
			hdMov = request.getParameter("hdMov").toString();
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
							+ " ORDER BY ALMFamilia.cDscBreve ");
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

		// Vector de Articulos
		try {
			String cdc = " where ALMArticulo.iCveTpoArticulo = "
					+ ivTipoArticulo
					+ " and ALMArticulo.iCveFamilia     = "
					+ ivFamilia
					+ " and ALMArtxAlm.iCveAlmacen      = "
					+ ivAlmacen
					+ " and ALMArticulo.iCveArticulo    = ALMArtxAlm.iCveArticulo";
			vArticulos = DALMArtxAlm.FindByArtAlm(cdc);
		} catch (Exception eee) {
			eee.printStackTrace();
		}

		// Vector Articulo a Mostrar
		if (!articulo.equals("0") || ivFamilia != 0) {
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

		Calendar h = Calendar.getInstance();
		if (!articulo.equals("0") && !articulo.equals("") && ivFamilia != 0
				&& hdMov.equals("")) {
			// Condiciones Filtro
			if (!cCondicion.equals("")) {
				cCondicion = " and " + cCondicion + " and M.iCveAlmacen  = "
						+ ivAlmacen + " and M.iAnio        = "
						+ h.get(Calendar.YEAR) + " and M.iCveArticulo = "
						+ articulo;
			} else {
				cCondicion = " and M.iCveAlmacen = " + ivAlmacen
						+ " and M.iAnio        = " + h.get(Calendar.YEAR)
						+ " and M.iCveArticulo = " + articulo;
			}
			// Orden
			if (cOrden.compareTo("") != 0) {
				cCondicion = cCondicion + cOrden;
			} else {
				cCondicion = cCondicion + " order by M.iCveMovimiento ";
			}
			// Llenar Bean Scroller
			try {
				vDespliega = DALMMovimiento.FindByAllMovXArt(cCondicion);
			} catch (Exception e) {
				vErrores.acumulaError("", 16, "");
				vDespliega = new Vector();
			}
		} else if (oldFamilia.equals("" + ivFamilia) && hdMov.equals("S")) {
			// Familias Iguales
			if (!cCondicion.equals("")) {
				cCondicion = " and " + cCondicion + " and M.iCveAlmacen  = "
						+ ivAlmacen + " and M.iAnio        = "
						+ h.get(Calendar.YEAR) + " and M.iCveArticulo = "
						+ articulo;
			} else {
				cCondicion = " and M.iCveAlmacen = " + ivAlmacen
						+ " and M.iAnio        = " + h.get(Calendar.YEAR)
						+ " and M.iCveArticulo = " + articulo;
			}
			// Orden
			if (cOrden.compareTo("") != 0) {
				cCondicion = cCondicion + cOrden;
			} else {
				cCondicion = cCondicion + " order by M.iCveMovimiento ";
			}
			// Llenar Bean Scroller
			try {
				vDespliega = DALMMovimiento.FindByAllMovXArt(cCondicion);
			} catch (Exception e) {
				vErrores.acumulaError("", 16, "");
				vDespliega = new Vector();
			}
		} else if (!oldFamilia.equals("" + ivFamilia) && hdMov.equals("S")
				&& ivFamilia != 0) {
			// Familias diferentes
			// Obtener datos del primer articulo para mostrar
			if (vArticulos.size() > 0) {
				tvArtxAlm = (TVALMArtxAlm) vArticulos.get(0);
				articulo = "" + tvArtxAlm.getiCveArticulo();
				// Vector Articulo a Mostrar
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

				if (!cCondicion.equals("")) {
					cCondicion = " and " + cCondicion
							+ " and M.iCveAlmacen  = " + ivAlmacen
							+ " and M.iAnio        = " + h.get(Calendar.YEAR)
							+ " and M.iCveArticulo = " + articulo;
				} else {
					cCondicion = " and M.iCveAlmacen = " + ivAlmacen
							+ " and M.iAnio        = " + h.get(Calendar.YEAR)
							+ " and M.iCveArticulo = " + articulo;
				}
				// Orden
				if (cOrden.compareTo("") != 0) {
					cCondicion = cCondicion + cOrden;
				} else {
					cCondicion = cCondicion + " order by M.iCveMovimiento ";
				}
				// Llenar Bean Scroller
				try {
					vDespliega = DALMMovimiento.FindByAllMovXArt(cCondicion);
				} catch (Exception e) {
					vErrores.acumulaError("", 16, "");
					vDespliega = new Vector();
				}

			}
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
