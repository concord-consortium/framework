package org.concord.framework.logging;

import java.util.StringTokenizer;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class SimpleLogFormatter extends Formatter {
	private static final long startMillis = System.currentTimeMillis();

	private static final String indentStr = "              ";
	
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
		return record.getSourceClassName();
	}
	
	private static class ConsoleLogTimeConverter {

		public static String getTime(long elapsedMs) {
			long elapsedSec = elapsedMs/1000;
			double s = elapsedMs/1000.0d % 60;
			long elapsedMin = elapsedSec/60;
			int m = (int) (elapsedMin % 60);
			long elapsedHour = m/60;
			int h = (int) (elapsedHour % 60);
			String pad = " ";
			String out = "";
			if (h > 10) {
				out += h + ":";
				pad = "0";
			} else if (h > 0) {
				out += pad + h + ":";
				pad = "0";
			} else {
				out += pad + pad + pad;
			}
			
			if (m > 10) {
				out += m + ":";
				pad = "0";
			} else if (m > 0) {
				out += pad + m + ":";
				pad = "0";
			} else {
				out += pad + pad + pad;
			}
			
			String s_out = "";
			if (s > 10) {
				s_out += s;
			} else if (s > 0) {
				s_out += pad + s;
			} else {
				s_out += pad + pad + pad + pad + pad + pad;
			}
			while (s_out.length() < 6) {
				s_out += "0";
			}
			if (s_out.length() > 6) {
				s_out = s_out.substring(0,6);
			}
			out += s_out;
			return out;
		}
	}

}
