package org.concord.framework.otrunk;

public interface OTCollection 
{
	public int size();
	
	public void clear();
	
	/**
	 * @deprecated use clear instead.
	 */
	public void removeAll();

	public boolean isEmpty();	
}
