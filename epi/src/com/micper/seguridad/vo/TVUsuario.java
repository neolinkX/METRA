package com.micper.seguridad.vo;

import java.io.*;

import com.micper.ingsw.TParametro;
import com.micper.sql.*;
import gob.sct.medprev.dao.TDUsuXExpLogin;
import gob.sct.medprev.vo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class TVUsuario implements Serializable {

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros.getPropEspecifica("ConDB");
	int iCveusuario;
	java.sql.Date dtRegistro;
	String cUsuario;
	String cPassword;
	String cNombre;
	String cApPaterno;
	String cApMaterno;
	String cCalle;
	String cColonia;
	String cCorreo;
	int iCvePais;
	String cDscPais;
	int iCveEntidadFed;
	String cDscEntidadFed;
	int iCveMunicipio;
	String cDscMunicipio;
	int iCodigoPostal;
	String cTelefono;
	int iCveUnidadOrg;
	int lBloqueado;
	   String cID;
	Vector vUnidades = new Vector();
	Vector vUsuMedicos = new Vector();
	// Permisos del usuario, se utilizarán para el llenado de los combos.
	private Vector vUniMed;
	private Vector vUMModulo;
	private Vector vUMModProc;
	private Vector vUMMPArea;
	private java.util.Vector vTodosP;
	String cDirIp;
	int ContadorAcceso;

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	   public String getID() {
		     return this.cID;
		   }
		 
		   public void setID(String cID) {
		     this.cID = cID;
		   }
		 
	
	public String getCApMaterno() {
		return cApMaterno;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public String getCCalle() {
		return cCalle;
	}

	public String getCNombre() {
		return cNombre;
	}

	public String getCColonia() {
		return cColonia;
	}
	
	public String getCCorreo() {
		return cCorreo;
	}

	public String getCPassword() {
		return cPassword;
	}

	public String getCTelefono() {
		return cTelefono;
	}

	public String getCUsuario() {
		return cUsuario;
	}

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public int getICveEntidadFed() {
		return iCveEntidadFed;
	}

	public int getICveMunicipio() {
		return iCveMunicipio;
	}

	public int getICvePais() {
		return iCvePais;
	}

	public int getICveUnidadOrg() {
		return iCveUnidadOrg;
	}

	public int getICveusuario() {
		return iCveusuario;
	}

	public int getLBloqueado() {
		return lBloqueado;
	}
	
	public String getDirIp() {
		return cDirIp;
	}

	public int getContadorAcceso() {
		return ContadorAcceso;
	}

	
	public void setLBloqueado(int lBloqueado) {
		this.lBloqueado = lBloqueado;
	}

	public void setICveusuario(int iCveusuario) {
		this.iCveusuario = iCveusuario;
	}

	public void setICveUnidadOrg(int iCveUnidadOrg) {
		this.iCveUnidadOrg = iCveUnidadOrg;
	}

	public void setICvePais(int iCvePais) {
		this.iCvePais = iCvePais;
	}

	public void setICveMunicipio(int iCveMunicipio) {
		this.iCveMunicipio = iCveMunicipio;
	}

	public void setICveEntidadFed(int iCveEntidadFed) {
		this.iCveEntidadFed = iCveEntidadFed;
	}

	public void setICodigoPostal(int iCodigoPostal) {
		this.iCodigoPostal = iCodigoPostal;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public void setCUsuario(String cUsuario) {
		this.cUsuario = cUsuario;
	}

	public void setCTelefono(String cTelefono) {
		this.cTelefono = cTelefono;
	}

	public void setCPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public void setCColonia(String cColonia) {
		this.cColonia = cColonia;
	}

	public void setCCorreo(String cCorreo) {
		this.cCorreo = cCorreo;
	}
	
	public void setCCalle(String cCalle) {
		this.cCalle = cCalle;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}
	
	public void setDirIp(String cDirIp) {
		this.cDirIp = cDirIp;
	}
	
	public void setContadorAcceso(int ContadorAcceso) {
		this.ContadorAcceso = ContadorAcceso;
	}

	public Vector getVUniFiltro(int iCveProceso) {
		int i;
		Vector vcProceso = new Vector();
		TVGRLUMUsuario vGRLUMUsu;
		for (i = 0; i < vUnidades.size(); i++) {
			vGRLUMUsu = (TVGRLUMUsuario) vUnidades.get(i);
			if (vGRLUMUsu.getICveProceso() == iCveProceso) {
				vcProceso.add(vGRLUMUsu);
			}
		}
		return vcProceso;
	}

	public int getiDedoAValidar() {
		int res = -1;
		TDUsuXExpLogin tdUsuXExpLogin = new TDUsuXExpLogin();
		res = tdUsuXExpLogin.getiDedoAValidar(this.iCveusuario);
		return res;
	}

	public Vector getVUnidades() {
		return vUnidades;
	}

	public Vector getVUniNoDup() {
		int i;
		Vector vcProceso = new Vector();
		HashMap hmProceso = new HashMap();
		TVGRLUMUsuario vGRLUMUsu;
		for (i = 0; i < vUnidades.size(); i++) {
			vGRLUMUsu = (TVGRLUMUsuario) vUnidades.get(i);
			hmProceso.put("" + vGRLUMUsu.getICveUniMed(), vGRLUMUsu);
		}
		Set stProceso = hmProceso.keySet();
		Iterator itProceso = stProceso.iterator();
		while (itProceso.hasNext()) {
			vcProceso.add(hmProceso.get(itProceso.next()));
		}
		return vcProceso;
	}

	public void setVUnidades(Vector vUnidades) {
		this.vUnidades = vUnidades;
	}

	public Vector getVUsuMedicos() {
		return vUsuMedicos;
	}

	public void setVUsuMedicos(Vector vUsuMedicos) {
		this.vUsuMedicos = vUsuMedicos;
	}

	public int getGrupoUsuarioADMSEG(int icveusuario) {
		int icveGrupo = -1;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "Select * from seggpoxusr where icveusuario =? and icvegrupo = 46";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, icveusuario);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				icveGrupo = 46;
			}
		} catch (Exception ex) {
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
				ex2.printStackTrace();
			}
		}
		return icveGrupo;
	}

	public int getIdIcveExpediente() {
		int icveexpediente = 0;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(
					VParametros.getPropEspecifica("ConDBModulo"));
			conn = dbConn.getConnection();

			String cSQL = "SELECT ICVEEXPEDIENTE FROM SEGUSUEXP   WHERE ICVEUSUARIO = ? ";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, this.iCveusuario);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				icveexpediente = rset.getInt("ICVEEXPEDIENTE");
			}
		} catch (Exception ex) {
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
				ex2.printStackTrace();
			}
		}
		return icveexpediente;
	}

	public int getIcveDoctoHuella() {
		int icveexpediente = 0;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(
					VParametros.getPropEspecifica("ConDBModulo"));
			conn = dbConn.getConnection();

			// String cSQL =
			// "SELECT INODOCTO FROM SEGUSUEXP  WHERE ICVEUSUARIO = ? ";
			String cSQL = "SELECT INODOCTO "
					+ " FROM LICFFH "
					+ " WHERE ICVEPERSONAL = (SELECT ICVEEXPEDIENTE FROM SEGUSUEXP  WHERE ICVEUSUARIO = ?) "
					+ " AND IDEDO=(SELECT IDEDO FROM SEGUSUEXP  WHERE ICVEUSUARIO = ?) "
					+ " AND ICVETIPOFFH = 3 ORDER BY TSCAPTURA DESC";

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, this.iCveusuario);
			pstmt.setInt(2, this.iCveusuario);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				icveexpediente = rset.getInt("INODOCTO");
			}
		} catch (Exception ex) {
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
				ex2.printStackTrace();
			}
		}
		return icveexpediente;
	}

	public HashMap getIcveDoctosHuellasPaciente(String icveexpedientepaciente) {
		int icveexpediente = 0;
		int ExisteHuella = 0;
		HashMap mapaNumeroDoctoDedos = new HashMap();

		try {
			icveexpediente = Integer.parseInt(icveexpedientepaciente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		DbConnection dbConn2 = null;
		Connection conn2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		try {
			dbConn = new DbConnection(
					VParametros.getPropEspecifica("ConDBModulo"));
			conn = dbConn.getConnection();
			dbConn2 = new DbConnection(
					VParametros.getPropEspecifica("ConDBModulo"));
			conn2 = dbConn.getConnection();

			// /Busqueda de la primer huella capturada despues del 29 de abril
			// del 2013
			// System.out.println("Busqueda de la primer huella capturada despues del 29 de abril del 2013");
			String cSQL2 = "SELECT IDEDO, INODOCTO FROM LICFFH WHERE ICVEPERSONAL = ? AND ICVETIPOFFH = 3 AND TSCAPTURA >= '"
					+ VParametros
							.getPropEspecifica("FechaCapturanConFingerPrint")
					+ "' ORDER BY  TSCAPTURA DESC ";
			// System.out.println(cSQL2);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(2);
			pstmt2 = conn2.prepareStatement(cSQL2);
			pstmt2.setInt(1, icveexpediente);
			rset2 = pstmt2.executeQuery();
			while (rset2.next()) {
				mapaNumeroDoctoDedos.put(rset2.getString("IDEDO"),
						rset2.getInt("INODOCTO"));
				ExisteHuella++;
			}

			// / Si no se encontraron huellas vamos por la ultima tomada
			if (ExisteHuella == 0) {
				// System.out.println("No se encontraron huellas se descarga la ultima tomada");
				String cSQL = "SELECT IDEDO, INODOCTO FROM LICFFH WHERE ICVEPERSONAL = ? AND ICVETIPOFFH = 3 ORDER BY TSCAPTURA ";
				// System.out.println(cSQL);
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
				int count;
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, icveexpediente);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					mapaNumeroDoctoDedos.put(rset.getString("IDEDO"),
							rset.getInt("INODOCTO"));
				}
			}
		} catch (Exception ex) {
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
				if (rset2 != null) {
					rset2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn2 != null) {
					conn2.close();
				}
				dbConn2.closeConnection();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
		return mapaNumeroDoctoDedos;
	}

	public int getIcveDoctoHuella(int iCveExpediente) {
		int icveexpediente = 0;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(
					VParametros.getPropEspecifica("ConDBModulo"));
			conn = dbConn.getConnection();

			String cSQL = "SELECT INODOCTO FROM SEGUSUEXP  WHERE ICVEEXPEDIENTE = ?";

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iCveExpediente);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				icveexpediente = rset.getInt("INODOCTO");
			}
		} catch (Exception ex) {
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
				ex2.printStackTrace();
			}
		}
		return icveexpediente;
	}

	public Vector getVServFiltro(int iCveUniMed, int iCveProceso, int iCveModulo) {
		int i;
		// OPTIMIZADO Ivan Santiago Méndez
		Vector vcProceso = new Vector();
		// TVGRLUSUMedicos vGRLUSUMedicos;
		for (i = 0; i < vUsuMedicos.size(); i++) {
			// vGRLUSUMedicos = ((TVGRLUSUMedicos) vUsuMedicos.get(i));
			// if (vGRLUSUMedicos.getICveUniMed() == iCveUniMed &&
			// vGRLUSUMedicos.getICveProceso() == iCveProceso &&
			// vGRLUSUMedicos.getICveModulo() == iCveModulo) {
			if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == iCveUniMed) {
				if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveProceso() == iCveProceso) {
					if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveModulo() == iCveModulo) {
						vcProceso.add(vUsuMedicos);
					}
				}
			}
		}
		return vcProceso;
	}

	public HashMap getVServicios(int iCveUniMed, int iCveProceso, int iCveModulo) {
		int i;
		/*
		 * MODIFICADO PARA PERFORMANCE DE WEBLOGIC EXCESO EN EL USO DE LA RAM
		 * CREANDO OBJETOS DE TIPO "TVGRLUSUMedicos" QUE NO SE USAN Y SE
		 * OOPTIMIZO LA VALIDACIÓN PARA QUE ENCUENTRE TODOS LOS QUE COINCIDAN
		 * PERO SI NO ENTRA EN ALGUNA NO GASTE CICLOS DE PROCESADOS EN OTRAS
		 * VALIDACIONES YA INECESARIAS.
		 * 
		 * IVAN SANTIAGO MÉNDEZ 06/04/2010
		 */

		HashMap hmProceso = new HashMap();
		// TVGRLUSUMedicos vGRLUSUMedicos = null;
		for (i = 0; i < vUsuMedicos.size(); i++) {
			// vGRLUSUMedicos = (TVGRLUSUMedicos) vUsuMedicos.get(i);
			// if (vGRLUSUMedicos.getICveUniMed() == iCveUniMed &&
			// vGRLUSUMedicos.getICveProceso() == iCveProceso &&
			// vGRLUSUMedicos.getICveModulo() == iCveModulo) {
			// hmProceso.put(""+vGRLUSUMedicos.getICveServicio(),vGRLUSUMedicos.getCDscServicio());
			// if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() ==
			// iCveUniMed &&
			// ((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveProceso() ==
			// iCveProceso &&
			// ((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveModulo() ==
			// iCveModulo) {
			if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == iCveUniMed) {
				if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveProceso() == iCveProceso) {
					if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveModulo() == iCveModulo) {
						hmProceso
								.put(""
										+ ((TVGRLUSUMedicos) vUsuMedicos.get(i))
												.getICveServicio(),
										((TVGRLUSUMedicos) vUsuMedicos.get(i))
												.getCDscServicio());
					}
				}
			}
		}
		return hmProceso;
	}

	public String getCDscEntidadFed() {
		return cDscEntidadFed;
	}

	public void setCDscEntidadFed(String cDscEntidadFed) {
		this.cDscEntidadFed = cDscEntidadFed;
	}

	public String getCDscMunicipio() {
		return cDscMunicipio;
	}

	public void setCDscMunicipio(String cDscMunicipio) {
		this.cDscMunicipio = cDscMunicipio;
	}

	public String getCDscPais() {
		return cDscPais;
	}

	public void setCDscPais(String cDscPais) {
		this.cDscPais = cDscPais;
	}

	public Vector getVUniMed() {
		return vUniMed;
	}

	public void setVUniMed(Vector vUniMed) {
		this.vUniMed = vUniMed;
	}

	public Vector getVUMModulo() {
		return vUMModulo;
	}

	public void setVUMModulo(Vector vUMModulo) {
		this.vUMModulo = vUMModulo;
	}

	public Vector getVUMModProc() {
		return vUMModProc;
	}

	public void setVUMModProc(Vector vUMModProc) {
		this.vUMModProc = vUMModProc;
	}

	public Vector getVUMMPArea() {
		return vUMMPArea;
	}

	public void setVUMMPArea(Vector vUMMPArea) {
		this.vUMMPArea = vUMMPArea;
	}

	public Vector getVModXUnidad(int iCveUniMed) {
		Vector vModulo = new Vector();
		TVDinRep vObjeto;
		for (int i = 0; i < this.vUMModulo.size(); i++) {
			vObjeto = new TVDinRep();
			vObjeto = (TVDinRep) this.vUMModulo.get(i);
			if (vObjeto.getInt("iCveUniMed") == iCveUniMed) {
				vModulo.add(vObjeto);
			}
		}
		return vModulo;
	}

	public void bloqueaUsuario() {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			cSQL = "UPDATE SEGUSUARIO SET LBLOQUEADO=1 WHERE ICVEUSUARIO = "
					+ this.iCveusuario;

			pstmt = conn.prepareStatement(cSQL);

			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
	}

	public void desbloqueaUsuario() {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			// TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			// Calcular Timestamp para el campo TINIEXA
			java.util.Date utilDate = new java.util.Date(); // fecha actual
			long lnMilisegundos = utilDate.getTime();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(
					lnMilisegundos);

			cSQL = "UPDATE SEGUSUARIO SET LBLOQUEADO=0 WHERE ICVEUSUARIO = "
					+ this.iCveusuario;

			pstmt = conn.prepareStatement(cSQL);

			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				ex2.printStackTrace();
			}
		}
	}

	// Modificado por error 500
	public Vector getVProcXModulo(int iCveUniMed, int iCveModulo) {
		Vector vProceso = new Vector();
		TVDinRep vObjeto;
		// Se añadio esta condicion
		if (this.vUMModProc == null) {
			// System.out.println("vUMModProc esta vacio no hacer nada");
		} else {
			// Parte original del codigo
			try {
				for (int i = 0; i < this.vUMModProc.size(); i++) {
					vObjeto = new TVDinRep();
					vObjeto = (TVDinRep) this.vUMModProc.get(i);
					if (vObjeto.getInt("iCveUniMed") == iCveUniMed
							&& vObjeto.getInt("iCveModulo") == iCveModulo) {
						vProceso.add(vObjeto);
					}
				}
			} catch (Exception e) {
				// System.out.println("Error: " + e);
			}
			// Finde parte original del codigo
		}
		return vProceso;
	}

	public java.util.Vector getVTodosP() {
		return vTodosP;
	}

	public void setVTodosP(java.util.Vector vTodosP) {
		this.vTodosP = vTodosP;
	}

	public Vector getModuloFUP(int iCveUniMed, int iCveProceso) {
		Vector vProceso = new Vector();
		TVDinRep vObjeto;
		/*
		 * // System.out.println("Debug de error del ModuloFUP");
		 * if(this.vTodosP == null) //
		 * System.out.println("vTodosP esta vacio no hacer nada"); else //
		 * System.out.println("vTodosP si contiene valores y contiene datos");
		 */
		try {
			for (int i = 0; i < this.vTodosP.size(); i++) {
				vObjeto = new TVDinRep();
				vObjeto = (TVDinRep) this.vTodosP.get(i);
				if (vObjeto.getInt("iCveUniMed") == iCveUniMed
						&& vObjeto.getInt("iCveProceso") == iCveProceso) {
					vObjeto.put("iClave", vObjeto.get("iCveModulo"));
					vObjeto.put("cDescripcion", vObjeto.get("cDscModulo"));
					vProceso.add(vObjeto);
				}
			}
		} catch (Exception e) {
			// System.out.println("Error: " + e);
		}

		return vProceso;
	}
}
