package org.concord.framework.datastream;

public interface DataSource
{
	public void addDataListener(DataListener listener);
	
	public void removeDataListener(DataListener listener);

	public DataStreamDescription getDataDescription();

	public void stop();

	public void start();
	
}
