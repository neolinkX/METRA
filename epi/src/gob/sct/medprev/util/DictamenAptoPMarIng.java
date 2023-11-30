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

/**
 * @author Eigen
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DictamenAptoPMarIng extends Dictamen {

	/**
	 * 
	 */
	public DictamenAptoPMarIng() {
		try {
			// System.out.println("Clase Dictamen");
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/DictamenAptoPMarIng.jasper");
		} catch (JRException e) {
			e.printStackTrace();
		}
		parametros = new HashMap<String, Comparable>();
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
		}
		parametros.put("fechaExamen", fechaExamen);
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
		if (nota == null) {
			nota = "";
		}
		// System.out.println(nota);
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

	/**
	 * @param ObserRestric
	 *            to set.
	 */
	public void setObserRestric(String ObserRestric) {
		if (ObserRestric == null) {
			ObserRestric = "";
		}
		parametros.put("ObserRestric", ObserRestric);
	}

	public void setImprimirCategoria(boolean imprimirCategoria) {
		parametros.put("imprimirCategoria", new Boolean(imprimirCategoria));
	}

	/**
	 * @param fechaDictamen
	 *            The InicioVigencia to set.
	 */
	public void setIniVig(String IniVig) {
		if (IniVig == null) {
			IniVig = "";
		}
		parametros.put("IniVig", IniVig);
	}

	/**
	 * @param fechaDictamen
	 *            The FinVIgencia to set.
	 */
	public void setFinVig(String FinVig) {
		if (FinVig == null) {
			FinVig = "";
		}
		parametros.put("FinVig", FinVig);
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
	public void setNotaTercero(String notaTercero) {
		if (notaTercero == null) {
			notaTercero = "";
		}
		parametros.put("notaTercero", notaTercero);
	}

	/**
	 * @param Numero
	 *            de Examen para constancias de historicos.
	 */
	public void setNumExamen(String NumExamen) {
		if (NumExamen == null) {
			NumExamen = "";
		}
		//System.out.println(NumExamen);
		parametros.put("NumExamen", NumExamen);
	}
	
	/**
	 * @param Nacionalidad.
	 */
	public void setNacionalidad(String Nacionalidad) {
		if (Nacionalidad == null) {
			Nacionalidad = "";
		}
		parametros.put("nacionalidad", Nacionalidad);
	}

}
