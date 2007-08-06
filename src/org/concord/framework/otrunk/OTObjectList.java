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
 * $Revision: 1.6 $
 * $Date: 2007-08-06 18:57:32 $
 * $Author: scytacki $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
 */
package org.concord.framework.otrunk;

import java.util.Vector;


/**
 * OTObjectList
 * Class name and description
 *
 * Date created: Dec 5, 2004
 *
 * @author scott<p>
 *
 */
public interface OTObjectList
{

	public OTObject get(int index);

	public Vector getVector();

	public void add(OTObject obj);

	public void add(int index, OTObject obj);

	/*
	 * It might be best if the users of this list could have all ids hidden from
	 * them.  However sometimes it is more efficient to add ids directly
	 * to the list.  If this method wasn't here then the call would have to
	 * instanciate the object and then call the other add method.
	 */
	public void add(OTID id);

	public void set(int index, OTObject obj);
	
	public int size();

	public void remove(int index);
	
	public void remove(OTObject obj);
	
	public void removeAll();    
}