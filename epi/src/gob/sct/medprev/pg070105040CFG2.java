/*
 * To change this template, choose Tools | Templates 
 * and open the template in the editor.
 */
package gob.sct.medprev;

/**
 *
 * @author AG SA
 */ 

import gob.sct.medprev.cntmgr.CM_GetContent;
import gob.sct.medprev.dao.LICDownFoto;
import gob.sct.medprev.dao.LICDownFotoHist;
import gob.sct.medprev.dao.TDEXPDictamenServ;
import gob.sct.medprev.dao.TDEXPExamAplica;
import gob.sct.medprev.dao.TDEXPExamCat;
import gob.sct.medprev.util.Dictamen;
import gob.sct.medprev.util.DictamenAEmoVDos;
import gob.sct.medprev.util.DictamenApto;
import gob.sct.medprev.util.DictamenAptoP;
import gob.sct.medprev.util.DictamenAptoPMarIng;
import gob.sct.medprev.util.DictamenNoApto;
import gob.sct.medprev.util.DictamenNoAptoEMO;
import gob.sct.medprev.util.Dictamenes;
import gob.sct.medprev.vo.TVEXPDictamen;
import gob.sct.medprev.vo.TVEXPDictamenServ;
import gob.sct.medprev.vo.TVPERDatos;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.micper.excepciones.CFGException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGListBase2;
import com.micper.util.TFechas;

/**
 * * Clase de configuraci√≥n para Diagnostico y Recomendacion
 * <p>
 * Administraci√≥n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a href="JavaScript:alert('Consulte los
 *      archivos:\n-pg070104031CFG.jsp')">Click para lista de JSP's</a></small>
 *      </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070104031CFG.png'>
 */
public class pg070105040CFG2 extends CFGListBase2 {
	private TParametro VParametros2 = new TParametro("7");

	public pg070105040CFG2() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070105010.jsp";
	}

	private StringBuffer activeX = new StringBuffer("");

	public boolean lGuardar = false;

	/**
	 * M√©todo Guardar
	 */
	@Override
	public void Guardar() {
		try {
			TVEXPDictamenServ vExamCat = new TVEXPDictamenServ();
			TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
			TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
			Vector<TVEXPDictamenServ> vListaCat = new Vector<TVEXPDictamenServ>(), vActualizar = new Vector<TVEXPDictamenServ>();
			String cNomRadio = "";
			System.out.println("GUARDARRRRRRRRRRRR");
			// Obtener los valores de las Categor√≠as Dictaminadas
			vListaCat = this.listaExaCat();
			if (vListaCat != null && vListaCat.size() > 0) {
				// Recorrer las categor√≠as para obtener los valores del
				// dictamen
				System.out.println("GUARDAR 2");
				for (int i = 0; i < vListaCat.size(); i++) {
					vExamCat = new TVEXPDictamenServ();
					vExamCat = vListaCat.get(i);
					if (vExamCat.getLDictamEm() == 0) {
						cNomRadio = "lDictamen" + vExamCat.getICveCategoria()
								+ "_" + vExamCat.getICveMdoTrans();
						if (request.getParameter(cNomRadio) != null) {
							vExamCat.setLDictamen(Integer.parseInt(request
									.getParameter(cNomRadio)));
						}
						vActualizar.add(vExamCat);
					}
				}
				System.out.println("GUARDAR 3");
				// El vector se encuentra lleno con la informaci√≥n de los
				// Dict√°menes a Emitir
				boolean lValida = dEXPDictamenServ.guarDictExamCat(vActualizar,
						this.getInputs());
				System.out.println("lValida="+lValida);
				if (lValida) {
					vErrores.acumulaError("El dictamen se guarda exitosamente",
							0, "");
					lGuardar = true;
				}
				System.out.println("GUARDAR 4");
			} // Se encontraron categor√≠as

			// Se Actualiza la fecha de fin de examen
			String regresa = dEXPExamAplica.tfinexaupdate(null,
					this.getInputs());
			System.out.println("GUARDAR 5");
			String re = dEXPExamAplica
					.RegresaS("Select ldictamen from expexamcat where icveexpediente = "
							+ request.getParameter("iCveExpediente")
							+ " and inumexamen = "
							+ request.getParameter("inumexamen")
							+ " and icvecategoria = 7 and icvemdotrans = 2");
			System.out.println("re="+re);
			System.out.println("GUARDAR 6");
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // M√©todo Guardar

	/**
	 * M√©todo GuardarA
	 */
	@Override
	public void GuardarA() {
		// System.out.println("GuardarA");
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
		// System.out.println("GuardarA");
	} // M√©todo GuardarA

	/**
	 * M√©todo Borrar
	 */
	@Override
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
	} // M√©todo Borrar

	/**
	 * M√©todo FillVector
	 */
	@Override
	public void fillVector() {
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();

		try {
			String lAction = request.getParameter("hdBoton");
			String cWhere = "";

			cWhere = " where iCveExpediente = "
					+ request.getParameter("iCveExpediente")
					+ " and iNumExamen = " + request.getParameter("iNumExamen");

			vDespliega = dEXPDictamenServ.FindByAll(cWhere);

			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").compareToIgnoreCase(
							"Imprime Documentacion") == 0 || this.lGuardar) {
				System.out.println("Comparando en fillVector");
				// BEA SYSTEMS 18/10/2006
				request.setAttribute("REPORTE_PDF",
						getReporte(this.getInputs()));
				System.out.println("Comparando en fillVector2");
				
				// activeX.append(this.GenRep(this.getInputs()));

			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * M√©todo getEdad
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
		// System.out.println("findExpediente");
		try {
			vPerDat = new TDEXPDictamenServ().findUserExp(this.getInputs());
		} catch (Exception ex) {
			error("findUser", ex);
		}
		return vPerDat;
	}

	public TVPERDatos findExpedienteDos(String exp) {
		TVPERDatos vPerDat = null;
		// System.out.println("findExpediente");
		try {
			vPerDat = new TDEXPDictamenServ().findUserExpDos(exp);
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
	
	public Vector findCatSolDos(String exp, String exa) {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.FindByCatSolDos(exp, exa);

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
	
	public Vector listaCategoriaDos(String exp, String exa) {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.FindListaCatDos(exp, exa);
		} catch (Exception ex) {
			error("listaCategoria", ex);
		}
		return vcDictamenServ;
	}

	public int lDictaminadoEADos(String exp, String exa) {
		int lDictaminado = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lDictaminado = dEXPDictamenServ.lDictaminadoExpExamAplicaDos(null, exp, exa);
		} catch (Exception ex) {
			error("lDictaminadoEA", ex);
		}
		return lDictaminado;
	}

	public int lDictamenCatDos(String exp, String exa) {
		int lDictaminado = 0;
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			lDictaminado = dEXPDictamenServ
					.lDictamenCatDos(null, exp, exa);
		} catch (Exception ex) {
			error("lDictamenCat", ex);
		}
		return lDictaminado;
	}

	public Vector listanMedicaDos(String exp, String exa) {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpSerDos(exp, exa);
		} catch (Exception ex) {
			error("listanMedica", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaDicSerDos(String exp, String exa) {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDicSerDos(exp, exa, "31");
		} catch (Exception ex) {
			error("listaDicSer", ex);
		}
		return vcDictamenServ;
	}

	public Vector<TVEXPDictamenServ> listaExaCatDos(String exp, String exa) {
		Vector<TVEXPDictamenServ> vcDictamenServ = new Vector<TVEXPDictamenServ>();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ
					.findByExpExamCatDos(exp, exa);
		} catch (Exception ex) {
			error("listaExaCat", ex);
		}
		return vcDictamenServ;
	}
	public Vector<TVEXPDictamenServ> listaExaCat() {
		Vector<TVEXPDictamenServ> vcDictamenServ = new Vector<TVEXPDictamenServ>();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ
					.findByExpExamCat(this.getInputs());
		} catch (Exception ex) {
			error("listaExaCat", ex);
		}
		return vcDictamenServ;
	}

	public Vector listaExpRecDos(String exp, String exa) {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpRecDos(exp, exa,"31");
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
	

	public Vector listaExpDiaDos(String exp, String exa) {
		Vector vcDictamenServ = new Vector();
		TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
		try {
			vcDictamenServ = dEXPDictamenServ.findByExpDiaDos(exp, exa, "31");
		} catch (Exception ex) {
			error("listaExpDia", ex);
		}
		return vcDictamenServ;
	}

	/**
	 * M√©todo FillPK
	 */
	/*
	 * public void FillPK() { mPk.add(cActual); }
	 */

	/**
	 * M√©todo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		// float fCampo;
		// java.sql.Date dtCampo;
		TVEXPDictamenServ vEXPDictamenServ = new TVEXPDictamenServ();
		// System.out.println("GetInputs");
		try {
			cCampo = "" + request.getParameter("iCveExpediente");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 10;
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

			cCampo = "" + request.getParameter("lDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLDictamen(iCampo);

			// A√±adido 11 de Enero 2005
			cCampo = "" + request.getParameter("cGpoSang");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEXPDictamenServ.setCGpoSang(cCampo);

			cCampo = "" + request.getParameter("lRH");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLRH(iCampo);

			cCampo = "" + request.getParameter("lUsaLentes");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLUsaLentes(iCampo);

			// Agrego MGonzalez 24/01/2005 7:40 P.M.

			cCampo = "" + request.getParameter("lAereo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLAereo(iCampo);

			cCampo = "" + request.getParameter("lContacto");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLContacto(iCampo);

			cCampo = "" + request.getParameter("lHipertension");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLHipertension(iCampo);

			cCampo = "" + request.getParameter("lDiabetes");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setLDiabetes(iCampo);

			cCampo = "" + request.getParameter("iCveUsuDictamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEXPDictamenServ.setICveUsuDictamen(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEXPDictamenServ;
	}

	/**
	 * Metodo encargadp de generar el reporte en PDF del Dictamen
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private byte[] getReporte(Object obj) throws Exception {
		 System.out.println("Imprimir Reporte");
		String[] keys = {"userid", "password", "entity", "maxResults",
				"queryOP", "lintiCveDocumen"};
		String[] operators = {"", "", "", "", "", "="};
		CM_GetContent cmImport = new CM_GetContent();

		byte[] data = null;

		TFechas pFecha = new TFechas();
		int iCvePersonal = 0;
		int iNumExamen = 0;
		StringBuffer cFiltro = new StringBuffer();
		Vector VResultado = new Vector();
		TVEXPDictamen VDictamen;

		try {
			if (request.getParameter("iCvePersonal").compareTo("null") != 0
					&& request.getParameter("iCvePersonal").compareTo("") != 0) {
				iCvePersonal = Integer.parseInt(
						request.getParameter("iCvePersonal"), 10);
			} else {
				iCvePersonal = 0;
			}
			if (request.getParameter("iNumExamen").compareTo("null") != 0
					&& request.getParameter("iNumExamen").compareTo("") != 0) {
				iNumExamen = Integer.parseInt(
						request.getParameter("iNumExamen"), 10);
			} else {
				iNumExamen = 0;

			}
			cFiltro.append("  where EC.iCveExpediente  = ")
					.append(iCvePersonal).append(" and EA.iNumExamen = ")
					.append(iNumExamen);
			VResultado = (new TDEXPExamCat())
					.InfoConstancia(cFiltro.toString());

			// recuperamos los inodocto para foto, firma y huella
			int[] inodoctos2 = new int[3];
			int[] inodoctos = new int[3];
			com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
					"07");
			String dataSourceName = VParametros
					.getPropEspecifica("ConDBModulo");
			com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
					dataSourceName);
			java.sql.Connection conn = dbConn.getConnection();

			StringBuffer cSQL = new StringBuffer();

			/*
			 * //Se modificaron las siguientes lineas de codigo con la finalidad
			 * de que salgan las fotos que le correspondieron en el momento del
			 * dictamen a cada constancia cSQL.append(
			 * "select 0,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 1 ) "
			 * ); cSQL.append("union "); cSQL.append(
			 * "select 1,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 2 ) "
			 * ); cSQL.append("union "); //Se modifico la consulta para traer el
			 * maximo en la huella 19/05/08 LAS //Se modifico nuevmanet6e la
			 * consulta para traer el la huella del dedo numero 2 27/05/08 LAS
			 * //cSQL.append(
			 * "select 2,inodocto from (Select inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 order by (SUBSTR(CHAR(tsCaptura),1,10)) DESC,  iDedo ASC FETCH FIRST 1 ROW ONLY) h "
			 * ); cSQL.append(
			 * "select 2,inodocto from (Select max(inodocto) as inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 AND IDEDO=2) h"
			 * );
			 */
			// Recolectando Parametros
			// Numero personal
			String ClaveExpediente = "";
			ClaveExpediente = "" + request.getParameter("iCveExpediente");

			// Numero de Examen
			String NumeroExamen = "";
			NumeroExamen = "" + request.getParameter("iNumExamen");

			// Numero Unidad Medica
			String NumeroUniMed = "";
			NumeroUniMed = "" + request.getParameter("iCveUniMed");

			// Ultimo Examen
			int Maxexamen = 0;
			boolean EsMax = false;
			Integer.parseInt(NumeroExamen);

			java.sql.PreparedStatement pstmtMEx = null;
			String cSQLMEx = "";
			cSQLMEx = "SELECT Max(INUMEXAMEN) FROM EXPEXAMAPLICA where icveexpediente = "
					+ ClaveExpediente + "";
			pstmtMEx = conn.prepareStatement(cSQLMEx);
			java.sql.ResultSet rsetMEx = pstmtMEx.executeQuery();
			rsetMEx = pstmtMEx.executeQuery();
			while (rsetMEx.next()) {
				Maxexamen = rsetMEx.getInt(1);
			}
			if (Integer.parseInt(NumeroExamen) == Maxexamen) {
				EsMax = true;
			}
			// Cerrando conexion
			rsetMEx.close();
			pstmtMEx.close();
			if (EsMax) {
				cSQL.append("select 0,inodocto from licffh where tscaptura = (select max(L.tscaptura) FROM LICFFH AS L, EXPEXAMCAT AS C ");
				cSQL.append("WHERE L.iCvePersonal = ? AND L.ICVETIPOFFH = 1 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen + "  ) ");
				cSQL.append("union ");
				cSQL.append("select 1,inodocto from licffh where tscaptura = (select max(L.tscaptura) FROM LICFFH AS L, EXPEXAMCAT AS C ");
				cSQL.append("WHERE L.iCvePersonal = ? AND L.ICVETIPOFFH = 2 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen + "  ) ");
				cSQL.append("union ");
				cSQL.append("select 2,inodocto from (Select max(L.inodocto) as inodocto FROM LICFFH AS L, EXPEXAMCAT AS C ");
				cSQL.append("WHERE L.iCvePersonal = ? AND L.ICVETIPOFFH = 3 AND IDEDO=2 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen + " ) h");
			} else {
				cSQL.append("select 0,inodocto from licffh where tscaptura = (select max(L.tscaptura) FROM LICFFH AS L, EXPEXAMCAT AS C ");
				cSQL.append("WHERE L.iCvePersonal = ? AND L.ICVETIPOFFH = 1 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen
						+ " AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS ) ");
				cSQL.append("union ");
				cSQL.append("select 1,inodocto from licffh where tscaptura = (select max(L.tscaptura) FROM LICFFH AS L, EXPEXAMCAT AS C ");
				cSQL.append("WHERE L.iCvePersonal = ? AND L.ICVETIPOFFH = 2 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen
						+ " AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS ) ");
				cSQL.append("union ");
				cSQL.append("select 2,inodocto from (Select max(L.inodocto) as inodocto FROM LICFFH AS L, EXPEXAMCAT AS C ");
				cSQL.append("WHERE L.iCvePersonal = ? AND L.ICVETIPOFFH = 3 AND IDEDO=2 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen
						+ " AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS) h");
			}

			java.sql.PreparedStatement pstmt = conn.prepareStatement(cSQL
					.toString());
			pstmt.setInt(1, iCvePersonal);
			pstmt.setInt(2, iCvePersonal);
			pstmt.setInt(3, iCvePersonal);
			java.sql.ResultSet rset = pstmt.executeQuery();
			for (int i = 0; rset.next() && i < inodoctos2.length; i++) {
				inodoctos2[rset.getInt(1)] = rset.getInt(2);
			}

			int inodoctonull = 51827;
			int inodoctonull2 = 42731;
			int foto = 0;
			int huella = 0;
			int firma = 0;

			// Verificando si es el ultimo examen el que si imprime
			boolean MExamen = false;
			int MaxExamen = 0;
			java.sql.PreparedStatement pstmtMExa = null;
			String cSQLMExa = "";
			cSQLMExa = "select max(inumexamen) from expexamaplica WHERE ICVEEXPEDIENTE = "
					+ request.getParameter("iCveExpediente") + " ";
			pstmtMExa = conn.prepareStatement(cSQLMExa);
			java.sql.ResultSet rsetMExa = pstmtMExa.executeQuery();
			rsetMExa = pstmtMExa.executeQuery();
			while (rsetMExa.next()) {
				MaxExamen = rsetMExa.getInt(1);
			}

			if (MaxExamen == iNumExamen)
				MExamen = true;
			
			// Cerrando conexion
			rsetMExa.close();
			pstmtMExa.close();

			// Obtener Foto
			java.sql.PreparedStatement pstmtFoto = null;
			String cSQLFoto = "";
			if (MExamen) {
				cSQLFoto = "select 0,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = "
						+ request.getParameter("iCveExpediente")
						+ " AND ICVETIPOFFH = 1 ) ";
			} else {
				cSQLFoto = "select 0,inodocto from licffh where tscaptura = (select max(L.tscaptura) FROM LICFFH AS L, EXPEXAMCAT AS C "
						+ "WHERE L.iCvePersonal = "
						+ request.getParameter("iCveExpediente")
						+ " AND L.ICVETIPOFFH = 1 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen
						+ " AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS )";
			}
			pstmtFoto = conn.prepareStatement(cSQLFoto);
			java.sql.ResultSet rsetFoto = pstmtFoto.executeQuery();
			rsetFoto = pstmtFoto.executeQuery();
			while (rsetFoto.next()) {
				foto = 1;
				inodoctos[rsetFoto.getInt(1)] = rsetFoto.getInt(2);
			}

			if (foto == 0) {
				inodoctos[0] = inodoctonull;
			}

			// Cerrando conexion
			rsetFoto.close();
			pstmtFoto.close();

			// Obtener Firma
			java.sql.PreparedStatement pstmtFirma = null;
			String cSQLFirma = "";
			if (MExamen) {
				cSQLFirma = "select 1,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = "
						+ request.getParameter("iCveExpediente")
						+ " AND ICVETIPOFFH = 2 ) ";
			} else {
				cSQLFirma = "select 1,inodocto from licffh where tscaptura = (select max(L.tscaptura) FROM LICFFH AS L, EXPEXAMCAT AS C "
						+ "WHERE L.iCvePersonal = "
						+ request.getParameter("iCveExpediente")
						+ " AND L.ICVETIPOFFH = 2 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen
						+ " AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS ) ";
			}
			pstmtFirma = conn.prepareStatement(cSQLFirma);
			java.sql.ResultSet rsetFirma = pstmtFirma.executeQuery();
			rsetFirma = pstmtFirma.executeQuery();
			while (rsetFirma.next()) {
				firma = 1;
				inodoctos[rsetFirma.getInt(1)] = rsetFirma.getInt(2);
			}

			if (firma == 0)
				inodoctos[1] = inodoctonull2;

			// Cerrando conexion
			rsetFirma.close();
			pstmtFirma.close();

			// Obtener Huella
			java.sql.PreparedStatement pstmtHuella = null;
			String cSQLHuella = "";
			if (MExamen) {
				cSQLHuella = "select 2,inodocto from (Select max(inodocto) as inodocto FROM LICFFH WHERE iCvePersonal = "
						+ request.getParameter("iCveExpediente")
						+ " AND ICVETIPOFFH = 3 AND IDEDO=2) h";
			} else {
				cSQLHuella = "select 2,inodocto from (Select max(L.inodocto) as inodocto FROM LICFFH AS L, EXPEXAMCAT AS C "
						+ "WHERE L.iCvePersonal = "
						+ request.getParameter("iCveExpediente")
						+ " AND L.ICVETIPOFFH = 3 AND IDEDO=2 AND C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND C.INUMEXAMEN = "
						+ iNumExamen
						+ " AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS) h";
			}
			pstmtHuella = conn.prepareStatement(cSQLHuella);
			java.sql.ResultSet rsetHuella = pstmtHuella.executeQuery();
			rsetHuella = pstmtHuella.executeQuery();
			while (rsetHuella.next()) {
				if (rsetHuella.getString(2) != null) {
					inodoctos[rsetHuella.getInt(1)] = rsetHuella.getInt(2);
					huella = 1;
				}
			}

			if (huella == 0)
				inodoctos[2] = inodoctonull2;

			// Cerrando conexion
			rsetHuella.close();
			pstmtHuella.close();

			// Validar si la constancia se bloqueara o no
			String Bloquear = "";
			if (VParametros2.getPropEspecifica("Bloquear").toString()
					.equals("true")) {
				Bloquear = "bloqueado.png";
			} else {
				Bloquear = "nobloqueado.png";
			}

			// Obtener Direccion Unidad Medica

			java.sql.PreparedStatement pstmtDireccion2 = null;
			String cSQLDireccion2 = "";

			cSQLDireccion2 = "select ICVEUNIMED from expexamaplica where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ "";
			pstmtDireccion2 = conn.prepareStatement(cSQLDireccion2);
			java.sql.ResultSet rsetDireccion2 = pstmt.executeQuery();
			rsetDireccion2 = pstmtDireccion2.executeQuery();
			while (rsetDireccion2.next()) {
				NumeroUniMed = rsetDireccion2.getString(1);
			}

			// Cerrando conexion
			rsetDireccion2.close();
			pstmtDireccion2.close();

			// Obtener Direccion Unidad Medica
			String DireccionUM = "";
			String Ciudad = "";
			String Colonia = "";
			String Calle = "";
			java.sql.PreparedStatement pstmtDireccion = null;
			String cSQLDireccion = "";

			cSQLDireccion = "select CCIUDAD, CCOLONIA, CCALLE  from GRLUNIMED where ICVEUNIMED = "
					+ NumeroUniMed + "";
			pstmtDireccion = conn.prepareStatement(cSQLDireccion);
			java.sql.ResultSet rsetDireccion = pstmt.executeQuery();
			rsetDireccion = pstmtDireccion.executeQuery();
			while (rsetDireccion.next()) {
				Ciudad = rsetDireccion.getString(1);
				Colonia = rsetDireccion.getString(2);
				Calle = rsetDireccion.getString(3);
			}
			DireccionUM = "" + Ciudad + ", " + Colonia + ", " + Calle;
			// Cerrando conexion
			rsetDireccion.close();
			pstmtDireccion.close();

			// Obtener Direccion Modulo

			java.sql.PreparedStatement pstmtDireccion3 = null;
			String cSQLDireccion3 = "";
			String NumeroModulo = "";

			cSQLDireccion3 = "select ICVEMODULO from expexamaplica where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ "";
			pstmtDireccion3 = conn.prepareStatement(cSQLDireccion3);
			java.sql.ResultSet rsetDireccion3 = pstmt.executeQuery();
			rsetDireccion3 = pstmtDireccion3.executeQuery();
			while (rsetDireccion3.next()) {
				NumeroModulo = rsetDireccion3.getString(1);
			}

			// Cerrando conexion
			rsetDireccion3.close();
			pstmtDireccion3.close();

			// Obtener Direccion Modulo
			String DireccionMod = "";
			String CiudadMod = "";
			String ColoniaMod = "";
			String CalleMod = "";
			String CMunicipio = "";
			String IMunicipio = "";
			String IEstado = "";
			String IPais = "";
			String CodPostal = "";

			java.sql.PreparedStatement pstmtDireccionM = null;
			String cSQLDireccionM = "";

			cSQLDireccionM = "select CCIUDAD, CCOLONIA, CCALLE, ICVEMUNICIPIO, ICVEESTADO, ICVEPAIS, ICP  from GRLMODULO where ICVEUNIMED = "
					+ NumeroUniMed + " AND ICVEMODULO = " + NumeroModulo;
			pstmtDireccionM = conn.prepareStatement(cSQLDireccionM);
			java.sql.ResultSet rsetDireccionM = pstmt.executeQuery();
			rsetDireccionM = pstmtDireccionM.executeQuery();
			while (rsetDireccionM.next()) {
				Ciudad = rsetDireccionM.getString(1);
				Colonia = rsetDireccionM.getString(2);
				Calle = rsetDireccionM.getString(3);
				IMunicipio = rsetDireccionM.getString(4);
				IEstado = rsetDireccionM.getString(5);
				IPais = rsetDireccionM.getString(6);
				CodPostal = rsetDireccionM.getString(7);
			}

			cSQLDireccionM = "SELECT CNOMBRE FROM GRLMUNICIPIO WHERE ICVEPAIS = "
					+ IPais
					+ " AND ICVEENTIDADFED = "
					+ IEstado
					+ " AND ICVEMUNICIPIO = " + IMunicipio + "";
			pstmtDireccionM = conn.prepareStatement(cSQLDireccionM);
			rsetDireccionM = pstmtDireccionM.executeQuery();
			while (rsetDireccionM.next()) {
				CMunicipio = rsetDireccionM.getString(1);
			}

			DireccionMod = "" + Calle + ", " + Colonia + ", " + Ciudad + ", "
					+ CMunicipio + ", C.P. " + CodPostal;
			// Cerrando conexion
			rsetDireccionM.close();
			pstmtDireccionM.close();

			// Obtener Ciudad Origen y Ciudad Destino
			String CiudadDestino = "";
			String CiudadOrigen = "";
			java.sql.PreparedStatement pstmtOrigenDestino = null;
			String cSQLOrigenDestino = "";
/*
			cSQLOrigenDestino = "select CORIGEN, CDESTINO from EMOEXAMEN where ICVEEXPEDIENTE = "
					+ ClaveExpediente + "";
			pstmtOrigenDestino = conn.prepareStatement(cSQLOrigenDestino);
			java.sql.ResultSet rsetOrigenDestino = pstmt.executeQuery();
			rsetOrigenDestino = pstmtOrigenDestino.executeQuery();
			while (rsetOrigenDestino.next()) {
				CiudadOrigen = rsetOrigenDestino.getString(1);
				CiudadDestino = rsetOrigenDestino.getString(2);
			}
			// Cerrando conexion
			rsetOrigenDestino.close();
			pstmtOrigenDestino.close();*/

			// Obtener Licencia
			String NumLicencia = "";
			java.sql.PreparedStatement pstmtLic = null;
			String cSQLLic = "";
/*
			cSQLLic = "select CLICENCIA from EMOEXAMEN where ICVEEXPEDIENTE = "
					+ ClaveExpediente + "";
			pstmtLic = conn.prepareStatement(cSQLLic);
			java.sql.ResultSet rsetLic = pstmt.executeQuery();
			rsetLic = pstmtLic.executeQuery();
			while (rsetLic.next()) {
				NumLicencia = rsetLic.getString(1);
			}
			// Cerrando conexion
			rsetLic.close();
			pstmtLic.close();*/

			// Obtener Tension Arterial
			String TAS = "";

			java.sql.PreparedStatement pstmtTAS = null;
			String cSQLTAS = "";

			cSQLTAS = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 18 ";
			pstmtTAS = conn.prepareStatement(cSQLTAS);
			java.sql.ResultSet rsetTAS = pstmt.executeQuery();
			rsetTAS = pstmtTAS.executeQuery();
			while (rsetTAS.next()) {
				TAS = rsetTAS.getString(1);
			}
			// Cerrando conexion
			rsetTAS.close();
			pstmtTAS.close();

			String TAD = "";
			java.sql.PreparedStatement pstmtTAD = null;
			String cSQLTAD = "";
			cSQLTAD = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 19 ";
			pstmtTAD = conn.prepareStatement(cSQLTAD);
			java.sql.ResultSet rsetTAD = pstmt.executeQuery();
			rsetTAD = pstmtTAD.executeQuery();
			while (rsetTAD.next()) {
				TAD = rsetTAD.getString(1);
			}
			String TATA = "" + TAS + "." + TAD;
			// Cerrando conexion
			rsetTAD.close();
			pstmtTAD.close();

			// Obtener Nombre
			String NombreP = "";
			String ApellidoPP = "";
			String ApellidoMP = "";
			String NumeroPersonal = "";
			String NombrePersona = "";
			int AnioNac = 0;
			NumeroPersonal = "" + request.getParameter("iCvePersonal");
			java.sql.PreparedStatement pstmtNAsP = null;
			String cSQLNAsP = "";

			cSQLNAsP = "select CNOMBRE, CAPPATERNO, CAPMATERNO, {FN YEAR (DTNACIMIENTO)} from PERDATOS where ICVEPERSONAL = "
					+ NumeroPersonal + "";
			pstmtNAsP = conn.prepareStatement(cSQLNAsP);
			java.sql.ResultSet rsetNAsP = pstmt.executeQuery();
			rsetNAsP = pstmtNAsP.executeQuery();
			while (rsetNAsP.next()) {
				NombreP = rsetNAsP.getString(1);
				ApellidoPP = rsetNAsP.getString(2);
				ApellidoMP = rsetNAsP.getString(3);
				AnioNac = rsetNAsP.getInt(4);
			}
			NombrePersona = "" + ApellidoPP + " " + ApellidoMP + " " + NombreP
					+ "";
			if (rsetNAsP != null)
				rsetNAsP.close();
			if (pstmtNAsP != null)
				pstmtNAsP.close();

			// Obtener Nacionalidad
			String Nacionalidad = "";
			String NacionalidadIng = "";
			java.sql.PreparedStatement pstmtNac = null;
			String cSQLNac = "";
			cSQLNac = "SELECT CDSCNACIONAL, CDSCNACING FROM GRLNACIONALIDAD AS N, PERDATOS AS P WHERE P.ICVEPAISNAC = N.ICVEPAIS AND P.ICVEPERSONAL = "
					+ NumeroPersonal + "";
			pstmtNac = conn.prepareStatement(cSQLNac);
			java.sql.ResultSet rsetNac = pstmtNac.executeQuery();
			while (rsetNac.next()) {
				Nacionalidad = rsetNac.getString(1);
				NacionalidadIng = rsetNac.getString(2);
			}
			if (rsetNAsP != null)
				rsetNAsP.close();
			if (pstmtNAsP != null)
				pstmtNAsP.close();

			// Obtener Hora y minutos
			String HM = "";
			String Hra = "";
			int milsec = 0;
			int second = 0;
			int minutos = 0;
			int hora = 0;
			int day = 0;
			int mees = 0;
			int aniio = 0;
			String milsec2 = "";
			String second2 = "";
			String minutos2 = "";
			String hora2 = "";
			String day2 = "";
			String mees2 = "";
			String aniio2 = "";
			String MedDictaminador = "";
			java.sql.PreparedStatement pstmtHM = null;
			String cSQLHM = "";
			cSQLHM = "select TCONCLUIDO, ICVEUSUDICTAMEN, YEAR(TINIEXA), MONTH(TINIEXA), DAY(TINIEXA), HOUR(TINIEXA), MINUTE(TINIEXA), SECOND(TINIEXA), MICROSECOND(TINIEXA)  from EXPEXAMAPLICA where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " ";
			pstmtHM = conn.prepareStatement(cSQLHM);
			java.sql.ResultSet rsetHM = pstmt.executeQuery();
			rsetHM = pstmtHM.executeQuery();
			while (rsetHM.next()) {
				HM = rsetHM.getString(1);
				MedDictaminador = rsetHM.getString(2);
				aniio = rsetHM.getInt(3);
				mees = rsetHM.getInt(4);
				day = rsetHM.getInt(5);
				hora = rsetHM.getInt(6);
				minutos = rsetHM.getInt(7);
				second = rsetHM.getInt(8);
				milsec = rsetHM.getInt(9);
			}
			if (milsec > 100000) {
				milsec2 = "" + milsec;
			}
			if (milsec < 100000) {
				milsec2 = "0" + milsec;
			}
			if (milsec < 10000) {
				milsec2 = "00" + milsec;
			}
			if (milsec < 1000) {
				milsec2 = "000" + milsec;
			}
			if (milsec < 100) {
				milsec2 = "s0000" + milsec;
			}
			if (milsec < 10) {
				milsec2 = "00000" + milsec;
			}
			if (second < 10) {
				second2 = "0" + second;
			} else {
				second2 = "" + second;
			}
			if (minutos < 10) {
				minutos2 = "0" + minutos;
			} else {
				minutos2 = "" + minutos;
			}
			if (hora < 10) {
				hora2 = "0" + hora;
			} else {
				hora2 = "" + hora;
			}
			if (day < 10) {
				day2 = "0" + day;
			} else {
				day2 = "" + day;
			}
			if (mees < 10) {
				mees2 = "0" + mees;
			} else {
				mees2 = "" + mees;
			}
			aniio2 = "" + aniio;
			Hra = second2 + "" + day2 + "" + minutos2 + "" + aniio2 + ""
					+ hora2 + "" + mees2 + "" + milsec2;
			// Cerrando conexion
			rsetHM.close();
			pstmtHM.close();

			// Obtener Medico Dictaminador
			String MNombre = "";
			String MPaterno = "";
			String MMaterno = "";
			String Doc = "";
			String Doc2 = "";
			java.sql.PreparedStatement pstmtMED = null;
			String cSQLMED = "";

			cSQLMED = "select CNOMBRE, CAPPATERNO, CAPMATERNO from SEGUSUARIO where ICVEUSUARIO = "
					+ MedDictaminador + "";
			pstmtMED = conn.prepareStatement(cSQLMED);
			java.sql.ResultSet rsetMED = pstmt.executeQuery();
			rsetMED = pstmtMED.executeQuery();
			while (rsetMED.next()) {
				MNombre = rsetMED.getString(1);
				MPaterno = rsetMED.getString(2);
				MMaterno = rsetMED.getString(3);
			}
			Doc = " " + MNombre + "  " + MPaterno + "  " + MMaterno + "";
			Doc2 = " " + MPaterno + "  " + MMaterno + "  " + MNombre + "";
			// Cerrando conexion
			rsetMED.close();
			pstmtMED.close();

			// Obtener Datos empresa
			String ClaveEmpresa = "";
			java.sql.PreparedStatement pstmtEmpresa = null;
			String cSQLEmpresa = "";
			cSQLEmpresa = "select ICVENUMEMPRESA from EXPEXAMAPLICA where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " ";
			pstmtEmpresa = conn.prepareStatement(cSQLEmpresa);
			java.sql.ResultSet rsetEmpresa = pstmt.executeQuery();
			rsetEmpresa = pstmtEmpresa.executeQuery();
			while (rsetEmpresa.next()) {
				ClaveEmpresa = rsetEmpresa.getString(1);
			}
			// Cerrando conexion
			rsetEmpresa.close();
			pstmtEmpresa.close();

			String NombreEmpresa = "";
			String NumEcoEmpresa = "";
			java.sql.PreparedStatement pstmtEmpresa2 = null;
			String cSQLEmpresa2 = "";
			// cSQLEmpresa2 =
			// "select CDSCEMPRESA, CIDDGPMPT from GRLEMPRESAS where ICVEEMPRESA = "
			// + ClaveEmpresa + " ";
			cSQLEmpresa2 = "select CDSCEMPRESA from GRLEMPRESAS where ICVEEMPRESA = "
					+ ClaveEmpresa + " ";
			pstmtEmpresa2 = conn.prepareStatement(cSQLEmpresa2);
			java.sql.ResultSet rsetEmpresa2 = pstmt.executeQuery();
			rsetEmpresa2 = pstmtEmpresa2.executeQuery();
			while (rsetEmpresa2.next()) {
				NombreEmpresa = rsetEmpresa2.getString(1);
				// NumEcoEmpresa = rsetEmpresa2.getString(2);
			}

			// Cerrando conexion
			rsetEmpresa2.close();
			pstmtEmpresa2.close();

			// Obtener Numero Economico o Matricula

			java.sql.PreparedStatement pstmtEconomico = null;
			String cSQLEconomico = "";
			/*cSQLEconomico = "select CMATRICULA from EMOEXAMEN where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " ";
			pstmtEconomico = conn.prepareStatement(cSQLEconomico);
			java.sql.ResultSet rsetEconomico = pstmt.executeQuery();
			rsetEconomico = pstmtEconomico.executeQuery();
			while (rsetEconomico.next()) {
				NumEcoEmpresa = rsetEconomico.getString(1);
			}

			// Cerrando conexion
			rsetEconomico.close();
			pstmtEconomico.close(); */

			// Obtener Auscultaci√≥n Area Card√≠aca
			String Frecuencia = "";
			java.sql.PreparedStatement pstmtFrec = null;
			String cSQLFrec = "";

			cSQLFrec = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 24 ";
			pstmtFrec = conn.prepareStatement(cSQLFrec);
			java.sql.ResultSet rsetFrec = pstmt.executeQuery();
			rsetFrec = pstmtFrec.executeQuery();
			while (rsetFrec.next()) {
				Frecuencia = rsetFrec.getString(1);
			}
			// Cerrando conexion
			rsetFrec.close();
			pstmtFrec.close();

			// Obtener Pulso y Ritmo
			String Pulso = "";
			String Ritmos = "";
			java.sql.PreparedStatement pstmtPul = null;
			String cSQLPul = "";
			cSQLPul = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 21 ";
			pstmtPul = conn.prepareStatement(cSQLPul);
			java.sql.ResultSet rsetPul = pstmt.executeQuery();
			rsetPul = pstmtPul.executeQuery();
			while (rsetPul.next()) {
				Ritmos = rsetPul.getString(1);
			}
			if (Ritmos != null) {
				if (Ritmos.equals("1")) {
					Pulso = "Positivo";
				} else {
					if (Ritmos.equals("0")) {
						Pulso = "Negativo";
					} else {
						Pulso = " ";
					}
				}
			} else {
				Ritmos = "";
			}
			// Cerrando conexion
			rsetPul.close();
			pstmtPul.close();

			// Obtener Alcohol en aliento
			String AlcoholA = "";
			String AlcoholAliento = "";

			java.sql.PreparedStatement pstmtAA = null;
			String cSQLAA = "";
			cSQLAA = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 41 ";
			pstmtAA = conn.prepareStatement(cSQLAA);
			java.sql.ResultSet rsetAA = pstmt.executeQuery();
			rsetAA = pstmtAA.executeQuery();
			while (rsetAA.next()) {
				AlcoholA = rsetAA.getString(1);
			}
			if (AlcoholA != null) {
				if (AlcoholA.equals("1")) {
					AlcoholAliento = "Positivo";
				} else {
					if (AlcoholA.equals("0")) {
						AlcoholAliento = "Negativo";
					} else {
						AlcoholAliento = " ";
					}
				}
			} else {
				AlcoholA = "";
			}
			// Cerrando conexion
			rsetAA.close();
			pstmtAA.close();

			// Obtener Reflejo Fotomotor
			String ReflejoF = "";
			String ReflejoFotomotor = "";

			java.sql.PreparedStatement pstmtRF = null;
			String cSQLRF = "";
			cSQLRF = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 26 ";
			pstmtRF = conn.prepareStatement(cSQLRF);
			java.sql.ResultSet rsetRF = pstmt.executeQuery();
			rsetRF = pstmtRF.executeQuery();
			while (rsetRF.next()) {
				ReflejoF = rsetRF.getString(1);
			}
			if (ReflejoF != null) {
				if (ReflejoF.equals("1")) {
					ReflejoFotomotor = "Positivo";
				} else {
					if (ReflejoFotomotor.equals("0")) {
						ReflejoFotomotor = "Negativo";
					} else {
						ReflejoFotomotor = " ";
					}
				}
			} else {
				ReflejoF = "";
			}
			// Cerrando conexion
			rsetRF.close();
			pstmtRF.close();

			// Obtener Romberg negativo
			String Romberg1 = "";
			String Romberg = "";

			java.sql.PreparedStatement pstmtRom = null;
			String cSQLRom = "";
			cSQLRom = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 39 ";
			pstmtRom = conn.prepareStatement(cSQLRom);
			java.sql.ResultSet rsetRom = pstmt.executeQuery();
			rsetRom = pstmtRom.executeQuery();
			while (rsetRom.next()) {
				Romberg1 = rsetRom.getString(1);
			}
			if (Romberg1 != null) {
				if (Romberg1.equals("1")) {
					Romberg = "Positivo";
				} else {
					if (Romberg1.equals("0")) {
						Romberg = "Negativo";
					} else {
						Romberg = " ";
					}
				}
			} else {
				Romberg1 = "";
			}
			// Cerrando conexion
			rsetRom.close();
			pstmtRom.close();

			// Obtener Relejo Osteotendinosos
			String Osteotendinosos = "";

			java.sql.PreparedStatement pstmtOST = null;
			String cSQLOST = "";
			cSQLOST = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 37 ";
			pstmtOST = conn.prepareStatement(cSQLOST);
			java.sql.ResultSet rsetOST = pstmt.executeQuery();
			rsetOST = pstmtOST.executeQuery();
			while (rsetOST.next()) {
				Osteotendinosos = rsetOST.getString(1);
			}
			// Cerrando conexion
			rsetOST.close();
			pstmtOST.close();

			// Obtener Glucometr√≠a
			String Glucometria = "";

			java.sql.PreparedStatement pstmtGlu = null;
			String cSQLGlu = "";
			cSQLGlu = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 58 ";
			pstmtGlu = conn.prepareStatement(cSQLGlu);
			java.sql.ResultSet rsetGlu = pstmt.executeQuery();
			rsetGlu = pstmtGlu.executeQuery();
			while (rsetGlu.next()) {
				Glucometria = rsetGlu.getString(1);
			}
			// Cerrando conexion
			rsetGlu.close();
			pstmtGlu.close();

			// Obtener Coloracion Piel Mucosa
			String ColorPielMucosa = "";

			java.sql.PreparedStatement pstmtCPM = null;
			String cSQLCPM = "";
			cSQLCPM = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 57 ";
			pstmtCPM = conn.prepareStatement(cSQLCPM);
			java.sql.ResultSet rsetCPM = pstmt.executeQuery();
			rsetCPM = pstmtCPM.executeQuery();
			while (rsetCPM.next()) {
				ColorPielMucosa = rsetCPM.getString(1);
			}
			// Cerrando conexion
			rsetCPM.close();
			pstmtCPM.close();

			// Obtener Estado de Hidrataci√≥n
			String EstadoHidratacion = "";

			java.sql.PreparedStatement pstmtEH = null;
			String cSQLEH = "";
			cSQLEH = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 55 ";
			pstmtEH = conn.prepareStatement(cSQLEH);
			java.sql.ResultSet rsetEH = pstmt.executeQuery();
			rsetEH = pstmtEH.executeQuery();
			while (rsetEH.next()) {
				EstadoHidratacion = rsetEH.getString(1);
			}
			// Cerrando conexion
			rsetEH.close();
			pstmtEH.close();

			// Obtener Estado de Alerta
			String EstadoAlerta = "";

			java.sql.PreparedStatement pstmtEA = null;
			String cSQLEA = "";
			cSQLEA = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 59 ";
			pstmtEA = conn.prepareStatement(cSQLEA);
			java.sql.ResultSet rsetEA = pstmt.executeQuery();
			rsetEA = pstmtEA.executeQuery();
			while (rsetEA.next()) {
				EstadoAlerta = rsetEA.getString(1);
			}
			// Cerrando conexion
			rsetEA.close();
			pstmtEA.close();

			// Obtener Marcha
			String Marcha = "";

			java.sql.PreparedStatement pstmtMarcha = null;
			String cSQLMarcha = "";
			cSQLMarcha = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 7 ";
			pstmtMarcha = conn.prepareStatement(cSQLMarcha);
			java.sql.ResultSet rsetMarcha = pstmt.executeQuery();
			rsetMarcha = pstmtMarcha.executeQuery();
			while (rsetMarcha.next()) {
				Marcha = rsetMarcha.getString(1);
			}
			// Cerrando conexion
			rsetMarcha.close();
			pstmtMarcha.close();

			// Obtener Lenguaje
			String Lenguaje = "";

			java.sql.PreparedStatement pstmtLenguaje = null;
			String cSQLLenguaje = "";
			cSQLLenguaje = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 48 ";
			pstmtLenguaje = conn.prepareStatement(cSQLLenguaje);
			java.sql.ResultSet rsetLenguaje = pstmt.executeQuery();
			rsetLenguaje = pstmtLenguaje.executeQuery();
			while (rsetLenguaje.next()) {
				Lenguaje = rsetLenguaje.getString(1);
			}
			// Cerrando conexion
			rsetLenguaje.close();
			pstmtLenguaje.close();

			// Obtener Orientacion
			String Orientacion = "";

			java.sql.PreparedStatement pstmtOrientacion = null;
			String cSQLOrientacion = "";
			cSQLOrientacion = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 49 ";
			pstmtOrientacion = conn.prepareStatement(cSQLOrientacion);
			java.sql.ResultSet rsetOrientacion = pstmt.executeQuery();
			rsetOrientacion = pstmtOrientacion.executeQuery();
			while (rsetOrientacion.next()) {
				Orientacion = rsetOrientacion.getString(1);
			}
			// Cerrando conexion
			rsetOrientacion.close();
			pstmtOrientacion.close();

			// Obtener Pupilas
			String Pupilas = "";

			java.sql.PreparedStatement pstmtPupilas = null;
			String cSQLPupilas = "";
			cSQLPupilas = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 54 ";
			pstmtPupilas = conn.prepareStatement(cSQLPupilas);
			java.sql.ResultSet rsetPupilas = pstmt.executeQuery();
			rsetPupilas = pstmtPupilas.executeQuery();
			while (rsetPupilas.next()) {
				Pupilas = rsetPupilas.getString(1);
			}
			// Cerrando conexion
			rsetPupilas.close();
			pstmtPupilas.close();

			// Obtener IndicePuntaNariz
			String IPN = "";

			java.sql.PreparedStatement pstmtIPN = null;
			String cSQLIPN = "";
			cSQLIPN = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 56 ";
			pstmtIPN = conn.prepareStatement(cSQLIPN);
			java.sql.ResultSet rsetIPN = pstmt.executeQuery();
			rsetIPN = pstmtIPN.executeQuery();
			while (rsetIPN.next()) {
				IPN = rsetIPN.getString(1);
			}
			// Cerrando conexion
			rsetIPN.close();
			pstmtIPN.close();

			// Obtener TipoLente
			String TipoLente = "";

			java.sql.PreparedStatement pstmtTipoLente = null;
			String cSQLTipoLente = "";
			cSQLTipoLente = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 50 ";
			pstmtTipoLente = conn.prepareStatement(cSQLTipoLente);
			java.sql.ResultSet rsetTipoLente = pstmt.executeQuery();
			rsetTipoLente = pstmtTipoLente.executeQuery();
			while (rsetTipoLente.next()) {
				TipoLente = rsetTipoLente.getString(1);
			}
			// Cerrando conexion
			rsetTipoLente.close();
			pstmtTipoLente.close();

			// Obtener UsodeLente
			String UsodeLente = "";

			java.sql.PreparedStatement pstmtUsodeLente = null;
			String cSQLUsodeLente = "";
			cSQLUsodeLente = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 11 ";
			pstmtUsodeLente = conn.prepareStatement(cSQLUsodeLente);
			java.sql.ResultSet rsetUsodeLente = pstmt.executeQuery();
			rsetUsodeLente = pstmtUsodeLente.executeQuery();
			while (rsetUsodeLente.next()) {
				UsodeLente = rsetUsodeLente.getString(1);
			}
			// Cerrando conexion
			rsetUsodeLente.close();
			pstmtUsodeLente.close();

			// Obtener Reflejo osteotendinoso derecho
			String ROD = "";

			java.sql.PreparedStatement pstmtROD = null;
			String cSQLROD = "";
			cSQLROD = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 52 ";
			pstmtROD = conn.prepareStatement(cSQLROD);
			java.sql.ResultSet rsetROD = pstmt.executeQuery();
			rsetROD = pstmtROD.executeQuery();
			while (rsetROD.next()) {
				ROD = rsetROD.getString(1);
			}
			// Cerrando conexion
			rsetROD.close();
			pstmtROD.close();

			// Obtener Reflejo osteotendinoso izquierdo
			String ROI = "";

			java.sql.PreparedStatement pstmtROI = null;
			String cSQLROI = "";
			cSQLROI = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 53 ";
			pstmtROI = conn.prepareStatement(cSQLROI);
			java.sql.ResultSet rsetROI = pstmt.executeQuery();
			rsetROI = pstmtROI.executeQuery();
			while (rsetROI.next()) {
				ROI = rsetROI.getString(1);
			}
			// Cerrando conexion
			rsetROI.close();
			pstmtROI.close();

			// Obtener Frecuaencia Respiratoria
			String FRES = "";

			java.sql.PreparedStatement pstmtFRES = null;
			String cSQLFRES = "";
			cSQLFRES = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 51 ";
			pstmtFRES = conn.prepareStatement(cSQLFRES);
			java.sql.ResultSet rsetFRES = pstmt.executeQuery();
			rsetFRES = pstmtFRES.executeQuery();
			while (rsetFRES.next()) {
				FRES = rsetFRES.getString(1);
			}
			// Cerrando conexion
			rsetFRES.close();
			pstmtFRES.close();

			// Obtener Soplos Cardiacos
			String Soplos = "";

			java.sql.PreparedStatement pstmtSoplos = null;
			String cSQLSoplos = "";
			cSQLSoplos = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 22 ";
			pstmtSoplos = conn.prepareStatement(cSQLSoplos);
			java.sql.ResultSet rsetSoplos = pstmt.executeQuery();
			rsetSoplos = pstmtSoplos.executeQuery();
			while (rsetSoplos.next()) {
				Soplos = rsetSoplos.getString(1);
			}
			// Cerrando conexion
			rsetSoplos.close();
			pstmtSoplos.close();

			// Obtener ESTADOOR
			String ESTADOOR = "";
/*
			java.sql.PreparedStatement pstmtESTADOOR = null;
			String cSQLESTADOOR = "";
			cSQLESTADOOR = "SELECT " + " GRLE.CNOMBRE " + " FROM "
					+ " EMOEXAMEN AS EMO, GRLENTIDADFED AS GRLE " + " WHERE "
					+ " EMO.ICVEPAISOR = GRLE.ICVEPAIS		AND "
					+ " EMO.ICVEEDOOR = GRLE.ICVEENTIDADFED	AND "
					+ " EMO.ICVEEXPEDIENTE = " + ClaveExpediente + "		AND "
					+ " EMO.INUMEXAMEN = " + NumeroExamen + "			 ";
			pstmtESTADOOR = conn.prepareStatement(cSQLESTADOOR);
			java.sql.ResultSet rsetESTADOOR = pstmt.executeQuery();
			rsetESTADOOR = pstmtESTADOOR.executeQuery();
			while (rsetESTADOOR.next()) {
				ESTADOOR = rsetESTADOOR.getString(1);
			}
			// Cerrando conexion
			rsetESTADOOR.close();
			pstmtESTADOOR.close();*/

			// Obtener ESTADODES
			String ESTADODES = "";
/*
			java.sql.PreparedStatement pstmtESTADODES = null;
			String cSQLESTADODES = "";
			cSQLESTADODES = "SELECT " + " GRLE.CNOMBRE " + " FROM "
					+ " EMOEXAMEN AS EMO, GRLENTIDADFED AS GRLE " + " WHERE "
					+ " EMO.ICVEPAISDES = GRLE.ICVEPAIS		AND "
					+ " EMO.ICVEEDODES = GRLE.ICVEENTIDADFED	AND "
					+ " EMO.ICVEEXPEDIENTE = " + ClaveExpediente + "		AND "
					+ " EMO.INUMEXAMEN = " + NumeroExamen + "			 ";
			pstmtESTADODES = conn.prepareStatement(cSQLESTADODES);
			java.sql.ResultSet rsetESTADODES = pstmt.executeQuery();
			rsetESTADODES = pstmtESTADODES.executeQuery();
			while (rsetESTADODES.next()) {
				ESTADODES = rsetESTADODES.getString(1);
			}
			// Cerrando conexion
			rsetESTADODES.close();
			pstmtESTADODES.close();
*/
			// Obtener Observaciones
			String Observaciones = "";

			java.sql.PreparedStatement pstmtObservaciones = null;
			String cSQLObservaciones = "";
			cSQLObservaciones = "SELECT CNOTAMEDICA FROM EXPSERVICIO WHERE ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ "   AND INUMEXAMEN = "
					+ NumeroExamen
					+ "  AND ICVESERVICIO = 1";
			pstmtObservaciones = conn.prepareStatement(cSQLObservaciones);
			java.sql.ResultSet rsetObservaciones = pstmt.executeQuery();
			rsetObservaciones = pstmtObservaciones.executeQuery();
			while (rsetObservaciones.next()) {
				Observaciones = rsetObservaciones.getString(1);
			}
			// Cerrando conexion
			rsetObservaciones.close();
			pstmtObservaciones.close();

			// Obtener Observaciones y Restricciones
			String ObserRestric = "";

			java.sql.PreparedStatement pstmtObserRestric = null;
			String cSQLObserRestric = "";
			cSQLObserRestric = "SELECT CNOTAMEDICA FROM EXPSERVICIO WHERE ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " AND ICVESERVICIO = 44";
			pstmtObserRestric = conn.prepareStatement(cSQLObserRestric);
			java.sql.ResultSet rsetObserRestric = pstmt.executeQuery();
			rsetObserRestric = pstmtObserRestric.executeQuery();
			while (rsetObserRestric.next()) {
				ObserRestric = rsetObserRestric.getString(1);
			}
			// Cerrando conexion
			rsetObserRestric.close();
			pstmtObserRestric.close();

			// Obtener PruebaTox
			String PruebaTox = "";

			java.sql.PreparedStatement pstmtPruebaTox = null;
			String cSQLPruebaTox = "";
			cSQLPruebaTox = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 60 ";
			pstmtPruebaTox = conn.prepareStatement(cSQLPruebaTox);
			java.sql.ResultSet rsetPruebaTox = pstmt.executeQuery();
			rsetPruebaTox = pstmtPruebaTox.executeQuery();
			while (rsetPruebaTox.next()) {
				PruebaTox = rsetPruebaTox.getString(1);
			}
			// Cerrando conexion
			rsetPruebaTox.close();
			pstmtPruebaTox.close();

			// Obtener HorasLab
			String HorasLab = "";

			// System.out.println("Constancia1");

			java.sql.PreparedStatement pstmtHorasLab = null;
			String cSQLHorasLab = "";
			cSQLHorasLab = "select LLOGICO from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 61 ";
			pstmtHorasLab = conn.prepareStatement(cSQLHorasLab);
			java.sql.ResultSet rsetHorasLab = pstmt.executeQuery();
			rsetHorasLab = pstmtHorasLab.executeQuery();
			while (rsetHorasLab.next()) {
				HorasLab = rsetHorasLab.getString(1);
			}
			// Cerrando conexion
			rsetHorasLab.close();
			pstmtHorasLab.close();

			// Obtener HorasLab24
			String HorasLab24 = "";
			// System.out.println("Constancia2");
			java.sql.PreparedStatement pstmtHorasLab24 = null;
			String cSQLHorasLab24 = "";
			cSQLHorasLab24 = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 14 ";
			pstmtHorasLab24 = conn.prepareStatement(cSQLHorasLab24);
			java.sql.ResultSet rsetHorasLab24 = pstmt.executeQuery();
			rsetHorasLab24 = pstmtHorasLab24.executeQuery();
			while (rsetHorasLab24.next()) {
				HorasLab24 = rsetHorasLab24.getString(1);
			}
			// Cerrando conexion
			rsetHorasLab24.close();
			pstmtHorasLab24.close();

			// Obtener PerDescanzo
			String PerDescanzo = "";

			java.sql.PreparedStatement pstmtPerDescanzo = null;
			String cSQLPerDescanzo = "";
			cSQLPerDescanzo = "select DVALORINI from EXPRESULTADO where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "   AND   ICVESERVICIO = 1   AND   ICVERAMA = 9   AND   ICVESINTOMA = 62 ";
			pstmtPerDescanzo = conn.prepareStatement(cSQLPerDescanzo);
			java.sql.ResultSet rsetPerDescanzo = pstmt.executeQuery();
			rsetPerDescanzo = pstmtPerDescanzo.executeQuery();
			while (rsetPerDescanzo.next()) {
				PerDescanzo = rsetPerDescanzo.getString(1);
			}
			// Cerrando conexion
			rsetPerDescanzo.close();
			pstmtPerDescanzo.close();
			// System.out.println("Constancia3");
			// Obtener Fecha del examen
			String fechadelexamen = "";

			java.sql.PreparedStatement pstmtFEX = null;
			String cSQLFEX = "";
			cSQLFEX = "SELECT DtAplicacion FROM EXPEXAMAPLICA where icveexpediente = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ "";
			pstmtFEX = conn.prepareStatement(cSQLFEX);
			java.sql.ResultSet rsetFEX = pstmt.executeQuery();
			rsetFEX = pstmtFEX.executeQuery();
			while (rsetFEX.next()) {
				fechadelexamen = rsetFEX.getString(1);
			}
			// Cerrando conexion
			rsetFEX.close();
			pstmtFEX.close();

			// Obtener Motivacion
			String Motivacion = "";
			String DiagnosticoNA = "";
			String MotivacionNA = "";

			java.sql.PreparedStatement pstmtMot = null;
			String cSQLMot = "";
			cSQLFEX = "select M.cdscDiagnostico, D.cmotivacion "
					+ "from ExpDiagnostico as D, MedDiagnostico as M "
					+ "where D.icveexpediente = "
					+ ClaveExpediente
					+ "  and D.inumexamen = "
					+ NumeroExamen
					+ " and "
					+ "D.icveespecialidad = M.icveespecialidad and D.icvediagnostico = M.icvediagnostico  and D.icveservicio = 31  Order by D.tidiagnostico  asc ";
			pstmtMot = conn.prepareStatement(cSQLFEX);
			java.sql.ResultSet rsetMot = pstmt.executeQuery();
			rsetMot = pstmtMot.executeQuery();
			while (rsetMot.next()) {
				Motivacion = Motivacion + "" + rsetMot.getString(1) + "  /  "
						+ rsetMot.getString(2) + ".\n";
				DiagnosticoNA = "" + rsetMot.getString(1) + "";
				MotivacionNA = "" + rsetMot.getString(2) + "";
			}
			// Cerrando conexion
			rsetMot.close();
			pstmtMot.close();

			// Obtener Diagnostico para no apto E.M.O.
			String DiagNAemo = "";
			String MotNAemo = "";

			java.sql.PreparedStatement pstmtDNAEMO = null;
			String cSQLDNAEMO = "";
			cSQLDNAEMO = "select M.cdscDiagnostico, D.cmotivacion "
					+ " from ExpDiagnostico as D, MedDiagnostico as M "
					+ "where D.icveexpediente = "
					+ ClaveExpediente
					+ "  and D.inumexamen =  "
					+ NumeroExamen
					+ " and "
					+ "D.icveespecialidad = M.icveespecialidad and D.icvediagnostico = M.icvediagnostico  and D.icveservicio = 1 and "
					+ "D.tidiagnostico = (select MIN( D2.tidiagnostico)  "
					+ "from ExpDiagnostico as D2, MedDiagnostico as M2  "
					+ "where D2.icveexpediente = "
					+ ClaveExpediente
					+ "  and D2.inumexamen =  "
					+ NumeroExamen
					+ " and  "
					+ "D2.icveespecialidad = M2.icveespecialidad and D2.icvediagnostico = M2.icvediagnostico  and D2.icveservicio = 1)";

			pstmtDNAEMO = conn.prepareStatement(cSQLDNAEMO);
			java.sql.ResultSet rsetDNAEMO = pstmtDNAEMO.executeQuery();
			rsetDNAEMO = pstmtDNAEMO.executeQuery();
			while (rsetDNAEMO.next()) {
				DiagNAemo = "" + rsetDNAEMO.getString(1);
				MotNAemo = "" + rsetDNAEMO.getString(2);
			}
			// Cerrando conexion
			rsetDNAEMO.close();
			pstmtDNAEMO.close();

			// Obtener Fundamentacion
			String Fundamentacion = "";

			java.sql.PreparedStatement pstmtFun = null;
			String cSQLFun = "";
			cSQLFun = "select cFundamentacion from expfundictamen where icveexpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ "";
			pstmtFun = conn.prepareStatement(cSQLFun);
			java.sql.ResultSet rsetFun = pstmt.executeQuery();
			rsetFun = pstmtFun.executeQuery();
			while (rsetFun.next()) {
				Fundamentacion = rsetFun.getString(1);
			}
			// Cerrando conexion
			rsetFun.close();
			pstmtFun.close();

			// Obtener si es apto o no
			String AptoSN = "";

			java.sql.PreparedStatement pstmtApto = null;
			String cSQLApto = "";
			cSQLApto = "select LDICTAMEN from EXPEXAMCAT where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " ";
			pstmtApto = conn.prepareStatement(cSQLApto);
			java.sql.ResultSet rsetApto = pstmt.executeQuery();
			rsetApto = pstmtApto.executeQuery();
			while (rsetApto.next()) {
				AptoSN = rsetApto.getString(1);
			}
			// Cerrando conexion
			rsetApto.close();
			pstmtApto.close();

			// Variables Obtener clave CIE y Diagnostico
			int ClaveEspecialidad = 0;
			int ClaveDiagnostico = 0;

			String ClaveEspecialidad2 = "_";
			String ClaveDiagnostico2 = "_";

			String DDiagnostico = "";
			String ClaveCIE = "";

			// Variables Actaulizar Letra y Numero de Foliio
			String EMOP = "2";
			String CveProceso = "" + request.getParameter("iCveProceso");
			int ContadorFolio = 0;
			int ContadorFolio2 = 0;
			String Letra = "";
			String ContadorFolioS = "";
			String ContadorFolio2S = "";
			String NumFolio = "";

			if (AptoSN.equals("1")) {

				java.sql.PreparedStatement pstmtCieDiag = null;
				String cSQLCieDiag = "";
				cSQLCieDiag = "select ICVEDIAGNOSTICO, ICVEESPECIALIDAD from EXPDIAGNOSTICO where ICVEEXPEDIENTE = "
						+ ClaveExpediente
						+ " AND INUMEXAMEN = "
						+ NumeroExamen
						+ " ";
				pstmtCieDiag = conn.prepareStatement(cSQLCieDiag);
				java.sql.ResultSet rsetCieDiag = pstmt.executeQuery();
				rsetCieDiag = pstmtCieDiag.executeQuery();
				while (rsetCieDiag.next()) {
					ClaveDiagnostico = rsetCieDiag.getInt(1);
					ClaveEspecialidad = rsetCieDiag.getInt(2);
				}
				// Cerrando conexion
				rsetCieDiag.close();
				pstmtCieDiag.close();

				if (ClaveDiagnostico >= 0 && ClaveEspecialidad >= 0) {
					ClaveDiagnostico2 = String.valueOf(ClaveDiagnostico);
					ClaveEspecialidad2 = String.valueOf(ClaveEspecialidad);

					java.sql.PreparedStatement pstmtCieDiag2 = null;
					String cSQLCieDiag2 = "";

					cSQLCieDiag2 = "select CDSCDIAGNOSTICO, CCIE from MEDDIAGNOSTICO where ICVEESPECIALIDAD = "
							+ ClaveEspecialidad2
							+ " AND ICVEDIAGNOSTICO = "
							+ ClaveDiagnostico2 + " ";
					pstmtCieDiag2 = conn.prepareStatement(cSQLCieDiag2);
					java.sql.ResultSet rsetCieDiag2 = pstmt.executeQuery();
					rsetCieDiag2 = pstmtCieDiag2.executeQuery();
					while (rsetCieDiag2.next()) {
						DDiagnostico = rsetCieDiag2.getString(1);
						ClaveCIE = rsetCieDiag2.getString(2);
					}
					// Cerrando conexion
					rsetCieDiag2.close();
					pstmtCieDiag2.close();
				}

				if (DDiagnostico.equals("") || DDiagnostico.equals(" ")
						|| DDiagnostico.equals(null)) {
					DDiagnostico = "ClÌnicamente Sano";
				}

				// Metodo para Actaulizar Letra y Numero de Foliio

				if (CveProceso.equals(EMOP)) {
					// Actualizar y obtener numero de folio
					java.sql.PreparedStatement pstmtNumFolio = null;
					String cSQLNumFolio = "select LETRAFOLIO, NUMEROFOLIO from NUMFOLIO";
					pstmtNumFolio = conn.prepareStatement(cSQLNumFolio);
					java.sql.ResultSet rsetNumFolio = pstmtNumFolio
							.executeQuery();
					while (rsetNumFolio.next()) {
						Letra = rsetNumFolio.getString(1);
						ContadorFolio = rsetNumFolio.getInt(2);
					}

					ContadorFolioS = String.valueOf(ContadorFolio);

					if (ContadorFolio == 9999999) {
						ContadorFolio = 0;
						char Letra2;
						Letra2 = Letra.charAt(0);
						int j = Letra2; // Convierte la letra a su valor
										// en ascii y se almacena en j
						j++; // Incrementamos J para tener el valor de la
								// siguiente letra en ascii
						char Letra3 = (char) j; // Convierte el int a letra
						Letra = Letra3 + ""; // Asignamos el char que contiene
												// la nueva letra al string
						// Actalizar Letra
						String SQLLetraFolio = "update NUMFOLIO set LETRAFOLIO = '"
								+ Letra
								+ "' where NUMEROFOLIO = "
								+ ContadorFolioS;
						pstmtNumFolio = conn.prepareStatement(SQLLetraFolio);
						pstmtNumFolio.executeUpdate();
					}

					ContadorFolio2 = ContadorFolio;
					ContadorFolio2++;

					ContadorFolio2S = String.valueOf(ContadorFolio2);
					int numcar = ContadorFolio2S.length();
					if (numcar == 1) {
						ContadorFolio2S = "000000" + ContadorFolio2S;
					}
					if (numcar == 2) {
						ContadorFolio2S = "00000" + ContadorFolio2S;
					}
					if (numcar == 3) {
						ContadorFolio2S = "0000" + ContadorFolio2S;
					}
					if (numcar == 4) {
						ContadorFolio2S = "000" + ContadorFolio2S;
					}
					if (numcar == 5) {
						ContadorFolio2S = "00" + ContadorFolio2S;
					}
					if (numcar == 6) {
						ContadorFolio2S = "0" + ContadorFolio2S;
					}

					NumFolio = Letra + ContadorFolio2S;

					String SQLNumFolio = "update NUMFOLIO set NUMEROFOLIO = "
							+ ContadorFolio2S + " where NUMEROFOLIO = "
							+ ContadorFolioS;

					pstmtNumFolio = conn.prepareStatement(SQLNumFolio);
					pstmtNumFolio.executeUpdate();
					// Cerrando conexion
					rsetNumFolio.close();
					pstmtNumFolio.close();
				}
			}

			// Obtener y Validar Modo Transporte
			String ModoTransporte = "";

			java.sql.PreparedStatement pstmtMT = null;
			String cSQLMT = "";

			cSQLMT = "select ICVEMDOTRANS from EXPEXAMCAT where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " ";
			pstmtMT = conn.prepareStatement(cSQLMT);
			java.sql.ResultSet rsetMT = pstmt.executeQuery();
			rsetMT = pstmtMT.executeQuery();
			while (rsetMT.next()) {
				ModoTransporte = rsetMT.getString(1);
			}
			rsetMT.close();
			pstmtMT.close();

			String PCarretero = "";
			String PAereo = "";
			String PMaritimo = "";
			String PFerroviario = "";

			if (ModoTransporte != null) {
				if (ModoTransporte.equals("1")) {
					PAereo = "X";
				}

				if (ModoTransporte.equals("2")) {
					PCarretero = "X";
				}

				if (ModoTransporte.equals("3")) {
					PFerroviario = "X";
				}

				if (ModoTransporte.equals("4")) {
					PMaritimo = "X";
				}
			}

			
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			dbConn.closeConnection();

			// Obtener clave proceso
			String ClaveProceso;
			String Psicofisico = "1";
			String EMO = "2";
			String AptoPro = " ";
			ClaveProceso = "" + request.getParameter("iCveProceso");
			if (ClaveProceso.equals(Psicofisico)) {
				AptoPro = "PSICOFISICO INTEGRAL";
			}
			if (ClaveProceso.equals(EMO)) {
				AptoPro = "MEDICO EN OPERACI”N";
			}

			// Generaci√≥n de formato por Categor√≠a
			// System.setProperty("java.awt.headless","true");
			Dictamenes dictamenes = new Dictamenes();
			for (int i = 0; i < VResultado.size(); i++) {
				VDictamen = new TVEXPDictamen();
				VDictamen = (TVEXPDictamen) VResultado.get(i);

				// Obtener fecha para validar que codigo de barras se imprimira
				boolean NuevoCodigoBarras = false;
				SimpleDateFormat formatoDelTexto2 = new SimpleDateFormat(
						"yyyy-MM-dd");
				String FechaCambioCodigoBarras = VParametros
						.getPropEspecifica("FechaCambioCodigoBarras");
				Date fechaCCB = null;
				Date FechaDictamen = null;
				try {
					fechaCCB = formatoDelTexto2.parse(FechaCambioCodigoBarras);
					FechaDictamen = formatoDelTexto2.parse(VDictamen
							.getDtDictamen().toString());
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				if (fechaCCB.compareTo(FechaDictamen) < 0) {
					NuevoCodigoBarras = true;
					// System.out.println("date es menor al dictaminado (Imprime nuevo codigo barras)");
				}

				// tamaÒo maximo de foto en constancia
				int TamMaxfoto = Integer.parseInt(VParametros
						.getPropEspecifica("TamanioMaxFotoBytes"));

				if (VDictamen.VExamCat.getLDictamen() == 1) {
					// Dictamenes de Aptitud

					if (ClaveProceso.equals(EMO))// Inicia Proceso Apto EMO
					{

						VDictamen.getDtSolicitado();

						// DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

						// Date d1 = df.parse("2008-12-31");
						// Date d1 = df.parse("2013-06-15");

						SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
								"yyyy-MM-dd");
						// String strFecha = "2013-04-15";
						String strFecha = VParametros
								.getPropEspecifica("FechaEmoV2");
						Date fecha = null;
						Date Solicitado = null;
						TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
						String TiniExa = "select DATE(TINIEXA) from EXPEXAMAPLICA where ICVEEXPEDIENTE = "
								+ ClaveExpediente
								+ " AND INUMEXAMEN = "
								+ NumeroExamen + " ";
						try {
							fecha = formatoDelTexto.parse(strFecha);
							// Solicitado =
							// formatoDelTexto.parse(VDictamen.getDtSolicitado().toString());
							Solicitado = formatoDelTexto.parse(dEXPExamAplica
									.RegresaS(TiniExa).toString());
						} catch (ParseException ex) {
							ex.printStackTrace();
						}

						// System.out.println("Comparara fechas "+ fecha
						// +" - "+Solicitado);

						if (fecha.compareTo(Solicitado) < 0) {
							// System.out.println("date es menor al solicitado (Imprime nueva constancia)");
							DictamenAEmoVDos dictamen = new DictamenAEmoVDos();
							dictamen.setCategoria(VDictamen.VExamCat
									.getCDscCategoria());
							dictamen.setDescUnidadMedica(VDictamen.VUniMed
									.getCDscUniMed());
							dictamen.setCdscModulo(VDictamen.vModulo
									.getCDscModulo());
							dictamen.setFechaExamen(pFecha.getFechaDDMMYYYY(
									VDictamen.VExamCat.getDtInicioVig(), "/"));
							dictamen.setHM(HM);
							dictamen.setCiudadOrigen(CiudadOrigen);
							dictamen.setCiudadDestino(CiudadDestino);
							dictamen.setPNombre(NombreP);
							dictamen.setPPaterno(ApellidoPP);
							dictamen.setPMaterno(ApellidoMP);
							dictamen.setNumAntExpDGPMPT(VDictamen.VPerDatos
									.getCExpediente());
							dictamen.setRfc(VDictamen.VPerDatos.getCRFC());
							dictamen.setPCarretero(PCarretero);
							dictamen.setPMaritimo(PMaritimo);
							dictamen.setPFerroviario(PFerroviario);
							dictamen.setDDiagnostico(DDiagnostico);
							dictamen.setClaveCIE(ClaveCIE);
							dictamen.setCedula(VDictamen.vUsuario.getCCedula());
							dictamen.setNumExpDGPMPT(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()));
							dictamen.setNumFolio(NumFolio);
							dictamen.setLicencia(NumLicencia);

							dictamen.setEstadoOr(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setEstadoDes(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setEdad(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()));
							dictamen.setSexo(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()));
							dictamen.setPAereo(PAereo, String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setPHorasL(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setPHrDL(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setUPDD(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setGlucometria(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setPTox(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setCPM(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setEsHid(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setEAlerta(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setMarcha(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setLenguaje(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setOrienta(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setRomberg(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setIPN(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setPupila(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setRFM(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setUDL(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setTDL(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setROD(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setROI(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setTA(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setFR(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setFC(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setSC(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setAlc(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setNotMed(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()), String
									.valueOf(iNumExamen));
							dictamen.setNoMed(String.valueOf(VDictamen.VExamCat
									.getICveExpediente()), String
									.valueOf(iNumExamen));

							// Validacion de existencia de Biometricos en la NAS
							// y su Guardado
							// System.out.println("////////////////////     VALIDANDO FICHEROS     //////////////////////");
							// Buscando la foto
							if (MExamen) {// Opcion si es el ultimo examen
								LICDownFoto dLICDownFoto = new LICDownFoto();
								String sFichero = ""
										+ VParametros2.getPropEspecifica(
												"RutaNAS2").toString() + "f-"
										+ ClaveExpediente + ".jpg";
								File fichero = new File(sFichero);
								if (!fichero.exists()) {
									dLICDownFoto.getImg(
											Integer.toString(inodoctos[0]),
											ClaveExpediente, conn);
								}
								dictamen.setRutaNAS(VParametros2
										.getPropEspecifica("RutaNAS2")
										.toString());
								dictamen.setNumExamen("");

								// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
								if (fichero.length() >= TamMaxfoto) {
									String source = "errorfoto";
									data = source.getBytes("UTF-8");
									return data;
								}

								// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
								if (fichero.length() == 0) {
									String source = "0bytes";
									data = source.getBytes("UTF-8");
									fichero.delete();
									return data;
								}

							} else { // Opcion por si es un examen anterior
								LICDownFotoHist dLICDownFotoHist = new LICDownFotoHist();
								String sFichero = ""
										+ VParametros2.getPropEspecifica(
												"RutaNASH").toString() + "f-"
										+ ClaveExpediente + "ne" + iNumExamen
										+ ".jpg";
								File fichero = new File(sFichero);
								if (!fichero.exists()) {
									dLICDownFotoHist.getImg(
											Integer.toString(inodoctos[0]),
											ClaveExpediente, conn, iNumExamen);
								}
								dictamen.setRutaNAS(VParametros2
										.getPropEspecifica("RutaNASH")
										.toString());
								dictamen.setNumExamen("ne" + iNumExamen);

								// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
								if (fichero.length() >= TamMaxfoto) {
									String source = "errorfoto";
									data = source.getBytes("UTF-8");
									return data;
								}

								// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
								if (fichero.length() == 0) {
									String source = "0bytes";
									data = source.getBytes("UTF-8");
									fichero.delete();
									return data;
								}
							}
							// GENERO EL CODIGO
							String codigo = Dictamen.generaCodigoNF(Hra,
									MedDictaminador);
							dictamen.setCodigo(codigo);
							dictamen.setCodigo2(codigo);
							// Bloquear constancia
							dictamen.setBloquear(Bloquear);
							dictamenes.add(dictamen);

						} else {
							// System.out.println("date es mayor o igual al solicitado(imprime constancia anterior)");
							DictamenApto dictamen = new DictamenApto();

							dictamen.setNombre(VDictamen.VPerDatos.getCNombre());

							// dictamen.setPNombre(NombrePersona);
							dictamen.setPNombre(VDictamen.VPerDatos
									.getCNombre());

							dictamen.setRfc(VDictamen.VPerDatos.getCRFC());
							dictamen.setCurp(VDictamen.VPerDatos.getCCURP());
							dictamen.setGenero(VDictamen.VPerDatos.getCGenero());
							dictamen.setNumExpDGPMPT(String
									.valueOf(VDictamen.VExamCat
											.getICveExpediente()));
							dictamen.setNumAntExpDGPMPT(VDictamen.VPerDatos
									.getCExpediente());
							dictamen.setFechaExamen(pFecha.getFechaDDMMYYYY(
									VDictamen.VExamCat.getDtInicioVig(), "/"));
							dictamen.setVencimiento(pFecha.getFechaDDMMYYYY(
									VDictamen.VExamCat.getDtFinVig(), "/"));
							dictamen.setModoTransporte(VDictamen.VExamCat
									.getCDscMdoTrans());
							// dictamen.setLogo(fileLogo);
							if (VDictamen.VExamCat.getCDscMdoTrans()
									.equalsIgnoreCase("AEREO"))
								dictamen.setNota("NOTA: conforme al artÌculo 22 del REGLAMENTO del servicio de Medicina Preventiva en el Transporte publicado el 21 de abril de 2004. La Constancia de Aptitud PsicofÌsica que expida la DirecciÛn, tendra una validez de noventa dÌas naturales, contados a partir de la fecha de su entrega, a efecto de que el personal obtenga o revalide la Licencia Federal o Titulo, Certificado o Libreta de Mar y de Identidad MarÌtima. Si concluido el tÈrmino de la vigencia de la Constancia de Aptitud PsicofÌsica, el interesado no ha realizado su tramite de que se trate, deber· practicarse nuevamente el examen respectivo y el pago correspondiente del mismo. Y para otros efectos una vigencia m·xima de un AÒo a partir del momento de la ExpediciÛn, Recuperacion o RevalidaciÛn de la licencia y/o el lÌmite de la vigencia de la misma.\n\nC.c.p. DirecciÛn General del Transporte P˙blico Federal correspondiente.\nExpediente");
							else
								dictamen.setNota("NOTA: conforme al artÌculo 22 del REGLAMENTO del servicio de Medicina Preventiva en el Transporte publicado el 21 de abril de 2004. La Constancia de Aptitud PsicofÌsica que expida la DirecciÛn, tendra una validez de noventa dÌas naturales, contados a partir de la fecha de su entrega, a efecto de que el personal obtenga o revalide la Licencia Federal o Titulo, Certificado o Libreta de Mar y de Identidad MarÌtima. Si concluido el tÈrmino de la vigencia de la Constancia de Aptitud PsicofÌsica, el interesado no ha realizado su tramite de que se trate, deber· practicarse nuevamente el examen respectivo y el pago correspondiente del mismo.\n\n\nC.c.p. DirecciÛn General del Transporte P˙blico Federal correspondiente.\nExpediente");
							dictamen.setCategoria(VDictamen.VExamCat
									.getCDscCategoria());
							dictamen.setDescMotivo(VDictamen.VExamCat
									.getCDscMotivo());
							dictamen.setGrupoSanguineo(VDictamen.VPerDatos
									.getCGpoSang());
							dictamen.setRh(VDictamen.VPerDatos.getLRH() == 1
									? "POSITIVO"
									: "NEGATIVO");
							dictamen.setUsaLentes(VDictamen.VPerDatos
									.getLUsaLentes() == 1 ? "X" : " ");
							dictamen.setAereos(VDictamen.VPerDatos.getLAereo() == 1
									? "X"
									: " ");
							dictamen.setContacto(VDictamen.VPerDatos
									.getLContacto() == 1 ? "X" : " ");
							dictamen.setDiabetes(VDictamen.VPerDatos
									.getLDiabetes() == 1 ? "X" : " ");
							dictamen.setHipertension(VDictamen.VPerDatos
									.getLHipertension() == 1 ? "X" : " ");
							dictamen.setDictaminador(VDictamen
									.getCDictaminador()
									+ ((VDictamen.vUsuario.getCCedula() != null)
											? (" (Ced. Prof. "
													+ VDictamen.vUsuario
															.getCCedula() + ")")
											: " "));
							dictamen.setDescUnidadMedica(VDictamen.VUniMed
									.getCDscUniMed());
							dictamen.setApto1("  X");
							dictamen.setCedula(VDictamen.vUsuario.getCCedula());
							dictamen.setDoc(Doc);
							dictamen.setCiudadOrigen(CiudadOrigen);
							dictamen.setCiudadDestino(CiudadDestino);
							dictamen.setTensionArterial(TATA);
							dictamen.setHM(HM);
							dictamen.setClaveEmpresa(ClaveEmpresa);
							dictamen.setNombreEmpresa(NombreEmpresa);
							dictamen.setNumEcoEmpresa(NumEcoEmpresa);
							dictamen.setFrecuencia(Frecuencia);
							dictamen.setPulso(Pulso);
							dictamen.setClaveUniMed(NumeroUniMed);
							dictamen.setClaveModulo(ClaveProceso);
							dictamen.setAlcoholAliento(AlcoholAliento);
							dictamen.setReflejoFotomotor(ReflejoFotomotor);
							dictamen.setRomberg(Romberg);
							dictamen.setOsteotendinosos(Osteotendinosos);
							dictamen.setDDiagnostico(DDiagnostico);
							dictamen.setClaveCIE(ClaveCIE);
							dictamen.setPCarretero(PCarretero);
							dictamen.setPAereo(PAereo);
							dictamen.setPMaritimo(PMaritimo);
							dictamen.setPFerroviario(PFerroviario);
							dictamen.setAptoPro(AptoPro);
							dictamen.setNumFolio(NumFolio);
							// Aqui va el Modulo, se cambia para que presente la
							// descripcion del modulo
							// dictamen.setLugarUnidadMedica(VDictamen.VUniMed.getCDscUMEstado().toUpperCase()+
							// ","+
							// VDictamen.VUniMed.getCDscUMPais().toUpperCase());
							dictamen.setLugarUnidadMedica(VDictamen.vModulo
									.getCDscModulo());
							dictamen.setFechaDictamen(pFecha.getFechaDDMMYYYY(
									VDictamen.getDtDictamen(), "/"));
							boolean imprimirCategoria = VDictamen.VExamCat
									.getICveMdoTrans() != 2;
							dictamen.setImprimirCategoria(imprimirCategoria);

							// GENERO EL CODIGO
							String codigo = "";
							if (NuevoCodigoBarras) {
								codigo = Dictamen.generaCodigoNF(Hra,
										MedDictaminador);
							} else {
								codigo = Dictamen.generaCodigo(
										VDictamen.VExamCat.getICveExpediente(),
										VDictamen.VExamCat.getDtInicioVig(),
										VDictamen.vUsuario.getICveUsuario());
							}

							dictamen.setCodigo(codigo);
							dictamen.setCodigo2(codigo);

							// Validacion de existencia de Biometricos en la NAS
							// y su Guardado
							// System.out.println("////////////////////     VALIDANDO FICHEROS     //////////////////////");
							// Buscando la foto
							if (MExamen) {// Opcion si es el ultimo examen
								LICDownFoto dLICDownFoto = new LICDownFoto();
								String sFichero = ""
										+ VParametros2.getPropEspecifica(
												"RutaNAS2").toString() + "f-"
										+ ClaveExpediente + ".jpg";
								File fichero = new File(sFichero);
								if (!fichero.exists()) {
									 //System.out.println("No existe por lo cual se descargara el biometrico");
									dLICDownFoto.getImg(
											Integer.toString(inodoctos[0]),
											ClaveExpediente, conn);

								}
								dictamen.setRutaNAS(VParametros2
										.getPropEspecifica("RutaNAS2")
										.toString());
								dictamen.setNumExamen("");

								// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
								if (fichero.length() >= TamMaxfoto) {
									String source = "errorfoto";
									data = source.getBytes("UTF-8");
									return data;
								}
								if (fichero.length() == 0) {
									String source = "0bytes";
									data = source.getBytes("UTF-8");
									fichero.delete();
									return data;
								}
							} else { // Opcion por si es un examen anterior
								LICDownFotoHist dLICDownFotoHist = new LICDownFotoHist();
								String sFichero = ""
										+ VParametros2.getPropEspecifica(
												"RutaNASH").toString() + "f-"
										+ ClaveExpediente + "ne" + iNumExamen
										+ ".jpg";
								File fichero = new File(sFichero);
								if (!fichero.exists()) {
									// System.out.println("No existe por lo cual se descargara el biometrico");
									dLICDownFotoHist.getImg(
											Integer.toString(inodoctos[0]),
											ClaveExpediente, conn, iNumExamen);
								}
								dictamen.setRutaNAS(VParametros2
										.getPropEspecifica("RutaNASH")
										.toString());
								dictamen.setNumExamen("ne" + iNumExamen);

								// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
								if (fichero.length() >= TamMaxfoto) {
									String source = "errorfoto";
									data = source.getBytes("UTF-8");
									return data;
								}

								// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
								if (fichero.length() == 0) {
									String source = "0bytes";
									data = source.getBytes("UTF-8");
									fichero.delete();
									return data;
								}
							}

							// Bloquear constancia
							dictamen.setBloquear(Bloquear);

							dictamenes.add(dictamen);
						}

						// System.out.println("Compararo fechas");

					} // Fin del Proceso Reporte Apto EMO
					else {
						// Genera Constancia EPI Apto
						DictamenAptoP dictamen = new DictamenAptoP();

						boolean ExamenMarina =  false;
						dictamen.setNombre(VDictamen.VPerDatos.getCNombre());
						dictamen.setRfc(VDictamen.VPerDatos.getCRFC());
						dictamen.setCurp(VDictamen.VPerDatos.getCCURP());
						dictamen.setGenero(VDictamen.VPerDatos.getCGenero());
						dictamen.setNumExpDGPMPT(String
								.valueOf(VDictamen.VExamCat.getICveExpediente()));
						dictamen.setNumAntExpDGPMPT(VDictamen.VPerDatos
								.getCExpediente());
						// dictamen.setFechaExamen(pFecha.getFechaDDMMYYYY(VDictamen.VExamCat.getDtInicioVig(),
						// "/"));fechadelexamen
						dictamen.setFechaExamen(fechadelexamen);
						dictamen.setVencimiento(pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtFinVig(), "/"));
						dictamen.setModoTransporte(VDictamen.VExamCat
								.getCDscMdoTrans());

						// Nota
						String nota = "";
						String notaIng = "";
						nota = "NOTA: Conforme al artÌculo 22 del REGLAMENTO del Servicio de Medicina Preventiva en el Transporte publicado el 01 de septiembre de 2010. La Constancia de Aptitud PsicofÌsica tendr· una validez de noventa dÌas naturales, contados a partir de la fecha de su expediciÛn para efectos de que el personal obtenga o revalide la Licencia Federal o Titulo, Certificado o Libreta de Mar y de Identidad MarÌtima.\n"
								+ "Si concluido la vigencia de la Constancia a que se refiere el p·rrafo anterior, el personal no obtiene, renueva, revalida o recupera la Licencia Federal, TÌtulo, Certificado o Libreta de Mar y de Identidad MarÌtima, asÌ como los permisos que expide la SecretarÌa para cada modo de transporte federal y sus servicios auxiliares, deber· practicarse otra vez el examen respectivo y efectuar nuevamente el pago correspondiente.\n"
								+ "El personal deber· portar durante todo el tiempo que lleve a cabo sus funciones en las vÌas generales de comunicaciÛn, el original o copia certificada de la constancia de aptitud psicofÌsica en los tÈrminos que seÒalen los requisitos mÈdicos para cada modo de transporte que emita la Direccion.\n";
						
						notaIng = "Note: according to Article 22 of Regulation preventive medicine service in transportation, published on September 1, 2010, the psychophysical aptitude record, will have a term of ninety calendar days counted from the date of issue, in order that personnel obtain or revalidate the title or federal license, certificate or book sea and maritime identity.\n"
								+ "If terminating validity of the certificate which the preceding paragraph refers, the personnel did not get, renew, revalidation or retrieves the federal license, as well as the permits issued by the Secretariat for each mode of transportation federal and ancillary services, should be practiced again the respective exam and make the corresponding payment anew.\n"
								+ "The personnel must carry at all times carry out their functions in the original general communication routes, or certified copy of the certificate of medical exam in terms that indicate the medical requirements for each mode of transport issued by the Direction.\n";

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("AEREO")) {
							/*
							 * nota += "\nPerfil Aeron√°utico \n "+
							 * "Art√≠culo Noveno.- El Personal T√©cnico Aeronautico de los Grupos 1y 4 deber√°n someterse a un Examen Psicof√≠sico Integral, a efecto de evaluar su Aptitud Psicof√≠sica para realizar un desempe√±o seguro y eficiente de las atribuciones que su Licencia Federal le confiera, con una periodicidad de un A√±o los menores de cuarenta A√±os, y cada seis meses a partir de los cuarenta A√±os cumplidos. \n "
							 * +
							 * "El Personal T√©cnico Aeronautico de los grupos 2 y 3 deber√° someterse a un Examen Psicof√≠sico Integral, a efecto de evaluar su Aptitud Psicof√≠sica para Realizar un desempe√±o seguro y eficiente de las atribuciones que su Licencia Federal le confiera, con una periodicidad de un A√±o los menores de cincuenta A√±os, y cada seis meses a partir de los cincuenta A√±os cumplidos, con excepci√≥n de sobrecargo, que se deber√° someter a un Examen Psicof√≠sico Integral con una periodicidad de un A√±o, independientemente de la Edad."
							 * ;
							 */
							nota += "ArtÌculo Noveno.- El Personal TÈcnico Aeron·utico del Grupo 1 deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Licencia Federal le confiera, con una periodicidad de un aÒo los menores de cuarenta aÒos, y cada seis meses a partir de los cuarenta aÒos cumplidos. \n "
									+ "El Personal TÈcnico Aeron·utico de los Grupos 2, 3 y 4 deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Licencia Federal le confiera, con una periodicidad de dos aÒos. El Personal TÈcnico Aeron·utico de los Grupos 2, 3 y 4, excepto el personal de tierra contemplado en el Grupo 3 antes referido, a partir de los cuarenta aÒos cumplidos deber· someterse a un Examen PsicofÌsico Integral con una periodicidad de un aÒo.";

						}

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("AUTOTRANSPORTE")) {
							nota += "\nPerfil de Autotransporte Federal \n"
									+ "El Personal del Autotransporte Publico Federal deber· someterse a un examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para el ejercicio de las atribuciones que su Licencia Federal le confiere, con una periodicidad de dos AÒos.";

						}

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("FERROVIARIO")) {
							nota += "\nPerfil Ferroviario \n "
									+ "ArtÌculo Octavo.- El Personal TÈcnico Ferroviario deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Licencia Federal le confiere, con una periodicidad de dos AÒos.";
						}

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("MARITIMO")) {
							nota += "\nPerfil MarÌtimo \n"
									+ "El Personal de Marina Mercante de los Grupos 1, 2, 3 y 4 deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Libreta de Mar, Titulo o Identidad MarÌtima le confiera, con una periodicidad de dos AÒos; a excepciÛn de los menores de dieciocho AÒos que ser· cada seis meses."
									+ "\n\nLa caducidad de esta constancia est· determinada por Anexo I Extracto del Convenio internacional  sobre normas de formaciÛn, titulaciÛn y guardia para la gente de mar, 1978, en su forma enmendada. Regla I/9 Normas mÈdicas";
							
							notaIng += "\nMaritime Profile \n"
									+ "Merchant navy personnel from groups one, two, three and four, must undergo a integral psychophysical exam, in order to evaluate their psychophysical aptitude for a safe and efficient performance of their duties sea book, tittle or maritime identity conferred, at a periodicity of two years; except those under 18 years of age for those who will be every six months.."
									+ "\n\nThe expiration of this record is determined by Annex I Abstract of International Convention on Standards of Training, Certification and Watchkeeping for seafarers, 1978, as amended. Regulation I / 9 Medical standards";
							
							ExamenMarina =  true;
						}

						// System.out.println(nota);
						dictamen.setNota(nota);

						dictamen.setCategoria(VDictamen.VExamCat
								.getCDscCategoria());
						dictamen.setDescMotivo(VDictamen.VExamCat
								.getCDscMotivo());
						dictamen.setGrupoSanguineo(VDictamen.VPerDatos
								.getCGpoSang());
						dictamen.setRh(VDictamen.VPerDatos.getLRH() == 1
								? "POSITIVO"
								: "NEGATIVO");
						dictamen.setUsaLentes(VDictamen.VPerDatos
								.getLUsaLentes() == 1 ? "X" : " ");
						dictamen.setAereos(VDictamen.VPerDatos.getLAereo() == 1
								? "X"
								: " ");
						dictamen.setContacto(VDictamen.VPerDatos.getLContacto() == 1
								? "X"
								: " ");
						dictamen.setDiabetes(VDictamen.VPerDatos.getLDiabetes() == 1
								? "X"
								: " ");
						dictamen.setHipertension(VDictamen.VPerDatos
								.getLHipertension() == 1 ? "X" : " ");
						dictamen.setDictaminador(VDictamen.getCDictaminador()
								+ " "
								+ ((VDictamen.vUsuario.getCCedula() != null)
										? ("\n    Ced. Prof. "
												+ VDictamen.vUsuario
														.getCCedula() + "\n")
										: "\n"));
						dictamen.setDescUnidadMedica(VDictamen.VUniMed
								.getCDscUniMed());

						// Validar que no es un tercero autorizado descripcion
						// del modulo
						String notaTercero = "";
						String notaTerceroIng = "";
						notaTercero = "Yo "
								+ Doc2
								+ ", bajo protesta de decir verdad, declaro ante la DirecciÛn General de ProtecciÛn y "
								+ "Medicina Preventiva en el Transporte," 
								///Se agrego la siguiente leyenda el dia 11 de aggosto 2015
								+" que verifique la veracidad de los documentos que avalan la identidad del personal, "
								+" que emito este dictamen derivado del examen psicofÌsico integral practicado el "
								+ pFecha.getFechaDDMMYYYY(
										VDictamen.getDtDictamen(), "/")
								+ " "
								+ "en "
								+ DireccionMod
								+ ", y que la informaciÛn contenida en el expediente electrÛnico No."
								+ ClaveExpediente
								+ " es "
								+ "verÌdica, y fue obtenida empleando para ello las mejores pr·cticas mÈdicas por personal calificado, que me hago responsable de la informaciÛn aportada "
								+ "con mi firma y en su caso con mi cÈdula profesional, asÌ como el equipo idÛneo, apercibido de que aquÈl que interrogado "
								+ "por autoridad p˙blica distinta de la judicial (en ejercicio de sus funciones o con motivo de ellas) faltare "
								+ "a la verdad, se hace acreedor a una pena de cuatro a ocho aÒos de prisiÛn y de cien a trescientos dÌas multa "
								+ "de acuerdo a lo establecido en el artÌculo 247, fracciÛn I del CÛdigo Penal Federal.\n\n"
								+ "                                                               Firma:_______________________________________________________________________________________________.";
						
						notaTerceroIng = "I "
								+ Doc2
								+ ", under oath, declare before the DirecciÛn General de ProtecciÛn y Medicina Preventiva en el Transporte, "
								+ "issue this dictum derived from integral psychophysical exam, realized on "
								+ pFecha.getFechaDDMMYYYY(
										VDictamen.getDtDictamen(), "/")
								+ " "
								+ "at "
								+ DireccionMod
								+ ", and that the information contained in the electronic record N."
								+ ClaveExpediente
								+ ", is true and was obtained using the best medical practices by qualified personnel, who are responsible for " 
								+ "the information provided with your signature and professional license, if necessary. As well as the suitable equipment, " 
								+ "apperceived that of one who interrogated by public authority other than the court (in performance of their duties " 
								+ "or by reason of them), shall fail to truth, becomes entitled to a sentence of four to eight years in prison, and one hundred " 
								+ "to three hundred days of fines in accordance with the established of article 247, fraction 1, of the federal criminal code.\n\n"
								+ "                                                               Signature:___________________________________________________________________________________________.";


						dictamen.setLugarUnidadMedica(VDictamen.vModulo
								.getCDscModulo());

						dictamen.setNotaTercero(notaTercero);
						dictamen.setFechaDictamen(pFecha.getFechaDDMMYYYY(
								VDictamen.getDtDictamen(), "/"));

						String fechaDictamen = pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtFinVig(), "/");
						String feDN = fechaDictamen.charAt(6) + ""
								+ fechaDictamen.charAt(7) + ""
								+ fechaDictamen.charAt(8) + ""
								+ fechaDictamen.charAt(9) + "";
						int fD = Integer.parseInt(feDN.toString());

						// Enviando Vigencia
						dictamen.setIniVig(pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtInicioVig(), "/"));
						dictamen.setFinVig(pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtFinVig(), "/"));

						dictamen.setObserRestric(ObserRestric);

						boolean imprimirCategoria = VDictamen.VExamCat
								.getICveMdoTrans() != 2;
						dictamen.setImprimirCategoria(imprimirCategoria);

						// /Nacionalidad 2014-04-01
						dictamen.setNacionalidad(Nacionalidad);

						// GENERO EL CODIGO
						String codigo = "";
						if (NuevoCodigoBarras) {
							codigo = Dictamen.generaCodigoNF(Hra,
									MedDictaminador);
						} else {
							codigo = Dictamen.generaCodigo(
									VDictamen.VExamCat.getICveExpediente(),
									VDictamen.VExamCat.getDtInicioVig(),
									VDictamen.vUsuario.getICveUsuario());
						}

						dictamen.setCodigo(codigo);

						// Validacion de existencia de Biometricos en la NAS y
						// su Guardado
						// System.out.println("////////////////////     VALIDANDO FICHEROS     //////////////////////");
						// Buscando la foto
						if (MExamen) {// Opcion si es el ultimo examen
							LICDownFoto dLICDownFoto = new LICDownFoto();
							String sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "f-"
									+ ClaveExpediente + ".jpg";
							File fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getImg(
										Integer.toString(inodoctos[0]),
										ClaveExpediente, conn);
							}

							// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
							if (fichero.length() >= TamMaxfoto) {
								String source = "errorfoto";
								data = source.getBytes("UTF-8");
								return data;
							}
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la firma
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "r-"
									+ ClaveExpediente + ".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getFirma(
										Integer.toString(inodoctos[1]),
										ClaveExpediente, conn);
							}

							// / VALIDAR QUE LA FIRMA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la huella
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "h-"
									+ ClaveExpediente + ".bmp";
							// sFichero =
							// ""+VParametros2.getPropEspecifica("RutaNASHuellas").toString()+"h-"+ClaveExpediente+".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getHuella(
										Integer.toString(inodoctos[2]),
										ClaveExpediente, conn);
							}

							// / VALIDAR QUE LA HUELLA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}
							// final de validacion de existencia y guardado de
							// biometricos
							dictamen.setRutaNAS(VParametros2.getPropEspecifica(
									"RutaNAS2").toString());
							// dictamen.setRutaNASHuellas(VParametros2.getPropEspecifica("RutaNASHuellas").toString());
							dictamen.setNumExamen("");

						} else {
							LICDownFotoHist dLICDownFotoHist = new LICDownFotoHist();
							String sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "f-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".jpg";
							File fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getImg(
										Integer.toString(inodoctos[0]),
										ClaveExpediente, conn, iNumExamen);
							}

							// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
							if (fichero.length() >= TamMaxfoto) {
								String source = "errorfoto";
								data = source.getBytes("UTF-8");
								return data;
							}
							// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la firma
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "r-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getFirma(
										Integer.toString(inodoctos[1]),
										ClaveExpediente, conn, iNumExamen);
							}
							// / VALIDAR QUE LA FIRMA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la huella
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "h-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getHuella(
										Integer.toString(inodoctos[2]),
										ClaveExpediente, conn, iNumExamen);
							}
							// / VALIDAR QUE LA HUELLA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// final de validacion de existencia y guardado de
							// biometricos
							dictamen.setRutaNAS(VParametros2.getPropEspecifica(
									"RutaNASH").toString());
							// dictamen.setRutaNASHuellas(VParametros2.getPropEspecifica("RutaNASH").toString());
							dictamen.setNumExamen("ne" + iNumExamen);

						}
						
						// Bloquear constancia
						dictamen.setBloquear(Bloquear);
						
						//// CONTANCIA EN INGLES PARA MARINA MERCANTE
						if(ExamenMarina){
									DictamenAptoPMarIng dictamenIng = new DictamenAptoPMarIng();		
									dictamenIng.setNombre(VDictamen.VPerDatos.getCNombre());
									dictamenIng.setRfc(VDictamen.VPerDatos.getCRFC());
									dictamenIng.setCurp(VDictamen.VPerDatos.getCCURP());
									dictamenIng.setGenero(VDictamen.VPerDatos.getCGenero());
									dictamenIng.setNumExpDGPMPT(String.valueOf(VDictamen.VExamCat.getICveExpediente()));
									dictamenIng.setNumAntExpDGPMPT(VDictamen.VPerDatos.getCExpediente());
									dictamenIng.setFechaExamen(fechadelexamen);
									dictamenIng.setVencimiento(pFecha.getFechaDDMMYYYY(VDictamen.VExamCat.getDtFinVig(), "/"));
									dictamenIng.setModoTransporte(VDictamen.VExamCat.getCDscMdoTransIng());
									dictamenIng.setNota(notaIng);		
									dictamenIng.setCategoria(VDictamen.VExamCat.getCDscCategoriaIng());
									dictamenIng.setDescMotivo(VDictamen.VExamCat.getCDscMotivo());
									dictamenIng.setGrupoSanguineo(VDictamen.VPerDatos.getCGpoSang());
									dictamenIng.setRh(VDictamen.VPerDatos.getLRH() == 1 ? "POSITIVE"	: "NEGATIVE");
									dictamenIng.setUsaLentes(VDictamen.VPerDatos.getLUsaLentes() == 1 ? "X" : " ");
									dictamenIng.setAereos(VDictamen.VPerDatos.getLAereo() == 1 ? "X"	: " ");
									dictamenIng.setContacto(VDictamen.VPerDatos.getLContacto() == 1 ? "X" : " ");
									dictamenIng.setDiabetes(VDictamen.VPerDatos.getLDiabetes() == 1 ? "X" : " ");
									dictamenIng.setHipertension(VDictamen.VPerDatos.getLHipertension() == 1 ? "X" : " ");
									dictamenIng.setDictaminador(VDictamen.getCDictaminador()	+ " "+ ((VDictamen.vUsuario.getCCedula() != null)
													? ("\n    Ced. Prof. "	+ VDictamen.vUsuario.getCCedula() + "\n") : "\n"));
									dictamenIng.setDescUnidadMedica(VDictamen.VUniMed.getCDscUniMed());
									dictamenIng.setLugarUnidadMedica(VDictamen.vModulo.getCDscModulo());
									dictamenIng.setNotaTercero(notaTerceroIng);
									dictamenIng.setFechaDictamen(pFecha.getFechaDDMMYYYY(VDictamen.getDtDictamen(), "/"));
									dictamenIng.setIniVig(pFecha.getFechaDDMMYYYY(VDictamen.VExamCat.getDtInicioVig(), "/"));
									dictamenIng.setFinVig(pFecha.getFechaDDMMYYYY(VDictamen.VExamCat.getDtFinVig(), "/"));		
									dictamenIng.setObserRestric(ObserRestric);		
									dictamenIng.setImprimirCategoria(imprimirCategoria);
									dictamenIng.setNacionalidad(NacionalidadIng);		
									dictamenIng.setCodigo(codigo);
		
									if (MExamen) {// Opcion si es el ultimo examen
										dictamenIng.setRutaNAS(VParametros2.getPropEspecifica("RutaNAS2").toString());
										dictamenIng.setNumExamen("");		
									} else {
										dictamenIng.setRutaNAS(VParametros2.getPropEspecifica("RutaNASH").toString());
										dictamenIng.setNumExamen("ne" + iNumExamen);
									}
									// Bloquear constancia
									dictamenIng.setBloquear(Bloquear);
									dictamenes.add(dictamenIng);
						}
					
						
						dictamenes.add(dictamen);
						

					}

				} else {
					// Dictamenes de No Aptitud
					// Compara si el dictamen sera Psicofisico
					if (ClaveProceso.equals(Psicofisico)) {
						// Genera Constancia EPI No Apto
						DictamenNoApto dictamen = new DictamenNoApto();

						dictamen.setNombre(VDictamen.VPerDatos.getCNombre());
						dictamen.setRfc(VDictamen.VPerDatos.getCRFC());
						dictamen.setCurp(VDictamen.VPerDatos.getCCURP());
						dictamen.setGenero(VDictamen.VPerDatos.getCGenero());
						dictamen.setNumExpDGPMPT(String
								.valueOf(VDictamen.VExamCat.getICveExpediente()));
						dictamen.setNumAntExpDGPMPT(VDictamen.VPerDatos
								.getCExpediente());
						// dictamen.setFechaExamen(pFecha.getFechaDDMMYYYY(VDictamen.VExamCat.getDtInicioVig(),
						// "/"));fechadelexamen
						dictamen.setFechaExamen(fechadelexamen);
						dictamen.setVencimiento(pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtFinVig(), "/"));
						dictamen.setModoTransporte(VDictamen.VExamCat
								.getCDscMdoTrans());

						// Nota
						String nota = "";
						nota = "NOTA: Conforme al artÌculo 22 del REGLAMENTO del Servicio de Medicina Preventiva en el Transporte publicado el 01 de septiembre de 2010. La Constancia de Aptitud PsicofÌsica tendr· una validez de noventa dÌas naturales, contados a partir de la fecha de su expediciÛn para efectos de que el personal obtenga o revalide la Licencia Federal o Titulo, Certificado o Libreta de Mar y de Identidad MarÌtima.\n"
								+ "Si concluido la vigencia de la Constancia a que se refiere el p·rrafo anterior, el personal no obtiene, renueva, revalida o recupera la Licencia Federal, TÌtulo, Certificado o Libreta de Mar y de Identidad MarÌtima, asÌ como los permisos que expide la SecretarÌa para cada modo de transporte federal y sus servicios auxiliares, deber· practicarse otra vez el examen respectivo y efectuar nuevamente el pago correspondiente.\n"
								+ "El personal deber· portar durante todo el tiempo que lleve a cabo sus funciones en las vÌas generales de comunicaciÛn, el original o copia certificada de la constancia de aptitud psicofÌsica en los tÈrminos que seÒalen los requisitos mÈdicos para cada modo de transporte que emita la DirecciÛn.\n";

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("AEREO")) {
							nota += "\nPerfil Aeron·utico \n "
									+ "ArtÌculo Noveno.- El Personal TÈcnico Aeron·utico de los Grupos 1y 4 deber·n someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Licencia Federal le confiera, con una periodicidad de un AÒo los menores de cuarenta AÒos, y cada seis meses a partir de los cuarenta AÒos cumplidos. \n "
									+ "El Personal TÈcnico Aeron·utico de los grupos 2 y 3 deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para Realizar un desempeÒo seguro y eficiente de las atribuciones que su Licencia Federal le confiera, con una periodicidad de un AÒo los menores de cincuenta AÒos, y cada seis meses a partir de los cincuenta AÒos cumplidos, con excepciÛn de sobrecargo, que se deber· someter a un Examen PsicofÌsico Integral con una periodicidad de un AÒo, independientemente de la Edad.";
						}

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("AUTOTRANSPORTE")) {
							nota += "\nPerfil de Autotransporte Federal \n"
									+ "El Personal del Autotransporte Publico Federal deber· someterse a un examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para el ejercicio de las atribuciones que su Licencia Federal le confiere, con una periodicidad de dos AÒos.";

						}

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("FERROVIARIO")) {
							nota += "\nPerfil Ferroviario \n "
									+ "ArtÌculo Octavo.- El Personal TÈcnico Ferroviario deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Licencia Federal le confiere, con una periodicidad de dos AÒos.";
						}

						if (VDictamen.VExamCat.getCDscMdoTrans()
								.equalsIgnoreCase("MARITIMO")) {
							nota += "\nPerfil MarÌtimo \n"
									+ "El Personal de Marina Mercante de los Grupos 1, 2, 3 y 4 deber· someterse a un Examen PsicofÌsico Integral, a efecto de evaluar su Aptitud PsicofÌsica para realizar un desempeÒo seguro y eficiente de las atribuciones que su Libreta de Mar, Titulo o Identidad MarÌtima le confiera, con una periodicidad de dos AÒos; a excepciÛn de los menores de dieciocho AÒos que ser· cada seis meses.";
						}

						dictamen.setNota(nota);

						dictamen.setCategoria(VDictamen.VExamCat
								.getCDscCategoria());
						dictamen.setDescMotivo(VDictamen.VExamCat
								.getCDscMotivo());
						dictamen.setGrupoSanguineo(VDictamen.VPerDatos
								.getCGpoSang());
						dictamen.setRh(VDictamen.VPerDatos.getLRH() == 1
								? "POSITIVO"
								: "NEGATIVO");
						dictamen.setUsaLentes(VDictamen.VPerDatos
								.getLUsaLentes() == 1 ? "X" : " ");
						dictamen.setAereos(VDictamen.VPerDatos.getLAereo() == 1
								? "X"
								: " ");
						dictamen.setContacto(VDictamen.VPerDatos.getLContacto() == 1
								? "X"
								: " ");
						dictamen.setDiabetes(VDictamen.VPerDatos.getLDiabetes() == 1
								? "X"
								: " ");
						dictamen.setHipertension(VDictamen.VPerDatos
								.getLHipertension() == 1 ? "X" : " ");
						dictamen.setDictaminador(VDictamen.getCDictaminador()
								+ ((VDictamen.vUsuario.getCCedula() != null)
										? (" (Ced. Prof. "
												+ VDictamen.vUsuario
														.getCCedula() + ")")
										: " "));
						dictamen.setDescUnidadMedica(VDictamen.VUniMed
								.getCDscUniMed());

						// Aqui va el Modulo, se cambia para que presente la
						// descripcion del modulo
						// dictamen.setLugarUnidadMedica(VDictamen.VUniMed.getCDscUMEstado().toUpperCase()+
						// ","+
						// VDictamen.VUniMed.getCDscUMPais().toUpperCase());
						if (NumeroUniMed.equals("107"))
							dictamen.setLugarUnidadMedica("     ");
						else
							dictamen.setLugarUnidadMedica(VDictamen.vModulo
									.getCDscModulo());
						dictamen.setFechaDictamen(pFecha.getFechaDDMMYYYY(
								VDictamen.getDtDictamen(), "/"));

						// Nuevos Valores
						String fechaDictamen = pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtFinVig(), "/");
						String feDN = fechaDictamen.charAt(6) + ""
								+ fechaDictamen.charAt(7) + ""
								+ fechaDictamen.charAt(8) + ""
								+ fechaDictamen.charAt(9) + "";
						int fD = Integer.parseInt(feDN.toString());

						// Enviando Vigencia
						dictamen.setIniVig(pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtInicioVig(), "/"));
						dictamen.setFinVig(pFecha.getFechaDDMMYYYY(
								VDictamen.VExamCat.getDtFinVig(), "/"));

						dictamen.setObserRestric(ObserRestric);

						// Motivacion y Fundamentacion
						dictamen.setMotivacion(Motivacion);
						dictamen.setFundamentacion(Fundamentacion);

						boolean imprimirCategoria = VDictamen.VExamCat
								.getICveMdoTrans() != 2;
						dictamen.setImprimirCategoria(imprimirCategoria);

						// GENERO EL CODIGO
						String codigo = "";
						if (NuevoCodigoBarras) {
							codigo = Dictamen.generaCodigoNF(Hra,
									MedDictaminador);
						} else {
							codigo = Dictamen.generaCodigo(
									VDictamen.VExamCat.getICveExpediente(),
									VDictamen.VExamCat.getDtInicioVig(),
									VDictamen.vUsuario.getICveUsuario());
						}

						dictamen.setCodigo(codigo);

						// Validacion de existencia de Biometricos en la NAS y
						// su Guardado
						// System.out.println("////////////////////     VALIDANDO FICHEROS     //////////////////////");
						// Buscando la foto
						if (MExamen) {// Opcion si es el ultimo examen
							LICDownFoto dLICDownFoto = new LICDownFoto();
							String sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "f-"
									+ ClaveExpediente + ".jpg";
							File fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getImg(
										Integer.toString(inodoctos[0]),
										ClaveExpediente, conn);
							}

							// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
							if (fichero.length() >= TamMaxfoto) {
								String source = "errorfoto";
								data = source.getBytes("UTF-8");
								return data;
							}
							// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la firma
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "r-"
									+ ClaveExpediente + ".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getFirma(
										Integer.toString(inodoctos[1]),
										ClaveExpediente, conn);
							}

							// / VALIDAR QUE LA FIRMA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la huella
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "h-"
									+ ClaveExpediente + ".bmp";
							// sFichero =
							// ""+VParametros2.getPropEspecifica("RutaNASHuellas").toString()+"h-"+ClaveExpediente+".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getHuella(
										Integer.toString(inodoctos[2]),
										ClaveExpediente, conn);
							}
							// / VALIDAR QUE LA HUELLA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// final de validacion de existencia y guardado de
							// biometricos
							dictamen.setRutaNAS(VParametros2.getPropEspecifica(
									"RutaNAS2").toString());
							// dictamen.setRutaNASHuellas(VParametros2.getPropEspecifica("RutaNASHuellas").toString());
							dictamen.setNumExamen("");
						} else {
							LICDownFotoHist dLICDownFotoHist = new LICDownFotoHist();
							String sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "f-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".jpg";
							File fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getImg(
										Integer.toString(inodoctos[0]),
										ClaveExpediente, conn, iNumExamen);
							}

							// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
							if (fichero.length() >= TamMaxfoto) {
								String source = "errorfoto";
								data = source.getBytes("UTF-8");
								return data;
							}
							// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la firma
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "r-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getFirma(
										Integer.toString(inodoctos[1]),
										ClaveExpediente, conn, iNumExamen);
							}
							// / VALIDAR QUE LA FIRMA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}

							// Buscando la huella
							sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "h-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".bmp";
							fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getHuella(
										Integer.toString(inodoctos[2]),
										ClaveExpediente, conn, iNumExamen);
							}
							// / VALIDAR QUE LA HUELLA NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}
							// final de validacion de existencia y guardado de
							// biometricos
							dictamen.setRutaNAS(VParametros2.getPropEspecifica(
									"RutaNASH").toString());
							// dictamen.setRutaNASHuellas(VParametros2.getPropEspecifica("RutaNASH").toString());
							dictamen.setNumExamen("ne" + iNumExamen);
						}

						// Bloquear constancia
						dictamen.setBloquear(Bloquear);

						dictamenes.add(dictamen);

					} else {
						// /DICTAMEN EMO NO APTO

						DictamenNoAptoEMO dictamen = new DictamenNoAptoEMO();

						dictamen.setClaveExpediente(String
								.valueOf(VDictamen.VExamCat.getICveExpediente()));
						dictamen.setDescripcionProceso(VDictamen
								.getCDscProceso());
						dictamen.setDescripcionUnidadMedica(VDictamen.VUniMed
								.getCDscUniMed());
						dictamen.setLugarFechaExpedicion(VDictamen.VUniMed
								.getCDscUMEstado().toUpperCase()
								+ ","
								+ VDictamen.VUniMed.getCDscUMPais()
										.toUpperCase()
								+ this.spanishDate(VDictamen.VExamCat
										.getDtInicioVig()));

						dictamen.setNombre(VDictamen.VPerDatos.getCNombre());
						// dictamen.setPNombre(NombrePersona);
						dictamen.setPNombre(VDictamen.VPerDatos.getCNombre());

						dictamen.setRfc(VDictamen.VPerDatos.getCRFC());

						dictamen.setDireccion1(VDictamen.VPerDatos.getCCalle()
								+ " " + VDictamen.VPerDatos.getCNumExt()
								+ VDictamen.VPerDatos.getCNumInt());
						dictamen.setDireccion2("COLONIA "
								+ VDictamen.VPerDatos.getCColonia());
						dictamen.setDireccion3(VDictamen.VPerDatos
								.getCDscMunicipio()
								+ ", "
								+ VDictamen.VPerDatos.getCDscEstado());
						dictamen.setDireccion4(VDictamen.VPerDatos
								.getCDscPais());
						dictamen.setResultadoFecha(spanishExamDate2(VDictamen.VExamCat
								.getDtInicioVig()));

						dictamen.setDoct(Doc);
						dictamen.setCedula(VDictamen.vUsuario.getCCedula());

						dictamen.setDictaminador(VDictamen.getCDictaminador()
								+ ((VDictamen.vUsuario.getCCedula() != null)
										? (" (Ced. Prof. "
												+ VDictamen.vUsuario
														.getCCedula() + ")")
										: " "));
						dictamen.setLugarUnidadMedica(VDictamen.vModulo
								.getCDscModulo());
						dictamen.setDireccionUnidadMedica(VDictamen.VUniMed
								.getCDscUMEstado().toUpperCase()
								+ ", "
								+ VDictamen.VUniMed.getCDscUMPais()
										.toUpperCase()
								+ this.spanishDate(VDictamen.VExamCat
										.getDtInicioVig()));
						dictamen.setDireccionUM(DireccionUM);
						dictamen.setNombreEmpresa(NombreEmpresa);
						dictamen.setMdotrans(VDictamen.VExamCat
								.getCDscMdoTrans());
						dictamen.setDiagNAemo(DiagNAemo);
						dictamen.setMotNAemo(MotNAemo);
						dictamen.setDtSolicitado(VDictamen.getDtSolicitado()
								+ "");
						dictamen.setLicencia(NumLicencia);
						dictamen.setFundamentacion(Fundamentacion);

						// /Nuevos parametros nuevo formato 2013-06-25
						dictamen.setNEmpresa(NombreEmpresa);
						dictamen.setDEmpresa(ClaveEmpresa);
						dictamen.setEdad(String.valueOf(VDictamen.VExamCat
								.getICveExpediente()));
						dictamen.setDiagnosticoNA(DiagnosticoNA);
						dictamen.setMotivacionNA(MotivacionNA);
						dictamen.setTransporte(VDictamen.VExamCat
								.getICveMdoTrans());

						// dictamen.setLogo(fileLogo);
						// GENERO EL CODIGO
						String codigo = "";
						if (NuevoCodigoBarras) {
							codigo = Dictamen.generaCodigoNF(Hra,
									MedDictaminador);
						} else {
							codigo = Dictamen.generaCodigo(
									VDictamen.VExamCat.getICveExpediente(),
									VDictamen.VExamCat.getDtInicioVig(),
									VDictamen.vUsuario.getICveUsuario());
						}

						dictamen.setCodigo(codigo);

						// Validacion de existencia de Biometricos en la NAS y
						// su Guardado
						// System.out.println("////////////////////     VALIDANDO FICHEROS     //////////////////////");
						// Buscando la foto
						if (MExamen) {// Opcion si es el ultimo examen
							LICDownFoto dLICDownFoto = new LICDownFoto();
							String sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNAS2")
											.toString() + "f-"
									+ ClaveExpediente + ".jpg";
							File fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFoto.getImg(
										Integer.toString(inodoctos[0]),
										ClaveExpediente, conn);
							}
							dictamen.setRutaNAS(VParametros2.getPropEspecifica(
									"RutaNAS2").toString());
							dictamen.setNumExamen("");

							// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
							if (fichero.length() >= TamMaxfoto) {
								String source = "errorfoto";
								data = source.getBytes("UTF-8");
								return data;
							}
							// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
							if (fichero.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero.delete();
								return data;
							}
						} else {
							LICDownFotoHist dLICDownFotoHist = new LICDownFotoHist();
							String sFichero = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "f-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".jpg";
							File fichero = new File(sFichero);
							if (!fichero.exists()) {
								// System.out.println("No existe por lo cual se descargara el biometrico");
								dLICDownFotoHist.getImg(
										Integer.toString(inodoctos[0]),
										ClaveExpediente, conn, iNumExamen);
							}
							dictamen.setRutaNAS(VParametros2.getPropEspecifica(
									"RutaNASH").toString());
							dictamen.setNumExamen("ne" + iNumExamen);

							// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
							String TFoto = ""
									+ VParametros2
											.getPropEspecifica("RutaNASH")
											.toString() + "f-"
									+ ClaveExpediente + "ne" + iNumExamen
									+ ".jpg";
							File fichero2 = new File(TFoto);
							if (fichero2.length() >= TamMaxfoto) {
								String source = "errorfoto";
								data = source.getBytes("UTF-8");
								return data;
							}
							// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
							if (fichero2.length() == 0) {
								String source = "0bytes";
								data = source.getBytes("UTF-8");
								fichero2.delete();
								return data;
							}

						}

						// Bloquear constancia
						dictamen.setBloquear(Bloquear);

						dictamenes.add(dictamen);
					}

				}// Valida No Apto EMO Psicofisico

			}// For
			data = dictamenes.getReportePDF();
		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Fin");
		return data;

	}

	public String getActiveX() {
		// System.out.println("getActiveX");
		return activeX.toString();
	}

	public String getAccion() {
		String regresa="";
		if(request.getParameter("hdBoton") !=null){
			regresa = request.getParameter("hdBoton");
		}
		return regresa;
	}

	public boolean getlGuardar() {
		return lGuardar;
	}

	public String spanishDate(java.sql.Date dConcluido) {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dConcluido);
		int iDay = dtFecha.getIntDay(dConcluido);
		String cMes = dtFecha.getMonthName(dConcluido).toUpperCase();
		fechaFormat = " a " + iDay + " de " + cMes + " de " + iAnio;
		return fechaFormat;
	}

	public String spanishExamDate(java.sql.Date dConcluido) {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dConcluido);
		int iDay = dtFecha.getIntDay(dConcluido);
		String cMes = dtFecha.getMonthName(dConcluido).toUpperCase();
		fechaFormat = iDay + " del mes " + cMes + " y AÒo de " + iAnio;
		return fechaFormat;
	}

	public String spanishExamDate2(java.sql.Date dConcluido) {
		String fechaFormat = "";
		TFechas dtFecha = new TFechas("07");
		int iAnio = dtFecha.getIntYear(dConcluido);
		int iDay = dtFecha.getIntDay(dConcluido);
		String cMes = dtFecha.getMonthName(dConcluido).toUpperCase();
		fechaFormat = iDay + " del mes de " + cMes + " del aÒo " + iAnio;
		return fechaFormat;
	}

}
