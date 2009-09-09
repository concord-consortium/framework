package org.concord.framework.logging;

public class ConsoleLogTimeConverter {
	public static enum Method { ONE, TWO, THREE }
	public static Method method = Method.ONE;
	
	/**
	 * Get a string formatted timestamp, which is a constant width (12 chars). There are 3 backend algorithms for creating this string:
	 * methodTwo is fastest, followed by methodOne (1.5 times slower), followed by methodThree (almost 5x slower).
	 * @param elapsedMs
	 * @return
	 */
	public static String getTime(long elapsedMs) {
		switch (method) {
		case ONE:
			return getMethodOne(elapsedMs);
		case TWO:
			return getMethodTwo(elapsedMs);
		case THREE:
			return getMethodThree(elapsedMs);

		default:
			break;
		}
		return "";
	}
	
	/**
	 * This is the original method as coded when this class was included as a private class in SimpleLogFormatter.
	 * @param elapsedMs
	 * @return
	 */
	private static String getMethodOne(long elapsedMs) {
		long elapsedSec = elapsedMs/1000;
		long ms = elapsedMs - (elapsedSec * 1000);
		long s = elapsedSec % 60;
		long elapsedMin = elapsedSec/60;
		int m = (int) (elapsedMin % 60);
		long elapsedHour = elapsedMin/60;
		int h = (int) (elapsedHour % 60);
		String pad = " ";
		String out = "";
		if (h >= 10) {
			out += h + ":";
			pad = "0";
		} else if (h > 0) {
			out += pad + h + ":";
			pad = "0";
		} else {
			out += pad + pad + pad;
		}
		
		if (m >= 10) {
			out += m + ":";
			pad = "0";
		} else if (m > 0) {
			out += pad + m + ":";
			pad = "0";
		} else {
			out += pad + pad + (h != 0 ? ":" : " ");
		}
		
		if (s >= 10) {
			out += s + ".";
			pad = "0";
		} else if (s > 0) {
			out += pad + s + ".";
			pad = "0";
		} else {
			out += pad + "0.";
		}
		
		if (ms >= 100) {
			out += ms;
		} else if (ms >= 10) {
			out += "0" + ms;
		} else {
			out += "00" + ms;
		}
		
		return out;
	}
	
	/**
	 * This method is similar to method one, but uses StringBuilder objects instead of normal Strings. It tends to be 10-15% faster than method one.
	 * @param elapsedMs
	 * @return
	 */
	private static String getMethodTwo(long elapsedMs) {
		long elapsedSec = elapsedMs/1000;
		long ms = elapsedMs - (elapsedSec * 1000);
		long s = elapsedSec % 60;
		long elapsedMin = elapsedSec/60;
		int m = (int) (elapsedMin % 60);
		long elapsedHour = elapsedMin/60;
		int h = (int) (elapsedHour % 60);
		boolean higherBitSet = false;
		StringBuilder out = new StringBuilder();
		if (h >= 10) {
			out.append(h);
			higherBitSet = true;
		} else if (h > 0) {
			out.append(" ");
			out.append(h);
			higherBitSet = true;
		} else {
			out.append("  ");
		}
		pad(out, higherBitSet, ":");
		
		if (m >= 10) {
			out.append(m);
			higherBitSet = true;
		} else if (m > 0) {
			pad(out, higherBitSet, "0");
			out.append(m);
			higherBitSet = true;
		} else {
			pad(out, higherBitSet, "0");
			pad(out, higherBitSet, "0");
		}
		pad(out, higherBitSet, ":");
		
		if (s >= 10) {
			out.append(s);
			out.append(".");
		} else if (s > 0) {
			pad(out, higherBitSet, "0");
			out.append(s);
			out.append(".");
		} else {
			pad(out, higherBitSet, "0");
			out.append("0.");
		}
		
		if (ms >= 100) {
			out.append(ms);
		} else if (ms >= 10) {
			out.append("0");
			out.append(ms);
		} else {
			out.append("00");
			out.append(ms);
		}
		
		return out.toString();
	}
	
	/**
	 * This method used String.format() to create the timestamp string. It's by far the clearest code-wise, but is also
	 * about 50% slower than the other two methods.
	 * @param elapsedMs
	 * @return
	 */
	private static String getMethodThree(long elapsedMs) {
		long elapsedSec = elapsedMs/1000;
		long ms = elapsedMs - (elapsedSec * 1000);
		long s = elapsedSec % 60;
		long elapsedMin = elapsedSec/60;
		int m = (int) (elapsedMin % 60);
		long elapsedHour = elapsedMin/60;
		int h = (int) (elapsedHour % 60);
		
		String msResult = "";
		if (h > 0) {
			msResult = String.format("%2d:%02d:%02d.%03d",h,m,s,ms);
		} else if (m > 0) {
			msResult = String.format("   %2d:%02d.%03d",m,s,ms);
		} else if (s > 0) {
			msResult = String.format("      %2d.%03d",s,ms);
		} else {
			msResult = String.format("       0.%03d",ms);
		}
		
		return msResult;
	}
	
	private static void pad(StringBuilder out, boolean higherBitSet, String padChar) {
		if (higherBitSet) {
			out.append(padChar);
		} else {
			out.append(" ");
		}
	}
}
