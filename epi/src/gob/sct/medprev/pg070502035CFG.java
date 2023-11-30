package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuracion para Clase para el Cat�logo de la tabla
 * CTRPersonal
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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070502035CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070502035CFG.java.png'>
 */
public class pg070502035CFG extends CFGCatBase2 {
	public Vector vLicencias = new Vector();

	public pg070502035CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070502034.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
		try {
			cClave = (String) dCTRPersonal.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
		try {
			cClave = (String) dCTRPersonal.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
		try {
			cClave = (String) dCTRPersonal.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	public Vector Licencias(int ivEmpresa, int ivPlantilla, int ivPersonal) {
		TDCTRPersonal DCTRPersonal = new TDCTRPersonal();

		String cCond1 = " where CTRPersonal.cLicencia in ( select lic.cLicencia       "
				+ "                                    from CTRPersonal lic     "
				+ "                                   where lic.iCveEmpresa   = "
				+ ivEmpresa
				+ "                                     and lic.iCvePlantilla = "
				+ ivPlantilla
				+ "                                     and lic.iNumPersonal  = "
				+ ivPersonal + " ) ";

		try {
			vLicencias = DCTRPersonal
					.FindByLicencia(
							cCond1,
							" order by CTRPersonal.iCveEmpresa, CTRPersonal.iCvePlantilla, CTRPersonal.iNumPersonal ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vLicencias;
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
		boolean lWhere = false;
		try {
			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}
			if (request.getParameter("hdIni") != null
					&& request.getParameter("hdIni").compareTo("null") != 0
					&& request.getParameter("hdIni").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND CTRPersonal.iCveEmpresa = "
							+ request.getParameter("hdIni");
				else {
					cCondicion = " WHERE CTRPersonal.iCveEmpresa = "
							+ request.getParameter("hdIni");
					lWhere = true;
				}
			}

			if (request.getParameter("hdIni2") != null
					&& request.getParameter("hdIni2").compareTo("null") != 0
					&& request.getParameter("hdIni2").compareTo("") != 0) {
				if (lWhere)
					cCondicion += " AND CTRPersonal.iCvePlantilla = "
							+ request.getParameter("hdIni2");
				else {
					cCondicion = " WHERE CTRPersonal.iCvePlantilla = "
							+ request.getParameter("hdIni2");
					lWhere = true;
				}
			}

			vDespliega = dCTRPersonal.FindByAll(cCondicion, cOrden);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(request.getParameter("hdIni").toString());
		mPk.add(request.getParameter("hdIni2").toString());
		mPk.add(request.getParameter("iNumPersonal").toString());
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
		TVCTRPersonal vCTRPersonal = new TVCTRPersonal();
		try {
			cCampo = "" + request.getParameter("hdIni");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICveEmpresa(iCampo);

			cCampo = "" + request.getParameter("hdIni2");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICvePlantilla(iCampo);

			cCampo = "" + request.getParameter("iNumPersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setINumPersonal(iCampo);

			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("cNombre");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCNombre(cCampo);

			cCampo = "" + request.getParameter("cApPaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCApPaterno(cCampo);

			cCampo = "" + request.getParameter("cApMaterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCApMaterno(cCampo);

			cCampo = "" + request.getParameter("cRFC");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCRFC(cCampo);

			cCampo = "" + request.getParameter("cCURP");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCCURP(cCampo);

			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCTRPersonal.setDtNacimiento(dtCampo);

			cCampo = "" + request.getParameter("iCvePaisNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICvePaisNac(iCampo);

			cCampo = "" + request.getParameter("iCveEstadoNac");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICveEstadoNac(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCvePuesto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICvePuesto(iCampo);

			cCampo = "" + request.getParameter("cLicencia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCLicencia(cCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cNumExt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCNumExt(cCampo);

			cCampo = "" + request.getParameter("cNumInt");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCNumInt(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCTRPersonal.setCTel(cCampo);

			cCampo = "" + request.getParameter("dtLicVencimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCTRPersonal.setDtLicVencimiento(dtCampo);

			cCampo = "" + request.getParameter("lBaseEventual");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setlBaseEventual(iCampo);

			cCampo = "" + request.getParameter("lActivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCTRPersonal.setlActivo(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vCTRPersonal;
	}
}
