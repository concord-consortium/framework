/*
 * Created on Jul 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.otrunk;





/**
 * This class can be used to create more complex OTObjects.
 * The OTObject interface can be used for simple interfaces
 * that adhere to the current conventions of the OTrunk.
 * If there is an interface that doesn't follow these conventions
 * you can extend this class and make an OTResourceSchema 
 * interface to store the state.  The OTrunk will create a 
 * proxy class that implements this OTResourceSchema.  
 * 
 * TODO provide some pointers to examples.
 * 
 * @author scott
 *
 */
public class DefaultOTObject implements OTObject
{
	private OTrunk otDatabase;
	
	private OTResourceSchema resources;
	
	public DefaultOTObject(OTResourceSchema resources)
	{
		this.resources = resources;		
	}
			
	public OTID getGlobalId()
	{
		return resources.getGlobalId();
	}
	
	public String getName()
	{
		return resources.getName();
	}
	
	public void setName(String name)
	{
		resources.setName(name);
	}
		
	/**
	 * This method can be used by an object to get its own database.
	 * @return
	 */
	protected OTrunk getOTDatabase()
	{
		return otDatabase;
	}
	
	public void setOTDatabase(OTrunk otDatabase)
	{
		this.otDatabase = otDatabase;
	}
	
	public void init()
	{
	}
	
	public OTObject getReferencedObject(OTID id)
	{
    	try {
    		OTrunk db = getOTDatabase();
    		return db.getOTObject(getGlobalId(), id);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
	}	
}
