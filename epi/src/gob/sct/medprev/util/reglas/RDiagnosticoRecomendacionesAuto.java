package gob.sct.medprev.util.reglas;

import com.micper.excepciones.DAOException;
import gob.sct.medprev.dao.TDEXPResultadoExt1;

public class RDiagnosticoRecomendacionesAuto {

	public RDiagnosticoRecomendacionesAuto(){
	}
	 
	// Activar como obligatorio la captura de Glucosa, Colesterol y Hemoglobina Glucosilada si 
	// Se da el caso que en ANTECEDENTES PERSONALES PATOLOGICOS se respondio SI a la pregunta 
	// Diagnostico Diabetes Mellitus
	public boolean DiagnosticoDiabetesMellitusAntecedentesPersPat(String iCveExpediente, String iNumExamen) throws DAOException{
		boolean obligatorio = false;
		try{
			TDEXPResultadoExt1 dRes = new TDEXPResultadoExt1();
			obligatorio = dRes.RegresaSintomaLogico(" "
									+ " SELECT LLOGICO FROM EXPRESULTADO "
									+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
									+ " INUMEXAMEN = "+iNumExamen+" AND "
									+ " ICVESERVICIO = 50 AND "
									+ " ICVERAMA = 1 AND "
									+ " ICVESINTOMA = 92");
			if(obligatorio){
				System.out.println("Diagnostivo Diabetes = SI");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return obligatorio;
	}
	
	
	// Activar como obligatorio la captura de Glucosa, Colesterol y Hemoglobina Glucosilada si 
		// Se da el caso que en ANTECEDENTES PERSONALES PATOLOGICOS se respondio SI a la pregunta 
		// Diagnostico Diabetes Mellitus
		public boolean DiagnosticoDiabetesMellitusAntecedentesPersPatInsulinodependiente(String iCveExpediente, String iNumExamen) throws DAOException{
			boolean obligatorio = false;
			try{
				TDEXPResultadoExt1 dRes = new TDEXPResultadoExt1();
				obligatorio = dRes.RegresaSintomaLogico(" "
										+ " SELECT LLOGICO FROM EXPRESULTADO "
										+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
										+ " INUMEXAMEN = "+iNumExamen+" AND "
										+ " ICVESERVICIO = 50 AND "
										+ " ICVERAMA = 1 AND "
										+ " ICVESINTOMA = 91");
				if(obligatorio){
					System.out.println("Diagnostivo Diabetes Insulinodependiente = SI");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return obligatorio;
		}
		
	
	
		// Se da el caso que en ANTECEDENTES PERSONALES PATOLOGICOS se respondio SI a la pregunta 
		// HipertensionArterial
		public boolean DiagnosticoHipertensionArterialAntecedentesPersPat(String iCveExpediente, String iNumExamen) throws DAOException{
			boolean obligatorio = false;
			try{
				TDEXPResultadoExt1 dRes = new TDEXPResultadoExt1();
				obligatorio = dRes.RegresaSintomaLogico(" "
						+ " SELECT LLOGICO FROM EXPRESULTADO "
						+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
						+ " INUMEXAMEN = "+iNumExamen+" AND "
						+ " ICVESERVICIO = 50 AND "
						+ " ICVERAMA = 1 AND "
						+ " ICVESINTOMA = 14");			

				if(obligatorio){
					System.out.println("Diagnostivo Diabetes= SI");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return obligatorio;
		}
		
	
}
