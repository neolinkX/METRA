package com.micper.seguridad.vo;

import java.util.*;

import com.micper.sql.*;
import com.micper.util.*;

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
public class TVDinRep02 implements HashBeanInterface {
	private BeanPK pk;
	private HashMap hmFieldsTable;
	private Vector vcKeys = new Vector();
	private String cLlave;

	public TVDinRep02() {
		pk = new BeanPK();
		hmFieldsTable = new HashMap();
	}

	public BeanPK getPK() {
		if (pk.size() == 0 && cLlave != null) {
			StringTokenizer stLlave = new StringTokenizer(cLlave, ",");
			while (stLlave.hasMoreTokens()) {
				addPK(getString(stLlave.nextToken()));
			}
		}
		return pk;
	}

	public HashMap toHashMap() {
		return hmFieldsTable;
	}

	public void setLlave(String cKey) {
		cLlave = cKey.toUpperCase();
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
		return hmFieldsTable.get(sKey.toUpperCase());
	}

	public int getInt(String sKey) {
		try {
			return Integer.parseInt("" + hmFieldsTable.get(sKey.toUpperCase()));
		} catch (Exception e) {
			return 0;
		}
	}

	public java.sql.Date getDate(String sKey) {
		java.sql.Date dtVer;
		try {
			dtVer = (java.sql.Date) hmFieldsTable.get(sKey.toUpperCase());
		} catch (Exception ex) {
			try {
				dtVer = new TFechas().getDateSQL((String) hmFieldsTable
						.get(sKey.toUpperCase()));
			} catch (Exception e) {
				dtVer = null;
			}
		}
		return dtVer;
	}

	public String getDateStringSQL(String sKey) {
		String cDateSQL = "";
		try {
			cDateSQL = (String) hmFieldsTable.get(sKey.toUpperCase());
			cDateSQL = "{ D '" + cDateSQL.substring(3, 5) + "-"
					+ cDateSQL.substring(0, 2) + "-"
					+ cDateSQL.substring(6, 10) + "' }";
		} catch (Exception e) {
			cDateSQL = "";
		}
		return cDateSQL;
	}

	public java.sql.Timestamp getTimeStamp(String sKey) {
		java.sql.Timestamp tsVer;
		int iAnio, iMes, iDia, iHora, iMinuto, iSegundo;
		try {
			StringTokenizer stTS = new StringTokenizer(
					(String) hmFieldsTable.get(sKey.toUpperCase()), "/");
			try {
				iAnio = Integer.parseInt(stTS.nextToken());
			} catch (Exception e) {
				iAnio = 0;
			}
			try {
				iMes = Integer.parseInt(stTS.nextToken());
			} catch (Exception e) {
				iMes = 0;
			}
			try {
				iDia = Integer.parseInt(stTS.nextToken());
			} catch (Exception e) {
				iDia = 0;
			}
			try {
				iHora = Integer.parseInt(stTS.nextToken());
			} catch (Exception e) {
				iHora = 0;
			}
			try {
				iMinuto = Integer.parseInt(stTS.nextToken());
			} catch (Exception e) {
				iMinuto = 0;
			}
			try {
				iSegundo = Integer.parseInt(stTS.nextToken());
			} catch (Exception e) {
				iSegundo = 0;
			}
			tsVer = new TFechas().getTimeStamp(iAnio, iMes, iDia, iHora,
					iMinuto, iSegundo);
		} catch (Exception ex) {
			tsVer = new TFechas().getTimeStamp(0, 0, 0, 0, 0, 0);
		}
		return tsVer;
	}

	public float getFloat(String sKey) {
		float dTmp;
		try {
			dTmp = Float.parseFloat("" + hmFieldsTable.get(sKey.toUpperCase()));
		} catch (Exception e) {
			dTmp = 0;
		}
		return dTmp;
	}

	public double getDouble(String sKey) {
		double dTmp;
		try {
			dTmp = Double.parseDouble(""
					+ hmFieldsTable.get(sKey.toUpperCase()));
		} catch (Exception e) {
			dTmp = 0;
		}
		return dTmp;
	}

	public String getString(String sKey) {
		return ("" + hmFieldsTable.get(sKey.toUpperCase()));
	}

	public boolean getBoolean(String sKey) {
		return Boolean.getBoolean("" + hmFieldsTable.get(sKey.toUpperCase()));
	}

	public long getLong(String sKey) {
		return Long.parseLong("" + hmFieldsTable.get(sKey.toUpperCase()));
	}

	public Object put(String sKey, Object objValue) {
		vcKeys.add(sKey.toUpperCase());
		return hmFieldsTable.put(sKey.toUpperCase(), objValue);
	}

	public Byte put(String sKey, byte btValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Byte) hmFieldsTable.put(sKey.toUpperCase(), new Byte(btValue));
	}

	public Short put(String sKey, short shValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Short) hmFieldsTable
				.put(sKey.toUpperCase(), new Short(shValue));
	}

	public Integer put(String sKey, int iValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Integer) hmFieldsTable.put(sKey.toUpperCase(), new Integer(
				iValue));
	}

	public Long put(String sKey, long lnValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Long) hmFieldsTable.put(sKey.toUpperCase(), new Long(lnValue));
	}

	public Float put(String sKey, float fValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Float) hmFieldsTable.put(sKey.toUpperCase(), new Float(fValue));
	}

	public Double put(String sKey, double dbValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Double) hmFieldsTable.put(sKey.toUpperCase(), new Double(
				dbValue));
	}

	public Boolean put(String sKey, boolean bValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Boolean) hmFieldsTable.put(sKey.toUpperCase(),
				Boolean.valueOf(bValue));
	}

	public java.sql.Date put(String sKey, java.sql.Date dtValue) {
		vcKeys.add(sKey.toUpperCase());
		return (java.sql.Date) hmFieldsTable.put(sKey.toUpperCase(), dtValue);
	}

	public Character put(String sKey, char cValue) {
		vcKeys.add(sKey.toUpperCase());
		return (Character) hmFieldsTable.put(sKey.toUpperCase(), new Character(
				cValue));
	}

	public Vector getVcKeys() {
		return vcKeys;
	}
}
