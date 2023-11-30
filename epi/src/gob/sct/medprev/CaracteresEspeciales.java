package gob.sct.medprev;

import java.util.*;

/**
 * 
 * Validacion de Strings con caracteres especiales unicode <br>
 * namecode, Lista de palabras<br>
 * contenido de codigo maligno XSS / SQLi. <br>
 * 
 * @author Ing. Andres Esteban Bernal Muñoz
 * @version v1.0
 * 
 */
public class CaracteresEspeciales {

	private HashSet nameCodeList;
	private HashSet uniCodeList;
	private HashSet SimbolosList;

	private final String cNamecode = "&quot,&amp,&apos,&lt,&gt,http,source,src,vbscript,javascript,language,script,alert";
	private final String cUnicode = "u0022,u0026,u0027,u003c,u003e";
	private final String cSimbolos = "\",&,\\,\',’,<,>,#,;,=,¿,?,°,¬,!,¡,$,%,:,*,~,[,],{,},^";

	// String cSimbolos2="\"&\\\'’<>#;¿?°¬!¡$%:*~[]{}^";
	public CaracteresEspeciales() { // Construct
		nameCodeList = new HashSet();
		uniCodeList = new HashSet();
		SimbolosList = new HashSet();
		onLoad();
	}

	/**
	 * Carga la listas nameCodeList, uniCodeList y SimbolosList
	 */
	private void onLoad() {
		OnloadNameCode();
		OnloaduniCode();
		OnloadSimbolos();
	}

	/**
	 * @return Devuelve la lista de NameCodes.
	 */
	public List getNameCodeList() {
		List array = new ArrayList<String>();
		for (Object NameCode : this.nameCodeList) {
			array.add((String) NameCode);
		}
		return array;
	}

	/**
	 * @param nameCode
	 *            Una variable String que sera agregada a la lista nameCode
	 * 
	 */
	public void addNameCode(String nameCode) {
		if (!nameCode.equals("") && nameCode != null) {
			this.nameCodeList.add(nameCode.toUpperCase());
		}
	}

	/**
	 * @return Devuelve la lista de Unicodes
	 */
	public List getUniCodeList() {
		List array = new ArrayList<String>();
		for (Object uniCode : this.uniCodeList) {
			array.add((String) uniCode);
		}
		return array;
	}

	/**
	 * @param uniCode
	 *            una variable String que sera agregada a la lista uniCode
	 * 
	 */
	public void addUniCode(String uniCode) {
		if (!uniCode.equals("") && uniCode != null) {
			this.uniCodeList.add(uniCode.toUpperCase());
		}
	}

	/**
	 * @return Devuelve la lista de Simbolos.
	 */
	public List getSimbolosList() {
		List array = new ArrayList<String>();
		for (Object Simbolo : this.SimbolosList) {
			array.add((String) Simbolo);
		}
		return array;
	}

	/**
	 * Agrega un Simbolo nuevo a la Lista.
	 * 
	 * @param simbolo
	 *            el Simbolo String que se agregara
	 * 
	 */
	public void addSimbolo(String simbolo) {
		if (!simbolo.equals("") && simbolo != null) {
			this.SimbolosList.add(simbolo.toUpperCase());
		}
	}

	/**
	 * carga la lista actual de NameCode.
	 * 
	 */
	public void OnloadNameCode() {
		StringTokenizer st = new StringTokenizer(this.cNamecode, ",");
		while (st.hasMoreTokens()) {
			this.addNameCode(st.nextToken());
		}
	}

	/**
	 * Carga la lista actual de uniCode.
	 */
	public void OnloaduniCode() {
		StringTokenizer st = new StringTokenizer(this.cUnicode, ",");
		while (st.hasMoreTokens()) {
			this.addUniCode(st.nextToken());
		}
	}

	/**
	 * Carga la Lista actual de Simbolos.
	 */
	public void OnloadSimbolos() {
		StringTokenizer st = new StringTokenizer(this.cSimbolos, ",");
		while (st.hasMoreTokens()) {
			this.addSimbolo(st.nextToken());
		}
	}

	/**
	 * 
	 * Remueve NameCodes encontrados de un cadena
	 * 
	 * @param str
	 *            Una cadena String.
	 * @return Devuelve un String formateado;
	 * 
	 */
	public String removeNameCode(String str) {
		List<String> nameCodeList = this.getNameCodeList();

		for (String nameCode : nameCodeList) {
			str = str.replace(nameCode, "");
		}
		return str;
	}

	/**
	 * 
	 * Remueve UniCodes encontrados de una cadena
	 * 
	 * @param str
	 *            Una cadena String.
	 * @return Devuelve una cadena formateada;
	 * 
	 */
	public String removeUnicode(String str) {
		List<String> uniCodeList = this.getUniCodeList();

		for (String uniCode : uniCodeList) {
			str = str.replace(uniCode, "");
		}

		return str;
	}

	/**
	 * 
	 * Remueve Simbolos encontrados de una cadena
	 * 
	 * @param str
	 *            Una cadena String.
	 * @return Devuelve una cadena formateada;
	 * 
	 */
	public String removeSigns(String str) {
		List<String> SimbolosList = this.getSimbolosList();

		for (String Simbolo : SimbolosList) {
			str = str.replace(Simbolo, "");
		}

		return str;
	}

	/**
	 * 
	 * Remueve NameCode,Unicode y Simbolos encontrados de una cadena
	 * 
	 * @param str
	 *            Variable de tipo String que su contenido sera formateado para
	 *            eliminar caracteres especiales (Simbolos), Unicode, y
	 *            NameCode. Nota: deben estar en ese orden.
	 * @return Devuelve una cadena formateada;
	 * 
	 */
	public String scanCompleto(String str) {

		str = this.removeNameCode(str);
		str = this.removeUnicode(str);
		str = this.removeSigns(str);

		return str;
	}

}
