package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304070CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vLoteCuantita = new Vector();
	public Vector vCalibCuantita = new Vector();
	public Vector vCuantAnalisis = new Vector();
	String cvAnio = "";
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivEquipo = 0;
	private int iSustUnifica;

	// TVUsuario vUsuario = (TVUsuario)
	// httpReq.getSession().getAttribute("UsrID");

	public pg070304070CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		TDTOXLoteCuantita dLoteCuantita = new TDTOXLoteCuantita();
		TDTOXCalibCuantita dCalibCuantita = new TDTOXCalibCuantita();

		if (request.getParameter("SLSAnio") != null)
			cvAnio = request.getParameter("SLSAnio");

		if (cvAnio.compareToIgnoreCase("") == 0)
			cvAnio = "2004";

		if (request.getParameter("iCveUniMed") != null)
			ivCveLaboratorio = new Integer(request.getParameter("iCveUniMed"))
					.intValue();

		if (request.getParameter("SLSSustancia") != null)
			ivSustancia = new Integer(request.getParameter("SLSSustancia")
					.toString()).intValue();

		if (request.getParameter("SLSLoteConfirmativo") != null)
			ivLoteCuantita = new Integer(request.getParameter(
					"SLSLoteConfirmativo").toString()).intValue();

		if (ivSustancia != 0) {

			try {

				vLoteCuantita = dLoteCuantita
						.FindByAll(" where TOXLoteCuantita.iAnio           =           "
								+ cvAnio
								+ "   and TOXLoteCuantita.iCveLaboratorio =           "
								+ ivCveLaboratorio
								+ "   and TOXLoteCuantita.iCveSustancia   =           "
								+ ivSustancia
								+ "   and TOXLoteCuantita.dtGeneracion    is not null "
								+ "   and TOXLoteCuantita.iCveUsuGenera   is not null "
								+ "   and TOXLoteCuantita.dtCalibracion   is not null "
								+ "   and TOXLoteCuantita.lValidaCalib = 1            "
								+ "   and TOXLoteCuantita.dtAnalisis      is not null "
								+ "   and TOXLoteCuantita.dtAutorizacion  is null     "
								+ " ");

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		try {

			vCalibCuantita = dCalibCuantita
					.FindByAll(" where iCveLaboratorio = "
							+ ivCveLaboratorio
							+ "   and iCveCalib = (select max(iCveCalib) from TOXCalibCuantita "
							+ "                     where iCveLaboratorio = "
							+ ivCveLaboratorio + " )");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void Guardar() {
		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		TDTOXLoteCuantita DTOXLoteCuantita = new TDTOXLoteCuantita();
		TDTOXExamResult DTOXExamResult = new TDTOXExamResult();
		TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();
		TFechas Fecha = new TFechas();
		Vector vCuantAnalisis = new Vector();
		Vector vLoteCuantita = new Vector();
		String cCond = "";
		String cvNombre = "";
		String cvObsNombre = "";
		String cvDilNombre = "";

		Vector vSustancia = new Vector();
		try {
			vSustancia = new TDTOXSustancia().FindByAll(
					" where iCveSustancia = " + ivSustancia, "");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (vSustancia.size() > 0) {
			cCond = " where iAnio          =   " + cvAnio
					+ " and iCveLaboratorio  =   " + ivCveLaboratorio
					+ " and iCveSustancia    in  (" + ivSustancia + ") "
					+ " and iCveLoteCuantita =   " + ivLoteCuantita
					+ " and iCveAnalisis    >= 0 " + "";

			try {
				vCuantAnalisis = DTOXCuantAnalisis.FindByAll(cCond);
			} catch (Exception e) {
				vErrores.acumulaError("", 16, "");
				vDespliega = new Vector();
			}

			if (!vCuantAnalisis.isEmpty()) {
				for (int i = 0; i < vCuantAnalisis.size(); i++) {
					TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
					TVTOXCuantAnalisis VTOXCuantAnalisisSet = new TVTOXCuantAnalisis();
					VTOXCuantAnalisis = (TVTOXCuantAnalisis) vCuantAnalisis
							.get(i);

					cvNombre = "TBXSel-"
							+ VTOXCuantAnalisis.getiCveAnalisis().toString();
					cvObsNombre = "TXTObservacion-"
							+ VTOXCuantAnalisis.getiCveAnalisis().toString();
					cvDilNombre = "FILDilucion-"
							+ VTOXCuantAnalisis.getiCveAnalisis().toString();

					// Escribir la Situación de la Muestra: Autorizada o
					// Reanalisis.
					if (request.getParameter(cvNombre) != null) {
						if (request.getParameter(cvNombre).compareToIgnoreCase(
								"1") == 0) {
							VTOXCuantAnalisisSet
									.setlAutorizado(new Integer("0"));
						} else
							VTOXCuantAnalisisSet
									.setlAutorizado(new Integer("1"));

					} else
						VTOXCuantAnalisisSet.setlAutorizado(new Integer("1"));

					// Escribir las Observaciones de Cada Muestra.
					if (request.getParameter(cvObsNombre) != null) {
						VTOXCuantAnalisisSet.setcObservacion(request
								.getParameter(cvObsNombre));
					}

					// Escribir las Dilusiones de Cada Muestra.
					if (request.getParameter(cvDilNombre) != null) {
						if (request.getParameter(cvDilNombre).toString()
								.compareToIgnoreCase("D") == 0)
							VTOXCuantAnalisisSet.setiDilusion(new Integer("1"));
						else
							VTOXCuantAnalisisSet.setiDilusion(new Integer(
									request.getParameter(cvDilNombre)));
					} else
						VTOXCuantAnalisisSet.setiDilusion(new Integer("0"));

					VTOXCuantAnalisisSet.setiAnio(new Integer(cvAnio));
					VTOXCuantAnalisisSet.setiCveSustancia(new Integer(
							ivSustancia));
					VTOXCuantAnalisisSet.setiCveLoteCuantita(new Integer(
							ivLoteCuantita));
					VTOXCuantAnalisisSet.setiCveAnalisis(VTOXCuantAnalisis
							.getiCveAnalisis());
					VTOXCuantAnalisisSet.setlResultado(VTOXCuantAnalisis
							.getlResultado());
					VTOXCuantAnalisisSet.setiCveLaboratorio(new Integer(
							ivCveLaboratorio));

					// Actualización de las Muestras.
					try {
						DTOXCuantAnalisis.updAutorizacion(VTOXCuantAnalisisSet);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} // Por cada registro

				// Condición para la Liberación de las muestras.
				String cCondicUpd = " update TOXExamResult "
						+ " set TOXExamResult.iAsignado = 0 "
						+ " where TOXExamResult.iAnio = "
						+ cvAnio
						+ " and   TOXExamResult.iCveLaboratorio = "
						+ ivCveLaboratorio
						+ " and   TOXExamResult.iCveLoteCualita in (select distinct TOXAnalisis.iCveLoteCualita "
						+ "                                         from TOXLoteCuantita "
						+ "                                         join TOXCuantAnalisis "
						+ "                                         on   TOXCuantAnalisis.iAnio            = TOXLoteCuantita.iAnio "
						+ "                                         and  TOXCuantAnalisis.iCveSustancia    = TOXLoteCuantita.iCveSustancia "
						+ "                                         and  TOXCuantAnalisis.iCveLoteCuantita = TOXLoteCuantita.iCveLoteCuantita "
						+ "                                         and  TOXCuantAnalisis.iCveLaboratorio  = TOXLoteCuantita.iCveLaboratorio "
						+ "                                         join TOXAnalisis  "
						+ "                                         on   TOXAnalisis.iAnio           = TOXCuantAnalisis.iAnio "
						+ "                                         and  TOXAnalisis.iCveLaboratorio = TOXCuantAnalisis.iCveLaboratorio "
						+ "                                         and  TOXAnalisis.iCveAnalisis    = TOXCuantAnalisis.iCveAnalisis "
						+ "                                         where TOXLoteCuantita.iAnio = "
						+ cvAnio
						+ "                                         and   TOXLoteCuantita.iCveLaboratorio = "
						+ ivCveLaboratorio
						+ "                                         and   TOXLoteCuantita.iCveSustancia = "
						+ ivSustancia
						+ "                                         and   TOXLoteCuantita.iCveLoteCuantita = "
						+ ivLoteCuantita
						+ " ) "
						+ " and   TOXExamResult.iCveSustancia = "
						+ ivSustancia
						+ " and   TOXExamResult.iCveAnalisis  in (select TOXCuantAnalisis.iCveAnalisis "
						+ "                                       from   TOXCuantAnalisis "
						+ "                                       where  TOXCuantAnalisis.iAnio            =     "
						+ cvAnio
						+ "                                         and  TOXCuantAnalisis.iCveSustancia    =     "
						+ ivSustancia
						+ "                                         and  TOXCuantAnalisis.iCveLoteCuantita =     "
						+ ivLoteCuantita
						+ "                                         and  TOXCuantAnalisis.iCveLaboratorio  =     "
						+ ivCveLaboratorio
						+ "                                         and  TOXCuantAnalisis.lAutorizado      =  0 )"
						+ " and   TOXExamResult.iAsignado     = 1 " + "";

				// Activación de las Muestras del Lote, para poder ser asignadas
				// a otro.
				try {
					DTOXExamResult.ActivarMuestras(cCondicUpd);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				VTOXLoteCuantita.setiAnio(new Integer(cvAnio));
				VTOXLoteCuantita.setiCveLaboratorio(new Integer(
						ivCveLaboratorio));
				VTOXLoteCuantita.setiCveSustancia(new Integer(ivSustancia));
				VTOXLoteCuantita
						.setiCveLoteCuantita(new Integer(ivLoteCuantita));
				VTOXLoteCuantita.setdtAutorizacion(Fecha.TodaySQL());
				TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
						.getAttribute("UsrID");
				VTOXLoteCuantita.setiCveUsuEncarga(new Integer(vUsuario
						.getICveusuario()));
				try {
					DTOXLoteCuantita.updAutorizacion(VTOXLoteCuantita);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}// Existen muestras presentadas
		}// Existe la sustancia solicitada
	}

	public void fillVector() {
		cPaginas = "pg070304031.jsp|";
		StringBuffer cFiltro;
		TDTOXCuantAnalisis DTOXCuantAnalisis = new TDTOXCuantAnalisis();
		if (cCondicion.compareTo("") != 0) {
			cCondicion = " where " + cCondicion;
		}
		Vector vSustancia = new Vector();
		try {
			vSustancia = new TDTOXSustancia().FindByAll(
					" where iCveSustancia = " + ivSustancia, "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (vSustancia.size() > 0) {
			String cUnifica = ((TVTOXSustancia) (vSustancia.get(0)))
					.getCSustUnifica();
			// Verificar el número de sustancias que integra el lote.
			if (cUnifica.indexOf(",") > 0)
				this.iSustUnifica = 1;

			if (ivLoteCuantita != 0) {
				cCondicion = " where iAnio          =   " + cvAnio
						+ " and TOXCuantAnalisis.iCveSustancia    in  ("
						+ cUnifica + ")" + " and iCveLoteCuantita =   "
						+ ivLoteCuantita + " and iCveLaboratorio  =   "
						+ ivCveLaboratorio + " and iCveAnalisis    >= 0 " + "";
				try {
					cFiltro = new StringBuffer();
					cFiltro.append("where L.iAnio = ").append(cvAnio)
							.append("  and L.iCveLaboratorio  = ")
							.append(ivCveLaboratorio)
							.append("  and L.iCveSustancia    = ")
							.append(ivSustancia)
							.append("  and L.iCveLoteCuantita = ")
							.append(ivLoteCuantita);
					vCuantAnalisis = DTOXCuantAnalisis.FindCalibra(cFiltro
							.toString());
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (cOrden.compareTo("") != 0) {
					cCondicion = cCondicion + cOrden;
				}

				try {
					vDespliega = DTOXCuantAnalisis.FindAllSustancia(cCondicion);
				} catch (Exception e) {
					vErrores.acumulaError("", 16, "");
					vDespliega = new Vector();
				}
			}

			if (!vDespliega.isEmpty()) {
				UpdStatus = "SaveCancelOnly";
				cImprimir = "Reporte";
			} else
				UpdStatus = "Hidden";
		}
	}

	public int getISustUnifica() {
		return iSustUnifica;
	}

	public void setISustUnifica(int iSustUnifica) {
		this.iSustUnifica = iSustUnifica;
	}
}
