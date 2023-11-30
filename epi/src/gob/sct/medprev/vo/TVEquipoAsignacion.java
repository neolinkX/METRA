package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object de Equipo y Asignaci�n del mismo.
 * </p>
 * <p>
 * Description: VO para la tabla EQMEquipo en join con Asignaci�n y datos de
 * usuario.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */
public class TVEquipoAsignacion {
	/**
	 * Objeto que contiene todos los valores de la tabla EQMEquipo, definido en
	 * otro Value Object.
	 */
	public TVEQMEquipo VEquipo = new TVEQMEquipo();
	/**
	 * Objeto que contiene todos los valores de la tabla EQMAsignacion, definido
	 * en otro Value Object.
	 */
	public TVEQMAsignacion VAsignacion = new TVEQMAsignacion();
	/**
	 * Variables internas que contendr�n el nombre, apellido paterno y materno
	 * del usuario responsable del equipo.
	 */
	private String cNombre = "", cApPaterno = "", cApMaterno = "";

	/**
	 * Constructor por omisi�n.
	 */
	public TVEquipoAsignacion() {
	}

	/**
	 * Metodo encargado de devolver el valor del atributo cNombre.
	 * 
	 * @return Valor del atributo.
	 */
	public String getCNombre() {
		return cNombre;
	}

	/**
	 * Metodo encargado de asignar el valor del atributo cNombre.
	 * 
	 * @param cNombre
	 *            Valor a asignar al atributo.
	 */
	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	/**
	 * Metodo encargado de devolver el valor del atributo cApPaterno.
	 * 
	 * @return Valor del atributo.
	 */
	public String getCApPaterno() {
		return cApPaterno;
	}

	/**
	 * Metodo encargado de asignar el valor del atributo cApPaterno.
	 * 
	 * @param cApPaterno
	 *            Valor a asignar al atributo.
	 */
	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	/**
	 * Metodo encargado de devolver el valor del atributo cApMaterno.
	 * 
	 * @return Valor del atributo.
	 */
	public String getCApMaterno() {
		return cApMaterno;
	}

	/**
	 * Metodo encargado de asginar el valor del atributo cApMaterno.
	 * 
	 * @param cApMaterno
	 *            Valor a asignar al atributo.
	 */
	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}

}
