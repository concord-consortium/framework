package org.concord.framework.data.stream;

import java.util.Vector;
import org.concord.framework.data.*;

public class DefaultDataProducer
	implements DataProducer
{
	Vector dataListeners = new Vector();

	float [] values = new float [1];
	DataStreamDescription dataDesc;
	DataEvent dataEvent;

	public DefaultDataProducer()
	{
		dataDesc = new DataStreamDescription();
		dataDesc.setDt(1.0f);

		dataEvent = new DataEvent(DataEvent.DATA_RECEIVED, values, null, dataDesc);
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

	public void stop()
	{
	}

	public void start()
	{
	}
	
	/* (non-Javadoc)
	 * @see org.concord.framework.data.DataFlow#reset()
	 */
	public void reset()
	{		
	}
	
	void dataStreamEvent(int type)
	{
		dataEvent.setType(type);
		for(int i=0; i<dataListeners.size(); i++) {
			DataListener dataListener = (DataListener)dataListeners.elementAt(i);
			if(dataListener != null)
			{
				dataListener.dataStreamEvent(dataEvent);
			}
		}
		dataEvent.setType(DataEvent.DATA_RECEIVED);
	}

	public void addValue(float value)
	{
		values[0] = value;
		dataEvent.setType(DataEvent.DATA_RECEIVED);
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
		dataDesc.setUnit(unit);
		dataStreamEvent(DataEvent.DATA_DESC_CHANGED);
	}

	public void setDt(float dt)
	{
		dataDesc.setDt(dt);
		dataStreamEvent(DataEvent.DATA_DESC_CHANGED);
	}

}
