package gob.sct.medprev;

import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Clase de configuracion para catalogo de
 * registro de accidentes
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070402011CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070402011CFG.png'>
 */
public class pg070402011CFG extends CFGCatBase2 {

	String cIAnio = "";
	String cMdoTrans = "";
	String cIIDDGPMPT = "";

	public pg070402011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070402010.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		try {
			TVINVRegistro vTmp = getInputs();
			String iAnioSel = request.getParameter("iAnioSel");
			String iCveMdoTransSel = request.getParameter("iCveMdoTransSel");
			String iCveMotivo = request.getParameter("iCveMotivo");
			if (iAnioSel != null && iAnioSel.length() != 0
					&& iCveMdoTransSel != null && iCveMdoTransSel.length() != 0
					&& iCveMotivo != null && iCveMotivo.length() != 0) {
				vTmp.setIAnio(Integer.parseInt(iAnioSel));
				vTmp.setICveMdoTrans(Integer.parseInt(iCveMdoTransSel));
				vTmp.setICveMotivo(Integer.parseInt(iCveMotivo));

				cClave = (String) new TDINVRegistro().insert(null, vTmp);

				StringTokenizer stk = new StringTokenizer(cClave, "|");
				cIAnio = stk.nextToken();
				cMdoTrans = stk.nextToken();
				cIIDDGPMPT = stk.nextToken();

			} else {
				vErrores.acumulaError("", 14, "");
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		try {
			cClave = (String) new TDINVRegistro().updateRegistro(null,
					this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	}

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		try {
			cClave = (String) new TDINVRegistro()
					.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {

			String lAction = request.getParameter("hdBoton");

			String cWhere = request.getParameter("hdCCondicion") != null ? request
					.getParameter("hdCCondicion") : "";
			String cOrder = request.getParameter("hdCOrdenar") != null ? request
					.getParameter("hdCOrdenar") : "";

			String cIAnio = request.getParameter("iAnioSel") != null ? request
					.getParameter("iAnioSel") : "0";
			String cICveMdoTrans = request.getParameter("iCveMdoTransSel") != null ? request
					.getParameter("iCveMdoTransSel") : "0";
			// Si le damos modificar o cancelar leemos las varibles que tienen
			// el valor gurdado con anterioridad
			if ("GuardarA".equalsIgnoreCase(cAccionOriginal)
					|| "Cancelar".equalsIgnoreCase(cAccionOriginal)) {
				cIAnio = request.getParameter("iAnio") != null ? request
						.getParameter("iAnio") : "0";
				cICveMdoTrans = request.getParameter("iCveMdoTrans") != null ? request
						.getParameter("iCveMdoTrans") : "0";
			}
			String cICveUniMed = request.getParameter("iCveUniMedSel");
			cICveUniMed = cICveUniMed != null ? cICveUniMed : "0";
			if (cWhere.length() != 0)
				cWhere += " and ";
			cWhere += " a.iAnio=" + cIAnio + " and a.iCveMdoTrans="
					+ cICveMdoTrans + " and a.iCveUniMed=" + cICveUniMed;

			vDespliega = new TDINVRegistro().FindByAll(cWhere, cOrder);

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo getMdoTrans
	 */
	public Vector getMdoTrans() {
		Vector vcTmp = null;
		try {
			vcTmp = new TDGRLMdoTrans().findByAll("", " order by cDscMdoTrans");
		} catch (Exception ex) {
			error("getMdoTrans", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo getPaises
	 */
	public Vector getPaises() {
		Vector vcTmp = null;
		try {
			vcTmp = new TDGRLPais().FindByAll("", " order by cNombre");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo getEntidadFeds
	 */
	public Vector getEntidadFeds(String cCvePais) {
		Vector vcTmp = null;
		try {
			vcTmp = new TDGRLEntidadFed().FindByAll("iCvePais=" + cCvePais,
					" order by cNombre");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo getMunicipios
	 */
	public Vector getMunicipios(String cCvePais, String cCveEntidadFed) {
		Vector vcTmp = null;
		try {
			vcTmp = new TDGRLMunicipio().FindByAll("iCvePais=" + cCvePais
					+ " and iCveEntidadFed=" + cCveEntidadFed,
					" order by cNombre");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo getTpoCausas
	 */
	public Vector getTpoCausas() {
		Vector vcTmp = null;
		try {
			vcTmp = new TDINVTpoCausa().FindByAll("", " order by cDscTpoCausa");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo getCausas
	 */
	public Vector getCausas(String cCveTpoCausa) {
		Vector vcTmp = null;
		try {
			vcTmp = new TDINVCausa().FindByAll("iCveTpoCausa=" + cCveTpoCausa,
					" order by cDscCausa");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo getCausas
	 */
	public Vector getMedInforms() {
		Vector vcTmp = null;
		try {
			vcTmp = new TDINVMedInforma().FindByAll("",
					" order by cDscMedInforma");
		} catch (Exception ex) {
			error("getUniMed", ex);
		}
		return vcTmp;
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdBoton").equalsIgnoreCase("Guardar")) {
			mPk.add(cIAnio);
			mPk.add(cMdoTrans);
			mPk.add(cIIDDGPMPT);
		} else {
			mPk.add(request.getParameter("iAnio"));
			mPk.add(request.getParameter("iCveMdoTrans"));
			mPk.add(request.getParameter("iIDDGPMPT"));
		}
	}

	public TVINVRegistro getInputs() throws CFGException {
		String cCampo;
		TFechas tfCampo = new TFechas();
		TVINVRegistro vINVRegistro = new TVINVRegistro();
		try {
			cCampo = request.getParameter("iAnio");
			vINVRegistro
					.setIAnio(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iCveMdoTrans");
			vINVRegistro
					.setICveMdoTrans(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iIDDGPMPT");
			vINVRegistro
					.setIIDDGPMPT(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iIDMdoTrans");
			vINVRegistro
					.setIIDMdoTrans(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("dtAccidente");
			vINVRegistro
					.setDtAccidente(cCampo != null && cCampo.length() != 0 ? tfCampo
							.getDateSQL(cCampo) : null);
			cCampo = request.getParameter("dtNotifica");
			vINVRegistro
					.setDtNotifica(cCampo != null && cCampo.length() != 0 ? tfCampo
							.getDateSQL(cCampo) : null);
			cCampo = request.getParameter("cDscBreve");
			vINVRegistro.setCDscBreve(cCampo != null ? cCampo : "");
			cCampo = request.getParameter("iCveMedInforma");
			vINVRegistro.setICveMedInforma(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("cLugar");
			vINVRegistro.setCLugar(cCampo != null ? cCampo : "");
			cCampo = request.getParameter("iCvePais");
			vINVRegistro
					.setICvePais(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iCveEstado");
			vINVRegistro
					.setICveEstado(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iCveMunicipio");
			vINVRegistro.setICveMunicipio(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iCveTpoCausa");
			vINVRegistro
					.setICveTpoCausa(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iCveCausa");
			vINVRegistro
					.setICveCausa(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("cObservacion");
			vINVRegistro.setCObservacion(cCampo != null ? cCampo : "");
			cCampo = request.getParameter("cDscAccidente");
			vINVRegistro.setCDscAccidente(cCampo != null ? cCampo : "");
			cCampo = request.getParameter("iVehFedInvolucra");
			vINVRegistro.setIVehFedInvolucra(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iVehEdoInvolucra");
			vINVRegistro.setIVehEdoInvolucra(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iVehPartInvolucra");
			vINVRegistro.setIVehPartInvolucra(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iPerFedInvolucra");
			vINVRegistro.setIPerFedInvolucra(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iPerEdoInvolucra");
			vINVRegistro.setIPerEdoInvolucra(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iPerPartInvolucra");
			vINVRegistro.setIPerPartInvolucra(cCampo != null
					&& cCampo.length() != 0 ? Integer.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("iCveUniMed");
			vINVRegistro
					.setICveUniMed(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("dtAsigna");
			vINVRegistro
					.setDtAsigna(cCampo != null && cCampo.length() != 0 ? tfCampo
							.getDateSQL(cCampo) : null);
			cCampo = request.getParameter("lInvestigado");
			vINVRegistro
					.setLInvestigado(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("cConclusion");
			vINVRegistro.setCConclusion(cCampo != null ? cCampo : "");
			cCampo = request.getParameter("iCveMotivo");
			vINVRegistro
					.setICveMotivo(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("lConcluido");
			vINVRegistro
					.setLConcluido(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("dtConcluido");
			vINVRegistro
					.setDtConcluido(cCampo != null && cCampo.length() != 0 ? tfCampo
							.getDateSQL(cCampo) : null);
			cCampo = request.getParameter("lCancelado");
			vINVRegistro
					.setLCancelado(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
			cCampo = request.getParameter("dtCancelado");
			vINVRegistro
					.setDtCancelado(cCampo != null && cCampo.length() != 0 ? tfCampo
							.getDateSQL(cCampo) : null);
			cCampo = request.getParameter("lFinRegistro");
			vINVRegistro
					.setLFinRegistro(cCampo != null && cCampo.length() != 0 ? Integer
							.parseInt(cCampo, 10) : 0);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vINVRegistro;
	}

	public String getUpdStatus() {
		if (bs != null && bs.getCurrentBean() != null
				&& ((TVINVRegistro) bs.getCurrentBean()).getLFinRegistro() == 1)
			return "AddOnly";
		return super.getUpdStatus();
	}
}
