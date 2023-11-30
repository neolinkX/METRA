/*
 * Created on 17/04/2013 
 * AG SA SANDOVAL
 */
package gob.sct.medprev.util;

import gob.sct.medprev.dao.TDConstancias;
import gob.sct.medprev.dao.TDEXPDictamenServ;

import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import java.util.StringTokenizer;

public class DictamenAEmoVDos extends Dictamen {

	public DictamenAEmoVDos() {
		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/DictAptoEMO.jasper");
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
	 * @param descUnidadMedica
	 *            The lugarExamen1 to set.
	 */
	public void setCdscModulo(String CdscModulo) {
		if (CdscModulo == null) {
			CdscModulo = "";
		}
		parametros.put("CdscModulo", CdscModulo);
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
			fechaExamen = "" + dia + " - " + mes + " - " + ano;
			parametros.put("fechaExamen", fechaExamen);
		}
	}

	/**
	 * @param Hora
	 *            to set.
	 */
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
			HM = "" + hora + ":" + minutos + "";
		}
		parametros.put("HM", HM);
	}

	/**
	 * @param Ciudad
	 *            Origen to set.
	 */
	public void setCiudadOrigen(String CiudadOrigen) {
		if (CiudadOrigen == null)
			CiudadOrigen = "";
		parametros.put("CiudadOrigen", CiudadOrigen);
	}

	/**
	 * @param Ciudad
	 *            Desitino to set.
	 */
	public void setCiudadDestino(String CiudadDestino) {
		if (CiudadDestino == null)
			CiudadDestino = "";
		parametros.put("CiudadDestino", CiudadDestino);
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
	 * @param Nombre
	 *            The Nombre to set.
	 */
	public void setPPaterno(String PPaterno) {
		if (PPaterno == null) {
			PPaterno = "";
		}
		parametros.put("PPaterno", PPaterno);
	}

	/**
	 * @param Nombre
	 *            The Nombre to set.
	 */
	public void setPMaterno(String PMaterno) {

		if (PMaterno == null) {
			PMaterno = "";
		}
		parametros.put("PMaterno", PMaterno);
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
	 * @param rfc
	 *            The rfc to set.
	 */
	public void setRfc(String rfc) {
		if (rfc == null) {
			rfc = "";
		}
		parametros.put("rfc", rfc);
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

	public void setCedula(String Cedula) {
		if (Cedula == null)
			Cedula = "";
		parametros.put("Cedula", Cedula);
	}

	/**
	 * @param The
	 *            RutaNAS to set.
	 */
	public void setRutaNAS(String RutaNAS) {
		if (RutaNAS == null) {
			RutaNAS = "";
		}
		parametros.put("RutaNAS", RutaNAS);
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
	 * @param numExpDGPMPT
	 *            The numExpDGPMPT to set.
	 */
	public void setNumExpDGPMPT(String numExpDGPMPT) {
		if (numExpDGPMPT == null) {
			numExpDGPMPT = "";
		}
		parametros.put("numExpDGPMPT", numExpDGPMPT);
	}

	public void setNumFolio(String NumFolio) {
		if (NumFolio == null)
			NumFolio = "";
		parametros.put("NumFolio", NumFolio);
	}

	public void setLicencia(String Licencia) {
		if (Licencia == null)
			Licencia = "";
		parametros.put("Licencia", Licencia);
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

	public void setPCarretero(String PCarretero) {
		if (PCarretero == null) {
			PCarretero = "";
		}
		parametros.put("PCarretero", PCarretero);
	}

	// //////////Nuevas peticiones de datos////////////
	TDConstancias dConstancias = new TDConstancias();

	// /Estado Origen
	public void setEstadoOr(String Exp, String Exa) {
		String EstadoOr = "";
		if (Exp == null || Exa == null) {
			EstadoOr = "-";
		} else {
			try {
				EstadoOr = dConstancias
						.RegresaS("SELECT F.CNOMBRE FROM GRLENTIDADFED AS F, "
								+ "EMOEXAMEN AS E WHERE E.ICVEEXPEDIENTE = "
								+ Exp + " AND " + "E.INUMEXAMEN = " + Exa
								+ " AND E.ICVEPAISOR = F.ICVEPAIS  AND "
								+ "E.ICVEEDOOR = F.ICVEENTIDADFED");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("EstadoOr", EstadoOr);
	}

	// Estado Destino
	public void setEstadoDes(String Exp, String Exa) {
		String EstadoDes = "";
		if (Exp == null || Exa == null) {
			EstadoDes = "-";
		} else {
			try {
				EstadoDes = dConstancias
						.RegresaS("SELECT F.CNOMBRE FROM GRLENTIDADFED AS F, "
								+ "EMOEXAMEN AS E WHERE E.ICVEEXPEDIENTE = "
								+ Exp + " AND " + "E.INUMEXAMEN = " + Exa
								+ " AND E.ICVEPAISDES = F.ICVEPAIS  AND "
								+ "E.ICVEEDODES = F.ICVEENTIDADFED");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("EstadoDes", EstadoDes);
	}

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

	// // Sexo
	public void setSexo(String Exp) {
		String F = "F";
		String M = "M";
		String S = "";
		if (Exp == null) {
			F = "-";
			M = "-";
		} else {
			try {
				S = dConstancias
						.RegresaS("select CSEXO from perdatos where icveexpediente = "
								+ Exp + "");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (S != null) {
			if (F.equals(S)) {
				F = "X";
				M = " ";
			} else {
				F = " ";
				M = "X";
			}
		} else {
			S = "";
		}
		parametros.put("F", F);
		parametros.put("M", M);
	}

	// /Transporte Aereo - Grupos
	public void setPAereo(String PAereo, String Exp, String Exa) {
		String R = "";
		String A1 = "1";
		String A2 = "2";
		String A3 = "3";
		String A4 = "4";
		if (PAereo == null) {
			PAereo = "";
		} else {
			if (PAereo.equals("X")) {
				try {
					R = dConstancias
							.RegresaS("select icvecategoria from expexamcat where icveexpediente = "
									+ Exp
									+ " and inumexamen = "
									+ Exa
									+ " and icvemdotrans = 1 ");
				} catch (Exception ex) {
					System.out.println(ex);
				}
				if (R != null) {
					if (R.equals(A1)) {
						A1 = "X";
						A2 = "";
						A3 = "";
						A4 = "";
					}
					if (R.equals(A2)) {
						A1 = "";
						A2 = "X";
						A3 = "";
						A4 = "";
					}
					if (R.equals(A3)) {
						A1 = "";
						A2 = "";
						A3 = "X";
						A4 = "";
					}
					if (R.equals(A4)) {
						A1 = "";
						A2 = "";
						A3 = "";
						A4 = "X";
					}
				} else {
					R = "";
				}
			} else {
				A1 = "";
				A2 = "";
				A3 = "";
				A4 = "";
			}
		}
		parametros.put("PAereo", PAereo);
		parametros.put("A1", A1);
		parametros.put("A2", A2);
		parametros.put("A3", A3);
		parametros.put("A4", A4);
	}

	// Horas laboradas
	public void setPHorasL(String Exp, String Exa) {
		String R = "";
		String M5H = "1";
		String D510H = "2";
		String MY10H = "3";
		if (Exp == null || Exa == null) {
			M5H = "";
			D510H = "";
			MY10H = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 50");
				if (R != null) {
					if (R.equals(M5H)) {
						M5H = "X";
						D510H = "";
						MY10H = "";
					}
					if (R.equals(D510H)) {
						M5H = "";
						D510H = "X";
						MY10H = "";
					}
					if (R.equals(MY10H)) {
						M5H = "";
						D510H = "";
						MY10H = "X";
					}
				} else {
					R = "";
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("M5H", M5H);
		parametros.put("D510H", D510H);
		parametros.put("MY10H", MY10H);
	}

	// Horas laboradas en los ultimos 7 dias
	public void setPHrDL(String Exp, String Exa) {
		String R = "";
		String MNH = "1";
		String MYH = "2";
		if (Exp == null || Exa == null) {
			MNH = "";
			MYH = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 51");
				if (R != null) {
					if (R.equals(MNH)) {
						MNH = "X";
						MYH = "";
					}
					if (R.equals(MYH)) {
						MNH = "";
						MYH = "X";
					}
				} else {
					R = "";
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("MNH", MNH);
		parametros.put("MYH", MYH);
	}

	// Ultimo periodo de descanzo
	public void setUPDD(String Exp, String Exa) {
		String UPDD = "";
		if (Exp == null || Exa == null) {
			UPDD = "";
		} else {
			try {
				UPDD = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 80");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("UPDD", UPDD);
	}

	// valor de glucosa
	public void setGlucometria(String Exp, String Exa) {
		String glucometria = "";
		if (Exp == null || Exa == null) {
			glucometria = "";
		} else {
			try {
				glucometria = dConstancias
						.RegresaS("select dvalorini from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 53");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("glucometria", glucometria);
	}

	// prueba Toxicologica
	public void setPTox(String Exp, String Exa) {
		String ToxSi = "1";
		String ToxNo = "0";
		String R = "";
		if (Exp == null || Exa == null) {
			ToxSi = "";
			ToxNo = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 54");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(ToxSi)) {
					ToxSi = "X";
					ToxNo = "";
				}
				if (R.equals(ToxNo)) {
					ToxSi = "";
					ToxNo = "X";
				}
			} else {
				R = "";
			}
			// System.out.println("-"+ToxSi+ "-"+ToxNo+"-");
		}
		parametros.put("ToxSi", ToxSi);
		parametros.put("ToxNo", ToxNo);
	}

	// Coloracion de Piel y Mucosas
	public void setCPM(String Exp, String Exa) {
		String CPMN = "1";
		String CPMP = "2";
		String CPMR = "3";
		String CPMC = "4";
		String R = "";
		if (Exp == null || Exa == null) {
			CPMN = "";
			CPMP = "";
			CPMR = "";
			CPMC = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 57");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(CPMN)) {
					CPMN = "X";
					CPMP = "";
					CPMR = "";
					CPMC = "";
				}
				if (R.equals(CPMP)) {
					CPMN = "";
					CPMP = "X";
					CPMR = "";
					CPMC = "";
				}
				if (R.equals(CPMR)) {
					CPMN = "";
					CPMP = "";
					CPMR = "X";
					CPMC = "";
				}
				if (R.equals(CPMC)) {
					CPMN = "";
					CPMP = "";
					CPMR = "";
					CPMC = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("CPMN", CPMN);
		parametros.put("CPMP", CPMP);
		parametros.put("CPMR", CPMR);
		parametros.put("CPMC", CPMC);
	}

	// Estado de Hidratacion
	public void setEsHid(String Exp, String Exa) {
		String HAde = "1";
		String HDes = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			HAde = "";
			HDes = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 58");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(HAde)) {
					HAde = "X";
					HDes = "";
				}
				if (R.equals(HDes)) {
					HAde = "";
					HDes = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("HAde", HAde);
		parametros.put("HDes", HDes);
	}

	// / Estado de Alerta
	public void setEAlerta(String Exp, String Exa) {
		String Alerta = "1";
		String Somno = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			Alerta = "";
			Somno = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 71");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(Alerta)) {
					Alerta = "X";
					Somno = "";
				}
				if (R.equals(Somno)) {
					Alerta = "";
					Somno = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("Alerta", Alerta);
		parametros.put("Somno", Somno);
	}

	// Marcha
	public void setMarcha(String Exp, String Exa) {
		String MNorm = "1";
		String MAnor = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			MNorm = "";
			MAnor = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 71");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(MNorm)) {
					MNorm = "X";
					MAnor = "";
				}
				if (R.equals(MAnor)) {
					MNorm = "";
					MAnor = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("MNorm", MNorm);
		parametros.put("MAnor", MAnor);
	}

	// / Lenguaje
	public void setLenguaje(String Exp, String Exa) {
		String LNorm = "1";
		String LAnor = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			LNorm = "";
			LAnor = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 73");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(LNorm)) {
					LNorm = "X";
					LAnor = "";
				}
				if (R.equals(LAnor)) {
					LNorm = "";
					LAnor = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("LNorm", LNorm);
		parametros.put("LAnor", LAnor);
	}

	// Orientacion
	public void setOrienta(String Exp, String Exa) {
		String Orienta = "1";
		String Desorie = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			Orienta = "";
			Desorie = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 74");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(Orienta)) {
					Orienta = "X";
					Desorie = "";
				}
				if (R.equals(Desorie)) {
					Orienta = "";
					Desorie = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("Orienta", Orienta);
		parametros.put("Desorie", Desorie);
	}

	// Romberg
	public void setRomberg(String Exp, String Exa) {
		String RPost = "1";
		String RNeg = "0";
		String R = "";
		if (Exp == null || Exa == null) {
			RPost = "";
			RNeg = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 39");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(RPost)) {
					RPost = "X";
					RNeg = "";
				}
				if (R.equals(RNeg)) {
					RPost = "";
					RNeg = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("RPost", RPost);
		parametros.put("RNeg", RNeg);
	}

	// Indice Punta Nariz
	public void setIPN(String Exp, String Exa) {
		String IPNNor = "1";
		String IPNAnor = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			IPNNor = "";
			IPNAnor = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 75");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(IPNNor)) {
					IPNNor = "X";
					IPNAnor = "";
				}
				if (R.equals(IPNAnor)) {
					IPNNor = "";
					IPNAnor = "X";
				}
			} else {
			}
		}
		parametros.put("IPNNor", IPNNor);
		parametros.put("IPNAnor", IPNAnor);
	}

	// Pupilas
	public void setPupila(String Exp, String Exa) {
		String PuNor = "1";
		String PuANr = "2";
		String PuMio = "3";
		String PuMid = "4";
		String PuSim = "5";
		String PuAsi = "6";
		String R = "";
		if (Exp == null || Exa == null) {
			PuNor = "";
			PuMio = "";
			PuMid = "";
			PuSim = "";
			PuAsi = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 76");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(PuNor)) {
					PuNor = "X";
					PuANr = "";
					PuMio = "";
					PuMid = "";
					PuSim = "";
					PuAsi = "";
				}
				if (R.equals(PuANr)) {
					PuNor = "";
					PuANr = "X";
					PuMio = "";
					PuMid = "";
					PuSim = "";
					PuAsi = "";
				}
				if (R.equals(PuMio)) {
					PuNor = "";
					PuANr = "";
					PuMio = "X";
					PuMid = "";
					PuSim = "";
					PuAsi = "";
				}
				if (R.equals(PuMid)) {
					PuNor = "";
					PuANr = "";
					PuMio = "";
					PuMid = "X";
					PuSim = "";
					PuAsi = "";
				}
				if (R.equals(PuSim)) {
					PuNor = "";
					PuANr = "";
					PuMio = "";
					PuMid = "";
					PuSim = "X";
					PuAsi = "";
				}
				if (R.equals(PuAsi)) {
					PuNor = "";
					PuANr = "";
					PuMio = "";
					PuMid = "";
					PuSim = "";
					PuAsi = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("PuNor", PuNor);
		parametros.put("PuANr", PuANr);
		parametros.put("PuMio", PuMio);
		parametros.put("PuMid", PuMid);
		parametros.put("PuSim", PuSim);
		parametros.put("PuAsi", PuAsi);
	}

	// Reflejo Fotomotor
	public void setRFM(String Exp, String Exa) {
		String RFMNor = "1";
		String RFMAnor = "2";
		String R = "";
		if (Exp == null || Exa == null) {
			RFMNor = "";
			RFMAnor = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 59");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(RFMNor)) {
					RFMNor = "X";
					RFMAnor = "";
				}
				if (R.equals(RFMAnor)) {
					RFMNor = "";
					RFMAnor = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("RFMNor", RFMNor);
		parametros.put("RFMAnor", RFMAnor);
	}

	// Uso De Lentes
	public void setUDL(String Exp, String Exa) {
		String UDLSi = "1";
		String UDLNo = "0";
		String R = "";
		if (Exp == null || Exa == null) {
			UDLSi = "";
			UDLNo = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 60");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(UDLSi)) {
					UDLSi = "X";
					UDLNo = "";
				}
				if (R.equals(UDLNo)) {
					UDLSi = "";
					UDLNo = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("UDLSi", UDLSi);
		parametros.put("UDLNo", UDLNo);
	}

	// Tipo De Lentes
	public void setTDL(String Exp, String Exa) {
		String UDLDC = "1";
		String UDLAe = "2";
		String R = "";
		String R2 = "";
		if (Exp == null || Exa == null) {
			UDLDC = "";
			UDLAe = "";
		} else {
			try {
				R2 = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 60");

				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 61");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R2 != null) {
				if (R2.equals("0")) {
					UDLDC = "";
					UDLAe = "";
				} else {
					if (R != null) {
						if (R.equals(UDLDC)) {
							UDLDC = "X";
							UDLAe = "";
						}
						if (R.equals(UDLAe)) {
							UDLDC = "";
							UDLAe = "X";
						}
					} else {
						R = "";
					}
				}
			} else {
				R2 = "";
			}
		}
		parametros.put("UDLDC", UDLDC);
		parametros.put("UDLAe", UDLAe);
	}

	// Reflejos Osteotenidinosos Derecho
	public void setROD(String Exp, String Exa) {
		String RODNo = "1";
		String RODAu = "2";
		String RODDi = "3";
		String R = "";
		if (Exp == null || Exa == null) {
			RODNo = "";
			RODAu = "";
			RODDi = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 62");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(RODNo)) {
					RODNo = "X";
					RODAu = "";
					RODDi = "";
				}
				if (R.equals(RODAu)) {
					RODNo = "";
					RODAu = "X";
					RODDi = "";
				}
				if (R.equals(RODDi)) {
					RODNo = "";
					RODAu = "";
					RODDi = "X";
				}
			} else {
				RODNo = "-";
				RODAu = "-";
				RODDi = "-";
			}
		}
		parametros.put("RODNo", RODNo);
		parametros.put("RODAu", RODAu);
		parametros.put("RODDi", RODDi);
	}

	// Reflejos Osteotenidinosos Izquierdo
	public void setROI(String Exp, String Exa) {
		String ROINo = "1";
		String ROIAu = "2";
		String ROIDi = "3";
		String R = "";
		if (Exp == null || Exa == null) {
			ROINo = "";
			ROIAu = "";
			ROIDi = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select ccaracter from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 63");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(ROINo)) {
					ROINo = "X";
					ROIAu = "";
					ROIDi = "";
				}
				if (R.equals(ROIAu)) {
					ROINo = "";
					ROIAu = "X";
					ROIDi = "";
				}
				if (R.equals(ROIAu)) {
					ROINo = "";
					ROIAu = "";
					ROIDi = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("ROINo", ROINo);
		parametros.put("ROIAu", ROIAu);
		parametros.put("ROIDi", ROIDi);
	}

	// Tension Arterial
	public void setTA(String Exp, String Exa) {
		String TAS = "";
		String TAD = "";
		String R = "";
		if (Exp == null || Exa == null) {
			TAS = "";
			TAD = "";
		} else {
			try {
				TAS = dConstancias
						.RegresaS("select DVALORINI from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 64");
				TAD = dConstancias
						.RegresaS("select DVALORINI from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 65");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("TAS", TAS);
		parametros.put("TAD", TAD);
	}

	// Frecuencia Respiratoria
	public void setFR(String Exp, String Exa) {
		String PFR = "";
		if (Exp == null || Exa == null) {
			PFR = "";
		} else {
			try {
				PFR = dConstancias
						.RegresaS("select DVALORINI from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 66");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("PFR", PFR);
	}

	// Frecuencia cardiaca
	public void setFC(String Exp, String Exa) {
		String PFC = "";
		if (Exp == null || Exa == null) {
			PFC = "";
		} else {
			try {
				PFC = dConstancias
						.RegresaS("select DVALORINI from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 67");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("PFC", PFC);
	}

	// Soplos Cardiacos
	public void setSC(String Exp, String Exa) {
		String PSCSi = "1";
		String PSCNo = "0";
		String R = "";
		if (Exp == null || Exa == null) {
			PSCSi = "";
			PSCNo = "";
		} else {
			try {
				R = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 68");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (R != null) {
				if (R.equals(PSCSi)) {
					PSCSi = "X";
					PSCNo = "";
				}
				if (R.equals(PSCNo)) {
					PSCSi = "";
					PSCNo = "X";
				}
			} else {
				R = "";
			}
		}
		parametros.put("PSCSi", PSCSi);
		parametros.put("PSCNo", PSCNo);
	}

	// Observaciones (Nota Medica)
	public void setNotMed(String Exp, String Exa) {
		String NotaMed = "";
		if (Exp == null || Exa == null) {
			NotaMed = "";
		} else {
			try {
				NotaMed = dConstancias
						.RegresaS("select cnotamedica from expservicio "
								+ "where icveexpediente = " + Exp + " and "
								+ "inumexamen = " + Exa + " and "
								+ "icveservicio = 1 ");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("NotaMed", NotaMed);
	}

	// Alcohol
	public void setAlc(String Exp, String Exa) {
		String ALC = "";
		String ALP = "";
		// String R = "";
		if (Exp == null || Exa == null) {
			ALP = "";
			ALC = "";
		} else {
			try {
				// Se modifico para de DVALORINI a llogico por peticio de la
				// direccion el dia 2013-08-15, modificado el 2013-08-22
				ALC = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 56");
				ALP = dConstancias
						.RegresaS("select llogico from expresultado where icveexpediente = "
								+ Exp
								+ " and "
								+ "inumexamen = "
								+ Exa
								+ " and icveservicio = 1 and icverama = 9 and icvesintoma = 55");
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (ALC != null) {
				if (ALC.equals("1")) {
					ALC = "SI";
				}
				if (ALC.equals("0")) {
					ALC = "NO";
				}
			} else {
				ALC = "";
			}

			if (ALP != null) {
				if (ALP.equals("1")) {
					ALP = "SI";
				}
				if (ALP.equals("0")) {
					ALP = "NO";
				}
			} else {
				ALP = "";
			}
		}
		parametros.put("ALC", ALC);
		parametros.put("ALP", ALP);
	}

	// Medico dictaminador
	public void setNoMed(String Exp, String Exa) {
		String NombreMed = "";
		String ApPatMed = "";
		String ApMatMed = "";
		if (Exp == null || Exa == null) {
			NombreMed = "";
			ApPatMed = "";
			ApMatMed = "";
		} else {
			try {
				NombreMed = dConstancias
						.RegresaS("select u.cnombre from segusuario as u, expexamcat as c "
								+ "where c.icveexpediente = "
								+ Exp
								+ " and "
								+ "c.inumexamen = "
								+ Exa
								+ " and "
								+ "u.icveusuario = c.lditamem");
				ApPatMed = dConstancias
						.RegresaS("select u.cappaterno from segusuario as u, expexamcat as c "
								+ "where c.icveexpediente = "
								+ Exp
								+ " and "
								+ "c.inumexamen = "
								+ Exa
								+ " and "
								+ "u.icveusuario = c.lditamem");
				ApMatMed = dConstancias
						.RegresaS("select u.capmaterno from segusuario as u, expexamcat as c "
								+ "where c.icveexpediente = "
								+ Exp
								+ " and "
								+ "c.inumexamen = "
								+ Exa
								+ " and "
								+ "u.icveusuario = c.lditamem");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		parametros.put("NombreMed", NombreMed);
		parametros.put("ApPatMed", ApPatMed);
		parametros.put("ApMatMed", ApMatMed);
	}

}
