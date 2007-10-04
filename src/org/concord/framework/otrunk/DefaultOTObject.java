/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01742
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * END LICENSE */

/*
 * Created on Jul 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.otrunk;

import org.concord.framework.otrunk.otcore.OTClass;
import org.concord.framework.otrunk.otcore.OTClassProperty;


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
public class DefaultOTObject 
	implements OTObject, OTChangeNotifying
{
	private OTResourceSchema resources;
	
	public DefaultOTObject(OTResourceSchema resources)
	{
		this.resources = resources;		
	}
	
	//public DefaultOTObject(Vector userList) {
	//	this.userList = userList;
	//}
			
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
     * This method can be used by an object to get the object service
     * associated with this object.  This service can be used for creating
     * new objects, and getting objects from ids.
     * @return
     */
    public OTObjectService getOTObjectService()
    {
        return resources.getOTObjectService();
    }
    
	public void init()
	{
	}
	
	public OTObject getReferencedObject(String id)
	{
        OTObjectService objService = resources.getOTObjectService();
	    OTID linkId = objService.getOTID(id);
	    if(linkId == null) {
	        return null;
	    }
	    return getReferencedObject(linkId);
	}
	
	public OTID getReferencedId(String id)
	{
        OTObjectService objService = resources.getOTObjectService();
        return objService.getOTID(id);
	}
	
	public OTObject getReferencedObject(OTID id)
	{
    	try {
            OTObjectService objService = resources.getOTObjectService();
    		return objService.getOTObject(id);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
	}	

    public int hashCode()
    {
        String str = getClass().getName() + "@" +  getGlobalId().hashCode();
        return str.hashCode();
    }
    
	public boolean equals(Object other)
	{
		if(!(other instanceof OTObject)){
			return false;
		}
		
		if(this == other) {
			return true;
		}
		
		if(((OTObject)other).getGlobalId().equals(getGlobalId())) {
			System.err.println("compared two ot objects with the same ID but different instances");
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
     * @see org.concord.framework.otrunk.OTChangeNotifying#addOTChangeListener(org.concord.framework.otrunk.OTChangeListener)
     */
    public void addOTChangeListener(OTChangeListener listener)
    {
    	resources.addOTChangeListener(listener);
    }
    
    /* (non-Javadoc)
     * @see org.concord.framework.otrunk.OTChangeNotifying#removeOTChangeListener(org.concord.framework.otrunk.OTChangeListener)
     */
    public void removeOTChangeListener(OTChangeListener listener)
    {
    	resources.removeOTChangeListener(listener);
    }

	public OTClass otClass() {
		return resources.otClass();
	}

	public Object otGet(OTClassProperty property) {
		return resources.otGet(property);
	}

	public boolean otIsSet(OTClassProperty property) {
		return resources.otIsSet(property);
	}

	public void otSet(OTClassProperty property, Object newValue) {
		resources.otSet(property, newValue);
	}

	public void otUnSet(OTClassProperty property) {
		resources.otUnSet(property);
	}    
}
