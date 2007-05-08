/**
 * 
 */
package org.concord.framework.otrunk;


/**
 * The Bundle name is taken from the term in OSGi
 * These bundles are currently lighter weight
 * 
 * @author scott
 *
 */
public interface OTBundle
{
	/**
	 * This method is called first.  The bundle should add any services
	 * it provides to the serviceContext.  All known bundles will be registered
	 * like this before initializeBundle is called.  This allows bundles to 
	 * access the services provided by other bundles during initializeBundle 
	 * 
	 * @param serviceContext
	 */
	public void registerServices(OTServiceContext serviceContext);
	
	public void initializeBundle(OTServiceContext serviceContext);
}
