package org.concord.framework.data.stream;

public interface DataConsumer 
{
	public void addDataProducer(DataProducer source);

	public void removeDataProducer(DataProducer source);
}
