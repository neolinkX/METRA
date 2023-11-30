package gob.sct.medprev;

import java.util.Vector;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.ingsw.*;
import com.micper.seguridad.vo.TVUsuario;
import java.lang.Math.*;

import com.oreilly.servlet.multipart.Part;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.FilePart;

public class servUpRadoxCFG extends HttpServlet {

	TParametro vParametros = new TParametro("07");

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest r = (HttpServletRequest) request;
		MultipartParser mp;
		Part p = null;

		String Record = "";
		String SubCadena = "";
		int posicion = 0;
		Integer iCveAnalisis = new Integer(0);
		String Sustancia = "";
		Double dCorte = new Double(0);
		Integer lPositivo = new Integer(0);
		Integer iNormalised = new Integer(0);
		Double dResultado = new Double(0);
		Double dDilucion = new Double(0);
		Integer iRLU = new Integer(0);
		Integer iCveSustancia = null;
		Integer Unidades = new Integer(0);
		Integer lDudoso = new Integer(0);

		int iAnio, iCveUniMed, iCveLoteCualita, iCveExamCualita;
		boolean lCarga = true;
		boolean lCargado = false;

		try {
			mp = new MultipartParser(r, Integer.MAX_VALUE);
			File fArchivo = new File(
					vParametros.getPropEspecifica("RutaRandox") + "randox2.txt");
			while ((p = mp.readNextPart()) != null) {
				if (p.isFile()) {
					FilePart fp = (FilePart) p;
					fp.writeTo(fArchivo);
				} else {
					throw new InternalError(
							"PART DE TIPO DESCONOCIDO. INCAPAZ PARSEAR REQUEST.");
				}
			}

			iAnio = Integer.parseInt(request.getParameter("iAnio"));
			iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed"));
			iCveLoteCualita = Integer.parseInt(request
					.getParameter("iCveLoteCualita"));
			iCveExamCualita = Integer.parseInt(request
					.getParameter("iCveExamCualita"));

			Unidades = new Integer(
					vParametros.getPropEspecifica("UnidadesDudoso"));

			try {
				File F = new File(vParametros.getPropEspecifica("RutaRandox")
						+ "randox2.txt");
				BufferedReader in = new BufferedReader(new FileReader(F));
				TDTOXExamResult dTOXExamResult = new TDTOXExamResult();
				TVTOXExamResult vTOXExamResult = new TVTOXExamResult();

				TDSustancia dSustancia = new TDSustancia();
				TVSustancia vSustancia = new TVSustancia();
				Vector v = dSustancia.FindByAll(" where lAnalizada = 1");
				int lTomaPositivo = 0;
				// System.out.println("Al realizar la carga");
				while ((Record = in.readLine()) != null) {
					// Clave de analisis

					if (!Record.equals("[RANDOX]".toString())) {

						posicion = Record.indexOf(",");
						SubCadena = Record.substring(0, posicion);
						SubCadena = SubCadena.substring(2, SubCadena.length());
						iCveAnalisis = new Integer(SubCadena);
						Record = Record.substring(posicion + 1);

						// Sustancia
						posicion = Record.indexOf(",");
						Sustancia = Record.substring(0, posicion);
						Record = Record.substring(posicion + 1);

						iCveSustancia = new Integer(0);
						lTomaPositivo = 0;
						for (int i = 0; i < v.size(); i++) {
							vSustancia = (TVSustancia) v.elementAt(i);
							if (Sustancia.equals(vSustancia.getCAbrvEq())) {
								iCveSustancia = new Integer(
										vSustancia.getICveSustancia());
								lTomaPositivo = vSustancia.getLPositiva();
							}
						}

						if (iCveSustancia.intValue() != 0) {
							// dCorte
							posicion = Record.indexOf(",");
							dCorte = new Double(Record.substring(0, posicion));
							Record = Record.substring(posicion + 1);

							// lPositivo
							posicion = Record.indexOf(",");
							lPositivo = new Integer(Record.substring(0,
									posicion));
							Record = Record.substring(posicion + 1);

							// iNormalised
							posicion = Record.indexOf(",");
							iNormalised = new Integer(Record.substring(0,
									posicion));
							Record = Record.substring(posicion + 1);

							// dResultado
							posicion = Record.indexOf(",");
							dResultado = new Double(Record.substring(0,
									posicion));
							Record = Record.substring(posicion + 1);

							// iRLU
							posicion = Record.indexOf(",");
							iRLU = new Integer(Record.substring(0, posicion));
							Record = Record.substring(posicion + 1);
							if (iRLU.intValue() == 0
									&& lPositivo.intValue() == 0) {
								lPositivo = new Integer(-1);
							}

							// dDilucion
							posicion = Record.indexOf(",");
							dDilucion = new Double(
									Record.substring(0, posicion));
							Record = Record.substring(posicion + 1);

							if (java.lang.Math.abs(dCorte.floatValue()
									- dResultado.floatValue()) <= (dCorte
									.floatValue() * (Unidades.doubleValue() / 100))) {
								lDudoso = new Integer(1);
							} else {
								lDudoso = new Integer(0);
							}

							vTOXExamResult.setiAnio(new Integer(iAnio));
							vTOXExamResult.setiCveLaboratorio(new Integer(
									iCveUniMed));
							vTOXExamResult.setiCveLoteCualita(new Integer(
									iCveLoteCualita));
							vTOXExamResult.setiCveExamCualita(new Integer(
									iCveExamCualita));
							vTOXExamResult.setiCveAnalisis(iCveAnalisis);
							vTOXExamResult.setiCveSustancia(iCveSustancia);
							vTOXExamResult.setLPositivo(lPositivo);
							if (lTomaPositivo == 0)
								vTOXExamResult.setLPositivo(new Integer(0));
							vTOXExamResult.setdResultado(dResultado);
							vTOXExamResult.setdDilucion(dDilucion);
							vTOXExamResult.setLDudoso(lDudoso);
							dTOXExamResult.insert(vTOXExamResult);
							lCargado = true;
						}
					}
				}
				in.close();
				if (lCargado) {
					TVUsuario vUsuario = (TVUsuario) request.getSession(true)
							.getAttribute("UsrID");

					TVTOXExamenCualita vTOXExamenCualita = new TVTOXExamenCualita();
					vTOXExamenCualita.setDtProcesado(new java.sql.Date(
							new java.util.Date().getTime()));
					vTOXExamenCualita.setICveUsuExam(vUsuario.getICveusuario());
					vTOXExamenCualita.setIAnio(iAnio);
					vTOXExamenCualita.setICveLoteCualita(iCveLoteCualita);
					vTOXExamenCualita.setICveExamCualita(iCveExamCualita);
					vTOXExamenCualita.setICveLaboratorio(iCveUniMed);

					TDTOXExamenCualita dTOXExamenCualita = new TDTOXExamenCualita();

					dTOXExamenCualita.updateUsuExam(null, vTOXExamenCualita);
				}
			} catch (Exception e) {
				e.printStackTrace();
				lCarga = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().println("<script language=\"JavaScript\">");
		if (!lCarga)
			response.getWriter().println(
					"alert('No todos los análisis han sido cargados!');");
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
}
