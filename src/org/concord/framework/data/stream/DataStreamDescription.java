package org.concord.framework.data.stream;

import java.util.Vector;

public class DataStreamDescription
{
	/**
	 * This set of data uses the dt because it is one after the
	 * other in time: sequencial data
	 */  
	public final static int DATA_SEQUENCE = 0;
	
	/**
	 * This set of data is a collection of points that is not
	 * sequential in time.
	 */ 
	public final static int DATA_SERIES = 1;

	private int dataType = DATA_SEQUENCE;
	private int channelPerSample;
	private float dt;
	private int dataOffset = 0;
	private int nextSampleOffset = -1;
	
	private Vector channelDescriptions;	//DataChannelDescription objects

	public DataStreamDescription(){
		this(0.0f,1);
	}

	public DataStreamDescription(float dt,int chPerSample){
		this.dt = dt;
		this.channelPerSample 	= chPerSample;
		channelDescriptions = new Vector();
		DataChannelDescription channelDesc = new DataChannelDescription();
		channelDescriptions.add(channelDesc);
	}
	
	public void setDt(float dt)
	{
		this.dt = dt;
	}

	public float getDt()
	{
		return dt;
	}

	public void	setChannelPerSample(int chPerSample)
	{
		this.channelPerSample = chPerSample;
	}

	public int getChannelPerSample()
	{
		return channelPerSample;
	}

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
			return channelPerSample;
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
	 * @return Returns the number of channelDesc.
	 */
	public int getNumbChannelDescription(){
	    if(channelDescriptions == null) return 0;
	    return channelDescriptions.size();
	}
	
	/**
	 * @return Returns the channelDesc.
	 */
	public DataChannelDescription getChannelDescription(int index)
	{
		if (index < 0 || index >= channelDescriptions.size()) return null;
		return (DataChannelDescription)channelDescriptions.elementAt(index);
	}

	/**
	 * @param channelDesc The channelDesc to set.
	 */
	public void setChannelDescription(DataChannelDescription channelDesc)
	{
		channelDescriptions.removeAllElements();
		addChannelDescription(channelDesc);
	}
	
	/**
	 * @param channelDesc The channelDesc to set.
	 */
	public void addChannelDescription(DataChannelDescription channelDesc)
	{
		channelDescriptions.add(channelDesc);
	}
}
