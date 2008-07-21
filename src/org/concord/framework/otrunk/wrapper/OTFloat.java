package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;

public interface OTFloat
    extends OTObjectInterface
{
	public float getValue();
	public void setValue(float value);
	
	public static float DEFAULT_value = 0f;
}
