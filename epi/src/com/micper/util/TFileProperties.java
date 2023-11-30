package com.micper.util;

import java.util.*;

/**
 * Clase para cargar archivo de propiedades solicitado.
 * <p>
 * Ingenier�a de Software generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de cargar un archivo de propiedades solicitado,
 * empleando el Locale de M�xico.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnolog�a Inred S.A. de C.V.
 * @author <dd>Jos� AG L�pez Hern�ndez
 *         <p>
 * @see <small><a
 *      href="./../ingsw/TListaErr.html">com.micper.ingsw.TListaErr</a></small>
 * @see <small><a
 *      href="./../ingsw/TParametro.html">com.micper.ingsw.TParametro</
 *      a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="TFileProperties.png">
 */
public class TFileProperties {
	/** Nombre del archivo de propiedades */
	private String cFileName;
	/** Objeto Locale generado en base a c�digo de pa�s y de lenguaje */
	private Locale cLocale;
	/** Objeto que contiene el juego de llaves y valores */
	private ResourceBundle cResourceBundle;

	/**
	 * Este constructor llama la creaci�n de una nueva instancia del archivo de
	 * propiedades en Espa�ol Mexicano.
	 * 
	 * @param pFileName
	 *            Nombre del archivo que se desea obtener
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Documentaci�n de C�digos de Pa�s e Idioma\n-java.util.Locale');">Click
	 *      para mas informaci�n</a>
	 */
	public TFileProperties(String pFileName) {
		this(pFileName, new Locale("es", "MX"));

	}

	/**
	 * Este constructor llama la creaci�n de una nueva instancia del archivo de
	 * propiedades en el C�digo de Pa�s y C�digo de Lenguaje especificados.
	 * 
	 * @param pFileName
	 *            Nombre del archivo que se desea obtener
	 * @param pPaisCode
	 *            Nombre del c�digo de pa�s a usar
	 * @param pLanguageCode
	 *            Nombre del c�digo de lenguaje a usar
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Documentaci�n de C�digos de Pa�s e Idioma\n-java.util.Locale');">Click
	 *      para mas informaci�n</a>
	 */
	public TFileProperties(String pFileName, String pPaisCode,
			String pLanguageCode) {
		this(pFileName, new Locale(pLanguageCode, pPaisCode));
	}

	/**
	 * Este constructor crea una nueva instancia del archivo de propiedades con
	 * el C�digo de Pa�s y C�digo de Lenguaje especificados como Locale.
	 * 
	 * @param pFileName
	 *            Nombre del archivo que se desea obtener
	 * @param pLocale
	 *            Objeto Locale que se cre� previamente, mismo con el que se
	 *            cargar� el archivo
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Documentaci�n de C�digos de Pa�s e Idioma\n-java.util.Locale');">Click
	 *      para mas informaci�n</a>
	 */
	public TFileProperties(String pFileName, Locale pLocale) {
		cFileName = pFileName;
		cLocale = pLocale;
		reload(false);
	}

	/**
	 * Este m�todo se encarga de realizar la carga de un archivo.
	 * 
	 * @param forceReload
	 *            Si el valor es true, recarga el archivo cada vez que se
	 *            solicita, de lo contrario solo si el objeto es nulo
	 *            <p>
	 * @see <a
	 *      href="JavaScript:alert('Documentaci�n sobre Recursos:\n-java.util.ResourceBundle');">Click
	 *      para mas informaci�n</a>
	 */
	public synchronized void reload(boolean forceReload) {
		if (cResourceBundle == null || forceReload) {
			cResourceBundle = ResourceBundle.getBundle(cFileName, cLocale);
			notify();
		}
	}

	/**
	 * Este m�todo se encarga de recuperar el valor de una llave del recurso
	 * indicado.
	 * 
	 * @param pKey
	 *            Nombre de la llave cuyo valor desea recuperar
	 *            <p>
	 * @return Valor de la propiedad solicitada
	 *         <p>
	 * @see <a
	 *      href="JavaScript:alert('Documentaci�n sobre Recursos:\n-java.util.ResourceBundle');">Click
	 *      para mas informaci�n</a>
	 */
	public String getProperty(String pKey) {
		return cResourceBundle.getString(pKey);
	}
}
