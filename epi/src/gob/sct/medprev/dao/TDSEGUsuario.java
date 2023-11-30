package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.dwr.vo.ClaseDatosInicio;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de SEGUsuario DAO
 * </p>
 * <p>
 * Description: DAO de la entidad SEGUsuario que es replica de ADMSEG (Solo
 * Lectura)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class TDSEGUsuario extends DAOBase {
	private TParametro VParametros = new TParametro("07");

	public TDSEGUsuario() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		String dataSourceName2 = VParametros.getPropEspecifica("ConDB");
		DbConnection dbConn = null;
		Connection conn = null;
		DbConnection dbConn2 = null;
		Connection conn2 = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		Vector vcSEGUsuario = new Vector();
		String PassWd = "";
		try {

			dbConn2 = new DbConnection(dataSourceName2);
			conn2 = dbConn2.getConnection();
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(2);
			String SQLPas = "Select cPassword from SEGUsuario " + cWhere;
			System.out.println(SQLPas);
			pstmt2 = conn2.prepareStatement(SQLPas);
			rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				PassWd = rset2.getString(1);
			}

			
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSEGUsuario vSEGUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "iCveUsuario,"
					+ "dtRegistro,"
					+ "cUsuario,"
					+ "SEGUsuario.cNombre,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "cCalle,"
					+ "cColonia,"
					+ "grlpais.iCvePais,"
					+ "grlentidadfed.iCveEntidadFed,"
					+ "grlmunicipio.iCveMunicipio,"
					+ "iCodigoPostal,"
					+ "cTelefono,"
					+ "lBloqueado, grlpais.cnombre, grlentidadfed.cnombre, grlmunicipio.cnombre,"
					+ " cPassword "
					+ " from SEGUsuario "
					+ "join grlpais on segusuario.icvepais = grlpais.icvepais "
					+ "join grlentidadfed on segusuario.icvepais = grlentidadfed.icvepais "
					+ "and segusuario.icveentidadfed = grlentidadfed.icveentidadfed "
					+ "join grlmunicipio on segusuario.icvepais = grlmunicipio.icvepais "
					+ "and segusuario.icveentidadfed = grlmunicipio.icveentidadfed "
					+ "and segusuario.icvemunicipio = grlmunicipio.icvemunicipio "
					+ cWhere;
			
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vSEGUsuario = new TVSEGUsuario();
				vSEGUsuario.setICveUsuario(rset.getInt(1));
				vSEGUsuario.setDtRegistro(rset.getDate(2));
				vSEGUsuario.setCUsuario(rset.getString(3));
				vSEGUsuario.setCNombre(rset.getString(4));
				vSEGUsuario.setCApPaterno(rset.getString(5));
				vSEGUsuario.setCApMaterno(rset.getString(6));
				vSEGUsuario.setCCalle(rset.getString(7));
				vSEGUsuario.setCColonia(rset.getString(8));
				vSEGUsuario.setICvePais(rset.getInt(9));
				vSEGUsuario.setICveEntidadFed(rset.getInt(10));
				vSEGUsuario.setICveMunicipio(rset.getInt(11));
				vSEGUsuario.setICodigoPostal(rset.getInt(12));
				vSEGUsuario.setCTelefono(rset.getString(13));
				vSEGUsuario.setLBloqueado(rset.getInt(14));
				vSEGUsuario.setCDscPais(rset.getString(15));
				vSEGUsuario.setCDscEntidadFed(rset.getString(16));
				vSEGUsuario.setCDscMunicipio(rset.getString(17));
				// vSEGUsuario.setCPassword(rset.getString(18));
				vSEGUsuario.setCPassword(PassWd);
				vcSEGUsuario.addElement(vSEGUsuario);
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
				if (rset2 != null) {
					rset2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn2 != null) {
					conn2.close();
				}
				dbConn2.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcSEGUsuario;
		}
	}

	/**
	 * Metodo FindByAllUsers
	 */
	public Vector FindByAllUsers(String cWhere) throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSEGUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSEGUsuario vSEGUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select " + "iCveUsuario, " + "cApPaterno, "
					+ "cApMaterno, " + "cNombre " + "from SEGUsuario " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vSEGUsuario = new TVSEGUsuario();
				vSEGUsuario.setICveUsuario(rset.getInt(1));
				vSEGUsuario.setCApPaterno(rset.getString(2));
				vSEGUsuario.setCApMaterno(rset.getString(3));
				vSEGUsuario.setCNombre(rset.getString(4));
				vcSEGUsuario.addElement(vSEGUsuario);
			}
		} catch (Exception ex) {
			warn("FindByAllUsers", ex);
			throw new DAOException("FindByAllUsers");
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
				warn("FindByAllUsers.close", ex2);
			}
			return vcSEGUsuario;
		}
	}

	/**
	 * Metodo update
	 */
	public boolean update(String cModulo) throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDB"), dataSourceName2 = VParametros
				.getPropEspecifica("ConDBModulo");
		DbConnection dbConn = null, dbConn2 = null;
		Connection conn = null, conn2 = null;
		PreparedStatement pstmtDel = null, pstmtIns = null;
		ResultSet rset = null;
		Vector vcSEGUsuario = new Vector();
		boolean lSuccess = true;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			dbConn2 = new DbConnection(dataSourceName2);
			conn2 = dbConn2.getConnection();

			String cSQL = "";
			TVSEGUsuario vSEGUsuario;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(2);

			cSQL = "select distinct "
					+ "segusuario.iCveUsuario, "
					+ "segusuario.dtRegistro, "
					+ "segusuario.cUsuario, "
					+ "segusuario.cNombre, "
					+ "segusuario.cApPaterno, "
					+ "segusuario.cApMaterno, "
					+ "segusuario.cCalle, "
					+ "segusuario.cColonia, "
					+ "segusuario.iCvePais, "
					+ "segusuario.iCveEntidadFed, "
					+ "segusuario.iCveMunicipio, "
					+ "segusuario.iCodigoPostal, "
					+ "segusuario.cTelefono, "
					+ "segusuario.lBloqueado, "
					+ "segusuario.cPassword "
					+ "from seggrupo "
					+ "join seggpoxusr on seggrupo.icvesistema = seggpoxusr.icvesistema "
					+ "and  seggrupo.icvegrupo = seggpoxusr.icvegrupo "
					+ "join segusuario on seggpoxusr.icveusuario = segusuario.icveusuario "
					+ "where seggrupo.icvesistema = " + cModulo
					+ "   or seggrupo.icvesistema = 13 ";

			pstmtDel = conn.prepareStatement(cSQL);
			rset = pstmtDel.executeQuery();
			while (rset.next()) {
				vSEGUsuario = new TVSEGUsuario();
				vSEGUsuario.setICveUsuario(rset.getInt(1));
				vSEGUsuario.setDtRegistro(rset.getDate(2));
				vSEGUsuario.setCUsuario(rset.getString(3));
				vSEGUsuario.setCNombre(rset.getString(4));
				vSEGUsuario.setCApPaterno(rset.getString(5));
				vSEGUsuario.setCApMaterno(rset.getString(6));
				vSEGUsuario.setCCalle(rset.getString(7));
				vSEGUsuario.setCColonia(rset.getString(8));
				vSEGUsuario.setICvePais(rset.getInt(9));
				vSEGUsuario.setICveEntidadFed(rset.getInt(10));
				vSEGUsuario.setICveMunicipio(rset.getInt(11));
				vSEGUsuario.setICodigoPostal(rset.getInt(12));
				vSEGUsuario.setCTelefono(rset.getString(13));
				vSEGUsuario.setLBloqueado(rset.getInt(14));
				vSEGUsuario.setCPassword(rset.getString(15));
				vcSEGUsuario.addElement(vSEGUsuario);
			}

			if (rset != null) {
				rset.close();
			}
			if (pstmtDel != null) {
				pstmtDel.close();
			}

			cSQL = "delete " + " from SEGUsuario ";

			pstmtDel = conn2.prepareStatement(cSQL);
			pstmtDel.executeUpdate();

			cSQL = "insert into SEGUsuario (" + "iCveUsuario, "
					+ "dtRegistro, " + "cUsuario, " + "cPassword, "
					+ "cNombre, " + "cApPaterno, " + "cApMaterno, "
					+ "cCalle, " + "cColonia, " + "iCvePais, "
					+ "iCveEntidadFed, " + "iCveMunicipio, "
					+ "iCodigoPostal, " + "cTelefono, " + "iCveUnidadOrg, "
					+ "lBloqueado "
					+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

			pstmtIns = conn2.prepareStatement(cSQL);

			for (int i = 0; i < vcSEGUsuario.size(); i++) {
				vSEGUsuario = (TVSEGUsuario) vcSEGUsuario.get(i);
				pstmtIns.setInt(1, vSEGUsuario.getICveUsuario());
				pstmtIns.setDate(2, vSEGUsuario.getDtRegistro());
				pstmtIns.setString(3, vSEGUsuario.getCUsuario());
				pstmtIns.setString(4, vSEGUsuario.getCPassword());
				pstmtIns.setString(5, vSEGUsuario.getCNombre());
				pstmtIns.setString(6, vSEGUsuario.getCApPaterno());
				pstmtIns.setString(7, vSEGUsuario.getCApMaterno());
				pstmtIns.setString(8, vSEGUsuario.getCCalle());
				pstmtIns.setString(9, vSEGUsuario.getCColonia());
				pstmtIns.setInt(10, vSEGUsuario.getICvePais());
				pstmtIns.setInt(11, vSEGUsuario.getICveEntidadFed());
				pstmtIns.setInt(12, vSEGUsuario.getICveMunicipio());
				pstmtIns.setInt(13, vSEGUsuario.getICodigoPostal());
				pstmtIns.setString(14, vSEGUsuario.getCTelefono());
				pstmtIns.setInt(15, 0);
				pstmtIns.setInt(16, vSEGUsuario.getLBloqueado());
				pstmtIns.executeUpdate();
			}
			conn2.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn2.rollback();
			} catch (Exception e) {
				warn("update.rollback", ex);
			}
			warn("update", ex);
			lSuccess = false;
		} finally {
			try {
				if (pstmtDel != null) {
					pstmtDel.close();
				}
				if (pstmtIns != null) {
					pstmtIns.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
				if (conn2 != null) {
					conn2.close();
				}
				dbConn2.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return lSuccess;
		}
	}

	public int FindByAllJUM(String cUsuArio, String cPassword)
			throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDB");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSEGUsuario = new Vector();
		int icveGrupo = 0;
		int iCveUsuario = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSEGUsuario vSEGUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT ICVEUSUARIO FROM SEGUSUARIO WHERE LBLOQUEADO = 0 AND CUSUARIO = '"
					+ cUsuArio + "' AND CPASSWORD = '" + cPassword + "'";
			// cSQL =
			// "SELECT ICVEUSUARIO FROM SEGUSUARIO WHERE LBLOQUEADO = 0 AND CUSUARIO = '"+cUsuArio+"' AND CPASSWORD = '"+cPassword+"'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				iCveUsuario = rset.getInt(1);
			}

			// System.out.println("Clave del usuario = " + iCveUsuario);

			if (iCveUsuario > 0) {
				// System.out.println("El usuario es mayor a cero");

				cSQL = "SELECT ICVEGRUPO FROM SEGGPOXUSR WHERE ICVESISTEMA = 7 AND ICVEGRUPO = 46 AND ICVEUSUARIO = "
						+ iCveUsuario;

				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					icveGrupo = rset.getInt(1);
					// System.out.println("resultado = " + rset.getInt(1));
				}
				if (icveGrupo < 46) {
					icveGrupo = -1;
					// System.out.println("El grupo es menor a 46 grupo = " +
					// icveGrupo);
				}
			}
			// System.out.println("Grupo = " +icveGrupo);
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
			return icveGrupo;
		}
	}

	public Vector FindByAllJUM(String cCondicion) throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDB");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSEGUsuario = new Vector();
		int icveGrupo = 0;
		int iCveUsuario = 0;
		// iCveUsuario = Integer.parseInt(NumMed);
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSEGUsuario vSEGUsuario;
			vSEGUsuario = new TVSEGUsuario();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			// cSQL =
			// "SELECT ICVEUSUARIO FROM SEGUSUARIO WHERE LBLOQUEADO = 0 AND CUSUARIO = '"+cUsuArio+"' AND CPASSWORD = '"+cPassword+"'";
			cSQL = "SELECT ICVEUSUARIO, " + " cNombre, " + " cApPaterno, "
					+ " cApMaterno "
					+ " FROM SEGUSUARIO WHERE LBLOQUEADO = 0 AND " + cCondicion
					+ "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				iCveUsuario = rset.getInt(1);
				vSEGUsuario.setICveUsuario(rset.getInt(1));
				vSEGUsuario.setCNombre(rset.getString(2));
				vSEGUsuario.setCApPaterno(rset.getString(3));
				vSEGUsuario.setCApMaterno(rset.getString(4));
			}

			// System.out.println("cSQL = " + cSQL);
			// System.out.println("Clave del usuario = " + iCveUsuario);

			if (iCveUsuario > 0) {

				// System.out.println("El usuario es mayor a cero");

				cSQL = "SELECT ICVEGRUPO FROM SEGGPOXUSR WHERE ICVESISTEMA = 7 AND ICVEGRUPO = 46 AND ICVEUSUARIO = "
						+ iCveUsuario;

				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					icveGrupo = rset.getInt(1);
					vSEGUsuario.setICodigoPostal(rset.getInt(1));
					// System.out.println("resultado = " + rset.getInt(1));
				}
				if (icveGrupo < 46) {
					icveGrupo = -1;
					// System.out.println("El grupo es menor a 46 grupo = " +
					// icveGrupo);
				}
			}
			vcSEGUsuario.addElement(vSEGUsuario);
			// System.out.println("Grupo = " +icveGrupo);
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
			return vcSEGUsuario;
		}
	}

	public int FindByAllJUM2(int iCveUsuario) throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDB");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSEGUsuario = new Vector();
		int icveGrupo = 0;
		// iCveUsuario = Integer.parseInt(NumMed);
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSEGUsuario vSEGUsuario;
			vSEGUsuario = new TVSEGUsuario();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			if (iCveUsuario > 0) {

				cSQL = "SELECT ICVEGRUPO FROM SEGGPOXUSR WHERE ICVESISTEMA = 7 AND ICVEGRUPO = 46 AND ICVEUSUARIO = "
						+ iCveUsuario;

				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					icveGrupo = rset.getInt(1);
				}
				if (icveGrupo < 46) {
					icveGrupo = -1;
				}
			}
			vcSEGUsuario.addElement(vSEGUsuario);
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
			return icveGrupo;
		}
	}

	public List<ClaseDatosInicio> findByUsuarioInicio(int iCveUsuario)
			throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ClaseDatosInicio> listaDatos = new ArrayList<ClaseDatosInicio>();
		ClaseDatosInicio datoInicio = new ClaseDatosInicio();
		// iCveUsuario = Integer.parseInt(NumMed);
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT DISTINCT(GUR.ICVEUNIMED), "
					+ // 1
					"            GM.ICVEMODULO, "
					+ // 2
					"            GM.CDSCMODULO, "
					+ // 3
					"            S.CNOMBRE, "
					+ // 4
					"            S.CAPPATERNO, "
					+ // 5
					"            S.CAPMATERNO, "
					+ // 6
					"            GS.CCEDULA, "
					+ // 7
					"            GS.CRFC, "
					+ // 8
					"            GP.CPREFESION, "
					+ // 9
					"            GM.CCALLE, "
					+ // 10
					"            GM.CCOLONIA, "
					+ // 11
					"            GM.ICP, "
					+ // 12
					"            GM.CCIUDAD, "
					+ // 13
					"            GM.CCLUES "
					+ // 14
					" FROM SEGUSUARIO S "
					+ " LEFT JOIN GRLUSUARIO GS ON GS.ICVEUSUARIO = S.ICVEUSUARIO "
					+ " LEFT JOIN GRLPROFESION GP ON GP.ICVEPROFESION = GS.ICVEPROFESION "
					+ " JOIN GRLUSUMEDICOS GUM ON GUM.ICVEUSUARIO = S.ICVEUSUARIO "
					+ " JOIN GRLMODULO GM ON GM.ICVEMODULO = GUM.ICVEMODULO AND GM.ICVEUNIMED = GUM.ICVEUNIMED "
					+ " JOIN GRLUNIMED GUR ON GUR.ICVEUNIMED = GM.ICVEUNIMED AND GUR.ICVEUNIMED = GUM.ICVEUNIMED "
					+ " WHERE S.ICVEUSUARIO = " + iCveUsuario
					+ " ORDER BY GM.ICVEMODULO";
			System.out.println("El query: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			System.out.println("Prepare");
			rset = pstmt.executeQuery();
			System.out.println("Ejecute");
			while (rset.next()) {
				//System.out.println("Entro al While");
				datoInicio = new ClaseDatosInicio();
				datoInicio.setCedula(rset.getString(7) == null ? "" : rset
						.getString(7));
				datoInicio.setRfc(rset.getString(8) == null ? "" : rset
						.getString(8));
				String nombre = rset.getString(4) == null ? " " : rset
						.getString(4);
				nombre += " ";
				nombre += rset.getString(5) == null ? " " : rset.getString(5);
				nombre += " ";
				nombre += rset.getString(6) == null ? " " : rset.getString(6);
				datoInicio.setNombreCompleto(nombre);
				datoInicio.setNombre(rset.getString(4));
				datoInicio.setApPaterno(rset.getString(5));
				datoInicio.setApMaterno(rset.getString(6));
				datoInicio.setEspecialidad(rset.getString(9) == null ? ""
						: rset.getString(9));
				/*
				 * String domicilio =
				 * rset.getString(10)==null?"":rset.getString(10); domicilio +=
				 * " "; domicilio +=
				 * rset.getString(11)==null?"":rset.getString(11); domicilio +=
				 * " "; domicilio +=
				 * rset.getString(12)==null?"":rset.getString(12); domicilio +=
				 * " "; domicilio +=
				 * rset.getString(13)==null?"":rset.getString(13);
				 */
				String domicilio = rset.getString(13) == null ? "" : rset
						.getString(13);
				datoInicio.setDomLaboral(domicilio);
				datoInicio.setClues(rset.getString(14) == null ? "" : rset
						.getString(14));
				listaDatos.add(datoInicio);
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
			return listaDatos;
		}
	}

	///Usuarios que estan en SEGUSUExp pero que no estan en SEGUSUARIO


	/**
	 * Metodo FindByDifSegUse
	 */
	public Vector UsuariosFaltantes() throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcSEGUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVSEGUsuario vSEGUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "SELECT A.ICVEUSUARIO " +
					"FROM SEGUSUEXP A " +
					"LEFT JOIN SEGUSUARIO B ON A.ICVEUSUARIO = B.ICVEUSUARIO " +
					"WHERE B.ICVEUSUARIO IS NULL";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vSEGUsuario = new TVSEGUsuario();
				vSEGUsuario.setICveUsuario(rset.getInt(1));
				vcSEGUsuario.addElement(vSEGUsuario);
			}
		} catch (Exception ex) {
			warn("FindByAllUsers", ex);
			throw new DAOException("FindByDifSegUse");
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
				warn("FindByDifSegUse.close", ex2);
			}
			return vcSEGUsuario;
		}
	}
	
	
	
	/**
	 * Metodo Find By All
	 */
	public Vector FindByUsuarioAdmseg(Vector obj) throws DAOException {
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		String dataSourceName2 = VParametros.getPropEspecifica("ConDB");
		DbConnection dbConn = null;
		Connection conn = null;
		DbConnection dbConn2 = null;
		Connection conn2 = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		Vector vcSEGUsuario = new Vector();
		Vector vcSEGUsuario2 = new Vector();
		String PassWd = "";
		TVSEGUsuario vSEGUsuario;
		TVSEGUsuario vSEGUsuario2;
		try {

			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			vcSEGUsuario = obj;
			if (vcSEGUsuario.size() > 0) {
				for (int i = 0; i < vcSEGUsuario.size(); i++) {
					vSEGUsuario = (TVSEGUsuario) vcSEGUsuario.get(i);

					cSQL = "select "
							+ "iCveUsuario,"
							+ "dtRegistro,"
							+ "cUsuario,"
							+ "SEGUsuario.cNombre,"
							+ "cApPaterno,"
							+ "cApMaterno,"
							+ "cCalle,"
							+ "cColonia,"
							+ "grlpais.iCvePais,"
							+ "grlentidadfed.iCveEntidadFed,"
							+ "grlmunicipio.iCveMunicipio,"
							+ "iCodigoPostal,"
							+ "cTelefono,"
							+ "lBloqueado, grlpais.cnombre, grlentidadfed.cnombre, grlmunicipio.cnombre,"
							+ " cPassword "
							+ " from SEGUsuario "
							+ "join grlpais on segusuario.icvepais = grlpais.icvepais "
							+ "join grlentidadfed on segusuario.icvepais = grlentidadfed.icvepais "
							+ "and segusuario.icveentidadfed = grlentidadfed.icveentidadfed "
							+ "join grlmunicipio on segusuario.icvepais = grlmunicipio.icvepais "
							+ "and segusuario.icveentidadfed = grlmunicipio.icveentidadfed "
							+ "and segusuario.icvemunicipio = grlmunicipio.icvemunicipio "
							+ "and segusuario.iCveUsuario="+vSEGUsuario.getICveUsuario();

					//System.out.println(cSQL);
					pstmt = conn.prepareStatement(cSQL);
					rset = pstmt.executeQuery();
					while (rset.next()) {
						vSEGUsuario2 = new TVSEGUsuario();
						vSEGUsuario2.setICveUsuario(rset.getInt(1));
						vSEGUsuario2.setDtRegistro(rset.getDate(2));
						vSEGUsuario2.setCUsuario(rset.getString(3));
						vSEGUsuario2.setCNombre(rset.getString(4));
						vSEGUsuario2.setCApPaterno(rset.getString(5));
						vSEGUsuario2.setCApMaterno(rset.getString(6));
						vSEGUsuario2.setCCalle(rset.getString(7));
						vSEGUsuario2.setCColonia(rset.getString(8));
						vSEGUsuario2.setICvePais(rset.getInt(9));
						vSEGUsuario2.setICveEntidadFed(rset.getInt(10));
						vSEGUsuario2.setICveMunicipio(rset.getInt(11));
						vSEGUsuario2.setICodigoPostal(rset.getInt(12));
						vSEGUsuario2.setCTelefono(rset.getString(13));
						vSEGUsuario2.setLBloqueado(rset.getInt(14));
						vSEGUsuario2.setCDscPais(rset.getString(15));
						vSEGUsuario2.setCDscEntidadFed(rset.getString(16));
						vSEGUsuario2.setCDscMunicipio(rset.getString(17));
						// vSEGUsuario.setCPassword(rset.getString(18));
						vSEGUsuario2.setCPassword("");
						vcSEGUsuario2.addElement(vSEGUsuario2);
					}
				}

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
			return vcSEGUsuario2;
		}
	}
	
	/**
	 * Metodo Insert
	 */
	public int insertSincronizaUsuarios(Connection conGral)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iCta = 0;
		Vector Faltantes = new Vector();
		Vector AdmsegFaltantes = new Vector();
		TVSEGUsuario vSEGUsuario;
		String dataSourceName = VParametros.getPropEspecifica("ConDBModulo");
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			
			Faltantes = this.UsuariosFaltantes();
			System.out.println("Usuarios Faltantes"+Faltantes.size());
			AdmsegFaltantes = this.FindByUsuarioAdmseg(Faltantes);
			System.out.println("Usuarios importados"+AdmsegFaltantes.size());
			
			String cSQL = "insert into SEGUSUARIO("
					+ "iCveUsuario,"
					+ "dtRegistro,"
					+ "cUsuario,"
					+ "cNombre,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "cCalle,"
					+ "cColonia,"
					+ "iCvePais,"
					+ "iCveEntidadFed,"
					+ "iCveMunicipio,"
					+ "iCodigoPostal,"
					+ "cTelefono,"
					+ "lBloqueado,"
					+ "cPassword) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			System.out.println(cSQL);
			if (AdmsegFaltantes.size() > 0) {
				for (int i = 0; i < AdmsegFaltantes.size(); i++) {
					vSEGUsuario = (TVSEGUsuario) AdmsegFaltantes.get(i);
					pstmt = conn.prepareStatement(cSQL);
					/*
					System.out.println("===============================");
					System.out.println( vSEGUsuario.getICveUsuario());
					System.out.println(vSEGUsuario.getDtRegistro());
					System.out.println(vSEGUsuario.getCUsuario());
					System.out.println(vSEGUsuario.getCNombre());
					System.out.println(vSEGUsuario.getCApPaterno());
					System.out.println(vSEGUsuario.getCApMaterno());
					System.out.println(vSEGUsuario.getCCalle());
					System.out.println(vSEGUsuario.getCColonia());
					System.out.println(vSEGUsuario.getICvePais());
					System.out.println(vSEGUsuario.getICveEntidadFed());
					System.out.println(vSEGUsuario.getICveMunicipio());
					System.out.println(vSEGUsuario.getICodigoPostal());
					System.out.println( vSEGUsuario.getCTelefono());
					System.out.println(vSEGUsuario.getLBloqueado());
					System.out.println(vSEGUsuario.getCPassword());
					System.out.println("===============================");
					*/
						pstmt.setInt(1, vSEGUsuario.getICveUsuario());
						pstmt.setDate(2, vSEGUsuario.getDtRegistro());
						pstmt.setString(3, vSEGUsuario.getCUsuario());
						pstmt.setString(4, vSEGUsuario.getCNombre());
						pstmt.setString(5, vSEGUsuario.getCApPaterno());
						pstmt.setString(6, vSEGUsuario.getCApMaterno());
						pstmt.setString(7, vSEGUsuario.getCCalle());
						pstmt.setString(8, vSEGUsuario.getCColonia());
						pstmt.setInt(9, vSEGUsuario.getICvePais());
						pstmt.setInt(10, vSEGUsuario.getICveEntidadFed());
						pstmt.setInt(11, vSEGUsuario.getICveMunicipio());
						pstmt.setInt(12, vSEGUsuario.getICodigoPostal());
						pstmt.setString(13, vSEGUsuario.getCTelefono());
						pstmt.setInt(14, vSEGUsuario.getLBloqueado());
						pstmt.setString(15, vSEGUsuario.getCPassword());
						iCta += pstmt.executeUpdate();
					
					if (conGral == null) {
						conn.commit();
					}					
					
					}
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
		}
		return iCta;
	}	

}
