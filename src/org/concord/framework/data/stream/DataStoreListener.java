/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2004-10-26 17:27:21 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;


/**
 * DataStoreListener
 * Class name and description
 *
 * Date created: Aug 25, 2004
 *
 * @author imoncada<p>
 *
 */
public interface DataStoreListener
{
	/**
	 * Method called when there is data added to a data store 
	 * @param evt
	 */
	public void dataAdded(DataStoreEvent evt);
	
	/**
	 * Method called when there is data removed from a data store 
	 * @param evt
	 */
	public void dataRemoved(DataStoreEvent evt);
	
	/**
	 * Method called when there is data changed on a data store 
	 * @param evt
	 */
	public void dataChanged(DataStoreEvent evt);

	/**
	 * Method called when there is a change in the data channels description
	 * or a channel has been added or removed 
	 * @param evt
	 */
	public void dataChannelDescChanged(DataStoreEvent evt);
}
