package com.micper.seguridad.dao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import com.micper.ingsw.*;
import com.micper.sql.*;

public class CFGCatBase2 extends CFGBase {
	protected BeanPK mPk = new BeanPK();
	protected BeanScroller bs = null;

	public CFGCatBase2() {
		UpdStatus = "UpdateComplete";
		NavStatus = "Disabled";
		OtroStatus = "";
		// CanWrite = "yes";
		SaveAction = "Guardar";
		DeleteAction = "BorrarB";
	}

	public void outputHeader(TError vErrores, PageContext pc,
			TEntorno vEntorno, HttpServletRequest httpReq) throws IOException,
			ServletException {
		this.vErrores = vErrores;
		this.httpReq = httpReq;
		out = pc.getOut();
		request = pc.getRequest();
		if (lVerAcceso) {
			this.setAccesos(pc);
			this.verAcceso(vEntorno.getNombrePagina());
		}
		if (AccesoValido) {
			// Detecta la accion que el usuario eligio en los paneles o botones
			// de busqueda y filtro
			if (request.getParameter("hdBoton") == null) {
				cAccion = "";
			} else {
				cAccion = request.getParameter("hdBoton");
			}
			cAccionOriginal = cAccion;
			// Forma en método post para actualizacion
			if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
				// Forma en método post para actualización
				if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
					if (request.getParameter("hdCCondicion") != null) {
						cCondicion = request.getParameter("hdCCondicion");
					}
					if (request.getParameter("hdCOrdenar") != null) {
						cOrden = request.getParameter("hdCOrdenar");
					}
				}
				// Validación de la Accion que entro
				cClave = "" + request.getParameter("hdCampoClave");
				this.mainBlock();

				if (cAccion.compareToIgnoreCase("Nuevo") == 0) {
					this.Nuevo();
				}

				if (cAccion.compareToIgnoreCase("Guardar") == 0) {
					this.Guardar();
				}

				if (cAccion.compareToIgnoreCase("GuardarA") == 0) {
					this.GuardarA();
				}

				if (cAccion.compareToIgnoreCase("Modificar") == 0) {
					this.Modificar();
				}

				if (cAccion.compareToIgnoreCase("Cancelar") == 0) {
					this.Cancelar();
				}

				if (cAccion.compareToIgnoreCase("Borrar") == 0) {
					this.Borrar();
				}

				if (cAccion.compareToIgnoreCase("BorrarB") == 0) {
					this.BorrarB();
				}

				if (cAccion.compareToIgnoreCase("Reporte") == 0) {
					this.Reporte();
				}
			}
			// Se genera la condición WHERE y ORDER BY
			if (request.getParameter("hdCCondicion") == null
					&& request.getParameter("hdLCondicion") == null) {
				cAccion = "Primero";
			}
			if (cAccion.compareToIgnoreCase("Guardar") == 0) {
				cCondicion = "";
			}

			// .
			// Se llena el bean scroller .
			// .

			this.fillVector();

			if (lVerAcceso) {
				this.verOtrosAccesos();
			}

			if (cAccion.compareToIgnoreCase("Nuevo") != 0) {
				try {
					if ((vDespliega != null) && (!vDespliega.isEmpty())) {
						bs = new BeanScroller(vDespliega);
					} else {
						UpdStatus = "AddOnly";
						cAccion = "";
					}
				} catch (Exception ex) {
					UpdStatus = "AddOnly";
					cAccion = "";
				}
			}

			if (bs != null) {
				bs.firstRow();
				if (bs.rowSize() == 0) {
					bs = null;
					cAccion = "";
					UpdStatus = "AddOnly";
				}
			}
			// forma en método GET
			if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
				if (bs != null) {
					bs.firstRow();
					if (bs.hasNext() == true) {
						NavStatus = "FirstRecord";
					} else {
						NavStatus = "Disabled";
					}
				}
			}
			// Forma en metodo post para manejo de Navegación
			if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
				if (cAccion.compareToIgnoreCase("Primero") == 0) {
					if (bs != null) {
						bs.firstRow();
						if (bs.hasNext() == true) {
							NavStatus = "FirstRecord";
						} else {
							NavStatus = "Disabled";
						}
					}
				}
				// Seleccionó el botón Anterior
				if (cAccion.compareToIgnoreCase("Anterior") == 0) {
					if (request.getParameter("hdRowNum") != null) {
						if (bs != null) {
							bs.prevTo(Integer.parseInt(request
									.getParameter("hdRowNum")));
						}
					}
					if (bs != null) {
						if (bs.hasPrev()) {
							if (bs.hasNext()) {
								NavStatus = "Record";
							} else {
								NavStatus = "LastRecord";
							}
						} else {
							if (bs.hasNext()) {
								NavStatus = "FirstRecord";
							} else {
								NavStatus = "Disabled";
							}
						}
					}
				}
				// Seleccionó el botón Siguiente
				if (cAccion.compareToIgnoreCase("Siguiente") == 0) {
					if (request.getParameter("hdRowNum") != null) {
						if (bs != null) {
							bs.nextTo(Integer.parseInt(request
									.getParameter("hdRowNum")));
						}
					}
					if (bs != null) {
						if (bs.hasPrev()) {
							if (bs.hasNext()) {
								NavStatus = "Record";
							} else {
								NavStatus = "LastRecord";
							}
						} else {
							if (bs.hasNext()) {
								NavStatus = "FirstRecord";
							} else {
								NavStatus = "Disabled";
							}
						}
					}
				}
				// Seleccionó el botón Ultimo
				if (cAccion.compareToIgnoreCase("Ultimo") == 0) {
					if (bs != null) {
						bs.lastRow();
					}
					if (bs != null) {
						if (bs.hasPrev()) {
							NavStatus = "LastRecord";
						} else {
							NavStatus = "Disabled";
						}
					}
				}
				if (cAccion.compareToIgnoreCase("Actual") == 0) {
					cActual = request.getParameter("hdRowNum");
					cAccion = "ReposPK";
				}
				if (cAccion.compareToIgnoreCase("ReposPK") == 0) {
					if (cActual != null) {
						if (cActual != "") {
							try {
								fillPK();
								if (bs != null) {
									bs.setRowByPK(mPk);
								}
							} catch (Exception E) {
								if (bs != null) {
									bs.firstRow();
								}
							}
							if (bs != null) {
								if (bs.hasPrev()) {
									if (bs.hasNext()) {
										NavStatus = "Record";
									} else {
										NavStatus = "LastRecord";
									}
								} else {
									if (bs.hasNext()) {
										NavStatus = "FirstRecord";
									} else {
										NavStatus = "Disabled";
									}
								}
							}
						}
					}
				}
				if (cAccion.compareToIgnoreCase("ReposVector") == 0) {
					try {
						if (bs != null) {
							bs.setRowIdx(Integer.parseInt(cActual));
						}
					} catch (Exception E) {
						if (bs != null) {
							bs.firstRow();
						}
					}
					if (UpdStatus.compareToIgnoreCase("UpdateBegin") != 0) {
						if (bs != null) {
							if (bs.hasPrev()) {
								if (bs.hasNext()) {
									NavStatus = "Record";
								} else {
									NavStatus = "LastRecord";
								}
							} else {
								if (bs.hasNext()) {
									NavStatus = "FirstRecord";
								} else {
									NavStatus = "Disabled";
								}
							}
						}
					}
				}
			}
		} else {
			vErrores.acumulaError("Su sesión ha expirado...", 0, "");
		}
	}

	// Metodos de Transacción Comunes de Base de Datos
	public void mainBlock() {
	}

	public void Buscar() {
	}

	public void Nuevo() {
		Captura = Nuevo = true;
		UpdStatus = "UpdateBegin";
		NavStatus = OtroStatus = "Disabled";
	}

	public void Guardar() {
		UpdStatus = "UpdateComplete";
		cAccion = "ReposPK";
		cActual = cClave;
	}

	public void GuardarA() {
		UpdStatus = "UpdateComplete";
		cActual = request.getParameter("hdRowNum");
		cAccion = "ReposVector";
		cClave = request.getParameter("hdCampoClave");
	}

	public void Modificar() {
		Captura = true;
		Nuevo = false;
		UpdStatus = "UpdateBegin";
		NavStatus = OtroStatus = "Disabled";
		SaveAction = "GuardarA";
		cActual = request.getParameter("hdRowNum");
		if (cActual != null) {
			cAccion = "ReposVector";
		}
	}

	public void Cancelar() {
		cActual = request.getParameter("hdRowNum");
		if (cActual != null) {
			cAccion = "ReposVector";
		}
	}

	public void Borrar() {
		UpdStatus = "UpdateComplete";
		cActual = request.getParameter("hdRowNum");
		cAccion = "ReposVector";
		cClave = request.getParameter("hdCampoClave");
	}

	public void BorrarB() {
		UpdStatus = "UpdateComplete";
		cActual = request.getParameter("hdRowNum");
		cAccion = "ReposVector";
		cClave = request.getParameter("hdCampoClave");
	}

	public void Reporte() {
	}

	public void fillVector() {
	}

	public void fillPK() {
		mPk.add(cActual);
	}

	public BeanScroller getBeanScroller() {
		return this.bs;
	}

}
