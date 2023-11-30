package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.TVDinRep;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TipoEnvio DAO
 * </p>
 * <p>
 * Description: DAO Tabla GRLTipoEnvio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonzï¿½lez Paz
 * @version 1.0
 */

public class TDToxMensajeria extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDToxMensajeria() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMensajeria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			//TVTipoEnvio vTipoEnvio;
			TVToxMensajeria vToxMensajeria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveMensajeria," + "cDscMensajeria,"
					+ "SitioOficial" + " from ToxMensajeria order by iCveMensajeria";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vToxMensajeria = new TVToxMensajeria();
				vToxMensajeria.setICveMensajeria(rset.getInt(1));
				vToxMensajeria.setCDscMensajeria(rset.getString(2));
				vToxMensajeria.setSitioOficial(rset.getString(3));
				vcMensajeria.addElement(vToxMensajeria);
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
			return vcMensajeria;
		}
	}
	public Vector FindByLast() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMensajeria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			//TVTipoEnvio vTipoEnvio;
			TVToxMensajeria vToxMensajeria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select MAX(iCveMensajeria) from ToxMensajeria";
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vToxMensajeria = new TVToxMensajeria();
				vToxMensajeria.setICveMensajeria(rset.getInt(1)+1);
				//vToxMensajeria.setCDscMensajeria(rset.getString(2));
				vcMensajeria.addElement(vToxMensajeria);				
			}
			
		} catch (Exception ex) {
			warn("FindByLast", ex);
			throw new DAOException("FindByLast");
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
				warn("FindByLast.close", ex2);
			}
			return vcMensajeria;
		}
	}
	
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMensajeria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			//TVTipoEnvio vTipoEnvio;
			TVToxMensajeria vToxMensajeria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			//int count;
			cSQL = "select " + "iCveMensajeria," + "cDscMensajeria,"
					+ "SitioOficial" + " from ToxMensajeria "+ cWhere;
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vToxMensajeria = new TVToxMensajeria();
				vToxMensajeria.setICveMensajeria(rset.getInt(1));
				vToxMensajeria.setCDscMensajeria(rset.getString(2));
				vToxMensajeria.setSitioOficial(rset.getString(3));
				vcMensajeria.addElement(vToxMensajeria);
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
			return vcMensajeria;
		}
	}
	

	public Vector getMensajeriaCombo(String cWhere) {
		Vector vProceso = new Vector();
		TVDinRep vObjeto;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMensajeria = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			//TVTipoEnvio vTipoEnvio;
			TVToxMensajeria vToxMensajeria;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			//int count;
			cSQL = "select " + "iCveMensajeria," + "cDscMensajeria,"
					+ "SitioOficial" + " from ToxMensajeria "+ cWhere;
			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vObjeto = new TVDinRep();
					vObjeto.put("iClave", rset.getInt(1));
					vObjeto.put("cDescripcion", rset.getString(2));
					vProceso.add(vObjeto);
			}
			
		} catch (Exception e) {
			// System.out.println("Error: " + e);
		}

		return vProceso;
	}
	
	
	
	
	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVToxMensajeria vToxMensajeria = (TVToxMensajeria) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);			
			cSQL = "insert into ToxMensajeria(" + "iCveMensajeria,"
					+ "cDscMensajeria, SitioOficial" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR Cï¿½DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vToxMensajeria.getICveMensajeria());
			pstmt.setString(2, vToxMensajeria.getCDscMensajeria());
			pstmt.setString(3, vToxMensajeria.getSitioOficial());
			pstmt.executeUpdate();
			cClave = "" + vToxMensajeria.getICveMensajeria();
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
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
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
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVToxMensajeria vToxMensajeria = (TVToxMensajeria) obj;
			/*Actualizacion del modulo para registro de bitacora*/
			
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			//SQL = "insert into ToxMensajeria(" + "iCveMensajeria,"
			//		+ "cDscMensajeria" + ")values(?,?,?)";

			cSQL = "update ToxMensajeria set cDscMensajeria= ?, "+ "SitioOficial= ? "
					+ "where iCveMensajeria = ?";
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vToxMensajeria.getCDscMensajeria());
			pstmt.setString(2, vToxMensajeria.getSitioOficial());
			pstmt.setInt(3, vToxMensajeria.getICveMensajeria());
			pstmt.executeUpdate();
			
			
			cClave = "";
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

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		//int iEntidades = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVToxMensajeria vToxMensajeria = (TVToxMensajeria) obj;
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			cSQL = "delete from ToxMensajeria" + " where iCveMensajeria = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vToxMensajeria.getICveMensajeria());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("delete", ex1);
			}
			warn("delete", ex);
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
				warn("delete.closeToxMensajeria", ex2);
			}
			return cClave;
		}
	}
	/**
	 * Agrega la bitacora correspondiente al realizar modificaciones a los campos existentes
	 * @author Ing. Andres Esteban Bernal Muñoz. 01/07/2014.
	 * @param conGral
	 * 					La conexion en general 
	 * @param obj
	 * 			El Objeto que contiene los datos a ingresar.
	 * @return
	 * 			Devuelve el objeto con las modificaciones.
	 * @throws DAOException
	 * 			Excepcion de Conexion
	 */
	public Object getOldToxMensajeria(String cCveMensajeria) throws DAOException {
		//TVToxMensajeria vToxMensajeria = (TVToxMensajeria) obj;		
		//vToxMensajeria = new TVToxMensajeria();}
		//((Vector) Tempo(obj)).get(0)
		int iCveMensajeria=0;
		try{
			iCveMensajeria = Integer.parseInt(cCveMensajeria);
			
		}
		catch(NumberFormatException e){
			System.err.println("TDToxMensajeria: getOldToxMensajeria "+e.toString());
			}
		
		//return (FindByAll(" where iCveMensajeria="+vToxMensajeria.getICveMensajeria())).get(0);
		return (FindByAll(" where iCveMensajeria="+iCveMensajeria).get(0));
		
	}
}