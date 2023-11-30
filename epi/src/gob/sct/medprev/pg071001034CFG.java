package gob.sct.medprev;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para Generales - Catalogo de Mensajeria
 * 
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author <dd>Ing. Andres Esteban Bernal Munoz 01/07/2014
 * 
 */

public class pg071001034CFG extends CFGCatBase2 {
	public pg071001034CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg071001033.jsp";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {

		TDToxMensajeria dToxMensajeria = new TDToxMensajeria();
		try {
			cClave = (String) dToxMensajeria.insert(null, this.getInputs());
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
		TDToxMensajeria dToxMensajeria = new TDToxMensajeria();
		try {
			TVToxMensajeria oldvToxMensajeria = (TVToxMensajeria) dToxMensajeria
					.getOldToxMensajeria(request.getParameter("iCveMensajeria"));
			// System.out.println(oldvToxMensajeria.getCDscMensajeria());
			// System.out.println(oldvToxMensajeria.getSitioOficial());
			/* agrega a la bitacora */
			ExpBitMod mod = new ExpBitMod();
			mod.setOperacion("20");
			mod.setiCveExpediente("0");
			mod.setiNumExamen("0");

			mod.setIpAddress(request.getParameter("hdIpAddress"));
			mod.setMacAddress(request.getParameter("hdMacAddress"));
			mod.setComputerName(request.getParameter("hdComputerName"));
			mod.setiCveUsuarioRealiza(request.getParameter("hdUsuarioActivo"));
			mod.setDescripcion("Mensaje Anterior: "
					+ oldvToxMensajeria.getCDscMensajeria() + ", URL previa: "
					+ oldvToxMensajeria.getSitioOficial());
			int insert = new TDEXPBITMOD().insertServiciosIpMacName(null, mod);// insertados
			//System.out.println(mod.getIpAddress());
			//System.out.println(mod.getiCveUsuarioRealiza());
			cClave = (String) dToxMensajeria.update(null, this.getInputs());

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Fin Metodo GuardarA

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDToxMensajeria dToxMensajeria = new TDToxMensajeria();
		try {

			String cWhere = "";
			if (cCondicion.compareTo("") != 0)
				cWhere = " where " + cCondicion;

			if (cOrden.compareTo("") != 0)
				cWhere += cOrden;
			else
				cWhere += " order by iCveMensajeria ";
			vDespliega = dToxMensajeria.FindByAll();
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo fillPK
	 */
	public void fillPK() {
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		TVToxMensajeria vToxMensajeria = new TVToxMensajeria();
		try {
			cCampo = "" + request.getParameter("iCveMensajeria");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vToxMensajeria.setICveMensajeria(iCampo);
			cCampo = "" + request.getParameter("cDscMensajeria");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vToxMensajeria.setCDscMensajeria(cCampo);

			cCampo = "" + request.getParameter("SitioOficial");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vToxMensajeria.setSitioOficial(cCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vToxMensajeria;
	}
}
