package com.micper.util.logging;

// Import log4j classes.
/*import org.apache.log4j.Logger;
 import org.apache.log4j.BasicConfigurator;
 */
/*
 import org.apache.log4j.Level;
 import org.apache.log4j.*;
 */
import com.micper.ingsw.*;

public class TLogger {

	/*
	 * public static TLoggerFactory factory = new TLoggerFactory();
	 */
	public static String DEBUG = "DEBUG";
	public static String WARN = "WARN";
	public static String ERROR = "ERROR";
	public static String FATAL = "FATAL";
	public static String INFO = "INFO";
	/*
	 * public static Logger LOG_DEBUG = Logger.getLogger(DEBUG, factory); public
	 * static Logger LOG_WARN = Logger.getLogger(WARN, factory); public static
	 * Logger LOG_ERROR = Logger.getLogger(ERROR, factory); public static Logger
	 * LOG_FATAL = Logger.getLogger(FATAL, factory); public static Logger
	 * LOG_INFO = Logger.getLogger(INFO, factory);
	 */
	public static boolean initialized = false;

	private static String cRuta = "", cModulo = "";
	private static boolean lSystem = false;

	private static boolean lInfo = false;
	private static boolean lDebug = false;
	private static boolean lWarn = false;
	private static boolean lWarnST = true;
	private static boolean lError = false;
	private static boolean lErrorST = true;
	private static boolean lFatal = false;
	private static boolean lFatalST = true;

	public static void setSistema(String cMod) {
		boolean lException = false;

		if (cMod.compareTo(cModulo) != 0) {

			cModulo = cMod;

			TParametro vParametros = new TParametro(cModulo);

			/*
			 * cRuta = vParametros.getPropEspecifica("LoggerFiles");
			 * 
			 * if (cRuta.compareToIgnoreCase("") == 0) { lSystem = true;
			 * System.out.println(cModulo +
			 * " El Logger no ha podido ser configurado en su totalidad!");
			 */
			System.out
					.println(cModulo
							+ " Los mensajes del sistema no serán registrados en los Archivos de Bitácora!");
			/*
			 * lException = true;
			 * 
			 * }
			 */
			cRuta = cRuta + cModulo + "-";

			System.out.println(cModulo + " El Logger ha sido Configurado...");
			/*
			 * System.out.println(cModulo + " - Los Archivos de Bitácora: " +
			 * cRuta);
			 */
			lInfo = Boolean.valueOf(vParametros.getPropEspecifica("lInfo"))
					.booleanValue();
			System.out.println(cModulo + " - lInfo: " + lInfo);

			lDebug = Boolean.valueOf(vParametros.getPropEspecifica("lDebug"))
					.booleanValue();
			System.out.println(cModulo + " - lDebug: " + lDebug);

			lError = Boolean.valueOf(vParametros.getPropEspecifica("lError"))
					.booleanValue();
			System.out.println(cModulo + " - lError: " + lError);

			lFatal = Boolean.valueOf(vParametros.getPropEspecifica("lFatal"))
					.booleanValue();
			System.out.println(cModulo + " - lFatal: " + lFatal);

			lWarn = Boolean.valueOf(vParametros.getPropEspecifica("lWarn"))
					.booleanValue();
			System.out.println(cModulo + " - lWarn: " + lWarn);

			lWarnST = Boolean.valueOf(vParametros.getPropEspecifica("lWarnST"))
					.booleanValue();
			System.out.println(cModulo + " - lWarnST: " + lWarnST);

			lErrorST = Boolean.valueOf(
					vParametros.getPropEspecifica("lErrorST")).booleanValue();
			System.out.println(cModulo + " - lErrorST: " + lErrorST);

			lFatalST = Boolean.valueOf(
					vParametros.getPropEspecifica("lFatalST")).booleanValue();
			System.out.println(cModulo + " - lFatalST: " + lFatalST);
		}
	}

	static void setup() {

		try {
			/*
			 * LOG_WARN.setLevel(Level.WARN); LOG_ERROR.setLevel(Level.ERROR);
			 * LOG_FATAL.setLevel(Level.FATAL); LOG_INFO.setLevel(Level.INFO);
			 * LOG_DEBUG.addAppender(new FileAppender(new DebugLayout(), cRuta +
			 * "logDebug.txt")); LOG_DEBUG.addAppender(new FileAppender(new
			 * DebugLayout(), cRuta + "logDebug.txt"));
			 * LOG_DEBUG.addAppender(new ConsoleAppender(new DebugLayout()));
			 * LOG_WARN.addAppender(new FileAppender(new WarnLayout(), cRuta +
			 * "logWarn.txt")); LOG_WARN.addAppender(new ConsoleAppender(new
			 * WarnLayout())); LOG_ERROR.addAppender(new FileAppender(new
			 * ErrorLayout(), cRuta + "logError.txt"));
			 * LOG_ERROR.addAppender(new ConsoleAppender(new ErrorLayout()));
			 * LOG_FATAL.addAppender(new FileAppender(new FatalLayout(), cRuta +
			 * "logFatal.txt")); LOG_FATAL.addAppender(new ConsoleAppender(new
			 * FatalLayout())); LOG_INFO.addAppender(new FileAppender(new
			 * InfoLayout(), cRuta + "logInfo.txt")); LOG_INFO.addAppender(new
			 * ConsoleAppender(new InfoLayout()));
			 */
			initialized = true;
		} catch (Exception e) {
			System.out
					.println(cModulo
							+ "- TLogger.Setup ha fallado, existe un error al generar los Archivos de Bitácora!");
			e.printStackTrace();
			System.out
					.println(cModulo
							+ "- TLogger.Setup Los mensajes solo serán mostrados en la consola!");
		}

	} // setup

	public static void log(Object obj, String level, String message, Throwable t) {
		if (!initialized) {
			setup();
		}
		if (lSystem) {
			System.out.println("TLogger.log: " + cModulo + " - " + obj + ": "
					+ message);
		} else {

			if (lDebug) {
				if (level.equalsIgnoreCase(DEBUG)) {
					// LOG_DEBUG.debug(" -DEBUG- " + cModulo + "." + obj + ": "
					// + message, t);
					System.out.println(" -DEBUG- " + cModulo + "." + obj + ": "
							+ message);
					if (t != null)
						t.printStackTrace();
				}
			}

			if (lInfo) {
				if (level.equalsIgnoreCase(INFO)) {
					// LOG_INFO.info(" -INFO- " + cModulo + "." + obj + ": " +
					// message);
					System.out.println(" -INFO- " + cModulo + "." + obj + ": "
							+ message);
				}
			}

			if (lError) {
				if (level.equalsIgnoreCase(ERROR)) {
					// LOG_ERROR.error(" -ERROR- " + cModulo + "." + obj + ": "
					// + message);
					System.out.println(" -ERROR- " + cModulo + "." + obj + ": "
							+ message);
					if (lErrorST) {
						t.printStackTrace();
					}
				}
			}

			if (lWarn) {
				if (level.equalsIgnoreCase(WARN)) {
					// LOG_WARN.warn(" -WARN- " + cModulo + "." + obj + ": " +
					// message);
					System.out.println(" -WARN- " + cModulo + "." + obj + ": "
							+ message);
					if (lWarnST) {
						t.printStackTrace();
					}
				}
			}

			if (lFatal) {
				if (level.equalsIgnoreCase(FATAL)) {
					// LOG_FATAL.fatal(" -FATAL- " + cModulo + "." + obj + ": "
					// + message);
					System.out.println(" -FATAL- " + cModulo + "." + obj + ": "
							+ message);
					if (lFatalST) {
						t.printStackTrace();
					}
				}
			}

		}

	}
}