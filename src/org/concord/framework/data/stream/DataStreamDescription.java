

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
package org.concord.framework.data.stream;

public class DataStreamDescription
{
	/**
	 * This set of data uses the dt because it is one after the
	 * other in time: sequencial data
	 */  
	public final static int DATA_SEQUENCE = 0;
	
	/**
	 * This set of data is a collection of points that 
	 * does not need to be sequencial in time.
	 */ 
	public final static int DATA_SERIES = 1;

	private int dataType = DATA_SEQUENCE;

	// This field is redundant
	// private int channelPerSample;
	private float dt;
	private int dataOffset = 0;
	private int nextSampleOffset = -1;
	
	private DataChannelDescription [] channelDescriptions = null;	//DataChannelDescription objects
	private DataChannelDescription dtChannelDescription;

	public DataStreamDescription(){
		this(0.0f,1);
	}

	public DataStreamDescription(float dt,int chPerSample){
		this.dt = dt;
		setChannelsPerSample(chPerSample);
		
		//Dt channel description
		dtChannelDescription =  new DataChannelDescription();
		dtChannelDescription.setName("dt");
		dtChannelDescription.setPrecision(2);			
	}
	
	public void setDt(float dt)
	{
		this.dt = dt;
	}

	public float getDt()
	{
		return dt;
	}

	/**
	 * This sets the number of channels per sample
	 * Warning: It will reset all the channel descriptions to null.  And
	 *  then create one new channel description for the 1st channel.
	 * 
	 * @param chPerSample
	 */
	public void	setChannelsPerSample(int chPerSample)
	{
		if(channelDescriptions == null ||
				chPerSample != channelDescriptions.length) {
			channelDescriptions = new DataChannelDescription [chPerSample];

			//Make sure we have at least one channel description
			DataChannelDescription channelDesc = new DataChannelDescription();
			channelDescriptions[0] = channelDesc;

		}
	}

	public int getChannelsPerSample()
	{
		return channelDescriptions.length;
	}

	/**
	 * Use DATA_SEQUENCE or DATA_SERIES here
	 * @param dataType
	 */
	public void	setDataType(int dataType)
	{
		this.dataType = dataType;
	}

	public int getDataType()
	{
		return dataType;
	}

	public void setDataOffset(int dataOffset)
	{
		this.dataOffset = dataOffset;
	}

	public int getDataOffset()
	{
		return dataOffset;
	}

	public void setNextSampleOffset(int next)
	{
		nextSampleOffset = next;
	}

	/** 
	 * This returns how much the index must be incremented to 
	 * get to the next sample.
	 * @return
	 */
	public int getNextSampleOffset()
	{
		if (nextSampleOffset == -1){
			return channelDescriptions.length;
		}
		return nextSampleOffset;
	}
	
	/**
	 * @return Returns the channelDesc.
	 */
	public DataChannelDescription getChannelDescription()
	{
		return getChannelDescription(0);
	}
	
	
	/**
	 * 
	 * @return Returns the channelDesc.
	 */
	public DataChannelDescription getChannelDescription(int index)
	{
		if (index < 0 || index >= channelDescriptions.length) {
			throw new IndexOutOfBoundsException("channel index: " + index);
		}
		return channelDescriptions[index];
	}

	/**
	 * @param channelDesc The channelDesc to set.  This put the description
	 * at position 0 replacing anything that was there.
	 */
	public void setChannelDescription(DataChannelDescription channelDesc)
	{
		if (channelDescriptions.length < 1) return;
		channelDescriptions[0] = channelDesc;
	}
		
	/**
	 * @param channelDesc The channelDesc to set.
	 * @param index the index of the channel that
	 * this channel description describes
	 */
	public void setChannelDescription(DataChannelDescription channelDesc, int index)
	{
		if (index < 0 || index >= channelDescriptions.length) {
				throw new IndexOutOfBoundsException("channel index: " + index);
		}
		channelDescriptions[index] = channelDesc;
	}
	
	/**
	 * @return Returns the dtChannelDescription.
	 */
	public DataChannelDescription getDtChannelDescription()
	{
		return dtChannelDescription;
	}
	
	/**
	 * @param dtChannelDescription The dtChannelDescription to set.
	 */
	public void setDtChannelDescription(DataChannelDescription dtChannelDescription)
	{
		this.dtChannelDescription = dtChannelDescription;
	}
}
