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
 * Created on Aug 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.concord.framework.otrunk;

import org.concord.framework.otrunk.otcore.OTClass;
import org.concord.framework.otrunk.otcore.OTClassProperty;



//import java.util.Vector;

/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface OTObject 
{
	/**
	 * The OTID returned by this method can be turned into a string
	 * which can be serialized using the OTObjectService.getExternalID method.
	 * 
	 * The toString method on the returned OTID should not be used for this
	 * purpose.
	 * 
	 * @return
	 */
	public OTID getGlobalId();
	
	public String getName();
	
	public void setName(String name);

	/**
	 * This method is called when an this object is created. 
	 * The method will also be called when the object has been
	 * stored and then re-instanciated.
	 */
	public void init();	
	
	/**
	 * This is a public method so users of the object can create new
	 * objects.  
	 * 
	 * @return
	 */
    public OTObjectService getOTObjectService();

    /**
     * This does the same as this.getOTObjectService().getExternalID(this)
     * It returns a string that can be used to reference this object.  This string
     * is relative to the OTObjectService that is managing this particular object.
     * 
     * The case of Id matches the case of getGlobalId.
     * 
     * @return
     */
    public String otExternalId();
    
    public OTClass otClass();
    
    public Object otGet(OTClassProperty property);
    public void otSet(OTClassProperty property, Object newValue);
    
    boolean otIsSet(OTClassProperty property);
    void otUnSet(OTClassProperty property);
    
    public OTObjectMap getAnnotations();
}
