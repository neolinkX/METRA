package com.micper.ingsw;

import com.micper.excepciones.DAOException;
import com.micper.sql.DAOBase;

import gob.sct.medprev.dao.TDEXPExamCatExt1;
import gob.sct.medprev.dao.TDEXPMultas;

public class Multas extends DAOBase  {

	public int ObteniendoMontodeLaMulta(int iCveExpediente) throws DAOException{
		int iMonto = 0;
		TDEXPExamCatExt1 dEXPExamCatExt1 = new TDEXPExamCatExt1();
		TDEXPMultas dEXPMultas = new TDEXPMultas();
		try{
			if(dEXPExamCatExt1.MultaRevaloracion(iCveExpediente)){
				iMonto = 500;
				if(dEXPMultas.MultaReincidencia(iCveExpediente)){
					iMonto = 1000;	
				}
			}
			if(dEXPMultas.RealizoPagoPorMulta(iCveExpediente)) {
				iMonto = 0;
			}
			System.out.println(iMonto);
		}catch(Exception ex){
			warn("Multas", ex);
		}
		return iMonto;
		
	}
	
	
	
}
