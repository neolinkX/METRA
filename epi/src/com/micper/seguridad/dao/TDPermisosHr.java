package com.micper.seguridad.dao;

//Java imports
import java.sql.*;
import java.util.*;

import com.micper.seguridad.vo.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.dao.*;
import com.micper.ingsw.*;
import com.micper.sql.*;

import com.micper.seguridad.*;

public class TDPermisosHr {
	private String dataSrcName = "";
	private String dataSrcName2 = "";
	private String dataSrcName3 = "";
	private Vector vcMenuUsuario = new Vector();
	private HashMap hmPUsuario;

	private TParametro vParametros;

	// Nuevas variables para nuevo metodo contraseñas
	public static final int PBKDF2_ITERATIONS = 1000;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;
	private StringBuilder builder;
	private TVDinRep usrData;

	public TDPermisosHr() {
	}

	public void menuUsuario(String cNumModulo, int iCveUsuario) {
		vParametros = new TParametro(cNumModulo);
		Vector vcMenu = TDMenu.getVSystemMenu(cNumModulo);
		hmPUsuario = this.permisosUsuario(iCveUsuario,
				Integer.parseInt(cNumModulo, 10));
		HashMap hmAuxPadres = new HashMap();

		TVMenu vMenu;
		TVMenu vUsuario;

		for (int i = 0; i < vcMenu.size(); i++) {
			vMenu = (TVMenu) vcMenu.get(i);
			if (hmPUsuario.containsKey(vMenu.getCNomPagina())) {
				if (vMenu.getIOpcPadre() == 0
						|| hmAuxPadres.containsKey("" + vMenu.getIOpcPadre())) {
					StringTokenizer stActualizacion = new StringTokenizer(
							(String) hmPUsuario.get(vMenu.getCNomPagina()), "|");
					if (stActualizacion.countTokens() == 2) {
						stActualizacion.nextElement();
						vMenu.setLActualizacion(Integer.parseInt(""
								+ stActualizacion.nextElement()));
					} else {
						vMenu.setLActualizacion(0);
					}
					vcMenuUsuario.add(vMenu);
					hmAuxPadres.put("" + vMenu.getIOrden(),
							"" + vMenu.getIOrden());
				}
			}
		}
	}

	private HashMap permisosUsuario(int iCveUsuario, int iCveSistema) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		HashMap vPermisos = new HashMap();
		boolean validaUM = false;
		boolean validaHr = false;
		boolean validaUsrAdmin = false;

		try {
			dataSrcName = vParametros.getPropEspecifica("ConDB");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select segprograma.cNombre, segprograma.cdscprograma, segpermisoxgpo.lactualizacion "
					+ "from segusuario  "
					+ "join seggpoxusr on segusuario.icveusuario = seggpoxusr.icveusuario "
					+ "and  seggpoxusr.icvesistema = ? "
					+ "join seggrupo on seggpoxusr.icvesistema = seggrupo.icvesistema "
					+ "and  seggpoxusr.icvegrupo = seggrupo.icvegrupo  "
					+ "and  seggrupo.lbloqueado = 0 "
					+ "join segpermisoxgpo on seggpoxusr.icvesistema = segpermisoxgpo.icvesistema "
					+ "and  seggpoxusr.icvegrupo = segpermisoxgpo.icvegrupo "
					+ "and  segpermisoxgpo.lejecucion = 1 "
					+ "join segprograma on segpermisoxgpo.icvesistema = segprograma.icvesistema "
					+ "and  segpermisoxgpo.icveprograma = segprograma.icveprograma  "
					+ "and  segprograma.lbloqueado = 0 "
					+ "where segusuario.icveusuario = ? "
					+ "and   segusuario.lbloqueado = 0 "
					+ "order by segprograma.icveprograma  ";

			lPStmt = conn.prepareStatement(lSQL);

			System.out.println(lSQL);

			lPStmt.setInt(1, iCveSistema);
			lPStmt.setInt(2, iCveUsuario);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vPermisos.put(lRSet.getString(1), lRSet.getString(2) + "|"
						+ lRSet.getInt(3));
			}
		} catch (Exception ex) {
			// System.out.println("permisosUsuario");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("permisosUsuario.Close");
			}
			return vPermisos;
		}
	}

	public HashMap getHmPUsuario() {
		return hmPUsuario;
	}

	public Vector getVcMenuUsuario() {
		return vcMenuUsuario;
	}

	public TVUsuario accesoUsuario(String cUsuario, String cContrasenia,
			String cNumModulo) {
		vParametros = new TParametro(cNumModulo);
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		TVUsuario vUsuario = null;
		boolean lSuccess = false;

		try {

			if (checkUserPass(cUsuario, cContrasenia)) { // Validamos usuario

				dataSrcName = vParametros.getPropEspecifica("ConDB");
				dbConn = new DbConnection(dataSrcName);
				conn = dbConn.getConnection();
				conn.setTransactionIsolation(2);

				String lSQL = "select segusuario.*, grlpais.cnombre as cdscpais, grlentidadfed.cnombre as cdscentidadfed, grlmunicipio.cnombre as cdscmunicipio "
						+ "from segusuario "
						+ "join grlpais on segusuario.icvepais = grlpais.icvepais "
						+ "join grlentidadfed on segusuario.icvepais = grlentidadfed.icvepais "
						+ "and segusuario.icveentidadfed = grlentidadfed.icveentidadfed "
						+ "join grlmunicipio on segusuario.icvepais = grlmunicipio.icvepais "
						+ "and segusuario.icveentidadfed = grlmunicipio.icveentidadfed "
						+ "and segusuario.icvemunicipio = grlmunicipio.icvemunicipio "
						+ "where cUsuario = ? " +
						// "and   cPassword = ? "+
						"and   lBloqueado = 0";

				lPStmt = conn.prepareStatement(lSQL);

				lPStmt.setString(1, cUsuario);
				// lPStmt.setString(2, cContrasenia);
				lRSet = lPStmt.executeQuery();

				while (lRSet.next()) {
					vUsuario = new TVUsuario();
					vUsuario.setICveusuario(lRSet.getInt("iCveUsuario"));
					vUsuario.setDtRegistro(lRSet.getDate("dtRegistro"));
					vUsuario.setCUsuario(lRSet.getString("cUsuario"));
					vUsuario.setCPassword(lRSet.getString("cPassword"));
					vUsuario.setCNombre(lRSet.getString("cNombre"));
					vUsuario.setCApPaterno(lRSet.getString("cApPaterno"));
					vUsuario.setCApMaterno(lRSet.getString("cApMaterno"));
					vUsuario.setCCalle(lRSet.getString("cCalle"));
					vUsuario.setCColonia(lRSet.getString("cColonia"));
					vUsuario.setICvePais(lRSet.getInt("iCvePais"));
					vUsuario.setICveEntidadFed(lRSet.getInt("iCveEntidadFed"));
					vUsuario.setICveMunicipio(lRSet.getInt("iCveMunicipio"));
					vUsuario.setICodigoPostal(lRSet.getInt("iCodigoPostal"));
					vUsuario.setCTelefono(lRSet.getString("cTelefono"));
					vUsuario.setICveUnidadOrg(lRSet.getInt("iCveUnidadOrg"));
					vUsuario.setLBloqueado(lRSet.getInt("lBloqueado"));
					vUsuario.setCDscPais(lRSet.getString("cdscpais"));
					vUsuario.setCDscEntidadFed(lRSet
							.getString("cdscentidadfed"));
					vUsuario.setCDscMunicipio(lRSet.getString("cdscmunicipio"));
					lSuccess = true;
				}

				if (lSuccess) {
					this.addUniMed(vUsuario, cNumModulo);
					this.addMedCons(vUsuario, cNumModulo);
					this.consUniMed(vUsuario);
					this.consUMModulo(vUsuario);
					this.consUMModProceso(vUsuario);
					this.consTodasDesc(vUsuario);
				}

			}

		} catch (Exception ex) {
			// System.out.println("accesoUsuario");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("accesoUsuario.Close");
			}
			return vUsuario;
		}
	}

	/*
	 * Regresa un Vector con las Unidades Mï¿½dicas sobre las que el usuario
	 * tiene acceso
	 */
	public void addUniMed(TVUsuario vUsuario, String cNumModulo) {
		vParametros = new TParametro(cNumModulo);
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuario = new Vector();
		TVGRLUMUsuario vGRLUMUsuario;

		try {
			dataSrcName = vParametros.getPropEspecifica("ConDBModulo");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = " select GRLUniMed.iCveUniMed, cDscUniMed,"
					+ "        GRLProceso.iCveProceso, cDscProceso "
					+ " from GRLUMUsuario "
					+ " inner join GRLProceso ON GRLUMUsuario.iCveProceso = GRLProceso.iCveProceso "
					+ " inner join GRLUniMed  ON GRLUMUsuario.iCveUniMed  = GRLUniMed.iCveUniMed   "
					+ " WHERE iCveUsuario = " + vUsuario.getICveusuario()
					+ " order by GRLUniMed.cDscUniMed ";
			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUniMed(lRSet.getInt(1));
				vGRLUMUsuario.setCDscUniMed(lRSet.getString(2));
				vGRLUMUsuario.setICveProceso(lRSet.getInt(3));
				vGRLUMUsuario.setCDscProceso(lRSet.getString(4));
				vUMUsuario.add(vGRLUMUsuario);
			}
			vUsuario.setVUnidades(vUMUsuario);
		} catch (Exception ex) {
			// System.out.println("accesoUsuario");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("accesoUsuario.Close");
			}
		}
	}

	/*
	 * Regresa la configuracion especï¿½fica del usuario con respecto de sus
	 * servicios y ramas
	 */
	public void addMedCons(TVUsuario vUsuario, String cNumModulo) {
		vParametros = new TParametro(cNumModulo);
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUsuMed = new Vector();
		TVGRLUSUMedicos vGRLUSUMedicos;

		try {
			dataSrcName = vParametros.getPropEspecifica("ConDBModulo");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select icveusuario, icveunimed, icveproceso, icvemodulo, GRLUSUMedicos.icveservicio, "
					+ "GRLUSUMedicos.icverama, cDscServicio, cDscRama "
					+ "from GRLUSUMedicos  "
					+ "join MEDServicio on GRLUSUMedicos.icveservicio = MEDServicio.icveservicio "
					+ "join MEDRama on GRLUSUMedicos.icveservicio = MEDRama.icveservicio "
					+ "and  GRLUSUMedicos.iCveRama = MEDRama.iCveRama "
					+ "where icveusuario = " + vUsuario.getICveusuario();

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUSUMedicos = new TVGRLUSUMedicos();
				vGRLUSUMedicos.setICveUsuario(lRSet.getInt(1));
				vGRLUSUMedicos.setICveUniMed(lRSet.getInt(2));
				vGRLUSUMedicos.setICveProceso(lRSet.getInt(3));
				vGRLUSUMedicos.setICveModulo(lRSet.getInt(4));
				vGRLUSUMedicos.setICveServicio(lRSet.getInt(5));
				vGRLUSUMedicos.setICveRama(lRSet.getInt(6));
				vGRLUSUMedicos.setCDscServicio(lRSet.getString(7));
				vGRLUSUMedicos.setCDscRama(lRSet.getString(8));
				vUsuMed.add(vGRLUSUMedicos);
			}
			vUsuario.setVUsuMedicos(vUsuMed);
		} catch (Exception ex) {
			// System.out.println("accesoUsuario");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("accesoUsuario.Close");
			}
		}
	}

	// //Este metodo no esta actualizado con respecto a produccion, revisar
	// clases y jsp en los que se utiliza -- 16 de febrero 2012
	public boolean cambioContrasenia(String cUsuario, String cNvaContrasenia,
			String cNumModulo, int id) {
		vParametros = new TParametro(cNumModulo);

		DbConnection dbConn = null;
		Connection conn = null;
		DbConnection dbConn2 = null;
		Connection conn2 = null;
		DbConnection dbConn3 = null;
		Connection conn3 = null;

		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		TVUsuario vUsuario = null;
		boolean lSuccess = true;

		String Hash = "";

		try {
			// Conexion ADMSEG
			dataSrcName = vParametros.getPropEspecifica("ConDB");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			// Conexion Medicina
			dataSrcName2 = vParametros.getPropEspecifica("ConDBModulo");
			dbConn2 = new DbConnection(dataSrcName2);
			conn2 = dbConn2.getConnection();
			conn2.setTransactionIsolation(2);

			// Conexion Ingresos
			/*
			 * dataSrcName3 = vParametros.getPropEspecifica("ConDBIng"); dbConn3
			 * = new DbConnection(dataSrcName3); conn3 =
			 * dbConn3.getConnection(); conn3.setTransactionIsolation(2);
			 */

			Hash = PasswordHash.createHash(cNvaContrasenia);
			String[] params = Hash.split(":");

			String lSQL = "update segusuario " + "set cPassword = '"
					+ cNvaContrasenia + "'," + " cPwdhsh = '"
					+ params[2].toString() + "'," + " cPwdslt = '"
					+ params[1].toString() + "' " + "where cUsuario = ? ";

			// System.out.println(lSQL);

			/*
			 * String lSQL = "update segusuario "+
			 * "set cPassword = '"+cNvaContrasenia+"'"+ "where cUsuario = ? ";
			 */

			// Actualizando en ADMSEG
			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.setString(1, cUsuario);
			lPStmt.executeUpdate();

			// Actualizando en Medprev
			// lPStmt = conn2.prepareStatement(lSQL);
			// lPStmt.setString(1, cUsuario);
			// lPStmt.executeUpdate();

			// Actualizando en Ingresos
			/*
			 * lPStmt = conn3.prepareStatement(lSQL); lPStmt.setString(1,
			 * cUsuario); lPStmt.executeUpdate();
			 */

			// Registrando cambio
			SEGAccPwd dSEGAccPwd = new SEGAccPwd();
			// int id = dSEGAccPwd.IdUser2(cUsuario);
			// System.out.println("ide permisos "+id);
			int inserta = 0;
			inserta = dSEGAccPwd.insert(null, id);
		} catch (Exception ex) {
			lSuccess = false;
			// System.out.println("cambioContrasenia");
			ex.printStackTrace();
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
				if (conn2 != null) {
					conn2.close();
				}
				if (conn3 != null) {
					conn3.close();
				}
				dbConn.closeConnection();
				dbConn2.closeConnection();
				dbConn3.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("accesoUsuario.Close");
			}
			return lSuccess;
		}
	}

	/**
	 * Metodo encargado de regresar un Arreglo con las unidades mï¿½dicas a las
	 * que tiene permiso el usuario, sin tomar en cuenta el proceso.
	 * 
	 * @param vUsuario
	 *            TVUsuario Informaciï¿½n del usuario.
	 */
	public void consUniMed(TVUsuario vUsuario) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuario = new Vector();
		TVDinRep vObjeto;
		try {
			dataSrcName = vParametros.getPropEspecifica("ConDBModulo");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);
			StringBuffer cSQL = new StringBuffer();
			cSQL.append(" select Distinct(UM.iCveUniMed), U.cDscUniMed ")
					.append("  from GRLUMUsuario UM ")
					.append("  inner join GRLUniMed  U on U.iCveUniMed = UM.iCveUniMed ")
					.append(" where UM.icveUsuario = ")
					.append(vUsuario.getICveusuario())
					.append("  order by U.cDscUniMed ");
			lPStmt = conn.prepareStatement(cSQL.toString());
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vObjeto = new TVDinRep();
				vObjeto.put("iClave", lRSet.getInt(1));
				vObjeto.put("cDescripcion", lRSet.getString(2));
				vUMUsuario.add(vObjeto);
			}
			vUsuario.setVUniMed(vUMUsuario);
		} catch (Exception ex) {
			// System.out.println("consUniMed");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("consUniMed.Close");
			}
		}
	}

	/**
	 * Metodo encargado de regresar un Arreglo con los mï¿½dulos de una unidad
	 * mï¿½dica en especï¿½fico.
	 * 
	 * @param vUsuario
	 *            TVUsuario Informaciï¿½n del usuario.
	 */
	public void consUMModulo(TVUsuario vUsuario) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuario = new Vector();
		TVDinRep vObjeto;
		try {
			dataSrcName = vParametros.getPropEspecifica("ConDBModulo");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);
			StringBuffer cSQL = new StringBuffer();
			cSQL.append(
					" select distinct UM.iCveUniMed, UM.iCveModulo, M.cDscModulo ")
					.append("  from GRLUsuMedicos UM ")
					.append("  inner join GRLModulo M on M.iCveUniMed = UM.iCveUniMed ")
					.append("                        and M.iCveModulo = UM.iCveModulo ")
					.append(" where UM.icveUsuario = ")
					.append(vUsuario.getICveusuario())
					.append("  order by UM.iCveUniMed, M.cDscModulo ");
			lPStmt = conn.prepareStatement(cSQL.toString());
			lRSet = lPStmt.executeQuery();
			while (lRSet.next()) {
				vObjeto = new TVDinRep();
				vObjeto.put("iCveUniMed", lRSet.getInt(1));
				vObjeto.put("iClave", lRSet.getInt(2));
				vObjeto.put("cDescripcion", lRSet.getString(3));
				vUMUsuario.add(vObjeto);
			}
			vUsuario.setVUMModulo(vUMUsuario);
		} catch (Exception ex) {
			// System.out.println("consUniMed");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("consUniMed.Close");
			}
		}
	}

	/**
	 * Metodo encargado de regresar un Arreglo con Procesos asignados en una
	 * Unidad Mï¿½dica y Mï¿½dulo en especï¿½fico.
	 * 
	 * @param vUsuario
	 *            TVUsuario Informaciï¿½n del usuario.
	 */
	public void consUMModProceso(TVUsuario vUsuario) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuario = new Vector();
		TVDinRep vObjeto = new TVDinRep();
		try {
			dataSrcName = vParametros.getPropEspecifica("ConDBModulo");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);
			StringBuffer cSQL = new StringBuffer();
			cSQL.append(
					" select distinct UM.iCveUniMed, UM.iCveModulo, UM.iCveProceso, P.cDscProceso ")
					.append("  from GRLUsuMedicos UM ")
					.append("  inner join GRLProceso P on P.iCveProceso = UM.iCveProceso ")
					.append(" where UM.icveUsuario = ")
					.append(vUsuario.getICveusuario())
					.append(" order by UM.iCveUniMed, UM.iCveModulo, UM.iCveProceso ");
			lPStmt = conn.prepareStatement(cSQL.toString());
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vObjeto = new TVDinRep();
				vObjeto.put("iCveUniMed", lRSet.getInt(1));
				vObjeto.put("iCveModulo", lRSet.getInt(2));
				vObjeto.put("iClave", lRSet.getInt(3));
				vObjeto.put("cDescripcion", lRSet.getString(4));
				vUMUsuario.add(vObjeto);
			}
			vUsuario.setVUMModProc(vUMUsuario);
		} catch (Exception ex) {
			// System.out.println("consUniMed");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("consUniMed.Close");
			}
		}
	}

	/**
	 * Metodo encargado de regresar un Arreglo con Procesos asignados en una
	 * Unidad Mï¿½dica y Mï¿½dulo en especï¿½fico.
	 * 
	 * @param vUsuario
	 *            TVUsuario Informaciï¿½n del usuario.
	 */
	public void consTodasDesc(TVUsuario vUsuario) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuario = new Vector();
		TVDinRep vObjeto = new TVDinRep();
		try {
			dataSrcName = vParametros.getPropEspecifica("ConDBModulo");
			dbConn = new DbConnection(dataSrcName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);
			StringBuffer cSQL = new StringBuffer();
			cSQL.append(" select distinct UM.iCveUniMed, U.cDscUniMed, ")
					.append("                 UM.iCveModulo, M.cDscModulo, ")
					.append("                 UM.iCveProceso, P.cDscProceso ")
					.append(" from GRLUsuMedicos UM ")
					.append(" inner join GRLUniMed U on U.iCveUniMed = UM.iCveUniMed ")
					.append(" inner join GRLModulo M on M.iCveUniMed = UM.iCveUniMed ")
					.append("                       and M.iCveModulo = UM.iCveModulo ")
					.append(" inner join GRLProceso P on P.iCveProceso = UM.iCveProceso ")
					.append(" where UM.icveUsuario = ")
					.append(vUsuario.getICveusuario())
					.append(" order by UM.iCveUniMed, M.cDscModulo, UM.iCveProceso ");

			lPStmt = conn.prepareStatement(cSQL.toString());
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vObjeto = new TVDinRep();
				vObjeto.put("iCveUniMed", lRSet.getInt(1));
				vObjeto.put("cDscUniMed", lRSet.getString(2));
				vObjeto.put("iCveModulo", lRSet.getInt(3));
				vObjeto.put("cDscModulo", lRSet.getString(4));
				vObjeto.put("iCveProceso", lRSet.getInt(5));
				vObjeto.put("cDscProceso", lRSet.getString(6));
				vUMUsuario.add(vObjeto);
			}
			vUsuario.setVTodosP(vUMUsuario);
		} catch (Exception ex) {
			// System.out.println("consTodasDesc");
			ex.printStackTrace();
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
			} catch (Exception ex2) {
				// System.out.println("consTodasDesc.Close");
			}
		}
	}

	public boolean checkUserPass(String cUsuario, String cPassword) {
		Vector data = null;
		DAOBase dAOBase = new DAOBase();
		dataSrcName = vParametros.getPropEspecifica("ConDB");
		try {
			String lSQL = "select CPWDSLT,CPWDHSH from segusuario where cUsuario = '"
					+ cUsuario + "'";

			data = dAOBase.FindByGeneric(lSQL,
					vParametros.getPropEspecifica("ConDB"));
			// .FindByGeneric("", lSQL, vParametros.getPropEspecifica("ConDB"));
			if (data.size() > 0) {
				builder = new StringBuilder();
				usrData = (TVDinRep) data.get(0);
				builder.append(PBKDF2_ITERATIONS).append(":")
						.append(usrData.get("CPWDSLT")).append(":")
						.append(usrData.get("CPWDHSH"));
				return PasswordHash.validatePassword(cPassword,
						builder.toString());
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
	}

	public boolean accesoUsuario2(String cUsuario, String cContrasenia,
			String cNumModulo) {
		vParametros = new TParametro(cNumModulo);
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		TVUsuario vUsuario = null;
		boolean lSuccess = false;

		try {

			if (checkUserPass(cUsuario, cContrasenia)) { // Validamos usuario
				lSuccess = true;
			}

		} catch (Exception ex) {
			// System.out.println("accesoUsuario");
			ex.printStackTrace();
		}
		return lSuccess;
	}

	/*
	 * public boolean SuspensionDeServicio() { vParametros = new
	 * TParametro("07"); DbConnection dbConn = null; Connection conn = null;
	 * PreparedStatement lPStmt = null; ResultSet lRSet = null; TVUsuario
	 * vUsuario = null; boolean lSuccess = false; int HoraInicioDeBloqueoUM =
	 * Integer.parseInt(vParametros.getPropEspecifica("HoraInicioDeBloqueoUM"));
	 * int HoraFinDeBloqueoUM =
	 * Integer.parseInt(vParametros.getPropEspecifica("HoraFinDeBloqueoUM"));
	 * //int hora = calendar.get(Calendar.HOUR_OF_DAY);
	 * System.out.println(hora);
	 * 
	 * try { /* if (checkUserPass(cUsuario, cContrasenia)) { // Validamos
	 * usuario lSuccess = true; }
	 */

	/*
	 * } catch (Exception ex) { // System.out.println("accesoUsuario");
	 * ex.printStackTrace(); } return lSuccess; }
	 */

}
