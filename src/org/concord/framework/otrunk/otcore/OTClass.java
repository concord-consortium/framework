package org.concord.framework.otrunk.otcore;

import java.util.ArrayList;


public interface OTClass extends OTType
{
	public ArrayList getOTAllClassProperties();
		
	public ArrayList getOTClassProperties();
	
	public OTClassProperty getProperty(String resourceName);

	public ArrayList getOTSuperTypes();
}
