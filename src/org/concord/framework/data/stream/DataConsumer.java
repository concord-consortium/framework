package org.concord.framework.data.stream;

public interface DataConsumer 
{
	public void addDataSource(DataProducer source);

	public void removeDataSource(DataProducer source);
}
