package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070802020CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vAlmxArt = new Vector();
	public Vector vUbicacion = new Vector();
	String cvAnio = "";
	int ivAlmacen = 0;
	int ivTipoArticulo = 0;
	int ivFamilia = 0;

	public pg070802020CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg080702021.jsp|";
	}

	public void mainBlock() {

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

	public void Guardar() {
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMUbicacion DALMUbicacion = new TDALMUbicacion();
		Vector vGuardar = new Vector();
		Vector vExisten = new Vector();

		String cCond = " where ALMArticulo.iCveTpoArticulo = " + ivTipoArticulo
				+ "   and ALMArticulo.iCveFamilia     = " + ivFamilia + "";

		// Vector de Registro a Guardar.
		try {
			vGuardar = DALMArticulo.FindByCustomWhere(cCond);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vGuardar = new Vector();
		}

		// Vector de los Artículos por Almacén ya Existentes.
		try {
			vExisten = DALMArtxAlm
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

		if (!vGuardar.isEmpty()) {
			for (int i = 0; i < vGuardar.size(); i++) {
				TVALMArticulo VALMArticulo = new TVALMArticulo();
				VALMArticulo = (TVALMArticulo) vGuardar.get(i);

				if (request.getParameter("TBXSel-"
						+ VALMArticulo.getiCveArticulo()) != null
						|| request.getParameter("TBXHidSel-"
								+ VALMArticulo.getiCveArticulo()) != null) {
					TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
					VALMArtxAlm.setiCveAlmacen(ivAlmacen);
					VALMArtxAlm.setiCveArticulo(VALMArticulo.getiCveArticulo());

					if (request.getParameter("FILMax-"
							+ VALMArticulo.getiCveArticulo()) != null) {
						if (request
								.getParameter(
										"FILMax-"
												+ VALMArticulo
														.getiCveArticulo())
								.toString().compareToIgnoreCase("") != 0)
							VALMArtxAlm.setdMaximo(new Double(request
									.getParameter("FILMax-"
											+ VALMArticulo.getiCveArticulo()))
									.doubleValue());
						else
							VALMArtxAlm.setdMaximo(new Double("0")
									.doubleValue());
					} else
						VALMArtxAlm.setdMaximo(new Double("0").doubleValue());

					if (request.getParameter("FILMin-"
							+ VALMArticulo.getiCveArticulo()) != null) {
						if (request
								.getParameter(
										"FILMin-"
												+ VALMArticulo
														.getiCveArticulo())
								.toString().compareToIgnoreCase("") != 0)
							VALMArtxAlm.setdMinimo(new Double(request
									.getParameter(
											"FILMin-"
													+ VALMArticulo
															.getiCveArticulo())
									.toString()).doubleValue());
						else
							VALMArtxAlm.setdMinimo(new Double("0")
									.doubleValue());
					} else
						VALMArtxAlm.setdMinimo(new Double("0").doubleValue());

					boolean lExiste = false;
					if (!vExisten.isEmpty()) {
						for (int j = 0; j < vExisten.size(); j++) {
							TVALMArtxAlm VALMArtxAlmExiste = new TVALMArtxAlm();
							VALMArtxAlmExiste = (TVALMArtxAlm) vExisten.get(j);

							if (VALMArtxAlmExiste.getiCveArticulo() == VALMArticulo
									.getiCveArticulo())
								lExiste = true;
						}
					}

					if (!lExiste) {
						// Asignación de los Artículos al Almacén, con Valores
						// Máximo y Minimo en su Caso.
						try {
							DALMArtxAlm.insert(null, VALMArtxAlm);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						// Asignación de los Artículos al Almacén, con Valores
						// Máximo y Minimo en su Caso.
						try {
							DALMArtxAlm.update(null, VALMArtxAlm);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} else {
					// Valores que se eliminan.
					if (request.getParameter("TBXHid-"
							+ VALMArticulo.getiCveArticulo()) != null) {
						TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
						VALMArtxAlm.setiCveAlmacen(ivAlmacen);
						VALMArtxAlm.setiCveArticulo(VALMArticulo
								.getiCveArticulo());

						try {
							DALMArtxAlm.delete(null, VALMArtxAlm);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						// Vector de la Ubicación de los Artículos en el
						// Almacen.
						try {
							DALMUbicacion.deleteCustomWhere(null,
									" delete from ALMUbicacion "
											+ "  where iCveAlmacen =     "
											+ ivAlmacen
											+ "    and iCveArticulo =    "
											+ VALMArticulo.getiCveArticulo());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
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
			vTpoArticulos = DALMTpoArticulo.FindByCustomWhere(// " join ALMArea "
																// +
																// " on ALMArea.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo "
																// +
																// " and ALMArea.iCveAlmacen    = "
																// + ivAlmacen +
					" where ALMTpoArticulo.lActivo = 1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Tipos de Artículos.
		try {
			vFamilias = DALMFamilia
					.FindByCustomWhere(" join ALMTpoArticulo "
							+ " on  ALMTpoArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo "
							+ " and ALMTpoArticulo.lActivo         = 1 "
							+
							// " join ALMArea " +
							// " on ALMArea.iCveTpoArticulo         = ALMTpoArticulo.iCveTpoArticulo "
							// +
							// " and ALMArea.iCveAlmacen            = " +
							// ivAlmacen +
							// " and ALMArea.lActivo                = 1 " +
							" where ALMFamilia.iCveTpoArticulo   = "
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

		if (cCondicion.compareToIgnoreCase("") == 0)
			cCondicion = " where ALMArticulo.iCveTpoArticulo = "
					+ ivTipoArticulo + "   and ALMArticulo.iCveFamilia     = "
					+ ivFamilia + "";
		else
			cCondicion = " where " + cCondicion
					+ " and ALMArticulo.iCveTpoArticulo = " + ivTipoArticulo
					+ " and ALMArticulo.iCveFamilia     = " + ivFamilia + "";

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by iCveArticulo ";

		try {
			vDespliega = DALMArticulo.FindByCustomWhere(cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}
