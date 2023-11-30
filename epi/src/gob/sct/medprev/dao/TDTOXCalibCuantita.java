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
 * Title: Data Acces Object de TOXCalibCuantita DAO
 * </p>
 * <p>
 * Description: Lotes Cuantitativos
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

public class TDTOXCalibCuantita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCalibCuantita() {
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
		Vector vcTOXCalibCuantita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCalibCuantita vTOXCalibCuantita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " TOXCalibCuantita.iCveLaboratorio,"
					+ " TOXCalibCuantita.iCveCalib,"
					+ " TOXCalibCuantita.iPosicion,"
					+ " TOXCalibCuantita.cDscCalib,"
					+ " TOXCalibCuantita.dPorcentaje,"
					+ " TOXCalibCuantita.lControl " + " from TOXCalibCuantita "
					+ cvFiltro + " ";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalibCuantita = new TVTOXCalibCuantita();
				vTOXCalibCuantita
						.setiCveLaboratorio(new Integer(rset.getInt(1)));
				vTOXCalibCuantita.setiCveCalib(new Integer(rset.getInt(2)));
				vTOXCalibCuantita.setiPosicion(new Integer(rset.getInt(3)));
				vTOXCalibCuantita.setcDscCalib(rset.getString(4));
				double dvPorcentaje = rset.getDouble(5);
				vTOXCalibCuantita.setdPorcentaje(new Double(dvPorcentaje));
				vTOXCalibCuantita.setlControl(new Integer(rset.getInt(6)));
				vcTOXCalibCuantita.addElement(vTOXCalibCuantita);
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
			return vcTOXCalibCuantita;
		}
	}
}