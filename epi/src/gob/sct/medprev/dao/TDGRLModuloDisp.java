package gob.sct.medprev.dao;

import gob.sct.medprev.vo.TVGRLModulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

/**
 * <p>
 * Title: Data Acces Object de GRLModuloDisp DAO
 * </p>
 * <p>
 * Description: DAO Tabla GRLModuloDisp
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SCT UTIC
 * </p>
 * 
 * @author Lic. AG SA L
 * @version 1.0
 */


public class TDGRLModuloDisp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLModuloDisp() {
	}
		
	/**
	 * Metodo Find By True
	 * 
	 * @author Lic. AG SA L
	 */
	public boolean FindByTrue(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean vcGRLModulo = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select A.IcveUnimed " +
				   "from GRLModuloDisp as A, GRLDispositivo as B " + 
					cWhere + 
				   " A.iCveDispositivo = B.iCveDispositivo and "+
				   " B.lActivo = 1 ";
			System.out.println("FindByTrue="+cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vcGRLModulo = true;
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
			return vcGRLModulo;
		}
	}
	
	/**
	 * Metodo Find By True
	 * 
	 * @author Lic. AG SA L
	 */
	public boolean ExisteTrue(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean vcGRLModulo = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLModulo vGRLModulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select iCveUnimed " +
				   "from GRLModuloDisp " + 
					cWhere ;
			//System.out.println(cSQL);
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vcGRLModulo = true;
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
			return vcGRLModulo;
		}
	}
	
	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		String TipoSentencia = "";
		String NumDispositivo = "";
		String Habilitar = "";
		String[] tempDispositivo;
		String[] tempSentencia;
		String[] tempValores;
		String delimiter = "-";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVGRLModulo vGRLModulo = (TVGRLModulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			//////Se obtienen los valores a actualizar//////////
			 	String strD = vGRLModulo.getCDispositivos()+"";
			 	String strS = vGRLModulo.getCDispSentencia()+"";
			 	String strV = vGRLModulo.getCDispValor()+"";
			 	tempDispositivo = strD.split(delimiter,-1);
			 	tempSentencia = strS.split(delimiter,-1);
			 	tempValores = strV.split(delimiter,-1);
			 	
			 	for(int i =0; i < tempDispositivo.length-1 ; i++){
			    		TipoSentencia=""+tempSentencia[i].replace("-", "");
			    		NumDispositivo=""+tempDispositivo[i].replace("-", "");
					    Habilitar=""+tempValores[i].replace("-", "").replace("Insert", "");
				    	
			    
			    //System.out.println("Valores="+TipoSentencia+"="+NumDispositivo+"="+Habilitar);
			    if(TipoSentencia.equals("Update")){
			    	cSQL = "update GRLModuloDisp" + 
							" set lActivo = ? " +
							"where iCveUniMed = ? "
							+ " and iCveModulo = ?"
							+ " and iCveDispositivo = ?";
			    	/*
			    	System.out.println(cSQL);
			    	System.out.println(Integer.parseInt(Habilitar));
			    	System.out.println(vGRLModulo.getICveUniMed());
			    	System.out.println(vGRLModulo.getICveModulo());
			    	System.out.println(Integer.parseInt(NumDispositivo));
			    	*/
			    	
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setInt(1, Integer.parseInt(Habilitar));
					pstmt.setInt(2, vGRLModulo.getICveUniMed());
					pstmt.setInt(3, vGRLModulo.getICveModulo());
					pstmt.setInt(4, Integer.parseInt(NumDispositivo));
					pstmt.executeUpdate();
					cClave = "";
			    }else{
			    	cSQL = "insert into GRLModuloDisp(iCveUniMed,iCveModulo,iCveProceso,iCveDispositivo,lActivo)" + 
							" values(?,?,1,?,?)" ;
					/*
			    	System.out.println(cSQL);
			    	System.out.println(vGRLModulo.getICveUniMed());
			    	System.out.println(vGRLModulo.getICveModulo());
			    	System.out.println(Integer.parseInt(NumDispositivo));
			    	System.out.println(Integer.parseInt(Habilitar));
			    	*/			    	
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setInt(1, vGRLModulo.getICveUniMed());
					pstmt.setInt(2, vGRLModulo.getICveModulo());
					pstmt.setInt(3, Integer.parseInt(NumDispositivo));
					pstmt.setInt(4, Integer.parseInt(Habilitar));
					pstmt.executeUpdate();
					cClave = "";
			    }
			  }
			  

			
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("update", ex1);
			}
			warn("update", ex);
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
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}	

}
