/**
 * @author AG SA.
 * @version 1.0
 */

package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MEDSintoma DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDMEDREGSINExt1 extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDREGSINExt1() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDREGSIN = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDREGSIN vMEDREGSIN;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT M.IMAYORA, M.IIGUALA, M.IMENORA, M.LOGICA,  "
					+ " M.LDICTAMENF, M.CALERTA, M.CDSCREGLA, M.LDEDPENDIENTE, M.ICVEREGLA, M.LACTIVO, T.CDSCMDOTRANS, C.CDSCCATEGORIA, S.ICVETPORESP,"
					+ " M.ICVEMDOTRANS, M.ICVECATEGORIA "
					+ " FROM	MEDREGSIN AS M, GRLMDOTRANS AS T, GRLCATEGORIA AS C, MEDSINTOMAS AS S  "
					+ " " + cWhere + "  AND  "
					+ " M.ICVEMDOTRANS = T.ICVEMDOTRANS AND	"
					+ " M.ICVEMDOTRANS = C.ICVEMDOTRANS AND	"
					+ " M.ICVECATEGORIA = C.ICVECATEGORIA AND		"
					+ " M.ICVESERVICIO = S.ICVESERVICIO AND		"
					+ " M.ICVERAMA = S.ICVERAMA AND		"
					+ " M.ICVESINTOMA = S.ICVESINTOMA "
					+ " ORDER BY M.ICVEREGLA";

		  System.out.println("MEDREGSIN = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setIMayorA(new Double(rset.getDouble(1)));
				vMEDREGSIN.setIIgualA(new Double(rset.getDouble(2)));
				vMEDREGSIN.setIMenorA(new Double(rset.getDouble(3)));
				vMEDREGSIN.setLogica(rset.getInt(4));
				vMEDREGSIN.setLDictamenF(rset.getInt(5));
				vMEDREGSIN.setCAlerta(rset.getString(6));
				vMEDREGSIN.setCdscRegla(rset.getString(7));
				vMEDREGSIN.setLDependiente(rset.getInt(8));
				vMEDREGSIN.setICveRegla(rset.getInt(9));
				vMEDREGSIN.setLActivo(rset.getInt(10));
				vMEDREGSIN.setCMdoTrans(rset.getString(11));
				vMEDREGSIN.setCCategorias(rset.getString(12));
				vMEDREGSIN.setICveTpoResp(rset.getInt(13));
				vMEDREGSIN.setICveMdoTrans(rset.getInt(14));
				vMEDREGSIN.setICveCategoria(rset.getInt(15));
				vcMEDREGSIN.addElement(vMEDREGSIN);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
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
				warn("FindByAll.close", ex2);
			}
			return vcMEDREGSIN;
		}
	}


	/**
	 * M??todo Insert - NO BORRAR JMG
	 */
	@SuppressWarnings("finally")
	public Object insert(Connection conGral, String csql) throws DAOException {
		// System.out.println("Entrando en el insert");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = ""+csql;
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.executeUpdate();
			cClave = "" + iMax;
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}
	
	public void CalculaMetaDiabetes(String ClaveExpediente, String NumeroExamen) throws Exception {

		com.micper.ingsw.TParametro VParametros = new com.micper.ingsw.TParametro(
				"07");
		String dataSourceName = VParametros
				.getPropEspecifica("ConDBModulo");

		com.micper.sql.DbConnection dbConn = new com.micper.sql.DbConnection(
				dataSourceName);
		java.sql.Connection conn = dbConn.getConnection();


		try {
			//Parametros
			float P1 = (float) 0.0;
			float P2 = (float) 0.0;
			float P10 = (float) 0.0;
			float P11 = (float) 0.0;
			String P15 = "NO";
			String Diabetes = "";
			int Sistolica = 0;
			int Distolica = 0;
			String TpoDiabetes = "";
			String Retionopatia = "";
			String UtilizaInsulina = "";
			String UtilizaHipoglucemiantes = "";
			int reglaNoAptitud = 0;
			int transporte = 0;
			int categoria = 0;
			
			
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
			

			////Obtener Transporte y Categoria
			String cSQLTransporte = "";
			cSQLTransporte = "select icvemdotrans, icvecategoria from expexamcat where iCveExpediente = "
					+ ClaveExpediente
					+ " and inumexamen = "
					+ NumeroExamen;
			PreparedStatement pstmtTransporte = conn.prepareStatement(cSQLTransporte);
			java.sql.ResultSet rsetTransporte = pstmtTransporte.executeQuery();
			rsetTransporte = pstmtTransporte.executeQuery();
			while (rsetTransporte.next()) {
					transporte = rsetTransporte.getInt(1);
					categoria = rsetTransporte.getInt(2);
			}
			// Cerrando conexion
			rsetTransporte.close();
			pstmtTransporte.close();
			
			
			
			
			
			////Calculos Diabetes
			if(P1 < 170){
				if(P2 < 9){
					if(Sistolica < 130 && Distolica < 80){
						if( P15.equals("NO")){
							Diabetes = "Diabetes No Insulinodependiente Controlada";
							reglaNoAptitud = 1;
						}
					}
				}
			}
			
			if(TpoDiabetes.equals("2")){
				//Opcion A
				if(P1 >= 200){
					Diabetes = "Diabetes No Insulinodependiente Descontrolada";
					reglaNoAptitud = 2;
				}
				//Opcion B
				if(P1 >= 170){
					if(P2 >= 9){
						if(Sistolica > 140 && Distolica > 90){
							if(P10 > 35){
								if(P11 > 35){
									if(Retionopatia.equals("3")){
										Diabetes = "Diabetes No Insulinodependiente Descontrolada";	
										reglaNoAptitud = 2;
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
										reglaNoAptitud = 2;
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
					reglaNoAptitud = 3;
				}
			}
			
			if(TpoDiabetes.equals("2")){
				if(P1 == 0 ||
						P2 == 0 ||
						Sistolica == 0 ||
						Distolica == 90 ||
						Retionopatia.equals("4") ){
					    	Diabetes = "Falta de información ";
					    	reglaNoAptitud = 4;
				}
			}
			
			
			TDEXPResultadoExt1 dEXPResultadoExt1 = new TDEXPResultadoExt1();
			dEXPResultadoExt1.DeleteSintoma(ClaveExpediente, NumeroExamen, "75", "1", "86");
			String csql ="";
			if(reglaNoAptitud == 1){
				csql = "Insert into ExpResultado values("+ClaveExpediente+","+NumeroExamen+"," +
						"75,1,86,0,0,1,0,'')";
				dEXPResultadoExt1.insert(null, csql);	
			}
			if(reglaNoAptitud == 2 || reglaNoAptitud == 3){
				csql = "Insert into ExpResultado values("+ClaveExpediente+","+NumeroExamen+"," +
						"75,1,86,0,0,2,0,'')";
				dEXPResultadoExt1.insert(null, csql);	
				csql = "Insert into ExpRegSin values("+ClaveExpediente+","+NumeroExamen+"," +
						"75,1,86,"+transporte+","+categoria+",0,1)";
				this.insert(null, csql);
			}
			if(reglaNoAptitud == 4){
				csql = "Insert into ExpResultado values("+ClaveExpediente+","+NumeroExamen+"," +
						"75,1,86,0,0,3,0,'')";
				dEXPResultadoExt1.insert(null, csql);	
			}
						
	
		} // / try
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}// metodo
	
	

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllAlertasDependientes(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDREGSIN = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDREGSIN vMEDREGSIN;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " SELECT M.IMAYORA, M.IIGUALA, M.IMENORA, M.LOGICA,  "
					+ " M.LDICTAMENF, M.CALERTA, M.CDSCREGLA, M.LDEDPENDIENTE, M.ICVEREGLA, M.LACTIVO, T.CDSCMDOTRANS, C.CDSCCATEGORIA, S.ICVETPORESP,"
					+ " M.ICVEMDOTRANS, M.ICVECATEGORIA, M.ICVESERVICIO, M.ICVERAMA, M.ICVESINTOMA, A.SINTOMADEP, S.CPREGUNTA "
					+ " FROM	MEDREGSIN AS M, GRLMDOTRANS AS T, GRLCATEGORIA AS C, MEDSINTOMAS AS S, MEDAREG AS A  "
					+ " " + cWhere + "  AND  "
					+ " M.ICVEMDOTRANS = T.ICVEMDOTRANS AND	"
					+ " M.ICVEMDOTRANS = C.ICVEMDOTRANS AND	"
					+ " M.ICVECATEGORIA = C.ICVECATEGORIA AND		"
					+ " M.ICVESERVICIO = S.ICVESERVICIO AND		"
					+ " M.ICVERAMA = S.ICVERAMA AND		"
					+ " M.ICVESINTOMA = S.ICVESINTOMA AND "
					+ " M.ICVESERVICIO = A.ICVESERVICIO AND		"
					+ " M.ICVERAMA = A.ICVERAMA AND		"
					+ " M.ICVESINTOMA = A.ICVESINTOMA "
					+ " ORDER BY M.ICVEREGLA";

			System.out.println("MEDREGSIN = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDREGSIN = new TVMEDREGSIN();
				vMEDREGSIN.setIMayorA(new Double(rset.getDouble(1)));
				vMEDREGSIN.setIIgualA(new Double(rset.getDouble(2)));
				vMEDREGSIN.setIMenorA(new Double(rset.getDouble(3)));
				vMEDREGSIN.setLogica(rset.getInt(4));
				vMEDREGSIN.setLDictamenF(rset.getInt(5));
				vMEDREGSIN.setCAlerta(rset.getString(6));
				vMEDREGSIN.setCdscRegla(rset.getString(7));
				vMEDREGSIN.setLDependiente(rset.getInt(8));
				vMEDREGSIN.setICveRegla(rset.getInt(9));
				vMEDREGSIN.setLActivo(rset.getInt(10));
				vMEDREGSIN.setCMdoTrans(rset.getString(11));
				vMEDREGSIN.setCCategorias(rset.getString(12));
				vMEDREGSIN.setICveTpoResp(rset.getInt(13));
				vMEDREGSIN.setICveMdoTrans(rset.getInt(14));
				vMEDREGSIN.setICveCategoria(rset.getInt(15));
				vMEDREGSIN.setICveServicio(rset.getInt(16));
				vMEDREGSIN.setICveRama(rset.getInt(17));
				vMEDREGSIN.setICveSintoma(rset.getInt(18));
				vMEDREGSIN.setSintomaDep(rset.getInt(19));
				vMEDREGSIN.setCPregunta(rset.getString(20));
				vcMEDREGSIN.addElement(vMEDREGSIN);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
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
				warn("FindByAll.close", ex2);
			}
			return vcMEDREGSIN;
		}
	}

	
		
}