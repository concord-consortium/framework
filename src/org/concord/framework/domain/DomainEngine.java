package org.concord.framework.domain;

import java.util.Vector;

public interface DomainEngine extends DomainPersistentElement
{
	public DomainEngine create(String domainSpecification);
	public void release(DomainEngine domainEngine);
		
	public Vector getDomainViews();
	public Vector getDomainModels();
	
	public void closeEverything();
}

