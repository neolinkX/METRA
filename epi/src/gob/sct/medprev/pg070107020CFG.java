package gob.sct.medprev;

import java.util.*;
import java.text.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

/**
 * Clase de configuracion para Clase de configuracion para Listado de
 * EXPExamAplica
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105000CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105000CFG.png'>
 */
public class pg070107020CFG extends CFGListBase2 {
	private String fechaFormateada = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private java.sql.Date d = null;
	private TFechas tf = new TFechas();
	private int iFlag = 0;

	public pg070107020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {

			if (request.getParameter("hdBoton") != null
					&& (request.getParameter("hdBoton").equals("Buscar") || request
							.getParameter("hdBoton").equals("GuardarA"))) {

				TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
				String cCveUniMed = "";
				String cCveModulo = "";
				String cCveProceso = "";
				String cCveExpediente = "";
				java.sql.Date dtFecha = new java.sql.Date(new Date().getTime()); // fecha
																					// actual

				if (request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveUniMed").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveUniMed")) > 0)
					cCveUniMed = request.getParameter("iCveUniMed");
				if (request.getParameter("iCveModulo") != null
						&& request.getParameter("iCveModulo").trim().length() > 0
						&& Integer.parseInt(request.getParameter("iCveModulo")) > 0)
					cCveModulo = request.getParameter("iCveModulo");
				if (request.getParameter("iCveProceso") != null
						&& request.getParameter("iCveProceso").trim().length() > 0
						&& Integer
								.parseInt(request.getParameter("iCveProceso")) > 0)
					cCveProceso = request.getParameter("iCveProceso");
				if (request.getParameter("iCveExpediente") != null
						&& request.getParameter("iCveExpediente").trim()
								.length() > 0)
					cCveExpediente = request.getParameter("iCveExpediente");

				HashMap p = new HashMap();
				p.put("iCveUniMed", cCveUniMed);
				p.put("iCveProceso", cCveProceso);
				p.put("iCveModulo", cCveModulo);
				p.put("iCveExpediente", cCveExpediente);
				p.put("dtFecha", dtFecha);
				p.put("lDictaminado", "1");
				vDespliega = dEXPExamAplica.buscaXLiberarDictamen(p);

				// iNumReg = vDespliega.size();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	public void GuardarA() {
		try {
			if (request.getParameter("iCveExpediente") != null
					&& request.getParameter("iCveExpediente").trim().length() > 0
					&& request.getParameter("iNumExamen") != null
					&& request.getParameter("iNumExamen").trim().length() > 0) {

				boolean bUpdate = false;
				String cveExp = request.getParameter("iCveExpediente");
				String numExam = request.getParameter("iNumExamen");
				// System.out.println("iCveExpediente: "+cveExp);
				// System.out.println("iNumExamen: "+numExam);
				Vector vectorConsulta = new TDEXPExamCat()
						.FindByAll("WHERE iCveExpediente = " + cveExp
								+ " AND iNumExamen = " + numExam
								+ " order by INUMEXAMEN desc");
				TVEXPExamCat examen = (TVEXPExamCat) vectorConsulta.get(0);
				bUpdate = new TDEXPExamCat().FreeService(
						Integer.parseInt(cveExp), Integer.parseInt(numExam));
				TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
						.getAttribute("UsrID");
				// System.out.println("Usuario: "+vUsuario.getCUsuario() + " " +
				// vUsuario.getICveusuario());
				// System.out.println("examen: "+examen.getLDictamen());
				if (bUpdate) {
					this.setIFlag(1);
					ExpBitMod mod = new ExpBitMod();
					mod.setiCveExpediente(cveExp);
					mod.setiNumExamen(numExam);
					mod.setOperacion("3");// de liberar dictamen
					mod.setDescripcion("Se libera el dictamen que tenia antes ldictamen = "
							+ examen.getLDictamen());
					mod.setiCveUsuarioRealiza(String.valueOf(vUsuario
							.getICveusuario()));
					mod.setlDictamen(String.valueOf(examen.getLDictamen()));
					mod.setIpAddress(request.getParameter("hdIpAddress"));
					mod.setMacAddress(request.getParameter("hdMacAddress"));
					mod.setComputerName(request.getParameter("hdComputerName"));
					int insert = new TDEXPBITMOD().insertDictamenesIpMacName(
							null, mod);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatDate(String fecha) {
		if (fecha == null || fecha.indexOf("-") == -1)
			return "&nbsp;";
		d = tf.getSQLDatefromSQLString(fecha);
		fechaFormateada = sdf.format(d);
		return fechaFormateada;
	}

	public int getIFlag() {
		return iFlag;
	}

	private void setIFlag(int iFlag) {
		this.iFlag = iFlag;
	}

}