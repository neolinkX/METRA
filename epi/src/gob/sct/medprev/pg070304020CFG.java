package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.TFechas;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304020CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vAnalisis = new Vector();
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivEquipo = 0;

	// TVUsuario vUsuario = (TVUsuario)
	// httpReq.getSession().getAttribute("UsrID");

	public pg070304020CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {
		TFechas vFecha = new TFechas();
		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();

		if (request.getParameter("SLSAnio") != null) {
			cvAnio = request.getParameter("SLSAnio");
		}
		if (cvAnio.compareToIgnoreCase("") == 0) {
			cvAnio = String.valueOf(vFecha.getIntYear(vFecha.TodaySQL()));
		}
		if (request.getParameter("iCveUniMed") != null) {
			ivCveLaboratorio = new Integer(request.getParameter("iCveUniMed"))
					.intValue();
		}
		if (request.getParameter("SLSSustancia") != null) {
			ivSustancia = new Integer(request.getParameter("SLSSustancia")
					.toString()).intValue();
		}
		if (request.getParameter("SLSLoteConfirmativo") != null) {
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();
		}
		if (request.getParameter("SLSEquipo") != null) {
			ivEquipo = new Integer(request.getParameter("SLSEquipo").toString())
					.intValue();
		}
		try {

			vLoteCuantita = dLoteCuantita
					.FindByAll(" where TOXLoteCuantita.iAnio           =           "
							+ cvAnio
							+ "   and TOXLoteCuantita.iCveLaboratorio =           "
							+ ivCveLaboratorio
							+ "   and TOXLoteCuantita.iCveSustancia   =           "
							+ ivSustancia
							+ "   and TOXLoteCuantita.dtGeneracion    is null     "
							+ "   and TOXLoteCuantita.dtAnalisis      is null     "
							+ "   and TOXLoteCuantita.dtAutorizacion  is null     "
							+ " ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void Guardar() {

		String cCond = "";
		String cOrd = "";
		String cLote = "", cMensaje = "";

		cCond = cCondicion;
		cOrd = cOrden;
		this.fillVector();
		cCondicion = cCond;
		cOrden = cOrd;

		String cvNombre = "";
		int ivCveLoteCuantita = 0;
		int iAutorizacion = 0;

		// Sustancias a Unificar
		String cUnifica = "";
		ArrayList aSustancias = new ArrayList();
		try {
			Vector vSustancia = new TDTOXSustancia().FindByAll(
					" where iCveSustancia = " + ivSustancia, "");
			if (vSustancia.size() > 0) {
				cUnifica = ((TVTOXSustancia) (vSustancia.get(0)))
						.getCSustUnifica();
				cLote = ((TVTOXSustancia) (vSustancia.get(0)))
						.getcPrefLoteConf();
			}
			if (cUnifica == null || cUnifica.equalsIgnoreCase(""))
				cUnifica = String.valueOf(ivSustancia);
			// Obtener un ArrayList con las Sustancias a Unificar
			StringTokenizer stSustancias = new StringTokenizer(cUnifica, ",");
			while (stSustancias.hasMoreTokens()) {
				aSustancias.add(stSustancias.nextToken());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		TDTOXLoteCuantita DTOXLoteCuantita = new TDTOXLoteCuantita();
		// Borrar Muestras
		if (!vDespliega.isEmpty()) {
			for (int i = 0; i < vDespliega.size(); i++) {
				TVTOXExamResult VTOXExamResult = new TVTOXExamResult();
				VTOXExamResult = (TVTOXExamResult) vDespliega.get(i);
				cvNombre = "TBXElim-" + VTOXExamResult.getiAnio().toString()
						+ "-" + VTOXExamResult.getiCveLaboratorio().toString()
						+ "-" + VTOXExamResult.getiCveLoteCualita().toString()
						+ "-" + VTOXExamResult.getiCveExamCualita().toString()
						+ "-" + VTOXExamResult.getiCveAnalisis().toString()
						+ "-" + VTOXExamResult.getiCveSustancia().toString();

				if (request.getParameter(cvNombre) != null) {
					if (request.getParameter(cvNombre).compareToIgnoreCase("1") == 0) {

						TDTOXExamResult DTOXExamResult = new TDTOXExamResult();
						TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
						TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
						TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();
						VTOXLoteCuantita.setiAnio(VTOXExamResult.getiAnio());
						VTOXLoteCuantita.setiCveLaboratorio(VTOXExamResult
								.getiCveLaboratorio());
						VTOXLoteCuantita.setiCveSustancia(VTOXExamResult
								.getiCveSustancia());
						VTOXLoteCuantita.setiCveEquipo(new Integer(ivEquipo));
						VTOXLoteCuantita.setiCveLoteCuantita(new Integer(
								ivLoteCuantita));

						VTOXCuantAnalisis.setiAnio(VTOXExamResult.getiAnio());
						VTOXCuantAnalisis.setiCveSustancia(VTOXExamResult
								.getiCveSustancia());
						// Valor del Lote.
						VTOXCuantAnalisis.setiCveLoteCuantita(new Integer(
								ivLoteCuantita));
						VTOXCuantAnalisis.setiCveAnalisis(VTOXExamResult
								.getiCveAnalisis());
						VTOXCuantAnalisis.setiCveLaboratorio(VTOXExamResult
								.getiCveLaboratorio());
						try {
							// Quitar la asignación a los Análisis

							VTOXExamResult.setlAsignado(new Integer("0"));
							DTOXExamResult.update(VTOXExamResult,
									" and E.iCveSustancia in (" + cUnifica
											+ ")");
							// Para cada sustancia se elimina el análisis
							for (int s = 0; s < aSustancias.size(); s++) {
								VTOXCuantAnalisis.setiCveSustancia(Integer
										.valueOf((String) aSustancias.get(s)));
								DTOXCuantAnalisis.delete(VTOXCuantAnalisis);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			// Se eliminaron Muestras por lo tanto se indica realiza la
			// validación del tamaño.
			try {
				TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();
				VTOXLoteCuantita.setiAnio(Integer.valueOf(this.cvAnio));
				VTOXLoteCuantita.setiCveLaboratorio(Integer.valueOf(String
						.valueOf(this.ivCveLaboratorio)));
				VTOXLoteCuantita.setiCveSustancia(Integer.valueOf(String
						.valueOf(this.ivSustancia)));
				VTOXLoteCuantita.setiCveLoteCuantita(Integer.valueOf(String
						.valueOf(this.ivLoteCuantita)));
				Vector vValidacion = DTOXLoteCuantita.MuestrasXLote(null,
						VTOXLoteCuantita);
				if (vValidacion != null && vValidacion.size() > 0) {
					TVTOXLoteCuantita VLote = ((TVTOXLoteCuantita) vValidacion
							.get(0));
					cLote = cvAnio + "-" + VLote.VSustancia.getCPrefLoteConf()
							+ "-" + VTOXLoteCuantita.getiCveLoteCuantita();
					// Generar los mensajes del Lote.
					if (VLote.getINumMuestras() == VLote.VSustancia
							.getIMuestrasLC()) {
						cMensaje = "El número de muestras ingresadas al lote llego al límite indicado de "
								+ VLote.VSustancia.getIMuestrasLC() + ".";
					}
					if (VLote.getINumMuestras() > VLote.VSustancia
							.getIMuestrasLC()) {
						cMensaje = "El número de muestras ingresadas al lote excede al límite indicado de "
								+ VLote.VSustancia.getIMuestrasLC() + ".";
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // Existen muestras a Desplegar

		// Código para la Autorización.
		if (request.getParameter("TBXAutorizacion") != null) {
			if (request.getParameter("TBXAutorizacion")
					.compareToIgnoreCase("1") == 0) {
				TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
				Vector vTOXCuantAnalisis = new Vector();
				TDTOXCorteXSust DTOXCorteXSust = new TDTOXCorteXSust();
				Vector vTOXCorteXSust = new Vector();
				TDTOXCalibCuantita DTOXCalibCuantita = new TDTOXCalibCuantita();
				Vector vTOXCalibCuantita = new Vector();
				int ivPosicion = 0;
				int ivCorte = 0;
				int ivCalib = 0;
				iAutorizacion = 1;
				// Localización del Calibrador, el Negativo y los Controles.
				try {
					vTOXCalibCuantita = DTOXCalibCuantita
							.FindByAll(" where iCveLaboratorio = "
									+ ivCveLaboratorio
									+ "   and iCveSustancia = "
									+ ivSustancia
									+ // Se agrego la siguiente línea en el
										// where para evitar el problema de
										// ordenamiento de lotes.
									"   and iCveCalib = (select max(iCveCalib) from TOXCalibCuantita "
									+ "                     where iCveLaboratorio = "
									+ ivCveLaboratorio + " )");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// Localización de los Cortes de las Sustancias.
				try {
					vTOXCorteXSust = DTOXCorteXSust
							.FindByAll(" where TOXCorteXSust.iCveSustancia in ("
									+ cUnifica
									+ ")"
									+ "   and TOXCorteXSust.lActivo = 1 "
									+ "   and lCuantCual = 1 ");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// Existe calibración
				if (!vTOXCalibCuantita.isEmpty()) {
					// Existen cortes para la sustancia
					if (!vTOXCorteXSust.isEmpty()) {
						for (int j = 0; j < vTOXCorteXSust.size(); j++) {
							TVTOXCorteXSust VTOXCorteXSust = new TVTOXCorteXSust();
							VTOXCorteXSust = (TVTOXCorteXSust) vTOXCorteXSust
									.get(j);
							ivCorte = VTOXCorteXSust.getiCveCorte().intValue();
							ivPosicion = 0;
							// System.out.println("vTOXCalibCuantita.size() = "
							// +vTOXCalibCuantita.size());
							for (int i = 0; i < vTOXCalibCuantita.size(); i++) {

								// System.out.println("contador = " +i);

								ivPosicion = ivPosicion + 1;

								// System.out.println("ivPosicion = "+ivPosicion);

								TVTOXCalibCuantita VTOXCalibCuantita = new TVTOXCalibCuantita();
								VTOXCalibCuantita = (TVTOXCalibCuantita) vTOXCalibCuantita
										.get(i);
								double dvConcentracion = 0;
								ivCalib = VTOXCalibCuantita.getiCveCalib()
										.intValue();
								// Cálculo de la Concentración para los
								// Controles.
								if (VTOXCalibCuantita.getlControl().toString()
										.compareToIgnoreCase("1") == 0) {
									dvConcentracion = VTOXCalibCuantita
											.getdPorcentaje().doubleValue()
											* VTOXCorteXSust.getdCorte()
													.doubleValue();
									ivCalib = VTOXCalibCuantita.getiCveCalib()
											.intValue();
								} else {
									dvConcentracion = VTOXCorteXSust
											.getdCorte().doubleValue();
								}
								// llenado de Datos para la Inserción de
								// Muestras en el Lote Cuantitativo.
								TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
								VTOXCuantAnalisis.setiAnio(new Integer(cvAnio));
								VTOXCuantAnalisis
										.setiCveLaboratorio(new Integer(
												ivCveLaboratorio));
								VTOXCuantAnalisis
										.setiCveSustancia(VTOXCorteXSust
												.getiCveSustancia());
								VTOXCuantAnalisis
										.setiCveLoteCuantita(new Integer(
												ivLoteCuantita));
								VTOXCuantAnalisis
										.setiCveAnalisis(VTOXCalibCuantita
												.getiPosicion());
								if (VTOXCalibCuantita.getlControl().toString()
										.compareToIgnoreCase("1") == 0) {
									VTOXCuantAnalisis.setlControl(new Integer(
											"1"));
								} else {
									VTOXCuantAnalisis.setlControl(new Integer(
											"0"));
								}
								VTOXCuantAnalisis.setiPosicion(new Integer(
										ivPosicion));
								VTOXCuantAnalisis
										.setiDilusion(new Integer("1"));
								VTOXCuantAnalisis.setdConcentracion(new Double(
										dvConcentracion));
								// Insercion de las Muestras al Lote
								// Cuantitativo: Calibrador, Negativo y Control.
								try {
									DTOXCuantAnalisis
											.insert1(VTOXCuantAnalisis);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							} // Para cada calibrador
						} // Para cada corte por sustancia
					} // Existen los cortes por cada sustancia
				} // Existe la calibración

				// Localización de los Analisis para Asignales Posición.
				try {
					vTOXCuantAnalisis = DTOXCuantAnalisis
							.FindByAll(" where iAnio            = " + cvAnio
									+ "   and iCveSustancia    = "
									+ ivSustancia
									+ "   and iCveLoteCuantita = "
									+ ivLoteCuantita
									+ "   and iCveLaboratorio  = "
									+ ivCveLaboratorio
									+ "   and iPosicion is null  "
									+ " order by iAnio, iCveAnalisis ");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// Existen análisis
				if (!vTOXCuantAnalisis.isEmpty()) {
					// System.out.println("vTOXCuantAnalisis.size() = "+vTOXCuantAnalisis.size());
					for (int m = 0; m < vTOXCuantAnalisis.size(); m++) {
						ivPosicion = ivPosicion + 1;

						// System.out.println("contador = " +m);
						// System.out.println("ivPosicion = "+ivPosicion);

						TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
						VTOXCuantAnalisis = (TVTOXCuantAnalisis) vTOXCuantAnalisis
								.get(m);
						VTOXCuantAnalisis.setiPosicion(new Integer(ivPosicion));
						// Actualización de la Posición a las Muestras.
						try {
							// actualizar para cada sustancia
							// Para cada sustancia se elimina el análisis
							for (int s = 0; s < aSustancias.size(); s++) {
								VTOXCuantAnalisis.setiCveSustancia(Integer
										.valueOf((String) aSustancias.get(s)));
								DTOXCuantAnalisis.update(VTOXCuantAnalisis);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				// Llenado de Datos de Lote Cuantita para su Actualización.
				TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();
				VTOXLoteCuantita.setiAnio(new Integer(cvAnio));
				VTOXLoteCuantita.setiCveLaboratorio(new Integer(
						ivCveLaboratorio));
				VTOXLoteCuantita.setiCveSustancia(new Integer(ivSustancia));
				VTOXLoteCuantita
						.setiCveLoteCuantita(new Integer(ivLoteCuantita));
				TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
						.getAttribute("UsrID");
				VTOXLoteCuantita.setiCveUsuGenera(new Integer(vUsuario
						.getICveusuario()));
				VTOXLoteCuantita.setiCveEquipo(new Integer(ivEquipo));
				VTOXLoteCuantita.setiCveCorte(new Integer(ivCorte));
				VTOXLoteCuantita.setiCveCalib(new Integer(ivCalib));

				// Actualización de Lote Cuantita para Dejarlo Autorizado.
				try {
					for (int j = 0; j < vTOXCorteXSust.size(); j++) {
						TVTOXCorteXSust VTOXCorteXSust = new TVTOXCorteXSust();
						VTOXCorteXSust = (TVTOXCorteXSust) vTOXCorteXSust
								.get(j);
						VTOXLoteCuantita.setiCveCorte(VTOXCorteXSust
								.getiCveCorte());
						VTOXLoteCuantita.setiCveSustancia(VTOXCorteXSust
								.getiCveSustancia());
						DTOXLoteCuantita.update(VTOXLoteCuantita);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();
		try {
			vLoteCuantita = dLoteCuantita
					.FindByAll(" where TOXLoteCuantita.iAnio           =       "
							+ cvAnio
							+ "   and TOXLoteCuantita.iCveLaboratorio =       "
							+ ivCveLaboratorio
							+ "   and TOXLoteCuantita.iCveSustancia   =       "
							+ ivSustancia
							+ "   and TOXLoteCuantita.dtGeneracion    is null "
							+ "   and TOXLoteCuantita.dtAnalisis      is null "
							+ "   and TOXLoteCuantita.dtAutorizacion  is null "
							+ " ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// cLote = cvAnio + "-" + cLote + "-" + this.ivLoteCuantita;
		if (iAutorizacion == 1) {
			this.vErrores.acumulaError("Se autorizó el Lote: " + cLote, 0, "");
			ivLoteCuantita = 0;
		} else {
			if (request.getParameter("SLSLoteConfirmativo") != null) {
				ivLoteCuantita = new Integer(request.getParameter(
						"SLSLoteConfirmativo").toString()).intValue();
			}
			this.vErrores.acumulaError("Se Actualizó el Lote " + cLote, 0, "");
		}
		if (cMensaje.length() > 0) {
			this.vErrores.acumulaError(cMensaje, 0, "");
		}
	}

	public void fillVector() {

		cPaginas = "pg070304021.jsp|";
		String cCondicion2 = "";

		TDTOXExamResult dTOXExamResult = new TDTOXExamResult();
		TDTOXAnalisis dTOXAnalisis = new TDTOXAnalisis();

		if (cCondicion.compareTo("") != 0) {
			cCondicion2 = " and " + cCondicion;
		}

		cCondicion = " inner join TOXAnalisis "
				+ "   on TOXAnalisis.iAnio            = TOXExamResult.iAnio          "
				+ "  and TOXAnalisis.iCveLaboratorio  = TOXExamResult.iCveLaboratorio "
				+ "  and TOXAnalisis.iCveLoteCualita  = TOXExamResult.iCveLoteCualita "
				+ "  and TOXAnalisis.iCveAnalisis     = TOXExamResult.iCveAnalisis    "
				+ " inner join TOXCuantAnalisis "
				+ "   on TOXCuantAnalisis.iAnio           = TOXAnalisis.iAnio           "
				+ "  and TOXCuantAnalisis.iCveLaboratorio = TOXAnalisis.iCveLaboratorio "
				+ "  and TOXCuantAnalisis.iCveLoteCuantita = "
				+ ivLoteCuantita
				+ "  and TOXCuantAnalisis.iCveAnalisis    = TOXAnalisis.iCveAnalisis    "
				+ "  and TOXCuantAnalisis.iCveSustancia    = "
				+ ivSustancia
				+ " inner join TOXLoteCuantita "
				+ "   on TOXLoteCuantita.iAnio           = TOXCuantAnalisis.iAnio           "
				+ "  and TOXLoteCuantita.iCveLaboratorio = TOXCuantAnalisis.iCveLaboratorio "
				+ "  and TOXLoteCuantita.iCveSustancia   =   "
				+ ivSustancia
				+ "  and TOXLoteCuantita.iCveLoteCuantita =  "
				+ ivLoteCuantita
				+ " inner join TOXSustancia S on S.iCveSustancia = TOXExamResult.iCveSustancia "
				+ " where TOXExamResult.iAnio            =   " + cvAnio
				+ " and TOXExamResult.iCveLaboratorio  =   " + ivCveLaboratorio
				+ " and TOXExamResult.iCveSustancia    =   " + ivSustancia
				+ " and TOXExamResult.iAsignado        = 1 " + "";
		if (cOrden.compareTo("") != 0) {
			cCondicion2 = cCondicion2 + cOrden;
		}
		try {
			vDespliega = dTOXExamResult.FindByAll(cCondicion + cCondicion2);
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}
		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Imprimir";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}
	}
}
