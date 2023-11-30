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
 * * Clase de configuracion para Clase para el control de la consulta de
 * m�ximos y m�nimos.
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>LSC Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803040CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803040CFG.java.png'>
 */
public class pg070803040CFG extends CFGListBase2 {
	TFechas tFecha = new TFechas("07");
	public Vector vAlmacenes = new Vector();
	public Vector vTpoArticulos = new Vector();
	public Vector vFamilias = new Vector();
	public Vector vArticuloExistencia = new Vector();
	private int ivCveUsuario = 0;
	public int ivUniMed = 0, ivAlmacen = 0, ivTipoArticulo = 0, ivFamilia = 0,
			ivExcedente = 0, ivDebajo = 0, ivCerca = 0;
	private TDALMAlmacen DALMAlmacen = new TDALMAlmacen();
	private TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
	private TDALMFamilia DALMFamilia = new TDALMFamilia();
	private TDALMArticulo DALMArticulo = new TDALMArticulo();
	private TDALMLote DALMLote = new TDALMLote();
	private TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
	private TVALMArticulo VArticulo;
	private TVALMLote VLote;
	private boolean lSuma = true;

	public pg070803040CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {
		TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true).getAttribute(
				"UsrID");
		if (vUsuario != null)
			ivCveUsuario = vUsuario.getICveusuario();
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
		if (request.getParameter("FILExcede") != null
				&& !request.getParameter("FILExcede").equals(""))
			ivExcedente = new Integer(request.getParameter("FILExcede")
					.toString()).intValue();
		if (request.getParameter("FILDebajo") != null
				&& !request.getParameter("FILDebajo").equals(""))
			ivDebajo = new Integer(request.getParameter("FILDebajo").toString())
					.intValue();
		if (request.getParameter("FILCerca") != null
				&& !request.getParameter("FILCerca").equals(""))
			ivCerca = new Integer(request.getParameter("FILCerca").toString())
					.intValue();
		UpdStatus = "Hidden";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		// Llena Vector de almacenes.
		try {
			vAlmacenes = DALMAlmacen
					.FindByCustomWhere(" WHERE ALMAlmacen.lActivo = 1 "
							+ "   AND ALMAlmacen.iCveUniMed = " + ivUniMed
							+ " ORDER BY ALMAlmacen.cDscAlmacen ");

		} catch (Exception ex) {
			warn("Error al recuperar almacenes.", ex);
		}
		// Vector de los Tipos de Art�culos.
		try {
			vTpoArticulos = DALMTpoArticulo
					.FindByCustomWhere(" join ALMArea "
							+ " on ALMArea.iCveTpoArticulo   = ALMTpoArticulo.iCveTpoArticulo "
							+ " and ALMArea.iCveAlmacen      = "
							+ ivAlmacen
							+ " where ALMTpoArticulo.lActivo = 1 ORDER BY ALMTpoArticulo.iIDPartida ");
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
		// Llena vector de articulos y lotes del art�culo en caso de manejar
		// lotes.
		this.llenaArticuloExistencia();
	}

	private void llenaArticuloExistencia() {
		vArticuloExistencia = new Vector();
		// Llena vector de art�culos
		try {
			String cClausulaWhere = "";
			String cOrden = "";
			if (ivAlmacen > 0)
				cClausulaWhere += " left join ALMArtxAlm on ALMArtxAlm.iCveAlmacen = "
						+ ivAlmacen
						+ "      AND ALMArtxAlm.iCveArticulo = ALMArticulo.iCveArticulo ";
			else
				cClausulaWhere += " left join ALMArtxAlm ON ALMArtxAlm.iCveArticulo = ALMArticulo.iCveArticulo ";
			cClausulaWhere += " left join ALMAlmacen ON ALMAlmacen.iCveAlmacen = ALMArtxAlm.iCveAlmacen ";
			if (ivUniMed > 0)
				cClausulaWhere += "      AND ALMAlmacen.iCveUniMed = "
						+ ivUniMed;
			cClausulaWhere += " left join GRLUniMed on GRLUniMed.iCveUniMed = ALMAlmacen.iCveUniMed ";
			cClausulaWhere += " WHERE 1=1 ";
			if (ivTipoArticulo > 0)
				cClausulaWhere += " AND ALMArticulo.iCveTpoArticulo = "
						+ ivTipoArticulo;
			if (ivFamilia > 0)
				cClausulaWhere += " AND ALMArticulo.iCveFamilia = " + ivFamilia;
			cOrden = " ORDER BY GRLUniMed.cDscUniMed, ALMAlmacen.cDscAlmacen, ALMTpoArticulo.cDscTpoArticulo";
			cOrden += ", ALMFamilia.cDscFamilia, ALMArticulo.cDscBreve";
			vArticuloExistencia = DALMArticulo.FindByAllWithMinMax(
					cClausulaWhere, cOrden);
			if (vArticuloExistencia != null && vArticuloExistencia.size() > 0)
				vDespliega = vArticuloExistencia;
			else
				vDespliega = new Vector();
		} catch (Exception ex) {
			warn("Error al recuperar art�culos.", ex);
		}
	}
}
