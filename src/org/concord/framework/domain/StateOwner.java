package org.concord.framework.domain;


public interface StateOwner
{
	public Object getState();
	public void setState(Object state);
}

