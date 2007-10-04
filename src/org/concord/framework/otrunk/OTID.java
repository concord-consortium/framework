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
 * $Revision: 1.4 $
 * $Date: 2007-10-04 21:27:21 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk;


/**
 * OTID
 * This is a mostly opaque object.  However this abstraction
 * is not currently maintained everywhere.  
 * 
 * The toString() should not be used to get a serialized version 
 * of the object.
 * Instead the getExternalID method should be used on the objectService.
 * 
 * Date created: Dec 5, 2004
 *
 * @author scott<p>
 *
 */
public interface OTID
{
	/**
	 * Not all IDs can provide a valid external form.  
	 * Those IDs that can't directly provide an external form
	 * throw a RuntimeException.
	 * 
	 * The method getExternalID on OTObjectService should be used instead.
	 * 
	 * @return
	 */
	public String toExternalForm();
}
