

/*
 *  Copyright (C) 2004  The Concord Consortium, Inc.,
 *  10 Concord Crossing, Concord, MA 01741
 *
 *  Web Site: http://www.concord.org
 *  Email: info@concord.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
/*
 * Last modification information:
 * $Revision: 1.4 $
 * $Date: 2004-11-15 17:41:59 $
 * $Author: scytacki $
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
	public DefaultMultipleDataProducer(float dt, int size)
	{
		super(dt);
		getDataDescription().setChannelsPerSample(size);
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
		
		if (dataDesc.getChannelsPerSample() != vals.length){
			dataDesc.setChannelsPerSample(vals.length);
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
