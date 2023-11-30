package com.micper.seguridad.vo;

import java.util.*;

import com.micper.sql.*;
import java.sql.Date;

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
 * @author Jaime Enrique Suárez Romero
 * @version 1.0
 */
public class TVDinRep implements HashBeanInterface {
	private BeanPK pk;
	private HashMap hmFieldsTable;
	private Vector vcKeys = new Vector();
    private String cLlave;
    
    
    public BeanPK getPK(){
        if(pk.size() == 0 && cLlave != null){
          StringTokenizer stLlave = new StringTokenizer(cLlave,",");
          while(stLlave.hasMoreTokens()){
            addPK(getString(stLlave.nextToken()));
          }
        }
        return pk;
      }
    
    
	public TVDinRep() {
		pk = new BeanPK();
		hmFieldsTable = new HashMap();
	}

	/*public BeanPK getPK() {
		return pk;
	}*/

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

	public int getInt(String sKey) {
		return ((Integer) hmFieldsTable.get(sKey)).intValue();
	}

	public float getFloat(String sKey) {
		return ((Float) hmFieldsTable.get(sKey)).floatValue();
	}

	public double getDouble(String sKey) {
		return ((Double) hmFieldsTable.get(sKey)).doubleValue();
	}

	public String getString(String sKey) {
		return ((String) hmFieldsTable.get(sKey));
	}

	public boolean getBoolean(String sKey) {
		return ((Boolean) hmFieldsTable.get(sKey)).booleanValue();
	}

	public long getLong(String sKey) {
		return ((Long) hmFieldsTable.get(sKey)).longValue();
	}

	public Object put(String sKey, Object objValue) {
		vcKeys.add(sKey);
		return hmFieldsTable.put(sKey, objValue);
	}

	public Byte put(String sKey, byte btValue) {
		vcKeys.add(sKey);
		return (Byte) hmFieldsTable.put(sKey, new Byte(btValue));
	}

	public Short put(String sKey, short shValue) {
		vcKeys.add(sKey);
		return (Short) hmFieldsTable.put(sKey, new Short(shValue));
	}

	public Integer put(String sKey, int iValue) {
		vcKeys.add(sKey);
		return (Integer) hmFieldsTable.put(sKey, new Integer(iValue));
	}

	public Long put(String sKey, long lnValue) {
		vcKeys.add(sKey);
		return (Long) hmFieldsTable.put(sKey, new Long(lnValue));
	}

	public Float put(String sKey, float fValue) {
		vcKeys.add(sKey);
		return (Float) hmFieldsTable.put(sKey, new Float(fValue));
	}

	public Double put(String sKey, double dbValue) {
		vcKeys.add(sKey);
		return (Double) hmFieldsTable.put(sKey, new Double(dbValue));
	}

	public Boolean put(String sKey, boolean bValue) {
		vcKeys.add(sKey);
		return (Boolean) hmFieldsTable.put(sKey, Boolean.valueOf(bValue));
	}

	public Character put(String sKey, char cValue) {
		vcKeys.add(sKey);
		return (Character) hmFieldsTable.put(sKey, new Character(cValue));
	}

	public Vector getVcKeys() {
		return vcKeys;
	}
	
	  public void setLlave(String cKey){
		    cLlave = cKey.toUpperCase();
		  }

	  public void remove(Object objKey){
		    hmFieldsTable.remove(objKey.toString().toUpperCase());
		  }

	
	
}
