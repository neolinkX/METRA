package gob.sct.medprev.vo;

import java.util.*;

import com.micper.sql.*;

/**
 * <p>
 * Title: Value Object Datos
 * </p>
 * 
 * @author FCS
 * @version 1.0
 */
public class TVPEREmpresa implements HashBeanInterface {
	private BeanPK pk;

	private String sDscEmpresa;
	private String sDenominacion;
	private int iCveEmpresa;
	private String sRFC;
	private int iCveGrupoEmp;
	private String sIdDgpmpt;
	private String sTpoPersona;
	private int iCveUniMed;
	private String sDscUniMed;
	private int iCveMdoTrans;
	private String sDscMdoTrans;
	private java.sql.Date dtRegistro;
	private String sRegistro;

	// ---------------- Datos UNIDAD MEDICA --------------------------
	private int iCveUniMedCbo;
	private String sDscUniMedCbo;

	// ---------------- Datos MODO DE TRANSPORTE --------------------------
	private int iCveMdoTransCbo;
	private String sDscMdoTransCbo;

	// ---------------- Datos DE DIRECCION --------------------------
	private int icvedomicilio;
	private String ccalle;
	private String ccolonia;
	private String cciudad;
	private int icp;
	private String cnombrePais;
	private String cnombreEstado;
	private String cnombreMun;
	private String ctel;
	private String cfax;
	private String ccorreoelec;
	private int lactivo;
	private int icvepais;
	private int icveestado;
	private int icvemunicipio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + sDscEmpresa);
		return pk;
	}

	public TVPEREmpresa() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("sDscEmpresa", "" + sDscEmpresa);
		hmfieldsTable.put("sDenominacion", "" + sDenominacion);
		hmfieldsTable.put("sRFC", "" + sRFC);
		hmfieldsTable.put("iCveGrupoEmp", "" + iCveGrupoEmp);
		hmfieldsTable.put("sIdDgpmpt", "" + sIdDgpmpt);
		hmfieldsTable.put("sTpoPersona", "" + sTpoPersona);
		hmfieldsTable.put("sDscUniMed", "" + sDscUniMed);
		hmfieldsTable.put("sDscMdoTrans", "" + sDscMdoTrans);
		hmfieldsTable.put("sRegistro", "" + sRegistro);
		hmfieldsTable.put("icvedomicilio", "" + icvedomicilio);
		hmfieldsTable.put("ccalle", "" + ccalle);
		hmfieldsTable.put("ccolonia", "" + ccolonia);
		hmfieldsTable.put("cciudad", "" + cciudad);
		hmfieldsTable.put("icp", "" + icp);
		hmfieldsTable.put("cnombrePais", "" + cnombrePais);
		hmfieldsTable.put("cnombreEstado", "" + cnombreEstado);
		hmfieldsTable.put("cnombreMun", "" + cnombreMun);
		hmfieldsTable.put("ctel", "" + ctel);
		hmfieldsTable.put("cfax", "" + cfax);
		hmfieldsTable.put("ccorreoelec", "" + ccorreoelec);
		hmfieldsTable.put("lactivo", "" + lactivo);
		hmfieldsTable.put("icvepais", "" + icvepais);
		hmfieldsTable.put("icveestado", "" + icveestado);
		hmfieldsTable.put("icvemunicipio", "" + icvemunicipio);

		return hmfieldsTable;
	}

	public String getSDscEmpresa() {
		return sDscEmpresa;
	}

	public void setSDscEmpresa(String sDscEmpresa) {
		this.sDscEmpresa = sDscEmpresa;
	}

	public String getSDenominacion() {
		return sDenominacion;
	}

	public void setSDenominacion(String sDenominacion) {
		this.sDenominacion = sDenominacion;
	}

	public int getICveEmpresa() {
		return iCveEmpresa;
	}

	public void setICveEmpresa(int iCveEmpresa) {
		this.iCveEmpresa = iCveEmpresa;
	}

	public String getSRFC() {
		return sRFC;
	}

	public void setSRFC(String sRFC) {
		this.sRFC = sRFC;
	}

	public int getICveGrupoEmp() {
		return iCveGrupoEmp;
	}

	public void setICveGrupoEmp(int iCveGrupoEmp) {
		this.iCveGrupoEmp = iCveGrupoEmp;
	}

	public String getSIdDgpmpt() {
		return sIdDgpmpt;
	}

	public void setSIdDgpmpt(String sIdDgpmpt) {
		this.sIdDgpmpt = sIdDgpmpt;
	}

	public String getSTpoPersona() {
		return sTpoPersona;
	}

	public void setSTpoPersona(String sTpoPersona) {
		this.sTpoPersona = sTpoPersona;
	}

	public int getICveUniMed() {
		return iCveUniMed;
	}

	public void setICveUniMed(int iCveUniMed) {
		this.iCveUniMed = iCveUniMed;
	}

	public String getSDscUniMed() {
		return sDscUniMed;
	}

	public void setSDscUniMed(String sDscUniMed) {
		this.sDscUniMed = sDscUniMed;
	}

	public int getICveMdoTrans() {
		return iCveMdoTrans;
	}

	public void setICveMdoTrans(int iCveMdoTrans) {
		this.iCveMdoTrans = iCveMdoTrans;
	}

	public String getSDscMdoTrans() {
		return sDscMdoTrans;
	}

	public void setSDscMdoTrans(String sDscMdoTrans) {
		this.sDscMdoTrans = sDscMdoTrans;
	}

	public java.sql.Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(java.sql.Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getSRegistro() {
		return sRegistro;
	}

	public void setSRegistro(String sRegistro) {
		this.sRegistro = sRegistro;
	}

	// ---------------- Datos UNIDAD MEDICA --------------------------
	public int getICveUniMedCbo() {
		return iCveUniMedCbo;
	}

	public void setICveUniMedCbo(int iCveUniMedCbo) {
		this.iCveUniMedCbo = iCveUniMedCbo;
	}

	public String getSDscUniMedCbo() {
		return sDscUniMedCbo;
	}

	public void setSDscUniMedCbo(String sDscUniMedCbo) {
		this.sDscUniMedCbo = sDscUniMedCbo;
	}

	// ---------------- Datos MODO DE TRANSPORTE --------------------------
	public int getICveMdoTransCbo() {
		return iCveMdoTransCbo;
	}

	public void setICveMdoTransCbo(int iCveMdoTransCbo) {
		this.iCveMdoTransCbo = iCveMdoTransCbo;
	}

	public String getSDscMdoTransCbo() {
		return sDscMdoTransCbo;
	}

	public void setSDscMdoTransCbo(String sDscMdoTransCbo) {
		this.sDscMdoTransCbo = sDscMdoTransCbo;
	}

	// ---------------- Datos MODO DE TRANSPORTE --------------------------
	public int geticvedomicilio() {
		return icvedomicilio;
	}

	public void seticvedomicilio(int icvedomicilio) {
		this.icvedomicilio = icvedomicilio;
	}

	public String getccalle() {
		return ccalle;
	}

	public void setccalle(String ccalle) {
		this.ccalle = ccalle;
	}

	public String getccolonia() {
		return ccolonia;
	}

	public void setccolonia(String ccolonia) {
		this.ccolonia = ccolonia;
	}

	public String getcciudad() {
		return cciudad;
	}

	public void setcciudad(String cciudad) {
		this.cciudad = cciudad;
	}

	public int geticp() {
		return icp;
	}

	public void seticp(int icp) {
		this.icp = icp;
	}

	public String getcnombrePais() {
		return cnombrePais;
	}

	public void setcnombrePais(String cnombrePais) {
		this.cnombrePais = cnombrePais;
	}

	public String getcnombreEstado() {
		return cnombreEstado;
	}

	public void setcnombreEstado(String cnombreEstado) {
		this.cnombreEstado = cnombreEstado;
	}

	public String getcnombreMun() {
		return cnombreMun;
	}

	public void setcnombreMun(String cnombreMun) {
		this.cnombreMun = cnombreMun;
	}

	public String getctel() {
		return ctel;
	}

	public void setctel(String ctel) {
		this.ctel = ctel;
	}

	public String getcfax() {
		return cfax;
	}

	public void setcfax(String cfax) {
		this.cfax = cfax;
	}

	public String getccorreoelec() {
		return ccorreoelec;
	}

	public void setccorreoelec(String ccorreoelec) {
		this.ccorreoelec = ccorreoelec;
	}

	public int getlactivo() {
		return lactivo;
	}

	public void setlactivo(int lactivo) {
		this.lactivo = lactivo;
	}

	public int geticvepais() {
		return icvepais;
	}

	public void seticvepais(int icvepais) {
		this.icvepais = icvepais;
	}

	public int geticveestado() {
		return icveestado;
	}

	public void seticveestado(int icveestado) {
		this.icveestado = icveestado;
	}

	public int geticvemunicipio() {
		return icvemunicipio;
	}

	public void seticvemunicipio(int icvemunicipio) {
		this.icvemunicipio = icvemunicipio;
	}

}
