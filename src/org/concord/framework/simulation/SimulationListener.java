/*
 * Last modification information:
 * $Revision: 1.1 $
 * $Date: 2004-08-25 00:44:01 $
 * $Author: imoncada $
 *
 * Licence Information
 * Copyright 2004 The Concord Consortium 
*/
package org.concord.framework.simulation;

/**
 * SimulationListener
 * Class name and description
 *
 * Date created: Aug 24, 2004
 *
 * @author imoncada<p>
 *
 */
public interface SimulationListener
{
	public void simulationStarted(SimulationEvent evt);
	public void simulationStopped(SimulationEvent evt);
	public void simulationReset(SimulationEvent evt);
}
