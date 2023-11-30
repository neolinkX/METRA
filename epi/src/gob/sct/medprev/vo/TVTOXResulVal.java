package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXResulVal
 * </p>
 * <p>
 * Description: VO TOXResulVal
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */
public class TVTOXResulVal implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCveLaboratorio;
	private int iCveValPres;
	private int iCveMValida;
	private int iCarrusel;
	private int iPosicion;
	private float dConcentracion;
	private float dPorcentaje;
	private float dResultado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCveLaboratorio);
		pk.add("" + iCveValPres);
		pk.add("" + iCveMValida);
		return pk;
	}

	public TVTOXResulVal() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCveLaboratorio", "" + iCveLaboratorio);
		hmfieldsTable.put("iCveValPres", "" + iCveValPres);
		hmfieldsTable.put("iCveMValida", "" + iCveMValida);
		hmfieldsTable.put("iCarrusel", "" + iCarrusel);
		hmfieldsTable.put("iPosicion", "" + iPosicion);
		hmfieldsTable.put("dConcentracion", "" + dConcentracion);
		hmfieldsTable.put("dPorcentaje", "" + dPorcentaje);
		hmfieldsTable.put("dResultado", "" + dResultado);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICveLaboratorio() {
		return iCveLaboratorio;
	}

	public void setICveLaboratorio(int iCveLaboratorio) {
		this.iCveLaboratorio = iCveLaboratorio;
	}

	public int getICveValPres() {
		return iCveValPres;
	}

	public void setICveValPres(int iCveValPres) {
		this.iCveValPres = iCveValPres;
	}

	public int getICveMValida() {
		return iCveMValida;
	}

	public void setICveMValida(int iCveMValida) {
		this.iCveMValida = iCveMValida;
	}

	public int getICarrusel() {
		return iCarrusel;
	}

	public void setICarrusel(int iCarrusel) {
		this.iCarrusel = iCarrusel;
	}

	public int getIPosicion() {
		return iPosicion;
	}

	public void setIPosicion(int iPosicion) {
		this.iPosicion = iPosicion;
	}

	public float getDConcentracion() {
		return dConcentracion;
	}

	public void setDConcentracion(float dConcentracion) {
		this.dConcentracion = dConcentracion;
	}

	public float getDPorcentaje() {
		return dPorcentaje;
	}

	public void setDPorcentaje(float dPorcentaje) {
		this.dPorcentaje = dPorcentaje;
	}

	public float getDResultado() {
		return dResultado;
	}

	public void setDResultado(float dResultado) {
		this.dResultado = dResultado;
	}
}
