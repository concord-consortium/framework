

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

import java.util.Vector;


/**
 * ProducerDataStore
 * Class name and description
 *
 * Date created: Oct 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class ProducerDataStore extends AbstractDataStore 
	implements DataListener
{
	protected DataProducer dataProducer;
	
	private int numberOfChannels = 0;
	private int dataOffset = 0;
	private int nextSampleOffset = 1;
	private float dt = 1;
	private boolean useDtAsChannel = true;
		
	protected DataStreamDescription dataStreamDesc;
	
	/**
	 * 
	 */
	public ProducerDataStore()
	{
		super();
	}
	
	/**
	 * 
	 */
	public ProducerDataStore(DataProducer dataProducer)
	{
		this();
		setDataProducer(dataProducer);
	}

	/**
	 * @return Returns the dataProducer.
	 */
	public DataProducer getDataProducer()
	{
		return dataProducer;
	}
	
	/**
	 * Sets the data producer of this data store
	 * @param dataProducer The dataProducer to set.
	 */
	public void setDataProducer(DataProducer dataProducer)
	{
		if (this.dataProducer != null){
			this.dataProducer.removeDataListener(this);
		}
		this.dataProducer = dataProducer;
		if (this.dataProducer != null){
			updateDataDescription(this.dataProducer.getDataDescription());
			this.dataProducer.addDataListener(this);
		}
	}

	
	
	/**
	 * @see org.concord.framework.data.stream.DataStore#getValueAt(int, int)
	 */
	public Object getValueAt(int numSample, int numChannel)
	{
		//Special case: when dt is a channel, it's the channel -1
		if (numChannel == -1){
			return new Float(numSample * dt);
		}
		
		return super.getValueAt(numSample, numChannel);
	}
	
	/**
	 * @see org.concord.framework.data.stream.DataStore#getTotalNumChannels()
	 */
	public int getTotalNumChannels()
	{
		return super.getTotalNumChannels();
		//if (useDtAsChannel){
		//	nChannels++;
		//}
	}
	
	/**
	 * @see org.concord.framework.data.stream.DataListener#dataReceived(org.concord.framework.data.stream.DataStreamEvent)
	 */
	public void dataReceived(DataStreamEvent dataEvent)
	{
		float [] data = dataEvent.getData();
		int numberOfSamples = dataEvent.getNumSamples();
		int sampleIndex;
		Float value;
		int iSamples = getTotalNumSamples();
				
		sampleIndex = dataOffset;
		
		for(int i=0; i<numberOfSamples; i++)
		{
			for(int j=0; j<numberOfChannels; j++)
			{
				value = new Float(data[sampleIndex + j]);
				
				//This is not a WritableDataStore, so this is not valid anymore:
				//setValueAt(i + iSamples, j, value);
				addValue(j, value);
			}
			sampleIndex+= nextSampleOffset;
		}

		notifyDataAdded();
	}

	/**
	 * Adds a value to the channel indicated
	 * If the channel doesn't exist, it doesn't do anything
	 *
	 * @param numChannel	channel number, starting from 0, >0
	 * @param value			value to add
	 */
	protected void addValue(int numChannel, Object value)
	{
		if (numChannel < 0) return;	
		if (numChannel >= channelsValues.size()) return;

		//Locate the channel
		Vector channel = (Vector)channelsValues.elementAt(numChannel);
		
		//Add the value to the channel
		channel.addElement(value);
		
		notifyDataAdded();
	}
	
	/**
	 * @see org.concord.framework.data.stream.DataListener#dataStreamEvent(org.concord.framework.data.stream.DataStreamEvent)
	 */
	public void dataStreamEvent(DataStreamEvent dataEvent)
	{
		updateDataDescription(dataEvent.getDataDescription());
		notifyChannelDescChanged();
	}

	protected void updateDataDescription(DataStreamDescription desc)
	{
		dataStreamDesc = desc;
		dataOffset = desc.getDataOffset();
		nextSampleOffset = desc.getNextSampleOffset();
		dt = desc.getDt();
		numberOfChannels = desc.getChannelPerSample();
		useDtAsChannel = (desc.getDataType() == DataStreamDescription.DATA_SEQUENCE);

		//Make sure the values vector has enough channels
		while (numberOfChannels > channelsValues.size()){
			//Add empty vectors until all channels have space
			channelsValues.addElement(new Vector());
		}
	}
	
	
	/**
	 * @see org.concord.framework.data.stream.DataStore#getDataChannelDescription(int)
	 */
	public DataChannelDescription getDataChannelDescription(int numChannel)
	{
		DataChannelDescription channelDesc;
		if (dataStreamDesc == null) return null;
		
		//Special case: using dt as the channel -1
		if (numChannel == -1){
			return dataStreamDesc.getDtChannelDescription();
		}
		
		channelDesc = dataStreamDesc.getChannelDescription(numChannel);
		if (channelDesc == null){
			channelDesc = super.getDataChannelDescription(numChannel);
		}
		return channelDesc;
	}
	
	/**
	 * @return Returns the useDtAsChannel.
	 */
	public boolean isUseDtAsChannel()
	{
		return useDtAsChannel;
	}
	
	/**
	 * @param useDtAsChannel The useDtAsChannel to set.
	 */
	public void setUseDtAsChannel(boolean useDtAsChannel)
	{
		this.useDtAsChannel = useDtAsChannel;
	}
}
