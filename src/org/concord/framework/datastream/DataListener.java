package org.concord.framework.datastream;

public interface DataListener
{
	public void dataReceived(DataEvent dataEvent);

	public void dataStreamEvent(DataEvent dataEvent);
}

