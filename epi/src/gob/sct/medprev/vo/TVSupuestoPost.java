package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object SupuestoPost
 * </p>
 * <p>
 * Description: Datos Tabla TOXSupuestoPost
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. González Paz
 * @version 1.0
 */
public class TVSupuestoPost implements HashBeanInterface {
	private BeanPK pk;
	private int iAnio;
	private int iCvePbaRapida;
	private int iCveSustancia;
	private int lResultado;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iAnio);
		pk.add("" + iCvePbaRapida);
		pk.add("" + iCveSustancia);
		return pk;
	}

	public TVSupuestoPost() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iAnio", "" + iAnio);
		hmfieldsTable.put("iCvePbaRapida", "" + iCvePbaRapida);
		hmfieldsTable.put("iCveSustancia", "" + iCveSustancia);
		hmfieldsTable.put("lResultado", "" + lResultado);
		return hmfieldsTable;
	}

	public int getIAnio() {
		return iAnio;
	}

	public void setIAnio(int iAnio) {
		this.iAnio = iAnio;
	}

	public int getICvePbaRapida() {
		return iCvePbaRapida;
	}

	public void setICvePbaRapida(int iCvePbaRapida) {
		this.iCvePbaRapida = iCvePbaRapida;
	}

	public int getICveSustancia() {
		return iCveSustancia;
	}

	public void setICveSustancia(int iCveSustancia) {
		this.iCveSustancia = iCveSustancia;
	}

	public int getLResultado() {
		return lResultado;
	}

	public void setLResultado(int lResultado) {
		this.lResultado = lResultado;
	}
}
