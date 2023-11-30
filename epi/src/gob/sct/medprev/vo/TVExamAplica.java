package gob.sct.medprev.vo;

import java.util.*;
import com.micper.sql.*;
import java.io.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Value Object ExamAplica
 * </p>
 * <p>
 * Description: Inicio del Examne
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier mendoza
 * @version 1.0
 */
public class TVExamAplica implements HashBeanInterface {
	private BeanPK pk;
	private int iCveClasificacion;
	private int iCveTpoLic;
	private java.sql.Date dtAplicacion;
	private int iCveDictamen;
	private int iCveExpediente;
	private int iCveTipoExamen;
	private int iCvePersonal;
	private int iNumExamen;
	private java.sql.Time tInicio;
	private java.sql.Time tConcluido;
	private java.sql.Timestamp tIniExa;
	private String ctIniExa;

	private java.sql.Date dtSolicitado;
	private java.sql.Date dtDictamen;
	private String cNombre;
	private String cApPaterno;
	private String cApMaterno;
	private String cRFC;
	private String cDscCategoria;
	private String cDscDiagnostico;
	private String cDscBreve;
	private int lDictExamen;
	private int lDictServicio;

	public BeanPK getPK() {
		pk = new BeanPK();
		pk.add("" + iCveExpediente);
		pk.add("" + iCvePersonal);
		pk.add("" + iNumExamen);
		return pk;
	}

	public TVExamAplica() {
	}

	public HashMap toHashMap() {
		HashMap hmfieldsTable = new HashMap();
		hmfieldsTable.put("iCveClasificacion", "" + iCveClasificacion);
		hmfieldsTable.put("iCveTpoLic", "" + iCveTpoLic);
		hmfieldsTable.put("dtAplicacion", "" + dtAplicacion);
		hmfieldsTable.put("iCveDictamen", "" + iCveDictamen);
		hmfieldsTable.put("iCveExpediente", "" + iCveExpediente);
		hmfieldsTable.put("iCveTipoExamen", "" + iCveTipoExamen);
		hmfieldsTable.put("iCvePersonal", "" + iCvePersonal);
		hmfieldsTable.put("iNumExamen", "" + iNumExamen);
		hmfieldsTable.put("tInicio", "" + tInicio);
		hmfieldsTable.put("tConcluido", "" + tConcluido);
		hmfieldsTable.put("tIniExa", "" + tIniExa);
		hmfieldsTable.put("ctIniExa", "" + ctIniExa);
		return hmfieldsTable;
	}

	public int getICveClasificacion() {
		return iCveClasificacion;
	}

	public void setICveClasificacion(int iCveClasificacion) {
		this.iCveClasificacion = iCveClasificacion;
	}

	public int getICveTpoLic() {
		return iCveTpoLic;
	}

	public void setICveTpoLic(int iCveTpoLic) {
		this.iCveTpoLic = iCveTpoLic;
	}

	public java.sql.Date getDtAplicacion() {
		return dtAplicacion;
	}

	public void setDtAplicacion(java.sql.Date dtAplicacion) {
		this.dtAplicacion = dtAplicacion;
	}

	public int getICveDictamen() {
		return iCveDictamen;
	}

	public void setICveDictamen(int iCveDictamen) {
		this.iCveDictamen = iCveDictamen;
	}

	public int getICveExpediente() {
		return iCveExpediente;
	}

	public void setICveExpediente(int iCveExpediente) {
		this.iCveExpediente = iCveExpediente;
	}

	public int getICveTipoExamen() {
		return iCveTipoExamen;
	}

	public void setICveTipoExamen(int iCveTipoExamen) {
		this.iCveTipoExamen = iCveTipoExamen;
	}

	public int getICvePersonal() {
		return iCvePersonal;
	}

	public void setICvePersonal(int iCvePersonal) {
		this.iCvePersonal = iCvePersonal;
	}

	public int getINumExamen() {
		return iNumExamen;
	}

	public void setINumExamen(int iNumExamen) {
		this.iNumExamen = iNumExamen;
	}

	public java.sql.Time getTConcluido() {
		return tConcluido;
	}

	public java.sql.Time getTInicio() {
		return tInicio;
	}

	public void setTInicio(java.sql.Time tInicio) {
		this.tInicio = tInicio;
	}

	public void setTConcluido(java.sql.Time tConcluido) {
		this.tConcluido = tConcluido;
	}

	public java.sql.Timestamp getTIniExa() {
		return tIniExa;
	}

	public void setTIniExa(java.sql.Timestamp tIniExa) {
		this.tIniExa = tIniExa;
	}

	public String getCTIniExa() {
		return ctIniExa;
	}

	public void setCTIniExa(String ctIniExa) {
		this.ctIniExa = ctIniExa;
	}

	public String getCApMaterno() {
		return cApMaterno;
	}

	public void setCApMaterno(String cApMaterno) {
		this.cApMaterno = cApMaterno;
	}

	public void setCDscBreve(String cDscBreve) {
		this.cDscBreve = cDscBreve;
	}

	public String getCDscBreve() {
		return cDscBreve;
	}

	public String getCDscCategoria() {
		return cDscCategoria;
	}

	public void setCDscCategoria(String cDscCategoria) {
		this.cDscCategoria = cDscCategoria;
	}

	public String getCDscDiagnostico() {
		return cDscDiagnostico;
	}

	public void setCDscDiagnostico(String cDscDiagnostico) {
		this.cDscDiagnostico = cDscDiagnostico;
	}

	public String getCNombre() {
		return cNombre;
	}

	public void setCNombre(String cNombre) {
		this.cNombre = cNombre;
	}

	public String getCRFC() {
		return cRFC;
	}

	public void setCRFC(String cRFC) {
		this.cRFC = cRFC;
	}

	public java.sql.Date getDtDictamen() {
		return dtDictamen;
	}

	public java.sql.Date getDtSolicitado() {
		return dtSolicitado;
	}

	public void setDtDictamen(java.sql.Date dtDictamen) {
		this.dtDictamen = dtDictamen;
	}

	public void setDtSolicitado(java.sql.Date dtSolicitado) {
		this.dtSolicitado = dtSolicitado;
	}

	public String getCApPaterno() {
		return cApPaterno;
	}

	public void setCApPaterno(String cApPaterno) {
		this.cApPaterno = cApPaterno;
	}

	public int getLDictExamen() {
		return lDictExamen;
	}

	public int getLDictServicio() {
		return lDictServicio;
	}

	public void setLDictExamen(int lDictExamen) {
		this.lDictExamen = lDictExamen;
	}

	public void setLDictServicio(int lDictServicio) {
		this.lDictServicio = lDictServicio;
	}
}


