/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2004-10-28 18:59:25 $
 * $Author: imoncada $
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
