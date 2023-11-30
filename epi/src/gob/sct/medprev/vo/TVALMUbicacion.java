package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ALMUbicacion
 * </p>
 * <p>
 * Description: VO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Oscar Castrejón Adame.
 * @version 1.0
 */
public class TVALMUbicacion implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAlmacen;
	private int iCveArea;
	private int iCveSeccion;
	private int iCveArticulo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAlmacen);
		pk.add("" + iCveArea);
		pk.add("" + iCveSeccion);
		pk.add("" + iCveArticulo);
		return pk;
	}

	public TVALMUbicacion() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAlmacen", "" + iCveAlmacen);
		hmfieldsTable.put("iCveArea", "" + iCveArea);
		hmfieldsTable.put("iCveSeccion", "" + iCveSeccion);
		hmfieldsTable.put("iCveArticulo", "" + iCveArticulo);
		return hmfieldsTable;
	}

	public int getiCveAlmacen() {
		return iCveAlmacen;
	}

	public void setiCveAlmacen(int iCveAlmacen) {
		this.iCveAlmacen = iCveAlmacen;
	}

	public int getiCveArea() {
		return iCveArea;
	}

	public void setiCveArea(int iCveArea) {
		this.iCveArea = iCveArea;
	}

	public int getiCveSeccion() {
		return iCveSeccion;
	}

	public void setiCveSeccion(int iCveSeccion) {
		this.iCveSeccion = iCveSeccion;
	}

	public int getiCveArticulo() {
		return iCveArticulo;
	}

	public void setiCveArticulo(int iCveArticulo) {
		this.iCveArticulo = iCveArticulo;
	}
}
