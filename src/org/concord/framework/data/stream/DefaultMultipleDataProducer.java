/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2004-09-09 21:46:39 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.data.stream;


/**
 * DefaultMultipleDataProducer
 * Class name and description
 *
 * Date created: Sep 9, 2004
 *
 * @author imoncada<p>
 *
 */
public class DefaultMultipleDataProducer extends DefaultDataProducer
{

	/**
	 * 
	 */
	public DefaultMultipleDataProducer()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param dt
	 */
	public DefaultMultipleDataProducer(float dt)
	{
		super(dt);
		// TODO Auto-generated constructor stub
	}

	public void addValues(float[] values)
	{
		this.values = values;
		dataEvent.setData(values);
		
		if (dataDesc.getChannelPerSample() != values.length){
			dataDesc.setChannelPerSample(values.length);
			notifyDataStreamEvent(DataEvent.DATA_DESC_CHANGED);
		}
		
		notifyDataReceived();
	}
}
