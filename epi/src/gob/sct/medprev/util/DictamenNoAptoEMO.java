/*
 * Created on 18/10/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gob.sct.medprev.util;

import java.util.HashMap;
import java.util.StringTokenizer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;

import gob.sct.medprev.dao.TDConstancias;

/**
 * @author Eigen
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DictamenNoAptoEMO extends Dictamen {

	/**
	 * 
	 */
	public DictamenNoAptoEMO() {
		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/DictamenNoAptoEMO.jasper");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parametros = new HashMap();
	}

	/**
	 * @param claveExpediente
	 *            The claveExpediente to set.
	 */
	public void setClaveExpediente(String claveExpediente) {
		if (claveExpediente == null) {
			claveExpediente = "";
		}
		parametros.put("claveExpediente", claveExpediente);
	}

	/**
	 * @param descripcionProceso
	 *            The descripcionProceso to set.
	 */
	public void setDescripcionProceso(String descripcionProceso) {
		if (descripcionProceso == null) {
			descripcionProceso = "";
		}
		parametros.put("descripcionProceso", descripcionProceso);
	}

	/**
	 * @param descripcionUnidadMedica
	 *            The descripcionUnidadMedica to set.
	 */
	public void setDescripcionUnidadMedica(String descripcionUnidadMedica) {
		if (descripcionUnidadMedica == null) {
			descripcionUnidadMedica = "";
		}
		parametros.put("descripcionUnidadMedica", descripcionUnidadMedica);
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
	 * @param direccion1
	 *            The direccion1 to set.
	 */
	public void setDireccion1(String direccion1) {
		if (direccion1 == null) {
			direccion1 = "";
		}
		parametros.put("direccion1", direccion1);
	}

	/**
	 * @param direccion2
	 *            The direccion2 to set.
	 */
	public void setDireccion2(String direccion2) {
		if (direccion2 == null) {
			direccion2 = "";
		}
		parametros.put("direccion2", direccion2);
	}

	/**
	 * @param direccion3
	 *            The direccion3 to set.
	 */
	public void setDireccion3(String direccion3) {
		if (direccion3 == null) {
			direccion3 = "";
		}
		parametros.put("direccion3", direccion3);
	}

	/**
	 * @param direccion4
	 *            The direccion4 to set.
	 */
	public void setDireccion4(String direccion4) {
		if (direccion4 == null) {
			direccion4 = "";
		}
		parametros.put("direccion4", direccion4);
	}

	/**
	 * @param direccionUnidadMedica
	 *            The direccionUnidadMedica to set.
	 */
	public void setDireccionUnidadMedica(String direccionUnidadMedica) {
		if (direccionUnidadMedica == null) {
			direccionUnidadMedica = "";
		}
		parametros.put("direccionUnidadMedica", direccionUnidadMedica);
	}

	/**
	 * @param lugarFechaExpedicion
	 *            The lugarFechaExpedicion to set.
	 */
	public void setLugarFechaExpedicion(String lugarFechaExpedicion) {
		if (lugarFechaExpedicion == null) {
			lugarFechaExpedicion = "";
		}
		parametros.put("lugarFechaExpedicion", lugarFechaExpedicion);
	}

	/**
	 * @param resultadoFecha
	 *            The resultado to set.
	 */
	public void setResultadoFecha(String resultadoFecha) {
		if (resultadoFecha == null) {
			resultadoFecha = "";
		}
		parametros.put("resultadoFecha", resultadoFecha);
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
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setCedula(String Cedula) {
		if (Cedula == null) {
			Cedula = "";
		}
		parametros.put("Cedula", Cedula);
	}

	/**
	 * @param Doc
	 *            y cedula The Doc y cedula to set.
	 */
	public void setDoct(String Doct) {

		if (Doct == null) {
			Doct = "";
		}
		parametros.put("Doct", Doct);
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setDireccionUM(String DireccionUM) {
		if (DireccionUM == null) {
			DireccionUM = "";
		}
		parametros.put("DireccionUM", DireccionUM);
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setMdotrans(String mdotrans) {
		if (mdotrans == null) {
			mdotrans = "";
		}
		parametros.put("mdotrans", mdotrans);
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setDiagNAemo(String DiagNAemo) {
		if (DiagNAemo == null) {
			DiagNAemo = "";
		}
		parametros.put("DiagNAemo", DiagNAemo);
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setMotNAemo(String MotNAemo) {
		if (MotNAemo == null) {
			MotNAemo = "";
		}
		parametros.put("MotNAemo", MotNAemo);
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setNombreEmpresa(String NombreEmpresa) {
		if (NombreEmpresa == null) {
			NombreEmpresa = "";
		}
		parametros.put("NombreEmpresa", NombreEmpresa);
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setDtSolicitado(String Fecha) {
		if (Fecha == null) {
			Fecha = "1990-01-01";
		}
		parametros.put("ano", Fecha.subSequence(0, 4));
		parametros.put("mes", Fecha.subSequence(5, 7));
		parametros.put("dia", Fecha.subSequence(8, 10));
	}

	/**
	 * @param Cedula
	 *            Profesional The Cedula Profesional.
	 */
	public void setLicencia(String Licencia) {
		if (Licencia == null) {
			Licencia = "";
		}
		parametros.put("Licencia", Licencia);
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
	 * @param fechaDictamen
	 *            The FinVIgencia to set.
	 */
	public void setFundamentacion(String Fundamentacion) {
		if (Fundamentacion == null) {
			Fundamentacion = "";
		}
		parametros.put("Fundamentacion", Fundamentacion);
	}

	/**
	 * @param Numero
	 *            de Examen.
	 */
	public void setNumExamen(String NumExamen) {
		if (NumExamen == null) {
			NumExamen = "";
		}
		parametros.put("NumExamen", NumExamen);
	}

	// /Nuevos parametros 25-06-2013 AG SA
	/**
	 * @param Nombre
	 *            Empresa.
	 */
	public void setNEmpresa(String NEmpresa) {
		if (NEmpresa == null) {
			NEmpresa = "";
		}
		parametros.put("NEmpresa", NEmpresa);
	}

	/**
	 * @param Direccion
	 *            Empresa.
	 */
	public void setDEmpresa(String DEmpresa) {
		if (DEmpresa == null) {
			DEmpresa = "";
		} else {
			try {
				DEmpresa = dConstancias
						.RegresaS("SELECT C.CCALLE ||', Col.'||C.CCOLONIA ||', '|| C.CCIUDAD ||','|| M.CNOMBRE ||' '|| E.CNOMBRE ||' '|| P.CNOMBRE "
								+ "FROM CTRDOMICILIO AS C, "
								+ "	GRLPAIS AS P, "
								+ "	GRLENTIDADFED AS E, "
								+ "	GRLMUNICIPIO AS M "
								+ "WHERE C.ICVEEMPRESA = "
								+ DEmpresa
								+ " AND "
								+ "	C.ICVEPAIS = P.ICVEPAIS AND "
								+ "	C.ICVEPAIS = E.ICVEPAIS AND "
								+ "	C.ICVEESTADO = E.ICVEENTIDADFED AND "
								+ "	C.ICVEPAIS = M.ICVEPAIS AND "
								+ "	C.ICVEESTADO = M.ICVEENTIDADFED AND "
								+ "	C.ICVEMUNICIPIO = M.ICVEMUNICIPIO AND "
								+ "	C.ICVEDOMICILIO = (SELECT D.ICVEDOMICILIO FROM CTRDOMICILIO AS D WHERE C.ICVEEMPRESA = D.ICVEEMPRESA)");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("DEmpresa", DEmpresa);
	}

	// //////////Nuevas peticiones de datos////////////
	TDConstancias dConstancias = new TDConstancias();

	// / Edad
	public void setEdad(String Exp) {
		String Edad = "";
		if (Exp == null) {
			Edad = "-";
		} else {
			try {
				Edad = dConstancias
						.RegresaS("select avg(year(current date - dtnacimiento)) "
								+ "from perdatos where icveexpediente = "
								+ Exp
								+ "");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("Edad", Edad);
	}

	/**
	 * @param Diagnostico
	 *            .
	 */
	public void setDiagnosticoNA(String DiagnosticoNA) {
		if (DiagnosticoNA == null) {
			DiagnosticoNA = "";
		}
		parametros.put("Diagnostico", DiagnosticoNA);
	}

	/**
	 * @param Diagnostico
	 *            .
	 */
	public void setMotivacionNA(String MotivacionNA) {
		if (MotivacionNA == null) {
			MotivacionNA = "";
		}
		parametros.put("Motivacion", MotivacionNA);
	}

	/**
	 * @param Diagnostico
	 *            .
	 */
	public void setTransporte(int Transporte) {
		String trans = "";
		if (Transporte == 1) {
			trans = "Personal Técnico Aeronáutico.";
		}
		if (Transporte == 2) {
			trans = "Autotransporte Público Federal.";
		}
		if (Transporte == 3) {
			trans = "Personal Técnico Ferroviario.";
		}
		if (Transporte == 4) {
			trans = "Personal Técnico Marítimo.";
		}
		parametros.put("Transporte", trans);
	}

}
