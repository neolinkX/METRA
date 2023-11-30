package gob.sct.medprev;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.poi.hssf.usermodel.*;
import org.jxls.transformer.*;
import com.micper.ingsw.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.msc.*;

/**
 * 
 * <p>
 * Title: Lee Plantillas JXLS de EXCEL
 * </p>
 * <p>
 * Description: Lee la plantilla la procesa y envia el resultado
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 */
public class servXLSpg0703060100 extends HttpServlet {

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
		TParametro vParametros = new TParametro("07");
		TFechas tFechas = new TFechas();

		// System.out.println("LLEGO AL servlet");
		// #####################################
		// ## AQUI METER EL CODIGO PARA EXCEL ##
		// #####################################
		try {
			if (request.getParameter("iCveLaboratorio") != null
					&& request.getParameter("SLSSustancia") != null
					&& request.getParameter("iCveEquipo") != null
					&& request.getParameter("dtFechaI") != null
					&& request.getParameter("dtFechaF") != null) {

				TDTOXLoteCuantita dTOXLoteCuantita = new TDTOXLoteCuantita();
				// System.out.println("Esta atendiendo el request");
				String cFiltro = " where LC.iCveLaboratorio = "
						+ request.getParameter("iCveLaboratorio")
						+ " and LC.iCveSustancia = "
						+ request.getParameter("SLSSustancia")
						+ " and LC.iCveEquipo     = "
						+ request.getParameter("iCveEquipo")
						+ " and LC.dtAnalisis between '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaI")
								.toString())
						+ "'"
						+ "                   and '"
						+ tFechas.getDateSQL(request.getParameter("dtFechaF")
								.toString())
						+ "'"
						+ " order by CLOTE, LC.iAnio, LC.iCveLaboratorio, LC.iCveSustancia, LC.dtCalibracion, cLote";

				JXLSBean jxlsbean = dTOXLoteCuantita.generaReporteXLS(cFiltro);

				Map beans = new HashMap();
				beans.put("jxlsbean", jxlsbean);
				XLSTransformer transformer = new XLSTransformer();
				transformer.groupCollection("calibrador.list");
				transformer.markAsFixedSizeCollection("calibrador.list");
				// transformer.setJexlInnerCollectionsAccess( true );

				HSSFWorkbook workbook1 = null;
				String excelFile = "pg0703060100";

				// // System.out.println("ANTES DE TRANSFllenadaOEMER");
				/********* FIN EJEMPLO */
				List sheetNames = new ArrayList();
				List maps = new ArrayList();

				int cont = 0;
				int cont2 = 1;
				int iniciogrupo = 0;
				int fingrupo = 0;
				int registros = 0;

				int urluti = 0;// nuevo

				for (Iterator it2 = beans.keySet().iterator(); it2.hasNext();) {
					String s = (String) it2.next();
					JXLSBean s1 = (JXLSBean) beans.get(s);
					List info = new ArrayList();

					for (Iterator it3 = s1.getList().iterator(); it3.hasNext();) {
						pg0703060100XLSBean valor = (pg0703060100XLSBean) (it3
								.next());
						info.add(valor);
					}
					// // System.out.println("ANTES DEllenado");

					String loteAnterior = "vacio";
					List temp = new ArrayList();
					for (Iterator it3 = info.iterator(); it3.hasNext();) {

						pg0703060100XLSBean valor = (pg0703060100XLSBean) (it3
								.next());
						if (cont2 == 1) {
							loteAnterior = valor.getCcodificacion().toString();
						}

						// // System.out.println("el lote anterior es " +
						// loteAnterior);
						// // System.out.println("El lote actual es es " +
						// valor.getCcodificacion().toString());
						// // System.out.println("la bean es " + cont2);

						if (!valor.getCcodificacion().toString()
								.equals(loteAnterior)
								|| cont2 == info.size()) {
							fingrupo = cont2;
							registros = fingrupo - iniciogrupo;

							if (cont2 == info.size()) {
								temp.add(valor);
							}
							int name = cont + 1;
							Map map = new HashMap();
							JXLSBean calibrador = new JXLSBean("CC " + name);
							List finalXhoja = new ArrayList();
							List limCorte = new ArrayList();
							List corte = new ArrayList();
							List positivo = new ArrayList();

							for (int f = 0; f < fingrupo - 1; f++) {
								if (f >= iniciogrupo) {
									finalXhoja.add(temp.get(f));
									pg0703060100XLSBean v = (pg0703060100XLSBean) (temp
											.get(f));
									limCorte.add(v.getDcorteneg());
									corte.add(v.getDcorte());
									positivo.add(v.getDcortepost());
								}
							}
							Resultados res = new Resultados();

							Double me = promedio(limCorte);
							Double DE = desestandar(limCorte);
							double media = me.doubleValue();
							// System.out.println("medilim "+media);
							double desEs = DE.doubleValue();
							// System.out.println("desilim "+desEs);
							Double CV = cv(desEs, media);
							// System.out.println("CV ilim "+CV);

							res.setMEDIAlimcorte(me);
							res.setDElimcorte(DE);
							res.setCVlimcorte(CV);

							Double me2 = promedio(corte);
							Double DE2 = desestandar(corte);
							double media2 = me2.doubleValue();
							double desEs2 = DE2.doubleValue();
							Double CV2 = cv(desEs2, media2);

							// System.out.println("medilim "+media2);
							// System.out.println("desilim "+desEs2);
							// System.out.println("CV ilim "+CV2);

							res.setMEDIAcorte(me2);
							res.setDEcorte(DE2);
							res.setCVcorte(CV2);

							Double me3 = promedio(positivo);
							Double DE3 = desestandar(positivo);
							double media3 = me3.doubleValue();
							double desEs3 = DE3.doubleValue();
							Double CV3 = cv(desEs3, media3);

							// System.out.println("medilim "+media3);
							// System.out.println("desilim "+desEs3);
							// System.out.println("CV ilim "+CV3);

							res.setMEDIApositivo(me3);
							res.setDEpositivo(DE3);
							res.setCVpositivo(CV3);

							calibrador.setList(finalXhoja);
							calibrador.setBean(res);

							map.put("calibrador", calibrador);
							sheetNames.add("CC " + name);
							maps.add(map);
							cont++;

							iniciogrupo = fingrupo;

						}
						temp.add(valor);
						loteAnterior = valor.getCcodificacion().toString();
						cont2++;
						// System.out.println("hasta ahora se han creado " +
						// cont + " numero de hash maps");
						// System.out.println("   ");

					}

				}

				response.setContentType("application/vnd.ms-excel");
				// Lo sig. hace que el archivo se abra fuera de la JSP
				// response.setHeader("Content-Disposition",
				// "attachment;filename=workbook.xls");
				// Lo sig. hace que el archivo se abra dentro de la JSP
				response.setHeader("Content-Disposition", "inline;filename="
						+ excelFile + "-out.xls");
				// response.setHeader("Content-Disposition",
				// "attachment;filename=" + excelFile + "-out.xls");

				// Verifico cual plantilla utilizar dependiendo de la Sustancia
				// ANFETAMINAS O METANFETAMINAS (GRAFICA 0-1200)
				// *********************************************
				if (new Integer(request.getParameter("SLSSustancia"))
						.intValue() == 1
						|| new Integer(request.getParameter("SLSSustancia"))
								.intValue() == 6) {

					// LEER LA PLANTILLA DE EXCEL
					// ##########################

					// URL url = new URL("http://localhost:8080/FileUploadJSP/"
					// + excelFile + ".xls");
					// System.out.println("URL = " +
					// vParametros.getPropEspecifica("ExcelRutaOrig") +
					// excelFile + ".xls");
					// URL urlAnfe = new
					// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
					// "pg0703060100Individual-Anfe&Meta.xls");
					// ///////URL urlAnfe = new
					// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
					// "pg0703060100-070123.xls");
					// ///////URLConnection connAnfe = urlAnfe.openConnection();
					// ///////InputStream isAnfe = connAnfe.getInputStream();

					// URL url = new
					// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
					// "pg0703060100Individual-Anfe&Meta.xls");
					URL url = new URL(
							"file:///C:/sct/MedprevInt/archivos/plantillas/pg0703060100Individual-Anfe&Meta.xls");

					URLConnection conn = url.openConnection();
					InputStream isPlantillaExcel = conn.getInputStream();
					// toma la platilla local
					// InputStream isPlantillaExcel = new
					// BufferedInputStream(new
					// FileInputStream("C:/sct/MedprevInt/archivos/plantillas/pg0703060100Individual-Anfe&Meta.xls"));

					/*
					 * ServletContext ctx = getServletContext(); URL url =
					 * ctx.getResource("/WEB-INF/plantillas/" + excelFile +
					 * ".xls"); URLConnection conn = url.openConnection();
					 * InputStream isPlantillaExcel = conn.getInputStream();
					 */

					/*
					 * InputStream isPlantillaExcel =
					 * ctx.getResourceAsStream("/" + excelFile + ".xls");
					 */
					// #####################################

					// ///////workbook1 = transformer.transformXLS(isAnfe,
					// beans);

					/** GENERAR VARIAS SHEETS EN EL EXCEL */
					// System.out.println("ANTES DE TRANSFOEMER");
					workbook1 = transformer.transformMultipleSheetsList(
							isPlantillaExcel, maps, sheetNames, "map",
							new HashMap(), 0);
					// workbook1.//
					// workbook1 =
					// transformer.transformMultipleSheetsList(isPlantillaExcel,
					// BeanLotes, HojasNombre, "map", new HashMap(), 0);
					// System.out.println("ANTES DE TRANSFOEMER23423");
					/** ANTERIOR PARA UNA SOLA HOJA */
					// workbook1 = transformer.transformXLS(isPlantillaExcel,
					// beans);
					// System.out.println("Ya leyo la plantilla");
					// System.out.println(vParametros.getPropEspecifica("ExcelRutaOrig")
					// + "pg0703060100Individual-Anfe&Meta.xls");
					// System.out.println(url.toString());
					// CANNABINOIDES O FENCICLIDINA (GRAFICA 0-100)
					// ********************************************
					isPlantillaExcel.close();
				} else if (new Integer(request.getParameter("SLSSustancia"))
						.intValue() == 2
						|| new Integer(request.getParameter("SLSSustancia"))
								.intValue() == 5) {

					// LEER LA PLANTILLA DE EXCEL
					// ##########################

					// URL url = new URL("http://localhost:8080/FileUploadJSP/"
					// + excelFile + ".xls");
					// System.out.println("URL = " +
					// vParametros.getPropEspecifica("ExcelRutaOrig") +
					// excelFile + ".xls");
					// ///////URL urlCann = new
					// URL(vParametros.getPropEspecifica("ExcelRutaOrig") +
					// "pg0703060100Individual-Cann&Fenc.xls");
					// ///////URLConnection connCann = urlCann.openConnection();
					// ///////InputStream isCann = connCann.getInputStream();

					URL url = new URL(
							vParametros.getPropEspecifica("ExcelRutaOrig")
									+ "pg0703060100Individual-Cann&Fenc.xls");
					// URL url = new
					// URL("file:///C:/sct/MedprevInt/archivos/plantillas/pg0703060100Individual-Cann&Fenc.xls");

					URLConnection conn = url.openConnection();
					InputStream isPlantillaExcel = conn.getInputStream();

					/*
					 * ServletContext ctx = getServletContext(); URL url =
					 * ctx.getResource("/WEB-INF/plantillas/" + excelFile +
					 * ".xls"); URLConnection conn = url.openConnection();
					 * InputStream isPlantillaExcel = conn.getInputStream();
					 */

					/*
					 * InputStream isPlantillaExcel =
					 * ctx.getResourceAsStream("/" + excelFile + ".xls");
					 */
					// #####################################

					// ///////workbook1 = transformer.transformXLS(isCann,
					// beans);
					// workbook1 = transformer.transformXLS(isPlantillaExcel,
					// beans);
					workbook1 = transformer.transformMultipleSheetsList(
							isPlantillaExcel, maps, sheetNames, "map",
							new HashMap(), 0);

					// System.out.println("Ya leyo la plantilla");
					// System.out.println(vParametros.getPropEspecifica("ExcelRutaOrig")
					// + "pg0703060100Individual-Cann&Fenc.xls");

					// CUALQUIER OTRO INCLUYENDO COCAINA, OPIACEOS, ETC (GRAFICA
					// 0-400)
					// ****************************************************************
				} else {

					// LEER LA PLANTILLA DE EXCEL
					// ##########################

					// URL url = new URL("http://localhost:8080/FileUploadJSP/"
					// + excelFile + ".xls");
					// System.out.println("URL = " +
					// vParametros.getPropEspecifica("ExcelRutaOrig") +
					// excelFile + ".xls");
					URL url = new URL(
							vParametros.getPropEspecifica("ExcelRutaOrig")
									+ "pg0703060100.xls");
					URLConnection conn = url.openConnection();
					InputStream isPlantillaExcel = conn.getInputStream();

					/*
					 * ServletContext ctx = getServletContext(); URL url =
					 * ctx.getResource("/WEB-INF/plantillas/" + excelFile +
					 * ".xls"); URLConnection conn = url.openConnection();
					 * InputStream isPlantillaExcel = conn.getInputStream();
					 */

					/*
					 * InputStream isPlantillaExcel =
					 * ctx.getResourceAsStream("/" + excelFile + ".xls");
					 */
					// #####################################

					workbook1 = transformer.transformXLS(isPlantillaExcel,
							beans);
				}
				int renglonestotales = cont2;
				int hojasTotales = cont;
				String fechai = request.getParameter("dtFechaI");
				String fechaf = request.getParameter("dtFechaF");
				String concat = "";// nuevo

				for (int i = 0; i < hojasTotales; i++) {
					HSSFSheet sheet0 = workbook1.getSheetAt(i);

					// Integer a[] = ((Integer[])(grupos.get(i)));
					//
					// for(int y=0; y<renglonestotales ; y++){
					// int inicio = a[0].intValue();
					// int fin = a[1].intValue();
					// if(y<inicio||y>fin){
					// HSSFRow row = sheet0.getRow(y);
					// sheet0.removeRow(row);
					// }
					// }

					HSSFRow row8 = sheet0.getRow(7);
					HSSFRow row9 = sheet0.getRow(8);
					HSSFRow row10 = sheet0.getRow(9);
					HSSFRow row27 = sheet0.getRow(26);

					HSSFCell cellFechaInicial = row8.getCell((short) 2);
					HSSFCell cellEquipo = row8.getCell((short) 5);
					HSSFCell cellFechaFinal = row9.getCell((short) 2);
					HSSFCell cellModelo = row9.getCell((short) 5);
					HSSFCell cellDroga = row10.getCell((short) 3);
					HSSFCell cellSerie = row10.getCell((short) 5);

					HSSFCell Formulas = row27.getCell((short) 3);

					if (cellFechaInicial == null) {
						cellFechaInicial = row8.createCell((short) 2);
					}

					if (cellEquipo == null) {
						cellEquipo = row8.createCell((short) 5);
					}

					if (cellFechaFinal == null) {
						cellFechaFinal = row9.createCell((short) 2);
					}

					if (cellModelo == null) {
						cellModelo = row9.createCell((short) 5);
					}

					if (cellDroga == null) {
						cellDroga = row10.createCell((short) 3);
					}

					if (cellSerie == null) {
						cellSerie = row10.createCell((short) 5);
					}

					if (Formulas == null) {
						Formulas = row10.createCell((short) 3);
					}

					cellFechaInicial.setCellValue(fechai);
					cellEquipo.setCellValue(dTOXLoteCuantita.getBean2()
							.getCcveequipo());
					cellFechaFinal.setCellValue(fechaf);
					cellModelo.setCellValue(dTOXLoteCuantita.getBean2()
							.getCmodelo());
					cellDroga.setCellValue(dTOXLoteCuantita.getBean2()
							.getCdscsustancia());
					cellSerie.setCellValue(dTOXLoteCuantita.getBean2()
							.getCnumserie());
					// Formulas.setCellFormula("DESVESTPA(D33:D34)");
					// nuevo

				}
				response.setHeader("Content-Disposition", "inline;filename="
						+ excelFile + "-out.xls");

				int read = 0;
				byte[] bytes = new byte[2048];

				OutputStream os = response.getOutputStream();

				InputStream is = new ByteArrayInputStream(workbook1.getBytes());

				while ((read = is.read(bytes)) != -1) {
					workbook1.write(os);
					// System.out.println("OS "+ os.toString());
					// System.out.println("CREO ESL WOOKR BOOKK1");
				}
				os.flush();
				os.close();

			}
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

	public Double desestandar(List NumericArray) {

		Double dblMea = promedio(NumericArray);
		double dblMean = dblMea.doubleValue();
		double sampleSize = NumericArray.size();
		// 'get sum of squared deviations
		double sumCuadrados = 0;
		for (int x = 0; x < NumericArray.size(); x++) {
			Double t = (Double) NumericArray.get(x);
			double t2 = t.doubleValue();
			sumCuadrados += Math.pow(t2 - dblMean, 2);
		}
		double dblAnswer = Math.sqrt(sumCuadrados / sampleSize - 1);

		return new Double(dblAnswer);
	}

	public Double promedio(List NumericArray) {

		double sumatoria = 0;
		double sampleSize = 0;

		// 'get sum and sample size
		for (int x = 0; x < NumericArray.size(); x++) {
			sampleSize++;
			Double res = (Double) NumericArray.get(x);
			double res2 = res.doubleValue();
			sumatoria += res2;
		}
		// 'get mean
		double dblMean = 0;
		if (sampleSize > 1) {
			dblMean = sumatoria / sampleSize;
		}
		return new Double(dblMean);
	}

	public Double cv(double desEst, double promedio) {
		double cv = (desEst / promedio) * 100;

		return new Double(cv);
	}
}
