package gob.sct.medprev.msc;

import java.util.*;

public class PrefijosLicenciasBean {

	public static final String[] DEPARTAMENTO = { "ACAPULCO", "AGUASCALIENTES",
			"C. METROPOLITANO BOMBAS", "C. METROPOLITANO VALLEJO", "CAMPECHE",
			"CANCUN", "CD. JUAREZ", "COATZACOALCOS", "COLIMA", "CUERNAVACA",
			"CULIACAN", "CHIHUAHUA", "DURANGO", "GUADALAJARA", "HERMOSILLO",
			"LA PAZ", "LAZARO CARDENAS", "LEON", "MAZANILLO", "MATAMOROS",
			"MAZATLAN", "MERIDA", "MEXICALI", "MONTERREY", "MORELIA",
			"NUEVO LAREDO", "OAXACA", "ORIZABA", "PACGUCA", "POZA RICA",
			"PUEBLA", "QUERETARO", "REYNOSA", "SALTILLO", "SAN AG POTOSI",
			"TAMPICO", "TAPACHULA", "TEHUANTEPEC", "TEPIC", "TIJUANA",
			"TLAXCALA", "TOLUCA", "TORREON", "TUXTLA GUTIERREZ", "VERACRUZ",
			"VILLA HERMOSA", "ZACATECAS" };

	public static final String[] ESTADO = { "GUERRERO", "AGUASCALIENTES",
			"DISTRITO FEDERAL", "DISTRITO FEDERAL", "CAMPECHE", "QUINTANA ROO",
			"CHIHUAHUA", "VERACRUZ", "COLIMA", "MORELOS", "SINALOA",
			"CHIHUAHUA", "DURANGO", "JALISCO", "SONORA", "BAJA CALIFORNIA SUR",
			"MICHOACAN", "GUANAJUATO", "COLIMA", "TAMAULIPAS", "SINALOA",
			"YUCATAN", "BAJA CALIFORNIA", "NUEVO LEON", "MICHOACAN",
			"TAMAULIPAS", "OAXACA", "VERACRUZ", "HIDALGO", "VERACRUZ",
			"PUEBLA", "QUERETARO", "TAMAULIPAS", "COAHUILA", "SAN AG POTOSI",
			"TAMAULIPAS", "CHIAPAS", "OAXACA", "NAYARIT", "BAJA CALIFORNIA",
			"TLAXCALA", "ESTADO DE MEXICO", "COAHUILA", "CHIAPAS", "VERACRUZ",
			"TABASCO", "ZACATECAS" };

	public static final String[] PREFIJO_ANTERIOR = { "GRO", "AGS", "DF1",
			"DF2", "CAMP", "QROO", "CHIH2", "VER3", "COL", "MOR", "SIN1",
			"CHIH1", "DGO", "JAL", "SON", "BCS", "MICH2", "GTO", "COL1",
			"TAMP1", "SIN2", "YUC", "BCN1", "NVOL", "MICH1", "TAMP2", "OAX1",
			"VER04", "HGO", "VER2", "PUE", "QRO", "TAMP4", "COAH1", "SLP",
			"TAMP3", "CHIS1", "OAX2", "NAY", "BCN2", "TLAX", "EDOM", "COAH2",
			"CHIS", "VER1", "TAB", "ZAC" };

	public static final String[] PREFIJO_SISTEMA = { "GRO00", "AGS00", "DF001",
			"DF002", "CAMP0", "QROO0", "CHIH2", "VER03", "COL00", "MOR00",
			"SIN01", "CHIH1", "DGO00", "JAL00", "SON00", "BCS00", "MICH2",
			"GTO00", "COL01", "TAMP1", "SIN02", "YUC00", "BCN01", "NVOL0",
			"MICH1", "TAMP2", "OAX01", "VER04", "HGO00", "VER02", "PUE00",
			"QRO00", "TAMP4", "COAH1", "SLP00", "TAMP3", "CHIS1", "OAX02",
			"NAY00", "BCN02", "TLAX0", "EDOM0", "COAH2", "CHIS0", "VER01",
			"TAB00", "ZAC00" };

	public static final String[] CLAVE_DEPTO = { "14", "44", "51", "55", "17",
			"62", "42", "34", "23", "05", "09", "27", "41", "03", "39", "50",
			"61", "48", "57", "46", "37", "26", "36", "02", "04", "20", "43",
			"07", "16", "10", "01", "35", "56", "13", "38", "32", "45", "28",
			"25", "24", "54", "15", "12", "19", "18", "40", "47" };

	private int iNumero;
	private String cDepartamento;
	private String cEstado;
	private String cPrefijoAnterior;
	private String cPrefijoSistema;
	private String cClaveDepto;

	/**
	 * @return Returns the cClaveDepto.
	 */
	public String getCClaveDepto() {
		return cClaveDepto;
	}

	/**
	 * @param claveDepto
	 *            The cClaveDepto to set.
	 */
	public void setCClaveDepto(String claveDepto) {
		cClaveDepto = claveDepto;
	}

	/**
	 * @return Returns the cDepartamento.
	 */
	public String getCDepartamento() {
		return cDepartamento;
	}

	/**
	 * @param departamento
	 *            The cDepartamento to set.
	 */
	public void setCDepartamento(String departamento) {
		cDepartamento = departamento;
	}

	/**
	 * @return Returns the cEstado.
	 */
	public String getCEstado() {
		return cEstado;
	}

	/**
	 * @param estado
	 *            The cEstado to set.
	 */
	public void setCEstado(String estado) {
		cEstado = estado;
	}

	/**
	 * @return Returns the cPrefijoAnterior.
	 */
	public String getCPrefijoAnterior() {
		return cPrefijoAnterior;
	}

	/**
	 * @param prefijoAnterior
	 *            The cPrefijoAnterior to set.
	 */
	public void setCPrefijoAnterior(String prefijoAnterior) {
		cPrefijoAnterior = prefijoAnterior;
	}

	/**
	 * @return Returns the cPrefijoSistema.
	 */
	public String getCPrefijoSistema() {
		return cPrefijoSistema;
	}

	/**
	 * @param prefijoSistema
	 *            The cPrefijoSistema to set.
	 */
	public void setCPrefijoSistema(String prefijoSistema) {
		cPrefijoSistema = prefijoSistema;
	}

	/**
	 * @return Returns the iNumero.
	 */
	public int getINumero() {
		return iNumero;
	}

	/**
	 * @param numero
	 *            The iNumero to set.
	 */
	public void setINumero(int numero) {
		iNumero = numero;
	}

	/**
	 * Obtiene la lista de todos los prefijos para licencia anteriores y de
	 * sistema
	 * 
	 * @return list
	 */
	public List getPrefijosLicenciasBean() {
		List list = new ArrayList();

		for (int i = 0; i < 47; i++) {
			PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
			bean.setINumero(i + 1);
			bean.setCDepartamento(DEPARTAMENTO[i]);
			bean.setCEstado(ESTADO[i]);
			bean.setCPrefijoAnterior(PREFIJO_ANTERIOR[i]);
			bean.setCPrefijoSistema(PREFIJO_SISTEMA[i]);
			bean.setCClaveDepto(CLAVE_DEPTO[i]);

			list.add(bean);
		}

		return list;
	}

	/**
	 * Obtiene el indice a partir de un Prefijo Anterior.
	 * 
	 * @param cPrefijo
	 * @return
	 */
	public int getIndicePrefijoAnterior(String cPrefijo) {
		int iIndice = 0;

		for (int i = 0; i < PREFIJO_ANTERIOR.length; i++) {
			if (cPrefijo.equalsIgnoreCase(PREFIJO_ANTERIOR[i])) {
				iIndice = i;
				break;
			}
		}

		return iIndice;
	}

	/**
	 * Obtiene el indice a partir de un Prefijo Sistema.
	 * 
	 * @param cPrefijo
	 * @return
	 */
	public int getIndicePrefijoSistema(String cPrefijo) {
		int iIndice = 0;

		for (int i = 0; i < PREFIJO_SISTEMA.length; i++) {
			if (cPrefijo.equalsIgnoreCase(PREFIJO_SISTEMA[i])) {
				iIndice = i;
				break;
			}
		}

		return iIndice;
	}

	public static void main(String[] args) {
		PrefijosLicenciasBean bean = new PrefijosLicenciasBean();
		System.out.println("PREFIJO ANTERIOR: "
				+ bean.getIndicePrefijoAnterior("VER2"));

		System.out.println("INDICE...BCN01: "
				+ bean.getIndicePrefijoSistema("BCN01"));
		System.out.println("Numero: " + bean.getIndicePrefijoSistema("BCN01")
				+ 1);
		System.out.println("Departamento: "
				+ DEPARTAMENTO[bean.getIndicePrefijoSistema("BCN01")]);
		System.out.println("Prefijo Anterior: "
				+ PREFIJO_ANTERIOR[bean.getIndicePrefijoSistema("BCN01")]);

		List list = bean.getPrefijosLicenciasBean();
		System.out
				.println("NUMERO  DEPARTAMENTO  ESTADO  ANTERIOR  SISTEMA  CLAVE");
		System.out
				.println("------------------------------------------------------");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(((PrefijosLicenciasBean) list.get(i))
					.getINumero()
					+ "  "
					+ ((PrefijosLicenciasBean) list.get(i)).getCDepartamento()
					+ "  "
					+ ((PrefijosLicenciasBean) list.get(i)).getCEstado()
					+ "  "
					+ ((PrefijosLicenciasBean) list.get(i))
							.getCPrefijoAnterior()
					+ "  "
					+ ((PrefijosLicenciasBean) list.get(i))
							.getCPrefijoSistema()
					+ "  "
					+ ((PrefijosLicenciasBean) list.get(i)).getCClaveDepto());
		}
	}
}
