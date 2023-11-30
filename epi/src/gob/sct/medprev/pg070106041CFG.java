package gob.sct.medprev;

import gob.sct.medprev.dao.TDEMOExamen;
import gob.sct.medprev.dao.TDGRLUSUMedicos;
import gob.sct.medprev.dao.TDPERCatMotRubNoAp;
import gob.sct.medprev.dao.TDPERCatalogoNoAp;
import gob.sct.medprev.dao.TDPERDatos;
import gob.sct.medprev.dao.TDPEREXAMENNoAp;
import gob.sct.medprev.dao.TDPERLicencia;
import gob.sct.medprev.vo.TVPERCatMotRubNoAp;
import gob.sct.medprev.vo.TVPERCatalogoNoAp;
import gob.sct.medprev.vo.TVPERLicencia;

import java.util.Vector;

import com.micper.excepciones.CFGException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGCatBase2;
import com.micper.util.TFechas;

/**
 * * Clase de configuracion para EPI - Actualizaciï¿½n de No Aptos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070106041CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070106041CFG.png'>
 */
public class pg070106041CFG extends CFGCatBase2 {

	public String cMensajeAccion = "Inicio";

	public pg070106041CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070106040.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		TDPERLicencia DPERLicencia = new TDPERLicencia();
		TVPERLicencia VPERLicencia = new TVPERLicencia();
		TVPERCatalogoNoAp VPERCatalogoNoAp = new TVPERCatalogoNoAp();
		try {
			cClave = (String) dPERCatalogoNoAp.insert(null, this.getInputs());
			
			//Guardar datos de examen
			this.guardaPerExamen();
			
			
			
			// Se agrego para actualizar la Tabla PERLicencia.
			if (cClave.compareToIgnoreCase("") != 0) {
				VPERCatalogoNoAp = (TVPERCatalogoNoAp) this.getInputs();
				VPERLicencia
						.setICvePersonal(VPERCatalogoNoAp.getICvePersonal());
				VPERLicencia
						.setICveMdoTrans(VPERCatalogoNoAp.getICveMdoTrans());
				VPERLicencia.setICveCategoria(VPERCatalogoNoAp
						.getICveCategoria());
				VPERLicencia.setCLicencia("");
				VPERLicencia.setLDictamen(0);
				VPERLicencia.setCConstancia("");
				VPERLicencia.setINumExamen(0);
				VPERLicencia.setDtIniVigencia(VPERCatalogoNoAp.getDtInicio());
				VPERLicencia.setDtFinVigencia(VPERCatalogoNoAp.getDtFin());
				DPERLicencia.insert(null, VPERLicencia);
			}

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo Actualiza Examen
	 */
	public void Actualizar() {
		try {
			//Guardar datos de examen
			this.eliminaExamen();
			this.guardaPerExamen();
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	
	
	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		try {
			cClave = (String) dPERCatalogoNoAp.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		TDPERLicencia DPERLicencia = new TDPERLicencia();
		TVPERLicencia VPERLicencia = new TVPERLicencia();
		TVPERCatalogoNoAp VPERCatalogoNoAp = new TVPERCatalogoNoAp();

		try {
			if (request.getParameter("iCvePersonal") != null)
				VPERCatalogoNoAp.setICvePersonal(Integer.parseInt(request
						.getParameter("iCvePersonal").toString()));
			if (request.getParameter("hdiCveMdoTrans") != null)
				VPERCatalogoNoAp.setICveMdoTrans(Integer.parseInt(request
						.getParameter("hdiCveMdoTrans").toString()));
			if (request.getParameter("hdiCveCategoria") != null)
				VPERCatalogoNoAp.setICveCategoria(Integer.parseInt(request
						.getParameter("hdiCveCategoria").toString()));
			if (request.getParameter("hdiCveCatalogoNoAp") != null)
				VPERCatalogoNoAp.setICveCatalogoNoAp(Integer.parseInt(request
						.getParameter("hdiCveCatalogoNoAp").toString()));
			VPERCatalogoNoAp.setLVigente(0);
			VPERCatalogoNoAp.setICveUsuBaja(Integer.parseInt(request
					.getParameter("iCveUsuResp")));

			cClave = (String) dPERCatalogoNoAp.disable(null, VPERCatalogoNoAp);

			// Se agrego para actulizar la tabla: PERLicencia.
			// VPERCatalogoNoAp = (TVPERCatalogoNoAp)this.getInputs();
			VPERLicencia.setICvePersonal(VPERCatalogoNoAp.getICvePersonal());
			VPERLicencia.setICveMdoTrans(VPERCatalogoNoAp.getICveMdoTrans());
			VPERLicencia.setICveCategoria(VPERCatalogoNoAp.getICveCategoria());
			DPERLicencia.deletePERLicencia(null, VPERLicencia);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDPERDatos dPERDatos = new TDPERDatos();
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		try {

			System.out.println("lMostrarDatos = "+request.getParameter("lMostrarDatos"));
			if (request.getParameter("lMostrarDatos") != null
					&& request.getParameter("lMostrarDatos").compareTo("true") == 0) {
				String cWhere = "";
				cWhere = " where iCvePersonal = "
						+ request.getParameter("iCvePersonal");

				if (cCondicion.compareTo("") != 0) {
					if (cWhere.compareTo("") != 0)
						cWhere += " and " + cCondicion;
					else
						cWhere += "where " + cCondicion;
				}

				if (cOrden.compareTo("") != 0) {
					cWhere += cOrden;
				}
				vDespliega = dPERDatos.FindBySelector(cWhere);
			}

			// Agregar los Motivos y Rubros de la No Aptitud.
			if (request.getParameter("lAccion") != null
					&& request.getParameter("lAccion").compareTo("AgregarMot") == 0) {
				TVPERCatMotRubNoAp VPERCatMotRubNoAp = new TVPERCatMotRubNoAp();
				VPERCatMotRubNoAp.setiCvePersonal(new Integer(request
						.getParameter("iCvePersonal")).intValue());
				VPERCatMotRubNoAp.setiCveMdoTrans(new Integer(request
						.getParameter("hdiCveMdoTrans")).intValue());
				VPERCatMotRubNoAp.setiCveCategoria(new Integer(request
						.getParameter("hdiCveCategoria")).intValue());
				VPERCatMotRubNoAp.setiCveCatalogoNoAp(new Integer(request
						.getParameter("hdiCveCatalogoNoAp")).intValue());
				VPERCatMotRubNoAp.setICveMotivoNoAp(new Integer(request
						.getParameter("iCveMotivoNoAp")).intValue());
				VPERCatMotRubNoAp.setICveRubroNoAp(new Integer(request
						.getParameter("iCveRubroNoAp")).intValue());
				try {
					String cClaveNoAp = (String) new TDPERCatMotRubNoAp()
							.insert(null, VPERCatMotRubNoAp);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (request.getParameter("lAccion") != null
					&& request.getParameter("lAccion").compareTo("BorrarMot") == 0) {
				TVPERCatMotRubNoAp VPERCatMotRubNoAp = new TVPERCatMotRubNoAp();
				VPERCatMotRubNoAp.setiCvePersonal(new Integer(request
						.getParameter("iCvePersonal")).intValue());
				VPERCatMotRubNoAp.setiCveMdoTrans(new Integer(request
						.getParameter("hdiCveMdoTrans")).intValue());
				VPERCatMotRubNoAp.setiCveCategoria(new Integer(request
						.getParameter("hdiCveCategoria")).intValue());
				VPERCatMotRubNoAp.setiCveCatalogoNoAp(new Integer(request
						.getParameter("hdiCveCatalogoNoAp")).intValue());
				VPERCatMotRubNoAp.setICveMotivoNoAp(new Integer(request
						.getParameter("iCveMotivoNoAp")).intValue());
				VPERCatMotRubNoAp.setICveRubroNoAp(new Integer(request
						.getParameter("iCveRubroNoAp")).intValue());
				try {
					String cClaveNoAp = (String) new TDPERCatMotRubNoAp()
							.delete(null, VPERCatMotRubNoAp);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (request.getParameter("lGuardar") != null
					&& request.getParameter("lGuardar").compareTo("true") == 0) {
				Vector vcPERCatalogoNoAp = new Vector();

				if (request.getParameter("lAccion") != null
						&& request.getParameter("lAccion").compareTo("Quitar") == 0)
					vcPERCatalogoNoAp = dPERCatalogoNoAp
							.FindByAll(" where iCvePersonal     = "
									+ request.getParameter("iCvePersonal")
									+ "   and iCveMdoTrans     = "
									+ request.getParameter("hdiCveMdoTrans")
									+ "   and iCveCategoria    = "
									+ request.getParameter("hdiCveCategoria")
									+ "   and iCveCatalogoNoAp = "
									+ request
											.getParameter("hdiCveCatalogoNoAp")
									+ "   and lVigente         = 1 ");
				else
					vcPERCatalogoNoAp = dPERCatalogoNoAp
							.FindByAll(" where iCvePersonal = "
									+ request.getParameter("iCvePersonal")
									+ " and iCveMdoTrans = "
									+ request.getParameter("iCveMdoTrans")
									+ " and iCveCategoria = "
									+ request.getParameter("iCveCategoria")
									+ " and lVigente = 1");

				// Agragar al catalogo
				if (vcPERCatalogoNoAp.size() > 0
						&& request.getParameter("lAccion") != null
						&& request.getParameter("lAccion").compareTo(
								"GuardarNoAp") == 0)
					cMensajeAccion = "ErrorAgregar";
				else if (request.getParameter("lAccion") != null
						&& request.getParameter("lAccion").compareTo(
								"GuardarNoAp") == 0) {
					cMensajeAccion = "Agrego";
					this.Guardar();
				}
				
				///Actualizar un examen 
				if(request.getParameter("lAccion").compareTo(
						"ActualizaNoAp") == 0){
						this.Actualizar();
				}
				

				// Quitar del catalogo
				if (vcPERCatalogoNoAp.size() > 0
						&& request.getParameter("lAccion") != null
						&& request.getParameter("lAccion").compareTo("Quitar") == 0) {
					cMensajeAccion = "Desactivo";
					this.Borrar();
				} else if (request.getParameter("lAccion") != null
						&& request.getParameter("lAccion").compareTo("Quitar") == 0)
					cMensajeAccion = "ErrorDesactivar";

			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave1").compareTo("null") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
			mPk.add(request.getParameter("hdCampoClave3"));
			mPk.add(request.getParameter("hdCampoClave4"));
		}
	}

	/**
	 * Metodo getInputs, Contiene los valores de la Pantalla.
	 * 
	 * @return Objeto con los Valores de la Pantalla.
	 * @throws CFGException
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVPERCatalogoNoAp vPERCatalogoNoAp = new TVPERCatalogoNoAp();
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		try {

			/*
			 * Calcula Fecha Actual
			 */
			TFechas dtFecha = new TFechas();
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
			String dFechaActual = "";
			dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha, "/");

			TDEMOExamen dEMOExamen = new TDEMOExamen();
			TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERCatalogoNoAp.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERCatalogoNoAp.setICvePersonal(iCampo);
			
			

			///Obtener Numero Maximo de examenes
			iCampo = dPERCatalogoNoAp.ExamenMaximo(request.getParameter("iCvePersonal") );
			vPERCatalogoNoAp.setINumExamen(iCampo+1);
			

			cCampo = "" + request.getParameter("iCveMdoTrans");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERCatalogoNoAp.setICveMdoTrans(iCampo);

			cCampo = "" + request.getParameter("iCveCategoria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERCatalogoNoAp.setICveCategoria(iCampo);

			if (request.getParameter("lAccion") == null
					|| request.getParameter("lAccion").compareTo("GuardarNoAp") == 0)
				vPERCatalogoNoAp.setLVigente(1);
			else if (request.getParameter("lAccion") == null
					|| request.getParameter("lAccion").compareTo("Quitar") == 0)
				vPERCatalogoNoAp.setLVigente(0);

			if (request.getParameter("lAccion") == null
					|| request.getParameter("lAccion").compareTo("GuardarNoAp") == 0)
				vPERCatalogoNoAp.setICveUsuAgrega(Integer.parseInt(request
						.getParameter("iCveUsuResp")));
			else if (request.getParameter("lAccion") == null
					|| request.getParameter("lAccion").compareTo("Quitar") == 0)
				vPERCatalogoNoAp.setICveUsuBaja(Integer.parseInt(request
						.getParameter("iCveUsuResp")));

			cCampo = "" + request.getParameter("dtInicio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPERCatalogoNoAp.setDtInicio(dtCampo);

			cCampo = dFechaActual;
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vPERCatalogoNoAp.setDtFin(dtCampo);

			cCampo = "" + request.getParameter("iPeriodo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERCatalogoNoAp.setIPeriodo(iCampo);

			cCampo = "" + request.getParameter("iCveMotivoNoAp");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vPERCatalogoNoAp.setICveMotivoNoAp(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vPERCatalogoNoAp;
	}
	
	public boolean guardaPerExamen() throws CFGException{
		boolean respuesta = false;
		TDPEREXAMENNoAp dPEREXAMENNoAp = new TDPEREXAMENNoAp();
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		try{
			String cCampo;
			int iCampo;
			float fCampo;
			int exp = 0;//expediente
			int exa = 0;//examen
			int serv = 76;//servicio
			int rama = 1;//rama
			int sintoma = 0;//sintoma
			String valor = "";
			int tpocampo = 0;
			
			
			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			exp = iCampo;
			

			///Obtener Numero Maximo de examenes
			exa = dPERCatalogoNoAp.ExamenMaximo(request.getParameter("iCvePersonal"));

			if (request.getParameter("cObsevaciones") != null){
				cCampo = request.getParameter("cObsevaciones");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 1;
			tpocampo = 3;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			

			if (request.getParameter("toxpendiente") != null){
				cCampo = request.getParameter("toxpendiente");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 9;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("alcolimetria") != null){
				cCampo = request.getParameter("alcolimetria");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 2;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("numpba") != null){
				cCampo = request.getParameter("numpba");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 10;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("numeqp") != null){
				cCampo = request.getParameter("numeqp");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 11;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("pbaorina") != null){
				cCampo = request.getParameter("pbaorina");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 3;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("rpositivo") != null){
				cCampo = request.getParameter("rpositivo");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 12;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("numffccc") != null){
				cCampo = request.getParameter("numffccc");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 4;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);

			if (request.getParameter("numenvio") != null){
				cCampo = request.getParameter("numenvio");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo;
			sintoma = 5;
			tpocampo = 2;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			if (request.getParameter("dttomada") != null){
				cCampo = request.getParameter("dttomada");
				}
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			valor = cCampo.substring(6,10)+"-"+cCampo.substring(3,5)+"-"+cCampo.substring(0,2);
			sintoma = 6;
			tpocampo = 5;
			dPEREXAMENNoAp.insert(null, exp, exa, serv, rama, sintoma, valor, tpocampo);
			
			
		}catch (Exception ex) {
			error("guardaPerExamen", ex);
			throw new CFGException("");
		}
	
		return respuesta;
	}

	public boolean eliminaExamen() throws CFGException {
		boolean respuesta = false;
		TDPEREXAMENNoAp dPEREXAMENNoAp = new TDPEREXAMENNoAp();
		TDPERCatalogoNoAp dPERCatalogoNoAp = new TDPERCatalogoNoAp();
		try {
			String cCampo;
			int iCampo;
			float fCampo;
			int exp = 0;// expediente
			int exa = 0;// examen

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			exp = iCampo;
			
			cCampo = "" + request.getParameter("hdNumExamen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			exa = iCampo;
			
			dPEREXAMENNoAp.eliminaExamen(null, exp, exa);
			
		} catch (Exception ex) {
			error("guardaPerExamen", ex);
			throw new CFGException("");
		}

		return respuesta;
	}
	
	public String Dato(String args) {
		// Se puede dividir por medio de comas o palabras
		String dias = "" + args;
		String diaArray[] = dias.split("\\.");
		String regresa = "";
		int count = 0;
		System.out.println("--Ejemplo 1--");
		for (String dia : diaArray) {
			System.out.println(dia);
			if(count == 0){
				regresa = dia;
			}
			count++;
		}
		return regresa;
	}
	
}

