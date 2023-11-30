package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.excepciones.*;

/**
 * * Clase de configuracion para el manejo de las recepciones de los envios
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Javier Mendoza
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070301110CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070303010CFG.png'>
 */
public class pg070303010CFG extends CFGCatBase2 {
	public pg070303010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "BorrarB";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		if (this.getLPagina("pg070303011.jsp"))
			cPaginas = "pg070303011.jsp|";
		String aWhereDesc = "";
		boolean bTotal = false;
		String cCampoC = "";
		try {
			vDespliega = dTOXEnvio.FindByAll(aWhereDesc);
			NavStatus = "FirstRecord";
			String lAction = request.getParameter("hdBoton");
			if (lAction.equalsIgnoreCase("Guardar")) {

				dTOXEnvio.update(null, this.getInputs());
				this.fillVectorc(this.getInputs(), true);
				NavStatus = "LastRecord";
			}
			if (lAction.equalsIgnoreCase("BtnBuscar")) {
				cCampoC = "" + request.getParameter("bCCveEnvio");
				if (cCampoC.compareTo("null") != 0
						&& cCampoC.compareTo("") != 0) {
					bTotal = true;
				}
				this.fillVectorc(this.getInputs(), bTotal);
				bTotal = false;
			}

			if (lAction.equalsIgnoreCase("Actual")
					|| lAction.equalsIgnoreCase("Primero")) {

				String cCampoA = "";
				String cCampoB = "";
				String cCampoZ = "";
				int iCampoA = 0;
				int iCampoB = 0;
				int iCampoC = 0;

				cCampoA = "" + request.getParameter("hdCampoClaveA");
				cCampoB = "" + request.getParameter("hdCampoClaveB");
				cCampoZ = "" + request.getParameter("hdCampoClaveC");
				if (cCampoA.compareTo("null") != 0
						&& cCampoA.compareTo("") != 0) {
					iCampoA = Integer.parseInt(cCampoA, 10);
				} else {
					iCampoA = 0;
				}

				if (cCampoB.compareTo("null") != 0
						&& cCampoB.compareTo("") != 0) {
					iCampoB = Integer.parseInt(cCampoB, 10);
				} else {
					iCampoB = 0;
				}

				if (cCampoZ.compareTo("null") != 0
						&& cCampoZ.compareTo("") != 0) {
					iCampoC = Integer.parseInt(cCampoZ, 10);
				} else {
					iCampoC = 0;
				}

				this.fillVectord(iCampoA, iCampoB, iCampoC);
				bTotal = false;
			}

		} catch (Exception ex) {
			error("FillVector", ex);
		}

		if (!vDespliega.isEmpty())
			UpdStatus = "SaveOnly";
		else
			UpdStatus = "Hidden";
	}

	public void fillVectorb() {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		cPaginas = "pg070301111.jsp|"; // Aqui va la pagina de origen
		String aWhereDesc = "Where a.iCveTipoEnvio = b.iCveTipoEnvio";
		try {
			vDespliega = dTOXEnvio.FindByAll(aWhereDesc);
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public void fillVectorc(Object obj, boolean bTotal) {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		TVTOXEnvio vTOXEnvio = (TVTOXEnvio) obj;
		cPaginas = "pg070301111.jsp|"; // Aqui va la pagina de origen
		// String aWhereDesc = "Where a.iCveTipoEnvio = b.iCveTipoEnvio";
		String aWhereDesc = "";
		if (bTotal) {
			if (vTOXEnvio.getICveEnvio() != 0) {

				if (vTOXEnvio.getIAnio() != 0) {
					aWhereDesc += " Where a.iAnio = " + vTOXEnvio.getIAnio();
				}
				if (vTOXEnvio.getICveUniMed() != 0) {
					aWhereDesc += " And a.iCveUniMed = "
							+ vTOXEnvio.getICveUniMed();
				}
				if (vTOXEnvio.getICveEnvio() != 0) {
					aWhereDesc += " And a.iCveEnvio = "
							+ vTOXEnvio.getICveEnvio();
				}
			}
			try {
				vDespliega = dTOXEnvio.FindByAll(aWhereDesc);
			} catch (Exception ex) {
				error("FillVector", ex);
			}
		}
	}

	public void fillVectord(int A, int B, int C) {
		TDTOXEnvio dTOXEnvio = new TDTOXEnvio();
		cPaginas = "pg070301111.jsp|"; // Aqui va la pagina de origen
		// String aWhereDesc = "Where a.iCveTipoEnvio = b.iCveTipoEnvio";
		String aWhereDesc = " Where ";
		aWhereDesc += " a.iAnio = " + A;
		aWhereDesc += " And a.iCveUniMed = " + B;
		aWhereDesc += " And a.iCveEnvio = " + C;
		try {
			vDespliega = dTOXEnvio.FindByAll(aWhereDesc);
		} catch (Exception ex) {
			error("FillVectord", ex);
		}
	}

	/**
	 * Metodo FillPK
	 */
	public void FillPK() {
		mPk.add(cActual);
	}

	/**
	 * Metodo getInputs
	 */
	public Object getInputs() throws CFGException {
		String cCampo;
		String cCampob;
		int iCampo;
		float fCampo;
		java.sql.Date dtCampo;
		// TFechas tfCampo = new TFechas();
		TVTOXEnvio vTOXEnvio = new TVTOXEnvio();
		try {
			cCampo = "" + request.getParameter("iCveEnvio");
			cCampob = "" + request.getParameter("bCCveEnvio");

			if (cCampob.compareTo("null") != 0 && cCampob.compareTo("") != 0) {
				cCampo = cCampob;

			}

			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}

			vTOXEnvio.setICveEnvio(iCampo);

			cCampo = "" + request.getParameter("cObsRecep");
			if (cCampo.compareTo("null") == 0) {
				cCampo = "";
			}
			vTOXEnvio.setCObsRecep(cCampo);

			cCampo = "" + request.getParameter("iAnio");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXEnvio.setIAnio(iCampo);

			cCampo = "" + request.getParameter("iCveUniMed");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXEnvio.setICveUniMed(iCampo);

			cCampo = "" + request.getParameter("hdUserId");
			if (cCampo.compareTo("null") != 0 && cCampo.compareTo("") != 0) {
				iCampo = Integer.parseInt(cCampo, 10);
			} else {
				iCampo = 0;
			}
			vTOXEnvio.setICveUsuRec(iCampo);

		} catch (Exception ex) {
			error("getInputs", ex);
			throw new CFGException("");
		}
		return vTOXEnvio;
	}

}
