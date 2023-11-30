package gob.sct.medprev.util.reglas;

import com.micper.excepciones.DAOException;

import gob.sct.medprev.dao.TDEXPResultadoExt1;

public class RServicioLaboratorioDeAnalisisClinicos {

	public RServicioLaboratorioDeAnalisisClinicos(){
	}
	
	
	public String ObligatoriosLaboratorio(String iCveExpediente, String iNumExamen) throws DAOException{
		boolean ObligatorioGlucosaHemoglobinaColesterol = false;
		boolean ObligatorioGlucosaColesterolTrigliceridos  = false;
		String codigoDeRegla = "";
		try{
			TDEXPResultadoExt1 dRes = new TDEXPResultadoExt1();

			// Activar como obligatorio la captura de Glucosa, Colesterol y Hemoglobina Glucosilada si 
			// Se da el caso que en ANTECEDENTES PERSONALES PATOLOGICOS se respondio SI a la pregunta 
			// Diagnostico Diabetes Mellitus
			ObligatorioGlucosaHemoglobinaColesterol = dRes.RegresaSintomaLogico(" "
													+ " SELECT LLOGICO FROM EXPRESULTADO "
													+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ " INUMEXAMEN = "+iNumExamen+" AND "
													+ " ICVESERVICIO = 50 AND "
													+ " ICVERAMA = 1 AND "
													+ " ICVESINTOMA = 92");
			
			// Activar como obligatorio la captura de de GLUCOSA, COLESTEROL Y TRIGLICERIDOS si 
		    // Se da el caso que en ANTECEDENTES PERSONALES PATOLOGICOS se respondio SI a la pregunta 
			// HIPERTENSION ARTERIAL
			ObligatorioGlucosaColesterolTrigliceridos = dRes.RegresaSintomaLogico(" "
													+ " SELECT LLOGICO FROM EXPRESULTADO "
													+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ " INUMEXAMEN = "+iNumExamen+" AND "
													+ " ICVESERVICIO = 50 AND "
													+ " ICVERAMA = 1 AND "
													+ " ICVESINTOMA = 14");			
			
			
			// Activar como obligatorio la captura de de GLUCOSA, COLESTEROL Y TRIGLICERIDOS si 
		    // Se da el caso que en SIGNOS VITALES Y SOMATOMETRIA el IMC es igual o mayor a 30
			if(!ObligatorioGlucosaColesterolTrigliceridos){
				double IMC = 0.0;
					IMC = dRes.RegresaSintomaDouble(" SELECT DVALORINI FROM EXPRESULTADO "
													+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ " INUMEXAMEN = "+iNumExamen+" AND "
													+ " ICVESERVICIO = 11 AND "
													+ " ICVERAMA = 1 AND "
													+ " ICVESINTOMA = 17");
					if(IMC>=30){
						ObligatorioGlucosaColesterolTrigliceridos =  true;
						//System.out.println("ReglaIMC");
					}
			}
			
	//////////Sintomas/////////////////
			
			//////////GLUCOSA/////////////////////////
			if(ObligatorioGlucosaHemoglobinaColesterol || ObligatorioGlucosaColesterolTrigliceridos){
				codigoDeRegla=codigoDeRegla+"\n"+
					     "if(form.dValorI_41.value =='' || form.dValorI_41.value < 0){"
							+"       cVMsg = cVMsg + \" - GLUCOSA \\n \"  "
							+"	} \n";
			}
			
			//////////HEMOGLOBINA GLUCOSILADA (HBA1C)/////////////////////////
			if(ObligatorioGlucosaHemoglobinaColesterol){
				codigoDeRegla=codigoDeRegla+"\n"
							+"if(form.dValorI_410.value =='' || form.dValorI_410.value < 0){"
							+"       cVMsg = cVMsg + \" - HEMOGLOBINA GLUCOSILADA (HBA1C) \\n \" "
							+"	} ";
			}
			
			//////////COLESTEROL TOTAL/////////////////////////
			if(ObligatorioGlucosaHemoglobinaColesterol || ObligatorioGlucosaColesterolTrigliceridos){
				codigoDeRegla=codigoDeRegla+"\n"
							+"if(form.dValorI_45.value =='' || form.dValorI_45.value < 0){"
							+"       cVMsg = cVMsg + \" - COLESTEROL TOTAL \\n \" "
							+"	}";
			}

			//////////TRIGLICÉRIDOS/////////////////////////
			if(ObligatorioGlucosaColesterolTrigliceridos){
				codigoDeRegla=codigoDeRegla+"\n"
						+"if(form.dValorI_46.value =='' || form.dValorI_46.value < 0){"
						+"       cVMsg = cVMsg + \" - TRIGLICÉRIDOS \\n \" "
						+"	} ";
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return codigoDeRegla;
	}
	

	
}
