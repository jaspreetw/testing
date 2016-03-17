package com.rjil.snw.webAutomation.utility;

import org.apache.log4j.Logger;

public class Log {
	// Initialize Log4j logs
	 
	 private static Logger Log = Logger.getLogger(Log.class.getName());
	 
		// Need to create these methods, so that they can be called  
	 
	 public static String info(String message) {
	 
			Log.info(message);
			System.out.println(message);
			return message;
	 
			}
	 
	 public static String warn(String message) {
	 
	    Log.warn(message);
	    System.out.println(message);
	    return message ;
		}
	 
	 public static String error(String message) {
	 
	    Log.error(message);
	    System.out.println(message);
	    return message ;
	 
		}
	 
	 public static String fatal(String message) {
	 
	    Log.fatal(message);
	    System.out.println(message);
	    return message ;
	 
		}
	 
	 public static String debug(String message) {
	 
	    Log.debug(message);
	    System.out.println(message);
	    return message ;
	 
		}

}
