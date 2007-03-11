/**
 * 
 */
package org.concord.framework.otrunk.view;


/**
 * @author scott
 *
 */
public abstract class AbstractOTView implements OTViewServiceProviderAware,
		OTView 
{
	protected OTViewServiceProvider serviceProvider;
	
	/* (non-Javadoc)
	 * @see org.concord.framework.otrunk.view.OTViewServiceProviderAware#setViewServiceProvider(org.concord.framework.otrunk.view.OTViewServiceProvider)
	 */
	public void setViewServiceProvider(OTViewServiceProvider serviceProvider) 
	{
		this.serviceProvider = serviceProvider;
	}

	protected Object getViewService(Class service)
	{
		return serviceProvider.getViewService(service);
	}
}
