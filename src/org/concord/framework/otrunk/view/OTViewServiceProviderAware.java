/**
 * 
 */
package org.concord.framework.otrunk.view;

/**
 * Implement this interface if your OTView needs to access services.
 * 
 * @author scott
 *
 */
public interface OTViewServiceProviderAware extends OTView 
{
	/**
	 * This will be called after the view is created before any other
	 * methods are called on the view.
	 * 
	 * @param serviceProvider
	 */
	public void setViewServiceProvider(OTViewServiceProvider serviceProvider);
}
