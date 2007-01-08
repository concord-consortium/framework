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
 * $Revision: 1.1 $
 * $Date: 2007-01-08 20:06:17 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;


/**
 * OTController
 * 
 * This interface is used by the OTrunk framework.
 * 
 * OTController are objects that manage a "real" objects and are able to
 * save and restore data to/from an OTObject.  The methods on this 
 * interface should be not be called directly.  Instead the OTControllerService
 * (soon to be merged with the OTViewFactory)
 * should be used to manage OTController objects.  
 *  
 * Date created: Mar 21, 2005
 *
 * @author scytacki<p>
 *
 */
public interface OTController
{
	/**
	 * You need to add a static field that has the same signature as this one
	 * to your implementation of this interface.  This field is used when the
	 * controller class is registered.  It it is also used by the default implementation
	 * of the createRealObject method in DefaultOTController
	 *  
	 */
	public final static Class [] realObjectClasses = null;

	public final static Class [] otObjectClasses = null;

    public void initialize(OTObject otObject, OTControllerService controllerService);
	
    public OTObject getOTObject();
    
	/**
	 * Create an instance of the real object which this controller represents.
	 * 
	 * It is valid to have multiple realObject classes supported by 
	 * the same OTController class.  In that case this method might branch on the 
	 * content of its OTObject.
	 * 
	 * @return
	 */
	public Object createRealObject();
	
	/**
	 * Load the state from the OT object into the realObject.  This method 
	 * can be called either directly after createRealObject or it can be 
	 * called on its own.   
	 * 
	 * @param controllerService
	 * @param realObject
	 */
    public void loadRealObject(Object realObject);
    
    /**
     * This will be called after loadRealObject.  It could also be called on its
     * own.  It should be used registers listeners on the real object. These listeners
     * are to update the state in the OTObject with the state in the real object.
     *  
     * The OTController might also want to update all the real objects if changes to 
     * one cause a change in the state of the OTObject.   This would be the case if
     * if a single OT object is being viewed in two places at the same time.  In that 
     * case there will be two controllerServices and two realObjects 
     * 
     * @param controllerService
     * @param realObject
     */
	public void registerRealObject(Object realObject);
	
	/**
	 * This saves the state of realObject into the ot object.
	 * 
	 * TODO it is not clear how much state should be saved here and whether it should
	 * be recursive.  If listeners are being used on all the sub objects then when
	 * this is called after the object has been registered it doesn't need to be
	 * recursive.  If this is called to store a realObject that wasn't created by 
	 * this framework then it will need to recursively save all the sub objects. 
	 * 
	 * It should not mess up anything if the method is recursive.  It is just a waste of
	 * time in some cases.  There is a danger of infinite loops with circular 
	 * references.  
	 * 
	 * @param controllerService
	 * @param realObject
	 */
    public void saveRealObject(Object realObject);
}
