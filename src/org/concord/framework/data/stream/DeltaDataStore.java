/*
 * Created on Mar 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.concord.framework.data.stream;

/**
 * @author scott
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface DeltaDataStore extends DataStore
{
	/**
	 * If this is true then channel -1 is time.  This channel
	 * is does not need to be stored in the data store.
	 * @return 
	 */
	public boolean isUseDtAsChannel();
	
	public float getDt();
}
