package gob.sct.medprev.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EXPResultado DAO
 * 30 de Julio 2015
 * @author AG SA L
 * @version 1.0
 */

public class TDEXPResultadoDos extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	public TDEXPResultadoDos() {
	}

	/**
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public Vector<TVEXPResultado> FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector<TVEXPResultado> vcEXPResultado = new Vector<TVEXPResultado>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVEXPResultado vEXPResultado;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "EXPResultado.iCveExpediente,"
					+ "EXPResultado.iNumExamen," + "EXPResultado.iCveServicio,"
					+ "EXPResultado.iCveRama," + "EXPResultado.iCveSintoma,"
					+ "EXPResultado.dValorIni," + "EXPResultado.lLogico,"
					+ "EXPResultado.cCaracter," + "EXPResultado.dValorFin" +
					// "MEDSintomas.cPregunta" +
					" from EXPResultado " + cWhere + " order by iCveExpediente";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEXPResultado = new TVEXPResultado();
				vEXPResultado.setICveExpediente(rset.getInt(1));
				vEXPResultado.setINumExamen(rset.getInt(2));
				vEXPResultado.setICveServicio(rset.getInt(3));
				vEXPResultado.setICveRama(rset.getInt(4));
				vEXPResultado.setICveSintoma(rset.getInt(5));

				// vEXPResultado.setDValorIni(rset.getFloat(6)); //FCS : Se
				// cambio el Tipo De Dato, de float a Double,
				// ya que formateaba a 4 posiciones en la parte decimal y le
				// resta 1,
				// ver linea siguiente.
				vEXPResultado.setDValorIni(rset.getDouble(6));

				vEXPResultado.setLLogico(rset.getInt(7));
				vEXPResultado.setCCaracter(rset.getString(8));
				vEXPResultado.setDValorFin(rset.getFloat(9));
				vcEXPResultado.addElement(vEXPResultado);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcEXPResultado;
		}
	}
	
	

	/**
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public String RegresaResp16(String expediente, String examen, String servicio, String rama, String sintoma) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String resultadoSintoma = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "cCaracter" +
					" from EXPResultadoDos " +
					"where " +
					"icveexpediente = ? and " +
					"inumexamen = ? and " +
					"icveservicio = ? and " +
					"icverama = ? and " +
					"icvesintoma = ? ";
			//System.out.println("#"+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, expediente);
			pstmt.setString(2, examen);
			pstmt.setString(3, servicio);
			pstmt.setString(4, rama);
			pstmt.setString(5, sintoma);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				resultadoSintoma = rset.getString(1);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return resultadoSintoma;
		}
	}
	

	/**
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public String RegresaResp15(String expediente, String examen, String servicio, String rama, String sintoma) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		ResultSet rset3 = null;
		ResultSet rset4 = null;
		String resultadoSintoma = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQLPais = "";
			String cSQLEstado = "";
			String cSQLMunicipio = "";
			String cSQLLocalidad = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			////////////Obteniendo Pais ///////////////
			cSQLPais = "select P.cNombre from expResultadoDos as R, grlPais as P where R.icvePais = P.icvePais and " +
					"R.icveexpediente = ? and R.inumexamen = ? and R.icveservicio = ? and R.icverama = ? and R.icvesintoma = ? ";
			//System.out.println("#"+cSQLPais);
			pstmt = conn.prepareStatement(cSQLPais);
			pstmt.setString(1, expediente);
			pstmt.setString(2, examen);
			pstmt.setString(3, servicio);
			pstmt.setString(4, rama);
			pstmt.setString(5, sintoma);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				resultadoSintoma = "Pa&iacute;s: "+rset.getString(1);
			}
			
			//////////// Obteniendo Estado ///////////////
			cSQLEstado = "select E.cNombre from expResultadoDos as R, grlEntidadFed as E where R.icvePais = E.icvePais and R.icveEntidadFed = E.icveEntidadFed and " +
					"R.icveexpediente = ? and R.inumexamen = ? and R.icveservicio = ? and R.icverama = ? and R.icvesintoma = ? ";
			//System.out.println("#"+cSQLEstado);
			pstmt2 = conn.prepareStatement(cSQLEstado);
			pstmt2.setString(1, expediente);
			pstmt2.setString(2, examen);
			pstmt2.setString(3, servicio);
			pstmt2.setString(4, rama);
			pstmt2.setString(5, sintoma);
			rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				resultadoSintoma = resultadoSintoma +" <br> Entidad Federativa: "+ rset2.getString(1);
			}
			
			//////////// Obteniendo Municipio ///////////////
			cSQLMunicipio = "select M.cNombre from expResultadoDos as R, grlMunicipio as M where R.icvePais = M.icvePais and " +
					"R.icveEntidadFed = M.icveEntidadFed and R.icveEntidadFed = M.icveMunicipio and " +
					"R.icveexpediente = ? and R.inumexamen = ? and R.icveservicio = ? and R.icverama = ? and R.icvesintoma = ? ";
			//System.out.println("#"+cSQLMunicipio);
			pstmt3 = conn.prepareStatement(cSQLMunicipio);
			pstmt3.setString(1, expediente);
			pstmt3.setString(2, examen);
			pstmt3.setString(3, servicio);
			pstmt3.setString(4, rama);
			pstmt3.setString(5, sintoma);
			rset3 = pstmt3.executeQuery();
			while (rset3.next()) {
				resultadoSintoma = resultadoSintoma +" <br> Municipio: "+ rset3.getString(1);
			}
						
			//////////// Obteniendo Localidad ///////////////
			cSQLLocalidad = "select L.cNombreLoc from expResultadoDos as R, grlLocalidad as L where R.icvePais = L.icvePais and R.icveEntidadFed = L.icveEntidadFed and " +
					"R.icveEntidadFed = L.icveMunicipio and R.icveLocalidad = L.icveLocalidad and " +
					"R.icveexpediente = ? and R.inumexamen = ? and R.icveservicio = ? and R.icverama = ? and R.icvesintoma = ? ";
			//System.out.println("#"+cSQLLocalidad);
			pstmt4 = conn.prepareStatement(cSQLLocalidad);
			pstmt4.setString(1, expediente);
			pstmt4.setString(2, examen);
			pstmt4.setString(3, servicio);
			pstmt4.setString(4, rama);
			pstmt4.setString(5, sintoma);
			rset4 = pstmt4.executeQuery();
			while (rset4.next()) {
				resultadoSintoma = resultadoSintoma +" <br> Localidad: "+ rset4.getString(1);
			}
			
			resultadoSintoma = resultadoSintoma.toString();
			
			
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return resultadoSintoma;
		}
	}
		

	/**
	 * Método Find By All
	 */
	@SuppressWarnings("finally")
	public String RegresaResp14(String expediente, String examen, String servicio, String rama, String sintoma) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String resultadoSintoma = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "tiFecha" +
					" from EXPResultadoDos " +
					"where " +
					"icveexpediente = ? and " +
					"inumexamen = ? and " +
					"icveservicio = ? and " +
					"icverama = ? and " +
					"icvesintoma = ? ";
			//System.out.println("#"+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, expediente);
			pstmt.setString(2, examen);
			pstmt.setString(3, servicio);
			pstmt.setString(4, rama);
			pstmt.setString(5, sintoma);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				resultadoSintoma = rset.getString(1);
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (final Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return resultadoSintoma;
		}
	}

	/**
	 * /** Método Insert
	 */
	@SuppressWarnings("finally")
	public Object insertResp14(Connection conGral, Object obj) throws DAOException {
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			////////////////Convertir String a Timestamp ////////////////////
			//System.out.println("fecha = "+vEXPResultado.getCCaracter());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			//java.util.Date date = sdf.parse("2004-07-24 09:45:52.189");
			java.util.Date date = sdf.parse(""+vEXPResultado.getCCaracter());
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			
			int registro = 0;
			registro = this.RegresaInt("Select count(icveexpediente) from expresultadoDos where "
					+" icveexpediente = "+vEXPResultado.getICveExpediente()
					+" and inumexamen = "+vEXPResultado.getINumExamen()
					+" and icveservicio = "+vEXPResultado.getICveServicio()
					+" and icverama = "+vEXPResultado.getICveRama()
					+" and icvesintoma = "+vEXPResultado.getICveSintoma());
			
			if(registro>0){
				return cClave;
			}
			
			cSQL = "insert into EXPResultadoDos(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "tiFecha"
					+ ")values(?,?,?,?,?,?)";
			
			
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vEXPResultado.getICveExpediente());
			pstmt.setInt(2, vEXPResultado.getINumExamen());
			pstmt.setInt(3, vEXPResultado.getICveServicio());
			pstmt.setInt(4, vEXPResultado.getICveRama());
			pstmt.setInt(5, vEXPResultado.getICveSintoma());
			pstmt.setTimestamp(6, timestamp);
			pstmt.executeUpdate();
			cClave = "" + vEXPResultado.getICveExpediente();
			
			conn.commit();
			
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
			} catch (final Exception ex2) {
				warn("insert16.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * /** Método Insert
	 */
	@SuppressWarnings("finally")
	public Object insertResp15(Connection conGral, Object obj) throws DAOException {
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			int registro = 0;
			registro = this.RegresaInt("Select count(icveexpediente) from expresultadoDos where "
					+" icveexpediente = "+vEXPResultado.getICveExpediente()
					+" and inumexamen = "+vEXPResultado.getINumExamen()
					+" and icveservicio = "+vEXPResultado.getICveServicio()
					+" and icverama = "+vEXPResultado.getICveRama()
					+" and icvesintoma = "+vEXPResultado.getICveSintoma());
			
			if(registro>0){
				return cClave;
			}
			
			
			////////////////Convertir String a Int  ////////////////////
			//System.out.println("Entidades = "+vEXPResultado.getCCaracter());
			//String colores = "rojo,amarillo,verde,azul,morado,marrón";
			String entidades = ""+vEXPResultado.getCCaracter();
			String[] arrayEntidades = entidades.split(",");
			int iPais = 0;
			int iEstado = 0;
			int iMunicipio = 0;
			int iLocalidad = 0;
			///Obtener Pais
			try{iPais = Integer.parseInt(""+arrayEntidades[0]);}
			catch (NumberFormatException nfe){iPais =0;}
			///Obtener Estado
			try{iEstado = Integer.parseInt(""+arrayEntidades[1]);}
			catch (NumberFormatException nfe){iEstado =0;}
			///Obtener Municipio
			try{iMunicipio = Integer.parseInt(""+arrayEntidades[2]);}
			catch (NumberFormatException nfe){iMunicipio =0;}
			///Obtener Localidad
			try{iLocalidad = Integer.parseInt(""+arrayEntidades[3]);}
			catch (NumberFormatException nfe){iLocalidad =0;}
			
			
			cSQL = "insert into EXPResultadoDos(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "iCvePais," + "iCveEntidadFed,"
					+ "iCveMunicipio," + "iCveLocalidad"
					+ ")values(?,?,?,?,?,?,?,?,?)";
			
			
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vEXPResultado.getICveExpediente());
			pstmt.setInt(2, vEXPResultado.getINumExamen());
			pstmt.setInt(3, vEXPResultado.getICveServicio());
			pstmt.setInt(4, vEXPResultado.getICveRama());
			pstmt.setInt(5, vEXPResultado.getICveSintoma());
			pstmt.setInt(6, iPais);
			pstmt.setInt(7, iEstado);
			pstmt.setInt(8, iMunicipio);
			pstmt.setInt(9, iLocalidad);

			pstmt.executeUpdate();
			cClave = "" + vEXPResultado.getICveExpediente();
			
			conn.commit();
			
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
			} catch (final Exception ex2) {
				warn("insert16.close", ex2);
			}
			return cClave;
		}
	}	


	/**
	 * /** Método Insert
	 */
	@SuppressWarnings("finally")
	public Object insertResp16(Connection conGral, Object obj) throws DAOException {
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			
			int registro = 0;
			registro = this.RegresaInt("Select count(icveexpediente) from expresultadoDos where "
					+" icveexpediente = "+vEXPResultado.getICveExpediente()
					+" and inumexamen = "+vEXPResultado.getINumExamen()
					+" and icveservicio = "+vEXPResultado.getICveServicio()
					+" and icverama = "+vEXPResultado.getICveRama()
					+" and icvesintoma = "+vEXPResultado.getICveSintoma());
			
			if(registro>0){
				return cClave;
			}
			
			
			cSQL = "insert into EXPResultadoDos(" + "iCveExpediente,"
					+ "iNumExamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma," + "cCaracter"
					+ ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vEXPResultado.getICveExpediente());
			pstmt.setInt(2, vEXPResultado.getINumExamen());
			pstmt.setInt(3, vEXPResultado.getICveServicio());
			pstmt.setInt(4, vEXPResultado.getICveRama());
			pstmt.setInt(5, vEXPResultado.getICveSintoma());
			pstmt.setString(6, vEXPResultado.getCCaracter());
			pstmt.executeUpdate();
			cClave = "" + vEXPResultado.getICveExpediente();
			
			conn.commit();
			
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
			} catch (final Exception ex2) {
				warn("insert16.close", ex2);
			}
			return cClave;
		}
	}	
	
	
	/**
	 * Método update
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
			TVEXPResultado vEXPResultado = (TVEXPResultado) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EXPResultado" + " set dValorIni= ?, "
					+ "lLogico= ?, " + "cCaracter= ?, "
					+ "dValorFin= ? "
					+
					// "cValRef= ? " +
					"where iCveExpediente = ? " + "and iNumExamen = ?"
					+ "and iCveServicio = ?" + "and iCveRama = ?"
					+ " and iCveSintoma = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDouble(1, vEXPResultado.getDValorIni());

			if (vEXPResultado.getLLogico() != 99) {
				pstmt.setInt(2, vEXPResultado.getLLogico());
			} else {
				pstmt.setNull(2, Types.INTEGER);
			}
			pstmt.setString(3, vEXPResultado.getCCaracter());
			pstmt.setFloat(4, vEXPResultado.getDValorFin());

			pstmt.setInt(5, vEXPResultado.getICveExpediente());
			pstmt.setInt(6, vEXPResultado.getINumExamen());
			pstmt.setInt(7, vEXPResultado.getICveServicio());
			pstmt.setInt(8, vEXPResultado.getICveRama());
			pstmt.setInt(9, vEXPResultado.getICveSintoma());
			pstmt.executeUpdate();
			cClave = "";
			
			////Se agrego la siguiente linea el dia 24 de marzo 2014 y se comentaron las posterires
			conn.commit();
			/*
			if (conGral == null) {
				conn.commit();
			}*/
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
	}


	/**
	 * Método Delete
	 */
	public int LiberaDos(HashMap<?, ?> hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iRset = 0;
		String cCveExpediente = (String) hmFiltros.get("iCveExpediente");
		String cNumExamen = (String) hmFiltros.get("iNumExamen");
		String cServicio = (String) hmFiltros.get("iCveServicio");
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EXPResultadoDos" + " where iCveExpediente = ?"
					+ " and iNumExamen = ?" + " and iCveServicio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, cCveExpediente);
			pstmt.setString(2, cNumExamen);
			pstmt.setString(3, cServicio);
			iRset =  pstmt.executeUpdate();
			conn.commit();
			
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
