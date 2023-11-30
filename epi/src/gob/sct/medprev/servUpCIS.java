// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 22/02/2011 01:23:55 p.m.
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   servUpCIS.java

package gob.sct.medprev;

import com.micper.ingsw.TParametro;
import com.micper.seguridad.vo.TVUsuario;
import com.micper.util.TFechas;
import com.oreilly.servlet.multipart.*;
import gob.sct.medprev.dao.TDEPICisCita;
import gob.sct.medprev.dao.TDGRLCategoria;
import gob.sct.medprev.dao.TDGRLMotivo;
import gob.sct.medprev.dao.TDPERDatos;
import gob.sct.medprev.vo.TVEPICisCita;
import gob.sct.medprev.vo.TVGRLCategoria;
import gob.sct.medprev.vo.TVGRLMotivo;
import gob.sct.medprev.vo.TVPERDatos;
import java.io.*;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;

public class servUpCIS extends HttpServlet {

	public servUpCIS() {
		vParametros = new TParametro("07");
		dataSourceName = vParametros.getPropEspecifica("ConDBModulo");
		vfechas = new Vector();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest r;
		boolean lCarga;
		boolean lCargado;
		TVUsuario usuario;
		r = request;
		Part p = null;
		String Record = "";
		int posicion = 0;
		int posicion2 = 0;
		lCarga = true;
		lCargado = false;
		String temporal = "";
		usuario = (TVUsuario) request.getSession().getAttribute("UsrID");
		MultipartParser mp = new MultipartParser(r, 0x7fffffff);
		File fArchivo = new File(vParametros.getPropEspecifica("RutaCIS")
				+ "CIS.txt");
		// Part p;
		while ((p = mp.readNextPart()) != null)
			if (p.isFile()) {
				FilePart fp = (FilePart) p;
				fp.writeTo(fArchivo);
			} else {
				throw new InternalError(
						"PART DE TIPO DESCONOCIDO. INCAPAZ PARSEAR REQUEST.");
			}
		int iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed"));
		int iCveModulo = Integer.parseInt(request.getParameter("iCveModulo"));
		try {
			File F = new File(vParametros.getPropEspecifica("RutaCIS")
					+ "CIS.txt");
			BufferedReader in = new BufferedReader(new FileReader(F));
			TDEPICisCita dTDEPICisCita = new TDEPICisCita();
			TDGRLMotivo dTDGRLMotivo = new TDGRLMotivo();
			TVGRLMotivo vTVGRLMotivo = new TVGRLMotivo();
			TDPERDatos dTDPERDatos = new TDPERDatos();
			TVPERDatos vTVPERDatos = new TVPERDatos();
			TDGRLCategoria dTDGRLCategoria = new TDGRLCategoria();
			TVGRLCategoria vTVGRLCategoria = new TVGRLCategoria();
			String cWhere = "";
			Vector v = new Vector();
			TFechas f = new TFechas();
			String cHora = "";
			// String Record;
			while ((Record = in.readLine()) != null) {
				TVEPICisCita vTVEPICisCita = new TVEPICisCita();
				vTVEPICisCita.setICvePais(1);
				vTVEPICisCita.setICvePaisNac(1);
				// Modificacion de la obtencion de parametros para la carga de
				// Unidad Medica y Modulo
				// vTVEPICisCita.setICveUniMed(iCveUniMed);
				// vTVEPICisCita.setICveModulo(iCveModulo);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setDtFecha(f.getDateSQL(Record.substring(0,
						posicion)));
				Record = Record.substring(posicion + 1);
				vfechas.add(vTVEPICisCita);
				posicion = Record.indexOf("\t");
				cHora = Record.substring(0, posicion);
				posicion2 = cHora.indexOf(":");
				vTVEPICisCita.setIHora((new Integer(cHora.substring(0,
						posicion2))).intValue());
				cHora = cHora.substring(posicion2 + 1);
				vTVEPICisCita.setIMinutos((new Integer(cHora)).intValue());
				vTVEPICisCita.setCHora(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCRFC(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCCURP(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setDtNacimiento(f.getDateSQL(Record.substring(0,
						posicion)));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setICveEstadoNac((new Integer(Record.substring(0,
						posicion))).intValue());
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCApPaterno(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCApMaterno(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCNombre(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCGenero(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCCalle(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCColonia(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita
						.setICP((new Integer(Record.substring(0, posicion)))
								.intValue());
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				if (Record.substring(0, posicion) != null
						&& Record.substring(0, posicion).compareTo("0") != 0)
					vTVEPICisCita.setICveMunicipio((new Integer(Record
							.substring(0, posicion))).intValue());
				else
					vTVEPICisCita.setICveMunicipio(1);
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				if (Record.substring(0, posicion) != null
						&& Record.substring(0, posicion).compareTo("0") != 0)
					vTVEPICisCita.setICveEstado((new Integer(Record.substring(
							0, posicion))).intValue());
				else
					vTVEPICisCita.setICveEstado(9);
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCTelefono(Record.substring(0, posicion));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				temporal = Record.substring(0, posicion);
				Record = Record.substring(posicion + 1);
				// System.out.println("temporal = "+temporal);
				if (temporal.equals("Carretero"))
					vTVEPICisCita.setICveMdoTrans(2);
				else if (temporal.equals("Aéreo"))
					vTVEPICisCita.setICveMdoTrans(1);
				else if (temporal.equals("Ferroviario"))
					vTVEPICisCita.setICveMdoTrans(3);
				else if (temporal.equals("Marítimo"))
					vTVEPICisCita.setICveMdoTrans(4);
				posicion = Record.indexOf("\t");

				// vTVEPICisCita.setCExpediente(Record.substring(0, posicion));
				// //Comentado por Modulo nuevas CITAS
				vTVEPICisCita.setCExpediente(""); // Linea para mODULO NUEVAS
													// CITAS
				vTVEPICisCita.setICvePuesto(Integer.parseInt(Record.substring(
						0, posicion)));// Linea para MODULO NUEVAS CITAS

				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setCDscCategoria(Record.substring(0, posicion));
				cWhere = " WHERE iCveMdoTrans = "
						+ vTVEPICisCita.getICveMdoTrans() + " AND "
						+ "           cDscCategoria = '"
						+ vTVEPICisCita.getCDscCategoria() + "' ";
				v = dTDGRLCategoria.FindWhere(cWhere);
				int i;
				if (v.isEmpty()) {
					vTVEPICisCita.setICveCategoria(0);
				} else {
					for (i = 0; i < v.size(); i++)
						vTVGRLCategoria = (TVGRLCategoria) v.elementAt(i);

					vTVEPICisCita.setICveCategoria(vTVGRLCategoria
							.getICveCategoria());
				}
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVGRLMotivo.setCDscMotivo(Record.substring(0, posicion));
				/*
				 * if(posicion == -1) { vTVGRLMotivo.setCDscMotivo(Record); }
				 * else { vTVGRLMotivo.setCDscMotivo(Record.substring(0,
				 * posicion)); Record = ""; }
				 */
				v = dTDGRLMotivo.FindDsc(" WHERE GRLMotivo.cDscMotivo = '"
						+ vTVGRLMotivo.getCDscMotivo() + "'");
				i = 0;
				do {
					if (i >= v.size())
						break;
					vTVGRLMotivo = (TVGRLMotivo) v.elementAt(i);
					if (vTVGRLMotivo.getICveProceso() == 1)
						break;
					i++;
				} while (true);
				vTVEPICisCita.setICveMotivo(vTVGRLMotivo.getICveMotivo());
				if (vTVEPICisCita.getICveMotivo() == 0)
					vTVEPICisCita.setICveMotivo(1);

				// vTVEPICisCita.setICvePuesto(1); ////Comentado por Modulo
				// nuevas CITAS

				// Modificacion de la obtencion de parametros para la carga de
				// Unidad Medica y Modulo
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");
				vTVEPICisCita.setICveUniMed(Integer.parseInt(Record.substring(
						0, posicion)));
				Record = Record.substring(posicion + 1);
				posicion = Record.indexOf("\t");

				// vTVEPICisCita.setICveModulo(Integer.parseInt(Record.substring(0,
				// posicion)));

				// System.out.println("posicion = "+posicion);

				if (posicion == -1) {
					// vTVGRLMotivo.setCDscMotivo(Record);
					vTVEPICisCita.setICveModulo(Integer.parseInt(Record));
				} else {
					// vTVGRLMotivo.setCDscMotivo(Record.substring(0,
					// posicion));
					vTVEPICisCita.setICveModulo(Integer.parseInt(Record
							.substring(0, posicion)));
					Record = "";
				}

				v = dTDPERDatos.FindBySelector(" WHERE CRFC = '"
						+ vTVEPICisCita.getCRFC() + "' AND "
						+ "CNOMBRE LIKE '%" + vTVEPICisCita.getCNombre()
						+ "%' AND " + "CAPPATERNO LIKE '%"
						+ vTVEPICisCita.getCApPaterno() + "%' AND "
						+ "CAPMATERNO LIKE '%" + vTVEPICisCita.getCApMaterno()
						+ "%' ");
				if (v.isEmpty()) {
					vTVEPICisCita.setICveSituacion(1);
				} else {
					vTVEPICisCita.setICveSituacion(2);
					for (i = 0; i < v.size(); i++)
						vTVPERDatos = (TVPERDatos) v.elementAt(i);

					vTVEPICisCita
							.setICvePersonal(vTVPERDatos.getICvePersonal());
				}
				if (usuario != null)
					vTVEPICisCita.setICveUsuCita(usuario.getICveusuario());

				dTDEPICisCita.insert(null, vTVEPICisCita);
				lCargado = true;

				// System.out.println("Linea numero " + Numlinea);
			}
			in.close();
			if (lCargado) {
				lCarga = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			lCarga = false;
		} finally {
			try {
				TDEPICisCita tdCisCita = new TDEPICisCita();
				tdCisCita.prepareDate(vfechas);
			} catch (Exception e) {
			}
		}
		response.getWriter().println("<script language=\"JavaScript\">");
		if (!lCarga) {
			response.getWriter().println(
					"alert('No todas las citas han sido cargadas.');");
		} else {
			response.getWriter()
					.println(
							"alert('Las citas fueron registradas de manera correcta.');");
		}
		response.getWriter().println(
				"window.opener.fSubmitForm('Buscar');window.close();");
		response.getWriter().println("</script>");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	TParametro vParametros;
	private String dataSourceName;
	private Vector vfechas;
}