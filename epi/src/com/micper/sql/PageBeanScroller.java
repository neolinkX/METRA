package com.micper.sql;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Esta clase se encarga de realizar la navegaci�n en un conjunto de registros.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * La navegaci�n se realiza a nivel p�gina, es decir un grupo de registros. <br>
 * Tambi�n permite conocer si exiten p�ginas antes o despu�s de la actual, de
 * tal modo que pueda controlarse el panel de navegaci�n y el reposicionamiento.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>Jos� AG L�pez Hern�ndez
 *         <p>
 * @see <small><a
 *      href="./../ingsample/pg9900009CFG.html">com.micper.ingsample.pg9900009CFG
 *      </a></small>
 * @see <small><a
 *      href="./../ingsample/pg9902010CFG.html">com.micper.ingsample.pg9902010CFG
 *      </a></small>
 * @see <small><a
 *      href="./../ingsample/pg9903010CFG.html">com.micper.ingsample.pg9903010CFG
 *      </a></small>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-pg9900009.jsp\n-pg9902010.jsp\n-pg9903010.jsp');"
 *      >Click para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="PageBeanScroller.png">
 */

public class PageBeanScroller extends BeanScroller implements Serializable {
	/** N�mero de registros por cada p�gina. */
	private int rowsXPage = 10;
	/** N�mero de p�ginas disponibles. */
	private int pageSize = 0;
	/** Identificador de p�gina actual. */
	private int pageIdx = -1;
	/** Identificador de registro inicial dentro de la p�gina. */
	private int rowIdxMin = 0;
	/** Identificador de registro final dentro de la p�gina. */
	private int rowIdxMax = 0;

	/**
	 * Constructor que se encarga de inicializar el vector de beans para hacer
	 * el c�lculo de paginaci�n.
	 * 
	 * @param pBeans
	 *            Vector de beans que se desea paginar.
	 * @param pRowsXPage
	 *            N�mero de registros que se desean por p�gina.
	 */
	public PageBeanScroller(Vector pBeans, int pRowsXPage) {
		super(pBeans);
		setRowsXPage(pRowsXPage);
	}

	/**
	 * M�todo encargado de asignar el n�mero de registros que se desea por
	 * p�gina, cambiando valores de variables de control.
	 * 
	 * @param pRowsXPage
	 *            N�mero de registros que se asignar� a cada p�gina.
	 */
	public void setRowsXPage(int pRowsXPage) {
		rowsXPage = pRowsXPage;
		if (pRowsXPage > 0)
			rowsXPage = pRowsXPage;
		int lSize = rowSize() / rowsXPage;
		if (rowSize() % rowsXPage > 0)
			pageSize = lSize + 1;
		else
			pageSize = lSize;
	}

	/**
	 * M�todo encargado de devolver el n�mero de p�ginas existentes en el
	 * vector.
	 * 
	 * @return N�mero de p�ginas existentes.
	 */
	public int pageSize() {
		return pageSize;
	}

	/**
	 * M�todo encargado de indicar el n�mero de p�gina actual.
	 * 
	 * @return N�mero de p�gina actual.
	 */
	public int pageNo() {
		return pageIdx + 1;
	}

	/**
	 * M�todo encargado de indicar el n�mero de registro inicial de la p�gina.
	 * 
	 * @return N�mero de registro inicial de la p�gina.
	 */
	public int pageMinRow() {
		return rowIdxMin + 1;
	}

	/**
	 * M�todo encargado de indicar el n�mero de registro m�ximo de la p�gina.
	 * 
	 * @return N�mero de registro m�ximo de la p�gina.
	 */
	public int pageMaxRow() {
		return rowIdxMax + 1;
	}

	/**
	 * M�todo encargado de colocar el n�mero de registro incial de la p�gina.
	 */
	private void setFirstRowOfPage() {
		rowIdxMin = pageIdx * rowsXPage;
		rowIdxMax = rowIdxMin + rowsXPage - 1;
		try {
			setRowIdx(rowIdxMin + 1);
		} catch (Exception ex) {
		}
	}

	/**
	 * M�todo encargado de colocar el n�mero de p�gina indicado.
	 * 
	 * @param pPageIdx
	 *            N�mero de p�gina que se desea seleccionar.
	 * @throws Exception
	 *             El error que puede arrojarse se debe a que se selecciona una
	 *             p�gina inexistente.
	 */
	public void setPageIdx(int pPageIdx) throws Exception {
		if (pageSize() <= 0)
			return;
		if (pPageIdx > 0 && pPageIdx <= pageSize())
			pageIdx = pPageIdx - 1;
		else
			throw new Exception("P�gina fuera de l�mites: " + pPageIdx);
		setFirstRowOfPage();
	}

	/**
	 * M�todo encargado de indicar si existe una p�gina siguiente o no.
	 * 
	 * @return Valor true en caso de existir una siguiente p�gina, false en caso
	 *         contrario.
	 */
	public boolean hasNextPage() {
		if (pageSize() <= 1)
			return false;
		if (pageIdx > 0 && pageIdx >= (pageSize() - 1))
			return false;
		return true;
	}

	/**
	 * M�todo encargado de indicar si existe una p�gina previa o no.
	 * 
	 * @return Valor true en caso de existir una p�gina previa, false en caso
	 *         contrario.
	 */
	public boolean hasPrevPage() {
		if (pageSize() <= 1)
			return false;
		if (pageIdx <= 0)
			return false;
		return true;
	}

	/**
	 * M�todo encargado de seleccionar la primer p�gina del vector.
	 */
	public void firstPage() {
		if (pageSize() > 0)
			pageIdx = 0;
		setFirstRowOfPage();
	}

	/**
	 * M�todo encargado de seleccionar la siguiente p�gina del vector.
	 * 
	 * @return Valor true en caso de que sea exitoso el reposicionamiento, false
	 *         en caso contrario.
	 */
	public boolean nextPage() {
		if (pageIdx == pageSize())
			firstPage();
		else if (pageSize() > 0)
			pageIdx++;
		if (pageSize() == 0 || pageIdx >= pageSize())
			return false;
		setFirstRowOfPage();
		return true;
	}

	/**
	 * M�todo encargado de seleccionar la siguiente p�gina a la p�gina
	 * proporcionada.
	 * 
	 * @param pPageIdx
	 *            N�mero de p�gina a la que se desea reposicionar.
	 * @return Valor true en caso de que se pueda colocar la p�gina siguiente,
	 *         false en caso contrario.
	 */
	public boolean nextPageTo(int pPageIdx) {
		try {
			setPageIdx(pPageIdx);
		} catch (Exception ex) {
		}
		return nextPage();
	}

	/**
	 * M�todo encargado de seleccionar la p�gina anterior del vector.
	 * 
	 * @return Valor true en caso de que sea exitoso el reposicionamiento, false
	 *         en caso contrario.
	 */
	public boolean prevPage() {
		if (pageIdx == -1)
			firstPage();
		else if (pageSize() > 0)
			pageIdx--;
		if (pageSize() == 0 || pageIdx < 0)
			return false;
		setFirstRowOfPage();
		return true;
	}

	/**
	 * M�todo encargado de seleccionar la p�gina previa a la p�gina
	 * proporcionada.
	 * 
	 * @param pPageIdx
	 *            N�mero de p�gina a la que se desea reposicionar.
	 * @return Valor true en caso de que se pueda colocar la p�gina anterior,
	 *         false en caso contrario.
	 */
	public boolean prevPageTo(int pPageIdx) {
		try {
			setPageIdx(pPageIdx);
		} catch (Exception ex) {
		}
		return prevPage();
	}

	/**
	 * M�todo encargado de seleccionar la �ltima p�gina del vector.
	 */
	public void lastPage() {
		if (pageSize() > 0)
			pageIdx = pageSize() - 1;
		setFirstRowOfPage();
	}

	/**
	 * M�todo encargado de posicionar el apuntador del scroller en el registro
	 * con la llave primaria proporcionada. <br>
	 * Adicionalmente se posicionar� en la p�gina que se encuentra el registro.
	 * 
	 * @param pBeanPK
	 *            Objeto BeanPK con los campos que forman la llave primaria del
	 *            bean.
	 */
	public void setPageByRowPK(BeanPK pBeanPK) {
		super.setRowByPK(pBeanPK);
		int mRowNo = rowNo();
		while (nextPage()) {
			if (mRowNo >= pageMinRow() && mRowNo <= pageMaxRow()) {
				break;
			}
		}
	}

	/**
	 * M�todo que se encarga de inicializar los apuntadores del scroller.
	 */
	public void start() {
		super.prevRow();
	}

	/**
	 * Muve el cursor al siguiente registro en la p�gina actual
	 */
	/**
	 * M�todo que se encarga de mover el cursor al siguiente registro en la
	 * p�gina actual.
	 * 
	 * @return Valor true en caso que se haya logrado el reposicionamiento,
	 *         false en caso contrario.
	 */
	public boolean nextRow() {
		if (rowNo() < pageMaxRow() && rowNo() < rowSize()) {
			super.nextRow();
			return true;
		}
		return false;
	}

	/**
	 * M�todo encargado de regresar un vector con los beans de la pagina actual.
	 * 
	 * @return Vector de beans de la p�gina actual.
	 */
	public Vector getPageVector() {
		Vector vResultado = new Vector();
		Vector vBeans = super.getVectorBeans();
		int iMax = rowIdxMax + 1;
		if (iMax > vBeans.size())
			iMax = vBeans.size();
		for (int i = rowIdxMin; i < iMax; i++)
			vResultado.add(vBeans.get(i));
		return vResultado;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.defaultWriteObject();
	}

	private void readObject(ObjectInputStream ois)
			throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}
}
