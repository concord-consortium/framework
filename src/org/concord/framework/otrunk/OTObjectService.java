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
 * $Revision: 1.5 $
 * $Date: 2007-02-12 05:37:47 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;

public interface OTObjectService
{
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
}
