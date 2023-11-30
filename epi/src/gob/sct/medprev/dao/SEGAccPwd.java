/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import gob.sct.medprev.vo.TVMEDSintExamen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

import java.sql.*;
import java.util.*;

/**
 * 
 * @author AG SA
 */
public class SEGAccPwd extends DAOBase {

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public SEGAccPwd() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(int id) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLEXISTE = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int existe = 0;
			int expira = 0;
			int count;

			cSQLEXISTE = "SELECT COUNT(ICVEUSUARIO) FROM SEGACCPWD WHERE ICVEUSUARIO = "
					+ id
					+ " AND ICONTACCESO = (SELECT MAX(ICONTACCESO) FROM SEGACCPWD WHERE ICVEUSUARIO = "
					+ id + ")";
			pstmtEx = conn.prepareStatement(cSQL);
			rsetEx = pstmtEx.executeQuery();
			while (rsetEx.next()) {
				existe = rset.getInt(1);
			}

			if (existe > 0) {
				cSQL = " SELECT TEXPIRA FROM SEGACCPWD WHERE ICVEUSUARIO = "
						+ id;

				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					vMEDSintExamen = new TVMEDSintExamen();
					vMEDSintExamen.setICveProceso(rset.getInt(1));
					vMEDSintExamen.setICveMotivo(rset.getInt(2));
					vMEDSintExamen.setICveServicio(rset.getInt(3));
					vMEDSintExamen.setICveRama(rset.getInt(4));
					vMEDSintExamen.setICveSintoma(rset.getInt(5));
					vMEDSintExamen.setICveMdoTrans(rset.getInt(6));
					vcMEDSintExamen.addElement(vMEDSintExamen);
				}

			} else {
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
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public int Expira(int id) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		Vector vcMEDSintExamen = new Vector();
		int expira = 1;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLEXISTE = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int existe = 0;
			int ano = 0;
			int mes = 0;
			int dia = 0;
			int anoh = 0;
			int mesh = 0;
			int diah = 0;
			int count;

			cSQLEXISTE = "SELECT COUNT(ICVEUSUARIO) FROM SEGACCPWD WHERE LCAMBIOPWD = 1 AND ICVEUSUARIO = "
					+ id;

			System.out.println("Consulta Expira = "+cSQLEXISTE);
			pstmtEx = conn.prepareStatement(cSQLEXISTE);
			rsetEx = pstmtEx.executeQuery();
			while (rsetEx.next()) {
				existe = rsetEx.getInt(1);
				System.out.println("existe"+existe);
			}

			

			if (existe > 0) {
				cSQL = "SELECT {FN YEAR(TEXPIRA)},{FN MONTH(TEXPIRA)},{FN DAY(TEXPIRA)}  FROM SEGACCPWD "
						+ " WHERE LCAMBIOPWD = 1 AND "
						+ " ICONTACCESO = (SELECT MAX(ICONTACCESO) FROM SEGACCPWD  WHERE LCAMBIOPWD = 1 AND ICVEUSUARIO = "
						+ id + ") AND " + " ICVEUSUARIO = " + id;
System.out.println(cSQL);
				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					ano = rset.getInt(1);
					mes = rset.getInt(2);
					dia = rset.getInt(3);
				}
				Calendar hoy = Calendar.getInstance();
				anoh = hoy.get(Calendar.YEAR);
				mesh = hoy.get(Calendar.MONTH);
				diah = hoy.get(Calendar.DATE);

				System.out.println("Consulto Expira");
				mesh = mesh + 1;

				if (anoh > ano) {
					System.out.println("anoh ="+anoh);
					System.out.println("ano ="+ano);
					System.out.println("op-- 1");
					expira = 0;
				} else {
					if (anoh == ano) {
						if (mesh > mes) {
						System.out.println("op- 2");
							expira = 0;
						} else {
							if (mesh == mes) {
								if (diah >= dia) {
									 System.out.println("op- 3");
									expira = 0;
								}
								if (diah < dia) {
									 System.out.println("op- 4");
									expira = 6;
								}
							}
							if (mesh < mes) {
								 System.out.println("op- 5");
								expira = 6;
							}
						}
					} else {
						 System.out.println("op- 6");
						expira = 6;
					}
				}

				if ((diah >= 10 && diah < 15) && mes == mesh && ano == anoh) {
					 System.out.println("op- 7");
					expira = dia - diah;
				}

				if (dia == diah && mes == mesh && ano == anoh) {
				 System.out.println("op- 8");
					expira = 0;
				}

			} else {
				expira = 0;
			}
			System.out.println("Aplicando regla fecha Expira");

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
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return expira;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public int Entero(int id) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		int regresa = 1;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLEXISTE = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLEXISTE = "SELECT MAX(ICVEUSUARIO) FROM SEGACCPWD WHERE ICVEUSUARIO = "
					+ id;

			pstmtEx = conn.prepareStatement(cSQLEXISTE);
			rsetEx = pstmtEx.executeQuery();
			while (rsetEx.next()) {
				regresa = rsetEx.getInt(1);
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
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return regresa;
		}
	}

	public int Max(int id) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		int regresa = 1;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLEXISTE = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLEXISTE = "SELECT MAX(ICONTACCESO) FROM SEGACCPWD WHERE ICVEUSUARIO = "
					+ id;

			pstmtEx = conn.prepareStatement(cSQLEXISTE);
			rsetEx = pstmtEx.executeQuery();
			while (rsetEx.next()) {
				regresa = rsetEx.getInt(1);
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
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return regresa;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public int MaxExp(int id) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		int regresa = 1;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLEXISTE = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLEXISTE = "SELECT MAX(ICONTACCESO) FROM SEGACCEXP WHERE ICVEXPEDIENTE = "
					+ id;

			pstmtEx = conn.prepareStatement(cSQLEXISTE);
			rsetEx = pstmtEx.executeQuery();
			while (rsetEx.next()) {
				regresa = rsetEx.getInt(1);
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
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return regresa;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public int IdUser(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmtU = null;
		ResultSet rsetU = null;
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLEXISTE = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLEXISTE = "SELECT ICVEUSUARIO FROM SEGUSUARIO WHERE CUSUARIO = '"
					+ where + "'";

			// System.out.println(cSQLEXISTE);
			pstmtU = conn.prepareStatement(cSQLEXISTE);
			rsetU = pstmtU.executeQuery();
			while (rsetU.next()) {
				regresa = rsetU.getInt(1);
			}
			// System.out.println(regresa);
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {
				if (rsetU != null) {
					rsetU.close();
				}
				if (pstmtU != null) {
					pstmtU.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return regresa;
		}
	}

	public int insert(Connection conGral, int id) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int expira = 0;
		int contador = 0;
		int regresa = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// Calcular fecha de expiracion.
			String fecha = "";
			int anoh = 0;
			int mesh = 0;
			int diah = 0;
			String dia = "";
			String mes = "";
			Calendar hoy = Calendar.getInstance();

			expira = this.Expira(id);
			contador = this.Max(id);

			// /Condiciones
			/*
			 * if(expira > 0 && expira < 6){ hoy.add(Calendar.MONTH, 3); anoh =
			 * hoy.get(Calendar.YEAR); mesh = hoy.get(Calendar.MONTH); diah =
			 * hoy.get(Calendar.DATE); mes = ""+mesh; dia = ""+diah; if(mesh <
			 * 10) mes = "0"+mesh; if(diah < 10) dia = "0"+diah; fecha =
			 * anoh+"-"+mes+"-15 00:00:00.000000";
			 * 
			 * // System.out.println("fecha = "+fecha); }else{
			 * hoy.add(Calendar.MONTH, 2); anoh = hoy.get(Calendar.YEAR); mesh =
			 * hoy.get(Calendar.MONTH); diah = hoy.get(Calendar.DATE); mes =
			 * ""+mesh; dia = ""+diah; if(mesh < 10) mes = "0"+mesh; if(diah <
			 * 10) dia = "0"+diah; fecha = anoh+"-"+mes+"-15 00:00:00.000000"; }
			 */

			hoy.add(Calendar.MONTH, 2);
			anoh = hoy.get(Calendar.YEAR);
			mesh = hoy.get(Calendar.MONTH);
			diah = hoy.get(Calendar.DATE);
			mes = "" + mesh;
			dia = "" + diah;
			if (mesh < 10) {
				mes = "0" + mesh;
			}
			if (diah < 10) {
				dia = "0" + diah;
			}
			fecha = anoh + "-" + mes + "-15 00:00:00.000000";

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss.SSSSSS");
			java.util.Date parsedDate = dateFormat.parse(fecha);
			java.sql.Timestamp timestamp = new java.sql.Timestamp(
					parsedDate.getTime());
			// fin del calculo de fecha de expiracion

			cSQL = " insert into SEGAccPwd( " + " ICVEUSUARIO, "
					+ " LCAMBIOPWD," + " TCAMBIOPWD," + " TEXPIRA,"
					+ " ICONTACCESO," + " CIPACCESO, " + " CNAVEGADOR "
					+ " ) values (?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			// System.out.println("insert = " + id);
			pstmt.setInt(1, id);
			pstmt.setInt(2, 1);
			pstmt.setTimestamp(3, sqlTimestamp);
			pstmt.setTimestamp(4, timestamp);
			pstmt.setInt(5, contador + 1);
			pstmt.setString(6, "");
			pstmt.setString(7, "");
			pstmt.executeUpdate();
			cClave = "" + expira;
			if (conGral == null) {
				conn.commit();
			}
			regresa = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return regresa;
		}
	}

	///Se comento la ejecucion del insert de este metodo el dia 20 de marzo del 2014
	public int insertValidacionBiometrica(Connection conGral, int id,
			String ip, String Navegador, String versionNav, String macAddress,
			String computerName) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int expira = 0;
		int contador = 0;
		int regresa = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// calculando maximo de contador
			contador = this.Max(id);

			cSQL = " insert into SEGAccPwd( " + " ICVEUSUARIO, " + " LACCESO, "
					+ " TINIACCESO," + " TFINACCESO," + " ICONTACCESO,"
					+ " CIPACCESO, " + " CNAVEGADOR, " + " CVERSIONNAV, "
					+ " CMACADDRESS, " + " CCOMPUTERNAME "
					+ " ) values (?,?,?,?,?,?,?,?,?,?)";

			//pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, id);
			pstmt.setInt(2, 1);
			pstmt.setTimestamp(3, sqlTimestamp);
			pstmt.setTimestamp(4, sqlTimestamp);
			pstmt.setInt(5, contador + 1);
			pstmt.setString(6, ip + "");
			pstmt.setString(7, Navegador + "");
			pstmt.setString(8, versionNav + "");
			pstmt.setString(9, macAddress + "");
			pstmt.setString(10, computerName + "");

			pstmt.executeUpdate();
			cClave = "" + expira;
			if (conGral == null) {
				conn.commit();
			}
			regresa = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return regresa;
		}
	}

	///Se comento la ejecucion del insert de este metodo el dia 20 de marzo del 2014
	public int insertValidacionBiometricaExpediente(int icveExpediente)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int expira = 0;
		int contador = 0;
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// calculando maximo de contador
			contador = this.MaxExp(icveExpediente);

			cSQL = " insert into SEGACCEXP( " + " ICVEXPEDIENTE, "
					+ " LACCESO, " + " LFALLIDO, " + " LACCESOACTIVO, "
					+ " TINIACCESO," + " ICONTACCESO"
					+ " ) values (?,?,?,?,?,?)";

			//pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, icveExpediente);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setTimestamp(5, sqlTimestamp);
			pstmt.setInt(6, contador + 1);

			pstmt.executeUpdate();
			cClave = "" + expira;

			conn.commit();
			regresa = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			try {

				conn.rollback();
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

				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return regresa;
		}
	}

	public boolean existeRegistroLoginExpediente(String iCveExpediente)
			throws DAOException {
		boolean res = false;

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "SELECT * FROM SEGUSUEXP WHERE ICVEEXPEDIENTE = "
					+ iCveExpediente;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				res = true;
			}
		} catch (Exception ex) {
			warn("insert", ex);
			throw new DAOException("insert");
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
				warn("FindAll.close", ex2);
			}
		}
		return res;
	}

	public int insertAcces(Connection conGral, int id, String ip,
			String Navegador, String versionNav, String macAddress,
			String computerName, String CIpRealAcceso, String CPais,
			String CCiudad, String CRegion, double Latitud, double Longitud,
			String ZonaHoraria, String ProvSI, String Org, String Modelo, String NumSerie) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int expira = 0;
		int contador = 0;
		int regresa = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// calculando maximo de contador
			contador = this.Max(id);

			cSQL = " insert into SEGAccPwd( "
					+ " ICVEUSUARIO, "
					+ " LACCESO, "
					+ " TINIACCESO,"
					+ " TFINACCESO,"
					+ " ICONTACCESO,"
					+ " CIPACCESO, "
					+ " CNAVEGADOR, "
					+ " CVERSIONNAV, "
					+ " CMACADDRESS, "
					+ " CCOMPUTERNAME, "
					+ " CIPREALACCESO, CPAIS, CCIUDAD, CREGION, "
					+ " CLATITUDE, DLONGITUD, CZONAHORARIA, CPROVEDORSI, CORGANIZACION, "
					+ " CMODELOEQUIPO, CNUMSERIEEQUIPO "
					+ " ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 

			//System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, id);
			pstmt.setInt(2, 1);
			pstmt.setTimestamp(3, sqlTimestamp);
			pstmt.setTimestamp(4, sqlTimestamp);
			pstmt.setInt(5, contador + 1);
			pstmt.setString(6, ip + "");
			pstmt.setString(7, Navegador + "");
			pstmt.setString(8, versionNav + "");
			pstmt.setString(9, macAddress + "");
			pstmt.setString(10, computerName + "");

			pstmt.setString(11, CIpRealAcceso + "");
			pstmt.setString(12, (CPais + "").replaceAll("\n", ""));
			pstmt.setString(13, (CCiudad + "").replaceAll("\n", ""));
			pstmt.setString(14, CRegion + "");
			pstmt.setDouble(15, Latitud);
			pstmt.setDouble(16, Longitud);
			pstmt.setString(17, ZonaHoraria + "");
			pstmt.setString(18, ProvSI + "");
			pstmt.setString(19, Org + "");
			pstmt.setString(20, Modelo + "");
			pstmt.setString(21, NumSerie + "");

			pstmt.executeUpdate();
			cClave = "" + expira;
			if (conGral == null) {
				conn.commit();
			}
			regresa = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return regresa;
		}
	}

	//Se comento la ejecucion del insert de este metodo el dia 20 de marzo del 2014
	public int insertAccesoFallidoBiometrico(Connection conGral, int id,
			String ip, String Navegador, String versionNav, String macAddress,
			String computerName) throws DAOException {
		// System.out.println("");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int expira = 0;
		int contador = 0;
		int regresa = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// calculando maximo de contador
			contador = this.Max(id);

			cSQL = " insert into SEGAccPwd( " + " ICVEUSUARIO, " + " LACCESO, "
					+ " TINIACCESO," + " TFINACCESO," + " ICONTACCESO,"
					+ " CIPACCESO, " + " CNAVEGADOR, " + " CVERSIONNAV, "
					+ " CMACADDRESS, " + " CCOMPUTERNAME, " + " LFALLIDO "
					+ " ) values (?,?,?,?,?,?,?,?,?,?,1)";

			//pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, id);
			pstmt.setInt(2, 1);
			pstmt.setTimestamp(3, sqlTimestamp);
			pstmt.setTimestamp(4, sqlTimestamp);
			pstmt.setInt(5, contador + 1);
			pstmt.setString(6, ip + "");
			pstmt.setString(7, Navegador + "");
			pstmt.setString(8, versionNav + "");
			pstmt.setString(9, macAddress + "");
			pstmt.setString(10, computerName + "");

			pstmt.executeUpdate();
			cClave = "" + expira;
			if (conGral == null) {
				conn.commit();
			}
			regresa = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return regresa;
		}
	}

	///Se comento la ejecucion del insert de este metodo el dia 20 de marzo del 2014
	public int insertAccesoFallidoBiometricoExp(int icveExpediente)
			throws DAOException {
		// System.out.println("");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int expira = 0;
		int contador = 0;
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			// calculando maximo de contador
			contador = this.MaxExp(icveExpediente);

			cSQL = " insert into SEGACCEXP( " + " ICVEXPEDIENTE, "
					+ " LACCESO, " + " LFALLIDO, " + " LACCESOACTIVO, "
					+ " TINIACCESO," + " ICONTACCESO"
					+ " ) values (?,?,?,?,?,?)";

			//pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, icveExpediente);
			pstmt.setInt(2, 1);
			pstmt.setInt(3, 1);
			pstmt.setInt(4, 0);
			pstmt.setTimestamp(5, sqlTimestamp);
			pstmt.setInt(6, contador + 1);

			pstmt.executeUpdate();
			cClave = "" + expira;

			conn.commit();
			regresa = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			try {

				conn.rollback();
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

				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return regresa;
		}
	}
}
