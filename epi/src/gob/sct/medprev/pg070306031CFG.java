package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuración para CFG Baja de Reactivos
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306031CFG.png'>
 */
public class pg070306031CFG extends CFGCatBase2 {
	public pg070306031CFG() {
		cPaginas = "pg070306030.jsp|";
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	private StringBuffer activeX = new StringBuffer("");

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDTOXBaja dTOXBaja = new TDTOXBaja();
		String tipo = "";
		int llave = 0;
		try {
			if (request.getParameter("tipo") != null) {
				tipo = request.getParameter("tipo");
			}
			if (request.getParameter("iCveReactivo") != null) {
				llave = Integer.parseInt(request.getParameter("iCveReactivo"));
			} else if (request.getParameter("iCveCtrolCalibra") != null) {
				llave = Integer.parseInt(request
						.getParameter("iCveCtrolCalibra"));
			}

			cClave = (String) dTOXBaja.insertBaja(null, this.getInputs(), tipo,
					llave);
			this.cActual = cClave;

			TFechas fecha = new TFechas();
			TVTOXBaja vBaja = (TVTOXBaja) (this.getInputs());
			String cCondicion = " where TOXBaja.iAnio = "
					+ fecha.getIntYear(fecha.TodaySQL())
					+ "   and TOXBaja.iCveLaboratorio = "
					+ vBaja.getICveLaboratorio() + "   and TOXBaja.iCveBaja = "
					+ cClave;

			// System.out.println("Condicion = " + cCondicion);
			Vector vcReporte = dTOXBaja.FindByAll(cCondicion);
			activeX.append(this.GenRep(vcReporte));
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
			ex.printStackTrace();
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TDTOXBaja dTOXBaja = new TDTOXBaja();
		try {
			cClave = (String) dTOXBaja.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Método GuardarA

	/**
	 * Método Borrar
	 */
	public void Borrar() {
		TDTOXBaja dTOXBaja = new TDTOXBaja();
		try {
			cClave = (String) dTOXBaja.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Método Borrar

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXBaja dTOXBaja = new TDTOXBaja();
		boolean lWhere = false;
		Vector vcReporte = new Vector();
		try {
			// Agregado por Rafael Alcocer Caldera 26/Sep/2006
			if (request.getParameter("RACiCveCtrolCalibra") != null) {
				cAccionOriginal = "Nuevo";
			}

			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("null") != 0) {
				if (lWhere) {
					cCondicion += " AND "
							+ " TOXBaja.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio")
									.toString();
				} else {
					cCondicion = " WHERE "
							+ " TOXBaja.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio")
									.toString();
					lWhere = true;
				}
			}

			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString()
							.compareTo("null") != 0) {
				if (lWhere) {
					cCondicion += " AND " + " TOXBaja.iAnio = "
							+ request.getParameter("iAnio").toString();
				} else {
					cCondicion = " WHERE " + " TOXBaja.iAnio = "
							+ request.getParameter("iAnio").toString();
					lWhere = true;
				}
			}

			else {

				if (request.getParameter("hdCampoClave2") != null
						&& request.getParameter("hdCampoClave2").toString()
								.compareTo("null") != 0) {
					if (lWhere) {
						cCondicion += " AND "
								+ " TOXBaja.iCveLaboratorio = "
								+ request.getParameter("hdCampoClave2")
										.toString();
					} else {
						cCondicion = " WHERE "
								+ " TOXBaja.iCveLaboratorio = "
								+ request.getParameter("hdCampoClave2")
										.toString();
						lWhere = true;
					}

					if (request.getParameter("hdCampoClave") != null
							&& request.getParameter("hdCampoClave").toString()
									.compareTo("null") != 0) {
						if (lWhere) {
							cCondicion += " AND "
									+ " TOXBaja.iAnio = "
									+ request.getParameter("hdCampoClave")
											.toString();
						} else {
							cCondicion = " WHERE "
									+ " TOXBaja.iAnio = "
									+ request.getParameter("hdCampoClave")
											.toString();
							lWhere = true;
						}
					}
				}

			}

			String cOrden = "" + request.getParameter("hdCOrdenar");

			if (cOrden.compareTo("null") != 0 && cOrden.compareTo("") != 0) {
				cOrden = request.getParameter("hdCOrdenar");
			} else {
				cOrden = " Order By C.iCveLaboratorio, C.iCveCtrolCalibra";

			}

			if (cCondicion.compareTo("") != 0
					&& cCondicion.compareTo("null") != 0) {
				vDespliega = dTOXBaja.FindByAll(cCondicion);
			} else {
				vDespliega = dTOXBaja
						.FindByAll(" where TOXBaja.iCveLaboratorio = 0 ");
			}

			if (request.getParameter("hdReporte") != null
					&& request.getParameter("hdReporte").toString()
							.compareTo("null") != 0) {

				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {
					vcReporte = dTOXBaja.FindByAll(cCondicion
							+ " and TOXBaja.iCveBaja = "
							+ request.getParameter("hdCampoClave3"));

					this.fillPK();
					// cAccion = "Cancelar";
					activeX.append(this.GenRep(vcReporte));
					// cAccion = "ReposVector";
					// this.fillPK();

				}
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070306031");
		int iRenglon = 0;
		int iFilasxPag = 65;
		int iPag = 0;
		int iPagAnt = 0;
		int iRenIni = 0;
		int iRenFin = 0;
		int iRenxPag = 4;
		int iCvePersonal = 0;
		int iCveUniMed = 0;
		int iCveProceso = 0;
		String cPersonal = "";
		String cCveUniMed = "";
		String cCveProceso = "";
		String pName = "";
		String pEmpresa = "";
		String Doctor = "";
		StringBuffer buffer = new StringBuffer();
		java.sql.Date dtConcluido;
		String dFinalExamen = "";
		int lDictamen = 1;
		TVTOXBaja vTOXBaja = new TVTOXBaja();
		Vector vcTOXBaja = new Vector();
		TFechas dtFecha = new TFechas("07");

		try {
			vcTOXBaja = (Vector) obj;

			if (vcTOXBaja.size() > 0) {
				vTOXBaja = (TVTOXBaja) vcTOXBaja.get(0);
				// if (lDictamen == 0){
			}

			TDGRLUniMed dUniMed = new TDGRLUniMed();
			TVGRLUniMed vUniMed = new TVGRLUniMed();
			Vector vcUniMed = new Vector();

			vcUniMed = dUniMed.FindUniMed("" + vTOXBaja.getICveLaboratorio());
			if (vcUniMed.size() > 0) {
				vUniMed = (TVGRLUniMed) vcUniMed.get(0);
				// System.out.println("Se encontró un registro");

			}
			TDGRLPais dPais = new TDGRLPais();
			TVGRLPais vPais = new TVGRLPais();
			Vector vcPais = new Vector();
			vcPais = dPais.FindByAll(" iCvePais =  " + vUniMed.getICvePais(),
					"");

			TDGRLEntidadFed dEstado = new TDGRLEntidadFed();
			TVGRLEntidadFed vEstado = new TVGRLEntidadFed();
			Vector vcEstado = new Vector();
			vcEstado = dEstado.FindByAll(
					" iCvePais =  " + vUniMed.getICvePais()
							+ " and iCveEntidadFed =  "
							+ vUniMed.getICveEstado(), "");

			if (vcPais.size() > 0 && vcEstado.size() > 0) {
				vPais = (TVGRLPais) vcPais.get(0);
				vEstado = (TVGRLEntidadFed) vcEstado.get(0);
				xl.comDespliega("B", 10,
						vPais.getCNombre() + "," + vEstado.getCAbreviatura());

			}
			xl.comDespliega(
					"E",
					10,
					""
							+ dtFecha.getFechaDDMMMMMYYYY(
									vTOXBaja.getDtDesechado(), " de "));

			if (vTOXBaja.getICveReactivo() > 0) {
				TDTOXReactivo dTOXReactivo = new TDTOXReactivo();
				TVTOXReactivo vTOXReactivo = new TVTOXReactivo();
				Vector vcTOXReactivo = new Vector();

				vcTOXReactivo = dTOXReactivo.FindByAll(
						" where iCveLaboratorio = "
								+ vTOXBaja.getICveLaboratorio()
								+ " and  iCveReactivo ="
								+ vTOXBaja.getICveReactivo(), "");
				if (vcTOXReactivo.size() > 0) {
					vTOXReactivo = (TVTOXReactivo) vcTOXReactivo.get(0);

				}
				xl.comDespliega(
						"B",
						15,
						vTOXReactivo.getCDscTpoReact(""
								+ vTOXReactivo.getICveTpoReact()));
				xl.comDespliega("B", 16, vTOXReactivo.getICodigo());
				xl.comDespliega("B", 17, vTOXReactivo.getCDscReactivo());
				xl.comDespliega("B", 18, vTOXReactivo.getCPreparadoDe());
				xl.comDespliega("B", 19, vTOXReactivo.getCDscMarcaSust());

			} else {
				TDTOXCtrolCalibra dTOXCtrolCalibra = new TDTOXCtrolCalibra();
				TVTOXCtrolCalibra vTOXCtrolCalibra = new TVTOXCtrolCalibra();
				Vector vcCtrolCalibra = new Vector();

				vcCtrolCalibra = dTOXCtrolCalibra.FindByAll(
						" where C.iCveLaboratorio = "
								+ vTOXBaja.getICveLaboratorio()
								+ " and  C.iCveCtrolCalibra  ="
								+ vTOXBaja.getICveCtrolCalibra(), "");
				if (vcCtrolCalibra.size() > 0) {
					vTOXCtrolCalibra = (TVTOXCtrolCalibra) vcCtrolCalibra
							.get(0);

				}
				xl.comDespliega("B", 15, vTOXCtrolCalibra.getCDscEmpleoCalib());
				xl.comDespliega("B", 16, vTOXCtrolCalibra.getCLote());
				xl.comDespliega("B", 17, vTOXCtrolCalibra.getCDscCtrolCalibra());
				xl.comDespliega("B", 18,
						"" + vTOXCtrolCalibra.getICveReactivo());

			}

			xl.comDespliega("B", 26, vTOXBaja.getCOrganoInterno());
			xl.comDespliega("B", 30, vTOXBaja.getCNombreUsuario());
			xl.comDespliega("B", 34, vTOXBaja.getCInactiva());
			xl.comDespliega("B", 38, vTOXBaja.getCObservacion());
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070306031", cNomArchivo, false, false, true);
		return buffer;
	}

	/**
	 * Método FillPK
	 */
	public void fillPK() {

		if (request.getParameter("hdBoton").compareTo("Actual") == 0
				&& request.getParameter("hdCampoClave") != null
				&& request.getParameter("hdCampoClave3") != null
				&& request.getParameter("hdCampoClave").compareTo("") != 0
				&& request.getParameter("hdCampoClave3").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave"));
			mPk.add(request.getParameter("hdCampoClave2"));
			mPk.add(request.getParameter("hdCampoClave3"));
		}
		if (request.getParameter("hdBoton").compareTo("Cancelar") == 0 // ){
				|| request.getParameter("hdBoton").compareTo(
						"Imprime Documentacion") == 0) {
			mPk.add(request.getParameter("hdCampoClave"));
			mPk.add(request.getParameter("hdCampoClave2"));
			mPk.add(request.getParameter("hdCampoClave3"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("GuardarA") == 0) {
			mPk.add(request.getParameter("hdCampoClave"));
			mPk.add(request.getParameter("hdCampoClave2"));
			mPk.add(cActual);
		}

		// mPk.add(cActual);
	}

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVTOXBaja vTOXBaja = new TVTOXBaja();
		try {
			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXBaja.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveLaboratorio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXBaja.setICveLaboratorio(iCampo);

			cCampo = "" + request.getParameter("iCveBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXBaja.setICveBaja(iCampo);

			cCampo = "" + request.getParameter("dtDesechado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vTOXBaja.setDtDesechado(dtCampo);

			cCampo = "" + request.getParameter("cOrganoInterno");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXBaja.setCOrganoInterno(cCampo);

			cCampo = "" + request.getParameter("iCveUsuBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXBaja.setICveUsuBaja(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXBaja.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("cInactiva");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXBaja.setCInactiva(cCampo);

			cCampo = "" + request.getParameter("iCveMotBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXBaja.setICveMotBaja(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXBaja;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	public String spanishDate() {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
		int iDay = dtFecha.getIntDay(dtFecha.TodaySQL());
		String cMes = dtFecha.getMonthName(dtFecha.TodaySQL());
		fechaFormat = " a " + iDay + " de " + cMes + " de " + iAnio;
		return fechaFormat;
	}

	public String spanishExamDate(java.sql.Date dConcluido) {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dConcluido);
		int iDay = dtFecha.getIntDay(dConcluido);
		String cMes = dtFecha.getMonthName(dConcluido);
		fechaFormat = iDay + " del mes " + cMes + " y año de " + iAnio;
		return fechaFormat;
	}

}
