package org.concord.framework.util;

public interface TimeProvider {
    public long currentTimeMillis();
    public long getSystemDrift();
}
