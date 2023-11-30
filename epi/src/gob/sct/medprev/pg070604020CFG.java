package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;
import com.micper.util.TExcel;
import com.micper.util.TFechas;
import java.text.*;

/**
 * * Clase de configuracion para Clase para el control de la tabla de
 * ALMArticulo
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
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803012CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803012CFG.java.png'>
 */
public class pg070604020CFG extends CFGListBase2 {
	boolean lCondicion = false;
	private StringBuffer activeX = new StringBuffer("");
	TFechas tFecha = new TFechas("07");
	private Vector vRegistros = new Vector();
	public Vector vTipoMantto;
	public int iEjercicio = tFecha.getIntYear(tFecha.TodaySQL());
	public int iEjercicioAct = iEjercicio;
	public int iTipoMantto = 1;
	public int iValorOrden = 1;

	public pg070604020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public Vector getVTipoMantto() {
		return this.vTipoMantto;
	}

	public String getActiveX() {
		return activeX.toString();
	}

	public StringBuffer Report(Vector vResultado, int iEjercicio,
			int iTipoMantto, String cTipoMantto) {
		TExcel Rep1 = new TExcel("07");
		int j = 15;
		char cColMeses = 'I';
		String cNomArch, cTemp;
		TVEQMEquipo VEquipo;
		Vector vManttos;
		cNomArch = "PROG_" + iEjercicio + "_" + iTipoMantto;
		try {
			if (vResultado.size() > 0) {
				Rep1.comDespliega("C", 11, cTipoMantto);
				Rep1.comDespliega("O", 11, iEjercicio + "");
				for (int i = 0; i < vResultado.size(); i++) {
					VEquipo = (TVEQMEquipo) ((Vector) vResultado.get(i)).get(0);
					vManttos = (Vector) ((Vector) vResultado.get(i)).get(1);
					Rep1.comDespliega("A", j, (i + 1) + "");
					Rep1.comAlineaRango("A", j, "A", j, Rep1.getAT_HCENTRO());
					Rep1.comFillCells("A", j, "A", j, 15);
					cTemp = VEquipo.getCDscBreveTpoEquipo();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("B", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEquipo.getCCveEquipo();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("C", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEquipo.getCDscBreveMarca();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("D", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEquipo.getCModelo();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("E", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEquipo.getCNumSerie();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("F", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEquipo.getCDscArea();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("G", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					cTemp = VEquipo.getCInventario();
					if (cTemp == null)
						cTemp = "";
					Rep1.comDespliega("H", j,
							cTemp.equalsIgnoreCase("null") ? "" : cTemp);
					Rep1.comAlineaRango("B", j, "H", j, Rep1.getAT_HIZQUIERDA());
					for (int x = 0; x < 12; x++) {
						if (((Boolean) vManttos.get(x)).booleanValue()) {
							Rep1.comDespliega("" + (char) (cColMeses + x), j,
									"X");
							Rep1.comFillCells("" + (char) (cColMeses + x), j,
									"" + (char) (cColMeses + x), j, 15);
							Rep1.comAlineaRango("" + (char) (cColMeses + x), j,
									"" + (char) (cColMeses + x), j,
									Rep1.getAT_HCENTRO());
						}
					}
					Rep1.comAlineaRangoVer("A", j, "T", j,
							Rep1.getAT_VSUPERIOR());
					Rep1.comAlineaRangoVer("A", j, "T", j,
							Rep1.getAT_VAJUSTAR());
					Rep1.comBordeTotal("A", j, "T", j, 1);
					j++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buffer = Rep1.creaActiveX("pg070604020", cNomArch, false,
				false, true);
		return buffer;
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		cPaginas = "";
		String cOrden = " ORDER BY ";
		String cWhere = " WHERE 1=1 ";
		String cTipoMantto = "";
		TDEQMEquipo dEquipo = new TDEQMEquipo();
		TVEQMEquipo vEQMEquipo;
		TDEQMTpoMantto dTipoMantto = new TDEQMTpoMantto();
		Vector vEquipo, vValores, vTemp;
		Vector vTipoMantElegido;
		try {
			vTipoMantto = dTipoMantto.FindByAll(" WHERE lActivo = 1 ",
					" ORDER BY cDscTpoMantto ");
			if (vTipoMantto == null)
				vTipoMantto = new Vector();
			if (request.getParameter("hdBoton").compareToIgnoreCase(
					"Generar Reporte") == 0) {
				if (request.getParameter("iEjercicio") != null
						&& request.getParameter("RSTOrdenar") != null
						&& request.getParameter("iCveTpoMantto") != null) {
					iValorOrden = Integer.parseInt(
							request.getParameter("iEjercicio"), 10);
					if (iValorOrden > 0)
						iEjercicio = iValorOrden;
					iValorOrden = Integer.parseInt(
							request.getParameter("iCveTpoMantto"), 10);
					if (iValorOrden > 0)
						iTipoMantto = iValorOrden;
					vTipoMantElegido = dTipoMantto.FindByAll(
							" WHERE iCveTpoMantto = " + iTipoMantto, "");
					if (vTipoMantElegido != null && vTipoMantElegido.size() > 0)
						cTipoMantto = ((TVEQMTpoMantto) vTipoMantElegido.get(0))
								.getCDscTpoMantto();
					if (cTipoMantto.equals("null"))
						cTipoMantto = "DESCRIPCI�N DE TIPO DE MANTENIMIENTO NO ENCONTRADA";
					iValorOrden = 1;
					iValorOrden = Integer.parseInt(
							request.getParameter("RSTOrdenar"), 10);
					cWhere += " AND (Mantenim.dtProgramado BETWEEN '"
							+ iEjercicio + "-01-01' AND '" + iEjercicio
							+ "-12-31' OR Mantenim.dtProgramado IS NULL) ";
					switch (iValorOrden) {
					case 1:
						cOrden += "TpoEquipo.cDscTpoEquipo";
						break;
					case 2:
						cOrden += "Marca.cDscMarca";
						break;
					case 3:
						cOrden += "Equipo.cModelo";
						break;
					case 4:
						cOrden += "Area.cDscArea";
						break;
					case 5:
						cOrden += "Mantenim.dtProgramado";
						break;
					default:
						cOrden = "";
					}
					vEquipo = dEquipo.FindByPrograma(cWhere, cOrden);
					for (int i = 0; i < vEquipo.size(); i++) {
						vValores = new Vector();
						vTemp = new Vector();
						vEQMEquipo = (TVEQMEquipo) vEquipo.get(i);
						vValores = dEquipo.FindByManttoAnual(
								vEQMEquipo.getICveEquipo(), iEjercicio,
								iTipoMantto);
						vTemp.add(0, vEQMEquipo);
						vTemp.add(1, vValores);
						vRegistros.add(vTemp);
					}
					activeX.append(this.Report(vRegistros, iEjercicio,
							iTipoMantto, cTipoMantto));
				} else {
					vErrores.acumulaError(
							"Debe elegir un tipo de mantenimiento, un ejercicio y un ordenamiento.",
							0, "");
				}
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}
}
