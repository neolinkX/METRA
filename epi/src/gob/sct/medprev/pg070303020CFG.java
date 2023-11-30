package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
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
public class pg070303020CFG extends CFGListBase2 {
	public String cImprimir = "";

	public pg070303020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		TDMuestra dMuestra = new TDMuestra();
		TVMuestra vMuestra = new TVMuestra();
		TDMuestraLote dMuestraLote = new TDMuestraLote();
		TVMuestraLote vMuestraLote = new TVMuestraLote();
		String cMensaje = "";
		try {
			vMuestra.setCFiltrar(request.getParameter("hdLCondicion"));
			vMuestra.setCOrdenar(request.getParameter("hdLOrdenar"));
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iCveUniMed") != null) {
				vMuestra.setIAnio(new Integer(request.getParameter("iAnio"))
						.intValue());
				vMuestra.setICveUniMed(new Integer(request
						.getParameter("iCveUniMed")).intValue());
				if (request.getParameter("iCveUnidad") != null)
					vMuestra.setICveEnvio(new Integer(request
							.getParameter("iCveUnidad")).intValue());
				vDespliega = dMuestra.FindByAll2(vMuestra);
			}
			int iCveLoteCualita = 0;
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton").toString()
							.compareToIgnoreCase("Generar") == 0) {
				if (request.getParameter("iCveLoteCualita")
						.compareToIgnoreCase("0") == 0) {
					// LOTE-CUALITA
					TDLoteCualita dLoteCualita = new TDLoteCualita();
					TVLoteCualita vLoteCualita = new TVLoteCualita();
					vLoteCualita.setIAnio(new Integer(request
							.getParameter("iAnio")).intValue());
					vLoteCualita.setICveLaboratorio(new Integer(request
							.getParameter("iCveUniMed")).intValue());
					iCveLoteCualita = dLoteCualita
							.FindLastLoteCualita(vLoteCualita) + 1;
					vLoteCualita.setICveLoteCualita(iCveLoteCualita);
					vLoteCualita.setDtGeneracion(null);
					dLoteCualita.insert(null, vLoteCualita);
					// LOTE-CUALITA
					vMuestraLote.setICveLoteCualita(iCveLoteCualita);
					cMensaje = "Se gener� el lot� "
							+ vLoteCualita.getIAnio() + "/" + iCveLoteCualita;
				} else {
					vMuestraLote.setICveLoteCualita(new Integer(request
							.getParameter("iCveLoteCualita")).intValue());
				}

				for (int i = 0; i < vDespliega.size(); i++) {
					vMuestra = (TVMuestra) vDespliega.get(i);
					if (request.getParameter("checked"
							+ vMuestra.getICveMuestra()) != null) {
						// MUESTRA
						vMuestra.setIAnio(new Integer(request
								.getParameter("iAnio")).intValue());
						vMuestra.setICveMuestra(vMuestra.getICveMuestra());
						vMuestra.setLIntegraLote(1);
						dMuestra.update2(vMuestra);
						// MUESTRA

						// MUESTRA-LOTE
						vMuestraLote.setIAnio(new Integer(request
								.getParameter("iAnio")).intValue());
						vMuestraLote.setICveMuestra(vMuestra.getICveMuestra());
						vMuestraLote.setICveLaboratorio(new Integer(request
								.getParameter("iCveUniMed")).intValue());
						dMuestraLote.insert(null, vMuestraLote);
						// MUESTRA-LOTE
					}
				}
				vMuestra.setCFiltrar(request.getParameter("hdLCondicion"));
				vMuestra.setCOrdenar(request.getParameter("hdLOrdenar"));
				if (request.getParameter("iAnio") != null
						&& request.getParameter("iCveUniMed") != null) {
					vMuestra.setIAnio(new Integer(request.getParameter("iAnio"))
							.intValue());
					vMuestra.setICveUniMed(new Integer(request
							.getParameter("iCveUniMed")).intValue());
					if (request.getParameter("iCveUnidad") != null)
						vMuestra.setICveEnvio(new Integer(request
								.getParameter("iCveUnidad")).intValue());
					vDespliega = dMuestra.FindByAll2(vMuestra);
				}
				// Crear el registro de los env�o integrados por lote
				TDTOXEnvioXLote DEnvio = new TDTOXEnvioXLote();
				String cFiltro = " Where L.iAnio = "
						+ request.getParameter("iAnio")
						+ "   and L.iCveLaboratorio = "
						+ request.getParameter("iCveUniMed")
						+ "   and L.iCveLoteCualita = "
						+ vMuestraLote.getICveLoteCualita();
				// Se borran los registros actuales
				if (request.getParameter("iCveLoteCualita")
						.compareToIgnoreCase("0") != 0) {
					DEnvio.delete(null, cFiltro);
				}
				Vector VEnvio = (Vector) DEnvio.FindEnvXLote(cFiltro);
				for (int i = 0; i < VEnvio.size(); i++) {
					TVTOXEnvioXLote VEnvXLote = new TVTOXEnvioXLote();
					VEnvXLote.VLote.setIAnio(new Integer(request
							.getParameter("iAnio")).intValue());
					VEnvXLote.VLote.setICveLaboratorio(new Integer(request
							.getParameter("iCveUniMed")).intValue());
					VEnvXLote.VLote.setICveLoteCualita(vMuestraLote
							.getICveLoteCualita());
					VEnvXLote.VEnvio.setICveUniMed(((TVTOXEnvio) VEnvio.get(i))
							.getICveUniMed());
					VEnvXLote.VEnvio.setICveEnvio(((TVTOXEnvio) VEnvio.get(i))
							.getICveEnvio());
					VEnvXLote.setIOrden(i + 1);
					DEnvio.insert(null, VEnvXLote);
				}

				// enviar mensaje para confirmar la actualizaci�n del lote.
				if (cMensaje.length() <= 0)
					cMensaje = "Se actualiz� el lot� "
							+ vMuestra.getIAnio() + "/"
							+ vMuestraLote.getICveLoteCualita();
				vErrores.acumulaError(cMensaje, 0, "");
			}

			if (!vDespliega.isEmpty()) {
				UpdStatus = "SaveCancelOnly";
				cImprimir = "Imprimir";
			} else {
				UpdStatus = "Hidden";
				NavStatus = "Hidden";
				OtroStatus = "Hidden";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}
}
