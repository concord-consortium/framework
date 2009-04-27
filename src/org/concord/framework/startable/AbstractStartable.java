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
	
	protected void notifyStartableListeners(StartableEventType eventType)
	{
		startableEvent.setType(eventType);
		for (StartableListener listener : startableListeners) {
			listener.startableEvent(startableEvent);			
		}
	}
}
