package org.concord.framework.domain;

import java.util.Vector;

public interface DomainView extends DomainPersistentElement
{
	public DomainView create(String viewSpecification);
	public void release(DomainView view);
	
	public Vector getViewMethods();
	public Vector getViewEvents();
	public Vector getViewActions();
	
	public DomainEngine getEngine();
	
	public void setDomainActivityOwner(Object domainActivityOwner);
	public void closeEverything();
}

