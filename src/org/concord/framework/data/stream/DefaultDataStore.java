/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2004-10-26 17:27:21 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;

import java.util.Vector;


/**
 * DefaultDataStore
 * This class is a default implementation of the DataStore
 * interface. It keeps values in a set of vectors, one vector
 * per channel. 
 * It also has a setValueAt() method, to set the values.
 *
 * Date created: Oct 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class DefaultDataStore extends AbstractDataStore
	implements WritableDataStore
{
	/**
	 * Sets a value at the sample and channel indicated
	 * This method adds samples and channels if necessary,
	 * so the intermediate values will be empty
	 * 
	 * @param numChannel	channel number, starting from 0, >0
	 * @param numSample		sample number, starting from 0, >0
	 * @param value			value to add
	 */
	public void setValueAt(int numSample, int numChannel, Object value)
	{
		boolean channelAdded = false;
		boolean valueAdded = false;
		if (numSample < 0 || numChannel < 0) return;
		
		//Locate the channel
		while (numChannel >= values.size()){
			//Add empty vectors until the desired channel
			values.addElement(new Vector());
			channelAdded = true;
		}
		Vector channel = (Vector)values.elementAt(numChannel);
		
		//Locate the sample within the channel
		while (numSample >= channel.size()){
			//Add empty elements until the desired sample
			channel.addElement(null);
			valueAdded = true;
		}
		
		//Set the value
		channel.setElementAt(value, numSample);
		
		if (channelAdded){
			notifyChannelDescChanged();
		}
		if (valueAdded){
			notifyDataAdded();
		}
		else{
			notifyDataChanged();
		}
	}
	
}
