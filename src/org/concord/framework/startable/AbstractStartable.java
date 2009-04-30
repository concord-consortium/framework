package org.concord.framework.startable;

import java.util.ArrayList;

import org.concord.framework.startable.StartableEvent.StartableEventType;

public abstract class AbstractStartable implements Startable 
{
	protected ArrayList<StartableListener> startableListeners = 
		new ArrayList<StartableListener>();
	protected StartableEvent startableEvent;  
	
	public AbstractStartable() {
		startableEvent = new StartableEvent();
		startableEvent.setStartable(this);
	}
	
	public void addStartableListener(StartableListener listener) 
	{
		if(startableListeners.contains(listener)){
			return;
		}
		startableListeners.add(listener);
	}

	public void removeStartableListener(StartableListener listener) 
	{
		startableListeners.remove(listener);
	}

	public StartableInfo getStartableInfo() {
		return null;
	}

	public boolean isInInitialState() {
		return true;
	}

	protected void notifyStarted(boolean wasInInitialState)
	{
		startableEvent.setWasInInitialState(wasInInitialState);
		notifyStartableListeners(StartableEventType.STARTED);
		
		// set it back to its default value 
		startableEvent.setWasInInitialState(false);		
	}

	protected void notifyStopped()
	{
		notifyStartableListeners(StartableEventType.STOPPED);		
	}
	
	protected void notifyReset()
	{
		notifyStartableListeners(StartableEventType.RESET);		
	}
	

	private void notifyStartableListeners(StartableEventType eventType)
	{
		startableEvent.setType(eventType);
		for (StartableListener listener : startableListeners) {
			listener.startableEvent(startableEvent);			
		}
	}
}
