package org.concord.framework.data.stream;

public class DataEvent
{
	public static final int DATA_DESC_CHANGED = 1004;

	public int type;

	public DataStreamDescription dataDesc = null;
	
	public DataEvent()
	{
		this(0);
	}

	public DataEvent(int type)
	{
		this.type = type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}

	public int getType()
	{
		return type;
	}

	public void setDataDescription(DataStreamDescription dataDesc)
	{ 
		this.dataDesc = dataDesc;
	}

	public DataStreamDescription getDataDescription()
	{
		return dataDesc;
	}

}
