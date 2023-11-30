package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070502031CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	int ivEmpresa = 0;
	int ivPlantilla = 0;
	int ivCveUsuario = 0;
	public Vector vEmpresas = new Vector();
	public Vector vMdoTrans = new Vector();
	public Vector vPeriodPla = new Vector();
	public Vector vPersonalActivo = new Vector();

	public pg070502031CFG() {
		vParametros = new TParametro("07");
		cPaginas = "pg070502032.jsp|pg070502030.jsp|";
	}

	public void mainBlock() {
		if (request.getParameter("hdIni") != null)
			if (request.getParameter("hdIni").toString()
					.compareToIgnoreCase("") != 0)
				ivEmpresa = new Integer(request.getParameter("hdIni"))
						.intValue();

		if (request.getParameter("iCveUsuario") != null)
			if (request.getParameter("iCveUsuario").toString()
					.compareToIgnoreCase("") != 0)
				ivCveUsuario = new Integer(request.getParameter("iCveUsuario"))
						.intValue();

		if (cAccion.compareToIgnoreCase("Copiar") == 0) {
			if (request.getParameter("hdIni2") != null)
				if (request.getParameter("hdIni2").toString()
						.compareToIgnoreCase("") != 0)
					ivPlantilla = new Integer(request.getParameter("hdIni2"))
							.intValue();

			this.Copiar();
		}
	}

	public void Copiar() {
		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();
		TVCTRPlantilla VCTRPlantillaCopia = new TVCTRPlantilla();
		TDCTRPersonal DCTRPersonal = new TDCTRPersonal();
		TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
		TVCTRPersonal VCTRPersonalCopia = new TVCTRPersonal();
		TFechas Fecha = new TFechas();
		Vector vPlantilla = new Vector();
		Vector vPersonal = new Vector();
		int ivPlantillaNueva = 0;
		// int ivCveUsuario = 0;
		int ivUniMed = 0;
		int ivEtapa = Integer.parseInt(vParametros
				.getPropEspecifica("CTREtapaInicial"));
		int ivSolictante = Integer.parseInt(vParametros
				.getPropEspecifica("CTRSolicitanteIni"));
		int ivTipoEntrega = Integer.parseInt(vParametros
				.getPropEspecifica("CTRTipoEntregaIni"));

		try {
			vPlantilla = DCTRPlantilla
					.FindByAll(" where CTRPlantilla.iCveEmpresa   = "
							+ ivEmpresa + "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vPlantilla.isEmpty())
			ivPlantillaNueva = vPlantilla.size() + 1;
		else
			ivPlantillaNueva = 1;
		vPlantilla = null;

		// Obtención del Encabezado de la Plantilla a Copiar.
		try {
			vPlantilla = DCTRPlantilla
					.FindByAll(" where CTRPlantilla.iCveEmpresa   = "
							+ ivEmpresa
							+ "   and CTRPlantilla.iCvePlantilla = "
							+ ivPlantilla + "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Obtención de los Movimientos del Personal.
		try {
			vPersonal = DCTRPersonal
					.FindByAll(
							" where CTRPersonal.iCveEmpresa   = " + ivEmpresa
									+ "   and CTRPersonal.iCvePlantilla = "
									+ ivPlantilla,
							" order by CTRPersonal.iCveEmpresa, CTRPersonal.iCvePlantilla, CTRPersonal.iNumPersonal ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Inserción del Encabezado de la Plantilla.
		if (!vPlantilla.isEmpty()) {
			for (int i = 0; i < vPlantilla.size(); i++) {
				VCTRPlantilla = (TVCTRPlantilla) vPlantilla.get(i);
				VCTRPlantillaCopia.setICveEmpresa(VCTRPlantilla
						.getICveEmpresa());
				VCTRPlantillaCopia.setiCvePlantilla(ivPlantillaNueva);
				VCTRPlantillaCopia.setiCveTpoEntrega(VCTRPlantilla
						.getiCveTpoEntrega());
				VCTRPlantillaCopia.setlProgramada(VCTRPlantilla
						.getlProgramada());
				VCTRPlantillaCopia.setiAnio(VCTRPlantilla.getiAnio());
				VCTRPlantillaCopia.setiCvePeriodPla(VCTRPlantilla
						.getiCvePeriodPla());
				VCTRPlantillaCopia.setdtSolicitud(Fecha.TodaySQL());
				VCTRPlantillaCopia.setdtVencimiento(VCTRPlantilla
						.getdtVencimiento());
				VCTRPlantillaCopia.setiCveUsuSolicita(ivCveUsuario);
				VCTRPlantillaCopia.setiCveUMSolicita(ivUniMed);

				// Agregando la Plantilla a Copiar.
				try {
					DCTRPlantilla.insert(null, VCTRPlantillaCopia);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				// Datos del Seguimiento.
				TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
				TDCTRSeguimiento DCTRSeguimiento = new TDCTRSeguimiento();
				VCTRSeguimiento.setiCveEmpresa(VCTRPlantillaCopia
						.getICveEmpresa());
				VCTRSeguimiento.setiCvePlantilla(ivPlantillaNueva);
				VCTRSeguimiento.setiCveMovimiento(1);
				VCTRSeguimiento.setiCveProceso(5);
				VCTRSeguimiento.setiCveEtapa(ivEtapa);
				VCTRSeguimiento.setdtSolicitud(Fecha.TodaySQL());
				VCTRSeguimiento.setiCveSolictante(ivSolictante);
				VCTRSeguimiento.setiCveUsuSolicita(ivCveUsuario);
				VCTRSeguimiento.setiCveUsuReg(ivCveUsuario);
				VCTRSeguimiento
						.setcObservacion("Por el Copiado de la Plantilla");

				// Registro del Seguimiento.
				try {
					DCTRSeguimiento.insert(null, VCTRSeguimiento);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (!vPersonal.isEmpty()) {
					for (int j = 0; j < vPersonal.size(); j++) {
						// Datos del Personal para la Nueva Plantilla.
						VCTRPersonal = (TVCTRPersonal) vPersonal.get(j);
						VCTRPersonalCopia.setICveEmpresa(VCTRPersonal
								.getICveEmpresa());
						VCTRPersonalCopia.setICvePlantilla(ivPlantillaNueva);
						VCTRPersonalCopia.setINumPersonal(VCTRPersonal
								.getINumPersonal());
						VCTRPersonalCopia.setICveExpediente(VCTRPersonal
								.getICveExpediente());
						VCTRPersonalCopia.setCNombre(VCTRPersonal.getCNombre());
						VCTRPersonalCopia.setCApPaterno(VCTRPersonal
								.getCApPaterno());
						VCTRPersonalCopia.setCApMaterno(VCTRPersonal
								.getCApMaterno());
						VCTRPersonalCopia.setCRFC(VCTRPersonal.getCRFC());
						VCTRPersonalCopia.setCCURP(VCTRPersonal.getCCURP());
						VCTRPersonalCopia.setDtNacimiento(VCTRPersonal
								.getDtNacimiento());
						VCTRPersonalCopia.setICvePaisNac(VCTRPersonal
								.getICvePaisNac());
						VCTRPersonalCopia.setICveEstadoNac(VCTRPersonal
								.getICveEstadoNac());
						VCTRPersonalCopia.setICveMdoTrans(VCTRPersonal
								.getiCveMdoTrans());
						VCTRPersonalCopia.setICvePuesto(VCTRPersonal
								.getICvePuesto());
						VCTRPersonalCopia.setCLicencia(VCTRPersonal
								.getCLicencia());
						VCTRPersonalCopia.setCCalle(VCTRPersonal.getCCalle());
						VCTRPersonalCopia.setCNumExt(VCTRPersonal.getCNumExt());
						VCTRPersonalCopia.setCNumInt(VCTRPersonal.getCNumInt());
						VCTRPersonalCopia.setCColonia(VCTRPersonal
								.getCColonia());
						VCTRPersonalCopia.setICP(VCTRPersonal.getICP());
						VCTRPersonalCopia.setCCiudad(VCTRPersonal.getCCiudad());
						VCTRPersonalCopia.setICvePais(VCTRPersonal
								.getICvePais());
						VCTRPersonalCopia.setICveEstado(VCTRPersonal
								.getICveEstado());
						VCTRPersonalCopia.setICveMunicipio(VCTRPersonal
								.getICveMunicipio());
						VCTRPersonalCopia.setCTel(VCTRPersonal.getCTel());
						VCTRPersonalCopia.setDtLicVencimiento(VCTRPersonal
								.getDtLicVencimiento());
						VCTRPersonalCopia.setlActivo(VCTRPersonal.getlActivo());
						VCTRPersonalCopia.setlBaseEventual(VCTRPersonal
								.getlBaseEventual());

						// Registro del Personal en la Nueva Plantilla.
						try {
							DCTRPersonal.insert(null, VCTRPersonalCopia);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void fillVector() {
		TDCTRPlantilla DCTRPlantilla = new TDCTRPlantilla();
		TDGRLEmpresas DGRLEmpresas = new TDGRLEmpresas();
		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
		TDCTRPersonal DCTRPersonal = new TDCTRPersonal();
		StringTokenizer stCondicion = new StringTokenizer(cCondicion);
		String tCondicion = "";
		boolean lEncontro = false;
		StringTokenizer stOrden = new StringTokenizer(cOrden);
		String tOrden = "";
		boolean lEncontroOrden = false;

		try {
			vEmpresas = DGRLEmpresas.FindByAll(" where iCveEmpresa = "
					+ ivEmpresa);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vMdoTrans = DGRLMdoTrans.findByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			vPeriodPla = DCTRPeriodPla.FindByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		while (stCondicion.hasMoreElements()) {
			tCondicion = stCondicion.nextToken();
			if (tCondicion.compareToIgnoreCase("GRLEmpresas.cIDDGPMPT") == 0
					&& !lEncontro) {
				lEncontro = true;
			}
			if (tCondicion.compareToIgnoreCase("GRLEmpresas.cDenominacion") == 0
					&& !lEncontro) {
				lEncontro = true;
			}
		}
		if (lEncontro)
			cCondicion = "";

		if (cCondicion.compareToIgnoreCase("") != 0)
			cCondicion = " and " + cCondicion;

		while (stOrden.hasMoreElements()) {
			tOrden = stOrden.nextToken();
			if (tOrden.compareToIgnoreCase("cIDDGPMPT") == 0 && !lEncontroOrden) {
				lEncontroOrden = true;
			}
			if (tOrden.compareToIgnoreCase("cDenominacion") == 0
					&& !lEncontroOrden) {
				lEncontroOrden = true;
			}
		}
		if (lEncontroOrden)
			cOrden = "";

		if (cOrden.compareTo("") != 0) {
			if (cOrden.compareToIgnoreCase(" order by cIDDGPMPT") == 0)
				cCondicion = cCondicion + " order by iCvePlantilla ";
			else
				cCondicion = cCondicion + cOrden;
		} else
			cCondicion = cCondicion + " order by iCvePlantilla ";

		// Vector del No. de Personal Activo por Plantilla de la Empresa.
		try {
			vPersonalActivo = DCTRPersonal
					.FindCountPersonal(" where CTRPersonal.iCveEmpresa =    "
							+ ivEmpresa
							+ "   and CTRPersonal.lActivo     = 1  "
							+ " group by CTRPersonal.iCveEmpresa,  "
							+ "          CTRPersonal.iCvePlantilla ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// Vector del Listado.
		try {
			vDespliega = DCTRPlantilla
					.FindByAll(" where CTRPlantilla.iCveEmpresa = " + ivEmpresa
							+ cCondicion);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "Hidden";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0)
				NavStatus = "FirstRecord";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}