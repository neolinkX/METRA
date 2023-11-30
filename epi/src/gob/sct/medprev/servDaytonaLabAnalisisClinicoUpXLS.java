package gob.sct.medprev;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * 
 * <p>
 * Title: Cargar Archivo
 * </p>
 * <p>
 * Description: Carga un archivo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ing. Rafael Alcocer Caldera
 * @version 1.0
 */
public class servDaytonaLabAnalisisClinicoUpXLS extends HttpServlet {

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
		TFechas tFechas = new TFechas();

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
					File fullFile = new File(item.getName());
					// File savedFile = new
					// File(getServletContext().getRealPath("/"),
					// fullFile.getName());
					// File savedFile = new
					// File(vParametros.getPropEspecifica("ExcelRutaDest"),
					// fullFile.getName());
					// File savedFile = new
					// File(vParametros.getPropEspecifica("ExcelRutaDest"),
					// "DaytonaLabAnalisisClinicoXLS.xls");

					// item.write(savedFile);

					try {
						// FileReader fileReader = new FileReader(savedFile);
						// BufferedReader reader = new
						// BufferedReader(fileReader);
						FileInputStream fis = new FileInputStream(fullFile);
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(fis));

						String line = null;

						int indexPatient = 0;
						int indexGLU = 0;
						int indexCHOL = 0;
						int indexTRIG = 0;
						int indexDHDL = 0;
						int indexLDL = 0;
						int indexHbA1c = 0;
						int indexINDAT = 0;

						List listPatient = new ArrayList();
						List listGLU = new ArrayList();
						List listCHOL = new ArrayList();
						List listTRIG = new ArrayList();
						List listDHDL = new ArrayList();
						List listLDL = new ArrayList();
						List listHbA1c = new ArrayList();
						List listINDAT = new ArrayList();

						out.print("<html>");
						// out.print("<script language='JavaScript'>");
						// out.print("function hola() {");
						// out.print("alert('HOLA')");
						// out.print("}");
						// out.print("</script>");
						out.print("<head>");
						out.print("</head>");
						out.print("<body>");
						out.print("<form name='daytonaForm'>");
						out.print("<table border='4' bordercolor='#0000ff'>");

						while ((line = reader.readLine()) != null) {
							String[] result = line.split(",");

							for (int i = 0; i < result.length; i++) {
								if (result[i].startsWith("Patient")) {
									indexPatient = i;
								} else if (result[i].startsWith("GLU")) {
									indexGLU = i;
								} else if (result[i].startsWith("CHOL")) {
									indexCHOL = i;
								} else if (result[i].startsWith("TRIG")) {
									indexTRIG = i;
								} else if (result[i].startsWith("DHDL")) {
									indexDHDL = i;
								} else if (result[i].startsWith("LDL")) {
									indexLDL = i;
								} else if (result[i].startsWith("HbA1c")) {
									indexHbA1c = i;
								} else if (result[i].startsWith("IND")) {
									indexINDAT = i;
								}

								if (indexPatient != 0 && indexPatient == i) {
									if (result[indexPatient] == null
											|| result[indexPatient]
													.compareToIgnoreCase("") == 0) {
										listPatient.add("");
									} else {
										listPatient.add(result[indexPatient]);
									}
								}

								if (indexGLU != 0 && indexGLU == i) {
									if (result[indexGLU] == null
											|| result[indexGLU]
													.compareToIgnoreCase("") == 0) {
										listGLU.add("");
									} else {
										listGLU.add(result[indexGLU]);
									}
								}

								if (indexCHOL != 0 && indexCHOL == i) {
									if (result[indexCHOL] == null
											|| result[indexCHOL]
													.compareToIgnoreCase("") == 0) {
										listCHOL.add("");
									} else {
										listCHOL.add(result[indexCHOL]);
									}
								}

								if (indexTRIG != 0 && indexTRIG == i) {
									if (result[indexTRIG] == null
											|| result[indexTRIG]
													.compareToIgnoreCase("") == 0) {
										listTRIG.add("");
									} else {
										listTRIG.add(result[indexTRIG]);
									}
								}

								if (indexDHDL != 0 && indexDHDL == i) {
									if (result[indexDHDL] == null
											|| result[indexDHDL]
													.compareToIgnoreCase("") == 0) {
										listDHDL.add("");
									} else {
										listDHDL.add(result[indexDHDL]);
									}
								}

								if (indexLDL != 0 && indexLDL == i) {
									if (result[indexLDL] == null
											|| result[indexLDL]
													.compareToIgnoreCase("") == 0) {
										listLDL.add("");
									} else {
										listLDL.add(result[indexLDL]);
									}
								}

								if (indexHbA1c != 0 && indexHbA1c == i) {
									if (result[indexHbA1c] == null
											|| result[indexHbA1c]
													.compareToIgnoreCase("") == 0) {
										listHbA1c.add("");
									} else {
										listHbA1c.add(result[indexHbA1c]);
									}
								}

								if (indexINDAT != 0 && indexINDAT == i) {
									if (result[indexINDAT] == null
											|| result[indexINDAT]
													.compareToIgnoreCase("") == 0) {
										listINDAT.add("");
									} else {
										listINDAT.add(result[indexINDAT]);
									}
								}
							}
						} // Fin While

						int size = 0;

						if (listPatient.size() > 0) {
							size = listPatient.size();
						} else if (listGLU.size() > 0) {
							size = listGLU.size();
						} else if (listCHOL.size() > 0) {
							size = listCHOL.size();
						} else if (listTRIG.size() > 0) {
							size = listTRIG.size();
						} else if (listDHDL.size() > 0) {
							size = listDHDL.size();
						} else if (listLDL.size() > 0) {
							size = listLDL.size();
						} else if (listHbA1c.size() > 0) {
							size = listHbA1c.size();
						} else if (listINDAT.size() > 0) {
							size = listINDAT.size();
						}

						// Con lo sig. almaceno el estado de insercion
						// o actualizacion de la tabla EXPRESULTADO
						int iInsertedGLU = 0;
						int iUpdatedGLU = 0;
						int iInsertedCHOL = 0;
						int iUpdatedCHOL = 0;
						int iInsertedTRIG = 0;
						int iUpdatedTRIG = 0;
						int iInsertedDHDL = 0;
						int iUpdatedDHDL = 0;
						int iInsertedLDL = 0;
						int iUpdatedLDL = 0;
						int iInsertedHbA1c = 0;
						int iUpdatedHbA1c = 0;
						int iInsertedINDAT = 0;
						int iUpdatedINDAT = 0;

						// Generar Tabla de los datos leidos del archivo
						for (int i = 0; i < size; i++) {
							out.print("<tr>");

							// Las sig. lineas agregarian un campo de texto
							// vacio
							// para ingresar el Num de Examen pero por lo pronto
							// se va a quedar fijo en 1
							// out.print("<td>");
							// if
							// (((String)listPatient.get(i)).startsWith("Patient"))
							// {
							// out.print("<input type='text' name='txtNumExamen' value='Num Examen' readonly='true'/>");
							// } else {
							// out.print("<input type='text' name='txtNumExamen'/>");
							// }
							// out.print("</td>");

							// DE LO SIG. ESTOY OBTENIENDO UNDEFINED
							// PERO PARA EL LAB DE ANALSIS CLINICO iCveServicio
							// = 2
							// System.out.println("Servicio: " +
							// request.getParameter("icveservicio"));

							// ### AQUI AGREGAR EL CODIGO DE INSERCION A LA BASE
							// DE DATOS
							// ### YA QUE SE INSERTARAN VARIOS NO SOLO UNO COMO
							// EN EL CHOLESTECH
							// A continuacion obtengo el No. de Expediente
							TDEXPCtrolLab dExpCtrolLab = new TDEXPCtrolLab();
							TDEXPResultado dExpResultado = new TDEXPResultado();
							TVEXPResultado vExpResultado = new TVEXPResultado();
							TDEXPRama dExpRama = new TDEXPRama();
							TVEXPRama vExpRama = new TVEXPRama();

							// Ya que el primer dato de listPatient.get(i)
							// es Patient#... entonces éste lo debo omitir
							if (!((String) listPatient.get(i))
									.startsWith("Patient")) {
								Vector vectorExpCtrolLab = dExpCtrolLab
										.findByWhere(" where iCveAnalisis = "
												+ new Integer(
														(String) listPatient
																.get(i))
														.intValue());

								TVExpCtrolLab vExpCtrolLab = new TVExpCtrolLab();

								// Aqui solo voy a obtener un resultado
								// con el cual puedo obtener el No. de
								// Expediente.
								for (int j = 0; j < vectorExpCtrolLab.size(); j++) {
									vExpCtrolLab = (TVExpCtrolLab) vectorExpCtrolLab
											.get(j);
								}

								// Aqui asigno los datos generales a Resultado
								vExpResultado.setICveExpediente(vExpCtrolLab
										.getICveExpediente());
								vExpResultado.setINumExamen(1);
								vExpResultado.setICveServicio(2); // 2 => LAB.
																	// DE
																	// ANALSIS
																	// CLINICO
								vExpResultado.setICveRama(Integer
										.parseInt(request
												.getParameter("icverama")));
								vExpResultado.setCValRef("");
								vExpResultado.setCCaracter("");
								// Aqui asigno los datos generales a Rama
								vExpRama.setICveExpediente(vExpCtrolLab
										.getICveExpediente());
								vExpRama.setINumExamen(1);
								vExpRama.setICveServicio(2); // 2 => LAB. DE
																// ANALSIS
																// CLINICO
								vExpRama.setICveRama(Integer.parseInt(request
										.getParameter("icverama")));
								vExpRama.setDtInicio(tFechas.TodaySQL());
								vExpRama.setDtFin(tFechas.TodaySQL());
							}

							if (listPatient.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtPatient' value='"
										+ listPatient.get(i)
										+ "' readonly='true' />");
								out.print("</td>");
							}

							// *******
							// GLUCOSA
							// *******
							if (listGLU.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtGLU' value='"
										+ listGLU.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								// Ya que el primer dato de listGLU.get(i)
								// comienza con GLU... entonces éste lo debo
								// omitir
								if (!((String) listGLU.get(i))
										.startsWith("GLU")
										&& !((String) listGLU.get(i))
												.equals("")) {
									// System.out.println("### GLU..." +
									// listGLU.get(i));
									vExpResultado.setICveSintoma(1); // GLUCOSA
																		// =>
																		// Sintoma
																		// = 1
									vExpResultado.setDValorIni(new Float(
											(String) listGLU.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listGLU.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedGLU = dExpResultado.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedGLU = dExpResultado.getIUpdated();

									// System.out.println("#### GLU iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### GLU iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							// ****************
							// COLESTEROL TOTAL
							// ****************
							if (listCHOL.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtCHOL' value='"
										+ listCHOL.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								if (!((String) listCHOL.get(i))
										.startsWith("CHOL")
										&& !((String) listCHOL.get(i))
												.equals("")) {
									// System.out.println("### CHOL..." +
									// listCHOL.get(i));
									vExpResultado.setICveSintoma(5); // COLESTEROL
																		// =>
																		// Sintoma
																		// = 5
									vExpResultado.setDValorIni(new Float(
											(String) listCHOL.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listCHOL.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedCHOL = dExpResultado
											.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedCHOL = dExpResultado.getIUpdated();

									// System.out.println("#### CHOL iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### CHOL iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							// *************
							// TRIGLICERIDOS
							// *************
							if (listTRIG.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtTRIG' value='"
										+ listTRIG.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								if (!((String) listTRIG.get(i))
										.startsWith("TRIG")
										&& !((String) listTRIG.get(i))
												.equals("")) {
									// System.out.println("### TRIG..." +
									// listTRIG.get(i));
									vExpResultado.setICveSintoma(6); // TRIGLICERIDOS
																		// =>
																		// Sintoma
																		// = 6
									vExpResultado.setDValorIni(new Float(
											(String) listTRIG.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listTRIG.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedTRIG = dExpResultado
											.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedTRIG = dExpResultado.getIUpdated();

									// System.out.println("#### TRIG iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### TRIG iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							// ****
							// DHDL
							// ****
							if (listDHDL.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtDHDL' value='"
										+ listDHDL.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								if (!((String) listDHDL.get(i))
										.startsWith("DHDL")
										&& !((String) listDHDL.get(i))
												.equals("")) {
									// System.out.println("### DHDL..." +
									// listDHDL.get(i));
									vExpResultado.setICveSintoma(8); // DHDL =>
																		// Sintoma
																		// = 8
									vExpResultado.setDValorIni(new Float(
											(String) listDHDL.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listDHDL.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedDHDL = dExpResultado
											.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedDHDL = dExpResultado.getIUpdated();

									// System.out.println("#### DHDL iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### DHDL iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							// ***
							// LDL
							// ***
							if (listLDL.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtLDL' value='"
										+ listLDL.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								if (!((String) listLDL.get(i))
										.startsWith("LDL")
										&& !((String) listLDL.get(i))
												.equals("")) {
									// System.out.println("### LDL..." +
									// listLDL.get(i));
									vExpResultado.setICveSintoma(9); // LDL =>
																		// Sintoma
																		// = 9
									vExpResultado.setDValorIni(new Float(
											(String) listDHDL.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listDHDL.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedLDL = dExpResultado.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedLDL = dExpResultado.getIUpdated();

									// System.out.println("#### LDL iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### LDL iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							// *****
							// HbA1c
							// *****
							if (listHbA1c.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtHbA1c' value='"
										+ listHbA1c.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								if (!((String) listHbA1c.get(i))
										.startsWith("HbA1c")
										&& !((String) listHbA1c.get(i))
												.equals("")) {
									// System.out.println("### HbA1c..." +
									// listHbA1c.get(i));
									vExpResultado.setICveSintoma(10); // HbA1c
																		// =>
																		// Sintoma
																		// = 10
									vExpResultado.setDValorIni(new Float(
											(String) listHbA1c.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listHbA1c.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedHbA1c = dExpResultado
											.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedHbA1c = dExpResultado.getIUpdated();

									// System.out.println("#### HbA1c iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### HbA1c iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							// ******************
							// INDICE ATEROGENICO
							// ******************
							if (listINDAT.size() > 0) {
								out.print("<td>");
								out.print("<input type='text' name='txtINDAT' value='"
										+ listINDAT.get(i)
										+ "' readonly='true' />");
								out.print("</td>");

								if (!((String) listINDAT.get(i))
										.startsWith("IND")
										&& !((String) listINDAT.get(i))
												.equals("")) {
									// System.out.println("### IND AT..." +
									// listINDAT.get(i));
									vExpResultado.setICveSintoma(13); // IND AT
																		// =>
																		// Sintoma
																		// = 13
									vExpResultado.setDValorIni(new Float(
											(String) listINDAT.get(i))
											.floatValue());
									vExpResultado.setDValorFin(new Float(
											(String) listINDAT.get(i))
											.floatValue());
									vExpResultado.setLLogico(1);

									// El sig. insert hace esto:
									// insert into EXPResultado(iCveExpediente,
									// iNumExamen,
									// iCveServicio, iCveRama, iCveSintoma,
									// dValorIni,
									// lLogico, cCaracter, dValorFin, cValRef)
									// values(?,?,?,?,?,null,null,null,null,?)
									dExpResultado.insert(null, vExpResultado);
									iInsertedINDAT = dExpResultado
											.getIInserted();

									// El sig. update hace esto:
									// update EXPResultado set dValorIni= ?,
									// lLogico= ?, cCaracter= ?, dValorFin= ?
									// //cValRef= ?
									// where iCveExpediente = ?
									// and iNumExamen = ?
									// and iCveServicio = ?
									// and iCveRama = ?
									// and iCveSintoma = ?
									dExpResultado.update(null, vExpResultado);
									iUpdatedINDAT = dExpResultado.getIUpdated();

									// System.out.println("#### IND AT iInserted: "
									// + dExpResultado.getIInserted());
									// System.out.println("#### IND AT iUpdated: "
									// + dExpResultado.getIUpdated());
									// System.out.println();
								}
							}

							out.print("</tr>");

							// Aqui termina la rama, por lo tanto, la cierro
							// siempre y cuando los inserts o updates sean > 0
							vExpRama.setIConcluido(1);
							dExpRama.insert(null, vExpRama);
							dExpRama.update(null, vExpRama);

							// System.out.println("########## RAMA INSERTADA..."
							// + dExpRama.getIInserted());
							// System.out.println("########## RAMA ACTUALIZAD..."
							// + dExpRama.getIUpdated());
						}

						// out.print("<tr>");
						// out.print("<td>");
						// out.print("<input type='button' value='Insertar Datos' "+
						// "onclick='hola();'/>");
						// out.print("</td>");
						// out.print("</tr>");
						out.print("</table>");
						out.print("</form>");
						out.print("</body>");
						out.print("</html>");

						reader.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
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
