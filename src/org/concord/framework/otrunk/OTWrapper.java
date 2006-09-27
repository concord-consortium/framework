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
 * Last modification information:
 * $Revision: 1.9 $
 * $Date: 2006-09-27 20:55:47 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;

/**
 * OTWrapper
 * 
 * This interface is used by the OTrunk framework.
 * OTWrappers are objects that wrap a "real" objects and are able to
 * save and restore data to/from this object.  The methods on this 
 * interface should be not be called directly.  Instead the OTWrapperService
 * should be used to manage ot wrapper objects.  
 *  
 * Date created: Mar 21, 2005
 *
 * @author scytacki<p>
 *
 */
public interface OTWrapper
    extends OTObject
{
	/**
	 * You need to add a static field that has the same signature as this one
	 * to your implementation of this interface.  This field is used when the
	 * wrapper class is registered.  It it is also used by the default implementation
	 * of the createRealObject method in DefaultOTWrapper
	 *  
	 */
	public final static Class [] realObjectClasses = null;
	
	/**
	 * Create an instance of the real object which this wrapper represents.
	 * 
	 * It is valid to have multiple classes of objects wrapped by 
	 * the same OTWrapper.  In that case this method might branch on the 
	 * content in the resources of the ot object.
	 * 
	 * 
	 * @return
	 */
	public Object createRealObject();
	
	/**
	 * Load the state from the OTWrapper into the realObject.  This method 
	 * can be called either directly after createRealObject or it can be 
	 * called on its own.   
	 * 
	 * @param wrapperService
	 * @param realObject
	 */
    public void loadRealObject(OTWrapperService wrapperService, Object realObject);
    
    /**
     * This will be called after loadRealObject.  It could also be called on its
     * own.  It should be used registers listeners on the real object. These listeners
     * are to update the state in the OTObject with the state in the real object.
     *  
     * The OTWrapper might also want to update all the real objects if changes to 
     * one cause a change in the state of the OTObject.   This would be the case if
     * if a single OTWrapper is being viewed in two places at the same time.  In that 
     * case there will be two wrapperServices and two instances 
     * 
     * @param wrapperService
     * @param realObject
     */
	public void registerRealObject(OTWrapperService wrapperService, Object realObject);
	
	/**
	 * This saves the state of realObject into this ot object.
	 * 
	 * TODO it is not clear how much state should be saved here and whether it should
	 * be recursive.  If listeners are being used on all the sub objects then when
	 * this is called after the object has been registered it doesn't need to be
	 * recursive.  If this is called to store a realObject that wasn't created by 
	 * the wrapper this will need to recursively save all the sub objects. 
	 * 
	 * It should not mess up anything if the method is recursive.  It is just a waste of
	 * time in some cases.  However there is a danger of infinite loops with circular 
	 * references.  
	 * 
	 * @param wrapperService
	 * @param realObject
	 */
    public void saveRealObject(OTWrapperService wrapperService, Object realObject);
}
