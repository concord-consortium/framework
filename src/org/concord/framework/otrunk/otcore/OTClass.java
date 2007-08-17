package org.concord.framework.otrunk.otcore;

import java.util.ArrayList;


public interface OTClass extends OTType
{
	public ArrayList getProperties();
		
	public OTClassProperty getProperty(String resourceName);
}
