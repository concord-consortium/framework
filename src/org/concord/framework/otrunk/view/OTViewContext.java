/**
 * 
 */
package org.concord.framework.otrunk.view;

import org.concord.framework.otrunk.OTObject;

/**
 * Use this interface to access services provided to a view.
 * 
 * @author scott
 *
 */
public interface OTViewContext 
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
	
	/**
	 * Use this method to add services to this viewContext.
	 * These services will be inherited from the parent context if there
	 * is one.
	 * Views can access these services by using the {@link OTViewContextAware} 
	 * interface to get a viewContext and then call getViewService on the 
	 * context.
	 * 
	 * @param service
	 */
	public void addViewService(Class serviceClass, Object service);
	
	/**
	 * NOT IMPLEMENTED
	 * 
	 * This is not implemented yet, if your view is a OTJComponentView then
	 * your View can implement OTJComponentViewContextAware, and then use
	 * {@link OTJComponentViewContext#getViewByObject(OTObject)} instead.
	 * 
	 * If this was implemented it should this returns the first view in this context that is viewing
	 * the passed in object.
	 * 
	 * @param obj
	 * @return
	 */
	public OTView getViewByObject(OTObject obj);
	
	/**
	 * Use this method to create a view factory that can be used for child views.
	 * The returned view factory should be reused for all views that should be siblings of each other
	 * and any returned views from the viewFactory should be closed when this view is closed.
	 * @return
	 */
    public OTViewFactory createChildViewFactory();
}
