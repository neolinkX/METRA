package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.util.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.*;

/**
 * * Clase de configuración para CFG pagina pg070802010
 * <p>
 * Administración y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>Leonel Popoca G.
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070802010CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070802010CFG.png'>
 */
public class pg070802010CFG extends CFGListBase2 {
	private StringBuffer sbReporte = new StringBuffer();

	public pg070802010CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
		cPaginas = "pg070802011.jsp|";
	}

	/**
	 * Método FillVector
	 */
	public void fillVector() {
		TDALMArticulo dALMArticulo = new TDALMArticulo();
		try {
			String cWhere = "";
			String cOrderBy = "";
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton")
							.equals("Generar Reporte")) {
				if (cCondicion.trim().length() == 0)
					cOrderBy = " WHERE ALMTpoArticulo.lActivo = 1 ORDER BY ALMTpoArticulo.cDscTpoArticulo, ALMFamilia.cDscFamilia, ALMArticulo.cDscBreve  ";
				else
					cOrderBy = " AND ALMTpoArticulo.lActivo = 1 ORDER BY ALMTpoArticulo.cDscTpoArticulo, ALMFamilia.cDscFamilia, ALMArticulo.cDscBreve  ";
			} else
				cOrderBy = " ORDER BY ALMArticulo.iCveArticulo ";

			if (cCondicion.compareTo("") != 0) {
				cWhere = " where " + cCondicion;
			}
			if (cOrden.compareTo("") != 0) {
				cOrderBy = cOrden;
			}

			vDespliega = dALMArticulo.FindByAll(cWhere, cOrderBy);
			if (request.getParameter("hdBoton") != null
					&& request.getParameter("hdBoton")
							.equals("Generar Reporte") && vDespliega != null
					&& vDespliega.size() > 0) {
				this.setExcel(vDespliega);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			error("FillVector", ex);
		}
	}

	private void setExcel(Vector v) {
		try {
			TExcel repExcel = new TExcel("07");
			String cTipo = "";
			String cTipoB = "";
			String cFamilia = "";
			String cFamiliaB = "";
			int iRen = 12;
			for (int i = 0; i < v.size(); i++) {
				TVALMArticulo tvALMArticulo = (TVALMArticulo) v.get(i);
				cTipo = tvALMArticulo.getcDscTpoArticulo();
				cFamilia = tvALMArticulo.getcDscFamilia();
				if (!cTipo.equals(cTipoB)) {
					cTipoB = tvALMArticulo.getcDscTpoArticulo();
					repExcel.comFillCells("A", iRen, "C", iRen, 16);
					repExcel.comFontBold("A", iRen, "C", iRen);
					repExcel.comDespliega("A", iRen, "TIPO DE ARTÍCULO: ");
					repExcel.comDespliega("B", iRen, cTipo);
					iRen++;
				}
				if (!cFamilia.equals(cFamiliaB)) {
					cFamiliaB = tvALMArticulo.getcDscFamilia();
					repExcel.comFillCells("A", iRen, "C", iRen, 15);
					repExcel.comFontBold("A", iRen, "C", iRen + 1);
					repExcel.comDespliega("A", iRen, "FAMILIA: ");
					repExcel.comDespliega("B", iRen, cFamilia);
					iRen++;
					repExcel.comDespliega("A", iRen, "CANTIDAD");
					repExcel.comDespliega("B", iRen, "DESCRIPCIÓN");
					repExcel.comDespliega("C", iRen, "UNIDAD");
					iRen++;
				}
				repExcel.comDespliega("A", iRen, tvALMArticulo.getiCveUniSum()
						+ "");
				repExcel.comDespliega("B", iRen, tvALMArticulo.getcDscBreve());
				repExcel.comDespliega("C", iRen, tvALMArticulo.getcDscUniAlm());
				iRen++;
			}
			sbReporte.append(repExcel.creaActiveX("pg070802010",
					"Catalogo de Productos", false, false, true));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public StringBuffer getSbReporte() {
		return sbReporte;
	}
}