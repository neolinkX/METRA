package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070502020CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	int ivUniMed = 0;
	int ivMdoTransporte = 0;
	int ivEmpresa = 0;
	int ivPais = 0;
	int ivEstado = 0;
	int ivMunicipio = 0;
	public Vector vMdoTrans = new Vector();
	public Vector vPais = new Vector();
	public Vector vEstado = new Vector();
	public Vector vMunicipio = new Vector();

	public pg070502020CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502021.jsp|";
	}

	public void mainBlock() {
		if (request.getParameter("SLSUniMed") != null)
			if (request.getParameter("SLSUniMed").toString()
					.compareToIgnoreCase("") != 0)
				ivUniMed = new Integer(request.getParameter("SLSUniMed"))
						.intValue();
		if (request.getParameter("SLSMdoTransporte") != null)
			if (request.getParameter("SLSMdoTransporte").toString()
					.compareToIgnoreCase("") != 0)
				ivMdoTransporte = new Integer(
						request.getParameter("SLSMdoTransporte")).intValue();
		if (request.getParameter("SLSEmpresas") != null)
			if (request.getParameter("SLSEmpresas").toString()
					.compareToIgnoreCase("") != 0)
				ivEmpresa = new Integer(request.getParameter("SLSEmpresas"))
						.intValue();
		if (request.getParameter("SLSPais") != null)
			if (request.getParameter("SLSPais").toString()
					.compareToIgnoreCase("") != 0)
				ivPais = new Integer(request.getParameter("SLSPais"))
						.intValue();
		if (request.getParameter("SLSEstado") != null)
			if (request.getParameter("SLSEstado").toString()
					.compareToIgnoreCase("") != 0)
				ivEstado = new Integer(request.getParameter("SLSEstado"))
						.intValue();
		if (request.getParameter("SLSMunicipio") != null)
			if (request.getParameter("SLSMunicipio").toString()
					.compareToIgnoreCase("") != 0)
				ivMunicipio = new Integer(request.getParameter("SLSMunicipio"))
						.intValue();
	}

	public void Guardar() {
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		Vector vGuardar = new Vector();
		String cCond = "";

		if (ivMunicipio > 0)
			cCond = "   and CTRDomicilio.iCveMunicipio = " + ivMunicipio;

		// Busqueda de las Empresas que se van a procesar.
		try {
			vGuardar = DGRLEmpresas
					.FindByCustomWhere(" join CTRDomicilio "
							+ "    on CTRDomicilio.iCveEmpresa   = GRLEmpresas.iCveEmpresa "
							+ "   and CTRDomicilio.iCvePais      = " + ivPais
							+ "   and CTRDomicilio.iCveEstado    = " + ivEstado
							+ cCond + "   and CTRDomicilio.lActivo       = 1 ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vGuardar.isEmpty()) {
			for (int i = 0; i < vGuardar.size(); i++) {
				TVGRLEmpresas VGRLEmpresas = new TVGRLEmpresas();
				VGRLEmpresas = (TVGRLEmpresas) vGuardar.get(i);

				if (request.getParameter("TBXSel-"
						+ VGRLEmpresas.getICveEmpresa()) != null) {

					VGRLEmpresas.setICveUniMed(ivUniMed);
					// Escritura de la Unidad Médica a la Empresa.
					try {
						DGRLEmpresas.updUniMed(null, VGRLEmpresas);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {
					// Empresas que tenian asignada la Unidad Médica, pero que
					// ya no.
					if (request.getParameter("TBXHid-"
							+ VGRLEmpresas.getICveEmpresa()) != null) {
						VGRLEmpresas.setICveUniMed(0);
						// Escritura de la Unidad Médica a la Empresa.
						try {
							DGRLEmpresas.updUniMed(null, VGRLEmpresas);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void fillVector() {

		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDPais DPais = new TDPais();
		TDEntidadFed DEntidadFed = new TDEntidadFed();
		TDMunicipio DMunicipio = new TDMunicipio();
		String cCond1 = "";
		String cCond2 = "";
		String cCond3 = "";

		// Obtención del Modo de Transporte
		try {
			TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
			VGRLMdoTrans.setICveMdoTrans(0);
			VGRLMdoTrans.setCDscMdoTrans("Todos");
			vMdoTrans = DGRLMdoTrans.findByAll("");
			vMdoTrans.addElement(VGRLMdoTrans);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención de los Países.
		try {
			TVPais VPais = new TVPais();
			VPais.setICvePais(0);
			VPais.setCNombre("Seleccione...");
			vPais = DPais.FindByAll(" order by cNombre ");
			vPais.addElement(VPais);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención de las Entidades Federativas.
		try {
			if (new Integer(ivPais).toString().compareToIgnoreCase("") != 0
					|| new Integer(ivPais).toString().compareToIgnoreCase("0") != 0)
				vEstado = DEntidadFed.FindByAll(" where iCvePais = "
						+ new Integer(ivPais).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención de los Municipios.
		try {
			if (new Integer(ivPais).toString().compareToIgnoreCase("") != 0
					&& new Integer(ivEstado).toString().compareToIgnoreCase("") != 0)
				vMunicipio = DMunicipio.FindByAll(
						new Integer(ivPais).toString(),
						new Integer(ivEstado).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		cPaginas = "pg070502020.jsp|";

		// Empresas Asignadas unicamente.
		if (ivEmpresa == 0) {
			if (ivUniMed != 0)
				cCond1 = " iCveUniMed = " + ivUniMed;
		}

		// Empresas Disponibles unicamente.
		if (ivEmpresa == 1) {
			cCond1 = " iCveUniMed = 0 ";
		}

		// Empresas Asignadas y Disponibles.
		if (ivEmpresa == 2) {
			cCond1 = " ( iCveUniMed = 0  or iCveUniMed = " + ivUniMed + " ) ";
		}

		if (ivMdoTransporte != 0) {
			cCond2 = " iCveMdoTrans = " + ivMdoTransporte;
		}

		if (cCondicion.compareToIgnoreCase("") != 0) {
			cCondicion = " where " + cCondicion;
			if (cCond1.compareToIgnoreCase("") != 0) {
				cCondicion = cCondicion + " and " + cCond1;
				if (cCond2.compareToIgnoreCase("") != 0)
					cCondicion = cCondicion + " and " + cCond2;
			} else {
				if (cCond2.compareToIgnoreCase("") != 0)
					cCondicion = cCondicion + " and " + cCond2;
			}
		} else {
			if (cCond1.compareToIgnoreCase("") != 0) {
				cCondicion = " where " + cCond1;
				if (cCond2.compareToIgnoreCase("") != 0)
					cCondicion = cCondicion + " and " + cCond2;
			} else {
				if (cCond2.compareToIgnoreCase("") != 0)
					cCondicion = " where " + cCond2;
			}
		}

		if (cOrden.compareTo("") != 0)
			cCondicion = cCondicion + cOrden;
		else
			cCondicion = cCondicion + " order by iCveEmpresa ";

		if (ivMunicipio > 0)
			cCond3 = "   and CTRDomicilio.iCveMunicipio = " + ivMunicipio;
		else
			cCond3 = "";

		try {
			if (ivUniMed > 0)
				vDespliega = DGRLEmpresas
						.FindByCustomWhere(" join CTRDomicilio "
								+ "    on CTRDomicilio.iCveEmpresa   = GRLEmpresas.iCveEmpresa "
								+ "   and CTRDomicilio.iCvePais      = "
								+ ivPais
								+ "   and CTRDomicilio.iCveEstado    = "
								+ ivEstado + cCond3
								+ "   and CTRDomicilio.lActivo       = 1 "
								+ cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}