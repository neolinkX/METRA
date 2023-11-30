package gob.sct.medprev;

import com.micper.util.*;
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
import com.micper.excepciones.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.TVUsuario;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;

/**
 * * Clase de configuracion para Diagnostico y Recomendacion
 * <p>
 * Administraci�n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070104031CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104031CFG.png'>
 */
public class pg070105011CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg070105011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070105010.jsp";

	}

	public String getAccion() {
		return request.getParameter("hdBoton");
	}

	/**
	 * Método Guardar
	 */
	public void Guardar() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		// System.out.println("metodo guardar");
		try {
			TVEXPDictamenServ Serv = new TVEXPDictamenServ();
			Serv = (TVEXPDictamenServ) this.getInputs();
			Vector vServicio = (new TDEXPServicio()).getLConcluido(
					String.valueOf(Serv.getICveExpediente()),
					String.valueOf(Serv.getINumExamen()),
					String.valueOf(Serv.getICveServicio()));
			if (vServicio.size() > 0
					&& ((TVEXPServicio) vServicio.get(0)).getLConcluido() != 1) {

				/**
				 * Inserta / Actualiza a EXPDictamenServ
				 */
				int lConcluye = 0;
				TVEXPDictamenServ vcListaCat = new TVEXPDictamenServ();
				Vector vListaCat = new Vector();
				vListaCat = this.findCatSol();
				int lDictamen = 0;
				if (vListaCat.size() > 0) {
					for (int i = 0; i < vListaCat.size(); i++) {
						vcListaCat = (TVEXPDictamenServ) vListaCat.get(i);
						if (request.getParameter("lDictamen"
								+ vcListaCat.getICveCategoria() + "_"
								+ vcListaCat.getICveMdoTrans()) != null) {
							lDictamen = Integer.parseInt(request.getParameter(
									"lDictamen" + vcListaCat.getICveCategoria()
											+ "_"
											+ vcListaCat.getICveMdoTrans())
									.toString());
						} else {
							lDictamen = 0;
						}

						cClave = (String) dEXPDictamenServ.insertDicCat(null,
								this.getInputs(),
								vcListaCat.getICveCategoria(),
								vcListaCat.getICveMdoTrans(), lDictamen);
					}
				}

				/**
				 * Inserci�n Marco Gonz�lez 21/Octubre/2004 Inserci�n a tabla
				 * EXPExamCat para confrimaci�n de Aptos o no Aptos en EMO Solo
				 * para proceso EMO uso del parametro EMOServicio
				 */
				/*
				 * if
				 * (request.getParameter("iCveServicio").compareTo(vParametros
				 * .getPropEspecifica("EMOServicio").toString()) == 0){
				 * //System.out.println("Ejecutando ExamCat por EMO !!!!!");
				 * TDEXPExamCat dEXPExamCat = new TDEXPExamCat(); TVEXPExamCat
				 * vEXPExamCat = new TVEXPExamCat();
				 * 
				 * vEXPExamCat.setINumExamen(Integer.parseInt(request.getParameter
				 * ("iNumExamen")));
				 * vEXPExamCat.setICveExpediente(Integer.parseInt
				 * (request.getParameter("iCveExpediente"))); //
				 * System.out.println(request.getParameter("iCveMdoTrans")); if
				 * (request.getParameter("iCveMdoTrans") != null &&
				 * request.getParameter("iCveMdoTrans").toString().equals("1"))
				 * {
				 * vEXPExamCat.setICveMdoTrans(Integer.parseInt(request.getParameter
				 * ("iCveMdoTrans"))); }else{
				 * vEXPExamCat.setICveMdoTrans(Integer.parseInt("2")); }
				 * 
				 * vEXPExamCat.setICveCategoria(Integer.parseInt(request.
				 * getParameter("iCveCategoria")));
				 * vEXPExamCat.setLDictamen(Integer
				 * .parseInt(request.getParameter("lDictamen" +
				 * vEXPExamCat.getICveCategoria() + "_" +
				 * vEXPExamCat.getICveMdoTrans())));
				 * dEXPExamCat.updateEMOCat(null,vEXPExamCat); }
				 */
				/***********************************************************************/

				/**
				 * Inserta / Actualiza a EXPServicio
				 */

				dEXPDictamenServ.insertExpSer(null, this.getInputs());

				String aCveDiagnostico[] = request
						.getParameterValues("iCveDiagnostico");
				// System.out.println("Diagnostico = "+request.getParameterValues("iCveDiagnostico"));
				String iCveEspecialidad = "";
				String iCveDiagnostico = "";

				if (aCveDiagnostico != null) {
					if (aCveDiagnostico.length > 0) {
						for (int w = 0; w < aCveDiagnostico.length; w++) {
							// System.out.println("==>> "+aCveDiagnostico[w]);
							// //
							// System.out.println("==>>SUBSTR LAST INDEX >> "+aCveDiagnostico[w].lastIndexOf("_"));
							// //
							// System.out.println("==>>SUBSTR iCveEspecialidad >> "+aCveDiagnostico[w].substring(aCveDiagnostico[w].lastIndexOf("_")+1,aCveDiagnostico[w].length()));
							// //
							// System.out.println("==>>SUBSTR iCveDiagnostico >> "+aCveDiagnostico[w].substring(0,aCveDiagnostico[w].lastIndexOf("_")));

							iCveEspecialidad = aCveDiagnostico[w].substring(
									aCveDiagnostico[w].lastIndexOf("_") + 1,
									aCveDiagnostico[w].length());
							iCveDiagnostico = aCveDiagnostico[w].substring(0,
									aCveDiagnostico[w].lastIndexOf("_"));
							// System.out.println(iCveDiagnostico+ "  "
							// +iCveEspecialidad);
							dEXPDictamenServ.insertExpDia(null,
									this.getInputs(), iCveDiagnostico,
									iCveEspecialidad);
						}
					}
				}
				
				/////Registrar Diagostico Diabetes/////
				String diabetes = request.getParameter("DiagnosticoDiabetes");
				//System.out.println("-"+diabetes+"-");
				if(diabetes.equals("true")){
					//System.out.println("entro insert");
					dEXPDictamenServ.insertExpDia(null,this.getInputs(), "50", "4");
				}else{
					//System.out.println("no entro insert");
				}
				
				

				////Registrar claves CIF
				

				String aCveDiagnostico2[] = request
						.getParameterValues("iCveDiagnostico2");
			 //System.out.println("Diagnostico = "+request.getParameterValues("iCveDiagnostico2"));
				String iCveRamaCif = "";
				String iCveCif = "";

				if (aCveDiagnostico2 != null) {
					if (aCveDiagnostico2.length > 0) {
						for (int w = 0; w < aCveDiagnostico2.length; w++) {
							iCveRamaCif = aCveDiagnostico2[w].substring(
									aCveDiagnostico2[w].lastIndexOf("_") + 1,
									aCveDiagnostico2[w].length());
							iCveCif = aCveDiagnostico2[w].substring(0,
									aCveDiagnostico2[w].lastIndexOf("_"));
							// System.out.println(iCveDiagnostico+ "  "
							// +iCveEspecialidad);
							dEXPDictamenServ.insertExpDiaCif(null,
									this.getInputs(), iCveCif,
									iCveRamaCif);
						}
					}
				}

				
				
				String aCveRecomendacion[] = request
						.getParameterValues("iCveRecomendacion");
				// System.out.println("Recomencadion = "+request.getParameterValues("iCveRecomendacion"));
				String iCveRecomendacion = "";

				// System.out.println(aCveRecomendacion.length);
				if (aCveRecomendacion != null) {
					if (aCveRecomendacion.length > 0) {
						for (int x = 0; x < aCveRecomendacion.length; x++) {
							// //
							// System.out.println("==>> "+aCveRecomendacion[x]);
							// //
							// System.out.println("==>>SUBSTR LAST INDEX >> "+aCveRecomendacion[x].lastIndexOf("_"));
							// //
							// System.out.println("==>>SUBSTR iCveEspecialidad >> "+aCveRecomendacion[x].substring(aCveRecomendacion[x].lastIndexOf("_")+1,aCveRecomendacion[x].length()));
							// //
							// System.out.println("==>>SUBSTR iCveRecomendacion >> "+aCveRecomendacion[x].substring(0,aCveRecomendacion[x].lastIndexOf("_")));
							iCveEspecialidad = aCveRecomendacion[x].substring(
									aCveRecomendacion[x].lastIndexOf("_") + 1,
									aCveRecomendacion[x].length());
							iCveRecomendacion = aCveRecomendacion[x].substring(
									0, aCveRecomendacion[x].lastIndexOf("_"));
							// System.out.println(iCveEspecialidad+ "  "
							// +iCveRecomendacion);
							dEXPDictamenServ.insertExpReco(null,
									this.getInputs(), iCveEspecialidad,
									iCveRecomendacion);
						}
					}
				}

				// Insertar Observaciones y restricciones
				dEXPServicio.insertObserRes(null, this.getInputs());

				lConcluye = this.lConcluidoES();

				if (lConcluye == 0) {
					dEXPDictamenServ
							.updateEXPExamAplica(null, this.getInputs());
				}
			}
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Método Guardar

	/**
	 * Método GuardarA
	 */
	public void GuardarA() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			// cClave = (String) dEXPDictamenServ.update(null,
			// this.getInputs());
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
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			cClave = (String) dEXPDictamenServ.delete(null, this.getInputs());
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
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		String cWhere = "";
		try {

			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Generar Reporte") == 0) {
				activeX.append(this.GenRep(this.getInputs()));
			}

			String lAction = request.getParameter("hdBoton");
			cWhere = " where iCveExpediente =  "
					+ request.getParameter("iCveExpediente")
					+ " and iNumExamen = " + request.getParameter("iNumExamen");
			vDespliega = dEXPDictamenServ.FindByAll(cWhere);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Método getEdad
	 */
	public int getEdad(java.sql.Date dtFechaNac) {
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

	public TVPERDatos findExpediente() {
		TVPERDatos vPerDat = null;
		try {
			vPerDat = new TDEXPDictamenServ().findUserbyExp(this.getInputs());
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	public Vector findCatSol() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.FindByCatSol(this.getInputs());
		} catch (Exception ex) {
			error("findCatSol", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaCategoria() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.FindListaCat(this.getInputs());
		} catch (Exception ex) {
			error("listaCategoria", ex);
		}
		return vcDictamenServ;
	}

	public Vector listanMedica() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpSer(this.getInputs());
		} catch (Exception ex) {
			error("listanMedica", ex);
		}
		return vcDictamenServ;
	}

	// Observaciones y Restricciones
	public Vector ObserRes() {
		Vector vcDictamenServ = new Vector();
		TDEXPServicio dEXPServicio = new TDEXPServicio();
		try {
			vcDictamenServ = dEXPServicio.findByObserRes(this.getInputs());
		} catch (Exception ex) {
			error("listanMedica", ex);
		}
		return vcDictamenServ;
	}

	public int lConcluidoES() {
		int lConcluye = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lConcluye = dEXPDictamenServ.lConcluidoExpServ(null,
					this.getInputs());
		} catch (Exception ex) {
			error("lConcluidoES", ex);
		}
		return lConcluye;
	}

	public int lConcluidoEA() {
		int lConcluye = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lConcluye = dEXPDictamenServ.lConcluidoExpExamAplica(null,
					this.getInputs());
		} catch (Exception ex) {
			error("lConcluidoEA", ex);
		}
		return lConcluye;
	}

	public Vector listaDicSer() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDicSer(this.getInputs());
		} catch (Exception ex) {
			error("listaDicSer", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaExpRec() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpRec(this.getInputs());
		} catch (Exception ex) {
			error("listaExpRec", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaExpDia() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDia(this.getInputs());
		} catch (Exception ex) {
			error("listaExpDia", ex);
		}
		return vcDictamenServ;
	}

	
	public Vector listaExpDiaCif() {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDiaCif(this.getInputs());
		} catch (Exception ex) {
			error("listaExpDia", ex);
		}
		return vcDictamenServ;
	}

	
	/**
	 * Método FillPK
	 */
	/*
	 * public void FillPK() { mPk.add(cActual); }
	 */

	/**
	 * Método getInputs
	 */
	public Object getInputs() throws CFGException {
		// //
		// System.out.println("****** OJO ===>>> CAMBIAR EL EXPEDIENTE Y SERVICIO DEF A 0 CUANDO SE ACABEN LAS PRUEBAS ******");
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		// TFechas tfCampo = new TFechas();
		TVEXPDictamenServ vEXPDictamenServ = new TVEXPDictamenServ();
		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vEXPDictamenServ.setICveExpediente(iCampo);

			cCampo = "" + request.getParameter("iNumExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vEXPDictamenServ.setINumExamen(iCampo);

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setICveCategoria(iCampo);

			cCampo = "" + request.getParameter("iCveServicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 1;
			}
			vEXPDictamenServ.setICveServicio(iCampo);

			cCampo = "" + request.getParameter("lDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLDictamen(iCampo);
			
			cCampo = "" + request.getParameter("hdICveProceso");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setICveProceso(iCampo);

			cCampo = "" + request.getParameter("cNotaMedica");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			// System.out.println("Ultima setCNotaMedica ==>> "+cCampo);//Agregado
			// por L.A.S.
			vEXPDictamenServ.setCNotaMedica(cCampo);

			cCampo = "" + request.getParameter("cObserRes");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			// System.out.println("cObserRes = " + cCampo);
			vEXPDictamenServ.setCObserRes(cCampo);

			int iCveUsuario = ((TVUsuario) (((HttpServletRequest) request)
					.getSession(true).getAttribute("UsrID"))).getICveusuario();
			vEXPDictamenServ.setICveUsuDictamen(iCveUsuario);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPDictamenServ;
	}

	public StringBuffer GenRep(Object obj) {
		TExcel xl = new TExcel("07");
		TFechas pFecha = new TFechas();
		String cNomArchivo = new String("pg070105011"), cDscMdoTrans;
		StringBuffer buffer = new StringBuffer(), cFiltro = new StringBuffer();
		Vector VResultado = new Vector(), VDiagRec = new Vector();

		TVEXPDictamen VDictamen;

		int iRengI, iReng = 0, iHoja = 3;
		int iCvePersonal, iNumExamen, iCveServ;

		try {
			if (request.getParameter("iCvePersonal").compareTo("null") != 0
					&& request.getParameter("iCvePersonal").compareTo("") != 0)
				iCvePersonal = Integer.parseInt(
						request.getParameter("iCvePersonal"), 10);
			else
				iCvePersonal = 0;
			if (request.getParameter("iNumExamen").compareTo("null") != 0
					&& request.getParameter("iNumExamen").compareTo("") != 0)
				iNumExamen = Integer.parseInt(
						request.getParameter("iNumExamen"), 10);
			else
				iNumExamen = 0;
			if (request.getParameter("iCveServicio").compareTo("null") != 0
					&& request.getParameter("iCveServicio").compareTo("") != 0)
				iCveServ = Integer.parseInt(
						request.getParameter("iCveServicio"), 10);
			else
				iCveServ = 0;

			cFiltro.append("  where EC.iCveExpediente  = ")
					.append(iCvePersonal)
					.append("    and EA.iNumExamen      = ").append(iNumExamen);
			VResultado = (new TDEXPExamCat())
					.InfoConstancia(cFiltro.toString());
			// Colocar datos del encabezado del Reporte
			VDictamen = new TVEXPDictamen();
			if (VResultado.size() > 0)
				VDictamen = (TVEXPDictamen) VResultado.get(0);
			iReng = 11;
			xl.comDespliega("B", iReng++, VDictamen.VPerDatos.getCNombre());
			xl.comDespliega("B", iReng++, VDictamen.VPerDatos.getCRFC());
			xl.comDespliega("B", iReng++, VDictamen.VPerDatos.getCCURP());
			xl.comDespliega("B", iReng++, VDictamen.VPerDatos.getCGenero());
			xl.comDespliega("F", iReng++,
					"'" + VDictamen.VExamCat.getICveExpediente());
			xl.comDespliega("F", iReng,
					"'" + VDictamen.VPerDatos.getCExpediente());
			iReng += 4;
			if (VDictamen.getDtSolicitado() != null)
				xl.comDespliega(
						"D",
						iReng,
						"'"
								+ pFecha.getFechaDDMMYYYY(
										VDictamen.getDtSolicitado(), "/"));
			xl.comDespliega("J", iReng, "");
			xl.comDespliega("K", iReng++, "");
			xl.comDespliega("D", iReng, VDictamen.VExamCat.getCDscMdoTrans());
			xl.comDespliega("D", iReng, VDictamen.VExamCat.getCDscMdoTrans());
			xl.comDespliega("D", iReng, VDictamen.VExamCat.getCDscMdoTrans());
			StringBuffer cCategoria = new StringBuffer();
			for (int i = 0; i < VResultado.size(); i++) {
				if (VDictamen.VExamCat.getCDscMdoTrans().equalsIgnoreCase(
						((TVEXPDictamen) VResultado.get(i)).VExamCat
								.getCDscMdoTrans())) {
					if (i > 0)
						cCategoria.append(", ");
					cCategoria
							.append(((TVEXPDictamen) VResultado.get(i)).VExamCat
									.getCDscCategoria());
				}
			}
			xl.comDespliega("K", iReng, cCategoria.toString());
			// B�squeda de Diagn�sticos
			VDiagRec = (new TDEXPDiagnostico()).getDiagEspXServ(
					String.valueOf(iCvePersonal), String.valueOf(iNumExamen),
					String.valueOf(iCveServ));
			iReng += 4;
			iRengI = iReng;
			for (int i = 0; i < VDiagRec.size(); i++) {
				xl.comDespliega("A", iReng,
						((TVEXPDiagnostico) VDiagRec.get(i)).getCCIE());
				xl.comDespliega("B", iReng,
						((TVEXPDiagnostico) VDiagRec.get(i))
								.getCDscDiagnostico());
				xl.comAlineaRango("B", iReng, "N", iReng,
						xl.getAT_COMBINA_IZQUIERDA());
				xl.comAlineaRangoVer("B", iReng, "N", iReng,
						xl.getAT_VJUSTIFICAR());
				iReng++;
			}
			xl.comBordeTotal("A", iRengI, "N", iReng - 1, 1);
			VDiagRec = new Vector();
			// B�squeda de Recomendaciones
			xl.comDespliega("A", iReng, "RECOMENDACIONES");
			xl.comAlineaRango("A", iReng, "N", iReng,
					xl.getAT_COMBINA_IZQUIERDA());
			xl.comFillCells("A", iReng, "N", iReng, 15);
			xl.comBordeTotal("A", iReng, "N", iReng, 1);
			xl.comFontBold("A", iReng, "N", iReng);
			xl.comAlineaRango("A", iReng, "N", iReng, xl.getAT_HCENTRO());
			xl.comFontSize("A", iReng, "N", iReng, 8);
			iReng++;
			iRengI = iReng;
			VDiagRec = (new TDEXPRecomendacion()).getRecEspXServ(
					String.valueOf(iCvePersonal), String.valueOf(iNumExamen),
					String.valueOf(iCveServ));
			for (int i = 0; i < VDiagRec.size(); i++) {
				xl.comDespliega("A", iReng, ((TVEXPRecomendacion) VDiagRec
						.get(i)).getCDscRecomendacion());
				xl.comAlineaRango("A", iReng, "N", iReng,
						xl.getAT_COMBINA_IZQUIERDA());
				xl.comAlineaRangoVer("A", iReng, "N", iReng,
						xl.getAT_VJUSTIFICAR());
				iReng++;
			}
			/*
			 * iRengI = iReng; // Despliega en la nota medica String NOTA = "";
			 * if(request.getParameter("cNotaMedica").equals(null) ||
			 * request.getParameter("cNotaMedica").equals("null") ||
			 * request.getParameter("cObserRes").equals("") ||
			 * request.getParameter("cObserRes").compareTo("null") == 0){ NOTA =
			 * ""; }else{ NOTA = request.getParameter("cNotaMedica");
			 * xl.comBordeTotal("A", iRengI, "N", iReng-1,1);
			 * xl.comAlineaRango("A",iReng, "N", iReng, xl.getAT_HCENTRO());
			 * xl.comDespliega("C", iReng, "NOTA M�DICA");
			 * xl.comAlineaRango("A", iReng, "N", iReng,
			 * xl.getAT_COMBINA_IZQUIERDA()); xl.comFillCells("A", iReng, "N",
			 * iReng, 15); xl.comBordeTotal("A", iReng, "N", iReng, 1);
			 * xl.comFontBold("A",iReng, "N", iReng);
			 * xl.comAlineaRango("A",iReng, "N", iReng, xl.getAT_HCENTRO());
			 * xl.comDespliega("C", iReng, "NOTA M�DICA");
			 * xl.comFontSize("A",iReng, "N", iReng,8);
			 * 
			 * xl.comAlineaRango("A", iReng, "N", iReng,
			 * xl.getAT_COMBINA_IZQUIERDA()); xl.comAlineaRangoVer("A", iReng,
			 * "N", iReng, xl.getAT_VJUSTIFICAR()); iReng++;
			 * 
			 * xl.comAlineaRango("B", iReng, "N", iReng,
			 * xl.getAT_COMBINA_IZQUIERDA()); xl.comAlineaRangoVer("B", iReng,
			 * "N", iReng, xl.getAT_VJUSTIFICAR()); xl.comDespliega("B", iReng,
			 * NOTA); iReng++; }
			 */
			// termina la nota medica

			// Despliega en Reporte las Observaciones/Restricciones: del
			// Dictaminador
			String ObservaRest = "";
			if (request.getParameter("cObserRes").equals(null)
					|| request.getParameter("cObserRes").equals("null")
					|| request.getParameter("cObserRes").equals("")
					|| request.getParameter("cObserRes").compareTo("null") == 0) {
				ObservaRest = "";
			} else {
				ObservaRest = request.getParameter("cObserRes");
				xl.comBordeTotal("A", iRengI, "N", iReng - 1, 1);
				xl.comAlineaRango("A", iReng, "N", iReng, xl.getAT_HCENTRO());
				xl.comDespliega("C", iReng,
						"OBSERVACIONES Y RESTRICCIONES DEL DICTAMINADOR");
				xl.comAlineaRango("A", iReng, "N", iReng,
						xl.getAT_COMBINA_IZQUIERDA());
				xl.comFillCells("A", iReng, "N", iReng, 15);
				xl.comBordeTotal("A", iReng, "N", iReng, 1);
				xl.comFontBold("A", iReng, "N", iReng);
				xl.comAlineaRango("A", iReng, "N", iReng, xl.getAT_HCENTRO());
				xl.comDespliega("C", iReng,
						"OBSERVACIONES Y RESTRICCIONES DEL DICTAMINADOR");
				xl.comFontSize("A", iReng, "N", iReng, 8);

				xl.comAlineaRango("A", iReng, "N", iReng,
						xl.getAT_COMBINA_IZQUIERDA());
				xl.comAlineaRangoVer("A", iReng, "N", iReng,
						xl.getAT_VJUSTIFICAR());
				iReng++;

				xl.comDespliega("B", iReng, ObservaRest);
			}

			// termina Observaciones/Restricciones del dictaminador

			xl.comBordeTotal("A", iRengI, "N", iReng - 1, 1);
			xl.comAlineaRango("B", iReng, "N", iReng,
					xl.getAT_COMBINA_IZQUIERDA());
			xl.comAlineaRangoVer("B", iReng, "N", iReng, xl.getAT_VJUSTIFICAR());

			iReng += 3;

			// Obteniendo Medico Dictaminador y Cedula
			int iCveExpediente = Integer.parseInt(request
					.getParameter("iCveExpediente"));
			int iNumExamen2 = Integer.parseInt(request
					.getParameter("iNumExamen"));
			// String cNombre = this.nombreMedicoDictaminador(iCveExpediente,
			// iNumExamen2);
			String cNombre = new TDGRLUsuario().findMedicoDictaminador2(
					iCveExpediente, iNumExamen2);

			VDiagRec = new Vector();
			VDiagRec = new TDEXPServicio().findMedNota(
					String.valueOf(iCvePersonal), String.valueOf(iNumExamen),
					String.valueOf(iCveServ));
			xl.comAlineaRango("A", iReng, "C", iReng,
					xl.getAT_COMBINA_DERECHA());
			xl.comFontBold("A", iReng, "N", iReng);
			xl.comDespliega("A", iReng, "Médico Dictaminador:");
			// xl.comDespliega("E", iReng,
			// ((TVEXPServicio)VDiagRec.get(0)).getCNombre());
			xl.comDespliega("E", iReng, cNombre);

			iReng += 5;
			xl.comDespliega(
					"A",
					iReng++,
					"Recibí notificación de la Información Médica Personal en los términos del Artículo 35 "
							+ " de la Ley Federal de Procedimiento Administrativo.");
			xl.comDespliega("A", iReng + 2, "NOMBRE Y FIRMA DEL INTERESADO");
			xl.comFontBold("A", iReng + 2, "N", iReng);
			iReng += 3;
			xl.comDespliega("A", iReng, VDictamen.VPerDatos.getCNombre());
			xl.comFontBold("A", iReng, "N", iReng);
		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Exception in Generar Reporte : " +
			// ex.getMessage());
		}
		buffer = xl.creaActiveX("pg070105011", cNomArchivo, false, false, true);
		return buffer;
	}

	public String getActiveX() {
		return activeX.toString();
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

	public String nombreMedicoDictaminador(int numExpendiente, int numExamen) {
		String nombreMedicoDictaminador = "NA";
		nombreMedicoDictaminador = new TDGRLUsuario().findMedicoDictaminador(
				numExpendiente, numExamen);
		return nombreMedicoDictaminador;

	}

	/*
	 * Observaciones y Restricciones public Vector CMotivacion() { Vector
	 * vcDiagServ = new Vector(); TDEXPDiagnostico dEXPDiagnostico = new
	 * TDEXPDiagnostico(); TDEXPDiagnostico dEXPServicio = new
	 * TDEXPDiagnostico(); try { vcDiagServ =
	 * dEXPServicio.CMotivacion(this.getInputs()); } catch (Exception ex) {
	 * error("listanMedica", ex); } return vcDictamenServ; }
	 */

}