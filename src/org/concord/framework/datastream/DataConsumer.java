package org.concord.framework.datastream;

public interface DataConsumer 
{
	public void addDataSource(DataProducer source);

	public void removeDataSource(DataProducer source);
}
