package org.concord.framework.simulation;

public interface Simulation{

public static final int SIM_UNDEF_STATE 	= 0;
public static final int SIM_RESET_STATE 	= 1;
public static final int SIM_STOP_STATE 		= 2;
public static final int SIM_RUN_STATE 		= 3;

/** stop
 * stop simulation
*/
	public void stop();
	
/** reset
 * reset simulation
*/
	public void reset();
	
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
	
/** start
 * start simulation
*/
	public void start();

/** doOneStep
 * does only one step
*/
	public void doOneStep();
	
	
	public int getSimulationState();
	
	public int getAvailableSteps();
	
	public int getCurrentStepNumber();
	
	public void goToStep(int stepNumber);
	
	public void gotoPreviousStep();
}
