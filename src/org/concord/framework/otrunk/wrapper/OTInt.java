package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;

public interface OTInt
    extends OTObjectInterface
{
	public int getValue();
	public void setValue(int value);
	
	public static int DEFAULT_value = 0;
}
