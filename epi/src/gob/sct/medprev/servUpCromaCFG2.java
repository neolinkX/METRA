package gob.sct.medprev;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.util.Vector;
import java.util.Iterator;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.TVUsuario;
import java.lang.Math.*;
import com.micper.util.*;

import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;

public class servUpCromaCFG2 extends HttpServlet {

	TParametro vParametros = new TParametro("07");
	protected HttpServletRequest httpReq = null;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest r = (HttpServletRequest) request;
		MultipartParser mp;
		Part p = null;

		String Record = "";

		int iAnio;
		int iCveSustancia;
		int iCveLoteCuantita;
		int iCveAnalisis;
		int iCveLaboratorio;

		Double dConcentracion;
		Double dConcentracion2;
		Double dTmpRetenc;
		Double dIon01;
		Double dIon02;
		Double dIon03;
		Double dTmpRetencD;
		Double dIon04;
		Double dIon05;
		Integer lCorrecto;

		String Prueba;
		int posicion = 0;
		Integer Clave;
		String ClaveStr;

		String Sustancia = "";
		StringBuffer cFiltroC = new StringBuffer();

		boolean lCarga = true;
		boolean lCargado = false;

		String condicion;
		Vector registros;
		Iterator iterador;

		TVTOXcLote rcLote;

		try {
			mp = new MultipartParser(r, Integer.MAX_VALUE);
			File fArchivo = new File(vParametros.getPropEspecifica("RutaCroma")
					+ "controles.txt");
			while ((p = mp.readNextPart()) != null) {
				if (p.isFile()) {
					FilePart fp = (FilePart) p;
					fp.writeTo(fArchivo);
				} else {
					throw new InternalError(
							"PART DE TIPO DESCONOCIDO. INCAPAZ PARSEAR REQUEST.");
				}
			}

			iAnio = Integer.parseInt(request.getParameter("iAnio"));
			iCveSustancia = Integer.parseInt(request
					.getParameter("iCveSustancia"));
			iCveLaboratorio = Integer.parseInt(request
					.getParameter("iCveUniMed"));
			iCveLoteCuantita = Integer.parseInt(request
					.getParameter("iCveLoteCuantita"));

			try {
				File F = new File(vParametros.getPropEspecifica("RutaCroma")
						+ "controles.txt");
				BufferedReader in = new BufferedReader(new FileReader(F));
				TDTOXCuantAnalisis dTOXCuantAnalisis = new TDTOXCuantAnalisis();
				TVTOXCuantAnalisis vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				TVTOXLoteCalibra VCalibrador = new TVTOXLoteCalibra();

				cFiltroC.append("where CA.iAnio = ").append(iAnio)
						.append("   and CA.iCveLaboratorio  = ")
						.append(iCveLaboratorio)
						.append("   and CA.iCveSustancia    = ")
						.append(iCveSustancia)
						.append("   and CA.iCveLoteCuantita = ")
						.append(iCveLoteCuantita)
						.append("   and CA.iPosicion        = 1");
				// Buscar el calibrador para validar
				VCalibrador = (TVTOXLoteCalibra) dTOXCuantAnalisis
						.FindCalibrador(cFiltroC.toString()).get(0);
				int lTomaPositivo = 0;

				while ((Record = in.readLine()) != null) {
					try {
						// Clave de analisis
						posicion = Record.indexOf(",");
						Prueba = new String(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						Clave = new Integer(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						iCveAnalisis = Clave.intValue();

						posicion = Record.indexOf(",");
						dTmpRetenc = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dIon01 = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dIon02 = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dIon03 = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dTmpRetencD = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dIon04 = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dIon05 = new Double(Record.substring(0, posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dConcentracion = new Double(Record.substring(0,
								posicion));
						Record = Record.substring(posicion + 1);

						posicion = Record.indexOf(",");
						dConcentracion2 = new Double(Record.substring(0,
								posicion));
						Record = Record.substring(posicion + 1);

						lCorrecto = new Integer(Record);

						condicion = " where iAnio           = " + iAnio
								+ "   and iCveLaboratorio = " + iCveLaboratorio
								+ "   and iCveSustancia   = " + iCveSustancia
								+ "   and iCveAnalisis    = " + iCveAnalisis;
						// "      iCveSustancia = " + iCveSustancia + " AND " +

						registros = dTOXCuantAnalisis.FindByAll(condicion);
						iterador = registros.iterator();
						while (iterador.hasNext()) {
							vTOXCuantAnalisis = (TVTOXCuantAnalisis) iterador
									.next();
							vTOXCuantAnalisis.setiAnio(new Integer(iAnio));
							vTOXCuantAnalisis.setiCveSustancia(new Integer(
									iCveSustancia));
							vTOXCuantAnalisis.setiCveLoteCuantita(new Integer(
									iCveLoteCuantita));
							vTOXCuantAnalisis.setiCveAnalisis(new Integer(
									iCveAnalisis));
							vTOXCuantAnalisis.setiCveLaboratorio(new Integer(
									iCveLaboratorio));

							vTOXCuantAnalisis.setdResultadoDil(dConcentracion);
							vTOXCuantAnalisis.setdResultado(new Double(
									dConcentracion.doubleValue()
											* vTOXCuantAnalisis.getiDilusion()
													.doubleValue()));
							vTOXCuantAnalisis.setDTmpRetenc(dTmpRetenc
									.doubleValue());
							vTOXCuantAnalisis.setDIon01(dIon01.doubleValue());
							vTOXCuantAnalisis.setDIon02(dIon02.doubleValue());
							vTOXCuantAnalisis.setDIon03(dIon03.doubleValue());
							vTOXCuantAnalisis.setDTmpRetencD(dTmpRetencD
									.doubleValue());
							vTOXCuantAnalisis.setDIon04(dIon04.doubleValue());
							vTOXCuantAnalisis.setDIon05(dIon05.doubleValue());
							// vTOXCuantAnalisis.setLCorrecto(lCorrecto);

							vTOXCuantAnalisis.setLRegistrado(new Integer(1));
							vTOXCuantAnalisis.setLCorrecto(new Integer(
									dTOXCuantAnalisis.ValidaMuestra(
											VCalibrador, vTOXCuantAnalisis)));
							vTOXCuantAnalisis.setlResultado(new Integer(
									dTOXCuantAnalisis.EvaluaResultado(
											VCalibrador, vTOXCuantAnalisis)));
							dTOXCuantAnalisis.updResultado(vTOXCuantAnalisis);
							lCargado = true;
						}
					} catch (Exception e) {
						e.printStackTrace();
						lCarga = false;
					}
				}
				in.close();
				if (lCargado) {
					lCarga = true;
					{
						// Información del usuario.
						TDTOXLoteCuantita DTOXLoteCuantita = new TDTOXLoteCuantita();
						TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();
						TFechas Fecha = new TFechas();
						VTOXLoteCuantita.setiAnio(new Integer(iAnio));
						VTOXLoteCuantita.setiCveLaboratorio(new Integer(
								iCveLaboratorio));
						VTOXLoteCuantita.setiCveSustancia(new Integer(
								iCveSustancia));
						VTOXLoteCuantita.setiCveLoteCuantita(new Integer(
								iCveLoteCuantita));
						VTOXLoteCuantita.setdtAnalisis(Fecha.TodaySQL());
						TVUsuario vUsuario = new TVUsuario();

						vUsuario = (TVUsuario) httpReq.getSession()
								.getAttribute("UsrID");
						VTOXLoteCuantita.setiCveUsuAnalista(new Integer(
								vUsuario.getICveusuario()));

						try {
							DTOXLoteCuantita.updResultado(VTOXLoteCuantita);
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				lCarga = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println("<script language=\"JavaScript\">");
		if (!lCarga)
			response.getWriter().println(
					"alert('No todos los análisis han sido cargados!');");
		response.getWriter().println(
				"window.opener.fSubmitForm('Buscar');window.close();");
		response.getWriter().println("</script>");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
