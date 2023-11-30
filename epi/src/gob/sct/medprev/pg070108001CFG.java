package gob.sct.medprev;

import com.micper.excepciones.CFGException;
import com.micper.ingsw.TParametro;
import com.micper.seguridad.dao.CFGCatBase2;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.TFechas;

import gob.sct.medprev.dao.TDEPICisCita;
import gob.sct.medprev.dao.TDEXPMultas;
import gob.sct.medprev.dao.TDMEDInhabilita;
import gob.sct.medprev.dao.TDSEGUsuExp;
import gob.sct.medprev.vo.TVEXPMulta;

/**
 * * Clase de configuraciÃ¯Â¿Â½n para CFG Registro de Multas
 * <p>
 * AdministraciÃ¯Â¿Â½n y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>AG SA L
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070102021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070102020CFG.png'>
 */
public class pg070108001CFG extends CFGCatBase2 {

	private String cMulta;
	private String cModulo;
	private String cFecha;

	public pg070108001CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void mainBlock() {

		cModulo = request.getParameter("iCveModulo");
		cFecha = request.getParameter("dtFecha");
		cMulta = "";
	}

	/**
	 * MÃƒÂ©todo Guardar
	 */
	public void Guardar() {
		//TDEPICisCita dEPICisMulta = new TDEPICisCita();
		TDEXPMultas dEPICisMulta = new TDEXPMultas();
		try {
			cClave = (String) dEPICisMulta.insertCA(null, this.getInputs());
			cMulta = cClave;
			System.out.println("cMulta = "+cMulta);
			System.out.println("cClave = "+cClave);
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			// System.out.println(cClave);
			if (cClave.compareTo("ERROR") == 0) {
				vErrores.acumulaError("Registro Existente: ", 0,
						"No es posible registrar la Multa para la misma persona el mismo d�a");
			}
			super.Guardar();
			cActual = request.getParameter("iCveUniMed");
		}
	} // MÃƒÂ©todo Guardar

	/**
	 * MÃƒÂ©todo GuardarA
	 */
	public void GuardarA() {
		//TDEPICisCita dEPICisMulta = new TDEPICisCita();
		TDEXPMultas dEPICisMulta = new TDEXPMultas();
		try {
			cClave = (String) dEPICisMulta.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // MÃƒÂ©todo GuardarA

	/**
	 * MÃƒÂ©todo Borrar
	 */
	public void Borrar() {
		TDEPICisCita dEPICisMulta = new TDEPICisCita();
		try {
			cClave = (String) dEPICisMulta.delete(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // MÃƒÂ©todo Borrar

	/**
	 * MÃƒÂ©todo fillVector
	 */
	public void fillVector() {
		//TDEPICisCita dEPICisMulta = new TDEPICisCita();
		TDEXPMultas dEPICisMulta = new TDEXPMultas();
		TFechas tfCampo = new TFechas();
		String cWhere;
		String cWhere2;
		try {

			/*
			 * cWhere = " where EPICisMulta.iCveUniMed = "; cWhere = cWhere +
			 * request.getParameter("iCveUniMed") + " "; cWhere = cWhere +
			 * " and EPICisMulta.dtFecha = '" +
			 * tfCampo.getDateSQL(request.getParameter("dtFecha")) + "' ";
			 * cWhere = cWhere + " and EPICisMulta.iCveModulo = " +
			 * request.getParameter("iCveModulo") + " ";
			 */

			if (request.getParameter("iCveUniMed") != null
					&& (request.getParameter("dtFecha") != null && request
							.getParameter("dtFecha").compareTo("") != 0)
					&& request.getParameter("iCveModulo") != null
					&& request.getParameter("iCvePersonal") != null	) {

				cWhere = " where EPICisMulta.iCveUniMed = ";
				cWhere = cWhere + request.getParameter("iCveUniMed") + " ";
				cWhere = cWhere + " and EPICisMulta.dtFecha = '"
						+ tfCampo.getDateSQL(request.getParameter("dtFecha"))
						+ "' ";
				cWhere = cWhere + " and EPICisMulta.iCveModulo = "
						+ request.getParameter("iCveModulo") + " ";

				cWhere2 = " E.iCveUniMed = ";
				cWhere2 = cWhere2 + request.getParameter("iCveUniMed") + " ";
				cWhere2 = cWhere2 + " and E.dtFecha = '"
						+ tfCampo.getDateSQL(request.getParameter("dtFecha"))
						+ "' ";
				cWhere2 = cWhere2 + " and E.iCveModulo = "
						+ request.getParameter("iCveModulo") + " ";

				vDespliega = dEPICisMulta.FindByAll(request.getParameter("iCvePersonal"));
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * MÃƒÂ©todo fillPK
	 */
	public void fillPK() {

		mPk.add(cActual);
		mPk.add(cModulo);
		mPk.add(cFecha);
		mPk.add(cMulta);

	}

	/**
	 * MÃƒÂ©todo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVEXPMulta vEPICisMulta = new TVEXPMulta();

		// System.out.println("Bajando el usuario de la sesion");
		TVUsuario usuario = (TVUsuario) this.httpReq.getSession().getAttribute(
				"UsrID"); // BEA SYSTEMS 16/10/2006
		try {
			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICveUniMed(iCampo);
			

			cCampo = "" + request.getParameter("iCvePersonal");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICvePersonal(iCampo);

			cCampo = "" + request.getParameter("iCveModulo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("dtFecha");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisMulta.setDtFecha(dtCampo);

			cCampo = "" + request.getParameter("iCveMulta");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICveMulta(iCampo);
			// Hora
			cCampo = "" + request.getParameter("iHora");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setIHora(iCampo);

			cCampo = "" + request.getParameter("iMinutos");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setIMinutos(iCampo);

			/*
			 * cCampo = "" + request.getParameter("cHora"); if
			 * (cCampo.compareTo("null") == 0) { cCampo = ""; }
			 * vEPICisMulta.setCHora(cCampo);
			 */
			if (request.getParameter("cApPaterno") != null)
				cCampo = new String(request.getParameter("cApPaterno")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisMulta.setCApPaterno(cCampo);

			if (request.getParameter("cApMaterno") != null)
				cCampo = new String(request.getParameter("cApMaterno")
						.getBytes("ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisMulta.setCApMaterno(cCampo);

			if (request.getParameter("cNombre") != null)
				cCampo = new String(request.getParameter("cNombre").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisMulta.setCNombre(cCampo);

			if (request.getParameter("cGenero") != null)
				cCampo = new String(request.getParameter("cGenero").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisMulta.setCGenero(cCampo);
			
			
/////////// Se agrego el nuevo Campo Sexo que cumple con la norma DGIS 27-05-2014////
			if (request.getParameter("cGenero") != null)
				cCampo = new String(request.getParameter("cGenero").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			if(cCampo.equals("M")){
				cCampo = "H";
			}
			if(cCampo.equals("F")){
				cCampo = "M";
			}
			vEPICisMulta.setCSexo_DGIS(cCampo);
////////////////////////////////////////////////////////////////////////////////////////			
			

			cCampo = "" + request.getParameter("dtNacimiento");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				cCampo = cCampo.charAt(6)+""+cCampo.charAt(7)+"/"+cCampo.charAt(4)+""+cCampo.charAt(5)+"/"+cCampo.charAt(0)+""+cCampo.charAt(1)+""+cCampo.charAt(2)+""+cCampo.charAt(3);
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisMulta.setDtNacimiento(dtCampo);

			if (request.getParameter("cRFC") != null)
				cCampo = new String(request.getParameter("cRFC").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisMulta.setCRFC(cCampo);

			if (request.getParameter("cCURP") != null)
				cCampo = new String(request.getParameter("cCURP").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vEPICisMulta.setCCURP(cCampo);
			
			if (request.getParameter("cCantidad") != null)
				cCampo = new String(request.getParameter("cCantidad").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICantidad(iCampo);
			
			
			

			if (request.getParameter("cTarifa") != null)
				cCampo = new String(request.getParameter("cTarifa").getBytes(
						"ISO-8859-1"), "UTF-8");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setITarifa(iCampo);

			

			// Actualizar

			cCampo = "" + request.getParameter("iCveUniMedA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICveUniMedA(iCampo);

			cCampo = "" + request.getParameter("iCveModuloA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vEPICisMulta.setICveModuloA(iCampo);

			cCampo = "" + request.getParameter("dtFechaA");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vEPICisMulta.setDtFechaA(dtCampo);

			// Se agrega el usuario el Usuario BEA SYSTEMS 16/10/2006
			if (usuario != null) {
				vEPICisMulta.setICveUsuCita(usuario.getICveusuario());
			}


		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vEPICisMulta;
	}

	/**
	 * Validando que el Expediente no esta inhabilitado
	 */
	public int getInhabilitado(String Expediente) {
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		Boolean Inhabilitado = false;
		int Activo = 0;
		try {
			String cCondicion = " M1.iCvePersonal = " + Expediente;
			Inhabilitado = dMEDInhabilita.Inhabilitado(cCondicion);
			if (Inhabilitado) {
				Activo = 1;
			}
		} catch (Exception e) {
			Activo = 0;
			e.printStackTrace();
		}
		return Activo;
	}
	
	
	/**
	 * Validando que el Usuario sea Medico Tercero
	 */
	public boolean getBloqueaGeneraMulta(String Usuario, int iCveUsuario) {
		boolean Bloquear = false;
		Usuario = Usuario.toLowerCase();
		TDSEGUsuExp dSEGUsuExp = new TDSEGUsuExp();
		try {
			//System.out.println(Usuario.substring(0, 3));
			if(Usuario.substring(0, 3).toString().equals("ext")){
				Bloquear = true;
			}
			if(Bloquear){
				Bloquear = dSEGUsuExp.FindByEsTercero(iCveUsuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Bloquear;
	}

}