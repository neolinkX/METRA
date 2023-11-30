package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VehControlSolic DAO
 * </p>
 * <p>
 * Description: DAO VEHControlSolic
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDVEHControlSolic extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	public int siguienteEtapa;
	public int grabaPrimeraEtapa;
	public int grabaUltimaEtapa;

	public TDVEHControlSolic() {
		siguienteEtapa = -1;
		grabaPrimeraEtapa = -1;
		grabaUltimaEtapa = 0;
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVehControlSolic = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHControlSolic vVehControlSolic;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveUniMed," + "iCveSolicitud,"
					+ "iCveEtapaSolic," + "iCveConfControl," + "dValorIni,"
					+ "lLogico," + "cCaracter," + "dValorFin"
					+ " from VehControlSolic order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVehControlSolic = new TVVEHControlSolic();
				vVehControlSolic.setIAnio(rset.getInt(1));
				vVehControlSolic.setICveUniMed(rset.getInt(2));
				vVehControlSolic.setICveSolicitud(rset.getInt(3));
				vVehControlSolic.setICveEtapaSolic(rset.getInt(4));
				vVehControlSolic.setICveConfControl(rset.getInt(5));
				vVehControlSolic.setDValorIni(rset.getFloat(6));
				vVehControlSolic.setLLogico(rset.getInt(7));
				vVehControlSolic.setCCaracter(rset.getString(8));
				vVehControlSolic.setDValorFin(rset.getFloat(9));
				vcVehControlSolic.addElement(vVehControlSolic);
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
			return vcVehControlSolic;
		}
	}

	/**
	 * 
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHControlSolic vVehControlSolic = (TVVEHControlSolic) obj;
			cSQL = "insert into VehControlSolic(" + "iAnio," + "iCveUniMed,"
					+ "iCveSolicitud," + "iCveEtapaSolic," + "iCveConfControl,"
					+ "dValorIni," + "lLogico," + "cCaracter," + "dValorFin"
					+ ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVehControlSolic.getIAnio());
			pstmt.setInt(2, vVehControlSolic.getICveUniMed());
			pstmt.setInt(3, vVehControlSolic.getICveSolicitud());
			pstmt.setInt(4, vVehControlSolic.getICveEtapaSolic());
			pstmt.setInt(5, vVehControlSolic.getICveConfControl());
			pstmt.setFloat(6, vVehControlSolic.getDValorIni());
			pstmt.setInt(7, vVehControlSolic.getLLogico());
			pstmt.setString(8, vVehControlSolic.getCCaracter().toUpperCase()
					.trim());
			pstmt.setFloat(9, vVehControlSolic.getDValorFin());
			pstmt.executeUpdate();
			cClave = "" + vVehControlSolic.getIAnio();
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
					dbConn.closeConnection();
				}
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
			TVVEHControlSolic vVehControlSolic = (TVVEHControlSolic) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VehControlSolic" + " set dValorIni= ?, "
					+ "lLogico= ?, " + "cCaracter= ?, " + "dValorFin= ? "
					+ "where iAnio = ? " + "and iCveUniMed = ?"
					+ "and iCveSolicitud = ?" + "and iCveEtapaSolic = ?"
					+ " and iCveConfControl = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setFloat(1, vVehControlSolic.getDValorIni());
			pstmt.setInt(2, vVehControlSolic.getLLogico());
			pstmt.setString(3, vVehControlSolic.getCCaracter().toUpperCase()
					.trim());
			pstmt.setFloat(4, vVehControlSolic.getDValorFin());
			pstmt.setInt(5, vVehControlSolic.getIAnio());
			pstmt.setInt(6, vVehControlSolic.getICveUniMed());
			pstmt.setInt(7, vVehControlSolic.getICveSolicitud());
			pstmt.setInt(8, vVehControlSolic.getICveEtapaSolic());
			pstmt.setInt(9, vVehControlSolic.getICveConfControl());
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
		int iEntidades = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHControlSolic vVehControlSolic = (TVVEHControlSolic) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VehControlSolic" + " where iAnio = ?"
					+ " and iCveUniMed = ?" + " and iCveSolicitud = ?"
					+ " and iCveEtapaSolic = ?" + " and iCveConfControl = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVehControlSolic.getIAnio());
			pstmt.setInt(2, vVehControlSolic.getICveUniMed());
			pstmt.setInt(3, vVehControlSolic.getICveSolicitud());
			pstmt.setInt(4, vVehControlSolic.getICveEtapaSolic());
			pstmt.setInt(5, vVehControlSolic.getICveConfControl());
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
				warn("delete.closeVehControlSolic", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find Datos Registrados en Etapas Anteriores
	 */
	public Vector FindByAllEtapasAnteriores(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVehControlSolic = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHControlSolic vVehControlSolic;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select VehControlSolic.iCveEtapaSolic, VehEtapaSolic.cDscBreve, VehEtapaxSolic.dtRegistro, "
					+ "VehConfControl.iOrden, VehConfControl.cDscTpoResp, VehControlSolic.dValorIni, "
					+ "VehControlSolic.dValorFin, VehControlSolic.lLogico, VehControlSolic.cCaracter, "
					+ "VehConfControl.iCveTpoResp, VehConfControl.cEtiqueta "
					+ "From VehControlSolic "
					+ "Left Join VehEtapaxSolic on VehControlSolic.iAnio=VehEtapaxSolic.iAnio "
					+ "     and VehControlSolic.iCveUniMed=VehEtapaxSolic.iCveUniMed "
					+ "     and VehControlSolic.iCveSolicitud=VehEtapaxSolic.iCveSolicitud "
					+ "     and VehControlSolic.iCveEtapaSolic=VehEtapaxSolic.iCveEtapaSolic "
					+ "Left Join VehEtapaSolic on VehEtapaxSolic.iCveEtapaSolic=VehEtapaSolic.iCveEtapaSolic "
					+ "Left Join VehConfControl on VehControlSolic.iCveEtapaSolic=VehConfControl.iCveEtapaSolic "
					+ "     and VehControlSolic.iCveConfControl=VehConfControl.iCveConfControl "
					+ sWhere
					+ " order by VehEtapaSolic.iOrden Desc, VehConfControl.iOrden Asc";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVehControlSolic = new TVVEHControlSolic();
				vVehControlSolic.setICveEtapaSolic(rset.getInt(1));
				vVehControlSolic.setCDscEtapaSolic(rset.getString(2));
				vVehControlSolic.setDtRegistro(rset.getDate(3));
				vVehControlSolic.setIOrden(rset.getInt(4));
				vVehControlSolic.setCDscTpoResp(rset.getString(5));
				vVehControlSolic.setDValorIni(rset.getFloat(6));
				vVehControlSolic.setDValorFin(rset.getFloat(7));
				vVehControlSolic.setLLogico(rset.getInt(8));
				vVehControlSolic.setCCaracter(rset.getString(9));
				vVehControlSolic.setICveTpoResp(rset.getInt(10));
				vVehControlSolic.setCEtiqueta(rset.getString(11));
				vcVehControlSolic.addElement(vVehControlSolic);
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
			return vcVehControlSolic;
		}
	}

	/**
	 * Metodo Find Etapas por solicitud
	 */
	public Vector FindByAllEtapasxSol() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVehControlSolic = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHControlSolic vVehControlSolic;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select VehEtapaSolic.iCveEtapaSolic, VehEtapaSolic.cDscBreve "
					+ "from VehEtapaSolic "
					+ "Where lActivo = 1 "
					+ "Order by VehEtapaSolic.iOrden ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVehControlSolic = new TVVEHControlSolic();
				vVehControlSolic.setICveEtapaSolic(rset.getInt(1));
				vVehControlSolic.setCDscEtapaSolic(rset.getString(2));
				vcVehControlSolic.addElement(vVehControlSolic);
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
			return vcVehControlSolic;
		}
	}

	/**
	 * Metodo Find Etapa Siguiente Encuentra la siguiente etapa de la solicitud.
	 */
	public Vector FindEtapaSiguiente(String sWhereqEgxS) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcControl = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHConfControl vVehConfControl;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// ETAPAS GUARDADAS X SOLICITUD
			int ultimaEtapaGuardada = -1;
			boolean tieneEtapasGuardadas = false;
			String qEgxS = "Select VehControlSolic.iCveEtapaSolic, VehEtapaSolic.iOrden "
					+ "from VehControlSolic "
					+ "Join VehEtapaSolic on "
					+ "VehControlSolic.iCveEtapaSolic=VehEtapaSolic.iCveEtapaSolic "
					+ sWhereqEgxS + " Order by VehEtapaSolic.iOrden";
			PreparedStatement psEgxS = conn.prepareStatement(qEgxS);
			ResultSet rsEgxS = psEgxS.executeQuery();
			while (rsEgxS.next()) {
				ultimaEtapaGuardada = rsEgxS.getInt(1);
				tieneEtapasGuardadas = true;
			}
			rsEgxS.close();
			psEgxS.close();

			// ETAPAS QUE SE PUEDEN GRABAR X SOLICITUD
			boolean primeravez = true;
			boolean etapaSiguienteEsLaBuena = false;
			int primeraEtapa = -1;
			int ultimaEtapa = -1;
			String qExS = "Select VehEtapaSolic.iCveEtapaSolic, VehEtapaSolic.cDscBreve, VehEtapaSolic.iOrden "
					+ "from VehEtapaSolic "
					+ "Where lActivo = 1 "
					+ "Order by VehEtapaSolic.iOrden";

			PreparedStatement psExS = conn.prepareStatement(qExS);
			ResultSet rsExS = psExS.executeQuery();
			while (rsExS.next()) {
				if (primeravez) {
					primeraEtapa = rsExS.getInt(1);
					primeravez = false;
				}
				if (etapaSiguienteEsLaBuena) {
					siguienteEtapa = rsExS.getInt(1);
					etapaSiguienteEsLaBuena = false;
				}
				if (ultimaEtapaGuardada == rsExS.getInt(1)) {
					etapaSiguienteEsLaBuena = true;
				}
				ultimaEtapa = rsExS.getInt(1);
			}
			rsExS.close();
			psExS.close();

			// NO TIENE ETAPAS GUARDADAS
			if (!tieneEtapasGuardadas) {
				siguienteEtapa = primeraEtapa;
				grabaPrimeraEtapa = 1;
			}
			// YA GRABO LA ULTIMA
			if (tieneEtapasGuardadas && etapaSiguienteEsLaBuena)
				siguienteEtapa = -1;

			// ULTIMA ETAPA
			if (siguienteEtapa == ultimaEtapa)
				grabaUltimaEtapa = 1;

			// CONTROLES DE LA ETAPA
			cSQL = "Select VehEtapaSolic.iCveEtapaSolic, VehConfControl.iCveConfControl, "
					+ "VehConfControl.cDscTpoResp, VehConfControl.cEtiqueta, VehConfControl.iCveTpoResp, "
					+ "VehConfControl.lObligatorio, VehConfControl.lActivo, VehConfControl.iOrden "
					+ "from VehEtapaSolic "
					+ "Join VehConfControl on VehEtapaSolic.iCveEtapaSolic=VehConfControl.iCveEtapaSolic "
					+ "Where VehEtapaSolic.iCveEtapaSolic="
					+ siguienteEtapa
					+ " Order by VehConfControl.iOrden";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVehConfControl = new TVVEHConfControl();
				vVehConfControl.setICveEtapaSolic(rset.getInt(1));
				vVehConfControl.setICveConfControl(rset.getInt(2));
				vVehConfControl.setCDscTpoResp(rset.getString(3));
				vVehConfControl.setCEtiqueta(rset.getString(4));
				vVehConfControl.setICveTpoResp(rset.getInt(5));
				vVehConfControl.setLObligatorio(rset.getInt(6));
				vVehConfControl.setLActivo(rset.getInt(7));
				vVehConfControl.setIOrden(rset.getInt(8));
				vcControl.addElement(vVehConfControl);
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
			return vcControl;
		}
	}
}