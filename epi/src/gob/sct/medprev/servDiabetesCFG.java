package gob.sct.medprev;

import com.micper.ingsw.TParametro;
import com.micper.util.TFechas;
import gob.sct.medprev.cntmgr.CM_GetContent;
import gob.sct.medprev.dao.ConsultaGral;
import gob.sct.medprev.dao.LICDownFoto;
import gob.sct.medprev.dao.TDEXPExamCat;
import gob.sct.medprev.util.*;
import gob.sct.medprev.vo.TVEXPDictamen;
import gob.sct.medprev.vo.TVEXPExamCat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 
 * @author
 */
public class servDiabetesCFG extends HttpServlet {

	private JasperReport report;
	String ruta = "";
	String rutaLogo = "";
	java.sql.Connection conn = null;
	private TParametro VParametros2 = new TParametro("7");

	/**
	 * Metodo de init() que se encarga de cargar el Reporte HojaDeAyuda.jasper
	 */
	public void init(ServletConfig config) throws ServletException {
		String appPath = config.getServletContext().getRealPath("");

		if (appPath == null) {
			// System.out.println("El appPath es nulo");
			try {
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			ruta = config.getServletContext().getRealPath(
					"WEB-INF/reportes/ConsenInf.jasper");
			// System.out.println("entro para colocar ruta");
		}

		try {
			report = (JasperReport) JRLoader
					.loadObjectFromLocation("gob/sct/medprev/util/Diabetes.jasper");
			// System.out.println("Seteo Report");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Mando error " + e.getMessage());
		}
		super.init(config);
	}

	/**
	 * Metodo de doPost() que se encarga de recibir los Datos Capturados en el
	 * Formulario
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Metodo de doPost() que se encarga de recibir los Datos Capturados en el
	 * Formulario
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ctaErrores = "";
		ctaErrores = "haslo";
		if (!ctaErrores.equals("")) {
			try {
				GeneraPDF(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<br /><br /><br /><br /><center>");
			out.println("Hubo un Problema al Guardar la Informaciï¿½n que se Capturo, ver log.");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

	private void GeneraPDF(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * System.out.print("Hoja de ayuda referencia alfanumerica " +
		 * request.getParameter("sRefAlfanumerica"));
		 * System.out.print("Revisando que contiene: " +
		 * request.getParameterMap().toString());
		 */
		HashMap hm = new HashMap();

		// System.out.println("Imprimir Reporte");

		int iCvePersonal = 0;
		int iNumExamen = 0;
		Vector VResultado = new Vector();
		com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
				"07");
		String dataSourceName = VParametros
				.getPropEspecifica("ConDBModulo");

		com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
				dataSourceName);
		java.sql.Connection conn = dbConn.getConnection();


		try {
			if (request.getParameter("hdNoExpedienteRep").compareTo("null") != 0
					&& request.getParameter("hdNoExpedienteRep").compareTo("") != 0) {
				iCvePersonal = Integer.parseInt(
						request.getParameter("hdNoExpedienteRep"), 10);
			} else {
				iCvePersonal = 0;
			}
			if (request.getParameter("hdiNumExamenRep").compareTo("null") != 0
					&& request.getParameter("hdiNumExamenRep").compareTo("") != 0) {
				iNumExamen = Integer.parseInt(
						request.getParameter("hdiNumExamenRep"), 10);
			} else {
				iNumExamen = 0;

			}
			
			

			//java.sql.PreparedStatement pstmt = conn.prepareStatement(cSQL.toString());
			//java.sql.ResultSet rset = pstmt.executeQuery();

			// Recolectando Parametros
			// Numero personal
			String ClaveExpediente = "";
			ClaveExpediente = "" + iCvePersonal;

			// Numero de Examen
			String NumeroExamen = "";
			NumeroExamen = "" + iNumExamen;

			
			//Parametros
			float P1 = (float) 0.0;
			float P2 = (float) 0.0;
			float P3 = (float) 0.0;
			float P4 = (float) 0.0;
			float P5 = (float) 0.0;
			float P6 = (float) 0.0;
			float P7 = (float) 0.0;
			float P8 = (float) 0.0;
			String P9 = "";
			float P10 = (float) 0.0;
			float P11 = (float) 0.0;
			float P12 = (float) 0.0;
			float P13 = (float) 0.0;
			String P14 = "NO";
			String P15 = "NO";
			String P16 = "NO";
			String P17 = "NO";
			String Diabetes = "";
			int Sistolica = 0;
			int Distolica = 0;
			String TpoDiabetes = "";
			String Retionopatia = "";
			String UtilizaInsulina = "";
			String UtilizaHipoglucemiantes = "";
			
			
			////Obtener Datos P1
			String cSQLP1 = "";
			cSQLP1 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 4";
			PreparedStatement pstmtP1 = conn.prepareStatement(cSQLP1);
			java.sql.ResultSet rsetP1 = pstmtP1.executeQuery();
			rsetP1 = pstmtP1.executeQuery();
			while (rsetP1.next()) {
				P1 = rsetP1.getFloat(1);
			}
			// Cerrando conexion
			rsetP1.close();
			pstmtP1.close();
			
			////Obtener Datos P2
			String cSQLP2 = "";
			cSQLP2 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 5";
			PreparedStatement pstmtP2 = conn.prepareStatement(cSQLP2);
			java.sql.ResultSet rsetP2 = pstmtP2.executeQuery();
			rsetP2 = pstmtP2.executeQuery();
			while (rsetP2.next()) {
				P2 = rsetP2.getFloat(1);
			}
			// Cerrando conexion
			rsetP2.close();
			pstmtP2.close();
			

			////Obtener Datos P3
			String cSQLP3 = "";
			cSQLP3 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 7";
			PreparedStatement pstmtP3 = conn.prepareStatement(cSQLP3);
			java.sql.ResultSet rsetP3 = pstmtP3.executeQuery();
			rsetP3 = pstmtP3.executeQuery();
			while (rsetP3.next()) {
				P3 = rsetP3.getFloat(1);
			}
			// Cerrando conexion
			rsetP3.close();
			pstmtP3.close();

			////Obtener Datos P4
			String cSQLP4 = "";
			cSQLP4 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 9";
			PreparedStatement pstmtP4 = conn.prepareStatement(cSQLP4);
			java.sql.ResultSet rsetP4 = pstmtP4.executeQuery();
			rsetP4 = pstmtP4.executeQuery();
			while (rsetP4.next()) {
				P4 = rsetP4.getFloat(1);
			}
			// Cerrando conexion
			rsetP4.close();
			pstmtP4.close();

			////Obtener Datos P5
			String cSQLP5 = "";
			cSQLP5 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 8";
			PreparedStatement pstmtP5 = conn.prepareStatement(cSQLP5);
			java.sql.ResultSet rsetP5 = pstmtP5.executeQuery();
			rsetP5 = pstmtP5.executeQuery();
			while (rsetP5.next()) {
				P5 = rsetP5.getFloat(1);
			}
			P6 = P5;
			// Cerrando conexion
			rsetP5.close();
			pstmtP5.close();
			
			////Obtener Datos P7
			String cSQLP7 = "";
			cSQLP7 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 6";
			PreparedStatement pstmtP7 = conn.prepareStatement(cSQLP7);
			java.sql.ResultSet rsetP7 = pstmtP7.executeQuery();
			rsetP7 = pstmtP7.executeQuery();
			while (rsetP7.next()) {
				P7 = rsetP7.getFloat(1);
			}
			// Cerrando conexion
			rsetP7.close();
			pstmtP7.close();
			
			////Obtener Datos P8
			String cSQLP8 = "";
			cSQLP8 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 10";
			PreparedStatement pstmtP8 = conn.prepareStatement(cSQLP8);
			java.sql.ResultSet rsetP8 = pstmtP8.executeQuery();
			rsetP8 = pstmtP8.executeQuery();
			while (rsetP8.next()) {
				P8 = rsetP8.getFloat(1);
			}
			// Cerrando conexion
			rsetP8.close();
			pstmtP8.close();			
			
			////Obtener Datos P9
			String cSQLP9 = "";
			cSQLP9 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 12";
			PreparedStatement pstmtP9 = conn.prepareStatement(cSQLP9);
			java.sql.ResultSet rsetP9 = pstmtP9.executeQuery();
			rsetP9 = pstmtP9.executeQuery();
			while (rsetP9.next()) {
				//P9 = rsetP9.getString(1);
				Sistolica = rsetP9.getInt(1);
			}
			cSQLP9 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 13";
			pstmtP9 = conn.prepareStatement(cSQLP9);
			rsetP9 = pstmtP9.executeQuery();
			rsetP9 = pstmtP9.executeQuery();
			while (rsetP9.next()) {
				//P9 = P9 +"/"+rsetP9.getString(1);
				Distolica = rsetP9.getInt(1);
			}
			P9 = Sistolica +"/"+Distolica;
			// Cerrando conexion
			rsetP9.close();
			pstmtP9.close();

			////Obtener Datos P10
			String cSQLP10 = "";
			cSQLP10 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 19";
			PreparedStatement pstmtP10 = conn.prepareStatement(cSQLP10);
			java.sql.ResultSet rsetP10 = pstmtP10.executeQuery();
			rsetP10 = pstmtP10.executeQuery();
			while (rsetP10.next()) {
				P10 = rsetP10.getFloat(1);
			}
			// Cerrando conexion
			rsetP10.close();
			pstmtP10.close();			

			////Obtener Datos P11
			String cSQLP11 = "";
			cSQLP11 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 17";
			PreparedStatement pstmtP11 = conn.prepareStatement(cSQLP11);
			java.sql.ResultSet rsetP11 = pstmtP11.executeQuery();
			rsetP11 = pstmtP11.executeQuery();
			while (rsetP11.next()) {
				P11 = rsetP11.getFloat(1);
			}
			// Cerrando conexion
			rsetP11.close();
			pstmtP11.close();
			
			////Obtener Datos P12
			String cSQLP12 = "";
			cSQLP12 = "select DvalorIni from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 20";
			PreparedStatement pstmtP12 = conn.prepareStatement(cSQLP12);
			java.sql.ResultSet rsetP12 = pstmtP12.executeQuery();
			rsetP12 = pstmtP12.executeQuery();
			while (rsetP12.next()) {
				P12 = rsetP12.getFloat(1);
			}
			P13 = P12;
			// Cerrando conexion
			rsetP12.close();
			pstmtP12.close();
			
			////Obtener Datos P14
			String cSQLP14 = "";
			cSQLP14 = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 85";
			PreparedStatement pstmtP14 = conn.prepareStatement(cSQLP14);
			java.sql.ResultSet rsetP14 = pstmtP14.executeQuery();
			rsetP14 = pstmtP14.executeQuery();
			while (rsetP14.next()) {
				P14 = rsetP14.getString(1);
				if(rsetP14.getString(1).equals("1")){
					P14 = "FUMA";
				}
			}
			// Cerrando conexion
			rsetP14.close();
			pstmtP14.close();

			////Obtener Datos P15
			String cSQLP15 = "";
			cSQLP15 = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 44";
			PreparedStatement pstmtP15 = conn.prepareStatement(cSQLP15);
			java.sql.ResultSet rsetP15 = pstmtP15.executeQuery();
			rsetP15 = pstmtP15.executeQuery();
			while (rsetP15.next()) {
				if(rsetP15.getString(1).equals("3") || rsetP15.getString(1).equals("4")){
					P15 = "SI";
				}
				Retionopatia = rsetP15.getString(1);
			}
			// Cerrando conexion
			rsetP15.close();
			pstmtP15.close();

			////Obtener Datos P16
			String cSQLP16 = "";
			cSQLP16 = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 63";
			PreparedStatement pstmtP16 = conn.prepareStatement(cSQLP16);
			java.sql.ResultSet rsetP16 = pstmtP16.executeQuery();
			rsetP16 = pstmtP16.executeQuery();
			while (rsetP16.next()) {
				if(rsetP16.getString(1).equals("1")){
					P16 = "D SANO";
				}
				if(rsetP16.getString(1).equals("2")){
					P16 = "D NO INCAPACITANTE";
				}
				if(rsetP16.getString(1).equals("3")){
					P16 = "D INCAPACITANTE";
				}
			}
			cSQLP16 = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 64";
			pstmtP16 = conn.prepareStatement(cSQLP16);
			rsetP16 = pstmtP16.executeQuery();
			rsetP16 = pstmtP16.executeQuery();
			while (rsetP16.next()) {
				if(rsetP16.getString(1).equals("1")){
					P17 = "I SANO";
				}
				if(rsetP16.getString(1).equals("2")){
					P17 = "I NO INCAPACITANTE";
				}
				if(rsetP16.getString(1).equals("3")){
					P17 = "I INCAPACITANTE";
				}
			}
			// Cerrando conexion
			rsetP16.close();
			pstmtP16.close();
			

			////Obtener Datos TpoDiab
			String cSQLTpoDiab = "";
			cSQLTpoDiab = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 2";
			PreparedStatement pstmtTpoDiab = conn.prepareStatement(cSQLTpoDiab);
			java.sql.ResultSet rsetTpoDiab = pstmtTpoDiab.executeQuery();
			rsetTpoDiab = pstmtTpoDiab.executeQuery();
			while (rsetTpoDiab.next()) {
					TpoDiabetes = rsetTpoDiab.getString(1);
			}
			// Cerrando conexion
			rsetTpoDiab.close();
			pstmtTpoDiab.close();


			////Obtener Datos UtilizaInsulina
			String cSQLUtilizaInsulina = "";
			cSQLUtilizaInsulina = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 2";
			PreparedStatement pstmtUtilizaInsulina = conn.prepareStatement(cSQLUtilizaInsulina);
			java.sql.ResultSet rsetUtilizaInsulina = pstmtUtilizaInsulina.executeQuery();
			rsetUtilizaInsulina = pstmtUtilizaInsulina.executeQuery();
			while (rsetUtilizaInsulina.next()) {
					UtilizaInsulina = rsetUtilizaInsulina.getString(1);
			}
			// Cerrando conexion
			rsetUtilizaInsulina.close();
			pstmtUtilizaInsulina.close();


			////Obtener Datos UtilizaHipoglucemiantes
			String cSQLUtilizaHipoglucemiantes = "";
			cSQLUtilizaHipoglucemiantes = "select cCaracter from expresultado where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ " and icveservicio = 75 and icverama = 1 and icvesintoma = 2";
			PreparedStatement pstmtUtilizaHipoglucemiantes = conn.prepareStatement(cSQLUtilizaHipoglucemiantes);
			java.sql.ResultSet rsetUtilizaHipoglucemiantes = pstmtUtilizaHipoglucemiantes.executeQuery();
			rsetUtilizaHipoglucemiantes = pstmtUtilizaHipoglucemiantes.executeQuery();
			while (rsetUtilizaHipoglucemiantes.next()) {
					UtilizaHipoglucemiantes = rsetUtilizaHipoglucemiantes.getString(1);
			}
			// Cerrando conexion
			rsetUtilizaHipoglucemiantes.close();
			pstmtUtilizaHipoglucemiantes.close();
			
			
			
			////Calculos Diabetes
			if(P1 < 170){
				if(P2 < 9){
					if(Sistolica < 130 && Distolica < 80){
						if( P15.equals("NO")){
							Diabetes = "Diabetes No Insulinodependiente Controlada";
						}
					}
				}
			}
			
			if(TpoDiabetes.equals("2")){
				//Opcion A
				if(P1 >= 200){
					Diabetes = "Diabetes No Insulinodependiente Descontrolada";
				}
				//Opcion B
				if(P1 >= 170){
					if(P2 >= 9){
						if(Sistolica > 140 && Distolica > 90){
							if(P10 > 35){
								if(P11 > 35){
									if(Retionopatia.equals("3")){
										Diabetes = "Diabetes No Insulinodependiente Descontrolada";					
									}
								}
							}
							
						}
					}
				}
				//Opcion C
				if(P1 >= 130 || P1 < 170){
					if(P2 >= 9){
						if(Sistolica > 140 && Distolica > 90){
							if(P10 > 35){
								if(P11 > 35){
									if(Retionopatia.equals("3")){
										Diabetes = "Diabetes No Insulinodependiente Descontrolada";					
									}
								}
							}
						}
					}
				}
			}
			
			if(TpoDiabetes.equals("1")){
				if(UtilizaInsulina.equals("1") || UtilizaHipoglucemiantes.equals("1")){
					Diabetes = "Diabetes Insulinodependiente";
				}
			}
			
			if(TpoDiabetes.equals("2")){
				if(P1 == 0 ||
						P2 == 0 ||
						Sistolica == 0 ||
						Distolica == 90 ||
						Retionopatia.equals("4") ){
					    	Diabetes = "Falta de información ";
				}
			}
			
			
			///Cargando Parametros
			hm.put("P1", ""+P1);
			hm.put("P2", ""+P2);
			hm.put("P3", ""+P3);
			hm.put("P4", ""+P4);
			hm.put("P5", ""+P5);
			hm.put("P6", ""+P6);
			hm.put("P7", ""+P7);
			hm.put("P8", ""+P8);
			hm.put("P9", ""+P9);
			hm.put("P10", ""+P10);
			hm.put("P11", ""+P11);
			hm.put("P12", ""+P12);
			hm.put("P13", ""+P13);
			hm.put("P14", ""+P14);
			hm.put("P15", ""+P15);
			hm.put("P16", ""+P16);
			hm.put("P17", ""+P17);
			hm.put("diabetes", ""+Diabetes);
			
	
		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
		}
		// hm.put("rLogo" ,
		// "http://aplicaciones.sct.gob.mx/imagenes/img/ing/Logo_e5Cinco_SCT.JPG");
		// NO SE USA Req5
		// hm.put("rLogo" , "Imagenes/Logo_e5Cinco_SCT.JPG"); NO SE USA Req5
		ServletOutputStream ouputStream = null;
		try {
			List jasperPrinterList = new ArrayList();
			try {
				ArrayList reportList = new ArrayList();
				Map reportMap = new HashMap();
				reportMap.put("temp", "");
				reportList.add(reportMap);
				JRBeanArrayDataSource dataSourceReport = new JRBeanArrayDataSource(
						reportList.toArray());
				/*JRBeanArrayDataSource dataSourceReport2 = new JRBeanArrayDataSource(
						reportList.toArray());*/
				jasperPrinterList.add(JasperFillManager.fillReport(report, hm,
						dataSourceReport));
				/*jasperPrinterList.add(JasperFillManager.fillReport(report2, hm,
						dataSourceReport2));*/
			} catch (JRException e) {
				e.printStackTrace();
				throw new Exception("Error al generar el archivo", e);
			}
			// System.out.println("Que viene report=" + report + " hm=" +
			// hm.values());
			/*
			 * byte[] bytes = JasperRunManager.runReportToPdf(report, hm);
			 * response.setContentType("application/pdf;charset=UTF-8");
			 * response.setContentLength(bytes.length); ouputStream =
			 * response.getOutputStream(); ouputStream.write(bytes, 0,
			 * bytes.length);
			 */
			/*
			 * byte[] bytes2 = JasperRunManager.runReportToPdf(report2, hm);
			 * response.setContentType("application/pdf;charset=UTF-8");
			 * response.setContentLength(bytes2.length); ouputStream =
			 * response.getOutputStream(); ouputStream.write(bytes2, 0,
			 * bytes2.length);
			 */
			JRPdfExporter exporter = new JRPdfExporter();

			ByteArrayOutputStream output = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrinterList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			exporter.setParameter(
					JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS,
					Boolean.TRUE);
			exporter.exportReport();
			byte[] byteArray = output.toByteArray();
			output.close();
			response.setContentType("application/pdf;charset=UTF-8");
			response.setContentLength(byteArray.length);
			ouputStream = response.getOutputStream();
			ouputStream.write(byteArray, 0, byteArray.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ouputStream.flush();
			ouputStream.close();
		}
	}// metodo
} // class

