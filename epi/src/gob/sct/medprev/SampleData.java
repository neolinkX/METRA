/*
 * SampleData.java
 *
 * Created on March 9, 2006, 10:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gob.sct.medprev;

import java.util.Calendar;
import java.util.ArrayList;

/**
 * 
 * @author Administrator
 */
public class SampleData {

	private Calendar LC_dtAnalisis;
	private String LC_iCveAnalisis;
	private String fileName;
	private String originalContent;
	private String Componentes;
	private java.sql.Date LC_dtAnalisisD;
	private ArrayList CA_Componente;
	private ArrayList CA_dTmpRetencD;
	private ArrayList CA_dIon05;
	private ArrayList CA_dTmpRetenc;
	private ArrayList CA_dResultadoDil;
	private ArrayList CA_dIon02;
	private ArrayList CA_dIon03;
	private String LC_SampleName;

	/** Creates a new instance of SampleData */
	public SampleData(String fileName, String originalContent) {
		setFileName(fileName);
		setOriginalContent(originalContent);
		setCA_dIon02(new ArrayList());
		setCA_dIon03(new ArrayList());
		setCA_dIon05(new ArrayList());
		setCA_dResultadoDil(new ArrayList());
		setCA_dTmpRetenc(new ArrayList());
		setCA_dTmpRetencD(new ArrayList());
		setCA_Componente(new ArrayList());
	}

	public Calendar getLC_dtAnalisis() {
		return LC_dtAnalisis;
	}

	public void setLC_dtAnalisis(Calendar LC_dtAnalisis) {
		this.LC_dtAnalisis = LC_dtAnalisis;
	}

	public String getLC_iCveAnalisis() {
		return LC_iCveAnalisis;
	}

	public void setLC_iCveAnalisis(String LC_iCveAnalisis) {
		this.LC_iCveAnalisis = LC_iCveAnalisis;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ArrayList getCA_dTmpRetencD() {
		return CA_dTmpRetencD;
	}

	public void setCA_dTmpRetencD(ArrayList CA_dTmpRetencD) {
		this.CA_dTmpRetencD = CA_dTmpRetencD;
	}

	public ArrayList getCA_dIon05() {
		return CA_dIon05;
	}

	public void setCA_dIon05(ArrayList CA_dIon05) {
		this.CA_dIon05 = CA_dIon05;
	}

	public ArrayList getCA_dTmpRetenc() {
		return CA_dTmpRetenc;
	}

	public void setCA_dTmpRetenc(ArrayList CA_dTmpRetenc) {
		this.CA_dTmpRetenc = CA_dTmpRetenc;
	}

	public ArrayList getCA_dResultadoDil() {
		return CA_dResultadoDil;
	}

	public void setCA_dResultadoDil(ArrayList CA_dResultadoDil) {
		this.CA_dResultadoDil = CA_dResultadoDil;
	}

	public ArrayList getCA_dIon02() {
		return CA_dIon02;
	}

	public void setCA_dIon02(ArrayList CA_dIon02) {
		this.CA_dIon02 = CA_dIon02;
	}

	public ArrayList getCA_dIon03() {
		return CA_dIon03;
	}

	public void setCA_dIon03(ArrayList CA_dIon03) {
		this.CA_dIon03 = CA_dIon03;
	}

	public String getOriginalContent() {
		return originalContent;
	}

	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}

	public String getComponentes() {
		return Componentes;
	}

	public void setComponentes(String Componentes) {
		this.Componentes = Componentes;
	}

	public ArrayList getCA_Componente() {
		return CA_Componente;
	}

	public void setCA_Componente(ArrayList CA_Componente) {
		this.CA_Componente = CA_Componente;
	}

	public java.sql.Date getLC_dtAnalisisD() {
		return LC_dtAnalisisD;
	}

	public void setLC_dtAnalisisD(java.sql.Date LC_dtAnalisisD) {
		this.LC_dtAnalisisD = LC_dtAnalisisD;
	}

	public String getLC_SampleName() {
		return LC_SampleName;
	}

	public void setLC_SampleName(String LC_SampleName) {
		this.LC_SampleName = LC_SampleName;
	}
}
