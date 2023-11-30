package com.micper.ingsw;

import com.micper.util.*;
import java.io.Serializable;
import java.util.*;

/**
 * Clase encargada de obtener los valores del archivo globales.properties.
 * <p>
 * En esta clase se conservaran los parametros de configuracion de cada modulo
 * en un solo archivo de propiedades.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnologia Inred S.A. de C.V.
 * @author <dd>Jose AG Lopez Hernandez
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a href=
 *      "JavaScript:alert('Consulte los archivos:\n-*.jsp\n-pgXXXXXXXCFG.java\n-Archivos de ingenieria de software com.micper.ingsw.*.java');"
 *      >Click para mas informaci�n</a></small> </dd>
 *      <p>
 *      </p>
 *      <DT><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TParametro.png">
 */

public class TParametro implements Serializable {
	/**
     * 
     */
	private static long serialVersionUID = 1L;
	/** Variable que contendra todos los atributos del archivo de propiedades. */
	static private boolean lCreate = false;
	static TGetADMSEG tGetADMSEG = new TGetADMSEG();
	public static HashMap hmPropiedades = null;
	/**
	 * Variable que contendra el identificador del objeto en el momento de
	 * instanciar.
	 */
	int iIdent = 0;

	/** Variable que contiene el nombre del archivo de propiedades a emplear. */

	/**
	 * Constructor que se encarga de cargar los parametros de configuracion del
	 * archivo globales.properties.
	 * 
	 * @param cId
	 *            Identificador que se asignara al objeto que contiene los
	 *            parametros cargados.
	 */
	public TParametro(String cId) {
		iIdent = new Integer(cId).intValue();
		if (lCreate == false) {
			lCreate = true;
			this.setParametros();
		}
	}

	private void setParametros() {
		try {
			TGetADMSEG tGetADMSEG = new TGetADMSEG();
			String cDataSource = tGetADMSEG.getPropEspecifica("ConDB");
			hmPropiedades = tGetADMSEG.getPropiedades(iIdent, cDataSource);
		} catch (Exception e) {
			// System.out.print("TParametro.setParametros");
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo se encarga de cargar los atributos, dependiendo del valor del
	 * parametro se sobreescriben o no.
	 * 
	 * @param forceReload
	 *            Valor true indicara que se obligue a recargar los valores,
	 *            false solo si no se ha cargado.
	 */
	public void reload(boolean forceReload) {
		try {
			hmPropiedades = null;
			String cDataSource = tGetADMSEG.getPropEspecifica("ConDB");
			hmPropiedades = tGetADMSEG.getPropiedades(iIdent, cDataSource);
		} catch (Exception e) {
			// System.out.print("TParametro.reload");
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo se encarga de obtener el valor asignado a un par�metro en el
	 * archivo de propiedades. <br>
	 * En este caso se obtiene el valor del parametro que no tenga un prefijo de
	 * m�dulo.
	 * 
	 * @param cProp
	 *            Nombre del parametro cuyo valor se desea obtener.
	 * @return Valor del parametro deseado.
	 */
	public String getPropGeneral(String cProp) {
		String cValor = "";
		try {
			cValor = "" + hmPropiedades.get(cProp);
			if (cValor.compareTo("null") == 0) {
				cValor = "";
				// System.out.println("TParametro.La Propiedad General: " +
				// cProp + " No Fue Encontrada!");
			}
		} catch (Exception e) {
			// System.out.println("TParametro.getPropGeneral");
			e.printStackTrace();
		}
		return cValor;
	}

	/**
	 * Este metodo se encarga de obtener el valor asignado a un par�metro en el
	 * archivo de propiedades. <br>
	 * En este caso se obtendra el valor del parametro que tenga el prefijo que
	 * se establecio en el identificador.
	 * 
	 * @param cProp
	 *            Nombre del parametro cuyo valor se desea obtener.
	 * @return Valor del parametro deseado (correspondiente al que tenga el
	 *         prefijo).
	 */
	public String getPropEspecifica(String cProp) {
		String cValor = "";
		try {
			cValor = "" + hmPropiedades.get(iIdent + "." + cProp);
			if (cValor.compareTo("null") == 0) {
				cValor = "" + tGetADMSEG.getPropEspecifica(cProp);
				if (cValor.compareTo("null") == 0) {
					System.out.println("TParametro.La Propiedad Especifica: "
							+ iIdent + "." + cProp + " No Fue Encontrada!");
				}
			}
		} catch (Exception e) {
			// System.out.println("TParametro.getPropEspecifica: " + cProp);
			e.printStackTrace();
		}
		return cValor;
	}
}