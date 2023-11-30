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
 * Title: Data Acces Object de PbaRapida DAO
 * </p>
 * <p>
 * Description: DAO Tabla TOXPbaRapida
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

public class TDPbaRapida extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPbaRapida() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(Object obj, String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPbaRapida = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPbaRapida vPbaRapida;
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCvePbaRapida," + "dtCaducidad,"
					+ "dtAsignacion," + "ToxPbaRapida.iCveUniMed,"
					+ "ToxPbaRapida.iCveProceso," + "dtAplicacion,"
					+ "iCvePersonal," + "iCveUsuAplica," + "dtCaptura,"
					+ "iCveUsuCaptura," + "ToxPbaRapida.iCveMotivo,"
					+ "lPosiblePost," + "cDscUniMed," + "cDscProceso,"
					+ "cDscMotivo," + "cNombre," + "cApPaterno,"
					+ "cApMaterno," + "cDscModulo," + "cDscMdoTrans, "
					+ "TOXPbaRapida.iCveModulo, "
					+ "TOXPbaRapida.iCveMdoTrans " + " from TOXPbaRapida ";
			cSQL = cSQL + " inner join GRLUnimed ON "
					+ " GrlUniMed.iCveUniMed = ToxPbaRapida.iCveUniMed ";
			cSQL = cSQL + " inner join GRLProceso ON "
					+ " GRLProceso.iCveProceso = ToxPbaRapida.iCveProceso ";
			cSQL = cSQL + " inner join GRLMotivo ON " +
			// " GRLMotivo.iCveProceso = ToxPbaRapida.iCveProceso " +
					" GRLMotivo.iCveMotivo = ToxPbaRapida.iCveMotivo ";
			cSQL = cSQL + " inner join SEGUsuario ON "
					+ " SEGUsuario.iCveUsuario = ToxPbaRapida.iCveUsuAplica ";
			cSQL = cSQL
					+ " inner join GRLModulo ON"
					+ " GRLModulo.iCveUniMed = ToxPbaRapida.iCveUniMed and GRLModulo.iCveModulo = ToxPbaRapida.iCveModulo ";
			cSQL = cSQL + " inner join GRLMdoTrans ON"
					+ " GRLMdoTrans.iCveMdoTrans = ToxPbaRapida.iCveMdoTrans ";

			if (cWhere.compareTo("true") == 0) {
				cSQL = cSQL + " where iAnio = ? " + " and iCvePbaRapida = ? "
						+ " and iCvePersonal = ? ";
			} else {
				cSQL = cSQL + "order by iAnio";
			}

			// System.out.println("FindByAll PbaRapida: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			if (cWhere.compareTo("true") == 0) {
				TVPbaRapida vPbaRapidaF = (TVPbaRapida) obj;
				// System.out.println("Param " + vPbaRapidaF.getIAnio() + " ** "
				// + vPbaRapidaF.getICvePbaRapida() + " ** " +
				// vPbaRapidaF.getICvePersonal());
				pstmt.setInt(1, vPbaRapidaF.getIAnio());
				pstmt.setInt(2, vPbaRapidaF.getICvePbaRapida());
				pstmt.setInt(3, vPbaRapidaF.getICvePersonal());
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPbaRapida = new TVPbaRapida();
				vPbaRapida.setIAnio(rset.getInt(1));
				vPbaRapida.setICvePbaRapida(rset.getInt(2));
				vPbaRapida.setDtCaducidad(rset.getDate(3));
				vPbaRapida.setDtAsignacion(rset.getDate(4));
				vPbaRapida.setICveUniMed(rset.getInt(5));
				vPbaRapida.setICveProceso(rset.getInt(6));
				vPbaRapida.setDtAplicacion(rset.getDate(7));
				vPbaRapida.setICvePersonal(rset.getInt(8));
				vPbaRapida.setICveUsuAplica(rset.getInt(9));
				vPbaRapida.setDtCaptura(rset.getDate(10));
				vPbaRapida.setICveUsuCaptura(rset.getInt(11));
				vPbaRapida.setICveMotivo(rset.getInt(12));
				vPbaRapida.setLPosiblePost(rset.getInt(13));
				vPbaRapida.setCDscUM(rset.getString(14));
				vPbaRapida.setCDscProceso(rset.getString(15));
				vPbaRapida.setCDscMotivo(rset.getString(16));
				vPbaRapida.setCDscUsuAplica(rset.getString(17) + " "
						+ rset.getString(18) + " " + rset.getString(19));
				vPbaRapida.setCDscModulo(rset.getString(20));
				vPbaRapida.setCDscMdoTrans(rset.getString(21));
				vPbaRapida.setICveModulo(rset.getInt(22));
				vPbaRapida.setICveMdoTrans(rset.getInt(23));

				if (vPbaRapida.getDtCaducidad() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtCaducidad(), "/");
					vPbaRapida.setCDtCaducidad(cFecha);
				} else {
					vPbaRapida.setCDtCaducidad("");
				}

				if (vPbaRapida.getDtAsignacion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtAsignacion(), "/");
					vPbaRapida.setCDtAsignacion(cFecha);
				} else {
					vPbaRapida.setCDtAsignacion("");
				}

				if (vPbaRapida.getDtAplicacion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtAplicacion(), "/");
					vPbaRapida.setCDtAplicacion(cFecha);
				} else {
					vPbaRapida.setCDtAplicacion("");
				}

				if (vPbaRapida.getDtCaptura() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtCaptura(), "/");
					vPbaRapida.setCDtCaptura(cFecha);
				} else {
					vPbaRapida.setCDtCaptura("");
				}

				vcPbaRapida.addElement(vPbaRapida);
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
			return vcPbaRapida;
		}
	}

	/**
	 * Metodo FindSust
	 */
	public int FindSust(String cAnio, String cPbaRapida, String cSustancia)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSustancia = new Vector();
		int iActivo = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSupuestoPost vSupuesto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iAnio,"
					+ "iCvePbaRapida,"
					+ "iCveSustancia,"
					+ "lResultado "
					+ " from TOXSupuestoPost "
					+ " where iAnio = ? and iCvePbaRapida = ? and iCveSustancia = ? and lResultado = 1"
					+ " order by iCveSustancia";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, Integer.parseInt(cAnio));
			pstmt.setInt(2, Integer.parseInt(cPbaRapida));
			pstmt.setInt(3, Integer.parseInt(cSustancia));

			rset = pstmt.executeQuery();
			while (rset.next()) {
				iActivo = rset.getInt(4);
			}
		} catch (Exception ex) {
			warn("FindSust", ex);
			throw new DAOException("FindSust");
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
				warn("FindSust.close", ex2);
			}
			return iActivo;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj, Object obSusApi)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtSusApi = null;
		PreparedStatement pstmtSusFind = null;
		ResultSet rset = null;
		ResultSet rsetFind = null;
		String cClave = "";
		String cFiltro = "";
		TDTOXSupuestoPost dSupuestoPost = new TDTOXSupuestoPost();
		TVSupuestoPost vSupuestoPost = new TVSupuestoPost();

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			TVPbaRapida vPbaRapida = (TVPbaRapida) obj;
			Vector vcSustanciaApi = (Vector) obSusApi;
			Vector vTemp = new Vector();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int j = 0;

			String cSQL = "";
			String cSQLFind;
			String cRes = "";

			cSQLFind = "select " + "iAnio,iCvePbaRapida,iCvePersonal "
					+ "from TOXPbaRapida "
					+ "where iAnio = ? and iCvePbaRapida = ? ";
			pstmtFind = conn.prepareStatement(cSQLFind);
			pstmtFind.setInt(1, vPbaRapida.getIAnio());
			pstmtFind.setInt(2, vPbaRapida.getICvePbaRapida());
			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				cRes = "" + rset.getInt(3);
				if (cRes.compareTo("0") != 0) {
					j = 1;
				}
			}

			if (j == 0) {
				cSQL = "update TOXPbaRapida"
						+ " set dtAsignacion= ?,"
						+ "iCveUniMed= ?, "
						+ "iCveProceso= ?, "
						+ "dtAplicacion= ?, "
						+ "iCvePersonal= ?, "
						+ "iCveUsuAplica= ?, "
						+ "iCveMotivo= ?, "
						+ "lPosiblePost= ?, "
						+ "dtCaptura = ?, "
						+ "iCveUsuCaptura = ?, "
						+ "iCveModulo = ?, "
						+ "iCveMdoTrans = ? "
						+ " where iAnio = ? and iCvePbaRapida = ? And iCveUniMed= ?";

				pstmt = conn.prepareStatement(cSQL);
				TFechas dtFecha = new TFechas();
				vPbaRapida.setDtAsignacion(dtFecha.TodaySQL());
				// vPbaRapida.setDtAplicacion(dtFecha.TodaySQL());
				vPbaRapida.setDtCaptura(dtFecha.TodaySQL());
				pstmt.setDate(1, vPbaRapida.getDtAsignacion());
				pstmt.setInt(2, vPbaRapida.getICveUniMed());
				pstmt.setInt(3, vPbaRapida.getICveProceso());
				pstmt.setDate(4, vPbaRapida.getDtAplicacion());
				pstmt.setInt(5, vPbaRapida.getICvePersonal());
				pstmt.setInt(6, vPbaRapida.getICveUsuAplica());
				pstmt.setInt(7, vPbaRapida.getICveMotivo());
				pstmt.setInt(8, vPbaRapida.getLPosiblePost());
				pstmt.setDate(9, vPbaRapida.getDtCaptura());
				pstmt.setInt(10, vPbaRapida.getICveUsuCaptura());
				pstmt.setInt(11, vPbaRapida.getICveModulo());
				pstmt.setInt(12, vPbaRapida.getICveMdoTrans());
				pstmt.setInt(13, vPbaRapida.getIAnio());
				pstmt.setInt(14, vPbaRapida.getICvePbaRapida());
				pstmt.setInt(15, vPbaRapida.getICveUniMed());

				pstmt.executeUpdate();

				int i;
				if (vcSustanciaApi.isEmpty() == false) {
					String cSQLSusApi = "";
					for (i = 0; i < vcSustanciaApi.size(); i++) {
						TVSustancia vSustancia = (TVSustancia) vcSustanciaApi
								.get(i);

						cFiltro = "  where TOXSupuestoPost.iAnio = "
								+ vPbaRapida.getIAnio()
								+ "    and TOXSupuestoPost.iCvePbaRapida = "
								+ vPbaRapida.getICvePbaRapida()
								+ "    and TOXSupuestoPost.iCveSustancia = "
								+ vSustancia.getICveSustancia();

						vTemp = dSupuestoPost.FindByAll(cFiltro, "");
						if (vTemp.size() <= 0) {
							vSupuestoPost.setIAnio(vPbaRapida.getIAnio());
							vSupuestoPost.setICvePbaRapida(vPbaRapida
									.getICvePbaRapida());
							vSupuestoPost.setICveSustancia(vSustancia
									.getICveSustancia());
							vSupuestoPost.setLResultado(1);
							dSupuestoPost.insert(null, vSupuestoPost);
						}
						pstmtSusFind = null;
						rsetFind = null;
					}
				}

			} else {
				cClave = "ERROR";
			}

			if (cClave.compareTo("null") == 0 && cClave.compareTo("") == 0) {
				cClave = "" + vPbaRapida.getIAnio();
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
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (rsetFind != null) {
					rsetFind.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtSusApi != null) {
					pstmtSusApi.close();
				}
				if (pstmtSusFind != null) {
					pstmtSusFind.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
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
	 * Metodo InsertEMOEPIINV
	 */
	public Object InsertEMOEPIINV(Connection conGral, Object obj,
			Object obSusApi, Object obExamGenera, Object obExamAplica)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtSusApi = null;
		PreparedStatement pstmtExamGenera = null;
		PreparedStatement pstmtUpdateExamGenera = null;
		ResultSet rset = null;
		ResultSet rsetExamGenera = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			TVPbaRapida vPbaRapida = (TVPbaRapida) obj;
			Vector vcSustanciaApi = (Vector) obSusApi;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int j = 0;

			String cSQL = "";
			String cSQLFind;
			String cRes = "";
			String cSQLExamAplica = "";

			cSQLFind = "select " + "iAnio,iCvePbaRapida,iCvePersonal "
					+ "from TOXPbaRapida "
					+ "where iAnio = ? and iCvePbaRapida = ? ";
			pstmtFind = conn.prepareStatement(cSQLFind);
			pstmtFind.setInt(1, vPbaRapida.getIAnio());
			pstmtFind.setInt(2, vPbaRapida.getICvePbaRapida());
			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				cRes = "" + rset.getInt(3);
				if (cRes.compareTo("0") != 0) {
					j = 1;
				}
			}

			if (j == 0) {
				cSQL = "update TOXPbaRapida"
						+ " set dtAsignacion= ?,"
						+ "iCveUniMed= ?, "
						+ "iCveProceso= ?, "
						+ "dtAplicacion= ?, "
						+ "iCvePersonal= ?, "
						+ "iCveUsuAplica= ?, "
						+ "iCveMotivo= ?, "
						+ "lPosiblePost= ?, "
						+ "dtCaptura = ?, "
						+ "iCveUsuCaptura = ?, "
						+ "iCveModulo = ?, "
						+ "iCveMdoTrans = ? "
						+ " where iAnio = ? and iCvePbaRapida = ? And iCveUniMed= ?";

				pstmt = conn.prepareStatement(cSQL);
				TFechas dtFecha = new TFechas();
				vPbaRapida.setDtAsignacion(dtFecha.TodaySQL());
				// vPbaRapida.setDtAplicacion(dtFecha.TodaySQL());
				vPbaRapida.setDtCaptura(dtFecha.TodaySQL());
				pstmt.setDate(1, vPbaRapida.getDtAsignacion());
				pstmt.setInt(2, vPbaRapida.getICveUniMed());
				pstmt.setInt(3, vPbaRapida.getICveProceso());
				pstmt.setDate(4, vPbaRapida.getDtAplicacion());
				pstmt.setInt(5, vPbaRapida.getICvePersonal());
				pstmt.setInt(6, vPbaRapida.getICveUsuAplica());
				pstmt.setInt(7, vPbaRapida.getICveMotivo());
				pstmt.setInt(8, vPbaRapida.getLPosiblePost());
				pstmt.setDate(9, vPbaRapida.getDtCaptura());
				pstmt.setInt(10, vPbaRapida.getICveUsuCaptura());
				pstmt.setInt(11, vPbaRapida.getICveModulo());
				pstmt.setInt(12, vPbaRapida.getICveMdoTrans());
				pstmt.setInt(13, vPbaRapida.getIAnio());
				pstmt.setInt(14, vPbaRapida.getICvePbaRapida());
				pstmt.setInt(15, vPbaRapida.getICveUniMed());

				pstmt.executeUpdate();

				int i;
				if (vcSustanciaApi.isEmpty() == false) {
					String cSQLSusApi = "";
					cSQLSusApi = "insert into TOXSupuestoPost(" + "iAnio,"
							+ "iCvePbaRapida," + "iCveSustancia,"
							+ "lResultado" + ")values(?,?,?,?)";

					pstmtSusApi = conn.prepareStatement(cSQLSusApi);

					for (i = 0; i < vcSustanciaApi.size(); i++) {
						TVSustancia vSustancia = (TVSustancia) vcSustanciaApi
								.get(i);
						pstmtSusApi.setInt(1, vPbaRapida.getIAnio());
						pstmtSusApi.setInt(2, vPbaRapida.getICvePbaRapida());
						pstmtSusApi.setInt(3, vSustancia.getICveSustancia());
						pstmtSusApi.setInt(4, 1);
						pstmtSusApi.executeUpdate();
					}
				}

				// Insercion de EXPExamAplica
				TVEXPExamGenera vExamGenera;
				TVEXPExamAplica vExamAplica;
				TDEXPExamAplica dExamAplica = new TDEXPExamAplica();
				String cExamGenera = "";
				String cRetornoExamAplica = "";
				int w = 0;

				vExamGenera = (TVEXPExamGenera) obExamGenera;
				vExamAplica = (TVEXPExamAplica) obExamAplica;
				cSQLExamAplica = "select iNumExamenGenera from EXPExamGenera "
						+ "where iCveExpediente = ? and iNumExamen = ? and iCveProceso = ? ";
				pstmtExamGenera = conn.prepareStatement(cSQLExamAplica);
				pstmtExamGenera.setInt(1, vExamGenera.getICveExpediente());
				pstmtExamGenera.setInt(2, vExamGenera.getINumExamen());
				pstmtExamGenera.setInt(3, vExamGenera.getICveProceso());
				rsetExamGenera = pstmtExamGenera.executeQuery();
				while (rsetExamGenera.next()) {
					cExamGenera = "" + rsetExamGenera.getInt(1);
					if (cExamGenera.compareTo("0") == 0) {
						w = 1;
					}
				}

				if (w == 1) {
					cRetornoExamAplica = (String) dExamAplica.insertPR(null,
							vExamAplica);
					if (Integer.parseInt(cRetornoExamAplica) > 0) {
						cSQLExamAplica = "Update EXPExamGenera "
								+ "set iNumExamenGenera = ? "
								+ "where iCveExpediente = ? and iNumExamen = ? and iCveProceso = ? ";
						pstmtUpdateExamGenera = conn
								.prepareStatement(cSQLExamAplica);
						pstmtUpdateExamGenera.setInt(1,
								Integer.parseInt(cRetornoExamAplica));
						pstmtUpdateExamGenera.setInt(2,
								vExamGenera.getICveExpediente());
						pstmtUpdateExamGenera.setInt(3,
								vExamGenera.getINumExamen());
						pstmtUpdateExamGenera.setInt(4,
								vExamGenera.getICveProceso());
						pstmtUpdateExamGenera.executeUpdate();
					}

				}
			} else {
				cClave = "ERROR";
			}

			if (cClave.compareTo("null") == 0 && cClave.compareTo("") == 0) {
				cClave = "" + vPbaRapida.getIAnio();
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
				warn("InsertEMOEPIINV", ex1);
			}
			warn("InsertEMOEPIINV", ex);
			throw new DAOException("");
		} finally {
			try {

				if (rsetExamGenera != null) {
					rsetExamGenera.close();
				}
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtSusApi != null) {
					pstmtSusApi.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
				}
				if (pstmtExamGenera != null) {
					pstmtExamGenera.close();
				}

				if (pstmtUpdateExamGenera != null) {
					pstmtUpdateExamGenera.close();
				}

				if (conGral == null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("InsertEMOEPIINV.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo InsertFromALM
	 */
	public boolean insertFromALM(int iAnio, int iUniMed, int iNumRegs)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		ResultSet rset = null;
		String cSQL = "";
		String cSQLFind = "";
		boolean lRetorno = true;
		int iPruebaIni = 0, iPruebaFin = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLFind = "select max(iCvePbaRapida) " + "from TOXPbaRapida "
					+ "where iAnio = " + iAnio;
			pstmtFind = conn.prepareStatement(cSQLFind);
			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				iPruebaIni = rset.getInt(1);
			}
			iPruebaIni++;
			iPruebaFin = iPruebaIni + iNumRegs;
			cSQL = "insert into TOXPbaRapida(" + "iAnio," + "iCvePbaRapida,"
					+ "iCveUniMed," + "iCveUsuAplica," + "iCveUsuCaptura,"
					+ "lPosiblePost" + ")values(?,?,?,?,?,?)";
			for (int i = iPruebaIni; i < iPruebaFin; i++) {
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, iAnio);
				pstmt.setInt(2, i);
				pstmt.setInt(3, iUniMed);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
				pstmt.setInt(6, 0);
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (Exception ex) {
			lRetorno = false;
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("insertFromALM", ex1);
			}
			warn("insertFromALM", ex);
			throw new DAOException("");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insertFromALM.close", ex2);
			}
		}
		return lRetorno;
	}
	
	/**
	 * Metodo InsertFromALM
	 */
	public int insertPBAFCCC(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		ResultSet rset = null;
		String cSQL = "";
		String cSQLFind = "";
		boolean lRetorno = true;
		int iPruebaIni = 0, iPruebaFin = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			TVPbaRapida vPbaRapida = (TVPbaRapida) obj;

			cSQLFind = "select max(iCvePbaRapida) " + "from TOXPbaRapida "
					+ "where iAnio = " + vPbaRapida.getIAnio();
			pstmtFind = conn.prepareStatement(cSQLFind);
			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				iPruebaIni = rset.getInt(1);
			}
			iPruebaIni++;

			cSQL = "INSERT INTO TOXPBARAPIDA VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vPbaRapida.getIAnio());
				pstmt.setInt(2, iPruebaIni);
				pstmt.setDate(3, vPbaRapida.getDtCaducidad());
				pstmt.setDate(4, vPbaRapida.getDtAsignacion());
				pstmt.setInt(5, vPbaRapida.getICveUniMed());
				pstmt.setInt(6, vPbaRapida.getICveProceso());
				pstmt.setDate(7, vPbaRapida.getDtAplicacion());
				pstmt.setInt(8, vPbaRapida.getICvePersonal());
				pstmt.setInt(9, vPbaRapida.getICveUsuAplica());
				pstmt.setDate(10, vPbaRapida.getDtCaptura());
				pstmt.setInt(11, vPbaRapida.getICveUsuCaptura());
				pstmt.setInt(12, vPbaRapida.getICveMotivo());
				pstmt.setInt(13, vPbaRapida.getLPosiblePost());
				pstmt.setInt(14, vPbaRapida.getICveMdoTrans());
				pstmt.setInt(15, vPbaRapida.getICveModulo());
				pstmt.setInt(16, vPbaRapida.getLAnfetaminas());
				pstmt.setInt(17, vPbaRapida.getLCanabis());
				pstmt.setInt(18, vPbaRapida.getLCocaina());
				pstmt.setInt(19, vPbaRapida.getLOpiaceos());
				pstmt.setInt(20, vPbaRapida.getLFenciclidina());
				pstmt.setInt(21, vPbaRapida.getLMetanfetaminas());
				pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			lRetorno = false;
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("insertPBAFCCC", ex1);
			}
			warn("insertPBAFCCC", ex);
			throw new DAOException("");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insertPBAFCCC.close", ex2);
			}
		}
		return iPruebaIni;
	}
	


	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllFCC(int ianio, int icvepersonal, int icvepbarapida) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPbaRapida = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPbaRapida vPbaRapida;
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCvePbaRapida," + "dtCaducidad,"
					+ "dtAsignacion," + "ToxPbaRapida.iCveUniMed,"
					+ "ToxPbaRapida.iCveProceso," + "dtAplicacion,"
					+ "iCvePersonal," + "iCveUsuAplica," + "dtCaptura,"
					+ "iCveUsuCaptura," + "ToxPbaRapida.iCveMotivo,"
					+ "lPosiblePost," + "cDscUniMed," + "cDscProceso,"
					+ "cDscMotivo," + "cNombre," + "cApPaterno,"
					+ "cApMaterno," + "cDscModulo," + "cDscMdoTrans, "
					+ "TOXPbaRapida.iCveModulo, "
					+ "TOXPbaRapida.iCveMdoTrans, " 
					+ "TOXPbaRapida.LANFETAMINAS, "
					+ "TOXPbaRapida.LCANNABINOIDES, "
					+ "TOXPbaRapida.LCOCAINA, "
					+ "TOXPbaRapida.LOPIACEOS, "
					+ "TOXPbaRapida.LFENCICLIDINA, "
					+ "TOXPbaRapida.LMETANFETAMINAS "
					+ " from TOXPbaRapida ";
			cSQL = cSQL + " inner join GRLUnimed ON "
					+ " GrlUniMed.iCveUniMed = ToxPbaRapida.iCveUniMed ";
			cSQL = cSQL + " inner join GRLProceso ON "
					+ " GRLProceso.iCveProceso = ToxPbaRapida.iCveProceso ";
			cSQL = cSQL + " inner join GRLMotivo ON " +
			// " GRLMotivo.iCveProceso = ToxPbaRapida.iCveProceso " +
					" GRLMotivo.iCveMotivo = ToxPbaRapida.iCveMotivo ";
			cSQL = cSQL + " inner join SEGUsuario ON "
					+ " SEGUsuario.iCveUsuario = ToxPbaRapida.iCveUsuAplica ";
			cSQL = cSQL
					+ " inner join GRLModulo ON"
					+ " GRLModulo.iCveUniMed = ToxPbaRapida.iCveUniMed and GRLModulo.iCveModulo = ToxPbaRapida.iCveModulo ";
			cSQL = cSQL + " inner join GRLMdoTrans ON"
					+ " GRLMdoTrans.iCveMdoTrans = ToxPbaRapida.iCveMdoTrans ";

			cSQL = cSQL + " where iAnio = ? " + " and iCvePbaRapida = ? "
						+ " and iCvePersonal = ? ";
				cSQL = cSQL + " order by iAnio";

			
			

			//System.out.println("FindByAllFCC PbaRapida: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, ianio);
				pstmt.setInt(2, icvepbarapida);
				pstmt.setInt(3, icvepersonal);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPbaRapida = new TVPbaRapida();
				vPbaRapida.setIAnio(rset.getInt(1));
				vPbaRapida.setICvePbaRapida(rset.getInt(2));
				vPbaRapida.setDtCaducidad(rset.getDate(3));
				vPbaRapida.setDtAsignacion(rset.getDate(4));
				vPbaRapida.setICveUniMed(rset.getInt(5));
				vPbaRapida.setICveProceso(rset.getInt(6));
				vPbaRapida.setDtAplicacion(rset.getDate(7));
				vPbaRapida.setICvePersonal(rset.getInt(8));
				vPbaRapida.setICveUsuAplica(rset.getInt(9));
				vPbaRapida.setDtCaptura(rset.getDate(10));
				vPbaRapida.setICveUsuCaptura(rset.getInt(11));
				vPbaRapida.setICveMotivo(rset.getInt(12));
				vPbaRapida.setLPosiblePost(rset.getInt(13));
				vPbaRapida.setCDscUM(rset.getString(14));
				vPbaRapida.setCDscProceso(rset.getString(15));
				vPbaRapida.setCDscMotivo(rset.getString(16));
				vPbaRapida.setCDscUsuAplica(rset.getString(17) + " "
						+ rset.getString(18) + " " + rset.getString(19));
				vPbaRapida.setCDscModulo(rset.getString(20));
				vPbaRapida.setCDscMdoTrans(rset.getString(21));
				vPbaRapida.setICveModulo(rset.getInt(22));
				vPbaRapida.setICveMdoTrans(rset.getInt(23));
				vPbaRapida.setLAnfetaminas(rset.getInt(24));
				vPbaRapida.setLCanabis(rset.getInt(25));
				vPbaRapida.setLCocaina(rset.getInt(26));
				vPbaRapida.setLOpiaceos(rset.getInt(27));
				vPbaRapida.setLFenciclidina(rset.getInt(28));
				vPbaRapida.setLMetanfetaminas(rset.getInt(29));

				if (vPbaRapida.getDtCaducidad() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtCaducidad(), "/");
					vPbaRapida.setCDtCaducidad(cFecha);
				} else {
					vPbaRapida.setCDtCaducidad("");
				}

				if (vPbaRapida.getDtAsignacion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtAsignacion(), "/");
					vPbaRapida.setCDtAsignacion(cFecha);
				} else {
					vPbaRapida.setCDtAsignacion("");
				}

				if (vPbaRapida.getDtAplicacion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtAplicacion(), "/");
					vPbaRapida.setCDtAplicacion(cFecha);
				} else {
					vPbaRapida.setCDtAplicacion("");
				}

				if (vPbaRapida.getDtCaptura() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPbaRapida.getDtCaptura(), "/");
					vPbaRapida.setCDtCaptura(cFecha);
				} else {
					vPbaRapida.setCDtCaptura("");
				}

				vcPbaRapida.addElement(vPbaRapida);
			}
		} catch (Exception ex) {
			warn("FindByAllFCC", ex);
			throw new DAOException("FindByAllFCC");
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
				warn("FindByAllFCC.close", ex2);
			}
			return vcPbaRapida;
		}
	}

	
}


