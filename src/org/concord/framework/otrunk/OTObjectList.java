

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
 * $Date: 2005-04-12 05:26:20 $
 * $Author: imoncada $
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

	public abstract OTObject get(int index);

	public abstract Vector getVector();

	public abstract void add(OTObject obj);

	public abstract void add(int index, OTObject obj);

	/*
	 * It might be best if the users of this list could have all ids hidden from
	 * them.  However sometimes it is more efficient to add ids directly
	 * to the list.  If this method wasn't here then the call would have to
	 * instanciate the object and then call the other add method.
	 */
	public abstract void add(OTID id);

	public abstract int size();

	public abstract void remove(int index);
	
	public abstract void remove(OTObject obj);
	
	public abstract void removeAll();
}