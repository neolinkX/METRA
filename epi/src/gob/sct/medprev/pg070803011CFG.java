package gob.sct.medprev;

import com.micper.ingsw.*;
import com.micper.seguridad.dao.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import java.util.Vector;

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
 *      "JavaScript:alert('Consulte los archivos:\n-pg070803011CFG.java.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src='pg070803011CFG.java.png'>
 */
public class pg070803011CFG extends CFGListBase2 {
	TDALMArticulo dALMArticulo = new TDALMArticulo();
	TVALMArticulo vALMArticulo = new TVALMArticulo();
	int lAutorizada = 0;
	boolean lCondicion = false;

	public pg070803011CFG() {
		vParametros = new TParametro("07");
		DeleteAction = "Borrar";
	}

	public void Despliega() {
		boolean lWhere = false;
		try {
			if (request.getParameter("iAnio") != null
					&& request.getParameter("iAnio").toString().compareTo("") != 0
					&& request.getParameter("iCveAlmacen") != null
					&& request.getParameter("iCveAlmacen").toString()
							.compareTo("") != 0
					&& request.getParameter("iCveSolicSum") != null
					&& request.getParameter("iCveSolicSum").toString()
							.compareTo("") != 0) {

				vALMArticulo
						.setIAnio(new Integer(request.getParameter("iAnio"))
								.intValue());
				vALMArticulo.setICveAlmacen(new Integer(request
						.getParameter("iCveAlmacen")).intValue());
				vALMArticulo.setICveSolicSum(new Integer(request
						.getParameter("iCveSolicSum")).intValue());
				if (!lCondicion) {
					if (cCondicion.compareTo("") != 0) {
						cCondicion = " WHERE " + cCondicion;
						lWhere = true;
					}

					if (request.getParameter("iCveTpoArticulo") != null
							&& request.getParameter("iCveTpoArticulo")
									.toString().compareTo("-1") != 0
							&& request.getParameter("iCveTpoArticulo")
									.toString().compareTo("") != 0) {
						if (lWhere)
							cCondicion += " AND ALMArticulo.iCveTpoArticulo = "
									+ request.getParameter("iCveTpoArticulo")
											.toString();
						else {
							cCondicion = " WHERE ALMArticulo.iCveTpoArticulo = "
									+ request.getParameter("iCveTpoArticulo")
											.toString();
							lWhere = true;
						}
					}

					if (request.getParameter("iCveFamilia") != null
							&& request.getParameter("iCveFamilia").toString()
									.compareTo("-1") != 0
							&& request.getParameter("iCveFamilia").toString()
									.compareTo("") != 0) {
						if (lWhere)
							cCondicion += " AND ALMArticulo.iCveFamilia = "
									+ request.getParameter("iCveFamilia")
											.toString();
						else {
							cCondicion = " WHERE ALMArticulo.iCveFamilia = "
									+ request.getParameter("iCveFamilia")
											.toString();
							lWhere = true;
						}
					}
					lCondicion = true;
				}

				TDALMSolicSumin dALMSolicSumin = new TDALMSolicSumin();
				TVALMSolicSumin vALMSolicSumin = new TVALMSolicSumin();
				Vector vSoliSumin = new Vector();
				String cFiltro = "";
				if (request.getParameter("iAnio") != null
						&& request.getParameter("iCveAlmacen") != null
						&& request.getParameter("iCveSolicSum") != null) {
					cFiltro = " WHERE ALMSolicSumin.iAnio        = "
							+ request.getParameter("iAnio")
							+ "   AND ALMSolicSumin.iCveAlmacen  = "
							+ request.getParameter("iCveAlmacen")
							+ "   AND ALMSolicSumin.iCveSolicSum = "
							+ request.getParameter("iCveSolicSum");
					vSoliSumin = dALMSolicSumin.FindByAll(cFiltro, "");
					if (vSoliSumin.size() > 0) {
						vALMSolicSumin = (TVALMSolicSumin) vSoliSumin.get(0);
					}
					lAutorizada = vALMSolicSumin.getLAutorizada();
				}
				if (cOrden.compareTo("") == 0)
					cOrden = " ORDER BY ALMArticulo.iCveArticulo";

				vDespliega = dALMArticulo.FindBySumArt2(vALMArticulo,
						cCondicion, cOrden, " left ", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo FillVector
	 */
	public void fillVector() {
		cPaginas = "pg070803012.jsp|pg070803010.jsp|";
		TDALMSumArt dALTSumArt = new TDALMSumArt();
		TVALMSumArt vALTSumArt = new TVALMSumArt();

		Vector vSumArt = new Vector();
		int iCveArticulo = 0;
		String cFiltro = "";
		boolean lExiste = false;
		int iAsignar = 0;

		float dUnidSolicita = 0; // Valor de BD
		float dUnidSolicitaP = 0; // Valor de PANTALLA
		int iCveMeta = 0; // Valor de BD
		int iCveMetaP = 0; // Valor de PANTALLA

		try {
			this.Despliega();
			if (request.getParameter("hdBoton").toString().compareTo("Guardar") == 0) {
				if (vDespliega.size() > 0) { // Se recorre el Vector de los
												// Articulos desplegados
					for (int i = 0; i < vDespliega.size(); i++) {
						vALMArticulo = (TVALMArticulo) vDespliega.get(i);
						iCveArticulo = vALMArticulo.getiCveArticulo(); // Se
																		// obtiene
																		// la
																		// clave
																		// del
																		// articulo
						if (request
								.getParameter("dUnidSolicita" + iCveArticulo) != null) {
							cFiltro = " WHERE ALMSumArt.iAnio         = "
									+ request.getParameter("iAnio")
									+ "   AND ALMSumArt.iCveAlmacen   = "
									+ request.getParameter("iCveAlmacen")
									+ "   AND ALMSumArt.iCveSolicSum  = "
									+ request.getParameter("iCveSolicSum")
									+ "   AND ALMSumArt.iCveArticulo  = "
									+ iCveArticulo
									+ "   AND ALMSumArt.lAutorizada   = 0 ";
							lExiste = false;
							dUnidSolicita = 0;
							iCveMeta = 0;
							vSumArt = dALTSumArt.FindByAll(cFiltro,
									" Order By ALMSumArt.iCveArticulo "); // Se
																			// buscan
																			// los
																			// registros
																			// de
																			// SumArt
							if (vSumArt.size() > 0) {
								for (int j = 0; j < vSumArt.size(); j++) { // Se
																			// recorre
																			// el
																			// Vector
																			// de
																			// SumArt
									vALTSumArt = (TVALMSumArt) vSumArt.get(j);
									if (vALTSumArt.getICveArticulo() == iCveArticulo) {
										lExiste = true; // Se confirma que el
														// articulo exista en
														// SumArt
										dUnidSolicita = vALTSumArt
												.getDUnidSolicita(); // Se
																		// obtiene
																		// el
																		// valor
																		// de
																		// dUniSolicita
										iCveMeta = vALTSumArt.getICveMeta(); // Se
																				// obtiene
																				// el
																				// valor
																				// de
																				// iCveMeta
									}
								}
							}
							if (request.getParameter("lAsignar" + iCveArticulo) != null)
								iAsignar = new Integer(request.getParameter(
										"lAsignar" + iCveArticulo).toString())
										.intValue();
							else
								iAsignar = 0;

							if (iAsignar == 1) { // Se ASIGNA
								dUnidSolicitaP = new Double(
										request.getParameter("dUnidSolicita"
												+ iCveArticulo)).floatValue();
								iCveMetaP = new Integer(
										request.getParameter("iCveMeta"
												+ iCveArticulo)).intValue();
								if (lExiste) { // El Articulo ya existe en
												// SumArt
									if (dUnidSolicita != dUnidSolicitaP
											|| iCveMeta != iCveMetaP) {
										// ACTUALIZAR - Si el del Articulo en
										// SumArt es diferente en dUniSolicita o
										// iCveMeta
										vALTSumArt.setIAnio(new Integer(request
												.getParameter("iAnio"))
												.intValue());
										vALTSumArt
												.setICveAlmacen(new Integer(
														request.getParameter("iCveAlmacen"))
														.intValue());
										vALTSumArt
												.setICveSolicSum(new Integer(
														request.getParameter("iCveSolicSum"))
														.intValue());
										vALTSumArt
												.setICveArticulo(iCveArticulo);
										vALTSumArt
												.setDUnidSolicita(dUnidSolicitaP);
										vALTSumArt.setICveMeta(iCveMetaP);
										dALTSumArt.update2(null, vALTSumArt);
									}
								} else { // INSERTAR - Si el del Articulo no
											// existe en SumArt
									vALTSumArt.setIAnio(new Integer(request
											.getParameter("iAnio")).intValue());
									vALTSumArt
											.setICveAlmacen(new Integer(
													request.getParameter("iCveAlmacen"))
													.intValue());
									vALTSumArt
											.setICveSolicSum(new Integer(
													request.getParameter("iCveSolicSum"))
													.intValue());
									vALTSumArt.setICveArticulo(iCveArticulo);
									vALTSumArt.setDUnidSolicita(dUnidSolicitaP);
									vALTSumArt.setICveMeta(iCveMetaP);
									vALTSumArt.setLAnalizada(0);
									vALTSumArt.setLAutorizada(0);
									dALTSumArt.insert(null, vALTSumArt);
								}
							} else { // Checked = FALSE
								if (lExiste) { // BORRAR si el Articulo Existe
									vALTSumArt.setIAnio(new Integer(request
											.getParameter("iAnio")).intValue());
									vALTSumArt
											.setICveAlmacen(new Integer(
													request.getParameter("iCveAlmacen"))
													.intValue());
									vALTSumArt
											.setICveSolicSum(new Integer(
													request.getParameter("iCveSolicSum"))
													.intValue());
									vALTSumArt.setICveArticulo(iCveArticulo);
									dALTSumArt.delete(null, vALTSumArt);
								}
							}
						}
					}
				}
				this.Despliega();
			}
		} catch (Exception ex) {
			error("FillVector", ex);
		}
	}

	public int getlAutorizada() {
		return lAutorizada;
	}
}
