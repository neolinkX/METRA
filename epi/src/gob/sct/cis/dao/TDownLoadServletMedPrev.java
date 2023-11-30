/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.sct.cis.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.micper.ingsw.TParametro;
import com.micper.sql.DbConnection;

public class TDownLoadServletMedPrev extends HttpServlet {
	// private static final String CONTENT_TYPE = "application/vnd.ms-excel";
	private static final String CONTENT_TYPE = "text/plain";
	private TParametro VParametros = new TParametro("12");
	private String dataSourceName = VParametros.getPropEspecifica("ConDBCis");

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ "CIS.txt");
		String cFiltrado = request.getParameter("Filtrado");

		// System.out.println("::Entro a TDownLoadServletMedPrev::");

		String cSql = " SELECT  CISCITAS.DTFECHA, CISCITAS.CHORA, CISINTERESADO.CRFC, CISINTERESADO.CCURP,"
				+ " CISINTERESADO.ICVEMUNICIPIO,GRLMUNICIPIO.CNOMBRE,"
				+ " CISINTERESADO.ICVEENTIDADFED,GRLENTIDADFED.CNOMBRE,"
				+ " CISINTERESADO.CNOMBRE, CISINTERESADO.CAPPATERNO,CISINTERESADO.CAPMATERNO,"
				+ " CISINTERESADO.CSEXO, "
				+ " CISINTERESADO.CDIRECCION,CISINTERESADO.CCP,"
				+ // CP 14
				/* 15 */" CISINTERESADO.ICVEENTIDADNAC, "
				+
				/* 16 */" ENTIDADNAC.CNOMBRE, "
				+
				/* 17 */" CISINTERESADO.CTELEFONO, "
				+
				/* 18 */" CSTREGTRAMITE.ICVETRAMITE, "
				+
				/* 19 */" CISCITAS.CDSCCAMPOS,"
				+
				/* 20 */" CISCITAS.CNUMTRAMITES,"
				+
				/* 21 */" CISINTERESADO.CCOLONIA, "
				+
				/* 22 */" CISAREAS.ICVEUNIMED, "
				+
				/* 23 */" CISAREAS.ICVEMODULO "
				+ " FROM    CISCITAS, CISINTERESADO, GRLMUNICIPIO, GRLENTIDADFED, GRLENTIDADFED AS ENTIDADNAC, CSTREGTRAMITE, CISAREAS "
				+ " WHERE   CISCITAS.ICVEINTERESADO = CISINTERESADO.ICVEINTERESADO "
				+ " AND CISCITAS.ICVEAREA = CISAREAS.ICVEAREA "
				+ " AND CISINTERESADO.ICVEMUNICIPIO = GRLMUNICIPIO.ICVEMUNICIPIO "
				+ " AND CISINTERESADO.ICVEENTIDADFED = GRLENTIDADFED.ICVEENTIDADFED "
				+ " AND CISINTERESADO.ICVEENTIDADFED = GRLMUNICIPIO.ICVEENTIDADFED "
				+ " AND CISINTERESADO.ICVEENTIDADNAC = ENTIDADNAC.ICVEENTIDADFED "
				+ " AND  CISCITAS.ICVETRAMITE = CSTREGTRAMITE.ICVETRAMITE "
				+ cFiltrado + " ORDER BY CISCITAS.CHORA";
		// " AND CISCITAS.ICVEAREA = 160 AND DTFECHA = '2011-06-29' ORDER BY CISCITAS.CHORA";

		PrintWriter out = response.getWriter();
		DbConnection dbConn = null;
		Connection conn = null;
		// System.out.println("::Exporta Citas SQL execute::" + cSql);

		try {
			// inicialización de la conexión y variables
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(cSql);
			String modoTransporte = "";
			String infoDinamica[];
			String motivoARM[];

			// ITERA RESULTADO DE QUERY
			while (resultSet.next()) {
				StringBuffer sb = new StringBuffer();
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

				// DTFECHA
				sb.append(formato.format(resultSet.getDate(1)) + '\t');
				// CHORA
				sb.append(resultSet.getString(2) + '\t');
				// RFC
				sb.append(resultSet.getString(3) + '\t');
				// CURP
				sb.append(resultSet.getString(4) + '\t');

				String datosFechaRFC = resultSet.getString(3).substring(4, 10);
				String fechaNacimiento = "01/01/1970";

				if (datosFechaRFC.length() == 6) {

					fechaNacimiento = datosFechaRFC.subSequence(4, 6) + "/"
							+ datosFechaRFC.subSequence(2, 4) + "/" + "19"
							+ datosFechaRFC.subSequence(0, 2);
				}

				// Fecha de nacimiento
				sb.append(fechaNacimiento + '\t');

				// CLAVE MUNICIPIO NAC
				sb.append("0" + '\t');
				// NOMBRE MUNICIPIO NAC
				sb.append("munNacDesconocido" + '\t');
				// CLAVE ENTIDAD FEDERATIVA NAC
				sb.append("" + resultSet.getInt(15) + '\t');
				// ENTIDAD FEDERATIVA NAC
				sb.append(resultSet.getString(16) + '\t');
				// NOMBRE(S)
				sb.append(resultSet.getString(9) + '\t');
				// PATERNO
				sb.append(resultSet.getString(10) + '\t');
				// MATERNO
				sb.append(resultSet.getString(11) + '\t');
				// SEXO

				if (!resultSet.getString(12).equals("")
						&& !resultSet.getString(12).equals(null)) {
					sb.append(resultSet.getString(12) + '\t');
				}

				else {
					sb.append("M" + '\t');
				}

				// DIRECCION
				sb.append(resultSet.getString(13) + '\t');

				// COLONIA
				if (!resultSet.getString(21).equals("")
						&& resultSet.getString(21).length() != 0)
					sb.append(resultSet.getString(21) + '\t');
				else
					sb.append("SIN DATO" + '\t');

				// CP
				if (!resultSet.getString(14).equals("")
						&& resultSet.getString(14).length() != 0)
					sb.append(resultSet.getString(14) + '\t');
				else
					sb.append("0" + '\t');

				// icve municipio dir
				sb.append(String.valueOf(resultSet.getInt(5)) + '\t');

				// descripcion de mun dir
				sb.append(resultSet.getString(6) + '\t');

				// CLAVE ENTIDAD FEDERATIVA dir
				sb.append("" + resultSet.getInt(7) + '\t');

				// ENTIDAD FEDERATIVA dir
				sb.append(resultSet.getString(8) + '\t');

				// TELEFONO
				if (!resultSet.getString(17).equals("")
						&& resultSet.getString(17).length() != 0)
					sb.append(resultSet.getString(17) + '\t');
				else
					sb.append("00000000" + '\t');

				// XAXA
				sb.append("0" + '\t');

				// MODO DE TRANSPORTE
				int idTramite = 0;
				idTramite = resultSet.getInt(18);

				if (idTramite == Integer.parseInt(VParametros
						.getPropEspecifica("IdSolTranFed"))) // transporte
																// federal
					modoTransporte = "Carretero";

				if (idTramite == Integer.parseInt(VParametros
						.getPropEspecifica("IdSolAT"))) // autotransporte
					modoTransporte = "Carretero";

				if (idTramite == Integer.parseInt(VParametros
						.getPropEspecifica("IdSolAC"))) // Aéreo
					modoTransporte = "Aéreo";

				if (idTramite == Integer.parseInt(VParametros
						.getPropEspecifica("IdSolMM"))) // Marina
					modoTransporte = "Marítimo";

				if (idTramite == Integer.parseInt(VParametros
						.getPropEspecifica("IdSolFM"))) // Ferroviario
					modoTransporte = "Ferroviario";

				sb.append(modoTransporte + '\t');

				// PUESTO
				if (modoTransporte.equals("Carretero")) {
					sb.append(VParametros
							.getPropEspecifica("IdPuestoAutoTUnicoMedPrev") + '\t'); // PUESTO
																						// UNICO
																						// PARA
																						// CARRETERO
				}

				else
					sb.append("1" + '\t'); // PUESTO UNICO PARA CARRETERO

				// CATEGORIA POR TIPO DE TRANSPORTE

				if (idTramite == Integer.parseInt(VParametros
						.getPropEspecifica("IdSolAC"))
						|| idTramite == Integer.parseInt(VParametros
								.getPropEspecifica("IdSolMM"))
						|| idTramite == Integer.parseInt(VParametros
								.getPropEspecifica("IdSolFM"))) {

					if (idTramite == Integer.parseInt(VParametros
							.getPropEspecifica("IdSolAC")))
						sb.append("GRUPO UNO" + '\t'); //

					if (idTramite == Integer.parseInt(VParametros
							.getPropEspecifica("IdSolMM")))
						sb.append("GRUPO CUATRO" + '\t'); //

					if (idTramite == Integer.parseInt(VParametros
							.getPropEspecifica("IdSolFM")))
						sb.append("GRUPO TRES" + '\t'); //
				}

				else
					sb.append("UNICA" + '\t'); // PUESTO UNICO PARA CARRETERO

				// MOTIVO
				String informacionDinamica = "EXPEDICION";

				if (!resultSet.getString(19).equals("")
						&& resultSet.getString(19).length() != 0) {

					// System.out.println("length >>" +
					// resultSet.getString(19).split(",").length);
					// infoDinamica = new String[6];
					infoDinamica = resultSet.getString(19).split(",");

					// System.out.println("infoDinamica.length"+infoDinamica.length);
					if (infoDinamica.length == 6) {

						motivoARM = infoDinamica[5].split("\\|");

						if (motivoARM.length == 2)
							informacionDinamica = motivoARM[1];

						sb.append(informacionDinamica + '\t');
					}

					else
						sb.append(informacionDinamica + '\t');
				}

				if (resultSet.getString(19).equals("")
						|| resultSet.getString(19).length() == 0) {
					sb.append(informacionDinamica + '\t');
				}

				// iCveUniMed 22
				sb.append(String.valueOf(resultSet.getInt(22)) + '\t');

				// iCveMod 23
				sb.append(String.valueOf(resultSet.getInt(23)));

				out.print(sb.toString() + "\r"); // FIN Y SALTO DE LINEA...

			} // FIN DE WHILE DE ITERACION DE RESULT

			out.flush();
			out.close();
			resultSet.close();
			stmt.close();

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
