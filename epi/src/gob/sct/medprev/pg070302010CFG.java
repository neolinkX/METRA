package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuración para CFG Proceso Aplicación de Prueba Rápida
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. González Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070302010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070302010CFG.png'>
 */
public class pg070302010CFG extends CFGCatBase2 {

	private Vector vcSustanciaApi = null;
	private int iSiPagina = 1;

	public int getISiPagina() {
		return iSiPagina;
	}

	public pg070302010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		UpdStatus = "Add";
	}

	public void Nuevo() {
		iSiPagina = 1;
		super.Nuevo();
		UpdStatus = "SaveCancel";
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDPbaRapida dPbaRapida = new TDPbaRapida();
		/* DbConnection dbConn = null; */
		try {
			/* dbConn = new DbConnection(dataSourceName); */
			/*
			 * cClave = (String) dPbaRapida.insert(dbConn.getConnection(),
			 * this.getInputs(), vcSustanciaApi);
			 */
			cClave = (String) dPbaRapida.insert(null, this.getInputs(),
					vcSustanciaApi);
			// this.setCIrPagina("1");
			/*
			 * if (cClave.compareTo("ERROR") != 0){
			 * out.print("<script language=\"JavaScript\">");
			 * out.print("fIrPagina();"); out.print("</script>");
			 * this.setCIrPagina("0"); }
			 */
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			if (cClave.compareTo("ERROR") == 0) {
				iSiPagina = 0;
				vErrores.acumulaError("Registro Existente: ", 0,
						"El Registro ya ha sido asignado anteriormente.");
			}
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDPbaRapida dPbaRapida = new TDPbaRapida();
		String cCadena = "";
		try {
			// this.setCIrPagina("0");
			cCadena = "" + request.getParameter("hdBoton");
			if (cCadena.compareTo("PbaRapida") == 0
					|| cCadena.compareTo("Guardar") == 0) {
				vDespliega = dPbaRapida.FindByAll(this.getInputs(), "true");
			} else {
				vDespliega = dPbaRapida.FindByAll(null, "false");
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVPbaRapida vPbaRapida = new TVPbaRapida();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCvePbaRapida");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICvePbaRapida(iCampo);

			cCampo = "" + request.getParameter("dtCaducidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPbaRapida.setDtCaducidad(dtCampo);

			cCampo = "" + request.getParameter("dtAsignacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPbaRapida.setDtAsignacion(dtCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("dtAplicacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPbaRapida.setDtAplicacion(dtCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("iCveUsuAplica");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveUsuAplica(iCampo);

			cCampo = "" + request.getParameter("dtCaptura");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPbaRapida.setDtCaptura(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuCaptura");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveUsuCaptura(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("lPosiblePost");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setLPosiblePost(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("iCveModTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPbaRapida.setICveMdoTrans(iCampo);

			vcSustanciaApi = new Vector();
			Vector vcSustancia = null;
			TDSustancia dSustancia = new TDSustancia();
			vcSustancia = dSustancia.FindByAll("");
			Object obElemento;
			int i;
			int iAcumulado = 0;

			for (i = 0; i < vcSustancia.size(); i++) {
				obElemento = vcSustancia.get(i);
				TVSustancia vSustancia = (TVSustancia) obElemento;
				cCampo = ""
						+ request.getParameter(vSustancia.getCDscSustancia());
				if (cCampo.compareTo("ON") == 0) {
					vcSustanciaApi.addElement(vSustancia);
				}
			}

		} catch (Exception ex) {
			error("getInputs", ex);
		}
		return vPbaRapida;
	}
}


