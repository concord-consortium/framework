package org.concord.framework.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class SimpleLogFormatter extends Formatter {
	private static final long startMillis = System.currentTimeMillis();

	@Override
	public synchronized String format(LogRecord record) {
		String output = "";
		output += ConsoleLogTimeConverter.getTime(record.getMillis() - startMillis);
		output += " " + record.getLevel().toString().substring(0, 4);
		output += " " + getSourceClassString(record);
		output += "." + record.getSourceMethodName();
		output += ": " + record.getMessage();
		output += "\n";
		if (record.getThrown() != null) {
			Throwable t = record.getThrown();
			output += t.toString();
			output += "\n";
			for (StackTraceElement e : t.getStackTrace()) {
				output += "   " + e.toString() + "\n";
			}
		}
		return output;
	}

	protected String getSourceClassString(LogRecord record)
	{
		return record.getSourceClassName();
	}
	
	private static class ConsoleLogTimeConverter {

		public static String getTime(long elapsedMs) {
			long sec = elapsedMs/1000;
			double s = elapsedMs/1000.0d % 60;
			long tot_min = sec/60;
			int m = (int) (tot_min % 60);
			long tot_hour = m/60;
			int h = (int) (tot_hour % 60);
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
