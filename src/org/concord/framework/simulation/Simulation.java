

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
package org.concord.framework.simulation;

import org.concord.framework.data.DataFlow;

public interface Simulation extends DataFlow{

public static final int SIM_UNDEF_STATE 	= 0;
public static final int SIM_RESET_STATE 	= 1;
public static final int SIM_STOP_STATE 		= 2;
public static final int SIM_RUN_STATE 		= 3;

	
	
/** isRunning
 * @return <code>true</code> if simulation is running
 *     or <code>false</code> if simulation was reset
 *
*/
	public boolean isRunning();

/** nextStep
 * calculates next simulation's step
 *
*/
	public void nextStep();
	
/** continueSteps
 * continues simulation
*/
	public void continueSteps();
	
/** doOneStep
 * does only one step
*/
	public void doOneStep();
	
	
	public int getSimulationState();
	
	public int getAvailableSteps();
	
	public int getCurrentStepNumber();
	
	public void goToStep(int stepNumber);
	
	public void gotoPreviousStep();

	/**
	 * Add a simulation listener 
	 * @param l	simulation listener to add
	 */
	public void addSimulationListener(SimulationListener l);	

	/**
	 * Remove a simulation listener 
	 * @param l	simulation listener to remove
	 */
	public void removeSimulationListener(SimulationListener l);	
}
