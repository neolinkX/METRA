package com.micper.seguridad.dao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

import com.micper.ingsw.*;
import com.micper.sql.*;

public class CFGListBase2 extends CFGBase {
	protected PageBeanScroller bs = null;

	public CFGListBase2() {
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
			// Forma en método post para actualización

			if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
				if (request.getParameter("hdLCondicion") != null) {
					cCondicion = request.getParameter("hdLCondicion");
				}
				if (request.getParameter("hdLOrdenar") != null) {
					cOrden = request.getParameter("hdLOrdenar");
				}
			}
			if ((request.getParameter("FILNumReng") == null)
					|| (("" + request.getParameter("FILNumReng")).compareTo("") == 0)) {
				iNumReg = Integer.parseInt(
						vParametros.getPropEspecifica("NumRengTab"), 10);
			} else {
				iNumReg = Integer.parseInt(
						"" + request.getParameter("FILNumReng"), 10);
			}
			if ((cAccion.compareToIgnoreCase("Buscar") != 0)
					&& (request.getParameter("hdLCondicion") != null)) {
				cCondicion = request.getParameter("hdLCondicion");
			} else {
				cAccion = "Primero";
			}

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

			this.fillVector();

			if (lVerAcceso) {
				this.verOtrosAccesos();
			}

			// .
			// Se ejecuta el llenado del bean scroller .
			// .
			try {
				if (!vDespliega.isEmpty()) {
					bs = new PageBeanScroller(vDespliega, iNumReg);
				} else {
					cAccion = "";
				}
			} catch (Exception ex) {
				vErrores.acumulaError("", 15, "");
				cAccion = "";
			}

			if (bs != null) {
				bs.firstPage();
				if (bs.pageSize() == 0) {
					bs = null;
					vErrores.acumulaError("", 15, "");
					cAccion = "";
				}
			}
			// forma en método GET
			if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
				if (bs != null) {
					bs.firstPage();
					if (bs.hasNextPage() == true) {
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
						bs.firstPage();
						if (bs.hasNextPage() == true) {
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
							bs.prevPageTo(Integer.parseInt(request
									.getParameter("hdRowNum")));
						}
					}
					if (bs != null) {
						if (bs.hasPrevPage()) {
							if (bs.hasNextPage()) {
								NavStatus = "Record";
							} else {
								NavStatus = "LastRecord";
							}
						} else {
							if (bs.hasNextPage()) {
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
							bs.nextPageTo(Integer.parseInt(request
									.getParameter("hdRowNum")));
						}
					}
					if (bs != null) {
						if (bs.hasPrevPage()) {
							if (bs.hasNextPage()) {
								NavStatus = "Record";
							} else {
								NavStatus = "LastRecord";
							}
						} else {
							if (bs.hasNextPage()) {
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
						bs.lastPage();
					}
					if (bs != null) {
						if (bs.hasPrevPage()) {
							NavStatus = "LastRecord";
						} else {
							NavStatus = "Disabled";
						}
					}
				}

				// Seleccionó el botón Guardar
				if (cAccion.compareToIgnoreCase("Guardar") == 0) {
					if (request.getParameter("hdRowNum") != null) {
						if (bs != null) {
							int ivPag = 0;
							if (request.getParameter("hdRowNum").toString()
									.compareToIgnoreCase("") != 0)
								ivPag = new Integer(
										request.getParameter("hdRowNum"))
										.intValue();
							if (bs.nextPageTo(ivPag)) {
								if (ivPag != 0)
									bs.prevPageTo(ivPag + 1);
								else
									bs.nextPageTo(ivPag);
							} else
								bs.nextPageTo(ivPag - 1);
						}
					}
				}
			}
		} else {
			vErrores.acumulaError("Su sesión ha expirado...", 0, "");
		}
	}

	public void mainBlock() {
	}

	public void Nuevo() {
	}

	public void Guardar() {
	}

	public void GuardarA() {
	}

	public void Modificar() {
	}

	public void Cancelar() {
	}

	public void Borrar() {
	}

	public void BorrarB() {
	}

	public void Buscar() {
	}

	public void Reporte() {
	}

	public void fillVector() {
	}

	/**
	 * Método encargado de enviar el objeto con los datos que se obtuvieron en
	 * la clase.
	 * 
	 * @return Objeto BeanScroller con la información que se obtuvo.
	 */
	public PageBeanScroller getBeanScroller() {
		return this.bs;
	}

}
