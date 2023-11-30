package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.util.TFechas;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de Envio DAO
 * </p>
 * <p>
 * Description: DAO TOXEnvio
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDEnvio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEnvio() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEnvio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEnvio vEnvio;
			TVEnvio vEnvioW = (TVEnvio) Obj;
			int count;
			cSQL = "select " + "iAnio," + "TOXEnvio.iCveUniMed," + "iCveEnvio,"
					+ "iCveUsuEnvia," + "dtEnvio," + "TOXEnvio.iCveTipoEnvio,"
					+ "dtRecepcion," + "iCveUsuRec," + "cObsEnvio,"
					+ "cObsRecep, " + "cDscTipoEnvio,"
					+ "GRLUniMed.cDscUniMed as cDscLaboratorio "
					+ " from TOXEnvio ";
			cSQL = cSQL + " inner join GRLTipoEnvio ON "
					+ " GRLTipoEnvio.iCveTipoEnvio = TOXEnvio.iCveTipoEnvio ";
			cSQL = cSQL + " inner join GRLUniMed ON "
					+ " GRLUniMed.iCveUniMed = TOXEnvio.iCveLaboratorio ";

			if (vEnvioW.getICveEnvio() > 0) {
				cSQL = cSQL
						+ " where  "
						+ " iAnio = ? and TOXEnvio.iCveUniMed = ? and iCveEnvio = ? ";
			}
			cSQL = cSQL + " order by iAnio, iCveUniMed, iCveEnvio";
			pstmt = conn.prepareStatement(cSQL);
			if (vEnvioW.getICveEnvio() > 0) {
				pstmt.setInt(1, vEnvioW.getIAnio());
				pstmt.setInt(2, vEnvioW.getICveUniMed());
				pstmt.setInt(3, vEnvioW.getICveEnvio());
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEnvio = new TVEnvio();
				vEnvio.setIAnio(rset.getInt(1));
				vEnvio.setICveUniMed(rset.getInt(2));
				vEnvio.setICveEnvio(rset.getInt(3));
				vEnvio.setICveUsuEnvia(rset.getInt(4));
				vEnvio.setDtEnvio(rset.getDate(5));
				vEnvio.setICveTipoEnvio(rset.getInt(6));
				vEnvio.setDtRecepcion(rset.getDate(7));
				vEnvio.setICveUsuRec(rset.getInt(8));
				vEnvio.setCObsEnvio(rset.getString(9));
				vEnvio.setCObsRecep(rset.getString(10));
				vEnvio.setCDscTipoEnvio(rset.getString(11));
				vEnvio.setCDscLaboratorio(rset.getString(12));
				vcEnvio.addElement(vEnvio);
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
			return vcEnvio;
		}
	}

	/**
	 * Metodo Find By Envio
	 */
	public Vector FindByEnvio(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtUpdate = null;
		ResultSet rset = null;
		Vector vcEnvio = new Vector();
		TFechas Fecha = new TFechas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLUpdate = "";
			TVEnvio vEnvio;
			TVEnvio vEnvioW = (TVEnvio) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQLUpdate = "update TOXEnvio"
					+ " set dtEnvio = ? ,"
					+ " iCveUsuEnvia = ?, "
					+ " cObsEnvio = CONCAT(cObsEnvio, ' ' || CHAR ({FN CURTIME() }, USA) )  "
					+ "where iAnio = ? " + "and iCveUniMed = ? "
					+ "and iCveEnvio = ?";
			pstmtUpdate = conn.prepareStatement(cSQLUpdate);
			pstmtUpdate.setDate(1, vEnvioW.getDtEnvio());
			pstmtUpdate.setInt(2, vEnvioW.getICveUsuEnvia());
			pstmtUpdate.setInt(3, vEnvioW.getIAnio());
			pstmtUpdate.setInt(4, vEnvioW.getICveUniMed());
			pstmtUpdate.setInt(5, vEnvioW.getICveEnvio());

			pstmtUpdate.executeUpdate();

			cSQL = "select " + "iAnio," + "iCveUniMed," + "iCveEnvio,"
					+ "iCveUsuEnvia," + "dtEnvio," + "TOXEnvio.iCveTipoEnvio,"
					+ "dtRecepcion," + "iCveUsuRec," + "cObsEnvio,"
					+ "cObsRecep, " + "cDscTipoEnvio" + " from TOXEnvio ";
			cSQL = cSQL + " inner join GRLTipoEnvio ON "
					+ " GRLTipoEnvio.iCveTipoEnvio = TOXEnvio.iCveTipoEnvio ";

			if (vEnvioW.getICveEnvio() > 0) {
				cSQL = cSQL + " where  "
						+ " iAnio = ? and iCveUniMed = ? and iCveEnvio = ? ";
			}

			cSQL = cSQL + " order by iAnio, iCveUniMed, iCveEnvio";

			pstmt = conn.prepareStatement(cSQL);
			if (vEnvioW.getICveEnvio() > 0) {
				pstmt.setInt(1, vEnvioW.getIAnio());
				pstmt.setInt(2, vEnvioW.getICveUniMed());
				pstmt.setInt(3, vEnvioW.getICveEnvio());
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEnvio = new TVEnvio();
				vEnvio.setIAnio(rset.getInt(1));
				vEnvio.setICveUniMed(rset.getInt(2));
				vEnvio.setICveEnvio(rset.getInt(3));
				vEnvio.setICveUsuEnvia(rset.getInt(4));
				vEnvio.setDtEnvio(rset.getDate(5));
				vEnvio.setICveTipoEnvio(rset.getInt(6));
				vEnvio.setDtRecepcion(rset.getDate(7));
				vEnvio.setICveUsuRec(rset.getInt(8));
				vEnvio.setCObsEnvio(rset.getString(9));
				vEnvio.setCObsRecep(rset.getString(10));
				vEnvio.setCDscTipoEnvio(rset.getString(11));
				vcEnvio.addElement(vEnvio);
			}
		} catch (Exception ex) {
			warn("FindByEnvio", ex);
			throw new DAOException("FindByEnvio");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();

				}
				if (pstmtUpdate != null) {
					pstmtUpdate.close();

				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByEnvio.close", ex2);
			}
			return vcEnvio;
		}
	}

	/**
	 * Metodo Find By Nuevo
	 */
	public Vector FindByNuevo(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEnvio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLUpdate = "";
			TVEnvio vEnvio;
			TVEnvio vEnvioW = (TVEnvio) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select iAnio,TOXEnvio.iCveUniMed,iCveEnvio, ";
			cSQL = cSQL + "iCveUsuEnvia," + "dtEnvio,"
					+ "TOXEnvio.iCveTipoEnvio," + "dtRecepcion,"
					+ "iCveUsuRec," + "cObsEnvio," + "cObsRecep, "
					+ "cDscTipoEnvio,"
					+ "GRLUniMed.cDscUniMed as cDscLaboratorio "
					+ " from TOXEnvio ";

			cSQL = cSQL + " inner join GRLTipoEnvio ON "
					+ " GRLTipoEnvio.iCveTipoEnvio = TOXEnvio.iCveTipoEnvio ";

			cSQL = cSQL + " inner join GRLUniMed ON "
					+ " GRLUniMed.iCveUniMed = TOXEnvio.iCveLaboratorio ";

			cSQL = cSQL
					+ "where  TOXEnvio.iCveEnvio = (select max(TOXEnvio.iCveEnvio) from ToxEnvio "
					+ "where TOXEnvio.iCveUniMed = ? and iAnio = ? and dtEnvio is null) ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEnvioW.getICveUniMed());
			pstmt.setInt(2, vEnvioW.getIAnio());
			rset = pstmt.executeQuery();
			while (rset.next()) {

				vEnvio = new TVEnvio();
				vEnvio.setIAnio(rset.getInt(1));
				vEnvio.setICveUniMed(rset.getInt(2));
				vEnvio.setICveEnvio(rset.getInt(3));
				vEnvio.setICveUsuEnvia(rset.getInt(4));
				vEnvio.setDtEnvio(rset.getDate(5));
				vEnvio.setICveTipoEnvio(rset.getInt(6));
				vEnvio.setDtRecepcion(rset.getDate(7));
				vEnvio.setICveUsuRec(rset.getInt(8));
				vEnvio.setCObsEnvio(rset.getString(9));
				vEnvio.setCObsRecep(rset.getString(10));
				vEnvio.setCDscTipoEnvio(rset.getString(11));
				vEnvio.setCDscLaboratorio(rset.getString(12));
				vcEnvio.addElement(vEnvio);
			}
		} catch (Exception ex) {
			warn("FindByNuevo", ex);
			throw new DAOException("FindByNuevo");
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
				warn("FindByNuevo.close", ex2);
			}
			return vcEnvio;
		}
	}

	/**
	 * Metodo Find By dtEnvio
	 */
	public Vector FindBydtEnvio(int iAnio, int iUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEnvio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEnvio vEnvio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveUniMed," + "iCveEnvio,"
					+ "iCveUsuEnvia," + "dtEnvio," + "iCveTipoEnvio,"
					+ "dtRecepcion," + "iCveUsuRec," + "cObsEnvio,"
					+ "cObsRecep" + " from TOXEnvio where dtEnvio is null "
					+ " and iAnio = ? and iCveUniMed = ? "
					+ "order by iAnio, iCveUniMed, iCveEnvio Desc";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iUniMed);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEnvio = new TVEnvio();
				vEnvio.setIAnio(rset.getInt(1));
				vEnvio.setICveUniMed(rset.getInt(2));
				vEnvio.setICveEnvio(rset.getInt(3));
				vEnvio.setICveUsuEnvia(rset.getInt(4));
				vEnvio.setDtEnvio(rset.getDate(5));
				vEnvio.setICveTipoEnvio(rset.getInt(6));
				vEnvio.setDtRecepcion(rset.getDate(7));
				vEnvio.setICveUsuRec(rset.getInt(8));
				vEnvio.setCObsEnvio(rset.getString(9));
				vEnvio.setCObsRecep(rset.getString(10));
				vcEnvio.addElement(vEnvio);
			}
		} catch (Exception ex) {
			warn("FindBydtEnvio", ex);
			throw new DAOException("FindBydtEnvio");
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
				warn("FindBydtEnvio.close", ex2);
			}
			return vcEnvio;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj, Object obMuestraApi,
			Object obMuestraCancel, int iEnvio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		PreparedStatement pstmtMuestra = null;
		PreparedStatement pstmtMuestraDel = null;
		ResultSet rset = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQLMax = "";
			String cRes = "";
			int iConsecutivo = 0;
			TVEnvio vEnvio = (TVEnvio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			Vector vcMuestraApi = (Vector) obMuestraApi;
			Vector vcMuestraCancel = (Vector) obMuestraCancel;

			if (iEnvio <= 0) {
				cSQLMax = "select max(iCveEnvio) from TOXEnvio ";
				cSQLMax = cSQLMax + "where iAnio = ? and iCveUniMed = ?";
				pstmtMax = conn.prepareStatement(cSQLMax);
				pstmtMax.setInt(1, vEnvio.getIAnio());
				pstmtMax.setInt(2, vEnvio.getICveUniMed());
				rset = pstmtMax.executeQuery();
				while (rset.next()) {
					iConsecutivo = rset.getInt(1);
				}
				vEnvio.setICveEnvio(iConsecutivo + 1);

				cSQL = "insert into TOXEnvio(" + "iAnio," + "iCveUniMed,"
						+ "iCveEnvio," + "iCveTipoEnvio," + "cObsEnvio,"
						+ "iCveLaboratorio" + ")values(?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEnvio.getIAnio());
				pstmt.setInt(2, vEnvio.getICveUniMed());
				pstmt.setInt(3, vEnvio.getICveEnvio());
				pstmt.setInt(4, vEnvio.getICveTipoEnvio());
				pstmt.setString(5, vEnvio.getCObsEnvio());
				pstmt.setInt(6, vEnvio.getICveLaboratorio());
				pstmt.executeUpdate();
			} else {
				vEnvio.setICveEnvio(iEnvio);

			}
			String cSQLMuestraApi = "";
			String cSQLMuestraDel = "";

			int i;
			if (vcMuestraApi != null) {
				if (vcMuestraApi.isEmpty() == false) {

					cSQLMuestraApi = "update TOXMuestra"
							+ " set iCveEnvio = ? " + "where iAnio = ? "
							+ "and iCveUniMed = ? " + "and iCveMuestra = ?";

					pstmtMuestra = conn.prepareStatement(cSQLMuestraApi);

					for (i = 0; i < vcMuestraApi.size(); i++) {

						TVMuestra vMuestra = (TVMuestra) vcMuestraApi.get(i);
						pstmtMuestra.setInt(1, vEnvio.getICveEnvio());
						pstmtMuestra.setInt(2, vMuestra.getIAnio());
						pstmtMuestra.setInt(3, vMuestra.getICveUniMed());
						pstmtMuestra.setInt(4, vMuestra.getICveMuestra());
						pstmtMuestra.executeUpdate();

					}
				}
			}

			if (vcMuestraCancel != null) {
				if (vcMuestraCancel.isEmpty() == false) {
					cSQLMuestraDel = "update TOXMuestra"
							+ " set iCveEnvio = null " + "where iAnio = ? "
							+ "and iCveUniMed = ? " + "and iCveMuestra = ?";

					pstmtMuestraDel = conn.prepareStatement(cSQLMuestraDel);

					for (i = 0; i < vcMuestraCancel.size(); i++) {

						TVMuestra vMuestra = (TVMuestra) vcMuestraCancel.get(i);
						pstmtMuestraDel.setInt(1, vMuestra.getIAnio());
						pstmtMuestraDel.setInt(2, vMuestra.getICveUniMed());
						pstmtMuestraDel.setInt(3, vMuestra.getICveMuestra());
						pstmtMuestraDel.executeUpdate();

					}
				}
			}

			cClave = "" + vEnvio.getIAnio();
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
				if (pstmtMuestra != null) {
					pstmtMuestra.close();
				}

				if (pstmtMuestraDel != null) {
					pstmtMuestraDel.close();
				}

				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (rset != null) {
					rset.close();
				}

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
	 * Metodo Insert
	 */
	public Object insertToxEnvio(Connection conGral, Object obj, Object obMuestraApi,
			Object obMuestraCancel, int iEnvio, Object obMuestraHielera, 
			Object obMuestraMensajeria) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		PreparedStatement pstmtMuestra = null;
		PreparedStatement pstmtMuestraHielera = null;
		PreparedStatement pstmtMuestraDel = null;
		PreparedStatement pstmtMuestraDel2 = null;
		ResultSet rset = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQLMax = "";
			String cRes = "";
			int iConsecutivo = 0;
			TVEnvio vEnvio = (TVEnvio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			Vector vcMuestraApi = (Vector) obMuestraApi;
			Vector vcMuestraCancel = (Vector) obMuestraCancel;
			Vector vcMuestraHielera = (Vector) obMuestraHielera;
			Vector vcMuestraMensajeria = (Vector) obMuestraMensajeria;

			if (iEnvio <= 0) {
				cSQLMax = "select max(iCveEnvio) from TOXEnvio ";
				cSQLMax = cSQLMax + "where iAnio = ? and iCveUniMed = ?";
				pstmtMax = conn.prepareStatement(cSQLMax);
				pstmtMax.setInt(1, vEnvio.getIAnio());
				pstmtMax.setInt(2, vEnvio.getICveUniMed());
				rset = pstmtMax.executeQuery();
				while (rset.next()) {
					iConsecutivo = rset.getInt(1);
				}
				vEnvio.setICveEnvio(iConsecutivo + 1);

				cSQL = "insert into TOXEnvio(" + "iAnio," + "iCveUniMed,"
						+ "iCveEnvio," + "iCveTipoEnvio," + "cObsEnvio,"
						+ "iCveLaboratorio" + ")values(?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEnvio.getIAnio());
				pstmt.setInt(2, vEnvio.getICveUniMed());
				pstmt.setInt(3, vEnvio.getICveEnvio());
				pstmt.setInt(4, vEnvio.getICveTipoEnvio());
				pstmt.setString(5, vEnvio.getCObsEnvio());
				pstmt.setInt(6, vEnvio.getICveLaboratorio());
				pstmt.executeUpdate();
				
			} else {
				vEnvio.setICveEnvio(iEnvio);

			}
			String cSQLMuestraApi = "";
			String cSQLMuestraHielera = "";
			String cSQLMuestraDel = "";
			String cSQLMuestraDel2 = "";

			int i;
			if (vcMuestraApi != null) {
				if (vcMuestraApi.isEmpty() == false) {

					cSQLMuestraApi = "update TOXMuestra"
							+ " set iCveEnvio = ? " + "where iAnio = ? "
							+ "and iCveUniMed = ? " + "and iCveMuestra = ?";

					pstmtMuestra = conn.prepareStatement(cSQLMuestraApi);

					for (i = 0; i < vcMuestraApi.size(); i++) {

						TVMuestra vMuestra = (TVMuestra) vcMuestraApi.get(i);
						pstmtMuestra.setInt(1, vEnvio.getICveEnvio());
						pstmtMuestra.setInt(2, vMuestra.getIAnio());
						pstmtMuestra.setInt(3, vMuestra.getICveUniMed());
						pstmtMuestra.setInt(4, vMuestra.getICveMuestra());
						pstmtMuestra.executeUpdate();

					}
					
					cSQLMuestraHielera = "insert into TOXEnvioMuestra(" + "iAnio," + "iCveUniMed,"
						+ "iCveEnvio," + "iCveMuestra," + "iCveHielera,"
						+ "iCveMensajeria" + ")values(?,?,?,?,?,?)";

					pstmtMuestraHielera = conn.prepareStatement(cSQLMuestraHielera);

					for (i = 0; i < vcMuestraApi.size(); i++) {
						//String Hielera = vcMuestraHielera.elementAt(i)+"";
						String Hielera = "0";
						String Mensajeria = vcMuestraMensajeria.elementAt(i)+"";
						TVMuestra vMuestra = (TVMuestra) vcMuestraApi.get(i);
						pstmtMuestraHielera.setInt(1, vMuestra.getIAnio());
						pstmtMuestraHielera.setInt(2, vMuestra.getICveUniMed());
						pstmtMuestraHielera.setInt(3, vEnvio.getICveEnvio());
						pstmtMuestraHielera.setInt(4, vMuestra.getICveMuestra());
						pstmtMuestraHielera.setInt(5, Integer.parseInt(Hielera));
						pstmtMuestraHielera.setInt(6, Integer.parseInt(Mensajeria));
						
						System.out.println(vMuestra.getIAnio());
						System.out.println(vMuestra.getICveUniMed());
						System.out.println(vEnvio.getICveEnvio());
						System.out.println(vMuestra.getICveMuestra());
						System.out.println(Integer.parseInt(Hielera));
						System.out.println(Integer.parseInt(Mensajeria));
						
						pstmtMuestraHielera.executeUpdate();
					}
					
					
				}
			}

			if (vcMuestraCancel != null) {
				if (vcMuestraCancel.isEmpty() == false) {
					cSQLMuestraDel = "update TOXMuestra"
							+ " set iCveEnvio = null " + "where iAnio = ? "
							+ "and iCveUniMed = ? " + "and iCveMuestra = ?";

					pstmtMuestraDel = conn.prepareStatement(cSQLMuestraDel);

					for (i = 0; i < vcMuestraCancel.size(); i++) {

						TVMuestra vMuestra = (TVMuestra) vcMuestraCancel.get(i);
						pstmtMuestraDel.setInt(1, vMuestra.getIAnio());
						pstmtMuestraDel.setInt(2, vMuestra.getICveUniMed());
						pstmtMuestraDel.setInt(3, vMuestra.getICveMuestra());
						pstmtMuestraDel.executeUpdate();

					}
					
					cSQLMuestraDel2 = "Delete TOXEnvioMuestra "
							+ "where iAnio = ? "
							+ "and iCveUniMed = ? " + "and iCveMuestra = ?";

					pstmtMuestraDel2 = conn.prepareStatement(cSQLMuestraDel2);

					for (i = 0; i < vcMuestraCancel.size(); i++) {

						TVMuestra vMuestra = (TVMuestra) vcMuestraCancel.get(i);
						pstmtMuestraDel2.setInt(1, vMuestra.getIAnio());
						pstmtMuestraDel2.setInt(2, vMuestra.getICveUniMed());
						pstmtMuestraDel2.setInt(3, vMuestra.getICveMuestra());
						pstmtMuestraDel2.executeUpdate();

					}
					
				}
			}

			cClave = "" + vEnvio.getIAnio();
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
				if (pstmtMuestra != null) {
					pstmtMuestra.close();
				}

				if (pstmtMuestraDel != null) {
					pstmtMuestraDel.close();
				}
				
				if (pstmtMuestraDel2 != null) {
					pstmtMuestraDel2.close();
				}
				
				if (pstmtMuestraHielera != null) {
					pstmtMuestraHielera.close();
				}

				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (rset != null) {
					rset.close();
				}

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
			TVEnvio vEnvio = (TVEnvio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXEnvio" + " set iCveLaboratorio= ?, "
					+ "iCveUsuEnvia= ?, " + "dtEnvio= ?, "
					+ "iCveTipoEnvio= ?, " + "dtRecepcion= ?, "
					+ "iCveUsuRec= ?, " + "cObsEnvio= ?, " + "cObsRecep= ? "
					+ "where iAnio = ? " + "and iCveUniMed = ?"
					+ " and iCveEnvio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEnvio.getICveLaboratorio());
			pstmt.setInt(2, vEnvio.getICveUsuEnvia());
			pstmt.setDate(3, vEnvio.getDtEnvio());
			pstmt.setInt(4, vEnvio.getICveTipoEnvio());
			pstmt.setDate(5, vEnvio.getDtRecepcion());
			pstmt.setInt(6, vEnvio.getICveUsuRec());
			pstmt.setString(7, vEnvio.getCObsEnvio());
			pstmt.setString(8, vEnvio.getCObsRecep());
			pstmt.setInt(9, vEnvio.getIAnio());
			pstmt.setInt(10, vEnvio.getICveUniMed());
			pstmt.setInt(11, vEnvio.getICveEnvio());
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
			TVEnvio vEnvio = (TVEnvio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXEnvio" + " where iAnio = ?"
					+ " and iCveUniMed = ?" + " and iCveEnvio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEnvio.getIAnio());
			pstmt.setInt(2, vEnvio.getICveUniMed());
			pstmt.setInt(3, vEnvio.getICveEnvio());
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
				warn("delete.closeEnvio", ex2);
			}
			return cClave;
		}
	}
}
