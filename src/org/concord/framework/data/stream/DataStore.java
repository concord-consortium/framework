/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2004-08-24 23:15:08 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;


/**
 * DataStore
 * A Data Store is a class that stores data in the form of 
 * samples. Each sample can have different channels.
 * (For example, if the data were to be shown in a data table, 
 * the samples are rows and the channels are columns) 
 * The data stored has a data description also (units, etc)
 *
 * Date created: Aug 23, 2004
 *
 * @author imoncada<p>
 *
 */
public interface DataStore
{
	/**
	 * Returns the total number of samples in the data
	 * (number of sets of values stored)
	 * (For example, if the data is shown in a data table, 
	 * this value corresponds with the number of rows) 
	 * @return total number of samples in the data stored
	 */
	public int getTotalNumSamples();
	
	/**
	 * Returns the total number of channels in the data
	 * (number values in each set of values stored)
	 * (For example, if the data is shown in a data table, 
	 * this value corresponds with the number of columns) 
	 * @return total number of channels in the data stored
	 */
	public int getTotalNumChannels();
	
	/**
	 * Returns the value of a specific sample in a specific
	 * channel
	 * @return data value stored at the sample and channel specified
	 */
	public Object getValueAt(int numSample, int numChannel);

	public DataChannelDescription getDataChannelDescription(int numChannel);
}
