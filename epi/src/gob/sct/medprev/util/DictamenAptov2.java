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
public class DictamenAptov2 extends Dictamen {

	/**     
	 * 
	 */
	public DictamenAptov2() {
		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/DictamenAptoEMOv2.jasper");
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
			fechaExamen = "" + dia + " / " + mes + " / " + ano;
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
		if (usaLentes.equals("0")) {
			usaLentes = "-           X";
		}
		if (usaLentes.equals("1")) {
			usaLentes = "X           -";
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
			TensionArterial = "" + sistolica + "             " + diastolica
					+ "   ";
			parametros.put("TensionArterial", TensionArterial);
		}

	}

	public void setHM(String HM) {
		if (HM == null) {
			HM = "";
		}
		/*
		 * else{ String HM2 = HM + ":"; String hora; String minutos;
		 * StringTokenizer HMT = new StringTokenizer(HM2, ":"); hora =
		 * HMT.nextToken(); minutos = HMT.nextToken(); HM = "" + hora + "  " +
		 * minutos + "" ; }
		 */
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

	public void setGlucometria(String Glucometria) {
		if (Glucometria == null) {
			Glucometria = "";
		} else {
			String Glucometria2 = Glucometria + ".";
			String Gluco;
			StringTokenizer GlucoArt = new StringTokenizer(Glucometria2, ".");
			Gluco = GlucoArt.nextToken();
			Glucometria = "" + Gluco + "";
			parametros.put("Glucometria", Glucometria);
		}
	}

	public void setAlcoholAliento(String AlcoholAliento) {
		if (AlcoholAliento == null) {
			AlcoholAliento = "";
		}
		if (AlcoholAliento.equals("Positivo")) {
			AlcoholAliento = "X                -";
		}
		if (AlcoholAliento.equals("Negativo")) {
			AlcoholAliento = "-                X";
		}
		parametros.put("AlcoholAliento", AlcoholAliento);
	}

	public void setColorPielMucosa(String ColorPielMucosa) {
		if (ColorPielMucosa == null)
			ColorPielMucosa = "";
		if (ColorPielMucosa.equals("1.0000")) {
			ColorPielMucosa = "X                  -                 -                 -";
		}
		if (ColorPielMucosa.equals("2.0000")) {
			ColorPielMucosa = "-                  X                 -                 -";
		}
		if (ColorPielMucosa.equals("3.0000")) {
			ColorPielMucosa = "-                  -                 X                 -";
		}
		if (ColorPielMucosa.equals("4.0000")) {
			ColorPielMucosa = "-                  -                 -                 X";
		}
		parametros.put("ColorPielMucosa", ColorPielMucosa);
	}

	public void setEstadoHidratacion(String EstadoHidratacion) {
		if (EstadoHidratacion == null)
			EstadoHidratacion = "";
		if (EstadoHidratacion.equals("1.0000")) {
			EstadoHidratacion = "X                              -    ";
		}
		if (EstadoHidratacion.equals("2.0000")) {
			EstadoHidratacion = "-                              X    ";
		}
		parametros.put("EstadoHidratacion", EstadoHidratacion);
	}

	public void setEstadoAlerta(String EstadoAlerta) {
		if (EstadoAlerta == null)
			EstadoAlerta = "";
		if (EstadoAlerta.equals("0")) {
			EstadoAlerta = "-               X   ";
		}
		if (EstadoAlerta.equals("1")) {
			EstadoAlerta = "X               -   ";
		}
		parametros.put("EstadoAlerta", EstadoAlerta);
	}

	public void setMarcha(String Marcha) {
		if (Marcha == null)
			Marcha = "";
		if (Marcha.equals("0")) {
			Marcha = " -              X   ";
		}
		if (Marcha.equals("1")) {
			Marcha = " X              -   ";
		}
		parametros.put("Marcha", Marcha);
	}

	public void setObservaciones(String Observaciones) {
		if (Observaciones == null)
			Observaciones = "";
		parametros.put("Observaciones", Observaciones);
	}

	public void setPruebaTox(String PruebaTox) {
		if (PruebaTox == null)
			PruebaTox = "";
		if (PruebaTox.equals("1")) {
			PruebaTox = "X              -";
		}
		if (PruebaTox.equals("0")) {
			PruebaTox = "-              X";
		}
		parametros.put("PruebaTox", PruebaTox);
	}

	public void setLenguaje(String Lenguaje) {
		if (Lenguaje == null)
			Lenguaje = "";
		if (Lenguaje.equals("0")) {
			Lenguaje = " -              X   ";
		}
		if (Lenguaje.equals("1")) {
			Lenguaje = " X              -   ";
		}
		parametros.put("Lenguaje", Lenguaje);
	}

	public void setOrientacion(String Orientacion) {
		if (Orientacion == null)
			Orientacion = "";
		if (Orientacion.equals("0")) {
			Orientacion = "-                 X         ";
		}
		if (Orientacion.equals("1")) {
			Orientacion = "X                 -         ";
		}
		parametros.put("Orientacion", Orientacion);
	}

	public void setReflejoFotomotor(String ReflejoFotomotor) {
		if (ReflejoFotomotor == null)
			ReflejoFotomotor = "";
		if (ReflejoFotomotor.equals("Positivo")) {
			ReflejoFotomotor = "X           -";
		}
		if (ReflejoFotomotor.equals("Negativo")) {
			ReflejoFotomotor = "-           X";
		}
		parametros.put("ReflejoFotomotor", ReflejoFotomotor);
	}

	public void setRomberg(String Romberg) {
		if (Romberg == null)
			Romberg = "";
		if (Romberg.equals("Negativo")) {
			Romberg = "-              X  ";
		}
		if (Romberg.equals("Positivo")) {
			Romberg = "X              -  ";
		}
		parametros.put("Romberg", Romberg);
	}

	public void setPupilas(String Pupilas) {
		if (Pupilas == null)
			Pupilas = "";
		if (Pupilas.equals("1.0000")) {
			Pupilas = "X            -              -            -              -                 -    ";
		}
		if (Pupilas.equals("2.0000")) {
			Pupilas = "-            X              -            -              -                 -    ";
		}
		if (Pupilas.equals("3.0000")) {
			Pupilas = "-            -              X            -              -                 -    ";
		}
		if (Pupilas.equals("4.0000")) {
			Pupilas = "-            -              -            X              -                 -    ";
		}
		if (Pupilas.equals("5.0000")) {
			Pupilas = "-            -              -            -              X                 -    ";
		}
		if (Pupilas.equals("6.0000")) {
			Pupilas = "-            -              -            -              -                 X    ";
		}
		parametros.put("Pupilas", Pupilas);
	}

	public void setIPN(String IPN) {
		if (IPN == null)
			IPN = "";
		if (IPN.equals("1.0000")) {
			IPN = "X           -";
		}
		if (IPN.equals("2.0000")) {
			IPN = "-           X";
		}
		parametros.put("IPN", IPN);
	}

	public void setTipoLente(String TipoLente) {
		if (TipoLente == null)
			TipoLente = "";
		if (TipoLente.equals("1.0000")) {
			TipoLente = "X                   -";
		}
		if (TipoLente.equals("2.0000")) {
			TipoLente = "-                   X";
		}
		parametros.put("TipoLente", TipoLente);
	}

	public void setEstadoO(String EstadoO) {
		if (EstadoO == null)
			EstadoO = "";
		parametros.put("EstadoO", EstadoO);
	}

	public void setEstadoD(String EstadoD) {
		if (EstadoD == null)
			EstadoD = "";
		parametros.put("EstadoD", EstadoD);
	}

	public void setROD(String ROD) {
		if (ROD == null)
			ROD = "";
		if (ROD.equals("1.0000")) {
			ROD = "  X                 -                  -     ";
		}
		if (ROD.equals("2.0000")) {
			ROD = "  -                 X                  -     ";
		}
		if (ROD.equals("3.0000")) {
			ROD = "  -                 -                  X     ";
		}
		parametros.put("ROD", ROD);
	}

	public void setROI(String ROI) {
		if (ROI == null)
			ROI = "";
		if (ROI.equals("1.0000")) {
			ROI = "X               -                 -     ";
		}
		if (ROI.equals("2.0000")) {
			ROI = "-               X                 -     ";
		}
		if (ROI.equals("3.0000")) {
			ROI = "-               -                 X     ";
		}
		parametros.put("ROI", ROI);
	}

	public void setHorasLab(String HorasLab) {
		if (HorasLab == null)
			HorasLab = "";
		if (HorasLab.equals("1")) {
			HorasLab = "X          -   ";
		}
		if (HorasLab.equals("0")) {
			HorasLab = "-          X   ";
		}
		parametros.put("HorasLab", HorasLab);
	}

	public void setHorasLab24(String HorasLab24) {
		int HorasL24 = 0;
		if (HorasLab24 == null)
			HorasLab24 = "";
		else {
			String HorasLab24Z;
			StringTokenizer HorasLab24Art = new StringTokenizer(HorasLab24, ".");
			HorasLab24Z = HorasLab24Art.nextToken();
			HorasLab24 = HorasLab24Z;

			HorasL24 = Integer.parseInt(HorasLab24);
			if (HorasL24 < 5) {
				HorasLab24 = "   X           -           -     ";
			}
			if (HorasL24 >= 5 && HorasL24 <= 10) {
				HorasLab24 = "   -           X           -     ";
			}
			if (HorasL24 > 10) {
				HorasLab24 = "   -           -           X     ";
			}
		}
		parametros.put("HorasLab24", HorasLab24);
	}

	public void setPerDescanzo(String PerDescanzo) {
		if (PerDescanzo == null)
			PerDescanzo = "";
		String PerDescanzoR = "";
		StringTokenizer PerDescanzoArt = new StringTokenizer(PerDescanzo, ".");
		PerDescanzoR = PerDescanzoArt.nextToken();
		PerDescanzo = PerDescanzoR;
		parametros.put("PerDescanzo", PerDescanzo);
	}

	public void setCTransporte(String CTransporte) {
		if (CTransporte == null)
			CTransporte = "";
		if (CTransporte.equals("1")) {
			CTransporte = " X       -      -   ";
		}
		if (CTransporte.equals("2")) {
			CTransporte = " -       X      -   ";
		}
		if (CTransporte.equals("3")) {
			CTransporte = " -       -      X   ";
		}
		parametros.put("CTransporte", CTransporte);
	}

	public void setSoplos(String Soplos) {
		if (Soplos == null)
			Soplos = "";
		if (Soplos.equals("1")) {
			Soplos = " X              -   ";
		}
		if (Soplos.equals("0")) {
			Soplos = " -              X   ";
		}
		parametros.put("Soplos", Soplos);
	}

	public void setFRES(String FRES) {
		if (FRES == null) {
			FRES = "";
		} else {
			String FRES2 = FRES + ".";
			String FRESR;
			StringTokenizer FRESArt = new StringTokenizer(FRES2, ".");
			FRESR = FRESArt.nextToken();
			FRES = "" + FRESR + "";
			parametros.put("FRES", FRES);
		}
		parametros.put("FRES", FRES);
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

	public void setNumLic(String NumLicencia) {
		if (NumLicencia == null)
			NumLicencia = "";
		parametros.put("NumLicencia", NumLicencia);
	}
}
