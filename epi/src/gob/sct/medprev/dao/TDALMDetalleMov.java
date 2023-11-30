package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMDetalleMov DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame
 * @version 1.0
 */

public class TDALMDetalleMov extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMDetalleMov() {
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMDetalleMov = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMDetalleMov vALMDetalleMov;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iAnio,  " + " iCveAlmacen,  "
					+ " iCveMovimiento,  " + " iCveLote,  " + " dUnidades  "
					+ " from ALMDetalleMov " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMDetalleMov = new TVALMDetalleMov();
				vALMDetalleMov.setiAnio(rset.getInt(1));
				vALMDetalleMov.setiCveAlmacen(rset.getInt(2));
				vALMDetalleMov.setiCveMovimiento(rset.getInt(3));
				vALMDetalleMov.setiCveLote(rset.getInt(4));
				vALMDetalleMov.setdUnidades(rset.getDouble(5));
				vcALMDetalleMov.addElement(vALMDetalleMov);
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
			return vcALMDetalleMov;
		}
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMDetalleMov = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMDetalleMov vALMDetalleMov;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMDetalleMov.iAnio,  "
					+ " ALMDetalleMov.iCveAlmacen,  "
					+ " ALMDetalleMov.iCveMovimiento,  "
					+ " ALMDetalleMov.iCveLote, " + " ALMDetalleMov.dUnidades "
					+ " from ALMDetalleMov " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMDetalleMov = new TVALMDetalleMov();
				vALMDetalleMov.setiAnio(rset.getInt(1));
				vALMDetalleMov.setiCveAlmacen(rset.getInt(2));
				vALMDetalleMov.setiCveMovimiento(rset.getInt(3));
				vALMDetalleMov.setiCveLote(rset.getInt(4));
				vALMDetalleMov.setdUnidades(rset.getDouble(5));
				vcALMDetalleMov.addElement(vALMDetalleMov);
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
			return vcALMDetalleMov;
		}
	}

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
			TVALMDetalleMov VALMDetalleMov = (TVALMDetalleMov) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMDetalleMov" + " set dUnidades = ? ,"
					+ " where iAnio = ? " + "   and iCveAlmacen = ? "
					+ "   and iCveMovimiento = ? " + "   and iCveLote = ? "
					+ "";
			pstmt = conn.prepareStatement(cSQL);

			if (new Double(VALMDetalleMov.getdUnidades()) == null)
				pstmt.setNull(1, Types.DOUBLE);
			else
				pstmt.setDouble(1, VALMDetalleMov.getdUnidades());

			pstmt.setInt(2, VALMDetalleMov.getiAnio());
			pstmt.setInt(3, VALMDetalleMov.getiCveAlmacen());
			pstmt.setInt(4, VALMDetalleMov.getiCveMovimiento());
			pstmt.setInt(5, VALMDetalleMov.getiCveLote());

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
			TVALMDetalleMov vALMDetalleMov = (TVALMDetalleMov) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from ALMDetalleMov " + " where iAnio = ?     "
					+ "   and iCveAlmacen = ?      "
					+ "   and iCveMovimiento = ?      "
					+ "   and iCveLote = ?      " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMDetalleMov.getiAnio());
			pstmt.setInt(2, vALMDetalleMov.getiCveAlmacen());
			pstmt.setInt(3, vALMDetalleMov.getiCveMovimiento());
			pstmt.setInt(4, vALMDetalleMov.getiCveLote());
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
				warn("delete.closeALMDetalleMov", ex2);
			}
			return cClave;
		}
	}

	public Object deleteCustomWhere(Connection conGral, String cSQL)
			throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

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
				warn("delete.closeALMDetalleMov", ex2);
			}
			return cClave;
		}
	}

	public Object insert(Connection conGral, Object obj) throws DAOException {
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVALMDetalleMov vALMDetalleMov = (TVALMDetalleMov) obj;
			cSQL = " insert into ALMDetalleMov( " + " iAnio, "
					+ " iCveAlmacen, " + " iCveMovimiento, " + " iCveLote, "
					+ " dUnidades " + " ) values (?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			pstmt.setInt(1, vALMDetalleMov.getiAnio());
			pstmt.setInt(2, vALMDetalleMov.getiCveAlmacen());
			pstmt.setInt(3, vALMDetalleMov.getiCveMovimiento());
			pstmt.setInt(4, vALMDetalleMov.getiCveLote());

			if (new Double(vALMDetalleMov.getdUnidades()) == null)
				pstmt.setNull(5, Types.FLOAT);
			else
				pstmt.setDouble(5, vALMDetalleMov.getdUnidades());

			if (pstmt.executeUpdate() > 0)
				cClave = "" + vALMDetalleMov.getiCveAlmacen();
			else
				cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (conGral == null)
					conn.rollback();
			} catch (Exception ex1) {
				warn("insert", ex1);
			}
			warn("insert", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conGral == null)
					if (!conn.isClosed()) {
						conn.close();
						dbConn.closeConnection();
					}
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

	public boolean MakeTransaction(int iAlmacen, int iAnio, int iNumSolic,
			int iCveUsuario) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean bReturn = false;
		String cSQL = "";
		int iAction = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			TDALMArtxAlm DALMArtxAlm = new TDALMArtxAlm();
			TDALMLote DALMLote = new TDALMLote();
			TDALMMovimiento DALMMovimiento = new TDALMMovimiento();
			TDALMSolicSumin DALMSolicSumin = new TDALMSolicSumin();
			TDALMSumArt DALMSumArt = new TDALMSumArt();

			/*********** Codigo *****************/

			Vector vGuardar = new Vector();
			Vector vMovimientos = new Vector();
			Vector vExistencias = new Vector();
			Vector vExisLotes = new Vector();
			Vector vLotesAct = new Vector();
			Vector vDetalleMovIns = new Vector();
			TFechas Fecha = new TFechas();
			int ivMovimiento = 0;
			int ivLote = 0;
			double dvExistencia = 0;

			vExistencias = DALMArtxAlm.FindByAll(" where iCveAlmacen = "
					+ iAlmacen);
			vExisLotes = DALMLote.FindByAll(" where iCveAlmacen = " + iAlmacen
					+ "   and dtCaducidad >= '" + Fecha.TodaySQL() + "'"
					+ "   and dtAgotado   is null "
					+ " order by iCveAlmacen, iCveArticulo, dtCaducidad asc ");
			ivMovimiento = DALMMovimiento.FindMaxMov(" where iAnio       = "
					+ iAnio + " and   iCveAlmacen = " + iAlmacen);

			/*
			 * if (!vMovimientos.isEmpty()) ivMovimiento =
			 * ((TVALMMovimiento)vMovimientos.get(0)).getiCveMovimiento() + 1;
			 * else ivMovimiento = 1;
			 */

			vGuardar = DALMSumArt.FindByAll(" where ALMSumArt.iAnio = " + iAnio
					+ "   and ALMSumArt.iCveAlmacen = " + iAlmacen
					+ "   and ALMSumArt.iCveSolicSum = " + iNumSolic, "");

			if (!vGuardar.isEmpty()) {
				for (int i = 0; i < vGuardar.size(); i++) {
					TVALMSumArt VALMSumArt = new TVALMSumArt();
					VALMSumArt = (TVALMSumArt) vGuardar.get(i);

					// Datos del Movimiento de Salida del Almacén.
					TVALMMovimiento VALMMovimiento = new TVALMMovimiento();
					VALMMovimiento.setiAnio(iAnio);
					VALMMovimiento.setiCveAlmacen(iAlmacen);
					VALMMovimiento.setiCveMovimiento(ivMovimiento);
					VALMMovimiento.setiCveSolicSum(iNumSolic);
					VALMMovimiento
							.setiCveArticulo(VALMSumArt.getICveArticulo());
					VALMMovimiento.setiCveTpoMovimiento(2); // Movimiento de
															// Salida
					VALMMovimiento.setiCveConcepto(1);
					VALMMovimiento.setdUnidades(VALMSumArt.getDUnidAutor());
					VALMMovimiento.setdtMovimiento(Fecha.TodaySQL());
					VALMMovimiento.setiCveUsuario(iCveUsuario);
					VALMMovimiento
							.setcObservacion(VALMSumArt.getCObservacion());
					VALMMovimiento.setlPNC(0); // Producto no Conforme.
					// Valores de las Existencias Generales.
					TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
					VALMArtxAlm.setiCveAlmacen(iAlmacen);
					VALMArtxAlm.setiCveArticulo(VALMSumArt.getICveArticulo());
					if (!vExistencias.isEmpty()) {
						for (int j = 0; j < vExistencias.size(); j++) {
							TVALMArtxAlm VALMArtxAlmExistencia = new TVALMArtxAlm();
							VALMArtxAlmExistencia = (TVALMArtxAlm) vExistencias
									.get(j);
							if (VALMArtxAlmExistencia.getiCveArticulo() == VALMSumArt
									.getICveArticulo())
								dvExistencia = VALMArtxAlmExistencia
										.getdExistencia();
						}
					}
					VALMArtxAlm.setdExistencia(dvExistencia
							- VALMSumArt.getDUnidAutor());

					// Valores de las Existencias de los Lotes.
					if (!vExisLotes.isEmpty()) {
						double dvAcumula = 0;
						for (int j = 0; j < vExisLotes.size(); j++) {
							TVALMLote VALMLoteExistencia = new TVALMLote();
							VALMLoteExistencia = (TVALMLote) vExisLotes.get(j);
							if (VALMLoteExistencia.getiCveArticulo() == VALMSumArt
									.getICveArticulo()) {/*
														 * System.out.println(
														 * "dvAcumula: "
														 * +dvAcumula);
														 * System.out.println(
														 * "getDUnidAutor: "
														 * +VALMSumArt
														 * .getDUnidAutor());
														 * System.out.println(
														 * "getICveArticulo: "
														 * +VALMSumArt
														 * .getICveArticulo());
														 */
								if (dvAcumula < VALMSumArt.getDUnidAutor()) {
									double dvFaltan = VALMSumArt
											.getDUnidAutor() - dvAcumula;
									double dvAplica = 0;
									double dvMovimiento = 0;
									if (dvFaltan >= VALMLoteExistencia
											.getdUnidades()) {
										dvAcumula = dvAcumula
												+ VALMLoteExistencia
														.getdUnidades();
										dvMovimiento = VALMLoteExistencia
												.getdUnidades();
										dvAplica = 0;
									}
									if (dvFaltan < VALMLoteExistencia
											.getdUnidades()) {
										dvAcumula = dvAcumula + dvFaltan;
										dvMovimiento = dvFaltan;
										dvAplica = VALMLoteExistencia
												.getdUnidades() - dvFaltan;
									}

									TVALMLote VALMLote = new TVALMLote();
									VALMLote.setiCveAlmacen(iAlmacen);
									VALMLote.setiCveArticulo(VALMSumArt
											.getICveArticulo());
									VALMLote.setiCveLote(VALMLoteExistencia
											.getiCveLote());
									VALMLote.setdUnidades(dvAplica);
									if (dvAplica == 0)
										VALMLote.setdtAgotado(Fecha.TodaySQL());
									vLotesAct.add(VALMLote);

									// Datos del Movimiento de Salida del
									// Almacen con Lote.
									TVALMDetalleMov VALMDetalleMov = new TVALMDetalleMov();
									VALMDetalleMov.setiAnio(iAnio);
									VALMDetalleMov.setiCveAlmacen(iAlmacen);
									VALMDetalleMov
											.setiCveMovimiento(ivMovimiento);
									// System.out.println("ivMovimiento: "+ivMovimiento);
									VALMDetalleMov
											.setiCveLote(VALMLoteExistencia
													.getiCveLote()); // Lote del
																		// Articulo.
									VALMDetalleMov.setdUnidades(dvMovimiento);
									vDetalleMovIns.add(VALMDetalleMov);
								}
							}
						}
					}

					// Inserción del Movimiento de Salida del Almacén.
					// DALMMovimiento.insert(null,VALMMovimiento);
					cSQL = " insert into ALMMovimiento( " + " iAnio, "
							+ " iCveAlmacen, " + " iCveMovimiento, "
							+ " iCveSolicSum, " + " iCveArticulo,"
							+ " iCveTpoMovimiento," + " iCveConcepto,"
							+ " dUnidades," + " iCveUsuario,"
							+ " dtMovimiento," + " cObservacion," + " lPNC "
							+ " ) values (?,?,?,?,?,?,?,?,?,?,?,?)";

					pstmt = conn.prepareStatement(cSQL);

					// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE
					// LA TABLA
					pstmt.setInt(1, VALMMovimiento.getiAnio());
					pstmt.setInt(2, VALMMovimiento.getiCveAlmacen());
					pstmt.setInt(3, VALMMovimiento.getiCveMovimiento());

					if (new Integer(VALMMovimiento.getiCveSolicSum()) == null)
						pstmt.setNull(4, Types.INTEGER);
					else
						pstmt.setInt(4, VALMMovimiento.getiCveSolicSum());

					if (new Integer(VALMMovimiento.getiCveArticulo()) == null)
						pstmt.setNull(5, Types.INTEGER);
					else
						pstmt.setInt(5, VALMMovimiento.getiCveArticulo());

					if (new Integer(VALMMovimiento.getiCveTpoMovimiento()) == null)
						pstmt.setNull(6, Types.INTEGER);
					else
						pstmt.setInt(6, VALMMovimiento.getiCveTpoMovimiento());

					if (new Integer(VALMMovimiento.getiCveConcepto()) == null)
						pstmt.setNull(7, Types.INTEGER);
					else
						pstmt.setInt(7, VALMMovimiento.getiCveConcepto());

					if (new Double(VALMMovimiento.getdUnidades()) == null)
						pstmt.setNull(8, Types.FLOAT);
					else
						pstmt.setDouble(8, VALMMovimiento.getdUnidades());

					if (new Integer(VALMMovimiento.getiCveUsuario()) == null)
						pstmt.setNull(9, Types.INTEGER);
					else
						pstmt.setInt(9, VALMMovimiento.getiCveUsuario());

					if (VALMMovimiento.getdtMovimiento() == null)
						pstmt.setNull(10, Types.DATE);
					else
						pstmt.setDate(10, VALMMovimiento.getdtMovimiento());

					if (VALMMovimiento.getcObservacion() == null)
						pstmt.setNull(11, Types.VARCHAR);
					else
						pstmt.setString(11, VALMMovimiento.getcObservacion()
								.toUpperCase().trim());

					if (new Integer(VALMMovimiento.getlPNC()) == null)
						pstmt.setNull(12, Types.INTEGER);
					else
						pstmt.setInt(12, VALMMovimiento.getlPNC());

					iAction = pstmt.executeUpdate();

					// System.out.println("iAction 1: "+iAction);

					if (pstmt != null)
						pstmt.close();

					ivMovimiento = ivMovimiento + 1;
					// Actualización de Existencias Generales.
					// DALMArtxAlm.updExistencias(null,VALMArtxAlm);

					cSQL = " update ALMArtxAlm " + " set dExistencia = ? "
							+ " where iCveAlmacen = ? "
							+ "   and iCveArticulo = ?";
					pstmt = conn.prepareStatement(cSQL);

					if (new Double(VALMArtxAlm.getdExistencia()) == null)
						pstmt.setNull(1, Types.FLOAT);
					else
						pstmt.setDouble(1, VALMArtxAlm.getdExistencia());

					pstmt.setInt(2, VALMArtxAlm.getiCveAlmacen());
					pstmt.setInt(3, VALMArtxAlm.getiCveArticulo());

					// /System.out.println("getiCveArticulo: "+VALMArtxAlm.getiCveArticulo());
					iAction = pstmt.executeUpdate();

					// System.out.println("iAction 2 Error: "+iAction);
					if (pstmt != null)
						pstmt.close();

					// Actualización de Movimientos con Lotes.
					if (!vDetalleMovIns.isEmpty()) {
						cSQL = " insert into ALMDetalleMov( " + " iAnio, "
								+ " iCveAlmacen, " + " iCveMovimiento, "
								+ " iCveLote, " + " dUnidades "
								+ " ) values (?,?,?,?,?)";
						// System.out.println("vDetalleMovIns: "+vDetalleMovIns.size());
						for (int iii = 0; iii < vDetalleMovIns.size(); iii++) {
							TVALMDetalleMov VALMDetalleMov = new TVALMDetalleMov();
							VALMDetalleMov = (TVALMDetalleMov) vDetalleMovIns
									.get(iii);

							// DALMDetalleMov.insert(null,VALMDetalleMov);

							pstmt = conn.prepareStatement(cSQL);/*
																 * 
																 * System.out.
																 * println
																 * ("getdUnidades: "
																 * +
																 * VALMDetalleMov
																 * .
																 * getdUnidades(
																 * ));
																 * System.out
																 * .println
																 * ("getiAnio: "
																 * +
																 * VALMDetalleMov
																 * .getiAnio());
																 * System
																 * .out.println(
																 * "getiCveAlmacen: "
																 * +
																 * VALMDetalleMov
																 * .
																 * getiCveAlmacen
																 * ());
																 * System.out
																 * .println(
																 * "getiCveMovimiento: "
																 * +
																 * VALMDetalleMov
																 * .
																 * getiCveMovimiento
																 * ());
																 * System.out
																 * .println
																 * ("getiCveLote: "
																 * +
																 * VALMDetalleMov
																 * .
																 * getiCveLote()
																 * );
																 */

							// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL
							// CONSECUTIVO DE LA TABLA
							pstmt.setInt(1, VALMDetalleMov.getiAnio());
							pstmt.setInt(2, VALMDetalleMov.getiCveAlmacen());
							pstmt.setInt(3, VALMDetalleMov.getiCveMovimiento());
							pstmt.setInt(4, VALMDetalleMov.getiCveLote());

							if (new Double(VALMDetalleMov.getdUnidades()) == null)
								pstmt.setNull(5, Types.FLOAT);
							else
								pstmt.setDouble(5,
										VALMDetalleMov.getdUnidades());

							iAction = pstmt.executeUpdate();

							if (pstmt != null)
								pstmt.close();
						}
						// System.out.println("iAction 3: "+iAction);
					}
					vDetalleMovIns = new Vector();
					if (!vLotesAct.isEmpty()) {
						cSQL = "update ALMLote           "
								+ " set dUnidades      = ? ,"
								+ "     dtAgotado      = ?  "
								+ " where iCveAlmacen  = ?  "
								+ "   and iCveArticulo = ?  "
								+ "   and iCveLote     = ?  " + "";

						for (int ii = 0; ii < vLotesAct.size(); ii++) {
							TVALMLote VALMLote = new TVALMLote();
							VALMLote = (TVALMLote) vLotesAct.get(ii);
							// DALMLote.updExistencias(null,VALMLote);

							pstmt = conn.prepareStatement(cSQL);

							if (new Double(VALMLote.getdUnidades()) == null)
								pstmt.setNull(1, Types.FLOAT);
							else
								pstmt.setDouble(1, VALMLote.getdUnidades());
							if (VALMLote.getdtAgotado() == null)
								pstmt.setNull(2, Types.DATE);
							else
								pstmt.setDate(2, VALMLote.getdtAgotado());

							pstmt.setInt(3, VALMLote.getiCveAlmacen());
							pstmt.setInt(4, VALMLote.getiCveArticulo());
							pstmt.setInt(5, VALMLote.getiCveLote());

							iAction = pstmt.executeUpdate();

							if (pstmt != null)
								pstmt.close();
						}
						// System.out.println("iAction 4: "+iAction);
					}
				}
				// Valores para la Modificación de la Situación de la Solicitud.
				TVALMSolicSumin VALMSolicSumin = new TVALMSolicSumin();
				VALMSolicSumin.setIAnio(iAnio);
				VALMSolicSumin.setICveAlmacen(iAlmacen);
				VALMSolicSumin.setICveSolicSum(iNumSolic);
				VALMSolicSumin.setDtSuministro(Fecha.TodaySQL());
				VALMSolicSumin.setLSuministrada(1); // Solicitud Suministrada.
				// Modificación de la Situación de la Solicitud.

				// DALMSolicSumin.updSumistrada(null,VALMSolicSumin);
				/* Pendiente */
				cSQL = " update ALMSolicSumin" + " set dtSuministro  = ? ,"
						+ "     lSuministrada = ?  " + " where iAnio = ? "
						+ " and iCveAlmacen = ?" + " and iCveSolicSum = ?";
				pstmt = conn.prepareStatement(cSQL);

				if (VALMSolicSumin.getDtSuministro() == null)
					pstmt.setNull(1, Types.DATE);
				else
					pstmt.setDate(1, VALMSolicSumin.getDtSuministro());

				if (new Integer(VALMSolicSumin.getLSuministrada()) == null)
					pstmt.setNull(2, Types.INTEGER);
				else
					pstmt.setInt(2, VALMSolicSumin.getLSuministrada());

				pstmt.setInt(3, VALMSolicSumin.getIAnio());
				pstmt.setInt(4, VALMSolicSumin.getICveAlmacen());
				pstmt.setInt(5, VALMSolicSumin.getICveSolicSum());

				iAction = pstmt.executeUpdate();

				if (iAction != 0)
					bReturn = true;

				// System.out.println("iAction 5: "+iAction);
			}
			/*********** Codigo ****************/
			conn.commit();
			// conn.rollback();
		} catch (Exception ex) {
			bReturn = false;
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
			}
		}
		return bReturn;
	}

}
