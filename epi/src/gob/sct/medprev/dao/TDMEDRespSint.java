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

public class TDMEDRespSint extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDRespSint() {
	}

	/**
	 * M�todo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select icvetiporesp, " + " iorden, " + " llogica, "
					+ " cdescripcion" + " from medrespsint " + " where "
					+ cWhere + " Order By iorden";
			/*
			 * System.out.println("-----------------------------------------");
			 * // System.out.println(cSQL); //
			 * System.out.println("-----------------------------------------");
			 */
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDRespSint = new TVMEDRespSint();
				vMEDRespSint.setICveTpoResp(rset.getInt(1));
				vMEDRespSint.setIOrden(rset.getInt(2));
				vMEDRespSint.setILogica(rset.getInt(3));
				vMEDRespSint.setCDescripcion(rset.getString(4));
				vcMEDSintoma.addElement(vMEDRespSint);
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
			return vcMEDSintoma;
		}
	}

	/**
	 * M�todo Find By All
	 */
	public String FindByResp(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		String Respuesta = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select cdescripcion" + " from medrespsint " + " where "
					+ cWhere + " Order By iorden";
			/*
			 * System.out.println("=========================================");
			 * // System.out.println(cSQL); //
			 * System.out.println("=========================================");
			 */
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Respuesta = rset.getString(1);
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
			return Respuesta;
		}
	}

	/**
	 * M�todo Find By All
	 */
	public String FindByResp2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		String Respuesta = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select cdescripcion" + " from medrespsint " + " where "
					+ cWhere + " Order By iorden";
			System.out.println("cSQL = "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Respuesta = Respuesta + "<br>" + rset.getString(1);
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
			return Respuesta;
		}
	}

	/**
	 * M�todo Find By All
	 */
	public String FindByAllS(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select cdescripcion               "
					+ "from medrespsint                " + "where 			" + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
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
			return Regresa;
		}
	}

	/**
	 * M�todo Insert - NO BORRAR JMG
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {

		// System.out.println(" =========== Entrando en el insert ================");

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		PreparedStatement pstmtMax2 = null;
		ResultSet rsetMax2 = null;
		String cClave = "";
		int iMax = 0;
		int iMax2 = 0;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
			TVMEDRespSint vMEDRespSint = (TVMEDRespSint) obj;
			Vector Respuesta = new Vector();
			String Resultado = "";
			// System.out.println(vMEDRespSint.getCDescripcion());
			for (int a = 0; a < vMEDRespSint.getCDescripcion().length(); a++) {
				if (vMEDRespSint.getCDescripcion().charAt(a) == '/') {
					Respuesta.addElement(Resultado);
					Resultado = "";
				} else {
					Resultado = "" + Resultado
							+ vMEDRespSint.getCDescripcion().charAt(a);
				}
			}

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// /Verificamos si hay datos ya agregados.
			String cSQLMax2 = "select count(iOrden) from MEDRESPSINT"
					+ " Where iCveServicio = " + vMEDRespSint.getICveServicio()
					+ " And iCveRama = " + vMEDRespSint.getICveRama()
					+ " And iCveSintoma = " + vMEDRespSint.getICveSintoma();

			// System.out.println(cSQLMax2);
			pstmtMax2 = conn.prepareStatement(cSQLMax2);
			rsetMax2 = pstmtMax2.executeQuery();
			while (rsetMax2.next()) {
				iMax2 = rsetMax2.getInt(1);
			}
			rsetMax2.close();
			pstmtMax2.close();

			if (iMax2 < 1)
				iMax2 = 0;

			// System.out.println("iMax2="+iMax2);

			if (vMEDRespSint.getICveTpoResp() == 1 && iMax2 == 0) {
				// System.out.println("insertando");

				cSQL = "insert into MEDRespSint(" + "  iCveServicio,"
						+ "  iCveRama," + "  iCveSintoma," + "  iCveTipoResp,"
						+ "  iOrden," + "  lLogica," + "  cDescripcion"
						+ ")values(?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vMEDRespSint.getICveServicio());
				pstmt.setInt(2, vMEDRespSint.getICveRama());
				pstmt.setInt(3, vMEDRespSint.getICveSintoma());
				pstmt.setInt(4, vMEDRespSint.getICveTpoResp());
				pstmt.setInt(5, 1);
				pstmt.setInt(6, vMEDRespSint.getILogica());
				pstmt.setString(7, "Logica");
				pstmt.executeUpdate();
				cClave = "" + iMax;
				if (conGral == null) {
					conn.commit();
				}
			}

			if (vMEDRespSint.getICveTpoResp() == 8
					|| vMEDRespSint.getICveTpoResp() == 9
					|| vMEDRespSint.getICveTpoResp() == 13) {
				if (Respuesta.size() > 0)
					for (int b = iMax2; b < Respuesta.size(); b++) {
						String Descripcion = (String) Respuesta.elementAt(b);

						cSQL = "insert into MEDRespSint(" + "  iCveServicio,"
								+ "  iCveRama," + "  iCveSintoma,"
								+ "  iCveTipoResp," + "  iOrden,"
								+ "  lLogica," + "  cDescripcion"
								+ ")values(?,?,?,?,?,?,?)";

						pstmt = conn.prepareStatement(cSQL);

						String cSQLMax = "select count(iOrden)+1 from MEDRESPSINT"
								+ " Where iCveServicio = "
								+ vMEDRespSint.getICveServicio()
								+ " And iCveRama = "
								+ vMEDRespSint.getICveRama()
								+ " And iCveSintoma = "
								+ vMEDRespSint.getICveSintoma();

						pstmtMax = conn.prepareStatement(cSQLMax);
						rsetMax = pstmtMax.executeQuery();
						while (rsetMax.next()) {
							iMax = rsetMax.getInt(1);
						}
						if (iMax == 0) {
							iMax = 1;
						}
						rsetMax.close();
						pstmtMax.close();
						// iMax = iMax + b -1;

						// ///////////////////////////////////
						pstmt.setInt(1, vMEDRespSint.getICveServicio());
						pstmt.setInt(2, vMEDRespSint.getICveRama());
						pstmt.setInt(3, vMEDRespSint.getICveSintoma());
						pstmt.setInt(4, vMEDRespSint.getICveTpoResp());
						if (vMEDRespSint.getICveTpoResp() == 1)
							pstmt.setInt(5, 1);
						else
							pstmt.setInt(5, iMax);
						pstmt.setInt(6, vMEDRespSint.getILogica());
						pstmt.setString(7, Descripcion);
						pstmt.executeUpdate();
						cClave = "" + iMax;
						if (conGral == null) {
							conn.commit();
						}

					}// Extraccion Vector
			}// Tipo de respuesta 8
				// System.out.println(" =========== finalizando el insert ================");
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
	 * M�todo update - NO BORRAR JMG
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
			// TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
			TVMEDRespSint vMEDRespSint = (TVMEDRespSint) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "update MEDRespSint" + " set lLogica = ? "
					+ " where iCveServicio =?" + " And   iCveRama =?"
					+ " And   iCveSintoma =?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDRespSint.getILogica());
			pstmt.setInt(2, vMEDRespSint.getICveServicio());
			pstmt.setInt(3, vMEDRespSint.getICveRama());
			pstmt.setInt(4, vMEDRespSint.getICveSintoma());

			pstmt.executeUpdate();
			cClave = "" + vMEDRespSint.getICveServicio() + "|"
					+ vMEDRespSint.getICveRama() + "|"
					+ vMEDRespSint.getICveSintoma();
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

	public Object updateRama(Connection conGral, Object obj,
			List<String> respuestas, List<String> numero) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		// System.out.println("LLego hasta el DAO");
		for (int i = 0; i < respuestas.size(); i++) {
			if (respuestas.get(i).equals("")) {
				this.delete(null, obj, Integer.valueOf(numero.get(i)));
			} else {
				try {
					if (conGral != null) {
						conn = conGral;
					} else {
						dbConn = new DbConnection(dataSourceName);
						conn = dbConn.getConnection();
					}
					String cSQL = "";
					// TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
					TVMEDRespSint vMEDRespSint = (TVMEDRespSint) obj;
					conn.setAutoCommit(false);
					conn.setTransactionIsolation(2);

					cSQL = "update MEDRespSint" + " set cdescripcion = ? "
							+ " where iCveServicio =?" + " And   iCveRama =?"
							+ " And   iCveSintoma =?" + " And   iOrden =?";
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setString(1, respuestas.get(i));
					pstmt.setInt(2, vMEDRespSint.getICveServicio());
					pstmt.setInt(3, vMEDRespSint.getICveRama());
					pstmt.setInt(4, vMEDRespSint.getICveSintoma());
					pstmt.setInt(5, Integer.valueOf(numero.get(i)));
					// System.out.println("Que trae " + cSQL);
					pstmt.executeUpdate();
					cClave = "" + vMEDRespSint.getICveServicio() + "|"
							+ vMEDRespSint.getICveRama() + "|"
							+ vMEDRespSint.getICveSintoma();
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

				}
			}

		}
		return cClave;
	}

	/**
	 * M�todo delete
	 */
	public Object delete(Connection conGral, Object obj, int orden)
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
			// TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
			TVMEDRespSint vMEDRespSint = (TVMEDRespSint) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "delete MEDRespSint" + " where iCveServicio =?"
					+ " And   iCveRama =?" + " And   iCveSintoma =?"
					+ " And   iOrden =?";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vMEDRespSint.getICveServicio());
			pstmt.setInt(2, vMEDRespSint.getICveRama());
			pstmt.setInt(3, vMEDRespSint.getICveSintoma());
			pstmt.setInt(4, orden);

			pstmt.executeUpdate();
			cClave = "" + vMEDRespSint.getICveServicio() + "|"
					+ vMEDRespSint.getICveRama() + "|"
					+ vMEDRespSint.getICveSintoma();
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
	 * M�todo Find By All
	 */
	public String FindByAllOption(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		String regresarespeuestas = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVMEDRespSint vMEDRespSint;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select icvetiporesp, " + " iorden, " + " llogica, "
					+ " cdescripcion" + " from medrespsint " + " where "
					+ cWhere + " Order By iorden";
			
			  System.out.println("-----------------------------------------");
			  System.out.println(cSQL); //
			  System.out.println("-----------------------------------------");
			 
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresarespeuestas = regresarespeuestas+"<option value=\""+rset.getInt(2)+"\">"+rset.getString(4)+"</option>";
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
			return regresarespeuestas;
		}
	}
	
	
}