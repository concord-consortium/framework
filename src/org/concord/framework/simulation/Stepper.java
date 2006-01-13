package org.concord.framework.simulation;

public interface Stepper 
{
	public void addStepListener(StepListener listener);
	
	public void removeStepListener(StepListener listener);
}
