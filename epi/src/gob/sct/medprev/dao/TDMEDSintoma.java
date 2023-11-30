package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.Random;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.TVUsuario;
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

public class TDMEDSintoma extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDSintoma() {
	}

	/**
	 * Metodo Find By All
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
			TVMEDSintoma vMEDSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "a.iCveServicio,"
					+ "a.iCveRama,"
					+ "a.iCveSintoma,"
					+ "a.cDscBreve,"
					+ "a.cPregunta,"
					+ "a.cGenero,"
					+ "a.lEstudio,"
					+ "a.iCveTpoResp,"
					+ "a.cEtiqueta,"
					+ "a.lCPersonal,"
					+ "a.lObligatorio,"
					+ "a.lEvalAuto,"
					+ "a.lActivo,"
					+ "cDscTpoResp, cDscServicio, cDscRama, a.iOrden, a.cValRef "
					+ " from MEDSintomas a"
					+ " left join  MedTpoResp on MedTpoResp.iCveTpoResp = a.iCveTpoResp "
					+ " left join  MedServicio on MedServicio.iCveServicio = a.iCveServicio "
					+ " left join  MedRama on MedRama.iCveRama = a.iCveRama "
					+ "            And MedRama.iCveServicio = a.iCveServicio "
					+ cWhere;

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintoma = new TVMEDSintoma();
				vMEDSintoma.setICveServicio(rset.getInt(1));
				vMEDSintoma.setICveRama(rset.getInt(2));
				vMEDSintoma.setICveSintoma(rset.getInt(3));
				vMEDSintoma.setCDscBreve(rset.getString(4));
				vMEDSintoma.setCPregunta(rset.getString(5));
				vMEDSintoma.setCGenero(rset.getString(6));
				vMEDSintoma.setLEstudio(rset.getInt(7));
				vMEDSintoma.setICveTpoResp(rset.getInt(8));
				vMEDSintoma.setCEtiqueta(rset.getString(9));
				vMEDSintoma.setLCPersonal(rset.getInt(10));
				vMEDSintoma.setLObligatorio(rset.getInt(11));
				vMEDSintoma.setLEvalAuto(rset.getInt(12));
				vMEDSintoma.setLActivo(rset.getInt(13));
				vMEDSintoma.setCDscTpoResp(rset.getString(14));
				vMEDSintoma.setCDscServicio(rset.getString(15));
				vMEDSintoma.setCDscRama(rset.getString(16));
				vMEDSintoma.setIOrden(rset.getInt(17));
				vMEDSintoma.setCValRef(rset.getString(18));

				vcMEDSintoma.addElement(vMEDSintoma);
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
	 * Metodo Find By All
	 */
	public Vector FindByAllExaEmo(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintoma vMEDSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "a.iCveServicio,"
					+ "a.iCveRama,"
					+ "a.iCveSintoma,"
					+ "a.cDscBreve,"
					+ "a.cPregunta,"
					+ "a.cGenero,"
					+ "a.lEstudio,"
					+ "a.iCveTpoResp,"
					+ "a.cEtiqueta,"
					+ "a.lCPersonal,"
					+ "a.lObligatorio,"
					+ "a.lEvalAuto,"
					+ "a.lActivo,"
					+ "cDscTpoResp, cDscServicio, cDscRama, a.iOrden, a.cValRef "
					+ " from MEDSintomas a"
					+ " left join  MedTpoResp on MedTpoResp.iCveTpoResp = a.iCveTpoResp "
					+ " left join  MedServicio on MedServicio.iCveServicio = a.iCveServicio "
					+ " left join  MedRama on MedRama.iCveRama = a.iCveRama "
					+ "            And MedRama.iCveServicio = a.iCveServicio "
					+ " left join MedSintExamenTMP on MedSintExamenTMP.iCveServicio = a.iCveServicio and "
					+ "                               MedSintExamenTMP.icverama = a.icverama and "
					+ "                               MedSintExamenTMP.icvesintoma = a.icvesintoma "
					+ cWhere;
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintoma = new TVMEDSintoma();
				vMEDSintoma.setICveServicio(rset.getInt(1));
				vMEDSintoma.setICveRama(rset.getInt(2));
				vMEDSintoma.setICveSintoma(rset.getInt(3));
				vMEDSintoma.setCDscBreve(rset.getString(4));
				vMEDSintoma.setCPregunta(rset.getString(5));
				vMEDSintoma.setCGenero(rset.getString(6));
				vMEDSintoma.setLEstudio(rset.getInt(7));
				vMEDSintoma.setICveTpoResp(rset.getInt(8));
				vMEDSintoma.setCEtiqueta(rset.getString(9));
				vMEDSintoma.setLCPersonal(rset.getInt(10));
				vMEDSintoma.setLObligatorio(rset.getInt(11));
				vMEDSintoma.setLEvalAuto(rset.getInt(12));
				vMEDSintoma.setLActivo(rset.getInt(13));
				vMEDSintoma.setCDscTpoResp(rset.getString(14));
				vMEDSintoma.setCDscServicio(rset.getString(15));
				vMEDSintoma.setCDscRama(rset.getString(16));
				vMEDSintoma.setIOrden(rset.getInt(17));
				vMEDSintoma.setCValRef(rset.getString(18));

				vcMEDSintoma.addElement(vMEDSintoma);
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
	 * Metodo Find By All
	 */
	public Vector FindByAllDep(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintoma vMEDSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveServicio," + "iCveRama," + "iCveSintoma,"
					+ "cDscBreve," + "cPregunta," + "cGenero," + "lEstudio,"
					+ "iCveTpoResp," + "cEtiqueta," + "lCPersonal,"
					+ "lObligatorio," + "lEvalAuto," + "lActivo,"
					+ "iOrden, cValRef " + " from MEDSintomas " + cWhere;

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintoma = new TVMEDSintoma();
				vMEDSintoma.setICveServicio(rset.getInt(1));
				vMEDSintoma.setICveRama(rset.getInt(2));
				vMEDSintoma.setICveSintoma(rset.getInt(3));
				vMEDSintoma.setCDscBreve(rset.getString(4));
				vMEDSintoma.setCPregunta(rset.getString(5));
				vMEDSintoma.setCGenero(rset.getString(6));
				vMEDSintoma.setLEstudio(rset.getInt(7));
				vMEDSintoma.setICveTpoResp(rset.getInt(8));
				vMEDSintoma.setCEtiqueta(rset.getString(9));
				vMEDSintoma.setLCPersonal(rset.getInt(10));
				vMEDSintoma.setLObligatorio(rset.getInt(11));
				vMEDSintoma.setLEvalAuto(rset.getInt(12));
				vMEDSintoma.setLActivo(rset.getInt(13));
				vMEDSintoma.setIOrden(rset.getInt(14));
				vMEDSintoma.setCValRef(rset.getString(15));

				vcMEDSintoma.addElement(vMEDSintoma);
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

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintoma vMEDSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select "
					+ " MEDSintomas.iCveServicio,"
					+ " MEDSintomas.iCveRama,"
					+ " MEDSintomas.iCveSintoma,"
					+ " MEDSintomas.cDscBreve,"
					+ " MEDSintomas.cPregunta,"
					+ " MEDSintomas.cGenero,"
					+ " MEDSintomas.lEstudio,"
					+ " MEDSintomas.iCveTpoResp,"
					+ " MEDSintomas.cEtiqueta,"
					+ " MEDSintomas.lCPersonal,"
					+ " MEDSintomas.lObligatorio,"
					+ " MEDSintomas.lEvalAuto,"
					+ " MEDSintomas.lActivo "
					+ " from MEDSintomas "
					+ " join MEDTpoResp on MEDTpoResp.iCveTpoResp = MEDSintomas.iCveTpoResp "
					+ "  and MEDTpoResp.lActivo = 1 " + cWhere + "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintoma = new TVMEDSintoma();
				vMEDSintoma.setICveServicio(rset.getInt(1));
				vMEDSintoma.setICveRama(rset.getInt(2));
				vMEDSintoma.setICveSintoma(rset.getInt(3));
				vMEDSintoma.setCDscBreve(rset.getString(4));
				vMEDSintoma.setCPregunta(rset.getString(5));
				vMEDSintoma.setCGenero(rset.getString(6));
				vMEDSintoma.setLEstudio(rset.getInt(7));
				vMEDSintoma.setICveTpoResp(rset.getInt(8));
				vMEDSintoma.setCEtiqueta(rset.getString(9));
				vMEDSintoma.setLCPersonal(rset.getInt(10));
				vMEDSintoma.setLObligatorio(rset.getInt(11));
				vMEDSintoma.setLEvalAuto(rset.getInt(12));
				vMEDSintoma.setLActivo(rset.getInt(13));
				vcMEDSintoma.addElement(vMEDSintoma);
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
	 * Devuelve las claves y descripciones de los sintomas, de las ramas y de
	 * los servicios activos, o de un servicio activo espec�fico.
	 * 
	 * @param p
	 *            Un HashMap con los par�metros requeridos con la consulta
	 * @return un Vector con los value objects obtenidos durante la consulta
	 * @throws DAOException
	 * @author Romeo Sanchez
	 */
	public Vector findSintomaRamaServicio(HashMap p) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		int iCveServicio = 0;
		int iNumExamen = 0;
		int iCveExpediente = 0;
		String cGenero = "";

		if (p.get("iCveExpediente") != null
				&& !p.get("iCveExpediente").equals("")) {
			iCveExpediente = Integer.parseInt(p.get("iCveExpediente")
					.toString());
		}
		if (p.get("iCveServicio") != null && !p.get("iCveServicio").equals("")) {
			iCveServicio = Integer.parseInt(p.get("iCveServicio").toString());
		}
		if (p.get("iNumExamen") != null && !p.get("iNumExamen").equals("")) {
			iNumExamen = Integer.parseInt(p.get("iNumExamen").toString());
		}
		if (p.get("cGenero") != null && !p.get("cGenero").equals("")
				&& !p.get("cGenero").equals("null")) {
			cGenero = p.get("cGenero").toString();
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintoma vMEDSintoma;
			int count;
			cSQL = " select "
					+ // buscar todos los sintomas de los servicios...
					" MEDServicio.iCveServicio, " + " MEDRama.iCveRama, "
					+ " MEDSintomas.iCveSintoma, "
					+ " MEDServicio.cDscServicio, " + " MEDRama.cDscRama, "
					+ " MEDSintomas.cDscBreve, "
					+ "     (select count(*) from EXPResultado  "
					+ "     where ";
			if (iCveExpediente != 0) {
				cSQL += " iCveExpediente = ? ";
			}
			if (iNumExamen != 0) {
				cSQL += " AND iNumExamen = ? ";
			}
			if (iCveServicio != 0) {
				cSQL += " AND iCveServicio = ? ";

			}
			cSQL += "     AND iCveRama=MEDSintomas.iCveRama AND iCveSintoma=MEDSintomas.iCveSintoma) as check "
					+ " from  "
					+ " MEDSintomas JOIN MEDRama ON  "
					+ "      (MEDSintomas.iCveServicio = MEDRama.iCveServicio AND MEDSintomas.iCveRama = MEDRama.iCveRama)  "
					+ " JOIN MEDServicio ON  "
					+ "      (MEDSintomas.iCveServicio = MEDServicio.iCveServicio) ";
			if (iCveServicio != 0) {
				cSQL += " AND MEDServicio.iCveServicio = ?  "; // (en caso de
																// pedir un
																// servicio
																// espec�fico)
			}
			if (!cGenero.equals("")) {
				cSQL += " AND ( MEDSintomas.cGenero = ? OR MEDSintomas.cGenero = 'A' ) "; // para
																							// el
																							// g�nero
																							// correspondiente
			}
			cSQL += " AND MEDServicio.lInterConsulta = 1 "
					+ // ...marcados como interconsulta
					" AND MEDServicio.lActivo = 1 "
					+ // ...y activos
					" AND MEDRama.lActivo = 1 "
					+ " AND MEDSintomas.lActivo = 1 "
					+ " order by  "
					+ " MEDServicio.iCveServicio, MEDRama.iCveRama, MEDSintomas.iCveSintoma "; // ordenados
																								// para
																								// recorrerlos

			pstmt = conn.prepareStatement(cSQL);
			log(" parametros recibidos: " + p.toString());
			log(".findSintomaRamaServicio (SQL): " + cSQL);

			int idx = 1;
			if (iCveExpediente != 0) {
				pstmt.setInt(idx++, iCveExpediente);
			}
			if (iNumExamen != 0) {
				pstmt.setInt(idx++, iNumExamen);
			}
			if (iCveServicio != 0) {
				pstmt.setInt(idx++, iCveServicio);
				pstmt.setInt(idx++, iCveServicio);
			}
			if (!cGenero.equals("")) {
				pstmt.setString(idx++, cGenero);
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintoma = new TVMEDSintoma();
				vMEDSintoma.setICveServicio(rset.getInt(1));
				vMEDSintoma.setICveRama(rset.getInt(2));
				vMEDSintoma.setICveSintoma(rset.getInt(3));
				vMEDSintoma.setCDscServicio(rset.getString(4));
				vMEDSintoma.setCDscRama(rset.getString(5));
				vMEDSintoma.setCDscBreve(rset.getString(6));
				vMEDSintoma.setLActivo(rset.getInt(7)); // si existe el registro
														// en EXPResultado, para
														// no agregar un campo
				vcMEDSintoma.addElement(vMEDSintoma);
			}
		} catch (Exception ex) {
			warn("findSintomaRamaServicio", ex);
			throw new DAOException("findSintomaRamaServicio");
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
				warn("findSintomaRamaServicio.close", ex2);
			}
		}
		log("sintomas obtenidos: " + vcMEDSintoma.size());
		return vcMEDSintoma;
	}

	/**
	 * Metodo update - NO BORRAR JMG
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
			TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDSintomas" + " set cDscBreve = ?,"
					+ "  cPregunta = ?," + "  cGenero = ?," + "  lEstudio = ?,"
					+ "  iCveTpoResp = ?," + "  cEtiqueta = ?,"
					+ "  lCPersonal = ?," + "  lObligatorio = ?,"
					+ "  lEvalAuto = ?," + "  lActivo = ?," + "  cValRef = ?"
					+ " where iCveServicio =?" + " And   iCveRama =?"
					+ " And   iCveSintoma =?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDSintoma.getCDscBreve());
			pstmt.setString(2, vMEDSintoma.getCPregunta());
			pstmt.setString(3, vMEDSintoma.getCGenero());
			pstmt.setInt(4, vMEDSintoma.getLEstudio());
			pstmt.setInt(5, vMEDSintoma.getICveTpoResp());
			pstmt.setString(6, vMEDSintoma.getCEtiqueta());
			pstmt.setInt(7, vMEDSintoma.getLCPersonal());
			pstmt.setInt(8, vMEDSintoma.getLObligatorio());
			pstmt.setInt(9, vMEDSintoma.getLEvalAuto());
			pstmt.setInt(10, vMEDSintoma.getLActivo());
			pstmt.setString(11, vMEDSintoma.getCValRef());
			pstmt.setInt(12, vMEDSintoma.getICveServicio());
			pstmt.setInt(13, vMEDSintoma.getICveRama());
			pstmt.setInt(14, vMEDSintoma.getICveSintoma());
			pstmt.executeUpdate();
			cClave = "" + vMEDSintoma.getICveServicio() + "|"
					+ vMEDSintoma.getICveRama() + "|"
					+ vMEDSintoma.getICveSintoma();
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
	 * Metodo update Orden de las Ramas
	 */
	public Object updateOrdenPreguntas(Connection conGral, Vector vcPreguntas)
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

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDSintomas" + " set iOrden= ? "
					+ "where iCveServicio = ? " + " and iCveRama = ?"
					+ " and iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			for (int i = 0; i < vcPreguntas.size(); i++) {
				TVMEDSintoma vMEDSintoma = (TVMEDSintoma) vcPreguntas.get(i);
				pstmt.setInt(1, vMEDSintoma.getIOrden());
				pstmt.setInt(2, vMEDSintoma.getICveServicio());
				pstmt.setInt(3, vMEDSintoma.getICveRama());
				pstmt.setInt(4, vMEDSintoma.getICveSintoma());
				pstmt.executeUpdate();
			}
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
	 * Metodo disable - No BORRAR JMG
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDSintomas" + " set lActivo = ?"
					+ " where iCveServicio =?" + " And   iCveRama =?"
					+ " And   iCveSintoma =?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vMEDSintoma.getICveServicio());
			pstmt.setInt(3, vMEDSintoma.getICveRama());
			pstmt.setInt(4, vMEDSintoma.getICveSintoma());
			pstmt.executeUpdate();
			cClave = "" + vMEDSintoma.getICveSintoma();
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
	 * Metodo Insert - NO BORRAR JMG
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
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
			TVMEDSintoma vMEDSintoma = (TVMEDSintoma) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDSintomas(" + "  iCveServicio,"
					+ "  iCveRama," + "  iCveSintoma," + "  cDscBreve,"
					+ "  cPregunta," + "  cGenero," + "  lEstudio,"
					+ "  iCveTpoResp," + "  cEtiqueta," + "  lCPersonal,"
					+ "  lObligatorio," + "  lEvalAuto," + "  lActivo,"
					+ "  iOrden," + "  cValRef"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			String cSQLMax = "select max(iCveSintoma)+1 from MEDSintomas"
					+ " Where iCveServicio = " + vMEDSintoma.getICveServicio()
					+ " And iCveRama = " + vMEDSintoma.getICveRama();
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

			// ///////////////////////////////////
			pstmt.setInt(1, vMEDSintoma.getICveServicio());
			pstmt.setInt(2, vMEDSintoma.getICveRama());
			pstmt.setInt(3, iMax);
			pstmt.setString(4, vMEDSintoma.getCDscBreve());
			pstmt.setString(5, vMEDSintoma.getCPregunta());
			pstmt.setString(6, vMEDSintoma.getCGenero());
			pstmt.setInt(7, vMEDSintoma.getLEstudio());
			pstmt.setInt(8, vMEDSintoma.getICveTpoResp());
			pstmt.setString(9, vMEDSintoma.getCEtiqueta());
			pstmt.setInt(10, vMEDSintoma.getLCPersonal());
			pstmt.setInt(11, vMEDSintoma.getLObligatorio());
			pstmt.setInt(12, vMEDSintoma.getLEvalAuto());
			pstmt.setInt(13, vMEDSintoma.getLActivo());
			pstmt.setInt(14, iMax);
			pstmt.setString(15, vMEDSintoma.getCValRef());
			pstmt.executeUpdate();
			cClave = "" + iMax;
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
	 * Metodo Find By All
	 */
	public String Consultas(String SQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintoma = new Vector();
		String regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintoma vMEDSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = SQL;

			// System.out.println("==============   cSQL Consultas  ========: "
			// + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getString(1);
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
	 * Metodo Find By All
	 */
	public String Sentencia(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// Vector vcMEDSintoma = new Vector();
		String regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintoma vMEDSintoma;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				regresa = rset.getString(1);
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
	 * Metodo update Orden de las Ramas
	 */
	public boolean updateOrdenOfta() {

		DbConnection dbConn = null;
		Connection conn = null;
		DbConnection dbConn2 = null;
		Connection conn2 = null;
		DbConnection dbConn3 = null;
		Connection conn3 = null;

		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		TVUsuario vUsuario = null;
		boolean lSuccess = true;
		int aleatorio = 0;
		Random rnd = new Random();
		String lSQL = "";

		try {
			// Conexion MEDPREV
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			// Actualizando Sintoma 52 - 169 - 54 - 55
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (52,169)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (54)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (55)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 56 - 57 - 58 -59 - 60
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (56)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (57)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (58)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (59)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 5
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (60)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 61 - 62 - 63 -64 - 65
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (61)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (62)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (63)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (64)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 5
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (65)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 66 - 67 - 68 -69 - 70
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (66)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (67)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (68)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (69)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 5
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (70)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 71 - 72 - 73 -74 - 170
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (71)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (72)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (73)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (74,170)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 75 - 76 - 77 -78 - 79
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (75)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (76)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (77)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (78)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 5
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (79)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 80 - 81 - 82 - 83 - 168
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (80)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (81)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (82)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (83,168)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 84 - 85 - 86 -87 - 88
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (84)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (85)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (86)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (87)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 5
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (88)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

			// Actualizando Sintoma 89 - 90 - 91 - 92 - 93
			aleatorio = 0;
			aleatorio = (int) (rnd.nextDouble() * 500.0);
			if (aleatorio < 200) {
				aleatorio = aleatorio + 200;
			}
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 1
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (89)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 2
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (90)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 3
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (91)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 4
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (92)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();
			lSQL = "update medsintomas set Iorden = "
					+ aleatorio
					+ 5
					+ " where icveservicio = 67 and icverama = 1 and icvesintoma in (93)";
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.executeUpdate();

		} catch (Exception ex) {
			lSuccess = false;
			// System.out.println("cambioContrasenia");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("accesoUsuario.Close");
			}
			return lSuccess;
		}
	}

	/**
	 * Atributo que define si los mensajes de depuración se envían a la
	 * consola. Si se requiere que los mensajes enviados por el Metodo log() no
	 * se muestren, es necesario establecer su valor a <code>false</code>.
	 */
	private boolean debug = false;

	/**
	 * Metodo que env�a un Object a la consola, como String, si la bandera
	 * debug est� activada. El nombre cualificado de la clase se env�a antes
	 * del objeto.
	 * 
	 * @param obj
	 *            el objeto que ser� enviado a la consola como String
	 * @author Romeo Sanchez
	 */
	private void log(Object obj) {
		// ************* No modificar *************
		if (debug) {
			// // System.out.println(this.getClass().getName() + ":" +
			// obj.toString());
			;
		}
	}

}