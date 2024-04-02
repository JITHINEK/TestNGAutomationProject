package com.testng_automation.utils;

import org.apache.log4j.Logger;
import com.testng_automation.listener.LogListener;

public class LoggerUtility {

	private static Logger logger = Logger.getLogger(LogListener.class);
	
	public static void log(String message) {
		logger.info(message);
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
