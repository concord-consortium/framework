

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
 * $Revision: 1.7 $
 * $Date: 2005-03-10 03:15:49 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;

import java.util.Vector;


/**
 * DefaultDataStore
 * This class is a default implementation of the WritableDataStore
 * interface. 
 *
 * Date created: Oct 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class DefaultDataStore extends AbstractDataStore
	implements WritableDataStore
{
	public DefaultDataStore()
	{
		super();
	}
	
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
		while (numChannel >= channelsValues.size()){
			//Add empty vectors until the desired channel
			channelsValues.addElement(new Vector());
			channelAdded = true;
		}
		Vector channel = (Vector)channelsValues.elementAt(numChannel);
		
		//Locate the sample within the channel
		while (numSample >= channel.size()){
			//Add empty elements until the desired sample
			channel.addElement(null);
		}
		
		if (numSample >= getTotalNumSamples()){
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

	/**
	 * @see org.concord.framework.data.stream.WritableDataStore#removeSampleAt(int)
	 */
	public void removeSampleAt(int numSample)
	{
		boolean valueRemoved = false;
		
		if (numSample < 0) return;
		
		for (int i=0; i < channelsValues.size(); i++){
			Vector channel = (Vector)channelsValues.elementAt(i);
			
			if (numSample < channel.size()){
				channel.remove(numSample);
				valueRemoved = true;
			}
		}
		
		if (valueRemoved){
			notifyDataRemoved();
		}
	}
	
	/**
	 * @param i
	 */
	public void insertSampleAt(int i)
	{
		for (int j=0; j < channelsValues.size(); j++){
			Vector channel = (Vector)channelsValues.elementAt(j);
			channel.add(i, null);
		}
	}
}
