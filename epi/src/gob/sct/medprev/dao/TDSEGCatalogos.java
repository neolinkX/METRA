package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de SEGUsuario DAO
 * </p>
 * <p>
 * Description: DAO de la entidad SEGUsuario que es replica de ADMSEG (Solo
 * Lectura)
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

public class TDSEGCatalogos extends DAOBase {
	private TParametro VParametros = new TParametro("07");

	/**
	 * Metodo update
	 */
	public boolean update() throws DAOException {
		String snADMSEG = VParametros.getPropEspecifica("ConDB"), snMODULO = VParametros
				.getPropEspecifica("ConDBModulo");
		DbConnection dcADMSEG = null, dcMODULO = null;
		Connection cxADMSEG = null, cxMODULO = null;
		PreparedStatement psRead = null, psInsPais = null, psDelPais = null, psInsEdo = null, psDelEdo = null, psInsMun = null, psDelMun = null;
		ResultSet rset = null;
		Vector vcPais = new Vector(), vcEdo = new Vector(), vcMun = new Vector();
		boolean lSuccess = true;
		int i = 0;
		try {
			dcADMSEG = new DbConnection(snADMSEG);
			cxADMSEG = dcADMSEG.getConnection();

			dcMODULO = new DbConnection(snMODULO);
			cxMODULO = dcMODULO.getConnection();

			String cSQL = "";

			cxADMSEG.setAutoCommit(false);
			cxADMSEG.setTransactionIsolation(2);

			cxMODULO.setAutoCommit(false);
			cxMODULO.setTransactionIsolation(2);

			// recupera pa�s
			cSQL = "select iCvePais, cNombre, cAbreviatura from GRLPAIS";

			psRead = cxADMSEG.prepareStatement(cSQL);
			rset = psRead.executeQuery();
			TVPais vPais;
			while (rset.next()) {
				vPais = new TVPais();
				vPais.setICvePais(rset.getInt(1));
				vPais.setCNombre(rset.getString(2));
				vPais.setCAbreviatura(rset.getString(3));
				vcPais.addElement(vPais);
			}

			if (rset != null) {
				rset.close();
			}
			if (psRead != null) {
				psRead.close();
			}

			// recupera entidad federativa
			cSQL = "select iCvePais, iCveEntidadFed, cNombre, cAbreviatura from GRLENTIDADFED";

			psRead = cxADMSEG.prepareStatement(cSQL);
			rset = psRead.executeQuery();
			TVEntidadFed vEdo;
			while (rset.next()) {
				vEdo = new TVEntidadFed();
				vEdo.setICvePais(rset.getInt(1));
				vEdo.setICveEntidadFed(rset.getInt(2));
				vEdo.setCNombre(rset.getString(3));
				vEdo.setCAbreviatura(rset.getString(4));
				vcEdo.addElement(vEdo);
			}

			if (rset != null) {
				rset.close();
			}
			if (psRead != null) {
				psRead.close();
			}

			// recupera municipio
			cSQL = "select iCvePais, iCveEntidadFed, iCveMunicipio, cNombre, cAbreviatura from GRLMUNICIPIO";

			psRead = cxADMSEG.prepareStatement(cSQL);
			rset = psRead.executeQuery();
			TVMunicipio vMun;
			while (rset.next()) {
				vMun = new TVMunicipio();
				vMun.setICvePais(rset.getInt(1));
				vMun.setICveEntidadFed(rset.getInt(2));
				vMun.setICveMunicipio(rset.getInt(3));
				vMun.setCNombre(rset.getString(4));
				vMun.setCAbreviatura(rset.getString(5));
				vcMun.addElement(vMun);
			}

			if (rset != null) {
				rset.close();
			}
			if (psRead != null) {
				psRead.close();
			}

			// Elimina Pa�s Local
			cSQL = "delete from GRLPais ";

			psDelPais = cxMODULO.prepareStatement(cSQL);
			psDelPais.executeUpdate();

			// Elimina Entidad Federativa Local
			cSQL = "delete from GRLEntidadFed ";

			psDelEdo = cxMODULO.prepareStatement(cSQL);
			psDelEdo.executeUpdate();

			// Elimina Municipio Local
			cSQL = "delete from GRLMunicipio ";

			psDelMun = cxMODULO.prepareStatement(cSQL);
			psDelMun.executeUpdate();

			// Inserta colecci�n de Pais
			cSQL = "insert into GRLPais (iCvePais, cNombre, cAbreviatura)"
					+ "values(?,?,?)";

			psInsPais = cxMODULO.prepareStatement(cSQL);

			for (i = 0; i < vcPais.size(); i++) {
				vPais = (TVPais) vcPais.get(i);
				psInsPais.setInt(1, vPais.getICvePais());
				psInsPais.setString(2, vPais.getCNombre());
				psInsPais.setString(3, vPais.getCAbreviatura());
				psInsPais.executeUpdate();
			}

			// Inserta colecci�n de Entidad Federativa
			cSQL = "insert into GRLEntidadFed (iCvePais, iCveEntidadFed, cNombre, cAbreviatura)"
					+ "values(?,?,?,?)";

			psInsEdo = cxMODULO.prepareStatement(cSQL);

			for (i = 0; i < vcEdo.size(); i++) {
				vEdo = (TVEntidadFed) vcEdo.get(i);
				psInsEdo.setInt(1, vEdo.getICvePais());
				psInsEdo.setInt(2, vEdo.getICveEntidadFed());
				psInsEdo.setString(3, vEdo.getCNombre());
				psInsEdo.setString(4, vEdo.getCAbreviatura());
				psInsEdo.executeUpdate();
			}

			// Inserta colecci�n de Municipio
			cSQL = "insert into GRLMunicipio (iCvePais, iCveEntidadFed, iCveMunicipio, cNombre, cAbreviatura)"
					+ "values(?,?,?,?,?)";

			psInsMun = cxMODULO.prepareStatement(cSQL);

			for (i = 0; i < vcMun.size(); i++) {
				vMun = (TVMunicipio) vcMun.get(i);
				psInsMun.setInt(1, vMun.getICvePais());
				psInsMun.setInt(2, vMun.getICveEntidadFed());
				psInsMun.setInt(3, vMun.getICveMunicipio());
				psInsMun.setString(4, vMun.getCNombre());
				psInsMun.setString(5, vMun.getCAbreviatura());
				psInsMun.executeUpdate();
			}

			cxMODULO.commit();
		} catch (Exception ex) {
			try {
				cxMODULO.rollback();
			} catch (Exception e) {
				warn("update.rollback", ex);
			}
			warn("update", ex);
			lSuccess = false;
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (psRead != null) {
					psRead.close();
				}
				if (psInsPais != null) {
					psInsPais.close();
				}
				if (psDelPais != null) {
					psDelPais.close();
				}
				if (psInsEdo != null) {
					psInsEdo.close();
				}
				if (psDelEdo != null) {
					psDelEdo.close();
				}
				if (psInsMun != null) {
					psInsMun.close();
				}
				if (psDelMun != null) {
					psDelMun.close();
				}
				if (cxADMSEG != null) {
					cxADMSEG.close();
				}
				dcADMSEG.closeConnection();
				if (cxMODULO != null) {
					cxMODULO.close();
				}
				dcMODULO.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return lSuccess;
		}
	}

}