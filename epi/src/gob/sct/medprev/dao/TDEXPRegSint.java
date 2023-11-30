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
 * 
 * @author AG SA
 */

public class TDEXPRegSint extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	int iInserted = 0;

	public TDEXPRegSint() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRegSin = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVEXPRegSin vEXPRegSin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT R.ICVESERVICIO, R.ICVERAMA, R.ICVESINTOMA, R.ICVEMDOTRANS, R.ICVECATEGORIA, R.LDICTAMENS, R.LDICTAMENF, M.CALERTA "
					+ "FROM EXPREGSIN AS R, MEDREGSIN AS M "
					+ "WHERE "
					+ "R.ICVESERVICIO = M.ICVESERVICIO AND "
					+ "R.ICVERAMA = M.ICVERAMA AND "
					+ "R.ICVESINTOMA = M.ICVESINTOMA AND "
					+ "R.ICVEMDOTRANS = M.ICVEMDOTRANS AND "
					+ "R.ICVECATEGORIA = M.ICVECATEGORIA AND "
					+ "R.LDICTAMENS = M.LDICTAMENS AND "
					+ "R.LDICTAMENF = M.LDICTAMENF AND "
					+ "M.LACTIVO = 1 AND "
					+ cWhere
					+ " GROUP BY R.ICVEMDOTRANS, R.ICVECATEGORIA, R.ICVESERVICIO, R.ICVERAMA, R.ICVESINTOMA, R.LDICTAMENS, R.LDICTAMENF, M.CALERTA ";

			// System.out.println("MEDREGSIN = "+cSQL +"  **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRegSin = new TVEXPRegSin();
				vEXPRegSin.setICveServicio(rset.getInt(1));
				vEXPRegSin.setICveRama(rset.getInt(2));
				vEXPRegSin.setICveSintoma(rset.getInt(3));
				vEXPRegSin.setICveMdoTrans(rset.getInt(4));
				vEXPRegSin.setICveCategoria(rset.getInt(5));
				vEXPRegSin.setLDictamenS(rset.getInt(6));
				vEXPRegSin.setLDictamenF(rset.getInt(7));
				vEXPRegSin.setCAlerta(rset.getString(8));
				vcEXPRegSin.addElement(vEXPRegSin);
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
			return vcEXPRegSin;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public int FindByCount(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRegSin = new Vector();
		int regresa = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPRegSin vEXPRegSin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = cWhere;

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getInt(1);
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
			return regresa;
		}
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
			TVEXPRegSin vEXPRegSin = (TVEXPRegSin) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPRegSin(" + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveRama," + "iCveSintoma,"
					+ "iCveMdoTrans," + "iCvecategoria," + "lDictamenS,"
					+ "lDictamenF" + ")values(?,?,?,?,?,?,?,?,?)";

			// System.out.println("//////////////////////////   REGISTRANDO REGLA   ////////////////////////////");

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEXPRegSin.getICveExpediente());
			pstmt.setInt(2, vEXPRegSin.getINumExamen());
			pstmt.setInt(3, vEXPRegSin.getICveServicio());
			pstmt.setInt(4, vEXPRegSin.getICveRama());
			pstmt.setInt(5, vEXPRegSin.getICveSintoma());
			pstmt.setInt(6, vEXPRegSin.getICveMdoTrans());
			pstmt.setInt(7, vEXPRegSin.getICveCategoria());
			pstmt.setInt(8, vEXPRegSin.getLDictamenS());
			pstmt.setInt(9, vEXPRegSin.getLDictamenF());
			iInserted = pstmt.executeUpdate();
			cClave = "" + vEXPRegSin.getICveExpediente();
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public String FindByPestana(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPRegSin = new Vector();
		int regresa = 0;
		int transporte = 0;
		int categoria = 0;
		int abierto = 0;
		int cerrado = 0;
		String transporte2 = "";
		String categoria2 = "";
		String encabezado = "<div id=\"tabvanilla\" class=\"widget\"> \n <ul class=\"tabnav\"> \n";
		String contenido = "";
		String fuente = "";

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count = 0;
			cSQL = "SELECT E.ICVEMDOTRANS, T.CDSCMDOTRANS, E.ICVECATEGORIA, C.CDSCCATEGORIA, M.CALERTA  "
					+ "FROM EXPREGSIN AS E, GRLMDOTRANS AS T, GRLCATEGORIA AS C, MEDREGSIN AS M "
					+ " WHERE "
					+ " E.ICVEMDOTRANS = T.ICVEMDOTRANS AND "
					+ " E.ICVEMDOTRANS = C.ICVEMDOTRANS AND "
					+ " E.ICVECATEGORIA = C.ICVECATEGORIA AND "
					+ " E.ICVEMDOTRANS = M.ICVEMDOTRANS AND "
					+ " E.ICVECATEGORIA = M.ICVECATEGORIA AND "
					+ " E.ICVESERVICIO = M.ICVESERVICIO AND "
					+ " E.ICVERAMA = M.ICVERAMA AND "
					+ " E.ICVESINTOMA = M.ICVESINTOMA AND "
					// + "E.ICVEEXPEDIENTE = 3434 AND "
					// + "E.INUMEXAMEN = 4 "
					+ cWhere + " ORDER BY T.CDSCMDOTRANS, C.CDSCCATEGORIA";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (transporte < rset.getInt(1)) {
					if (categoria > rset.getInt(3)
							|| categoria < rset.getInt(3)) {
						encabezado = encabezado + "   <li><a href=\"#"
								+ rset.getInt(1) + rset.getInt(3) + "\">"
								+ rset.getString(2) + "-" + rset.getString(4)
								+ "</a></li>\n";
						if (count > 0 && abierto == 1) {
							contenido = contenido + "</ul>\n</div><!--"
									+ transporte2 + "-" + categoria2 + "-->\n";
							contenido = contenido + " <div id=\""
									+ rset.getInt(1) + rset.getInt(3)
									+ "\" class=\"tabdiv\"><ul>\n";
							count++;
							abierto = 1;
						}
						if (count == 0) {
							contenido = contenido + "    <div id=\""
									+ rset.getInt(1) + rset.getInt(3)
									+ "\" class=\"tabdiv\"><ul>\n";
							count++;
							abierto = 1;
						}
					}
					if (categoria == rset.getInt(3)) {
						encabezado = encabezado + "   <li><a href=\"#"
								+ rset.getInt(1) + rset.getInt(3) + "\">"
								+ rset.getString(2) + "-" + rset.getString(4)
								+ "</a></li>\n";
						if (count > 0 && abierto == 1) {
							contenido = contenido
									+ "        </ul>\n     </div><!--"
									+ transporte2 + "-" + categoria2 + "-->\n";
							contenido = contenido + "    <div id=\""
									+ rset.getInt(1) + rset.getInt(3)
									+ "\" class=\"tabdiv\"><ul>\n";
							count++;
							abierto = 1;
						}
						if (count == 0) {
							contenido = contenido + "    <div id=\""
									+ rset.getInt(1) + rset.getInt(3)
									+ "\" class=\"tabdiv\"><ul>\n";
							abierto = 1;
							count++;
						}
					}
					transporte = rset.getInt(1);
					categoria = rset.getInt(3);
					transporte2 = rset.getString(2);
					categoria2 = rset.getString(4);
				}
				if (transporte == rset.getInt(1)) {
					if (categoria == rset.getInt(3)) {
						contenido = contenido + "<li><a href=\"#\">&nbsp;"
								+ rset.getString(5) + "</a></li>\n";
						abierto = 1;
					}
				}
			}
			contenido = contenido + "          </ul>\n     </div><!--"
					+ transporte2 + "-" + categoria2 + "-->\n";
			fuente = encabezado + "   </ul>\n" + contenido
					+ "</div><!--/widget-->";
			// System.out.println("encabezado = "+ fuente);
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
			return fuente;
		}
	}

	public int Liberar(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " DELETE FROM EXPRegSin "
					+ " WHERE iCveExpediente = ? " + " AND iNumExamen = ? "
					+ " AND iCveServicio = ? ";

			String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
			String cNumExamen = (String) hmFiltros.get("iNumExamen");
			String cServicio = (String) hmFiltros.get("iCveServicio");

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(cCveExpediente));
			pstmt.setInt(2, Integer.parseInt(cNumExamen));
			pstmt.setInt(3, Integer.parseInt(cServicio));
			iRset = pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Liberar", ex2);
			}
		}
		return iRset;
	}

	/**
	 * Metodo Find By All
	 */
	@SuppressWarnings("finally")
	public Vector<TVEXPRegSin> FindByAllRegSin(String cExpediente, String cInumExamen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector<TVEXPRegSin> vcEXPRegSin = new Vector<TVEXPRegSin>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintoma vMEDSintoma;
			TVEXPRegSin vEXPRegSin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " SELECT" +
					"		E.ICVEEXPEDIENTE," +
					"		E.INUMEXAMEN," +
					"		T.CDSCMDOTRANS," +
					"		C.CDSCCATEGORIA," +
					"		S.CDSCSERVICIO," +
					"		R.CDSCRAMA," +
					"		P.CPREGUNTA," +
					"		P.ICVETPORESP," +
					"		V.DVALORINI," +
					"		V.LLOGICO," +
					"		V.CCARACTER," +
					"		M.IMAYORA," +
					"		M.IMENORA," +
					"		M.IIGUALA," +
					"		M.CALERTA," +
					"		M.LOGICA," +
					"		M.DTGENERADO," +
					"		U.CNOMBRE || ' ' || U.CAPPATERNO || ' ' || U.CAPMATERNO," +
					"		E.ICVESERVICIO," +
					"		E.ICVERAMA," +
					"		E.ICVESINTOMA" +
				" FROM" +
					"		EXPREGSIN AS E," +
					"		GRLMDOTRANS AS T," +
					"		GRLCATEGORIA AS C," +
					"		MEDSERVICIO AS S," +
					"		MEDRAMA AS R," +
					"		MEDSINTOMAS AS P," +
					"		EXPRESULTADO AS V," +
					"		MEDREGSIN AS M," +
					"		SEGUSUARIO AS U" +
				" WHERE " +
					"		E.ICVEMDOTRANS = T.ICVEMDOTRANS AND" +
					"		E.ICVEMDOTRANS = C.ICVEMDOTRANS AND" +
					"		E.ICVECATEGORIA = C.ICVECATEGORIA AND" +
					"		E.ICVESERVICIO = S.ICVESERVICIO AND" +
					"		E.ICVESERVICIO = R.ICVESERVICIO AND" +
					"		E.ICVERAMA = R.ICVERAMA AND" +
					"		E.ICVESERVICIO = P.ICVESERVICIO AND" +
					"		E.ICVERAMA = P.ICVERAMA AND" +
					"		E.ICVESINTOMA = P.ICVESINTOMA AND" +
					"		E.ICVESERVICIO = V.ICVESERVICIO AND" +
					"		E.ICVERAMA = V.ICVERAMA AND" +
					"		E.ICVESINTOMA = V.ICVESINTOMA AND" +
					"		E.ICVEEXPEDIENTE = V.ICVEEXPEDIENTE AND" +
					"		E.INUMEXAMEN = V.INUMEXAMEN AND" +
					"		E.ICVESERVICIO = M.ICVESERVICIO AND" +
					"		E.ICVERAMA = M.ICVERAMA AND" +
					"		E.ICVESINTOMA = M.ICVESINTOMA AND" +
					"		E.ICVEMDOTRANS = M.ICVEMDOTRANS AND" +
					"		E.ICVECATEGORIA = M.ICVECATEGORIA AND" +
					"		M.ICVEUSUGENERA = U.ICVEUSUARIO AND" +
					//"		M.LDICTAMENF = 1 AND" +
					"		E.ICVEEXPEDIENTE = "+cExpediente+" AND" +
					"		E.INUMEXAMEN = "+cInumExamen;

			//System.out.println("MEDREGSIN = "+cSQL +" **** ");

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPRegSin = new TVEXPRegSin();
				vEXPRegSin.setICveExpediente(rset.getInt(1));
				vEXPRegSin.setINumExamen(rset.getInt(2));
				vEXPRegSin.setCMdoTrans(rset.getString(3));
				vEXPRegSin.setCCategorias(rset.getString(4));
				vEXPRegSin.setCServicios(rset.getString(5));
				vEXPRegSin.setCRama(rset.getString(6));
				vEXPRegSin.setCPregunta(rset.getString(7));
				vEXPRegSin.setICveTpoResp(rset.getInt(8));
				vEXPRegSin.setIDvalorIni(rset.getDouble(9));
				vEXPRegSin.setLogicaR(rset.getInt(10));
				vEXPRegSin.setCCaracterR(rset.getString(11));
				vEXPRegSin.setIMayorA(rset.getDouble(12));
				vEXPRegSin.setIMenorA(rset.getDouble(13));
				vEXPRegSin.setIIgualA(rset.getDouble(14));
				vEXPRegSin.setCAlerta(rset.getString(15));
				vEXPRegSin.setLogica(rset.getInt(16));
				vEXPRegSin.setDtGenerado(rset.getTimestamp(17));
				vEXPRegSin.setCUsuario(rset.getString(18));
				vEXPRegSin.setICveServicio(rset.getInt(19));
				vEXPRegSin.setICveRama(rset.getInt(20));
				vEXPRegSin.setICveSintoma(rset.getInt(21));
				vcEXPRegSin.addElement(vEXPRegSin);

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
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPRegSin;
		}
	}
	/**
	 * Metodo Insert
	 */
	@SuppressWarnings("finally")
	public String insert(Connection conGral, int exp, int exa, int serv, int ram, int sin, int tran, int cat, int dics, int dicf) throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPRegSin(" + "iCveExpediente," + "iNumExamen,"
					+ "iCveServicio," + "iCveRama," + "iCveSintoma,"
					+ "iCveMdoTrans," + "iCvecategoria," + "lDictamenS,"
					+ "lDictamenF" + ")values(?,?,?,?,?,?,?,?,?)";
			//System.out.println("//////////////////////////   REGISTRANDO REGLA   ////////////////////////////");

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, exp);
			pstmt.setInt(2, exa);
			pstmt.setInt(3, serv);
			pstmt.setInt(4, ram);
			pstmt.setInt(5, sin);
			pstmt.setInt(6, tran);
			pstmt.setInt(7, cat);
			pstmt.setInt(8, dics);
			pstmt.setInt(9, dicf);
			iInserted = pstmt.executeUpdate();
			cClave = "" + exp;
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (final Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	
	public int RegresaInt(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		int regreso = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = SQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vPERDatos = new TVPERDatos();
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

}