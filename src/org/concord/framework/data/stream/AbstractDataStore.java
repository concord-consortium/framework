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
public abstract class AbstractDataStore
	implements DataStore
{
	protected boolean provideDefaultChannelDesc = true;	//Indicates if the data store will provide 
														//default DataChannelDescriptions with the index of the channel (headers 0,1,2,3...)
	
	protected Vector channelsValues;		//Vector of Vector objects, one vector per channel
	protected Vector channelDesc;	//Vector of ChannelDescription objects, one per channel
	
	protected Vector dataStoreListeners;
	
	/**
	 * 
	 */
	public AbstractDataStore()
	{
		super();
		channelsValues = new Vector();
		channelDesc = new Vector();
		dataStoreListeners = new Vector();
	}

	/**
	 * @see org.concord.framework.data.stream.DataStore#getTotalNumSamples()
	 */
	public int getTotalNumSamples()
	{
		//Returns the maximum between the number of samples in each channel
		int t = 0;
		for (int i=0; i < channelsValues.size(); i++){
			Vector channel = (Vector)channelsValues.elementAt(i);
			if (channel.size() > t){
				t = channel.size();
			}
		}
		return t;
	}

	/**
	 * @see org.concord.framework.data.stream.DataStore#getTotalNumChannels()
	 */
	public int getTotalNumChannels()
	{
		//System.out.println("channels:"+values.size());
		return channelsValues.size();
	}

	/**
	 * @see org.concord.framework.data.stream.DataStore#getValueAt(int, int)
	 */
	public Object getValueAt(int numSample, int numChannel)
	{
		if (numSample < 0 || numChannel < 0) return null;
		
		//Locate the channel
		if (numChannel >= channelsValues.size()) return null;		
		Vector channel = (Vector)channelsValues.elementAt(numChannel);
		
		//Locate the sample within the channel
		if (numSample >= channel.size()) return null;		
		Object val = channel.elementAt(numSample);
		
		return val;
	}

	/**
	 * Clears all values in the data store
	 */
	public void clearValues()
	{
		for (int i=0; i < channelsValues.size(); i++){
			Vector channel = (Vector)channelsValues.elementAt(i);
			channel.removeAllElements();
		}
		notifyDataRemoved();
	}
	
	/**
	 * @see org.concord.framework.data.stream.DataStore#getDataChannelDescription(int)
	 */
	public DataChannelDescription getDataChannelDescription(int numChannel)
	{
		if (numChannel < 0) return null;
		
		//Create the default channel descriptions for each channel (0,1,2,3...)
		if (provideDefaultChannelDesc){
			if (numChannel >= channelDesc.size()){
				DataChannelDescription desc = new DataChannelDescription(String.valueOf(numChannel));
				setDataChannelDescription(numChannel, desc);
			}
		}
		
		//Locate the channel
		return (DataChannelDescription)channelDesc.elementAt(numChannel);
	}
	
	/**
	 *
	 */
	public void setDataChannelDescription(int numChannel, DataChannelDescription desc)
	{
		if (numChannel < 0) return;
		
		//Locate the channel
		while (numChannel >= channelDesc.size()){
			//Add empty objects until the desired channel
			channelDesc.addElement(null);
		}
		channelDesc.setElementAt(desc, numChannel);
	}
	
	/**
	 * @see org.concord.framework.data.stream.DataStore#addDataStoreListener(org.concord.framework.data.stream.DataStoreListener)
	 */
	public void addDataStoreListener(DataStoreListener l)
	{
		if (!dataStoreListeners.contains(l)){
			dataStoreListeners.add(l);
		}
	}

	/**
	 * @see org.concord.framework.data.stream.DataStore#removeDataStoreListener(org.concord.framework.data.stream.DataStoreListener)
	 */
	public void removeDataStoreListener(DataStoreListener l)
	{
		dataStoreListeners.remove(l);		
	}

	protected void notifyDataAdded()
	{
		DataStoreEvent evt = new DataStoreEvent(this, DataStoreEvent.DATA_ADDED);
		DataStoreListener l;
		for (int i=0; i<dataStoreListeners.size(); i++){
			l = (DataStoreListener)dataStoreListeners.elementAt(i);
			l.dataAdded(evt);
		}
	}
	
	protected void notifyDataRemoved()
	{
		DataStoreEvent evt = new DataStoreEvent(this, DataStoreEvent.DATA_ADDED);
		DataStoreListener l;
		for (int i=0; i<dataStoreListeners.size(); i++){
			l = (DataStoreListener)dataStoreListeners.elementAt(i);
			l.dataRemoved(evt);
		}
	}
	
	protected void notifyDataChanged()
	{
		DataStoreEvent evt = new DataStoreEvent(this, DataStoreEvent.DATA_ADDED);
		DataStoreListener l;
		for (int i=0; i<dataStoreListeners.size(); i++){
			l = (DataStoreListener)dataStoreListeners.elementAt(i);
			l.dataChanged(evt);
		}
	}
	
	protected void notifyChannelDescChanged()
	{
		DataStoreEvent evt = new DataStoreEvent(this, DataStoreEvent.DATA_DESC_CHANGED);
		DataStoreListener l;
		for (int i=0; i<dataStoreListeners.size(); i++){
			l = (DataStoreListener)dataStoreListeners.elementAt(i);
			l.dataChannelDescChanged(evt);
		}
	}
	
	/**
	 * @return Returns the provideDefaultChannelDesc.
	 */
	public boolean isProvideDefaultChannelDesc()
	{
		return provideDefaultChannelDesc;
	}
	
	/**
	 * @param provideDefaultChannelDesc The provideDefaultChannelDesc to set.
	 */
	public void setProvideDefaultChannelDesc(boolean provideDefaultChannelDesc)
	{
		this.provideDefaultChannelDesc = provideDefaultChannelDesc;
	}
}
