package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;
import com.micper.util.TNumeros;

//import java.lang.*;

/**
 * * Clase de configuracion para CFG de la pagina pg07030304
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg07030304CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg07030304CFG.png'>
 */
public class pg070308020CFG extends CFGListBase2 {
	private int iLFlag = 0;
	private int iBFlag = 0;

	public pg070308020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";

	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		try {

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void GuardarA() {
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").trim().length() > 0
					&& request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").trim().length() > 0
					&& request.getParameter("iCveLoteCualita") != null
					&& request.getParameter("iCveLoteCualita").trim().length() > 0
					&& request.getParameter("iCveExamCualita") != null
					&& request.getParameter("iCveExamCualita").trim().length() > 0) {
				TVTOXExamenCualita tvExamenCualita = new TVTOXExamenCualita();
				boolean bUpdate = false;

				tvExamenCualita.setIAnio(Integer.parseInt(request
						.getParameter("iAnio")));
				tvExamenCualita.setICveLaboratorio(Integer.parseInt(request
						.getParameter("iCveUniMed")));
				tvExamenCualita.setICveLoteCualita(Integer.parseInt(request
						.getParameter("iCveLoteCualita")));
				tvExamenCualita.setICveExamCualita(Integer.parseInt(request
						.getParameter("iCveExamCualita")));

				bUpdate = new TDTOXExamenCualita()
						.liberarLotes(tvExamenCualita);
				if (bUpdate)
					this.setILFlag(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Borrar() {
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").trim().length() > 0
					&& request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").trim().length() > 0
					&& request.getParameter("iCveLoteCualita") != null
					&& request.getParameter("iCveLoteCualita").trim().length() > 0
					&& request.getParameter("iCveExamCualita") != null
					&& request.getParameter("iCveExamCualita").trim().length() > 0) {
				TVTOXExamenCualita tvExamenCualita = new TVTOXExamenCualita();
				boolean bUpdate = false;

				tvExamenCualita.setIAnio(Integer.parseInt(request
						.getParameter("iAnio")));
				tvExamenCualita.setICveLaboratorio(Integer.parseInt(request
						.getParameter("iCveUniMed")));
				tvExamenCualita.setICveLoteCualita(Integer.parseInt(request
						.getParameter("iCveLoteCualita")));
				tvExamenCualita.setICveExamCualita(Integer.parseInt(request
						.getParameter("iCveExamCualita")));

				bUpdate = new TDTOXExamenCualita().borraLotes(tvExamenCualita);
				if (bUpdate)
					this.setIBFlag(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getIBFlag() {
		return iBFlag;
	}

	public int getILFlag() {
		return iLFlag;
	}

	private void setIBFlag(int iBFlag) {
		this.iBFlag = iBFlag;
	}

	private void setILFlag(int iLFlag) {
		this.iLFlag = iLFlag;
	}
}