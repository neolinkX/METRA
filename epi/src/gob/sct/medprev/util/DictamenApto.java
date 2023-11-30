/*
 * Created on 18/10/2006 
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gob.sct.medprev.util;

import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import java.util.StringTokenizer;

/**
 * @author Eigen
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DictamenApto extends Dictamen {

	/**
	 * 
	 */
	public DictamenApto() {
		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/DictamenApto.jasper");
		} catch (JRException e) {
			e.printStackTrace();
		}
		parametros = new HashMap();
	}

	/**
	 * @param categoria
	 *            The categoria to set.
	 */
	public void setCategoria(String categoria) {
		if (categoria == null) {
			categoria = "";
		}
		parametros.put("categoria", categoria);
	}

	/**
	 * @param curp
	 *            The curp to set.
	 */
	public void setCurp(String curp) {
		if (curp == null) {
			curp = "";
		}
		parametros.put("curp", curp);
	}

	/**
	 * @param Nombre
	 *            The Nombre to set.
	 */
	public void setPNombre(String PNombre) {

		if (PNombre == null) {
			PNombre = "";
		}
		parametros.put("PNombre", PNombre);
	}

	/**
	 * @param fechaDictamen
	 *            The fechaDictamen to set.
	 */
	public void setFechaDictamen(String fechaDictamen) {
		if (fechaDictamen == null) {
			fechaDictamen = "";
		}
		parametros.put("fechaDictamen", fechaDictamen);
	}

	/**
	 * @param fechaExamen
	 *            The fechaExamen to set.
	 */
	public void setFechaExamen(String fechaExamen) {

		if (fechaExamen == null) {
			fechaExamen = "";
		} else {
			String fechaExamen2 = fechaExamen + "/";
			String dia;
			String mes;
			String ano;
			StringTokenizer solDatos = new StringTokenizer(fechaExamen2, "/");
			dia = solDatos.nextToken();
			mes = solDatos.nextToken();
			ano = solDatos.nextToken();
			fechaExamen = "" + dia + "                  " + mes
					+ "               " + ano;
			parametros.put("fechaExamen", fechaExamen);
		}
	}

	/**
	 * @param genero
	 *            The genero to set.
	 */
	public void setGenero(String genero) {
		if (genero == null) {
			genero = "";
		}
		parametros.put("genero", genero);
	}

	/**
	 * @param descUnidadMedica
	 *            The lugarExamen1 to set.
	 */
	public void setDescUnidadMedica(String descUnidadMedica) {
		if (descUnidadMedica == null) {
			descUnidadMedica = "";
		}
		parametros.put("descUnidadMedica", descUnidadMedica);
	}

	/**
	 * @param lugarUnidadMedica
	 *            The lugarExamen2 to set.
	 */
	public void setLugarUnidadMedica(String lugarUnidadMedica) {
		if (lugarUnidadMedica == null) {
			lugarUnidadMedica = "";
		}
		parametros.put("lugarUnidadMedica", lugarUnidadMedica);
	}

	/**
	 * @param modoTransporte
	 *            The modoTransporte to set.
	 */
	public void setModoTransporte(String modoTransporte) {
		if (modoTransporte == null) {
			modoTransporte = "";
		}
		parametros.put("modoTransporte", modoTransporte);
	}

	public void setNota(String nota) {
		if (nota == null)
			nota = "";
		parametros.put("nota", nota);
	}

	/**
	 * @param numAntExpDGPMPT
	 *            The numAntExpDGPMPT to set.
	 */
	public void setNumAntExpDGPMPT(String numAntExpDGPMPT) {
		if (numAntExpDGPMPT == null) {
			numAntExpDGPMPT = "";
		}
		parametros.put("numAntExpDGPMPT", numAntExpDGPMPT);
	}

	/**
	 * @param numExpDGPMPT
	 *            The numExpDGPMPT to set.
	 */
	public void setNumExpDGPMPT(String numExpDGPMPT) {
		if (numExpDGPMPT == null) {
			numExpDGPMPT = "";
		}
		parametros.put("numExpDGPMPT", numExpDGPMPT);
	}

	/**
	 * @param rfc
	 *            The rfc to set.
	 */
	public void setRfc(String rfc) {
		if (rfc == null) {
			rfc = "";
		}
		parametros.put("rfc", rfc);
	}

	/**
	 * @param vencimiento
	 *            The vencimiento to set.
	 */
	public void setVencimiento(String vencimiento) {
		if (vencimiento == null) {
			vencimiento = "";
		}
		parametros.put("vencimiento", vencimiento);
	}

	/**
	 * @param aereos
	 *            The aereos to set.
	 */
	public void setAereos(String aereos) {
		if (aereos == null) {
			aereos = "";
		}
		parametros.put("aereos", aereos);
	}

	/**
	 * @param contacto
	 *            The contacto to set.
	 */
	public void setContacto(String contacto) {
		if (contacto == null) {
			contacto = "";
		}
		parametros.put("contacto", contacto);
	}

	/**
	 * @param diabetes
	 *            The diabetes to set.
	 */
	public void setDiabetes(String diabetes) {
		if (diabetes == null) {
			diabetes = "";
		}
		parametros.put("diabetes", diabetes);
	}

	/**
	 * @param grupoSanguineo
	 *            The grupoSanguineo to set.
	 */
	public void setGrupoSanguineo(String grupoSanguineo) {
		if (grupoSanguineo == null) {
			grupoSanguineo = "";
		}
		parametros.put("grupoSanguineo", grupoSanguineo);
	}

	/**
	 * @param hipertension
	 *            The hipertension to set.
	 */
	public void setHipertension(String hipertension) {
		if (hipertension == null) {
			hipertension = "";
		}
		parametros.put("hipertension", hipertension);
	}

	/**
	 * @param rh
	 *            The rh to set.
	 */
	public void setRh(String rh) {
		if (rh == null) {
			rh = "";
		}
		parametros.put("rh", rh);
	}

	/**
	 * @param usaLentes
	 *            The usaLentes to set.
	 */
	public void setUsaLentes(String usaLentes) {
		if (usaLentes == null) {
			usaLentes = "";
		}
		parametros.put("usaLentes", usaLentes);
	}

	/**
	 * @param descMotivo
	 *            The descMotivo to set.
	 */
	public void setDescMotivo(String descMotivo) {
		if (descMotivo == null) {
			descMotivo = "";
		}
		parametros.put("descMotivo", descMotivo);
	}

	public void setImprimirCategoria(boolean imprimirCategoria) {
		parametros.put("imprimirCategoria", new Boolean(imprimirCategoria));
	}

	public void setApto1(String Apto1) {
		if (Apto1 == null)
			Apto1 = "";
		parametros.put("Apto1", Apto1);
	}

	public void setCedula(String Cedula) {
		if (Cedula == null)
			Cedula = "";
		parametros.put("Cedula", Cedula);
	}

	public void setDoc(String Doc) {

		if (Doc == null) {
			Doc = "";
		}
		parametros.put("Doc", Doc);
	}

	public void setCiudadOrigen(String CiudadOrigen) {
		if (CiudadOrigen == null)
			CiudadOrigen = "";
		parametros.put("CiudadOrigen", CiudadOrigen);
	}

	public void setCiudadDestino(String CiudadDestino) {
		if (CiudadDestino == null)
			CiudadDestino = "";
		parametros.put("CiudadDestino", CiudadDestino);
	}

	public void setTensionArterial(String TensionArterial) {
		if (TensionArterial == null) {
			TensionArterial = "";
		} else {
			String TensionArterial2 = TensionArterial + ".";
			String sistolica;
			String ceros;
			String diastolica;
			StringTokenizer TensionArt = new StringTokenizer(TensionArterial2,
					".");
			sistolica = TensionArt.nextToken();
			ceros = TensionArt.nextToken();
			diastolica = TensionArt.nextToken();
			TensionArterial = "" + sistolica + "                 " + diastolica
					+ "   ";
			parametros.put("TensionArterial", TensionArterial);
		}

	}

	public void setHM(String HM) {
		if (HM == null) {
			HM = "";
		} else {
			String HM2 = HM + ":";
			String hora;
			String minutos;
			StringTokenizer HMT = new StringTokenizer(HM2, ":");
			hora = HMT.nextToken();
			minutos = HMT.nextToken();
			HM = "" + hora + "                  " + minutos + "";
		}
		parametros.put("HM", HM);
	}

	public void setClaveEmpresa(String ClaveEmpresa) {
		if (ClaveEmpresa == null)
			ClaveEmpresa = "";
		parametros.put("ClaveEmpresa", ClaveEmpresa);
	}

	public void setNombreEmpresa(String NombreEmpresa) {
		if (NombreEmpresa == null)
			NombreEmpresa = "";
		parametros.put("NombreEmpresa", NombreEmpresa);
	}

	public void setNumEcoEmpresa(String NumEcoEmpresa) {
		if (NumEcoEmpresa == null)
			NumEcoEmpresa = "";
		parametros.put("NumEcoEmpresa", NumEcoEmpresa);
	}

	public void setFrecuencia(String Frecuencia) {
		if (Frecuencia == null) {
			Frecuencia = "";
		} else {
			String Frecuencia2 = Frecuencia + ".";
			String FrecuenciaR;
			StringTokenizer FrecuenciaArt = new StringTokenizer(Frecuencia2,
					".");
			FrecuenciaR = FrecuenciaArt.nextToken();
			Frecuencia = "" + FrecuenciaR + "";
			parametros.put("Frecuencia", Frecuencia);
		}
	}

	public void setPulso(String Pulso) {
		if (Pulso == null)
			Pulso = "";
		parametros.put("Pulso", Pulso);
	}

	public void setClaveUniMed(String ClaveUniMed) {
		if (ClaveUniMed == null)
			ClaveUniMed = "";
		parametros.put("ClaveUniMed", ClaveUniMed);
	}

	public void setClaveModulo(String ClaveModulo) {
		if (ClaveModulo.equals("") || ClaveModulo.equals(" ")
				|| ClaveModulo == null)
			ClaveModulo = "";
		parametros.put("ClaveModulo", ClaveModulo);
	}

	public void setAlcoholAliento(String AlcoholAliento) {
		if (AlcoholAliento == null)
			AlcoholAliento = "";
		parametros.put("AlcoholAliento", AlcoholAliento);
	}

	public void setReflejoFotomotor(String ReflejoFotomotor) {
		if (ReflejoFotomotor == null)
			ReflejoFotomotor = "";
		parametros.put("ReflejoFotomotor", ReflejoFotomotor);
	}

	public void setRomberg(String Romberg) {
		if (Romberg == null)
			Romberg = "";
		parametros.put("Romberg", Romberg);
	}

	public void setOsteotendinosos(String Osteotendinosos) {
		if (Osteotendinosos == null) {
			Osteotendinosos = "";
		} else {
			String Osteotendinosos2 = Osteotendinosos + ".";
			String OsteotendinososR;
			StringTokenizer OsteotendinososArt = new StringTokenizer(
					Osteotendinosos2, ".");
			OsteotendinososR = OsteotendinososArt.nextToken();
			Osteotendinosos = "" + OsteotendinososR + "";
			parametros.put("Osteotendinosos", Osteotendinosos);
		}
	}

	public void setDDiagnostico(String DDiagnostico) {
		if (DDiagnostico == null)
			DDiagnostico = "";
		parametros.put("DDiagnostico", DDiagnostico);
	}

	public void setClaveCIE(String ClaveCIE) {
		if (ClaveCIE == null)
			ClaveCIE = "";
		parametros.put("ClaveCIE", ClaveCIE);
	}

	public void setPCarretero(String PCarretero) {
		if (PCarretero == null)
			PCarretero = "";
		parametros.put("PCarretero", PCarretero);
	}

	public void setPAereo(String PAereo) {
		if (PAereo == null)
			PAereo = "";
		parametros.put("PAereo", PAereo);
	}

	public void setPMaritimo(String PMaritimo) {
		if (PMaritimo == null)
			PMaritimo = "";
		parametros.put("PMaritimo", PMaritimo);
	}

	public void setPFerroviario(String PFerroviario) {
		if (PFerroviario == null)
			PFerroviario = "";
		parametros.put("PFerroviario", PFerroviario);
	}

	public void setAptoPro(String AptoPro) {
		if (AptoPro == null)
			AptoPro = "";
		parametros.put("AptoPro", AptoPro);
	}

	public void setNumFolio(String NumFolio) {
		if (NumFolio == null)
			NumFolio = "";
		parametros.put("NumFolio", NumFolio);
	}

	/**
	 * @param fechaDictamen
	 *            The RutaNAS to set.
	 */
	public void setRutaNAS(String RutaNAS) {
		if (RutaNAS == null) {
			RutaNAS = "";
		}
		parametros.put("RutaNAS", RutaNAS);
	}

	/**
	 * @param Bloquear
	 *            constancia.
	 */
	public void setBloquear(String Bloquear) {
		if (Bloquear == null) {
			Bloquear = "";
		}
		parametros.put("Bloquear", Bloquear);
	}

	/**
	 * @param Bloquear
	 *            constancia.
	 */
	public void setNumExamen(String NumExamen) {
		if (NumExamen == null) {
			NumExamen = "";
		}
		parametros.put("NumExamen", NumExamen);
	}

}
