package com.micper.util.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class TLoggerFactory implements LoggerFactory {

	public TLoggerFactory() {
	}

	public Logger makeNewLoggerInstance(String name) {
		return new TheLogger(name);
	}
}
