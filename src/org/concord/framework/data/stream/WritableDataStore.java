

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
 * $Date: 2004-11-12 18:40:24 $
 * $Author: eblack $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;


/**
 * WritableDataStore
 * This is a Data Store with data that can be modified
 *
 * Date created: Oct 25, 2004
 *
 * @author imoncada<p>
 *
 */
public interface WritableDataStore extends DataStore
{
	/**
	 * Sets the value in a specific sample in a specific
	 * channel
	 * @param numChannel	channel number, starting from 0, >0
	 * @param numSample		sample number, starting from 0, >0
	 * @param value			value to add
	 */
	public void setValueAt(int numSample, int numChannel, Object value);

	/**
	 * Removes a value in a specific sample in all channels
	 * @param numSample		sample number, starting from 0, >0
	 */
	public void removeValueAt(int numSample);
}
