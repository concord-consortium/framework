package org.concord.framework.logging;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class StdoutConsoleHandler extends StreamHandler {
	public StdoutConsoleHandler() {
		super();
		setOutputStream(System.out);
	}

    /**
     * Publish a <tt>LogRecord</tt>.
     * <p>
     * The logging request was made initially to a <tt>Logger</tt> object,
     * which initialized the <tt>LogRecord</tt> and forwarded it here.
     * <p>
     * @param  record  description of the log event. A null record is
     *                 silently ignored and is not published
     */
    public void publish(LogRecord record) {
	super.publish(record);	
	flush();
    }
	
    /**
     * Override <tt>StreamHandler.close</tt> to do a flush but not
     * to close the output stream.  That is, we do <b>not</b>
     * close <tt>System.err</tt>.
     */
    public void close() {
	flush();
    }

}
