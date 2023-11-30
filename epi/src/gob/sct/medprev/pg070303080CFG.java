package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Listado de TOXCtrolCalibra
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303080CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070303080CFG.png'>
 */
public class pg070303080CFG extends CFGListBase2 {
	public pg070303080CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		Vector vcRegistros = new Vector();
		TDTOXExamResult dTDTOXExamResult = new TDTOXExamResult();
		try {
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements();) {
				String cParam = (String) e.nextElement();
				String cValue = request.getParameter(cParam);
				if (cParam.startsWith("dDilucion|") && cValue != null
						&& cValue.length() > 0) {
					cValue = cValue.equalsIgnoreCase("D") ? "1" : cValue;
					TVTOXExamResult dTVTOXExamResult = new TVTOXExamResult();
					StringTokenizer st = new StringTokenizer(cParam, "|");
					st.nextToken(); // inicio del campo
					dTVTOXExamResult.setiCveAnalisis(Integer.valueOf(st
							.nextToken()));// iCveAnalisis
					dTVTOXExamResult.setiCveLaboratorio(Integer.valueOf(st
							.nextToken()));// iCveLaboratorio
					dTVTOXExamResult.setiAnio(Integer.valueOf(st.nextToken()));// iAnio
					dTVTOXExamResult.setiCveLoteCualita(Integer.valueOf(st
							.nextToken()));// iCveLoteCualita
					dTVTOXExamResult.setiCveExamCualita(Integer.valueOf(st
							.nextToken()));// iCveExamCualita
					dTVTOXExamResult.setiCveSustancia(Integer.valueOf(st
							.nextToken()));// iCveSustancia
					dTVTOXExamResult.setdDilucion(Double.valueOf(cValue));// dDilucion
					vcRegistros.add(dTVTOXExamResult);
				}
			}
			dTDTOXExamResult.updateDilucion(null, vcRegistros);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar los registros", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		Vector vcRegistros = new Vector();
		try {
			HashMap hmFiltros = getParameters();
			Vector vcAnalisis = new TDTOXAnalisis().FindAnalisis(hmFiltros);
			Vector vcResultados = new TDTOXExamResult().FindByAll(hmFiltros);
			Vector vcSustancias = new TDTOXCualtSust().FindByAll(hmFiltros);
			for (Enumeration eResultados = vcResultados.elements(); eResultados
					.hasMoreElements();) {
				TVTOXExamResult vResultados = (TVTOXExamResult) eResultados
						.nextElement();
				if (vcAnalisis.contains(vResultados.getiCveAnalisis())) {
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
					vcRegistros.add(vRegistro);
					vcAnalisis.remove(vResultados.getiCveAnalisis());
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

			for (Enumeration e = vcRegistros.elements(); e.hasMoreElements();) {
				TVDinamico vRegistro = (TVDinamico) e.nextElement();

				for (Enumeration eSustancias = vcSustancias.elements(); eSustancias
						.hasMoreElements();) {
					int iCveSustancia = ((TVTOXCualtSust) eSustancias
							.nextElement()).getICveSustancia();

				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		vDespliega = vcRegistros;
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

	/**
	 * Metodo miCeldaCampo
	 */
	public StringBuffer miCeldaCampo(String cEstiloC, String cTipo, int iSize,
			int iMaxLen, String cName, String cValue, int iTooltip,
			String cOnChange, String cOnBlur, boolean lSelectonFocus,
			boolean lActivo, boolean lCampo, String cLetrero) {
		StringBuffer sbRes = new StringBuffer("");
		if (lCampo == true) {
			sbRes.append("<td class=\"" + cEstiloC + "\">\n");
			sbRes.append(cLetrero);
			sbRes.append("<input type=\"" + cTipo + "\" size=\"" + iSize
					+ "\" maxlength=\"");
			sbRes.append(iMaxLen + "\" name=\"" + cName + "\"\n");
			sbRes.append("value=\"" + cValue + "\"\n");
			if (lActivo == false) {
				sbRes.append(" disabled");
			}
			if (lSelectonFocus == true) {
				sbRes.append(" onfocus=\"this.select();\"\n");
			}
			sbRes.append(" onChange=\"" + cOnChange + "\"\n");
			sbRes.append(" onBlur=\"" + cOnBlur + "\"\n");
			sbRes.append(" onMouseOut=\"if (window.fOutField) window.fOutField();\"\n");
			sbRes.append(" onMouseOver=\"if (window.fOverField) window.fOverField(");
			sbRes.append(iTooltip);
			sbRes.append(");\">");
			if (cName.substring(0, 2).compareTo("dt") == 0) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(new java.util.Date());
				sbRes.append("<a class=\"" + "EAncla" + "\" name=\""
						+ "calendario" + "\" href=\"" + "JavaScript:fLoadCal("
						+ calendar.get(calendar.DAY_OF_MONTH) + ","
						+ (calendar.get(calendar.MONTH) + 1) + ","
						+ calendar.get(calendar.YEAR) + ","
						+ "document.forms[0]." + cName + ");" + "\">");
				sbRes.append("(dd/mm/aaaa)");
				sbRes.append("</a>");
			}
			sbRes.append("</td>\n");
		} else {
			sbRes.append("<td class=\"" + cEstiloC + "\">" + cLetrero + cValue
					+ "</td>\n");
		}
		return sbRes;
	}

	/**
	 * Metodo getCalibradores
	 */
	public Vector getCalibradores() {
		Vector vcTOXCualtSust = new Vector();
		try {
			String cTmp = request.getParameter("iAnio");
			int iAnio = cTmp != null ? Integer.parseInt(cTmp) : 0;
			cTmp = request.getParameter("iCveUniMed");
			int iCveLaboratorio = cTmp != null ? Integer.parseInt(cTmp) : 0;
			cTmp = request.getParameter("iCveLoteCualita");
			int iCveLoteCualita = cTmp != null ? Integer.parseInt(cTmp) : 0;
			cTmp = request.getParameter("iCveExamCualita");
			int iCveExamCualita = cTmp != null ? Integer.parseInt(cTmp) : 0;
			if (iAnio != 0 && iCveLaboratorio != 0 && iCveLoteCualita != 0
					&& iCveExamCualita != 0)
				vcTOXCualtSust = new TDTOXCualtSust()
						.FindByAllAnalisisPresuntivo(iAnio, iCveLaboratorio,
								iCveLoteCualita, iCveExamCualita);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
		return vcTOXCualtSust;
	}

	/**
	 * Metodo getUpdStatus
	 */
	public String getUpdStatus() {
		if (bs == null)
			return "Hidden";
		return "SaveCancelOnly";
	}
}