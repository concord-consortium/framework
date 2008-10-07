package org.concord.framework.otrunk.otcore;



public interface OTClassProperty
{
	String getName();
	
	OTType getType();
	
	Object getDefault();
	
	boolean isPrimitive();
	
	boolean isList();
	
	boolean isMap();	
	
	boolean isOnlyInOverlayProperty();
	
	public boolean isOverriddenProperty();
	
	public OTClassProperty getOnlyInOverlayProperty();
	
	public OTClassProperty getOverriddenProperty();
}
