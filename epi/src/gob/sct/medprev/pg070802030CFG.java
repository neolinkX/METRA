package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;
import com.micper.seguridad.vo.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070802030
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>LCI. Oscar Castrej�n Adame.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070802030CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070802030CFG.png'>
 */
public class pg070802030CFG extends CFGCatBase2 {
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vArticulos = new Vector();
	public Vector vConceptos = new Vector();
	int ivAlmacen = 0;
	int ivTipoArticulo = 0;
	int ivFamilia = 0;

	public pg070802030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070802030.jsp|";
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

		if (UpdStatus.compareToIgnoreCase("UpdateComplete") == 0)
			UpdStatus = "AddOnly";

	}

	public void Nuevo() {
		super.Nuevo();
		UpdStatus = "SaveCancel";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDALMMovimiento DALMMovimiento = new TDALMMovimiento();
		TVALMMovimiento VALMMovimiento = new TVALMMovimiento();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
		TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
		TVALMArtxAlm VALMArtxAlmActual = new TVALMArtxAlm();
		TDALMDetalleMov DALMDetalleMov = new TDALMDetalleMov();
		TVALMDetalleMov VALMDetalleMov = new TVALMDetalleMov();
		TDALMLote DALMLote = new TDALMLote();
		TVALMLote VALMLote = new TVALMLote();
		Vector vALMArtxAlm = new Vector();

		TFechas Fecha = new TFechas();
		int ivMovimiento = 0;
		int ivArticulo = 0;
		int ivConcepto = 0;
		double dvUnidades = 0;
		double dvExistencias = 0;
		String cvObservacion = "";
		int ivlPNC = 0;
		int ivLote = 0;
		String cvCaducidad = "";
		String cvLote = "";

		if (request.getParameter("SLSArticulo") != null)
			ivArticulo = new Integer(request.getParameter("SLSArticulo"))
					.intValue();

		if (request.getParameter("SLSConcepto") != null)
			ivConcepto = new Integer(request.getParameter("SLSConcepto"))
					.intValue();

		if (request.getParameter("FILUnidades") != null)
			dvUnidades = new Double(request.getParameter("FILUnidades"))
					.doubleValue();

		if (request.getParameter("TXTObservacion") != null)
			cvObservacion = request.getParameter("TXTObservacion").toString();

		if (request.getParameter("TBXlPNC") != null)
			ivlPNC = new Integer(request.getParameter("TBXlPNC")).intValue();

		if (request.getParameter("dtCaducidad") != null)
			cvCaducidad = request.getParameter("dtCaducidad");

		if (request.getParameter("FILLote") != null)
			cvLote = request.getParameter("FILLote").toString();

		try {
			Vector vALMMovimiento = new Vector();
			vALMMovimiento = DALMMovimiento
					.FindByCustomWhere(" where iAnio       = "
							+ Fecha.getIntYear(Fecha.TodaySQL())
							+ "   and iCveAlmacen = "
							+ ivAlmacen
							+ "  ORDER BY iAnio, iCveAlmacen, iCveMovimiento DESC ");

			if (!vALMMovimiento.isEmpty())
				ivMovimiento = ((TVALMMovimiento) vALMMovimiento.get(0))
						.getiCveMovimiento() + 1;
			else
				ivMovimiento = 1;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			Vector vALMLote = new Vector();
			vALMLote = DALMLote.FindByCustomWhere(" where iCveAlmacen  = "
					+ ivAlmacen + "   and iCveArticulo = " + ivArticulo
					+ " ORDER BY iCveAlmacen, iCveArticulo, iCveLote DESC ");
			if (!vALMLote.isEmpty())
				ivLote = ((TVALMLote) vALMLote.get(0)).getiCveLote() + 1;
			else
				ivLote = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vALMArtxAlm = DALMArtxAlm.FindByAll(" where iCveAlmacen  = "
					+ ivAlmacen + "   and iCveArticulo = " + ivArticulo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vALMArtxAlm.isEmpty()) {
			VALMArtxAlmActual = (TVALMArtxAlm) vALMArtxAlm.get(0);
			dvExistencias = VALMArtxAlmActual.getdExistencia() + dvUnidades;
		}

		VALMMovimiento.setiAnio(Fecha.getIntYear(Fecha.TodaySQL()));
		VALMMovimiento.setiCveAlmacen(ivAlmacen);
		VALMMovimiento.setiCveMovimiento(ivMovimiento);
		VALMMovimiento.setiCveArticulo(ivArticulo);
		VALMMovimiento.setiCveTpoMovimiento(1); // Entrada de Almacen.
		VALMMovimiento.setiCveConcepto(ivConcepto);
		VALMMovimiento.setdUnidades(dvUnidades);
		TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
				.getAttribute("UsrID");
		VALMMovimiento.setiCveUsuario(vUsuario.getICveusuario()); // Usuario por
																	// Deafult.
		VALMMovimiento.setdtMovimiento(Fecha.TodaySQL());
		VALMMovimiento.setcObservacion(cvObservacion);
		VALMMovimiento.setlPNC(ivlPNC);

		if (cvLote.compareToIgnoreCase("") != 0) {

			VALMDetalleMov.setiAnio(Fecha.getIntYear(Fecha.TodaySQL()));
			VALMDetalleMov.setiCveAlmacen(ivAlmacen);
			VALMDetalleMov.setiCveMovimiento(ivMovimiento);
			VALMDetalleMov.setiCveLote(ivLote);
			VALMDetalleMov.setdUnidades(dvUnidades);

			VALMLote.setiCveAlmacen(ivAlmacen);
			VALMLote.setiCveArticulo(ivArticulo);
			VALMLote.setiCveLote(ivLote);
			VALMLote.setdUnidades(dvUnidades);
			// TVUsuario vUsuario = (TVUsuario)
			// this.httpReq.getSession(true).getAttribute("UsrID");
			VALMLote.setiCveUsuario(vUsuario.getICveusuario());
			VALMLote.setdtIngreso(Fecha.TodaySQL());
			VALMLote.setdtCaducidad(Fecha.getDateSQL(cvCaducidad));
			VALMLote.setiCveTpoMovimiento(1);
			VALMLote.setiCveConcepto(ivConcepto);
			VALMLote.setcObservacion(cvObservacion);
			VALMLote.setcLote(cvLote);
		}

		try {
			cClave = (String) DALMMovimiento.insert(null, VALMMovimiento);
		} catch (Exception ex) {
			ex.printStackTrace();
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		}

		VALMArtxAlm.setiCveAlmacen(ivAlmacen);
		VALMArtxAlm.setiCveArticulo(ivArticulo);
		VALMArtxAlm.setdExistencia(dvExistencias);

		// Actualizaci�n de las Existencias.
		try {
			DALMArtxAlm.updExistencias(null, VALMArtxAlm);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cvLote.compareToIgnoreCase("") != 0) {
			// Inserci�n del Relaci�n Movimiento con Lote del Art�culo.
			try {
				DALMDetalleMov.insert(null, VALMDetalleMov);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Inserci�n del Lote del Articulo.
			try {
				DALMLote.insert(null, VALMLote);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		super.Guardar();

	} // Metodo Guardar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDALMMovimiento DALMMovimiento = new TDALMMovimiento();
		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
		TDALMFamilia DALMFamilia = new TDALMFamilia();
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMConcepto DALMConcepto = new TDALMConcepto();
		TDALMLote DALMLote = new TDALMLote();

		// Vector de los Almacenes.
		try {
			vAlmacenes = DALMAlmacen.FindByCustomWhere(" where lActivo = 1");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Tipos de Art�culos.
		try {
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" join ALMArea "
							+ " on ALMArea.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo "
							+ " and ALMArea.iCveAlmacen    = " + ivAlmacen
							+ " where ALMTpoArticulo.lActivo = 1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de las Familias de Art�culos.
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

		// Vector de los Art�culos.
		try {
			vArticulos = DALMArticulo
					.FindByCustomWhere(" inner join ALMArtxAlm "
							+ " on   ALMArtxAlm.iCveAlmacen = "
							+ ivAlmacen
							+ " and  ALMArtxAlm.iCveArticulo = ALMArticulo.iCveArticulo "
							+ " where ALMArticulo.iCveTpoArticulo = "
							+ ivTipoArticulo
							+ "   and ALMArticulo.iCveFamilia     = "
							+ ivFamilia + "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector de los Conceptos.
		try {
			vConceptos = DALMConcepto
					.FindByCustomWhere(" join ALMTpoMovimiento "
							+ " on   ALMTpoMovimiento.iCveTpoMovimiento = ALMConcepto.iCveTpoMovimiento "
							+ " and  ALMTpoMovimiento.lActivo           = 1 "
							+ " where ALMConcepto.iCveTpoMovimiento     = 1 "
							+ "   and ALMConcepto.lActivo               = 1 "
							+ "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			String cWhere = "";
			String cOrderBy = "";

			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0) {
				if (cOrden == "Order by iCveMovimiento")
					cOrderBy = " Order by ALMMovimiento.iAnio, ALMMovimiento.iCveAlmacen, ALMMovimiento.iCveMovimiento";
				if (cOrden == "Order by cDscArticulo")
					cOrderBy = " Order by ALMMovimiento.iAnio, ALMMovimiento.iCveAlmacen, ALMArticulo.cDscArticulo ";
			}

			vDespliega = DALMMovimiento
					.FindByCustomWhere(" join ALMArticulo "
							+ "    on ALMArticulo.iCveArticulo = ALMMovimiento.iCveArticulo "
							+ "   and ALMArticulo.iCveTpoArticulo = "
							+ ivTipoArticulo
							+ "   and ALMArticulo.iCveFamilia     = "
							+ ivFamilia + " where iCveAlmacen = " + ivAlmacen
							+ "   and iCveTpoMovimiento = 1 " + cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty()) {
			// UpdStatus = "SaveCancelOnly";
			// cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0) {
				if (cAccion.compareToIgnoreCase("Nuevo") != 0)
					NavStatus = "FirstRecord";
			}
		} else {
			// UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

		if (cAccion.compareToIgnoreCase("ReposPK") == 0)
			UpdStatus = "AddOnly";
		if (cAccion.compareToIgnoreCase("Primero") == 0)
			UpdStatus = "AddOnly";

	}

	public Vector getLotes(int ivAnio, int ivAlmacen, int ivMovimiento) {
		Vector vLotes = new Vector();
		TDALMLote DALMLote = new TDALMLote();

		try {
			vLotes = DALMLote
					.FindByCustomWhere(" join ALMDetalleMov "
							+ " on   ALMDetalleMov.iAnio       = "
							+ ivAnio
							+ " and  ALMDetalleMov.iCveAlmacen = ALMLote.iCveAlmacen "
							+ " and  ALMDetalleMov.iCveMovimiento = "
							+ ivMovimiento
							+ " and  ALMDetalleMov.iCveLote    = ALMLote.iCveLote "
							+ " join ALMMovimiento "
							+ " on  ALMMovimiento.iAnio = ALMDetalleMov.iAnio "
							+ " and ALMMovimiento.iCveAlmacen = ALMDetalleMov.iCveAlmacen "
							+ " and ALMMovimiento.iCveMovimiento = ALMDetalleMov.iCveMovimiento "
							+ " and ALMMovimiento.iCveArticulo = ALMLote.iCveArticulo "
							+ " where ALMLote.iCveAlmacen =  " + ivAlmacen);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return vLotes;
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {
		TFechas Fecha = new TFechas();
		mPk.add(new Integer(Fecha.getIntYear(Fecha.TodaySQL())).toString());
		mPk.add(new Integer(ivAlmacen).toString());
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVALMMovimiento VALMMovimiento = new TVALMMovimiento();
		try {
			cCampo = "" + request.getParameter("SLSAlmacen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			VALMMovimiento.setiCveAlmacen(iCampo);

			cCampo = "" + request.getParameter("SLSArticulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			VALMMovimiento.setiCveArticulo(iCampo);

			cCampo = "" + request.getParameter("TXTObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			VALMMovimiento.setcObservacion(cCampo);

			// Movimiento para las Entradas de Almac�n.
			cCampo = "1";
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			VALMMovimiento.setiCveTpoMovimiento(new Integer(cCampo).intValue());

			cCampo = "" + request.getParameter("iCveUsuResp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			// VALMMovimiento.setiCveUsuario(iCampo);

			if (request.getParameter("chklActivo") == null) {
				cCampo = "0";
			} else {
				cCampo = "1";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			// VALMMovimiento..setLActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return VALMMovimiento;
	}
}
