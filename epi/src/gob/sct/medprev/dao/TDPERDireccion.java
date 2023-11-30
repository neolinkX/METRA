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

/**
 * <p>
 * Title: Data Acces Object de PERDireccion DAO
 * </p>
 * <p>
 * Description: DAO Tabla PERDireccion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonzï¿½lez Paz
 * @Modified by Javier Mendoza
 * @version 1.0
 */

public class TDPERDireccion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERDireccion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDireccion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDireccion vPERDireccion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCvePersonal," + "iCveDireccion," + "cCalle,"
					+ "cNumExt," + "cNumInt," + "cColonia," + "iCP,"
					+ "cCiudad," + "iCvePais," + "iCveEstado,"
					+ "iCveMunicipio," + "cTel"
					+ " from PERDireccion order by iCvePersonal";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERDireccion = new TVPERDireccion();
				vPERDireccion.setICvePersonal(rset.getInt(1));
				vPERDireccion.setICveDireccion(rset.getInt(2));
				vPERDireccion.setCCalle(rset.getString(3));
				vPERDireccion.setCNumExt(rset.getString(4));
				vPERDireccion.setCNumInt(rset.getString(5));
				vPERDireccion.setCColonia(rset.getString(6));
				vPERDireccion.setICP(rset.getInt(7));
				vPERDireccion.setCCiudad(rset.getString(8));
				vPERDireccion.setICvePais(rset.getInt(9));
				vPERDireccion.setICveEstado(rset.getInt(10));
				vPERDireccion.setICveMunicipio(rset.getInt(11));
				vPERDireccion.setCTel(rset.getString(12));
				vcPERDireccion.addElement(vPERDireccion);
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
			return vcPERDireccion;
		}
	}
	
	/**
	 * Metodo Find By All
	 */
	public Boolean FLocalidad(String iCvePersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean regresa = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDireccion vPERDireccion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveLocalidad "
					+ " from PERDireccion " 
					+ " where iCvePersonal = "+iCvePersonal;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if(rset.getInt(1) >= 0){
					regresa = true;
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
			return regresa;
		}
	}	
	

	/**
	 * Metodo Find By All cWhere
	 * 
	 * @Author Javier Mendoza
	 */
	public Vector FindByAllcWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDireccion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDireccion vPERDireccion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select a.iCvePersonal,a.iCveDireccion,a.cCalle, "
					+ " a.cNumExt,a.cNumInt,a.cColonia,a.iCP, "
					+ " a.cCiudad,a.iCvePais,a.iCveEstado, "
					+ " a.iCveMunicipio,a.cTel, b.cNombre Pais, c.cDscEstado Estado "
					+ " From  PERDireccion a "
					+ " inner join GRLPais b on b.iCvePais = a.iCvePais "
					+ " inner join GRLEstado c on c.iCvePais = a.iCvePais "
					+ " and c.iCveEstado = a.iCvePais " + " " + cWhere + " "
					+ " order by a.iCvePersonal";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERDireccion = new TVPERDireccion();
				vPERDireccion.setICvePersonal(rset.getInt(1));
				vPERDireccion.setICveDireccion(rset.getInt(2));
				vPERDireccion.setCCalle(rset.getString(3));
				vPERDireccion.setCNumExt(rset.getString(4));
				vPERDireccion.setCNumInt(rset.getString(5));
				vPERDireccion.setCColonia(rset.getString(6));
				vPERDireccion.setICP(rset.getInt(7));
				vPERDireccion.setCCiudad(rset.getString(8));
				vPERDireccion.setICvePais(rset.getInt(9));
				vPERDireccion.setICveEstado(rset.getInt(10));
				vPERDireccion.setICveMunicipio(rset.getInt(11));
				vPERDireccion.setCTel(rset.getString(12));
				vPERDireccion.setCDscPais(rset.getString(13));
				vPERDireccion.setCDscEstado(rset.getString(14));
				vcPERDireccion.addElement(vPERDireccion);
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
			return vcPERDireccion;
		}
	}

	/**
	 * Metodo Find By Persona
	 */
	public Vector FindByPersona(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDireccion = new Vector();
		TDPERDatos dPERDatos = new TDPERDatos();
		int Existe = 0;
		int Contador = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDireccion vPERDireccion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			String Sentencia = "select Count(iCveDireccion) from PERDireccion where iCvePersonal ="
					+ cCvePersona;
			pstmt = conn.prepareStatement(Sentencia);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Existe = rset.getInt(1);
			}

			cSQL = "select "
					+ "iCvePersonal,"
					+ "iCveDireccion,"
					+ "cCalle,"
					+ "cNumExt,"
					+ "cNumInt,"
					+ "cColonia,"
					+ "iCP,"
					+ "cCiudad,"
					+ "PERDireccion.iCvePais,"
					+ "PERDireccion.iCveEstado,"
					+ "PERDireccion.iCveMunicipio,"
					+ "cTel, "
					+ "GRLPais.cNombre, "
					+ "GRLEntidadFed.cNombre, "
					+ "GRLMunicipio.cNombre, "
					+ "PERDireccion.iCveLocalidad"
					+ " from PERDireccion "
					+ " inner join GRLPais ON GRLPais.iCvePais = PERDireccion.iCvePais "
					+ " inner join GRLEntidadFed ON GRLEntidadFed.iCvePais = PERDireccion.iCvePais and "
					+ " GRLEntidadFed.iCveEntidadFed= PERDireccion.iCveEstado "
					+ " inner join GRLMunicipio ON GRLMunicipio.iCvePais = PERDireccion.iCvePais and "
					+ " GRLMunicipio.iCveEntidadFed= PERDireccion.iCveEstado and "
					+ " GRLMunicipio.iCveMunicipio= PERDireccion.iCveMunicipio "
					+ " where iCvePersonal = " + cCvePersona + " "
					+ " and iCveDireccion = (select max(iCveDireccion) "
					+ " from PERDireccion where iCvePersonal =" + cCvePersona
					+ ") " + " order by iCveDireccion";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Contador++;
				vPERDireccion = new TVPERDireccion();
				vPERDireccion.setICvePersonal(rset.getInt(1));
				vPERDireccion.setICveDireccion(rset.getInt(2));
				vPERDireccion.setCCalle(rset.getString(3));
				vPERDireccion.setCNumExt(rset.getString(4));
				vPERDireccion.setCNumInt(rset.getString(5));
				vPERDireccion.setCColonia(rset.getString(6));
				vPERDireccion.setICP(rset.getInt(7));
				vPERDireccion.setCCiudad(rset.getString(8));
				vPERDireccion.setICvePais(rset.getInt(9));
				vPERDireccion.setICveEstado(rset.getInt(10));
				vPERDireccion.setICveMunicipio(rset.getInt(11));
				vPERDireccion.setCTel(rset.getString(12));
				vPERDireccion.setCDscPais(rset.getString(13));
				vPERDireccion.setCDscEstado(rset.getString(14));
				vPERDireccion.setCDscMunicipio(rset.getString(15));
				vPERDireccion.setICveLocalidad(rset.getInt(16));
				vcPERDireccion.addElement(vPERDireccion);
			}

			// Validar si existio problema en los catalogos.
			if (Contador == 0 && Existe == 1) {
				dPERDatos
						.Sentencia("UPDATE PERDIRECCION SET ICvePais = 1, ICveEstado = 0, ICveMunicipio = 0 WHERE ICVEPERSONAL = "
								+ cCvePersona
								+ "  AND ICVEDIRECCION = (select max(iCveDireccion)  from PERDireccion where iCvePersonal ="
								+ cCvePersona + ")");
				// System.out.println("Existio un problema en el catalogo del expediente = "+
				// cCvePersona);

				// Recabando la Informacion Nuevamente
				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					vPERDireccion = new TVPERDireccion();
					vPERDireccion.setICvePersonal(rset.getInt(1));
					vPERDireccion.setICveDireccion(rset.getInt(2));
					vPERDireccion.setCCalle(rset.getString(3));
					vPERDireccion.setCNumExt(rset.getString(4));
					vPERDireccion.setCNumInt(rset.getString(5));
					vPERDireccion.setCColonia(rset.getString(6));
					vPERDireccion.setICP(rset.getInt(7));
					vPERDireccion.setCCiudad(rset.getString(8));
					vPERDireccion.setICvePais(rset.getInt(9));
					vPERDireccion.setICveEstado(rset.getInt(10));
					vPERDireccion.setICveMunicipio(rset.getInt(11));
					vPERDireccion.setCTel(rset.getString(12));
					vPERDireccion.setCDscPais(rset.getString(13));
					vPERDireccion.setCDscEstado(rset.getString(14));
					vPERDireccion.setCDscMunicipio(rset.getString(15));
					vPERDireccion.setICveLocalidad(rset.getInt(16));
					vcPERDireccion.addElement(vPERDireccion);
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
			return vcPERDireccion;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rset = null;
		String cClave = "";
		int iConsecutivo = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQLMax = "";
			TVPERDireccion vPERDireccion = (TVPERDireccion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLMax = "select max(iCveDireccion) from PERDireccion where iCvePersonal = ?";
			pstmtMax = conn.prepareStatement(cSQLMax);
			pstmtMax.setInt(1, vPERDireccion.getICvePersonal());
			rset = pstmtMax.executeQuery();
			while (rset.next()) {
				iConsecutivo = rset.getInt(1);
			}
			vPERDireccion.setICveDireccion(iConsecutivo + 1);

			cSQL = "insert into PERDireccion(" + "iCvePersonal,"
					+ "iCveDireccion," + "cCalle," + "cNumExt," + "cNumInt,"
					+ "cColonia," + "iCP," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cTel,"+ "iCveLocalidad"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vPERDireccion.getICvePersonal());
			pstmt.setInt(2, vPERDireccion.getICveDireccion());
			pstmt.setString(3, vPERDireccion.getCCalle());
			pstmt.setString(4, vPERDireccion.getCNumExt());
			pstmt.setString(5, vPERDireccion.getCNumInt());
			pstmt.setString(6, vPERDireccion.getCColonia());
			pstmt.setInt(7, vPERDireccion.getICP());
			pstmt.setString(8, vPERDireccion.getCCiudad());
			pstmt.setInt(9, vPERDireccion.getICvePais());
			pstmt.setInt(10, vPERDireccion.getICveEstado());
			pstmt.setInt(11, vPERDireccion.getICveMunicipio());
			pstmt.setString(12, vPERDireccion.getCTel());
			pstmt.setInt(13, vPERDireccion.getICveLocalidad());
			pstmt.executeUpdate();
			cClave = "" + vPERDireccion.getICveDireccion();

			TDEXPBITMOD bitacora = new TDEXPBITMOD();
			ExpBitMod registrobitacora = new ExpBitMod();
			registrobitacora.setiCveExpediente(vPERDireccion.getICvePersonal()
					+ "");
			registrobitacora.setiNumExamen("0");
			registrobitacora.setOperacion("18");
			registrobitacora
					.setDescripcion("Fue generado un nuevo domicilio para el expediente "
							+ vPERDireccion.getICvePersonal()
							+ ", con clave de domicilio "
							+ vPERDireccion.getICveDireccion());
			registrobitacora.setiCveUsuarioRealiza(""
					+ vPERDireccion.getICveUsuario());
			bitacora.insertaRegistroBitacoraBiometricos(registrobitacora);

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
				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
					dbConn.closeConnection();
				}

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
			String cWhereLoc = "";
			TVPERDireccion vPERDireccion = (TVPERDireccion) obj;

			// /Resgitrando en bitacora el actual domicilio
			Vector Direccion2 = new Vector();
			Direccion2 = this.FindByPersona(vPERDireccion.getICvePersonal()
					+ "");
			TVPERDireccion vPERDireccion2;
			TDEXPBITMOD bitacora = new TDEXPBITMOD();
			TDGRLLocalidad UbicaLocalidad =  new TDGRLLocalidad();
			TDEXPExamAplica ConAplica = new TDEXPExamAplica();
			ExpBitMod registrobitacora = new ExpBitMod();
			if (Direccion2.size() > 0) {
				for (int i = 0; i < Direccion2.size(); i++) {
					vPERDireccion2 = (TVPERDireccion) Direccion2.get(i);

					/*
					 * String Pais = ""+ConAplica.RegresaS(
					 * "Select cnombre from GRLPAIS where icvepais = "
					 * +vPERDireccion2.getICvePais()); String Estado =
					 * ""+ConAplica.RegresaS(
					 * "Select cnombre from GRLEntidadFed where icvepais = "
					 * +vPERDireccion2
					 * .getICvePais()+" and icveentidadfed ="+vPERDireccion2
					 * .getICveEstado()); String Municipio =
					 * ""+ConAplica.RegresaS
					 * ("Select cnombre from GRLMunicipio where icvepais = "
					 * +vPERDireccion2
					 * .getICvePais()+" and icveentidadfed ="+vPERDireccion2
					 * .getICveEstado()
					 * +" and icvemunicipio = "+vPERDireccion2.getICveMunicipio
					 * ());
					 */
					
					///Obtener Localidad///
					String cLocalidad = "";
					cWhereLoc = " icvePais = "+vPERDireccion2.getICvePais()+
								" and icveEntidadFed = "+vPERDireccion2.getICveEstado()+
								" and icveMunicipio = "+vPERDireccion2.getICveMunicipio()+
								" and icveLocalidad = "+vPERDireccion2.getICveLocalidad();
					cLocalidad = UbicaLocalidad.UbicaLocalidad(cWhereLoc);
					
					
					registrobitacora.setiCveExpediente(vPERDireccion2
							.getICvePersonal() + "");
					registrobitacora.setiNumExamen("0");
					registrobitacora.setOperacion("19");
					registrobitacora
							.setDescripcion("Los datos antes de la modificación eran los siguientes: Domicilio No.:"
									+ vPERDireccion2.getICveDireccion()
									+ " / Calle:"
									+ vPERDireccion2.getCCalle()
									+ " / Num. Ext. "
									+ vPERDireccion2.getCNumExt()
									+ " / Num. Int. "
									+ vPERDireccion2.getCNumInt()
									+ " / Colonia: "
									+ vPERDireccion2.getCColonia()
									+ " / CP: "
									+ vPERDireccion2.getICP()
									+ " / Ciudad: "
									+ vPERDireccion2.getCCiudad()
									+ " / Pais: "
									+ vPERDireccion2.getCDscPais()
									+ " / Estado: "
									+ vPERDireccion2.getCDscEstado()
									+ " / Municipio: "
									+ vPERDireccion2.getCDscMunicipio()
									+ " / Tel: " + vPERDireccion2.getCTel()
									+ " / Localidad: " + cLocalidad);
					registrobitacora.setiCveUsuarioRealiza(""
							+ vPERDireccion.getICveUsuario());
					bitacora.insertaRegistroBitacoraBiometricos(registrobitacora);
				}
			}

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERDireccion" + " set cCalle= ?, " + "cNumExt= ?, "
					+ "cNumInt= ?, " + "cColonia= ?, " + "iCP= ?, "
					+ "cCiudad= ?, " + "iCvePais= ?, " + "iCveEstado= ?, "
					+ "iCveMunicipio= ?, " + "cTel= ?, " + "iCveLocalidad= ? "
					+ "where iCvePersonal = ? " + " and iCveDireccion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vPERDireccion.getCCalle());
			pstmt.setString(2, vPERDireccion.getCNumExt());
			pstmt.setString(3, vPERDireccion.getCNumInt());
			pstmt.setString(4, vPERDireccion.getCColonia());
			pstmt.setInt(5, vPERDireccion.getICP());
			pstmt.setString(6, vPERDireccion.getCCiudad());
			pstmt.setInt(7, vPERDireccion.getICvePais());
			pstmt.setInt(8, vPERDireccion.getICveEstado());
			pstmt.setInt(9, vPERDireccion.getICveMunicipio());
			pstmt.setString(10, vPERDireccion.getCTel());
			pstmt.setInt(11, vPERDireccion.getICveLocalidad());
			pstmt.setInt(12, vPERDireccion.getICvePersonal());
			pstmt.setInt(13, vPERDireccion.getICveDireccion());
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
			TVPERDireccion vPERDireccion = (TVPERDireccion) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERDireccion" + " where iCvePersonal = ?"
					+ " and iCveDireccion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERDireccion.getICvePersonal());
			pstmt.setInt(2, vPERDireccion.getICveDireccion());
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
				warn("delete.closePERDireccion", ex2);
			}
			return cClave;
		}
	}
}
