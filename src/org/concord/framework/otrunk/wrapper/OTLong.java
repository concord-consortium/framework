package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;

public interface OTLong extends OTObjectInterface {
    public long getValue();
    public void setValue(long value);
    
    public static long DEFAULT_value = 0;
}
