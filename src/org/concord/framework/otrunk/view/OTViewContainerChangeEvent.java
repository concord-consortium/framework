package org.concord.framework.otrunk.view;

import java.util.EventObject;

import javax.swing.event.ChangeEvent;

import org.concord.framework.otrunk.OTObject;

public class OTViewContainerChangeEvent extends EventObject
{
	// If there was no object in the ContainerPanel and a new one is being added
	public static int NEW_CURRENT_OBJECT_EVT = 0;
	
	// If the current object is being replaced with itself (usually if 
	// getUpdateableContainer.reloadView was called)
	public static int CHANGE_CURRENT_OBJECT = 1;
	
	// If the current object is being replaced with a new object
	public static int REPLACE_CURRENT_OBJECT_EVT = 2;
	
	// If the current object is null
	public static int DELETE_CURRENT_OBJECT_EVT = 3;
	
	private int eventType;
	
	private OTObject previousObject;
	
	public OTViewContainerChangeEvent(OTViewContainer source, int eventType)
    {
	    this(source, eventType, null);
    }
	
	public OTViewContainerChangeEvent(OTViewContainer source, int eventType, OTObject previousObject)
    {
	    super(source);
	    this.eventType = eventType;
	    this.previousObject = previousObject;
    }
	
	public int getEventType(){
		return eventType;
	}
	
	public OTObject getValue(){
		return ((OTViewContainer)source).getCurrentObject();
	}
	
	public OTObject getPreviousObject(){
		return previousObject;
	}
	
}
