package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXExamenCualita DAO
 * </p>
 * <p>
 * Description: DAO para la tabla TOXExamenCualita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class TDTOXExamenCualita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXExamenCualita() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamenCualita vTOXExamenCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveLoteCualita,"
					+ "iCveExamCualita," + "iCveLaboratorio," + "dtEntrega,"
					+ "dtProcesado," + "dtAutorizado," + "lAutorizado,"
					+ "lReanalisis," + "iCveEquipo," + "iCveUsuPrepara,"
					+ "iCveUsuExam," + "iCveUsuAutoriza"
					+ " from TOXExamenCualita ";
			if (cFiltro.compareToIgnoreCase("") != 0)
				cSQL = cSQL + cFiltro;
			if (cOrden.compareToIgnoreCase("") != 0)
				cSQL = cSQL + cOrden;
			else
				cSQL = cSQL + " order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamenCualita = new TVTOXExamenCualita();
				vTOXExamenCualita.setIAnio(rset.getInt(1));
				vTOXExamenCualita.setICveLoteCualita(rset.getInt(2));
				vTOXExamenCualita.setICveExamCualita(rset.getInt(3));
				vTOXExamenCualita.setICveLaboratorio(rset.getInt(4));
				vTOXExamenCualita.setDtEntrega(rset.getDate(5));
				vTOXExamenCualita.setDtProcesado(rset.getDate(6));
				vTOXExamenCualita.setDtAutorizado(rset.getDate(7));
				vTOXExamenCualita.setLAutorizado(rset.getInt(8));
				vTOXExamenCualita.setLReanalisis(rset.getInt(9));
				vTOXExamenCualita.setICveEquipo(rset.getInt(10));
				vTOXExamenCualita.setICveUsuPrepara(rset.getInt(11));
				vTOXExamenCualita.setICveUsuExam(rset.getInt(12));
				vTOXExamenCualita.setICveUsuAutoriza(rset.getInt(13));
				vcTOXExamenCualita.addElement(vTOXExamenCualita);
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
			return vcTOXExamenCualita;
		}
	}

	/**
	 * Metodo Find By AllRep Utilizado en el reporte del programa 070303040, ya
	 * que requiere de join con TOXExamAnalisis y orden por carrusel y posicion
	 */
	public Vector RepLote(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXExamenCualita vTOXExamenCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append("select A.iCveAnalisis,")
					.append("       C.iAnio, C.iCveLaboratorio, C.iCveLoteCualita, C.iCveExamCualita, C.dtEntrega, ")
					.append("       E.cDscEquipo, E.cNumSerie, E.cModelo ")
					.append(" from TOXExamAnalisis A ")
					.append(" inner join TOXExamenCualita C on (C.iAnio = A.iAnio ")
					.append("                              and  C.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                              and  C.iCveLoteCualita = A.iCveLoteCualita ")
					.append("                              and  C.iCveExamCualita = A.iCveExamCualita) ")
					.append(" inner join EQMEquipo E on E.iCveEquipo = C.iCveEquipo ");
			if (cFiltro.compareToIgnoreCase("") != 0)
				cSQL.append(cFiltro);
			cSQL.append(" order by A.iAnio, A.iCveLaboratorio, A.iCveLoteCualita, A.iCveExamCualita, A.iCveAnalisis");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			int i = 0;
			vTOXExamenCualita = new TVTOXExamenCualita();
			while (rset.next()) {
				if (i == 0) {
					vTOXExamenCualita = new TVTOXExamenCualita();
					vTOXExamenCualita.setICveAnalisis(rset.getInt(1));
					vTOXExamenCualita.setIAnio(rset.getInt(2));
					vTOXExamenCualita.setICveLaboratorio(rset.getInt(3));
					vTOXExamenCualita.setICveLoteCualita(rset.getInt(4));
					vTOXExamenCualita.setICveExamCualita(rset.getInt(5));
					vTOXExamenCualita.setDtEntrega(rset.getDate(6));
					vTOXExamenCualita.VEquipo = new TVEQMEquipo();
					vTOXExamenCualita.VEquipo.setCDscEquipo(rset.getString(7));
					vTOXExamenCualita.VEquipo.setCNumSerie(rset.getString(8));
					vTOXExamenCualita.VEquipo.setCModelo(rset.getString(9));
					vResultado.addElement(vTOXExamenCualita);
					vTOXExamenCualita = new TVTOXExamenCualita();
				}
				i++;
				vTOXExamenCualita.setICveAnalisis(rset.getInt(1));
				vTOXExamenCualita.setIAnio(rset.getInt(2));
				vTOXExamenCualita.setIPosicion(i);
			}
			if (i > 0)
				vResultado.addElement(vTOXExamenCualita);
		} catch (Exception ex) {
			warn("FindByAllRep", ex);
			throw new DAOException("FindByAllRep");
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
				warn("FindByAllRep.close", ex2);
			}
			return vResultado;
		}
	} // /

	/**
	 * Metodo Find By AllRep2 Utilizado en el reporte del programa 0703303060
	 * que incluye el join con TOExamResult
	 */
	public Vector FindByAllRep2(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			int iAnalisis = 0;
			boolean lPendientes = false;
			;
			TVTOXExamenCualita vTOXExamenCualita;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "TOXExamenCualita.ianio, "
					+ "TOXExamenCualita.iCveLoteCualita, "
					+ "TOXExamenCualita.iCveExamCualita, "
					+ "TOXExamenCualita.iCveLaboratorio, "
					+ "TOXExamenCualita.dtEntrega , "
					+ "TOXExamAnalisis.iCarrusel, "
					+ "TOXExamAnalisis.iPosicion, "
					+ "TOXExamAnalisis.iCveAnalisis, "
					+ "TOXExamResult.lPositivo, "
					+ "S.cPrefLoteConf,  "
					+ " TOXExamenCualita.dtProcesado, "
					+ " E.cDscEquipo, E.cModelo, E.cNumSerie, TE.iPosiciones, E.iCveEquipo "
					+ "from TOXExamenCualita "
					+ "inner join TOXExamAnalisis  on TOXExamAnalisis.iAnio = TOXExamenCualita.iAnio "
					+ "and TOXExamAnalisis.iCveLoteCualita = TOXExamenCualita.iCveLoteCualita "
					+ "and TOXExamAnalisis.icveExamCualita = TOXExamenCualita.icveExamCualita "
					+ "and TOXExamAnalisis.icveLaboratorio = TOXExamenCualita.icvelaboratorio "
					+ "inner join TOXExamResult on TOXExamResult.iAnio = TOXExamAnalisis.iAnio "
					+ "and TOXExamResult.iCveLoteCualita = TOXExamAnalisis.iCveLoteCualita "
					+ "and TOXExamResult.icveExamCualita = TOXExamAnalisis.icveExamCualita "
					+ "and TOXExamResult.icveLaboratorio = TOXExamAnalisis.icveLaboratorio "
					+ "and TOXExamResult.iCveAnalisis = TOXEXamAnalisis.iCveAnalisis "
					+ "inner join TOXSustancia S on S.iCveSustancia = TOXExamResult.iCveSustancia "
					+ "inner join EQMEquipo E on E.iCveEquipo = TOXExamenCualita.iCveEquipo "
					+ " inner join TOXEquipo TE on TE.iCveEquipo = TOXExamenCualita.iCveEquipo ";

			if (cFiltro.compareToIgnoreCase("") != 0)
				cSQL = cSQL + cFiltro;

			cSQL = cSQL
					+ " order by TOXExamAnalisis.iCarrusel, TOXExamAnalisis.iPosicion, TOXExamResult.lPositivo";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vTOXExamenCualita = new TVTOXExamenCualita();
			vTOXExamenCualita.setCResultado("");
			while (rset.next()) {
				// Barrer el ResulSet para generar los objetos del resultado
				if (iAnalisis > 0 && iAnalisis != rset.getInt(8)) {
					vTOXExamenCualita.setCResultado(vTOXExamenCualita
							.getcResultado().equals("") ? "NEGATIVO"
							: vTOXExamenCualita.getcResultado());
					if (lPendientes) {
						vTOXExamenCualita.setCResultado("ERROR");
					}
					// Agregar el elemento al Vector
					vcTOXExamenCualita.addElement(vTOXExamenCualita);
					vTOXExamenCualita = new TVTOXExamenCualita();
					vTOXExamenCualita.setCResultado("");
					iAnalisis = 0;
					lPendientes = false;
				}
				if (iAnalisis == 0)
					iAnalisis = rset.getInt(8);

				vTOXExamenCualita.setIAnio(rset.getInt(1));
				vTOXExamenCualita.setICveLaboratorio(rset.getInt(4));
				vTOXExamenCualita.setICveLoteCualita(rset.getInt(2));
				vTOXExamenCualita.setICveExamCualita(rset.getInt(3));
				vTOXExamenCualita.setICveAnalisis(rset.getInt(8));
				vTOXExamenCualita.setDtEntrega(rset.getDate(5));
				vTOXExamenCualita.setICarrusel(rset.getInt(6));
				vTOXExamenCualita.setIPosicion(rset.getInt(7));
				String cResul = vTOXExamenCualita.getcResultado();
				if (rset.getInt(9) == -1) {
					lPendientes = true;
				}
				vTOXExamenCualita.setCResultado(rset.getInt(9) == 1 ? !cResul
						.equalsIgnoreCase("") ? cResul.concat(", ").concat(
						rset.getString(10)) : cResul.concat(rset.getString(10))
						: cResul);
				vTOXExamenCualita.setDtProcesado(rset.getDate(11));
				vTOXExamenCualita.VEquipo = new TVEQMEquipo();
				vTOXExamenCualita.VEquipo.setCDscEquipo(rset.getString(12));
				vTOXExamenCualita.VEquipo.setCModelo(rset.getString(13));
				vTOXExamenCualita.VEquipo.setCNumSerie(rset.getString(14));
				vTOXExamenCualita.VEquipo.setILimiteUso(rset.getInt(15));
				vTOXExamenCualita.VEquipo.setICveEquipo(rset.getInt(16));
			}
			if (iAnalisis > 0) {
				vTOXExamenCualita.setCResultado(vTOXExamenCualita
						.getcResultado().equals("") ? "NEGATIVO"
						: vTOXExamenCualita.getcResultado());
				if (lPendientes) {
					vTOXExamenCualita.setCResultado("ERROR");
				}
				// Agregar el elemento al Vector
				vcTOXExamenCualita.addElement(vTOXExamenCualita);
			}

		} catch (Exception ex) {
			warn("FindByAllRep2", ex);
			throw new DAOException("FindByAllRep2");
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
				warn("FindByAllRep2.close", ex2);
			}
			return vcTOXExamenCualita;
		}
	} // /

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
			}
			String cSQL = "";
			TVTOXExamenCualita vTOXExamenCualita = (TVTOXExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXExamenCualita(" + "iAnio,"
					+ "iCveLoteCualita," + "iCveExamCualita,"
					+ "iCveLaboratorio," + "dtEntrega," + "dtProcesado,"
					+ "dtAutorizado," + "lAutorizado," + "lReanalisis,"
					+ "iCveEquipo," + "iCveUsuPrepara," + "iCveUsuExam,"
					+ "iCveUsuAutoriza" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXExamenCualita.getIAnio());
			pstmt.setInt(2, vTOXExamenCualita.getICveLoteCualita());
			pstmt.setInt(3, vTOXExamenCualita.getICveExamCualita());
			pstmt.setInt(4, vTOXExamenCualita.getICveLaboratorio());
			pstmt.setDate(5, vTOXExamenCualita.getDtEntrega());
			pstmt.setDate(6, vTOXExamenCualita.getDtProcesado());
			pstmt.setDate(7, vTOXExamenCualita.getDtAutorizado());
			pstmt.setInt(8, vTOXExamenCualita.getLAutorizado());
			pstmt.setInt(9, vTOXExamenCualita.getLReanalisis());
			pstmt.setInt(10, vTOXExamenCualita.getICveEquipo());
			pstmt.setInt(11, vTOXExamenCualita.getICveUsuPrepara());
			pstmt.setInt(12, vTOXExamenCualita.getICveUsuExam());
			pstmt.setInt(13, vTOXExamenCualita.getICveUsuAutoriza());
			pstmt.executeUpdate();
			cClave = "" + vTOXExamenCualita.getIAnio();
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
			TVTOXExamenCualita vTOXExamenCualita = (TVTOXExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXExamenCualita" + " set dtEntrega= ?, "
					+ "dtProcesado= ?, " + "dtAutorizado= ?, "
					+ "lAutorizado= ?, " + "lReanalisis= ?, "
					+ "iCveEquipo= ?, " + "iCveUsuPrepara= ?, "
					+ "iCveUsuExam= ?, " + "iCveUsuAutoriza= ? "
					+ "where iAnio = ? " + "and iCveLoteCualita = ?"
					+ "and iCveExamCualita = ?" + " and iCveLaboratorio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vTOXExamenCualita.getDtEntrega());
			pstmt.setDate(2, vTOXExamenCualita.getDtProcesado());
			pstmt.setDate(3, vTOXExamenCualita.getDtAutorizado());
			pstmt.setInt(4, vTOXExamenCualita.getLAutorizado());
			pstmt.setInt(5, vTOXExamenCualita.getLReanalisis());
			pstmt.setInt(6, vTOXExamenCualita.getICveEquipo());
			pstmt.setInt(7, vTOXExamenCualita.getICveUsuPrepara());
			pstmt.setInt(8, vTOXExamenCualita.getICveUsuExam());
			pstmt.setInt(9, vTOXExamenCualita.getICveUsuAutoriza());
			pstmt.setInt(10, vTOXExamenCualita.getIAnio());
			pstmt.setInt(11, vTOXExamenCualita.getICveLoteCualita());
			pstmt.setInt(12, vTOXExamenCualita.getICveExamCualita());
			pstmt.setInt(13, vTOXExamenCualita.getICveLaboratorio());
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
			TVTOXExamenCualita vTOXExamenCualita = (TVTOXExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXExamenCualita" + " where iAnio = ?"
					+ " and iCveLoteCualita = ?" + " and iCveExamCualita = ?"
					+ " and iCveLaboratorio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXExamenCualita.getIAnio());
			pstmt.setInt(2, vTOXExamenCualita.getICveLoteCualita());
			pstmt.setInt(3, vTOXExamenCualita.getICveExamCualita());
			pstmt.setInt(4, vTOXExamenCualita.getICveLaboratorio());
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
				warn("delete.closeTOXExamenCualita", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update
	 */
	public Object updateUsuExam(Connection conGral, Object obj)
			throws DAOException {
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
			TVTOXExamenCualita vTOXExamenCualita = (TVTOXExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXExamenCualita" + " set  " + "dtProcesado= ?, "
					+ "iCveUsuExam= ? " + "where iAnio = ? "
					+ "and iCveLoteCualita = ?" + "and iCveExamCualita = ?"
					+ " and iCveLaboratorio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vTOXExamenCualita.getDtProcesado());
			pstmt.setInt(2, vTOXExamenCualita.getICveUsuExam());
			pstmt.setInt(3, vTOXExamenCualita.getIAnio());
			pstmt.setInt(4, vTOXExamenCualita.getICveLoteCualita());
			pstmt.setInt(5, vTOXExamenCualita.getICveExamCualita());
			pstmt.setInt(6, vTOXExamenCualita.getICveLaboratorio());
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
	 * Metodo Find By All
	 */
	public boolean lAutorizado(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamenCualita = new Vector();
		boolean lAutorizado = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamenCualita vTOXExamenCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select lAutorizado " + " from TOXExamenCualita ";
			if (cFiltro.compareToIgnoreCase("") != 0)
				cSQL = cSQL + cFiltro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (rset.getInt(1) == 1)
					lAutorizado = true;
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
			return lAutorizado;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindMaxValue(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vcTOXExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamenCualita vTOXExamenCualita;
			int count;
			cSQL = " SELECT MAX(iCveExamCualita) AS iCveExamCualita "
					+ " from TOXExamenCualita ";
			if (cFiltro.compareToIgnoreCase("") != 0)
				cSQL = cSQL + cFiltro;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				vTOXExamenCualita = new TVTOXExamenCualita();
				vTOXExamenCualita.setICveExamCualita(rset
						.getInt("iCveExamCualita"));
				vcTOXExamenCualita.addElement(vTOXExamenCualita);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcTOXExamenCualita;
		}
	}

	/**
	 * Metodo borraLotes
	 */

	public boolean borraLotes(TVTOXExamenCualita tvExamenCualita)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		java.sql.Date cDate = new TFechas().TodaySQL();
		int iUpdate = 0;
		boolean bUpdate = false;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " DELETE FROM TOXExamenCualita " + " WHERE iAnio = ? "
					+ " AND iCveLaboratorio = ? " + " AND iCveLoteCualita = ? "
					+ " AND iCveExamCualita = ? " + " AND dtProcesado is null ";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, tvExamenCualita.getIAnio());
			pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
			pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
			pstmt.setInt(4, tvExamenCualita.getICveExamCualita());
			iUpdate += pstmt.executeUpdate();

			if (pstmt != null)
				pstmt.close();

			cSQL = " DELETE FROM TOXExamAnalisis " + " WHERE iAnio = ? "
					+ " AND iCveLaboratorio = ? " + " AND iCveLoteCualita = ? "
					+ " AND iCveExamCualita = ? ";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, tvExamenCualita.getIAnio());
			pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
			pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
			pstmt.setInt(4, tvExamenCualita.getICveExamCualita());

			iUpdate += pstmt.executeUpdate();

			if (pstmt != null)
				pstmt.close();

			if (tvExamenCualita.getICveExamCualita() == 1) {
				cSQL = " DELETE FROM TOXAnalisis " + " WHERE iAnio = ? "
						+ " AND iCveLaboratorio = ? "
						+ " AND iCveLoteCualita = ? ";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
				iUpdate += pstmt.executeUpdate();

				if (pstmt != null)
					pstmt.close();

				cSQL = " UPDATE TOXLoteCualita  "
						+ " SET  dtGeneracion = null " + " WHERE iAnio = ? "
						+ " AND iCveLaboratorio = ? "
						+ " AND iCveLoteCualita = ? ";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());

				iUpdate += pstmt.executeUpdate();

				if (pstmt != null)
					pstmt.close();

				// Liberar las muestras del Lote
				cSQL = " update TOXMuestra " + " set lintegralote = 0 "
						+ "  where iAnio = ? " + "    and iCveMuestra in "
						+ "   (select ML.iCveMuestra "
						+ "     from TOXMuestraLote ML  "
						+ "     where ML.iAnio = ? "
						+ "       and ML.iCveLaboratorio = ? "
						+ "       and ML.iCveLoteCualita = ?); ";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getIAnio());
				pstmt.setInt(3, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(4, tvExamenCualita.getICveLoteCualita());
				iUpdate += pstmt.executeUpdate();

				if (pstmt != null)
					pstmt.close();

				// Borrar las muestras por Lote
				cSQL = " delete from TOXMuestraLote "
						+ "  where iAnio           = ? "
						+ "    and iCveLaboratorio = ? "
						+ "    and iCveLotecualita = ? ";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
				iUpdate += pstmt.executeUpdate();

				if (pstmt != null)
					pstmt.close();

				// Borrar los env�os por lote
				cSQL = " delete from TOXEnvioXLote "
						+ " where iAnio           = ? "
						+ "   and iCveLaboratorio = ? "
						+ "   and iCveLotecualita = ? ";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
				iUpdate += pstmt.executeUpdate();

			} else {
				cSQL = " UPDATE TOXExamAnalisis SET " + " lTerminado = 0 "
						+ " WHERE iAnio = ? " + " AND iCveLaboratorio = ? "
						+ " AND iCveLoteCualita = ? "
						+ " AND iCveExamCualita = ? ";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
				pstmt.setInt(4, (tvExamenCualita.getICveExamCualita() - 1));

				iUpdate += pstmt.executeUpdate();

				if (pstmt != null)
					pstmt.close();
			}

			if (iUpdate != 0) {
				conn.commit();
				bUpdate = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return bUpdate;
		}
	}

	/**
	 * Metodo borraLotes
	 */

	public boolean liberarLotes(TVTOXExamenCualita tvExamenCualita)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean bUpdate = false;
		int iUpdate = 0;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT * FROM TOXExamAnalisis " + " WHERE iAnio = ? "
					+ " AND iCveLaboratorio = ? " + " AND iCveLoteCualita = ? "
					+ " AND iCveExamCualita = ? ";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, tvExamenCualita.getIAnio());
			pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
			pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
			pstmt.setInt(4, tvExamenCualita.getICveExamCualita());
			rset = pstmt.executeQuery();
			// Actualizar la informaci�n de cada uno de los An�lisis
			// Autorizados
			// dentro del Lote y examen indicado.
			while (rset.next()) {
				cSQL = " UPDATE TOXAnalisis SET " + " lPresuntoPost = 0, "
						+ " lAutorizado = 0, " + " lResultado = 0, "
						+ " dtAutorizacion = null, "
						+ " iCveUsuAutoriza = 0 , " + " iSustPost = null, "
						+ " iSustConf =  null " + " WHERE iAnio = ? "
						+ " AND iCveLaboratorio = ? "
						+ " AND iCveAnalisis = ? ";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, tvExamenCualita.getIAnio());
				pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
				pstmt.setInt(3, rset.getInt("iCveAnalisis"));
				iUpdate += pstmt.executeUpdate();
			}
			// Actualizar la informaci�n del Lote.
			if (pstmt != null)
				pstmt.close();

			cSQL = " UPDATE TOXExamenCualita   "
					+ " SET  dtAutorizado = null, "
					+ "      lReanalisis  = 0,    "
					+ "      lAutorizado  = 0,    "
					+ "      iCveUsuAutoriza = null, "
					+ "      lTerminado   = null  " + " WHERE iAnio = ? "
					+ " AND iCveLaboratorio = ? " + " AND iCveLoteCualita = ? "
					+ " AND iCveExamCualita = ? ";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, tvExamenCualita.getIAnio());
			pstmt.setInt(2, tvExamenCualita.getICveLaboratorio());
			pstmt.setInt(3, tvExamenCualita.getICveLoteCualita());
			pstmt.setInt(4, tvExamenCualita.getICveExamCualita());

			iUpdate += pstmt.executeUpdate();

			if (iUpdate != 0) {
				conn.commit();
				bUpdate = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return bUpdate;
		}
	}

}
