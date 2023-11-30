package com.micper.sql;

import java.sql.*;
import javax.sql.*;
import java.util.*;

import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.seguridad.vo.*;
import com.micper.util.logging.*;
import com.micper.excepciones.*;

/**
 * <p>
 * Title: Clase que hace conexion a una base de datos
 * </p>
 * <p>
 * Description: Clase que hace conexion a una base de datos
 * </p>
 * <p>
 * 
 * @author Tecnología Inred S.A. de C.V.
 * @author <dd>Ing. Miguel Angel Magos Ruiz
 *         <p>
 * @version 1.0
 */

public class DAOBase {

	private TParametro vParametros = null;
	protected String dataSourceName = null;
	protected String cError = "";
	protected DbConnection dbConn;
	protected Connection conn;
	protected DataSource dataSource;

	public DAOBase() {
	}

	/**
	 * Método que se encarga de cerrar una conexión previamente establecida.
	 * 
	 * @param cModulo
	 *            Objeto que es el módulo general del Sistema
	 */

	public void setSistema(String cModulo) {
		vParametros = new TParametro(cModulo);
		dataSourceName = vParametros.getPropEspecifica("ConDBModulo");
	}

	/**
	 * Método que permite la posibilidad de llamar al DAO a través del
	 * AppCacheManager.
	 * 
	 * @param String
	 *            , permite pasar una lista de parametros para hacer la
	 *            búsqueda.
	 * @return boolean, permite conocer el resultado de la búsqueda.
	 */
	public boolean findCollection(String cCollName, String cKey) {
		return false;
	}

	/**
	 * Método que permite la posibilidad de llamar al DAO a través del
	 * AppCacheManager.
	 * 
	 * @return Object, Objeto que regresa el resultado de la búsqueda.
	 */
	public Object getCollection() {
		return "";
	}

	/**
	 * Método Find By All Genérico
	 */
	public final Vector FindByGeneric(String cSQL, String dataSourceName)
			throws DAOException {
		this.dataSourceName = dataSourceName;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcDinRep = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			System.out.println(cSQL);
						
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			vcDinRep = this.getVO(rset);


		} catch (Exception ex) {
			cError = ex.toString();
			ex.printStackTrace();
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
				warn("FindByGeneric.close", ex2);
			}
			if (!cError.equals(""))
				throw new DAOException(cError);
			return vcDinRep;
		}
	}

	/**
	 * Método que permite dado un resultset crear un VO dinámico con los nombres
	 * de los campos
	 * 
	 * @return Vector de VODinámicos.
	 */
	public Vector getVO(ResultSet rset) throws DAOException {
		Vector vcDinRep = new Vector();
		int iTipo = 0;
		try {
			ResultSetMetaData rsetMD = rset.getMetaData();

			TVDinRep vDinRep;
			int iNumCols = rsetMD.getColumnCount();

			while (rset.next()) {
				vDinRep = new TVDinRep();
				for (int count = 1; count <= iNumCols; count++) {
					if (count >= 1) {
						iTipo = this.getITipo(rsetMD.getColumnName(count));
						switch (iTipo) {
						case 1:
							vDinRep.put(rsetMD.getColumnName(count),
									rset.getInt(count));
							break;
						case 2:
							vDinRep.put(rsetMD.getColumnName(count),
									rset.getString(count));
							break;
						case 3:
							vDinRep.put(rsetMD.getColumnName(count),
									rset.getDate(count));
							break;
						case 4:
							vDinRep.put(rsetMD.getColumnName(count),
									rset.getFloat(count));
							break;
						default:
							vDinRep.put(rsetMD.getColumnName(count),
									rset.getString(count));
						}
					}
				}
				vcDinRep.addElement(vDinRep);
			}
		} catch (Exception e) {
			warn("setVO", e);
			throw new DAOException("setVO");
		}
		return vcDinRep;

	}

	/**
	 * Método que permite obtener el tipo de dato en base al estandar para
	 * identificadores
	 * 
	 * @return tipo 1:Entero, 2: String, 3: SQL Fecha, 4: Float, 0: No
	 *         reconocido -> String.
	 */
	public int getITipo(String cColumnName) {
		int iTipo = 0;

		if (cColumnName.length() > 1) {

			String cTipo1 = cColumnName.substring(0, 1);
			String cTipo2 = cColumnName.substring(0, 2);
			if (cTipo1.equals("I") || cTipo1.equals("L")) {
				iTipo = 1;
			}
			if (cTipo1.equals("C")) {
				iTipo = 2;
			}
			if (cTipo1.equals("D")) {
				if (cTipo2.equals("DT")) {
					iTipo = 3;
				} else {
					iTipo = 4;
				}
			}
		} else {
			if (cColumnName.length() == 1) {
				iTipo = 2;
			}
		}
		return iTipo;
	}

	/**
	 * Método encargado de enviar mensajes de advertencia que el programador
	 * decida enviar, dentro del metodo catch.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	protected void warn(String Aviso, Exception ex) {
		TLogger.log(this, TLogger.WARN, Aviso, ex);
	}

	/**
	 * Método encargado de enviar mensajes informativos que el programador
	 * decida enviar. Se pueden poner en cualquier parte del código.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 */
	public void info(String Aviso) {
		TLogger.log(this, TLogger.INFO, Aviso, null);
	}

	/**
	 * Método encargado de enviar mensajes de debugueo que el programador decida
	 * enviar. Se pueden poner en cualquier parte del código y tendrán la opcion
	 * de poderse apagar mediante una bandera.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 */
	public void debug(String Aviso) {
		TLogger.log(this, TLogger.DEBUG, Aviso, null);
	}

	/**
	 * Método encargado de enviar mensajes de error que el programador decida
	 * enviar, dentro del metodo catch.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	public void error(String Aviso, Exception ex) {
		TLogger.log(this, TLogger.ERROR, Aviso, ex);
	}

	/**
	 * Método encargado de enviar mensajes de errores fatales que el programador
	 * decida enviar, dentro del metodo catch.
	 * 
	 * @param obj
	 *            se refiere al modulo en donde ocurre el error.
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	public void fatal(String Aviso, Exception ex) {
		TLogger.log(this, TLogger.FATAL, Aviso, ex);
	}
	
	
	 
	   public final Vector FindByGeneric2(String cKey, String cSQL, String dataSourceName)
	     throws DAOException, SQLException
	   {
		  //System.out.println("FindByGeneric de DaoBase");
	     this.dataSourceName = dataSourceName;
	     DbConnection dbConn = null;
	     Connection conn = null;
	     PreparedStatement pstmt = null;
	     ResultSet rset = null;
	     Vector vcDinRep = new Vector();     
	     try {
	       dbConn = new DbConnection(dataSourceName);
	       conn = dbConn.getConnection();
	       conn.setAutoCommit(false);
	       conn.setTransactionIsolation(2);
	       //System.out.println(cSQL);
	 
	       pstmt = conn.prepareStatement(cSQL);
	       rset = pstmt.executeQuery();
	 
	       vcDinRep = getVO(cKey, rset);
	     }
	     catch (Exception ex) {
	       this.cError = ex.toString();
	       ex.printStackTrace();
	     }finally{///AG SA 29-06-2017
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
	           //System.out.println("fin FindByGeneric de DaoBase");
	           return vcDinRep;
	     } /////////////////////////////////////
	     
	   }
	 

	   public Vector getVO(String cKey, ResultSet rset)
	     throws DAOException
	   {
	     Vector vcDinRep = new Vector();
	     int iTipo = 0;
	     try {
	       ResultSetMetaData rsetMD = rset.getMetaData();
	 
	       int iNumCols = rsetMD.getColumnCount();
	 
	       while (rset.next()) {
	         TVDinRep vDinRep = new TVDinRep();
	         vDinRep.setLlave(cKey.toUpperCase());
	         for (int count = 1; count <= iNumCols; count++) {
	           if (count >= 1) {
	             iTipo = getITipo(rsetMD.getColumnName(count));
	             switch (iTipo) {
	             case 1:
	               vDinRep.put(rsetMD.getColumnName(count), rset.getInt(count));
	               break;
	             case 2:
	               vDinRep.put(rsetMD.getColumnName(count), rset.getString(count));
	               break;
	             case 3:
	               vDinRep.put(rsetMD.getColumnName(count), rset.getDate(count));
	               break;
	             case 4:
	               vDinRep.put(rsetMD.getColumnName(count), rset.getFloat(count));
	               break;
	             case 6:
	               vDinRep.put(rsetMD.getColumnName(count), rset.getTimestamp(count));
	               break;
	             case 5:
	             default:
	               vDinRep.put(rsetMD.getColumnName(count), rset.getString(count));
	             }
	           }
	         }
	         vcDinRep.addElement(vDinRep);
	       }
	     } catch (Exception e) {
	       warn("setVO", e);
	       throw new DAOException("setVO");
	     }
	     return vcDinRep;
	   }

}
