/**
 * 
 */
package org.concord.framework.otrunk.view;

/**
 * Use this interface to access services provided to a view.
 * 
 * @author scott
 *
 */
public interface OTViewServiceProvider 
{
	/**
	 * Use this method to get a service for example:
	 * OTFrameManager frameManager = 
	 *    (OTFrameManager)serviceProvider.getViewService(OTFrameManager.class);
	 * 
	 * If this framework is moved to java 1.5 then generics can be used to make
	 * this easier to use.
	 * 
	 * @param serviceClass this can either be a class or interface.   
	 * @return the instance of this serviceClass available to a view.  Or null
	 *   if there is no service implementing this interface.
	 */
	public Object getViewService(Class serviceClass);
}
