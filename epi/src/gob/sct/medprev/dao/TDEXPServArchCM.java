package gob.sct.medprev.dao;

import java.io.File;
import java.sql.*;
import java.util.*;

import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import com.micper.util.TFechas;
import com.micper.seguridad.vo.TVDinRep02;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Data Acces Object de LICFFH DAO </p>
 * 
 * @author AG SA
 */

public class TDEXPServArchCM extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEXPServArchCM() {
	}

	/**
	 * Metodo InodoctoDeServicios, regresa un vector con los documentos que se
	 * deben subir de la NAS al content;
	 * 
	 * @Autor: AG SA
	 */
	public Vector InodoctoDeServicios(String inicio, String fin)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String regreso = "null";
		Vector datos = new Vector();
		try {
			String SQL = "Select LIntIcveDocumen from EXPServArchCM "
					+ "where tsgenerado>='" + inicio
					+ " 00:00:00.000000' and tsgenerado<='" + fin
					+ " 23:59:59.000000' ";
			System.out.println(SQL);
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = SQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				datos.addElement(rset.getString(1));
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
			return datos;
		}
	}

	/**
	 * Metodo validaRegistroDeDocumento, verifica que el documento que se esta
	 * intentando subir haya sido registrado en la base de datos * @Autor: AG
	 * SA
	 */
	public boolean validaRegistroDeDocumento(String icvedocumento)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String regreso = "null";
		boolean existe = false;
		try {
			String SQL = "Select LIntIcveDocumen from EXPServArchCM "
					+ "where LIntIcveDocumen = " + icvedocumento;
			System.out.println(SQL);
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = SQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				existe = true;
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
			return existe;
		}
	}
	
	/**
	 * Metodo EliminaDocsFallidosNAS, elimina documentos fallidos Iguales a 0 bytes
	 * * @Autor: AG SA 2016-06-03
	 */
	public boolean EliminaDocsFallidosNAS(String exp)
			throws DAOException {
		//System.out.println("Clase EliminaDocsFallidosNAS");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		String regreso = "null";
		boolean existe = false;
		int minimo = 0;
		int maximo = 0;
		try {
			String cSQL = "SELECT MIN(LINTICVEDOCUMEN) "
					+ "FROM EXPSERVARCHCM  "
					+ "WHERE  "
					+ "TSGENERADO >= CURRENT DATE";
			/*String cSQL = "SELECT MAX(LINTICVEDOCUMEN) "
					+ "FROM EXPSERVARCHCM  "
					+ "WHERE  "
					+ "TSGENERADO >= CURRENT DATE  -1 DAYS AND "
					+ "TSGENERADO <= CURRENT DATE";*/
			System.out.println(cSQL);
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				minimo = rset.getInt(1);
			}
			//System.out.println("MINIMO = "+minimo);
			String cSQL2 = "SELECT MAX(LINTICVEDOCUMEN) "
					+ "FROM EXPSERVARCHCM  "
					+ "WHERE  "
					+ "TSGENERADO >= CURRENT DATE ";
			//System.out.println(cSQL2);
			pstmt2 = conn.prepareStatement(cSQL2);
			rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				maximo = rset2.getInt(1);
			}
			if(minimo >0 && maximo > 0){
				for(int i = minimo; i <= maximo; i++){
					String sFichero = ""+ VParametros.getPropEspecifica("RutaNASSM").toString() + ""+ i + ".jpg";
					File fichero = new File(sFichero);
					String sFichero2 = ""+ VParametros.getPropEspecifica("RutaNASSM").toString() + ""+ i + "CE.jpg";
					File fichero2 = new File(sFichero2);
					//System.out.println(sFichero);
					//System.out.println(sFichero2);
					if (fichero.exists()) {
						//System.out.println("1-Existe");
						if (fichero.length()<10) {
							fichero.delete();
							//System.out.println("1-Menor a 10");
						}
					}
					if (fichero2.exists()) {
						//System.out.println("2-Existe");
						if (fichero2.length()<10) {
							fichero2.delete();	
							//System.out.println("2-Menor a 10");
						}
					}
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
			} catch (Exception ex2) {
				warn("RegresaInt", ex2);
			}
			return existe;
		}
	}

}
