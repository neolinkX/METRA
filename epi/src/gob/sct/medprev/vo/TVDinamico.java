package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Dinamico
 * </p>
 * <p>
 * Description: Value Object con campos dinamicos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */
public class TVDinamico implements HashBeanInterface {
	private BeanPK pk;
	private HashMap hmFieldsTable;
	private Vector vcKeys;

	public TVDinamico() {
		pk = new BeanPK();
		hmFieldsTable = new HashMap();
	}

	public BeanPK getPK() {
		return pk;
	}

	public HashMap toHashMap() {
		return hmFieldsTable;
	}

	public void addPK(Object objValue) {
		pk.add("" + objValue);
	}

	public int size() {
		return hmFieldsTable.size();
	}

	public boolean containsKey(Object objKey) {
		return hmFieldsTable.containsKey(objKey);
	}

	public Set keySet() {
		return hmFieldsTable.keySet();
	}

	public Object get(String sKey) {
		return hmFieldsTable.get(sKey);
	}

	public Object put(String sKey, Object objValue) {
		return hmFieldsTable.put(sKey, objValue);
	}

	public Byte put(String sKey, byte btValue) {
		return (Byte) hmFieldsTable.put(sKey, new Byte(btValue));
	}

	public Short put(String sKey, short shValue) {
		return (Short) hmFieldsTable.put(sKey, new Short(shValue));
	}

	public Integer put(String sKey, int iValue) {
		return (Integer) hmFieldsTable.put(sKey, new Integer(iValue));
	}

	public Long put(String sKey, long lnValue) {
		return (Long) hmFieldsTable.put(sKey, new Long(lnValue));
	}

	public Float put(String sKey, float fValue) {
		return (Float) hmFieldsTable.put(sKey, new Float(fValue));
	}

	public Double put(String sKey, double dbValue) {
		return (Double) hmFieldsTable.put(sKey, new Double(dbValue));
	}

	public Boolean put(String sKey, boolean bValue) {
		return (Boolean) hmFieldsTable.put(sKey, Boolean.valueOf(bValue));
	}

	public Character put(String sKey, char cValue) {
		return (Character) hmFieldsTable.put(sKey, new Character(cValue));
	}
}
