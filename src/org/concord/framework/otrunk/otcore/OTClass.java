package org.concord.framework.otrunk.otcore;

import java.util.ArrayList;


public interface OTClass extends OTType
{
	public ArrayList<OTClassProperty> getOTAllClassProperties();
		
	public ArrayList<OTClassProperty> getOTClassProperties();
	
	public OTClassProperty getProperty(String resourceName);

	public ArrayList<OTClass> getOTSuperTypes();
}
