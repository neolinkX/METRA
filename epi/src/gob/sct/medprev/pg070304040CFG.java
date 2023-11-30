package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070304040CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vCalibEquipo = new Vector();
	public Vector vCalib = new Vector();
	Vector vCalibEquipo2 = new Vector();
	int ivSustancia = 0;
	int ivCveLaboratorio = 0;
	int ivLoteCuantita = 0;
	int ivEquipo = 0;
	// String dtCalEquipo;
	java.sql.Date dtCalEquipo;
	TVUsuario vUsuario = new TVUsuario();

	public pg070304040CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		TDTOXCalibEquipo DTOXCalibEquipo = new TDTOXCalibEquipo();
		TFechas Fecha = new TFechas();

		if (request.getParameter("iCveUniMed") != null)
			ivCveLaboratorio = new Integer(request.getParameter("iCveUniMed"))
					.intValue();

		if (request.getParameter("SLSEquipo") != null)
			ivEquipo = new Integer(request.getParameter("SLSEquipo").toString())
					.intValue();

		if (request.getParameter("dtCalEquipo") != null)
			dtCalEquipo = Fecha.getDateSQL(request.getParameter("dtCalEquipo")
					.toString());
		else
			dtCalEquipo = Fecha.TodaySQL();

		// Extracción de los Registros del Encabezado de la Calibración del
		// Equipo.
		try {
			vCalibEquipo = DTOXCalibEquipo.FindByAll(" where iCveEquipo = "
					+ ivEquipo
					+ "   and iCveCalib  = (select max(iCveCalib)    "
					+ "                      from toxcalibequipo     "
					+ "                      where iCveEquipo   =    "
					+ ivEquipo
					+ "                        and fecha        =    '"
					+ dtCalEquipo + "'" +

					"                        and iCveUsuCalib = 0 )"
					+ "   and fecha      = '" + dtCalEquipo + "'"
					+ "   and iCveUsuCalib = 0 ");
			vCalibEquipo2 = vCalibEquipo;
			vUsuario = (TVUsuario) httpReq.getSession().getAttribute("UsrID");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Guardar() {
		TDTOXParamCalib DTOXParamCalib = new TDTOXParamCalib();
		TDTOXCalibEquipo DTOXCalibEquipo = new TDTOXCalibEquipo();
		TDTOXCalib DTOXCalib = new TDTOXCalib();
		Vector vTOXParamCalib = new Vector();
		TFechas Fecha = new TFechas();
		int ivMayores = 0;

		try {
			vTOXParamCalib = DTOXParamCalib
					.FindByAll(" where iCveLaboratorio = " + ivCveLaboratorio
							+ "   and lActivo         = 1 " + "");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (!vCalibEquipo2.isEmpty())
			for (int i = 0; i < vCalibEquipo2.size(); i++) {
				TVTOXCalibEquipo VTOXCalibEquipo = new TVTOXCalibEquipo();
				VTOXCalibEquipo = (TVTOXCalibEquipo) vCalibEquipo2.get(i);

				if (request.getParameter("TBXCorrecto") != null)
					VTOXCalibEquipo.setlCorrecto(new Integer(request
							.getParameter("TBXCorrecto").toString()));

				for (int j = 0; j < vTOXParamCalib.size(); j++) {
					TVTOXParamCalib VTOXParamCalib = new TVTOXParamCalib();
					VTOXParamCalib = (TVTOXParamCalib) vTOXParamCalib.get(j);
					TVTOXCalib VTOXCalib = new TVTOXCalib();

					VTOXCalib.setiCveEquipo(VTOXCalibEquipo.getiCveEquipo());
					VTOXCalib.setiCveCalib(VTOXCalibEquipo.getiCveCalib());
					VTOXCalib.setiCveLaboratorio(new Integer(ivCveLaboratorio));
					VTOXCalib.setiCveParamCalib(VTOXParamCalib
							.getiCveParamCalib());
					if (request.getParameter("FILValor-"
							+ VTOXParamCalib.getiCveParamCalib().toString()) != null)
						VTOXCalib.setdValor(new Double(request
								.getParameter("FILValor-"
										+ VTOXParamCalib.getiCveParamCalib()
												.toString())));
					else
						VTOXCalib.setdValor(new Double("0"));
					System.out.println("Información a validar = "
							+ VTOXParamCalib.getdValorMin().doubleValue()
							+ " min " + VTOXCalib.getdValor().doubleValue()
							+ " - "
							+ VTOXParamCalib.getDValorMax().doubleValue()
							+ " max");
					if (VTOXCalib.getdValor().doubleValue() < VTOXParamCalib
							.getdValorMin().doubleValue()
							|| VTOXCalib.getdValor().doubleValue() > VTOXParamCalib
									.getDValorMax().doubleValue())
						ivMayores = ivMayores + 1;

					try {
						DTOXCalib.update(VTOXCalib);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// System.out.println("Mayores = " + ivMayores);
				if (request.getParameter("TBXValidacion") != null) {
					if (request.getParameter("TBXValidacion").toString()
							.compareToIgnoreCase("1") == 0) {
						if (ivMayores > 0) {
							VTOXCalibEquipo.setlCorrecto(new Integer("0"));
							this.vErrores
									.acumulaError(
											"La calibración fué Incorrecta. Favor de calibrar nuevamente el equipo.",
											0, "");
						} else {
							VTOXCalibEquipo.setlCorrecto(new Integer("1"));
							this.vErrores.acumulaError(
									"La calibración fué Correcta.", 0, "");
						}
						VTOXCalibEquipo.setFecha(Fecha.TodaySQL());
						VTOXCalibEquipo.setiCveUsuCalib(new Integer(vUsuario
								.getICveusuario()));

					} else {
						VTOXCalibEquipo.setiCveUsuCalib(new Integer("0"));
						VTOXCalibEquipo.setFecha(Fecha.TodaySQL());
					}
				} else {
					VTOXCalibEquipo.setiCveUsuCalib(new Integer("0"));
					VTOXCalibEquipo.setFecha(Fecha.TodaySQL());
				}
				// Actualización de la observación
				if (request.getParameter("cObservacion") != null) {
					VTOXCalibEquipo.setcObservacion(request.getParameter(
							"cObservacion").toString());
				}
				try {
					DTOXCalibEquipo.update(VTOXCalibEquipo);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		else { // No Existe información de la calibración
			TVTOXCalibEquipo VTOXCalibEquipo = new TVTOXCalibEquipo();
			VTOXCalibEquipo.setiCveEquipo(new Integer(ivEquipo));
			int ivCveCalib = 0;

			try {
				ivCveCalib = DTOXCalibEquipo
						.findLastForSequence(VTOXCalibEquipo);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			VTOXCalibEquipo.setiCveEquipo(new Integer(ivEquipo));
			VTOXCalibEquipo.setiCveCalib(new Integer(ivCveCalib));
			VTOXCalibEquipo.setFecha(dtCalEquipo);
			VTOXCalibEquipo.setiCveUsuCalib(new Integer("0"));
			VTOXCalibEquipo.setcObservacion("");
			VTOXCalibEquipo.setiCveAcCorrectiva(new Integer("0"));

			if (request.getParameter("TBXCorrecto") != null)
				VTOXCalibEquipo.setlCorrecto(new Integer(request.getParameter(
						"TBXCorrecto").toString()));
			else
				VTOXCalibEquipo.setlCorrecto(new Integer("0"));

			try {
				DTOXCalibEquipo.insert(VTOXCalibEquipo);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			for (int j = 0; j < vTOXParamCalib.size(); j++) {
				TVTOXParamCalib VTOXParamCalib = new TVTOXParamCalib();
				TVTOXCalib VTOXCalib = new TVTOXCalib();
				VTOXParamCalib = (TVTOXParamCalib) vTOXParamCalib.get(j);

				VTOXCalib.setiCveEquipo(new Integer(ivEquipo));
				VTOXCalib.setiCveCalib(new Integer(ivCveCalib));
				VTOXCalib.setiCveLaboratorio(new Integer(ivCveLaboratorio));
				VTOXCalib.setiCveParamCalib(VTOXParamCalib.getiCveParamCalib());
				if (request.getParameter("FILValor-"
						+ VTOXParamCalib.getiCveParamCalib().toString()) != null)
					VTOXCalib.setdValor(new Double(request
							.getParameter("FILValor-"
									+ VTOXParamCalib.getiCveParamCalib()
											.toString())));
				else
					VTOXCalib.setdValor(new Double("0"));
				try {
					DTOXCalib.insert(VTOXCalib);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		// Extracción de los Registros del Encabezado de la Calibración del
		// Equipo.
		try {
			vCalibEquipo = DTOXCalibEquipo.FindByAll(" where iCveEquipo = "
					+ ivEquipo
					+ "   and iCveCalib  = (select max(iCveCalib)    "
					+ "                      from toxcalibequipo     "
					+ "                      where iCveEquipo   =    "
					+ ivEquipo
					+ "                        and fecha        =    '"
					+ dtCalEquipo + "'"
					+ "                        and iCveUsuCalib = 0 )"
					+ "   and fecha      = '" + dtCalEquipo + "'"
					+ "   and iCveUsuCalib = 0 ");
			vCalibEquipo2 = vCalibEquipo;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void fillVector() {

		cPaginas = "pg070304041.jsp|";

		TDTOXParamCalib DTOXParamCalib = new TDTOXParamCalib();
		TDTOXCalib DTOXCalib = new TDTOXCalib();

		if (cCondicion.compareTo("") != 0) {
			cCondicion = " where " + cCondicion;
		}

		cCondicion = " where iCveLaboratorio = " + ivCveLaboratorio
				+ "   and lActivo         = 1 " + "";

		if (cOrden.compareTo("") != 0) {
			cCondicion = cCondicion + cOrden;
		}

		try {
			vDespliega = DTOXParamCalib.FindByAll(cCondicion);
			iNumReg = vDespliega.size() + 1;
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Reporte";
			NavStatus = "Disabled";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Disabled";
		}

		// Extracción de los Datos del Detalle de la Calibración del Equipo.
		if (!vCalibEquipo2.isEmpty()) {
			TVTOXCalibEquipo VTOXCalibEquipo = new TVTOXCalibEquipo();
			VTOXCalibEquipo = (TVTOXCalibEquipo) vCalibEquipo2.get(0);

			try {
				vCalib = DTOXCalib.FindByAll(" where iCveEquipo      = "
						+ ivEquipo + "   and iCveCalib       = "
						+ VTOXCalibEquipo.getiCveCalib()
						+ "   and iCveLaboratorio = " + ivCveLaboratorio);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
