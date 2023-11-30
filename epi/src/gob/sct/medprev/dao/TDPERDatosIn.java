package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import java.lang.Object.*;
import java.lang.String.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;

import java.util.StringTokenizer;

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
 * @author Jaime Enrique SuÃ¡rez Romero
 * @version 1.0
 */

/*
 * JESR: Solo estoy utilizando el mÃ©todo findBySelector;
 */

public class TDPERDatosIn extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	String RFC2;
	String Paterno2;
	String Materno2;
	String Nombre2;
	String Resultado;

	String cSQL = "";
	String cSQL2 = "";

	String regresa;
	String cRFC;

	String ICVEDIRECCION = "1";

	String insertsql;
	String insertsql2;
	String insertsql3;
	String insertsql4;

	String Insert2;

	Connection conGral = null;

	public TDPERDatosIn() {
	}

	public TDPERDatosIn(String RFC2, String Paterno2, String Materno2,
			String Nombre2, String insertsql, String insertsql2,
			String insertsql3, String insertsql4) {
		this.Nombre2 = Nombre2;
		this.RFC2 = RFC2;
		this.Paterno2 = Paterno2;
		this.Materno2 = Materno2;

		this.insertsql = insertsql;
		this.insertsql2 = insertsql2;
		this.insertsql3 = insertsql3;
		this.insertsql4 = insertsql4;
	}

	/**
	 * Metodos para recibir datos
	 */

	public String getRFC2() {
		return RFC2;
	}

	public void setRFC2(String RFC2) {
		this.RFC2 = RFC2;
	}

	public String getPaterno2() {
		return Paterno2;
	}

	public void setPaterno2(String Paterno2) {
		this.Paterno2 = Paterno2;
	}

	public String getMaterno2() {
		return Materno2;
	}

	public void setMaterno2(String Materno2) {
		this.Materno2 = Materno2;
	}

	public String getNombre2() {
		return Nombre2;
	}

	public void setNombre2(String Nombre2) {
		this.Nombre2 = Nombre2;
	}

	public String getinsertsql() {
		return insertsql;
	}

	public void setinsertsql(String insertsql) {
		this.insertsql = insertsql;
	}

	public String getinsertsql2() {
		return insertsql2;
	}

	public void setinsertsql2(String insertsql2) {
		this.insertsql2 = insertsql2;
	}

	public String getinsertsql3() {
		return insertsql3;
	}

	public void setinsertsql3(String insertsql3) {
		this.insertsql3 = insertsql3;
	}

	public String getinsertsql4() {
		return insertsql4;
	}

	public void setinsertsql4(String insertsql4) {
		this.insertsql4 = insertsql4;
	}

	/**
	 * Metodo Insert2
	 */
	public String getInsert2() {
		String nentro = "E";

		if (RFC2 != null) {
			DbConnection dbConn = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmtCvePersonal = null;
			PreparedStatement pstmtCveExpediente = null;
			ResultSet rsetCvePersona = null;
			ResultSet rsetCveExpediente = null;

			int iPersonal = 0;
			int iExpediente = 0;

			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";

			try {
				if (conGral != null) {
					conn = conGral;
				} else {
					dbConn = new DbConnection(dataSourceName);
					conn = dbConn.getConnection();
				}

				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);

				cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
				pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
				rsetCvePersona = pstmtCvePersonal.executeQuery();
				while (rsetCvePersona.next()) {
					iPersonal = rsetCvePersona.getInt(1);
				}
				iPersonal++;

				cSQLCveExpediente = "select max(iCveExpediente) from PERDatos ";
				pstmtCveExpediente = conn.prepareStatement(cSQLCveExpediente);
				rsetCveExpediente = pstmtCveExpediente.executeQuery();
				while (rsetCveExpediente.next()) {
					iExpediente = rsetCveExpediente.getInt(1);
				}
				iExpediente++;

				String cper = "";
				String ce = "";

				if (iPersonal > iExpediente) {
					cper = String.valueOf(iPersonal);
					ce = String.valueOf(iPersonal);
				}

				if (iPersonal < iExpediente) {
					cper = String.valueOf(iExpediente);
					ce = String.valueOf(iExpediente);
				}

				if (iPersonal == iExpediente) {
					cper = String.valueOf(iPersonal);
					ce = String.valueOf(iExpediente);
				}

				// // System.out.println("Clave personal = |"+iPersonal+"|");
				// / //
				// System.out.println("Clave expediente = |"+iExpediente+"|");

				// Damos de Alta a la persona en PERDATOS

				cSQL = insertsql + cper + ", " + ce + ", " + insertsql2;

				pstmt = conn.prepareStatement(cSQL);
				pstmt.executeUpdate();

				// Damos de alta la direccion de la persona en PERDIRECCION

				cSQL2 = insertsql3 + cper + insertsql4;

				pstmt2 = conn.prepareStatement(cSQL2);
				pstmt2.executeUpdate();

				Insert2 = String.valueOf(iPersonal);

				regresa = "La Persona se agrego con Éxito";

			}

			catch (Exception ex) {
				try {
					if (conGral == null) {
						conn.rollback();
					}
				} catch (Exception ex1) {
					warn("insert", ex1);
				}
				warn("insert", ex);
				// throw new DAOException("");
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
					if (pstmt2 != null) {
						pstmt2.close();
					}
					if (conGral == null) {
						conn.close();
						dbConn.closeConnection();
					}
				} catch (Exception ex2) {
					warn("insert.close", ex2);
				}

			}
			return regresa;
		} else
			return nentro;

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
			String cSQL2 = "";
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERDatos " + "set " +

			"iCvePaisNac= ?, " + "iCveEstadoNac= ?  " +

			"where iCvePersonal = ?";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vPERDatos.getICvePaisNac());
			pstmt.setInt(2, vPERDatos.getICveEstadoNac());

			pstmt.setInt(3, vPERDatos.getICvePersonal());

			pstmt.executeUpdate();

			cSQL2 = "update PERDireccion " + "set " +

			"iCvePais= ?, " + "iCveEstado= ?, " + "iCveMunicipio= ? " +

			"where iCvePersonal = ?";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL2);
			pstmt.setInt(1, vPERDatos.getICvePais());
			pstmt.setInt(2, vPERDatos.getICveEstado());
			pstmt.setInt(3, vPERDatos.getICveMunicipio());

			pstmt.setInt(4, vPERDatos.getICvePersonal());

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

	/*
	 * public String actualiza(String cFDI, String cFDF) throws DAOException {
	 * // System.out.println("Inicio |"+cFDI +"| fin |" + cFDF); String
	 * resultado= "Se han actualizado ";
	 * 
	 * DbConnection dbConn = null; Connection conn = null; PreparedStatement
	 * pstmtUpdate = null; ResultSet rsetUpdate = null; PreparedStatement pstmt
	 * = null;
	 * 
	 * try{ if (conGral != null) { conn = conGral; } else { dbConn = new
	 * DbConnection(dataSourceName); conn = dbConn.getConnection(); }
	 * 
	 * conn.setAutoCommit(false); conn.setTransactionIsolation(2);
	 * 
	 * String cSQLCvePersonal; int resultadoquery = 0;
	 * 
	 * cSQLCvePersonal =
	 * "select count( ICVEEXPEDIENTE) FROM EXPEXAMCAT WHERE DTDICTAMINADO BETWEEN '"
	 * +cFDI+"' AND '"+cFDF+"' "; pstmtUpdate =
	 * conn.prepareStatement(cSQLCvePersonal); rsetUpdate =
	 * pstmtUpdate.executeQuery(); while (rsetUpdate.next()) { resultadoquery =
	 * rsetUpdate.getInt(1); } String str=String.valueOf(resultadoquery);
	 * resultado = resultado +" "+ str;
	 * 
	 * Vector cp=new Vector(); Vector ne=new Vector(); Vector fe=new Vector();
	 * 
	 * //cSQLCvePersonal =
	 * "select count( ICVEEXPEDIENTE) FROM EXPEXAMCAT WHERE DTDICTAMINADO BETWEEN '"
	 * +cFDI+"' AND '"+cFDF+"' "; cSQLCvePersonal =
	 * "select  ICVEEXPEDIENTE, INUMEXAMEN, DTDICTAMINADO FROM EXPEXAMCAT WHERE DTDICTAMINADO BETWEEN '"
	 * +cFDI+"' AND '"+cFDF+"' ORDER BY DTDICTAMINADO ASC"; pstmtUpdate =
	 * conn.prepareStatement(cSQLCvePersonal); rsetUpdate =
	 * pstmtUpdate.executeQuery(); //
	 * System.out.println("**************  LLENANDO VECTOR  ****************");
	 * while (rsetUpdate.next()) { cp.addElement(rsetUpdate.getString(1));
	 * ne.addElement(rsetUpdate.getString(2));
	 * fe.addElement(rsetUpdate.getString(3)); }
	 * 
	 * int i=0; String fecha=""; String fechaFV=""; String dia =""; String mes
	 * =""; String año =""; String añofv=""; int año2=0; int dia2=0; int mes2=0;
	 * int contador=600; int resta=1;
	 * 
	 * String cSQLPER=""; String cSQLCAT="";
	 * 
	 * // //
	 * System.out.println("Numero total de datos guardados en el vector "+cp
	 * .size()+"    "+ne.size()+"   "+fe.size()); // // System.out.println(
	 * "Contador  ClaveExpediente       NumExamen       FechaIV           FechaFV"
	 * ); // System.out.println(
	 * "**************  EJECUTANDO SENTENCIAS UPDATE  ****************");
	 * for(i=0; i <= cp.size(); i++){
	 * 
	 * resta = contador - i; if(resta == 0){ //
	 * System.out.println("*************** DETENEMOS LA CONEXION ***************"
	 * ); pstmt.close(); conn.close(); dbConn.closeConnection();
	 * 
	 * //
	 * System.out.println("*************** LEVANTAMOS LA CONEXION  ***************"
	 * ); pstmt = null; dbConn = new DbConnection(dataSourceName); conn =
	 * dbConn.getConnection(); conn.setAutoCommit(false);
	 * conn.setTransactionIsolation(2); contador =contador + 600; }
	 * 
	 * 
	 * fecha = fe.elementAt(i).toString()+"-"; StringTokenizer dividefecha = new
	 * StringTokenizer(fecha, "-"); año = dividefecha.nextToken(); mes =
	 * dividefecha.nextToken(); dia = dividefecha.nextToken();
	 * 
	 * //Incrementamos el año a 2 para el fin de la vigencia
	 * año2=Integer.parseInt(año.trim()); año2=año2+2;
	 * añofv=String.valueOf(año2);
	 * 
	 * //Excepsion 29 de febrero mes2=Integer.parseInt(mes.trim());
	 * dia2=Integer.parseInt(dia.trim());
	 * 
	 * if(mes2 == 2 && dia2 == 29){ mes2=mes2+1; dia2=1;
	 * mes=String.valueOf(mes2); dia=String.valueOf(dia2); }
	 * 
	 * 
	 * fechaFV=añofv+"-"+mes+"-"+dia;
	 * 
	 * // System.out.println("Contador (i) ::: " + i +"    Contador" + contador
	 * +"          resta:::"+resta);
	 * 
	 * //Actualizacion de Perlicencia cSQLPER =
	 * "update PERLICENCIA SET DTINIVIGENCIA = '"
	 * +fe.elementAt(i).toString()+"', DTFINVIGENCIA = '"
	 * +fechaFV+"' WHERE ICVEPERSONAL = "
	 * +cp.elementAt(i).toString()+" AND INUMEXAMEN = "
	 * +ne.elementAt(i).toString() +""; // System.out.println(cSQLPER); pstmt =
	 * conn.prepareStatement(cSQLPER); pstmt.executeUpdate();
	 * 
	 * //Actualizacion de ExamCat cSQLCAT =
	 * "update EXPEXAMCAT SET DTINICIOVIG = '"
	 * +fe.elementAt(i).toString()+"', DTFINVIG = '"
	 * +fechaFV+"' WHERE ICVEEXPEDIENTE = "
	 * +cp.elementAt(i).toString()+" AND INUMEXAMEN = "
	 * +ne.elementAt(i).toString() +""; // System.out.println(cSQLCAT); pstmt =
	 * conn.prepareStatement(cSQLCAT); pstmt.executeUpdate();
	 * 
	 * // //
	 * System.out.println(i+"      "+cp.elementAt(i)+"        "+ne.elementAt
	 * (i)+"        |"+fe.elementAt(i)+"|         |"+fechaFV+"|"); }
	 * 
	 * } catch (Exception ex2) { warn("pstmtUpdate.close", ex2); } finally { try
	 * {
	 * 
	 * if (pstmtUpdate != null) { pstmtUpdate.close(); }
	 * 
	 * if (rsetUpdate != null) { rsetUpdate.close(); }
	 * 
	 * if (pstmt != null) { pstmt.close(); }
	 * 
	 * if (conGral == null) { conn.close(); dbConn.closeConnection(); } } catch
	 * (Exception ex2) { warn("pstmtUpdate.close", ex2); }
	 * 
	 * }
	 * 
	 * return resultado; }
	 */

}
