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
 * $Date: 2007-05-23 19:42:08 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;

public interface OTObjectService
{
	/**
	 * Use this method create a new OTObject.  
	 * 
	 * @param objectClass - the class or interface of the object to create this class
	 *   needs to implement OTObject
	 * @return
	 * @throws Exception
	 */
    public OTObject createObject(Class objectClass) throws Exception;

    public OTObject getOTObject(OTID childID) throws Exception;

    /**
     * This will create an OTID from the passed in string.
     * 
     * @param otidStr
     * @return
     */
    public OTID getOTID(String otidStr);

    public OTControllerService createControllerService(); 

    /**
     * create a new OTObject and copy the original object 
     * into that one.    
     * 
     * This will store orphan objects in the libary of the OTSystem if there is one.
     * Otherwise orphan objects will not be copied.  See the other copyObject for more
     * info about orphan objects.
     * 
     * @param original
     * @param maxDepth how many objects deep to copy, -1 means
     *   every referenced object.  0 means only copy the original
     *   object, 1 means copy the original object and all of the
     *   objects it directly references, 2 means copy the original
     *   object and all of the objects it directly references and 
     *   all of the object those objects directly reference...
     * @return
     */
    public OTObject copyObject(OTObject original, int maxDepth)
    	throws Exception;
    
    /**
     * create a new OTObject and copy the original object 
     * into that one.    
     * 
     * this has an OTObjectList were the orphan objects will
     *   be copied into.  Orphan objects occur when there is a reference the object
     *   but that type of reference cannot contain the object.  The prime example of 
     *   this is in OTCompoundDoc where an object can be referenced in the bodyText
     *   using refid="__object_id__".  The object being referenced can be defined anywhere 
     *   in the otml file.  When doing a deep copy the copy of this reference needs to be 
     *   defined/stored somewhere.  With this method it is stored in the orphanObjectList
     *   If the copy code can determine the object is stored somewhere within the copied
     *   object tree, then it won't be added to the orphanObjectListobject.    
     *   
     * @param original the object to be copied.
     * @param orphanObjectList 
     * @param maxDepth see other copyObject
     * @return
     * @throws Exception
     */
	public OTObject copyObject(OTObject original, OTObjectList orphanObjectList, 
	                           int maxDepth) 
		throws Exception;
	
	/**
	 * This can be used to register OTPackages.  If you are loading
	 * in all the state from storage this should not be needed.  
	 * But if you want create OTObjects dynamically then you should
	 * register all the packages for the OTObjects you are going to create. 
	 * 
	 * @param packageClass
	 */
    public void registerPackageClass(Class packageClass);
    
    /**
     * Get an OTrunk service, this could be used for example to get the 
     * scripting service, or the sensor data manger.
     * 
     * The default implemention just calls OTrunk.getService
     * 
     * @param serviceClass
     * @return
     */
    public Object getOTrunkService(Class serviceInterface);
}
