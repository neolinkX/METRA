package gob.sct.medprev;

import com.micper.util.caching.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;

/**
 * <p>
 * Title: StartUp
 * </p>
 * <p>
 * Description: Clase que configura los parámetros de inicio del AppCacheManager
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Tecnología Inred S.A. de C.V.
 * </p>
 * 
 * @author Lic. Jaime Enrique Suárez Romero
 * @version 1.0
 */

public class ACMCFG {
	private static boolean lConfig = false;

	public static void setACMCFG(String cModulo) {
		if (lConfig == false) {
			// (Vector) AppCacheManager.getColl("GRLProceso","");
			// (Vector) AppCacheManager.getColl("GRLPuesto","1|");

			AppCacheManager.SetAppCacheManager(cModulo, "|");
			AppCacheManager.setDAOByColl("GRLProceso", new DAOGRLProceso());
			AppCacheManager.setDAOByColl("GRLPuesto", new DAOGRLPuesto());
			AppCacheManager.setDAOByColl("TOXSustancia", new DAOTOXSustancia());
			AppCacheManager.setDAOByColl("GRLUniMed", new DAOGRLUniMed());
			AppCacheManager.setDAOByColl("EQMEquipo", new DAOEQMEquipo());
			AppCacheManager.setDAOByColl("AcCorrectiva",
					new DAOTOXAcCorrectiva());
			AppCacheManager.setDAOByColl("GRLMotivo", new DAOGRLMotivo());
			AppCacheManager.setDAOByColl("MEDServicio", new DAOMEDServicio());
			AppCacheManager.setDAOByColl("MEDRama", new DAOMEDRama());
			AppCacheManager.setDAOByColl("GRLModulo", new DAOGRLModulo());
			lConfig = true;
		}
	}
}