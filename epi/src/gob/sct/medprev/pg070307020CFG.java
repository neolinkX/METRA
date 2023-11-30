package gob.sct.medprev;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import org.jxls.transformer.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuración para CFG de la página pg070101020
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Jaime Enrique Suárez Romero
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070101020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070101020CFG.png'>
 */
public class pg070307020CFG extends CFGListBase2 {

	private JXLSBean jxlsBean; // Agregado por Rafael Alcocer Caldera
	private boolean lTempRefrigeradores; // Agregado por Rafael Alcocer Caldera
	private boolean lTemperaturaAmbiente; // Agregado por Rafael Alcocer Caldera
	private boolean lHumedad; // Agregado por Rafael Alcocer Caldera
	private StringBuffer activeX = new StringBuffer(""); // Agregado por Rafael
															// Alcocer Caldera

	public pg070307020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDTOXTemperRefr dToxTemperRefr = new TDTOXTemperRefr();
		TDTOXTurnoRef dToxTurnoRef = new TDTOXTurnoRef();

		if (request.getParameter("iCveArea") != null
				&& !request.getParameter("iCveArea").equals("")
				&& request.getParameter("iAnio") != null
				&& !request.getParameter("iAnio").equals("")
				&& request.getParameter("iMeses") != null
				&& !request.getParameter("iMeses").equals("")
				&& request.getParameter("iCveEquipo") != null
				&& !request.getParameter("iCveEquipo").equals("")) {

			int iAnio = Integer.parseInt(request.getParameter("iAnio"));
			int iMeses = Integer.parseInt(request.getParameter("iMeses"));
			int iCveArea = Integer.parseInt(request.getParameter("iCveArea"));
			int iCveEquipo = Integer.parseInt(request
					.getParameter("iCveEquipo"));
			List listTurnos = new ArrayList();
			List listEquipos = new ArrayList();
			List listAreas = new ArrayList();

			try {
				listTurnos = dToxTemperRefr.findTurnos(iCveArea);
				listEquipos = dToxTemperRefr.findEquipos();
				listAreas = dToxTurnoRef.findAreas();
			} catch (DAOException ex) {
				ex.printStackTrace();
			}

			/*
			 * Si se oprime el link Reporte ****************************
			 */
			if (request.getParameter("hdReporte") != null) {
				/*
				 * REFRIGERADORES Y CONGELADORES *****************************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Reporte") == 0) {
					try {
						// Primero bajo la plantilla del servidor al disco local
						// Este método por default ya incluye la ruta y la
						// extensión (.xls)
						TExcel RepTemp = new TExcel("07");
						this.activeX.append(RepTemp.creaActiveX("Temperaturas",
								"Temperaturas", false, false, false));

						JXLSBean jxlsbean = dToxTemperRefr.generaReporte(iAnio,
								iMeses, iCveArea, iCveEquipo, listTurnos);
						Map beans = new HashMap();
						beans.put("jxlsbean", jxlsbean);
						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "Temperaturas.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "Temperaturas-out.xls");
						// URL url = new
						// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
						// "Temperaturas.xls");
						// InputStream is = url.openStream();

						// HSSFWorkbook workbook = transformer.transformXLS(is,
						// beans);

						// FileOutputStream fileOutput = new
						// FileOutputStream(vParametros.getPropEspecifica("ExcelRutaDest")
						// + "Temperaturas-out.xls");
						// workbook.write(fileOutput);
						// fileOutput.flush();
						// fileOutput.close();

						POIFSFileSystem fs = new POIFSFileSystem(
								new FileInputStream(vParametros
										.getPropEspecifica("ExcelRutaDest")
										+ "Temperaturas-out.xls"));
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet0 = wb.getSheetAt(0);

						// Estilo para Marca, Modelo y Serie
						HSSFFont font = wb.createFont();
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
						HSSFCellStyle style = wb.createCellStyle();
						style.setFont(font);
						style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

						// MES, AÑO, AREA
						HSSFRow row9 = sheet0.getRow(8);
						// MARCA
						HSSFRow row24 = sheet0.getRow(23);
						HSSFRow row25 = sheet0.getRow(24);
						// MODELO
						HSSFRow row26 = sheet0.getRow(25);
						HSSFRow row27 = sheet0.getRow(26);
						// SERIE
						HSSFRow row28 = sheet0.getRow(27);
						HSSFRow row29 = sheet0.getRow(28);

						HSSFCell cellMes = row9.getCell((short) 10);
						HSSFCell cellAnio = row9.getCell((short) 16);
						HSSFCell cellArea = row9.getCell((short) 24);

						HSSFCell cellEtiquetaMarca = row24.getCell((short) 22);
						HSSFCell cellMarca = row25.getCell((short) 22);

						HSSFCell cellEtiquetaModelo = row26.getCell((short) 22);
						HSSFCell cellModelo = row27.getCell((short) 22);

						HSSFCell cellEtiquetaSerie = row28.getCell((short) 22);
						HSSFCell cellSerie = row29.getCell((short) 22);

						if (cellMes == null) {
							cellMes = row9.createCell((short) 10);
						}

						if (cellAnio == null) {
							cellAnio = row9.createCell((short) 16);
						}

						if (cellArea == null) {
							cellArea = row9.createCell((short) 24);
						}

						if (cellEtiquetaMarca == null) {
							cellEtiquetaMarca = row24.createCell((short) 22);
						}

						if (cellMarca == null) {
							cellMarca = row25.createCell((short) 22);
						}

						if (cellEtiquetaModelo == null) {
							cellEtiquetaModelo = row26.createCell((short) 22);
						}

						if (cellModelo == null) {
							cellModelo = row27.createCell((short) 22);
						}

						if (cellEtiquetaSerie == null) {
							cellEtiquetaSerie = row28.createCell((short) 22);
						}

						if (cellSerie == null) {
							cellSerie = row29.createCell((short) 22);
						}

						cellEtiquetaMarca.setCellStyle(style);
						cellEtiquetaModelo.setCellStyle(style);
						cellEtiquetaSerie.setCellStyle(style);

						// Despues de dToxTemperRefr.generaReporte()
						// puedo obtener datos de este bean
						pg070307020Bean bean2 = dToxTemperRefr.getBean2();

						TDEQMMarca dEQMMarca = new TDEQMMarca();
						Vector vcMarca = dEQMMarca.FindByAll(
								" where iCveMarca = " + bean2.getIcvemarca(),
								"");

						if (vcMarca.size() > 0) {
							for (int i = 0; i < vcMarca.size(); i++) {
								TVEQMMarca vEQMMarca = (TVEQMMarca) vcMarca
										.get(i);
								bean2.setCdscmarca(vEQMMarca.getCDscMarca());
							}
						} else {
							bean2.setCdscmarca("");
						}

						cellMes.setCellValue(String.valueOf(bean2.getImes()));
						cellAnio.setCellValue(String.valueOf(bean2.getIanio()));
						cellArea.setCellValue(bean2.getCdscArea());
						cellEtiquetaMarca.setCellValue("Marca:");
						cellMarca.setCellValue(bean2.getCdscmarca());
						cellEtiquetaModelo.setCellValue("Modelo:");
						cellModelo.setCellValue(bean2.getCmodelo());
						cellEtiquetaSerie.setCellValue("No. de Serie:");
						cellSerie.setCellValue(bean2.getCnumserie());

						FileOutputStream fileOut = new FileOutputStream(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "Temperaturas-out.xls");
						wb.write(fileOut);
						fileOut.flush();
						fileOut.close();
					} catch (DAOException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel RepTempOut = new TExcel("07");
						// Este método por default ya incluye la ruta y la
						// extensión (.xls)
						this.activeX.append(RepTempOut.creaActiveX(
								"Temperaturas-out", false, false, true));
					}
					/*
					 * TEMPERATURA AMBIENTE ********************
					 */
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("ReporteTemperaturaAmbiente") == 0) {
					try {
						// Primero bajo la plantilla del servidor al disco local
						// Este método por default ya incluye la ruta y la
						// extensión (.xls)
						TExcel RepTempAmb = new TExcel("07");
						this.activeX.append(RepTempAmb.creaActiveX(
								"TemperaturaAmbiente", "TemperaturaAmbiente",
								false, false, false));

						JXLSBean jxlsbean = dToxTurnoRef
								.generaReporteTempAmbiente(iAnio, iMeses,
										iCveArea, listTurnos);
						Map beans = new HashMap();
						beans.put("jxlsbean", jxlsbean);
						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "TemperaturaAmbiente.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "TemperaturaAmbiente-out.xls");
						// URL url = new
						// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
						// "TemperaturaAmbiente.xls");
						// InputStream is = url.openStream();

						// HSSFWorkbook workbook = transformer.transformXLS(is,
						// beans);

						// FileOutputStream fileOutput = new
						// FileOutputStream(vParametros.getPropEspecifica("ExcelRutaDest")
						// + "TemperaturaAmbiente-out.xls");
						// workbook.write(fileOutput);
						// fileOutput.flush();
						// fileOutput.close();

						POIFSFileSystem fs = new POIFSFileSystem(
								new FileInputStream(vParametros
										.getPropEspecifica("ExcelRutaDest")
										+ "TemperaturaAmbiente-out.xls"));
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet0 = wb.getSheetAt(0);

						// MES, AÑO, AREA
						HSSFRow row9 = sheet0.getRow(8);

						HSSFCell cellMes = row9.getCell((short) 10);
						HSSFCell cellAnio = row9.getCell((short) 16);
						HSSFCell cellArea = row9.getCell((short) 24);

						if (cellMes == null) {
							cellMes = row9.createCell((short) 10);
						}

						if (cellAnio == null) {
							cellAnio = row9.createCell((short) 16);
						}

						if (cellArea == null) {
							cellArea = row9.createCell((short) 24);
						}

						// Despues de dToxTemperRefr.generaReporte()
						// puedo obtener datos de este bean
						pg070307020Bean bean2 = dToxTurnoRef.getBean2();

						cellMes.setCellValue(String.valueOf(bean2.getImes()));
						cellAnio.setCellValue(String.valueOf(bean2.getIanio()));
						cellArea.setCellValue(bean2.getCdscArea());

						FileOutputStream fileOut = new FileOutputStream(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "TemperaturaAmbiente-out.xls");
						wb.write(fileOut);
						fileOut.flush();
						fileOut.close();

						/**
						 * Abrir el archivo generado de Excel
						 */
						if ("ReporteTemperaturaAmbiente"
								.equalsIgnoreCase(this.cAccionOriginal)) {
							TExcel RepTempAmbOut = new TExcel("07");
							// Este método por default ya incluye la ruta y la
							// extensión (.xls)
							this.activeX.append(RepTempAmbOut.creaActiveX(
									"TemperaturaAmbiente-out", false, false,
									true));
						}
					} catch (DAOException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					/*
					 * HUMEDAD *******
					 */
				} else if (request.getParameter("hdReporte")
						.compareToIgnoreCase("ReporteHumedad") == 0) {
					try {
						// Primero bajo la plantilla del servidor al disco local
						// Este método por default ya incluye la ruta y la
						// extensión (.xls)
						TExcel RepHum = new TExcel("07");
						this.activeX.append(RepHum.creaActiveX("Humedad",
								"Humedad", false, false, false));

						JXLSBean jxlsbean = dToxTurnoRef.generaReporteHumedad(
								iAnio, iMeses, iCveArea, listTurnos);
						Map beans = new HashMap();
						beans.put("jxlsbean", jxlsbean);
						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "Humedad.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "Humedad-out.xls");
						// URL url = new
						// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
						// "Humedad.xls");
						// InputStream is = url.openStream();

						// HSSFWorkbook workbook = transformer.transformXLS(is,
						// beans);

						// FileOutputStream fileOutput = new
						// FileOutputStream(vParametros.getPropEspecifica("ExcelRutaDest")
						// + "Humedad-out.xls");
						// workbook.write(fileOutput);
						// fileOutput.flush();
						// fileOutput.close();

						POIFSFileSystem fs = new POIFSFileSystem(
								new FileInputStream(vParametros
										.getPropEspecifica("ExcelRutaDest")
										+ "Humedad-out.xls"));
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet0 = wb.getSheetAt(0);

						// MES, AÑO, AREA
						HSSFRow row9 = sheet0.getRow(8);

						HSSFCell cellMes = row9.getCell((short) 10);
						HSSFCell cellAnio = row9.getCell((short) 16);
						HSSFCell cellArea = row9.getCell((short) 24);

						if (cellMes == null) {
							cellMes = row9.createCell((short) 10);
						}

						if (cellAnio == null) {
							cellAnio = row9.createCell((short) 16);
						}

						if (cellArea == null) {
							cellArea = row9.createCell((short) 24);
						}

						// Despues de dToxTemperRefr.generaReporte()
						// puedo obtener datos de este bean
						pg070307020Bean bean2 = dToxTurnoRef.getBean2();

						cellMes.setCellValue(String.valueOf(bean2.getImes()));
						cellAnio.setCellValue(String.valueOf(bean2.getIanio()));
						cellArea.setCellValue(bean2.getCdscArea());

						FileOutputStream fileOut = new FileOutputStream(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "Humedad-out.xls");
						wb.write(fileOut);
						fileOut.flush();
						fileOut.close();

						/**
						 * Abrir el archivo generado de Excel
						 */
						if ("ReporteHumedad"
								.equalsIgnoreCase(this.cAccionOriginal)) {
							TExcel RepHumOut = new TExcel("07");
							// Este método por default ya incluye la ruta y la
							// extensión (.xls)
							this.activeX.append(RepHumOut.creaActiveX(
									"Humedad-out", false, false, true));
						}
					} catch (DAOException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}

			if (request.getParameter("hdBuscarTemperaturas") != null) {
				/*
				 * Si se oprime el link Refrigeradores y Congeladores
				 * **************************************************
				 */
				if (request.getParameter("hdBuscarTemperaturas")
						.compareToIgnoreCase("BuscarRefrigeradores") == 0) {
					try {
						jxlsBean = dToxTemperRefr.findTemperaturas(iAnio,
								iMeses, iCveArea, listTurnos, listEquipos);
						lTempRefrigeradores = true;
					} catch (DAOException ex) {
						ex.printStackTrace();
					}
					/*
					 * Si se oprime el link Temperatura Ambiente
					 * *****************************************
					 */
				} else if (request.getParameter("hdBuscarTemperaturas")
						.compareToIgnoreCase("BuscarTemperaturaAmbiente") == 0) {
					try {
						jxlsBean = dToxTurnoRef.findTemperaturasAmbiente(iAnio,
								iMeses, listAreas);
						lTemperaturaAmbiente = true;
					} catch (DAOException ex) {
						ex.printStackTrace();
					}
					/*
					 * Si se oprime el link Humedad ****************************
					 */
				} else if (request.getParameter("hdBuscarTemperaturas")
						.compareToIgnoreCase("BuscarHumedad") == 0) {
					try {
						jxlsBean = dToxTurnoRef.findHumedad(iAnio, iMeses,
								listAreas);
						lHumedad = true;
					} catch (DAOException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 19/Sep/2006
	 * 
	 * Obtiene los turnos a partir del area.
	 * 
	 * @param iCveArea
	 *            int
	 * @return Vector
	 */
	public Vector getTurnos(int iCveArea) {
		TDTOXTurnoValida tdTOXTurnoValida = new TDTOXTurnoValida();
		Vector vcTurnos = null;
		String cWhere = " where TOXTurnoValida.iCveArea = " + iCveArea;

		try {
			vcTurnos = tdTOXTurnoValida.FindByAll3(cWhere, "");
		} catch (DAOException ex) {
			ex.printStackTrace();
		}

		return vcTurnos;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera
	 * 
	 * @return JXLSBean
	 */
	public JXLSBean getJXLSBean() {
		return jxlsBean;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera
	 * 
	 * @return boolean
	 */
	public boolean getLTempRefrigeradores() {
		return lTempRefrigeradores;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera
	 * 
	 * @return boolean
	 */
	public boolean getLTemperaturaAmbiente() {
		return lTemperaturaAmbiente;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera
	 * 
	 * @return boolean
	 */
	public boolean getLHumedad() {
		return lHumedad;
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}
}
