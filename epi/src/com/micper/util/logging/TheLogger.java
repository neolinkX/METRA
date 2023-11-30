package com.micper.util.logging;

import org.apache.log4j.*;

/*import org.apache.log4j.spi.LoggerFactory;
 import org.apache.log4j.xml.DOMConfigurator;
 import org.apache.log4j.PropertyConfigurator;
 import org.apache.log4j.helpers.LogLog;
 */
//public class TheLogger extends Logger {
public class TheLogger extends Logger {
	// It's enough to instantiate a factory once and for all.
	private static TLoggerFactory factory = new TLoggerFactory();

	public TheLogger(String name) {
		super(name);
	}

	public static Category getInstance(String name) {
		return Logger.getLogger(name, factory);
	}

	public static Logger getLogger(String name) {
		return Logger.getLogger(name, factory);
	}

}// class

