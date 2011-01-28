package org.concord.framework.otrunk.wrapper;

import org.concord.framework.otrunk.OTObjectInterface;

public interface OTDouble
    extends OTObjectInterface
{
	public double getValue();
	public void setValue(double value);
	
	public static double DEFAULT_value = 0d;
}
