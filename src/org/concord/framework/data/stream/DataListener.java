package org.concord.framework.data.stream;

public interface DataListener
{
	public void dataReceived(DataStreamEvent dataEvent);

	public void dataStreamEvent(DataStreamEvent dataEvent);
}

