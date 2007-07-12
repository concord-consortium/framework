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
 * $Revision: 1.12 $
 * $Date: 2007-07-12 18:07:52 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;


/**
 * PfViewContainer
 * Class name and description
 *
 * Date created: Sep 8, 2004
 *
 * @author scott<p>
 *
 */
public interface OTViewContainer
{
	/**
	 * This will set the current object in the view container based
	 * on the default view returned from the viewFactory.
	 * 
	 * @param pfObject
	 */
	public void setCurrentObject(OTObject pfObject);

	// TODO add a method to specify the viewEntry to use
	
	public OTObject getCurrentObject();
    
    public boolean isUpdateable();
    public void setUpdateable(boolean b);
    
    public OTViewContainer getParentContainer();
    
    public OTViewContainer getUpdateableContainer();
}
