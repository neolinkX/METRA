package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object TOXAcCorrectiva
 * </p>
 * <p>
 * Description: Catalogo de acciones correctivas
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author
 * @version 1.0
 */
public class TVTOXAcCorrectiva implements HashBeanInterface {
	private BeanPK pk;
	private int iCveAcCorrectiva;
	private String cDscAcCorrectiva;
	private int lActivo;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveAcCorrectiva);
		return pk;
	}

	public TVTOXAcCorrectiva() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveAcCorrectiva", "" + iCveAcCorrectiva);
		hmfieldsTable.put("cDscAcCorrectiva", cDscAcCorrectiva);
		hmfieldsTable.put("lActivo", "" + lActivo);
		return hmfieldsTable;
	}

	public int getICveAcCorrectiva() {
		return iCveAcCorrectiva;
	}

	public void setICveAcCorrectiva(int iCveAcCorrectiva) {
		this.iCveAcCorrectiva = iCveAcCorrectiva;
	}

	public String getCDscAcCorrectiva() {
		return cDscAcCorrectiva;
	}

	public void setCDscAcCorrectiva(String cDscAcCorrectiva) {
		this.cDscAcCorrectiva = cDscAcCorrectiva;
	}

	public int getLActivo() {
		return lActivo;
	}

	public void setLActivo(int lActivo) {
		this.lActivo = lActivo;
	}
}
