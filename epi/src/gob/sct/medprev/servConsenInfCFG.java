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
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 
 * @author
 */
public class servConsenInfCFG extends HttpServlet {

	private JasperReport report;
	private JasperReport report2;
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
					.loadObjectFromLocation("gob/sct/medprev/util/ConsenInf.jasper");
			//report2 = (JasperReport) JRLoader
				//	.loadObjectFromLocation("gob/sct/medprev/util/DeclaraSalud.jasper");
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
			GeneraPDF(request, response);
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<br /><br /><br /><br /><center>");
			out.println("Hubo un Problema al Guardar la Información que se Capturo, ver log.");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}
	}

	private void GeneraPDF(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * System.out.print("Hoja de ayuda referencia alfanumerica " +
		 * request.getParameter("sRefAlfanumerica"));
		 * System.out.print("Revisando que contiene: " +
		 * request.getParameterMap().toString());
		 */
		HashMap hm = new HashMap();

		// System.out.println("Imprimir Reporte");

		byte[] data = null;

		TFechas pFecha = new TFechas();
		int iCvePersonal = 0;
		int iNumExamen = 0;
		StringBuffer cFiltro = new StringBuffer();
		Vector VResultado = new Vector();
		TVEXPDictamen VDictamen = new TVEXPDictamen();

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
			cFiltro.append("  where EC.iCveExpediente  = ")
					.append(iCvePersonal).append(" and EA.iNumExamen = ")
					.append(iNumExamen);
			VResultado = (new TDEXPExamCat())
					.InfoConstancia(cFiltro.toString());
			VDictamen = (TVEXPDictamen) VResultado.get(0);

			// recuperamos los inodocto para foto, firma y huella
			int[] inodoctos2 = new int[3];
			int[] inodoctos = new int[3];
			com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
					"07");
			String dataSourceName = VParametros
					.getPropEspecifica("ConDBModulo");
			com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
					dataSourceName);
			java.sql.Connection conn = dbConn.getConnection();

			StringBuffer cSQL = new StringBuffer();

			cSQL.append("select 0,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 1 ) ");
			cSQL.append("union ");
			cSQL.append("select 1,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 2 ) ");
			cSQL.append("union ");
			// Se modifico la consulta para traer el maximo en la huella
			// 19/05/08 LAS
			// Se modifico nuevmanet6e la consulta para traer el la huella del
			// dedo numero 2 27/05/08 LAS
			// cSQL.append("select 2,inodocto from (Select inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 order by (SUBSTR(CHAR(tsCaptura),1,10)) DESC,  iDedo ASC FETCH FIRST 1 ROW ONLY) h ");
			cSQL.append("select 2,inodocto from (Select max(inodocto) as inodocto FROM LICFFH WHERE iCvePersonal = ? AND ICVETIPOFFH = 3 AND IDEDO=2) h");

			java.sql.PreparedStatement pstmt = conn.prepareStatement(cSQL
					.toString());
			pstmt.setInt(1, iCvePersonal);
			pstmt.setInt(2, iCvePersonal);
			pstmt.setInt(3, iCvePersonal);
			java.sql.ResultSet rset = pstmt.executeQuery();
			for (int i = 0; rset.next() && i < inodoctos2.length; i++) {
				inodoctos2[rset.getInt(1)] = rset.getInt(2);
			}

			int inodoctonull = 51827;
			int inodoctonull2 = 42731;
			int foto = 0;
			int huella = 0;
			int firma = 0;

			// Obtener Foto
			java.sql.PreparedStatement pstmtFoto = null;
			String cSQLFoto = "";

			cSQLFoto = "select 0,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = "
					+ iCvePersonal + " AND ICVETIPOFFH = 1 ) ";
			// System.out.println(cSQLDireccion2);
			pstmtFoto = conn.prepareStatement(cSQLFoto);
			java.sql.ResultSet rsetFoto = pstmtFoto.executeQuery();
			rsetFoto = pstmtFoto.executeQuery();
			while (rsetFoto.next()) {
				foto = 1;
				inodoctos[rsetFoto.getInt(1)] = rsetFoto.getInt(2);

			}

			if (foto == 0)
				inodoctos[0] = inodoctonull;

			// Cerrando conexion
			rsetFoto.close();
			pstmtFoto.close();

			// Obtener Firma
			/*
			 * java.sql.PreparedStatement pstmtFirma = null; String cSQLFirma =
			 * "";
			 * 
			 * cSQLFirma =
			 * "select 1,inodocto from licffh where tscaptura = (select max(tscaptura) FROM LICFFH WHERE iCvePersonal = "
			 * +iCvePersonal+" AND ICVETIPOFFH = 2 ) ";
			 * //System.out.println(cSQLDireccion2); pstmtFirma =
			 * conn.prepareStatement(cSQLFirma); java.sql.ResultSet rsetFirma =
			 * pstmtFirma.executeQuery(); rsetFirma = pstmtFirma.executeQuery();
			 * while (rsetFirma.next()) { firma = 1;
			 * inodoctos[rsetFirma.getInt(1)] = rsetFirma.getInt(2); }
			 * 
			 * 
			 * if(firma == 0) inodoctos[1] = inodoctonull2;
			 * 
			 * 
			 * //Cerrando conexion rsetFirma.close(); pstmtFirma.close();
			 */

			// Obtener Huella
			java.sql.PreparedStatement pstmtHuella = null;
			String cSQLHuella = "";

			cSQLHuella = "select 2,inodocto from (Select max(inodocto) as inodocto FROM LICFFH WHERE iCvePersonal = "
					+ iCvePersonal + " AND ICVETIPOFFH = 3 AND IDEDO=2) h";
			// System.out.println(cSQLDireccion2);
			pstmtHuella = conn.prepareStatement(cSQLHuella);
			java.sql.ResultSet rsetHuella = pstmtHuella.executeQuery();
			rsetHuella = pstmtHuella.executeQuery();
			while (rsetHuella.next()) {
				if (rsetHuella.getString(2) != null) {
					inodoctos[rsetHuella.getInt(1)] = rsetHuella.getInt(2);
					huella = 1;
				}
			}

			if (huella == 0)
				inodoctos[2] = inodoctonull2;

			// System.out.println();

			// Cerrando conexion
			rsetHuella.close();
			pstmtHuella.close();

			// Recolectando Parametros
			// Numero personal
			String ClaveExpediente = "";
			ClaveExpediente = "" + iCvePersonal;

			// Numero de Examen
			String NumeroExamen = "";
			NumeroExamen = "" + iNumExamen;

			// Numero Unidad Medica
			String NumeroUniMed = "";

			// System.out.println("Expediente = " + ClaveExpediente +
			// " numeroexamen = "+ NumeroExamen + " NumeroUniMed = "+
			// NumeroUniMed+"");

			// Obtener Direccion Unidad Medica

			java.sql.PreparedStatement pstmtDireccion2 = null;
			String cSQLDireccion2 = "";

			cSQLDireccion2 = "select ICVEUNIMED from expexamaplica where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ "";
			// System.out.println(cSQLDireccion2);
			pstmtDireccion2 = conn.prepareStatement(cSQLDireccion2);
			java.sql.ResultSet rsetDireccion2 = pstmt.executeQuery();
			rsetDireccion2 = pstmtDireccion2.executeQuery();
			while (rsetDireccion2.next()) {
				NumeroUniMed = rsetDireccion2.getString(1);
			}

			// Cerrando conexion
			rsetDireccion2.close();
			pstmtDireccion2.close();

			// Obtener Direccion Unidad Medica
			String DireccionUM = "";
			String Ciudad = "";
			String Colonia = "";
			String Calle = "";
			java.sql.PreparedStatement pstmtDireccion = null;
			String cSQLDireccion = "";

			cSQLDireccion = "select CCIUDAD, CCOLONIA, CCALLE  from GRLUNIMED where ICVEUNIMED = "
					+ NumeroUniMed + "";
			pstmtDireccion = conn.prepareStatement(cSQLDireccion);
			java.sql.ResultSet rsetDireccion = pstmt.executeQuery();
			rsetDireccion = pstmtDireccion.executeQuery();
			while (rsetDireccion.next()) {
				Ciudad = rsetDireccion.getString(1);
				Colonia = rsetDireccion.getString(2);
				Calle = rsetDireccion.getString(3);
			}
			DireccionUM = "" + Ciudad + ", " + Colonia + ", " + Calle;
			// Cerrando conexion
			rsetDireccion.close();
			pstmtDireccion.close();

			// Obtener Direccion Modulo

			java.sql.PreparedStatement pstmtDireccion3 = null;
			String cSQLDireccion3 = "";
			String NumeroModulo = "";

			cSQLDireccion3 = "select ICVEMODULO from expexamaplica where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen
					+ "";
			// System.out.println(cSQLDireccion2);
			pstmtDireccion3 = conn.prepareStatement(cSQLDireccion3);
			java.sql.ResultSet rsetDireccion3 = pstmt.executeQuery();
			rsetDireccion3 = pstmtDireccion3.executeQuery();
			while (rsetDireccion3.next()) {
				NumeroModulo = rsetDireccion3.getString(1);
			}

			// Cerrando conexion
			rsetDireccion3.close();
			pstmtDireccion3.close();

			// Obtener Direccion Modulo
			String DireccionMod = "";
			String CiudadMod = "";
			String ColoniaMod = "";
			String CalleMod = "";
			String CMunicipio = "";
			String IMunicipio = "";
			String IEstado = "";
			String IPais = "";
			String CodPostal = "";

			java.sql.PreparedStatement pstmtDireccionM = null;
			String cSQLDireccionM = "";

			cSQLDireccionM = "select CCIUDAD, CCOLONIA, CCALLE, ICVEMUNICIPIO, ICVEESTADO, ICVEPAIS, ICP  from GRLMODULO where ICVEUNIMED = "
					+ NumeroUniMed + " AND ICVEMODULO = " + NumeroModulo;
			pstmtDireccionM = conn.prepareStatement(cSQLDireccionM);
			java.sql.ResultSet rsetDireccionM = pstmt.executeQuery();
			rsetDireccionM = pstmtDireccionM.executeQuery();
			while (rsetDireccionM.next()) {
				Ciudad = rsetDireccionM.getString(1);
				Colonia = rsetDireccionM.getString(2);
				Calle = rsetDireccionM.getString(3);
				IMunicipio = rsetDireccionM.getString(4);
				IEstado = rsetDireccionM.getString(5);
				IPais = rsetDireccionM.getString(6);
				CodPostal = rsetDireccionM.getString(7);
			}

			cSQLDireccionM = "SELECT CNOMBRE FROM GRLMUNICIPIO WHERE ICVEPAIS = "
					+ IPais
					+ " AND ICVEENTIDADFED = "
					+ IEstado
					+ " AND ICVEMUNICIPIO = " + IMunicipio + "";
			pstmtDireccionM = conn.prepareStatement(cSQLDireccionM);
			rsetDireccionM = pstmtDireccionM.executeQuery();
			while (rsetDireccionM.next()) {
				CMunicipio = rsetDireccionM.getString(1);
			}

			DireccionMod = "" + Calle + ", " + Colonia + ", " + Ciudad + ", "
					+ CMunicipio + ", C.P. " + CodPostal;
			// Cerrando conexion
			rsetDireccionM.close();
			pstmtDireccionM.close();

			// Obtener Nombre
			String NombreP = "";
			String ApellidoPP = "";
			String ApellidoMP = "";
			String NumeroPersonal = "";
			String NombrePersona = "";
			int AnoNac = 0;
			NumeroPersonal = "" + ClaveExpediente;
			java.sql.PreparedStatement pstmtNAsP = null;
			String cSQLNAsP = "";

			cSQLNAsP = "select CNOMBRE, CAPPATERNO, CAPMATERNO, {FN YEAR (DTNACIMIENTO)} from PERDATOS where ICVEPERSONAL = "
					+ NumeroPersonal + "";
			pstmtNAsP = conn.prepareStatement(cSQLNAsP);
			java.sql.ResultSet rsetNAsP = pstmt.executeQuery();
			rsetNAsP = pstmtNAsP.executeQuery();
			while (rsetNAsP.next()) {
				NombreP = rsetNAsP.getString(1);
				ApellidoPP = rsetNAsP.getString(2);
				ApellidoMP = rsetNAsP.getString(3);
				AnoNac = rsetNAsP.getInt(4);
			}
			NombrePersona = "" + ApellidoPP + " " + ApellidoMP + " " + NombreP
					+ "";
			if (rsetNAsP != null)
				rsetNAsP.close();
			if (pstmtNAsP != null)
				pstmtNAsP.close();

			// Obtener y Validar Modo Transporte
			String ModoTransporte = "";

			java.sql.PreparedStatement pstmtMT = null;
			String cSQLMT = "";

			cSQLMT = "select ICVEMDOTRANS from EXPEXAMCAT where ICVEEXPEDIENTE = "
					+ ClaveExpediente
					+ " AND INUMEXAMEN = "
					+ NumeroExamen
					+ " ";
			pstmtMT = conn.prepareStatement(cSQLMT);
			java.sql.ResultSet rsetMT = pstmt.executeQuery();
			rsetMT = pstmtMT.executeQuery();
			while (rsetMT.next()) {
				ModoTransporte = rsetMT.getString(1);
			}
			rsetMT.close();
			pstmtMT.close();

			String PCarretero = "";
			String PAereo = "";
			String PMaritimo = "";
			String PFerroviario = "";

			if (ModoTransporte.equals("1")) {
				PAereo = "X";
			}

			if (ModoTransporte.equals("2")) {
				PCarretero = "X";
			}

			if (ModoTransporte.equals("3")) {
				PFerroviario = "X";
			}

			if (ModoTransporte.equals("4")) {
				PMaritimo = "X";
			}

			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			dbConn.closeConnection();

			// Obtener clave proceso
			String ClaveProceso;
			String Psicofisico = "1";
			String EMO = "2";
			String AptoPro = " ";
			ClaveProceso = "" + request.getParameter("iCveProceso");

			/*
			 * hm.put("rRFC", "JEJEJEJJ"); hm.put("rCURP", "");
			 * hm.put("rRazonSocial", "Prueba"); hm.put("rAPaterno", "");
			 * hm.put("rAMaterno", ""); hm.put("rNombre", "Probando");
			 * hm.put("rRazonSocial", ""); hm.put("rIdDependencia", "12");
			 * hm.put("rDependencia","Organismo"); hm.put("rNoAplPer", "X");
			 * hm.put("rMen", ""); hm.put("rBim", ""); hm.put("rTri", "");
			 * hm.put("rCua", ""); hm.put("rSem", ""); hm.put("rDEj", "");
			 * hm.put("rEjercicio", ""); hm.put("rPeriodo", "");
			 * hm.put("rCveReferencia", "12608263"); hm.put("rCadDependencia",
			 * "cad"); hm.put("rImporte", ""); hm.put("rIVA", "");
			 * hm.put("rPActualizada", ""); hm.put("rRecargos", "");
			 * hm.put("rAPagar", ""); hm.put("rTotalPagar", "");
			 */
			// File file = new File("/medprev/img/logo_SCT.jpg");
			// FileInputStream fileLogo = new FileInputStream(file);

			// GeneraciÃ³n de formato por CategorÃ­a
			// System.setProperty("java.awt.headless","true");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatoHora = new SimpleDateFormat("h:mm");
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
					"yyyy-MM-dd");
			String strFecha = VDictamen.VPerDatos.getCDscFecNacimiento();
			Date fechaConvert = null;
			try {
				fechaConvert = formatoDelTexto.parse(strFecha);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}

			Calendar hoy = Calendar.getInstance();
			Calendar nac = Calendar.getInstance();
			nac.setTime(fechaConvert);
			int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
			if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
					.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE))
				edad--;
			// System.out.println("fecha de base: " + strFecha +
			// " fecha convertida: " + fechaConvert + " edad: " + edad);
			TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
			TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
			Vector vcEXPExamCat = dEXPExamCat.FindBy(ClaveExpediente,
					NumeroExamen);
			if (vcEXPExamCat.size() > 0) {
				String concaMdoTrans = "", concaCat = "", contaProcesoMot = "";
				for (int i = 0; i < vcEXPExamCat.size(); i++) {
					vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);
					if (i == 0) {
						hm.put("categoria", vEXPExamCat.getCDscCategoria());
						hm.put("modoTransporte", vEXPExamCat.getCDscMdoTrans());
						hm.put("procesoMotivo", VDictamen.getCDscProceso()
								+ " POR " + vEXPExamCat.getCDscMotivo());
					}
					if (i == 1) {
						hm.put("categoria2", vEXPExamCat.getCDscCategoria());
						hm.put("modoTransporte2", vEXPExamCat.getCDscMdoTrans());
						hm.put("procesoMotivo2", VDictamen.getCDscProceso()
								+ " POR " + vEXPExamCat.getCDscMotivo());
					}
					if (i == 2) {
						hm.put("categoria3", vEXPExamCat.getCDscCategoria());
						hm.put("modoTransporte3", vEXPExamCat.getCDscMdoTrans());
						hm.put("procesoMotivo3", VDictamen.getCDscProceso()
								+ " POR " + vEXPExamCat.getCDscMotivo());
					}

				}
				if (vcEXPExamCat.size() == 1) {
					hm.put("categoria2", "");
					hm.put("modoTransporte2", "");
					hm.put("procesoMotivo2", "");
					hm.put("categoria3", "");
					hm.put("modoTransporte3", "");
					hm.put("procesoMotivo3", "");
				}
				if (vcEXPExamCat.size() == 2) {
					hm.put("categoria3", "");
					hm.put("modoTransporte3", "");
					hm.put("procesoMotivo3", "");
				}
			} else {
				/*
				 * hm.put("categoria",
				 * request.getParameter("hdDscCategoriaRep"));
				 * hm.put("modoTransporte",
				 * request.getParameter("hdMdoTransRep"));
				 * hm.put("procesoMotivo", VDictamen.getCDscProceso() + " POR "
				 * + request.getParameter("hdDscMotivoRep"));
				 */
				hm.put("categoria", "");
				hm.put("modoTransporte", "");
				hm.put("procesoMotivo", "");
			}

			Date fechaActual = new Date();
			hm.put("lugarDir", DireccionMod.toString());
			hm.put("fechaExamen", formatoFecha.format(fechaActual).toString());
			hm.put("hora", formatoHora.format(fechaActual).toString());
			hm.put("descUnidadMedica", VDictamen.VUniMed.getCDscUniMed());
			hm.put("nombre", VDictamen.VPerDatos.getCNombre());
			hm.put("edad", String.valueOf(edad));
			hm.put("rfc", VDictamen.VPerDatos.getCRFC());
			hm.put("curp", VDictamen.VPerDatos.getCCURP());
			hm.put("genero", VDictamen.VPerDatos.getCGenero());
			hm.put("numExpDGPMPT",
					String.valueOf(VDictamen.VExamCat.getICveExpediente()));

			LICDownFoto dLICDownFoto = new LICDownFoto();
			// System.out.println("////////////////////     VALIDANDO FICHEROS     //////////////////////");
			// Buscando la foto
			String sFichero = ""
					+ VParametros2.getPropEspecifica("RutaNAS2").toString()
					+ "f-" + ClaveExpediente + ".jpg";
			boolean errorbiometricotamaño = false;
			boolean errorbiometrico = false;
			
			File ficheroF = new File(sFichero);
			if (!ficheroF.exists()) {
				//System.out.println("No existe por lo cual se descargara el biometrico");
				dLICDownFoto.getImg(Integer.toString(inodoctos[0]),
						ClaveExpediente, conn);
			}
			// / VALIDAR QUE LA FOTO NO SEA MAYOR A UN MEGA
						// tamaño maximo de foto en constancia
						int TamMaxfoto = Integer.parseInt(VParametros
								.getPropEspecifica("TamanioMaxFotoBytes"));
						if (ficheroF.length() >= TamMaxfoto) {
							String source = "errorfoto";
							data = source.getBytes("UTF-8");
							errorbiometricotamaño = true;
							//System.out.println("Tamaño grande foto");
							//return data;
						}

						// / VALIDAR QUE LA FOTO NO SEA IGUAL A 0 BYTES
						if (ficheroF.length() == 0) {
							String source = "0bytes";
							data = source.getBytes("UTF-8");
							ficheroF.delete();
							//System.out.println("Tamaño 0 foto");
							errorbiometrico = true;
							//return data;
						}
						
			
			
			// Buscando la firma
			/*
			 * sFichero =
			 * ""+VParametros2.getPropEspecifica("RutaNAS2").toString(
			 * )+"r-"+ClaveExpediente+".bmp"; fichero = new File(sFichero); if
			 * (!fichero.exists()){ //System.out.println(
			 * "No existe por lo cual se descargara el biometrico");
			 * dLICDownFoto.getFirma(Integer.toString(inodoctos[1]),
			 * ClaveExpediente, conn); }
			 */

			// Buscando la huella
			sFichero = ""
					+ VParametros2.getPropEspecifica("RutaNAS2").toString()
					+ "h-" + ClaveExpediente + ".bmp";
			File ficheroH = new File(sFichero);
			//fichero = new File(sFichero);
			if (!ficheroH.exists()) {
				//System.out.println("No existe por lo cual se descargara el biometrico");
				dLICDownFoto.getHuella(Integer.toString(inodoctos[2]),
						ClaveExpediente, conn);
			}
			
			// / VALIDAR QUE LA HUELLA NO SEA IGUAL A 0 BYTES
			if (ficheroH.length() == 0) {
				String source = "0bytes";
				data = source.getBytes("UTF-8");
				ficheroH.delete();
				//System.out.println("Tamaño 0 huella");
				errorbiometrico = true;
				//return data;
			}
			if(errorbiometricotamaño || errorbiometrico){
				this.mostrarMensaje(errorbiometricotamaño, errorbiometrico, ClaveExpediente);
				response.sendRedirect("closeWindow.html"); 
				throw new Exception("Error al generar el archivo");
			}
			
			// final de validacion de existencia y guardado de biometricos
			hm.put("RutaNAS", VParametros2.getPropEspecifica("RutaNAS2")
					.toString());
			hm.put("imprimirCategoria", new Boolean(false));
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
				//JRBeanArrayDataSource dataSourceReport2 = new JRBeanArrayDataSource(  //Comentado por A. Ortiz
					//	reportList.toArray()); // Comentado por A. Ortiz
				jasperPrinterList.add(JasperFillManager.fillReport(report, hm,
						dataSourceReport));
				//jasperPrinterList.add(JasperFillManager.fillReport(report2, hm,
					//	dataSourceReport2));
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
	 protected void mostrarMensaje(boolean errorbiometricotamaño, boolean errorbiometrico, String expediente) {
         String tamaño = "- La foto del usuario es mayor a 1 Megabyte, favor de capturarla \n nuevamente con una resolución menor a 1024 píxeles";
         String cero = "- Ocurrió un  error al generar el formato de consentimiento informado \n y declaración de salud , intente generar nuevamente y si el problema \n persiste capture biométricos de nuevo.";
         if(errorbiometricotamaño){
        	 JOptionPane.showMessageDialog(null, tamaño);
         }
         if(errorbiometrico){
        	 JOptionPane.showMessageDialog(null, cero);
         }
         String sFichero = ""
					+ VParametros2.getPropEspecifica("RutaNAS2").toString()
					+ "h-" + expediente + ".bmp";
			File ficheroF = new File(sFichero);
			sFichero = ""
					+ VParametros2.getPropEspecifica("RutaNAS2").toString()
					+ "h-" + expediente + ".bmp";
			File ficheroH = new File(sFichero);
			ficheroF.delete();
			ficheroH.delete();
			
	 }
} // class

