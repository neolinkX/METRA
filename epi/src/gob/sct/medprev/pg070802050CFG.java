package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070802050CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public Vector vLoteCuantita = new Vector();
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vAlmxArt = new Vector();
	public Vector vInventarios = new Vector();
	public Vector vDtlleInve = new Vector();

	String cvAnio = "";
	int ivAlmacen = 0;
	int ivTipoArticulo = 0;
	int ivFamilia = 0;
	int ivInventario = 0;
	int ivAnio = 2004;
	int ivUsrCaptura = 0;

	public StringBuffer Reporte = new StringBuffer("");

	public pg070802050CFG() {
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
		if (request.getParameter("SLSInventario") != null) {
			if (request.getParameter("SLSInventario").toString()
					.compareToIgnoreCase("") != 0)
				ivInventario = new Integer(request
						.getParameter("SLSInventario").toString()).intValue();
		}
		if (request.getParameter("SLSCaptura") != null)
			ivUsrCaptura = new Integer(request.getParameter("SLSCaptura")
					.toString()).intValue();
	}

	public void Guardar() {
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMInventario DALMInventario = new TDALMInventario();
		TDALMDtlleInve DALMDtlleInve = new TDALMDtlleInve();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMLote DALMLote = new TDALMLote();
		TFechas Fecha = new TFechas();

		Vector vGuardar = new Vector();
		Vector vExisten = new Vector();
		Vector vExistencias = new Vector();
		Vector vExisGrales = new Vector();
		Vector vInventario = new Vector();
		java.sql.Date dtConteo;

		String cCond1 = " left join ALMLote "
				+ " on   ALMLote.iCveAlmacen           = "
				+ ivAlmacen
				+ " and  ALMLote.iCveArticulo          = ALMArticulo.iCveArticulo "
				+ " where ALMArticulo.iCveTpoArticulo  = " + ivTipoArticulo;
		if (ivFamilia > 0)
			cCond1 += " and   ALMArticulo.iCveFamilia      = " + ivFamilia;
		cCond1 += " order by ALMArticulo.iCveArticulo ";

		// Vector de Registro que existen.
		try {
			vExisten = DALMDtlleInve
					.FindByCustomWhere(" where iAnio          = " + ivAnio
							+ "   and iCveAlmacen    = " + ivAlmacen
							+ "   and iCveinventario = " + ivInventario);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
		}

		// Generales para los que no tienen Lotes.
		try {
			vExistencias = DALMArtxAlm
					.FindByCustomWhere(" join ALMArticulo on ALMArticulo.iCveArticulo = ALMArtxAlm.iCveArticulo "
							+ " where iCveAlmacen = " + ivAlmacen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Generales para los que tienen Lotes.
		try {
			vExisGrales = DALMLote
					.FindByCustomWhere(" join ALMArticulo on ALMArticulo.iCveArticulo = ALMLote.iCveArticulo "
							+ "  and ALMArticulo.lLote = 1 "
							+ " where iCveAlmacen = " + ivAlmacen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de Registro a Guardar.
		try {
			vGuardar = DALMArticulo.FindByLotes(cCond1, "");
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vGuardar = new Vector();
		}

		if (!vGuardar.isEmpty()) {
			for (int i = 0; i < vGuardar.size(); i++) {
				TVALMArticulo VALMArticulo = new TVALMArticulo();
				VALMArticulo = (TVALMArticulo) vGuardar.get(i);
				boolean lExiste = false;
				double dExistencia = 0;

				if (!vExisten.isEmpty()) {
					for (int j = 0; j < vExisten.size(); j++) {
						TVALMDtlleInve VALMDtlleInve = new TVALMDtlleInve();
						VALMDtlleInve = (TVALMDtlleInve) vExisten.get(j);

						if (VALMDtlleInve.getiCveArticulo() == VALMArticulo
								.getiCveArticulo()
								&& VALMDtlleInve.getiCveLote() == VALMArticulo
										.getiCveLote())
							lExiste = true;
					}
				}

				if (new Integer(VALMArticulo.getlLote()).toString()
						.compareToIgnoreCase("1") != 0) {
					if (!vExistencias.isEmpty()) {
						for (int j = 0; j < vExistencias.size(); j++) {
							TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
							VALMArtxAlm = (TVALMArtxAlm) vExistencias.get(j);

							if (VALMArtxAlm.getiCveArticulo() == VALMArticulo
									.getiCveArticulo())
								dExistencia = VALMArtxAlm.getdExistencia();
						}
					}
				} else {
					if (!vExisGrales.isEmpty()) {
						for (int j = 0; j < vExisGrales.size(); j++) {
							TVALMLote VALMLote = new TVALMLote();
							VALMLote = (TVALMLote) vExisGrales.get(j);

							if (VALMLote.getiCveArticulo() == VALMArticulo
									.getiCveArticulo()
									&& VALMLote.getiCveLote() == VALMArticulo
											.getiCveLote())
								dExistencia = VALMLote.getdUnidades();
						}
					}
				}

				if (ivInventario == 0) {

					try {
						vInventario = DALMInventario
								.FindByAll(" where iAnio       = "
										+ ivAnio
										+ "   and iCveAlmacen = "
										+ ivAlmacen
										+ " order by iAnio, iCveAlmacen, iCveInventario DESC ");
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					if (!vInventario.isEmpty())
						ivInventario = ((TVALMInventario) vInventario.get(0))
								.getiCveInventario() + 1;
					else
						ivInventario = 1;

					TVALMInventario VALMInventario = new TVALMInventario();
					VALMInventario.setiAnio(ivAnio);
					VALMInventario.setiCveAlmacen(ivAlmacen);
					VALMInventario.setiCveInventario(ivInventario);
					VALMInventario.setdtGeneracion(Fecha.TodaySQL());
					VALMInventario.setiCveUsuGenera(12);

					if (request.getParameter("dtCaptura") != null)
						if (request.getParameter("dtCaptura").toString()
								.compareToIgnoreCase("") != 0)
							VALMInventario.setdtConteo(Fecha.getDateSQL(request
									.getParameter("dtCaptura")));

					try {
						DALMInventario.insert(null, VALMInventario);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					TVALMInventario VALMInventario = new TVALMInventario();
					VALMInventario.setiAnio(ivAnio);
					VALMInventario.setiCveAlmacen(ivAlmacen);
					VALMInventario.setiCveInventario(ivInventario);
					VALMInventario.setiCveUsuGenera(12);

					if (request.getParameter("dtCaptura") != null)
						if (request.getParameter("dtCaptura").toString()
								.compareToIgnoreCase("") != 0) {
							VALMInventario.setdtConteo(Fecha.getDateSQL(request
									.getParameter("dtCaptura")));

							try {
								DALMInventario.updCaptura(null, VALMInventario);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

				}

				if (request.getParameter("FILFis-"
						+ VALMArticulo.getiCveArticulo()
						+ VALMArticulo.getiCveLote()) != null) {
					TVALMDtlleInve VALMDtlleInve = new TVALMDtlleInve();
					TVALMInventario VALMInventario = new TVALMInventario();

					VALMDtlleInve.setiAnio(ivAnio);
					VALMDtlleInve.setiCveAlmacen(ivAlmacen);
					VALMDtlleInve.setiCveInventario(ivInventario);
					VALMDtlleInve.setiCveArticulo(VALMArticulo
							.getiCveArticulo());
					VALMDtlleInve.setiCveLote(VALMArticulo.getiCveLote());
					VALMDtlleInve.setdELogica(dExistencia);
					if (request
							.getParameter(
									"FILFis-" + VALMArticulo.getiCveArticulo()
											+ VALMArticulo.getiCveLote())
							.toString().compareToIgnoreCase("") != 0)
						VALMDtlleInve.setdEFisica(new Double(request
								.getParameter("FILFis-"
										+ VALMArticulo.getiCveArticulo()
										+ VALMArticulo.getiCveLote()))
								.doubleValue());

					if (lExiste) {

						// Actualizando las Existencias.
						try {
							DALMDtlleInve.update(null, VALMDtlleInve);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						// Insertando las Existencias.
						try {
							DALMDtlleInve.insert(null, VALMDtlleInve);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} else {
					if (!lExiste) {
						TVALMDtlleInve VALMDtlleInve = new TVALMDtlleInve();
						VALMDtlleInve.setiAnio(ivAnio);
						VALMDtlleInve.setiCveAlmacen(ivAlmacen);
						VALMDtlleInve.setiCveInventario(ivInventario);
						VALMDtlleInve.setiCveArticulo(VALMArticulo
								.getiCveArticulo());
						VALMDtlleInve.setiCveLote(VALMArticulo.getiCveLote());

						// Eliminando Registros ya existentes que no fueron
						// confirmados.
						try {
							DALMDtlleInve.delete(null, VALMDtlleInve);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}

		if (request.getParameter("dtCaptura") != null) {
			if (request.getParameter("dtCaptura").toString()
					.compareToIgnoreCase("") == 0)
				ivInventario = new Integer(request
						.getParameter("SLSInventario").toString()).intValue();
			else
				ivInventario = new Integer("0").intValue();
		}

	}

	public void fillVector() {

		cPaginas = "pg070802050.jsp|";
		String cCondicion2 = "";

		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
		TDALMFamilia DALMFamilia = new TDALMFamilia();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMUbicacion DALMUbicacion = new TDALMUbicacion();
		TDALMInventario DALMInventario = new TDALMInventario();
		TDALMDtlleInve DALMDtlleInve = new TDALMDtlleInve();
		TFechas Fecha = new TFechas();

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
			String cTemp1 = " join ALMArticulo                                          "
					+ " on ALMArticulo.iCveArticulo     = ALMArtxAlm.iCveArticulo "
					+ " and ALMArticulo.iCveTpoArticulo = " + ivTipoArticulo;
			if (ivFamilia > 0)
				cTemp1 += " and ALMArticulo.iCveFamilia     = " + ivFamilia;
			cTemp1 += " where ALMArtxAlm.iCveAlmacen    = " + ivAlmacen;

			vAlmxArt = DALMArtxAlm.FindByCustomWhere(cTemp1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de Inventarios de los Artículos.
		try {
			vInventarios = DALMInventario
					.FindByAll(" where ALMInventario.iAnio       = "
							+ Fecha.getIntYear(Fecha.TodaySQL())
							+ "   and ALMInventario.iCveAlmacen = " + ivAlmacen
							+ "   and ALMInventario.dtConteo        is null "
							+ "   and ALMInventario.dtActualizacion is null "
							+ "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cCondicion.compareToIgnoreCase("") != 0) {
			cCondicion = " left join ALMLote "
					+ " on   ALMLote.iCveAlmacen           = "
					+ ivAlmacen
					+ " and  ALMLote.iCveArticulo          = ALMArticulo.iCveArticulo "
					+ " where ALMArticulo.iCveTpoArticulo  = " + ivTipoArticulo;
			if (ivFamilia > 0)
				cCondicion += " and   ALMArticulo.iCveFamilia      = "
						+ ivFamilia;
			cCondicion += " and   " + cCondicion;
		} else {
			cCondicion = " left join ALMLote "
					+ " on   ALMLote.iCveAlmacen           = "
					+ ivAlmacen
					+ " and  ALMLote.iCveArticulo          = ALMArticulo.iCveArticulo "
					+ " where ALMArticulo.iCveTpoArticulo  = " + ivTipoArticulo;
			if (ivFamilia > 0)
				cCondicion += " and   ALMArticulo.iCveFamilia      = "
						+ ivFamilia;
		}
		try {
			vDtlleInve = DALMDtlleInve.FindByAll(" where iAnio          = "
					+ Fecha.getIntYear(Fecha.TodaySQL())
					+ "   and iCveAlmacen    =  " + ivAlmacen
					+ "   and iCveInventario = " + ivInventario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by ALMArticulo.iCveArticulo ";

		try {
			vDespliega = DALMArticulo.FindByLotes(cCondicion, "");
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

		if (cAccion.compareToIgnoreCase("Generar Reporte") == 0) {
			Reporte();
		}
	}

	public void Reporte() {
		TExcel tRep = new TExcel("07");
		TFechas tFecha = new TFechas("07");
		TVALMArticulo VArticulo;
		int iReng = 17;
		int iRengAnt = iReng;
		int iAlmacen = 0, iTpoArt = 0, iFamilia = 0;

		try {
			if (vDespliega != null && vDespliega.size() > 0) {
				// Recorrido de registros
				VArticulo = (TVALMArticulo) vDespliega.get(0);
				iAlmacen = VArticulo.getICveAlmacen();
				iTpoArt = VArticulo.getiCveTpoArticulo();
				iFamilia = VArticulo.getiCveFamilia();
				for (int k = 0; k < vAlmacenes.size(); k++) {
					if (((TVALMAlmacen) vAlmacenes.get(k)).getICveAlmacen() == ivAlmacen) {
						tRep.comDespliega("C", 11, ((TVALMAlmacen) vAlmacenes
								.get(k)).getCDscAlmacen());
						break;
					}
				}
				tRep.comDespliega("F", 11, VArticulo.getIPartida() + "");
				tRep.comDespliega("C", 12, VArticulo.getcDscTpoArticulo());
				if (ivFamilia > 0)
					tRep.comDespliega("C", 13, VArticulo.getcDscFamilia());
				else
					tRep.comDespliega("C", 13, "Todas las familias");
				tRep.comDespliega("F", 13,
						"'" + tFecha.getFechaDDMMMYYYY(tFecha.TodaySQL(), "/"));
				int iArtAnterior = 0;
				for (int i = 0; i < vDespliega.size(); i++) {
					VArticulo = (TVALMArticulo) vDespliega.get(i);
					if (iArtAnterior != VArticulo.getiCveArticulo()) {
						iArtAnterior = VArticulo.getiCveArticulo();
						tRep.comAlineaRango("A", iRengAnt, "B",
								iReng > iRengAnt ? iReng - 1 : iReng,
								tRep.getAT_COMBINA_DERECHA());
						tRep.comAlineaRango("C", iRengAnt, "C",
								iReng > iRengAnt ? iReng - 1 : iReng,
								tRep.getAT_COMBINA_IZQUIERDA());
						iRengAnt = iReng;
						tRep.comDespliega("A", iReng, iArtAnterior + "");
						tRep.comDespliega("C", iReng,
								VArticulo.getcDscArticulo());
						tRep.comAlineaRango("C", iReng, "D", iReng,
								tRep.getAT_HJUSTIFICA());
					}
					tRep.comAlineaRango("A", iReng, "B", iReng,
							tRep.getAT_COMBINA_DERECHA());
					if (VArticulo.getlLote() == 1) {
						if (VArticulo.getcLote() != null) {
							tRep.comDespliega("D", iReng,
									"'" + VArticulo.getcLote());
							tRep.comDespliega(
									"E",
									iReng,
									"'"
											+ tFecha.getFechaDDMMMYYYY(
													VArticulo.getDtCaducidad(),
													"/"));
							tRep.comAlineaRango("D", iReng, "D", iReng,
									tRep.getAT_HIZQUIERDA());
							tRep.comAlineaRango("E", iReng, "E", iReng,
									tRep.getAT_HCENTRO());
							tRep.comDespliega("F", iReng,
									VArticulo.getdUnidades() + "");
							tRep.comAlineaRango("F", iReng, "F", iReng,
									tRep.getAT_HDERECHA());
							tRep.comCellFormat("F", iReng, "F", iReng, "#,##0");
						} else {
							tRep.comDespliega("D", iReng,
									"El artículo maneja lotes y no hay lotes registrados");
							tRep.comAlineaRango("D", iReng, "G", iReng,
									tRep.getAT_COMBINA_IZQUIERDA());
						}
					} else {
						if (!vAlmxArt.isEmpty()) {
							for (int j = 0; j < vAlmxArt.size(); j++) {
								TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
								VALMArtxAlm = (TVALMArtxAlm) vAlmxArt.get(j);
								if (VArticulo.getiCveArticulo() == VALMArtxAlm
										.getiCveArticulo()) {
									tRep.comDespliega("F", iReng,
											VALMArtxAlm.getdExistencia() + "");
									tRep.comAlineaRango("F", iReng, "F", iReng,
											tRep.getAT_HDERECHA());
									tRep.comCellFormat("F", iReng, "F", iReng,
											"#,##0");
									break;
								}
							}
						} else
							tRep.comDespliega("F", iReng, "0");
					}

					tRep.comAlineaRangoVer("A", iReng, "G", iReng,
							tRep.getAT_VAJUSTAR());
					tRep.comAlineaRangoVer("A", iReng, "G", iReng,
							tRep.getAT_VSUPERIOR());
					tRep.comBordeTotal("A", iReng, "G", iReng, 1);
					iReng++;
				}
			}
		} catch (Exception ex) {
			error("Reporte", ex);
		}
		Reporte = tRep.creaActiveX("pg070802050",
				"Inv_" + iAlmacen + "_" + iTpoArt + "_" + iFamilia + "_"
						+ tFecha.getFechaDDMMYYYY(tFecha.TodaySQL(), "-"),
				false, false, true);
	}

	public String getReporte() {
		return Reporte.toString();
	}

}
