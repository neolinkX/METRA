package gob.sct.medprev;

import java.util.*;
import java.text.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import gob.sct.ingresosws.ws.ConUsrIng.*;
import gob.sct.ingresosws.ws.ConAreaRec.TVConceptoII;
import gob.sct.ingresos.TEncriptaDatos;
import com.micper.util.TNumeros;
import com.micper.util.TDIngresos;
import com.micper.util.TFechas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070103010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070103010CFG.png'>
 */
public class pg070103011CFG extends CFGListBase2 {

	private String cParametros;
	private TVDinRep02 vInfoIng;

	public pg070103011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070103010.jsp";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		Vector vcRegistros = null;
		Vector vcPuestos = null;
		try {
			if (!cAccion.equals("Guardar")) {
				vcPuestos = getPuestosSeleccionados();
				// httpReq.getSession().setAttribute("pg07010301x.vcPuestos",vcPuestos);
				vcRegistros = new TDGRLPuesto().FindByAll(getParameters(""),
						buildPuestos(vcPuestos));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
		if (vcRegistros != null) {
			vDespliega = vcRegistros;
		} else {
			vDespliega = new Vector();
		}
	}

	/**
	 * Metodo Guardar
	 */
//	public void Guardar() {
	public String Guardar2(String id,String modotrans,String categoria,String puesto,
			String motivo,String cunimed,String cmodulo,String iduser, String lNuevo) {
		String registroCompleto = "";
		DbConnection dbConn = null;
		java.sql.Connection conn = null;
		try {
			dbConn = new DbConnection(
					vParametros.getPropEspecifica("ConDBModulo"));
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
			System.out.println("tra 1");
			// obten los datos del personal de PERDatos y construye un
			// TVEXPExamAplica
			HashMap hmParams = getParameters(id);
			TVPERDatos vPerDatos = findUser(id);
			java.sql.Date dtHoy = new java.sql.Date(new Date().getTime());
			TVEXPExamAplica vEXPExamAplica = buildEXPExamAplica(vPerDatos,
					hmParams, dtHoy,iduser);
			int iEXPExamAplica = 0;
			System.out.println("tra 2");
			// Trata de inserta el TVEXPExamAplica en EXPExamAplica, verifica
			// que haya concluido con los examenes anteriores
			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			if (dEXPExamAplica.noConcluido(vEXPExamAplica.getICveExpediente()) == true) {
				//vErrores.acumulaError("", 0,"No se llevo a cabo la operación porque aun tiene examenes sin concluir.");
				registroCompleto ="No se llevo a cabo la operación porque aun tiene examenes sin concluir.";
				return registroCompleto;
			}
			System.out.println("tra 3");			
			TVEXPExamAplica vExam = dEXPExamAplica.CategAdicional(
					vEXPExamAplica.getICveExpediente(), Integer
							.parseInt(vParametros
									.getPropEspecifica("EPIProceso")));

			
			/*if (vExam.getINumExamen() == 0
					|| (request.getParameter("lNuevo") != null && "true"
							.compareToIgnoreCase(request.getParameter("lNuevo")
									.toString()) == 0)) {*/
			if (vExam.getINumExamen() == 0
					|| (lNuevo != null && "true"
							.compareToIgnoreCase(lNuevo) == 0)) {

				dEXPExamAplica.insert(conn, vEXPExamAplica);
				System.out.println("tra 4");
				// agrega a los parametros los datos extras para insertar en las
				// demas tablas
				hmParams.put("iCveExpediente",
						Integer.toString(vPerDatos.getICveExpediente()));
				hmParams.put("iNumExamen",
						Integer.toString(vEXPExamAplica.getINumExamen()));
				System.out.println("tra 5");
				// inserta en las demas tablas dependiendo de los parametros que
				// le pasaron
				Vector vcEXPExamCat = new Vector();
				Vector vcEXPExamGrupo = new Vector();
				Vector vcEXPExamPuesto = new Vector();
				Vector vcPERTpoLicencia = new Vector();
				HashMap hmMotivo = null;
				HashMap hmGrupo = null;
				TVEXPExamCat vEXPExamCat = null;
				TVEXPExamGrupo vEXPExamGrupo = null;
				/*for (Enumeration e = request.getParameterNames(); e
						.hasMoreElements();) {
					System.out.println("tra 6");
					String cParam = (String) e.nextElement();
					if (cParam.toUpperCase().startsWith("CBO|")) {
						hmMotivo = buildMotivo(cParam,
								request.getParameter(cParam));
						vcPERTpoLicencia.add(buildTVPERTpoLicencia(hmParams,
								hmMotivo));
						vEXPExamCat = buildTVEXPExamCat(hmParams, hmMotivo);
						if (!vcEXPExamCat.contains(vEXPExamCat)) {
							vcEXPExamCat.add(vEXPExamCat);
						}
					} else if (cParam.toUpperCase().startsWith("CB|")) {
						hmGrupo = buildGrupo(cParam,
								request.getParameter(cParam));
						vcEXPExamPuesto.add(buildTVEXPExamPuesto(hmParams,
								hmGrupo));
						vEXPExamGrupo = buildTVEXPExamGrupo(hmParams, hmGrupo);
						if (!vcEXPExamGrupo.contains(vEXPExamGrupo)) {
							vcEXPExamGrupo.add(vEXPExamGrupo);
						}
					}
				}*/
				
				////////////NUevo/////////////////////////////////////////
					System.out.println("tra 6");
					String cParam="CB|"+modotrans+"|"+categoria+"|"+puesto+"|"+motivo+"|";
					if (cParam.toUpperCase().startsWith("CBO|")) {
						System.out.println("tra 6 ="+cParam);
						hmMotivo = buildMotivo(cParam,motivo);
						vcPERTpoLicencia.add(buildTVPERTpoLicencia(hmParams,
								hmMotivo));
						vEXPExamCat = buildTVEXPExamCat(hmParams, hmMotivo);
						if (!vcEXPExamCat.contains(vEXPExamCat)) {
							vcEXPExamCat.add(vEXPExamCat);
						}
					} else if (cParam.toUpperCase().startsWith("CB|")) {
						System.out.println("tra 6- ="+cParam);
						hmGrupo = buildGrupo(cParam,motivo);
						vcEXPExamPuesto.add(buildTVEXPExamPuesto(hmParams,
								hmGrupo));
						vEXPExamGrupo = buildTVEXPExamGrupo(hmParams, hmGrupo);
						if (!vcEXPExamGrupo.contains(vEXPExamGrupo)) {
							vcEXPExamGrupo.add(vEXPExamGrupo);
						}
						vEXPExamCat = buildTVEXPExamCat(hmParams, hmGrupo);
						if (!vcEXPExamCat.contains(vEXPExamCat)) {
							vcEXPExamCat.add(vEXPExamCat);
						}
					}
				
				
				
				
				
				System.out.println("tra 7");
				// Validamos que la persona que realiza la cita no sea tercero
				boolean validacionTerceros = true;
				for (int x = 0; x < vcEXPExamCat.size(); x++) {
					System.out.println("tra 8");
					TVEXPExamCat EXAMCAT = (TVEXPExamCat) vcEXPExamCat.get(x);

					int esTercero = 0;// No es tercero
					String cSQLValidaTerceros = "SELECT LPRIVADA FROM GRLUNIMED WHERE ICVEUNIMED =  "
							+ cunimed;
					// System.out.println(cSQLValidaTerceros);
					PreparedStatement pstmtValidaTerceros = conn
							.prepareStatement(cSQLValidaTerceros);
					ResultSet rsetValidaTerceros = pstmtValidaTerceros
							.executeQuery();
					while (rsetValidaTerceros.next()) {
						esTercero = rsetValidaTerceros.getInt(1);
					}
					System.out.println("tra 9");
					if (EXAMCAT.getICveMotivo() == 5 && esTercero == 1) {// Si
																			// es
																			// REVALORACION
																			// y
																			// es
																			// TERCERO
						cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
								+ "FROM EXPEXAMCAT A "
								+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
								+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
								+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
								+ "WHERE A.ICVEEXPEDIENTE = "
								+ vPerDatos.getICveExpediente()
								+ " AND B.ICVEPROCESO = 1 "
								+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
						// System.out.println(cSQLValidaTerceros);
						PreparedStatement pstmtValidaTerceros2 = conn
								.prepareStatement(cSQLValidaTerceros);
						ResultSet rsetValidaTerceros2 = pstmtValidaTerceros2
								.executeQuery();
						System.out.println("tra 10");
						int ldictamen = 1;// APTO
						int ldictaminado = 1;// DICTAMINADO
						int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
						while (rsetValidaTerceros2.next()) {// Se quedara con
															// los valores del
															// ultimo examen por
															// el ciclo
							ldictamen = rsetValidaTerceros2.getInt(1);
							ldictaminado = rsetValidaTerceros2.getInt(2);
							icveunimedEsTercero = rsetValidaTerceros2.getInt(3);
							// if (ldictamen == 0 && ldictaminado == 1)
							// validacionTerceros = false;
						}
						if (ldictamen == 0 && ldictaminado == 1
								&& icveunimedEsTercero == 0) {// Si el ultimo
																// examen fue
																// DICTAMINADO y
																// resulto NO
																// APTO y fue
																// por
																// DICTAMINADO
																// POR LA SCT
							// if (ldictamen == 0 && ldictaminado == 1) {//Si el
							// ultimo examen fue DICTAMINADO y resulto NO APTO y
							// fue por DICTAMINADO POR LA SCT
							validacionTerceros = false;
							//vErrores.acumulaError("",0,"No estás autorizado para realizar este trámite, ya que este expediente en su último examen resulto no Apto. Favor de contactar al Administrador del Sistema.");
							registroCompleto = "No estás autorizado para realizar este trámite, ya que este expediente en su último examen resulto no Apto. Favor de contactar al Administrador del Sistema.";
							return registroCompleto;
						}
					}
					System.out.println("tra 11");
					if (EXAMCAT.getICveMotivo() == 49 && esTercero == 1) {
						validacionTerceros = false;
						// System.out.println("TErcero y motivo 49");
						vErrores.acumulaError("",0,"Este expediente, su último examen médico fue realizado por el motivo de Evaluación técnica del desempeño, por lo cual tendrán que acudir a una Unidad Médica para la realización de su examen.");
						registroCompleto = "Este expediente, su último examen médico fue realizado por el motivo de Evaluación técnica del desempeño, por lo cual tendrán que acudir a una Unidad Médica para la realización de su examen.";
						return registroCompleto;
					}
				}
				System.out.println("tra 12");
				// FIN DE Validamos que la persona que realiza la cita no sea
				// tercero

				if (validacionTerceros == true) {
					// System.out.println("terceros uno");
					new TDEXPExamGenera().insert(conn,
							buildTVEXPExamGenera(vEXPExamAplica, dtHoy));
					System.out.println("vector cat="+vcEXPExamCat.size());
					new TDEXPExamCat().insert(conn, vcEXPExamCat);
					// new TDEXPExamGrupo().FindByAll()
					new TDEXPExamGrupo().insert(conn, vcEXPExamGrupo);
					new TDEXPExamPuesto().insert(conn, vcEXPExamPuesto);
					// new TDPERTpoLicencia().insert(conn,vcPERTpoLicencia);
					conn.commit();
					//vErrores.acumulaError("", 0,"Se guardaron correctamente los datos.");
					registroCompleto = "success";
					//return registroCompleto;
				} else {
					System.out.println("tra 13");
					conn.commit();
					String cSQLB = "SELECT MAX(INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
							+ vPerDatos.getICveExpediente() + " ";
					int maximo = 0;
					String cSQLB2 = "";
					TDEXPServicio dEXPServicio = new TDEXPServicio();
					String Recibe = "";
					try {
						maximo = dEXPExamAplica.RegresaInt(cSQLB);
						// System.out.println("examen = "+maximo);
						cSQLB2 = "DELETE FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
								+ vPerDatos.getICveExpediente()
								+ " AND INUMEXAMEN = " + maximo + "";
						Recibe = dEXPServicio.Sdelete(cSQLB2);
						// System.out.println("recibe = "+Recibe);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}
				}System.out.println("tra 14");
			} // Validacion de NoConcluido
			else {
				System.out.println("tra 15");
				// Hay quq agregar el calculo del ultimo examen sin concluir
				// iEXPExamAplica = dEXPExamAplica.FindLastEPI("" +
				// vPerDatos.getICveExpediente
				// (),vParametros.getPropEspecifica("EPIProceso") );
				iEXPExamAplica = vExam.getINumExamen();
				// agrega a los parametros los datos extras para insertar en las
				// demas tablas
				hmParams.put("iCveExpediente",
						Integer.toString(vPerDatos.getICveExpediente()));
				hmParams.put("iNumExamen", "" + iEXPExamAplica);
				System.out.println("tra 16");
				// inserta en las demas tablas dependiendo de los parametros que
				// le pasaron
				Vector vcEXPExamCat = new Vector();
				Vector vcEXPExamGrupo = new Vector();
				Vector vcEXPExamPuesto = new Vector();
				Vector vcPERTpoLicencia = new Vector();
				HashMap hmMotivo = null;
				HashMap hmGrupo = null;
				TVEXPExamCat vEXPExamCat = null;
				TVEXPExamGrupo vEXPExamGrupo = null;
				for (Enumeration e = request.getParameterNames(); e
						.hasMoreElements();) {
					String cParam = (String) e.nextElement();
					if (cParam.toUpperCase().startsWith("CBO|")) {
						hmMotivo = buildMotivo(cParam,
								request.getParameter(cParam));
						vcPERTpoLicencia.add(buildTVPERTpoLicencia(hmParams,
								hmMotivo));
						vEXPExamCat = buildTVEXPExamCat(hmParams, hmMotivo);
						if (!vcEXPExamCat.contains(vEXPExamCat)) {
							vcEXPExamCat.add(vEXPExamCat);
						}
					} else if (cParam.toUpperCase().startsWith("CB|")) {
						hmGrupo = buildGrupo(cParam,request.getParameter(cParam));
						vcEXPExamPuesto.add(buildTVEXPExamPuesto(hmParams,hmGrupo));
						vEXPExamGrupo = buildTVEXPExamGrupo(hmParams, hmGrupo);
						if (!vcEXPExamGrupo.contains(vEXPExamGrupo)) {
							vcEXPExamGrupo.add(vEXPExamGrupo);
						}
					}
				}
				System.out.println("tra 17");
				// Validamos que la persona que realiza la cita no sea tercero
				boolean validacionTerceros = true;
				for (int x = 0; x < vcEXPExamCat.size(); x++) {

					TVEXPExamCat EXAMCAT = (TVEXPExamCat) vcEXPExamCat.get(x);

					int esTercero = 0;// No es tercero
					String cSQLValidaTerceros = "SELECT LPRIVADA FROM GRLUNIMED WHERE ICVEUNIMED =  "
							+ request.getParameter("hdICveUniMed");
					// System.out.println(cSQLValidaTerceros);
					PreparedStatement pstmtValidaTerceros = conn
							.prepareStatement(cSQLValidaTerceros);
					ResultSet rsetValidaTerceros = pstmtValidaTerceros
							.executeQuery();
					while (rsetValidaTerceros.next()) {
						esTercero = rsetValidaTerceros.getInt(1);
					}
					System.out.println("tra 18");
					if (EXAMCAT.getICveMotivo() == 5 && esTercero == 1) {// Si
																			// es
																			// REVALORACION
																			// y
																			// es
																			// TERCERO
						cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
								+ "FROM EXPEXAMCAT A "
								+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
								+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
								+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
								+ "WHERE A.ICVEEXPEDIENTE = "
								+ vPerDatos.getICveExpediente()
								+ " AND B.ICVEPROCESO = 1 "
								+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
						// System.out.println(cSQLValidaTerceros);
						PreparedStatement pstmtValidaTerceros2 = conn
								.prepareStatement(cSQLValidaTerceros);
						ResultSet rsetValidaTerceros2 = pstmtValidaTerceros2
								.executeQuery();
						int ldictamen = 1;// APTO
						int ldictaminado = 1;// DICTAMINADO
						int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
						while (rsetValidaTerceros2.next()) {// Se quedara con
															// los valores del
															// ultimo examen por
															// el ciclo
							ldictamen = rsetValidaTerceros2.getInt(1);
							ldictaminado = rsetValidaTerceros2.getInt(2);
							icveunimedEsTercero = rsetValidaTerceros2.getInt(3);
						}
						System.out.println("tra 19");
						if (ldictamen == 0 && ldictaminado == 1
								&& icveunimedEsTercero == 0) {// Si el ultimo
																// examen fue
																// DICTAMINADO y
																// resulto NO
																// APTO y fue
																// por
																// DICTAMINADO
																// POR LA SCT
							// if (ldictamen == 0 && ldictaminado == 1) {//Si el
							// ultimo examen fue DICTAMINADO y resulto NO APTO y
							// fue por DICTAMINADO POR LA SCT
							validacionTerceros = false;
							//vErrores.acumulaError("",0,"No estás autorizado para realizar este trámite, ya que este expediente en su último examen resulto no Apto. Favor de contactar al Administrador del Sistema.");
							registroCompleto="No estás autorizado para realizar este trámite, ya que este expediente en su último examen resulto no Apto. Favor de contactar al Administrador del Sistema.";
							return registroCompleto;
						}
					}
					System.out.println("tra 20");
					if (EXAMCAT.getICveMotivo() == 49 && esTercero == 1) {
						validacionTerceros = false;
						System.out.println("TErcero y motivo 49");
						//vErrores.acumulaError("",0,"Este expediente, su último examen médico fue realizado por el motivo de Evaluación técnica del desempeño, por lo cual tendrán que acudir a una Unidad Médica para la realización de su examen.");
						registroCompleto="Este expediente, su último examen médico fue realizado por el motivo de Evaluación técnica del desempeño, por lo cual tendrán que acudir a una Unidad Médica para la realización de su examen.";
						return registroCompleto;
					}

				}
				// FIN DE Validamos que la persona que realiza la cita no sea
				// tercero

				if (validacionTerceros == true) {
					// System.out.println("terceros dos");
					// new TDEXPExamGenera
					// ().insert(conn,buildTVEXPExamGenera(vEXPExamAplica,dtHoy));
					new TDEXPExamCat().insert(conn, vcEXPExamCat);
					// new TDEXPExamGrupo ().insertverifica(conn,vcEXPExamGrupo
					// );
					new TDEXPExamPuesto().insert(conn, vcEXPExamPuesto);
					// new TDPERTpoLicencia().insert(conn,vcPERTpoLicencia);

					conn.commit();
					//vErrores.acumulaError("", 0,"Se agregaron correctamente los datos.");
					registroCompleto="success";
					//return registroCompleto;
				} else {
					System.out.println("tra 21");
					conn.commit();
					String cSQLB = "SELECT MAX(INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
							+ vPerDatos.getICveExpediente() + " ";
					int maximo = 0;
					String cSQLB2 = "";
					TDEXPServicio dEXPServicio = new TDEXPServicio();
					String Recibe = "";
					try {
						maximo = dEXPExamAplica.RegresaInt(cSQLB);
						// System.out.println("examen = "+maximo);
						cSQLB2 = "DELETE FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
								+ vPerDatos.getICveExpediente()
								+ " AND INUMEXAMEN = " + maximo + "";
						Recibe = dEXPServicio.Sdelete(cSQLB2);
						// System.out.println("recibe = "+Recibe);
					} catch (Exception ex) {
						warn("Sentencia", ex);
					}
				}
			}
			System.out.println("tra 22");
			if(registroCompleto.length()<2){
				registroCompleto="Fin del tramite";
				registroCompleto = "success";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("Guardar", ex1);
			}
			vErrores.acumulaError("", 14, "");
			error("Error al insertar los registros", ex);
			registroCompleto="Error al insertar los registros";
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				ex2.printStackTrace();
				warn("Guardar.close", ex2);
			}
			//super.Guardar();
			return registroCompleto;
		}
		//return registroCompleto;
	}

	/**
	 * Metodo findUser
	 */
	public TVPERDatos findUser(String id) {
		TVPERDatos vPerDat = null;
		try {
			System.out.println("id find user="+id);
			String cTmp = id;
			int iCvePersonal = cTmp == null ? 0 : Integer.parseInt(cTmp);
			vPerDat = new TDPERDatos().findUser(iCvePersonal);
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	/**
	 * Metodo findUserComplete
	 * 
	 * @return TVPERDatos
	 */
	public TVPERDatos findUserComplete() {
		TVPERDatos vPerDat = null;
		Vector vcPerDat = new Vector();
		try {
			String cTmp = request.getParameter("hdICvePersonal");
			vcPerDat = new TDPERDatos().FindByAll(cTmp);
			vPerDat = (TVPERDatos) vcPerDat.get(0);
		} catch (Exception ex) {
			error("findUserComplete", ex);
		}
		return vPerDat;
	}

	/**
	 * Metodo estaEnNoAptos
	 */
	public boolean estaEnNoAptos(String cCveMdotrans, String cCveCategoria) {
		boolean bTmp = false;
		try {

			String cCvePersonal = request.getParameter("hdICvePersonal");
			int iCvePersonal = cCvePersonal == null ? 0 : Integer
					.parseInt(cCvePersonal);
			int iCveMdotrans = cCveMdotrans == null ? 0 : Integer
					.parseInt(cCveMdotrans);
			int iCveCategoria = cCveCategoria == null ? 0 : Integer
					.parseInt(cCveCategoria);
			// Validar la categorÃ¯Â¿Â½a especÃ¯Â¿Â½fica
			bTmp = new TDPERCatalogoNoAp().estaEnNoAptos(iCvePersonal,
					iCveMdotrans, iCveCategoria);
			// Si no se encontrÃ¯Â¿Â½ la categorÃ¯Â¿Â½a especÃ¯Â¿Â½fica se busca
			// como desconocida.
			if (!bTmp) {
				iCveCategoria = Integer.parseInt(vParametros
						.getPropEspecifica("EPICatDesconocida"));
				bTmp = new TDPERCatalogoNoAp().estaEnNoAptos(iCvePersonal,
						iCveMdotrans, iCveCategoria);
			}
		} catch (Exception ex) {
			error("estaEnNoAptos", ex);
		}
		return bTmp;
	}

	/**
	 * Metodo getEdad
	 */
	public int getEdad(Date dtFechaNac) {
		Calendar hoy = Calendar.getInstance();
		Calendar nac = Calendar.getInstance();
		nac.setTime(dtFechaNac);
		int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
		if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
				.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE)) {
			edad--;
		}
		return edad;
	}

	/**
	 * Metodo getMotivos
	 */
	public Vector getMotivos() {
		Vector vcMotivos = null;
		try {
			String cTmp = vParametros.getPropEspecifica("EPIProceso");
			int iCveProceso = cTmp == null ? 0 : Integer.parseInt(cTmp);
			String cTmp2 = request.getParameter("hdICveUniMed");
			int unimed = cTmp2 == null ? 0 : Integer.parseInt(cTmp2);
			if (unimed == 107) {
				vcMotivos = new TDGRLMotivo().getMotivosTerceros(iCveProceso);
			} else {
				vcMotivos = new TDGRLMotivo().getMotivos(iCveProceso);
			}
		} catch (Exception ex) {
			error("getMotivos", ex);
		}
		return vcMotivos;
	}

	/**
	 * Metodo getParameters
	 */
	private HashMap getParameters(String id) {
		HashMap hmTmp = new HashMap();
		System.out.println("id getparameters="+id);
		String cTmp = vParametros.getPropEspecifica("EPIProceso");
		hmTmp.put("iCveProceso", cTmp != null ? cTmp : "0");
		//cTmp = request.getParameter("hdICveUniMed");
		cTmp = "1";
		hmTmp.put("iCveUniMed", cTmp != null ? cTmp : "0");
		//cTmp = request.getParameter("hdICveModulo");
		cTmp = "1";
		hmTmp.put("iCveModulo", cTmp != null ? cTmp : "0");
		cTmp = id;
		hmTmp.put("iCvePersonal", cTmp != null ? cTmp : "0");
		return hmTmp;
	}

	/**
	 * Metodo buildMotivo
	 */
	private HashMap buildMotivo(String cParam, String cValue) {
		HashMap hmTmp = new HashMap();
		String[] cTmp = cParam.split("\\|");
		hmTmp.put("iCveMdoTrans", cTmp[1]);
		hmTmp.put("iCvePuesto", cTmp[2]);
		hmTmp.put("iCveCategoria", cTmp[3]);
		hmTmp.put("iCveGrupo", cTmp[4]);
		hmTmp.put("iCveMotivo", cValue);
		return hmTmp;
	}

	/**
	 * Metodo buildGrupo
	 */
	private HashMap buildGrupo(String cParam, String cValue) {
		HashMap hmTmp = new HashMap();
		String[] cTmp = cParam.split("\\|");
		hmTmp.put("iCveMdoTrans", cTmp[1]);
		//hmTmp.put("iCvePuesto", cTmp[2]);
		//hmTmp.put("iCveCategoria", cTmp[3]);
		hmTmp.put("iCveCategoria", cTmp[2]);
		hmTmp.put("iCvePuesto", cTmp[3]);

		hmTmp.put("iCveGrupo", cTmp[4]);
		hmTmp.put("iCveMotivo", cValue);
		hmTmp.put("iCvePerfil", getPerfil(cTmp[1], cTmp[4]));
		System.out.println("buil="+cTmp[1]);
		System.out.println("buil="+cTmp[2]);
		System.out.println("buil="+cTmp[3]);
		System.out.println("buil="+cTmp[4]);
		return hmTmp;
	}

	/**
	 * Metodo getPerfil
	 */
	private String getPerfil(String cCveMdoTrans, String cCveGrupo) {
		String cRet = "0";
		String cWhere = " where lVigente=1 and iCveMdoTrans=" + cCveMdoTrans
				+ " and iCveGrupo=" + cCveGrupo;
		try {
			Vector vcTmp = new TDMEDPerfilMC().findByWhere(cWhere, "");
			if (vcTmp.size() != 0) {
				cRet = Integer.toString(((TVMEDPerfilMC) vcTmp.get(0))
						.getICvePerfil());
			}
		} catch (Exception ex) {
			error("getPerfil", ex);
		}
		return cRet;
	}

	/**
	 * Metodo getPuestosSeleccionados
	 */
	private Vector getPuestosSeleccionados() {
		Vector vcPuestos = new Vector();
		for (Enumeration eParams = request.getParameterNames(); eParams
				.hasMoreElements();) {
			String cParam = ((String) eParams.nextElement()).toUpperCase();
			if (cParam.startsWith("CB|")) {
				vcPuestos.add(cParam);
			}
		}
		return vcPuestos;
	}

	/**
	 * Metodo buildPuestos
	 */
	private String buildPuestos(Vector vcPuestos) {
		StringBuffer sbTmp = new StringBuffer(0);
		for (Enumeration ePuestos = vcPuestos.elements(); ePuestos
				.hasMoreElements();) {
			String cParam = ((String) ePuestos.nextElement());
			String[] asTmp = cParam.split("\\|");
			sbTmp.append("(").append(asTmp[1]).append(",").append(asTmp[2])
					.append("),");
		}
		String cTmp = sbTmp.toString();
		return cTmp.length() > 0 ? cTmp.substring(0, cTmp.length() - 1) : "";
	}

	/**
	 * Metodo buildEXPExamAplica
	 */
	private TVEXPExamAplica buildEXPExamAplica(TVPERDatos vPer,
			HashMap hmParams, java.sql.Date dtHoy, String id) {
		TVEXPExamAplica vTmp = new TVEXPExamAplica();
		vTmp.setICveExpediente(vPer.getICveExpediente());
		vTmp.setICvePersonal(vPer.getICvePersonal());
		vTmp.setICveNumEmpresa(vPer.getICveNumEmpresa());
		vTmp.setICveUniMed(Integer.parseInt((String) hmParams.get("iCveUniMed")));
		vTmp.setICveProceso(Integer.parseInt((String) hmParams
				.get("iCveProceso")));
		vTmp.setICveModulo(Integer.parseInt((String) hmParams.get("iCveModulo")));
		vTmp.setDtSolicitado(dtHoy);
		vTmp.setINumExamen(0); // se lo ponemos en cero y a la hora de insertar
								// lo calculamos
		vTmp.setIFolioEs(0); // por mientras
		vTmp.setLIniciado(0);
		vTmp.setLDictaminado(0);
		vTmp.setLInterConsulta(0);
		vTmp.setLInterConcluye(0);
		vTmp.setLConcluido(0);
		vTmp.setDtAplicacion(null);
		vTmp.setDtConcluido(null);
		vTmp.setDtDictamen(null);
		vTmp.setDtEntregaRes(null);
		vTmp.setDtArchivado(null);
		vTmp.setICveUsuSolicita(Integer.parseInt(id));
		return vTmp;
	}

	/**
	 * Metodo buildTVEXPExamCat
	 */
	private TVEXPExamCat buildTVEXPExamCat(HashMap hmParams, HashMap hmMotivo) {
		TVEXPExamCat vTmp = new TVEXPExamCat();
		vTmp.setICveExpediente(Integer.parseInt((String) hmParams
				.get("iCveExpediente")));
		vTmp.setINumExamen(Integer.parseInt((String) hmParams.get("iNumExamen")));
		vTmp.setICveMdoTrans(Integer.parseInt((String) hmMotivo
				.get("iCveMdoTrans")));
		vTmp.setICveCategoria(Integer.parseInt((String) hmMotivo
				.get("iCveCategoria")));
		vTmp.setICveMotivo(Integer.parseInt((String) hmMotivo.get("iCveMotivo")));
		vTmp.setLDictamen(1); // por mientras
		vTmp.setCRefAlfanum(""); // por mientras
		vTmp.setDtMovIngreso(null);
		vTmp.setIVigMeses(0);
		vTmp.setDtInicioVig(null);
		vTmp.setDtFinVig(null);
		return vTmp;
	}

	/**
	 * Metodo buildTVEXPExamPuesto
	 */
	private TVEXPExamPuesto buildTVEXPExamPuesto(HashMap hmParams,
			HashMap hmMotivo) {
		TVEXPExamPuesto vTmp = new TVEXPExamPuesto();
		vTmp.setICveExpediente(Integer.parseInt((String) hmParams
				.get("iCveExpediente")));
		vTmp.setINumExamen(Integer.parseInt((String) hmParams.get("iNumExamen")));
		vTmp.setICveMdoTrans(Integer.parseInt((String) hmMotivo
				.get("iCveMdoTrans")));
		vTmp.setICvePuesto(Integer.parseInt((String) hmMotivo.get("iCvePuesto")));
		return vTmp;
	}

	/**
	 * Metodo buildTVEXPExamGrupo
	 */
	private TVEXPExamGrupo buildTVEXPExamGrupo(HashMap hmParams, HashMap hmGrupo) {
		TVEXPExamGrupo vTmp = new TVEXPExamGrupo();
		vTmp.setICveExpediente(Integer.parseInt((String) hmParams
				.get("iCveExpediente")));
		vTmp.setINumExamen(Integer.parseInt((String) hmParams.get("iNumExamen")));
		vTmp.setICveMdoTrans(Integer.parseInt((String) hmGrupo
				.get("iCveMdoTrans")));
		vTmp.setICveGrupo(Integer.parseInt((String) hmGrupo.get("iCveGrupo")));
		vTmp.setICvePerfil(Integer.parseInt((String) hmGrupo.get("iCvePerfil")));
		return vTmp;
	}

	/**
	 * Metodo buildTVPERTpoLicencia
	 */
	private TVPERTpoLicencia buildTVPERTpoLicencia(HashMap hmParams,
			HashMap hmMotivo) {
		TVPERTpoLicencia vTmp = new TVPERTpoLicencia();
		vTmp.setICvePersonal(Integer.parseInt((String) hmParams
				.get("iCvePersonal")));
		vTmp.setICveMdoTrans(Integer.parseInt((String) hmMotivo
				.get("iCveMdoTrans")));
		vTmp.setICveCategoria(Integer.parseInt((String) hmMotivo
				.get("iCveCategoria")));
		vTmp.setICveCategoria(Integer.parseInt((String) hmMotivo
				.get("iCveCategoria")));

		vTmp.setCLicencia(""); // por mientras
		vTmp.setLDictamen(0); // por mientras
		return vTmp;
	}

	/**
	 * Metodo buildTVPERTpoLicencia
	 */
	private TVEXPExamGenera buildTVEXPExamGenera(
			TVEXPExamAplica vEXPExamAplica, java.sql.Date dtHoy) {
		TVEXPExamGenera vTmp = new TVEXPExamGenera();
		vTmp.setICveExpediente(vEXPExamAplica.getICveExpediente());
		vTmp.setINumExamen(vEXPExamAplica.getINumExamen());
		vTmp.setIExamenGenera(vEXPExamAplica.getINumExamen());
		vTmp.setICveProceso(Integer.parseInt(vParametros
				.getPropEspecifica("ToxProcesoLab")));
		vTmp.setINumExamenGenera(0);
		vTmp.setDtProgramado(dtHoy);
		vTmp.setDtPosibleAten(dtHoy);
		vTmp.setDtAplicacion(dtHoy);
		vTmp.setLAplicado(0);
		return vTmp;
	}

	/**
	 * Metodo guardarA
	 */
	public void GuardarA() {
		TDIngresos dI = new TDIngresos();
		Vector datosIns = new Vector();
		Long idUsuario = null;
		DecimalFormat refFmt = new DecimalFormat("00000000");
		TEncriptaDatos eDato = new TEncriptaDatos();
		try {
			Vector vConsultU = new TDSEGUsuario()
					.FindByAll(" where iCveUsuario = "
							+ ((TVUsuario) httpReq.getSession().getAttribute(
									"UsrID")).getICveusuario());
			if (vConsultU != null && vConsultU.size() > 0) {
				TVSEGUsuario vUsuario = (TVSEGUsuario) vConsultU.get(0);
				// Clase para encriptar...
				eDato.setLengthAndKey(Integer.parseInt(vParametros
						.getPropEspecifica("EPIIngNumBytes")), vParametros
						.getPropEspecifica("EPIIngCifrado"));

				/**/
				// Obtener parÃ¯Â¿Â½metros
				HashMap hParametros = getParameters("");

				int viTipoPersona = 0;
				int viCveUsuario = 0;
				String cNombre = "", cRFC = "";

				setCParametros("");
				viCveUsuario = Integer.parseInt(request.getParameter(
						"rstDatRecibo").substring(0,
						request.getParameter("rstDatRecibo").indexOf("|")));
				viTipoPersona = Integer.parseInt(request.getParameter(
						"rstDatRecibo").substring(
						request.getParameter("rstDatRecibo").indexOf("|") + 1,
						request.getParameter("rstDatRecibo").length()));
				TVPERDatos vPerDatos = findUserComplete();

				if (request.getParameter("cNombre") != null
						&& request.getParameter("cNombre").toString().length() > 0) {
					cNombre = request.getParameter("cNombre").toString().trim();
					cRFC = request.getParameter("cRFC").toString().trim();
				} else {
					cNombre = vPerDatos.getCNombreCompleto();
					cRFC = vPerDatos.getCRFC();
				}

				// Datos para el expediente y examen
				int iExpediente = vPerDatos.getICveExpediente();
				int iEXPExamAplica = new TDEXPExamAplica().FindLastEPI(""
						+ vPerDatos.getICveExpediente(),
						vParametros.getPropEspecifica("EPIProceso"));

				// Asigna el usuario
				idUsuario = new Long(viCveUsuario);

				// InserciÃ¯Â¿Â½n del Usuario en el Web Services de Ingresos.
				if (viTipoPersona == 3) { // Es un usuario Personal
					String usuario = (String) dI.insertUsr(
							"",
							vPerDatos.getCRFC(),
							vPerDatos.getCCURP(),
							vPerDatos.getCNombre(),
							vPerDatos.getCApPaterno(),
							vPerDatos.getCApMaterno(),
							1,
							vPerDatos.getCCalle(),
							vPerDatos.getCColonia(),
							vPerDatos.getICP(),
							vPerDatos.getCTelefono(),
							vPerDatos.getICvePais(),
							vPerDatos.getICveEstado(),
							vPerDatos.getICveMunicipio(),
							vPerDatos.getCCiudad() != null ? vPerDatos
									.getCCiudad() : "");
					if (usuario != null && usuario.length() > 0) {
						idUsuario = new Long(usuario);
					}
				}
				if (viTipoPersona == 4) { // Es un usuario Empresa
					Vector vcEmpresa = new Vector();
					TDGRLEmpresas dEmpresa = new TDGRLEmpresas();
					TVGRLEmpresas vEmpresa = new TVGRLEmpresas();
					vcEmpresa = dEmpresa
							.FindByEmpDom(" WHERE GRLEmpresas.iCveEmpresa = "
									+ viCveUsuario);
					vEmpresa = (TVGRLEmpresas) vcEmpresa.get(0);
					String usuario = (String) dI.insertUsr(
							"",
							vEmpresa.getCRFC(),
							vEmpresa.getCCurp(),
							vEmpresa.getCNombreRS(),
							vEmpresa.getCApPaterno(),
							vEmpresa.getCApMaterno(),
							0,
							vEmpresa.getcCalle(),
							vEmpresa.getcColonia(),
							vEmpresa.getICP(),
							vEmpresa.getCTelefono(),
							vEmpresa.getICvePais(),
							vEmpresa.getICveEntidadFed(),
							vEmpresa.getICveMunicipio(),
							vEmpresa.getcCiudad() != null ? vEmpresa
									.getcCiudad() : "");
					if (usuario != null && usuario.length() > 0) {
						idUsuario = new Long(usuario);
					}
				}

				// Busca los datos para generar el movimiento en Ingresos
				String cRefAlfaNumerica = null;
				int iReferencia = 0;
				int asunto = 0;
				int unidades = 0;
				int iAnio = 0;
				String cObservacionesA = "", cObservacionesP = "";
				String cFiltro = "";
				if (idUsuario != null && idUsuario.longValue() > 0) {
					// Generar objeto para solicitar la pÃ¯Â¿Â½gina insertable
					TVGRLCategoria vCategoria = new TVGRLCategoria();
					TDGRLCategoria dCategoria = new TDGRLCategoria();
					Vector vcCategoria = new Vector();
					Vector vcReferencias = new Vector();
					//String cReferencias = request.getParameter("hdPago");
					String cReferencias = "sin referencia";
					StringTokenizer stReferencias = new StringTokenizer(
							cReferencias, "|");
					String tReferencias = "";
					int i = 0, j = 0;
					int iCategoria = 0, iMdoTrans = 0;
					String cDato = "", cDscMotivo = "";
					// Obtener informaciÃ¯Â¿Â½n de la Unidad Administrativa y la
					// Oficina.
					String cFilIng = " where M.iCveUniMed = "
							+ hParametros.get("iCveUniMed").toString()
							+ "   and M.iCveModulo = "
							+ hParametros.get("iCveModulo").toString();
					TVGRLModulo vModulo = (TVGRLModulo) (((Vector) (new TDGRLModulo()
							.buscarInfoIngresos(cFilIng))).get(0));
					// Agregar al VOD la informaciÃ¯Â¿Â½n de unidad, area y
					// oficina
					this.vInfoIng = new TVDinRep02(); //
					this.vInfoIng.put("cPagina",
							vParametros.getPropEspecifica("EPIIngURL"));
					// this.vInfoIng.put("cPagina",
					// "http://10.33.141.204:7010/ingresos/pg0106013.jsp");
					this.vInfoIng.put("iCliente", idUsuario);
					this.vInfoIng.put("cNombre", cNombre);
					this.vInfoIng.put("cRFC", cRFC);
					this.vInfoIng.put("iUnidadAdm", vModulo.getICveUddAdmiva());
					this.vInfoIng.put("iArea",
							vParametros.getPropEspecifica("EPIIngArea"));
					this.vInfoIng.put("iOficina", vModulo.getICveOficina());
					// this.vInfoIng.put("iUsrIng",
					// eDato.getCifrado(String.valueOf( )));
					this.vInfoIng.put("cUsrIngresos",
							eDato.getCifrado(vUsuario.getCUsuario()));
					this.vInfoIng.put("cPassUsrIngresos",
							eDato.getCifrado(vUsuario.getCPassword()));
					// System.out.println("Ingresos = "+vUsuario.getCPassword());
					this.vInfoIng.put("cURLResp",
							vParametros.getPropEspecifica("EPIIngURLResp"));
					// this.vInfoIng.put("cURLResp",
					// "http://10.33.141.204:7010/medprev/pg070103014.jsp");

					// Suma los conceptos dependiendo las categorÃ¯Â¿Â½as
					// solicitadas
					int lPrimerMovto = 1, iPrincipal = 0, iAdicional = 0;
					int iPos = 0;
					Vector vGeneraMov = new Vector();
					TVDinRep02 vMovimiento;
					while (stReferencias.hasMoreElements()) {
						tReferencias = stReferencias.nextToken();
						StringTokenizer stCategoria = new StringTokenizer(
								tReferencias, ",");
						j = 0;
						while (stCategoria.hasMoreElements()) {
							cDato = stCategoria.nextToken();
							if (j == 0) {
								iMdoTrans = Integer.parseInt(cDato);
							} else if (j == 1) {
								iCategoria = Integer.parseInt(cDato);
							} else {
								cDscMotivo = cDato;
							}
							j++;
						}
						vcCategoria = dCategoria
								.FindWhere(" where iCveCategoria = "
										+ iCategoria + " and iCveMdoTrans ="
										+ iMdoTrans);
						vCategoria = (TVGRLCategoria) vcCategoria.get(0);

						if (lPrimerMovto == 1) {
							if (request.getParameter("hdMovtoRegistrado") == null) { // Ocupa
																						// la
																						// tarifa
																						// "Adicional"
								iPrincipal += 1;
								cObservacionesP += cDscMotivo + " "
										+ vCategoria.getCDscCategoria() + ", ";
							} else { // Ocupa la tarifa "Principal"
								iAdicional += 1;
								cObservacionesA += cDscMotivo + " "
										+ vCategoria.getCDscCategoria() + ", ";
							}
						} else { // Ocupa la tarifa "Adicional"
							iAdicional += 1;
							cObservacionesA += cDscMotivo + " "
									+ vCategoria.getCDscCategoria() + ", ";
						}
						lPrimerMovto = 0;
					} // Se obtiene la informaciÃ¯Â¿Â½n de las adicionales

					// Busca la tarifas y genera el movto en ingresos
					Vector vcRefConcepto = new Vector();
					TDEXPRefConcepto dEXPRefConcepto = new TDEXPRefConcepto();
					TVEXPRefConcepto vRefConcepto = new TVEXPRefConcepto();
					TFechas tFechas = new TFechas();
					java.sql.Date dtActual = tFechas.TodaySQL();

					String cRefAlfaNumericaP = "", cRefAlfaNumericaA = "";
					int iRefNumericaP = 0, iRefNumericaA = 0;
					if (iPrincipal > 0) {
						// busca la tarifa "Principal"

						cFiltro = " JOIN EXPConcepto on EXPConcepto.iCveConcepto = EXPRefConcepto.iCveConcepto "
								+ "  AND EXPConcepto.lAdicional = 0 "
								+ " WHERE EXPRefConcepto.iEjercicio = "
								+ tFechas.getStringYear(dtActual);

						vcRefConcepto = dEXPRefConcepto.FindByAll(cFiltro, "");
						if (vcRefConcepto.size() > 0) {
							vRefConcepto = (TVEXPRefConcepto) vcRefConcepto
									.get(0);
						}
						vMovimiento = new TVDinRep02();
						iRefNumericaP = vRefConcepto.getIRefNumerica();
						cObservacionesP = cObservacionesP + " PERSONAL "
								+ vPerDatos.getICveExpediente() + " "
								+ vPerDatos.getCNombre() + " "
								+ vPerDatos.getCApPaterno() + " "
								+ vPerDatos.getCApMaterno();
						TNumeros formato = new TNumeros();
						vMovimiento.put("cRefNum", formato
								.getNumeroSinSeparador(new Integer(
										iRefNumericaP), 8));
						vMovimiento.put("iUnidad", iPrincipal);
						vMovimiento.put(
								"iTotal",
								buscaImporte(String.valueOf(iRefNumericaP),
										iPrincipal));
						vMovimiento.put("cObserva", cObservacionesP);
						vGeneraMov.add(vMovimiento);
						// Solicita generar el movimiento
						// cRefAlfaNumericaP = (String)
						// dI.generaMov(iRefNumericaP,iPrincipal,
						// idUsuario.intValue(), cObservacionesP,
						// vModulo.getICveUddAdmiva(),
						// vModulo.getICveOficina());

					}
					if (iAdicional > 0) {
						// busca la tarifa "Adicional"
						cFiltro = " JOIN EXPConcepto on EXPConcepto.iCveConcepto = EXPRefConcepto.iCveConcepto "
								+ "  AND EXPConcepto.lAdicional = 1 "
								+ " WHERE EXPRefConcepto.iEjercicio = "
								+ tFechas.getStringYear(dtActual);
						vcRefConcepto = dEXPRefConcepto.FindByAll(cFiltro, "");
						if (vcRefConcepto.size() > 0) {
							vRefConcepto = (TVEXPRefConcepto) vcRefConcepto
									.get(0);
						}
						iRefNumericaA = vRefConcepto.getIRefNumerica();
						cObservacionesA = cObservacionesA + " PERSONAL "
								+ vPerDatos.getICveExpediente() + " "
								+ vPerDatos.getCNombre() + " "
								+ vPerDatos.getCApPaterno() + " "
								+ vPerDatos.getCApMaterno();
						// Solicita generar el movimiento
						// cRefAlfaNumericaA = (String)
						// dI.generaMov(iRefNumericaA,iAdicional,
						// idUsuario.intValue(), cObservacionesA,
						// vModulo.getICveUddAdmiva(),
						// vModulo.getICveOficina());
						vMovimiento = new TVDinRep02();
						TNumeros formato = new TNumeros();
						vMovimiento.put("cRefNum", formato
								.getNumeroSinSeparador(new Integer(
										iRefNumericaA), 8));
						vMovimiento.put("iUnidad", iAdicional);
						vMovimiento.put(
								"iTotal",
								buscaImporte(String.valueOf(iRefNumericaA),
										iAdicional));
						vMovimiento.put("cObserva", cObservacionesA);
						vGeneraMov.add(vMovimiento);
					}
					this.vInfoIng.put("vGeneraMov", vGeneraMov);
					this.vInfoIng.put("cDatoAdic", iExpediente + ","
							+ iEXPExamAplica + "|" + cReferencias);
				}
				vErrores.acumulaError("", 0,
						"Se agregaron correctamente los datos.");
			} else {
				vErrores.acumulaError("", 0,
						"No se validá el usuario de sesion.");
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar los registros", ex);
		} finally {
			super.GuardarA();
		}
	}

	public Vector getUsuarios(TVPERDatos vPerDatos, TVGRLEmpresas vEmpresa) {
		Vector vcUsuario = new Vector();
		TVINGUsuario vUsuario = new TVINGUsuario();
		String cDomicilioTMP = "";

		// Usuario Personal en Ingresos
		Vector domicilios = new Vector();
		domicilios = (Vector) new TDIngresos().findRFC(vPerDatos.getCRFC()
				+ vPerDatos.getCHomoclave(), TDIngresos.RFC);
		for (int i = 0; i < domicilios.size(); i++) {
			vUsuario = (TVINGUsuario) domicilios.get(i);
			vcUsuario.add(vUsuario);
		}

		// Usuario Personal en MedPrev
		vUsuario = new TVINGUsuario();
		vUsuario.setCApMaterno(vPerDatos.getCApMaterno());
		vUsuario.setCApPaterno(vPerDatos.getCApPaterno());
		cDomicilioTMP = vPerDatos.getCCalle();
		if (vPerDatos.getCNumExt() != null) {
			cDomicilioTMP += " " + vPerDatos.getCNumExt();
		}
		if (vPerDatos.getCNumInt() != null) {
			cDomicilioTMP += " " + vPerDatos.getCNumInt();
		}
		vUsuario.setCCalle(cDomicilioTMP);
		vUsuario.setCCiudad(vPerDatos.getCCiudad());
		vUsuario.setCColonia(vPerDatos.getCColonia());
		vUsuario.setCCURP(vPerDatos.getCCURP());
		vUsuario.setCDscEntidadFed(vPerDatos.getCDscEstado());
		vUsuario.setCDscMunicipio(vPerDatos.getCDscMunicipio());
		vUsuario.setICveEntidadFed(vPerDatos.getICveEstado());
		vUsuario.setICveMunicipio(vPerDatos.getICveMunicipio());
		vUsuario.setICvePais(vPerDatos.getICvePais());
		vUsuario.setCRPA("");
		vUsuario.setCTelefono(vPerDatos.getCTelefono());
		vUsuario.setCNombre(vPerDatos.getCNombre());
		vUsuario.setICP(vPerDatos.getICP());
		vUsuario.setCRFC(vPerDatos.getCRFC() + vPerDatos.getCHomoclave());
		vUsuario.setICveUsuario(0);
		vUsuario.setLFisicoMoral(true); // indica si el usuario es el personal.
		vUsuario.setCRPA(vPerDatos.getICvePersonal() + ""); // utilizado para
															// guardar la clave
															// del personal
		vcUsuario.add(vUsuario);

		if (vEmpresa != null && vEmpresa.getICveEmpresa() != 0) {
			// Usuario Empresa en Ingresos
			domicilios = (Vector) new TDIngresos().findRFC(vEmpresa.getCRFC(),
					TDIngresos.RFC);
			for (int i = 0; i < domicilios.size(); i++) {
				vUsuario = (TVINGUsuario) domicilios.get(i);
				vcUsuario.add(vUsuario);
			}

			// Usuario Empresa en MedPrev
			vUsuario = new TVINGUsuario();
			vUsuario.setCApMaterno(vEmpresa.getCApMaterno());
			vUsuario.setCApPaterno(vEmpresa.getCApPaterno());
			vUsuario.setCCalle(vEmpresa.getcCalle());
			vUsuario.setCCiudad(vEmpresa.getcCiudad());
			vUsuario.setCColonia(vEmpresa.getcColonia());
			vUsuario.setCCURP("");
			vUsuario.setCDscEntidadFed(vEmpresa.getCDscEntidadFed());
			vUsuario.setCDscMunicipio(vEmpresa.getCDscMunicipio());
			vUsuario.setCRPA("");
			vUsuario.setCTelefono(vEmpresa.getCTelefono());
			vUsuario.setCNombre(vEmpresa.getCNombreRS());
			vUsuario.setCRFC(vEmpresa.getCRFC());
			vUsuario.setICveUsuario(0);
			vUsuario.setICveEntidadFed(vEmpresa.getICveEntidadFed());
			vUsuario.setICvePais(vEmpresa.getICvePais());
			vUsuario.setICveMunicipio(vEmpresa.getICveMunicipio());
			vUsuario.setICP(vEmpresa.getICP());
			vUsuario.setLFisicoMoral(false); // indica si el usuario es la
												// empresa
			vUsuario.setCRPA(vEmpresa.getICveEmpresa() + ""); // Utilizado para
																// guardar la
																// clave de la
																// empresa
			vcUsuario.add(vUsuario);
		}
		return vcUsuario;
	}

	public String getCParametros() {
		return cParametros;
	}

	public void setCParametros(String cParametros) {
		this.cParametros = cParametros;
	}

	public String buscaImporte(String cRefNumerica, int iCantidad) {
		TDIngresos dI = new TDIngresos();
		TFechas tFechas = new TFechas();
		Vector vcTarifa = new Vector();
		TVConceptoII vTarifa = new TVConceptoII();
		int iEjercicio = 0, iCategoria = 0, iConcepto = 0, iTipoRef = 0;
		double dImporte = 0;
		String cImporte;

		// DecimalFormat df = new DecimalFormat("$    ###,###,##0.00");
		DecimalFormat refFmt = new DecimalFormat("00000000");

		String cReferencia = refFmt.format(Double.parseDouble(cRefNumerica));
		iCategoria = Integer.parseInt(cReferencia.substring(5, 6));
		iConcepto = Integer.parseInt(cReferencia.substring(2, 5));
		iTipoRef = 1;
		iEjercicio = tFechas.getIntYear(tFechas.TodaySQL());

		vcTarifa = (Vector) dI.findTarifaConcepto(iEjercicio, iCategoria,
				iConcepto, iTipoRef);

		vTarifa = (TVConceptoII) vcTarifa.get(0);

		dImporte = vTarifa.getDTarifaCAjuste().doubleValue();
		cImporte = String.valueOf(dImporte * iCantidad);

		// cImporte = df.format(dImporte * iCantidad);
		return cImporte;
	}

	public String getDatosArea() {
		String cDatos = "";
		TParametro vParametro = new TParametro("07");
		cDatos = "&hdCAreaRec="
				+ vParametro.getPropEspecifica("WSIngDscRecauda");
		cDatos += "&hdCOficina="
				+ vParametro.getPropEspecifica("WSIngDscOficina");
		cDatos += "&hdCRFCSCT=" + vParametro.getPropEspecifica("WSIngRFCSCT");
		return cDatos;
	}

	public String getDatosUsuario(int iUsuario) {
		String cDatos = "";
		TDIngresos dI = new TDIngresos();
		TVINGUsuario vI = new TVINGUsuario();
		vI = (TVINGUsuario) dI.getUsuarioSCT(Integer.parseInt("" + iUsuario));

		cDatos = "&hdCNombre=" + vI.getCNombre() + " " + vI.getCApPaterno()
				+ " " + vI.getCApMaterno();
		cDatos += "&hdCRFC=" + vI.getCRFC();
		return cDatos;
	}

	public com.micper.seguridad.vo.TVDinRep02 getVInfoIng() {
		return vInfoIng;
	}
}


