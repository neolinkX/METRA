package gob.sct.medprev;

import java.util.*;
import java.text.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.vo.*;

/**
 * Clase de configuracion para la Modificaci�n de Categor�as y Motivos
 * cuando el examen no ha sido inicilizado
 * <p>
 * Sistema MedPrev en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>AG SA L
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070107040CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070107040CFG.png'>
 */
public class pg070107040CFG extends CFGListBase2 {
	private String fechaFormateada = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private java.sql.Date d = null;
	private TFechas tf = new TFechas();
	private int iFlag = 0;

	public pg070107040CFG() {
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
				HashMap p = new HashMap();
				java.sql.Date dtFecha = new java.sql.Date(new Date().getTime()); // fecha
																					// actual

				// System.out.println(request.getParameter("hdBoton") );

				/*
				 * if(request.getParameter("iCveUniMed") != null &&
				 * request.getParameter("iCveUniMed").trim().length() > 0 &&
				 * Integer.parseInt(request.getParameter("iCveUniMed")) > 0 )
				 * p.put("iCveUniMed", request.getParameter("iCveUniMed"));
				 * 
				 * if(request.getParameter("iCveModulo") != null &&
				 * request.getParameter("iCveModulo").trim().length() > 0 &&
				 * Integer.parseInt(request.getParameter("iCveModulo")) > 0 )
				 * p.put("iCveModulo", request.getParameter("iCveModulo"));
				 * 
				 * if(request.getParameter("iCveProceso") != null &&
				 * request.getParameter("iCveProceso").trim().length() > 0 &&
				 * Integer.parseInt(request.getParameter("iCveProceso")) > 0 )
				 * p.put("iCveProceso",request.getParameter("iCveProceso"));
				 * 
				 * if(request.getParameter("iCveExpediente") != null &&
				 * request.getParameter("iCveExpediente").trim().length() > 0)
				 * p.put("iCveExpediente",
				 * request.getParameter("iCveExpediente"));
				 * 
				 * p.put("dtFecha", dtFecha); p.put("lDictaminado", "0");
				 * p.put("lInciado" , "0");
				 * 
				 * vDespliega = dEXPExamAplica.buscaXLiberarServicio(p);
				 * 
				 * // iNumReg = vDespliega.size();l
				 * 
				 * if(request.getParameter("iCveExpediente1") != null &&
				 * request.getParameter("iCveExpediente1").trim().length() > 0
				 * && Integer.parseInt(request.getParameter("iCveExpediente1"))
				 * > 0 ) //
				 * System.out.println(request.getParameter("iCveExpediente1"));
				 * 
				 * 
				 * if(request.getParameter("iCveExpediente2") != null &&
				 * request.getParameter("iCveExpediente2").trim().length() > 0
				 * && Integer.parseInt(request.getParameter("iCveExpediente2"))
				 * > 0 ) //
				 * System.out.println(request.getParameter("iCveExpediente2"));
				 */
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
				int iUpdate = 0;

				int iMaxValue = Integer.parseInt(request
						.getParameter("maxValue"));
				for (int i = 0; i < iMaxValue; i++) {
					if (request.getParameter("id" + i) != null) {
						TVEXPExamCat tvExamCat = new TVEXPExamCat();

						tvExamCat.setICveExpediente(Integer.parseInt(request
								.getParameter("iCveExpediente")));
						tvExamCat.setINumExamen(Integer.parseInt(request
								.getParameter("iNumExamen")));

						tvExamCat.setICveCategoriaInicial(Integer
								.parseInt(request
										.getParameter("iCveCategoriaInicial"
												+ i)));
						tvExamCat.setICvePuestoInicial(Integer.parseInt(request
								.getParameter("iCvePuestoInicial" + i)));

						String cP = request.getParameter("iCvePuesto" + i);

						tvExamCat
								.setICveMdoTransInicial(Integer.parseInt(request
										.getParameter("iCveMdoTransInicial" + i)));
						tvExamCat.setICveMdoTrans(Integer.parseInt(request
								.getParameter("iCveMdoTrans" + i)));
						tvExamCat.setICveMotivoInicial(Integer.parseInt(request
								.getParameter("iCveMotivoInicial" + i)));
						tvExamCat.setICveMotivo(Integer.parseInt(request
								.getParameter("iCveMotivo" + i)));

						tvExamCat.setICvePuesto(Integer.parseInt(cP.substring(
								0, cP.indexOf("|"))));
						tvExamCat.setICveCategoria(Integer.parseInt(cP
								.substring(cP.indexOf("|") + 1, cP.length())));

						// iUpdate = new TDEXPExamCat().UpdateExam(tvExamCat,
						// "0");
						/*
						 * //
						 * System.out.println("getICveExpediente: "+tvExamCat.
						 * getICveExpediente()); //
						 * System.out.println("getINumExamen: "
						 * +tvExamCat.getINumExamen()); //
						 * System.out.println("getICvePuestoInicial: "
						 * +tvExamCat.getICvePuestoInicial()); //
						 * System.out.println
						 * ("getICvePuesto: "+tvExamCat.getICvePuesto()); //
						 * System
						 * .out.println("getICveCategoriaInicial: "+tvExamCat
						 * .getICveCategoriaInicial()); //
						 * System.out.println("getICveCategoria: "
						 * +tvExamCat.getICveCategoria()); //
						 * System.out.println(
						 * "getICveMdoTransInicial: "+tvExamCat
						 * .getICveMdoTransInicial()); //
						 * System.out.println("getICveMdoTrans: "
						 * +tvExamCat.getICveMdoTrans()); //
						 * System.out.println("getICveMotivoInicial: "
						 * +tvExamCat.getICveMotivoInicial()); //
						 * System.out.println
						 * ("getICveMotivo: "+tvExamCat.getICveMotivo());
						 */
					}
				}

				if (iUpdate != 0)
					this.setIFlag(1);
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

	public Vector EXPMigra1() {
		Vector Expediente1 = new Vector();
		TDPERDatos dPERDatos = new TDPERDatos();
		String iCveExpediente = "";
		if (request.getParameter("iCveExpediente1") != null
				&& request.getParameter("iCveExpediente1").trim().length() > 0
				&& Integer.parseInt(request.getParameter("iCveExpediente1")) > 0) {
			// System.out.println(request.getParameter("iCveExpediente1"));
			iCveExpediente = request.getParameter("iCveExpediente1");
			try {
				Expediente1 = dPERDatos.Migraciones(iCveExpediente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Expediente1;
	}

	public Vector EXPMigra2(String iCveExpediente) {
		Vector Expediente2 = new Vector();
		TDPERDatos dPERDatos = new TDPERDatos();
		try {
			Expediente2 = dPERDatos.Migraciones(iCveExpediente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Expediente2;
	}

	public String MigrandoExp(int iCveExpediente1, int Examen1,
			int iCveExpediente2, int Examen2) {
		Vector Sentencias = new Vector();
		TDPERDatos dPERDatos = new TDPERDatos();
		String Resultado = "";
		int countR = 0;
		int existeL = 0;
		int existeT = 0;
		try {
			// validar si existe en Licencias
			// System.out.println(request.getParameter("hdIpAddress"));
			// System.out.println(request.getParameter("hdMacAddress"));
			// System.out.println(request.getParameter("hdComputerName"));
			TDLICPERLicencia dLICPERLicencia = new TDLICPERLicencia();
			existeL = dLICPERLicencia.FindByAll(iCveExpediente2 + "");
			// Validar si existe en toxicologia
			TDTOXMuestra dTOXMuestra = new TDTOXMuestra();
			existeT = dTOXMuestra.FindByAllM(iCveExpediente2 + "");

			if (existeL == 0 && existeT == 0) {

				for (int i = 1; i <= Examen2; i++) {
					// System.out.println("i = "+ i +"   secuencia examen = "
					// +(i + Examen1)+"");

					dPERDatos
							.Sentencia("update EXPEXAMAPLICA set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", ICVEPERSONAL = "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ "  where  ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ "   AND INUMEXAMEN="
									+ i + "");
					dPERDatos
							.Sentencia("update EXPEXAMCAT set ICVEEXPEDIENTE= "
									+ iCveExpediente1 + ", INUMEXAMEN= "
									+ (i + Examen1) + " where ICVEEXPEDIENTE="
									+ iCveExpediente2 + " AND  INUMEXAMEN=" + i
									+ "");
					dPERDatos
							.Sentencia("update EXPEXAMGENERA set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ "  AND INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPEXAMGRUPO set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ "  AND INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPEXAMPUESTO set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos.Sentencia("update EXPRAMA set ICVEEXPEDIENTE= "
							+ iCveExpediente1 + ", INUMEXAMEN= "
							+ (i + Examen1) + " where ICVEEXPEDIENTE="
							+ iCveExpediente2 + " AND  INUMEXAMEN=" + i + "");
					dPERDatos
							.Sentencia("update EXPRECOMENDACION set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPREQUISITOS set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPRESULTADO set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPSERVICIO set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPDICTAMENSERV set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update PERLICENCIA set ICVEPERSONAL =  "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEPERSONAL ="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					//
					dPERDatos
							.Sentencia("update EXPDIAGNOSTICO set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPFUNDICTAMEN set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPINTERCONSULTA set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");
					dPERDatos
							.Sentencia("update EXPSERVARCHCM set ICVEEXPEDIENTE= "
									+ iCveExpediente1
									+ ", INUMEXAMEN= "
									+ (i + Examen1)
									+ " where ICVEEXPEDIENTE="
									+ iCveExpediente2
									+ " AND  INUMEXAMEN="
									+ i
									+ "");

					// INSERTANDO EN BITACORA
					TVUsuario vUsuario = (TVUsuario) httpReq.getSession(true)
							.getAttribute("UsrID");
					String result = "";
					String Servicio = "0";
					// Calculando Timestamp para el campo TINIEXA
					java.util.Date utilDate = new java.util.Date(); // fecha
																	// actual
					long lnMilisegundos = utilDate.getTime();
					java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
							lnMilisegundos);
					// System.out.println("sql.Timestamp: "+sqlTimestamp);
					// Guardando el registro en bitacora de cambios
					String Nota1 = "Se migro el Expediente " + iCveExpediente2
							+ " al expediente " + iCveExpediente1;
					String Descripcion = Nota1;
					String cSQL = "insert into EXPBITMOD "
							+ "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, icveservicio, CMACADDRESS, CCOMPUTERNAME, CIPACCESO)"
							+ "values(" + iCveExpediente1 + ", "
							+ (i + Examen1) + ", 6, '" + sqlTimestamp + "', '"
							+ Descripcion + "', " + vUsuario.getICveusuario()
							+ ", " + Servicio + " , '"
							+ request.getParameter("hdMacAddress") + "' , '"
							+ request.getParameter("hdComputerName") + "' , '"
							+ request.getParameter("hdIpAddress") + "')";
					// GENERANDO DESCRIPCIÓN
					TDEXPServicio dEXPServicio = new TDEXPServicio();
					try {
						dEXPServicio.Sentencia(cSQL);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}
				}

				dPERDatos.Sentencia("update EPICISCITA set ICVEPERSONAL= "
						+ iCveExpediente1 + " where  ICVEPERSONAL="
						+ iCveExpediente2 + " ");
				// dPERDatos.Sentencia("update TOXMUESTRA set ICVEPERSONAL =  "+iCveExpediente1+" where ICVEPERSONAL ="+iCveExpediente2+" ");
				dPERDatos
						.Sentencia(" DELETE FROM PERDATOS WHERE ICVEEXPEDIENTE = "
								+ iCveExpediente2 + " ");
				dPERDatos
						.Sentencia(" DELETE FROM PERDIRECCION WHERE ICVEPERSONAL = "
								+ iCveExpediente2 + " ");

				// Resultado =
				// dPERDatos.Sentencia("update EXPRESULTADO set ICVEEXPEDIENTE= "+iCveExpediente1+"  , INUMEXAMEN= "+(i
				// +
				// Examen1)+" where ICVEEXPEDIENTE="+iCveExpediente2+"   INUMEXAMEN="+i+"");

				Resultado = " <div align=\"left\"><span class=\"ETituloTSist\">La información contenida en el expediente "
						+ iCveExpediente2
						+ " ha sido migrada al expediente "
						+ iCveExpediente1 + " satisfactoriamente.</span>";
			} else {
				Resultado = "LA INFORMACIÓN CONTENIDA EN EL EXPEDIENTE "
						+ iCveExpediente2 + " NO ES POSIBLE MIGRARSE.";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Resultado;
	}

}
