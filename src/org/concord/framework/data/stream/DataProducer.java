package org.concord.framework.data.stream;

import org.concord.framework.data.DataFlow;

public interface DataProducer 
	extends DataFlow
{
	public void addDataListener(DataListener listener);
	
	public void removeDataListener(DataListener listener);

	public DataStreamDescription getDataDescription();
	
}
