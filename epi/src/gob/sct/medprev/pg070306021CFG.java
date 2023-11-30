package gob.sct.medprev;

import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.poifs.filesystem.*;
import org.jxls.transformer.*;
import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * * Clase de configuracion para CFG Cat TOXCtrolCalibra
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070306021CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306021CFG.png'>
 */
public class pg070306021CFG extends CFGCatBase2 {

	private StringBuffer activeX = new StringBuffer("");

	public pg070306021CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo Guardar
	 */
	public void Guardar() {
		TDTOXCtrolCalibra dCtrolCalibra = new TDTOXCtrolCalibra();
		try {
			cClave = (String) dCtrolCalibra.insert(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al insertar el registro", ex);
		} finally {
			super.Guardar();
		}
	} // Metodo Guardar

	/**
	 * Metodo GuardarA
	 */
	public void GuardarA() {
		TDTOXCtrolCalibra dCtrolCalibra = new TDTOXCtrolCalibra();
		try {
			cClave = (String) dCtrolCalibra.update(null, this.getInputs());
		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al actualizar el registro", ex);
		} finally {
			super.GuardarA();
		}
	} // Metodo GuardarA

	/**
	 * Metodo Borrar
	 */
	public void Borrar() {
		TDTOXCtrolCalibra dCtrolCalibra = new TDTOXCtrolCalibra();
		try {
			cClave = (String) dCtrolCalibra.delete(null, this.getInputs());

		} catch (Exception ex) {
			vErrores.acumulaError("", 14, "");
			error("Error al eliminar/deshabilitar el registro", ex);
		} finally {
			super.Borrar();
		}
	} // Metodo Borrar

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXCtrolCalibra dCtrolCalibra = new TDTOXCtrolCalibra();

		cPaginas = "pg070306020.jsp|";
		boolean lWhere = false;
		try {

			if (cCondicion.compareTo("") != 0) {
				cCondicion = " WHERE " + cCondicion;
				lWhere = true;
			}

			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("iCveLaboratorio").toString()
							.compareTo("null") != 0) {
				if (lWhere) {
					cCondicion += " AND "
							+ " C.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio")
									.toString();
				} else {
					cCondicion = " WHERE "
							+ " C.iCveLaboratorio = "
							+ request.getParameter("iCveLaboratorio")
									.toString();
					lWhere = true;
				}
			}

			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString()
							.compareTo("null") != 0) {
				if (lWhere) {
					cCondicion += " AND " + " C.iAnio = "
							+ request.getParameter("iAnio").toString();
				} else {
					cCondicion = " WHERE " + " C.iAnio = "
							+ request.getParameter("iAnio").toString();
					lWhere = true;
				}
			}

			String cOrden = "" + request.getParameter("hdCOrdenar");

			if (cOrden.compareTo("null") != 0 && cOrden.compareTo("") != 0) {
				cOrden = request.getParameter("hdCOrdenar");
			} else {
				cOrden = " Order By C.iCveLaboratorio, C.iCveCtrolCalibra";

			}
			vDespliega = dCtrolCalibra.FindByAll(cCondicion, cOrden);

			// Agregado por Rafael Alcocer Caldera 25/Ago/2006
			// ## AQUI SE AGREGA CODIGO PARA GENERAR REPORTES ##
			if (request.getParameter("hdReporte") != null) {
				/**
				 * ANALISIS CONFIRMATORIO **********************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Analisis Confirmatorio") == 0) {
					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						// No me sirve la linea de abajo ya que los objetos no
						// vienen
						// creados y por lo tanto, envia valores por default
						// (ceros o "")
						// Tuve que poner hidden en la JSP
						// TVTOXCtrolCalibra vTOXCtrolCalibra =
						// (TVTOXCtrolCalibra)getInputs();

						TExcel Rep = new TExcel("07");

						// Primero bajo la plantilla del servidor al disco local
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("pg070306021AC",
								"pg070306021AC", false, false, false));

						int iCveLaboratorio = 0;
						String cLote = "";
						int iCveReactivo = 0;
						int iLCuantCual = 1;

						if (request.getParameter("iCveLaboratorio") != null) {
							iCveLaboratorio = Integer.parseInt(request
									.getParameter("iCveLaboratorio"));
							// System.out.println("### iCveLaboratorio: " +
							// iCveLaboratorio);
						}

						if (request.getParameter("hdCLote") != null) {
							cLote = request.getParameter("hdCLote");
							// System.out.println("### cLote: " + cLote);
						}

						if (request.getParameter("hdICveReactivo") != null) {
							iCveReactivo = Integer.parseInt(request
									.getParameter("hdICveReactivo"));
							// System.out.println("### iCveReactivo: " +
							// iCveReactivo);
						}

						// System.out.println("### SUBSTRING cLote: " +
						// cLote.substring(0, 6) + "\'");

						// lCuantCual = 1 => Traer s�lo lo relacionado a
						// An�lisis Confirmatorio
						String cWhere = " where C.iCveLaboratorio = "
								+ iCveLaboratorio + " and C.iCveReactivo = "
								+ iCveReactivo + " and C.lCuantCual = "
								+ iLCuantCual + " and substr(C.cLote,1,6) = '"
								+ cLote.substring(0, 6) + "'";

						String cOrden2 = " order by C.cLote ";

						dCtrolCalibra.FindByAll(cWhere, cOrden2);

						List jxlsBeanList = dCtrolCalibra.getJXLSBeanList();
						Map beans = new HashMap();
						beans.put("jxlsBeanList", jxlsBeanList);

						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306021AC.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306021AC-out.xls");

						POIFSFileSystem fs = new POIFSFileSystem(
								new FileInputStream(vParametros
										.getPropEspecifica("ExcelRutaDest")
										+ "pg070306021AC-out.xls"));
						HSSFWorkbook wb = new HSSFWorkbook(fs);
						HSSFSheet sheet = wb.getSheetAt(0);

						// Renglon 10
						HSSFRow row10 = sheet.getRow(9);
						HSSFCell cellE10 = row10.getCell((short) 4);
						HSSFCell cellF10 = row10.getCell((short) 5);
						// Renglon 11
						HSSFRow row11 = sheet.getRow(10);
						HSSFCell cellE11 = row11.getCell((short) 4);
						HSSFCell cellF11 = row11.getCell((short) 5);
						// Renglon 12
						HSSFRow row12 = sheet.getRow(11);
						HSSFCell cellE12 = row12.getCell((short) 4);
						HSSFCell cellF12 = row12.getCell((short) 5);
						// Renglon 13
						HSSFRow row13 = sheet.getRow(12);
						HSSFCell cellE13 = row13.getCell((short) 4);
						HSSFCell cellF13 = row13.getCell((short) 5);

						// Renglon 26
						HSSFRow row26 = sheet.getRow(25);
						HSSFCell cellD26 = row26.getCell((short) 3);
						// Renglon 27
						HSSFRow row27 = sheet.getRow(26);
						HSSFCell cellD27 = row27.getCell((short) 3);
						// Renglon 28
						HSSFRow row28 = sheet.getRow(27);
						HSSFCell cellD28 = row28.getCell((short) 3);
						// Renglon 29
						HSSFRow row29 = sheet.getRow(28);
						HSSFCell cellD29 = row29.getCell((short) 3);

						// Renglon 42
						HSSFRow row42 = sheet.getRow(41);
						HSSFCell cellD42 = row42.getCell((short) 3);
						// Renglon 43
						HSSFRow row43 = sheet.getRow(42);
						HSSFCell cellD43 = row43.getCell((short) 3);
						// Renglon 44
						HSSFRow row44 = sheet.getRow(43);
						HSSFCell cellD44 = row44.getCell((short) 3);
						// Renglon 45
						HSSFRow row45 = sheet.getRow(44);
						HSSFCell cellD45 = row45.getCell((short) 3);

						// CORTE
						// Clave del control
						cellE10.setCellValue(cellD26.getStringCellValue());
						// Volumen
						cellE11.setCellValue(cellD27.getStringCellValue());
						// Concentracion Teorica
						cellE12.setCellValue(cellD28.getStringCellValue());
						// Concentracion Experimental
						cellE13.setCellValue(cellD29.getStringCellValue());

						// POSITIVO
						// Clave del control
						cellF10.setCellValue(cellD42.getStringCellValue());
						// Volumen
						cellF11.setCellValue(cellD43.getStringCellValue());
						// Concentracion Teorica
						cellF12.setCellValue(cellD44.getStringCellValue());
						// Concentracion Experimental
						cellF13.setCellValue(cellD45.getStringCellValue());

						for (int i = 23; i < sheet.getLastRowNum(); i++) {
							sheet.removeRow(sheet.getRow(i));
						}

						// Unir celdas (merge) para las Observaciones
						sheet.addMergedRegion(new Region(14, (short) 2, 19,
								(short) 5));

						FileOutputStream fileOut = new FileOutputStream(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306021AC-out.xls");
						wb.write(fileOut);
						fileOut.close();

						this.activeX.append(Rep.creaActiveX(
								"pg070306021AC-out", false, false, true));
					}
				}

				/**
				 * ANALISIS PRESUNTIVO *******************
				 */
				if (request.getParameter("hdReporte").compareToIgnoreCase(
						"Analisis Presuntivo") == 0) {

					/**
					 * Abrir el archivo generado de Excel
					 */
					if ("Reporte".equalsIgnoreCase(this.cAccionOriginal)) {
						TExcel Rep = new TExcel("07");

						// Primero bajo la plantilla del servidor al disco local
						// Este Metodo por default ya incluye la ruta y la
						// extensi�n (.xls)
						this.activeX.append(Rep.creaActiveX("pg070306021AP",
								"pg070306021AP", false, false, false));

						TVTOXCtrolCalibra vTOXCtrolCalibra = (TVTOXCtrolCalibra) getInputs();

						// lCual = 1 => Traer s�lo lo relacionado a An�lisis
						// Confirmatorio
						String cWhere = " where C.iCveLaboratorio = "
								+ vTOXCtrolCalibra.getICveLaboratorio()
								+ " and C.iCveCtrolCalibra = "
								+ vTOXCtrolCalibra.getICveCtrolCalibra();

						String cOrden3 = "";

						dCtrolCalibra.FindByAll(cWhere, cOrden3);

						List jxlsBeanList = dCtrolCalibra.getJXLSBeanList();
						Map beans = new HashMap();
						beans.put("jxlsBeanList", jxlsBeanList);

						XLSTransformer transformer = new XLSTransformer();
						transformer.transformXLS(
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306021AP.xls", beans,
								vParametros.getPropEspecifica("ExcelRutaDest")
										+ "pg070306021AP-out.xls");
						// transformer.transformXLS("C:/sct/medprev/pg070306021AP.xls",
						// beans,"C:/sct/medprev/pg070306021AP-out.xls");

						this.activeX.append(Rep.creaActiveX(
								"pg070306021AP-out", false, false, true));
					}
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void fillPK() {

		if (request.getParameter("hdBoton").compareTo("Actual") == 0
				&& request.getParameter("hdCampoClave") != null
				&& request.getParameter("hdCampoClave3") != null
				&& request.getParameter("hdCampoClave").compareTo("") != 0
				&& request.getParameter("hdCampoClave3").compareTo("") != 0) {
			mPk.add(request.getParameter("hdCampoClave"));
			mPk.add(request.getParameter("hdCampoClave3"));
		}
		if (request.getParameter("hdBoton").compareTo("Cancelar") == 0) {
			mPk.add(request.getParameter("hdCampoClave"));
			mPk.add(request.getParameter("hdCampoClave3"));
		}
		if (request.getParameter("hdBoton").compareTo("Guardar") == 0
				|| request.getParameter("hdBoton").compareTo("GuardarA") == 0) {
			mPk.add(request.getParameter("hdCampoClave"));
			mPk.add(cActual);
		}

		// Antes
		// mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		TFechas tfCampo = new TFechas();
		TVTOXCtrolCalibra vCtrolCalibra = new TVTOXCtrolCalibra();
		try {
			cCampo = "" + request.getParameter("iCveLaboratorio"); // iCveLaboratorio
																	// , aqui
																	// tenia
																	// iCveLaboratorio
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveLaboratorio(iCampo);

			cCampo = "" + request.getParameter("hdCampoClave3"); // hdCampoClave3
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vCtrolCalibra.setICveCtrolCalibra(iCampo);

			cCampo = "" + request.getParameter("iCveReactivo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveReactivo(iCampo);

			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setIAnio(iCampo);

			cCampo = "" + request.getParameter("cLote");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCLote(cCampo);

			cCampo = "" + request.getParameter("cDscCtrolCalibra");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCDscCtrolCalibra(cCampo);

			cCampo = "" + request.getParameter("cDscBreve");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCDscBreve(cCampo);

			cCampo = "" + request.getParameter("iCveSustancia");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveSustancia(iCampo);

			cCampo = "" + request.getParameter("dVolumen");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDVolumen(fCampo);

			cCampo = "" + request.getParameter("dConcentracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDConcentracion(fCampo);

			cCampo = "" + request.getParameter("iCveEmpleoCalib");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveEmpleoCalib(iCampo);
			if (request.getParameter("iCuantCual") != null) {
				cCampo = "" + request.getParameter("iCuantCual");
			} else {
				cCampo = "null";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);

			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLCuantCual(iCampo);

			if (request.getParameter("iCual") != null) {
				cCampo = "" + request.getParameter("iCual");
			} else {
				cCampo = "null";
			}
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);

			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLCual(iCampo);

			cCampo = "" + request.getParameter("iViales");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setIViales(iCampo);

			cCampo = "" + request.getParameter("dtPreparacion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtPreparacion(dtCampo);

			cCampo = "" + request.getParameter("dtCaducidad");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtCaducidad(dtCampo);

			cCampo = "" + request.getParameter("dtAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtAutoriza(dtCampo);

			cCampo = "" + request.getParameter("iCveUsuAutoriza");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveUsuAutoriza(iCampo);

			cCampo = "" + request.getParameter("lAgotado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLAgotado(iCampo);

			cCampo = "" + request.getParameter("dtAgotado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtAgotado(dtCampo);

			cCampo = "" + request.getParameter("lBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setLBaja(iCampo);

			cCampo = "" + request.getParameter("dtBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtBaja(dtCampo);

			cCampo = "" + request.getParameter("iAnioBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setIAnioBaja(iCampo);

			cCampo = "" + request.getParameter("iCveBaja");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveBaja(iCampo);

			cCampo = "" + request.getParameter("iCveMarcaSust");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveMarcaSust(iCampo);

			cCampo = "" + request.getParameter("cObservacion");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vCtrolCalibra.setCObservacion(cCampo);

			cCampo = "" + request.getParameter("iCveUsuPrepara");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveUsuPrepara(iCampo);

			/*
			 * cCampo = "" + request.getParameter("lCual"); if
			 * (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
			 * iCampo = Integer.parseInt(cCampo, 10); } else { iCampo = 0; }
			 * vCtrolCalibra.setLCual(iCampo);
			 */

			cCampo = "" + request.getParameter("dConcentExper");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDConcentExper(fCampo);
			// System.out.println("LA CLAVE DE CONCENTRACION es "+ fCampo);

			cCampo = "" + request.getParameter("iCveEquipo");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vCtrolCalibra.setICveEquipo(iCampo);

			cCampo = "" + request.getParameter("dtValoracion");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				dtCampo = tfCampo.getDateSQL(cCampo);
			} else {
				dtCampo = null;
			}
			vCtrolCalibra.setDtBaja(dtCampo);

			cCampo = "" + request.getParameter("dVolUtilizado");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				fCampo = Float.parseFloat(cCampo);
			} else {
				fCampo = 0;
			}
			vCtrolCalibra.setDVolUtilizado(fCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vCtrolCalibra;
	}

	public String getActiveX() {
		return this.activeX.toString();
	}

	public String getAccion() {
		return this.cAccionOriginal;
	}
}
