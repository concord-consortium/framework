
/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
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
 */

/*
 * Last modification information:
 * $Revision: 1.3 $
 * $Date: 2005-04-10 03:57:13 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;

/**
 * OTWrapper
 * 
 * This interface is used by the OTrunk framework.
 * OTWrappers are objects that wrap a "real" object and are able to
 * save and restore data to/from this object.
 *  
 * Note:
 * The OT objects implementing this interface should also implement 
 * a public method called getWrappedObject() that returns the object
 * that is being "wrapped". The return type of this method should
 * coincide with the class of the wrapped object.
 * (That is the reason why this method is not defined in this interface)
 *
 * Date created: Mar 21, 2005
 *
 * @author scytacki<p>
 *
 */
public interface OTWrapper
    extends OTObject
{
    public void saveObject();

	/* Also, implement this implicit method:
	 * 
	 * public <type> getWrappedObject()
	 * 
	 * This method is not explicitly defined in this interface because  
	 * each object would return a different class, so the method would
	 * have a different signature, and that is not possible in java.
	 */
}
