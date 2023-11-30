package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305051.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305051CFG.png'>
 */
public class pg070305051CFG extends CFGListBase2 {
	public pg070305051CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070305050.jsp|pg070305030.jsp|";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		/* Validar */
		Vector vcRegistros = new Vector();
		TVTOXAnalisis vAnalisis;
		try {
			HashMap hmFiltros = getParameters();
			HashMap vcAnalisis = new TDTOXAnalisis()
					.FindAnalisisCtrolCalibraCons(hmFiltros);
			Vector vcResultados = new TDTOXExamResult().FindByAll(hmFiltros);
			Vector vcSustancias = new TDTOXCualtSust().FindByAll(hmFiltros);
			for (Enumeration eResultados = vcResultados.elements(); eResultados
					.hasMoreElements();) {
				TVTOXExamResult vResultados = (TVTOXExamResult) eResultados
						.nextElement();
				if (vcAnalisis.containsKey(vResultados.getiCveAnalisis()
						.toString())) {
					vAnalisis = (TVTOXAnalisis) vcAnalisis.get(vResultados
							.getiCveAnalisis().toString());
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
					String cDscBreve = vAnalisis.getCDscMotivo();
					vRegistro.put("cDscBreve", cDscBreve);
					vRegistro.put("lResultado", vAnalisis.getlResultado()
							.toString());

					vRegistro.put("vAnalisis", vAnalisis);

					vcRegistros.add(vRegistro);
					vcAnalisis.remove(vResultados.getiCveAnalisis().toString());
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
			ex.printStackTrace();
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
