package org.concord.framework.util;

import java.util.logging.Logger;

/**
 * Standard implementation of the TimeProvider interface. Simply wraps the system clock.
 * @author aunger
 *
 */
public class TimeProviderImpl implements TimeProvider {
    private static final Logger logger = Logger.getLogger(TimeProviderImpl.class.getName());
    
    public TimeProviderImpl() {
        logger.info("Creating default TimeProvider...");
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long getSystemDrift() {
        return 0;
    }

    public long adjustTime(long time) {
        return time;
    }

}
