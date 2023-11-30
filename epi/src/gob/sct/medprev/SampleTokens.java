/*
 * SampleTokens.java
 *
 * Created on March 13, 2006, 11:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gob.sct.medprev;

/**
 * 
 * @author Administrator
 */
public interface SampleTokens {
	String[] UNIQUE_TOKENS = { "File  ", "Acquired", "Sample Name",
			"Number of Compounds in Database" };
	String[] TOKENS = { "Ret Time", "Q1", "Compound:", "Ret Time",
			"Concentration", "Q1", "Q2" };
	String EXTENSION = ".erp";
	int iPosIdentifica = 3;
	String SEPARADOR = "\\";
	int iTamNombre = 8;

	// Acquired -->LC.iCveAnalisis
	// sampleDate -->LC.dtAnalisis

	// Ret Time -->CA.dTmpRetencD la primera ocurrencia
	// CA.dIon05 -->Q1 y 2 pipes hasta el primer no espacio
	// CA.dTmpRetenc -->Ret Time 2da ocurrencia
	// CA.dResultadoDil-->Concentration la primera con base en la anterior
	// CA.dIon02 --> Q1 con base en la anterior
	// CA.dIon03 --> Q2 con base en la anterior
}
