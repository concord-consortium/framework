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
 * DataStreamEvent
 * Class name and description
 *
 * Date created: Aug 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class DataStreamEvent extends DataEvent
{
	public static final int DATA_RECEIVED 		= 1000;
	public static final int DATA_COLLECTING 	= 1001;
	public static final int DATA_READY_TO_START = 1002;
	public static final int DATA_STOPPED        = 1003;
	public static final int DATA_DESC_CHANGED   = 1004;

	public int type;

	public float 	[]data = null;
	public DataStreamDescription	dataDesc = null;
	public int		numSamples = 1;
	public int	 	[]intData = null;
	public float    refVal = 0;

    public int [] pTimes = new int [10];
    public int numPTimes = 0;

	public DataStreamEvent()
	{
		this(DATA_RECEIVED, null, null, null);
	}

	public DataStreamEvent(int type)
	{
		this(type, null, null, null);
	}

	public DataStreamEvent(int type, float[] data, DataStreamDescription dataDesc)
	{
		this(type, data, null, dataDesc);
	}
	
	public DataStreamEvent(int type, float[] data, int[] intData, DataStreamDescription dataDesc)
	{
		this.type 		= type;
		this.data 		= data;
		this.intData 	= intData;
		this.dataDesc 	= dataDesc;
	}
	
	public void setData(float[] data)
	{
		this.data = data;
	}

	public float[] getData()
	{
		return data;
	}

	public void setIntData(int[] data)
	{
		this.intData = data;
	}

	public int[] getIntData()
	{
		return intData;
	}

	public void setDataDescription(DataStreamDescription dataDesc)
	{ 
		this.dataDesc = dataDesc;
	}

	public DataStreamDescription getDataDescription()
	{
		return dataDesc;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getType()
	{
		return type;
	}

	public void setNumSamples(int numSamples)
	{
		this.numSamples = numSamples;
	}

	public int getNumSamples()
	{
		return numSamples;
	}
}
