package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

public class pg070304010CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();

	// public Vector vAnalisis = new Vector();
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 1;
	int ivLoteCuantita = 0;
	private int iSustUnifica = 0;

	public pg070304010CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();

		if (request.getParameter("SLSAnio") != null) {
			cvAnio = request.getParameter("SLSAnio");

		}
		if (cvAnio.compareToIgnoreCase("") == 0) {
			cvAnio = "2004";

		}
		if (request.getParameter("iCveUniMed") != null
				&& request.getParameter("iCveUniMed").toString()
						.compareToIgnoreCase("null") != 0) {
			ivCveLaboratorio = new Integer(request.getParameter("iCveUniMed"))
					.intValue();
		} else {
			ivCveLaboratorio = 0;

		}
		if (request.getParameter("SLSSustancia") != null) {
			ivSustancia = new Integer(request.getParameter("SLSSustancia")
					.toString()).intValue();

		}
		if (request.getParameter("SLSLoteConfirmativo") != null) {
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();

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
		String cLoteGenerado = "";

		cCond = cCondicion;
		cOrd = cOrden;

		this.fillVector();

		cCondicion = cCond;
		cOrden = cOrd;

		String cvNombre = "";
		String cvNomDilucion = "";
		String cLote = "";
		boolean lvGenerado = false, lvValidado = false;
		int ivCveLoteCuantita = 0;
		int ivDilucion = 0;
		int iCveSustancia = 0;

		// Definición de Variables
		TVTOXExamResult VTOXExamResult = new TVTOXExamResult();
		TDTOXExamResult DTOXExamResult = new TDTOXExamResult();
		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
		TDTOXLoteCuantita DTOXLoteCuantita = new TDTOXLoteCuantita();
		TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();

		if (request.getParameter("SLSLoteConfirmativo") != null) {
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();

		}
		// Sustancias a Unificar
		String cUnifica = "";
		ArrayList aSustancias = new ArrayList();
		try {
			Vector vSustancia = new TDTOXSustancia().FindByAll(
					" where iCveSustancia = " + ivSustancia, "");
			if (vSustancia.size() > 0)
				cUnifica = ((TVTOXSustancia) (vSustancia.get(0)))
						.getCSustUnifica();
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

		if (!vDespliega.isEmpty()) {
			for (int i = 0; i < vDespliega.size(); i++) {
				VTOXExamResult = new TVTOXExamResult();
				VTOXExamResult = (TVTOXExamResult) vDespliega.get(i);
				// int ivCveLoteCuantita = 0;
				cvNombre = "TBXSel-" + VTOXExamResult.getiAnio().toString()
						+ "-" + VTOXExamResult.getiCveLaboratorio().toString()
						+ "-" + VTOXExamResult.getiCveLoteCualita().toString()
						+ "-" + VTOXExamResult.getiCveExamCualita().toString()
						+ "-" + VTOXExamResult.getiCveAnalisis().toString()
						+ "-" + VTOXExamResult.getiCveSustancia().toString();

				cvNomDilucion = "FILDilucion-"
						+ VTOXExamResult.getiAnio().toString() + "-"
						+ VTOXExamResult.getiCveLaboratorio().toString() + "-"
						+ VTOXExamResult.getiCveLoteCualita().toString() + "-"
						+ VTOXExamResult.getiCveExamCualita().toString() + "-"
						+ VTOXExamResult.getiCveAnalisis().toString() + "-"
						+ VTOXExamResult.getiCveSustancia().toString();

				if (request.getParameter(cvNombre) != null) {
					if (request.getParameter(cvNombre).compareToIgnoreCase("1") == 0) {
						DTOXExamResult = new TDTOXExamResult();
						DTOXCuantAnalisis = new TDTOXCuantAnalisis();
						VTOXCuantAnalisis = new TVTOXCuantAnalisis();
						DTOXLoteCuantita = new TDTOXLoteCuantita();
						VTOXLoteCuantita = new TVTOXLoteCuantita();

						if (request.getParameter(cvNomDilucion) != null) {
							VTOXExamResult.setdDilucion(new Double(request
									.getParameter(cvNomDilucion)));
							ivDilucion = new Double(
									request.getParameter(cvNomDilucion))
									.intValue();
						}
						VTOXExamResult.setlAsignado(new Integer("1"));
						VTOXLoteCuantita.setiAnio(VTOXExamResult.getiAnio());
						VTOXLoteCuantita.setiCveLaboratorio(VTOXExamResult
								.getiCveLaboratorio());
						// Se asigna la sustancia del objeto que se encontró.
						VTOXLoteCuantita.setiCveSustancia(VTOXExamResult
								.getiCveSustancia());

						iCveSustancia = VTOXExamResult.getiCveSustancia()
								.intValue();

						// Cuando se tiene que agregar un Lote Nuevo.
						if (ivLoteCuantita == 0) {
							if (ivCveLoteCuantita == 0) {
								VTOXLoteCuantita.setiCveSustancia(Integer
										.valueOf(String.valueOf(ivSustancia)));
								ivCveLoteCuantita = DTOXLoteCuantita
										.findLastForSequence(VTOXLoteCuantita);
								ivLoteCuantita = ivCveLoteCuantita;
							}
						}
						// Cuando se tiene que agregar elementos al Lote
						// Existente.
						else {
							ivCveLoteCuantita = ivLoteCuantita;
							lvGenerado = true;
						}
						VTOXLoteCuantita.setiCveLoteCuantita(new Integer(
								ivCveLoteCuantita));
						VTOXCuantAnalisis.setiAnio(VTOXExamResult.getiAnio());
						VTOXCuantAnalisis.setiCveSustancia(VTOXExamResult
								.getiCveSustancia());
						// Valor del Lote.
						VTOXCuantAnalisis.setiCveLoteCuantita(new Integer(
								ivCveLoteCuantita));
						VTOXCuantAnalisis.setiCveAnalisis(VTOXExamResult
								.getiCveAnalisis());
						VTOXCuantAnalisis.setiCveLaboratorio(VTOXExamResult
								.getiCveLaboratorio());
						VTOXCuantAnalisis.setiDilusion(new Integer(ivDilucion));
						// Generar el lote.
						if (!lvGenerado) {
							try {
								for (int s = 0; s < aSustancias.size(); s++) {
									VTOXLoteCuantita.setiCveSustancia(Integer
											.valueOf((String) aSustancias
													.get(s)));
									DTOXLoteCuantita.insert(VTOXLoteCuantita);
								}
								lvGenerado = true;
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						try {
							// Actualizar los resultados para el análisis
							// asignado. Todas las sustancias unificadas
							DTOXExamResult.update(VTOXExamResult,
									" and E.iCveSustancia in (" + cUnifica
											+ ")");
							// Para cada sustancia se integra un análisis
							for (int s = 0; s < aSustancias.size(); s++) {
								VTOXCuantAnalisis.setiCveSustancia(Integer
										.valueOf((String) aSustancias.get(s)));
								DTOXCuantAnalisis.insert(VTOXCuantAnalisis);
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			// Realizar validación del tamaño del lote
			try {
				Vector vValidacion = DTOXLoteCuantita.MuestrasXLote(null,
						VTOXLoteCuantita);
				if (vValidacion != null && vValidacion.size() > 0) {
					TVTOXLoteCuantita VLote = ((TVTOXLoteCuantita) vValidacion
							.get(0));
					cLote = cvAnio + "-" + VLote.VSustancia.getCPrefLoteConf()
							+ "-" + ivCveLoteCuantita;
					// Generar los mensajes del Lote.
					if (!lvGenerado) {
						this.vErrores.acumulaError(
								"Se Generó el Lote " + cLote, 0, "");
					} else {
						this.vErrores.acumulaError("Se Actualizó el Lote "
								+ cLote, 0, "");
					}
					if (VLote.getINumMuestras() == VLote.VSustancia
							.getIMuestrasLC()) {
						this.vErrores.acumulaError(
								"El número de muestras ingresadas al lote llego al límite indicado de "
										+ VLote.VSustancia.getIMuestrasLC()
										+ ".", 0, "");
					}
					if (VLote.getINumMuestras() > VLote.VSustancia
							.getIMuestrasLC()) {
						this.vErrores.acumulaError(
								"El número de muestras ingresadas al lote excede al límite indicado de "
										+ VLote.VSustancia.getIMuestrasLC()
										+ ".", 0, "");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} // Se tienen elementos en el Vector.

		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();
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
		if (request.getParameter("SLSLoteConfirmativo") != null) {
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();
		}
	}

	public void fillVector() {

		cPaginas = "pg070304011.jsp|";
		String cCondicion2 = "";

		TDTOXExamResult dTOXExamResult = new TDTOXExamResult();

		if (cCondicion.compareTo("") != 0) {
			cCondicion2 = " and " + cCondicion;
		}
		try {
			// Buscar la información de la Sustancia.
			Vector vSustancia = new TDTOXSustancia().FindByAll(
					" where iCveSustancia = " + ivSustancia, "");
			if (vSustancia.size() > 0) {
				String cUnifica = ((TVTOXSustancia) (vSustancia.get(0)))
						.getCSustUnifica();
				// Verificar el número de sustancias que integra el lote.
				if (cUnifica.indexOf(",") > 0)
					this.iSustUnifica = 1;
				cCondicion = " join TOXAnalisis "
						+ " on TOXAnalisis.iAnio = TOXExamResult.iAnio "
						+ " and TOXAnalisis.iCveLaboratorio = TOXExamResult.iCveLaboratorio "
						+ " and TOXAnalisis.iCveAnalisis    = TOXExamResult.iCveAnalisis "
						+ " and TOXAnalisis.iCveLoteCualita = TOXExamResult.iCveLoteCualita "
						+ " and TOXAnalisis.iCveExamCualita = TOXExamResult.iCveExamCualita "
						+ " and TOXAnalisis.lPresuntoPost = 1 "
						+ " and TOXAnalisis.lControl = 0 "
						+ " inner join TOXSustancia S on S.iCveSustancia = TOXExamResult.iCveSustancia "
						+ " where TOXExamResult.iAnio         =   "
						+ cvAnio
						+ " and TOXExamResult.iCveLaboratorio =   "
						+ ivCveLaboratorio
						+ " and TOXExamResult.iCveSustancia   in   ("
						+ ((TVTOXSustancia) (vSustancia.get(0)))
								.getCSustUnifica() + ")"
						+ " and TOXExamResult.iAsignado       = 0 "
						+ " and ( TOXExamResult.lPositivo       = 1 "
						+ " or  TOXExamResult.lDudoso         = 1 ) ";

				if (cOrden.compareTo("") != 0) {
					cCondicion2 += cOrden;
				} else {
					cCondicion2 += " order by iCveLoteCualita ";
				}
				cCondicion2 += ", iCveAnalisis ";
				vDespliega = dTOXExamResult.FindByAll(cCondicion + cCondicion2);
			}
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Imprimir";
			if (NavStatus.compareToIgnoreCase("Disabled") == 0) {
				NavStatus = "FirstRecord";
			}
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

	}

	public int getISustUnifica() {
		return iSustUnifica;
	}

	public void setISustUnifica(int iSustUnifica) {
		this.iSustUnifica = iSustUnifica;
	}
}
