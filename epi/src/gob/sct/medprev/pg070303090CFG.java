package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;
import java.util.*;

/**
 * * Clase de configuración para CFG List de TOXExamenCualita
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Juan Manuel Vazquez
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303090CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070306020CFG.png'>
 */
public class pg070303090CFG extends CFGListBase2 {
	public pg070303090CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {

		String cWhere = "";

		String cAnio = "";
		String cCveLoteCualita = "";
		String cCveExamCualita = "";
		String cCveEquipo = "";
		String cCveLaboratorio = "";
		String cSQL = "";
		int iCveExamenCualita = 0;

		/* Metodo Para Insertar un Nuevo Examen */
		if (request.getParameter("hdBoton") != null
				&& request.getParameter("hdBoton").toString()
						.compareToIgnoreCase("Guardar") == 0) {

			cAnio = request.getParameter("hdAnio");
			cCveLoteCualita = request.getParameter("hdCveLoteCualita");
			cCveExamCualita = request.getParameter("hdCveExamCualita");
			cCveEquipo = request.getParameter("hdCveEquipo");
			cCveLaboratorio = request.getParameter("hdCveLaboratorio");

			if (cAnio.compareTo("null") != 0 && cAnio.compareTo("") != 0
					&& cCveLoteCualita.compareTo("null") != 0
					&& cCveLoteCualita.compareTo("") != 0
					&& cCveExamCualita.compareTo("null") != 0
					&& cCveExamCualita.compareTo("") != 0
					&& cCveLaboratorio.compareTo("null") != 0
					&& cCveLaboratorio.compareTo("") != 0) {

				/* 1.- Inserta el Registro del Nuevo Examen */
				try {
					TDExamenCualita dExamenCualita = new TDExamenCualita();
					TVExamenCualita vExamenCualita = new TVExamenCualita();
					vExamenCualita.setIAnio(new Integer(request
							.getParameter("hdAnio")).intValue());
					vExamenCualita.setICveLoteCualita(new Integer(request
							.getParameter("hdCveLoteCualita")).intValue());
					vExamenCualita.setICveLaboratorio(new Integer(request
							.getParameter("hdCveLaboratorio")).intValue());
					TFechas dtFecha = new TFechas();
					vExamenCualita.setDtEntrega(dtFecha.TodaySQL());
					vExamenCualita.setICveEquipo(new Integer(request
							.getParameter("hdCveEquipo")).intValue());
					TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(
							true).getAttribute("UsrID");
					vExamenCualita.setICveUsuPrepara(new Integer(vUsuario
							.getICveusuario()).intValue());
					// 1.1.- Se actualiza el valor de
					// TOXEXAMENCUALITA.lTerminado = 1
					vExamenCualita.setICveExamCualita(Integer.parseInt(request
							.getParameter("hdCveExamCualita")));
					vExamenCualita.setLTerminado(1);
					dExamenCualita.updateTerminado(null, vExamenCualita); // UPDATE
																			// EXAMEN
																			// CUALITA
																			// lterminado
																			// =
																			// 1
					// 1.1.- Se actualiza el valor de
					// TOXEXAMENCUALITA.lTerminado = 1

					iCveExamenCualita = Integer.parseInt(request
							.getParameter("hdCveExamCualita")) + 1; // Se genera
																	// el
																	// siguiente
																	// examen
					vExamenCualita.setICveExamCualita(iCveExamenCualita);
					dExamenCualita.insert(null, vExamenCualita); // INSERT
																	// EXAMEN
																	// CUALITA
				} catch (Exception ex) {
					error("Insert", ex);
				}
				/* 1.- Inserta el Registro del Nuevo Examen */

				// 2.- Se obtiene el Carruceles y Posiciones para generar
				// TOXEXAMANALISIS
				try {
					TDTOXEquipo dTOXEquipo = new TDTOXEquipo();
					TVTOXEquipo vTOXEquipo = new TVTOXEquipo();
					Vector vEquipo = new Vector();
					String cFiltro = "";
					String cOrdena = "";
					cFiltro = " WHERE TOXEquipo.lCuantCual = 0 AND TOXEquipo.iCveEquipo = "
							+ request.getParameter("hdCveEquipo");
					cOrdena = " ORDER BY TOXEquipo.iCveEquipo ";

					vEquipo = dTOXEquipo.FindByAll(cFiltro, cOrdena);
					int iCarruseles = 0;
					int iPosiciones = 0;
					if (vEquipo.size() > 0) {
						vTOXEquipo = (TVTOXEquipo) vEquipo.get(0);
						iCarruseles = vTOXEquipo.getICarrucel();
						iPosiciones = vTOXEquipo.getIPosiciones();
					}

					TDTOXExamAnalisis dTOXExamAnalisis = new TDTOXExamAnalisis();
					TVTOXExamAnalisis vTOXExamAnalisis = new TVTOXExamAnalisis();
					vTOXExamAnalisis.setIAnio(new Integer(request
							.getParameter("hdAnio")).intValue());
					vTOXExamAnalisis.setICveLaboratorio(new Integer(request
							.getParameter("hdCveLaboratorio")).intValue());
					vTOXExamAnalisis.setICveLoteCualita(new Integer(request
							.getParameter("hdCveLoteCualita")).intValue());
					vTOXExamAnalisis.setICveExamCualita(iCveExamenCualita);// pasar
																			// el
																			// examen
																			// recien
																			// generado
					Vector vAnalisis = new Vector();

					cFiltro = " Where TOXAnalisis.iAnio = "
							+ request.getParameter("hdAnio")
							+ " and TOXAnalisis.iCveLaboratorio = "
							+ request.getParameter("hdCveLaboratorio")
							+ " and TOXAnalisis.iCveLoteCualita = "
							+ request.getParameter("hdCveLoteCualita")
							+
							// " and TOXAnalisis.iCveExamCualita = " +
							// request.getParameter("hdCveExamCualita") +
							" and TOXAnalisis.lAutorizado = 0 "
							+ "order by TOXAnalisis.iAnio, TOXAnalisis.iCveLaboratorio, "
							+ " TOXAnalisis.iCveAnalisis";

					TDTOXAnalisis dToxAnalisis = new TDTOXAnalisis();
					TVTOXAnalisis vToxAnalisis = new TVTOXAnalisis();
					vAnalisis = dToxAnalisis.FindByAll(cFiltro);
					vToxAnalisis.setiCveExamCualita(new Integer(
							iCveExamenCualita));
					vToxAnalisis.setiAnio(new Integer(request
							.getParameter("hdAnio")));
					vToxAnalisis.setiCveLaboratorio(new Integer(request
							.getParameter("hdCveLaboratorio")));
					vToxAnalisis.setiCveLoteCualita(new Integer(request
							.getParameter("hdCveLoteCualita")));
					dToxAnalisis.updateExamA(null, vToxAnalisis); // UPDATE
																	// EXAMANALISIS
					/* 2.1 INSERT TOXEXAMANALISIS CONTROLES Y MUESTRAS */
					int Carr = 1;
					int Pos = 0;
					if (vAnalisis.size() > 0) {
						for (int i = 0; i < vAnalisis.size(); i++) {
							Pos++;
							vToxAnalisis = (TVTOXAnalisis) vAnalisis.get(i);
							vTOXExamAnalisis.setICveAnalisis(vToxAnalisis
									.getiCveAnalisis().intValue());
							vTOXExamAnalisis.setICarrusel(Carr);
							vTOXExamAnalisis.setIPosicion(Pos);
							vTOXExamAnalisis.setIAnio(Integer.parseInt(cAnio)); // Año
							vTOXExamAnalisis.setICveLoteCualita(Integer
									.parseInt(cCveLoteCualita)); // Lote
							vTOXExamAnalisis
									.setICveExamCualita(iCveExamenCualita); // Examen
																			// Recien
																			// Generado
							vTOXExamAnalisis.setICveLaboratorio(Integer
									.parseInt(cCveLaboratorio)); // Laboratorio
							dTOXExamAnalisis.insert(null, vTOXExamAnalisis); // INSERT
																				// TOXEXAMANALISIS
							if (Pos == iPosiciones) {
								Pos = 0;
								Carr++;
							}
							if (Carr > iCarruseles)
								Carr = 1;
						}
					}
					/* 2.1 INSERT TOXEXAMANALISIS CONTROLES Y MUESTRAS */
				} catch (Exception e) {
				}
				// 2.- Se obtiene el Carruceles y Posiciones
			}
		}
		try {
			cWhere = "";
			if (cCondicion != null && cCondicion.compareToIgnoreCase("") != 0) {
				cWhere = " and " + cCondicion;
			}
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null) {
				cWhere += " and iAnio = " + request.getParameter("iAnio");
				cWhere += " and iCveLaboratorio = "
						+ request.getParameter("iCveUniMed");
				TDExamenCualita dExamenCualita = new TDExamenCualita();
				vDespliega = dExamenCualita.FindByReanalisis(cWhere, cOrden);
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
