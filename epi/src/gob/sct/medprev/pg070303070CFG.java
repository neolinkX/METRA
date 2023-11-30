package gob.sct.medprev;

import java.util.*;
import javax.servlet.http.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Catalogo de Equipos
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Ernesto Avalos
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301071CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070301071CFG.png'>
 */
public class pg070303070CFG extends CFGListBase2 {
	public pg070303070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TVTOXExamenCualita vTOXExamenCualita = new TVTOXExamenCualita();
		int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
				.getSession(true).getAttribute("UsrID"))).getICveusuario();
		java.sql.Date dtHoy = new TFechas().TodaySQL();
		Vector vAnalisis = new Vector();
		Vector vExamCual = new Vector();
		try {
			vTOXExamenCualita.setLReanalisis(0);
			vTOXExamenCualita.setICveUsuAutoriza(iCveUsuario);
			vTOXExamenCualita.setDtAutorizado(dtHoy);
			vTOXExamenCualita.setLAutorizado(1);
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements();) {
				String cParam = (String) e.nextElement();
				String cValue = request.getParameter(cParam);
				if (cParam.startsWith("lPositivo|") && cValue != null
						&& cValue.length() > 0) {
					StringTokenizer st = new StringTokenizer(cParam, "|");
					st.nextToken();// inicio del campo
					Integer iCveAnalisis = Integer.valueOf(st.nextToken());// iCveAnalisis
					vTOXExamenCualita.setICveLaboratorio(Integer.parseInt(st
							.nextToken()));// iCveLaboratorio
					vTOXExamenCualita
							.setIAnio(Integer.parseInt(st.nextToken()));// iAnio
					vTOXExamenCualita.setICveLoteCualita(Integer.parseInt(st
							.nextToken()));// iCveLoteCualita
					vTOXExamenCualita.setICveExamCualita(Integer.parseInt(st
							.nextToken()));// iCveExamCualita

					if (request.getParameter("cbReanalisis|"
							+ cParam.substring("lPositivo|".length())) != null) {
						vTOXExamenCualita.setLReanalisis(1);
					} else {
						TVTOXAnalisis vToxAnalisis = new TVTOXAnalisis();
						vToxAnalisis.setiAnio(new Integer(vTOXExamenCualita
								.getIAnio()));
						vToxAnalisis.setiCveLaboratorio(new Integer(
								vTOXExamenCualita.getICveLaboratorio()));
						vToxAnalisis.setlAutorizado(new Integer(1));
						vToxAnalisis
								.setiCveUsuAutoriza(new Integer(iCveUsuario));
						vToxAnalisis.setlPresuntoPost(Integer.valueOf(cValue));
						vToxAnalisis.setiCveExamCualita(new Integer(
								vTOXExamenCualita.getICveExamCualita()));
						if (vToxAnalisis.getlPresuntoPost().intValue() == 0) {
							vToxAnalisis.setlResultado(vToxAnalisis
									.getlPresuntoPost());
							vToxAnalisis.setISustPost(new Integer(0));
							vToxAnalisis.setISustConf(new Integer(0));
							// Agregar a los dudosos
							if (request.getParameter("cbDudoso|"
									+ cParam.substring("lPositivo|".length())) != null) {
								vToxAnalisis.setlPresuntoPost(new Integer(1));
								vToxAnalisis.setlResultado(new Integer(0));
								vToxAnalisis.setISustPost(new Integer(request
										.getParameter("iSustDudo|"
												+ cParam.substring("lPositivo|"
														.length()))));
								vToxAnalisis.setISustConf(new Integer(0));
							}
						} else if (request.getParameter("iSustPost|"
								+ cParam.substring("lPositivo|".length())) != null) {
							vToxAnalisis.setlResultado(new Integer(0));
							vToxAnalisis.setISustPost(new Integer(request
									.getParameter("iSustPost|"
											+ cParam.substring("lPositivo|"
													.length()))));
							vToxAnalisis.setISustConf(new Integer(0));
						}
						vToxAnalisis.setiCveAnalisis(iCveAnalisis);
						vToxAnalisis.setdtAutorizacion(dtHoy);

						vAnalisis.add(vToxAnalisis);
					}
				}
			}
			vExamCual.add(vTOXExamenCualita);
			new TDTOXExamResult()
					.updateAnalisisExamResutl(vAnalisis, vExamCual);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar los registros", ex);
		} finally {
			super.Guardar();
		}
	}

	public void Validar() {
		TVTOXExamenCualita vTOXExamenCualita = new TVTOXExamenCualita();
		int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
				.getSession(true).getAttribute("UsrID"))).getICveusuario();
		java.sql.Date dtHoy = new TFechas().TodaySQL();
		Vector vAnalisis = new Vector();
		Vector vExamCual = new Vector();
		try {
			HashMap hmFiltros = getParameters();
			Vector vcAnalisis = new TDTOXAnalisis()
					.FindAnalisisCtrolCalibra(hmFiltros);
			int iAnio = 0;
			int iCveLaboratorio = 0;
			int iCveLoteCualita = 0;
			int iCveExamCualita = 0;
			if (request.getParameter("iAnio") != null)
				iAnio = Integer.parseInt(request.getParameter("iAnio"));
			if (request.getParameter("iCveUniMed") != null)
				iCveLaboratorio = Integer.parseInt(request
						.getParameter("iCveUniMed"));
			if (request.getParameter("iCveLoteCualita") != null)
				iCveLoteCualita = Integer.parseInt(request
						.getParameter("iCveLoteCualita"));
			if (request.getParameter("iCveExamCualita") != null)
				iCveExamCualita = Integer.parseInt(request
						.getParameter("iCveExamCualita"));

			vTOXExamenCualita.setIAnio(iAnio);// iAnio
			vTOXExamenCualita.setICveLaboratorio(iCveLaboratorio);// iCveLaboratorio
			vTOXExamenCualita.setICveLoteCualita(iCveLoteCualita);// iCveLoteCualita
			vTOXExamenCualita.setICveExamCualita(iCveExamCualita);// iCveExamCualita
			vTOXExamenCualita.setLReanalisis(0);
			vTOXExamenCualita.setICveUsuAutoriza(iCveUsuario);
			vTOXExamenCualita.setDtAutorizado(dtHoy);
			vTOXExamenCualita.setLAutorizado(1);
			/* */
			if (vcAnalisis.size() > 0)
				vTOXExamenCualita.setLReanalisis(1);
			else
				vTOXExamenCualita.setLReanalisis(0);
			vExamCual.add(vTOXExamenCualita);

			new TDTOXExamResult().updateLoteCualita(vExamCual);
			this.vErrores.acumulaError("El Resultado del Examen fue validado",
					0, "");
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar los registros", ex);
		} finally {
			super.Guardar();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		/* Validar */
		if (cAccion.equalsIgnoreCase("Validar")) {
			this.Validar();
		}
		Vector vcRegistros = new Vector();
		try {
			HashMap hmFiltros = getParameters();
			Vector vcAnalisis = new TDTOXAnalisis()
					.FindAnalisisCtrolCalibra(hmFiltros);
			// Vector vcResultados=new TDTOXExamResult().FindByAll (hmFiltros);
			Vector vcResultados = new TDTOXExamResult()
					.FindByAllEAAP(hmFiltros);
			Vector vcSustancias = new TDTOXCualtSust().FindByAll(hmFiltros);
			for (Enumeration eResultados = vcResultados.elements(); eResultados
					.hasMoreElements();) {
				TVTOXExamResult vResultados = (TVTOXExamResult) eResultados
						.nextElement();
				if (vcAnalisis.contains(vResultados.getiCveAnalisis())) {
					int iIndex = vcAnalisis.indexOf(vResultados
							.getiCveAnalisis()) + 1;
					TVDinamico vRegistro = new TVDinamico();
					vRegistro.put("iAnio", vResultados.getiAnio());
					vRegistro.put("iCveLaboratorio",
							vResultados.getiCveLaboratorio());
					vRegistro.put("iCveLoteCualita",
							vResultados.getiCveLoteCualita());
					vRegistro.put("iCveExamCualita",
							vResultados.getiCveExamCualita());
					vRegistro
							.put("iCveAnalisis", vResultados.getiCveAnalisis());
					for (Enumeration eSustancias = vcSustancias.elements(); eSustancias
							.hasMoreElements();) {
						TVTOXCualtSust vSustancia = (TVTOXCualtSust) eSustancias
								.nextElement();
						int iCveSustancia = vSustancia.getICveSustancia();
						vRegistro.put("dResultadoParaIdSustancia"
								+ iCveSustancia, "");
						vRegistro.put("lPositivoParaIdSustancia"
								+ iCveSustancia, "");
						vRegistro.put("dDilucionParaIdSustancia"
								+ iCveSustancia, "");
						vRegistro.put("lAsignadoParaIdSustancia"
								+ iCveSustancia, "");
						vRegistro.put("lDudosoParaIdSustancia" + iCveSustancia,
								"");
					}

					String cDscBreve = (String) vcAnalisis.elementAt(iIndex);
					vRegistro.put("cDscBreve", cDscBreve);
					vcRegistros.add(vRegistro);
					vcAnalisis.remove(vResultados.getiCveAnalisis());
					vcAnalisis.remove(cDscBreve);
				}
			}
			HashMap hmRegistros = buildHashMap(vcRegistros);
			for (Enumeration eResultados = vcResultados.elements(); eResultados
					.hasMoreElements();) {
				TVTOXExamResult vResultados = (TVTOXExamResult) eResultados
						.nextElement();
				TVDinamico vRegistro = (TVDinamico) hmRegistros.get("K"
						+ (Integer) vResultados.getiCveAnalisis());
				if (vRegistro == null)
					continue;
				Integer iTmp = (Integer) vResultados.getiCveSustancia();
				String cElem = "dResultadoParaIdSustancia" + iTmp;
				if (vRegistro.containsKey(cElem))
					vRegistro.put(cElem, vResultados.getdResultado());
				cElem = "lPositivoParaIdSustancia" + iTmp;
				if (vRegistro.containsKey(cElem))
					vRegistro.put(cElem, vResultados.getLPositivo());
				cElem = "dDilucionParaIdSustancia" + iTmp;
				if (vRegistro.containsKey(cElem))
					vRegistro.put(cElem, vResultados.getdDilucion());
				cElem = "lAsignadoParaIdSustancia" + iTmp;
				if (vRegistro.containsKey(cElem))
					vRegistro.put(cElem, vResultados.getlAsignado());
				cElem = "lDudosoParaIdSustancia" + iTmp;
				if (vRegistro.containsKey(cElem))
					vRegistro.put(cElem, vResultados.getLDudoso());
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		vDespliega = vcRegistros;
	}

	/**
	 * Metodo FindCalibradores
	 */
	public Vector FindCalibradores() {
		int iAnio = 0;
		int iCveLaboratorio = 0;
		int iCveLoteCualita = 0;
		int iCveExamCualita = 0;
		Vector vcTOXCualtsust = null;
		try {
			if (request.getParameter("iAnio") != null)
				iAnio = Integer.parseInt(request.getParameter("iAnio"));
			if (request.getParameter("iCveUniMed") != null)
				iCveLaboratorio = Integer.parseInt(request
						.getParameter("iCveUniMed"));
			if (request.getParameter("iCveLoteCualita") != null)
				iCveLoteCualita = Integer.parseInt(request
						.getParameter("iCveLoteCualita"));
			if (request.getParameter("iCveExamCualita") != null)
				iCveExamCualita = Integer.parseInt(request
						.getParameter("iCveExamCualita"));
			if (iCveLaboratorio != 0 && iCveLoteCualita != 0
					&& iCveExamCualita != 0)
				vcTOXCualtsust = new TDTOXCualtSust()
						.FindByAllAnalisisPresuntivo(iAnio, iCveLaboratorio,
								iCveLoteCualita, iCveExamCualita);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return vcTOXCualtsust;
	}

	/**
	 * Metodo getSubstancias
	 */
	public Vector getSubstancias() {
		try {
			return new TDTOXCualtSust().FindByAll(getParameters());
		} catch (Exception ex) {
			error("FillVector", ex);
			return new Vector();
		}
	}

	/**
	 * Metodo getUpdStatus
	 */
	public String getUpdStatus() {
		if (bs == null)
			return "Hidden";
		return "SaveCancelOnly";
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters() {
		HashMap hmTmp = new HashMap();
		hmTmp.put("iAnio", request.getParameter("iAnio"));
		hmTmp.put("iCveLaboratorio", request.getParameter("iCveUniMed"));// ojo!!!
																			// iCveLaboratorio=iCveUniMed
		hmTmp.put("iCveLoteCualita", request.getParameter("iCveLoteCualita"));
		hmTmp.put("iCveExamCualita", request.getParameter("iCveExamCualita"));
		return hmTmp;
	}

	/**
	 * Metodo buildHashMap
	 */
	private HashMap buildHashMap(Vector vcRegistros) {
		HashMap hmTmp = new HashMap();
		for (Enumeration eRegistros = vcRegistros.elements(); eRegistros
				.hasMoreElements();) {
			TVDinamico vRegistro = (TVDinamico) eRegistros.nextElement();
			hmTmp.put("K" + (Integer) vRegistro.get("iCveAnalisis"), vRegistro);
		}
		return hmTmp;
	}

	public boolean isLValidar() {
		try {
			TDTOXExamenCualita Examen = new TDTOXExamenCualita();
			StringBuffer cFiltro = new StringBuffer();
			cFiltro.append(" where iAnio = ")
					.append(request.getParameter("iAnio"))
					.append("   and iCveLaboratorio = ")
					.append(request.getParameter("iCveUniMed"))
					.append("   and iCveLoteCualita = ")
					.append(request.getParameter("iCveLoteCualita"))
					.append("   and iCveExamCualita = ")
					.append(request.getParameter("iCveExamCualita"));
			return Examen.lAutorizado(cFiltro.toString());
		} catch (Exception ex) {
			error("FillVector", ex);
			return false;
		}

	}
}