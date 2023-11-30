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
import javax.swing.*;

/**
 * <p>
 * Title: Data Acces Object de INVPersonal DAO
 * </p>
 * <p>
 * Description: DAO Tabla INVPersonal
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDGenExamenes extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGenExamenes() {
	}

	/**
	 * Metodo Insert
	 */
	public Object genera(TVEXPExamAplica vExamAplica, int iCvePuesto,
			int iCveMdoTrans, String cGenero) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement psread = null, psExamApl = null, psExamCat = null, psExamGrupo = null, psExamPuesto = null, psEXPServicio = null, psEXPRama = null, psEXPResultado = null, psEXPDictamenServ = null;
		ResultSet rsRead = null;
		String cClave = "";
		boolean lError = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int i = 0;
			int iCveGrupo = 0, iCveCategoria = 0, iCvePerfil = 0;
			int iMtvoXInvAcc = Integer.parseInt(
					VParametros.getPropEspecifica("MtvoXInvAcc"), 10);

			String cSQL = "";

			// Generar �ltimo Examen EXPExamAplica
			cSQL = "select max(iNumExamen) " + "from expexamaplica "
					+ "where icveexpediente = "
					+ vExamAplica.getICveExpediente();
			psread = conn.prepareStatement(cSQL);
			rsRead = psread.executeQuery();

			while (rsRead.next()) {
				vExamAplica.setINumExamen(rsRead.getInt(1) + 1);
				cClave = "" + vExamAplica.getINumExamen();
			}
			if (rsRead != null) {
				rsRead.close();
			}
			if (psread != null) {
				psread.close();
			}

			cSQL = "insert into EXPExamAplica(" + "icveexpediente,"
					+ "inumexamen," + "iCveUniMed," + "iCvePersonal,"
					+ "iCveModulo," + "dtSolicitado," + "iCveUsuSolicita,"
					+ "iCveProceso," + "tInicio,cConstancia"
					+ ")values(?,?,?,?,?,?,?,?,{FN CURTIME()},?)";
			psExamApl = conn.prepareStatement(cSQL);
			psExamApl.setInt(1, vExamAplica.getICveExpediente());
			psExamApl.setInt(2, vExamAplica.getINumExamen());
			psExamApl.setInt(3, vExamAplica.getICveUniMed());
			psExamApl.setInt(4, vExamAplica.getICvePersonal());
			psExamApl.setInt(5, vExamAplica.getICveModulo());
			psExamApl.setDate(6,
					new java.sql.Date(new java.util.Date().getTime()));
			psExamApl.setInt(7, vExamAplica.getICveUsuSolicita());
			psExamApl.setInt(8, vExamAplica.getICveProceso());
			psExamApl.setString(9, "TDGenExamenes - genera");
			psExamApl.executeUpdate();

			// Obtener Grupo y Categoria
			cSQL = "select iCveGrupo, iCveCategoria " + "from GRLPuesto "
					+ "where iCveMdoTrans = " + iCveMdoTrans
					+ " and  iCvePuesto = " + iCvePuesto;

			psread = conn.prepareStatement(cSQL);
			rsRead = psread.executeQuery();

			while (rsRead.next()) {
				iCveGrupo = rsRead.getInt(1);
				iCveCategoria = rsRead.getInt(2);
			}
			if (rsRead != null) {
				rsRead.close();
			}
			if (psread != null) {
				psread.close();
			}

			// Obtener Perfil
			cSQL = "select iCvePerfil " + "from medperfilmc "
					+ "where icvemdotrans = " + iCveMdoTrans
					+ " and icvegrupo = " + iCveGrupo + " and lvigente = 1 ";

			psread = conn.prepareStatement(cSQL);
			rsRead = psread.executeQuery();

			while (rsRead.next()) {
				iCvePerfil = rsRead.getInt(1);
			}
			if (rsRead != null) {
				rsRead.close();
			}
			if (psread != null) {
				psread.close();
			}
			if (iCvePerfil == 0 || iCveGrupo == 0 || iCveCategoria == 0) {
				throw new DAOException("");
			}

			// Genera EXPExamCat
			cSQL = "insert into EXPExamCat(" + "icveexpediente,"
					+ "inumexamen," + "iCveMdoTrans," + "iCveCategoria,"
					+ "iCveMotivo," + "lDictamen," + "cRefAlfaNum"
					+ ")values(?,?,?,?,?,?,?)";
			psExamCat = conn.prepareStatement(cSQL);
			psExamCat.setInt(1, vExamAplica.getICveExpediente());
			psExamCat.setInt(2, vExamAplica.getINumExamen());
			psExamCat.setInt(3, iCveMdoTrans);
			psExamCat.setInt(4, iCveCategoria);
			psExamCat.setInt(5, iMtvoXInvAcc);
			psExamCat.setInt(6, 0);
			psExamCat.setString(7, "");
			psExamCat.executeUpdate();

			// Genera EXPExamGrupo
			cSQL = "insert into EXPExamGrupo(" + "icveexpediente,"
					+ "inumexamen," + "iCveMdoTrans," + "iCveGrupo,"
					+ "iCvePerfil" + ")values(?,?,?,?,?)";
			psExamGrupo = conn.prepareStatement(cSQL);
			psExamGrupo.setInt(1, vExamAplica.getICveExpediente());
			psExamGrupo.setInt(2, vExamAplica.getINumExamen());
			psExamGrupo.setInt(3, iCveMdoTrans);
			psExamGrupo.setInt(4, iCveGrupo);
			psExamGrupo.setInt(5, iCvePerfil);
			psExamGrupo.executeUpdate();

			// Genera EXPExamPuesto
			cSQL = "insert into EXPExamPuesto(" + "icveexpediente,"
					+ "inumexamen," + "iCveMdoTrans," + "iCvePuesto"
					+ ")values(?,?,?,?)";
			psExamPuesto = conn.prepareStatement(cSQL);
			psExamPuesto.setInt(1, vExamAplica.getICveExpediente());
			psExamPuesto.setInt(2, vExamAplica.getINumExamen());
			psExamPuesto.setInt(3, iCveMdoTrans);
			psExamPuesto.setInt(4, iCvePuesto);
			psExamPuesto.executeUpdate();

			// Obtener MEDSintExamen (vcServicios, vcRamas, vcSintExamen)
			cSQL = " select MEDSintExamen.iCveServicio, MEDSintExamen.iCveRama, MEDSintExamen.iCveSintoma "
					+ "from MEDSintExamen  "
					+ "join MEDServicio on MEDSintExamen.icveservicio = MEDServicio.icveservicio "
					+ "and MEDServicio.lactivo = 1 "
					+ "join MEDRama on MEDSintExamen.icveservicio = MEDRama.icveservicio "
					+ "and MEDSintExamen.icverama = MEDRama.icverama "
					+ "and MEDRama.lactivo = 1 "
					+ "join MEDSintomas on MEDSintExamen.icveservicio = MEDSintomas.icveservicio "
					+ "and MEDSintExamen.icverama = MEDSintomas.icverama "
					+ "and MEDSintExamen.icvesintoma = MEDSintomas.icvesintoma "
					+ "and MEDSintomas.lactivo = 1 "
					+ "and (MEDSintomas.cGenero = '"
					+ cGenero
					+ "' OR MEDSintomas.cGenero='A')"
					+ " where iCveProceso = "
					+ vExamAplica.getICveProceso()
					+ " and icvemotivo = "
					+ iMtvoXInvAcc
					+ " and icvemdotrans = "
					+ iCveMdoTrans
					+ " order by MEDSintExamen.icveservicio, MEDSintExamen.icverama, MEDSintExamen.icvesintoma ";

			psread = conn.prepareStatement(cSQL);
			rsRead = psread.executeQuery();
			Vector vcMedSintExam = new Vector(), vcServicios = new Vector(), vcRamas = new Vector();
			TVMEDSintExamen vMSE = null;
			int iCveServicio = 0, iCveRama = 0;

			while (rsRead.next()) {
				vMSE = new TVMEDSintExamen();
				vMSE.setICveServicio(rsRead.getInt(1));
				vMSE.setICveRama(rsRead.getInt(2));
				vMSE.setICveSintoma(rsRead.getInt(3));

				if (iCveServicio != vMSE.getICveServicio()) {
					iCveServicio = vMSE.getICveServicio();
					iCveRama = vMSE.getICveRama();
					vcServicios.add(vMSE);
					vcRamas.add(vMSE);
				} else {
					if (iCveRama != vMSE.getICveRama()) {
						iCveRama = vMSE.getICveRama();
						vcRamas.add(vMSE);
					}
				}
				vcMedSintExam.add(vMSE);
			}

			if (rsRead != null) {
				rsRead.close();
			}
			if (psread != null) {
				psread.close();
			}

			// Generar EXPServicio y EXPDictamenServ
			cSQL = "insert into EXPServicio(" + "icveexpediente,"
					+ "inumexamen," + "iCveServicio," + "lConcluido,"
					+ "iCveUsuAplica," + "dtinicio," + "dtfin"
					+ ")values(?,?,?,0,0,?,?)";
			psEXPServicio = conn.prepareStatement(cSQL);

			cSQL = "insert into EXPDictamenServ(" + "icveexpediente,"
					+ "inumexamen," + "iCveMdoTrans," + "iCveCategoria,"
					+ "iCveServicio," + "lDictamen" + ")values(?,?,?,?,?,0)";
			psEXPDictamenServ = conn.prepareStatement(cSQL);

			for (i = 0; i < vcServicios.size(); i++) {
				vMSE = (TVMEDSintExamen) vcServicios.get(i);
				psEXPServicio.setInt(1, vExamAplica.getICveExpediente());
				psEXPServicio.setInt(2, vExamAplica.getINumExamen());
				psEXPServicio.setInt(3, vMSE.getICveServicio());
				psEXPServicio.setDate(4,
						new java.sql.Date(new java.util.Date().getTime()));
				psEXPServicio.setDate(5,
						new java.sql.Date(new java.util.Date().getTime()));

				psEXPServicio.executeUpdate();

				psEXPDictamenServ.setInt(1, vExamAplica.getICveExpediente());
				psEXPDictamenServ.setInt(2, vExamAplica.getINumExamen());
				psEXPDictamenServ.setInt(3, iCveMdoTrans);
				psEXPDictamenServ.setInt(4, iCveCategoria);
				psEXPDictamenServ.setInt(5, vMSE.getICveServicio());

				psEXPDictamenServ.executeUpdate();
			}

			// Generar EXPRama
			cSQL = "insert into EXPRama(" + "icveexpediente," + "inumexamen,"
					+ "iCveServicio," + "iCveRama," + "lConcluido,"
					+ "iCveUsuAplica" + ")values(?,?,?,?,0,0)";
			psEXPRama = conn.prepareStatement(cSQL);

			for (i = 0; i < vcRamas.size(); i++) {
				vMSE = (TVMEDSintExamen) vcRamas.get(i);
				psEXPRama.setInt(1, vExamAplica.getICveExpediente());
				psEXPRama.setInt(2, vExamAplica.getINumExamen());
				psEXPRama.setInt(3, vMSE.getICveServicio());
				psEXPRama.setInt(4, vMSE.getICveRama());
				psEXPRama.executeUpdate();
			}

			// Generar EXPResultado
			cSQL = "insert into EXPResultado(" + "icveexpediente,"
					+ "inumexamen," + "iCveServicio," + "iCveRama,"
					+ "iCveSintoma" + ")values(?,?,?,?,?)";
			psEXPResultado = conn.prepareStatement(cSQL);

			for (i = 0; i < vcMedSintExam.size(); i++) {
				vMSE = (TVMEDSintExamen) vcMedSintExam.get(i);
				psEXPResultado.setInt(1, vExamAplica.getICveExpediente());
				psEXPResultado.setInt(2, vExamAplica.getINumExamen());
				psEXPResultado.setInt(3, vMSE.getICveServicio());
				psEXPResultado.setInt(4, vMSE.getICveRama());
				psEXPResultado.setInt(5, vMSE.getICveSintoma());
				psEXPResultado.executeUpdate();
			}

			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("genera", ex1);
			}
			warn("genera", ex);
			lError = true;
		} finally {
			try {
				if (psread != null) {
					psread.close();
				}
				if (psExamApl != null) {
					psExamApl.close();
				}
				if (psExamCat != null) {
					psExamCat.close();
				}
				if (psExamGrupo != null) {
					psExamGrupo.close();
				}
				if (psExamPuesto != null) {
					psExamPuesto.close();
				}
				if (psEXPServicio != null) {
					psEXPServicio.close();
				}
				if (psEXPRama != null) {
					psEXPRama.close();
				}
				if (psEXPResultado != null) {
					psEXPResultado.close();
				}
				if (psEXPDictamenServ != null) {
					psEXPDictamenServ.close();
				}
				if (rsRead != null) {
					rsRead.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("genera.close", ex2);
			}

			if (lError) {
				throw new DAOException("");
			}

			return cClave;
		}
	}
}