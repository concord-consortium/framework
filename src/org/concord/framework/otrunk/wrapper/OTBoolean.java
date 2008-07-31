package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;

public interface OTBoolean
    extends OTObjectInterface
{
	public boolean getValue();
	public void setValue(boolean value);
	
	public static boolean DEFAULT_value = false;
}
