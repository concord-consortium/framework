package org.concord.framework.datastream;

public interface DataProducer
{
	public void addDataListener(DataListener listener);
	
	public void removeDataListener(DataListener listener);

	public DataStreamDescription getDataDescription();

	public void stop();

	public void start();
	
}
