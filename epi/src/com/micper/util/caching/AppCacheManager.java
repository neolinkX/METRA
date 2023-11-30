package com.micper.util.caching;

import java.util.*;

import com.micper.ingsw.*;
import com.micper.util.logging.*;
import com.micper.sql.*;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Tecnolog�a Inred S.A. de C.V.
 * </p>
 * 
 * @author Lic. Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class AppCacheManager {
	private static HashMap hmDAOColl = new HashMap();
	private static HashMap hmColls = new HashMap();
	private static String cDelimiter = "|";
	private static String cExpGral = "";
	private static HashMap hmExpEsp = new HashMap();
	private static String cModulo = "No Configurado";
	private static boolean lDefog = false;
	private static long iStartDefog = 0;
	private static long iLoopDefog = 0;
	private static boolean lCSOnDefog = false;

	/**
	 * Metodo encargado de configurar las caracter�sticas espec�ficas del
	 * Modulo, delimitador, Logger y Caducidades
	 * 
	 * @param Modulo
	 *            del sistema.
	 * @param Delimitador
	 *            .
	 */

	public static void SetAppCacheManager(String cMod, String Delimitador) {
		String cExpEspCol = "", cExpEspCad = "";
		TParametro vParametros = new TParametro(cMod);
		StringTokenizer stExpEspCol, stExpEspCad;
		cModulo = cMod;
		// System.out.println("");
		// System.out.println("");
		info(" - Iniciando...");
		// Expiraciones y Loop - globales properties
		try {
			cExpGral = vParametros.getPropEspecifica("AC-ExpGral");
			cExpEspCol = vParametros.getPropEspecifica("AC-ExpEspCol");
			cExpEspCad = vParametros.getPropEspecifica("AC-ExpEspCad");
			lCSOnDefog = Boolean.valueOf(
					vParametros.getPropEspecifica("AC-CollSetsOnDefog"))
					.booleanValue();

			Float dCadGral = new Float(
					vParametros.getPropEspecifica("AC-LoopGral"));
			dCadGral = new Float(dCadGral.floatValue() * (1000 * 60 * 60));
			iLoopDefog = dCadGral.longValue();
		} catch (Exception e) {
			cExpGral = "24";
			iLoopDefog = 24 * (1000 * 60 * 60);
			info("No se encuentra a las Expiraciones AC-ExpGral/AC-ExpEspCol/AC-ExpEspCad - AC-LoopGral - lCSOnDefog.");
			info("La Expiraci�n general queda configurada a 24Hrs.");
			info("El m�todo Defog queda configurado a 24Hrs.");
		}
		cDelimiter = Delimitador;
		// Expiraciones espec�ficas
		try {
			if (cExpEspCol.compareTo("") != 0) {
				stExpEspCol = new StringTokenizer(cExpEspCol, cDelimiter);
				stExpEspCad = new StringTokenizer(cExpEspCad, cDelimiter);
				while (stExpEspCol.hasMoreTokens()) {
					hmExpEsp.put(stExpEspCol.nextToken(),
							stExpEspCad.nextToken());
				}
			}
		} catch (Exception e) {
			error("Error al cargar las caducidades espec�ficas.", e);
		}
		info("El AppCacheManager ha sido configurado...");
		info("  - cExpGral: " + cExpGral + " Hrs.");
		info("  - cExpEspCol: " + cExpEspCol);
		info("  - cExpEspCad: " + cExpEspCad);
		info("  - iLoopDefog: "
				+ (new Float(iLoopDefog).floatValue() / 3600000) + " Hrs.");
	}

	/**
	 * Metodo encargado de configurar al DAO que se encargar� de la
	 * recuperaci�n de registros por cada Colecci�n
	 * 
	 * @param Nombre
	 *            de la Colecci�n.
	 * @param Objeto
	 *            DAO.
	 * @return True, se asign� el objeto; false, no se efectu� la
	 *         asignaci�n.
	 */
	public static boolean setDAOByColl(String cCollName, Object DAOColl) {
		boolean lSuccess = false;
		StringTokenizer stParametros;
		int i = 0;
		try {
			hmDAOColl.put(cCollName, DAOColl);
			lSuccess = true;
			info("El DAO para: \"" + cCollName
					+ "\" ha sido configurado exitosamente.");
		} catch (Exception e) {
			fatal("setDAOByColl:" + cCollName, e);
		} finally {
			return lSuccess;
		}
	}

	/**
	 * Metodo encargado de obtener una Colecci�n ya sea del cach� o de la base
	 * de datos
	 * 
	 * @param Nombre
	 *            de la Colecci�n.
	 * @param Llave
	 *            sobre el que se har� la b�squeda.
	 * @return Objeto de la Colecci�n encontrada.
	 */
	public static Object getColl(String cCollName, String cKey) {
		Object oColl = null;
		AppCache acColl;
		boolean lGetDAOColl = false;
		try {
			// retorno del Cache
			if (hmColls.containsKey(cCollName + cKey)) {
				acColl = (AppCache) hmColls.get(cCollName + cKey);
				if (!acColl.expires()) {
					oColl = acColl.getColl();
				} else {
					lGetDAOColl = true;
				}
			} else {
				lGetDAOColl = true;

			}
			if (lGetDAOColl) {
				oColl = getDAOColl(cCollName, cKey);
			}
		} catch (Exception e) {
			fatal("setDAOByColl:" + cCollName, e);
		} finally {
			try {
				if (!lDefog) {
					defog();
				}
			} catch (Exception e) {
			}
			return oColl;
		}
	}

	/**
	 * Metodo encargado de obtener una Colecci�n de la base de datos
	 * 
	 * @param Nombre
	 *            de la Colecci�n.
	 * @param Llave
	 *            sobre el que se har� la b�squeda.
	 * @return Objeto de la Colecci�n encontrada.
	 */
	private synchronized static Object getDAOColl(String cCollName, String cKey) {
		int i = 0;
		boolean lDAOFind = false;
		String cCaducidad = cExpGral;
		Object oReturn = null;
		DAOBase dDAOColl;
		AppCache acColl;
		try {
			if (hmDAOColl.containsKey(cCollName)) {
				dDAOColl = (DAOBase) hmDAOColl.get(cCollName);
				lDAOFind = dDAOColl.findCollection(cCollName, cKey);
				oReturn = dDAOColl.getCollection();

				if (lDAOFind) {
					if (hmExpEsp.containsKey(cCollName)) {
						cCaducidad = (String) hmExpEsp.get(cCollName);
					}
					acColl = new AppCache(cCollName, cKey, oReturn, cCaducidad);
					while (lDefog == true) {
					}
					hmColls.put(cCollName + cKey, acColl);
				}
			} else {
				info("La Colecci�n: " + cCollName
						+ " no cuenta con un DAO configurado.");
			}
		} catch (Exception e) {
			fatal("getDAOColl: " + cCollName, e);
		} finally {
			return oReturn;
		}
	}

	/**
	 * Metodo encargado de depurar a las colecciones que han caducado
	 */

	private static void defog() {
		if (iStartDefog <= new java.util.Date().getTime()) {
			realdefog();
		}
	}

	private synchronized static void realdefog() {
		lDefog = true;
		info("Iniciando Defog...");
		iStartDefog = new java.util.Date().getTime() + iLoopDefog;
		Set setColl;
		Iterator iColl;
		AppCache acColl;
		Vector vDeletedKeys = new Vector();
		String cKey = "";
		try {
			if (!hmColls.isEmpty()) {
				setColl = hmColls.keySet();
				iColl = setColl.iterator();
				while (iColl.hasNext()) {
					cKey = "" + iColl.next();
					acColl = (AppCache) hmColls.get(cKey);
					if (acColl.expires()) {
						vDeletedKeys.add(cKey);
					}
				}
				if (!vDeletedKeys.isEmpty()) {
					for (int i = 0; i < vDeletedKeys.size(); i++) {
						cKey = (String) vDeletedKeys.get(i);
						hmColls.remove(cKey);
						info("Eliminando: -" + cKey + "-");
					}
				}

				if (lCSOnDefog)
					getCollSets();

			} else {
				info("La Colecci�n est� vac�a.");
			}
		} catch (Exception e) {
			warn("defog: No pudo ser depurada la Colecci�n: " + cKey, e);
		} finally {
			info("Finalizando Defkog...");
			lDefog = false;
		}
	}

	/**
	 * Metodo encargado de eliminar una llave de la Colecci�n principal
	 * 
	 * @param Nombre
	 *            de la Colecci�n.
	 * @param Llave
	 *            sobre el que se har� la b�squeda.
	 * @return True, se elimin� la Colecci�n; false, no se elimin� la
	 *         coleci�n.
	 */
	public static boolean delCollKey(String cCollName, String cKey) {
		boolean lSuccess = false;
		try {
			if (hmColls.containsKey(cCollName + cKey)) {
				hmColls.remove(cCollName + cKey);
			}
			lSuccess = true;
		} catch (Exception e) {
			info("delCollKey: No fue eliminada la llave: " + cKey);
		}
		return lSuccess;
	}

	/**
	 * Metodo encargado de eliminar una Colecci�n completa
	 * 
	 * @param Nombre
	 *            de la Colecci�n.
	 * @return True, se elimin� la Colecci�n; false, no se elimin� la
	 *         Colecci�n.
	 */
	public static boolean delColl(String cCollName) {
		boolean lSuccess = false;
		Set setColl;
		Iterator iColl;
		AppCache acDelColl;
		Vector vDeletedKeys = new Vector();
		String cKey = "";
		try {
			setColl = hmColls.keySet();
			iColl = setColl.iterator();
			while (iColl.hasNext()) {
				cKey = "" + iColl.next();
				acDelColl = (AppCache) hmColls.get(cKey);
				if (acDelColl.getCollName().compareTo(cCollName) == 0) {
					vDeletedKeys.add(cKey);
				}
			}
			if (!vDeletedKeys.isEmpty()) {
				info("delColl.Eliminando Colecci�n: " + cCollName);
				for (int i = 0; i < vDeletedKeys.size(); i++) {
					cKey = (String) vDeletedKeys.get(i);
					hmColls.remove(cKey);
					info(" - Eliminando: " + cKey);
				}
			} else {
				info("delColl.No Existe la Colecci�n: " + cCollName);
			}
			lSuccess = true;
			getCollSets();
		} catch (Exception e) {
			info("delColl: No fue eliminada la Colecci�n: " + cCollName);
		}
		return lSuccess;
	}

	/**
	 * Metodo que despliega en consola las colecciones existentes en memoria
	 */
	public static void getCollSets() {
		Set setColl;
		Iterator iColl;
		String cMsg = "";
		int i = 0;
		try {
			if (!hmColls.isEmpty()) {
				info("getCollSets: Iniciando ...");
				setColl = hmColls.keySet();
				iColl = setColl.iterator();
				while (iColl.hasNext()) {
					info(" - Colecci�n " + ++i + ": " + iColl.next());
				}
				info("getCollSets: Finalizando ...");
			} else {
				info("getCollSets: La Colecci�n est� vac�a.");
			}
		} catch (Exception e) {
			warn("getCollSets: No pudo ser consultada la Coleccion", e);
		}
	}

	/**
	 * Metodo encargado de enviar mensajes de advertencia que el programador
	 * decida enviar, dentro del metodo catch.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	private static void warn(String Aviso, Exception ex) {
		TLogger.log("AppCacheMgr", TLogger.WARN, Aviso, ex);
	}

	/**
	 * Metodo encargado de enviar mensajes informativos que el programador
	 * decida enviar. Se pueden poner en cualquier parte del c�digo.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 */
	private static void info(String Aviso) {
		TLogger.log("AppCacheMgr", TLogger.INFO, Aviso, null);
	}

	/**
	 * Metodo encargado de enviar mensajes de debugueo que el programador decida
	 * enviar. Se pueden poner en cualquier parte del c�digo y tendr�n la
	 * opcion de poderse apagar mediante una bandera.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 */
	private static void debug(String Aviso) {
		TLogger.log("AppCacheMgr", TLogger.DEBUG, Aviso, null);
	}

	/**
	 * Metodo encargado de enviar mensajes de error que el programador decida
	 * enviar, dentro del metodo catch.
	 * 
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	private static void error(String Aviso, Exception ex) {
		TLogger.log("AppCacheMgr", TLogger.ERROR, Aviso, ex);
	}

	/**
	 * Metodo encargado de enviar mensajes de errores fatales que el programador
	 * decida enviar, dentro del metodo catch.
	 * 
	 * @param obj
	 *            se refiere al modulo en donde ocurre el error.
	 * @param Aviso
	 *            se refiere al mensaje que el programador decida poner.
	 * @param ex
	 *            se refiere a la Excepcion que envia el metodo catch.
	 */
	private static void fatal(String Aviso, Exception ex) {
		TLogger.log("AppCacheMgr", TLogger.FATAL, Aviso, ex);
	}
}
