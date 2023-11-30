package com.micper.sql;

import java.util.*;
import java.io.*;

/**
 * Clase que conservar� los valores y campos de la llave primaria de un
 * registro.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de crear un arreglo con los campos que corresponden a
 * la llave primaria del registro.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>Jos� AG L�pez Hern�ndez
 *         <p>
 * @see <small><a href="BeanScroller.html">BeanScroller</a></small>
 * @see <small><a href="HashBeanInterface.html">HashBEanInterface</a></small>
 * @see <small><a href="PageBeanScroller.html">PageBeanScroller</a></small>
 * @see <small><a
 *      href="./../ingsample/pg9900012CFG.html">com.micper.ingsample.pg9900012CFG
 *      </a></small>
 * @see <small><a
 *      href="./../ingsample/pg9902011CFG.html">com.micper.ingsample.pg9902011CFG
 *      </a></small>
 * @see <small><a
 *      href="./../ingsample/pg9903011CFG.html">com.micper.ingsample.pg9903011CFG
 *      </a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="BeanPK.png">
 */

public class BeanPK implements Serializable {
	/** Arreglo que contendr� los campos de la llave primaria de un registro. */
	private ArrayList cFields;

	/** Crea una nueva instancia de BeanPK, inicializando el arreglo de campos. */
	public BeanPK() {
		cFields = new ArrayList();
	}

	/**
	 * M�todo encargado de agregar un nuevo objeto a la lista de campos.
	 * 
	 * @param pField
	 *            Objeto que se desea agregar al arreglo.
	 */
	public void add(Object pField) {
		cFields.add(pField);
	}

	/**
	 * M�todo encargado de indicar cuantos campos conforman la llave primaria.
	 * 
	 * @return Valor del n�mero de campos en el arreglo.
	 */
	public int size() {
		return cFields.size();
	}

	/**
	 * M�todo encargado de regresar la lista de objetos que se agregaron como
	 * llave primaria.
	 * 
	 * @return Arreglo que contiene los campos agregados como parte de la llave
	 *         primaria.
	 */
	public ArrayList getFields() {
		return cFields;
	}

	/**
	 * M�todo encargado de regresar el objeto correspondiente al campo X de la
	 * llave primaria.
	 * 
	 * @param pIdx
	 *            N�mero de campo a obtener.
	 * @return Elemento solicitado correspondiente a un campo de la llave
	 *         primaria.
	 */
	public Object getField(int pIdx) {
		return cFields.get(pIdx);
	}

	/**
	 * Este m�todo se encarga de comparar los valores contenidos en el arreglo
	 * de objetos correspondientes a la llave primaria contra los valores del
	 * objeto proporcionado.
	 * 
	 * @param pPk
	 *            Objeto que contiene los valores a comparar contra la llave
	 *            primaria.
	 * @return Indica con true que los valores son iguales, y con false si no
	 *         son iguales o no son llaves equivalentes.
	 */
	public boolean equals(BeanPK pPk) {
		boolean mReturn = true;
		if (size() != pPk.size())
			return false;
		Object mThisObj;
		String mThisStrObj;
		Integer mThisIntObj;
		Object mObj;
		for (int i = 0, len = size(); i < len; i++) {
			mThisObj = cFields.get(i);
			mObj = pPk.getField(i);
			if (mThisObj instanceof String) {
				mThisStrObj = (String) mThisObj;
				if (!mThisStrObj.equals((String) mObj)) {
					mReturn = false;
					break;
				}
			} else if (cFields.get(i) instanceof Integer) {
				mThisIntObj = (Integer) mThisObj;
				if (mThisIntObj.intValue() != ((Integer) mObj).intValue()) {
					mReturn = false;
					break;
				}
			} // aqu� se puede agregar comparaci�n de otros tipos de datos para
				// llave primaria
		}
		return mReturn;
	}
}
