package org.concord.framework.data.stream;

public interface DataListener
{
	public void dataReceived(DataEvent dataEvent);

	public void dataStreamEvent(DataEvent dataEvent);
}

