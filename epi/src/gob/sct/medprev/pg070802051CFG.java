package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.vo.*;

public class pg070802051CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
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
	int ivUsrAutoriza = 0;

	public pg070802051CFG() {
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

		if (request.getParameter("SLSInventario1") != null) {
			if (request.getParameter("SLSInventario1").toString()
					.compareToIgnoreCase("") != 0)
				ivInventario = new Integer(request.getParameter(
						"SLSInventario1").toString()).intValue();
		}

		if (request.getParameter("SLSAutorizacion") != null)
			ivUsrAutoriza = new Integer(request.getParameter("SLSAutorizacion")
					.toString()).intValue();

	}

	public void Guardar() {
		TDALMInventario DALMInventario = new TDALMInventario();
		TVALMInventario VALMInventario = new TVALMInventario();
		TDALMDtlleInve DALMDtlleInve = new TDALMDtlleInve();
		TDALMMovimiento DALMMovimiento = new TDALMMovimiento();
		TDALMDetalleMov DALMDetalleMov = new TDALMDetalleMov();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TDALMLote DALMLote = new TDALMLote();
		Vector vInventario = new Vector();
		Vector vDetalle = new Vector();
		Vector vMovimiento = new Vector();
		Vector vArtxAlm = new Vector();
		TFechas Fecha = new TFechas();
		double dvUnidades = 0;
		int ivMovimiento = 0;
		int ivCveTpoMovimiento = 0;

		// Los Movimientos de Todos los Artículos del Almacen en el Año.
		try {
			vMovimiento = DALMMovimiento
					.FindByCustomWhere(" where iAnio          = "
							+ Fecha.getIntYear(Fecha.TodaySQL())
							+ "   and iCveAlmacen = "
							+ ivAlmacen
							+ "  ORDER BY iAnio, iCveAlmacen, iCvemovimiento DESC ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vMovimiento.isEmpty())
			ivMovimiento = ((TVALMMovimiento) vMovimiento.get(0))
					.getiCveMovimiento() + 1;
		else
			ivMovimiento = 1;

		try {
			vDetalle = DALMDtlleInve.FindByAll(" where iAnio          = "
					+ Fecha.getIntYear(Fecha.TodaySQL())
					+ "   and iCveAlmacen    = " + ivAlmacen
					+ "   and iCveInventario = " + ivInventario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vDetalle.isEmpty()) {
			for (int i = 0; i < vDetalle.size(); i++) {
				TVALMDtlleInve VALMDtlleInve = new TVALMDtlleInve();
				VALMDtlleInve = (TVALMDtlleInve) vDetalle.get(i);

				// Para los Movimientos solicitados con existencias Físicas y
				// Logicas diferentes unicamente.
				if (new Double(VALMDtlleInve.getdELogica()).toString()
						.compareToIgnoreCase(
								new Double(VALMDtlleInve.getdEFisica())
										.toString()) != 0) {

					// Las Existencias de todos los Articulos del Almacen.
					try {
						vArtxAlm = DALMArtxAlm
								.FindByAll(" where iCveAlmacen  = " + ivAlmacen
										+ "   and iCveArticulo = "
										+ VALMDtlleInve.getiCveArticulo());
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// Perdida en el Almacen, se trata como Salida de Almacen.
					if (VALMDtlleInve.getdELogica() > VALMDtlleInve
							.getdEFisica()) {
						dvUnidades = VALMDtlleInve.getdELogica()
								- VALMDtlleInve.getdEFisica();
						ivCveTpoMovimiento = 4; // Ajuste con Disminución de
												// Existencias.
					}

					// Ganancia en el Almacen, se trata como Entrada de Almacén.
					if (VALMDtlleInve.getdELogica() < VALMDtlleInve
							.getdEFisica()) {
						dvUnidades = VALMDtlleInve.getdEFisica()
								- VALMDtlleInve.getdELogica();
						ivCveTpoMovimiento = 3; // Ajuste con Aumento de
												// Existencias.
					}

					TVALMMovimiento VALMMovimiento = new TVALMMovimiento();
					VALMMovimiento.setiAnio(Fecha.getIntYear(Fecha.TodaySQL()));
					VALMMovimiento.setiCveAlmacen(ivAlmacen);
					VALMMovimiento.setiCveMovimiento(ivMovimiento);
					VALMMovimiento.setiCveArticulo(VALMDtlleInve
							.getiCveArticulo());
					VALMMovimiento.setiCveTpoMovimiento(ivCveTpoMovimiento); // Salida
																				// de
																				// Almacén
					VALMMovimiento.setiCveConcepto(1); // Salida por
														// Desaparición o
														// Mermas.
					VALMMovimiento.setdUnidades(dvUnidades);
					TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(
							true).getAttribute("UsrID");
					VALMMovimiento.setiCveUsuario(vUsuario.getICveusuario());
					VALMMovimiento.setdtMovimiento(Fecha.TodaySQL());
					VALMMovimiento.setcObservacion("Movimiento de Ajuste"); // request.getParameter("TXTObservacion"));
					VALMMovimiento.setlPNC(0);

					// Valores para Actualizar las Existencias Generales.
					TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
					VALMArtxAlm.setiCveAlmacen(ivAlmacen);
					VALMArtxAlm
							.setiCveArticulo(VALMDtlleInve.getiCveArticulo());

					if (VALMDtlleInve.getiCveLote() == 0)
						VALMArtxAlm.setdExistencia(VALMDtlleInve.getdEFisica()); // Se
																					// toman
																					// las
																					// Existencias
																					// Físicas.
					else {
						if (!vArtxAlm.isEmpty()) {
							for (int ii = 0; ii < vArtxAlm.size(); ii++) {
								TVALMArtxAlm VALMArtxAlmExistencia = new TVALMArtxAlm();
								VALMArtxAlmExistencia = (TVALMArtxAlm) vArtxAlm
										.get(ii);
								if (VALMArtxAlmExistencia.getiCveArticulo() == VALMDtlleInve
										.getiCveArticulo()) {
									if (VALMDtlleInve.getdELogica() > VALMDtlleInve
											.getdEFisica())
										VALMArtxAlm
												.setdExistencia(VALMArtxAlmExistencia
														.getdExistencia()
														- (VALMDtlleInve
																.getdELogica() - VALMDtlleInve
																.getdEFisica())); // Se
																					// toman
																					// las
																					// Existencias
																					// Reales.
									else
										VALMArtxAlm
												.setdExistencia(VALMArtxAlmExistencia
														.getdExistencia()
														+ (VALMDtlleInve
																.getdEFisica() - VALMDtlleInve
																.getdELogica())); // Se
																					// toman
																					// las
																					// Existencias
																					// Reales.
								}
							}
						}
					}

					// Valores para Actualizar las Existencias de los Lotes.
					TVALMLote VALMLote = new TVALMLote();
					if (VALMDtlleInve.getiCveLote() != 0) {
						VALMLote.setiCveAlmacen(ivAlmacen);
						VALMLote.setiCveArticulo(VALMDtlleInve
								.getiCveArticulo());
						VALMLote.setiCveLote(VALMDtlleInve.getiCveLote());
						VALMLote.setdUnidades(VALMDtlleInve.getdEFisica());
						if (VALMLote.getdUnidades() == 0)
							VALMLote.setdtAgotado(Fecha.TodaySQL()); // En caso
																		// de
																		// Existencia
																		// = 0,
																		// se
																		// Agota
																		// el
																		// Lote.
					}

					// Valores de los Lotes con el Movimiento.
					TVALMDetalleMov VALMDetalleMov = new TVALMDetalleMov();
					if (VALMDtlleInve.getiCveLote() != 0) {
						VALMDetalleMov.setiAnio(Fecha.getIntYear(Fecha
								.TodaySQL()));
						VALMDetalleMov.setiCveAlmacen(ivAlmacen);
						VALMDetalleMov.setiCveMovimiento(ivMovimiento);
						VALMDetalleMov.setiCveLote(VALMDtlleInve.getiCveLote());
						VALMDetalleMov
								.setdUnidades(VALMDtlleInve.getdEFisica());
					}

					// Generación del Movimiento de Ajuste.
					try {
						DALMMovimiento.insert(null, VALMMovimiento);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// Actualización de Existencias Generales.
					try {
						DALMArtxAlm.updExistencias(null, VALMArtxAlm);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					// Actualización de Existencias del Lote.
					if (VALMDtlleInve.getiCveLote() != 0) {
						try {
							DALMLote.updExistencias(null, VALMLote);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						try {
							DALMDetalleMov.insert(null, VALMDetalleMov);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					ivMovimiento = ivMovimiento + 1;
				}
			}
		}

		VALMInventario.setiAnio(ivAnio);
		VALMInventario.setiCveAlmacen(ivAlmacen);
		VALMInventario.setiCveInventario(ivInventario);
		if (request.getParameter("dtAutorizacion") != null)
			if (request.getParameter("dtAutorizacion").toString()
					.compareToIgnoreCase("") != 0)
				VALMInventario.setdtActualizacion(Fecha.getDateSQL(request
						.getParameter("dtAutorizacion").toString()));
		if (request.getParameter("SLSAutorizacion") != null)
			if (request.getParameter("SLSAutorizacion").toString()
					.compareToIgnoreCase("") != 0)
				VALMInventario.setiCveAutoriza(new Integer(request
						.getParameter("SLSAutorizacion")).intValue());
		if (request.getParameter("TXTObservacion") != null)
			if (request.getParameter("TXTObservacion").toString()
					.compareToIgnoreCase("") != 0)
				VALMInventario.setcObservacion(request
						.getParameter("TXTObservacion"));

		// Actualización del Registro del Inventario.
		try {
			DALMInventario.updAutorizacion(null, VALMInventario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Reposicionamiento de la Pantalla.
		if (request.getParameter("dtAutorizacion") != null)
			ivInventario = 0;
		else {
			if (request.getParameter("SLSInventario1") != null) {
				if (request.getParameter("SLSInventario1").toString()
						.compareToIgnoreCase("") != 0)
					ivInventario = new Integer(request.getParameter(
							"SLSInventario1").toString()).intValue();
			}
		}
	}

	public void fillVector() {

		cPaginas = "pg070802051.jsp|";
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

		// Vector de Inventarios de los Artículos.
		try {
			vInventarios = DALMInventario
					.FindByAll(" where ALMInventario.iAnio       = "
							+ Fecha.getIntYear(Fecha.TodaySQL())
							+ "   and ALMInventario.iCveAlmacen = "
							+ ivAlmacen
							+ "   and ALMInventario.dtConteo        is not null "
							+ "   and ALMInventario.dtActualizacion is null "
							+ "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cCondicion.compareToIgnoreCase("") != 0)
			cCondicion = " left join ALMLote "
					+ " on   ALMLote.iCveAlmacen           = "
					+ ivAlmacen
					+ " and  ALMLote.iCveArticulo          = ALMArticulo.iCveArticulo "
					+ " where ALMArticulo.iCveTpoArticulo  = " + ivTipoArticulo
					+ " and   ALMArticulo.iCveFamilia      = " + ivFamilia
					+ " and " + cCondicion;
		else
			cCondicion = " left join ALMLote "
					+ " on   ALMLote.iCveAlmacen           = "
					+ ivAlmacen
					+ " and  ALMLote.iCveArticulo          = ALMArticulo.iCveArticulo "
					+ " where ALMArticulo.iCveTpoArticulo  = " + ivTipoArticulo
					+ " and   ALMArticulo.iCveFamilia      = " + ivFamilia + "";

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

		if (ivInventario != 0) {
			try {
				vDespliega = DALMArticulo.FindByLotes(cCondicion, "");
			} catch (Exception e) {
				vErrores.acumulaError("", 16, "");
				vDespliega = new Vector();
			}
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
