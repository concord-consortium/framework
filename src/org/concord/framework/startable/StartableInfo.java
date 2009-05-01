package org.concord.framework.startable;

public class StartableInfo {
	public boolean enabled = true;
	public boolean canResetWhileRunning = false;
	public boolean canRestartWithoutReset = false;
	public String label;
	public String startVerb;
	public String stopVerb;
	public String resetVerb;
	public String startStopVerb;
}
