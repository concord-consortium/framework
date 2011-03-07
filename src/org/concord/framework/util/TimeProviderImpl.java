package org.concord.framework.util;

/**
 * Standard implementation of the TimeProvider interface. Simply wraps the system clock.
 * @author aunger
 *
 */
public class TimeProviderImpl implements TimeProvider {

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long getSystemDrift() {
        return 0;
    }

}
