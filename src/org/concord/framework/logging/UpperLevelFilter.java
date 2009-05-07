package org.concord.framework.logging;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

public class UpperLevelFilter implements Filter {
	Level upperLevel;
	
	
	public UpperLevelFilter() {
        LogManager manager = LogManager.getLogManager();
    	String cname = getClass().getName();
    	String property = manager.getProperty(cname + ".level");
    	if(property != null){
    		upperLevel = Level.parse(property.trim());
    	}
	}
	
	public boolean isLoggable(LogRecord record) {
		if(upperLevel == null){
			return true;
		}
		
		int levelValue = upperLevel.intValue();
		if(record.getLevel().intValue() > levelValue){
			return false;
		}
		
		return true;
	}

}
