package gob.sct.medprev.dao;

import gob.sct.medprev.vo.TVPERDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.micper.excepciones.DAOException;
import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

public class TDSEGUsuExp extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDSEGUsuExp() {
	}
	
	/**
	 * Metodo Find By Selector (Utilizado para la Seleccion de personal) AG
	 * SA
	 */
	public boolean FindByEsTercero(int cUsuario) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean EsTercero = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT COUNT(ICVEUSUARIO) FROM SEGUSUEXP " +
					" WHERE LLICENCIAS = 0 AND ICVEMDOTRANS = 0 AND ICVEUSUARIO = "+cUsuario;

			//System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1)>0){
					EsTercero = true;
					}
			}
			//System.out.println("EsTercero="+EsTercero);
		} catch (Exception ex) {
			warn("FindBySelector", ex);
			throw new DAOException("FindBySelector");
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
				warn("FindBySelector.close", ex2);
			}
			return EsTercero;
		}
	}
	
}
