package gob.sct.medprev.msc;

/*
 * QueryStructure.java
 *
 * Created on 31 de octubre de 2005, 06:20 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.util.*;

/**
 * 
 * @author Javier Llamas Mondragón
 */
public class QueryStructure {

	private HashMap fieldValues;
	private String table;
	private String clause;
	private int queryType;
	private String[] orderedAttributes;
	private String[] orderedValues;

	public static final int INSERT = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	public static final int SELECT = 4;

	/** Creates a new instance of QueryStructure */
	public QueryStructure(HashMap fieldValues, String table, String clause,
			int queryType) {
		setFieldValues(fieldValues);
		setTable(table);
		setClause(clause);
		setQueryType(queryType);
	}

	/**
	 * De los nombres de los atributos se genera un arreglo de String. Ello para
	 * hacer mas sencillala creación de un query al vuelo
	 * 
	 * @return String[] donde se han insertado los nombres de todos los
	 *         atributos
	 */
	public String[] getAttributes() {
		Object[] setLlaves = fieldValues.keySet().toArray();
		String[] tmp = new String[setLlaves.length];
		for (int i = 0; i < tmp.length; i++)
			tmp[i] = (String) setLlaves[i];
		return tmp;
	}

	/**
	 * De los valores de los atributos se genera un arreglo de String. Ello para
	 * hacer mas sencillala creación de un query al vuelo
	 * 
	 * @return String[] donde se han insertado los valores de todos los
	 *         atributos
	 */
	public String[] getValues(String[] keyOrder) {
		String[] orderedValues = new String[getOrderedAttributes().length];
		for (int i = 0; i < orderedValues.length; i++)
			orderedValues[i] = (String) fieldValues
					.get(getOrderedAttributes()[i]);
		return orderedValues;
	}

	/**
	 * De los atributos se genera un String separado por comas. Ello para hacer
	 * mas sencillala creación de un query al vuelo
	 * 
	 * @return String donde se han concatenado los nombres de de todos los
	 *         atributos separados por comas
	 */
	public String getAttributesString() {
		StringBuffer cadenaLlaves = new StringBuffer();
		for (int i = 0; i < getOrderedAttributes().length - 1; i++)
			cadenaLlaves.append(getOrderedAttributes()[i]).append(", ");
		cadenaLlaves
				.append(getOrderedAttributes()[getOrderedAttributes().length - 1]);
		return cadenaLlaves.toString();
	}

	/**
	 * De los valores de los atributos se genera un String separado por comas.
	 * Ello para hacer mas sencillala creación de un query al vuelo
	 * 
	 * @return String donde se han concatenado los valores de todos los
	 *         atributos separados por comas
	 */
	public String getValuesString() {
		StringBuffer cadenaValores = new StringBuffer();
		for (int i = 0; i < getOrderedValues().length - 1; i++)
			cadenaValores.append(getOrderedValues()[i]).append(", ");
		cadenaValores.append(getOrderedValues()[getOrderedValues().length - 1]);
		return cadenaValores.toString();
	}

	/**
	 * De los valores de los atributos y sus nombres se genera un String del
	 * tipo "atributo = valor" separado por comas. Ello para hacer mas
	 * sencillala creación de un query al vuelo
	 * 
	 * @return String donde se han concatenado los valores y nombres de todos
	 *         los atributos separados por comas
	 */
	public String getAttributeValueString() {
		StringBuffer cadenaRelacion = new StringBuffer();
		for (int i = 0; i < getOrderedAttributes().length - 1; i++)
			cadenaRelacion.append(getOrderedAttributes()[i]).append(" = ")
					.append(getOrderedValues()[i]).append(", ");
		cadenaRelacion
				.append(getOrderedAttributes()[getOrderedAttributes().length - 1])
				.append(" = ")
				.append(getOrderedValues()[getOrderedValues().length - 1]);
		return cadenaRelacion.toString();
	}

	/**
	 * Para un SELECT los valores que vienen asociados a un atributo son Strings
	 * vacios en caso de no ser llaves. En caso contrario, el valor asociado es
	 * el nombre del atributo mismo. Ello debido a la manera en que se generan
	 * los Value Objects Dinamicos
	 * 
	 * @see TVDinRep
	 * @see DAOBase
	 */
	public String getKeysString() {
		StringBuffer llaves = new StringBuffer();
		for (int i = 0; i < getOrderedValues().length - 1; i++)
			llaves.append(getOrderedValues()[i]).append(", ");
		llaves.append(getOrderedValues()[getOrderedValues().length - 1]);
		return llaves.toString();
	}

	/* **************** sets y gets****************************** */

	public void setFieldValues(HashMap hm) {
		this.fieldValues = hm;
		setOrderedAttributes(getAttributes());
		setOrderedValues(getValues(getOrderedAttributes()));
	}

	public HashMap getFieldValues() {
		return this.fieldValues;
	}

	/**
	 * @return nombre de la tabla especificada.
	 */
	public String getTable() {
		return table;
	}

	/**
	 * Determina el nombre de la tabla a emplear
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * especifica la clausula a emplear en caso de un UPDATE o un DELETE.
	 */
	public void setClause(String clause) {
		this.clause = clause;
	}

	/**
	 * regresa la clausula en caso de un UPDATE o un DELETE.
	 */
	public String getClause() {
		return this.clause;
	}

	/**
	 * regresa el tipo de query de q se trata.
	 */
	public int getQueryType() {
		return queryType;
	}

	/**
	 * especifica el tipo de query de q se trata.
	 */
	public void setQueryType(int type) {
		queryType = type;
	}

	/**
	 * metodo para pruebas locales
	 */
	public static void main(String[] args) {
		HashMap a = new HashMap();
		a.put("1", "'alfa'");
		a.put("2", "beta");
		QueryStructure b = new QueryStructure(a, "mitabla", "Where a =3",
				QueryStructure.INSERT);
		System.out.println("b.getAttributes()" + b.getAttributesString());
		String[] m = b.getAttributes();
		System.out.println("keys " + m[0]);
		System.out.println("valores " + b.getValuesString());
	}

	public String[] getOrderedAttributes() {
		return orderedAttributes;
	}

	public void setOrderedAttributes(String[] orderedAttributes) {
		this.orderedAttributes = orderedAttributes;
	}

	public String[] getOrderedValues() {
		return orderedValues;
	}

	public void setOrderedValues(String[] orderedValues) {
		this.orderedValues = orderedValues;
	}
}
