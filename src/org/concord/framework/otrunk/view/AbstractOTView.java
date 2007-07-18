/**
 * 
 */
package org.concord.framework.otrunk.view;


/**
 * @author scott
 *
 */
public abstract class AbstractOTView implements OTViewContextAware,
		OTView 
{
	protected OTViewContext viewContext;
	protected OTViewFactory viewFactory;
	
	/* (non-Javadoc)
	 * @see org.concord.framework.otrunk.view.OTViewServiceProviderAware#setViewServiceProvider(org.concord.framework.otrunk.view.OTViewServiceProvider)
	 */
	public void setViewContext(OTViewContext serviceProvider) 
	{
		this.viewContext = serviceProvider;
	}

	protected Object getViewService(Class service)
	{
		return viewContext.getViewService(service);
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
		if(viewFactory != null){
			return viewFactory;
		}
		
		viewFactory = viewContext.createChildViewFactory();
		
		return viewFactory;
	}
}
