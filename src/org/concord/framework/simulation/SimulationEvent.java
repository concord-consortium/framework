

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
 * $Revision: 1.3 $
 * $Date: 2004-11-12 18:40:24 $
 * $Author: eblack $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.simulation;

import java.util.EventObject;


/**
 * SimulationEvent
 * Class name and description
 *
 * Date created: Aug 24, 2004
 *
 * @author imoncada<p>
 *
 */
public class SimulationEvent extends EventObject
{
	//The source of this event should be a Simulation object
	
	private int previousSimulationState;	//The same states that Simulation class
	private int simulationState;	//The same states that Simulation class
	
	/**
	 * @param source
	 */
	public SimulationEvent(Object source)
	{
		super(source);
	}

	/**
	 * @param source
	 */
	public SimulationEvent(Object source, int oldState, int newState)
	{
		super(source);
		this.previousSimulationState = oldState;
		this.simulationState = newState;
	}
	
	/**
	 * @return Returns the simulationState.
	 */
	public int getSimulationState()
	{
		return simulationState;
	}
	
	/**
	 * @param simulationState The simulationState to set.
	 */
	public void setSimulationState(int simulationState)
	{
		this.simulationState = simulationState;
	}
	/**
	 * @return Returns the previousSimulationState.
	 */
	public int getPreviousSimulationState()
	{
		return previousSimulationState;
	}
	/**
	 * @param previousSimulationState The previousSimulationState to set.
	 */
	public void setPreviousSimulationState(int previousSimulationState)
	{
		this.previousSimulationState = previousSimulationState;
	}
}
