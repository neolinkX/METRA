package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;

import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;

/**
 * <p>
 * Title: Data Acces Object de EXPServicio DAO
 * </p>
 * <p>
 * Description: Extension de la clase TDEXPServicio que obtiene datos de la tabla EXPServicio
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

public class TDEXPServicioExt1 extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregado por Rafael Alcocer Caldera 08/Nov/2006
	private int iUpdated; // Agregado por Rafael Alcocer Caldera 08/Nov/2006

	public TDEXPServicioExt1() {
	}

	
	/**
	 * Método getLConcluidoLabAnalisisClinicos
	 * Laboratorio de analisis clinicos no podra ser concluido hasta que se haya realizado el
	 * llenado total de lo siguientes servicios:
	 * Signos Vitales(11) / Antecedentes heredofamiliares (49)/ antecedentes personales no patologicos (52) /
	 * Antecedentes personales Patologicos (50)/ Interrogatorio por aparatos y sistemas (48)
	 * Exploracion Fisica (54) / Cardiologia y Electrocardiograma (10)
	 */
	public Boolean getLConcluidoLabAnalisisClinicos(String cExpediente, String cExamen,
			String cServicio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean aplicarLaboratorio = false;
		int ServicioConcluidos = 0;
		CargaSintomas sintomas = new CargaSintomas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPServicio vEXPServicio;
			String cSQL = "select count(iCveExpediente) "
					+ "from EXPServicio " + "where iCveExpediente =  "
					+ cExpediente + "  and iNumExamen =  " + cExamen
					+ " and iCveServicio in (11,49,52,50,48,54,10)"
					+ " and lConcluido = 1";

		    System.out.println("Query getLConcluidoLabAnalisisClinicos: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				ServicioConcluidos = rset.getInt(1);
			}
			if(ServicioConcluidos == 7){
				aplicarLaboratorio = true;
			}
			
			
		} catch (Exception ex) {
			warn("getLConcluido", ex);
			throw new DAOException("getLConcluido");
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getLConcluido.close", ex2);
			}
		}
		return aplicarLaboratorio;
	}
	

}
