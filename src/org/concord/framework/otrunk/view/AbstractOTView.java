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
	
	/**
	 * Utility class to make it easy to get the frame manager
	 * @return
	 */
	public OTFrameManager getFrameManager() 
	{
		return (OTFrameManager) getViewService(OTFrameManager.class);
	}

	/**
	 * Utility class to make it easy to get the view factory
	 * @return
	 */
	public OTViewFactory getViewFactory() 
	{
		return (OTViewFactory) getViewService(OTViewFactory.class);
	}
}
