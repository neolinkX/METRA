package com.micper.util;

//Java imports
import java.util.*;
import java.net.*;
import ingresosws.generated.*;
import gob.sct.ingresosws.ws.ConAreaRec.*;
import gob.sct.ingresosws.ws.ConUsrIng.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: TDIngresos
 * </p>
 * <p>
 * Description: Clase que llama a los m�todos de los WebServices de Consulta y
 * que se utiliza como interfaz para evitar el llamado de primitivas, regresando
 * colecciones con Value Objects de TVCiudadano
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Tecnolog�a Inred S.A. de C.V.
 * </p>
 * 
 * @author Ing. David SA G�mez.
 * @version 1.0
 */

public class TDIngresos extends DAOBase {
	public final static int TODAS = 0;
	public final static int S_TARIFAS = 1;
	public final static int S_PORCENTAJES = 2;
	public final static int S_TARIFAS_DIF_CERO = 3;
	public final static int RFC = 1;
	public final static int CURP = 2;
	public final static int RPA = 3;
	private TParametro parametros = null;
	private String ingWSURL = "";
	private String cUsuario = "";
	private String cContrasenia = "";

	/**
	 * Constructor que configura a la clase por sistema, para el llamado del
	 * Logger
	 */
	public TDIngresos() {
		this.setSistema("07");
		parametros = new TParametro("07");
		ingWSURL = parametros.getPropEspecifica("URLWSING");
		// ingWSURL = parametros.getPropEspecifica("URLWSINGPba");
	}

	/**
	 * M�todo que busca todas las Tarifas
	 * 
	 * @param pIEjercicio
	 *            Ejercicio.
	 * @param pICveCategoria
	 *            Clave Categoria (TODAS o un valor dado).
	 * @param pICveConcepto
	 *            Clave Concepto (TODAS o un valor dado).
	 * @param pITipoRef
	 *            Clave TipoRef (TODAS, S_TARIFAS, S_PORCENTAJES,
	 *            S_TARIFAS_DIF_CERO).
	 * @return Colecci�n de Tarifas encontrados.
	 */
	public Object findTarifaConcepto(int pIEjercicio, int pICveCategoria,
			int pICveConcepto, int pITipoRef) {
		Vector vResultado = new Vector();
		Object obj[] = null;
		try {
			String Usuario = parametros.getPropEspecifica("ConWSIngUsr");
			String Contrasenia = parametros.getPropEspecifica("ConWSIngPwd");
			int Recaudacion = Integer.parseInt(parametros
					.getPropEspecifica("WSIngRecauda"));
			if (ingWSURL.compareTo("") != 0) {
				// binding al WebService
				String wsdlUrl = ingWSURL + "ConTarifaConcepto?WSDL";
				TDConTarifaConcepto service = new TDConTarifaConcepto_Impl(
						wsdlUrl);
				TDConTarifaConceptoPort conTarifaConcepto = service
						.getTDConTarifaConceptoPort();
				obj = conTarifaConcepto.findTarifaConcepto(Usuario,
						Contrasenia, pIEjercicio, Recaudacion, pICveCategoria,
						pICveConcepto, pITipoRef);
				for (int i = 0; obj != null && i < obj.length; i++) {
					TVConceptoII vC = (TVConceptoII) obj[i];
					if (vC.getCOficioAutoriza() != null)
						vC.setCOficioAutoriza(URLDecoder.decode(
								vC.getCOficioAutoriza(), "UTF-8"));
					if (vC.getCCveArticulo() != null)
						vC.setCCveArticulo(URLDecoder.decode(
								vC.getCCveArticulo(), "UTF-8"));
					if (vC.getCInciso() != null)
						vC.setCInciso(URLDecoder.decode(vC.getCInciso(),
								"UTF-8"));
					if (vC.getCDscConcepto() != null)
						vC.setCDscConcepto(URLDecoder.decode(
								vC.getCDscConcepto(), "UTF-8"));
					if (vC.getCDscImpresion() != null)
						vC.setCDscImpresion(URLDecoder.decode(
								vC.getCDscImpresion(), "UTF-8"));
					vResultado.add(vC);
				}
			} else {
				info("findTarifaConcepto-WSIN no cuenta con ingWSURL.");
			}
		} catch (Exception ex) {
			warn("findTarifaConcepto-WSIN", ex);
		} finally {
		}
		return vResultado;
	}

	/**
	 * M�todo que genera un movimiento
	 * 
	 * @param iReferencia
	 *            Referecia.
	 * @param iUnidades
	 *            Unidades.
	 * @param iCveUsuario
	 *            Clave del Usuarios.
	 * @param cObservacion
	 *            Observaciones.
	 * @return Referencia del movimiento actualizado.
	 */
	public Object generaMov(int iReferencia, int iUnidades, int iCveUsuario,
			String cObservacion) {
		Vector vResultado = new Vector();
		Object obj[] = null;
		String sResultado = "";
		try {

			String Usuario = parametros.getPropEspecifica("ConWSIngUsr");
			String Contrasenia = parametros.getPropEspecifica("ConWSIngPwd");
			int iCveUAdmva = Integer.parseInt(parametros
					.getPropEspecifica("WSIngUAdmva"));
			int iCveOficina = Integer.parseInt(parametros
					.getPropEspecifica("WSIngOficina"));
			if (ingWSURL.compareTo("") != 0) {
				// binding al WebService
				String wsdlUrl = ingWSURL + "GenMovimiento?WSDL";
				TDGenMovimiento service = new TDGenMovimiento_Impl(wsdlUrl);
				TDGenMovimientoPort genMovimiento = service
						.getTDGenMovimientoPort();
				if (cObservacion != null)
					cObservacion = URLEncoder.encode(cObservacion, "UTF-8");
				// cObservacion = cObservacion; //
				// URLEncoder.encode(cObservacion, "UTF-8");
				obj = genMovimiento.generaMov(Usuario, Contrasenia,
						iReferencia, iUnidades, iCveUAdmva, iCveOficina,
						iCveUsuario, cObservacion);
				for (int i = 0; obj != null && i < obj.length; i++) {
					TVMovGenerado vMG = (TVMovGenerado) obj[i];
					if (vMG.getCRefAlfanum() != null)
						sResultado = vMG.getCRefAlfanum();
				}
			} else {
				info("generaMov-WSIN no cuenta con ingWSURL.");
			}
		} catch (Exception ex) {
			warn("generaMov-WSIN", ex);
		} finally {
		}
		return sResultado;
	}

	/**
	 * M�todo que genera un movimiento
	 * 
	 * @param iReferencia
	 *            Referecia.
	 * @param iUnidades
	 *            Unidades.
	 * @param iCveUsuario
	 *            Clave del Usuarios.
	 * @param cObservacion
	 *            Observaciones.
	 * @param iCveUAdmva
	 *            Unidad administrativa a la cual pertenece el movimiento.
	 * @param iCveOficina
	 *            Identificador de la oficina que genera el movimiento.
	 * @return Referencia del movimiento actualizado.
	 */
	public Object generaMov(int iReferencia, int iUnidades, int iCveUsuario,
			String cObservacion, int iCveUAdmva, int iCveOficina) {
		Vector vResultado = new Vector();
		Object obj[] = null;
		String sResultado = "";
		try {
			String Usuario = parametros.getPropEspecifica("ConWSIngUsr");
			String Contrasenia = parametros.getPropEspecifica("ConWSIngPwd");
			if (ingWSURL.compareTo("") != 0) {
				// binding al WebService
				String wsdlUrl = ingWSURL + "GenMovimiento?WSDL";
				TDGenMovimiento service = new TDGenMovimiento_Impl(wsdlUrl);
				TDGenMovimientoPort genMovimiento = service
						.getTDGenMovimientoPort();
				if (cObservacion != null)
					cObservacion = URLEncoder.encode(cObservacion, "UTF-8");
				// cObservacion = cObservacion; //
				// URLEncoder.encode(cObservacion, "UTF-8");
				obj = genMovimiento.generaMov(Usuario, Contrasenia,
						iReferencia, iUnidades, iCveUAdmva, iCveOficina,
						iCveUsuario, cObservacion);
				for (int i = 0; obj != null && i < obj.length; i++) {
					TVMovGenerado vMG = (TVMovGenerado) obj[i];
					if (vMG.getCRefAlfanum() != null)
						sResultado = vMG.getCRefAlfanum();
				}
			} else {
				info("generaMov-WSIN no cuenta con ingWSURL.");
			}
		} catch (Exception ex) {
			warn("generaMov-WSIN", ex);
		} finally {
		}
		return sResultado;
	}

	/**
	 * M�todo que inserta un registro de usuario
	 * 
	 * @param cRPA
	 *            Dato RPA.
	 * @param cRFC
	 *            Dato RFC.
	 * @param cCURP
	 *            Dato CURP.
	 * @param cNombre
	 *            Nombre.
	 * @param cApPaterno
	 *            Apellido Paterno.
	 * @param cApMaterno
	 *            Apellido Materno.
	 * @param lFisicoMoral
	 *            Persona Fisica o Moral.
	 * @param cCalle
	 *            Dato Calle.
	 * @param cColonia
	 *            Dato Colonia.
	 * @param iCP
	 *            Codigo Postal.
	 * @param cTelefono
	 *            Dato Telefono.
	 * @param iCvePais
	 *            Clave Pais.
	 * @param iCveEntidadFed
	 *            Clave Entidad Federativa.
	 * @param iCveMunicipio
	 *            Clave Municipio.
	 * @param cCiudad
	 *            Dato Ciudad.
	 * @return Clave del dato insertado.
	 */
	public Object insertUsr(String cRPA, String cRFC, String cCURP,
			String cNombre, String cApPaterno, String cApMaterno,
			int lFisicoMoral, String cCalle, String cColonia, int iCP,
			String cTelefono, int iCvePais, int iCveEntidadFed,
			int iCveMunicipio, String cCiudad) {
		Vector vResultado = new Vector();
		long obj = 0;
		String sResultado = "";
		try {
			String Usuario = parametros.getPropEspecifica("ConWSIngUsr");
			String Contrasenia = parametros.getPropEspecifica("ConWSIngPwd");
			int iCveUAdmva = Integer.parseInt(parametros
					.getPropEspecifica("WSIngUAdmva"));
			if (ingWSURL.compareTo("") != 0) {
				// binding al WebService
				String wsdlUrl = ingWSURL + "ConUsrIng?WSDL";
				TDConUsrIng service = new TDConUsrIng_Impl(wsdlUrl);
				TDConUsrIngPort conUsrIng = service.getTDConUsrIngPort();
				if (cRPA != null)
					cRPA = URLEncoder.encode(cRPA, "UTF-8");
				if (cRFC != null)
					cRFC = URLEncoder.encode(cRFC, "UTF-8");
				if (cCURP != null)
					cCURP = URLEncoder.encode(cCURP, "UTF-8");
				if (cNombre != null)
					cNombre = URLEncoder.encode(cNombre, "UTF-8");
				if (cApPaterno != null)
					cApPaterno = URLEncoder.encode(cApPaterno, "UTF-8");
				if (cApMaterno != null)
					cApMaterno = URLEncoder.encode(cApMaterno, "UTF-8");
				if (cCalle != null)
					cCalle = URLEncoder.encode(cCalle, "UTF-8");
				if (cColonia != null)
					cColonia = URLEncoder.encode(cColonia, "UTF-8");
				if (cTelefono != null)
					cTelefono = URLEncoder.encode(cTelefono, "UTF-8");
				if (cCiudad != null)
					cCiudad = URLEncoder.encode(cCiudad, "UTF-8");
				obj = conUsrIng.insertUsr(Usuario, Contrasenia, iCveUAdmva,
						cRPA, cRFC, cCURP, cNombre, cApPaterno, cApMaterno,
						lFisicoMoral, cCalle, cColonia, iCP, cTelefono,
						iCvePais, iCveEntidadFed, iCveMunicipio, cCiudad);
				sResultado = obj + "";
			} else {
				info("generaMov-WSIN no cuenta con ingWSURL.");
			}
		} catch (Exception ex) {
			warn("generaMov-WSIN", ex);
		} finally {
		}
		return sResultado;
	}

	/**
	 * M�todo que busca un usuario en base al RFC, CURP, RPA
	 * 
	 * @param cValor
	 *            Cadena conel valor por el cual se va a realizar la b�squeda
	 *            (RFC, CURP, RPA).
	 * @param iTipoBusqueda
	 *            Por qu� se va a realizar la b�squeda, 1 por RFC, 2 por
	 *            CURP, 3 por RPA.
	 * @return datos del valor buscado.
	 */
	public Object findRFC(String cValor, int iTipoBusqueda) {
		Vector vResultado = new Vector();
		Object obj[] = null;
		try {
			String Usuario = parametros.getPropEspecifica("ConWSIngUsr");
			String Contrasenia = parametros.getPropEspecifica("ConWSIngPwd");
			int iCveUAdmva = Integer.parseInt(parametros
					.getPropEspecifica("WSIngUAdmva"));
			if (ingWSURL.compareTo("") != 0) {
				// binding al WebService
				String wsdlUrl = ingWSURL + "ConUsrIng?WSDL";
				TDConUsrIng service = new TDConUsrIng_Impl(wsdlUrl);
				TDConUsrIngPort conUsrIng = service.getTDConUsrIngPort();
				if (cValor != null)
					cValor = URLEncoder.encode(cValor, "UTF-8");
				obj = conUsrIng.findRFC(Usuario, Contrasenia, cValor,
						iTipoBusqueda);
				for (int i = 0; obj != null && i < obj.length; i++) {
					TVINGUsuario vU = (TVINGUsuario) obj[i];
					if (vU.getCApMaterno() != null)
						vU.setCApMaterno(URLDecoder.decode(vU.getCApMaterno(),
								"UTF-8"));
					if (vU.getCApPaterno() != null)
						vU.setCApPaterno(URLDecoder.decode(vU.getCApPaterno(),
								"UTF-8"));
					if (vU.getCCURP() != null)
						vU.setCCURP(URLDecoder.decode(vU.getCCURP(), "UTF-8"));
					if (vU.getCCalle() != null)
						vU.setCCalle(URLDecoder.decode(vU.getCCalle(), "UTF-8"));
					if (vU.getCCiudad() != null)
						vU.setCCiudad(URLDecoder.decode(vU.getCCiudad(),
								"UTF-8"));
					if (vU.getCColonia() != null)
						vU.setCColonia(URLDecoder.decode(vU.getCColonia(),
								"UTF-8"));
					if (vU.getCDscEntidadFed() != null)
						vU.setCDscEntidadFed(URLDecoder.decode(
								vU.getCDscEntidadFed(), "UTF-8"));
					if (vU.getCDscMunicipio() != null)
						vU.setCDscMunicipio(URLDecoder.decode(
								vU.getCDscMunicipio(), "UTF-8"));
					if (vU.getCDscPais() != null)
						vU.setCDscPais(URLDecoder.decode(vU.getCDscPais(),
								"UTF-8"));
					if (vU.getCNombre() != null)
						vU.setCNombre(URLDecoder.decode(vU.getCNombre(),
								"UTF-8"));
					if (vU.getCRFC() != null)
						vU.setCRFC(URLDecoder.decode(vU.getCRFC(), "UTF-8"));
					if (vU.getCRPA() != null)
						vU.setCRPA(URLDecoder.decode(vU.getCRPA(), "UTF-8"));
					if (vU.getCTelefono() != null)
						vU.setCTelefono(URLDecoder.decode(vU.getCTelefono(),
								"UTF-8"));
					vResultado.add(vU);
				}
			} else {
				info("generaMov-WSIN no cuenta con ingWSURL.");
			}
		} catch (Exception ex) {
			warn("generaMov-WSIN", ex);
		} finally {
		}
		return vResultado;
	}

	/**
	 * M�todo que busca un usuario en base a su clave
	 * 
	 * @param iCveUsuario
	 *            Clave del usuario.
	 * @return datos del valor buscado.
	 */
	public Object getUsuarioSCT(int iCveUsuario) {
		TVINGUsuario obj = null;
		try {
			String Usuario = parametros.getPropEspecifica("ConWSIngUsr");
			String Contrasenia = parametros.getPropEspecifica("ConWSIngPwd");
			int iCveUAdmva = Integer.parseInt(parametros
					.getPropEspecifica("WSIngUAdmva"));
			if (ingWSURL.compareTo("") != 0) {
				// binding al WebService
				String wsdlUrl = ingWSURL + "ConUsrIng?WSDL";
				TDConUsrIng service = new TDConUsrIng_Impl(wsdlUrl);
				TDConUsrIngPort conUsrIng = service.getTDConUsrIngPort();
				obj = conUsrIng
						.getUsuarioSCT(Usuario, Contrasenia, iCveUsuario);
				if (obj.getCApMaterno() != null)
					obj.setCApMaterno(URLDecoder.decode(obj.getCApMaterno(),
							"UTF-8"));
				if (obj.getCApPaterno() != null)
					obj.setCApPaterno(URLDecoder.decode(obj.getCApPaterno(),
							"UTF-8"));
				if (obj.getCCURP() != null)
					obj.setCCURP(URLDecoder.decode(obj.getCCURP(), "UTF-8"));
				if (obj.getCCalle() != null)
					obj.setCCalle(URLDecoder.decode(obj.getCCalle(), "UTF-8"));
				if (obj.getCCiudad() != null)
					obj.setCCiudad(URLDecoder.decode(obj.getCCiudad(), "UTF-8"));
				if (obj.getCColonia() != null)
					obj.setCColonia(URLDecoder.decode(obj.getCColonia(),
							"UTF-8"));
				if (obj.getCDscEntidadFed() != null)
					obj.setCDscEntidadFed(URLDecoder.decode(
							obj.getCDscEntidadFed(), "UTF-8"));
				if (obj.getCDscMunicipio() != null)
					obj.setCDscMunicipio(URLDecoder.decode(
							obj.getCDscMunicipio(), "UTF-8"));
				if (obj.getCDscPais() != null)
					obj.setCDscPais(URLDecoder.decode(obj.getCDscPais(),
							"UTF-8"));
				if (obj.getCNombre() != null)
					obj.setCNombre(URLDecoder.decode(obj.getCNombre(), "UTF-8"));
				if (obj.getCRFC() != null)
					obj.setCRFC(URLDecoder.decode(obj.getCRFC(), "UTF-8"));
				if (obj.getCRPA() != null)
					obj.setCRPA(URLDecoder.decode(obj.getCRPA(), "UTF-8"));
				if (obj.getCTelefono() != null)
					obj.setCTelefono(URLDecoder.decode(obj.getCTelefono(),
							"UTF-8"));
			} else {
				info("generaMov-WSIN no cuenta con ingWSURL.");
			}
		} catch (Exception ex) {
			warn("generaMov-WSIN", ex);
		} finally {
		}
		return obj;
	}

}
