package gob.sct.medprev;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import com.micper.excepciones.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;
import jxl.*;
import jxl.read.biff.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

/**
 * 
 * <p>
 * Title: Cargar Archivo
 * </p>
 * <p>
 * Description: Carga un archivo de Excel
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 */
public class servCtrUpXLS extends HttpServlet {

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Crea un factory para items de archivos disk-based
		// FileItemFactory factory = new DiskFileItemFactory();

		// Crea un factory para items de archivos disk-based
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Fija los constraints del factory
		// factory.setSizeThreshold(1000000);
		// factory.setRepository(new File("C:\\Temp"));

		// Crea un nuevo manejador para subir archivos
		ServletFileUpload upload = new ServletFileUpload(factory);

		PrintWriter out = response.getWriter();
		// TParametro vParametros = new TParametro("07");

		try {
			List items = upload.parseRequest(request);
			Iterator it = items.iterator();

			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();

				// Checa si el item actual es un form field o un archivo subido
				if (item.isFormField()) {
					// System.out.println("##### item es un form field y no un archivo");
				} else {
					// El item debe ser un archivo subido salvado en el disco.
					// Hay que notar que parece haber un bug en item.getName()
					// ya que regresa el path completo en la maquina del cliente
					// para el nombre del archivo subido, en vez de solo el
					// nombre.
					// Para resolver esto, se usa fullFile.getName().
					// File fullFile = new File(item.getName());
					// File savedFile = new
					// File(getServletContext().getRealPath("/"),
					// fullFile.getName());
					// File savedFile = new
					// File(vParametros.getPropEspecifica("ExcelRutaDest"),
					// fullFile.getName());
					// File savedFile = new
					// File(vParametros.getPropEspecifica("ExcelRutaDest"),
					// "CtrXLS.xls");

					if (item.getName().endsWith(".xls")) {
						// System.out.println("SI ES ARCHIVO DE EXCEL");
						// item.write(savedFile);

						/*
						 * out.println("El Archivo: " + savedFile.getName());
						 * out.println("<br />");
						 * out.println("Se guardó correctamente");
						 * out.println("<br />"); out.println("<br />");
						 * out.println("Empresa: " +
						 * request.getParameter("icveempresa"));
						 * out.println("<br />"); out.println("Plantilla: " +
						 * request.getParameter("icveplantilla"));
						 */

						Workbook workbook = null;
						Sheet sheet0 = null;

						try {
							// Se puede usar InputStream (is) o File (savedFile)
							// como argumento de getWorkbook()
							// workbook = Workbook.getWorkbook(savedFile);

							workbook = Workbook.getWorkbook(item
									.getInputStream());

							sheet0 = workbook.getSheet(0);

							JXLSBean jxlsBeanAlta = new JXLSBean("ALTA");
							JXLSBean jxlsBeanBaja = new JXLSBean("BAJA");
							JXLSBean jxlsBeanActualizacion = new JXLSBean(
									"ACTUALIZACION");
							JXLSBean jxlsBeanVacio = new JXLSBean("");

							TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
							TFechas dtFecha = new TFechas();
							java.sql.Date dtHoy = dtFecha.TodaySQL();

							// System.out.println("ROWS...." +
							// sheet0.getRows());

							// for (int i = 6; i < sheet0.getRows(); i++) { Se
							// cambio esta parte ya que los datos de la nueva
							// plantilla inician en la pocision 17 y no en la 6
							// System.out.println(sheet0.getRows());
							// for (int i = 17; i < sheet0.getRows(); i++) {
							for (int i = 15; i < sheet0.getRows(); i++) {
								// Obtengo todas las celdas del renglon
								Cell[] cell = sheet0.getRow(i);

								StringBuffer pais = new StringBuffer();

								TVCTRPersonal vCTRPersonal = new TVCTRPersonal();
								// Campo requerido ICVEEMPRESA (No null)
								vCTRPersonal.setICveEmpresa(Integer
										.parseInt(request
												.getParameter("icveempresa")));
								// Campo requerido ICVEPLANTILLA (No null)
								vCTRPersonal
										.setICvePlantilla(Integer.parseInt(request
												.getParameter("icveplantilla")));
								// Campo requerido INUMPRESONAL (No null)
								// vCTRPersonal.setINumPersonal(0); // Se le
								// asigna un valor al momento del insert

								// Nota: Envia un ArrayIndexOutOfBoundsException
								// sI trata de llamar
								// un metodo de la celda y ésta es la utima y
								// esta vacia.
								// Tambien puede enviar un ClassCastException si
								// por ejemplo
								// trato de asignar una celda vacia a un Date.

								String tipocelda = "";
								if (cell[0].getContents().trim() == ""
										|| cell[0].getContents() == null) {
									String variableexcel = "nulos";
									// System.out.println(variableexcel);
									i = sheet0.getRows();
								} else {

									// Acepta null ICVEEXPEDIENTE
									try {
										// // System.out.println("CELL TYPE: " +
										// cell[0].getType());
										// // System.out.println("CELL VALUE: "
										// + cell[0].getContents());
										// Verifico si la celda es de tipo
										// Numerica o String
										if (cell[0].getType() == CellType.NUMBER) {
											vCTRPersonal
													.setICveExpediente((int) ((NumberCell) cell[0])
															.getValue());
										} else if (cell[0].getType() == CellType.LABEL) {
											vCTRPersonal
													.setICveExpediente(Integer
															.parseInt(cell[0]
																	.getContents()
																	.trim()
																	.replace(
																			"'",
																			"")));
										}
									} catch (Exception ex) {
										vCTRPersonal.setICveExpediente(0);
									}

									// Acepta null CNOMBRE
									try {
										// // System.out.println("CELL TYPE: " +
										// cell[1].getType());
										// // System.out.println("CELL 1: " +
										// cell[1].getContents());
										vCTRPersonal.setCNombre(cell[1]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCNombre("");
									}

									// Acepta null CAPPATERNO
									try {
										// System.out.println("CELL TYPE: " +
										// cell[2].getType());
										// System.out.println("CELL 2: " +
										// cell[2].getContents());
										vCTRPersonal.setCApPaterno(cell[2]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCApPaterno("");
									}

									// Acepta null CAPMATERNO
									try {
										// System.out.println("CELL TYPE: " +
										// cell[3].getType());
										// System.out.println("CELL 3: " +
										// cell[3].getContents());
										vCTRPersonal.setCApMaterno(cell[3]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCApMaterno("");
									}

									// Acepta null CRFC
									try {
										vCTRPersonal.setCRFC(cell[4]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCRFC("");
									}

									// Acepta null CCURP
									try {
										vCTRPersonal.setCCURP(cell[5]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCCURP("");
									}

									// Campo requerido DTNACIMIENTO (No null)
									try {
										// System.out.println("CELL TYPE: " +
										// cell[6].getType());
										// System.out.println("CELL 6: " +
										// cell[6].getContents());
										// System.out.println("##### RAC ####: "
										// +
										// dtFecha.getSQLDateFromString(cell[6].getContents()));
										vCTRPersonal.setDtNacimiento(dtFecha
												.getSQLDateFromString(cell[6]
														.getContents()));
										// System.out.println("vCTRPersonal.getDtNacimiento(): "
										// + vCTRPersonal.getDtNacimiento());
									} catch (Exception ex) {
										vCTRPersonal
												.setDtNacimiento(dtFecha
														.getSQLDateFromString("01-Ene-1900"));
									}

									// Acepta null ICVEPAIS
									try {
										pais.replace(0, pais.length(),
												cell[7].getContents().trim()
														.replace("'", ""));

										if (pais.toString().equalsIgnoreCase(
												"MEXICO")) {
											pais.replace(0, pais.length(),
													"M�XICO");
										}

										int iCvePais = dCTRPersonal
												.findICvePaisByCNombre(pais
														.toString());
										vCTRPersonal.setICvePaisNac(iCvePais);
									} catch (Exception ex) {
										vCTRPersonal.setICvePaisNac(1); // Por
																		// default
																		// MÉXICO
									}

									// Acepta null ICVEESTADONAC
									// Si ICVEPAIS = 1 y ICVEENTIDADFED = 0 =>
									// DESCONOCIDO
									try {
										int iCvePais = vCTRPersonal
												.getICvePaisNac(); // Del try
																	// anterior
																	// ya trae
																	// informacion
										String cDscEstado = cell[8]
												.getContents().trim()
												.replace("'", "");
										int iCveEstado = dCTRPersonal
												.findICveEstadoByCNombre(
														iCvePais, cDscEstado);
										vCTRPersonal
												.setICveEstadoNac(iCveEstado);
									} catch (Exception ex) {
										vCTRPersonal.setICveEstadoNac(0);
									}

									// Acepta null ICVEMDOTRANS
									try {
										// System.out.println("transporte  = "+cell[9].getContents());
										int iCveMdoTrans = dCTRPersonal
												.findICveMdoTransByCDscMdoTrans(cell[9]
														.getContents().trim()
														.replace("'", ""));
										vCTRPersonal
												.setICveMdoTrans(iCveMdoTrans);
									} catch (Exception ex) {
										vCTRPersonal.setICveMdoTrans(0);
									}

									// Acepta null ICVEPUESTO
									try {
										int iCveMdoTrans = vCTRPersonal
												.getiCveMdoTrans();
										int iCvePuesto = dCTRPersonal
												.findICvePuestoByCDscPuesto(
														iCveMdoTrans,
														cell[10].getContents()
																.trim()
																.replace("'",
																		""));
										vCTRPersonal.setICvePuesto(iCvePuesto);
									} catch (Exception ex) {
										vCTRPersonal.setICvePuesto(0);
									}

									// Acepta null CLICENCIA
									try {
										vCTRPersonal.setCLicencia(cell[11]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCLicencia("");
									}

									// Se aumento el numero de celdas a 2 por el
									// cambio de formato excel
									// Agregados el 2 de abril 2012 por AG
									// SA.

									// Acepta null Resultado de Examen
									// Toxicologico
									try {
										// System.out.println("Tox = " +
										// cell[14].getContents());
										vCTRPersonal.setCRExaTox(cell[13]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCCalle("");
									}

									// Acepta null Resultado de Examen de
									// Deteccion de Alcohol
									try {
										// System.out.println("Alc = " +
										// cell[14].getContents());
										vCTRPersonal.setCRExaDAlc(cell[14]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCCalle("");
									}

									// Acepta null CCALLE
									try {
										vCTRPersonal.setCCalle(cell[15]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCCalle("");
									}

									// Acepta null CNUMEXT
									try {
										// Verifico si la celda es de tipo
										// Numerica o String
										if (cell[16].getType() == CellType.NUMBER) {
											vCTRPersonal
													.setCNumExt(""
															+ (int) ((NumberCell) cell[16])
																	.getValue());
										} else if (cell[16].getType() == CellType.LABEL) {
											vCTRPersonal.setCNumExt(cell[16]
													.getContents());
										}
									} catch (Exception ex) {
										vCTRPersonal.setCNumExt("");
									}

									// Acepta null CNUMINT
									try {
										// Verifico si la celda es de tipo
										// Numerica (0) o String (1)
										if (cell[75].getType() == CellType.NUMBER) {
											vCTRPersonal
													.setCNumInt(""
															+ (int) ((NumberCell) cell[17])
																	.getValue());
										} else if (cell[17].getType() == CellType.LABEL) {
											vCTRPersonal.setCNumInt(cell[17]
													.getContents().trim()
													.replace("'", ""));
										}
									} catch (Exception ex) {
										vCTRPersonal.setCNumInt("");
									}

									// Acepta null CCOLONIA
									try {
										vCTRPersonal.setCColonia(cell[18]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCColonia("");
									}

									// Campo requerido ICP (No null)
									try {
										// Verifico si la celda es de tipo
										// Numerica (0) o String (1)
										if (cell[19].getType() == CellType.NUMBER) {
											vCTRPersonal
													.setICP((int) ((NumberCell) cell[19])
															.getValue());
										} else if (cell[19].getType() == CellType.LABEL) {
											vCTRPersonal.setICP(Integer
													.parseInt(cell[19]
															.getContents()
															.trim()
															.replace("'", "")));
										}
									} catch (Exception ex) {
										vCTRPersonal.setICP(0);
									}

									// Acepta null CCIUDAD
									try {
										vCTRPersonal.setCCiudad(cell[20]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCCiudad("");
									}

									// Campo requerido ICVEPAIS (No null)
									try {
										pais.replace(0, pais.length(),
												cell[21].getContents().trim()
														.replace("'", ""));

										if (pais.toString().equalsIgnoreCase(
												"MEXICO")) {
											pais.replace(0, pais.length(),
													"M�XICO");
										}

										int iCvePais = dCTRPersonal
												.findICvePaisByCNombre(pais
														.toString());
										vCTRPersonal.setICvePais(iCvePais);
									} catch (Exception ex) {
										vCTRPersonal.setICvePais(1); // por
																		// default
																		// MÉXICO
									}

									// Campo requerido ICVEESTADO (No null)
									try {
										int iCvePais = vCTRPersonal
												.getICvePais(); // Del try
																// anterior ya
																// trae
																// informacion
										int iCveEstado = dCTRPersonal
												.findICveEstadoByCNombre(
														iCvePais,
														cell[22].getContents()
																.trim()
																.replace("'",
																		""));
										vCTRPersonal.setICveEstado(iCveEstado);
									} catch (Exception ex) {
										vCTRPersonal.setICveEstado(0); // DESCONOCIDO
									}

									// Campo requerido ICVEMUNICIPIO (No null)
									try {
										int iCvePais = vCTRPersonal
												.getICvePais(); // Del try
																// anterior ya
																// trae
																// informacion
										int iCveEstado = vCTRPersonal
												.getICveEstado(); // Del try
																	// anterior
																	// ya trae
																	// informacion
										int iCveMunicipio = dCTRPersonal
												.findICveMunicipioByCNombre(
														iCvePais,
														iCveEstado,
														cell[23].getContents()
																.trim()
																.replace("'",
																		""));
										vCTRPersonal
												.setICveMunicipio(iCveMunicipio);
									} catch (Exception ex) {
										vCTRPersonal.setICveMunicipio(0);
									}

									// Acepta null CTEL
									try {
										vCTRPersonal.setCTel(cell[24]
												.getContents().trim()
												.replace("'", ""));
									} catch (Exception ex) {
										vCTRPersonal.setCTel("");
									}

									// Los sig. llenarian las descripciones que
									// por ahora no necesito.
									// vCTRPersonal.setCDscEmpresa("");
									// vCTRPersonal.setCDscPaisNac("");
									// vCTRPersonal.setCDscEstadoNac("");
									// vCTRPersonal.setCDscMdotrans("");
									// vCTRPersonal.setCDscPuesto("");
									// vCTRPersonal.setCDscPais("");
									// vCTRPersonal.setCDscEstado("");
									// vCTRPersonal.setCDscMunicipio("");
									vCTRPersonal.setDtRecepcion(dtHoy);

									// Acepta null DTLICVENCIMIENTO
									try {
										// System.out.println("DTLICVENCIMIENTO: "
										// + new
										// java.sql.Date(((DateCell)cell[12]).getDate().getTime()));
										vCTRPersonal
												.setDtLicVencimiento(dtFecha
														.getSQLDateFromString(cell[12]
																.getContents()));
										// System.out.println("vCTRPersonal.getDtLicVencimiento(): "
										// +
										// vCTRPersonal.getDtLicVencimiento());
									} catch (Exception ex) {
										vCTRPersonal
												.setDtLicVencimiento(dtFecha
														.getSQLDateFromString("01-Ene-1900"));
									}

									// Acepta null LACTIVO
									// ### PREGUNTAR A QUE SE REFIERE ACTIVO SI
									// ES ALTRA O BAJA?
									vCTRPersonal.setlActivo(1);

									// Acepta null LBASEEVENTUAL
									try {
										vCTRPersonal.setlBaseEventual(1);
									} catch (Exception ex) {
										vCTRPersonal.setlBaseEventual(1);
									}

									/*
                       *
                       */
									String estatus = "";

									try {
										estatus = cell[26].getContents().trim()
												.replace("'", "");
									} catch (Exception ex) {
										estatus = "";
									}

									/********** ALTAS **********/
									/***************************/
									// System.out.println("estatus = "+estatus);
									if (estatus.equalsIgnoreCase("ALTA")) {
										try {
											// System.out.println("Altas");
											if (dCTRPersonal
													.existsOperador(
															vCTRPersonal
																	.getICveEmpresa(),
															vCTRPersonal
																	.getiCvePlantilla(),
															vCTRPersonal
																	.getCNombre(),
															vCTRPersonal
																	.getCApPaterno(),
															vCTRPersonal
																	.getCApMaterno(),
															vCTRPersonal
																	.getCRFC())) {
												out.println("NO SE DIO DE ALTA PORQUE YA EXISTE: "
														+ vCTRPersonal
																.getCNombre()
														+ " "
														+ vCTRPersonal
																.getCApPaterno()
														+ " "
														+ vCTRPersonal
																.getCApMaterno());
												out.println("<br />");
												jxlsBeanVacio
														.addBean(vCTRPersonal);
											} else {
												// Lo da de alta si el nombre,
												// apellidos y RFC no estan
												// vacíos
												if (!vCTRPersonal.getCNombre()
														.equalsIgnoreCase("")
														&& !vCTRPersonal
																.getCApPaterno()
																.equalsIgnoreCase(
																		"")
														&& !vCTRPersonal
																.getCApMaterno()
																.equalsIgnoreCase(
																		"")
														&& !vCTRPersonal
																.getCRFC()
																.equalsIgnoreCase(
																		"")) {

													// Tambien se verifica que
													// la Licencia tenga el
													// Prefijo
													PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
													List list = bean
															.getPrefijosLicenciasBean();
													boolean lLicenciaOK = false;

													for (int x = 0; x < list
															.size(); x++) {
														if (vCTRPersonal
																.getCLicencia()
																.toUpperCase()
																.startsWith(
																		((PrefijosLicenciasBean) list
																				.get(x))
																				.getCPrefijoAnterior())
																|| vCTRPersonal
																		.getCLicencia()
																		.toUpperCase()
																		.startsWith(
																				((PrefijosLicenciasBean) list
																						.get(x))
																						.getCPrefijoSistema())) {
															lLicenciaOK = true;
															break;
														}
													}

													if (lLicenciaOK == true) {
														// La claveAlta regresa
														// ICveEmpresa
														String claveAlta = dCTRPersonal
																.insert(null,
																		vCTRPersonal);

														// Si iInserted == 1 =>
														// Si se insertó el
														// renglón en la BD
														// System.out.println("dCTRPersonal.getIInserted(): "
														// +
														// dCTRPersonal.getIInserted());
														if (dCTRPersonal
																.getIInserted() == 1) {
															jxlsBeanAlta
																	.addBean(vCTRPersonal);
														} else {
															jxlsBeanVacio
																	.addBean(vCTRPersonal);
														}
													} else {
														out.println("NO SE DIO DE ALTA PORQUE LA LICENCIA NO TIENE EL PREFIJO CORRECTO: "
																+ vCTRPersonal
																		.getCNombre()
																+ " "
																+ vCTRPersonal
																		.getCApPaterno()
																+ " "
																+ vCTRPersonal
																		.getCApMaterno());
														out.println("<br />");
														jxlsBeanVacio
																.addBean(vCTRPersonal);
													}
												} else {
													out.println("NO SE DIO DE ALTA PORQUE FALTA NOMBRE, APELLIDOS Y RFC: "
															+ vCTRPersonal
																	.getCNombre()
															+ " "
															+ vCTRPersonal
																	.getCApPaterno()
															+ " "
															+ vCTRPersonal
																	.getCApMaterno());
													out.println("<br />");
													jxlsBeanVacio
															.addBean(vCTRPersonal);
												}
											}
										} catch (DAOException ex) {
											jxlsBeanVacio.addBean(vCTRPersonal);

											// ex.printStackTrace();
										}
										/********** BAJAS **********/
										/***************************/
									} else if (estatus.equalsIgnoreCase("BAJA")) {
										try {
											String filtro = " where iCveEmpresa = "
													+ vCTRPersonal
															.getICveEmpresa()
													+ " and iCvePlantilla = "
													+ vCTRPersonal
															.getiCvePlantilla()
													+ " and cNombre = '"
													+ vCTRPersonal.getCNombre()
													+ "'"
													+ " and cApPaterno = '"
													+ vCTRPersonal
															.getCApPaterno()
													+ "'"
													+ " and cApMaterno = '"
													+ vCTRPersonal
															.getCApMaterno()
													+ "'"
													+ " and cRFC = '"
													+ vCTRPersonal.getCRFC()
													+ "'";

											TVCTRPersonal vCTRPersonalBaja = dCTRPersonal
													.findOperador(filtro);
											int existe = dCTRPersonal
													.findOperador2(filtro);

											// La claveBaja me va a regresar
											// siempre ""
											// System.out.println("Existe "
											// +existe);

											if (existe == 0) {
												existe = 0;
												// System.out.println("la persona no existe");
											} else {
												Object claveBaja = dCTRPersonal
														.delete(null,
																vCTRPersonalBaja);
											}
											// Si iDeletedd == 1 => Si se
											// eliminó el renglón en la BD
											// System.out.println("dCTRPersonal.getIDeleted(): "
											// + dCTRPersonal.getIDeleted());
											if (dCTRPersonal.getIDeleted() == 1) {
												jxlsBeanBaja
														.addBean(vCTRPersonalBaja);
											} else {
												jxlsBeanVacio
														.addBean(vCTRPersonal);
											}
										} catch (DAOException ex) {
											jxlsBeanVacio.addBean(vCTRPersonal);

											// ex.printStackTrace();
										}
										/********** ACTUALIZACIONES **********/
										/*************************************/
									} else if (estatus
											.equalsIgnoreCase("ACTUALIZACION")
											|| estatus
													.equalsIgnoreCase("ACTUALIZACI�N")) {
										try {
											if (dCTRPersonal
													.existsOperador(
															vCTRPersonal
																	.getICveEmpresa(),
															vCTRPersonal
																	.getiCvePlantilla(),
															vCTRPersonal
																	.getCNombre(),
															vCTRPersonal
																	.getCApPaterno(),
															vCTRPersonal
																	.getCApMaterno(),
															vCTRPersonal
																	.getCRFC())) {
												// Obtengo lo datos de la BD
												// para obtener el iNumPersonal
												// ya que de la carga de Excel
												// va a traer iNumPersonal = 0
												// siempre
												TVCTRPersonal vCtrPersonalBD = dCTRPersonal
														.findOperador(" where iCveEmpresa = "
																+ vCTRPersonal
																		.getICveEmpresa()
																+ " and iCvePlantilla = "
																+ vCTRPersonal
																		.getiCvePlantilla()
																+ " and cNombre = '"
																+ vCTRPersonal
																		.getCNombre()
																+ "' "
																+ " and cApPaterno = '"
																+ vCTRPersonal
																		.getCApPaterno()
																+ "' "
																+ " and cApMaterno = '"
																+ vCTRPersonal
																		.getCApMaterno()
																+ "' "
																+ " and cRFC = '"
																+ vCTRPersonal
																		.getCRFC()
																+ "' ");
												// Asigno iNumPersonal de la BD
												// al de la carga de Excel
												vCTRPersonal
														.setINumPersonal(vCtrPersonalBD
																.getINumPersonal());

												// Tambien se verifica que la
												// Licencia tenga el Prefijo
												PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
												List list = bean
														.getPrefijosLicenciasBean();
												boolean lLicenciaOK = false;

												// System.out.println("vCTRPersonal.getCLicencia(): "
												// +
												// vCTRPersonal.getCLicencia());

												for (int x = 0; x < list.size(); x++) {
													// System.out.println("ANTERIOR..."
													// +
													// ((PrefijosLicenciasBean)list.get(x)).getCPrefijoAnterior());
													// System.out.println("SISTEMA..."
													// +
													// ((PrefijosLicenciasBean)list.get(x)).getCPrefijoSistema());

													if (vCTRPersonal
															.getCLicencia()
															.toUpperCase()
															.startsWith(
																	((PrefijosLicenciasBean) list
																			.get(x))
																			.getCPrefijoAnterior())
															|| vCTRPersonal
																	.getCLicencia()
																	.toUpperCase()
																	.startsWith(
																			((PrefijosLicenciasBean) list
																					.get(x))
																					.getCPrefijoSistema())) {
														lLicenciaOK = true;
														break;
													}
												}

												// System.out.println("lLicenciaOK: "
												// + lLicenciaOK);

												if (lLicenciaOK == true) {
													// ////System.out.println("####### DENTRO DEL SERVLET (ACTUALIZACION)...SI EXISTE EL OPERADOR ########");
													// La claveUpdate siempre va
													// a regresar ""
													Object claveUpdate = dCTRPersonal
															.update(null,
																	vCTRPersonal);

													// Si iUpdated == 1 => Si
													// actualizó el renglón en
													// la BD
													// ////System.out.println("dCTRPersonal.getIUpdated(): "
													// +
													// dCTRPersonal.getIUpdated());
													if (dCTRPersonal
															.getIUpdated() == 1) {
														jxlsBeanActualizacion
																.addBean(vCTRPersonal);
													} else {
														jxlsBeanVacio
																.addBean(vCTRPersonal);
													}
												} else {
													out.println("NO SE ACTUALIZ� PORQUE EL PREFIJO DE LA LICENCIA ESTA INCORRECTO : "
															+ vCTRPersonal
																	.getCNombre()
															+ " "
															+ vCTRPersonal
																	.getCApPaterno()
															+ " "
															+ vCTRPersonal
																	.getCApMaterno());
													out.println("<br />");
													jxlsBeanVacio
															.addBean(vCTRPersonal);
												}
											}
										} catch (DAOException ex) {
											jxlsBeanVacio.addBean(vCTRPersonal);

											// ex.printStackTrace();
										}
										// Si no especifican en la celda ALTA,
										// BAJA o ACTUALIZACION
										// entonces verifico primero si el
										// operador existe
										// si existe => realizo ACTUALIZACION
										// si no existe => realizo ALTA
										/********** VACIO **********/
										/***************************/
									} else if (estatus.equals("")) {
										try {
											if (dCTRPersonal
													.existsOperador(
															vCTRPersonal
																	.getICveEmpresa(),
															vCTRPersonal
																	.getiCvePlantilla(),
															vCTRPersonal
																	.getCNombre(),
															vCTRPersonal
																	.getCApPaterno(),
															vCTRPersonal
																	.getCApMaterno(),
															vCTRPersonal
																	.getCRFC())) {
												// Obtengo lo datos de la BD
												// para obtener el iNumPersonal
												// ya que de la carga de Excel
												// va a traer iNumPersonal = 0
												// siempre
												TVCTRPersonal vCtrPersonalBD = dCTRPersonal
														.findOperador(" where iCveEmpresa = "
																+ vCTRPersonal
																		.getICveEmpresa()
																+ " and iCvePlantilla = "
																+ vCTRPersonal
																		.getiCvePlantilla()
																+ " and cNombre = '"
																+ vCTRPersonal
																		.getCNombre()
																+ "' "
																+ " and cApPaterno = '"
																+ vCTRPersonal
																		.getCApPaterno()
																+ "' "
																+ " and cApMaterno = '"
																+ vCTRPersonal
																		.getCApMaterno()
																+ "' "
																+ " and cRFC = '"
																+ vCTRPersonal
																		.getCRFC()
																+ "' ");
												// Asigno iNumPersonal de la BD
												// al de la carga de Excel
												vCTRPersonal
														.setINumPersonal(vCtrPersonalBD
																.getINumPersonal());

												// Tambien se verifica que la
												// Licencia tenga el Prefijo
												PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
												List list = bean
														.getPrefijosLicenciasBean();
												boolean lLicenciaOK = false;

												for (int x = 0; x < list.size(); x++) {
													if (vCTRPersonal
															.getCLicencia()
															.toUpperCase()
															.startsWith(
																	((PrefijosLicenciasBean) list
																			.get(x))
																			.getCPrefijoAnterior())
															|| vCTRPersonal
																	.getCLicencia()
																	.toUpperCase()
																	.startsWith(
																			((PrefijosLicenciasBean) list
																					.get(x))
																					.getCPrefijoSistema())) {
														lLicenciaOK = true;
														break;
													}
												}

												if (lLicenciaOK == true) {
													// La claveUpdate siempre va
													// a regresar ""
													Object claveUpdate = dCTRPersonal
															.update(null,
																	vCTRPersonal);

													// Si iUpdated == 1 => Si
													// actualizó el renglón en
													// la BD
													// ////System.out.println("dCTRPersonal.getIUpdated(): "
													// +
													// dCTRPersonal.getIUpdated());
													if (dCTRPersonal
															.getIUpdated() == 1) {
														jxlsBeanActualizacion
																.addBean(vCTRPersonal);
													} else {
														jxlsBeanVacio
																.addBean(vCTRPersonal);
													}
												} else {
													out.println("NO SE ACTUALIZ� PORQUE EL PREFIJO DE LA LICENCIA ESTA INCORRECTO : "
															+ vCTRPersonal
																	.getCNombre()
															+ " "
															+ vCTRPersonal
																	.getCApPaterno()
															+ " "
															+ vCTRPersonal
																	.getCApMaterno());
													out.println("<br />");
													jxlsBeanVacio
															.addBean(vCTRPersonal);
												}
											} else {
												// Lo da de alta si el nombre,
												// apellidos y RFC no estan
												// vacíos
												if (!vCTRPersonal.getCNombre()
														.equalsIgnoreCase("")
														&& !vCTRPersonal
																.getCApPaterno()
																.equalsIgnoreCase(
																		"")
														&& !vCTRPersonal
																.getCApMaterno()
																.equalsIgnoreCase(
																		"")
														&& !vCTRPersonal
																.getCRFC()
																.equalsIgnoreCase(
																		"")) {

													// Tambien se verifica que
													// la Licencia tenga el
													// Prefijo
													PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
													List list = bean
															.getPrefijosLicenciasBean();
													boolean lLicenciaOK = false;

													for (int x = 0; x < list
															.size(); x++) {
														if (vCTRPersonal
																.getCLicencia()
																.toUpperCase()
																.startsWith(
																		((PrefijosLicenciasBean) list
																				.get(x))
																				.getCPrefijoAnterior())
																|| vCTRPersonal
																		.getCLicencia()
																		.toUpperCase()
																		.startsWith(
																				((PrefijosLicenciasBean) list
																						.get(x))
																						.getCPrefijoSistema())) {
															lLicenciaOK = true;
															break;
														}
													}

													if (lLicenciaOK == true) {
														String claveAlta = dCTRPersonal
																.insert(null,
																		vCTRPersonal);

														// Si iInserted == 1 =>
														// Si se insertó el
														// renglón en la BD
														// ////System.out.println("dCTRPersonal.getIInserted(): "
														// +
														// dCTRPersonal.getIInserted());
														if (dCTRPersonal
																.getIInserted() == 1) {
															jxlsBeanAlta
																	.addBean(vCTRPersonal);
														} else {
															jxlsBeanVacio
																	.addBean(vCTRPersonal);
														}
													} else {
														out.println("NO SE DIO DE ALTA PORQUE LA LICENCIA NO TIENE EL PREFIJO CORRECTO: "
																+ vCTRPersonal
																		.getCNombre()
																+ " "
																+ vCTRPersonal
																		.getCApPaterno()
																+ " "
																+ vCTRPersonal
																		.getCApMaterno());
														out.println("<br />");
														jxlsBeanVacio
																.addBean(vCTRPersonal);
													}
												} else {
													out.println("NO SE DIO DE ALTA PORQUE FALTA NOMBRE, APELLIDOS Y RFC: "
															+ vCTRPersonal
																	.getCNombre()
															+ " "
															+ vCTRPersonal
																	.getCApPaterno()
															+ " "
															+ vCTRPersonal
																	.getCApMaterno());
													out.println("<br />");
													jxlsBeanVacio
															.addBean(vCTRPersonal);
												}
											}
										} catch (DAOException ex) {
											jxlsBeanVacio.addBean(vCTRPersonal);

											// ex.printStackTrace();
										}
										/**********
										 * CUALQUIER OTRO VALOR DIFERENTE DE
										 * VACIO
										 **********/
										/*************************************************************/
									} else {
										jxlsBeanVacio.addBean(vCTRPersonal);
									}
								}
							} // Fin del for

							// Cerrar Workbook y liberar memoria
							workbook.close();

							out.println("<br />");
							out.println("<br />");

							// ALTAS
							out.print("<b><i>ALTAS ("
									+ jxlsBeanAlta.getList().size()
									+ ")</i></b>");
							out.print("<table border='4' bordercolor='#0000ff'>");
							out.print("<tr>");
							out.print("<th>No. DE EXPEDIENTE</th>");
							out.print("<th>NOMBRE</th>");
							out.print("<th>APELLIDO PATERNO</th>");
							out.print("<th>APELLIDO MATERNO</th>");
							out.print("</tr>");

							for (int i = 0; i < jxlsBeanAlta.getList().size(); i++) {
								TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanAlta
										.getList().get(i);

								if (vCTRPersonal != null) {
									out.print("<tr>");
									out.print("<td>");
									out.print(vCTRPersonal.getICveExpediente());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCNombre());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApPaterno());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApMaterno());
									out.print("</td>");
									out.print("</tr>");
								}
							}

							out.println("</table>");

							out.println("<br />");
							out.println("<br />");

							// BAJAS
							out.print("<b><i>BAJAS ("
									+ jxlsBeanBaja.getList().size()
									+ ")</i></b>");
							out.print("<table border='4' bordercolor='#0000ff'>");
							out.print("<tr>");
							out.print("<th>No. DE EXPEDIENTE</th>");
							out.print("<th>NOMBRE</th>");
							out.print("<th>APELLIDO PATERNO</th>");
							out.print("<th>APELLIDO MATERNO</th>");
							out.print("</tr>");

							for (int i = 0; i < jxlsBeanBaja.getList().size(); i++) {
								TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanBaja
										.getList().get(i);

								if (vCTRPersonal != null) {
									out.print("<tr>");
									out.print("<td>");
									out.print(vCTRPersonal.getICveExpediente());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCNombre());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApPaterno());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApMaterno());
									out.print("</td>");
									out.print("</tr>");
								}
							}

							out.println("</table>");

							out.println("<br />");
							out.println("<br />");

							// ACTUALIZACIONES
							out.print("<b><i>ACTUALIZACIONES ("
									+ jxlsBeanActualizacion.getList().size()
									+ ")</i></b>");
							out.print("<table border='4' bordercolor='#0000ff'>");
							out.print("<tr>");
							out.print("<th>No. DE EXPEDIENTE</th>");
							out.print("<th>NOMBRE</th>");
							out.print("<th>APELLIDO PATERNO</th>");
							out.print("<th>APELLIDO MATERNO</th>");
							out.print("</tr>");

							for (int i = 0; i < jxlsBeanActualizacion.getList()
									.size(); i++) {
								TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanActualizacion
										.getList().get(i);

								if (vCTRPersonal != null) {
									out.print("<tr>");
									out.print("<td>");
									out.print(vCTRPersonal.getICveExpediente());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCNombre());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApPaterno());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApMaterno());
									out.print("</td>");
									out.print("</tr>");
								}
							}

							out.println("</table>");

							out.println("<br />");
							out.println("<br />");

							// VACIO
							out.print("<b><i>NO SE REALIZ� NINGUNA OPERACION ("
									+ jxlsBeanVacio.getList().size()
									+ ")</i></b>");
							out.print("<table border='4' bordercolor='#ff0000'>");
							out.print("<tr>");
							out.print("<th>No. DE EXPEDIENTE</th>");
							out.print("<th>NOMBRE</th>");
							out.print("<th>APELLIDO PATERNO</th>");
							out.print("<th>APELLIDO MATERNO</th>");
							out.print("</tr>");

							for (int i = 0; i < jxlsBeanVacio.getList().size(); i++) {
								TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanVacio
										.getList().get(i);

								if (vCTRPersonal != null) {
									out.print("<tr>");
									out.print("<td>");
									out.print(vCTRPersonal.getICveExpediente());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCNombre());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApPaterno());
									out.print("</td>");
									out.print("<td>");
									out.print(vCTRPersonal.getCApMaterno());
									out.print("</td>");
									out.print("</tr>");
								}
							}

							out.println("</table>");

							/*
							 * Lo sig. es una prueba para mostrar los datos (no
							 * todos) con el Jasper y para crear el reporte
							 * tambien en PDF. try { InputStream is = new
							 * FileInputStream(vParametros.getPropEspecifica(
							 * "ExcelRutaOrig") + "CtrPersonal.jrxml");
							 * JasperReport jasperReport =
							 * JasperCompileManager.compileReport(is);
							 * JRBeanCollectionDataSource jrCollectionDataSource
							 * = new
							 * JRBeanCollectionDataSource(jxlsBeanVacio.getList
							 * ()); JasperPrint jasperPrint =
							 * JasperFillManager.fillReport(jasperReport, new
							 * HashMap(), jrCollectionDataSource);
							 * JasperExportManager
							 * .exportReportToPdfFile(jasperPrint,
							 * "C:\\Temp\\CtrPersonal.pdf");
							 * JasperViewer.viewReport(jasperPrint); } catch
							 * (JRException ex) { ex.printStackTrace(); }
							 */
						} catch (BiffException ex) {
							out.println("<br />");
							out.println("Seleccione un archivo de Excel con formato1");
							out.println("<br />");
							// ex.printStackTrace();
						} catch (IndexOutOfBoundsException ex) {
							// ex.printStackTrace();
						}
					} else {

						if (item.getName().endsWith(".csv")) {
							// System.out.println("Se a agregado un Archivo csv");
							// System.out.println(item.getInputStream());
							// System.out.println(item.getName());

							CsvReader reader = new CsvReader(item.getName());

							try {// 2

								JXLSBean jxlsBeanAlta = new JXLSBean("ALTA");
								JXLSBean jxlsBeanBaja = new JXLSBean("BAJA");
								JXLSBean jxlsBeanActualizacion = new JXLSBean(
										"ACTUALIZACION");
								JXLSBean jxlsBeanVacio = new JXLSBean("");

								TDCTRPersonal dCTRPersonal = new TDCTRPersonal();
								TFechas dtFecha = new TFechas();
								java.sql.Date dtHoy = dtFecha.TodaySQL();

								int c = 0;

								// System.out.println(reader.getColumnCount());
								// System.out.println(reader.getHeaderCount());
								// System.out.println(reader.getEscapeMode());

								while (reader.readRecord()) {

									StringBuffer pais = new StringBuffer();

									TVCTRPersonal vCTRPersonal = new TVCTRPersonal();

									vCTRPersonal
											.setICveEmpresa(Integer.parseInt(request
													.getParameter("icveempresa")));

									vCTRPersonal
											.setICvePlantilla(Integer.parseInt(request
													.getParameter("icveplantilla")));

									c++;

									// reader.get(Integer) - devuelve el
									// contenido del numero de columna que le
									// pasamos.
									// if( c > 17){
									if (c > 15) {

										String ExpedienteC = reader.get(0);
										String NombreC = reader.get(1);
										String ApellidoPC = reader.get(2);
										String ApellidoMC = reader.get(3);
										String RFCC = reader.get(4);
										String CURPC = reader.get(5);
										String FechaDNacC = reader.get(6);
										String PaisNaC = reader.get(7);
										String EstadoNaC = reader.get(8);
										String MdoTransC = reader.get(9);
										String PuestoC = reader.get(10);
										String LicenciaC = reader.get(11);
										String FechaDVenC = reader.get(12);
										String CalleC = reader.get(13);
										String NoExtC = reader.get(14);
										String NoIntC = reader.get(15);
										String ColC = reader.get(16);
										String CPC = reader.get(17);
										String CiudadC = reader.get(18);
										String PaisC = reader.get(19);
										String EstadoC = reader.get(20);
										String MuniC = reader.get(21);
										String TelC = reader.get(22);
										String BasEvC = reader.get(23);
										String EstatusC = reader.get(24);

										// System.out.println(ExpedienteC.length());

										if (ExpedienteC.length() <= 0) {
											reader.getCurrentRecord();
											reader.skipLine();
											reader.skipRecord();
											// System.out.println("Filas: " +
											// ExpedienteC + "-" + NombreC + "-"
											// + ApellidoPC + "-" + ApellidoMC);
										} else {

											// System.out.println("Filas: " +
											// ExpedienteC + "-" + NombreC + "-"
											// + ApellidoPC + "-" + ApellidoMC);

											try {

												ExpedienteC = ExpedienteC
														.replace("'", "");
												vCTRPersonal
														.setICveExpediente(Integer
																.parseInt(ExpedienteC));
											} catch (Exception ex) {
												vCTRPersonal
														.setICveExpediente(0);
											}

											// Acepta null CNOMBRE
											try {
												NombreC = NombreC.replace("'",
														"");
												vCTRPersonal
														.setCNombre(NombreC);
											} catch (Exception ex) {
												vCTRPersonal.setCNombre("");
											}

											// Acepta null CAPPATERNO
											try {
												ApellidoPC = ApellidoPC
														.replace("'", "");
												vCTRPersonal
														.setCApPaterno(ApellidoPC);
											} catch (Exception ex) {
												vCTRPersonal.setCApPaterno("");
											}

											// Acepta null CAPMATERNO
											try {
												ApellidoMC = ApellidoMC
														.replace("'", "");
												vCTRPersonal
														.setCApMaterno(ApellidoMC);
											} catch (Exception ex) {
												vCTRPersonal.setCApMaterno("");
											}

											// Acepta null CRFC
											try {
												RFCC = RFCC.replace("'", "");
												vCTRPersonal.setCRFC(RFCC);
											} catch (Exception ex) {
												vCTRPersonal.setCRFC("");
											}

											// Acepta null CCURP
											try {
												CURPC = CURPC.replace("'", "");
												vCTRPersonal.setCCURP(CURPC);
											} catch (Exception ex) {
												vCTRPersonal.setCCURP("");
											}

											// Campo requerido DTNACIMIENTO (No
											// null)
											try {
												FechaDNacC = FechaDNacC
														.replace("'", "");
												vCTRPersonal
														.setDtNacimiento(dtFecha
																.getSQLDateFromString(FechaDNacC));
											} catch (Exception ex) {
												vCTRPersonal
														.setDtNacimiento(dtFecha
																.getSQLDateFromString("01-Ene-1900"));
											}

											// Acepta null ICVEPAIS
											try {
												PaisNaC = PaisNaC.replace("'",
														"");
												pais.replace(0, pais.length(),
														PaisNaC);
												if (pais.toString()
														.equalsIgnoreCase(
																"MEXICO")) {
													pais.replace(0,
															pais.length(),
															"M�XICO");
												}

												int iCvePais = dCTRPersonal
														.findICvePaisByCNombre(pais
																.toString());
												vCTRPersonal
														.setICvePaisNac(iCvePais);
											} catch (Exception ex) {
												vCTRPersonal.setICvePaisNac(1); // Por
																				// default
																				// MÉXICO
											}

											// Acepta null ICVEESTADONAC
											// Si ICVEPAIS = 1 y ICVEENTIDADFED
											// = 0 => DESCONOCIDO
											try {
												int iCvePais = vCTRPersonal
														.getICvePaisNac(); // Del
																			// try
																			// anterior
																			// ya
																			// trae
																			// informacion
												EstadoNaC = EstadoNaC.replace(
														"'", "");
												String cDscEstado = EstadoNaC;
												int iCveEstado = dCTRPersonal
														.findICveEstadoByCNombre(
																iCvePais,
																cDscEstado);
												vCTRPersonal
														.setICveEstadoNac(iCveEstado);
											} catch (Exception ex) {
												vCTRPersonal
														.setICveEstadoNac(0);
											}

											// Acepta null ICVEMDOTRANS
											try {
												MdoTransC = MdoTransC.replace(
														"'", "");
												int iCveMdoTrans = dCTRPersonal
														.findICveMdoTransByCDscMdoTrans(MdoTransC);
												vCTRPersonal
														.setICveMdoTrans(iCveMdoTrans);
											} catch (Exception ex) {
												vCTRPersonal.setICveMdoTrans(0);
											}

											// Acepta null ICVEPUESTO
											try {
												PuestoC = PuestoC.replace("'",
														"");
												int iCveMdoTrans = vCTRPersonal
														.getiCveMdoTrans();
												int iCvePuesto = dCTRPersonal
														.findICvePuestoByCDscPuesto(
																iCveMdoTrans,
																PuestoC);
												vCTRPersonal
														.setICvePuesto(iCvePuesto);
											} catch (Exception ex) {
												vCTRPersonal.setICvePuesto(0);
											}

											// Acepta null CLICENCIA
											try {
												LicenciaC = LicenciaC.replace(
														"'", "");
												vCTRPersonal
														.setCLicencia(LicenciaC);
											} catch (Exception ex) {
												vCTRPersonal.setCLicencia("");
											}

											// Acepta null CCALLE
											try {
												CalleC = CalleC
														.replace("'", "");
												vCTRPersonal.setCCalle(CalleC);
											} catch (Exception ex) {
												vCTRPersonal.setCCalle("");
											}

											// Acepta null CNUMEXT
											try {
												NoExtC = NoExtC
														.replace("'", "");
												// Verifico si la celda es de
												// tipo Numerica o String
												vCTRPersonal.setCNumExt(NoExtC);
											} catch (Exception ex) {
												vCTRPersonal.setCNumExt("");
											}

											// Acepta null CNUMINT
											try {
												NoIntC = NoIntC
														.replace("'", "");
												// Verifico si la celda es de
												// tipo Numerica (0) o String
												// (1)
												vCTRPersonal.setCNumInt(NoIntC
														.trim());
											} catch (Exception ex) {
												vCTRPersonal.setCNumInt("");
											}

											// Acepta null CCOLONIA
											try {
												ColC = ColC.replace("'", "");
												vCTRPersonal.setCColonia(ColC
														.trim());
											} catch (Exception ex) {
												vCTRPersonal.setCColonia("");
											}

											// Campo requerido ICP (No null)
											try {
												CPC = CPC.replace("'", "");
												// Verifico si la celda es de
												// tipo Numerica (0) o String
												// (1)
												vCTRPersonal.setICP(Integer
														.parseInt(CPC.trim()));
											} catch (Exception ex) {
												vCTRPersonal.setICP(0);
											}

											// Acepta null CCIUDAD
											try {
												CiudadC = CiudadC.replace("'",
														"");
												vCTRPersonal.setCCiudad(CiudadC
														.trim());
											} catch (Exception ex) {
												vCTRPersonal.setCCiudad("");
											}

											// Campo requerido ICVEPAIS (No
											// null)
											try {
												PaisC = PaisC.replace("'", "");
												pais.replace(0, pais.length(),
														PaisC.trim());
												if (pais.toString()
														.equalsIgnoreCase(
																"MEXICO")) {
													pais.replace(0,
															pais.length(),
															"M�XICO");
												}

												int iCvePais = dCTRPersonal
														.findICvePaisByCNombre(pais
																.toString());
												vCTRPersonal
														.setICvePais(iCvePais);
											} catch (Exception ex) {
												vCTRPersonal.setICvePais(1); // por
																				// default
																				// MÉXICO
											}

											// Campo requerido ICVEESTADO (No
											// null)
											try {
												EstadoC = EstadoC.replace("'",
														"");
												int iCvePais = vCTRPersonal
														.getICvePais(); // Del
																		// try
																		// anterior
																		// ya
																		// trae
																		// informacion
												int iCveEstado = dCTRPersonal
														.findICveEstadoByCNombre(
																iCvePais,
																EstadoC.trim());
												vCTRPersonal
														.setICveEstado(iCveEstado);
											} catch (Exception ex) {
												vCTRPersonal.setICveEstado(0); // DESCONOCIDO
											}

											// Campo requerido ICVEMUNICIPIO (No
											// null)
											try {
												MuniC = MuniC.replace("'", "");
												int iCvePais = vCTRPersonal
														.getICvePais(); // Del
																		// try
																		// anterior
																		// ya
																		// trae
																		// informacion
												int iCveEstado = vCTRPersonal
														.getICveEstado(); // Del
																			// try
																			// anterior
																			// ya
																			// trae
																			// informacion
												int iCveMunicipio = dCTRPersonal
														.findICveMunicipioByCNombre(
																iCvePais,
																iCveEstado,
																MuniC.trim());
												vCTRPersonal
														.setICveMunicipio(iCveMunicipio);
											} catch (Exception ex) {
												vCTRPersonal
														.setICveMunicipio(0);
											}

											// Acepta null CTEL
											try {
												TelC = TelC.replace("'", "");
												vCTRPersonal.setCTel(TelC
														.trim());
											} catch (Exception ex) {
												vCTRPersonal.setCTel("");
											}

											// Los sig. llenarian las
											// descripciones que por ahora no
											// necesito.
											// vCTRPersonal.setCDscEmpresa("");
											// vCTRPersonal.setCDscPaisNac("");
											// vCTRPersonal.setCDscEstadoNac("");
											// vCTRPersonal.setCDscMdotrans("");
											// vCTRPersonal.setCDscPuesto("");
											// vCTRPersonal.setCDscPais("");
											// vCTRPersonal.setCDscEstado("");
											// vCTRPersonal.setCDscMunicipio("");
											vCTRPersonal.setDtRecepcion(dtHoy);

											// Acepta null DTLICVENCIMIENTO
											try {
												FechaDVenC = FechaDVenC
														.replace("'", "");
												vCTRPersonal
														.setDtLicVencimiento(dtFecha
																.getSQLDateFromString(FechaDVenC));
											} catch (Exception ex) {
												vCTRPersonal
														.setDtLicVencimiento(dtFecha
																.getSQLDateFromString("01-Ene-1900"));
											}

											// Acepta null LACTIVO
											// ### PREGUNTAR A QUE SE REFIERE
											// ACTIVO SI ES ALTA O BAJA?
											vCTRPersonal.setlActivo(1);

											// Acepta null LBASEEVENTUAL
											try {
												vCTRPersonal
														.setlBaseEventual(1);
											} catch (Exception ex) {
												vCTRPersonal
														.setlBaseEventual(1);
											}

											String estatus = "";

											try {
												estatus = EstatusC.trim();
											} catch (Exception ex) {
												estatus = "";
											}

											// ********** ALTAS **********/
											// ***************************/

											if (estatus
													.equalsIgnoreCase("ALTA")) {
												try {
													if (dCTRPersonal
															.existsOperador(
																	vCTRPersonal
																			.getICveEmpresa(),
																	vCTRPersonal
																			.getiCvePlantilla(),
																	vCTRPersonal
																			.getCNombre(),
																	vCTRPersonal
																			.getCApPaterno(),
																	vCTRPersonal
																			.getCApMaterno(),
																	vCTRPersonal
																			.getCRFC())) {
														out.println("NO SE DIO DE ALTA PORQUE YA EXISTE: "
																+ vCTRPersonal
																		.getCNombre()
																+ " "
																+ vCTRPersonal
																		.getCApPaterno()
																+ " "
																+ vCTRPersonal
																		.getCApMaterno());
														out.println("<br />");
														jxlsBeanVacio
																.addBean(vCTRPersonal);
													} else {
														// Lo da de alta si el
														// nombre, apellidos y
														// RFC no estan vacíos
														if (!vCTRPersonal
																.getCNombre()
																.equalsIgnoreCase(
																		"")
																&& !vCTRPersonal
																		.getCApPaterno()
																		.equalsIgnoreCase(
																				"")
																&& !vCTRPersonal
																		.getCApMaterno()
																		.equalsIgnoreCase(
																				"")
																&& !vCTRPersonal
																		.getCRFC()
																		.equalsIgnoreCase(
																				"")) {

															// Tambien se
															// verifica que la
															// Licencia tenga el
															// Prefijo
															PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
															List list = bean
																	.getPrefijosLicenciasBean();
															boolean lLicenciaOK = false;

															for (int x = 0; x < list
																	.size(); x++) {
																if (vCTRPersonal
																		.getCLicencia()
																		.toUpperCase()
																		.startsWith(
																				((PrefijosLicenciasBean) list
																						.get(x))
																						.getCPrefijoAnterior())
																		|| vCTRPersonal
																				.getCLicencia()
																				.toUpperCase()
																				.startsWith(
																						((PrefijosLicenciasBean) list
																								.get(x))
																								.getCPrefijoSistema())) {
																	lLicenciaOK = true;
																	break;
																}
															}

															if (lLicenciaOK == true) {
																// La claveAlta
																// regresa
																// ICveEmpresa
																String claveAlta = dCTRPersonal
																		.insert(null,
																				vCTRPersonal);

																// Si iInserted
																// == 1 => Si se
																// insertó el
																// renglón en
																// la BD
																// System.out.println("dCTRPersonal.getIInserted(): "
																// +
																// dCTRPersonal.getIInserted());
																if (dCTRPersonal
																		.getIInserted() == 1) {
																	jxlsBeanAlta
																			.addBean(vCTRPersonal);
																} else {
																	jxlsBeanVacio
																			.addBean(vCTRPersonal);
																}
															} else {
																out.println("NO SE DIO DE ALTA PORQUE LA LICENCIA NO TIENE EL PREFIJO CORRECTO: "
																		+ vCTRPersonal
																				.getCNombre()
																		+ " "
																		+ vCTRPersonal
																				.getCApPaterno()
																		+ " "
																		+ vCTRPersonal
																				.getCApMaterno());
																out.println("<br />");
																jxlsBeanVacio
																		.addBean(vCTRPersonal);
															}
														} else {
															out.println("NO SE DIO DE ALTA PORQUE FALTA NOMBRE, APELLIDOS Y RFC: "
																	+ vCTRPersonal
																			.getCNombre()
																	+ " "
																	+ vCTRPersonal
																			.getCApPaterno()
																	+ " "
																	+ vCTRPersonal
																			.getCApMaterno());
															out.println("<br />");
															jxlsBeanVacio
																	.addBean(vCTRPersonal);
														}
													}
												} catch (DAOException ex) {
													jxlsBeanVacio
															.addBean(vCTRPersonal);

													// ex.printStackTrace();
												}
												/********** BAJAS **********/
												/***************************/
											} else if (estatus
													.equalsIgnoreCase("BAJA")) {
												try {
													String filtro = " where iCveEmpresa = "
															+ vCTRPersonal
																	.getICveEmpresa()
															+ " and iCvePlantilla = "
															+ vCTRPersonal
																	.getiCvePlantilla()
															+ " and cNombre = '"
															+ vCTRPersonal
																	.getCNombre()
															+ "'"
															+ " and cApPaterno = '"
															+ vCTRPersonal
																	.getCApPaterno()
															+ "'"
															+ " and cApMaterno = '"
															+ vCTRPersonal
																	.getCApMaterno()
															+ "'"
															+ " and cRFC = '"
															+ vCTRPersonal
																	.getCRFC()
															+ "'";

													TVCTRPersonal vCTRPersonalBaja = dCTRPersonal
															.findOperador(filtro);
													int existe = dCTRPersonal
															.findOperador2(filtro);

													// La claveBaja me va a
													// regresar siempre ""
													// System.out.println("Existe "
													// +existe);

													if (existe == 0) {
														existe = 0;
														// System.out.println("la persona no existe");
													} else {
														Object claveBaja = dCTRPersonal
																.delete(null,
																		vCTRPersonalBaja);
													}
													// Si iDeletedd == 1 => Si
													// se eliminó el renglón
													// en la BD
													// System.out.println("dCTRPersonal.getIDeleted(): "
													// +
													// dCTRPersonal.getIDeleted());
													if (dCTRPersonal
															.getIDeleted() == 1) {
														jxlsBeanBaja
																.addBean(vCTRPersonalBaja);
													} else {
														jxlsBeanVacio
																.addBean(vCTRPersonal);
													}
												} catch (DAOException ex) {
													jxlsBeanVacio
															.addBean(vCTRPersonal);

													// ex.printStackTrace();
												}
												/********** ACTUALIZACIONES **********/
												/*************************************/
											} else if (estatus
													.equalsIgnoreCase("ACTUALIZACION")
													|| estatus
															.equalsIgnoreCase("ACTUALIZACI�N")) {
												try {
													if (dCTRPersonal
															.existsOperador(
																	vCTRPersonal
																			.getICveEmpresa(),
																	vCTRPersonal
																			.getiCvePlantilla(),
																	vCTRPersonal
																			.getCNombre(),
																	vCTRPersonal
																			.getCApPaterno(),
																	vCTRPersonal
																			.getCApMaterno(),
																	vCTRPersonal
																			.getCRFC())) {
														// Obtengo lo datos de
														// la BD para obtener el
														// iNumPersonal
														// ya que de la carga de
														// Excel va a traer
														// iNumPersonal = 0
														// siempre
														TVCTRPersonal vCtrPersonalBD = dCTRPersonal
																.findOperador(" where iCveEmpresa = "
																		+ vCTRPersonal
																				.getICveEmpresa()
																		+ " and iCvePlantilla = "
																		+ vCTRPersonal
																				.getiCvePlantilla()
																		+ " and cNombre = '"
																		+ vCTRPersonal
																				.getCNombre()
																		+ "' "
																		+ " and cApPaterno = '"
																		+ vCTRPersonal
																				.getCApPaterno()
																		+ "' "
																		+ " and cApMaterno = '"
																		+ vCTRPersonal
																				.getCApMaterno()
																		+ "' "
																		+ " and cRFC = '"
																		+ vCTRPersonal
																				.getCRFC()
																		+ "' ");
														// Asigno iNumPersonal
														// de la BD al de la
														// carga de Excel
														vCTRPersonal
																.setINumPersonal(vCtrPersonalBD
																		.getINumPersonal());

														// Tambien se verifica
														// que la Licencia tenga
														// el Prefijo
														PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
														List list = bean
																.getPrefijosLicenciasBean();
														boolean lLicenciaOK = false;

														// System.out.println("vCTRPersonal.getCLicencia(): "
														// +
														// vCTRPersonal.getCLicencia());

														for (int x = 0; x < list
																.size(); x++) {
															// System.out.println("ANTERIOR..."
															// +
															// ((PrefijosLicenciasBean)list.get(x)).getCPrefijoAnterior());
															// System.out.println("SISTEMA..."
															// +
															// ((PrefijosLicenciasBean)list.get(x)).getCPrefijoSistema());

															if (vCTRPersonal
																	.getCLicencia()
																	.toUpperCase()
																	.startsWith(
																			((PrefijosLicenciasBean) list
																					.get(x))
																					.getCPrefijoAnterior())
																	|| vCTRPersonal
																			.getCLicencia()
																			.toUpperCase()
																			.startsWith(
																					((PrefijosLicenciasBean) list
																							.get(x))
																							.getCPrefijoSistema())) {
																lLicenciaOK = true;
																break;
															}
														}

														// System.out.println("lLicenciaOK: "
														// + lLicenciaOK);

														if (lLicenciaOK == true) {
															// System.out.println("####### DENTRO DEL SERVLET (ACTUALIZACION)...SI EXISTE EL OPERADOR ########");
															// La claveUpdate
															// siempre va a
															// regresar ""
															Object claveUpdate = dCTRPersonal
																	.update(null,
																			vCTRPersonal);

															// Si iUpdated == 1
															// => Si actualizó
															// el renglón en la
															// BD
															// System.out.println("dCTRPersonal.getIUpdated(): "
															// +
															// dCTRPersonal.getIUpdated());
															if (dCTRPersonal
																	.getIUpdated() == 1) {
																jxlsBeanActualizacion
																		.addBean(vCTRPersonal);
															} else {
																jxlsBeanVacio
																		.addBean(vCTRPersonal);
															}
														} else {
															out.println("NO SE ACTUALIZ� PORQUE EL PREFIJO DE LA LICENCIA ESTA INCORRECTO : "
																	+ vCTRPersonal
																			.getCNombre()
																	+ " "
																	+ vCTRPersonal
																			.getCApPaterno()
																	+ " "
																	+ vCTRPersonal
																			.getCApMaterno());
															out.println("<br />");
															jxlsBeanVacio
																	.addBean(vCTRPersonal);
														}
													}
												} catch (DAOException ex) {
													jxlsBeanVacio
															.addBean(vCTRPersonal);

													// ex.printStackTrace();
												}
												// Si no especifican en la celda
												// ALTA, BAJA o ACTUALIZACION
												// entonces verifico primero si
												// el operador existe
												// si existe => realizo
												// ACTUALIZACION
												// si no existe => realizo ALTA
												/********** VACIO **********/
												/***************************/
											} else if (estatus.equals("")) {
												try {
													if (dCTRPersonal
															.existsOperador(
																	vCTRPersonal
																			.getICveEmpresa(),
																	vCTRPersonal
																			.getiCvePlantilla(),
																	vCTRPersonal
																			.getCNombre(),
																	vCTRPersonal
																			.getCApPaterno(),
																	vCTRPersonal
																			.getCApMaterno(),
																	vCTRPersonal
																			.getCRFC())) {
														// Obtengo lo datos de
														// la BD para obtener el
														// iNumPersonal
														// ya que de la carga de
														// Excel va a traer
														// iNumPersonal = 0
														// siempre
														TVCTRPersonal vCtrPersonalBD = dCTRPersonal
																.findOperador(" where iCveEmpresa = "
																		+ vCTRPersonal
																				.getICveEmpresa()
																		+ " and iCvePlantilla = "
																		+ vCTRPersonal
																				.getiCvePlantilla()
																		+ " and cNombre = '"
																		+ vCTRPersonal
																				.getCNombre()
																		+ "' "
																		+ " and cApPaterno = '"
																		+ vCTRPersonal
																				.getCApPaterno()
																		+ "' "
																		+ " and cApMaterno = '"
																		+ vCTRPersonal
																				.getCApMaterno()
																		+ "' "
																		+ " and cRFC = '"
																		+ vCTRPersonal
																				.getCRFC()
																		+ "' ");
														// Asigno iNumPersonal
														// de la BD al de la
														// carga de Excel
														vCTRPersonal
																.setINumPersonal(vCtrPersonalBD
																		.getINumPersonal());

														// Tambien se verifica
														// que la Licencia tenga
														// el Prefijo
														PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
														List list = bean
																.getPrefijosLicenciasBean();
														boolean lLicenciaOK = false;

														for (int x = 0; x < list
																.size(); x++) {
															if (vCTRPersonal
																	.getCLicencia()
																	.toUpperCase()
																	.startsWith(
																			((PrefijosLicenciasBean) list
																					.get(x))
																					.getCPrefijoAnterior())
																	|| vCTRPersonal
																			.getCLicencia()
																			.toUpperCase()
																			.startsWith(
																					((PrefijosLicenciasBean) list
																							.get(x))
																							.getCPrefijoSistema())) {
																lLicenciaOK = true;
																break;
															}
														}

														if (lLicenciaOK == true) {
															// La claveUpdate
															// siempre va a
															// regresar ""
															Object claveUpdate = dCTRPersonal
																	.update(null,
																			vCTRPersonal);

															// Si iUpdated == 1
															// => Si actualizó
															// el renglón en la
															// BD
															// System.out.println("dCTRPersonal.getIUpdated(): "
															// +
															// dCTRPersonal.getIUpdated());
															if (dCTRPersonal
																	.getIUpdated() == 1) {
																jxlsBeanActualizacion
																		.addBean(vCTRPersonal);
															} else {
																jxlsBeanVacio
																		.addBean(vCTRPersonal);
															}
														} else {
															out.println("NO SE ACTUALIZ� PORQUE EL PREFIJO DE LA LICENCIA ESTA INCORRECTO : "
																	+ vCTRPersonal
																			.getCNombre()
																	+ " "
																	+ vCTRPersonal
																			.getCApPaterno()
																	+ " "
																	+ vCTRPersonal
																			.getCApMaterno());
															out.println("<br />");
															jxlsBeanVacio
																	.addBean(vCTRPersonal);
														}
													} else {
														// Lo da de alta si el
														// nombre, apellidos y
														// RFC no estan vacíos
														if (!vCTRPersonal
																.getCNombre()
																.equalsIgnoreCase(
																		"")
																&& !vCTRPersonal
																		.getCApPaterno()
																		.equalsIgnoreCase(
																				"")
																&& !vCTRPersonal
																		.getCApMaterno()
																		.equalsIgnoreCase(
																				"")
																&& !vCTRPersonal
																		.getCRFC()
																		.equalsIgnoreCase(
																				"")) {

															// Tambien se
															// verifica que la
															// Licencia tenga el
															// Prefijo
															PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
															List list = bean
																	.getPrefijosLicenciasBean();
															boolean lLicenciaOK = false;

															for (int x = 0; x < list
																	.size(); x++) {
																if (vCTRPersonal
																		.getCLicencia()
																		.toUpperCase()
																		.startsWith(
																				((PrefijosLicenciasBean) list
																						.get(x))
																						.getCPrefijoAnterior())
																		|| vCTRPersonal
																				.getCLicencia()
																				.toUpperCase()
																				.startsWith(
																						((PrefijosLicenciasBean) list
																								.get(x))
																								.getCPrefijoSistema())) {
																	lLicenciaOK = true;
																	break;
																}
															}

															if (lLicenciaOK == true) {
																String claveAlta = dCTRPersonal
																		.insert(null,
																				vCTRPersonal);

																// Si iInserted
																// == 1 => Si se
																// insertó el
																// renglón en
																// la BD
																// System.out.println("dCTRPersonal.getIInserted(): "
																// +
																// dCTRPersonal.getIInserted());
																if (dCTRPersonal
																		.getIInserted() == 1) {
																	jxlsBeanAlta
																			.addBean(vCTRPersonal);
																} else {
																	jxlsBeanVacio
																			.addBean(vCTRPersonal);
																}
															} else {
																out.println("NO SE DIO DE ALTA PORQUE LA LICENCIA NO TIENE EL PREFIJO CORRECTO: "
																		+ vCTRPersonal
																				.getCNombre()
																		+ " "
																		+ vCTRPersonal
																				.getCApPaterno()
																		+ " "
																		+ vCTRPersonal
																				.getCApMaterno());
																out.println("<br />");
																jxlsBeanVacio
																		.addBean(vCTRPersonal);
															}
														} else {
															out.println("NO SE DIO DE ALTA PORQUE FALTA NOMBRE, APELLIDOS Y RFC: "
																	+ vCTRPersonal
																			.getCNombre()
																	+ " "
																	+ vCTRPersonal
																			.getCApPaterno()
																	+ " "
																	+ vCTRPersonal
																			.getCApMaterno());
															out.println("<br />");
															jxlsBeanVacio
																	.addBean(vCTRPersonal);
														}
													}
												} catch (DAOException ex) {
													jxlsBeanVacio
															.addBean(vCTRPersonal);

													// ex.printStackTrace();
												}
												/**********
												 * CUALQUIER OTRO VALOR
												 * DIFERENTE DE VACIO
												 **********/
												/*************************************************************/
											} else {
												jxlsBeanVacio
														.addBean(vCTRPersonal);
											}
										}
									} // Fin if while
								} // Fin del while
								// System.out.println("Numero de registros procesados"
								// + c);
								// Cerrar Workbook y liberar memoria
								// workbook.close();

								out.println("<br />");
								out.println("<br />");

								// ALTAS
								out.print("<b><i>ALTAS ("
										+ jxlsBeanAlta.getList().size()
										+ ")</i></b>");
								out.print("<table border='4' bordercolor='#0000ff'>");
								out.print("<tr>");
								out.print("<th>No. DE EXPEDIENTE</th>");
								out.print("<th>NOMBRE</th>");
								out.print("<th>APELLIDO PATERNO</th>");
								out.print("<th>APELLIDO MATERNO</th>");
								out.print("</tr>");

								for (int i = 0; i < jxlsBeanAlta.getList()
										.size(); i++) {
									TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanAlta
											.getList().get(i);

									if (vCTRPersonal != null) {
										out.print("<tr>");
										out.print("<td>");
										out.print(vCTRPersonal
												.getICveExpediente());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCNombre());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApPaterno());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApMaterno());
										out.print("</td>");
										out.print("</tr>");
									}
								}

								out.println("</table>");

								out.println("<br />");
								out.println("<br />");

								// BAJAS
								out.print("<b><i>BAJAS ("
										+ jxlsBeanBaja.getList().size()
										+ ")</i></b>");
								out.print("<table border='4' bordercolor='#0000ff'>");
								out.print("<tr>");
								out.print("<th>No. DE EXPEDIENTE</th>");
								out.print("<th>NOMBRE</th>");
								out.print("<th>APELLIDO PATERNO</th>");
								out.print("<th>APELLIDO MATERNO</th>");
								out.print("</tr>");

								for (int i = 0; i < jxlsBeanBaja.getList()
										.size(); i++) {
									TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanBaja
											.getList().get(i);

									if (vCTRPersonal != null) {
										out.print("<tr>");
										out.print("<td>");
										out.print(vCTRPersonal
												.getICveExpediente());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCNombre());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApPaterno());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApMaterno());
										out.print("</td>");
										out.print("</tr>");
									}
								}

								out.println("</table>");

								out.println("<br />");
								out.println("<br />");

								// ACTUALIZACIONES
								out.print("<b><i>ACTUALIZACIONES ("
										+ jxlsBeanActualizacion.getList()
												.size() + ")</i></b>");
								out.print("<table border='4' bordercolor='#0000ff'>");
								out.print("<tr>");
								out.print("<th>No. DE EXPEDIENTE</th>");
								out.print("<th>NOMBRE</th>");
								out.print("<th>APELLIDO PATERNO</th>");
								out.print("<th>APELLIDO MATERNO</th>");
								out.print("</tr>");

								for (int i = 0; i < jxlsBeanActualizacion
										.getList().size(); i++) {
									TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanActualizacion
											.getList().get(i);

									if (vCTRPersonal != null) {
										out.print("<tr>");
										out.print("<td>");
										out.print(vCTRPersonal
												.getICveExpediente());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCNombre());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApPaterno());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApMaterno());
										out.print("</td>");
										out.print("</tr>");
									}
								}

								out.println("</table>");

								out.println("<br />");
								out.println("<br />");

								// VACIO
								out.print("<b><i>NO SE REALIZ� NINGUNA OPERACION ("
										+ jxlsBeanVacio.getList().size()
										+ ")</i></b>");
								out.print("<table border='4' bordercolor='#ff0000'>");
								out.print("<tr>");
								out.print("<th>No. DE EXPEDIENTE</th>");
								out.print("<th>NOMBRE</th>");
								out.print("<th>APELLIDO PATERNO</th>");
								out.print("<th>APELLIDO MATERNO</th>");
								out.print("</tr>");

								for (int i = 0; i < jxlsBeanVacio.getList()
										.size(); i++) {
									TVCTRPersonal vCTRPersonal = (TVCTRPersonal) jxlsBeanVacio
											.getList().get(i);

									if (vCTRPersonal != null) {
										out.print("<tr>");
										out.print("<td>");
										out.print(vCTRPersonal
												.getICveExpediente());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCNombre());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApPaterno());
										out.print("</td>");
										out.print("<td>");
										out.print(vCTRPersonal.getCApMaterno());
										out.print("</td>");
										out.print("</tr>");
									}
								}

								out.println("</table>");

							} catch (IOException e) {
								e.printStackTrace();
							}

							reader.close();
						}// fin del if

						else {
							out.println("<br />");
							out.println("Seleccione un archivo de Excel con formato");
							out.println("<br />");
						}
					}// fin de else
				}
			}
		} catch (FileUploadException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
