package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import java.util.*;
import java.lang.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG de la pagina pg070104070
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104070CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104070CFG.png'>
 */
public class pg070104070CFG extends CFGListBase2 {
	public pg070104070CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070105020.jsp";
		NavStatus = "Disabled";
	}

	public pg070104070CFG(String cOpcPaginas) {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = cOpcPaginas;
		// cPaginas="pg070105020.jsp";
		NavStatus = "Disabled";
	}

	private StringBuffer activeX = new StringBuffer("");
	private com.micper.seguridad.vo.TVDinRep02 vEXPDatos;
	private java.util.Vector vSintomas;
	private java.util.Vector vDiagnostico;
	private java.util.Vector vRemendacion;

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDEXPResultado dEXPResultado = new TDEXPResultado();
		Vector vcReporte = new Vector();
		try {
			// Obtener datos del Examen.
			TVEXPRama vDatos = (TVEXPRama) this.getInputs();
			if (vDatos.getICveExpediente() == 0)
				vDatos = (TVEXPRama) this.getInputs05020();
			this.vEXPDatos = new TDEXPExamAplica().findResultExamA(
					vDatos.getICveExpediente(), vDatos.getINumExamen(),
					vDatos.getICveServicio());
			vDespliega = dEXPResultado.findResultExamA(
					vDatos.getICveExpediente(), vDatos.getINumExamen(),
					vDatos.getICveServicio(), vDatos.getICveRama());
			this.vSintomas = vDespliega;
			iNumReg = vDespliega.size();
			// Obtener informaci�n de los diagn�sticos y Recomendaciones
			if (this.vEXPDatos.getInt("lReqDiag") == 1) {
				this.vDiagnostico = new TDEXPDiagnostico().getDiagEspXServ(
						String.valueOf(vDatos.getICveExpediente()),
						String.valueOf(vDatos.getINumExamen()),
						String.valueOf(vDatos.getICveServicio()));
				this.vRemendacion = new TDEXPRecomendacion().getRecEspXServ(
						String.valueOf(vDatos.getICveExpediente()),
						String.valueOf(vDatos.getINumExamen()),
						String.valueOf(vDatos.getICveServicio()));
			}
			if (request.getParameter("hdReporte") != null) {
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {
					vcReporte = vDespliega;
					activeX.append(this.GenRep(vcReporte));
				}
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070104011");
		StringBuffer buffer = new StringBuffer();
		TVEXPResultado vEXPResultado = new TVEXPResultado();
		Vector vcResultado = new Vector();
		TFechas dtFecha = new TFechas("07");
		int iServicioCardio = new Integer(
				vParametros.getPropEspecifica("EPIServicioCardio")).intValue();
		int iRamaCardio = new Integer(
				vParametros.getPropEspecifica("EPIRamaCardio")).intValue();
		int iSintomaCardio = new Integer(
				vParametros.getPropEspecifica("EPISintomaCardio")).intValue();

		try {
			vcResultado = (Vector) obj;
			for (int j = 0; j < vcResultado.size(); j++) {
				vEXPResultado = (TVEXPResultado) vcResultado.get(j);
				if (vEXPResultado.getICveServicio() == iServicioCardio
						&& vEXPResultado.getICveRama() == iRamaCardio
						&& vEXPResultado.getICveSintoma() == iSintomaCardio) {
					xl.comDespliega("C", 8, vEXPResultado.getCCaracter());
					xl.comDespliega("I", 13, request.getParameter("hdCNombre"));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070104011", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	public com.micper.seguridad.vo.TVDinRep02 getVEXPDatos() {
		return vEXPDatos;
	}

	/**
	 * Metodo utilizado para obtener los par�metros enviados a la p�gina.
	 * 
	 * @return Object TVEXPRama Contiene la informaci�n del Expediente, el
	 *         n�mero de Examen, el servicio y la rama que se est�
	 *         consultado.
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		TVEXPRama vDatos = new TVEXPRama();
		try {
			cCampo = "" + request.getParameter("hdiCveExpediente");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveExpediente(iCampo);
			cCampo = "" + request.getParameter("hdiNumExamen");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setINumExamen(iCampo);
			cCampo = "" + request.getParameter("hdICveServicio");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveServicio(iCampo);
			cCampo = "" + request.getParameter("hdICveRama");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveRama(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vDatos;
	}

	/**
	 * Metodo utilizado para obtener los par�metros enviados a la p�gina.
	 * 
	 * @return Object TVEXPRama Contiene la informaci�n del Expediente, el
	 *         n�mero de Examen, el servicio y la rama que se est�
	 *         consultado.
	 */
	public Object getInputs05020() throws CFGException {
		String cCampo;
		int iCampo;
		TVEXPRama vDatos = new TVEXPRama();
		try {
			cCampo = "" + request.getParameter("hdICveExpediente");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveExpediente(iCampo);
			cCampo = "" + request.getParameter("hdINumExamen");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setINumExamen(iCampo);
			cCampo = "" + request.getParameter("hdICveServicio");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveServicio(iCampo);
			cCampo = "" + request.getParameter("hdICveRama");
			iCampo = (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) ? Integer
					.parseInt(cCampo, 10) : 0;
			vDatos.setICveRama(iCampo);
		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vDatos;
	}

	public long getEdadPersonal(java.sql.Date dtNacimiento) {
		java.sql.Date dtFecha = new java.sql.Date(
				new java.util.Date().getTime()); // fecha actual
		long edad = dtFecha.getTime() - dtNacimiento.getTime();
		edad /= (1000 * 60 * 60 * 24 * 365.25);
		return edad;
	}

	public java.util.Vector getVSintomas() {
		return vSintomas;
	}

	public java.util.Vector getVDiagnostico() {
		return vDiagnostico;
	}

	public java.util.Vector getVRemendacion() {
		return vRemendacion;
	}

}
