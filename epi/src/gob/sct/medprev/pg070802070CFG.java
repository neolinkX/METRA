package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import java.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;
import com.micper.util.*;
import java.text.*;

/**
 * * Clase de configuracion para Clase para el control de la tabla de
 * ALMArticulo
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803012CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803012CFG.java.png'>
 */
public class pg070802070CFG extends CFGCatBase2 {
	TFechas tFecha = new TFechas("07");
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	private Vector vArticulos = new Vector();
	private Vector vLotes = new Vector();
	public Vector vArticuloLote = new Vector();
	public Vector vTpoMovimiento = new Vector();
	public Vector vConcepto = new Vector();
	private int ivCveUsuario = 0;
	public int ivAnio = 0, ivUniMed = 0, ivAlmacen = 0, ivTipoArticulo = 0,
			ivFamilia = 0, ivTipoMovim = 0, ivConceptoMov = 0;
	private TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
	private TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
	private TDALMFamilia DALMFamilia = new TDALMFamilia();
	private TDALMArticulo DALMArticulo = new TDALMArticulo();
	private TDALMLote DALMLote = new TDALMLote();
	private TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
	private TDALMTpoMovimiento DALMTpoMov = new TDALMTpoMovimiento();
	private TDALMConcepto DALMConcepto = new TDALMConcepto();
	private TVALMArticulo VArticulo;
	private TVALMLote VLote;
	private boolean lSuma = true;

	public pg070802070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {
		TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute(
				"UsrID");
		if (vUsuario != null)
			ivCveUsuario = vUsuario.getICveusuario();
		if (request.getParameter("iEjercicio") != null)
			ivAnio = new Integer(request.getParameter("iEjercicio")).intValue();
		if (request.getParameter("iCveUniMed") != null)
			ivUniMed = new Integer(request.getParameter("iCveUniMed"))
					.intValue();
		if (request.getParameter("SLSAlmacen") != null)
			ivAlmacen = new Integer(request.getParameter("SLSAlmacen"))
					.intValue();
		if (request.getParameter("SLSTipoArticulo") != null)
			ivTipoArticulo = new Integer(
					request.getParameter("SLSTipoArticulo")).intValue();
		if (request.getParameter("SLSFamilia") != null)
			ivFamilia = new Integer(request.getParameter("SLSFamilia")
					.toString()).intValue();
		if (request.getParameter("SLSTipoMov") != null)
			ivTipoMovim = new Integer(request.getParameter("SLSTipoMov")
					.toString()).intValue();
		if (request.getParameter("SLSConceptoMov") != null)
			ivConceptoMov = new Integer(request.getParameter("SLSConceptoMov")
					.toString()).intValue();
		if (UpdStatus.compareToIgnoreCase("UpdateComplete") == 0)
			UpdStatus = "AddOnly";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		// Llena Vector de almacenes.
		try {
			vAlmacenes = DALMAlmacen
					.FindByCustomWhere(" WHERE ALMAlmacen.iCveUniMed = "
							+ ivUniMed + "   AND ALMAlmacen.lActivo = 1 "
							+ " ORDER BY ALMAlmacen.cDscAlmacen ");

		} catch (Exception ex) {
			warn("Error al recuperar almacenes.", ex);
		}
		// Vector de los Tipos de Art�culos.
		try {
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" join ALMArea "
							+ " on ALMArea.iCveAlmacen      = "
							+ ivAlmacen
							+ " and ALMArea.iCveTpoArticulo   = ALMTpoArticulo.iCveTpoArticulo "
							+ " where ALMTpoArticulo.lActivo = 1 "
							+ " ORDER BY ALMTpoArticulo.iIDPartida ");
		} catch (Exception ex) {
			warn("Error al recuperar tipos de art�culo.", ex);
		}
		// Vector de las Familias de Art�culos.
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
							+ " ORDER BY ALMFamilia.cDscBreve ");
		} catch (Exception ex) {
			warn("Error al recuperar familias.", ex);
		}
		// Llena Vector de tipos de movimiento.
		try {
			vTpoMovimiento = DALMTpoMov.FindByAll(
					" WHERE ALMTpoMovimiento.lActivo = 1",
					" ORDER BY ALMTpoMovimiento.cDscBreve ");

		} catch (Exception ex) {
			warn("Error al recuperar tipos de movimiento.", ex);
		}
		// Llena Vector de conceptos por tipo de movimiento.
		try {
			vConcepto = DALMConcepto
					.FindByCustomWhere(" WHERE ALMConcepto.iCveTpoMovimiento = "
							+ ivTipoMovim
							+ " AND ALMConcepto.lActivo = 1 "
							+ " ORDER BY ALMConcepto.cDscConcepto ");

		} catch (Exception ex) {
			warn("Error al recuperar conceptos por tipo de movimiento.", ex);
		}
		// Llena vector de articulos y lotes del art�culo en caso de manejar
		// lotes.
		this.llenaArticuloLote();
		if (request.getParameter("hdBoton") != null
				&& request.getParameter("hdBoton").equalsIgnoreCase("Guardar")) {
			this.creaAjuste();
			this.llenaArticuloLote();
		}
	}

	private void llenaArticuloLote() {
		vArticuloLote = new Vector();
		vArticulos = new Vector();
		// Llena vector de art�culos
		try {
			String cClausulaWhere = " join ALMArtxAlm on ALMArtxAlm.iCveAlmacen = "
					+ ivAlmacen
					+ " AND ALMArtxAlm.iCveArticulo = ALMArticulo.iCveArticulo "
					+ " where ALMArticulo.iCveTpoArticulo = " + ivTipoArticulo;
			if (ivFamilia > 0)
				cClausulaWhere += "   and ALMArticulo.iCveFamilia = "
						+ ivFamilia;
			vArticulos = DALMArticulo.FindByAll(cClausulaWhere,
					" ORDER BY ALMArticulo.cDscBreve ");
		} catch (Exception ex) {
			warn("Error al recuperar art�culos.", ex);
		}
		// Llena vector de articulos y lotes del art�culo en caso de manejar
		// lotes.
		try {
			if (vArticulos == null)
				vArticulos = new Vector();
			Vector vTempArticLote;
			for (int i = 0; i < vArticulos.size(); i++) {
				VArticulo = (TVALMArticulo) vArticulos.get(i);
				vTempArticLote = new Vector();
				vTempArticLote.insertElementAt(VArticulo, 0);
				vTempArticLote.insertElementAt(new Vector(), 1);
				if (VArticulo.getlLote() == 1) {
					vLotes = DALMLote
							.FindByCustomWhere(" join ALMArtxAlm "
									+ " on   ALMartxAlm.iCveAlmacen  = ALMLote.iCveAlmacen "
									+ " and  ALMArtxAlm.iCveArticulo = ALMLote.iCveArticulo "
									+ " where ALMLote.iCveAlmacen =  "
									+ ivAlmacen
									+ "   and ALMLote.iCveArticulo = "
									+ VArticulo.getiCveArticulo()
									+ "   and ALMLote.dUnidades > 0 "
									+ "   and ALMLote.dtAgotado IS NULL "
									+ " ORDER BY ALMLote.dtCaducidad ");
					vTempArticLote.setElementAt(vLotes, 1);
				} else {
					TVALMArtxAlm VArtxAlm;
					Vector vArtxAlm = DALMArtxAlm
							.FindByCustomWhere(" join ALMArticulo on ALMArticulo.iCveArticulo = ALMArtxAlm.iCveArticulo "
									+ " WHERE ALMArtxAlm.iCveAlmacen = "
									+ ivAlmacen
									+ " AND ALMArtxAlm.iCveArticulo = "
									+ VArticulo.getiCveArticulo());
					if (vArtxAlm != null && vArtxAlm.size() > 0) {
						VArtxAlm = (TVALMArtxAlm) vArtxAlm.get(0);
						VArticulo.setdUnidades(VArtxAlm.getdExistencia());
						vTempArticLote.setElementAt(VArticulo, 0);
					}
				}
				vArticuloLote.add(vTempArticLote);
			}
		} catch (Exception ex) {
			warn("Error al obtener los lotes.", ex);
		}
	}

	private void creaAjuste() {
		String cNombreCant, cCantidad, cNombreObs, cObserva;
		int iCantidad;
		boolean lError = false, lResp = true;
		Vector vTemp;
		try {
			if (vArticuloLote != null && vArticuloLote.size() > 0) {
				if (ivTipoMovim > 0) {
					vTemp = DALMTpoMov.FindByAll(" WHERE iCveTpoMovimiento = "
							+ ivTipoMovim, "");
					if (vTemp != null && vTemp.size() > 0)
						if (((TVALMTpoMovimiento) vTemp.get(0)).getLSuma() == 0)
							lSuma = true;
						else
							lSuma = false;
				}
				for (int x = 0; x < vArticuloLote.size(); x++) {
					VArticulo = (TVALMArticulo) ((Vector) vArticuloLote.get(x))
							.get(0);
					vLotes = (Vector) ((Vector) vArticuloLote.get(x)).get(1);
					if (vLotes != null && vLotes.size() > 0) {
						for (int i = 0; i < vLotes.size(); i++) {
							VLote = (TVALMLote) vLotes.get(i);
							if (VLote != null) {
								cCantidad = "0";
								cObserva = "";
								cNombreCant = "iCantidad_"
										+ VArticulo.getiCveArticulo() + "_";
								cNombreCant += VArticulo.getlLote() == 1 ? VLote
										.getiCveLote() + ""
										: "0";
								if (request.getParameter(cNombreCant) != null)
									if (request.getParameter(cNombreCant) != "")
										cCantidad = request
												.getParameter(cNombreCant);
								cNombreObs = "cObserva_"
										+ VArticulo.getiCveArticulo() + "_";
								cNombreObs += VArticulo.getlLote() == 1 ? VLote
										.getiCveLote() + "" : "0";
								if (request.getParameter(cNombreObs) != null)
									if (request.getParameter(cNombreObs) != "")
										cObserva = request
												.getParameter(cNombreObs);
								try {
									iCantidad = Integer.parseInt(cCantidad, 10);
								} catch (Exception ex) {
									iCantidad = 0;
								}
								lResp = true;
								if (iCantidad > 0)
									lResp = generaAjuste(
											VArticulo.getiCveArticulo(),
											VLote.getiCveLote(), iCantidad,
											cObserva);
								if (!lResp)
									lError = true;
							}
						}
					} else {
						cCantidad = "0";
						cObserva = "";
						cNombreCant = "iCantidad_"
								+ VArticulo.getiCveArticulo() + "_0";
						if (request.getParameter(cNombreCant) != null)
							if (request.getParameter(cNombreCant) != "")
								cCantidad = request.getParameter(cNombreCant);
						cNombreObs = "cObserva_" + VArticulo.getiCveArticulo()
								+ "_0";
						if (request.getParameter(cNombreObs) != null)
							if (request.getParameter(cNombreObs) != "")
								cObserva = request.getParameter(cNombreObs);
						try {
							iCantidad = Integer.parseInt(cCantidad, 10);
						} catch (Exception ex) {
							iCantidad = 0;
						}
						lResp = true;
						if (iCantidad > 0)
							lResp = generaAjuste(VArticulo.getiCveArticulo(),
									0, iCantidad, cObserva);
						if (!lResp)
							lError = true;
					}
				}
			} else
				vErrores.acumulaError(
						"No esiten art�culos a los cuales realizar un ajuste.",
						0, "");
			if (lError)
				vErrores.acumulaError(
						"Error al insertar registros de ajuste al inventario o actualizar existencias.",
						0, "");
		} catch (Exception ex) {
			warn("Error al crear Ajustes al Inventario", ex);
		}
	}

	private boolean generaAjuste(int iArticulo, int iLote, int iCantidadMov,
			String cObserva) {
		boolean lRegresa = true;
		TDALMMovimiento DMovim = new TDALMMovimiento();
		TDALMDetalleMov DDetMov = new TDALMDetalleMov();
		TDALMArtxAlm DArtAlm = new TDALMArtxAlm();
		TDALMLote DLote = new TDALMLote();
		TVALMMovimiento VMovim = new TVALMMovimiento();
		TVALMDetalleMov VDetMov = new TVALMDetalleMov();
		TVALMArtxAlm VArtAlm = new TVALMArtxAlm();
		TVALMLote VLote = new TVALMLote();
		Connection conn = null;
		Vector vTemp;
		int iClaveMov = 0;
		String cTemp = "";
		double dExistenciaGlobal = 0, dExistenciaLote = 0, iCantSumaResta = iCantidadMov;

		try {
			conn = DMovim.getConnection();
			vTemp = DArtAlm
					.FindByCustomWhere(" JOIN ALMArticulo ON ALMArticulo.iCveArticulo = ALMArtxAlm.iCveArticulo "
							+ " WHERE ALMArtxAlm.iCveAlmacen = "
							+ ivAlmacen
							+ "   AND ALMArtxAlm.iCveArticulo = " + iArticulo);
			if (vTemp != null && vTemp.size() > 0)
				dExistenciaGlobal = ((TVALMArtxAlm) vTemp.get(0))
						.getdExistencia();
			if (iLote > 0) {
				vTemp = DLote.FindByCustomWhere(" WHERE ALMLote.iCveAlmacen = "
						+ ivAlmacen + "   AND ALMLote.iCveArticulo = "
						+ iArticulo + "   AND ALMLote.iCveLote = " + iLote);
				if (vTemp != null && vTemp.size() > 0)
					dExistenciaLote = ((TVALMLote) vTemp.get(0)).getdUnidades();
			}
			if (!lSuma) {
				if (iLote > 0) {
					if (iCantSumaResta > dExistenciaLote)
						iCantSumaResta = dExistenciaLote;
				}
				if (iCantSumaResta > dExistenciaGlobal)
					iCantSumaResta = dExistenciaGlobal;
				dExistenciaGlobal -= iCantSumaResta;
				if (iLote > 0)
					dExistenciaLote -= iCantSumaResta;
			} else {
				dExistenciaGlobal += iCantSumaResta;
				dExistenciaLote += iCantSumaResta;
			}
			if (iCantSumaResta > 0) {
				vTemp = DMovim
						.FindByCustomWhere(" WHERE ALMMovimiento.iAnio = "
								+ ivAnio
								+ "   AND ALMMovimiento.iCveAlmacen = "
								+ ivAlmacen
								+ " ORDER BY ALMMovimiento.iAnio, ALMMovimiento.iCveAlmacen, "
								+ "          ALMMovimiento.iCveMovimiento DESC ");
				if (vTemp != null && vTemp.size() > 0)
					iClaveMov = ((TVALMMovimiento) vTemp.get(0))
							.getiCveMovimiento();
				iClaveMov++;
				VMovim.setiAnio(ivAnio);
				VMovim.setiCveAlmacen(ivAlmacen);
				VMovim.setiCveMovimiento(iClaveMov);
				VMovim.setiCveSolicSum(0);
				VMovim.setiCveArticulo(iArticulo);
				VMovim.setiCveTpoMovimiento(ivTipoMovim);
				VMovim.setiCveConcepto(ivConceptoMov);
				VMovim.setdUnidades((double) iCantSumaResta);
				VMovim.setiCveUsuario(ivCveUsuario);
				VMovim.setdtMovimiento(tFecha.TodaySQL());
				VMovim.setcObservacion(cObserva);
				VMovim.setlPNC(0);
				cTemp = (String) DMovim.insert(conn, VMovim);
				if (cTemp.equals(""))
					lRegresa = false;
				else {
					if (iLote > 0) {
						VDetMov.setiAnio(ivAnio);
						VDetMov.setiCveAlmacen(ivAlmacen);
						VDetMov.setiCveMovimiento(iClaveMov);
						VDetMov.setiCveLote(iLote);
						VDetMov.setdUnidades((double) iCantSumaResta);
						cTemp = (String) DDetMov.insert(conn, VDetMov);
						if (cTemp.equals(""))
							lRegresa = false;
						else {
							VLote.setiCveAlmacen(ivAlmacen);
							VLote.setiCveArticulo(iArticulo);
							VLote.setiCveLote(iLote);
							VLote.setdUnidades(dExistenciaLote);
							if (dExistenciaLote == 0)
								VLote.setdtAgotado(tFecha.TodaySQL());
							cTemp = (String) DLote.updExistencias(conn, VLote);
							if (cTemp.equals(""))
								lRegresa = false;
						}
					}
					VArtAlm.setiCveAlmacen(ivAlmacen);
					VArtAlm.setiCveArticulo(iArticulo);
					VArtAlm.setdExistencia(dExistenciaGlobal);
					cTemp = (String) DArtAlm.updExistencias(conn, VArtAlm);
					if (cTemp.equals(""))
						lRegresa = false;
				}
			}
			if (!lRegresa)
				conn.rollback();
		} catch (Exception ex) {
			lRegresa = false;
			try {
				if (conn != null)
					conn.rollback();
			} catch (Exception ex1) {
				warn("generaAjuste.rollBack", ex1);
			}
		} finally {
			try {
				if (!lRegresa)
					if (conn != null)
						conn.rollback();
				if (conn != null)
					if (!conn.isClosed())
						DMovim.closeConnection();
			} catch (Exception ex2) {
				warn("generaAjuste.close", ex2);
			}
		}
		return lRegresa;
	}
}
