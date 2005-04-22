
/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package org.concord.framework.logging;

import java.io.OutputStream;
import java.io.Writer;

public class LoggableAdapter implements Loggable
{
	LogManager logManager;
    protected final static String contentCDATAPrefix = "<![CDATA[";
    protected final static String contentCDATASuffix = "]]>";
    protected final static String contentCDATASuffixSubstitute = "]}>";
    protected final static int lengthCDATASuffix = contentCDATASuffix.length();
	private int logMode = LOG_NONE;
    private String logName;
	
	public void log(Writer writer,LogHintMessage hint){}
	
	public void log(OutputStream out,LogHintMessage hint){}
	
	public Loggable getLoggable(Object selector){return this;}
	
	public void setLogMode(int logMode){
		this.logMode = logMode;
	}
	
	public int  getLogMode(){
		return logMode;
	}
	
	public int getDefaultLogMode(){
	    return LOG_NONE;
	}
	
	public void initLogState(){}
	
	public void setLogName(String str){
	    logName = str;
	}

	public String getLogName(){
	    return logName;
	}
	
	public Interaction getFirstInteraction(){return null;}

	public Interaction getActionInteraction(){return null;}
	
	public Interaction getLastInteraction(){return null;}
		
	public static boolean doLog(Loggable loggable, int logFrom){
	    if(loggable == null) return false;
	    if(logFrom == Loggable.LOG_FROM_ALWAYS) return true;
	    if(logFrom < LOG_FROM_UNDEFINE || logFrom > LOG_FROM_LAST) return false; 
	    int logMode = loggable.getLogMode();
	    boolean retValue = false;
	    try{
	        int mask = logMaskChoices[logFrom];
	        retValue = ((logMode & mask) != 0);
	    }catch(Throwable t){
	        retValue = false;
	    }
	    return retValue;
	    
	}

    public static String wrapStringAsCDATA(String str){
        if(str == null) return null;
        StringBuffer sb = new StringBuffer();
        sb.append(contentCDATAPrefix);
        sb.append(checkForCDATASuffix(str));
        sb.append(contentCDATASuffix);
        return sb.toString();
    }

    public static String checkForCDATASuffix(String str){
        if(str == null) return str;
        int ind = str.indexOf(contentCDATASuffix);
        if(ind < 0) return str;
        
        StringBuffer sb = new StringBuffer();
        int offsetStr = 0;
        while(ind >= 0){
            if(ind > 0) sb.append(str.substring(offsetStr,ind));
            sb.append(contentCDATASuffixSubstitute);
            offsetStr = ind + lengthCDATASuffix;
            ind = str.indexOf(contentCDATASuffix,offsetStr);
            if(ind < 0) sb.append(str.substring(offsetStr));
        }
        
        return sb.toString();
    }
	
	public LogManager getLogManager(){return logManager;}
	public void setLogManager(LogManager logManager){this.logManager = logManager;}
	
}

