package com.micper.util.logging;

import org.apache.log4j.spi.*;
import org.apache.log4j.*;
import java.util.*;
import java.text.*;

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

public class FatalLayout extends Layout {
	public FatalLayout() {
	}

	public void activateOptions() {
		/**
		 * @todo Implement this org.apache.log4j.spi.OptionHandler abstract
		 *       method
		 */
	}

	public String format(LoggingEvent parm1) {
		/** @todo Implement this org.apache.log4j.Layout abstract method */
		// DateFormat dateFormat= new DateFormat(
		String fecha = SimpleDateFormat.getDateTimeInstance().format(
				Calendar.getInstance().getTime());
		return "\n [" + fecha + "] " + "[" + parm1.getRenderedMessage() + "]";
		// throw new
		// java.lang.UnsupportedOperationException("Method format() not yet implemented.");
	}

	public boolean ignoresThrowable() {
		return false;
		/** @todo Implement this org.apache.log4j.Layout abstract method */
		// throw new
		// java.lang.UnsupportedOperationException("Method ignoresThrowable() not yet implemented.");
	}

}