package gob.sct.medprev;

import java.util.*;
import java.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import com.micper.util.TFechas;
import com.micper.util.TExcel;
import com.micper.util.TNumeros;
import gob.sct.medprev.vo.TVTOXLoteCuantita;
import gob.sct.medprev.vo.TVMuestra;
import gob.sct.medprev.vo.TVTOXSustancia;

/**
 * * Clase de configuración para CFG de la Consulta de Positivos
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Itzia María del Carmen Sánchez Méndez.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070305060.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070305060.png'>
 */
public class pg070305060CFG extends CFGListBase2 {
	public Vector VRegistros = new Vector();
	private StringBuffer activeX = new StringBuffer("");
	private StringBuffer activeY = new StringBuffer("");
	TFechas Fecha = new TFechas();
	private boolean lConsPost;

	public pg070305060CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDExamenCualita dLote = new TDExamenCualita();
		cPaginas = "pg070305031.jsp|";
		boolean lWhere = false;
		try {
			this.lConsPost = this.getLPagina("pg070305065");
			int i = 1;
			boolean lDeshecho = false;
			if (request.getParameter("TBXDeshecho") != null)
				lDeshecho = true;

			if (cCondicion.compareToIgnoreCase("") != 0) {
				lWhere = true;
				cCondicion = " Where " + cCondicion;
			}
			if (request.getParameter("iAnio") != null) {
				if (lWhere)
					cCondicion += " and A.iAnio = "
							+ request.getParameter("iAnio");
				else {
					cCondicion = "  where A.iAnio = "
							+ request.getParameter("iAnio");
					lWhere = true;
				}
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("0") != 0) {
				if (lWhere)
					cCondicion += " and A.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
				else {
					cCondicion = " where LC.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio");
					lWhere = true;
				}
				if (lWhere)
					cCondicion += " and A.lResultado  = 1 ";
				else
					cCondicion = " where A.lResultado  = 1 ";
				if (request.getParameter("RSTipo") != null && !lDeshecho)
					i = Integer.parseInt(request.getParameter("RSTipo")
							.toString(), 10);
				switch (i) {
				case 1: // Confirmados
					cCondicion += " and A.iSustConf   >= A.iSustPost";
					break;
				case 2: // Pendientes
					cCondicion += " and A.iSustConf   < A.iSustPost";
					break;
				case 3: // Ambos
					break;
				}
				// El resultado debe estar autorizado y haber sido correcto y
				// positivo
				cCondicion += " and CA.lAutorizado = 1 and CA.lCorrecto   = 1  and CA.lResultado  = 1 ";
				// Rango de fechas en que fueron confirmados
				if (!lDeshecho
						&& request.getParameter("dtFechaI") != null
						&& request.getParameter("dtFechaF") != null
						&& request.getParameter("dtFechaI").toString().length() > 0
						&& request.getParameter("dtFechaF").toString().length() > 0) {
					cCondicion += " and LC.dtAnalisis between '"
							+ Fecha.getDateSQL(request.getParameter("dtFechaI")
									.toString())
							+ "'"
							+ " and '"
							+ Fecha.getDateSQL(request.getParameter("dtFechaF")
									.toString()) + "'";
				}
				if (request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveUniMed").toString()
								.compareTo("-1") != 0) {
					cCondicion += "   and M.iCveUniMed = "
							+ request.getParameter("iCveUniMed").toString();
					if (request.getParameter("iCveModulo") != null
							&& request.getParameter("iCveModulo").toString()
									.compareTo("-1") != 0) {
						cCondicion += "   and M.iCveModulo = "
								+ request.getParameter("iCveModulo").toString();
					}
				}

				// Agregar la condición de la situación de la muestra

				i = 1;
				if (request.getParameter("RSSituacion") != null
						&& request.getParameter("RSSituacion").toString()
								.length() > 0)
					i = Integer.parseInt(request.getParameter("RSSituacion")
							.toString());

				// se están solicitando las muestras para deshecho
				// System.out.println("Opción de Situación = " + i +
				// " Deshecho = " + lDeshecho);
				if (request.getParameter("TBXDeshecho") != null) {
					i = 2;
					if (request.getParameter("dtFechaAD") != null
							&& request.getParameter("dtFechaAD").toString()
									.length() > 0) {
						cCondicion += " and LC.dtAnalisis <='"
								+ Fecha.getDateSQL(request.getParameter(
										"dtFechaAD").toString()) + "'"
								+ " and A.dtDesecho is null";
					}
				} else { // No se solicita el desecho
					switch (i) {
					case 2: // En resguardo
						cCondicion += " and A.dtDesecho is null ";
						break;
					case 3: // Deshecho
						if (request.getParameter("dtFechaID") != null
								&& request.getParameter("dtFechaFD") != null
								&& request.getParameter("dtFechaID").toString()
										.length() > 0
								&& request.getParameter("dtFechaFD").toString()
										.length() > 0 && !lDeshecho) {
							cCondicion += " and A.dtDesecho between '"
									+ Fecha.getDateSQL(request.getParameter(
											"dtFechaID").toString())
									+ "'"
									+ " and '"
									+ Fecha.getDateSQL(request.getParameter(
											"dtFechaFD").toString()) + "'";
						} else
							cCondicion += " and A.dtDesecho is not null ";
						break;
					}// switch
				} // No se solicita el deshecho.

				// Agregar condición para una sustancia en específico
				if (request.getParameter("iCveSustancia") != null
						&& request.getParameter("iCveSustancia").toString()
								.compareTo("-1") != 0) {
					cCondicion += "   and CA.iCveSustancia = "
							+ request.getParameter("iCveSustancia").toString();
					// System.out.println("Agregando condición de la clave de la sustancia");
				}
				cCondicion += " order by A.iAnio, A.iCveAnalisis, LC.dtAnalisis ";
				// Realizar la búsqueda
				Connection conn = null;
				vDespliega = new TDTOXAnalisis().MuestrasPost(conn, cCondicion);
				VRegistros = vDespliega;
				this.iNumReg = VRegistros.size();
				// Verificar que se solicita Deshechar
				if (lDeshecho == true
						&& "Deshecho".equalsIgnoreCase(this.cAccionOriginal)) {
					// Enviar busqueda para asignar el usuario y la fecha de
					// deshecho.
					int iRegistros = new TDTOXAnalisis()
							.updateDeshecho(VRegistros);
					if (iRegistros > 0) {
						this.vErrores.acumulaError("Se deshecharon "
								+ iRegistros, 0, " muestras.");
						lDeshecho = true;
					} else {
						lDeshecho = false;
						this.vErrores.acumulaError("No se deshecharon ", 0,
								" muestras.");
					}
				}
				// Generar el Reporte de las Positivas
				if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
					this.activeX.append(this.GenReporte());
				}
				// Generar el Listado de las Positivas
				if ("Listado".equalsIgnoreCase(this.cAccionOriginal)
						|| (lDeshecho == true && "Deshecho"
								.equalsIgnoreCase(this.cAccionOriginal))) {
					this.activeY.append(this.GenListado());
				}
			} // Se determinó laboratorio
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	} // fillVector()

	public String getOtrasSust(Vector vSustancias) {
		StringBuffer cTexto = new StringBuffer();
		TVTOXLoteCuantita VAnalisis;
		for (int i = 0; i < vSustancias.size(); i++) {
			VAnalisis = new TVTOXLoteCuantita();
			VAnalisis = (TVTOXLoteCuantita) vSustancias.get(i);
			if (i > 0)
				cTexto.append("<br>");
			cTexto.append(VAnalisis.VSustancia.getCDscSustancia());
			cTexto.append(" - ")
					.append(VAnalisis.getiAnio())
					.append("/")
					.append(new TNumeros().getNumeroSinSeparador(
							VAnalisis.getiCveLoteCuantita(), 3))
					.append(" * ")
					.append(Fecha.getFechaDDMMYYYY(VAnalisis.getdtAnalisis(),
							"/"));
		}
		return cTexto.toString();
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getActiveY() {
		return this.activeY.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}

	public StringBuffer GenReporte() {
		TExcel Rep = new TExcel("07");
		TVMuestra VMuestra;
		TNumeros Numero = new TNumeros();
		StringBuffer buffer = new StringBuffer();
		String cNomArchivo = "RepPositivos_";
		int iReng = 9, iHoja = 1, iRengI;
		try {
			// Verificar que existan registros a Desplegar
			if (this.VRegistros.size() > 0) {
				// Buscar las sustancias a Presentar
				Vector vSustancias = new TDTOXSustancia()
						.FindByAll(
								" where lActivo = 1 and lAnalizada = 1 and lPositiva = 1 ",
								" order by iCveSustancia ");
				char Cel = 'F', CelS, CelI;
				HashMap hCeldaSust = new HashMap();
				StringBuffer cLote;
				CelI = CelS = Cel;
				// Generar columnas pra las sustancias a Desplegar
				for (int s = 0; s < vSustancias.size(); s++) {
					hCeldaSust.put(String.valueOf(((TVTOXSustancia) vSustancias
							.get(s)).getiCveSustancia()), String.valueOf(Cel));
					Rep.comDespliega(String.valueOf(Cel), iReng,
							((TVTOXSustancia) vSustancias.get(s))
									.getcDscSustancia());
					CelS = (char) (Cel + 1);
					Rep.comAlineaRango(String.valueOf(Cel), iReng,
							String.valueOf(CelS), iReng,
							Rep.getAT_COMBINA_CENTRO());
					Cel = (char) (Cel + 2);
				}
				Rep.comAlineaRango(String.valueOf(CelI), iReng - 1,
						String.valueOf(CelS), iReng - 1,
						Rep.getAT_COMBINA_CENTRO());

				Rep.comFillCells(String.valueOf(CelI), iReng - 1,
						String.valueOf(CelS), iReng, 15);
				Rep.comFontBold(String.valueOf(CelI), iReng - 1,
						String.valueOf(CelS), iReng);
				Rep.comBordeTotal(String.valueOf(CelI), iReng - 1,
						String.valueOf(CelS), iReng, 1);

				iRengI = ++iReng;
				for (int i = 0; i < this.VRegistros.size(); i++) {
					VMuestra = new TVMuestra();
					VMuestra = (TVMuestra) this.VRegistros.get(i);
					// Presentar la información de la Muestra
					Rep.comDespliega("A", iReng, String.valueOf(i + 1));
					Rep.comDespliega("B", iReng,
							String.valueOf(VMuestra.getIAnio()));
					Rep.comDespliega("C", iReng, VMuestra.getCMuestra());
					Rep.comDespliega("D", iReng, VMuestra.getCAnalisis());
					/*
					 * Rep.comDespliega("E", iReng, VMuestra.getCDscUM());
					 * Rep.comDespliega("F", iReng, VMuestra.getCDscModulo());
					 * Rep.comDespliega("G", iReng, VMuestra.getCDscProceso());
					 * Rep.comDespliega("H", iReng, VMuestra.getCDscMotivo());
					 */
					Rep.comDespliega(
							"E",
							iReng,
							"'"
									+ Fecha.getFechaDDMMYYYY(
											VMuestra.getDtRecoleccion(), "/"));
					// Presentar las drogas por positivo
					TVTOXLoteCuantita VAnalisis;
					for (int iRes = 0; iRes < VMuestra.vResultado.size(); iRes++) {
						VAnalisis = (TVTOXLoteCuantita) VMuestra.vResultado
								.get(iRes);
						cLote = new StringBuffer();
						if (hCeldaSust.containsKey(String.valueOf(VAnalisis
								.getiCveSustancia()))) {
							Cel = hCeldaSust
									.get(String.valueOf(VAnalisis
											.getiCveSustancia())).toString()
									.toCharArray()[0];
							Rep.comDespliega(
									String.valueOf(Cel),
									iReng,
									"'"
											+ Fecha.getFechaDDMMYYYY(
													VAnalisis.getdtAnalisis(),
													"/"));
							cLote.append(VAnalisis.getiAnio())
									.append(" / ")
									.append(Numero.getNumeroSinSeparador(
											VAnalisis.getiCveLoteCuantita(), 3));
							Cel = (char) ++Cel;
							Rep.comDespliega(String.valueOf(Cel), iReng,
									cLote.toString());
						}
					}
					iReng++;
				} // Recorrer muestras
					// Generar archivo
				Rep.comBordeTotal("A", iRengI, String.valueOf(CelS), iReng - 1,
						1);
				cNomArchivo += Fecha.getFechaMMDDYYYYSinSep(Fecha.TodaySQL());
				buffer = Rep.creaActiveX("pg070305060-1", cNomArchivo, false,
						false, true);
			} // Existen registros
			else
				this.vErrores.acumulaError(
						"No existen registros para generar el Reporte.", 0, "");
		} // try
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer;
	} // Generar Reporte

	public StringBuffer GenListado() {
		TExcel Rep = new TExcel("07");
		TVMuestra VMuestra = new TVMuestra();
		TNumeros Numero = new TNumeros();
		StringBuffer buffer = new StringBuffer();
		String cNomArchivo = "Listado_Deshecho_";
		int iReng = 10, iHoja = 1, iRengI;
		try {
			// Verificar que existan registros a Desplegar
			if (this.VRegistros.size() > 0) {
				// Buscar las sustancias a Presentar
				StringBuffer cLote;
				// Generar columnas pra las sustancias a Desplegar
				iRengI = iReng;
				for (int i = 0; i < this.VRegistros.size(); i++) {
					VMuestra = new TVMuestra();
					VMuestra = (TVMuestra) this.VRegistros.get(i);
					// Presentar la información de la Muestra
					Rep.comDespliega("A", iReng, String.valueOf(i + 1));
					Rep.comDespliega("B", iReng, VMuestra.getCMuestra());
					Rep.comDespliega("C", iReng, "'" + VMuestra.getCAnalisis());
					// Presentar las drogas por positivo
					TVTOXLoteCuantita VAnalisis;
					for (int iRes = 0; iRes < VMuestra.vResultado.size(); iRes++) {
						iReng = (iRes > 0) ? (iReng + 1) : iReng;
						VAnalisis = (TVTOXLoteCuantita) VMuestra.vResultado
								.get(iRes);
						cLote = new StringBuffer();
						Rep.comDespliega("D", iReng,
								VAnalisis.VSustancia.getCTitRepConf());
						Rep.comDespliega(
								"E",
								iReng,
								"'"
										+ Fecha.getFechaDDMMYYYY(
												VAnalisis.getdtAnalisis(), "/"));
					}
					iReng++;
				} // Recorrer muestras
					// Generar archivo
				Rep.comBordeTotal("B", iRengI, "E", iReng - 1, 1);

				// Enviar información del pie de página
				iReng++;
				Rep.comDespliega("A", iReng, this.vParametros
						.getPropEspecifica("TOXLeyendaListPost"));
				Rep.comAlineaRango("A", iReng, "E", iReng,
						Rep.getAT_COMBINA_IZQUIERDA());
				Rep.comAlineaRangoVer("A", iReng, "E", iReng,
						Rep.getAT_VAJUSTAR());
				iReng += 2;
				Rep.comDespliega("B", iReng, "FECHA DE ELIMINACIÓN");
				Rep.comDespliega(
						"B",
						iReng,
						"'"
								+ Fecha.getFechaDDMMYYYY(
										VMuestra.getDtDeshecho(), "/"));
				Rep.comDespliega("C", iReng, this.vParametros
						.getPropEspecifica("TOXPuestoCientCert"));
				Rep.comDespliega("D", iReng,
						this.vParametros.getPropEspecifica("TOXPuestoFirma"));
				Rep.comDespliega("E", iReng,
						this.vParametros.getPropEspecifica("TOXPuestoFirmaD"));
				iReng++;
				Rep.comDespliega("C", iReng, this.vParametros
						.getPropEspecifica("TOXNombreCientCert"));
				Rep.comDespliega("D", iReng,
						this.vParametros.getPropEspecifica("TOXNombreFirma"));
				Rep.comDespliega("E", iReng,
						this.vParametros.getPropEspecifica("TOXNombreFirmaD"));

				cNomArchivo += Fecha.getFechaMMDDYYYYSinSep(Fecha.TodaySQL());
				buffer = Rep.creaActiveX("pg070305060-2", cNomArchivo, false,
						false, true);
			} // Existen registros
			else
				this.vErrores.acumulaError(
						"No existen registros para generar el Reporte.", 0, "");
		} // try
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return buffer;
	}

	public boolean getLConsPost() {
		return lConsPost;
	}

}
