package org.concord.framework.startable;

public class StartableEvent {
	public enum StartableEventType{
		STARTED, STOPPED, RESET 
	}
	
	StartableEventType type;
	Startable startable;

	// This is helpful to know for a started event
	boolean wasInInitialState;
	
	public StartableEvent() {
	}
	
	public StartableEvent(StartableEventType type, Startable startable) {
		this.type = type;
		this.startable = startable;
	}

	public void setType(StartableEventType type) {
		this.type = type;
	}
	
	public StartableEventType getType() {
		return type;
	}
	
	public void setStartable(Startable startable) {
		this.startable = startable;
	}
	
	public Startable getStartable() {
		return startable;
	}
	
	public void setWasInInitialState(boolean wasInInitialState) {
		this.wasInInitialState = wasInInitialState;
	}
	
	public boolean getWasInInitialState() {
		return wasInInitialState;
	}
}
