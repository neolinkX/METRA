package gob.sct.medprev;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.micper.sql.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.util.logging.*;
import com.micper.util.*;

/**
 * Clase de configuracion para listar equipos asignados y permitir asignaci�n
 * y desasignaci�n de equipos a las �reas de un m�dulo en una unidad
 * m�dica.
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de manejar las acciones del JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Micros Personales S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg070602020CFG.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070602020CFG.png'>
 */
public class pg070602020CFG extends CFGListBase2 {
	int iUniMed = 0, iModulo = 0, iArea = 0, iMostrar = 0, iClasifi = 0,
			iTpoEqui = 0;
	public Vector vClasif = new Vector();

	/**
	 * Constructor por omisi�n.
	 */
	public pg070602020CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	/**
	 * Metodo fillVector, se encarga de llenar el vector de valores a desplegar
	 * en el JSP de acuerdo a los diferentes valores seleccionados en el JSP.
	 */
	public void fillVector() {
		TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
		TDEQMClasificacion dClasif = new TDEQMClasificacion();
		TVEQMClasificacion tvClasif = new TVEQMClasificacion();
		StringBuffer bfFiltro = new StringBuffer("");

		try {
			if (cOrden.equals(""))
				cOrden = " ORDER BY E.iCveEquipo";
			if (request.getParameter("RSTMostrar") != null)
				iMostrar = Integer.parseInt(request.getParameter("RSTMostrar"),
						10);
			if (request.getParameter("iCveUniMed") != null)
				iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),
						10);
			if (request.getParameter("iCveModulo") != null)
				iModulo = Integer.parseInt(request.getParameter("iCveModulo"),
						10);
			if (request.getParameter("iCveArea") != null)
				iArea = Integer.parseInt(request.getParameter("iCveArea"), 10);
			if (request.getParameter("iCveClasificacion") != null)
				iClasifi = Integer.parseInt(
						request.getParameter("iCveClasificacion"), 10);
			if (request.getParameter("iCveTpoEquipo") != null)
				iTpoEqui = Integer.parseInt(
						request.getParameter("iCveTpoEquipo"), 10);
			// Se cambian valores en b�squedas para Guardar por campos ocultos
			if (iMostrar == 1 && this.cAccion.equals("Guardar")) {
				if (request.getParameter("iUnidadSelected") != null)
					iUniMed = Integer.parseInt(request
							.getParameter("iUnidadSelected"));
				if (request.getParameter("iModuloSelected") != null)
					iModulo = Integer.parseInt(request
							.getParameter("iModuloSelected"));
				if (request.getParameter("iAreaSelected") != null)
					iArea = Integer.parseInt(request
							.getParameter("iAreaSelected"));
			}
			switch (iMostrar) {
			case 0:
				bfFiltro.append("AND (A.lActual IS NULL OR (A.lActual = 0 and (A.iCveAsignacion = (SELECT MAX(EA.iCveAsignacion) FROM EQMAsignacion EA WHERE EA.iCveEquipo = E.iCveEquipo)))) ");
				break;
			case 1:
				bfFiltro.append("AND A.lActual = 1 ");
				if (iUniMed > 0)
					bfFiltro.append("AND A.iCveUniMed = " + iUniMed + " ");
				if (iModulo > 0)
					bfFiltro.append("AND A.iCveModulo = " + iModulo + " ");
				if (iArea > 0)
					bfFiltro.append("AND A.iCveArea = " + iArea + " ");
				break;
			default:
				bfFiltro = new StringBuffer("");
				break;
			}
			if (!cCondicion.equals(""))
				bfFiltro.append(" AND ").append(cCondicion);
			if (iClasifi > 0)
				bfFiltro.append(" AND E.iCveClasificacion = " + iClasifi + " ");
			if (iTpoEqui > 0)
				bfFiltro.append("AND E.iCveTpoEquipo = " + iTpoEqui + " ");
			bfFiltro.append(cOrden);
			tvClasif.setCDscClasificacion("Todas...");
			tvClasif.setICveClasificacion(0);
			vClasif = dClasif.FindByAll(" where lActivo=1 ",
					" order by iCveClasificacion ");
			vClasif.insertElementAt(tvClasif, 0);
			vDespliega = dEQMEquipo.FindByAsignacion(bfFiltro);
			if (this.cAccion.compareToIgnoreCase("Guardar") == 0) {
				if (this.Asignar())
					vDespliega = dEQMEquipo.FindByAsignacion(bfFiltro);
			}
		} catch (Exception ex) {
			error("pg070602020CFG.FillVector", ex);
		}
	}

	/**
	 * Metodo Asignar, se encarga de guardar asignaci�n/desasignaci�n de
	 * equipo.
	 * 
	 * @return Verdadero si se pudo asignar, falso en caso contrario.
	 */
	private boolean Asignar() {
		boolean lExito = true;
		try {
			if (vDespliega == null)
				vDespliega = new Vector();
			int iEquipo = 0, iUsuario = 0;
			boolean lDesAsignar = false, lAsignar = false, lProcesar = true;

			TVEquipoAsignacion VEqAsignacion = null;
			TVEquipoAsignacion VEqAsignaTemp = null;
			TFechas pFecha = new TFechas();
			TDEQMAsignacion dAsignacion = new TDEQMAsignacion();
			for (int i = 0; i < vDespliega.size(); i++) {
				lDesAsignar = false;
				lAsignar = false;
				VEqAsignacion = (TVEquipoAsignacion) vDespliega.get(i);
				iEquipo = VEqAsignacion.VEquipo.getICveEquipo();
				if (request.getParameter("iCveUsuAplica" + iEquipo) != null)
					iUsuario = Integer.parseInt(request
							.getParameter("iCveUsuAplica" + iEquipo));
				if (request.getParameter("TBXAsigna" + iEquipo) == null
						|| request.getParameter("TBXAsigna" + iEquipo).equals(
								"0")) {
					if (iMostrar == 1)
						lDesAsignar = true;
				} else {
					if (iMostrar == 0)
						lAsignar = true;
				}
				if (lProcesar) {
					VEqAsignaTemp = new TVEquipoAsignacion();
					VEqAsignaTemp.VAsignacion
							.setICveEquipo(VEqAsignacion.VEquipo
									.getICveEquipo());
					VEqAsignaTemp.VAsignacion.setICveUniMed(iUniMed);
					VEqAsignaTemp.VAsignacion.setICveModulo(iModulo);
					VEqAsignaTemp.VAsignacion.setICveArea(iArea);
					switch (iMostrar) {
					case 0: // Asignar equipo crear registro
						VEqAsignaTemp.VAsignacion.setICveUsuResp(iUsuario);
						VEqAsignaTemp.VAsignacion.setLActual(1);
						VEqAsignaTemp.VAsignacion
								.setDtAsigna(pFecha.TodaySQL());
						VEqAsignaTemp.VAsignacion.setDtDesasigna(null);
						if (lAsignar) {
							dAsignacion.insertWithSequence(null,
									VEqAsignaTemp.VAsignacion);
						}
						break;
					case 1: // Desasignar equipo, actualizar registro
						VEqAsignaTemp.VAsignacion.setLActual(0);
						VEqAsignaTemp.VAsignacion.setDtDesasigna(pFecha
								.TodaySQL());
						if (lDesAsignar) {
							dAsignacion.desasignar(null,
									VEqAsignaTemp.VAsignacion);
						}
						break;
					default:
						break;
					}
				}
			}
		} catch (Exception ex) {
			error("pg070602020CFG.Asignar", ex);
		}
		return lExito;
	}

	/**
	 * Metodo encargado de regresar el vector de valores a desplegar en el JSP.
	 * 
	 * @return Vector de objetos con los valores a desplegar en el JSP.
	 */
	public Vector getVDespliega() {
		return this.vDespliega == null ? new Vector() : this.vDespliega;
	}
}