package com.project.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	private static Logger logger = LogManager.getLogger(Log.class.getName());
	
	public static void info(String message) {
		
		logger.info(message);
	}
	
	public static void debug(String message) {
		
		logger.debug(message);
	}
	
	public static void warn(String message) {
		
		logger.warn(message);
	}
	
	public static void error(String message) {
		
		logger.error(message);
	}
}
