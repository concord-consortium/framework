package org.concord.framework.datastream;

public interface DataSink 
{
	public void addDataSource(DataSource source);

	public void removeDataSource(DataSource source);
}
