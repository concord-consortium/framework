package org.concord.framework.logging;

import java.io.OutputStream;
import java.io.Writer;

public interface Loggable
{
	public final static int LOG_NONE			= 0x0;
	public final static int LOG_ACTION			= 0x1;
	public final static int LOG_MOUSE			= 0x2;
	public final static int LOG_KEY				= 0x4;
	public final static int LOG_NODE_ENTER		= 0x8;
	public final static int LOG_NODE_EXIT		= 0x10;
	public final static int LOG_EVENT			= 0x20;
	
	public final static int LOG_FROM_FORCE_LOG	= -1;
	public final static int LOG_FROM_UNDEFINE	= 0;
	public final static int LOG_FROM_ACTION		= 1;
	public final static int LOG_FROM_MOUSE		= 2;
	public final static int LOG_FROM_KEY		= 3;
	public final static int LOG_FROM_NODE_ENTER	= 4;
	public final static int LOG_FROM_NODE_EXIT	= 5;
	public final static int LOG_FROM_EVENT		= 6;
	
	public void log(Writer writer,LogHintMessage hint);
	public void log(OutputStream out,LogHintMessage hint);
	public Loggable getLoggable(Object selector);
	
	public void setLogMode(int logmode);
	public int  getLogMode();
	
	public LogManager getLogManager();
	public void setLogManager(LogManager logManager);
	
}

