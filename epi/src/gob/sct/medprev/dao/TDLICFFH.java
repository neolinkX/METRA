package gob.sct.medprev.dao;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.vo.*;
import com.micper.util.*;
import com.micper.seguridad.vo.TVDinRep02;

/**
 * Data Acces Object de LICFFH DAO </p>
 * 
 * @author AG SA 
 */

public class TDLICFFH extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDLICFFH() {
	}

	/**
	 * Método findExpToDelete (regresa el resultado de una consulta)
	 * 
	 * @Autor: AG SA
	 */
	public String findByCustom(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String regreso = "null";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = SQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regreso = rset.getString(1);
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}

	/**
	 * Metodo InodoctoDeFotoFirmaHuella, regresa un vector con los documentos
	 * que se deben subir de la NAS al content, por tipo de coumento(foto firma
	 * o huella);
	 * 
	 * @Autor: AG SA
	 */
	@SuppressWarnings("finally")
	public Vector<String> InodoctoDeFotoFirmaHuella(String inicio, String fin)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cadena = "";
		Vector<String> datos = new Vector<String>();
		try {
			String SQL = "SELECT ICVEPERSONAL, INODOCTO, ICVETIPOFFH FROM LICFFH "
					+ "WHERE icvepersonal = 586135 and TSCAPTURA >='"
					+ inicio
					+ " 00:00:00.000000' and TSCAPTURA<='"
					+ fin
					+ " 23:59:59.000000' "
					+ "and ((icvetipoffh = 3 and idedo = 2 ) or(icvetipoffh = 2) or "
					+ "(icvetipoffh = 1) ) ";
			//System.out.println(SQL);
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = SQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (rset.getInt(3) == 1) {
					cadena = "f-" + rset.getString(1) + ".jpg/"
							+ rset.getString(2) + "/";
					datos.addElement(cadena);
				}
				if (rset.getInt(3) == 2) {
					cadena = "r-" + rset.getString(1) + ".bmp/"
							+ rset.getString(2) + "/";
					datos.addElement(cadena);
				}
				if (rset.getInt(3) == 3) {
					cadena = "h-" + rset.getString(1) + ".bmp/"
							+ rset.getString(2) + "/";
					datos.addElement(cadena);
				}

			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return datos;
		}
	}
	
	/**
	 * Método findHuellasValidas(regresa el true si tiene una toma de huellas posterior al 29 de abril del 2013)
	 * 
	 * @Autor: AG SA
	 */
	public boolean findHuellasValidas(String cExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean regreso = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "SELECT ICVEPERSONAL FROM LICFFH WHERE TSCAPTURA >= '2013-04-29 00:00:00.000001' AND ICVETIPOFFH = 3 AND ICVEPERSONAL = "+cExpediente;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regreso = true;
			}
		} catch (Exception ex) {
			warn("RegresaInt", ex);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}	
	
	
	/**
	 * Método findHuellasValidas(regresa el true si tiene una toma de huellas posterior al 29 de abril del 2013)
	 * 
	 * @Autor: AG SA
	 */
	public int findFotoHuellasValidas(String cExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int regreso = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "select 0,inodocto " +
					"	   from licffh " +
					"	   where tscaptura = (select max(L.tscaptura) " +
					"						FROM LICFFH AS L, EXPEXAMCAT AS C" +
					"  						WHERE L.iCvePersonal = "+cExpediente+" "+
					"							AND L.ICVETIPOFFH = 1 AND" +
					"							C.ICVEEXPEDIENTE = L.ICVEPERSONAL AND" +
					"							C.DTINICIOVIG < (SELECT MAX(DTFECHA) " +
					"											FROM EPICISCITA " +
					"											WHERE ICVEPERSONAL = "+cExpediente+")" +
					"							AND L.TSCAPTURA < C.DTINICIOVIG +1 DAYS)";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regreso = rset.getInt(2);
			}
		} catch (Exception ex) {
			warn("RegresaInt", ex);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}	

	/**
	 * Devuelve el numero de dias que han transcurrido desde la primer toma de biometricos
	 * 
	 * 
	 * @Autor: AG SA
	 */
	public int NumDiasPrimerTomaBiometricos(int iExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int regreso = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "SELECT DAYS(CURRENT DATE) - DAYS(MIN(TSCAPTURA)) " +
					      "FROM LICFFH AS L  WHERE ICVEPERSONAL ="+iExpediente;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regreso = rset.getInt(1);
			}

		} catch (Exception ex) {
			warn("RegresaInt", ex);
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}		
	

	/**
	 * Verifica que el expediente contenga Biometricos
	 * @throws ParseException 
	 * 
	 * @Autor: AG SA
	 */
	@SuppressWarnings("finally")
	public String findByVerificaBiometricos(String expediente) throws DAOException, ParseException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rset1 = null;
		ResultSet rset2 = null;
		ResultSet rset3 = null;
		String regreso = "";
		boolean foto = false;
		boolean firma = false;
		boolean huella = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
		String fecha = "2000-01-01 00:00:00.000001";
		java.util.Date parsedDate = dateFormat.parse(fecha);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		java.sql.Timestamp tfoto = timestamp; 
		java.sql.Timestamp tfirma = timestamp; 
		java.sql.Timestamp thuella = timestamp;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL1 = "SELECT TSCAPTURA FROM LICFFH WHERE ICVETIPOFFH = 1 AND ICVEPERSONAL = "+expediente +" ORDER BY TSCAPTURA  ASC";
			String cSQL2 = "SELECT TSCAPTURA FROM LICFFH WHERE ICVETIPOFFH = 2 AND ICVEPERSONAL = "+expediente +" ORDER BY TSCAPTURA  ASC";
			String cSQL3 = "SELECT TSCAPTURA FROM LICFFH WHERE ICVETIPOFFH = 3 AND ICVEPERSONAL = "+expediente +" ORDER BY TSCAPTURA  ASC";
			
			pstmt1 = conn.prepareStatement(cSQL1);
			rset1 = pstmt1.executeQuery();
			while (rset1.next()) {
				foto = true;
				tfoto = rset1.getTimestamp(1);
				//System.out.println("foto");
			}
			pstmt2 = conn.prepareStatement(cSQL2);
			rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				firma = true;
				tfirma = rset2.getTimestamp(1);
				//System.out.println("firma");
			}
			pstmt3 = conn.prepareStatement(cSQL3);
			rset3 = pstmt3.executeQuery();
			while (rset3.next()) {
				huella = true;
				thuella = rset3.getTimestamp(1);
				//System.out.println("huella");
			}
			
			//System.out.println(thuella);
			
			if(!foto){
				regreso= regreso+"<tr><td class=\"EEtiqueta\"><center>Favor de Capturar Foto </td></tr>";
			}
			if(!firma){
				regreso= regreso+"<tr><td class=\"EEtiqueta\"><center>Favor de Capturar Firma </td></tr>";
			}
			if(!huella){
				regreso= regreso+"<tr><td class=\"EEtiqueta\"><center>Favor de Capturar Huella</td></tr>";
			}
			this.EliminaBiometricos3K(expediente, tfoto, tfirma, thuella);
			//System.out.println("regreso = "+regreso);
			
		} catch (Exception ex) {
			warn("findByVerificaBiometricos", ex);
		} finally {
			try {
				if (rset1 != null) {
					rset1.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (rset3 != null) {
					rset3.close();
				}
				if (pstmt3 != null) {
					pstmt3.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return regreso;
		}
	}
	

	/**
	 * Elimina Biometricos menores a 3K
	 * @throws ParseException 
	 * 
	 * @Autor: AG SA
	 */
	public void EliminaBiometricos3K(String expediente, java.sql.Timestamp tfoto, 
									java.sql.Timestamp tfirma, java.sql.Timestamp thuella
									) throws DAOException, ParseException {
		//System.out.println("EliminaBiometricos3K");
		String sFicherof = ""+ VParametros.getPropEspecifica("RutaNAS2").toString() + "f-"+ expediente + ".jpg";
		String sFicheror = ""+ VParametros.getPropEspecifica("RutaNAS2").toString() + "r-"+ expediente + ".bmp";
		String sFicheroh = ""+ VParametros.getPropEspecifica("RutaNAS2").toString() + "h-"+ expediente + ".bmp";
		//System.out.println("EliminaBiometricos3K - 2");
		File ficherof = new File(sFicherof);
		File ficheror = new File(sFicheror);
		File ficheroh = new File(sFicheroh);
		int TresK = 3072;
		//System.out.println("EliminaBiometricos3K - 3");
		try{
			if (ficherof.exists()) {
				//System.out.println("EliminaBiometricos3K - 4");
				//System.out.println("foto length = "+ficherof.length());
				if (ficherof.length() <= TresK) {
					ficherof.delete();
				}else{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
					long ms = ficherof.lastModified();
					Date d = new Date(ms);
					Calendar c = new GregorianCalendar();
					c.setTime(d);
					String dia = Integer.toString(c.get(Calendar.DATE));
					String mes = Integer.toString((c.get(Calendar.MONTH) + 1)) ;
					String annio = Integer.toString(c.get(Calendar.YEAR));
					String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
					String minuto = Integer.toString(c.get(Calendar.MINUTE));
					String segundo = Integer.toString(c.get(Calendar.SECOND));
					String fecha = annio + "-" + mes + "-"+dia+" "+hora+":"+minuto+":"+segundo+".000000";
					java.util.Date parsedDate = dateFormat.parse(fecha);
					java.sql.Timestamp timestampf = new java.sql.Timestamp(parsedDate.getTime());
					long f1 = timestampf.getTime();
					long f2 = tfoto.getTime();
					if(f1 < f2){
						ficherof.delete();
						//System.out.println("Foto no actualizada");
				    }				
					//System.out.println("Fehca de foto = "+annio +"-"+ mes +"-"+ dia +"-"+ hora +":"+ minuto +":"+ segundo);
				}
			}
			if (ficheror.exists()) {
				//System.out.println("EliminaBiometricos3K - 5");
				//System.out.println("firma length = "+ficheror.length());
				if (ficheror.length() <= TresK) {
					ficheror.delete();
				}else{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
					long ms = ficheror.lastModified();
					Date d = new Date(ms);
					Calendar c = new GregorianCalendar();
					c.setTime(d);
					String dia = Integer.toString(c.get(Calendar.DATE));
					String mes = Integer.toString((c.get(Calendar.MONTH) + 1)) ;
					String annio = Integer.toString(c.get(Calendar.YEAR));
					String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
					String minuto = Integer.toString(c.get(Calendar.MINUTE));
					String segundo = Integer.toString(c.get(Calendar.SECOND));
					String fecha = annio + "-" + mes + "-"+dia+" "+hora+":"+minuto+":"+segundo+".000000";
					java.util.Date parsedDate = dateFormat.parse(fecha);
					java.sql.Timestamp timestampr = new java.sql.Timestamp(parsedDate.getTime());
					long r1 = timestampr.getTime();
					long r2 = tfirma.getTime();
					if(r1 < r2){
						ficheror.delete();
						//System.out.println("Firma no actualizada");
				    }				
					//System.out.println("Fehca de firma = "+annio +"-"+ mes +"-"+ dia +"-"+ hora +":"+ minuto +":"+ segundo);
				}
			}
			if (ficheroh.exists()) {
				//System.out.println("EliminaBiometricos3K - 6");
				//System.out.println("Longitud de huella = "+sFicheroh.length());
				if (ficheroh.length() <= TresK) {
					ficheroh.delete();
				}else{
					//System.out.println("Huella mayor a 3 K");
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
					long ms = ficheroh.lastModified();
					Date d = new Date(ms);
					Calendar c = new GregorianCalendar();
					c.setTime(d);
					String dia = Integer.toString(c.get(Calendar.DATE));
					String mes = Integer.toString((c.get(Calendar.MONTH) + 1)) ;
					String annio = Integer.toString(c.get(Calendar.YEAR));
					String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
					String minuto = Integer.toString(c.get(Calendar.MINUTE));
					String segundo = Integer.toString(c.get(Calendar.SECOND));
					String fecha = annio + "-" + mes + "-"+dia+" "+hora+":"+minuto+":"+segundo+".000000";
					java.util.Date parsedDate = dateFormat.parse(fecha);
					java.sql.Timestamp timestamph = new java.sql.Timestamp(parsedDate.getTime());
					long h1 = timestamph.getTime();
					long h2 = thuella.getTime();
					//System.out.println("Fehca de huella Nas = "+annio +"-"+ mes +"-"+ dia +"-"+ hora +":"+ minuto +":"+ segundo);
					//System.out.println("Fehca de huella Content= "+thuella);
					if(h1 < h2){
						ficheroh.delete();
						//System.out.println("Huella no actualizada");
				    }				
					//System.out.println("Fehca de huella = "+annio +"-"+ mes +"-"+ dia +"-"+ hora +":"+ minuto +":"+ segundo);
				}
			}
		}catch(Exception e){
			warn("EliminaBiometricos3K", e);
		}
	}
	
}
