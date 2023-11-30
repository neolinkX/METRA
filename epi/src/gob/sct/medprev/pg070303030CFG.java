package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;
import java.sql.Date;

/**
 * * Clase de configuracion para Generaci�n de Lotes de Muetras Recibidas
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Marco Antonio Hern�ndez Garc�a
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070303020.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070303020.png'>
 */
public class pg070303030CFG extends CFGListBase2 {
	public String cImprimir = "";
	private java.util.Vector VOrden;

	public pg070303030CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMuestraLote dMuestraLote = new TDMuestraLote();
		TVMuestraLote vMuestraLote = new TVMuestraLote();
		TDTOXEnvioXLote DEnvio = new TDTOXEnvioXLote();
		String cFiltro;
		try {
			vMuestraLote.setCFiltrar(request.getParameter("hdLCondicion"));
			vMuestraLote.setCOrdenar(request.getParameter("hdLOrdenar"));
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareToIgnoreCase("Orden") == 0) {
				// Buscar los objetos
				cFiltro = " Where EL.iAnio = " + request.getParameter("iAnio")
						+ "   and EL.iCveLaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and EL.iCveLoteCualita = "
						+ request.getParameter("iCveLoteCualita");
				VOrden = DEnvio.FindByAll(cFiltro);
				cFiltro = " Where L.iAnio = " + request.getParameter("iAnio")
						+ "   and L.iCveLaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and L.iCveLoteCualita = "
						+ request.getParameter("iCveLoteCualita");

				DEnvio.delete(null, cFiltro);
				TVTOXEnvioXLote VEL = new TVTOXEnvioXLote();
				Vector Actualizar = new Vector();
				for (int i = 0; i < VOrden.size(); i++) {
					VEL = (TVTOXEnvioXLote) VOrden.get(i);
					String cValor = request.getParameter(
							"FOrden" + VEL.VEnvio.getICveUniMed() + "-"
									+ VEL.VEnvio.getICveEnvio()).toString();
					if (cValor != null)
						VEL.setIOrden(new Integer(cValor).intValue());
					else
						VEL.setIOrden(1);
					DEnvio.insert(null, VEL);
				}
			}

			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveLoteCualita") != null) {
				vMuestraLote
						.setIAnio(new Integer(request.getParameter("iAnio"))
								.intValue());
				vMuestraLote.setICveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")).intValue());
				vMuestraLote.setICveLoteCualita(new Integer(request
						.getParameter("iCveLoteCualita")).intValue());
				cFiltro = " Where EL.iAnio = " + request.getParameter("iAnio")
						+ "   and EL.iCveLaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and EL.iCveLoteCualita = "
						+ request.getParameter("iCveLoteCualita");

				VOrden = DEnvio.FindByAll(cFiltro);

				vDespliega = dMuestraLote.FindByLoteCualita(vMuestraLote);
			}
			// QUITAR MUESTRA DEL LOTE
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareToIgnoreCase("Guardar") == 0) {
				// Ciclo para borrar las muestras seleccionadas.
				for (int i = 0; i < vDespliega.size(); i++) {
					vMuestraLote = (TVMuestraLote) vDespliega.get(i);
					if (request.getParameter("checked"
							+ vMuestraLote.getICveMuestra()) != null) {
						// QUITAR MUESTRA
						TDMuestra dMuestra = new TDMuestra();
						TVMuestra vMuestra = new TVMuestra();
						vMuestra.setIAnio(new Integer(request
								.getParameter("iAnio")).intValue());
						vMuestra.setICveMuestra(vMuestraLote.getICveMuestra());
						vMuestra.setLIntegraLote(0);
						dMuestra.update2(vMuestra);
						// QUITAR MUESTRA

						// MUESTRA-LOTE
						vMuestraLote.setIAnio(new Integer(request
								.getParameter("iAnio")).intValue());
						vMuestraLote.setICveLaboratorio(new Integer(request
								.getParameter("iCveUniMed")).intValue());
						vMuestraLote.setICveLoteCualita(new Integer(request
								.getParameter("iCveLoteCualita")).intValue());
						dMuestraLote.delete(null, vMuestraLote);
						// MUESTRA-LOTE
						// BORRAR-LOTE
					}
				}
				// En caso de que ya no existan Muestras relacionadas a los
				// Lotes, el Lote se Borra de la tabla TOXLoteCualita:
				// BORRAR-LOTE
				Vector vTemporal = new Vector();
				cFiltro = " Where TOXMuestraLote.iAnio = "
						+ request.getParameter("iAnio")
						+ "   and TOXMuestraLote.iCveLaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and TOXMuestraLote.iCveLoteCualita = "
						+ request.getParameter("iCveLoteCualita");
				vTemporal = dMuestraLote.FindByAll(cFiltro, "");
				if (vTemporal.size() == 0) {
					TDLoteCualita dLoteCualita = new TDLoteCualita();
					TVLoteCualita vLoteCualita = new TVLoteCualita();
					vLoteCualita.setIAnio(new Integer(request
							.getParameter("iAnio")).intValue());
					vLoteCualita.setICveLaboratorio(new Integer(request
							.getParameter("iCveUniMed")).intValue());
					vLoteCualita.setICveLoteCualita(new Integer(request
							.getParameter("iCveLoteCualita")).intValue());
					dLoteCualita.delete(null, vLoteCualita);
				} else {
					// Crear el registro de los env�o integrados por lote
					// Se borran los registros actuales
					cFiltro = " Where L.iAnio = "
							+ request.getParameter("iAnio")
							+ "   and L.iCveLaboratorio = "
							+ request.getParameter("iCveUniMed")
							+ "   and L.iCveLoteCualita = "
							+ request.getParameter("iCveLoteCualita");
					DEnvio.delete(null, cFiltro);
					Vector VEnvio = (Vector) DEnvio.FindEnvXLote(cFiltro);
					for (int i = 0; i < VEnvio.size(); i++) {
						TVTOXEnvioXLote VEnvXLote = new TVTOXEnvioXLote();
						VEnvXLote.VLote.setIAnio(new Integer(request
								.getParameter("iAnio")).intValue());
						VEnvXLote.VLote.setICveLaboratorio(new Integer(request
								.getParameter("iCveUniMed")).intValue());
						VEnvXLote.VLote.setICveLoteCualita(new Integer(request
								.getParameter("iCveLoteCualita")).intValue());
						VEnvXLote.VEnvio.setICveUniMed(((TVTOXEnvio) VEnvio
								.get(i)).getICveUniMed());
						VEnvXLote.VEnvio.setICveEnvio(((TVTOXEnvio) VEnvio
								.get(i)).getICveEnvio());
						VEnvXLote.setIOrden(i + 1);
						DEnvio.insert(null, VEnvXLote);
					}
				}

				vMuestraLote.setCFiltrar(request.getParameter("hdLCondicion"));
				vMuestraLote.setCOrdenar(request.getParameter("hdLOrdenar"));
				if (request.getParameter("iAnio") != null
						&& request.getParameter("iCveUniMed") != null
						&& request.getParameter("iCveLoteCualita") != null) {
					vMuestraLote.setIAnio(new Integer(request
							.getParameter("iAnio")).intValue());
					vMuestraLote.setICveLaboratorio(new Integer(request
							.getParameter("iCveUniMed")).intValue());
					vMuestraLote.setICveLoteCualita(new Integer(request
							.getParameter("iCveLoteCualita")).intValue());
					vDespliega = dMuestraLote.FindByLoteCualita(vMuestraLote);
				}
			}
			// QUITAR MUESTRAS DEL LOTE

			// AUTORIZAR - GENERAR ANALISIS Y EXAMEN
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareToIgnoreCase("Autorizar") == 0) {
				// LOTE-CUALITA

				TDLoteCualita dLoteCualita = new TDLoteCualita();
				TVLoteCualita vLoteCualita = new TVLoteCualita();
				vLoteCualita
						.setIAnio(new Integer(request.getParameter("iAnio"))
								.intValue());
				vLoteCualita.setICveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")).intValue());
				vLoteCualita.setICveLoteCualita(new Integer(request
						.getParameter("iCveLoteCualita")).intValue());
				TFechas dtFecha = new TFechas();
				vLoteCualita.setDtGeneracion(dtFecha.TodaySQL()); // UPDATE A
																	// LOTECUALITA.DTGENERACION
				dLoteCualita.update(null, vLoteCualita);

				// LOTE-CUALITA

				TVTOXAnalisis vToxAnalisis = new TVTOXAnalisis();
				TDTOXAnalisis dToxAnalisis = new TDTOXAnalisis();
				vToxAnalisis
						.setiAnio(new Integer(request.getParameter("iAnio")));
				vToxAnalisis.setiCveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")));
				int iMaxAnalisis = dToxAnalisis.FindMax(vToxAnalisis);
				double dControles = new Double(vParametros.getPropEspecifica(
						"Controles").toString()).doubleValue();
				double x = vDespliega.size() * dControles;
				int iCtrolExt = 0, iNoControles = 0;
				if (request.getParameter("FCtrolExt") != null)
					iCtrolExt = Integer.parseInt(request.getParameter(
							"FCtrolExt").toString());
				iNoControles = (int) Math.ceil(x);
				/*
				 * if (iNoControles < iCtrolExt) iNoControles = iCtrolExt;
				 */
				if (iNoControles < Integer.parseInt(this.vParametros
						.getPropEspecifica("TOXMinCtrolPres"))) {
					iNoControles = Integer.parseInt(this.vParametros
							.getPropEspecifica("TOXMinCtrolPres"));
				}

				Vector vTemporal = new Vector();
				Vector vTemporal2 = new Vector();

				Vector vTemp = new Vector();
				vTemp.setSize(new Integer((iMaxAnalisis + vDespliega.size()
						+ iNoControles + 1 + iCtrolExt)).intValue());

				int itotal = vDespliega.size() + iNoControles + iCtrolExt;
				int Inicio = iMaxAnalisis + 1;
				int Fin = iMaxAnalisis + itotal;

				for (int i = Inicio; i <= Fin; i++) {
					vTemp.set(i, new Integer(0));
					// System.out.println("Posicion insertada " + i);
				}
				// Se obtiene el CveCtrolCalibra
				TVTOXCtrolCalibra vCtrolCalibra = new TVTOXCtrolCalibra();
				// Se obtiene el CveCtrolCalibra

				// Se obtiene el Carruceles y Posiciones
				TDTOXEquipo dTOXEquipo = new TDTOXEquipo();
				TVTOXEquipo vTOXEquipo = new TVTOXEquipo();
				Vector vEquipo = new Vector();
				cFiltro = " WHERE TOXEquipo.lCuantCual = 0 AND TOXEquipo.iCveEquipo = "
						+ request.getParameter("iCveEquipo");
				String cOrden = " ORDER BY TOXEquipo.iCveEquipo ";
				vEquipo = dTOXEquipo.FindByAll(cFiltro, cOrden);
				int iCarruseles = 0;
				int iPosiciones = 0;
				if (vEquipo.size() > 0) {
					vTOXEquipo = (TVTOXEquipo) vEquipo.get(0);
					iCarruseles = vTOXEquipo.getICarrucel();
					iPosiciones = vTOXEquipo.getIPosiciones();
				}
				// Se obtiene el Carruceles y Posiciones del Equipo

				// Se inserta el registro en la tabla TOXExamenCualita
				TDExamenCualita dExamenCualita = new TDExamenCualita();
				TVExamenCualita vExamenCualita = new TVExamenCualita();
				vExamenCualita.setIAnio(new Integer(request
						.getParameter("iAnio")).intValue());
				vExamenCualita.setICveLoteCualita(new Integer(request
						.getParameter("iCveLoteCualita")).intValue());
				vExamenCualita.setICveExamCualita(new Integer(1).intValue());
				vExamenCualita.setICveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")).intValue());
				vExamenCualita.setDtEntrega(dtFecha.TodaySQL());
				vExamenCualita.setICveEquipo(new Integer(request
						.getParameter("iCveEquipo")).intValue());
				TVUsuario vUsuario = (TVUsuario) this.httpReq.getSession(true)
						.getAttribute("UsrID");
				vExamenCualita.setICveUsuPrepara(new Integer(vUsuario
						.getICveusuario()).intValue());
				dExamenCualita.insert(null, vExamenCualita); /*
															 * INSERT EXAMEN
															 * CUALITA
															 */
				// Se inserta el registro en la tabla TOXExamenCualita

				vToxAnalisis
						.setiAnio(new Integer(request.getParameter("iAnio")));
				vToxAnalisis.setiCveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")));
				vToxAnalisis.setiCveLoteCualita(new Integer(request
						.getParameter("iCveLoteCualita")));
				vToxAnalisis.setiCveMuestra(0);
				vToxAnalisis.setlControl(new Integer(1));
				vToxAnalisis.setlResultado(new Integer(0));
				vToxAnalisis.setlPresuntoPost(new Integer(0));
				vToxAnalisis.setlAutorizado(new Integer(0));
				vToxAnalisis.setiCveExamCualita(new Integer(1));
				vToxAnalisis.setiCveUsuAutoriza(new Integer(1));

				// iTotal
				// Se obtienen los numeros de controles,las posiciones de los
				// controles al azar y se inserta en la base de datos
				TVTOXAnalisis vAnalisis = new TVTOXAnalisis();
				Vector vControles = (Vector) new TDTOXLoteCuantita()
						.obtenerControles(request.getParameter("iCveUniMed")
								.toString(), iNoControles, itotal);
				int iPosControl = 0;
				for (int i = 0; i < vControles.size(); i++) {
					vAnalisis = (TVTOXAnalisis) vControles.get(i);
					iPosControl = vAnalisis.getiCveAnalisis().intValue()
							+ Inicio;
					do {
						if (vTemp.get(iPosControl).toString()
								.equalsIgnoreCase("1")) {
							iPosControl = new TDTOXLoteCuantita().aleatorio(
									new Random(), itotal) + Inicio;
						}
					} while (vTemp.get(iPosControl) != null
							&& vTemp.get(iPosControl).toString()
									.equalsIgnoreCase("1"));
					vToxAnalisis.setiCveAnalisis(new Integer(iPosControl));
					vTemp.set(iPosControl, new Integer(1));
					vToxAnalisis.setiCveCtrolCalibra(new Integer(vAnalisis
							.getiCveCtrolCalibra().toString()));
					dToxAnalisis.insert(null, vToxAnalisis); /*
															 * INSERT
															 * TOXANALISIS
															 * CONTROLES
															 */
				}
				// Se obtienen los numeros de controles,las posiciones de los
				// controles al azar y se inserta en la base de datos

				// Se insertan las muestras en sus respectivas posiciones
				int j = 0;
				vToxAnalisis.setiCveCtrolCalibra(new Integer(0));
				for (int i = Inicio; i <= Fin - iCtrolExt; i++) {
					if (vTemp.get(i).toString().compareToIgnoreCase("0") == 0) {

						vMuestraLote = (TVMuestraLote) vDespliega.get(j);
						vToxAnalisis.setiCveAnalisis(new Integer(i));
						vToxAnalisis.setiCveMuestra(vMuestraLote
								.getICveMuestra());
						vToxAnalisis.setlControl(new Integer(0));
						vToxAnalisis.setlResultado(new Integer(0));
						vToxAnalisis.setlPresuntoPost(new Integer(0));
						vToxAnalisis.setlAutorizado(new Integer(0));
						vToxAnalisis.setiCveExamCualita(new Integer(1));
						vToxAnalisis.setiCveUsuAutoriza(new Integer(1));
						vTemp.set(i, new Integer(1));
						dToxAnalisis.insert(null, vToxAnalisis); /*
																 * INSERT
																 * TOXANALISIS
																 * MUESTRAS
																 */
						j++;
					}
				}
				// Se insertan las muestras en sus respectivas posiciones
				// Insertar controles externos.
				for (int i = (iCtrolExt - 1); i >= 0; i--) {
					vToxAnalisis.setiCveAnalisis(new Integer(Fin - i));
					vToxAnalisis.setiCveMuestra(0);
					vToxAnalisis.setlControl(new Integer(1));
					vToxAnalisis.setlResultado(new Integer(0));
					vToxAnalisis.setlPresuntoPost(new Integer(0));
					vToxAnalisis.setlAutorizado(new Integer(0));
					vToxAnalisis.setiCveExamCualita(new Integer(1));
					vToxAnalisis.setiCveUsuAutoriza(new Integer(0));
					vTemp.set(Fin - i, new Integer(1));
					dToxAnalisis.insert(null, vToxAnalisis); /*
															 * INSERT
															 * TOXANALISIS
															 * MUESTRAS
															 */
				}

				// Se genera TOXExamAnalisis
				TDTOXExamAnalisis dTOXExamAnalisis = new TDTOXExamAnalisis();
				TVTOXExamAnalisis vTOXExamAnalisis = new TVTOXExamAnalisis();
				vTOXExamAnalisis.setIAnio(new Integer(request
						.getParameter("iAnio")).intValue());
				vTOXExamAnalisis.setICveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")).intValue());
				vTOXExamAnalisis.setICveLoteCualita(new Integer(request
						.getParameter("iCveLoteCualita")).intValue());
				vTOXExamAnalisis.setICveExamCualita(1);
				Vector vNumAnalisis = new Vector();
				cFiltro = " Where TOXAnalisis.iAnio = "
						+ request.getParameter("iAnio")
						+ "   and TOXAnalisis.iCveLaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and TOXAnalisis.iCveAnalisis >= " + Inicio
						+ "   and TOXAnalisis.iCveAnalisis <= " + Fin;
				vNumAnalisis = dToxAnalisis.FindByAll(cFiltro);
				int Carr = 1;
				int Pos = 0;
				if (vNumAnalisis.size() > 0) {
					for (int i = 0; i < vNumAnalisis.size(); i++) {
						Pos++;
						vToxAnalisis = (TVTOXAnalisis) vNumAnalisis.get(i);
						vTOXExamAnalisis.setICveAnalisis(vToxAnalisis
								.getiCveAnalisis().intValue());
						vTOXExamAnalisis.setICarrusel(Carr);
						vTOXExamAnalisis.setIPosicion(Pos);
						dTOXExamAnalisis.insert(null, vTOXExamAnalisis); /*
																		 * INSERT
																		 * TOXEXAMANALISIS
																		 * CONTROLES
																		 * Y
																		 * MUESTRAS
																		 */
						if (Pos == iPosiciones) {
							Pos = 0;
							Carr++;
						}
						if (Carr > iCarruseles)
							Carr = 1;
					}
				}
				// Se genera TOXExamAnalisis
				vErrores.acumulaError("Se autoriz� el lote.", 0, "");
			}
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveLoteCualita") != null) {
				vMuestraLote
						.setIAnio(new Integer(request.getParameter("iAnio"))
								.intValue());
				vMuestraLote.setICveLaboratorio(new Integer(request
						.getParameter("iCveUniMed")).intValue());
				vMuestraLote.setICveLoteCualita(new Integer(request
						.getParameter("iCveLoteCualita")).intValue());
				vDespliega = dMuestraLote.FindByLoteCualita(vMuestraLote);

			}

			// GENERAR ANALISIS Y EXAMEN

			if (!vDespliega.isEmpty()) {
				UpdStatus = "SaveCancelOnly";
				cImprimir = "Imprimir";
			} else {
				UpdStatus = "Hidden";
				NavStatus = "Hidden";
				OtroStatus = "Hidden";
			}
		} catch (Exception ex) {
			error("FillVector", ex);
			ex.printStackTrace();
		}
	}

	public java.util.Vector getVOrden() {
		return VOrden;
	}

	public void setVOrden(java.util.Vector VOrden) {
		this.VOrden = VOrden;
	}
}
