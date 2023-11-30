package gob.sct.medprev;

import java.util.Vector;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.msc.MailAvisoBiometric;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Catï¿½logo de Mï¿½dulos
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
 *      href="JavaScript:alert('Consulte los archivos:\n-pg071003021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg071003021CFG.png'>
 */
public class pg071003031CFG extends CFGCatBase2 {
	public pg071003031CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071003030.jsp|pg071003010.jsp|pg071003032.jsp|";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDGRLModulo dGRLModulo = new TDGRLModulo();
		try {
			cClave = (String) dGRLModulo.insert(null, this.getInputs());
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
		TDGRLModulo dGRLModulo = new TDGRLModulo();
		TDGRLModuloDisp dGRLModuloDisp = new TDGRLModuloDisp();
		TDGRLDispositivo dDispositivo = new TDGRLDispositivo();
		try {

			cClave = (String) dGRLModulo.update(null, this.getInputs());
			cClave = (String) dGRLModuloDisp.update(null, this.getInputs());

			if (!request.getParameter("hdLValidaAnt").equals(
					request.getParameter("lValida"))) {
				ExpBitMod mod = new ExpBitMod();
				MailAvisoBiometric mail = new MailAvisoBiometric();

				String valida = "Ninguno";
				if (request.getParameter("hdLValidaAnt").equals("1")) {
					valida = "Doctor";
				} else if (request.getParameter("hdLValidaAnt").equals("2")) {
					valida = "Paciente";
				} else if (request.getParameter("hdLValidaAnt").equals("3")) {
					valida = "Ambos";
				}
				mod.setiCveExpediente("0");
				mod.setiNumExamen("0");
				mod.setOperacion("16");
				StringBuffer mensaje = new StringBuffer();
				mensaje.append("Se cambia validación biométrica de " + valida
						+ " a " + request.getParameter("hdNameValidaBio")
						+ " para " + request.getParameter("cDscModulo")
						+ " de " + request.getParameter("hdNameUniMed"));
				mod.setDescripcion(mensaje.toString());
				mod.setiCveUsuarioRealiza(request
						.getParameter("hdUsuarioActivo"));
				mod.setIpAddress(request.getParameter("hdIpAddress"));
				mod.setMacAddress(request.getParameter("hdMacAddress"));
				mod.setComputerName(request.getParameter("hdComputerName"));
				mensaje.append(" por " + request.getParameter("hdNameUsr"));
				// mod.setiCveServicio(cServicio);
				int insert = new TDEXPBITMOD().insertServiciosIpMacName(null,
						mod);
				mail.enviaMailValidaBiometric(mensaje.toString());
			}
			
			/////Guardar en Bitacora la Modificacion del Estatus del Dispositivo//////////
			
			TVGRLModulo vGRLModulo = (TVGRLModulo) this.getInputs();
			
		//////Se obtienen los valores a actualizar//////////
					 	String strD = vGRLModulo.getCDispositivos()+"";
					 	String delimiter = "-";
					 	String[] tempDispositivo;
					 	String NumDispositivo = "";
					 	tempDispositivo = strD.split(delimiter,-1);
					 	String LeyendaDisp = "";
					 	String NombreD ="";
					 	for(int i =0; i < tempDispositivo.length-1 ; i++){
				    		NumDispositivo=""+tempDispositivo[i].replace("-", "");
				    		if (!request.getParameter("hdLValidaDispAnt"+NumDispositivo+"").equals(
									request.getParameter("D"+NumDispositivo+""))) {
				    			try{
				    				 NombreD = dDispositivo.FindByNombreDisp(NumDispositivo);
				    			}catch(Exception e){
				    				System.out.println("Se produjo un error al consultar el nombre del dispositivo");
				    			}
				    			
				    			String StatusAnt = "";
				    			if(request.getParameter("hdLValidaDispAnt"+NumDispositivo+"").equals("Si")){
				    				StatusAnt = "Habilitado";
				    			}else{
				    				StatusAnt = "Inhabilitado";
				    			}
				    			
				    			String StatusAc = "";
				    			if(request.getParameter("D"+NumDispositivo+"").equals("Si")){
				    				StatusAc = "Habilitado";
				    			}else{
				    				StatusAc = "Inhabilitado";
				    			}
				    			
				    				LeyendaDisp = LeyendaDisp+"Se modificó el estatus del dispositivo "+NombreD+" de  "+StatusAnt+" a "+StatusAc+" / ";
							}
						}
					 	
					 	
					 	if(LeyendaDisp.length() > 10){
							 	ExpBitMod mod = new ExpBitMod();
								String valida = "Ninguno";
								mod.setiCveExpediente("0");
								mod.setiNumExamen("0");
								mod.setOperacion("21");
								mod.setDescripcion(LeyendaDisp.toString());
								mod.setiCveUsuarioRealiza(request
										.getParameter("hdUsuarioActivo"));
								mod.setIpAddress(request.getParameter("hdIpAddress"));
								mod.setMacAddress(request.getParameter("hdMacAddress"));
								mod.setComputerName(request.getParameter("hdComputerName"));
								int insert = new TDEXPBITMOD().insertServiciosIpMacName(null,
										mod);
								//System.out.println(LeyendaDisp);
					 	}
					 	
			

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
		TDGRLModulo dGRLModulo = new TDGRLModulo();
		try {
			cClave = (String) dGRLModulo.disable(null, this.getInputs());
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
		TDGRLModulo dGRLModulo = new TDGRLModulo();
		try {
			if (request.getParameter("hdBoton").compareTo("Cancelar") == 0
					&& request.getParameter("hdCampoClave") != null)
				cActual = request.getParameter("hdCampoClave");

			String cWhere = "";
			String cOrderBy = "";

			if (request.getParameter("hdCampoClave1") != null
					&& request.getParameter("hdCampoClave1").compareTo("") != 0)
				cWhere += " where iCveUniMed = "
						+ request.getParameter("hdCampoClave1");
			else if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0)
				cWhere += " where iCveUniMed = "
						+ request.getParameter("iCveUniMed");
			else if (request.getParameter("hdUniMed") != null
					&& request.getParameter("hdUniMed").compareTo("") != 0)
				cWhere += " where iCveUniMed = "
						+ request.getParameter("hdUniMed");
			if (cCondicion.compareTo("") != 0) {
				if (cWhere.compareTo("") != 0)
					cWhere += " and " + cCondicion;
				else
					cWhere += cCondicion;
			}

			if (cOrden.compareTo("") != 0)
				cOrderBy = cOrden;
			else
				cOrderBy = " order by iCveModulo ";

			if ((request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").compareTo("") != 0
					&& request.getParameter("iCveUniMed").compareTo("null") != 0 && Integer
					.parseInt(request.getParameter("iCveUniMed")) > 0)
					|| (request.getParameter("hdUniMed") != null
							&& request.getParameter("hdUniMed").compareTo("") != 0
							&& request.getParameter("hdUniMed").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdUniMed")) > 0)
					|| (request.getParameter("hdCampoClave1") != null
							&& request.getParameter("hdCampoClave1").compareTo(
									"") != 0
							&& request.getParameter("hdCampoClave").compareTo(
									"null") != 0 && Integer.parseInt(request
							.getParameter("hdCampoClave1")) > 0))
				vDespliega = dGRLModulo.FindByAll(cWhere, cOrderBy);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		if (request.getParameter("hdCampoClave1") != null
				&& request.getParameter("hdCampoClave2") != null
				&& request.getParameter("hdCampoClave1").compareTo("") != 0
				&& request.getParameter("hdCampoClave2").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave1"));
			mPk.add(request.getParameter("hdCampoClave2"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
			mPk.add(request.getParameter("hdUniMed"));
			mPk.add(cActual);
		}
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVGRLModulo vGRLModulo = new TVGRLModulo();
		
		TDGRLDispositivo dDisp = new TDGRLDispositivo();
		TVGRLDispositivo vDispositivo = new TVGRLDispositivo();
		Vector vcDispositivo = new Vector();
		TDGRLModuloDisp dMDisp = new TDGRLModuloDisp();
		
		try {
			if (request.getParameter("hdBoton").compareTo("Guardar") == 0
					|| request.getParameter("hdBoton").compareTo("GuardarA") == 0
					|| request.getParameter("hdBoton").compareTo("Cancelar") == 0)
				cCampo = "" + request.getParameter("hdUniMed");
			else
				cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setICveModulo(iCampo);

			cCampo = "" + request.getParameter("cDscModulo");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCDscModulo(cCampo);

			cCampo = "" + request.getParameter("cClues");
			// System.out.println("Que traigo desde jsp: " +
			// request.getParameter("cClues"));
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCClues(cCampo);

			cCampo = "" + request.getParameter("cCalle");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCCalle(cCampo);

			cCampo = "" + request.getParameter("cColonia");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCColonia(cCampo);

			cCampo = "" + request.getParameter("iCP");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setICP(iCampo);

			cCampo = "" + request.getParameter("cCiudad");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCCiudad(cCampo);

			cCampo = "" + request.getParameter("iCvePais");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setICvePais(iCampo);

			cCampo = "" + request.getParameter("iCveEstado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setICveEstado(iCampo);

			cCampo = "" + request.getParameter("iCveMunicipio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setICveMunicipio(iCampo);

			cCampo = "" + request.getParameter("cTel");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCTel(cCampo);

			cCampo = "" + request.getParameter("cCorreoE");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vGRLModulo.setCCorreoe(cCampo);

			if (request.getParameter("linterconsulta").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setLinterconsulta(iCampo);

			if (request.getParameter("lVigente").compareTo("Si") == 0)
				cCampo = "1";
			else
				cCampo = "0";
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setLVigente(iCampo);

			/*
			 * if(request.getParameter("lValida").compareTo("Si")==0) cCampo =
			 * "1"; else cCampo = "0";
			 */
			cCampo = request.getParameter("lValida");

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vGRLModulo.setlValida(iCampo);
			
		/////Obtener Request de Dispositivos/// 
			vcDispositivo = dDisp.FindByAll();
			String cWhere="";
			String DispValores = "";
			String Dispositivos = "";
			String DispSentencia = "";
			boolean DispositivoActivo = false;
			try {
				if(vcDispositivo.size() > 0){
					   for(int i=0;i<vcDispositivo.size();i++){
					   		vDispositivo = (TVGRLDispositivo) vcDispositivo.get(i);
					   		boolean existe = false;
					   		
						   ///Verificar que el dispositivo este activo
					   		//System.out.println("Request="+request.getParameter("D"+vDispositivo.getICveDispositivos()+""));
							if (request.getParameter("D"+vDispositivo.getICveDispositivos()+"").compareTo("Si") == 0){
								cCampo = "1";
							}else{
								cCampo = "0";
							}
								
							//System.out.println("op1");
								cWhere = " where "+ 
							   			"iCveUnimed = "+request.getParameter("hdUniMed")+" and "+ 
							   			"iCveModulo = "+request.getParameter("hdCampoClave")+" and "+
							   			"iCveDispositivo = "+vDispositivo.getICveDispositivos()+" ";
								DispositivoActivo = dMDisp.ExisteTrue(cWhere);							
								if (DispositivoActivo){
									//DispValores = DispValores + "Update-"+vDispositivo.getICveDispositivos()+"-"+cCampo+"-|";
									DispSentencia = DispSentencia + "Update-";
									Dispositivos = Dispositivos + ""+vDispositivo.getICveDispositivos()+"-";
									DispValores = DispValores + cCampo+"-";
								}else{
									//DispValores = DispValores + "Insert-"+vDispositivo.getICveDispositivos()+"-"+cCampo+"-|";
									DispSentencia = DispSentencia + "Insert-";
									Dispositivos = Dispositivos + ""+vDispositivo.getICveDispositivos()+"-";
									DispValores = DispValores + cCampo+"-";
								}
						}
				}
			} catch (Exception e) {
				System.out.println("Se produjo un error al tratar de obtener el listado de Dispositivos");
			}
			vGRLModulo.setCDispositivos(Dispositivos);
			vGRLModulo.setCDispSentencia(DispSentencia);
			vGRLModulo.setCDispValor(DispValores);
			//System.out.println(Valores);
		
			

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vGRLModulo;
	}
}