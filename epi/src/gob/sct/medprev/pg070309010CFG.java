package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

/**
 * 
 * <p>
 * Title: Registro y Comportamiento del Reactivo y Soluciones para Análisis
 * Presuntivo
 * </p>
 * <p>
 * Description: Obtiene el Reporte
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 */
public class pg070309010CFG extends CFGListBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg070309010CFG() {
		vParametros = new TParametro("07");
	}

	/**
	 * Método fillVector
	 */
	public void fillVector() {
		TDEXPServicio dExpServicio = new TDEXPServicio();
		TDPERDatos dPerDatos = new TDPERDatos();
		TFechas tFechas = new TFechas();

		try {
			// ## AQUI METER EL CODIGO PARA EXCEL ##
			if (request.getParameter("hdReporte") != null) {
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {

					if (request.getParameter("numExpediente") != null
							&& !(request.getParameter("numExpediente")
									.compareToIgnoreCase("") == 0)
							&& request.getParameter("numExamen") != null
							&& !(request.getParameter("numExamen")
									.compareToIgnoreCase("") == 0)
							&& request.getParameter("iCveServicio") != null
							&& !(request.getParameter("iCveServicio")
									.startsWith("Seleccione"))
							&& request.getParameter("dtFecha") != null
							&& !(request.getParameter("dtFecha")
									.compareToIgnoreCase("") == 0)) {

						// Vector vcExpServicio =
						// dExpServicio.getNotaMed(request.getParameter("numExpediente"),
						// request.getParameter("numExamen"),
						// request.getParameter("iCveServicio"));
						HashMap map = new HashMap();
						map.put("iCveExpediente",
								request.getParameter("numExpediente"));
						map.put("iNumExamen", request.getParameter("numExamen"));
						map.put("iCveServicio",
								request.getParameter("iCveServicio"));
						Vector vcExpServicio = dExpServicio.findByPK(map);

						TVEXPServicio vExpServicio = new TVEXPServicio();

						StringBuffer bufferNotaMedicaAnterior = new StringBuffer();
						StringBuffer bufferNotaMedicaActual = new StringBuffer();

						for (int i = 0; i < vcExpServicio.size(); i++) {
							vExpServicio = (TVEXPServicio) vcExpServicio.get(i);
							bufferNotaMedicaAnterior.append(vExpServicio
									.getCNotaMedica());
							bufferNotaMedicaAnterior.append("\n");
						}

						int iInserted = 0;
						int iUpdated = 0;

						if (request.getParameter("notaActual") != null) {
							if (!(request.getParameter("notaActual")
									.compareToIgnoreCase("") == 0)) {
								bufferNotaMedicaActual.append(request
										.getParameter("dtFecha"));
								bufferNotaMedicaActual.append(": ");
								bufferNotaMedicaActual.append("\n");
								bufferNotaMedicaActual.append(request
										.getParameter("notaActual"));

								vExpServicio
										.setICveExpediente(Integer.parseInt(request
												.getParameter("numExpediente")));
								vExpServicio.setINumExamen(Integer
										.parseInt(request
												.getParameter("numExamen")));
								vExpServicio.setICveServicio(Integer
										.parseInt(request
												.getParameter("iCveServicio")));

								// Si el Vector > 0 => Ya hay datos ACTUALIZAR
								if (vcExpServicio.size() > 0) {
									vExpServicio.setDtFin(tFechas.TodaySQL());
									vExpServicio
											.setCNotaMedica(bufferNotaMedicaAnterior
													.toString()
													+ " "
													+ "\n"
													+ bufferNotaMedicaActual
															.toString());
									dExpServicio.update(null, vExpServicio);
									iUpdated = dExpServicio.getIUpdated();
									// Si el Vector == 0 => No hay Datos
									// INSERTAR
								} else {
									vExpServicio
											.setDtInicio(tFechas.TodaySQL());
									vExpServicio.setDtFin(tFechas.TodaySQL());
									vExpServicio.setLConcluido(0);
									vExpServicio
											.setCNotaMedica(bufferNotaMedicaActual
													.toString());

									dExpServicio.insert(null, vExpServicio);

									iInserted = dExpServicio.getIInserted();
								}
							}
						}

						URL url = new URL(
								vParametros.getPropEspecifica("ExcelRutaOrig")
										+ "pg070309010.xls");
						URLConnection urlConnection = url.openConnection();
						InputStream is = urlConnection.getInputStream();
						BufferedInputStream in = new BufferedInputStream(is);
						FileOutputStream file = new FileOutputStream(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070309010-out.xls");
						BufferedOutputStream out = new BufferedOutputStream(
								file);

						int i;

						while ((i = in.read()) != -1) {
							out.write(i);
						}

						out.flush();

						// Obtengo el Nombre, Sexo, etc.
						TVPERDatos vPerDatos = dPerDatos
								.findUserbyExp(vExpServicio.getICveExpediente());

						if (vPerDatos != null) {
							POIFSFileSystem fs = new POIFSFileSystem(
									new FileInputStream(vParametros
											.getPropEspecifica("ExcelRutaDest")
											+ "pg070309010-out.xls"));
							HSSFWorkbook wb = new HSSFWorkbook(fs);
							HSSFSheet sheet0 = wb.getSheetAt(0);

							HSSFRow row2 = sheet0.getRow(1);
							HSSFRow row3 = sheet0.getRow(2);
							HSSFRow row4 = sheet0.getRow(3);

							HSSFCell cellServicio = row2.getCell((short) 1);
							HSSFCell cellNumExpediente = row2
									.getCell((short) 3);
							HSSFCell cellNombre = row3.getCell((short) 1);
							HSSFCell cellEdad = row4.getCell((short) 1);
							HSSFCell cellGenero = row4.getCell((short) 3);

							if (cellServicio == null) {
								cellServicio = row2.createCell((short) 1);
							}

							if (cellNumExpediente == null) {
								cellNumExpediente = row2.createCell((short) 3);
							}

							if (cellNombre == null) {
								cellNombre = row3.createCell((short) 1);
							}

							if (cellEdad == null) {
								cellEdad = row4.createCell((short) 1);
							}

							if (cellGenero == null) {
								cellGenero = row4.createCell((short) 3);
							}

							cellServicio.setCellValue(vExpServicio
									.getCDscServicio());
							cellNumExpediente.setCellValue(vExpServicio
									.getICveExpediente());
							cellNombre.setCellValue(vPerDatos
									.getCNombreCompleto());
							cellEdad.setCellValue(vPerDatos.getDtNacimiento());
							cellGenero.setCellValue(vPerDatos.getCGenero());

							String[] notaArray = vExpServicio.getCNotaMedica()
									.split("\n");
							HSSFRow[] rowArray = new HSSFRow[notaArray.length];
							HSSFCell[] cellArray = new HSSFCell[notaArray.length];

							for (int j = 0; j < notaArray.length; j++) {
								int row = j + 5;

								rowArray[j] = sheet0.getRow(row);

								if (rowArray[j] == null) {
									rowArray[j] = sheet0.createRow((short) row);
								}

								cellArray[j] = rowArray[j].getCell((short) 1);

								if (cellArray[j] == null) {
									cellArray[j] = rowArray[j]
											.createCell((short) 1);
								}

								cellArray[j].setCellValue(notaArray[j]);
							}

							FileOutputStream fileOut = new FileOutputStream(
									vParametros
											.getPropEspecifica("ExcelRutaDest")
											+ "pg070309010-out.xls");
							wb.write(fileOut);
							fileOut.flush();
							fileOut.close();
						}

						/**
						 * Abrir el archivo generado de Excel
						 */
						if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
							TExcel Rep = new TExcel("07");
							this.activeX.append(Rep.creaActiveX(
									"pg070309010-out", false, false, true));
						}
					}
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}
}
