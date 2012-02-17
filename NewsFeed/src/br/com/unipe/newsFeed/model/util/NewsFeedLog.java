package br.com.unipe.newsFeed.model.util;

import org.apache.log4j.Logger;

/**
 * @author moacir
 *
 */
public final class NewsFeedLog {
	private static Logger logger = null;

	private NewsFeedLog() {
		
	}

	static {
		try {
			logger = Logger.getLogger("br.com.unipe.newsFeed");
		} catch (Exception e) {
			System.out.println("Não foi possível criar o mecanismo de log.");
		}
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	public static void info(Object message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
	
	public static void debug(Exception e) {
		logger.debug(e.getCause().getMessage());
	}

	public static void info(Exception e) {
		logger.info(e.getCause().getMessage());
	}
	
	public static void warn(Exception e) {
		logger.warn(e.getCause().getMessage());
	}
	
	public static void error(Exception e) {
		logger.error(e.getCause().getMessage());
	}
	
	
}
