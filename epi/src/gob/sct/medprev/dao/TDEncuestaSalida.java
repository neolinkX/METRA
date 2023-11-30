package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de AcCorrectiva DAO
 * </p>
 * <p>
 * Description: DAO de TOXAcCorrectiva
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */

public class TDEncuestaSalida extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDEncuestaSalida() {
	}

	public Vector findByDate(String cFecha, String cCveUniMed, String cCveModulo)
			throws DAOException {
		Vector vcRecords = new Vector();
		Vector vcPreguntas = new Vector();
		Vector vcResultados = new Vector();
		TVDinRep vPreguntas, vReporte;
		int iDato;
		float fDato;
		cError = "";
		try {
			String cSQL = "select iCveServicio,icverama,icvesintoma,iCveTpoResp,cPregunta "
					+ "from MEDSintomas "
					+ "where iCveServicio = 3 "
					+ "order by iCveServicio,icverama,icvesintoma";

			vcPreguntas = this.FindByGeneric(cSQL, dataSourceName);

			for (int i = 0; i < vcPreguntas.size(); i++) {
				vPreguntas = (TVDinRep) vcPreguntas.get(i);
				vReporte = new TVDinRep();
				vReporte.put("CPREGUNTA", vPreguntas.getString("CPREGUNTA"));
				vReporte.put("1", "");
				vReporte.put("2", "");

				/*  */if (vPreguntas.getInt("ICVETPORESP") == 1) { // Tipo Lógico
					cSQL = "select count(llogico) as iCount "
							+ "from expresultado "
							+ "join expexamaplica on expresultado.icveexpediente = expexamaplica.icveexpediente "
							+ "and expresultado.inumexamen = expexamaplica.inumexamen "
							+ " and expexamaplica.iCveUniMed = " + cCveUniMed
							+ " and expexamaplica.iCveModulo = " + cCveModulo
							+ cFecha + " where expresultado.icveservicio = "
							+ vPreguntas.getInt("ICVESERVICIO")
							+ " and   expresultado.icverama = "
							+ vPreguntas.getInt("ICVERAMA")
							+ " and   expresultado.icvesintoma = "
							+ vPreguntas.getInt("ICVESINTOMA")
							+ " and   llogico = 1 ";

					vcResultados = this.FindByGeneric(cSQL, dataSourceName);
					iDato = ((TVDinRep) vcResultados.get(0)).getInt("ICOUNT");
					vReporte.put("1", "" + iDato);

					cSQL = "select count(llogico) as iCount "
							+ "from expresultado "
							+ "join expexamaplica on expresultado.icveexpediente = expexamaplica.icveexpediente "
							+ "and expresultado.inumexamen = expexamaplica.inumexamen "
							+ " and expexamaplica.iCveUniMed = " + cCveUniMed
							+ " and expexamaplica.iCveModulo = " + cCveModulo
							+ cFecha + " where expresultado.icveservicio = "
							+ vPreguntas.getInt("ICVESERVICIO")
							+ " and   expresultado.icverama = "
							+ vPreguntas.getInt("ICVERAMA")
							+ " and   expresultado.icvesintoma = "
							+ vPreguntas.getInt("ICVESINTOMA")
							+ " and   llogico = 0 ";

					vcResultados = this.FindByGeneric(cSQL, dataSourceName);
					iDato = ((TVDinRep) vcResultados.get(0)).getInt("ICOUNT");
					vReporte.put("2", "" + iDato);
				}
				/*   */if (vPreguntas.getInt("ICVETPORESP") == 2) { // Tipo
																	// Caracter
				}
				/*   */if (vPreguntas.getInt("ICVETPORESP") == 3) { // Tipo
																	// Numérico
					cSQL = "select avg(dValorIni) as dCount "
							+ "from expresultado "
							+ "join expexamaplica on expresultado.icveexpediente = expexamaplica.icveexpediente "
							+ "and expresultado.inumexamen = expexamaplica.inumexamen "
							+ " and expexamaplica.iCveUniMed = " + cCveUniMed
							+ " and expexamaplica.iCveModulo = " + cCveModulo
							+ cFecha + " where expresultado.icveservicio = "
							+ vPreguntas.getInt("ICVESERVICIO")
							+ " and   expresultado.icverama = "
							+ vPreguntas.getInt("ICVERAMA")
							+ " and   expresultado.icvesintoma = "
							+ vPreguntas.getInt("ICVESINTOMA");

					vcResultados = this.FindByGeneric(cSQL, dataSourceName);
					fDato = ((TVDinRep) vcResultados.get(0)).getFloat("DCOUNT");
					vReporte.put("1", "" + fDato);
				}

				/*   */if (vPreguntas.getInt("ICVETPORESP") == 4) { // Tipo
																	// Caracter
				}
				/*   */if (vPreguntas.getInt("ICVETPORESP") == 5) { // Tipo Rango
					vReporte.put("1", (float) 0);
					vReporte.put("2", (float) 0);
					cSQL = "select avg(dValorIni) as dCount, avg(dValorFin) as dCount2 "
							+ "from expresultado "
							+ "join expexamaplica on expresultado.icveexpediente = expexamaplica.icveexpediente "
							+ "and expresultado.inumexamen = expexamaplica.inumexamen "
							+ " and expexamaplica.iCveUniMed = "
							+ cCveUniMed
							+ " and expexamaplica.iCveModulo = "
							+ cCveModulo
							+ cFecha
							+ " where expresultado.icveservicio = "
							+ vPreguntas.getInt("ICVESERVICIO")
							+ " and   expresultado.icverama = "
							+ vPreguntas.getInt("ICVERAMA")
							+ " and   expresultado.icvesintoma = "
							+ vPreguntas.getInt("ICVESINTOMA");

					vcResultados = this.FindByGeneric(cSQL, dataSourceName);
					fDato = ((TVDinRep) vcResultados.get(0)).getFloat("DCOUNT");
					vReporte.put("1", "" + fDato);
					fDato = ((TVDinRep) vcResultados.get(0))
							.getFloat("DCOUNT2");
					vReporte.put("2", "" + fDato);
				}
				vcRecords.add(vReporte);
			}
		} catch (Exception e) {
			cError = e.toString();
			e.printStackTrace();
		} finally {
			if (!cError.equals(""))
				throw new DAOException(cError);
			return vcRecords;
		}

	}

}
