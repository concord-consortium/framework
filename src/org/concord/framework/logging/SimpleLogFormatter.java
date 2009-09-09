package org.concord.framework.logging;

import java.util.StringTokenizer;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class SimpleLogFormatter extends Formatter {
	private static final long startMillis = System.currentTimeMillis();

	private static final String indentStr = "              ";
	private static final boolean showPackageNames = false;
	
	@Override
	public synchronized String format(LogRecord record) {
		StringBuffer sb = new StringBuffer();
		sb.append(ConsoleLogTimeConverter.getTime(record.getMillis() - startMillis));
		sb.append(" ").append(getLevelShortString(record.getLevel()));
		sb.append(" ").append(getSourceClassString(record));
		sb.append(".").append(record.getSourceMethodName());
		sb.append(": ");
		handleMultiLine(record.getMessage(), sb);
		if (record.getThrown() != null) {
			Throwable t = record.getThrown();			
			sb.append(indentStr).append(t.toString()).append('\n');
			for (StackTraceElement e : t.getStackTrace()) {
				sb.append(indentStr).append("   ").append(e.toString()).append('\n');
			}
		}
		return sb.toString();
	}

	protected void handleMultiLine(String message, StringBuffer sb){
		if(message == null || message.length() == 0){
			sb.append('\n');
			return;
		}
		
		StringTokenizer st = new StringTokenizer(message, "\n");
		sb.append(st.nextToken()).append('\n');
		while(st.hasMoreTokens()){
			sb.append(indentStr).append("-").append(st.nextToken()).append('\n');
		}
		return;
	}
	
	protected String getLevelShortString(Level level){
		if(level == Level.FINER){
			return "FNER";
		} else if(level == Level.FINEST){
			return "FNST";
		} else {
			return level.toString().substring(0, 4);
		}
	}
	
	protected String getSourceClassString(LogRecord record)
	{
		if (showPackageNames) {
			return record.getSourceClassName();
		}
		
		String sourceClassName = record.getSourceClassName();
		int dotIndex = sourceClassName.lastIndexOf('.');
		if(dotIndex != -1){
			sourceClassName = sourceClassName.substring(dotIndex+1);
		}
		return sourceClassName;
	}
}
