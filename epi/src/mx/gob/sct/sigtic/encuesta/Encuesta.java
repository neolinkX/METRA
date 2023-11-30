package mx.gob.sct.sigtic.encuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.micper.ingsw.TParametro;
import com.micper.sql.DbConnection;

public class Encuesta {
	private String dataSrcName = "";
	private TParametro vParametros;
	
	public int isDone3(String cUser, String cSys) {
		vParametros = new TParametro("07");
		int done = -3;//Estado Inicial
		///Conexion SysMedprev//////////////
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		///////////////////////////////////
		String sql = "SELECT VALIDACION FROM PREGUNTAS_ENCUESTA WHERE FORMA_DE_TRABAJO=? AND AREA=?";
		int resultado = 2;
		try {
			
			///Conexion SysMedprev//////////////
			dataSrcName = vParametros.getPropEspecifica("ConDBSIGTIC");
			System.out.println(dataSrcName);
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);
			lPStmt = conn.prepareStatement(sql);
			lPStmt.setString(1, cUser);
			lPStmt.setString(2, cSys);
			lRSet = lPStmt.executeQuery();
			while (lRSet.next()) {
				resultado = lRSet.getInt(1);	
				System.out.println("lRSet: "+lRSet.getInt(1));
			}
			///////////////////////////////////			
		} catch (Exception e) {
			done=-1;
			System.out.println("(-1) estado error: "+e.toString());
			//e.printStackTrace();
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
			} catch (Exception e) {
				done=-2;
				System.out.println("(-2) estado error por cerrar rs, stmt: "+e.toString());
				e.printStackTrace();
			}
		}
		
		if (resultado == 1) {
			done = 1;
			System.out.println("(1)USUARIO YA hizo encuesta");
		}
		else if(resultado== 2 && done== -3){
			done=2;
			System.out.println(" (2) Estado inicial - no aparece en tabla (no ha realizado encuesta)");
		}
		if(cUser.substring(0, 3).equalsIgnoreCase("EXT")){
			done=3;
			System.out.println("(3) usuario Externo (usuario Externo) ");
		}
		
		System.out.println("done: "+ done);

		return done;
	}
	
	
}
