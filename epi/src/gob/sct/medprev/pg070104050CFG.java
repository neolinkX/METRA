package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG FF de Ctrol. y Cadena de Custodia
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco A. Gonz�lez Paz
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104050CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104050CFG.png'>
 */
public class pg070104050CFG extends CFGCatBase2 {
	public pg070104050CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		UpdStatus = "Add";
	}

	public void Nuevo() {
		super.Nuevo();
		UpdStatus = "SaveCancel";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDMuestra dMuestra = new TDMuestra();
		try {
			cClave = (String) dMuestra.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			if (cClave.compareTo("ERROR") == 0) {
				vErrores.acumulaError("Registro Existente: ", 0,
						"El Registro ya ha sido asignado anteriormente");
				vErrores.muestraError();
			}
			super.Guardar();
			UpdStatus = "Add";
			NavStatus = "true";
		}
	} // Metodo Guardar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMuestra dMuestra = new TDMuestra();
		TVMuestra vMuestra = new TVMuestra();
		String cCadena = "";
		int iAnio = 0;
		int iUniMed = 0;
		try {
			cCadena = "" + request.getParameter("hdBoton");
			if (request.getParameter("iAnioB") != null) {
				iAnio = Integer.parseInt(request.getParameter("iAnioB"), 10);
				vMuestra.setIAnio(iAnio);
			} else {
				TFechas dtFecha = new TFechas();
				iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
				vMuestra.setIAnio(iAnio);
			}

			if (request.getParameter("iCveUniMedB") != null) {
				iUniMed = Integer.parseInt(request.getParameter("iCveUniMedB"),
						10);
				vMuestra.setICveUniMed(iUniMed);
			}

			if (cCadena.compareTo("Guardar") == 0) {
				vDespliega = dMuestra.FindByDsc(this.getInputs(), "true");
			} else {
				vDespliega = dMuestra.FindByDsc(vMuestra, cCondicion);
			}

			// vDespliega = dMuestra.FindByDsc(null, "false");
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
		mPk.add("iCveMuestra");
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
		TVMuestra vMuestra = new TVMuestra();
		try {
			cCampo = "" + request.getParameter("hdAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveMuestra");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveMuestra(iCampo);

			cCampo = "" + request.getParameter("hdOPPbaRapida");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICvePbaRapida(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("iCveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("iCveMotivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveMotivo(iCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("iCveEmpresa");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveEmpresa(iCampo);

			/*
			 * cCampo = "" + request.getParameter("dtCaptura"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * dtCampo = tfCampo.getDateSQL(cCampo); } else { dtCampo = null; }
			 */
			TFechas fFechas = new TFechas();
			dtCampo = fFechas.TodaySQL();
			vMuestra.setDtCaptura(dtCampo);

			cCampo = "" + request.getParameter("dtRecoleccion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vMuestra.setDtRecoleccion(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuRecolec");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveUsuRecolec(iCampo);

			cCampo = "" + request.getParameter("iCveSituacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vMuestra.setICveSituacion(iCampo);

			cCampo = "" + request.getParameter("lTemperaC");
			if (cCampo.compareTo("ON") == 0) {
				iCampo = 1;
			} else {
				iCampo = 0;
			}
			vMuestra.setLTemperaC(iCampo);

			cCampo = "" + request.getParameter("lAlteracion");
			if (cCampo.compareTo("ON") == 0) {
				iCampo = 1;
			} else {
				iCampo = 0;
			}
			vMuestra.setLAlteracion(iCampo);

			cCampo = "" + request.getParameter("lBajoObserva");
			if (cCampo.compareTo("ON") == 0) {
				iCampo = 1;
			} else {
				iCampo = 0;
			}
			vMuestra.setLBajoObserva(iCampo);

			cCampo = "" + request.getParameter("cObsMuestra");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMuestra.setCObsMuestra(cCampo);

			cCampo = "" + request.getParameter("iCveModTrans");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vMuestra.setICveMdoTrans(new Integer(cCampo).intValue());

			cCampo = "" + request.getParameter("iCveUsuCaptura");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setICveUsuCaptura(iCampo);

			cCampo = "" + request.getParameter("iEdad");
			// System.out.println("Edad: " + cCampo);
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vMuestra.setIEdad(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vMuestra;
	}

}