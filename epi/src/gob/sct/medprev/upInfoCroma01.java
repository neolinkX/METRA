/*
 * upInfoCroma01
 *
 * Created on Marzo 9, 2006, 11:30 AM
 */

package gob.sct.medprev;

import java.io.*;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Vector;

import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;

import com.micper.seguridad.vo.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.TDTOXCuantAnalisis;
import gob.sct.medprev.dao.TDTOXSustancia;
import gob.sct.medprev.dao.TDTOXLoteCuantita;
import com.micper.util.TFechas;

/**
 * 
 * @author Itzia Mar�a del C. S�nchez M�ndez
 * @version 1.0
 */

public class upInfoCroma01 extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		int exitoso = 1, iMuestProc = 0;
		String fileName = "", cMensajes = "", cNomMuestra = "";
		HashMap hsMuestras = new HashMap();
		HashMap hsSustancia = new HashMap(), hsCalibracion = new HashMap();
		try {
			// Definiciones para el manejo del archivo
			MultipartParser mp = new MultipartParser(request, 3 * 1024 * 1024); // 10MB
			Part part;

			// Definici�n de Variables para procesar los an�lisis
			SampleProcesor sp = new SampleProcesor();
			SampleData sd = null;
			QueryManager qm = new QueryManager("07", "ConDBModulo");
			TVTOXCuantAnalisis vCuantA = null;
			TDTOXCuantAnalisis DCuantA = new TDTOXCuantAnalisis();
			TVTOXLoteCalibra VCalibrador = new TVTOXLoteCalibra();
			StringBuffer cFiltroC = new StringBuffer();
			TFechas Fecha = new TFechas();

			// Definici�n de la salida
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();

			// Obtener la informaci�n del Lote Cuantitativo
			int iTipoCarga = Integer.parseInt(request
					.getParameter("iTipoCarga"));
			TVTOXLoteCuantita vInfoLote = new TVTOXLoteCuantita();
			vInfoLote.setiAnio(Integer.valueOf(request.getParameter("iAnio")));
			vInfoLote.setiCveLaboratorio(Integer.valueOf(request
					.getParameter("iCveUniMed")));
			vInfoLote.setiCveSustancia(Integer.valueOf(request
					.getParameter("iCveSustancia")));
			vInfoLote.setiCveLoteCuantita(Integer.valueOf(request
					.getParameter("iCveLoteCuantita")));
			// Generar filtro para calibraci�n
			cFiltroC.append("where CA.iAnio = ").append(vInfoLote.getiAnio())
					.append("   and CA.iCveLaboratorio  = ")
					.append(vInfoLote.getiCveLaboratorio())
					.append("   and CA.iCveSustancia    in ")
					.append(vInfoLote.getiCveSustancia())
					.append("   and CA.iCveLoteCuantita = ")
					.append(vInfoLote.getiCveLoteCuantita())
					.append("   and CA.iPosicion        = 1");

			/******** SUSTITUIR POR BUSQUEDA EN LA BASE DE DATOS *******/
			// Definici�n de Sustancias
			Vector vSustancias = (Vector) (new TDTOXSustancia().FindByAll(
					" where lActivo = 1 and lAnalizada = 1", ""));
			for (int i = 0; i < vSustancias.size(); i++) {
				if (iTipoCarga == 2) {
					// Buscar la calibraci�n para cada Sustancias
					cFiltroC = new StringBuffer();
					cFiltroC.append("where CA.iAnio = ")
							.append(vInfoLote.getiAnio())
							.append("   and CA.iCveLaboratorio  = ")
							.append(vInfoLote.getiCveLaboratorio())
							.append("   and CA.iCveSustancia    = ")
							.append(((TVTOXSustancia) vSustancias.get(i))
									.getiCveSustancia())
							.append("   and CA.iCveLoteCuantita = ")
							.append(vInfoLote.getiCveLoteCuantita())
							.append("   and CA.iPosicion        = 1");
					// Obtener los calibradores para las diferentes sustancias
					Vector vCalibra = DCuantA.FindCalibrador(cFiltroC
							.toString());
					if (vCalibra.size() > 0)
						VCalibrador = (TVTOXLoteCalibra) vCalibra.get(0);
					hsCalibracion.put(((TVTOXSustancia) vSustancias.get(i))
							.getCAbrevEqAC(), VCalibrador);
				} // Carga de Tipo 2
				hsSustancia.put(((TVTOXSustancia) vSustancias.get(i))
						.getCAbrevEqAC(), Integer.valueOf(String
						.valueOf(((TVTOXSustancia) vSustancias.get(i))
								.getiCveSustancia())));
			}

			// System.out.println(" Sustancias " + hsSustancia.values());
			while ((part = mp.readNextPart()) != null) {
				if (part.getName().equals("fArchivo")) {
					FilePart filePart = (FilePart) part;
					// Validaci�n del Contenido del archivo para ser procesado
					if ((!filePart.getContentType().equals(
							"application/x-zip-compressed"))) {
						exitoso = 2; // error x tipo de dato
						break;
					}
					// Obtener nombre del archivo que ser� procesado.
					fileName = filePart.getFileName();
					// Enviar a procesar el archivo que se est� leyendo
					sp.readZipFile(filePart.getInputStream());
					// Procesar las muestras del archivo
					sp.processSamples();
					// Obtener informaci�n del Lote y las muestras seg�n la
					// informaci�n a cargar
					hsMuestras = DCuantA.findCarga(vInfoLote, iTipoCarga,
							SampleTokens.iPosIdentifica);
					// Obtener la informaci�n de las muestras
					for (int i = 0; i < sp.getSamples().size(); i++) {
						sd = (SampleData) sp.getSamples().get(i);
						/*
						 * if(iTipoCarga == 1) cNomMuestra =
						 * sd.getLC_iCveAnalisis().trim(); if(iTipoCarga == 2)
						 */
						cNomMuestra = sd
								.getLC_iCveAnalisis()
								.trim()
								.substring(
										sd.getLC_iCveAnalisis()
												.trim()
												.lastIndexOf(
														SampleTokens.SEPARADOR) + 1,
										sd.getLC_iCveAnalisis()
												.trim()
												.lastIndexOf(
														SampleTokens.SEPARADOR)
												+ SampleTokens.iTamNombre + 1);
						// System.out.println("Muestra a buscar =" +
						// cNomMuestra);
						// verificar que la muestra sea del Lote
						if (hsMuestras.containsKey(cNomMuestra)) {
							vCuantA = (TVTOXCuantAnalisis) hsMuestras
									.get(cNomMuestra);
							vInfoLote.setdtAnalisis(sd.getLC_dtAnalisisD());
							// Fecha = new
							// java.sql.Date(sd.getLC_dtAnalisis().getInstance());
							// Informaci�n de la Muestra
							// System.out.println(" N�mero de componentes" +
							// sd.getCA_Componente().size());
							for (int iSust = 0; iSust < sd.getCA_Componente()
									.size(); iSust++) {
								// System.out.println("Componente a cargar: " +
								// sd.getCA_Componente().get(iSust).toString().trim());
								if (hsSustancia.containsKey(sd
										.getCA_Componente().get(iSust)
										.toString().trim())) {
									vCuantA.setiCveSustancia((Integer) hsSustancia
											.get(sd.getCA_Componente()
													.get(iSust).toString()
													.trim()));
									vCuantA.setdResultadoDil(Double.valueOf(sd
											.getCA_dResultadoDil().get(iSust)
											.toString()));
									vCuantA.setdResultado(vCuantA
											.getdResultadoDil());
									vCuantA.setDTmpRetenc(Double.parseDouble(sd
											.getCA_dTmpRetenc().get(iSust)
											.toString()));
									vCuantA.setDIon01(Double.parseDouble("1"));
									if (sd.getCA_dIon02().size() > iSust)
										vCuantA.setDIon02(Double.parseDouble(sd
												.getCA_dIon02().get(iSust)
												.toString()));
									else
										vCuantA.setDIon02(Double
												.parseDouble("0"));
									if (sd.getCA_dIon03().size() > iSust)
										vCuantA.setDIon03(Double.parseDouble(sd
												.getCA_dIon03().get(iSust)
												.toString()));
									else
										vCuantA.setDIon03(Double
												.parseDouble("0"));
									vCuantA.setDTmpRetencD(Double
											.parseDouble(sd.getCA_dTmpRetencD()
													.get(iSust).toString()));
									vCuantA.setDIon04(Double.parseDouble("1"));
									vCuantA.setDIon05(Double.parseDouble(sd
											.getCA_dIon05().get(iSust)
											.toString()));
									vCuantA.setLRegistrado(new Integer(1));
									if (iTipoCarga == 1) {
										vCuantA.setLCorrecto(new Integer(1));
										vCuantA.setlResultado(new Integer(0));
									}
									if (iTipoCarga == 2) {
										VCalibrador = (TVTOXLoteCalibra) hsCalibracion
												.get(sd.getCA_Componente()
														.get(iSust).toString()
														.trim());

										/*
										 * Esta es la l�nea original que hace
										 * la validaci�n de correcto /
										 * incorrecto Se agrega el if para
										 * evitar que se realice con
										 * metanfetaminas. Se debe de sustituir
										 * el hard code con la variable de la
										 * droga vCuantA.setLCorrecto(new
										 * Integer
										 * (DCuantA.ValidaMuestra(VCalibrador,
										 * vCuantA)));
										 */
										if (vCuantA.getiCveSustancia().equals(
												new Integer(7)))
											vCuantA.setLCorrecto(Integer
													.valueOf("1"));
										else
											vCuantA.setLCorrecto(new Integer(
													DCuantA.ValidaMuestra(
															VCalibrador,
															vCuantA)));

										vCuantA.setlResultado(new Integer(
												DCuantA.EvaluaResultado(
														VCalibrador, vCuantA)));
									}
									try {
										DCuantA.updResultado(vCuantA);
									} catch (Exception e) {
										exitoso = 2;
									} // Try
								}// La sustancia est� registrada
							} // Por cada componente del archivo
							if (exitoso != 2)
								iMuestProc++;
						}// Si la muestra es del lote
					} // por cada muestra procesada
				} // Se valido el nombre del archivo
			} // Fin del ciclo para leer las partes del archivo.

			// Asignar informaci�n al Lote
			if (exitoso != 2 && iTipoCarga == 2) {
				// Informaci�n del usuario.
				TDTOXLoteCuantita DTOXLoteCuantita = new TDTOXLoteCuantita();
				TVUsuario vUsuario = new TVUsuario();
				vUsuario = (TVUsuario) request.getSession().getAttribute(
						"UsrID");
				vInfoLote.setiCveUsuAnalista(new Integer(vUsuario
						.getICveusuario()));
				try {
					DTOXLoteCuantita.updResultado(vInfoLote);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// Evaluar el resultado de las sustancias para el caso de
				// anfetaminas
				if (vInfoLote.getiCveSustancia().intValue() == 1)
					DCuantA.EvaluaDosSustDep(vInfoLote, "7");
			}
			if (exitoso != 2)
				cMensajes = "En el archivo " + fileName + " se procesaron "
						+ iMuestProc + " registros";
			else
				cMensajes = " El archivo "
						+ fileName
						+ " no pudo ser procesado, pues no corresponde al tipo requerido.";
		} catch (Exception e) {
			e.printStackTrace();
		} // try
		out.println("<script language=\"JavaScript\">");
		out.println("alert('" + cMensajes + "');");
		out.println("window.opener.fSubmitForm('Buscar');window.close();");
		out.println("</script>");
		out.close();
	} // Fin del Metodo

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 */
	public String getServletInfo() {
		return "Short description";
	}
}
