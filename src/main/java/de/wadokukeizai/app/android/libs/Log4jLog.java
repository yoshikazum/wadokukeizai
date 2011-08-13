package de.wadokukeizai.app.android.libs;

import org.apache.commons.logging.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jLog implements Log {

	private Logger log;
	private String tag;
	
	public Log4jLog(String tag){
		this.tag = tag;
		log = Logger.getLogger(tag);
		log.setLevel(Level.ALL);
		PropertyConfigurator.configure("log4j.properties");
	}
	
	public void debug(Object arg0) {
		log.debug(arg0);
	}

	public void debug(Object arg0, Throwable arg1) {
		log.debug(arg0, arg1);
	}

	public void error(Object arg0) {
		log.error(arg0);
	}

	public void error(Object arg0, Throwable arg1) {
		log.error(arg0, arg1);
	}

	public void fatal(Object arg0) {
		log.fatal(arg0);
	}

	public void fatal(Object arg0, Throwable arg1) {
		log.fatal(arg0, arg1);
	}

	public void info(Object arg0) {
		log.info(arg0);
	}

	public void info(Object arg0, Throwable arg1) {
		log.info(arg0, arg1);
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public boolean isFatalEnabled() {
		return true;
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isTraceEnabled() {
		return true;
	}

	public boolean isWarnEnabled() {
		return true;
	}

	public void trace(Object arg0) {
		log.trace(arg0);
	}

	public void trace(Object arg0, Throwable arg1) {
		log.trace(arg0, arg1);
	}

	public void warn(Object arg0) {
		log.warn(arg0);
	}

	public void warn(Object arg0, Throwable arg1) {
		log.warn(arg0, arg1);
	}

}
