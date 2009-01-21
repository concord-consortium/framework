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
	 * If this viewContext doesn't have the service then the getViewService method
	 * on the parent view context will be called.
	 * 
	 * @param serviceClass this can either be a class or interface.   
	 * @return the instance of this serviceClass available to a view.  Or null
	 *   if there is no service implementing this interface.
	 */
	public <T> T getViewService(Class<T> serviceClass);
	
	/**
	 * Use this method to add services to this viewContext.
	 * Views can access these services by using the {@link OTViewContextAware} 
	 * interface to get a viewContext and then call getViewService on the 
	 * context.
	 * 
	 * @param service
	 */
	public <T> void addViewService(Class<T> serviceClass, T service);
	
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

    /**
     * Get the value of a property.  If this viewContext doesn't have the property then
     * getProperty on the parent will be called.  These properties can be used by parent
     * views to customize their children.
     * 
     * @param propertyName
     * @return
     */
    public String getProperty(String propertyName);
    
    /**
     * Add a property to this viewContext.  Setting it to null will override any parent
     * view context and return null for this property.  Use unsetProperty to remove this
     * property from this viewContext.
     * 
     * @param propertyName
     * @param propertyValue
     */
    public void setProperty(String propertyName, String propertyValue);
    
    public void unsetProperty(String propertyName);
}
