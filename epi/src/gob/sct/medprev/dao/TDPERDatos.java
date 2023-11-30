package gob.sct.medprev.dao;
  
import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Data Acces Object de PERDatos DAO
 * </p>
 * <p>
 * Description: DAO de la entidad PERDatos (PERPersona)
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

/*
 * JESR: Solo estoy utilizando el Metodo findBySelector;
 */
 
public class TDPERDatos extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERDatos() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author: Marco A. Gonz�lez Paz
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
	public Vector FindByAll(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtDir = null;
		ResultSet rsetDir = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			int iMaxDir = 1;

			cSQLDir = "select max(iCveDireccion) from PERDireccion "
					+ "where iCvePersonal = " + cCvePersona;

			pstmtDir = conn.prepareStatement(cSQLDir);
			rsetDir = pstmtDir.executeQuery();
			while (rsetDir.next()) {
				iMaxDir = rsetDir.getInt(1);
			}

			cSQL = "select "
					+ "PERDatos.iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "PERDatos.cNombre,"
					+ "PERDatos.cApPaterno,"
					+ "PERDatos.cApMaterno,"
					+ "PERDatos.cRFC,"
					+ "PERDatos.cHomoclave,"
					+ "PERDatos.cCURP,"
					+ "dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+ "lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"PERDatos.iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "PERDatos.iCveNumEmpresa,"
					+

					"GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "GRLEmpresas.cDscEmpresa,"
					+

					"PERDireccion.cCalle,"
					+ "PERDireccion.cNumExt,"
					+ "PERDireccion.cNumInt,"
					+ "PERDireccion.cColonia,"
					+ "PERDireccion.iCP,"
					+ "PERDireccion.cCiudad,"
					+ "Pais.cNombre,"
					+ "Estado.cNombre,"
					+ "Municipio.cNombre,"
					+ "PERDireccion.cTel, "
					+

					"PERDireccion.iCvePais ,"
					+ "PERDireccion.iCveEstado ,"
					+ "PERDireccion.iCveMunicipio, "
					+

					"PERDatos.cGpoSang,"
					+ "PERDatos.lRH,"
					+ "PERDatos.lUsaLentes,"
					+ "PERDatos.lHipertension,"
					+ "PERDatos.lDiabetes,"
					+

					"PERDatos.lAereo,"
					+ "PERDatos.lContacto, "
					+ "PERDatos.cLicencia, "
					+ "PERDatos.cLicenciaInt, "
					+ "PEREmpresa.iCveEmpresa, "
					
					
					+ "PERDatos.cSexo_DGIS, "
					+ "PERDireccion.iCveLocalidad "
					+

					" from PERDatos "
					+ "inner join GRLPais ON GRLPais.iCvePais = PERDatos.iCvePaisNac "
					+ "inner join GRLEntidadFed ON GRLEntidadFed.iCvePais = PERDatos.iCvePaisNac and GRLEntidadFed.iCveEntidadFed = PERDatos.iCveEstadoNac "
					+ " left join PEREmpresa ON  PEREmpresa.iCvePersonal = PERDatos.iCvePersonal "
					+ "   and PEREmpresa.iCvenumEmpresa = PERDatos.iCveNumEmpresa "
					+ "left join GRLEmpresas ON GRLEmpresas.iCveEmpresa = PEREmpresa.iCveEmpresa "
					+ "inner join PERDireccion ON PERDireccion.iCvePersonal = "
					+ cCvePersona
					+ " and PERDireccion.iCveDireccion =  "
					+ iMaxDir
					+ " "
					+ "inner join GRLPais Pais ON Pais.iCvePais = PerDireccion.iCvePais "
					+ "inner join GRLEntidadFed Estado ON Estado.iCvePais = PerDireccion.iCvePais and Estado.iCveEntidadFed = PerDireccion.iCveEstado "
					+ "inner join GRLMunicipio Municipio ON Municipio.iCvePais = PerDireccion.iCvePais and Municipio.iCveEntidadFed = PerDireccion.iCveEstado and Municipio.iCveMunicipio = PerDireccion.iCveMunicipio "
					+ "where PERDatos.iCvePersonal = " + cCvePersona + " "
					+ "order by PERDatos.iCvePersonal";

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				vPERDatos = new TVPERDatos();

				vPERDatos.setICvePersonal(rset.getInt(1));
				vPERDatos.setICveExpediente(rset.getInt(2));
				vPERDatos.setCSexo(rset.getString(3));
				vPERDatos.setCNombre(rset.getString(4));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(7));
				vPERDatos.setCHomoclave(rset.getString(8));
				vPERDatos.setCCURP(rset.getString(9));
				vPERDatos.setDtNacimiento(rset.getDate(10));

				vPERDatos.setICvePaisNac(rset.getInt(11));
				vPERDatos.setICveEstadoNac(rset.getInt(12));
				vPERDatos.setCExpediente(rset.getString(13));
				vPERDatos.setCSenasPersonal(rset.getString(14));
				vPERDatos.setCCorreoElec(rset.getString(15));
				vPERDatos.setLDonadorOrg(rset.getInt(16));
				vPERDatos.setCPersonaAviso(rset.getString(17));
				vPERDatos.setCDirecAviso(rset.getString(18));
				vPERDatos.setCTelAviso(rset.getString(19));
				vPERDatos.setCCorreoAviso(rset.getString(20));

				vPERDatos.setICveDireccion(rset.getInt(21));
				vPERDatos.setICveFoto(rset.getInt(22));
				vPERDatos.setLNoHuellas(rset.getInt(23));
				vPERDatos.setICveNumEmpresa(rset.getInt(24));

				vPERDatos.setCDscPaisNac(rset.getString(25));
				vPERDatos.setCDscEstadoNac(rset.getString(26));
				vPERDatos.setCDscEmpresa(rset.getString(27));

				vPERDatos.setCCalle(rset.getString(28));
				vPERDatos.setCNumExt(rset.getString(29));
				vPERDatos.setCNumInt(rset.getString(30));
				vPERDatos.setCColonia(rset.getString(31));
				vPERDatos.setICP(rset.getInt(32));
				vPERDatos.setCCiudad(rset.getString(33));
				vPERDatos.setCDscPais(rset.getString(34));
				vPERDatos.setCDscEstado(rset.getString(35));
				vPERDatos.setCDscMunicipio(rset.getString(36));
				vPERDatos.setCTelefono(rset.getString(37));

				vPERDatos.setICvePais(rset.getInt(38));
				vPERDatos.setICveEstado(rset.getInt(39));
				vPERDatos.setICveMunicipio(rset.getInt(40));

				vPERDatos.setCGpoSang(rset.getString(41));
				vPERDatos.setLRH(rset.getInt(42));
				vPERDatos.setLUsaLentes(rset.getInt(43));
				vPERDatos.setLHipertension(rset.getInt(44));
				vPERDatos.setLDiabetes(rset.getInt(45));

				vPERDatos.setLAereo(rset.getInt(46));
				vPERDatos.setLContacto(rset.getInt(47));
				// Licencias
				vPERDatos.setCLicencia(rset.getString(48));
				vPERDatos.setCLicenciaInt(rset.getString(49));

				vPERDatos.setICveEmpresa(rset.getInt(50));
				
				vPERDatos.setCSexo_DGIS(rset.getString(51));
				vPERDatos.setICveLocalidad(rset.getInt(52));	

				if (vPERDatos.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPERDatos.getDtNacimiento(), "/");
					vPERDatos.setCDscFecNacimiento(cFecha);
					cFecha = dtFecha.getFechaYYYYMMMDDNom024(
							vPERDatos.getDtNacimiento());
					vPERDatos.setCDscFecNacimientoNom024(cFecha);
				} else {
					vPERDatos.setCDscFecNacimiento("");
					vPERDatos.setCDscFecNacimientoNom024("");
				}
				vcPERDatos.addElement(vPERDatos);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {

				if (pstmtDir != null) {
					pstmtDir.close();
				}
				if (rsetDir != null) {
					rsetDir.close();
				}

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
			return vcPERDatos;
		}
	}

	/**
	 * Metodo Find By Persona
	 * 
	 * @param: cCvePersona - Clave del Personal en Caracter.
	 * @author: Marco A. Gonz�lez Paz
	 */
	public Vector FindByPersona(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int Contador = 0;
			int Existe = 0;
			int count;
			int iMaxDir = 1;
			// Validando que los datos de los catalogos son correctos
			Vector valida = new Vector();
			valida = this.FindByValida2(cCvePersona);
			
			//System.out.println("FindByPersona cCvePersona = "+cCvePersona);

			if (valida.elementAt(0).toString().compareTo("0") == 0) {
				this.Sentencia("UPDATE PERDATOS SET ICVEPAISNAC = 0 WHERE ICVEEXPEDIENTE = "
						+ cCvePersona + "");
				this.Sentencia("UPDATE PERDATOS SET ICVEESTADONAC = 0 WHERE ICVEEXPEDIENTE = "
						+ cCvePersona + "");
			}
			if (valida.elementAt(3).toString().compareTo("0") == 0) {
				this.Sentencia("UPDATE PERDIRECCION SET ICVEESTADO = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona + "");
				this.Sentencia("UPDATE PERDIRECCION SET ICVEMUNICIPIO = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona + "");
			}
			if (valida.elementAt(4).toString().compareTo("0") == 0) {
				this.Sentencia("UPDATE PERDIRECCION SET ICVEMUNICIPIO = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona + "");
			}

			// validando si el expediente en realidad existe
			String Sentencia = "select Count(iCvePersonal) from PERDatos where iCvePersonal ="
					+ cCvePersona;
			pstmt = conn.prepareStatement(Sentencia);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Existe = rset.getInt(1);
			}

			cSQL = "select "
					+ "PERDatos.iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "PERDatos.cNombre,"
					+ "PERDatos.cApPaterno,"
					+ "PERDatos.cApMaterno,"
					+ "PERDatos.cRFC,"
					+ "PERDatos.cHomoclave,"
					+ "PERDatos.cCURP,"
					+ "PERDatos.dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+ "lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"PERDatos.iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa,"
					+

					"GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "GRLEmpresas.cDscEmpresa, "
					+

					"PERDatos.cGpoSang,"
					+ "PERDatos.lRH,"
					+ "PERDatos.lUsaLentes,"
					+ "PERDatos.lHipertension,"
					+ "PERDatos.lDiabetes, "
					+ "PERDatos.cExpediente, "
					+ "PERDatos.lAereo,"
					+ "PERDatos.lContacto, "
					+ "PERDatos.cLicencia, "
					+ "PERDatos.cLicenciaInt, "
					
					+ "PERDatos.cSexo_DGIS "
					+

					"from PERDatos "
					+ "inner join GRLPais ON GRLPais.iCvePais = PERDatos.iCvePaisNac "
					+ "inner join GRLEntidadFed ON GRLEntidadFed.iCvePais = PERDatos.iCvePaisNac and GRLEntidadFed.iCveEntidadFed = PERDatos.iCveEstadoNac "
					+ "left join GRLEmpresas ON GRLEmpresas.iCveEmpresa = PERDatos.iCveNumEmpresa "
					+ "where PERDatos.iCvePersonal = " + cCvePersona + " "
					+ "order by PERDatos.iCvePersonal";
			
			//System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				Contador++;
				vPERDatos = new TVPERDatos();

				vPERDatos.setICvePersonal(rset.getInt(1));
				vPERDatos.setICveExpediente(rset.getInt(2));
				vPERDatos.setCSexo(rset.getString(3));
				vPERDatos.setCNombre(rset.getString(4));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(7));
				vPERDatos.setCHomoclave(rset.getString(8));
				vPERDatos.setCCURP(rset.getString(9));
				vPERDatos.setDtNacimiento(rset.getDate(10));

				vPERDatos.setICvePaisNac(rset.getInt(11));
				vPERDatos.setICveEstadoNac(rset.getInt(12));
				vPERDatos.setCExpediente(rset.getString(13));
				vPERDatos.setCSenasPersonal(rset.getString(14));
				vPERDatos.setCCorreoElec(rset.getString(15));
				vPERDatos.setLDonadorOrg(rset.getInt(16));
				vPERDatos.setCPersonaAviso(rset.getString(17));
				vPERDatos.setCDirecAviso(rset.getString(18));
				vPERDatos.setCTelAviso(rset.getString(19));
				vPERDatos.setCCorreoAviso(rset.getString(20));

				vPERDatos.setICveDireccion(rset.getInt(21));
				vPERDatos.setICveFoto(rset.getInt(22));
				vPERDatos.setLNoHuellas(rset.getInt(23));
				vPERDatos.setICveNumEmpresa(rset.getInt(24));

				vPERDatos.setCDscPaisNac(rset.getString(25));
				vPERDatos.setCDscEstadoNac(rset.getString(26));
				vPERDatos.setCDscEmpresa(rset.getString(27));

				vPERDatos.setCGpoSang(rset.getString(28));
				vPERDatos.setLRH(rset.getInt(29));
				vPERDatos.setLUsaLentes(rset.getInt(30));
				vPERDatos.setLHipertension(rset.getInt(31));
				vPERDatos.setLDiabetes(rset.getInt(32));

				vPERDatos.setCExpediente(rset.getString(33));

				vPERDatos.setLAereo(rset.getInt(34));
				vPERDatos.setLContacto(rset.getInt(35));

				// Licencias
				vPERDatos.setCLicencia(rset.getString(36));
				vPERDatos.setCLicenciaInt(rset.getString(37));
				vPERDatos.setCSexo_DGIS(rset.getString(38));

				if (vPERDatos.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPERDatos.getDtNacimiento(), "/");
					vPERDatos.setCDscFecNacimiento(cFecha);
				} else {
					vPERDatos.setCDscFecNacimiento("");
				}
				vcPERDatos.addElement(vPERDatos);
			}

			// Validar si existio problema en los catalogos.
			if (Contador == 0 && Existe == 1) {
				this.Sentencia("UPDATE PERDatos SET ICvePaisNac = 1, ICveEstadoNac = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona);
				// System.out.println("Existio un problema en el catalogo del expediente = "+
				// cCvePersona);
				// Recabando la Informacion Nuevamente
				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					vPERDatos = new TVPERDatos();

					vPERDatos.setICvePersonal(rset.getInt(1));
					vPERDatos.setICveExpediente(rset.getInt(2));
					vPERDatos.setCSexo(rset.getString(3));
					vPERDatos.setCNombre(rset.getString(4));
					vPERDatos.setCApPaterno(rset.getString(5));
					vPERDatos.setCApMaterno(rset.getString(6));
					vPERDatos.setCRFC(rset.getString(7));
					vPERDatos.setCHomoclave(rset.getString(8));
					vPERDatos.setCCURP(rset.getString(9));
					vPERDatos.setDtNacimiento(rset.getDate(10));

					vPERDatos.setICvePaisNac(rset.getInt(11));
					vPERDatos.setICveEstadoNac(rset.getInt(12));
					vPERDatos.setCExpediente(rset.getString(13));
					vPERDatos.setCSenasPersonal(rset.getString(14));
					vPERDatos.setCCorreoElec(rset.getString(15));
					vPERDatos.setLDonadorOrg(rset.getInt(16));
					vPERDatos.setCPersonaAviso(rset.getString(17));
					vPERDatos.setCDirecAviso(rset.getString(18));
					vPERDatos.setCTelAviso(rset.getString(19));
					vPERDatos.setCCorreoAviso(rset.getString(20));

					vPERDatos.setICveDireccion(rset.getInt(21));
					vPERDatos.setICveFoto(rset.getInt(22));
					vPERDatos.setLNoHuellas(rset.getInt(23));
					vPERDatos.setICveNumEmpresa(rset.getInt(24));

					vPERDatos.setCDscPaisNac(rset.getString(25));
					vPERDatos.setCDscEstadoNac(rset.getString(26));
					vPERDatos.setCDscEmpresa(rset.getString(27));

					vPERDatos.setCGpoSang(rset.getString(28));
					vPERDatos.setLRH(rset.getInt(29));
					vPERDatos.setLUsaLentes(rset.getInt(30));
					vPERDatos.setLHipertension(rset.getInt(31));
					vPERDatos.setLDiabetes(rset.getInt(32));

					vPERDatos.setCExpediente(rset.getString(33));

					vPERDatos.setLAereo(rset.getInt(34));
					vPERDatos.setLContacto(rset.getInt(35));

					if (vPERDatos.getDtNacimiento() != null) {
						cFecha = "";
						cFecha = dtFecha.getFechaDDMMYYYY(
								vPERDatos.getDtNacimiento(), "/");
						vPERDatos.setCDscFecNacimiento(cFecha);
					} else {
						vPERDatos.setCDscFecNacimiento("");
					}
					vcPERDatos.addElement(vPERDatos);
				}
			}

		} catch (Exception ex) {
			warn("FindByPersona", ex);
			throw new DAOException("FindByPersona");
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
				warn("FindByPersona.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo Find By Persona
	 * 
	 * @param: cCvePersona - Clave del Personal en Caracter.
	 * @author: AG SA.
	 */
	public Vector FindByPersona2(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtAd = null;
		ResultSet rsetAd = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLAd = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int Contador = 0;
			int Existe = 0;
			int count;
			int iMaxDir = 1;
			// Validando que los datos de los catalogos son correctos
			Vector valida = new Vector();
			valida = this.FindByValida2(cCvePersona);

			if (valida.elementAt(0).toString().compareTo("0") == 0) {
				this.Sentencia("UPDATE PERDATOS SET ICVEPAISNAC = 0 WHERE ICVEEXPEDIENTE = "
						+ cCvePersona + "");
				this.Sentencia("UPDATE PERDATOS SET ICVEESTADONAC = 0 WHERE ICVEEXPEDIENTE = "
						+ cCvePersona + "");
			}
			if (valida.elementAt(3).toString().compareTo("0") == 0) {
				this.Sentencia("UPDATE PERDIRECCION SET ICVEESTADO = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona + "");
				this.Sentencia("UPDATE PERDIRECCION SET ICVEMUNICIPIO = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona + "");
			}
			if (valida.elementAt(4).toString().compareTo("0") == 0) {
				this.Sentencia("UPDATE PERDIRECCION SET ICVEMUNICIPIO = 0 WHERE ICVEPERSONAL = "
						+ cCvePersona + "");
			}

			// validando si el expediente en realidad existe
			String Sentencia = "select Count(iCvePersonal) from PERDatos where iCvePersonal ="
					+ cCvePersona;
			pstmt = conn.prepareStatement(Sentencia);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Existe = rset.getInt(1);
			}

			cSQL = "select "
					+ "PERDatos.iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "PERDatos.cNombre,"
					+ "PERDatos.cApPaterno,"
					+ "PERDatos.cApMaterno,"
					+ "PERDatos.cRFC,"
					+ "PERDatos.cHomoclave,"
					+ "PERDatos.cCURP,"
					+ "PERDatos.dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+ "lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"PERDatos.iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa,"
					+

					"GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "GRLEmpresas.cDscEmpresa, "
					+

					"PERDatos.cGpoSang,"
					+ "PERDatos.lRH,"
					+ "PERDatos.lUsaLentes,"
					+ "PERDatos.lHipertension,"
					+ "PERDatos.lDiabetes, "
					+ "PERDatos.cExpediente, "
					+ "PERDatos.lAereo,"
					+ "PERDatos.lContacto, "
					+ "PERDatos.cLicencia, "
					+ "PERDatos.cLicenciaInt, "

					+ "PERDatos.cSexo_DGIS "
					+
					"from PERDatos "
					+ "inner join GRLPais ON GRLPais.iCvePais = PERDatos.iCvePaisNac "
					+ "inner join GRLEntidadFed ON GRLEntidadFed.iCvePais = PERDatos.iCvePaisNac and GRLEntidadFed.iCveEntidadFed = PERDatos.iCveEstadoNac "
					+ "left join GRLEmpresas ON GRLEmpresas.iCveEmpresa = PERDatos.iCveNumEmpresa "
					+ "where PERDatos.iCvePersonal = " + cCvePersona + " "
					+ "order by PERDatos.iCvePersonal";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			vPERDatos = new TVPERDatos();

			while (rset.next()) {
				Contador++;

				vPERDatos.setICvePersonal(rset.getInt(1));
				vPERDatos.setICveExpediente(rset.getInt(2));
				vPERDatos.setCSexo(rset.getString(3));
				vPERDatos.setCNombre(rset.getString(4));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(7));
				vPERDatos.setCHomoclave(rset.getString(8));
				vPERDatos.setCCURP(rset.getString(9));
				vPERDatos.setDtNacimiento(rset.getDate(10));

				vPERDatos.setICvePaisNac(rset.getInt(11));
				vPERDatos.setICveEstadoNac(rset.getInt(12));
				vPERDatos.setCExpediente(rset.getString(13));
				vPERDatos.setCSenasPersonal(rset.getString(14));
				vPERDatos.setCCorreoElec(rset.getString(15));
				vPERDatos.setLDonadorOrg(rset.getInt(16));
				vPERDatos.setCPersonaAviso(rset.getString(17));
				vPERDatos.setCDirecAviso(rset.getString(18));
				vPERDatos.setCTelAviso(rset.getString(19));
				vPERDatos.setCCorreoAviso(rset.getString(20));

				vPERDatos.setICveDireccion(rset.getInt(21));
				vPERDatos.setICveFoto(rset.getInt(22));
				vPERDatos.setLNoHuellas(rset.getInt(23));
				vPERDatos.setICveNumEmpresa(rset.getInt(24));

				vPERDatos.setCDscPaisNac(rset.getString(25));
				vPERDatos.setCDscEstadoNac(rset.getString(26));
				vPERDatos.setCDscEmpresa(rset.getString(27));

				vPERDatos.setCGpoSang(rset.getString(28));
				vPERDatos.setLRH(rset.getInt(29));
				vPERDatos.setLUsaLentes(rset.getInt(30));
				vPERDatos.setLHipertension(rset.getInt(31));
				vPERDatos.setLDiabetes(rset.getInt(32));

				vPERDatos.setCExpediente(rset.getString(33));

				vPERDatos.setLAereo(rset.getInt(34));
				vPERDatos.setLContacto(rset.getInt(35));

				// Licencias
				vPERDatos.setCLicencia(rset.getString(36));
				vPERDatos.setCLicenciaInt(rset.getString(37));
				
				vPERDatos.setCSexo_DGIS(rset.getString(38));

				if (vPERDatos.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPERDatos.getDtNacimiento(), "/");
					vPERDatos.setCDscFecNacimiento(cFecha);
				} else {
					vPERDatos.setCDscFecNacimiento("");
				}
			}

			cSQLAd = "SELECT P.cTel2, P.iCveVivienda, V.cConcepto, P.iCveDiscapacidad,  D.cDcsDiscapacidad, P.iCveGpoEtnico, "
					+ " T.cGpoEtnico,P.iCveReligion,R.cCodDsc,P.iCveNivelSec,N.cNivelSec, P.iCveParPol,PL.cDscParPol,P.iCveTpoSangre, "
					+ " S.cTpoSangre,P.iCveECivil,C.cECivil,D.ICVEGRUPO,D.ICVESUBGRUPO,R.ICVEGRUPO,R.ICVESUBGRUPO FROM PERADICIONAL AS P,GRLVIVIENDA AS V, GRLDISCAPACIDAD AS D, "
					+ " GRLGPOETNICO AS T,GRLRELIGION AS R,GRLNIVELSEC AS N,GRLPARPOL AS PL,GRLTPOSANGRE AS S, GRLECIVIL AS C "
					+ " WHERE P.ICVEVIVIENDA = V.ICVEVIVIENDA AND P.ICVEDISCAPACIDAD = D.ICVEDISCAPACIDAD AND "
					+ " P.ICVEGPOETNICO = T.ICVEGPOETNICO AND P.ICVERELIGION = R.ICVERELIGION AND P.ICVENIVELSEC = N.ICVENIVELSEC AND "
					+ " P.ICVEPARPOL = PL.ICVEPARPOL  AND  P.ICVETPOSANGRE = S.ICVETPOSANGRE AND P.ICVEECIVIL = C.ICVEECIVIL "
					+ " AND P.ICVEPERSONAL = " + cCvePersona;

			// System.out.println("Probando query: "+cSQLAd);

			pstmtAd = conn.prepareStatement(cSQLAd);
			rsetAd = pstmtAd.executeQuery();

			while (rsetAd.next()) {
				vPERDatos.setCTelefono2(rsetAd.getString(1));
				vPERDatos.setICveVivienda(rsetAd.getInt(2));
				vPERDatos.setCConcepto(rsetAd.getString(3));
				vPERDatos.setICveDiscapacidad(rsetAd.getInt(4));
				vPERDatos.setCDcsDiscapacidad(rsetAd.getString(5));
				vPERDatos.setICveGpoEtnico(rsetAd.getInt(6));
				vPERDatos.setCGpoEtnico(rsetAd.getString(7));
				vPERDatos.setICveReligion(rsetAd.getInt(8));
				vPERDatos.setCCodDsc(rsetAd.getString(9));
				vPERDatos.setICveNivelSEC(rsetAd.getInt(10));
				vPERDatos.setCNivelSec(rsetAd.getString(11));
				vPERDatos.setICveParPOL(rsetAd.getInt(12));
				vPERDatos.setCDscParPol(rsetAd.getString(13));
				vPERDatos.setICveTpoSangre(rsetAd.getInt(14));
				vPERDatos.setCTpoSangre(rsetAd.getString(15));
				vPERDatos.setICveECivil(rsetAd.getInt(16));
				vPERDatos.setCECivil(rsetAd.getString(17));
				vPERDatos.setICveGrupoD(rsetAd.getInt(18));
				vPERDatos.setICveSubGrupoD(rsetAd.getString(19));
				vPERDatos.setICveGrupoR(rsetAd.getInt(20));
				vPERDatos.setICveSubGrupoR(rsetAd.getInt(21));
			}

			vcPERDatos.addElement(vPERDatos);

			/*
			 * 
			 * //Validar si existio problema en los catalogos. if(Contador == 0
			 * && Existe == 1){ this.Sentencia(
			 * "UPDATE PERDatos SET ICvePaisNac = 1, ICveEstadoNac = 0 WHERE ICVEPERSONAL = "
			 * +cCvePersona); //System.out.println(
			 * "Existio un problema en el catalogo del expediente = "+
			 * cCvePersona); //Recabando la Informacion Nuevamente pstmt =
			 * conn.prepareStatement(cSQL); rset = pstmt.executeQuery();
			 * 
			 * while (rset.next()) { vPERDatos = new TVPERDatos();
			 * 
			 * vPERDatos.setICvePersonal(rset.getInt(1));
			 * vPERDatos.setICveExpediente(rset.getInt(2));
			 * vPERDatos.setCSexo(rset.getString(3));
			 * vPERDatos.setCNombre(rset.getString(4));
			 * vPERDatos.setCApPaterno(rset.getString(5));
			 * vPERDatos.setCApMaterno(rset.getString(6));
			 * vPERDatos.setCRFC(rset.getString(7));
			 * vPERDatos.setCHomoclave(rset.getString(8));
			 * vPERDatos.setCCURP(rset.getString(9));
			 * vPERDatos.setDtNacimiento(rset.getDate(10));
			 * 
			 * vPERDatos.setICvePaisNac(rset.getInt(11));
			 * vPERDatos.setICveEstadoNac(rset.getInt(12));
			 * vPERDatos.setCExpediente(rset.getString(13));
			 * vPERDatos.setCSenasPersonal(rset.getString(14));
			 * vPERDatos.setCCorreoElec(rset.getString(15));
			 * vPERDatos.setLDonadorOrg(rset.getInt(16));
			 * vPERDatos.setCPersonaAviso(rset.getString(17));
			 * vPERDatos.setCDirecAviso(rset.getString(18));
			 * vPERDatos.setCTelAviso(rset.getString(19));
			 * vPERDatos.setCCorreoAviso(rset.getString(20));
			 * 
			 * vPERDatos.setICveDireccion(rset.getInt(21));
			 * vPERDatos.setICveFoto(rset.getInt(22));
			 * vPERDatos.setLNoHuellas(rset.getInt(23));
			 * vPERDatos.setICveNumEmpresa(rset.getInt(24));
			 * 
			 * vPERDatos.setCDscPaisNac(rset.getString(25));
			 * vPERDatos.setCDscEstadoNac(rset.getString(26));
			 * vPERDatos.setCDscEmpresa(rset.getString(27));
			 * 
			 * vPERDatos.setCGpoSang(rset.getString(28));
			 * vPERDatos.setLRH(rset.getInt(29));
			 * vPERDatos.setLUsaLentes(rset.getInt(30));
			 * vPERDatos.setLHipertension(rset.getInt(31));
			 * vPERDatos.setLDiabetes(rset.getInt(32));
			 * 
			 * vPERDatos.setCExpediente(rset.getString(33));
			 * 
			 * vPERDatos.setLAereo(rset.getInt(34));
			 * vPERDatos.setLContacto(rset.getInt(35));
			 * 
			 * if (vPERDatos.getDtNacimiento() != null) { cFecha = ""; cFecha =
			 * dtFecha.getFechaDDMMYYYY(vPERDatos.getDtNacimiento(), "/");
			 * vPERDatos.setCDscFecNacimiento(cFecha); } else {
			 * vPERDatos.setCDscFecNacimiento(""); }
			 * vcPERDatos.addElement(vPERDatos); } }
			 */

		} catch (Exception ex) {
			warn("FindByPersona", ex);
			throw new DAOException("FindByPersona");
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
				warn("FindByPersona.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		int iPersonal = 0;
		int iExpediente = 0;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			vPERDatos.setICvePersonal(iPersonal + 1);

			cSQLCveExpediente = "select max(iCveExpediente) from PERDatos ";
			pstmtCveExpediente = conn.prepareStatement(cSQLCveExpediente);
			rsetCveExpediente = pstmtCveExpediente.executeQuery();
			while (rsetCveExpediente.next()) {
				iExpediente = rsetCveExpediente.getInt(1);
			}
			vPERDatos.setICveExpediente(iExpediente + 1);

			cSQL = "insert into PERDatos("
					+ "iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "cNombre,"
					+ "cApPaterno,"
					+

					"cApMaterno,"
					+ "cRFC,"
					+ "cHomoclave,"
					+ "cCURP,"
					+ "dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+

					"lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa, "
					+ "iCveUsuRegistra, "
					+ "tsgenerado "
					+

					")values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,(SELECT current timestamp FROM sysibm.sysdummy1))";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vPERDatos.getICvePersonal());
			pstmt.setInt(2, vPERDatos.getICveExpediente());
			pstmt.setString(3, vPERDatos.getCSexo());
			pstmt.setString(4, vPERDatos.getCNombre());
			pstmt.setString(5, vPERDatos.getCApPaterno());

			pstmt.setString(6, vPERDatos.getCApMaterno());
			pstmt.setString(7, vPERDatos.getCRFC());
			pstmt.setString(8, vPERDatos.getCHomoclave());
			pstmt.setString(9, vPERDatos.getCCURP());
			pstmt.setDate(10, vPERDatos.getDtNacimiento());

			pstmt.setInt(11, vPERDatos.getICvePaisNac());
			pstmt.setInt(12, vPERDatos.getICveEstadoNac());
			pstmt.setString(13, vPERDatos.getCExpediente());
			pstmt.setString(14, vPERDatos.getCSenasPersonal());
			pstmt.setString(15, vPERDatos.getCCorreoElec());

			pstmt.setInt(16, vPERDatos.getLDonadorOrg());
			pstmt.setString(17, vPERDatos.getCPersonaAviso());
			pstmt.setString(18, vPERDatos.getCDirecAviso());
			pstmt.setString(19, vPERDatos.getCTelAviso());
			pstmt.setString(20, vPERDatos.getCCorreoAviso());

			pstmt.setInt(21, vPERDatos.getICveDireccion());
			pstmt.setInt(22, vPERDatos.getICveFoto());
			pstmt.setInt(23, vPERDatos.getLNoHuellas());
			pstmt.setInt(24, vPERDatos.getICveNumEmpresa());
			pstmt.setInt(25, vPERDatos.getICveUsuRegistra());
			pstmt.executeUpdate();
			cClave = "" + vPERDatos.getICvePersonal();
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

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
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
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERDatos " + "set " +

			"cSexo= ?, " + "cNombre= ?, " + "cApPaterno= ?, "
					+ "cApMaterno= ?, " + "cRFC= ?, " +

					"cHomoclave= ?, " + "cCURP= ?, " + "dtNacimiento= ?, "
					+ "iCvePaisNac= ?, " + "iCveEstadoNac= ?, " +

					"cExpediente= ?, " + "cSenasPersonal= ?, "
					+ "cCorreoElec= ?, " + "lDonadorOrg= ?, "
					+ "cPersonaAviso = ?, " +

					"cDirecAviso= ?, " + "cTelAviso= ?, " + "cCorreoAviso= ?, "
					+ "iCveNumEmpresa = ?, cSexo_DGIS= ? " + "where iCvePersonal = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vPERDatos.getCSexo());
			pstmt.setString(2, vPERDatos.getCNombre());
			pstmt.setString(3, vPERDatos.getCApPaterno());
			pstmt.setString(4, vPERDatos.getCApMaterno());
			pstmt.setString(5, vPERDatos.getCRFC());

			pstmt.setString(6, vPERDatos.getCHomoclave());
			pstmt.setString(7, vPERDatos.getCCURP());
			pstmt.setDate(8, vPERDatos.getDtNacimiento());
			pstmt.setInt(9, vPERDatos.getICvePaisNac());
			pstmt.setInt(10, vPERDatos.getICveEstadoNac());

			pstmt.setString(11, vPERDatos.getCExpediente());
			pstmt.setString(12, vPERDatos.getCSenasPersonal());
			pstmt.setString(13, vPERDatos.getCCorreoElec());
			pstmt.setInt(14, vPERDatos.getLDonadorOrg());
			pstmt.setString(15, vPERDatos.getCPersonaAviso());

			pstmt.setString(16, vPERDatos.getCDirecAviso());
			pstmt.setString(17, vPERDatos.getCTelAviso());
			pstmt.setString(18, vPERDatos.getCCorreoAviso());
			pstmt.setInt(19, vPERDatos.getICveNumEmpresa());
			pstmt.setString(20, vPERDatos.getCSexo_DGIS());
			pstmt.setInt(21, vPERDatos.getICvePersonal());
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

	// *********************************************************************************************************

	/**
	 * Metodo update
	 */
	public Object update2(Connection conGral, Object obj) throws DAOException {
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
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERDatos " + "set " +

			"cSexo= ?, " + "cNombre= ?, " + "cApPaterno= ?, "
					+ "cApMaterno= ?, " + "cRFC= ?, " +

					"cHomoclave= ?, " + "cCURP= ?, " + "dtNacimiento= ?, "
					+ "iCvePaisNac= ?, " + "iCveEstadoNac= ?, " +

					"cExpediente= ?, " + "cSenasPersonal= ?, "
					+ "cCorreoElec= ?, " + "lDonadorOrg= ?, "
					+ "cPersonaAviso = ?, " +

					"cDirecAviso= ?, " + "cTelAviso= ?, " + "cCorreoAviso= ?, "
					+ "iCveNumEmpresa = ?, " + "cLicencia = ? ,"
					+ "cLicenciaInt = ?, cSexo_DGIS = ? " + "where iCvePersonal = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vPERDatos.getCSexo());
			pstmt.setString(2, vPERDatos.getCNombre());
			pstmt.setString(3, vPERDatos.getCApPaterno());
			pstmt.setString(4, vPERDatos.getCApMaterno());
			pstmt.setString(5, vPERDatos.getCRFC());

			pstmt.setString(6, vPERDatos.getCHomoclave());
			pstmt.setString(7, vPERDatos.getCCURP());
			pstmt.setDate(8, vPERDatos.getDtNacimiento());
			pstmt.setInt(9, vPERDatos.getICvePaisNac());
			pstmt.setInt(10, vPERDatos.getICveEstadoNac());

			pstmt.setString(11, vPERDatos.getCExpediente());
			pstmt.setString(12, vPERDatos.getCSenasPersonal());
			pstmt.setString(13, vPERDatos.getCCorreoElec());
			pstmt.setInt(14, vPERDatos.getLDonadorOrg());
			pstmt.setString(15, vPERDatos.getCPersonaAviso());

			pstmt.setString(16, vPERDatos.getCDirecAviso());
			pstmt.setString(17, vPERDatos.getCTelAviso());
			pstmt.setString(18, vPERDatos.getCCorreoAviso());
			pstmt.setInt(19, vPERDatos.getICveNumEmpresa());
			pstmt.setString(20, vPERDatos.getCLicencia());
			pstmt.setString(21, vPERDatos.getCLicenciaInt());
			pstmt.setString(22, vPERDatos.getCSexo_DGIS());
			pstmt.setInt(23, vPERDatos.getICvePersonal());
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

	// *********************************************************************************************************

	/**
	 * Metodo update AG SA
	 */
	public String Sentencia(String cSQL) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// TVPERDatos vPERDatos = (TVPERDatos) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);

			pstmt.executeUpdate();
			cClave = "correcto";
			if (conn == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conn == null) {
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
				if (conn == null) {
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
	 * Metodo update
	 */
	public String SInsert(String insertsql, String insertsql2,
			String insertsql3, String insertsql4) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtdir = null;
		ResultSet rsetCvePersona = null;
		String cSQL = "";
		String cSQL2 = "";
		int iPersonal = 0;
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			iPersonal++;
			String cper = String.valueOf(iPersonal);

			cSQL = insertsql + cper + ", " + cper + ", " + insertsql2;
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.executeUpdate();

			cSQL2 = insertsql3 + cper + insertsql4;
			// System.out.println(cSQL2);
			pstmtdir = conn.prepareStatement(cSQL2);
			pstmtdir.executeUpdate();

			cClave = cper;
			if (conn == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conn == null) {
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
				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}
				if (pstmtdir != null) {
					pstmtdir.close();
				}
				if (conn == null) {
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
	 * MÃ©todo Find By All
	 */
	public String SenFindBy(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEXPDictamenServ = new Vector();
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEXPDictamenServ vEXPDictamenServ;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
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
			return Regresa;
		}
	}

	// *********************************************************************************************************

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
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from PERDatos" + " where iCvePersonal = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERDatos.getICvePersonal());
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
				warn("delete.closePERDatos", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By Selector (Utilizado para la Seleccion de personal) AG
	 * SA
	 */
	public Vector FindBySelector(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select " + "iCvePersonal," + "iCveExpediente," + "cNombre,"
					+ "cApPaterno," + "cApMaterno," + "cRFC," + "cHomoclave,"
					+ "cCURP," +  "dtNacimiento, cSexo" + " from PERDatos " + cWhere;

			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset.getInt(1));
				vPERDatos.setICveExpediente(rset.getInt(2));
				vPERDatos.setCNombre(rset.getString(3));
				vPERDatos.setCApPaterno(rset.getString(4));
				vPERDatos.setCApMaterno(rset.getString(5));
				vPERDatos.setCRFC(rset.getString(6));
				vPERDatos.setCHomoclave(rset.getString(7));
				vPERDatos.setCCURP(rset.getString(8));
				vPERDatos.setDtNacimiento(rset.getDate(9));
				vPERDatos.setCSexo(rset.getString(10));
				vcPERDatos.addElement(vPERDatos);
			}
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
			return vcPERDatos;
		}
	}

	
	
	/**
	 * Metodo Find By Sel Examen (Utilizado para la Seleccion de personal con
	 * Numero de Examen)
	 */
	public Vector FindBySelExamen(String cWhere, int iCveUniMed,
			int iCveModulo, int iTipo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null, pstmt2 = null;
		ResultSet rset1 = null, rset2 = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL1 = "", cSQL2 = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL1 = "select "
					+ "PERDatos.iCvePersonal,"
					+ "PERDatos.iCveExpediente,"
					+ "PERDatos.cNombre,"
					+ "PERDatos.cApPaterno,"
					+ "PERDatos.cApMaterno,"
					+ "PERDatos.cRFC,"
					+ "PERDatos.cHomoclave,"
					+ "PERDatos.cCURP"
					+ " from PERDatos "
					+ " join EPICISCita on PERDatos.iCvePersonal = EPICISCita.iCvePersonal "
					+ " and EPICISCita.iCveUniMed = " + iCveUniMed
					+ " and EPICISCita.iCveModulo = " + iCveModulo
					+ " and EPICISCita.dtFecha = ? " + cWhere;

			cSQL2 = "select max(iNumExamen) " + "from EXPExamAplica "
					+ "where iCveExpediente = ? ";

			if (iTipo == 1) {
				cSQL2 = cSQL2 + " and EXPExamAplica.lIniciado = 0";
			}
			if (iTipo == 2) {
				cSQL2 = cSQL2 + " and EXPExamAplica.lIniciado = 1";
			}
			if (iTipo == 3) {
				cSQL2 = cSQL2
						+ " and EXPExamAplica.lIniciado = 1 and EXPExamAplica.lConcluido = 0 ";
			}

			pstmt1 = conn.prepareStatement(cSQL1);
			pstmt1.setDate(1, new java.sql.Date(new java.util.Date().getTime()));

			pstmt2 = conn.prepareStatement(cSQL2);

			// System.out.println(cSQL1);
			// System.out.println(cSQL2);

			rset1 = pstmt1.executeQuery();
			while (rset1.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset1.getInt(1));
				vPERDatos.setICveExpediente(rset1.getInt(2));
				vPERDatos.setCNombre(rset1.getString(3));
				vPERDatos.setCApPaterno(rset1.getString(4));
				vPERDatos.setCApMaterno(rset1.getString(5));
				vPERDatos.setCRFC(rset1.getString(6));
				vPERDatos.setCHomoclave(rset1.getString(7));
				vPERDatos.setCCURP(rset1.getString(8));

				pstmt2.setInt(1, vPERDatos.getICveExpediente());
				rset2 = pstmt2.executeQuery();

				while (rset2.next()) {
					vPERDatos.setINumExamen(rset2.getInt(1));
				}

				if (vPERDatos.getINumExamen() != 0) {
					vcPERDatos.addElement(vPERDatos);
				}
			}
		} catch (Exception ex) {
			warn("FindBySelExamen", ex);
			throw new DAOException("FindBySelExamen");
		} finally {
			try {
				if (rset1 != null) {
					rset1.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindBySelExamen.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo findUser (Utilizado para la Selecci�n de personal)
	 */
	public TVPERDatos findUser(int iCvePersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCvePersonal,iCveExpediente,cNombre,cApPaterno,cApMaterno,cSexo,"
					+ "dtNacimiento,iCveNumEmpresa,cRFC,cSexo_DGIS " + "from PerDatos "
					+ "where iCvePersonal=?";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, iCvePersonal);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
				vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
				vPERDatos.setCNombre(rset.getString("cNombre"));
				vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
				vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
				vPERDatos.setCSexo(rset.getString("cSexo"));
				vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
				vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
				vPERDatos.setCRFC(rset.getString("cRFC"));
				
				vPERDatos.setCSexo_DGIS(rset.getString("cSexo_DGIS"));
			}
		} catch (Exception ex) {
			warn("findUser", ex);
			throw new DAOException("findUser");
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
				warn("findUser.close", ex2);
			}
			return vPERDatos;
		}
	}

	/**
	 * Metodo findUserbyExp (Utilizado para la Selecci�n de personal por
	 * Expediente)
	 */
	public TVPERDatos findUserbyExp(int iCveExpediente) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVPERDatos vPERDatos = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iCvePersonal,iCveExpediente,cNombre,cApPaterno,cApMaterno,cSexo,"
					+ "dtNacimiento,iCveNumEmpresa,cRFC " + "from PerDatos "
					+ "where iCveExpediente=?";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset.getInt("iCvePersonal"));
				vPERDatos.setICveExpediente(rset.getInt("iCveExpediente"));
				vPERDatos.setCNombre(rset.getString("cNombre"));
				vPERDatos.setCApPaterno(rset.getString("cApPaterno"));
				vPERDatos.setCApMaterno(rset.getString("cApMaterno"));
				vPERDatos.setCSexo(rset.getString("cSexo"));
				vPERDatos.setDtNacimiento(rset.getDate("dtNacimiento"));
				vPERDatos.setICveNumEmpresa(rset.getInt("iCveNumEmpresa"));
				vPERDatos.setCRFC(rset.getString("cRFC"));
			}
		} catch (Exception ex) {
			warn("findUserbyExp", ex);
			throw new DAOException("findUserbyExp");
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
				warn("findUserbyExp.close", ex2);
			}
			return vPERDatos;
		}
	}

	/**
	 * Metodo Find By Sel Examen (Utilizado para la Selecci�n de personal con
	 * N�mero de Examen)
	 */
	public Vector FindBySelExamGenera(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL1 = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL1 = "select PERDatos.iCvePersonal,PERDatos.iCveExpediente,PERDatos.cNombre,PERDatos.cApPaterno, "
					+ "PERDatos.cApMaterno,PERDatos.cRFC,PERDatos.cHomoclave,PERDatos.cCURP, EXPExamGenera.iNumExamen, "
					+ "PERDatos.ICvePaisNac  "
					+ "from EXPExamGenera "
					+ "join PERDatos on EXPExamGenera.iCveExpediente = PERDatos.iCveExpediente "
					+ cWhere + " and EXPExamGenera.lAplicado = 0 ";

			System.out.println(cSQL1);
			pstmt1 = conn.prepareStatement(cSQL1);
			rset1 = pstmt1.executeQuery();

			while (rset1.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset1.getInt(1));
				vPERDatos.setICveExpediente(rset1.getInt(2));
				vPERDatos.setCNombre(rset1.getString(3));
				vPERDatos.setCApPaterno(rset1.getString(4));
				vPERDatos.setCApMaterno(rset1.getString(5));
				vPERDatos.setCRFC(rset1.getString(6));
				vPERDatos.setCHomoclave(rset1.getString(7));
				vPERDatos.setCCURP(rset1.getString(8));
				vPERDatos.setINumExamen(rset1.getInt(9));
				vPERDatos.setICvePaisNac(rset1.getInt(10));

				vcPERDatos.addElement(vPERDatos);
			}
		} catch (Exception ex) {
			warn("FindBySelExamGenera", ex);
			throw new DAOException("FindBySelExamGenera");
		} finally {
			try {
				if (rset1 != null) {
					rset1.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindBySelExamGenera.close", ex2);
			}
			return vcPERDatos;
		}
	}

	public Vector validarAlta(Object vDatos) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt1 = null, pstmt2 = null;
		ResultSet rset1 = null, rset2 = null;
		Vector vcPERDatos = new Vector(), vResultado = new Vector();
		TFechas tFecha = new TFechas();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			String cWhereRFC = "", cWhereNom = "";
			TVPERDatos vPERDatos;
			TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			// Preparar la sentencia
			cSQL.append(" SELECT D.iCvePersonal, D.iCveExpediente, ")
					// 1, 2
					.append("        D.cNombre, D.cApPaterno,  D.cApMaterno, ")
					// 3, 4, 5
					.append("        D.cRFC, D.cHomoclave, D.cCURP, ")
					// 6, 7, 8
					.append("        D.dtNacimiento, D.cSexo,")
					// 9, 10
					.append("        D.iCvePaisNac, D.iCveEstadoNac, ")
					// 11, 12
					.append("        EN.cNombre || ', ' || PN.cNombre as cLugNac,")
					// 13
					.append("        D.iCveDireccion, ")
					// 14
					.append("        DI.cCalle || ' ' || DI.cNumExt || ' ' || DI.cNumInt || ' ' || ")
					.append("        DI.cColonia || ',<br>' || MD.cNombre || ',<br>' || ED.cNombre || ',<br>' || ")
					.append("        PD.cNombre as cDomicilio ")
					// 15
					.append(" from PERDatos D ")
					.append("  inner join  GRLPais PN on PN.iCvePais = D.iCvePaisNac ")
					.append("  inner join  GRLEntidadFed EN on EN.iCvePais       = D.iCvePaisNac ")
					.append("                              and EN.iCveEntidadFed = D.iCveEstadoNac ")
					.append("  inner join  PERDireccion DI on DI.iCvePersonal  = D.iCvePersonal ")
					// .append("                             and DI.iCveDireccion = D.iCveDireccion  ")
					.append("                             and DI.iCveDireccion = (Select max (pn.icvedireccion) from perdireccion as pn where pn.icvepersonal = D.iCvePersonal)  ")
					.append("  inner join  GRLPais PD on PD.iCvePais = DI.iCvePais ")
					.append("  inner join  GRLEntidadFed ED on ED.iCvePais       = DI.iCvePais ")
					.append("                              and ED.iCveEntidadFed = DI.iCveEstado ")
					.append("  inner join  GRLMunicipio MD on MD.iCvePais       = DI.iCvePais ")
					.append("                             and MD.iCveEntidadFed = DI.iCveEstado ")
					.append("                             and MD.iCveMunicipio  = DI.iCveMunicipio ");
			// Condici�n para buscar por RFC
			vEPICisCita = (TVEPICisCita) vDatos;
			cWhereRFC = " where RTRIM(LTRIM(D.cRFC)) = '"
					+ vEPICisCita.getCRFC() + "'";
			 //System.out.println("*** Primer B�squeda \n " + cSQL.toString()
			 //+ cWhereRFC);
			cWhereNom = " where RTRIM(LTRIM(D.cNombre))  = '"
					+ vEPICisCita.getCNombre() + "'"
					+ "   and RTRIM(LTRIM(D.cApPaterno)) = '"
					+ vEPICisCita.getCApPaterno() + "'"
					+ "   and RTRIM(LTRIM(D.cApMaterno)) = '"
					+ vEPICisCita.getCApMaterno() + "'"
					+ "   and D.dtNacimiento  = '"
					+ vEPICisCita.getDtNacimiento() + "'";
			/*
			 * CARLOS RAMOS <
			 */
			String cWhereApPat = " where RTRIM(LTRIM(D.cApPaterno)) = '"
					+ vEPICisCita.getCApPaterno() + "'"
					+ " and D.dtNacimiento  = '"
					+ vEPICisCita.getDtNacimiento() + "'";
			// AG SA
			// Se corrigio la validacion de apellido materno, ya que buscaba con
			// el parametro de apellido paterno
			String cWhereApMat = " where RTRIM(LTRIM(D.cApMaterno)) = '"
					+ vEPICisCita.getCApMaterno() + "'"
					+ " and D.dtNacimiento  = '"
					+ vEPICisCita.getDtNacimiento() + "'";

			String cWhereCURP = " where RTRIM(LTRIM(D.cCURP)) = '"
					+ vEPICisCita.getCCURP() + "'";

			/*
			 * > CARLOS RAMOS
			 */
			vcPERDatos = new Vector();
			if(vEPICisCita.getICvePaisNac() == 1){///Buscar Con CURP Solo si es Mexicano / AG SA 30 de Sep 2014
					//System.out.println("SQL = "+cSQL.toString() + cWhereCURP);
					pstmt1 = conn.prepareStatement(cSQL.toString() + cWhereCURP);
					// B�squeda por Nombre completo y Fecha de Nacimiento
					rset1 = pstmt1.executeQuery();
					while (rset1.next()) {
						vPERDatos = new TVPERDatos();
						vPERDatos.setICvePersonal(rset1.getInt(1));
						vPERDatos.setICveExpediente(rset1.getInt(2));
						vPERDatos.setCNombre(rset1.getString(3));
						vPERDatos.setCApPaterno(rset1.getString(4));
						vPERDatos.setCApMaterno(rset1.getString(5));
						vPERDatos.setCRFC(rset1.getString(6));
						vPERDatos.setCHomoclave(rset1.getString(7));
						vPERDatos.setCCURP(rset1.getString(8));
						vPERDatos.setDtNacimiento(rset1.getDate(9));
						vPERDatos.setCDscFecNacimiento(tFecha.getFechaDDMMYYYY(
								vPERDatos.getDtNacimiento(), "/"));
						vPERDatos.setCSexo(rset1.getString(10));
						vPERDatos.setICvePaisNac(rset1.getInt(11));
						vPERDatos.setICveEstadoNac(rset1.getInt(12));
						vPERDatos.setCDscEstadoNac(rset1.getString(13));
						vPERDatos.setICveDireccion(rset1.getInt(14));
						vPERDatos.setCCalle(rset1.getString(15));
						vcPERDatos.addElement(vPERDatos);
					}
			}//Fin Buscar con CURP solo si es Mexicano
			// Agregar el resultado de CURP a la busqueda
			vResultado.add(vcPERDatos);
			
			/*
			 * > CARLOS RAMOS
			 */
			// B�squeda por Nombre completo y Fecha de Nacimiento
			vcPERDatos = new Vector();
			pstmt1 = conn.prepareStatement(cSQL.toString() + cWhereRFC);
			rset1 = pstmt1.executeQuery();
			while (rset1.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset1.getInt(1));
				vPERDatos.setICveExpediente(rset1.getInt(2));
				vPERDatos.setCNombre(rset1.getString(3));
				vPERDatos.setCApPaterno(rset1.getString(4));
				vPERDatos.setCApMaterno(rset1.getString(5));
				vPERDatos.setCRFC(rset1.getString(6));
				vPERDatos.setCHomoclave(rset1.getString(7));
				vPERDatos.setCCURP(rset1.getString(8));
				vPERDatos.setDtNacimiento(rset1.getDate(9));
				vPERDatos.setCDscFecNacimiento(tFecha.getFechaDDMMYYYY(
						vPERDatos.getDtNacimiento(), "/"));
				vPERDatos.setCSexo(rset1.getString(10));
				vPERDatos.setICvePaisNac(rset1.getInt(11));
				vPERDatos.setICveEstadoNac(rset1.getInt(12));
				vPERDatos.setCDscEstadoNac(rset1.getString(13));
				vPERDatos.setICveDireccion(rset1.getInt(14));
				vPERDatos.setCCalle(rset1.getString(15));
				vcPERDatos.addElement(vPERDatos);
			}
			// Agregar el resultado de la b�squeda
			vResultado.add(vcPERDatos);

			// B�squeda por Nombre completo y Fecha de Nacimiento
			vcPERDatos = new Vector();
			pstmt2 = conn.prepareStatement(cSQL.toString() + cWhereNom);
			rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset2.getInt(1));
				vPERDatos.setICveExpediente(rset2.getInt(2));
				vPERDatos.setCNombre(rset2.getString(3));
				vPERDatos.setCApPaterno(rset2.getString(4));
				vPERDatos.setCApMaterno(rset2.getString(5));
				vPERDatos.setCRFC(rset2.getString(6));
				vPERDatos.setCHomoclave(rset2.getString(7));
				vPERDatos.setCCURP(rset2.getString(8));
				vPERDatos.setDtNacimiento(rset2.getDate(9));
				vPERDatos.setCDscFecNacimiento(tFecha.getFechaDDMMYYYY(
						vPERDatos.getDtNacimiento(), "/"));
				vPERDatos.setCSexo(rset2.getString(10));
				vPERDatos.setICvePaisNac(rset2.getInt(11));
				vPERDatos.setICveEstadoNac(rset2.getInt(12));
				vPERDatos.setCDscEstadoNac(rset2.getString(13));
				vPERDatos.setICveDireccion(rset2.getInt(14));
				vPERDatos.setCCalle(rset2.getString(15));
				vcPERDatos.addElement(vPERDatos);
			}
			// Agregar el resultado de la b�squeda
			vResultado.add(vcPERDatos);

			/*
			 * CARLOS RAMOS <
			 */
			// B�squeda por apellido paterno y Fecha de Nacimiento
			vcPERDatos = new Vector();
			pstmt1.close();
			pstmt1 = conn.prepareStatement(cSQL.toString() + cWhereApPat);
			rset1 = pstmt1.executeQuery();
			while (rset1.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset1.getInt(1));
				vPERDatos.setICveExpediente(rset1.getInt(2));
				vPERDatos.setCNombre(rset1.getString(3));
				vPERDatos.setCApPaterno(rset1.getString(4));
				vPERDatos.setCApMaterno(rset1.getString(5));
				vPERDatos.setCRFC(rset1.getString(6));
				vPERDatos.setCHomoclave(rset1.getString(7));
				vPERDatos.setCCURP(rset1.getString(8));
				vPERDatos.setDtNacimiento(rset1.getDate(9));
				vPERDatos.setCDscFecNacimiento(tFecha.getFechaDDMMYYYY(
						vPERDatos.getDtNacimiento(), "/"));
				vPERDatos.setCSexo(rset1.getString(10));
				vPERDatos.setICvePaisNac(rset1.getInt(11));
				vPERDatos.setICveEstadoNac(rset1.getInt(12));
				vPERDatos.setCDscEstadoNac(rset1.getString(13));
				vPERDatos.setICveDireccion(rset1.getInt(14));
				vPERDatos.setCCalle(rset1.getString(15));
				vcPERDatos.addElement(vPERDatos);
			}
			// Agregar el resultado de la b�squeda
			vResultado.add(vcPERDatos);

			// B�squeda por apellido materno y Fecha de Nacimiento
			vcPERDatos = new Vector();
			pstmt1.close();
			pstmt1 = conn.prepareStatement(cSQL.toString() + cWhereApPat);
			rset1 = pstmt1.executeQuery();
			while (rset1.next()) {
				vPERDatos = new TVPERDatos();
				vPERDatos.setICvePersonal(rset1.getInt(1));
				vPERDatos.setICveExpediente(rset1.getInt(2));
				vPERDatos.setCNombre(rset1.getString(3));
				vPERDatos.setCApPaterno(rset1.getString(4));
				vPERDatos.setCApMaterno(rset1.getString(5));
				vPERDatos.setCRFC(rset1.getString(6));
				vPERDatos.setCHomoclave(rset1.getString(7));
				vPERDatos.setCCURP(rset1.getString(8));
				vPERDatos.setDtNacimiento(rset1.getDate(9));
				vPERDatos.setCDscFecNacimiento(tFecha.getFechaDDMMYYYY(
						vPERDatos.getDtNacimiento(), "/"));
				vPERDatos.setCSexo(rset1.getString(10));
				vPERDatos.setICvePaisNac(rset1.getInt(11));
				vPERDatos.setICveEstadoNac(rset1.getInt(12));
				vPERDatos.setCDscEstadoNac(rset1.getString(13));
				vPERDatos.setICveDireccion(rset1.getInt(14));
				vPERDatos.setCCalle(rset1.getString(15));
				vcPERDatos.addElement(vPERDatos);
			}
			// Agregar el resultado de la b�squeda
			vResultado.add(vcPERDatos);

		}// termina try
		catch (Exception ex) {
			ex.printStackTrace();
			warn("TDPerDatos.validarAlta", ex);
			throw new DAOException("TDPerDatos.validarAlta");
		} finally {
			try {
				if (rset1 != null) {
					rset2.close();
					rset1.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				ex2.printStackTrace();
				warn("TDPerDatos.validarAlta.close", ex2);
			}
			return vResultado;
		}

	}

	/**
	 * Metodo Find By Persona
	 * 
	 * @author: AG SA
	 */
	public Vector Migraciones(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			int iMaxDir = 1;

			cSQL = "SELECT"
					+ " 	P.CNOMBRE,"
					+ "	P.CAPPATERNO,"
					+ "	P.CAPMATERNO,"
					+ "	P.CRFC,"
					+ "	P.DTNACIMIENTO,"
					+ "	GP.CNOMBRE,"
					+ "	GE.CNOMBRE,"
					+ "      	P.ICVEPAISNAC,"
					+ "	P.ICVEESTADONAC,"
					+ "	P.ICVEEXPEDIENTE,"
					+ "       (SELECT MAX(INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
					+ cCvePersona + ") AS INUMEXAMEN" + " FROM "
					+ "	PERDATOS AS P," + "	GRLPAIS AS GP,"
					+ "       GRLENTIDADFED AS GE" + " WHERE "
					+ "	P.ICVEPAISNAC = GP.ICVEPAIS AND"
					+ "	P.ICVEPAISNAC = GE.ICVEPAIS AND"
					+ "	P.ICVEESTADONAC = GE.ICVEENTIDADFED AND"
					+ "	ICVEEXPEDIENTE = " + cCvePersona + "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				vPERDatos = new TVPERDatos();

				vPERDatos.setCNombre(rset.getString(1));
				vPERDatos.setCApPaterno(rset.getString(2));
				vPERDatos.setCApMaterno(rset.getString(3));
				vPERDatos.setCRFC(rset.getString(4));
				vPERDatos.setDtNacimiento(rset.getDate(5));
				vPERDatos.setCDscPaisNac(rset.getString(6));
				vPERDatos.setCDscEstadoNac(rset.getString(7));
				vPERDatos.setICvePaisNac(rset.getInt(8));
				vPERDatos.setICveEstadoNac(rset.getInt(9));
				vPERDatos.setICveExpediente(rset.getInt(10));
				vPERDatos.setINumExamen(rset.getInt(11));

				if (vPERDatos.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPERDatos.getDtNacimiento(), "/");
					vPERDatos.setCDscFecNacimiento(cFecha);
				} else {
					vPERDatos.setCDscFecNacimiento("");
				}
				vcPERDatos.addElement(vPERDatos);
			}
		} catch (Exception ex) {
			warn("FindByPersona", ex);
			throw new DAOException("FindByPersona");
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
				warn("FindByPersona.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author: AG SA L
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
	public int FindByValida(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtDir = null;
		ResultSet rsetDir = null;
		Vector vcPERDatos = new Vector();
		int Suma = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			int iMaxDir = 0;

			cSQLDir = "select max(iCveDireccion) from PERDireccion "
					+ "where iCvePersonal = " + cCvePersona;

			pstmtDir = conn.prepareStatement(cSQLDir);
			rsetDir = pstmtDir.executeQuery();
			while (rsetDir.next()) {
				iMaxDir = rsetDir.getInt(1);
			}

			if (iMaxDir >= 1) {
				cSQL = "SELECT P.ICVEPAISNAC, P.ICVEESTADONAC, P.ICVEDIRECCION, D.ICVEPAIS, D.ICVEESTADO, D.ICVEMUNICIPIO, D.ICVEDIRECCION "
						+ "FROM	PERDATOS AS P, PERDIRECCION AS D WHERE 	P.ICVEPERSONAL = D.ICVEPERSONAL AND P.ICVEPERSONAL = "
						+ cCvePersona + " AND D.ICVEDIRECCION = " + iMaxDir;
			} else {
				cSQL = "SELECT P.ICVEPAISNAC, P.ICVEESTADONAC, P.ICVEDIRECCION, D.ICVEPAIS, D.ICVEESTADO, D.ICVEMUNICIPIO, D.ICVEDIRECCION "
						+ "FROM	PERDATOS AS P, PERDIRECCION AS D WHERE 	P.ICVEPERSONAL = D.ICVEPERSONAL AND P.ICVEPERSONAL = "
						+ cCvePersona;
			}

			// System.out.println("Expediente = " +cCvePersona);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				vPERDatos = new TVPERDatos();
				if (rset.getInt(1) < 1) {
					Suma++;
				}
				if (rset.getInt(2) < 1) {
					Suma++;
				}
				if (rset.getInt(3) < 1) {
					Suma++;
				}
				if (rset.getInt(4) < 1) {
					Suma++;
				}
				if (rset.getInt(5) < 1) {
					Suma++;
				}
				if (rset.getInt(6) < 1) {
					Suma++;
				}
				if (rset.getInt(7) < 1) {
					Suma++;
				}

			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {

				if (pstmtDir != null) {
					pstmtDir.close();
				}
				if (rsetDir != null) {
					rsetDir.close();
				}

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
			return Suma;
		}
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author: AG SA L
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
	public Vector FindByValida2(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtDir = null;
		ResultSet rsetDir = null;
		Vector vcPERDatos = new Vector();
		int Suma = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			int iMaxDir = 0;

			cSQLDir = "select max(iCveDireccion) from PERDireccion "
					+ "where iCvePersonal = " + cCvePersona;

			pstmtDir = conn.prepareStatement(cSQLDir);
			rsetDir = pstmtDir.executeQuery();
			while (rsetDir.next()) {
				iMaxDir = rsetDir.getInt(1);
			}

			if (iMaxDir >= 1) {
				cSQL = "SELECT P.ICVEPAISNAC, P.ICVEESTADONAC, P.ICVEDIRECCION, D.ICVEPAIS, D.ICVEESTADO, D.ICVEMUNICIPIO, D.ICVEDIRECCION "
						+ "FROM	PERDATOS AS P, PERDIRECCION AS D WHERE 	P.ICVEPERSONAL = D.ICVEPERSONAL AND P.ICVEPERSONAL = "
						+ cCvePersona + " AND D.ICVEDIRECCION = " + iMaxDir;
			} else {
				cSQL = "SELECT P.ICVEPAISNAC, P.ICVEESTADONAC, P.ICVEDIRECCION, D.ICVEPAIS, D.ICVEESTADO, D.ICVEMUNICIPIO, D.ICVEDIRECCION "
						+ "FROM	PERDATOS AS P, PERDIRECCION AS D WHERE 	P.ICVEPERSONAL = D.ICVEPERSONAL AND P.ICVEPERSONAL = "
						+ cCvePersona;
			}

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				vPERDatos = new TVPERDatos();
				vcPERDatos.addElement(rset.getInt(1));
				vcPERDatos.addElement(rset.getInt(2));
				vcPERDatos.addElement(rset.getInt(3));
				vcPERDatos.addElement(rset.getInt(4));
				vcPERDatos.addElement(rset.getInt(5));
				vcPERDatos.addElement(rset.getInt(6));
				vcPERDatos.addElement(rset.getInt(7));
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {

				if (pstmtDir != null) {
					pstmtDir.close();
				}
				if (rsetDir != null) {

					rsetDir.close();
				}

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
			return vcPERDatos;
		}
	}

	// FCSReq7
	/**
	 * Metodo DetalleEmpresa
	 * 
	 * @param: iCveEmpresa - Clave de la Empresa.
	 * @author: FCS
	 */
	public Vector DetalleEmpresa(int iCveEmpresa) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPEREmpresa vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select ge.cDscEmpresa , "
					+ // 1
					"       ge.iCveGrupoEmp, "
					+ "       ge.cIdDgpmpt, "
					+ "       ge.cRFC, "
					+ // 4
					"       gum.cDscUniMed, "
					+ // 5
					"       gmt.cDscMdoTrans, "
					+ // 6
					"       ge.cTpoPersona, "
					+ "       ge.DTRegistro, "
					+ // 8
					"       ge.cDenominacion " + "from   GrlEmpresas ge, "
					+ "       GrlUniMed  gum, " + "       GrlMdoTrans gmt "
					+ "where  ge.iCveEmpresa = " + iCveEmpresa + " "
					+ "and    gum.iCveUniMed = ge.iCveUniMed "
					+ "and    gmt.iCveMdoTrans =  ge.iCveMdoTrans ";

			// System.out.println("\n********** TDPerDatos - DetalleEmpresa (cSQL): \n "
			// +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				vPERDatos = new TVPEREmpresa();

				vPERDatos.setSDscEmpresa(rset.getString(1));
				vPERDatos.setICveGrupoEmp(rset.getInt(2));
				vPERDatos.setSIdDgpmpt(rset.getString(3));
				vPERDatos.setSRFC(rset.getString(4));
				vPERDatos.setSDscUniMed(rset.getString(5));
				vPERDatos.setSDscMdoTrans(rset.getString(6));
				vPERDatos.setSTpoPersona(rset.getString(7));
				vPERDatos.setDtRegistro(rset.getDate(8));
				vPERDatos.setSRegistro(rset.getString(8));
				vPERDatos.setSDenominacion(rset.getString(9));

				vcPERDatos.addElement(vPERDatos);

			}
		} catch (Exception ex) {
			warn("DetalleEmpresa: ", ex);
			throw new DAOException("DetalleEmpresa");
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
				warn("DetalleEmpresa.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo UpdEmpresa
	 * 
	 * @author: FCS
	 */
	public int UpdEmpresa(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int TotReg = -1;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			TVPEREmpresa tvPerEmpresa = (TVPEREmpresa) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " update GrlEmpresas " + " set " +
			// " cDscEmpresa = '" +tvPerEmpresa.getSDscEmpresa()+ "', " +
					" iCveGrupoEmp = " + tvPerEmpresa.getICveGrupoEmp() + ", "
					+ " cIdDgpmpt = '" + tvPerEmpresa.getSIdDgpmpt() + "', "
					+ " cRFC = '" + tvPerEmpresa.getSRFC() + "', "
					+ " iCveUniMed = " + tvPerEmpresa.getICveUniMed() + ", "
					+ " iCveMdoTrans = " + tvPerEmpresa.getICveMdoTrans()
					+ ", " + " cTpoPersona = '" + tvPerEmpresa.getSTpoPersona()
					+ "', " + " cDenominacion = '"
					+ tvPerEmpresa.getSDenominacion() + "', "
					+ " dtRegistro = '" + tvPerEmpresa.getSRegistro() + "' "
					+ " where  iCveEmpresa = " + tvPerEmpresa.getICveEmpresa()
					+ " ";

			// System.out.println("\n********** UpdEmpresa - cSQL: \n" +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			TotReg = pstmt.executeUpdate();

			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("UpdEmpresa: ", ex1);
			}
			warn("UpdEmpresa: ", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				conn.close();

				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("UpdEmpresa.close", ex2);
			}

			return TotReg;
		}
	}

	// FCSReq7

	/**
	 * Metodo DatosUnidMed
	 * 
	 * @author: FCS
	 */
	public Vector DatosUnidMed() throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPEREmpresa vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select iCveUniMed, cDscUniMed " + "from   GrlUniMed "
					+ "where lVigente = 1 " + "order by iCveUniMed asc ";

			// System.out.println("\n********** TDPerDatos - DatosUnidMed (cSQL): \n "
			// +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vPERDatos = new TVPEREmpresa();

				vPERDatos.setICveUniMedCbo(rset.getInt(1));
				vPERDatos.setSDscUniMedCbo(rset.getString(2));

				vcPERDatos.addElement(vPERDatos);

			}
		} catch (Exception ex) {
			warn("DatosUnidMed: ", ex);
			throw new DAOException("DatosUnidMed");
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
				warn("DatosUnidMed.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo DatosModTra
	 * 
	 * @author: FCS
	 */
	public Vector DatosMdoTrans() throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPEREmpresa vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select   iCveMdoTrans, cDscMdoTrans "
					+ "from     GrlMdoTrans " + "where    lActivo = 1 "
					+ "order by iCveMdoTrans asc ";

			// System.out.println("\n********** TDPerDatos - DatosMdoTrans (cSQL): \n "
			// +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vPERDatos = new TVPEREmpresa();

				vPERDatos.setICveMdoTransCbo(rset.getInt(1));
				vPERDatos.setSDscMdoTransCbo(rset.getString(2));

				vcPERDatos.addElement(vPERDatos);

			}
		} catch (Exception ex) {
			warn("DatosMdoTrans: ", ex);
			throw new DAOException("DatosMdoTrans");
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
				warn("DatosMdoTrans.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo CompruebaCURP
	 * 
	 * @author: AG SA
	 */
	public int ValidaCURP(String icvepersonal) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();
		int regresa = 0;
		String CCURP = "";
		String PAIS = "";

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPEREmpresa vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT CCURP,ICVEPAISNAC FROM PERDATOS WHERE ICVEEXPEDIENTE = "
					+ icvepersonal;

			// System.out.println("\n********** TDPerDatos - DatosMdoTrans (cSQL): \n "
			// +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				CCURP = rset.getString(1);
				PAIS = rset.getString(2);
				// System.out.println(CCURP.length());
				if (PAIS.equals("1")) {
					if (CCURP.length() == 18) {
						if (CCURP.charAt(0) >= 'A' && CCURP.charAt(0) <= 'Z')
							regresa++;
						if (CCURP.charAt(1) >= 'A' && CCURP.charAt(1) <= 'Z')
							regresa++;
						if (CCURP.charAt(2) >= 'A' && CCURP.charAt(2) <= 'Z')
							regresa++;
						if (CCURP.charAt(3) >= 'A' && CCURP.charAt(3) <= 'Z')
							regresa++;
						if (CCURP.charAt(4) >= '0' && CCURP.charAt(4) <= '9')
							regresa++;
						if (CCURP.charAt(5) >= '0' && CCURP.charAt(5) <= '9')
							regresa++;
						if (CCURP.charAt(6) >= '0' && CCURP.charAt(6) <= '9')
							regresa++;
						if (CCURP.charAt(7) >= '0' && CCURP.charAt(7) <= '9')
							regresa++;
						if (CCURP.charAt(8) >= '0' && CCURP.charAt(8) <= '9')
							regresa++;
						if (CCURP.charAt(9) >= '0' && CCURP.charAt(9) <= '9')
							regresa++;
						if (CCURP.charAt(10) >= 'A' && CCURP.charAt(10) <= 'Z')
							regresa++;
						if (CCURP.charAt(11) >= 'A' && CCURP.charAt(11) <= 'Z')
							regresa++;
						if (CCURP.charAt(12) >= 'A' && CCURP.charAt(12) <= 'Z')
							regresa++;
						if (CCURP.charAt(13) >= 'A' && CCURP.charAt(13) <= 'Z')
							regresa++;
						if (CCURP.charAt(14) >= 'A' && CCURP.charAt(14) <= 'Z')
							regresa++;
						if (CCURP.charAt(15) >= 'A' && CCURP.charAt(15) <= 'Z')
							regresa++;
						if ((CCURP.charAt(16) >= '0' && CCURP.charAt(16) <= '9') || (CCURP.charAt(16) >= 'A' && CCURP.charAt(16) <= 'Z'))
							regresa++;
						if (CCURP.charAt(17) >= '0' && CCURP.charAt(17) <= '9')
							regresa++;
					} else {
						regresa++;
					}
				} else {
					regresa = 18;
				}
			}

		} catch (Exception ex) {
			warn("DatosMdoTrans: ", ex);
			throw new DAOException("DatosMdoTrans");
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
				warn("DatosMdoTrans.close", ex2);
			}
			return regresa;
		}
	}

	/**
	 * Metodo update
	 */
	public Object UPerEmpresa(Connection conGral, Object obj, int usuario)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cClave = "";
		try {
			/*if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}*/

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL2 = "";
			String cSQL = "";
			// TVPEREmpresa vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			TVPERDatos vPERDatos = (TVPERDatos) obj;

			cSQL2 = "SELECT COUNT(ICVEPERSONAL) FROM PEREMPRESATMP WHERE ICVEPERSONAL = "
					+ vPERDatos.getICvePersonal();

			pstmt = conn.prepareStatement(cSQL2);
			rset = pstmt.executeQuery();
			int existe = 0;
			while (rset.next()) {
				existe = rset.getInt(1);
			}

			// Calculando Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			if (existe == 0) {
				cSQL = "INSERT INTO PEREMPRESATMP "
						+ "(ICVEPERSONAL, CDSCEMPRESA, TIREGISTRO,ICVEUSUREGISTRA)"
						+ "VALUES(" + vPERDatos.getICvePersonal() + "," + "'"
						+ vPERDatos.getCDscEmpresaTmp() + "'," + "'"
						+ sqlTimestamp + "'," + "" + usuario + ")";
			} else {
				cSQL = "UPDATE PEREMPRESATMP SET " + "CDSCEMPRESA = '"
						+ vPERDatos.getCDscEmpresaTmp() + "', "
						+ "TIREGISTRO = '" + sqlTimestamp + "', "
						+ "ICVEUSUREGISTRA = " + usuario + " "
						+ "WHERE ICVEPERSONAL = " + vPERDatos.getICvePersonal();
			}

			pstmt = conn.prepareStatement(cSQL);
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
				if (conGral != null) {
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
	 * Metodo Find By de Constancias con el Formato de codigo de barras
	 * posterior a mayo 2013
	 * 
	 * @author: AG SA
	 */
	public Vector Conatancias(String cCodigo) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();
		String Fechat = "";
		String MedicoD = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			if (cCodigo.length() > 19) {
				Fechat = cCodigo.charAt(6) + "" + cCodigo.charAt(7) + ""
						+ cCodigo.charAt(8) + "" + cCodigo.charAt(9) + "-"
						+ cCodigo.charAt(12) + "" + cCodigo.charAt(13) + "-"
						+ cCodigo.charAt(2) + "" + cCodigo.charAt(3) + " "
						+ cCodigo.charAt(10) + "" + cCodigo.charAt(11) + ":"
						+ cCodigo.charAt(4) + "" + cCodigo.charAt(5) + ":"
						+ cCodigo.charAt(0) + "" + cCodigo.charAt(1) + "."
						+ cCodigo.charAt(14) + "" + cCodigo.charAt(15) + ""
						+ cCodigo.charAt(16) + "" + cCodigo.charAt(17) + ""
						+ cCodigo.charAt(18) + "" + cCodigo.charAt(19) + "";
				for (int i = 20; i < cCodigo.length(); i++) {
					MedicoD = MedicoD + "" + cCodigo.charAt(i);
				}
			} else {
				Fechat = "0";
				MedicoD = "0";
			}

			cSQL = "SELECT" + " 	P.CNOMBRE," + "	P.CAPPATERNO,"
					+ "	P.CAPMATERNO," + "	P.CRFC," + "	P.DTNACIMIENTO,"
					+ "	GP.CNOMBRE," + "	GE.CNOMBRE," + "      	P.ICVEPAISNAC,"
					+ "	P.ICVEESTADONAC," + "	P.ICVEEXPEDIENTE,"
					+ "   A.INUMEXAMEN, " + "PS.CDSCPROCESO, "
					+ "C.LDICTAMEN, " + "GU.CDSCUNIMED, " + "GM.CDSCMODULO, "
					+ "A.DTSOLICITADO, " + "C.DTINICIOVIG, " + "C.DTFINVIG "
					+ " FROM " + "	PERDATOS AS P," + "	GRLPAIS AS GP,"
					+ "   GRLENTIDADFED AS GE," + "   EXPEXAMAPLICA AS A, "

					+ " GRLPROCESO AS PS, " + " EXPEXAMCAT AS C, "
					+ " GRLUNIMED AS GU, " + " GRLMODULO AS GM   "

					+ " WHERE " + "	P.ICVEPAISNAC = GP.ICVEPAIS AND"
					+ "	P.ICVEPAISNAC = GE.ICVEPAIS AND"
					+ "	P.ICVEESTADONAC = GE.ICVEENTIDADFED AND"
					+ "	A.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE AND"
					+ "	A.LDICTAMINADO = 1 AND"
					+ "	A.ICVEPROCESO = PS.ICVEPROCESO AND"
					+ "	A.ICVEEXPEDIENTE = C.ICVEEXPEDIENTE AND"
					+ "	A.INUMEXAMEN = C.INUMEXAMEN AND   "
					+ "	A.ICVEUNIMED = GU.ICVEUNIMED AND"
					+ "	A.ICVEUNIMED = GM.ICVEUNIMED AND"
					+ "	A.ICVEMODULO = GM.ICVEMODULO AND"
					+ "   A.ICVEUSUDICTAMEN = " + MedicoD + " AND "
					+ "   A.TINIEXA = '" + Fechat + "'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				vPERDatos = new TVPERDatos();

				vPERDatos.setCNombre(rset.getString(1));
				vPERDatos.setCApPaterno(rset.getString(2));
				vPERDatos.setCApMaterno(rset.getString(3));
				vPERDatos.setCRFC(rset.getString(4));
				vPERDatos.setDtNacimiento(rset.getDate(5));
				vPERDatos.setCDscPaisNac(rset.getString(6));
				vPERDatos.setCDscEstadoNac(rset.getString(7));
				vPERDatos.setICvePaisNac(rset.getInt(8));
				vPERDatos.setICveEstadoNac(rset.getInt(9));
				vPERDatos.setICveExpediente(rset.getInt(10));
				vPERDatos.setINumExamen(rset.getInt(11));
				vPERDatos.setCCalle(rset.getString(12));// PROCESO
				vPERDatos.setICP(rset.getInt(13));// DICTAMEN
				vPERDatos.setCDscEmpresa(rset.getString(14));// UNIDAD MEDICA
				vPERDatos.setCDscEmpresaTmp(rset.getString(15));// MODULO
				vPERDatos.setDtSolicitado(rset.getDate(16));// SOLICITADO
				vPERDatos.setDtIniVIg(rset.getDate(17));// INICIO VIG
				vPERDatos.setDtFinVig(rset.getDate(18));// FIN VIG

				if (vPERDatos.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPERDatos.getDtNacimiento(), "/");
					vPERDatos.setCDscFecNacimiento(cFecha);
				} else {
					vPERDatos.setCDscFecNacimiento("");
				}
				vcPERDatos.addElement(vPERDatos);
			}
		} catch (Exception ex) {
			warn("FindByPersona", ex);
			throw new DAOException("FindByPersona");
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
				warn("FindByPersona.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo Find By de Constancias con el Formato de codigo de barras anterior
	 * a mayo 2013
	 * 
	 * @author: AG SA
	 */
	public Vector ConatFAnt(String cCodigo, String Expediente)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();
		String Fechat = "";
		String Fechat2 = "";
		String Mes = "";
		String MedicoD = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			if (cCodigo.length() >= 7) {
				String Codigo2 = "";
				Codigo2 = cCodigo.replace(Expediente, "");

				Fechat = Codigo2.charAt(2) + "" + Codigo2.charAt(3) + ""
						+ Codigo2.charAt(4) + "" + Codigo2.charAt(5) + "-"
						+ Codigo2.charAt(6) + "" + Codigo2.charAt(7) + "-"
						+ Codigo2.charAt(0) + "" + Codigo2.charAt(1) + "";
				
				Mes = Codigo2.charAt(6) + "" + Codigo2.charAt(7) + "";
				if(this.isNumeric(Mes)){
					if(Integer.parseInt(Mes) > 12){
						Fechat = "2000-01-01";
					}
				}
				Fechat2 = Codigo2.charAt(4) + "" + Codigo2.charAt(5) + ""
						+ Codigo2.charAt(6) + "" + Codigo2.charAt(7) + "-"
						+ Codigo2.charAt(2) + "" + Codigo2.charAt(3) + "-"
						+ Codigo2.charAt(0) + "" + Codigo2.charAt(1) + "";
				Mes = Codigo2.charAt(2) + "" + Codigo2.charAt(3) + "";
				if(this.isNumeric(Mes)){
					if(Integer.parseInt(Mes) > 12){
						Fechat2 = "2000-01-01";
					}
				}
				
				for (int i = 8; i < Codigo2.length(); i++) {
					MedicoD = MedicoD + "" + Codigo2.charAt(i);
				}
			} else {
				Fechat = "0";
				MedicoD = "0";
			}

			cSQL = "SELECT" + " 	P.CNOMBRE," + "	P.CAPPATERNO,"
					+ "	P.CAPMATERNO," + "	P.CRFC," + "	P.DTNACIMIENTO,"
					+ "	GP.CNOMBRE," + "	GE.CNOMBRE," + "      	P.ICVEPAISNAC,"
					+ "	P.ICVEESTADONAC," + "	P.ICVEEXPEDIENTE,"
					+ "   A.INUMEXAMEN, " + "PS.CDSCPROCESO, "
					+ "C.LDICTAMEN, " + "GU.CDSCUNIMED, " + "GM.CDSCMODULO, "
					+ "A.DTSOLICITADO, " + "C.DTINICIOVIG, " + "C.DTFINVIG "
					+ " FROM " + "	PERDATOS AS P," + "	GRLPAIS AS GP,"
					+ "   GRLENTIDADFED AS GE," + "   EXPEXAMAPLICA AS A, "

					+ " GRLPROCESO AS PS, " + " EXPEXAMCAT AS C, "
					+ " GRLUNIMED AS GU, " + " GRLMODULO AS GM   "

					+ " WHERE " + "	P.ICVEPAISNAC = GP.ICVEPAIS AND"
					+ "	P.ICVEPAISNAC = GE.ICVEPAIS AND"
					+ "	P.ICVEESTADONAC = GE.ICVEENTIDADFED AND"
					+ "	A.ICVEEXPEDIENTE = P.ICVEEXPEDIENTE AND"
					+ "	A.LDICTAMINADO = 1 AND"
					+ "	A.ICVEPROCESO = PS.ICVEPROCESO AND"
					+ "	A.ICVEEXPEDIENTE = C.ICVEEXPEDIENTE AND"
					+ "	A.INUMEXAMEN = C.INUMEXAMEN AND   "
					+ "	A.ICVEUNIMED = GU.ICVEUNIMED AND"
					+ "	A.ICVEUNIMED = GM.ICVEUNIMED AND"
					+ "	A.ICVEMODULO = GM.ICVEMODULO AND"
					+ "   A.ICVEUSUDICTAMEN = " + MedicoD + " AND "
					+ " (  C.DTINICIOVIG = '" + Fechat + "' OR " 
					+ "    C.DTINICIOVIG = '" + Fechat2 + "' ) AND "
					+ "   A.ICVEEXPEDIENTE = " + Expediente;
			
			System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			while (rset.next()) {
				vPERDatos = new TVPERDatos();

				vPERDatos.setCNombre(rset.getString(1));
				vPERDatos.setCApPaterno(rset.getString(2));
				vPERDatos.setCApMaterno(rset.getString(3));
				vPERDatos.setCRFC(rset.getString(4));
				vPERDatos.setDtNacimiento(rset.getDate(5));
				vPERDatos.setCDscPaisNac(rset.getString(6));
				vPERDatos.setCDscEstadoNac(rset.getString(7));
				vPERDatos.setICvePaisNac(rset.getInt(8));
				vPERDatos.setICveEstadoNac(rset.getInt(9));
				vPERDatos.setICveExpediente(rset.getInt(10));
				vPERDatos.setINumExamen(rset.getInt(11));
				vPERDatos.setCCalle(rset.getString(12));// PROCESO
				vPERDatos.setICP(rset.getInt(13));// DICTAMEN
				vPERDatos.setCDscEmpresa(rset.getString(14));// UNIDAD MEDICA
				vPERDatos.setCDscEmpresaTmp(rset.getString(15));// MODULO
				vPERDatos.setDtSolicitado(rset.getDate(16));// SOLICITADO
				vPERDatos.setDtIniVIg(rset.getDate(17));// INICIO VIG
				vPERDatos.setDtFinVig(rset.getDate(18));// FIN VIG

				if (vPERDatos.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vPERDatos.getDtNacimiento(), "/");
					vPERDatos.setCDscFecNacimiento(cFecha);
				} else {
					vPERDatos.setCDscFecNacimiento("");
				}
				vcPERDatos.addElement(vPERDatos);
			}
		} catch (Exception ex) {
			warn("FindByPersona", ex);
			throw new DAOException("FindByPersona");
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
				warn("FindByPersona.close", ex2);
			}
			return vcPERDatos;
		}
	}
	/**
	 * Metodo validaCURP2  
	 * devuelve el numero de ocurrencias CURP con expedientes diferentes.
	 * @param CCURP String CURP
	 * @param icvepersonal String icvepersonal
	 * @author: Ing. Andres Esteban Bernal Mu?z. 
	 */
	public int ValidaCURP2(String CCURP,String icvepersonal) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int regresa = 0;		
		
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT count(ICVEPERSONAL) FROM PERDATOS WHERE ICVEEXPEDIENTE <>"
					+ icvepersonal +" AND CCURP='" +CCURP+"'";
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			rset.next();//Solo envia 1 unico resultado.
			regresa= Integer.parseInt(rset.getString(1));				
				
		} catch (Exception ex) {
			warn("CURPMdoValida: ", ex);
			throw new DAOException("CURPMdoValida");
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
				warn("CURPMdoValida.close", ex2);
			}
			return regresa;
		}
	}
	/**
	 * Metodo findCURP
	 * 
	 * @author: Ing. Andres Esteban Bernal Mu?z. 
	 * Devuelve CURP por icvepersonal
	 */
	public String findCURP(String icvepersonal) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//int regresa = 0;		
		String CCURP= null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT CCURP FROM PERDATOS WHERE ICVEEXPEDIENTE ="
					+ icvepersonal;
			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			rset.next();//Solo envia 1 unico resultado.
			CCURP=rset.getString(1);				
				
		} catch (Exception ex) {
			warn("CURPfindBy: ", ex);
			throw new DAOException("CURPfindBy");
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
				warn("CURPfindBy.close", ex2);
			}
			return CCURP;
		}
	}

	/*
	 * Encuentra CURP para validar TDEPiCisCita 
	 * */
	public int iCURP(String cCURP) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		int regresa = 0;		
		
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT count(CCURP) FROM PERDATOS WHERE CCURP ='"+ cCURP+"'";			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			rset.next();//Solo envia 1 unico resultado.
			regresa=Integer.parseInt(rset.getString(1));				
				
		} catch (Exception ex) {
			warn("CURPfindBy: ", ex);
			throw new DAOException("CURPfindBy");
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
				warn("CURPfindBy.close", ex2);
			}
			return regresa;
		}
	}
	/**
	 * Valida nomenclatura de CURP 
	 * */
	public boolean validaCURP2(String cCURP){
		
		if(cCURP.matches(
				"[A-Z]{4}[0-9]{2}" + //Se realiza modificacion en las primeras cuatro letras
		//para que valide siendo unicamente LETRAS por los criterios de excepcion de RENAPO						
				"(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
				"[HM]{1}" +
				"(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
				"[B-DF-HJ-NP-TV-Z]{3}" +
				"[0-9A-Z]{1}[0-9]{1}$")){//AAAA######AAAAAA##
			
			return true;
		}
		return false;
	}

		
	/** 04/06/2014
	 * Encuentra el numero de expediente
	 * @param cCURP
	 * 				Curp del que se requiere el ICVEEXPEDIENTE 
	 * return Devuelve num de expediente
	 * 
	 * */
	public int getExpediente(String cCURP) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		int regresa = 0;	
		String str="";
		
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT ICVEEXPEDIENTE FROM PERDATOS WHERE CCURP ='"+ cCURP+"'";			
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			rset.next();
			str=rset.getString(1);
			regresa=Integer.parseInt(str);
			
		} catch (Exception ex) {
			warn("getExpediente: ", ex);
			throw new DAOException("getExpediente");
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
				warn("getExpediente.close", ex2);
			}
			return regresa;
		}
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}