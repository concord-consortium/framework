/*
 * Last modification information:
 * $Revision: 1.2 $
 * $Date: 2004-08-27 17:00:37 $
 * $Author: imoncada $
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
