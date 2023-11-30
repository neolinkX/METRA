package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import java.text.*;

/**
 * * Clase de configuración para Clase para el control de la tabla de
 * ALMArticulo
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hernández García
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
public class pg070802080CFG extends CFGCatBase2 {
	TFechas tFecha = new TFechas("07");
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vArticulos = new Vector();
	public Vector vLotes = new Vector();
	public Vector vExistencias = new Vector();
	public int ivAnio = 0, ivUniMed = 0, ivAlmacen = 0, ivTipoArticulo = 0,
			ivFamilia = 0, ivArticulo = 0, ivCantidad = 0, ivOpcion = 0;
	public String cNombreTpo = "", cNombreFam = "", cNombreArt = "";
	public double dExistencias = (float) 0.0;

	public pg070802080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {
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
		if (request.getParameter("SLSArticulo") != null)
			ivArticulo = new Integer(request.getParameter("SLSArticulo")
					.toString()).intValue();
		if (request.getParameter("RSTOpcion") != null)
			ivOpcion = new Integer(request.getParameter("RSTOpcion").toString())
					.intValue();
		if (request.getParameter("iCantidad") != null)
			ivCantidad = new Integer(request.getParameter("iCantidad")
					.toString()).intValue();
		if (UpdStatus.compareToIgnoreCase("UpdateComplete") == 0)
			UpdStatus = "AddOnly";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
		TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
		TDALMFamilia DALMFamilia = new TDALMFamilia();
		TDALMArticulo DALMArticulo = new TDALMArticulo();
		TDALMLote DALMLote = new TDALMLote();
		TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();

		// Llena Vector de almacenes.
		try {
			vAlmacenes = DALMAlmacen.FindByCustomWhere(" where lActivo = 1");
		} catch (Exception ex) {
			warn("Error al recuperar almacenes.", ex);
		}
		// Vector de los Tipos de Artículos.
		try {
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" WHERE ALMTpoArticulo.iCveTpoArticulo = "
							+ ivTipoArticulo);
			if (vTpoArticulos != null && vTpoArticulos.size() > 0)
				cNombreTpo = ((TVALMTpoArticulo) vTpoArticulos.get(0))
						.getCDscBreve();
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" join ALMArea "
							+ " on ALMArea.iCveTpoArticulo   = ALMTpoArticulo.iCveTpoArticulo "
							+ " and ALMArea.iCveAlmacen      = "
							+ ivAlmacen
							+ " where ALMTpoArticulo.lActivo = 1 ORDER BY ALMTpoArticulo.iIDPartida ");
		} catch (Exception ex) {
			warn("Error al recuperar tipos de artículo.", ex);
		}
		// Vector de las Familias de Artículos.
		try {
			vFamilias = DALMFamilia
					.FindByCustomWhere(" WHERE ALMFamilia.iCveTpoArticulo = "
							+ ivTipoArticulo + " AND ALMFamilia.iCveFamilia = "
							+ ivFamilia);
			if (vFamilias != null && vFamilias.size() > 0)
				cNombreFam = ((TVALMFamilia) vFamilias.get(0)).getcDscFamilia();
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
			warn("Error al recuperar familias.", ex);
		}
		try {
			vArticulos = DALMArticulo
					.FindByCustomWhere(" WHERE ALMArticulo.iCveArticulo = "
							+ ivArticulo);
			if (vArticulos != null && vArticulos.size() > 0)
				cNombreArt = ((TVALMArticulo) vArticulos.get(0)).getcDscBreve();
			vArticulos = DALMArticulo.FindByCustomWhere(" join ALMArtxAlm "
					+ " on   ALMArtxAlm.iCveAlmacen = " + ivAlmacen
					+ " where ALMArticulo.iCveTpoArticulo = " + ivTipoArticulo
					+ "   and ALMArticulo.iCveFamilia     = " + ivFamilia
					+ " ORDER BY ALMArticulo.cDscBreve ");
		} catch (Exception ex) {
			warn("Error al recuperar artículos.", ex);
		}
		try {
			vLotes = DALMLote.FindByCustomWhere(" join ALMArtxAlm "
					+ " on   ALMartxAlm.iCveAlmacen  = ALMLote.iCveAlmacen "
					+ " and  ALMArtxAlm.iCveArticulo = ALMLote.iCveArticulo "
					+ " where ALMLote.iCveAlmacen =  " + ivAlmacen
					+ "   and ALMLote.iCveArticulo = " + ivArticulo
					+ "   and ALMLote.dUnidades > 0 "
					+ "   and ALMLote.dtAgotado IS NULL "
					+ "   and ALMLote.dtCaducidad >= '"
					+ tFecha.getFechaYYYYMMDD(tFecha.TodaySQL(), "-") + "'"
					+ " ORDER BY ALMLote.dtCaducidad ");
		} catch (Exception ex) {
			warn("Error al obtener los lotes.", ex);
		}
		try {
			vExistencias = DALMArtxAlm
					.FindByCustomWhere(" JOIN ALMArticulo ON ALMArticulo.iCveArticulo = ALMArtxAlm.iCveArticulo "
							+ " WHERE ALMArtxAlm.iCveAlmacen = "
							+ ivAlmacen
							+ " AND ALMArtxAlm.iCveArticulo = " + ivArticulo);
			if (vExistencias != null && vExistencias.size() > 0)
				dExistencias = (float) ((TVALMArtxAlm) vExistencias.get(0))
						.getdExistencia();
		} catch (Exception ex) {
			warn("Error al obtener existencias.", ex);
		}
		if (request.getParameter("hdBoton") != null
				&& request.getParameter("hdBoton").equalsIgnoreCase("Guardar")) {
			switch (ivOpcion) {
			case 1:
				if (ivAnio > 0 && ivUniMed > 0 && ivCantidad > 0)
					this.creaFFCCC();
				else
					vErrores.acumulaError(
							"Debe proporcionar el año, la unidad médica y la cantidad a generar.",
							0, "");
				break;
			case 2:
				if (ivAnio > 0 && ivUniMed > 0 && ivCantidad > 0
						&& ivAlmacen > 0 && ivTipoArticulo > 0 && ivFamilia > 0
						&& ivArticulo > 0)
					this.creaPbaRapida();
				else
					vErrores.acumulaError(
							"Debe proporcionar el año, la unidad médica y la cantidad a generar.",
							0, "");
				break;
			default:
				vErrores.acumulaError(
						"No eligió una opción válida, por favor elija una opción",
						0, "");
				break;
			}
		}
	}

	private void creaFFCCC() {
		TDMuestra DMuestra = new TDMuestra();
		try {
			if (!DMuestra.insertFromALM(ivAnio, ivUniMed, ivCantidad))
				vErrores.acumulaError(
						"Error al insertar los registros de FFCCC", 0, "");
			else
				vErrores.acumulaError("Se insertaron (" + ivCantidad
						+ ") registros de FFCCC", 0, "");
		} catch (Exception ex) {
			warn("Error al crear registros de FFCCC", ex);
		}
	}

	private void creaPbaRapida() {
		TDPbaRapida DPbaRapida = new TDPbaRapida();
		try {
			if (!DPbaRapida.insertFromALM(ivAnio, ivUniMed, ivCantidad))
				vErrores.acumulaError(
						"Error al crear registros de Prueba Rápida", 0, "");
			else
				vErrores.acumulaError("Se insertaron (" + ivCantidad
						+ ") registros de pruebas rápidas", 0, "");
		} catch (Exception ex) {
			warn("Error al crear registros de Prueba Rápida", ex);
		}
	}
}
