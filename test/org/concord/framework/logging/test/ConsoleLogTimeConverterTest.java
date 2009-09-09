package org.concord.framework.logging.test;

import junit.framework.TestCase;

import org.concord.framework.logging.ConsoleLogTimeConverter;
import org.concord.framework.logging.ConsoleLogTimeConverter.Method;

public class ConsoleLogTimeConverterTest extends TestCase {
	private static final long SECONDS = 1000;
	private static final long MINUTES = 60*SECONDS;
	private static final long HOURS = 60*MINUTES;
	private static final long DAYS = 24*HOURS;
	private long startMs;
	
	private void doTest(String expected, long ms) throws Exception {
		String actual = ConsoleLogTimeConverter.getTime(ms);
		assertEquals(expected, actual);
	}
	
	public void test100Millisecond() throws Exception {
		doTest("       0.100", 100);
	}
	
	public void testOneSecond() throws Exception {
		doTest("       1.000", 1*SECONDS);
	}
	
	public void testOneMinute() throws Exception {
		doTest("    1:00.000", 1*MINUTES);
	}
	
	public void testOneHour() throws Exception {
		doTest(" 1:00:00.000", 1*HOURS);
	}
	
	public void testOneDay() throws Exception {
		doTest("24:00:00.000", 1*DAYS);
	}
	
	public void testMethodOne() throws Exception {
		startMs = System.currentTimeMillis();
		ConsoleLogTimeConverter.method = Method.ONE;
		long count = all();
		logStop(count);
	}
	
	public void testMethodTwo() throws Exception {
		startMs = System.currentTimeMillis();
		ConsoleLogTimeConverter.method = Method.TWO;
		long count = all();
		logStop(count);
	}
	
	public void testMethodThree() throws Exception {
		startMs = System.currentTimeMillis();
		ConsoleLogTimeConverter.method = Method.THREE;
		long count = all();
		logStop(count);
	}
	
	private long all() throws Exception {
		long count = 0;
		for (int h = 0; h < 25; h++) {
//			System.out.println(h + ": ");
			for (int m = 0; m < 60; m++) {
//				System.out.print(".");
				for (int s = 0; s < 60; s++) {
//					for (int ms = 0; ms < 1000; ms++) {
						int ms = (int) ((Math.random()*1000) % 1000);
						int timeVal = (h*60*60*1000)+(m*60*1000)+(s*1000)+ms;
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
						
						doTest(msResult, timeVal);
						count++;
//					}
				}
			}
//			System.out.println();
		}
		return count;
	}
	
	private void logStop(long count) {
		long stopMs = System.currentTimeMillis();
		long elapsed = stopMs - startMs;
		System.out.println("Ran " + count + " times in " + elapsed + " ms. Avg: " + ((double)elapsed)/((double)count) + " ms/invocation");
	}
}
