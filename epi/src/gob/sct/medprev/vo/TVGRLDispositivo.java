package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**

 * @author AG SA
 * @version 1.0
 */
public class TVGRLDispositivo implements HashBeanInterface {
	private BeanPK pk;
	private int iCveDispositivo;
	private String cNombreDispositivo;
	private String cFabricante;
	private String cModelo;
	private String cPuertoDeInterfaz;
	private String cSOCompatible;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveDispositivo);
		return pk;
	}

	public TVGRLDispositivo() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveDispositivo", "" + iCveDispositivo);
		hmfieldsTable.put("cNombreDispositivo", cNombreDispositivo);
		hmfieldsTable.put("cFabricante", cFabricante);
		hmfieldsTable.put("cModelo", cModelo);
		hmfieldsTable.put("cPuertoDeInterfaz", cPuertoDeInterfaz);
		hmfieldsTable.put("cSOCompatible", cSOCompatible);
		hmfieldsTable.put("lActivo", lActivo);
		return hmfieldsTable;
	}

	public int getICveDispositivos() {
		return iCveDispositivo;
	}

	public void setICveDispositivos(int iCveDispositivo) {
		this.iCveDispositivo = iCveDispositivo;
	}

	public String getCNombreDispositivo() {
		return cNombreDispositivo;
	}

	public void setCNombreDispositivo(String cNombreDispositivo) {
		this.cNombreDispositivo = cNombreDispositivo;
	}

	public String getCFabricante() {
		return cFabricante;
	}

	public void setCFabricante(String cFabricante) {
		this.cFabricante = cFabricante;
	}

	public String getCModelo() {
		return cModelo;
	}

	public void setCModelo(String cModelo) {
		this.cModelo = cModelo;
	}

	
	public String getCPuertoDeInterfaz() {
		return cPuertoDeInterfaz;
	}

	public void setCPuertoDeInterfaz(String cPuertoDeInterfaz) {
		this.cPuertoDeInterfaz = cPuertoDeInterfaz;
	}

	
	public String getCSOCompatible() {
		return cSOCompatible;
	}

	public void setCSOCompatible(String cSOCompatible) {
		this.cSOCompatible = cSOCompatible;
	}


	/**
	 * @return the lActivo
	 */
	public int getIActivo() {
		return lActivo;
	}

	/**
	 * @param lActivo
	 *            the lActivo to set
	 */
	public void setIActivo(int lActivo) {
		this.lActivo = lActivo;
	}

}
