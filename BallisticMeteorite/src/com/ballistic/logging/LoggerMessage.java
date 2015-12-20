package com.ballistic.logging;

import org.apache.log4j.Logger;

public class LoggerMessage {
	
	public static void printLog(String className, String message){		
		Logger log = Logger.getLogger(className);
		log.info(message);
	}
	
}
