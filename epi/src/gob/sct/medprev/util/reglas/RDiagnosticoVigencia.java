package gob.sct.medprev.util.reglas;   

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.micper.excepciones.DAOException;

import gob.sct.medprev.dao.TDEXPDiagnostico;
import gob.sct.medprev.dao.TDEXPDictamenServ;
import gob.sct.medprev.dao.TDEXPResultadoExt1;

public class RDiagnosticoVigencia {

	public RDiagnosticoVigencia(){
	}
	
	// Activar como obligatorio la captura de Glucosa, Colesterol y Hemoglobina Glucosilada si 
	// Se da el caso que en ANTECEDENTES PERSONALES PATOLOGICOS se respondio SI a la pregunta 
	// Diagnostico Diabetes Mellitus
	@SuppressWarnings("finally")
	public java.sql.Date FechaDeReglaDeVigenciaPorDiagnostico(String iCveExpediente, String iNumExamen, int IUnidadMedica, int IModulo) throws DAOException {		
		java.sql.Date dtFinV = null;
		TDEXPDictamenServ dDicServ = new TDEXPDictamenServ(); 
		int iTmpVigencia = 0;
		int ianosVigencia = 0; 
		int iMesesVigencia = 0;
		String str_date = "";
		Date date;
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			TDEXPDiagnostico dDiag = new TDEXPDiagnostico();
			iTmpVigencia = dDiag.ReglaVigenciaPorDiagnostico(iCveExpediente, iNumExamen, IUnidadMedica, IModulo);
			System.out.println("iTmpVigencia = "+iTmpVigencia);
			if(iTmpVigencia == -1){
				iTmpVigencia = 100;
			}
					ianosVigencia = iTmpVigencia / 12;
					int ano = iTmpVigencia / 12;
					iMesesVigencia = iTmpVigencia - (ano * 12);
					if (ianosVigencia > 0 && iMesesVigencia > 0) {
						System.out.println("OP 1");
						System.out.println(ianosVigencia+" > 0 && "+iMesesVigencia+" > 0");
							str_date = dDicServ.SenFindBy("select current date +" + ianosVigencia + "  YEARS + "
									+ iMesesVigencia + " MONTHS from sysibm.sysdummy1");
							// Convierte String a Date
							date = (Date) formatter.parse(str_date);
							java.util.Date y2 = new java.util.Date();
							y2 = date;
							java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
							dtFinV = CalVigencia;
					} else {
						if (ianosVigencia > 0) {
							System.out.println("OP 2");
								str_date = dDicServ.SenFindBy(
										"select current date +" + ianosVigencia + "  YEARS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
						}
						if (iMesesVigencia > 0) {
							System.out.println("OP 3");
								str_date = dDicServ.SenFindBy(
										"select current date +" + iMesesVigencia + " MONTHS from sysibm.sysdummy1");
								// Convierte String a Date
								date = (Date) formatter.parse(str_date);
								java.util.Date y2 = new java.util.Date();
								y2 = date;
								java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
								dtFinV = CalVigencia;
						}
					}
					
					if(iTmpVigencia==0){
						System.out.println("OP 4");
						str_date = dDicServ.SenFindBy(
								"select current date from sysibm.sysdummy1");
						// Convierte String a Date
						date = (Date) formatter.parse(str_date);
						java.util.Date y2 = new java.util.Date();
						y2 = date;
						java.sql.Date CalVigencia = new java.sql.Date(y2.getTime());
						dtFinV = CalVigencia;
					}

		 System.out.println("dtFinV = "+dtFinV);
		} catch (Exception ex) {
			System.out.println("DeterminaVigenciaAnticoagulante "+ ex);
			throw new DAOException("FindByAll");
		} finally {
			/// Validar reglas con respecto a los valores en EXPRESULTADO
			return dtFinV;
		}
	}
	
}
