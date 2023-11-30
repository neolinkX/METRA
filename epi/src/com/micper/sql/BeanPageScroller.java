package com.micper.sql;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Esta clase se encarga de realizar la navegaci�n en un conjunto de registros.
 * <p>Ingenier�a de Software generada en JAVA (J2EE).
 * <p>La navegaci�n se realiza a nivel p�gina, es decir un grupo de registros.
 * <br>Tambi�n permite conocer si exiten p�ginas antes o despu�s de la actual, de tal
 * modo que pueda controlarse el panel de navegaci�n y el reposicionamiento.
 * @version 1.0
 * <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>Jos� AG L�pez Hern�ndez
 * <p>
 * @deprecated Use PageBeanScroller.
 * <p>
 * </dd>
 * <p></p><dt><strong>Diagrama de Clase:</strong>
 * <p><img src="BeanPageScroller.png">
 */

public class BeanPageScroller implements Serializable {
	/** N�mero de paginas existentes en base al n�mero de elementos resultantes. */
	private int pageSize = 0;
	/** N�mero de elementos que tendr� cada p�gina. */
	private int linesXPage = 10;
	/** Elementos que se desean paginar. */
	private Vector beans;
	/** Indicar� el n�mero de p�gina en la que se desea reposicionar. */
	private int pageIdx = -1;
	/** Indicar� el n�mero de rengl�n en el que se desea reposicionar. */
	private int rowIdx = -1;
	/** Indica cual es el primer n�mero de rengl�n. */
	private int rowIdxMin = 0;
	/** Indica cual es el �ltimo n�mero de rengl�n. */
	private int rowIdxMax = 0;

	/**
	 * Constructor encargado de crear el objeto y asignar los valores
	 * proporcionados, asi como tambi�n se encargar� de inicializar algunas
	 * variables internas.
	 * 
	 * @param pBeans
	 *            Vector de elementos que se desean paginar.
	 * @param pLinesXPage
	 *            N�mero de elementos que se desea tener por cada p�gina.
	 */
	public BeanPageScroller(Vector pBeans, int pLinesXPage) {
		if (pLinesXPage > 0)
			linesXPage = pLinesXPage;
		beans = pBeans;
		int lSize = beans.size() / linesXPage;
		int lRemain = beans.size() % linesXPage;
		if (lRemain > 0)
			pageSize = lSize + 1;
		else
			pageSize = lSize;
	}

	/**
	 * M�todo encargado de devolver el n�mero de p�ginas disponibles en el
	 * vector de elementos.
	 * 
	 * @return N�mero de p�ginas generadas.
	 */
	public int size() {
		return pageSize;
	}

	/**
	 * M�todo encargado de asignar el n�mero de p�gina que se desea desplegar
	 * (si existe).
	 * 
	 * @param pPageIdx
	 *            N�mero de p�gina que se desea colocar.
	 * @throws Exception
	 *             Excepci�n que puede arrojar es por intentar colocar una
	 *             p�gina no existente.
	 */
	public void setPageIdx(int pPageIdx) throws Exception {
		if (pageIdx > 0 && pPageIdx <= pageSize) {
			pageIdx = pPageIdx - 1;
			rowIdx = -1;
			rowIdxMax = (pageIdx * linesXPage) + linesXPage;
			rowIdxMin = rowIdxMax - linesXPage;
		} else {
			throw new Exception("P�gina fuera de l�mites: " + pPageIdx);
		}
	}

	/**
	 * Este m�todo se encarga de devolver el n�mero de p�gina en la que nos
	 * encontramos ubicados.
	 * 
	 * @return N�mero de p�gina en la que nos encontramos.
	 */
	public int pageNo() {
		return pageIdx + 1;
	}

	/**
	 * M�todo encargado de obtener la siguiente p�gina, asignando los valores de
	 * rengl�n m�nimo y m�ximo.
	 */
	public void nextPage() {
		if (pageIdx >= size()) {
			pageIdx = 0;
		} else {
			pageIdx++;
		}
		rowIdx = -1;
		rowIdxMax = (pageIdx * linesXPage) + linesXPage;
		rowIdxMin = rowIdxMax - linesXPage;
	}

	/**
	 * M�todo encargado de obtener la p�gina anterior, asignando los valores de
	 * rengl�n m�nimo y m�ximo.
	 */
	public void previousPage() {
		if (pageIdx == 0) {
			pageIdx = size();
		} else {
			pageIdx--;
		}
		rowIdx = -1;
		rowIdxMax = (pageIdx * linesXPage) + linesXPage;
		rowIdxMin = rowIdxMax - linesXPage;
	}

	/**
	 * M�todo encargado de reubicar la paginaci�n en base al n�mero de elemento
	 * proporcionado.
	 * 
	 * @param pRowIdx
	 *            Identificador o n�mero de rengl�n a ubicar.
	 * @throws Exception
	 *             La excepci�n que se arroja se debe a que se intenta ubicar un
	 *             rengl�n inexistente.
	 */
	public void setRowIdx(int pRowIdx) throws Exception {
		if (pRowIdx > rowIdxMin && pRowIdx <= rowIdxMax) {
			rowIdx = pRowIdx - 1;
		} else {
			throw new Exception("Registro fuera de l�mites: " + pRowIdx);
		}
	}

	/**
	 * M�todo encargado de devolver el n�mero de renglon en el que estamos
	 * ubicados.
	 * 
	 * @return N�mero de rengl�n seleccionado.
	 */
	public int rowNo() {
		return rowIdx + 1;
	}

	/**
	 * M�todo que se encarga de obtener el primer elemento de la p�gina actual.
	 */
	public void firstRow() {
		rowIdx = pageIdx * linesXPage;
	}

	/**
	 * M�todo que se encarga de indicarnos si existe un rengl�n posterior al
	 * �ltimo desplegado.
	 * 
	 * @return Valor true si existe un registro adicional.
	 */
	public boolean nextRow() {
		if (rowIdx == -1)
			firstRow();
		else
			rowIdx++;
		if (rowIdx >= rowIdxMax)
			return false;
		return true;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}
