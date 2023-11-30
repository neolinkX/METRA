package gob.sct.medprev.util.reglas;

import com.micper.excepciones.DAOException;

import gob.sct.medprev.dao.TDEXPResultadoExt1;

public class ReglasDeDictamenCompuestas {


	
	public boolean ObligatoriosLaboratorio(String iCveExpediente, String iNumExamen) throws DAOException{
		boolean INSULINODEPENDIENTE = false;
		boolean DIAGNOSTICODEDIABETESMELLITUS = false;
		boolean NoApto = false;
		try{
			TDEXPResultadoExt1 dRes = new TDEXPResultadoExt1();
			
			// Verificar que haya contestado SI a la pregunta INSULINODEPENDIENTE
			INSULINODEPENDIENTE = dRes.RegresaSintomaLogico(" "
													+ " SELECT LLOGICO FROM EXPRESULTADO "
													+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ " INUMEXAMEN = "+iNumExamen+" AND "
													+ " ICVESERVICIO = 50 AND "
													+ " ICVERAMA = 1 AND "
													+ " ICVESINTOMA = 91");
			
			
			// Verificar que haya contestado SI a la pregunta DIAGNOSTICO DE DIABETES MELLITUS
			DIAGNOSTICODEDIABETESMELLITUS = dRes.RegresaSintomaLogico(" "
													+ " SELECT LLOGICO FROM EXPRESULTADO "
													+ " WHERE ICVEEXPEDIENTE = "+iCveExpediente+" AND "
													+ " INUMEXAMEN = "+iNumExamen+" AND "
													+ " ICVESERVICIO = 50 AND "
													+ " ICVERAMA = 1 AND "
													+ " ICVESINTOMA = 92");
			
			if(INSULINODEPENDIENTE && DIAGNOSTICODEDIABETESMELLITUS){
				NoApto = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return NoApto;
	}
	
	
}
