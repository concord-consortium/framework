package org.concord.framework.domain;

import java.util.Vector;

public interface DomainModel extends DomainPersistentElement
{
	public DomainModel create(String modelSpecification);
	public void release(DomainModel view);
	
	public Vector getModelMethods();
	public Vector getModelEvents();
	public Vector getModelActions();
}

