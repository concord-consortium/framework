/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2004-09-10 19:20:49 $
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
	boolean valuesSent = true;
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

	public void addValues(float[] vals)
	{
		addValues(vals, true);
	}
	
	public void addValues(float[] vals, boolean bSendValues)
	{
		float[] tempValues;
		if (!valuesSent){
			tempValues = new float[vals.length + this.values.length];
			for (int i=0; i<tempValues.length; i++){
				if (i < this.values.length){
					tempValues[i] = this.values[i];
				}
				else{
					tempValues[i] = vals[i - this.values.length];
				}
			}
			
			dataEvent.setNumSamples(dataEvent.getNumSamples()+1);
		}
		else if (!bSendValues){
			tempValues = new float[vals.length];
			for (int i=0; i<tempValues.length; i++){
				tempValues[i] = vals[i];
			}
			
			dataEvent.setNumSamples(1);
		}
		else{
			tempValues = vals;
		}
		this.values = tempValues;
		
		dataEvent.setData(this.values);
		
		if (dataDesc.getChannelPerSample() != vals.length){
			dataDesc.setChannelPerSample(vals.length);
			notifyDataStreamEvent(DataEvent.DATA_DESC_CHANGED);
		}
		
		if (bSendValues){
			notifyDataReceived();
			valuesSent = true;
		}
		else{
			valuesSent = false;		
		}
	}
}
