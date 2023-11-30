package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de TOXAnalisis DAO
 * </p>
 * <p>
 * Description: Lotes Cualitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame.
 * @version 1.0
 */

public class TDTOXAnalisis extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXAnalisis() {
	}

	public Vector FindControl(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VControles = new Vector();
		TVTOXAnalisisCtrol VAnCtrol;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			int count;
			cSQL.append(
					"select A.iAnio, A.iCveLaboratorio, A.iCveLoteCualita, A.iCveExamCualita,")
					.append("       A.iCveAnalisis, A.iCarrusel, A.iPosicion, ")
					.append("       An.lControl,")
					.append("       CC.cLote, CC.cDscCtrolCalibra, CC.cDscBreve, CC.dConcentracion,")
					.append("       E.cDscEmpleoCalib, ")
					.append("       S.cDscSustancia")
					.append(" from TOXExamAnalisis A ")
					.append("          inner join TOXAnalisis An on An.iAnio           = A.iAnio ")
					.append("                                   and An.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                                   and An.iCveAnalisis    = A.iCveAnalisis ")
					.append("      left join TOXCtrolCalibra CC on CC.iCveLaboratorio  = An.iCveLaboratorio ")
					.append("                              and CC.iCveCtrolCalibra = An.iCveCtrolCalibra ")
					.append("      left join TOXEmpleoCalib E on E.iCveEmpleoCalib = CC.iCveEmpleoCalib ")
					.append("      left join TOXSustancia   S on S.iCveSustancia   = CC.iCveSustancia")
					.append(cFiltro);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VAnCtrol = new TVTOXAnalisisCtrol();
				// Obtener los valores de los datos.
				VAnCtrol.VExamen.setIAnio(rset.getInt(1));
				VAnCtrol.VExamen.setICveLaboratorio(rset.getInt(2));
				VAnCtrol.VExamen.setICveLoteCualita(rset.getInt(3));
				VAnCtrol.VExamen.setICveExamCualita(rset.getInt(4));
				VAnCtrol.VExamen.setICveAnalisis(rset.getInt(5));
				VAnCtrol.VExamen.setICarrusel(rset.getInt(6));
				VAnCtrol.VExamen.setIPosicion(rset.getInt(7));
				VAnCtrol.setLControl(rset.getInt(8));
				VAnCtrol.VCtrol.setCLote(rset.getString(9));
				VAnCtrol.VCtrol.setCDscCtrolCalibra(rset.getString(10));
				VAnCtrol.VCtrol.setCDscBreve(rset.getString(11));
				VAnCtrol.VCtrol.setDConcentracion(rset.getFloat(12));
				VAnCtrol.VCtrol.setCDscEmpleoCalib(rset.getString(13));
				VAnCtrol.VCtrol.setCDscSustancia(rset.getString(14));
				VControles.addElement(VAnCtrol);
			}
		} catch (Exception ex) {
			warn("FindControl", ex);
			throw new DAOException("FindControl");
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
			return VControles;
		}
	}

	/**
	 * Metodo Find By All
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindByAll(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iAnio," + " iCveLaboratorio,"
					+ " iCveAnalisis," + " iCveMuestra," + " iCveLoteCualita,"
					+ " lControl," + " iCveCtrolCalibra," + " lResultado,"
					+ " lPresuntoPost," + " lAutorizado," + " dtAutorizacion,"
					+ " iCveUsuAutoriza," + " iCveExamCualita, "
					+ " iSustPost, " + " iSustConf " + " from TOXAnalisis"
					+ cvFiltro + " ";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAnalisis = new TVTOXAnalisis();
				vTOXAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vTOXAnalisis.setiCveLaboratorio(new Integer(rset.getInt(2)));
				vTOXAnalisis.setiCveAnalisis(new Integer(rset.getInt(3)));
				vTOXAnalisis.setiCveMuestra(rset.getInt(4));
				vTOXAnalisis.setiCveLoteCualita(new Integer(rset.getInt(5)));
				vTOXAnalisis.setlControl(new Integer(rset.getInt(6)));
				vTOXAnalisis.setiCveCtrolCalibra(new Integer(rset.getInt(7)));
				vTOXAnalisis.setlResultado(new Integer(rset.getInt(8)));
				vTOXAnalisis.setlPresuntoPost(new Integer(rset.getInt(9)));
				vTOXAnalisis.setlAutorizado(new Integer(rset.getInt(10)));
				vTOXAnalisis.setdtAutorizacion(rset.getDate(11));
				vTOXAnalisis.setiCveUsuAutoriza(new Integer(rset.getInt(12)));
				vTOXAnalisis.setiCveExamCualita(new Integer(rset.getInt(13)));
				vTOXAnalisis.setISustPost(new Integer(rset.getInt(14)));
				vTOXAnalisis.setISustConf(new Integer(rset.getInt(15)));
				vcTOXAnalisis.addElement(vTOXAnalisis);
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
			return vcTOXAnalisis;
		}
	}

	public Vector FindByAll2(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "TOXAnalisis.iAnio, TOXAnalisis.iCveLaboratorio, TOXAnalisis.iCveLoteCualita, "
					+ "TOXAnalisis.iCveMuestra, TOXAnalisis.iCveAnalisis, TOXExamAnalisis.iCarrusel, "
					+ "TOXExamAnalisis.iPosicion, GRLUniMed.iCveUniMed "
					+ "from TOXAnalisis "
					+ "left join TOXExamAnalisis on TOXAnalisis.iCveAnalisis = TOXExamAnalisis.iCveAnalisis "
					+ "left join GRLUniMed on TOXAnalisis.iCveLaboratorio = GRLUniMed.iCveUniMed "
					+ cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAnalisis = new TVTOXAnalisis();
				vTOXAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vTOXAnalisis.setiCveLaboratorio(new Integer(rset.getInt(2)));
				vTOXAnalisis.setiCveLoteCualita(new Integer(rset.getInt(3)));
				vTOXAnalisis.setiCveMuestra(rset.getInt(4));
				vTOXAnalisis.setiCveAnalisis(new Integer(rset.getInt(5)));
				vTOXAnalisis.setiCarrusel(new Integer(rset.getInt(6)));
				vTOXAnalisis.setiPosicion(new Integer(rset.getInt(7)));
				vTOXAnalisis.setiCveUniMed(new Integer(rset.getInt(8)));
				vcTOXAnalisis.addElement(vTOXAnalisis);
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
			return vcTOXAnalisis;
		}
	}

	public Vector FindByAll3(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select distinct(TOXAnalisis.iCveMuestra), "
					+ " TOXAnalisis.iAnio, "
					+ " TOXAnalisis.iCveLaboratorio, "
					+ " TOXAnalisis.iCveLoteCualita, "
					+ " TOXAnalisis.iCveAnalisis, "
					+ " TOXExamAnalisis.iCarrusel, "
					+ " TOXExamAnalisis.iPosicion "
					+ " from TOXAnalisis "
					+ " join TOXLoteCualita "
					+ " on  TOXLoteCualita.iAnio           = TOXAnalisis.iAnio "
					+ " and TOXLoteCualita.iCveLaboratorio = TOXAnalisis.iCveLaboratorio "
					+ " and TOXLoteCualita.iCveLoteCualita = TOXAnalisis.iCveLoteCualita "
					+ " and TOXLoteCualita.dtGeneracion    is not null "
					+ " join TOXExamAnalisis "
					+ " on TOXAnalisis.iCveLaboratorio = TOXExamAnalisis.iCveLaboratorio "
					+ " and  TOXAnalisis.iAnio           = TOXExamAnalisis.iAnio "
					+ " and  TOXAnalisis.iCveLoteCualita = TOXExamAnalisis.iCveLoteCualita "
					+ " and  TOXAnalisis.iCveAnalisis    = TOXExamAnalisis.iCveAnalisis "
					+ cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAnalisis = new TVTOXAnalisis();
				vTOXAnalisis.setiCveMuestra(rset.getInt(1));
				vTOXAnalisis.setiAnio(new Integer(rset.getInt(2)));
				vTOXAnalisis.setiCveLaboratorio(new Integer(rset.getInt(3)));
				vTOXAnalisis.setiCveLoteCualita(new Integer(rset.getInt(4)));
				vTOXAnalisis.setiCveAnalisis(new Integer(rset.getInt(5)));
				vTOXAnalisis.setiCarrusel(new Integer(rset.getInt(6)));
				vTOXAnalisis.setiPosicion(new Integer(rset.getInt(7)));
				vcTOXAnalisis.addElement(vTOXAnalisis);
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
			return vcTOXAnalisis;
		}
	}

	public Vector FindByAll4(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select ToxAnalisis.icvemuestra,toxexamanalisis.icveanalisis, toxexamanalisis.icarrusel,toxexamanalisis.iposicion, toxexamanalisis.ianio "
					+ " from toxexamanalisis "
					+ " inner join TOXAnalisis on toxanalisis.ianio = toxexamanalisis.ianio "
					+ " and toxanalisis.icvelaboratorio = toxexamanalisis.icvelaboratorio  "
					+ " and toxanalisis.icveanalisis = toxexamanalisis.icveanalisis "
					+ cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAnalisis = new TVTOXAnalisis();
				vTOXAnalisis.setiCveMuestra(rset.getInt(1));
				vTOXAnalisis.setiCveAnalisis(new Integer(rset.getInt(2)));
				vTOXAnalisis.setiCarrusel(new Integer(rset.getInt(3)));
				vTOXAnalisis.setiPosicion(new Integer(rset.getInt(4)));
				vTOXAnalisis.setiAnio(new Integer(rset.getInt(5)));
				vcTOXAnalisis.addElement(vTOXAnalisis);
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
			return vcTOXAnalisis;
		}
	}

	/* FIND MAX iCveAnalisis */
	public int FindMax(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		int iMax = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis;
			vTOXAnalisis = (TVTOXAnalisis) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select max(icveanalisis) " + "  from toxanalisis "
					+ " where iAnio = " + vTOXAnalisis.getiAnio().toString()
					+ "   and iCveLaboratorio = "
					+ vTOXAnalisis.getiCveLaboratorio();

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				iMax = rset.getInt(1);
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
			return iMax;
		}
	}

	/* FIND MAX iCveAnalisis */
	public Vector FindByAll5(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis = (TVTOXAnalisis) Obj;
			int count;

			cSQL = " select a.iAnio, c.icvelaboratorio, a.iCveMuestra, a.iCveEnvio, b.iCveTipoRecep, b.cDscTipoRecep, c.iCveAnalisis, "
					+ "        c.iSustPost, c.iSustConf, d.icvesustancia, "
					+ "        e.cDscSustancia,          "
					+ "        c.lResultado, c.lPresuntoPost, "
					+ "        m.cdscmotrecep,    "
					+ "        c.lAutorizado, c.dtAutorizacion, a.iCveSituacion, c.iCveLoteCualita "
					+ " from TOXMuestra a "
					+ "   join TOXTipoRecep b on b.iCveTipoRecep = a.iCveTipoRecep "
					+ "   left join TOXAnalisis c on c.iAnio  = a.iAnio "
					+ "                     and c.iCveMuestra = a.iCveMuestra "
					+ "   left join toxcuantanalisis d on d.ianio           = c.ianio    "
					+ "                               and d.icveanalisis    = c.icveanalisis "
					+ "                               and d.icvelaboratorio = c.icvelaboratorio "
					+ "                               and d.lResultado      = 1  "
					+ "                               and d.lAutorizado     = 1  "
					+ "   left join toxsustancia e on e.icvesustancia= d.icvesustancia  "
					+ "   left join toxmotivorecep m on m.icvetiporecep = a.icvetiporecep and  "
					+ "                                 m.icvemotrecep = a.icvemotrecep        "
					+ " where a.iAnio = ? and a.iCveUniMed = ? and a.iCveEnvio  = ? ";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXAnalisis.getiAnio().intValue());
			pstmt.setInt(2, vTOXAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(3, vTOXAnalisis.getICveEnvio().intValue());

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAnalisis = new TVTOXAnalisis();
				vTOXAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vTOXAnalisis.setiCveLaboratorio(new Integer(rset.getInt(2)));
				vTOXAnalisis.setiCveMuestra(rset.getInt(3));
				vTOXAnalisis.setICveEnvio(new Integer(rset.getInt(4)));
				vTOXAnalisis.setICveTipoRecep(new Integer(rset.getInt(5)));
				vTOXAnalisis.setCDscTipoRecep(rset.getString(6));
				vTOXAnalisis.setiCveAnalisis(new Integer(rset.getInt(7)));
				vTOXAnalisis.setISustPost(new Integer(rset.getInt(8)));
				vTOXAnalisis.setISustConf(new Integer(rset.getInt(9)));
				vTOXAnalisis.setICveSustancia(new Integer(rset.getInt(10)));
				vTOXAnalisis.setCDscSustancia(rset.getString(11));
				vTOXAnalisis.setlResultado(new Integer(rset.getInt(12)));
				vTOXAnalisis.setlPresuntoPost(new Integer(rset.getInt(13)));
				vTOXAnalisis.setCDscMotivo(rset.getString(14));
				vTOXAnalisis.setlAutorizado(new Integer(rset.getInt(15)));
				vTOXAnalisis.setdtAutorizacion(rset.getDate(16));
				vTOXAnalisis.setICveSituacion(new Integer(rset.getInt(17)));
				vTOXAnalisis.setiCveLoteCualita(new Integer(rset.getInt(18)));
				vcTOXAnalisis.addElement(vTOXAnalisis);
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
			return vcTOXAnalisis;
		}
	}

	/* FIND MAX iCveAnalisis */
	public Vector FindByAll6(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXAnalisis vTOXAnalisis = null;
			int count;
			cSQL = " select a.iAnio, c.icvelaboratorio, a.iCveMuestra, a.iCveEnvio, b.iCveTipoRecep, b.cDscTipoRecep, c.iCveAnalisis, "
					+ "        c.iSustPost, c.iSustConf, d.icvesustancia, "
					+ "        e.cDscSustancia,          "
					+ "        c.lResultado, c.lPresuntoPost, "
					+ "        m.cdscmotrecep,    "
					+ "        c.lAutorizado, c.dtAutorizacion, a.iCveSituacion, c.iCveLoteCualita, "
					+ "        a.iCveEnvio "
					+ " from TOXMuestra a "
					+ "   join TOXTipoRecep b on b.iCveTipoRecep = a.iCveTipoRecep "
					+ "   left join TOXAnalisis c on c.iAnio  = a.iAnio "
					+ "                     and c.iCveMuestra = a.iCveMuestra "
					+ "   left join toxcuantanalisis d on d.ianio           = c.ianio    "
					+ "                               and d.icveanalisis    = c.icveanalisis "
					+ "                               and d.icvelaboratorio = c.icvelaboratorio "
					+ "                               and d.lResultado      = 1  "
					+ "                               and d.lAutorizado     = 1  "
					+ "   left join toxsustancia e on e.icvesustancia= d.icvesustancia  "
					+ "   left join toxmotivorecep m on m.icvetiporecep = a.icvetiporecep and  "
					+ "                                 m.icvemotrecep = a.icvemotrecep        "
					+ cWhere;
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println("Filtro = " + cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAnalisis = new TVTOXAnalisis();
				vTOXAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vTOXAnalisis.setiCveLaboratorio(new Integer(rset.getInt(2)));
				vTOXAnalisis.setiCveMuestra(rset.getInt(3));
				vTOXAnalisis.setICveEnvio(new Integer(rset.getInt(4)));
				vTOXAnalisis.setICveTipoRecep(new Integer(rset.getInt(5)));
				vTOXAnalisis.setCDscTipoRecep(rset.getString(6));
				vTOXAnalisis.setiCveAnalisis(new Integer(rset.getInt(7)));
				vTOXAnalisis.setISustPost(new Integer(rset.getInt(8)));
				vTOXAnalisis.setISustConf(new Integer(rset.getInt(9)));
				vTOXAnalisis.setICveSustancia(new Integer(rset.getInt(10)));
				vTOXAnalisis.setCDscSustancia(rset.getString(11));
				vTOXAnalisis.setlResultado(new Integer(rset.getInt(12)));
				vTOXAnalisis.setlPresuntoPost(new Integer(rset.getInt(13)));
				vTOXAnalisis.setCDscMotivo(rset.getString(14));
				vTOXAnalisis.setlAutorizado(new Integer(rset.getInt(15)));
				vTOXAnalisis.setdtAutorizacion(rset.getDate(16));
				vTOXAnalisis.setICveSituacion(new Integer(rset.getInt(17)));
				vTOXAnalisis.setiCveLoteCualita(new Integer(rset.getInt(18)));
				vTOXAnalisis.setICveEnvio(new Integer(rset.getInt(19)));
				vcTOXAnalisis.addElement(vTOXAnalisis);
			}
		} catch (Exception ex) {
			warn("FindByAll6", ex);
			throw new DAOException("FindByAll6");
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
				warn("FindByAll6.close", ex2);
			}
			return vcTOXAnalisis;
		}
	}

	/* FIND MAX iCveAnalisis */
	public Vector RepTarjeta(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VTOXAnalisis = new Vector();
		Vector VResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXAnalisis vTOXAnalisis = (TVTOXAnalisis) Obj;
			TVTOXAnalisis VAnalisis;
			int count;
			// B�squeda de los rechazados
			cSQL.append(
					" SELECT M.iCveMotRecep, R.cDscMotRecep, M.iCveMuestra, R.cDscLong, ")
					.append("        M.iAnio ")
					.append(" FROM TOXMuestra M ")
					.append(" inner join TOXMotivoRecep R on R.iCveTipoRecep = M.iCveTipoRecep ")
					.append("                            and R.iCveMotRecep  = M.iCveMotRecep ")
					.append(" where M.iAnio         = ? ")
					.append("   and M.iCveUniMed    = ? ")
					.append("   and M.iCveEnvio     = ? ")
					.append("   and M.iCveSituacion = ? ")
					.append(" order by R.iOrden, M.iCveMuestra ");
			pstmt = conn.prepareStatement(cSQL.toString());
			pstmt.setInt(1, vTOXAnalisis.getiAnio().intValue());
			pstmt.setInt(2, vTOXAnalisis.getiCveUniMed().intValue());
			pstmt.setInt(3, vTOXAnalisis.getICveEnvio().intValue());
			pstmt.setInt(4, Integer.parseInt(VParametros
					.getPropEspecifica("TOXRechazo")));
			rset = pstmt.executeQuery();
			Vector Negativos = new Vector();
			int iCveMotivo = 0;
			while (rset.next()) {
				if (iCveMotivo != rset.getInt(1)) {
					if (iCveMotivo > 0) {
						Negativos.add(VTOXAnalisis);
						VTOXAnalisis = new Vector();
					}
					iCveMotivo = rset.getInt(1);
				}
				VAnalisis = new TVTOXAnalisis();
				VAnalisis.setICveSituacion(new Integer(rset.getInt(1)));
				VAnalisis.setCDscTipoRecep(rset.getString(2));
				VAnalisis.setiCveMuestra(rset.getInt(3));
				VAnalisis.setCDscMotivo(rset.getString(4));
				VAnalisis.setiAnio(new Integer(rset.getInt(5)));
				VTOXAnalisis.addElement(VAnalisis);
			}
			if (iCveMotivo > 0) {
				Negativos.add(VTOXAnalisis);
			}
			VResultado.addElement(Negativos);
			// B�squeda de los negativos
			cSQL = new StringBuffer();
			cSQL.append(" select M.iCveMuestra, ")
					.append("       A.iCveAnalisis, A.lResultado, A.LAutorizado, A.dtAutorizacion, A.LPresuntoPost, ")
					.append("       P.cDscProceso, M.iAnio ")
					.append(" from TOXMuestra M ")
					.append(" inner join TOXAnalisis A on A.iAnio = M.iAnio ")
					.append("                        and A.iCveMuestra = M.iCveMuestra ")
					.append(" inner join GRLProceso P on P.iCveProceso = M.iCveProceso")
					.append(" where M.iAnio         = ? ")
					.append("   and M.iCveUniMed    = ? ")
					.append("   and M.iCveEnvio     = ? ")
					.append("   and M.iCveSituacion = ? ")
					.append("   and A.lResultado    = 0 ")
					.append("   and ( A.lAutorizado   = 1 and A.iSustPost = iSustConf) ")
					.append(" order by M.iCveMuestra ");
			rset.close();
			pstmt = null;
			rset = null;
			pstmt = conn.prepareStatement(cSQL.toString());
			pstmt.setInt(1, vTOXAnalisis.getiAnio().intValue());
			pstmt.setInt(2, vTOXAnalisis.getiCveUniMed().intValue());
			pstmt.setInt(3, vTOXAnalisis.getICveEnvio().intValue());
			pstmt.setInt(4, Integer.parseInt(VParametros
					.getPropEspecifica("TOXEnEstudio")));
			// System.out.println("cSQL" + cSQL.toString() + " En Estudio" +
			// VParametros.getPropEspecifica("TOXEnEstudio"));
			rset = pstmt.executeQuery();
			VTOXAnalisis = new Vector();
			while (rset.next()) {
				VAnalisis = new TVTOXAnalisis();
				VAnalisis.setiCveMuestra(rset.getInt(1));
				VAnalisis.setiCveAnalisis(new Integer(rset.getInt(2)));
				VAnalisis.setlResultado(new Integer(rset.getInt(3)));
				VAnalisis.setdtAutorizacion(rset.getDate(5));
				VAnalisis.setCDscMotivo(rset.getString(7));
				VAnalisis.setiAnio(new Integer(rset.getInt(8)));
				VTOXAnalisis.addElement(VAnalisis);
			}
			VResultado.addElement(VTOXAnalisis);
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
			return VResultado;
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
			TVTOXAnalisis VAnalisis = (TVTOXAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXAnalisis(" + "iAnio," + "iCveLaboratorio,"
					+ "iCveAnalisis," + "iCveMuestra," + "iCveLoteCualita,"
					+ "lControl," + "iCveCtrolCalibra," + "lResultado,"
					+ "lPresuntoPost," + "lAutorizado," + "dtAutorizacion,"
					+ "iCveUsuAutoriza," + "iCveExamCualita"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, VAnalisis.getiAnio().intValue());
			pstmt.setInt(2, VAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(3, VAnalisis.getiCveAnalisis().intValue());
			pstmt.setInt(4, VAnalisis.getiCveMuestra());
			pstmt.setInt(5, VAnalisis.getiCveLoteCualita().intValue());
			pstmt.setInt(6, VAnalisis.getlControl().intValue());
			pstmt.setInt(7, VAnalisis.getiCveCtrolCalibra().intValue());
			pstmt.setInt(8, VAnalisis.getlResultado().intValue());
			pstmt.setInt(9, VAnalisis.getlPresuntoPost().intValue());
			pstmt.setInt(10, VAnalisis.getlAutorizado().intValue());
			pstmt.setDate(11, VAnalisis.getdtAutorizacion());
			pstmt.setInt(12, VAnalisis.getiCveUsuAutoriza().intValue());
			pstmt.setInt(13, VAnalisis.getiCveExamCualita().intValue());
			pstmt.executeUpdate();
			cClave = "" + VAnalisis.getiAnio();
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
			TVTOXAnalisis VAnalisis = (TVTOXAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXAnalisis" + " set iCveMuestra= ?, "
					+ "iCveLoteCualita= ?, " + "lControl= ?, "
					+ "iCveCtrolCalibra= ?, " + "lResultado= ?, "
					+ "lPresuntoPost= ?, " + "lAutorizado= ?, "
					+ "dtAutorizacion= ?, " + "iCveUsuAutoriza= ?, "
					+ "iCveExamCualita= ? " + "where iAnio = ? "
					+ "and iCveLaboratorio = ?" + " and iCveAnalisis = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, VAnalisis.getiCveMuestra());
			pstmt.setInt(2, VAnalisis.getiCveLoteCualita().intValue());
			pstmt.setInt(3, VAnalisis.getlControl().intValue());
			pstmt.setInt(4, VAnalisis.getiCveCtrolCalibra().intValue());
			pstmt.setInt(5, VAnalisis.getlResultado().intValue());
			pstmt.setInt(6, VAnalisis.getlPresuntoPost().intValue());
			pstmt.setInt(7, VAnalisis.getlAutorizado().intValue());
			pstmt.setDate(8, VAnalisis.getdtAutorizacion());
			pstmt.setInt(9, VAnalisis.getiCveUsuAutoriza().intValue());
			pstmt.setInt(10, VAnalisis.getiCveExamCualita().intValue());
			pstmt.setInt(11, VAnalisis.getiAnio().intValue());
			pstmt.setInt(12, VAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(13, VAnalisis.getiCveAnalisis().intValue());
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
	 * Metodo Update solo para actualizar el campo iCveExamCualita
	 */
	public Object updateExamA(Connection conGral, Object obj)
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
			TVTOXAnalisis VAnalisis = (TVTOXAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXAnalisis         "
					+ "    set iCveExamCualita= ?  "
					+ "  where iAnio = ?           "
					+ "    and iCveLaboratorio = ? "
					+ "    and iCveAnalisis = ?    ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, VAnalisis.getiCveExamCualita().intValue());
			pstmt.setInt(2, VAnalisis.getiAnio().intValue());
			pstmt.setInt(3, VAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(4, VAnalisis.getiCveAnalisis().intValue());
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
	 * Metodo Update lResultado solo para actualizar el campo lResultado y
	 * iSustConf
	 */
	public Object updatelResultado(Connection conGral, Object obj)
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
			TVTOXAnalisis VAnalisis = (TVTOXAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXAnalisis         "
					+ "    set lResultado      = ?,"
					+ "        iSustConf       = ? "
					+ "  where iAnio           = ? "
					+ "    and iCveLaboratorio = ? "
					+ "    and iCveAnalisis    = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, VAnalisis.getlResultado().intValue());
			pstmt.setInt(2, VAnalisis.getISustConf().intValue());
			pstmt.setInt(3, VAnalisis.getiAnio().intValue());
			pstmt.setInt(4, VAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(5, VAnalisis.getiCveAnalisis().intValue());
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
			TVTOXAnalisis VAnalisis = (TVTOXAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXAnalisis" + " where iAnio = ?"
					+ " and iCveLaboratorio = ?" + " and iCveAnalisis = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, VAnalisis.getiAnio().intValue());
			pstmt.setInt(2, VAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(3, VAnalisis.getiCveAnalisis().intValue());
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
				warn("delete.closeOtra", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo FindAnalisis
	 * 
	 * @param hmFiltro
	 *            Valores del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindAnalisis(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select a.iCveAnalisis "
					+ "from TOXAnalisis a join TOXExamAnalisis b on ("
					+ "a.iAnio=b.iAnio and a.iCveLaboratorio=b.iCveLaboratorio and "
					+ "a.iCveExamCualita=b.iCveExamCualita and "
					+ "a.iCveAnalisis=b.iCveAnalisis ) "
					+ "where a.lAutorizado=1 and a.lPresuntoPost=1 and a.lControl=0 and "
					+ "b.iAnio=? and b.iCveLaboratorio=? and b.iCveExamCualita=? and "
					+ "b.iCveLoteCualita=?";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);

			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vcTOXAnalisis.addElement(new Integer(rset
						.getInt("iCveAnalisis")));
			}
		} catch (Exception ex) {
			warn("FindAnalisis", ex);
			throw new DAOException("FindAnalisis");
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
				warn("FindAnalisis.close", ex2);
			}
			return vcTOXAnalisis;
		}
	}

	//
	/**
	 * Metodo FindAnalisisCtrolCalibra
	 * 
	 * @param hmFiltro
	 *            Valores del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindAnalisisCtrolCalibra(HashMap hmFiltro)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			cSQL.append(" select A.iCveAnalisis, C.cDscBreve ")
					.append(" from TOXExamAnalisis A ")
					.append(" inner join TOXAnalisis B on B.iAnio           = A.iAnio ")
					.append("                         and B.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                         and B.iCveAnalisis    = A.iCveAnalisis ")
					.append("           inner join TOXExamenCualita D on D.iAnio           = A.iAnio ")
					.append("                                        and D.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                                        and D.iCveLoteCualita = A.iCveLoteCualita ")
					.append("                                        and D.iCveExamCualita = A.iCveExamCualita ")
					.append(" left join TOXCtrolCalibra C on C.iCveLaboratorio  = B.iCveLaboratorio ")
					.append("                            and C.iCveCtrolCalibra = B.iCveCtrolCalibra ")
					.append(" where A.iAnio = ? ")
					.append("   and A.iCveLaboratorio = ?")
					.append("   and A.iCveLoteCualita = ?")
					.append("   and A.iCveExamCualita = ?")
					.append("   and B.lAutorizado = 0 ")
					.append("   and D.lAutorizado = 0 ")
					.append(" order by A.iCveAnalisis ");
			pstmt = conn.prepareStatement(cSQL.toString());

			// System.out.println("*********1***********\n"+cSQL.toString());

			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vcTOXAnalisis.addElement(new Integer(rset
						.getInt("iCveAnalisis")));
				cTmp = rset.getString("cDscBreve");
				vcTOXAnalisis.addElement(cTmp == null ? "&nbsp;" : cTmp);
			}
		} catch (Exception ex) {
			warn("FindAnalisisCtrolCalibra", ex);
			throw new DAOException("FindAnalisisCtrolCalibra");
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
				warn("FindAnalisisCtrolCalibra.close", ex2);
			}
			return vcTOXAnalisis;
		}
	}

	/**
	 * Metodo FindAnalisisCtrolCalibra
	 * 
	 * @param hmFiltro
	 *            Valores del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public HashMap FindAnalisisCtrolCalibraCons(HashMap hmFiltro)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap vcTOXAnalisis = new HashMap();
		TVTOXAnalisis vAnalisis;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			cSQL.append(
					" select A.iCveAnalisis, C.cDscBreve, D.lAutorizado, B.lAutorizado as lAutorizadoA, B.iCveExamCualita as iCveAutorizado,")
					.append("         A.iCveExamCualita, B.lResultado ")
					.append(" from TOXExamAnalisis A ")
					.append(" inner join TOXAnalisis B on B.iAnio           = A.iAnio ")
					.append("                         and B.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                         and B.iCveAnalisis    = A.iCveAnalisis ")
					.append(" inner join TOXExamenCualita D on D.iAnio           = A.iAnio ")
					.append("                              and D.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                              and D.iCveLoteCualita = A.iCveLoteCualita ")
					.append("                              and D.iCveExamCualita = A.iCveExamCualita ")
					.append(" left join TOXCtrolCalibra C on C.iCveLaboratorio  = B.iCveLaboratorio ")
					.append("                            and C.iCveCtrolCalibra = B.iCveCtrolCalibra ")
					.append(" where A.iAnio = ? ")
					.append("   and A.iCveLaboratorio = ?")
					.append("   and A.iCveLoteCualita = ?")
					.append("   and A.iCveExamCualita = ?")
					.append(" order by A.iCveAnalisis ");
			// System.out.println("cSQL");
			pstmt = conn.prepareStatement(cSQL.toString());
			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAnalisis = new TVTOXAnalisis();
				vAnalisis.setCDscMotivo(rset.getString("cDscBreve"));
				vAnalisis.setlAutorizado(new Integer(rset
						.getInt("lAutorizadoA")));
				vAnalisis.setiCveExamCualita(new Integer(rset
						.getInt("iCveAutorizado")));
				if (rset.getInt("lAutorizadoA") == 1
						&& rset.getInt("iCveAutorizado") == rset
								.getInt("iCveExamCualita")) {
					vAnalisis.setlPresuntoPost(new Integer(1));
				} else if (rset.getInt("lAutorizado") == 1) {
					vAnalisis.setlPresuntoPost(new Integer(0));
				} else {
					vAnalisis.setlPresuntoPost(new Integer(2));
				}
				if (rset.getInt("lAutorizadoA") == 1) {
					vAnalisis.setlResultado(new Integer(rset
							.getInt("lResultado")));
				} else {
					vAnalisis.setlResultado(new Integer(2));
				}
				vcTOXAnalisis.put(String.valueOf(rset.getInt("iCveAnalisis")),
						vAnalisis);
			}
		} catch (Exception ex) {
			warn("FindAnalisisCtrolCalibra", ex);
			ex.printStackTrace();
			throw new DAOException("FindAnalisisCtrolCalibra");
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
				warn("FindAnalisisCtrolCalibra.close", ex2);
			}
			return vcTOXAnalisis;
		}
	}

	/**
	 * Metodo Update lResultado solo para actualizar el campo lResultado y
	 * iSustConf
	 */
	public Object updateCtrolExt(Connection conGral, Object obj)
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
			TVTOXAnalisis VAnalisis = (TVTOXAnalisis) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXAnalisis         "
					+ "    set iCveCtrolCalibra      = ? "
					+ "  where iAnio           = ? "
					+ "    and iCveLaboratorio = ? "
					+ "    and iCveAnalisis    = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, VAnalisis.getiCveCtrolCalibra().intValue());
			pstmt.setInt(2, VAnalisis.getiAnio().intValue());
			pstmt.setInt(3, VAnalisis.getiCveLaboratorio().intValue());
			pstmt.setInt(4, VAnalisis.getiCveAnalisis().intValue());
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
	 * Met�do que regresa la informaci�n de las muestras confirmadas que
	 * fueron positivas
	 * 
	 * @param connE
	 *            Conexi�n a la base de datos.
	 * @param OLote
	 *            Objeto TVTOXLoteCuantita con la informaci�n del lote que se
	 *            est� analizando
	 * @param OEnvio
	 *            Objeto TVTOXEnvio con la informaci�n del Env�o a
	 *            consultar.
	 * @return Regresa el vector de objetos TVMuestra con la informaci�n del
	 *         query.
	 * @throws DAOException
	 */
	public Vector MuestrasPost(Connection connE, String cFiltro)
			throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMuestra = new Vector();
		StringBuffer cSQL;
		TVMuestra VMuestra;
		try {
			if (connE == null) {
				DbConnection dbConn = null;
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else {
				conn = connE;

				// Query
			}
			cSQL = new StringBuffer();
			cSQL.append(" select A.iAnio, A.iCveLaboratorio, A.iCveAnalisis, ")
					// 1,2,3
					.append("        A.iCveMuestra, A.lResultado, ")
					// 4, 5
					.append("        A.iSustPost, A.iSustConf, ")
					// 6, 7
					.append("        CA.iCveSustancia, CA.iCveLoteCuantita, LC.dtAnalisis, ")
					// 8, 9, 10
					.append("        M.iCveUniMed, M.dtRecoleccion, UM.cDscUniMed, ")
					// 11, 12, 13
					.append("        MM.cDscModulo, ")
					// 14
					.append("        M.iCveProceso, M.iCveMotivo, ")
					// 15, 16
					.append("        P.cDscProceso, PM.cDscMotivo, ")
					// 17, 18
					.append("        S.cDscSustancia, S.cTitRepConf, ")
					// 19, 20
					.append("        A.dtDesecho ")
					// 21
					.append("  from TOXAnalisis A ")
					.append("  inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio ")
					.append("                                and CA.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                                and CA.iCveAnalisis    = A.iCveAnalisis    ")
					.append("  inner join TOXLoteCuantita  LC on LC.iAnio            = CA.iAnio ")
					.append("                                and LC.iCveLaboratorio  = CA.iCveLaboratorio ")
					.append("                                and LC.iCveSustancia    = CA.iCveSustancia ")
					.append("                                and LC.iCveLoteCuantita = CA.iCveLoteCuantita ")
					.append("  inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia ")
					.append("  inner join TOXMuestra M on M.iAnio       = A.iAnio  ")
					.append("                         and M.iCveMuestra = A.iCveMuestra ")
					.append("  inner join GRLUniMed UM on UM.iCveUniMed = M.iCveUniMed ")
					.append("  inner join GRLModulo MM on MM.iCveUniMed = M.iCveUniMed ")
					.append("                         and MM.iCveModulo = M.iCveModulo ")
					.append("  inner join GRLProceso P on P.iCveProceso = M.iCveProceso ")
					.append("  inner join GRLMotivo PM on PM.iCveMotivo = M.iCveMotivo ")
					.append(cFiltro);
			// System.out.println("Busqueda Positivos \n" + cSQL.toString());
			// Ejecutar el query
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			int iCveMuestra = 0;
			while (rset.next()) {
				VMuestra = new TVMuestra();
				// Obtener informaci�n
				if (iCveMuestra != rset.getInt(4)) {
					iCveMuestra = rset.getInt(4);
					VMuestra.setIAnio(rset.getInt(1));
					VMuestra.setICveLaboratorio(rset.getInt(2));
					VMuestra.setICveAnalisis(rset.getInt(3));
					VMuestra.setICveMuestra(rset.getInt(4));
					VMuestra.setLResultado(rset.getInt(5));
					VMuestra.setISustPost(rset.getInt(6));
					VMuestra.setISustConf(rset.getInt(7));
					VMuestra.setdtRecoleccion(rset.getDate(12));
					VMuestra.setCDscUM(rset.getString(13));
					VMuestra.setCDscModulo(rset.getString(14));
					VMuestra.setCDscProceso(rset.getString(17));
					VMuestra.setCDscMotivo(rset.getString(18));
					VMuestra.setDtDeshecho(rset.getDate(21));
					// Consultar los resultados de las muestras positivas
					if (VMuestra.getLResultado() == 1) {
						Vector VResultado = new Vector();
						// Verificar cuantas sustancias se confirmaron
						if (VMuestra.getISustPost() == 1) {
							TVTOXLoteCuantita VLote = new TVTOXLoteCuantita();
							// Obtener informaci�n
							VLote.setiAnio(new Integer(rset.getInt(1)));
							VLote.setiCveSustancia(new Integer(rset.getInt(8)));
							VLote.setiCveLoteCuantita(new Integer(rset
									.getInt(9)));
							VLote.setdtAnalisis(rset.getDate(10));
							VLote.VSustancia = new TVSustancia();
							VLote.VSustancia.setCDscSustancia(rset
									.getString(19));
							VLote.VSustancia.setCTitRepConf(rset.getString(20));
							VResultado.add(VLote);
						} else {
							VResultado = new TDTOXLoteCuantita().SustPost(conn,
									VMuestra);
						}
						VMuestra.vResultado = VResultado;
					}
					// Agregar las muestras
					vcTOXMuestra.add(VMuestra);
				} // Muestras diferentes
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("MuestrasPost", ex);
			throw new DAOException("MuestrasPost");
		} finally {
			try {
				if (connE == null) {
					if (rset != null) {
						rset.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
					if (dbConn != null) {
						dbConn.closeConnection();
					}
				}
			} catch (Exception ex2) {
				warn("MuestrasPost.close", ex2);
			}
			return vcTOXMuestra;
		}
	}

	/**
	 * Metodo Update lResultado solo para actualizar el campo lResultado y
	 * iSustConf
	 */
	public int updateDeshecho(Vector vMuestras) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iSentencia = 0;
		TFechas tFecha = new TFechas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra VMuestra = new TVMuestra();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXAnalisis         "
					+ "    set dtDesecho      = ? "
					+ "  where iAnio           = ? "
					+ "    and iCveLaboratorio = ? "
					+ "    and iCveAnalisis    = ? ";
			pstmt = conn.prepareStatement(cSQL);
			for (int i = 0; i < vMuestras.size(); i++) {
				// System.out.println("Muestra "+ i + " " +
				// VMuestra.getICveMuestra() );
				VMuestra = (TVMuestra) vMuestras.get(i);
				pstmt.setDate(1, tFecha.TodaySQL());
				pstmt.setInt(2, VMuestra.getIAnio());
				pstmt.setInt(3, VMuestra.getICveLaboratorio());
				pstmt.setInt(4, VMuestra.getICveAnalisis());
				iSentencia += pstmt.executeUpdate();
				VMuestra.setDtDeshecho(tFecha.TodaySQL());
			}
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
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
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				ex2.printStackTrace();
				warn("update.close", ex2);
			}
			return iSentencia;
		}
	}

}
