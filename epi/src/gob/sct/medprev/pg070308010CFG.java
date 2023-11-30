package gob.sct.medprev;

import java.util.*;
import java.text.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.util.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * Clase de configuración para Clase de configuración para Listado de
 * EXPExamAplica
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Esteban Viveros
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070105000CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070105000CFG.png'>
 */
public class pg070308010CFG extends CFGListBase2 {
	private int iLFlag = 0;
	private int iBFlag = 0;

	public pg070308010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		try {

			if (request.getParameter("hdBoton") != null
					&& (request.getParameter("hdBoton").equals("Buscar") || request
							.getParameter("hdBoton").equals("GuardarA"))) {

				TDTOXMuestra dEXPExamAplica = new TDTOXMuestra();
				String cAnio = "";
				String cCveMuestra = "";

				if (request.getParameter("iAnio") != null)
					cAnio = request.getParameter("iAnio");
				if (request.getParameter("iCveMuestra") != null
						&& request.getParameter("iCveMuestra").trim().length() > 0)
					cCveMuestra = request.getParameter("iCveMuestra");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	/*
	 * public void Guardar(){ try{ if(request.getParameter("iAnio") != null &&
	 * request.getParameter("iAnio").trim().length() > 0 &&
	 * request.getParameter("iCveMuestra") != null &&
	 * request.getParameter("iCveMuestra").trim().length() > 0){
	 * 
	 * boolean bUpdate = false;
	 * 
	 * bUpdate = new TDTOXMuestra().LiberaMuestra(request.getParameter("iAnio"),
	 * request.getParameter("iCveMuestra"));
	 * 
	 * if(bUpdate) this.setILFlag(1); } } catch(Exception e){
	 * e.printStackTrace(); } }
	 */

	public void GuardarA() {
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").trim().length() > 0
					&& request.getParameter("iCveMuestra") != null
					&& request.getParameter("iCveMuestra").trim().length() > 0) {

				boolean bUpdate = false;
				bUpdate = new TDTOXMuestra().LiberaMuestra(
						request.getParameter("iAnio"),
						request.getParameter("iCveMuestra"));
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
					&& request.getParameter("iCveMuestra") != null
					&& request.getParameter("iCveMuestra").trim().length() > 0) {

				boolean bUpdate = false;
				bUpdate = new TDTOXMuestra().BorrarMuestra(
						request.getParameter("iAnio"),
						request.getParameter("iCveMuestra"));
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