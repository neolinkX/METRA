package com.micper.util.logging;

import java.util.*;

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
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class Test {
	public Test() {
	}

	public static void main(String[] args) {
		// TLogger tlogger = new TLogger();
		Test t = new Test();
		t.call();

	}

	public void call() {
		TLogger.log(Calendar.getInstance(), TLogger.DEBUG, "depurando",
				new Exception("exception lanzada"));
		TLogger.log(this, TLogger.INFO, "hello", null);
		TLogger.log(null, TLogger.WARN, "cuidado!!!", null);
		TLogger.log(null, TLogger.FATAL, "crashh!!!", null);
		TLogger.log(null, TLogger.ERROR, "uppsss!!!", null);

	}// call
}