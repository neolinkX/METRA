package gob.sct.medprev;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

public class pg070101071CFG extends CFGListBase2 {
	int iCveSistema = 07;
	public String cImprimir = "";
	public Vector vGRLMdoTrans = new Vector();
	public Vector vMEDSintoma = new Vector();
	public Vector vMEDSintExamen = new Vector();
	public int ivCuantos = 0;
	// public Vector vAnalisis = new Vector();
	int ivProceso = 0;
	int ivMotivo = 0;
	int ivServicio = 0;
	int ivRama = 0;

	public pg070101071CFG() {
		vParametros = new TParametro("07");
	}

	public void mainBlock() {

		if (request.getParameter("SLSProceso") != null)
			ivProceso = new Integer(request.getParameter("SLSProceso"))
					.intValue();

		if (request.getParameter("SLSMotivo") != null)
			ivMotivo = new Integer(request.getParameter("SLSMotivo"))
					.intValue();

		if (request.getParameter("SLSServicio") != null)
			ivServicio = new Integer(request.getParameter("SLSServicio"))
					.intValue();

		if (request.getParameter("SLSRama") != null)
			ivRama = new Integer(request.getParameter("SLSRama")).intValue();

	}

	public void Guardar() {

		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDMEDSintoma DMEDSintoma = new TDMEDSintoma();
		TDMEDSintExamen DMEDSintExamen = new TDMEDSintExamen();
		Vector vDespliega2 = new Vector();
		Vector vDatos = new Vector();
		boolean lBorrado = false;

		try {
			vGRLMdoTrans = DGRLMdoTrans.findByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ivCuantos = vGRLMdoTrans.size() + 1;

		try {
			vDespliega2 = DMEDSintoma
					.FindByCustomWhere(" where MEDSintomas.iCveServicio =   "
							+ ivServicio
							+ "   and MEDSintomas.iCveRama      =   " + ivRama
							+ "   and MEDSintomas.lActivo       = 1 "
							+ "  order by iCveServicio, iCveRama, iCveSintoma ");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!vDespliega2.isEmpty()) {
			for (int i = 0; i < vDespliega2.size(); i++) {
				TVMEDSintoma VMEDSintoma = new TVMEDSintoma();
				VMEDSintoma = (TVMEDSintoma) vDespliega2.get(i);

				lBorrado = false;

				if (request.getParameter("TBXGral-"
						+ VMEDSintoma.getICveSintoma()) != null) {
					if (new Integer(request.getParameter("TBXGral-"
							+ VMEDSintoma.getICveSintoma())).intValue() == 1) {

						// Eliminación de los Registros en caso de Tener
						// previos.
						try {
							DMEDSintExamen
									.deleteCustomWhere(
											null,
											" delete from MEDSintExamen "
													+ " where iCveProceso  =      "
													+ ivProceso
													+ "   and iCveMotivo   =      "
													+ ivMotivo
													+ "   and iCveServicio =      "
													+ ivServicio
													+ "   and iCveRama     =      "
													+ ivRama
													+ "   and iCveSintoma  =      "
													+ VMEDSintoma
															.getICveSintoma()
													+ "");
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						// Valores de Identificación
						TVMEDSintExamen VMEDSintExamen = new TVMEDSintExamen();
						VMEDSintExamen.setICveProceso(ivProceso);
						VMEDSintExamen.setICveMotivo(ivMotivo);
						VMEDSintExamen.setICveServicio(VMEDSintoma
								.getICveServicio());
						VMEDSintExamen.setICveRama(VMEDSintoma.getICveRama());
						VMEDSintExamen.setICveSintoma(VMEDSintoma
								.getICveSintoma());
						// Colocando valores para el General.
						VMEDSintExamen.setICveSintoma(VMEDSintoma
								.getICveSintoma());
						VMEDSintExamen.setICveMdoTrans(0);

						try {
							DMEDSintExamen.insert(null, VMEDSintExamen);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						if (!vGRLMdoTrans.isEmpty()) {
							// Valores de Identificación
							TVMEDSintExamen VMEDSintExamenAdic = new TVMEDSintExamen();
							VMEDSintExamenAdic.setICveProceso(ivProceso);
							VMEDSintExamenAdic.setICveMotivo(ivMotivo);
							VMEDSintExamenAdic.setICveServicio(VMEDSintoma
									.getICveServicio());
							VMEDSintExamenAdic.setICveRama(VMEDSintoma
									.getICveRama());
							VMEDSintExamenAdic.setICveSintoma(VMEDSintoma
									.getICveSintoma());

							for (int j = 0; j < vGRLMdoTrans.size(); j++) {
								TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
								VGRLMdoTrans = (TVGRLMdoTrans) vGRLMdoTrans
										.get(j);

								// Colocando valores para el General.
								VMEDSintExamen.setICveSintoma(VMEDSintoma
										.getICveSintoma());
								VMEDSintExamen.setICveMdoTrans(VGRLMdoTrans
										.getICveMdoTrans());

								try {
									DMEDSintExamen.insert(null, VMEDSintExamen);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						}
					}
				} else {
					if (!vGRLMdoTrans.isEmpty()) {

						// Valores de Identificación
						TVMEDSintExamen VMEDSintExamen = new TVMEDSintExamen();
						VMEDSintExamen.setICveProceso(ivProceso);
						VMEDSintExamen.setICveMotivo(ivMotivo);
						VMEDSintExamen.setICveServicio(VMEDSintoma
								.getICveServicio());
						VMEDSintExamen.setICveRama(VMEDSintoma.getICveRama());
						VMEDSintExamen.setICveSintoma(VMEDSintoma
								.getICveSintoma());

						// Eliminación de los Registros en caso de Tener
						// previos.
						try {
							DMEDSintExamen
									.deleteCustomWhere(
											null,
											" delete from MEDSintExamen "
													+ " where iCveProceso  =      "
													+ ivProceso
													+ "   and iCveMotivo   =      "
													+ ivMotivo
													+ "   and iCveServicio =      "
													+ VMEDSintoma
															.getICveServicio()
													+ "   and iCveRama     =      "
													+ VMEDSintoma.getICveRama()
													+ "   and iCveSintoma  =      "
													+ VMEDSintoma
															.getICveSintoma()
													+ "");

						} catch (Exception ex) {
							ex.printStackTrace();
						}

						for (int j = 0; j < vGRLMdoTrans.size(); j++) {
							TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
							VGRLMdoTrans = (TVGRLMdoTrans) vGRLMdoTrans.get(j);

							// Valores Seleccionados.
							if (request.getParameter("TBXSel-"
									+ new Integer(VMEDSintoma.getICveSintoma())
											.toString()
									+ new Integer(VGRLMdoTrans
											.getICveMdoTrans()).toString()) != null) {
								if (request.getParameter(
										"TBXSel-"
												+ new Integer(VMEDSintoma
														.getICveSintoma())
														.toString()
												+ new Integer(VGRLMdoTrans
														.getICveMdoTrans())
														.toString())
										.compareToIgnoreCase("1") == 0) {
									VMEDSintExamen.setICveMdoTrans(VGRLMdoTrans
											.getICveMdoTrans());
									try {
										String cValorI = (String) DMEDSintExamen
												.insert(null, VMEDSintExamen);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void fillVector() {

		cPaginas = "pg070101071.jsp|";

		TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
		TDMEDSintoma DMEDSintoma = new TDMEDSintoma();
		TDMEDSintExamen DMEDSintExamen = new TDMEDSintExamen();

		try {
			vGRLMdoTrans = DGRLMdoTrans.findByAll("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ivCuantos = vGRLMdoTrans.size() + 1;

		try {
			vMEDSintExamen = DMEDSintExamen
					.FindByAll(" where iCveProceso  = "
							+ ivProceso
							+ "   and iCveMotivo   = "
							+ ivMotivo
							+ "   and iCveServicio = "
							+ ivServicio
							+ "   and iCveRama     = "
							+ ivRama
							+ "  order by iCveProceso, iCveMotivo, iCveServicio, iCveRama, iCveSintoma, iCveMdoTrans ");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (cCondicion.compareTo("") != 0) {
			cCondicion = " where " + cCondicion;
		}

		if (cOrden.compareTo("") != 0) {
			cCondicion = cCondicion + cOrden;
		}

		try {

			vDespliega = DMEDSintoma
					.FindByCustomWhere(" where MEDSintomas.iCveServicio =   "
							+ ivServicio
							+ "   and MEDSintomas.iCveRama     =   " + ivRama
							+ "   and MEDSintomas.lActivo      = 1 "
							+ "  order by iCveServicio, iCveRama, iCveSintoma ");

			iNumReg = vDespliega.size() + 1;
		} catch (Exception e) {
			vErrores.acumulaError("", 16, "");
			vDespliega = new Vector();
		}

		if (!vDespliega.isEmpty()) {
			UpdStatus = "SaveCancelOnly";
			cImprimir = "Imprimir";
			NavStatus = "Hidden";
		} else {
			UpdStatus = "Hidden";
			NavStatus = "Hidden";
		}

	}
}