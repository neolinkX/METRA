package gob.sct.medprev.msc;

/**
 * Esta clase genera el No. de Analisis.
 * 
 * @author Rafael Alcocer Caldera
 * @version 1.0
 */
public class GenerarNumAnalisis {

	/**
	 * Genera el primer Numero de Analisis "000001". En caso contrario regresa
	 * vacio "".
	 * 
	 * @param cAnio
	 * @param cUnidadMedica
	 * @return cNumAnalisis
	 */
	public String generaPrimerNumAnalisis(String cAnio, String cUnidadMedica) {
		if (cAnio.length() == 2 && cUnidadMedica.length() == 2) {
			return cAnio + cUnidadMedica + "000001";
		} else {
			return "";
		}
	}

	/**
	 * Obtiene el Siguiente Numero de Analisis.
	 * 
	 * @param cAnio
	 * @param cUnidadMedica
	 * @param cDigitos
	 * @return
	 */
	public String getSigNumAnalisis(String cAnio, String cUnidadMedica,
			String cDigitos) {
		String cDig = generarUltimosDigitos(cDigitos);

		// System.out.println("cDigitos: " + cDigitos);
		// System.out.println("cDig: " + cDig);

		if (cDigitos.equals("999999")) {
			return "";
		} else if (!cAnio.equals("") && cAnio.length() == 4
				&& !cUnidadMedica.equals("") && cUnidadMedica.length() == 1
				&& !cDigitos.equals("") && cDigitos.length() == 6) {
			cAnio = cAnio.substring(2, 4);
			cUnidadMedica = "0" + cUnidadMedica;

			return cAnio + cUnidadMedica + cDig;
		} else if (!cAnio.equals("") && cAnio.length() == 2
				&& !cUnidadMedica.equals("") && cUnidadMedica.length() == 2
				&& !cDigitos.equals("") && cDigitos.length() == 6) {
			return cAnio + cUnidadMedica + cDig;
		} else {
			return "";
		}
	}

	/**
	 * Genera los siguientes 6 digitos a partir del último obtenido (cDigitos)
	 * con formato XXXXXX
	 * 
	 * @param cDigitos
	 * @return
	 */
	public String generarUltimosDigitos(String cDigitos) {
		int iDigitos = Integer.parseInt(cDigitos) + 1;

		if (String.valueOf(iDigitos).length() == 1) {
			return "00000" + iDigitos;
		} else if (String.valueOf(iDigitos).length() == 2) {
			return "0000" + iDigitos;
		} else if (String.valueOf(iDigitos).length() == 3) {
			return "000" + iDigitos;
		} else if (String.valueOf(iDigitos).length() == 4) {
			return "00" + iDigitos;
		} else if (String.valueOf(iDigitos).length() == 5) {
			return "0" + iDigitos;
		} else if (String.valueOf(iDigitos).length() == 6) {
			return "" + iDigitos;
		}

		return "";
	}
}
