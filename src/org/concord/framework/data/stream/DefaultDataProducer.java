package org.concord.framework.data.stream;

import java.util.Vector;
import org.concord.framework.data.*;

public class DefaultDataProducer
	implements DataProducer
{
	protected Vector dataListeners = new Vector();

	protected float [] values = new float [1];
	protected DataStreamDescription dataDesc;
	protected DataStreamEvent dataEvent;

	public DefaultDataProducer()
	{
		this(1.0f);
	}

	public DefaultDataProducer(float dt)
	{
		dataDesc = new DataStreamDescription();
		dataDesc.setDt(dt);

		dataEvent = new DataStreamEvent(DataStreamEvent.DATA_RECEIVED, values, null, dataDesc);
	}

	public void addDataListener(DataListener listener)
	{
		dataListeners.addElement(listener);
	}
	
	public void removeDataListener(DataListener listener)
	{
		dataListeners.removeElement(listener);
	}

	public DataStreamDescription getDataDescription()
	{
		return dataDesc;
	}

	/**
	 * @see org.concord.framework.data.DataFlow#stop()
	 */
	public void stop()
	{
	}

	/**
	 * @see org.concord.framework.data.DataFlow#start()
	 */
	public void start()
	{
	}
	
	/**
	 * @see org.concord.framework.data.DataFlow#reset()
	 */
	public void reset()
	{		
	}
	
	protected void notifyDataStreamEvent(int type)
	{
		dataEvent.setType(type);
		for(int i=0; i<dataListeners.size(); i++) {
			DataListener dataListener = (DataListener)dataListeners.elementAt(i);
			if(dataListener != null)
			{
				dataListener.dataStreamEvent(dataEvent);
			}
		}
		dataEvent.setType(DataStreamEvent.DATA_RECEIVED);
	}

	public void addValue(float value)
	{
		values[0] = value;
		
		notifyDataReceived();
	}

	protected void notifyDataReceived()
	{
		dataEvent.setType(DataStreamEvent.DATA_RECEIVED);
		for(int i=0; i<dataListeners.size(); i++) {
			DataListener dataListener = (DataListener)dataListeners.elementAt(i);
			if(dataListener != null)
			{
				dataListener.dataReceived(dataEvent);
			}
		}
	}
	
	public void setUnit(DataDimension unit)
	{
		dataDesc.getChannelDescription().setUnit(unit);
		notifyDataStreamEvent(DataEvent.DATA_DESC_CHANGED);
	}

	public void setDt(float dt)
	{
		dataDesc.setDt(dt);
		notifyDataStreamEvent(DataEvent.DATA_DESC_CHANGED);
	}

}
